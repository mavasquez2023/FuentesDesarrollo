/**
 * 
 */
package conector.integracion.as400;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.AS400Text;
import com.ibm.as400.access.CharConverter;
import com.ibm.as400.access.CommandCall;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.ObjectDoesNotExistException;
import com.ibm.as400.access.ProgramCall;
import com.ibm.as400.access.ProgramParameter;

import conector.configuracion.ClasspathConfig;
import conector.configuracion.excepciones.ConfiguracionException;
import conector.configuracion.excepciones.ConfiguracionParametroNoEncontradoException;
import conector.integracion.as400.excepciones.ConexionAS400SysException;
import conector.integracion.as400.excepciones.ConexionJDBCAS400SysException;
import conector.integracion.as400.excepciones.EjecucionAS400SysException;
import conector.integracion.as400.excepciones.EjecucionJDBCAS400SysException;
import conector.integracion.as400.vo.Estructura;
import conector.integracion.as400.vo.Parametro;

/**
 * Realiza la conexión y ejecución de sentencias al AS400, vía: JDBC.
 * 
 * @author amartoq@microsystem.cl
 */
public class InvocadorAS400 {
    /** Serial Version UID. */
    public static final long serialVersionUID = 1L;

    /** Nombre por omisión del datasource */
    public static final String DEFAULT_DATASOURCE = "as400/datasource";

    /** Nombre del archivo de configuración dentro del classpath */
    public static final String CONFIG_RESOURCE = "/as400.properties";

    /**
     * Nombre de computador o dirección IP; utilizado en ejecución directa via
     * Toolkit.
     */
    private String host;

    /**
     * Nombre de usuario; utilizado en ejecución directa vía Toolkit.
     */
    private String user;

    /**
     * Clave de usuario; utilizado en ejecución directa vía Toolkit.
     */
    private String pass;

    /**
     * Lista de bibliotecas a incluir con ADDLIBLE, separadas por espacio.
     */
    private String libs;

    /**
     * Lista de bibliotecas a incluir con ADDLIBLE.
     */
    private List bibliotecas = new ArrayList();

    /**
     * Objeto de conexión al AS400.
     */
    private AS400 as400;

    /**
     * El nombre de la conexion de este objeto.
     */
    private String nombreConexion;

    /**
     * Objeto con la configuración de este invocador.
     */
    private ClasspathConfig config;

    /**
     * Crea una conexión JDBC al AS400, utilizando el nombre de datasource por
     * omisión: "as400/datasource".
     * 
     * @return una conexión JDBC.
     * @throws ConexionAS400SysException
     *             si ocurre un error al conectarse.
     */
    protected static Connection conectarDataSource() throws ConexionAS400SysException {
        /* conexión al datasource por omisión */
        return conectarDataSource(DEFAULT_DATASOURCE);
    }

    /**
     * Crea una conexión JDBC al AS400, utilizando el nombre de datasource por
     * omisión: "as400/datasource".
     * 
     * @return una conexión JDBC.
     * @throws ConexionAS400SysException
     *             si ocurre un error al conectarse.
     */
    protected static Connection conectarDataSource(String nombreDatasource) throws ConexionAS400SysException {
        try {
            /* buscamos datasource JNDI */
            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext.lookup(nombreDatasource);

            /* obtenemos conexión y retornamos */
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new ConexionAS400SysException(e, nombreDatasource);
        } catch (NamingException e) {
            throw new ConexionAS400SysException(e, nombreDatasource);
        }
    }

    /**
     * Crea un nuevo invocador de AS400.
     * 
     * @param nombreConexion
     *            identificador para obtener los parámetros desde el archivo de
     *            configuración.
     * @throws ConfiguracionException
     *             si ocurre un error al leer los parámetros de configuración.
     */
    public InvocadorAS400(String nombreConexion) throws ConfiguracionException {
        this.nombreConexion = nombreConexion;
        config = new ClasspathConfig(CONFIG_RESOURCE);
        host = getConfigString("host");
        user = getConfigString("user");
        pass = getConfigString("pass");
        libs = getConfigString("libs");

        String tmp[] = libs.split(" ");
        for (int i = 0; i < tmp.length; i++) {
            String lib = tmp[i].trim();
            if (lib.length() == 0) {
                continue;
            }
            bibliotecas.add(lib);
        }
    }

