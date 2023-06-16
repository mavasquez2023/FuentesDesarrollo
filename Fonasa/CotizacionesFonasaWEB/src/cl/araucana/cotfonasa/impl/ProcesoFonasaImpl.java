package cl.araucana.cotfonasa.impl;

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

import java.util.Calendar;
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

 
	
	private static String PREFIJO_NOMBRE_ARCHIVO;
	private static String EXTENSION_ARCHIVO;
	private static String DIRECTORIO_ENTRADA;
	private static String DIRECTORIO_SALIDA;
	private static String CORREO_USUARIO;
	private static String CORREO_ADMIN;
	private static String SCHEMA;
	
	public ProcesoFonasaImpl() {
		super();
		
		
		 try {
			
			Properties props = new Properties();
			ProcesoFonasaDAO dao = new ProcesoFonasaDAO();
			
			props.load(ProcesoFonasaImpl.class.getClassLoader().getResourceAsStream("cl/araucana/cotfonasa/properties/parametros.properties"));
			
			PREFIJO_NOMBRE_ARCHIVO = props.getProperty("PREFIJO_NOMBRE_ARCHIVO");
			EXTENSION_ARCHIVO = props.getProperty("EXTENSION_ARCHIVO");
			SCHEMA = props.getProperty("SCHEMA");
			
			
		    ParametrosVO[] param = dao.getParametros();
		    
		    DIRECTORIO_ENTRADA = param[0].getDirectorioEntrada().trim();
			DIRECTORIO_SALIDA  = param[0].getDirectorioSalida().trim();
		    CORREO_USUARIO = param[0].getCorreoUsuario().trim();
		    CORREO_ADMIN = param[0].getCorreoAdmin().trim();
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
	}

	

	public static void main(String[] args) {
		// TODO Apéndice de método generado automáticamente

		String archivoEntrada= "C://CCAF112013_copia.txt";
		String archivoSalida = "C://CCAF112013_SAL.txt";
		
		ProcesoFonasaImpl proc = new ProcesoFonasaImpl();
		
		//System.out.println("driver:"+DB_DRIVER);
		
		
		try{
			//ProcesoFonasaImpl.ejecutaProceso2("112013");
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
	
	
	public void ejecutaProceso(String periodo)
	{
		
		
		DateFormat dF = new SimpleDateFormat("dd-MM-yyy");
		DateFormat timeFormat2 = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		AccesoAS400 as400 = new AccesoAS400();
		String periodoBd = periodo.substring(2, 6)+ periodo.substring(0, 2); 
		Date fechaInicio2 = new Date();
		String horaInicioIns = timeFormat2.format(date);
        String archivoEntradaAS= DIRECTORIO_ENTRADA + PREFIJO_NOMBRE_ARCHIVO + periodo + EXTENSION_ARCHIVO;
		String archivoErroresAS= DIRECTORIO_SALIDA +  "ER" +periodo + EXTENSION_ARCHIVO;
		String archivoSalidaOKAS = DIRECTORIO_SALIDA + "OK"+periodo + EXTENSION_ARCHIVO;
		String destinatarios[] = new String[2];
		destinatarios[0] = CORREO_ADMIN;
		destinatarios[1] = CORREO_USUARIO;
		String archivoEntrada = "C://tempEntrada.txt";
		String archivoErrores= "C://tempErrores.txt";
		String archivoSalidaTemp ="C://temporal.txt";
		String archivoSalidaOK ="C://salida.txt";
		ProcesoFonasaDAO dao = new ProcesoFonasaDAO();
		int count =0;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		MailConexion correo2 = new MailConexion();
		

		try {
			long time_start, time_end;
			Date dat = new Date(19000101);
			File f = new File(archivoEntrada);
			File fNew = new File(archivoErrores);
			BufferedReader entrada;
			boolean flagErrores = false;
			String horaTermino = timeFormat2.format(date);
			Date fechaTermino ;
			time_start = System.currentTimeMillis();
			System.out.println("Se inicia proceso para periodo: "+periodoBd);
			
			try {
				// se inserta log de inicio de proceso
				dao.insertLog(periodoBd,archivoEntradaAS,"En proceso",
						fechaInicio2,Util.stringToDate(horaInicioIns,"HH:mm:ss"),
						Util.stringToDate("00:00:00","HH:mm:ss"),dat, 2);
			} catch (Exception e2) {
				// TODO Bloque catch generado automáticamente
				e2.printStackTrace();
			}
			
			
			// copia el archivo desde el as400 a archivoEntrada
			as400.copyFileAS400toServer(archivoEntradaAS, archivoEntrada);

			String insertTableSQL = "INSERT INTO "+SCHEMA+".CT21F1"
					+ "(TE6WA, SE5FAJC, SE5FBH3, CMNA,CMOA,CT1AA) VALUES"
					+ "(?,?,?,?,?,?)";
		    
			
		
				dbConnection = getDBConnection();
				preparedStatement = dbConnection.prepareStatement(insertTableSQL);
	 
				dbConnection.setAutoCommit(false);
	
				String linea;
				int estado=1;
		        FileWriter w = new FileWriter(fNew);
				//ArrayList inputs=  new ArrayList() ;
				BufferedWriter bw = new BufferedWriter(w);
				PrintWriter wr = new PrintWriter(bw);
				int multiplicador=1;
				int counter=200000;
				int registros =0;
				String msje ="";
				boolean flag=false;
	
			    
					entrada = new BufferedReader( new FileReader( f ) );
		        
					while ((linea = entrada.readLine()) != null)   {
					
						
					String [] datos = linea.split(";");
				    flag = false;
					msje="";
					
					if(datos.length != 5)
					{
						msje= "--Cantidad de campos requeridos son 5--";
						flagErrores = true;
						flag=true;
						estado=0;
					}else{
					
					System.out.println("datos: "+datos[0]+";"+datos[1]+";"+datos[2]+";"+datos[3]+";"+datos[4]);
					System.out.println("---------------------------------------------------------------------------");
					
					if(datos[0].trim().equals("") || datos[0].equals(null) || datos[1].trim().equals("") || datos[1].equals(null) ||
						datos[2].trim().equals("") || datos[2].equals(null) || datos[3].trim().equals("") || datos[3].equals(null)
						|| datos[4].trim().equals("") || datos[4].equals( null	)){
						
						
						msje= "--Dato vacío en archivo de entrada--";
						flagErrores = true;
						flag=true;
						estado=0;
					}else{
					
			                    if( !Util.isNumeric(datos[0]))    
			                    {
			                    	msje = msje + " --Rut de Cotizante No es Numérico--";
									flagErrores = true;
									flag=true;
									estado=0;
			                    	
			                    }else						
								// validamos los ruts 
								 if( !(Util.verificarRut(Integer.parseInt(datos[0]), datos[1].charAt(0))))
								 {
									   // wr.println(linea + "  ==> Rut de Cotizante No Valido, linea :"+ (count + 1));
									    msje = msje + " --Rut de Cotizante No Valido--";
										flagErrores = true;
										flag=true;
										estado=0;
									 
								 }
								 
			                    if( !Util.isNumeric(datos[2]))    
			                    {
			                    	msje = msje + " --Rut de Empleador No es Numérico--";
									flagErrores = true;
									flag=true;
									estado=0;
			                    	
			                    }else
								 if( !(Util.verificarRut(Integer.parseInt(datos[2]), datos[3].charAt(0))))
								 {
									   // wr.println(linea + "  ==> Rut de Empleador No Valido, linea :"+ (count + 1));
										
									     msje = msje + " --Rut de Empleador No Valido-- ";
									    flagErrores = true;
									    flag=true;
										estado=0;
									 
								 }
			                    
			                    if( !Util.isNumeric(datos[4]))    
			                    {
			                    	msje = msje + " --Fecha debe estar compuesta solo por números--";
									flagErrores = true;
									flag=true;
									estado=0;
			                    	
			                    }else			                    
								 if( !(Util.validarFecha(datos[4])))
								 {
									    //wr.println(linea + "  ==> Formato de Fecha no Valido, linea :"+ (count + 1));
										msje = msje + " --Formato de Fecha no Valido-- ";
									    flagErrores = true;
									    flag=true;
										estado=0;
									 
								 }
					   }
					
					}
								 // si hay algun error, este se imprime en archivo de errores de salida
								 if(flag)
									 wr.println(linea + "==>" + msje+ "linea: "+ (count + 1));
	
					
					if(flagErrores == false)
					{
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
				
				
				count++;
				}
		        
					
				// si count = 0 , indica que archivo es vacio
					if(count ==0){
						wr.println("Archivo de Entrada no Contiene Datos!");
						flagErrores=true;
						estado=0;
					}
				// cerramos archivo de escritura
				bw.close();
				// si no hubo errores , entonces se hace el insert en la tabla.
				if(flagErrores == false )
				{
					
					// borramos archivo de errores ya que no existieron
					
					Util.deleteFileServer(archivoErrores);
					//as400.deleteFromAS400(archivoErroresAS);
					
					System.out.println("Se ejecuta ultimo batch");
					preparedStatement.executeBatch();
					dbConnection.commit();
					
					if (dbConnection != null)
					dbConnection.close();
					
					
					System.out.println("Cantidad registros insertados en tabla de entrada:"+count);
					
					System.out.println("Se ejecuta SP COT_FONASA");
					// luego como no existieron errores se ejecuta el SP.
					//luego se ejecuta procedimiento almacenado que toma los datos desde la tabla de entrada y deja el resultado
					//en tabla de salida.
					
					RespSpVO resp = new RespSpVO();
					
					resp = dao.ejecutaSpProceso();
					
					// si se ejecuta proceso ok procedemos a volcar la data de la tabla de salida.
					if(resp.getSqlCode().equals("1"))
					{
						String fileTemp="/"+SCHEMA+"_ct22f1"+periodo+".txt";
						
						// volcar data de tabla de salida a un archivo.txt separado por ;
						
						// si el proceso salio bien.... se exporta tabla de salida de proceso a txt
						if (as400.exportArchiveFromTable(SCHEMA+"/CT22F1",fileTemp ) == 1)
						{
			
							
							// una vez creado el archivo en as , se transfiere hacia el server
							as400.copyFileAS400toServer(fileTemp, archivoSalidaTemp);
							
							//revisar archivo de salida, suprimir espacios blancos y sumprimir comillas dobles
							
							Util.procesoArchivo(archivoSalidaTemp, archivoSalidaOK);
							
							
							//copiamos el archivo OK en as400
							
							as400.writeFileFromServerToAS400(archivoSalidaOK, archivoSalidaOKAS);
							
							//eliminar archivo temporal creado en server
							if (Util.deleteFileServer(archivoSalidaTemp) ==1)
								System.out.println("fichero salida temporal borrado");
							
							if (Util.deleteFileServer(archivoSalidaOK) ==1)
								System.out.println("fichero salida temporal OK borrado");
							
							// eliminar archivo temporal creado en AS400
							
							as400.deleteFromAS400(fileTemp);
	
							//insertar archivo de log ok
							Date date2 = new Date();
							horaTermino = timeFormat2.format(date2);
							 //fechaTermino=dateFormat2.format(date2);
							 fechaTermino = new Date();

								System.out.println("Borramos Tablas de Entrada y Salida");
								// se borran tablas de entrada y salida
								  
								
							    dao.deleteTable(SCHEMA+".CT21F1");
							    dao.deleteTable(SCHEMA+".CT22F1");
							
							 //proceso ok, se ingresa un 1 en estado
							dao.insertLog(periodoBd,archivoEntradaAS,archivoSalidaOKAS,
									fechaInicio2,Util.stringToDate(horaInicioIns,"HH:mm:ss"),
									Util.stringToDate(horaTermino,"HH:mm:ss"),fechaTermino, 1);
							
							//enviar correo de proceso OK.
							
							String fecha = dF.format(date2);
							
							MailVO mail = new MailVO("",destinatarios,"Proceso Cotizaciones Ejecutado Exitosamente", 
									Util.getMensajeExitosoMail(resp,fecha, archivoEntradaAS,periodo));
							
						
							correo2.sendMail(mail);
							
						
						}else{
							System.out.println("Error en exportArchiveFromTable");
						}
						
						
						
						
						
					}else
					{
						// se copia archivo de errores en as400
						
						as400.writeFileFromServerToAS400(archivoErrores, archivoErroresAS);
						
						// se borra archivo en server una vez copiado en as400
						
						Util.deleteFileServer(archivoErrores);
						
						
						
						// ingresamos error en archivo de log
						horaTermino = timeFormat2.format(date);
						 //fechaTermino=dateFormat2.format(date);
						 fechaTermino = new Date();
						 
						// como proceso de ingreso de archivo de entrada tuvo errores, se ingresa un registro con error en log
						//dao.insertLog(periodoBd,archivoEntrada,archivoErrores,fechaInicio2,horaInicioIns,horaTermino,fechaTermino, estado);
						dao.insertLog(periodoBd,archivoEntradaAS,"ERROR EN EJECUCION DE SP",
								fechaInicio2,Util.stringToDate(horaInicioIns,"HH:mm:ss"),
								Util.stringToDate(horaTermino,"HH:mm:ss"),fechaTermino, estado);
						
						
						
					}

					
					
					
				}else
				{
					// como proceso de ingreso de archivo de entrada tuvo errores, se ingresa un registro con error en log
					 System.out.println("Proceso con Error");
					 Date date3 = new Date();
					 horaTermino = timeFormat2.format(date3);
					 //fechaTermino=dateFormat2.format(date3);
					 fechaTermino = new Date();
					 as400.writeFileFromServerToAS400(archivoErrores, archivoErroresAS);
					 

						System.out.println("Borramos Tablas de Entrada y Salida");
						// se borran tablas de entrada y salida
						  
						
					    dao.deleteTable(SCHEMA+".CT21F1");
					    dao.deleteTable(SCHEMA+".CT22F1");
					 
					    System.out.println("Se actualiza Log");
					dao.insertLog(periodoBd,archivoEntradaAS,archivoErroresAS,
							fechaInicio2,Util.stringToDate(horaInicioIns,"HH:mm:ss"),
							Util.stringToDate(horaTermino,"HH:mm:ss"),fechaTermino, estado);
					
					// enviar correo de proceso NO OK.
					
					MailVO mail = new MailVO("",destinatarios,"Proceso Cotizaciones Fonasa - Error en archivo de entrada", 
							"Estimado Usuario: Proceso de Cotizaciones Fonasa presenta problemas en archivo entrada para periodo: "+periodo+".\nFavor revisar archivo de errores:!: \n "+archivoErroresAS);
					
					correo2.sendMail(mail);
					
					
				}
				
                   
					time_end = System.currentTimeMillis();
				
				    long milisegundos = time_end - time_start;
				    
					//System.out.println("the task has taken "+ (time_end - time_start ) + " : mili seconds");
				System.out.println("El proceso ha demorado en ejecutarse: "+Util.convertMilisegundos(milisegundos));
				
			
		}catch (IOException e) {
			String horaTermino = timeFormat2.format(date);
			//String fechaTermino=dateFormat2.format(date);
			
			Date fechaTermino = new Date();
			
			
			// como proceso de ingreso de archivo de entrada tuvo errores, se ingresa un registro con error en log
			//dao.insertLog(periodoBd,archivoEntrada,archivoErrores,fechaInicio2,horaInicioIns,horaTermino,fechaTermino, estado);
			
			e.printStackTrace();
			
			System.out.println("No existe archivo de entrada");
				try {
					dao.insertLog(periodoBd,"No existe archivo de Entrada para Periodo","No se generó",
							fechaInicio2,Util.stringToDate(horaInicioIns,"HH:mm:ss"),
							Util.stringToDate(horaTermino,"HH:mm:ss"),fechaTermino, 4);
				} catch (Exception e2) {
					// TODO Bloque catch generado automáticamente
					e2.printStackTrace();
				}
			
			
				MailVO mail = new MailVO("",destinatarios,"Proceso Cotizaciones Fonasa: Archivo de Entrada No Existe", 
						"Estimado Usuario: No existe archivo de entrada para periodo: "+periodo);
				
				try {
					correo2.sendMail(mail);
				} catch (MessagingException e1) {
					// TODO Bloque catch generado automáticamente
					e1.printStackTrace();
				} catch (NamingException e1) {
					// TODO Bloque catch generado automáticamente
					e1.printStackTrace();
				}
			
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
			

			System.out.println("Borramos Tablas de Entrada y Salida");
			// se borran tablas de entrada y salida
			
			
			
		    try {
				dao.deleteTable(SCHEMA+".CT21F1");
				dao.deleteTable(SCHEMA+".CT22F1");
			} catch (Exception e1) {
				// TODO Bloque catch generado automáticamente
				System.out.println("Error al Borrar archivos de entrada y salida");
				e1.printStackTrace();
			}
		    
 
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
			
			// se borran las tablas
			try {
				dao.deleteTable(SCHEMA+".CT21F1");
				dao.deleteTable(SCHEMA+".CT22F1");
			} catch (Exception e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
 
		}
		
	}
	
	private static Connection getDBConnection() {
		 
		Connection connection = null;
		try {
		    InitialContext context = new InitialContext();
		    DataSource dataSource = (DataSource) context
			    .lookup("jdbc/cotFonasa");
		    connection = dataSource.getConnection();
		} catch (NamingException e) {
		    e.printStackTrace();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return connection;
	    
	
	}
 
	
	public int consultaProceso(String periodo){

	    ProcesoFonasaDAO dao = new ProcesoFonasaDAO();
	    
	   return  dao.consultaProceso();

	}


	
	
}

