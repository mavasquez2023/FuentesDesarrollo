package cl.araucana.cp.hibernate.dao;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;



import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.servlet.http.HttpSession;

public class CustomDataSource {
	
	
	 static Connection conn = null;
	    CallableStatement cs = null;
	    /**
	     * 
	     */
	    public static Properties cfg = null;
	    /**
	     * 
	     */
	    public String JNDI_ORACLE = "FRAMEWORK_JDNI";
	    // Setear conexión
	    /**
	     * @param newConn
	     */
	    public void setConnection(Connection newConn) {
	        conn = newConn;
	    }
	    // Recuperar conexión
	    /**
	     * @return conexion
	     */
	    public Connection getConnection() {
	        return conn;
	    }
	    // Abrir conexión por defecto oracle
	    /**
	     * 
	     */
	    public void openConnection() {
	        openConnection(JNDI_ORACLE);
	    }
	    /**
	     * @param jndi
	     */
	    // Abrir conexión
	    public void openConnection(String jndi) {
	    	if(conn==null){
		       // System.out.println("jndi : " + jndi);
		       // System.out.println("abro la conexion");
		        try {
		        /*	if(cfg==null){
		        		cfg = new Properties();
		            	cfg.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("conectaDB2.cfg"));
		        	}
		        	*/
		            InitialContext ic = null;
		            DataSource ds = null;
		        
		                ic = new InitialContext();
		            
		
		            
		                Hashtable env = new Hashtable();
		                env.put(Context.INITIAL_CONTEXT_FACTORY,"com.ibm.websphere.naming.WsnInitialContextFactory");
		                ic = new InitialContext(env);
		            
		
		            ds = (DataSource)ic.lookup("jdbc/pw");
		            conn = ds.getConnection();
		        } catch (SQLException ex) {
		            System.out.println("CustomDataSource.openConnection: SQLException(" + ex + ")");
		            conn = null;
		        } catch (Exception ex) {
		            System.out.println("CustomDataSource.openConnection: Exception(" + ex + ")");
		            conn = null;
		        }
		       // System.out.println("abri la conexion");
	    	} else {
	    		//System.out.println("la conexion ya estaba abierta");
	    	}
	    }

	    // Verificar conexión (rescata conexión de session)
	    /**
	     * @param session
	     * @return verifica conexion
	     */
	    public boolean isConnect(HttpSession session) {
	        conn = (Connection)session.getAttribute("connDataBase");
	        if (conn == null)
	            return false;
	        else
	            return true;
	    }
	    
	    /**
	     * se cierra conexion
	     */
	    // Cerrar conexión
	    public void closeConnection() {
	        try {
	            //conn.close();
	        } catch (Exception ex) {
	            System.out.println("CustomDataSource.closeConnection: Exception(" + ex + ")");
	        }
	    }

	    /**
	     * @param nomFuncion
	     * @param listaParametros
	     * @return objeto con resultados de la funcion
	     */
	   // Ejecutar función
	    public Object executeFunction(String nomFuncion, ArrayList listaParametros){
	        try {
	            cs = conn.prepareCall("{? = call " + nomFuncion + "(" + cadenaParametros(listaParametros,1,",") + ")}");
	            ObjectParameter parametro = (ObjectParameter)listaParametros.get(0);
	            cs.registerOutParameter(parametro.indice,parametro.tipo);
	            for (int i=1; i<listaParametros.size(); i++) {
	                parametro = (ObjectParameter)listaParametros.get(i);
	                cs.setObject(parametro.indice,parametro.valor);
	            }
	            cs.execute();

	            //mod
	            //cs.close();
	            return cs.getObject(1);
	/*          String salida = cs.getObject(1).toString();
	            cs.close();
	            return salida;*/
//	fin mod
	        } catch (SQLException ex) {
	            System.out.println("CustomDataSource.executeFunction: SQLException=" + ex);
	            return null;
	        } catch (Exception ex) {
	            System.out.println("CustomDataSource.executeFunction: Exception=" + ex);
	            System.out.println("******************************************************************");
	            ex.printStackTrace();
	            System.out.println("******************************************************************");
	            return null;
	        }finally{
	            if(cs!=null){
	                try{ 
	                    cs.close();
	                    }
	                catch(Exception e){
	                    
	                    System.out.println("CustomDataSource.executeFunction: Exception=" + e);
	                    System.out.println("******************************************************************");
	                    e.printStackTrace();
	                    System.out.println("******************************************************************");
	                    
	                }{
	                }
	            }
	        }

	    }

	    // Ejecutar procedimiento almacenado
	    /**
	     * @param nomProcedimiento
	     * @param listaParametros
	     * @return resultado del procedimiento
	     */
	    public CallableStatement executeProcedure(String nomProcedimiento, ArrayList listaParametros) {
	        try {
	            String sql = "{call " + nomProcedimiento + "(" + cadenaParametros(listaParametros,0,",") + ")}"; 
	            cs = conn.prepareCall(sql);
	            for (int i=0; i<listaParametros.size(); i++) {
	                ObjectParameter parametro = (ObjectParameter)listaParametros.get(i);
	                if (parametro.modo==java.sql.DatabaseMetaData.procedureColumnIn || parametro.modo==java.sql.DatabaseMetaData.procedureColumnInOut)
	                    if (parametro.tipo==java.sql.Types.DATE)
	                        cs.setDate(parametro.indice,(java.sql.Date)parametro.valor);
	                    else
	                        cs.setObject(parametro.indice,parametro.valor);
	                if (parametro.modo==java.sql.DatabaseMetaData.procedureColumnOut || parametro.modo==java.sql.DatabaseMetaData.procedureColumnInOut)
	                    cs.registerOutParameter(parametro.indice,parametro.tipo);
	            }
	                cs.execute();
	                return cs;
	        } catch (SQLException ex) {
	            System.out.println("CustomDataSource.executeProcedure: SQLException=" + ex);
	            ex.printStackTrace();
	            return null;
	        } catch (Exception ex) {
	            System.out.println("CustomDataSource.executeProcedure: Exception=" + ex);
	            ex.printStackTrace();
	            return null;
	        }
	    }

	    // Concatenar lista de parámetros
	    private String cadenaParametros(ArrayList lista, int inicio, String separa) {
	        String cadena = "";
	        for (int i=inicio; i<lista.size(); i++) {
	            if (i>(inicio))
	                cadena += separa;
	                cadena += "?";
	        }
	        return cadena;

}
}