    /**
     * Retorna el objeto utilizado para la configuración de este invocador.
     * 
     * @return el objeto utilizado para la configuración de este invocador.
     * @throws ConfiguracionParametroNoEncontradoException
     *             si ocurre un error al leer los parámetros de configuración.
     */
    public String getConfigString(String param) throws ConfiguracionParametroNoEncontradoException {
        return config.getString(nombreConexion + "." + param);
    }

    /**
     * Ejecuta un programa directamente en el AS400, utilizando el API de IBM
     * Java Toolkit.
     * 
     * @throws ConexionAS400SysException
     *             si ocurre un error al conectarse al AS400.
     * @throws EjecucionAS400SysException
     *             si ocurre un error al ejecutar el programa en AS400.
     */
    public void ejecutar(final String rutaPrograma, final Estructura estructura) throws EjecucionAS400SysException,
            ConexionAS400SysException {
        try {
            prepararConexion(rutaPrograma);
            invocar(as400, rutaPrograma, estructura);
        } catch (ConexionAS400SysException e) {
            throw e;
        } catch (Throwable t) {
            throw new EjecucionAS400SysException(t, host, user, pass, libs, rutaPrograma);
        } finally {
            terminarConexion();
        }
    }

    /**
     * Retorna una conexión JDBC según la configuración definida.
     * 
     * @return una conexión JDBC.
     * @throws ConexionJDBCAS400SysException
     *             si ocurre un error al conectarse.
     */
    public Connection conectarJDBC() throws ConexionJDBCAS400SysException {
        String url = "jdbc:as400://" + host + "/";
        if (bibliotecas.size() > 0) {
            url += bibliotecas.get(0);
        }
        try {
            Class.forName("com.ibm.as400.access.AS400JDBCDriver");
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
//            throw new ConexionJDBCAS400SysException(e, url, user, user);
            throw new ConexionJDBCAS400SysException(e, url, user, user, libs);
        } catch (ClassNotFoundException e) {
//            throw new ConexionJDBCAS400SysException(e, url, user, pass);
            throw new ConexionJDBCAS400SysException(e, url, user, user, libs);
        }
    }

