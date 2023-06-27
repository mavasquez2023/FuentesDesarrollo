/**
 * 
 */
package cl.araucana.cheque.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author usist199
 *
 */
public class TriggerCheque_2url {
	private static Connection con=null;
	public static void main(String args[]) throws Exception 
	{
		
		try 
		{
			if(args.length!=4){
				System.out.println("Error en parámetros ingresados. Ingrese: Folio Cola Dispositivo Usuario");
				System.exit(1);
			}
			Properties properties = new Properties();
			properties.load(TriggerCheque_2url.class.getResourceAsStream("/etc/config.properties"));
			String http= properties.getProperty("urlwas");
			String http2= properties.getProperty("urlwas2");
			String paramsencode= Encripta.encode(args[3], args[0]);
			String parametros= "?folio=" + paramsencode+ "&cola=" + args[1] + "&dispositivo=" + args[2];
			//Se conecta a server WASpara invocar la URL
			URL url = new URL(http + parametros);
			if(!connectServer(url)){
				//Si servidor WAS tuvo problemas se usa server de Respaldo
				url = new URL(http2 + parametros);
				if(!connectServer(url)){
					String ip = properties.getProperty("serveras400");
					String user = properties.getProperty("usuarioas400");
					String pass = properties.getProperty("passwordas400");
					String schema = "PUBDTA";
					if(connectDB2(ip, schema, user, pass)){
						insert(ip, args[0], args[1], args[2], args[3]);
						System.out.println("Operacion fallida, respaldada en bitacora.");
					}
				}
			}
		} 
		catch (Exception ex) 
		{	 
			ex.printStackTrace();
		}
		finally{
			closeConnect();
		}
	}
	
	public static boolean connectServer(URL url){
		boolean resultado= false;
		try 
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine;
			while((inputLine = in.readLine()) != null)
				System.out.println(inputLine);
			in.close();
			resultado=true;
		}catch (ConnectException ex) 
		{	 
			System.out.println("Conexión rechazada a " + url.getHost() + ", mensaje=" + ex.getMessage());
		}
		catch (IOException ex) 
		{	 
			System.out.println("I/O fallida a " + url.getHost() + ", mensaje=" + ex.getMessage());
		}
		return resultado;
	}
	
	public static boolean connectDB2(String ip, String schema, String user, String pass) {
		try {
			Class.forName("com.ibm.as400.access.AS400JDBCDriver");
			con = DriverManager.getConnection("jdbc:as400://"+ip+"/"+schema+";user="+user+";password="+pass);
			return true;
			//con.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return false;	
	}
	
	public static void closeConnect() {
		try {
			if (con != null) {
				con.close();
			}
		}  catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		 	
	}
	
	public static int insert(String server, String folio, String cola, String dispositivo, String usuario) throws SQLException {
		StringBuffer query= new StringBuffer();
		query.append("insert  into pubdta.bitcheque ");
		query.append("( AREA, OFICINA, USUARIO, FOLIO, MONTO, ESTADO, COLA, DISPOSITIVO, IPREMOTA, USERNAME, MENSAJE ) ");
		query.append("values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );
		PreparedStatement db2= con.prepareStatement(query.toString());
		db2.setString(1, "");
		db2.setString(2, "SERVER");
		db2.setString(3, usuario);
		db2.setInt(4, Integer.parseInt(folio));
		db2.setInt(5, 0);
		db2.setString(6, "0");
		db2.setString(7, cola);
		db2.setString(8, dispositivo);
		db2.setString(9, server);
		db2.setString(10, "");
		db2.setString(11, "Sin conexión a servidor WAS");
		int resultado= db2.executeUpdate();
		db2.close();
		return resultado;
	}
	
}
