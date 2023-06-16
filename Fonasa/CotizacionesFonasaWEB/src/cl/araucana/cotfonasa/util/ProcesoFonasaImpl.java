package cl.araucana.cotfonasa.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import cl.araucana.cotfonasa.util.AccesoAS400;
import cl.araucana.cotfonasa.util.EnviaCorreo;
import cl.araucana.cotfonasa.util.MailConexion;
import cl.araucana.cotfonasa.util.Util;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DataTruncation;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


import cl.araucana.cotfonasa.config.ConfiguracionSqlMap;
import cl.araucana.cotfonasa.dao.ProcesoFonasaDAO;

import cl.araucana.cotfonasa.vo.LogVO;
import cl.araucana.cotfonasa.vo.MailVO;
import cl.araucana.cotfonasa.vo.ParametrosVO;
import cl.araucana.cotfonasa.vo.RespSpVO;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibm.as400.access.AS400SecurityException;

public class ProcesoFonasaImpl {

 
	private static final String DB_DRIVER = "com.ibm.as400.access.AS400JDBCDriver";
	private static final String DB_CONNECTION = "jdbc:as400://146.83.1.2;naming=sql;errors=full";
	private static final String DB_USER = "schema";
	private static final String DB_PASSWORD = "schema";
 


	

	public static void main(String[] args) {
		// TODO Apéndice de método generado automáticamente

		String archivoEntrada= "C://CC112013.txt";
		String archivoSalida = "C://CCAF112013_SAL.txt";
		
		ProcesoFonasaImpl proc = new ProcesoFonasaImpl();
		
		//System.out.println("driver:"+DB_DRIVER);
		
		
		try{
			ProcesoFonasaImpl.ejecutaProceso(archivoEntrada);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
		
	}
	
	public int updateEstadoLog(String periodo, int estado)
	{
		
		ProcesoFonasaDAO dao = new ProcesoFonasaDAO();
		return dao.updateEstadoLog(periodo,estado);
		
		
	}
	
	
	public static void ejecutaProceso(String archivoEntrada)
	{
		
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		DateFormat dF = new SimpleDateFormat("dd-MM-yyy");
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat timeFormat = new SimpleDateFormat("HH-mm-ss");
		DateFormat timeFormat2 = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
	
	
	
		//String archivoEntrada = "C://tempEntrada.txt";
		String archivoErrores= "C://tempErrores.txt";
		String archivoSalidaTemp ="C://temporal.txt";
		String archivoSalidaOK ="C://salida.txt";
		File fNew = new File(archivoErrores);
		File f = new File(archivoEntrada);
		

		
		int count =0;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		MailConexion correo2 = new MailConexion();
		BufferedReader entrada;

		try {
		    
			
	 
			String insertTableSQL = "INSERT INTO CTDTA.CT21F1"
					+ "(TE6WA, SE5FAJC, SE5FBH3, CMNA,CMOA,CT1AA) VALUES"
					+ "(?,?,?,?,?,?)";
		    
			
		
				dbConnection = getDBConnection();
				preparedStatement = dbConnection.prepareStatement(insertTableSQL);
	 
				dbConnection.setAutoCommit(false);
				
				long time_start, time_end;
					
				
				String linea;
				int estado=1;
		        FileWriter w = new FileWriter(fNew);
				//ArrayList inputs=  new ArrayList() ;
				BufferedWriter bw = new BufferedWriter(w);
				PrintWriter wr = new PrintWriter(bw);
				time_start = System.currentTimeMillis();
				
				
				
				int multiplicador=1;
				int counter=200000;
				int registros =0;
				
	
			    
					entrada = new BufferedReader( new FileReader( f ) );
					//while(entrada.ready()){
					while ((linea = entrada.readLine()) != null)   {
					
						
					String [] datos = linea.split(";");
				
					
					System.out.println("datos: "+datos[0]+";"+datos[1]+";"+datos[2]+";"+datos[3]+";"+datos[4]);
					System.out.println("registro: "+count);
					
					if(datos[0].equals("") || datos[0].equals(null) || datos[1].equals("") || datos[1].equals(null) ||
						datos[2].equals("") || datos[2].equals(null) || datos[3].equals("") || datos[3].equals(null)
						|| datos[4].equals("") || datos[4].equals( null	)){
					  // break;
						
					}else{
					

					
					
						preparedStatement.setInt(1, count);
						preparedStatement.setInt(2, Integer.parseInt(datos[0]));
						preparedStatement.setString(3, datos[1]);
						preparedStatement.setInt(4, Integer.parseInt(datos[2]));
						preparedStatement.setString(5, datos[3]);
						preparedStatement.setInt(6, Integer.parseInt(datos[4]));
						
						// se revisa la memoria, si esta ok, se hace add batch, sino se vacia...
						
						// se hace executebatch, commit cada 200.000 registros
						
						registros = multiplicador * counter;
						
						
						
						if(count == registros)
						{
							preparedStatement.addBatch();
							preparedStatement.executeBatch();
							 
							dbConnection.commit();
							
							multiplicador++;
			
						}else
							preparedStatement.addBatch();
						
					}
						
					
				
				    //System.out.println("------- \n");

					
				count++;
				}
		        
				// cerramos archivo de escritura
				bw.close();
				preparedStatement.executeBatch();
				dbConnection.commit();
				
				if (dbConnection != null)
				dbConnection.close();
				
			
					time_end = System.currentTimeMillis();
				
				    long milisegundos = time_end - time_start;
				    
					//System.out.println("the task has taken "+ (time_end - time_start ) + " : mili seconds");
				System.out.println("El proceso ha demorado en ejecutarse: "+Util.convertMilisegundos(milisegundos));
				
			
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println("batch exception: "+e.getMessage());
			e.getCause();
			
			e.getLocalizedMessage();
			
		}catch(BatchUpdateException e)
		{
			e.printStackTrace();
			System.out.println("batch exception: "+e.getMessage());
			e.getCause();
			e.getUpdateCounts();
			e.getLocalizedMessage();
			
			
			
			
		}catch(ArrayIndexOutOfBoundsException ex)
		{
			System.out.println("Array Index Of Bound exception: "+ex.getMessage()+"cause: "+ex.getCause());
			ex.printStackTrace();
		}catch(DataTruncation e)
		{
			e.printStackTrace();
			System.out.println("data Truncation: "+e.getMessage());
			e.getDataSize();
			e.getRead();
			e.getErrorCode();
			
		}catch (SQLException e) {
          
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			e.getErrorCode();
			try {
				dbConnection.rollback();
			} catch (SQLException e1) {
				// TODO Bloque catch generado automáticamente
				e1.printStackTrace();
			}
 
		} catch(OutOfMemoryError e)
		{
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally {
 
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
					
				} catch (SQLException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
			}
 
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
			}
			
			
 
		}
		
	}
	
	private static Connection getDBConnection() {
		 
		Connection dbConnection = null;
		 
		try {
 
			Class.forName(DB_DRIVER);
 
		} catch (ClassNotFoundException e) {
 
			System.out.println(e.getMessage());
 
		}
 
		try {
 
			dbConnection = DriverManager.getConnection(
                              DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
		}
 
		return dbConnection;
	    
	
	}
 
	
	public int consultaProceso(String periodo){

	    ProcesoFonasaDAO dao = new ProcesoFonasaDAO();
	    
	   return  dao.consultaProceso();

	}


	
	
}