    public int ejecutarUpdateSQL(String sentencia) throws ConexionJDBCAS400SysException, EjecucionJDBCAS400SysException {
        Connection connection = conectarJDBC();
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sentencia);
        } catch (SQLException e) {
            throw new EjecucionJDBCAS400SysException(e, host, user, pass, libs, sentencia);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                // Uffff... ignoramos.
            }
        }
    }

    /**
     * Ejecuta una consulta SQL, retornando un lista de mapas con el resultado.
     * 
     * @param consulta
     *            sentencia SQL a ejecutar.
     * @return una lista de Map, donde la key es el nombre del campo y el value
     *         es el valor del campo en esa fila.
     * @throws ConfiguracionException
     *             si hay un error en la configuración.
     * @throws ConexionJDBCAS400SysException
     *             si ocurre un error al conectarse.
     * @throws EjecucionJDBCAS400SysException
     *             si ocurre un error al ejecutar la consulta SQL.
     * 
     * @author Aldrin Martoq - amartoq@microsystem.cl
     */
    public List ejecutarConsultaSQL(String consulta) throws ConfiguracionException, ConexionJDBCAS400SysException,
            EjecucionJDBCAS400SysException {
        Connection connection = conectarJDBC();
        List result = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            String[] columnas = new String[resultSetMetaData.getColumnCount()];
            for (int i = 0; i < columnas.length; i++) {
                columnas[i] = resultSetMetaData.getColumnName(i + 1);
            }
            while (resultSet.next()) {
                Map map = new HashMap();
                for (int i = 0; i < columnas.length; i++) {
                    map.put(columnas[i], resultSet.getString(i + 1));
                }
                result.add(map);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new EjecucionJDBCAS400SysException(e, host, user, pass, libs, consulta);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                // Uffff... ignoramos.
            }
        }
        return result;
    }

    private void prepararConexion(String rutaPrograma) throws ConexionAS400SysException, EjecucionAS400SysException {
        try {
            as400 = new AS400(host, user, pass);
            CommandCall commandCall = new CommandCall(as400);
            for (Iterator iterator = bibliotecas.iterator(); iterator.hasNext();) {
                String cmd = "ADDLIBLE " + iterator.next() + " *LAST";
                log("ejecutando: " + cmd);
                commandCall.run(cmd);
            }
        } catch (AS400SecurityException e) {
            throw new EjecucionAS400SysException(e, host, user, pass, libs, rutaPrograma);
        } catch (ErrorCompletingRequestException e) {
            throw new EjecucionAS400SysException(e, host, user, pass, libs, rutaPrograma);
        } catch (IOException e) {
            throw new EjecucionAS400SysException(e, host, user, pass, libs, rutaPrograma);
        } catch (InterruptedException e) {
            throw new EjecucionAS400SysException(e, host, user, pass, libs, rutaPrograma);
        } catch (PropertyVetoException e) {
            throw new EjecucionAS400SysException(e, host, user, pass, libs, rutaPrograma);
        } finally {
        }
    }

    private void terminarConexion() {
        try {
            as400.disconnectAllServices();
        } catch (Throwable t) {
            System.err.println("Ignorando error en desconexión: " + t);
            t.printStackTrace();
        }
    }

    private void invocar(AS400 as400, String rutaPrograma, Estructura estructura) throws ConexionAS400SysException,
            EjecucionAS400SysException {
        try {
            CharConverter charConverter = new CharConverter(as400.getCcsid());

            log("numero parámetros: " + estructura.getNumeroParametros());
            ProgramParameter[] programParameters = new ProgramParameter[estructura.getNumeroParametros()];
            for (int i = 0; i < programParameters.length; i++) {
                Parametro parametro = estructura.getParametro(i);
                AS400Text as400Text = new AS400Text(parametro.largo);
                String data = estructura.getBufferParametro(0, parametro);
                byte[] bytes = null;
                if (parametro.isSalida) {
                    bytes = new byte[parametro.largo];
                } else {
                    bytes = as400Text.toBytes(data);
                }
                programParameters[i] = new ProgramParameter(bytes, parametro.largo);
                log("enviado: " + parametro.largo + " - " + parametro + " = [" + data + "]");
            }

            ProgramCall programCall = new ProgramCall(as400);
            programCall.setProgram(rutaPrograma, programParameters);
            log("ejecutando: " + rutaPrograma);
            if (!programCall.run()) {
                // FIXME: agregar mensajes de salida a la excepción.
                AS400Message[] messages = programCall.getMessageList();
                for (int i = 0; i < messages.length; i++) {
                }
            }

            for (int i = 0; i < programParameters.length; i++) {
                Parametro parametro = estructura.getParametro(i);
                String data = charConverter.byteArrayToString(programParameters[i].getOutputData());
                log("retorno: " + parametro.nombre + " = [" + data + "]");
                estructura.setBufferParametro(0, parametro, data);
            }
        } catch (UnsupportedEncodingException e) {
            throw new EjecucionAS400SysException(e, host, user, pass, libs, rutaPrograma);
        } catch (AS400SecurityException e) {
            throw new EjecucionAS400SysException(e, host, user, pass, libs, rutaPrograma);
        } catch (ErrorCompletingRequestException e) {
            throw new EjecucionAS400SysException(e, host, user, pass, libs, rutaPrograma);
        } catch (IOException e) {
            throw new EjecucionAS400SysException(e, host, user, pass, libs, rutaPrograma);
        } catch (InterruptedException e) {
            throw new EjecucionAS400SysException(e, host, user, pass, libs, rutaPrograma);
        } catch (PropertyVetoException e) {
            throw new EjecucionAS400SysException(e, host, user, pass, libs, rutaPrograma);
        } catch (ObjectDoesNotExistException e) {
            throw new EjecucionAS400SysException(e, host, user, pass, libs, rutaPrograma);
        } finally {
            as400.disconnectService(AS400.COMMAND);
        }
    }

    private static void log(String mensaje) {
        System.out.println(mensaje);
    }
}
