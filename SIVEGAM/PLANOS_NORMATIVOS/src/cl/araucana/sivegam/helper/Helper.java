package cl.araucana.sivegam.helper;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import cl.araucana.sivegam.vo.param.Parametro;

/*Implementacion de la clase Helper. 
 * Contiene implementaciones de funciones utiles para ser usadas en todo el sistema.*/
public class Helper {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    /*
     * Funcion que obtiene la descripcion de un parametro. Recibe como entrada
     * un arreglo de tipo parametro y un codigo. Retorna la glosa asociada al
     * codigo.
     */
    public static String obtenerDescripcion(Parametro[] lista, int codigo) {

        for (int i = 0; i < lista.length; i++) {

            Parametro aux = new Parametro();
            aux = lista[i];

            if (aux.getCodigo() == codigo) {

                return aux.getGlosa();
            }
        }
        return "";
    }

    public static int obtenerCodigo(Parametro[] lista, String descripcion) {

        for (int i = 0; i < lista.length; i++) {

            Parametro aux = new Parametro();
            aux = lista[i];

            if (aux.getGlosa().equals(descripcion)) {

                return aux.getCodigo();
            }
        }
        return -1;
    }

    /* Funcion que obtiene la fecha de vigencia. */
    public static String obtenerFechaVigencia(String fecha) {

        String fechaRes = "";
        Date date = new Date();
        Calendar cal = Calendar.getInstance();

        String DATE_FORMAT = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

        try {
            date = sdf.parse(fecha);
            cal.setTime(date);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 2);
            date = cal.getTime();
            fechaRes = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return fechaRes;
    }

    public static void borrarArchivos(String usuario) {

        usuario = usuario + "_";

        File g = new File("");

        StringBuffer excelFile = new StringBuffer("");
        excelFile.append(g.getAbsolutePath());
        excelFile.append(IND_Constants.DIR_INF);

        String dir = excelFile.toString();

        File f = new File(dir);

        File[] ficheros = f.listFiles();

        for (int i = 0; i < ficheros.length; i++) {

            File temp = ficheros[i];

            if (usuario.equals(temp.getName().substring(0, usuario.length()))) {

                temp.delete();
            }
        }

    }

    public static String digitoVerificadorRut(String strRut) {
        int rut = 0;
        int s = 0;
        String l_dv = "";

        rut = Integer.parseInt(strRut);

        if ("0".equals(strRut.trim())) {
            return "0";
        }

        for (int i = 2; i < 8; i++) {
            s = s + (rut % 10) * i;
            rut = (rut - (rut % 10)) / 10;
        }

        s = s + (rut % 10) * 2;
        rut = (rut - (rut % 10)) / 10;
        s = s + (rut % 10) * 3;
        rut = (rut - (rut % 10)) / 10;
        s = 11 - (s % 11);

        if (s == 10)
            l_dv = "K";
        else if (s == 11)
            l_dv = "0";
        else
            l_dv = s + "";

        return l_dv;
    }

    /** Funcion que separa cantidades numericas separadas en puntos. */
    public static String separadorDeMiles(String cadena) {
        String resultado = "";
        int count = 0;

        for (int i = cadena.length(); i >= 1; i--) {
            count++;

            if (count == 3) {
                resultado = "." + cadena.charAt(i - 1) + resultado;
                count = 0;
            } else {
                resultado = cadena.charAt(i - 1) + resultado;
            }

        }

        if (resultado.charAt(0) == '.' && resultado.length() > 0) {
            resultado = resultado.substring(1, resultado.length());
        }

        return resultado;
    }

    /* Metodo para obtener una variable parametrizada prio ambiente */
    public static String getVarPorAmbiente(String var) {
        String retorno = "";
        GlobalProperties global = GlobalProperties.getInstance();
        String ambiente = global.getValorExterno("IND.properties.ambiente");

        retorno = global.getValor(ambiente + "." + var);

        return retorno;
    }

    /* Metodo para validar correos electrónicos. */
    public static boolean isEmail(String correo) {
        boolean esEmail = false;
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        mat = pat.matcher(correo);
        if (mat.find()) {
            esEmail = true;
        }
        return esEmail;
    }

    public static Connection getConnection() {
        Connection connection = null;

        try {
            InitialContext context = new InitialContext();

            DataSource dataSource = (DataSource) context.lookup(IND_Constants.JNDI_Sivegam);
            connection = dataSource.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static String paddingString(String s, int n, char c, boolean paddingLeft) {

        if (s == null) {
            char[] ch2 = new char[n];
            Arrays.fill(ch2, c);
            String resp = new String(ch2);
            return resp;
        }

        int add = n - s.length();
        if (add <= 0) {
            return s;
        }

        StringBuffer str = new StringBuffer(s);
        char[] ch = new char[add];
        Arrays.fill(ch, c);
        if (paddingLeft) {
            str.insert(0, ch);
        } else {
            str.append(ch);
        }

        return str.toString();
    }

    /* Función que elimina un archivo dada la ruta especifica. */
    public static int deleteFile(String rutaArchivo) {

        File file = new File(rutaArchivo);

        if (file.delete()) {

            return 1;

        } else {

            return 0;
        }
    }

    public static int deleteFilesSivegam() {

        File g = new File("");
        int resp = 0;
        StringBuffer fileIntercaja = new StringBuffer("");
        fileIntercaja.append(g.getAbsolutePath());
        fileIntercaja.append(IND_Constants.DIR_UPLFILE);

        String dir = fileIntercaja.toString();

        File f = new File(dir);

        File[] ficheros = f.listFiles();

        for (int i = 0; i < ficheros.length; i++) {

            File temp = ficheros[i];

 //           logger.debug("ficheros: " + temp);
            String nameFile = temp.toString().substring(temp.toString().lastIndexOf(".")).toLowerCase();

 //           logger.debug("extension del archivo = " + nameFile);
            if (temp.delete()) {

                resp = 1;
  //              logger.debug("ficheros eliminados");
            } else {
                resp = 0;
 //               logger.debug("ficheros no eliminados");
            }
        }

        return resp;
    }
}
