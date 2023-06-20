package com.araucana.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;


import com.araucana.controller.Rutas;

public class Archivos_unificar {
	private static final Logger log = Logger.getLogger(Archivos_unificar.class); //LogManager
	
	//public File gloArchivo = new File("");
	public String gloContinua = "Si";
	public String gloHuboError = "No";
	
	public String gloErrorArchivo = "";
	public String gloRut_corto = "";
	public String gloRut_correcto = "";
	public String gloRut_correcto_Solo_Rut = "";
	
	//public long gloGrabadosEnLocal = 0;
	//public String gloContinuaEnFTP = "N";
	
	private FTPClient clienteFtp;

	public static int gloLargoReg = 0;
	public static List<String> gloListaComas = new ArrayList<String>();
	private static DateFormat f_t = new SimpleDateFormat("HHmmss.SSS");
	private static DateFormat f_d = new SimpleDateFormat("yyyyMMdd");
	
	public static void main(String[] args) throws IOException {
		int totParam = args.length;
		String parPeriodo = "";
		String parTipo = "";
		String parIndicaFTP = "";
		String xContinua = "S";

		if ((totParam > 0) && (totParam <= 3)) {
			if (totParam > 0) {
				parPeriodo = args[0];
			}

			if (totParam > 1) {
				parTipo = args[1];
			}

			if (totParam == 3) {
				parIndicaFTP = args[2]; // valores: S=Si (Elimina Sólo Cambios), N=No Elimina, M=Masivo (Elimina previamente Todos).
			}

			if (parPeriodo.length() != 6) {
				xContinua = "N";
			} else {
				if (!parIndicaFTP.equals("N") && (!parIndicaFTP.equals("S")) && (!parIndicaFTP.equals("M"))) {
					xContinua = "N";
				}
			}
		} else {
			xContinua = "N";
		}

		if (xContinua.equals("S")) {
			Archivos_unificar unificar = new Archivos_unificar();
			unificar.procesoUnificarNominas(parPeriodo, parTipo, parIndicaFTP);
		}

	}

	public void procesoUnificarNominas(String parPeriodo, String parTipo, String parIndicaFTP)
			throws IOException {
		String xContinua = "S";

		if (parPeriodo.length() != 6) {
			xContinua = "N";
		} else {
			if (!parIndicaFTP.equals("N") && (!parIndicaFTP.equals("S")) && (!parIndicaFTP.equals("M"))) {
				xContinua = "N";
			}
		}
		
		if (xContinua.equals("S")) {
			char xCar = '-';
			String rutaProyecto = "";
			String xTipoGlosa = "";
			String xTipoCorto = "";
			String xTipoCorto2 = "";
			String xTipoVal = "";
			
			
		    String xHora = f_t.format(new Date());
		    String xFecha = f_d.format(new Date());
		    

			
			// Leer properties donde esta la Ruta Origen
			String xRutaOrigen = "";
			Properties properties = new Properties();

			try {
				InputStream input = Rutas.class.getClassLoader().getResourceAsStream("application.properties");
				if (input == null) {
					log.error("Archivo 'application.properties' no encontrado");
				}
				properties.load(input);
				xRutaOrigen = properties.getProperty("Ruta_Origen");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			log.info("***Rutas.xRutaOrigen: " + xRutaOrigen);
			rutaProyecto = xRutaOrigen;
			// Fin			

			xCar = '*';
			xTipoGlosa = "???";
			xTipoCorto = "XX";
			xTipoCorto2 = "XX";
			int parTipoint= Integer.parseInt(parTipo);
			switch (parTipoint) {
			case 1: {
				xCar = '-';
				xTipoGlosa = "Nóminas de Crédito";
				xTipoCorto = "NC";
				xTipoCorto2 = "EBCRED";
				xTipoVal = "1";

				gloLargoReg = 95;
				gloListaComas = subAsignarComas(xTipoCorto);

				break;
			}
			case 2: {
				xCar = '.';
				xTipoGlosa = "Nóminas de Asignaciones Familiares";
				xTipoCorto = "CF";
				xTipoCorto2 = "EBASFAM";
				xTipoVal = "2";

				gloLargoReg = 133;
				gloListaComas = subAsignarComas(xTipoCorto);

				break;
			}
			case 3: {
				xCar = '.';
				xTipoGlosa = "Nóminas de Leasing";
				xTipoCorto = "NL";
				xTipoCorto2 = "EBLEASIN";
				xTipoVal = "2";

				gloLargoReg = 104;
				gloListaComas = subAsignarComas(xTipoCorto);

				break;
			}
			case 4: {
				xCar = '.';
				xTipoGlosa = "Nóminas de Asignaciones Familiares (ANEXOS)";
				xTipoCorto = "AT";
				xTipoCorto2 = "EBASFAM";
				xTipoVal = "2";

				gloLargoReg = 119;
				gloListaComas = subAsignarComas(xTipoCorto);

				break;
			}
			}

			if (xTipoGlosa == "???") {
				xContinua = "N";
			} else {
				xHora = f_t.format(new Date());
				xFecha = f_d.format(new Date());
				
				log.info("*** Fecha/Hora Inicio Proceso: " + xFecha + " " + xHora);
				log.info("*** CARPETA PROYECTO BASE: " + rutaProyecto);
				log.info("");
				log.info("Parámetro 1: " + parPeriodo);
				log.info("Parámetro 2: " + parTipo);
				log.info("Parámetro 3: " + parIndicaFTP);
				log.info("");
				log.info("Nota informativa: Se asume que el nombre del Archivo comienza con un RUT hasta el caracter '" + xCar + "'.");
				log.info("");

				if ((parIndicaFTP.equals("S")) || (parIndicaFTP.equals("M"))) {
					subCopiarNominasDesdeFTP(parTipo, rutaProyecto, xTipoCorto2, xTipoGlosa, xCar, xTipoVal, parIndicaFTP);
				} else {
					log.info("*** Importante: No se obtendrán las Nóminas desde el FTP Server.");
					log.info(" ");
				}

				if (gloContinua == "Si") {
					subProcesar(parTipo, rutaProyecto, xCar, parPeriodo, xTipoGlosa);
					subCreaIndice(rutaProyecto, parTipo, parPeriodo);
				} else {
					log.info(" ");
					log.info("*** Proceso cancelado!");
					log.info(" ");
				}
			}
		} else {
			xContinua = "N";
		}

		if (xContinua.equals("N")) {
			log.error("");
			log.error("***Error: Parametros Mal ingresados***");
			log.error("");
			log.error("Parámetro 1: " + parPeriodo);
			log.error("Parámetro 2: " + parTipo);
			log.error("Parámetro 3: " + parIndicaFTP);
			log.error("");
			log.error("Deben ingresarse 3 parámetros: aaaamm x y");
			log.error("aaaamm: Es el Período de Proceso de largo 6 en formato añomes.");
			log.error("x: Debe ser:");
			log.error("   1 = Nóminas de Crédito (Formato archivo en FTP: 1234567890-123-123, largo Reg: 95)");
			log.error("   2 = Nóminas de Asignaciones Familiares (CARGAS) (Formato archivo en FTP: 1234567890.123, largo Reg: 133)");
			log.error("   3 = Nóminas de Leasing (Formato archivo en FTP: 1234567890.123, largo Reg: 104)");
			log.error("   4 = Nóminas de Asignaciones Familiares (ANEXOS) (Formato archivo en FTP: 1234567890.123, largo Reg: 119)");
			log.error("y: Debe ser:");
			log.error("   S = Si. Si acceder al FTP Server para extraer las Nóminas, pero sólo actualizar los archivos en la Carpeta Destino.");
			log.error("   M = Masivo. Si acceder al FTP Server para extraer las Nóminas, pero borrar Todos los archivos en la Carpeta Destino previamente.");
			log.error("   N = No. No acceder al FTP Server para extraer las Nóminas.");
			log.error("");
		}

	}

	public static List<String> subAsignarComas(String xCual) {
		List<String> xListaComas = new ArrayList<String>();
		
		xListaComas.clear(); // Contiene posiciones de inicio de cada campo
		
		if(xCual.equals("NC")) {
				xListaComas.add("3");
				xListaComas.add("12");
				xListaComas.add("13");
				xListaComas.add("16");
				xListaComas.add("27");
				xListaComas.add("36");
				xListaComas.add("37");
				xListaComas.add("73");
				xListaComas.add("75");
				xListaComas.add("77");
				xListaComas.add("83");
				xListaComas.add("85");
				xListaComas.add("88");
				xListaComas.add("95");
			}else if(xCual.equals("NL")) {
				xListaComas.add("8");
				xListaComas.add("9");
				xListaComas.add("12");
				xListaComas.add("15");
				xListaComas.add("23");
				xListaComas.add("24");
				xListaComas.add("76");
				xListaComas.add("82");
				xListaComas.add("83");
				xListaComas.add("86");
				xListaComas.add("95");
				xListaComas.add("104");
			}else if(xCual.equals("CF")){
				xListaComas.add("9");
				xListaComas.add("10");
				xListaComas.add("19");
				xListaComas.add("20");
				xListaComas.add("29");
				xListaComas.add("30");
				xListaComas.add("70");
				xListaComas.add("71");
				xListaComas.add("72");
				xListaComas.add("80");
				xListaComas.add("88");
				xListaComas.add("96");
				xListaComas.add("102");
				xListaComas.add("103");
				xListaComas.add("113");
				xListaComas.add("123");
				xListaComas.add("133");
			}else if(xCual.equals("AT")){
				xListaComas.add("9");
				xListaComas.add("10");
				xListaComas.add("13");
				xListaComas.add("16");
				xListaComas.add("19");
				xListaComas.add("28");
				xListaComas.add("29");
				xListaComas.add("44");
				xListaComas.add("59");
				xListaComas.add("79");
				xListaComas.add("85");
				xListaComas.add("86");
				xListaComas.add("89");
				xListaComas.add("99");
				xListaComas.add("109");
				xListaComas.add("119");
			}
		
		return xListaComas;
	}
	
	public void subCopiarNominasDesdeFTP(String xTipo, String rutaProyecto, String xTipoCorto2, String xTipoGlosa, char xCar, String xTipoVal, String parIndicaFTP) {
		String rutaArchivosMonOrigenFTP = "";
		String rutaArchivosMonDestinoFTP = "";
		String rutaArchivosFTP = "";
		String xServer = "";
		String xUser = "";
		String xPass = "";
		int xTimeout = 0;
		String sTimeout = "";

		log.info("INICIO Proceso de Copiado de Nóminas desde FTP Server.");
		log.info("*** TIPO = " + xTipo + " (" + xTipoGlosa + ")");
		log.info(" ");
				
		//Leer properties donde esta la Ruta Origen y Destino del FTP
		Properties properties = new Properties();

		try  {
			InputStream input = Rutas.class.getClassLoader().getResourceAsStream("application.properties");
			if (input == null) {
				log.error("Archivo 'application.properties' no encontrado");
			}
			properties.load(input);
			
			xServer = properties.getProperty("ftp_IP");
			xUser = properties.getProperty("ftp_Usuario");
			xPass = properties.getProperty("ftp_Clave");
			sTimeout = properties.getProperty("ftp_Timeout");
			xTimeout = Integer.parseInt(sTimeout);
			
			log.info("Variables Properties:");
			log.info("*** Server = " + xServer);
			log.info("*** User = " + xUser);
			log.info("*** Pass = " + xPass);
			log.info("*** Timeout (String) = " + sTimeout);		
			log.info("*** Timeout (Int) = " + xTimeout);
			log.info("");		
			int xTipoint= Integer.parseInt(xTipo);
		    switch (xTipoint) {
		        case 1: //NC
		        	rutaArchivosFTP = properties.getProperty("NC_ruta_origen_ftp");
		        	rutaArchivosMonDestinoFTP = properties.getProperty("NC_ruta_origen");
		            break;
		        case 2: //CF
		        	rutaArchivosFTP = properties.getProperty("CF_ruta_origen_ftp");
		        	rutaArchivosMonDestinoFTP = properties.getProperty("CF_ruta_origen");
		            break;
		        case 3: //NL
		        	rutaArchivosFTP = properties.getProperty("NL_ruta_origen_ftp");
		        	rutaArchivosMonDestinoFTP = properties.getProperty("NL_ruta_origen");
		            break;
		        case 4: //AT
		        	rutaArchivosFTP = properties.getProperty("AT_ruta_origen_ftp");
		        	rutaArchivosMonDestinoFTP = properties.getProperty("AT_ruta_origen");
		            break;
		        default:
		            break;
		    }			
		} catch (FileNotFoundException e) {
			gloContinua = "No";
			e.printStackTrace();
		} catch (IOException e) {
			gloContinua = "No";
			e.printStackTrace();
		}			
		// Fin			

		rutaArchivosMonOrigenFTP = rutaArchivosFTP;

		log.info("*** Server = " + xServer);
		log.info("*** User = " + xUser);
		log.info("*** Pass = " + xPass);
		log.info("*** Timeout = " + xTimeout + " milisegundos (1000 Milliseconds = 1 Seconds)");
		log.info("*** Carpeta Origen FTP = " + rutaArchivosMonOrigenFTP);
		log.info("*** Carpeta Destino Local = " + rutaArchivosMonDestinoFTP);
		log.info("");

		subAccederFTP(xServer, xUser, xPass, xTimeout, rutaArchivosMonDestinoFTP, rutaArchivosMonOrigenFTP, xCar, xTipoVal, parIndicaFTP);
	}
	
	public void subEliminarArchivosEnDestino(String localPath) {
		//Lo Primero: Eliminar archivos en carpeta destino si existen.
		File dirCarpetaDestino = new File(localPath);

		if (!dirCarpetaDestino.exists()) { // Crear donde se grabarán los archivos si NO existe
			log.info("*** No existe CARPETA DONDE DEJAR ARCHIVOS COPIADOS DESDE FTP SERVER.");
			log.info("*** CREAR CARPETA DONDE DEJAR ARCHIVOS COPIADOS DESDE FTP SERVER:");

			if (dirCarpetaDestino.mkdir()) {
				log.info("***    Carpeta creada: " + dirCarpetaDestino);
			} else {
				log.info("***    Error al crear Carpeta: " + dirCarpetaDestino);
			}
		} else { // Si Carpeta existe, eliminar todos los archivos
			File listFile[] = dirCarpetaDestino.listFiles();

			int kk = listFile.length;

			log.info("*** LIMPIAR CARPETA EXISTENTE: " + dirCarpetaDestino);
			log.info("***   Hay " + kk + " Archivos para eliminar.");

			if (kk > 0) {
				log.info(" ");

				// Eliminar los archivos
				for (int ii = 0; ii < kk; ii++) {
					if (listFile[ii].isFile()) {
						if (listFile[ii].delete()) {
							log.info("***   [" + (ii + 1) + " de " + kk
									+ "] Archivo eliminado...: " + listFile[ii]);
						} else {
							log.info("***   [" + (ii + 1) + " de " + kk
									+ "] Error, Archivo No eliminado: " + listFile[ii]);
						}
					} else {
						subEliminar2(listFile[ii], ii + 1);
						
						if (listFile[ii].delete()) {
							log.info("***   [" + (ii + 1) + " de " + kk
									+ "] Carpeta especial eliminada...: " + listFile[ii]);
						} else {
							log.info("***   [" + (ii + 1) + " de " + kk
									+ "] Error, Carpeta especial No eliminada: " + listFile[ii]);
						}
					}
				}
			}
		}
		// Fin Eliminar archivos en carpeta destino si existen.			
	}
	
	public void subEliminarArchivosEnDestino_SoloUno(String localPath, String localPath2, String xOpcion) {
		if (xOpcion.equals("C")) { //Crear Directorio Destino si no existe
			File dirCarpetaDestino = new File(localPath);
	
			if (!dirCarpetaDestino.exists()) { // Crear donde se grabarán los archivos si NO existe
				log.info("*** No existe CARPETA DONDE DEJAR ARCHIVOS COPIADOS DESDE FTP SERVER.");
				log.info("*** CREAR CARPETA DONDE DEJAR ARCHIVOS COPIADOS DESDE FTP SERVER:");
	
				if (dirCarpetaDestino.mkdir()) {
					log.info("***    Carpeta creada: " + dirCarpetaDestino);
				} else {
					log.info("***    Error al crear Carpeta: " + dirCarpetaDestino);
				}
			} 
		} else { // Si Carpeta o Archvo existe, eliminar
			File xFile = new File(localPath2);

			if (xFile.exists()) {
				if (xFile.isFile()) {
					if (xFile.delete()) {
						log.info("*** Archivo eliminado...: " + xFile);
					} else {
						log.info("*** Error, Archivo No eliminado: " + xFile);
					}
				} else {
					subEliminar3(xFile);
					
					if (xFile.delete()) {
						log.info("*** Carpeta especial eliminada...: " + xFile);
					} else {
						log.info("*** Error, Carpeta especial No eliminada: " + xFile);
					}
				}
			} else {
				log.info("*** Carpeta/Archivo No existe para eliminarlo...: " + xFile);
			}
		}
		// Fin			
	}	

	public void subAccederFTP(String server, String user, String pass, int xTimeout, String localPath, String remotePath, char xCar, String xTipoVal, String parIndicaFTP) {
		int xCuentaOK = 0;
		int xCuentaError = 0;
		int xCuentaErrorFmto = 0;
		int xCuentaOK_dir = 0;
		int xCuentaError_dir = 0;		
		int jj = 0, tt = 0, aa = 0, tArchivos = 0, tCarpetas = 0, tArchivosCarpetas = 0, kk1 = 0, jj1 = 0;
		String xHayError = "";
		String xNombreArchivoDestino = "";
		long xLeidos = 0;
		
		if (parIndicaFTP.equals("M")) { //M=Eliminación Masiva
			log.info("*** Copia desde FTP con Eliminación Masiva de la Carpeta Destino.");
			log.info("");
			subEliminarArchivosEnDestino(localPath);
		} else {
			log.info("*** Copia desde FTP con sólo Actualización, es decir, Sin Eliminación Masiva de la Carpeta Destino.");
			subEliminarArchivosEnDestino_SoloUno(localPath, "", "C"); //Crear Directorio Destino si no existe
		}
		
		File dirCarpetaDestino = new File(localPath);
	
		final String SERVIDOR = server;
		final int PUERTO = 21;
		final String USUARIO = user;
		final String PASSWORD = pass;

		log.info("");
		log.info("*** clienteFtp = new FTPClient(); - antes");
		clienteFtp = new FTPClient();
		clienteFtp.setConnectTimeout(xTimeout); // 1000 Milliseconds (1 Seconds)
		log.info("*** clienteFtp = new FTPClient(); - despues");

		try {
			clienteFtp.connect(SERVIDOR, PUERTO);

			int respuesta = clienteFtp.getReplyCode();

			if (!FTPReply.isPositiveCompletion(respuesta)) {
				log.info("");
				log.info("*** Algo ha salido mal. El servidor respondió con el código: " + respuesta);
				gloContinua = "No";
			} else {
				boolean loginSatisfactorio = clienteFtp.login(USUARIO, PASSWORD);

				if (loginSatisfactorio) {
					log.info("*** Se ha iniciado sesión en el servidor FTP correctamente.");

					FTPFile[] archivos = clienteFtp.listFiles(remotePath);
					tt = archivos.length;
					
					log.info("*** Hay " + tt + " Archivos en directorio FTP: " + remotePath);

					if (tt == 0) {
						log.info("");
						log.info("*** No hay archivos en la carpeta '" + remotePath + "' para Copiar.");
						gloContinua = "No";
					} else {
						// Copiar archivos
						System.out.println("");
						System.out.println("Espere por favor, ahora vamos a Copiar desde Servidor FTP...");
						System.out.println("");
						
						log.info("");
						log.info("Ahora vamos a Copiar solo las Carpetas desde Servidor FTP...");
						
						xLeidos = 0;

						//Primero: Copiar los Directorios
						for (FTPFile archivo : archivos) {	
							xLeidos += 1;
							
							if (archivo.isDirectory()) { //Es un Directorio								
								tCarpetas += 1;
								File dirCopiar = new File(remotePath + "/"  + archivo.getName());	//FTP
								File dirDescarga = new File(localPath + "\\" + archivo.getName());  //Servidor Local
								
								//Eliminar Carpeta a crear
								if (parIndicaFTP.equals("S")) { //S=Eliminación Puntual
									subEliminarArchivosEnDestino_SoloUno(localPath, localPath + "\\" + archivo.getName(), "E"); //Eliminar Carpeta Destino si existe
								}
								//Fin
								
								try 
								{
									FTPFile[] archivosDir = clienteFtp.listFiles(remotePath + "/"  + archivo.getName());
									aa = archivosDir.length;
									
									log.info("");
									log.info("Carpeta a copiar: " + dirCopiar);
																		
									kk1 = 0;
								
									if (dirDescarga.mkdir()) {
										log.info("Carpeta creada: " + dirDescarga);
										log.info("Se copiarán " + aa + " archivos");
										
										if (aa > 0) {
											for (FTPFile archivoDir : archivosDir) {	
												kk1 += 1;
												log.info("Copiando archivo " + kk1 + ": " + remotePath + "/"  + archivo.getName() + "/" + archivoDir.getName());
												
												FileOutputStream xDestino;
											
												xDestino = new FileOutputStream(localPath + "\\" + archivo.getName() + "\\" + archivoDir.getName());
												
												log.info("   -> Hacia: " + localPath + "\\" + archivo.getName() + "\\" + archivoDir.getName());											
												this.clienteFtp.retrieveFile(remotePath + "/"  + archivo.getName() + "/" + archivoDir.getName(), xDestino);
												
												Path archivoEspecial_origen = Paths.get(remotePath + "/"  + archivo.getName() + "/" + archivoDir.getName());
												Path archivoEspecial_destino = Paths.get(localPath + "\\" + archivo.getName() + "\\" + archivoDir.getName());
												
												log.info("Archivo Nro. " + kk1 + " copiado OK!");
												log.info("   - Archivo origen: " + archivoEspecial_origen);
												log.info("   - Archivo destino: " + archivoEspecial_destino);
												
												if (gloHuboError == "No") {
													xCuentaOK_dir += 1;
												} else {
													xCuentaError_dir += 1;
												}			
												
												tArchivosCarpetas += 1;
											}
										}
									} else {
										log.info("Carpeta No creada: " + dirDescarga);
									}
									
									log.info("");
								} catch (IOException e) {
									log.info("*** ERROR al Copiar Directorio '" + dirDescarga + "': " + e.getMessage());
									log.info("*** Proceso 'Copiar desde Servidor FTP' continua...");
								}								
							}
							
							jj += 1; jj1 += 1;
							if (jj >= 100) {
								jj = 0;
								System.out.println("Van " + jj1 + " archivos Copiados de " + tt + " ("
										+ "OK: " + xCuentaOK_dir + " | Error en Copia: " + xCuentaError + " | Error x Fmto: " + xCuentaErrorFmto + ")...");
							}
						} //Fin For para copiar Directorios
						
						if (xCuentaOK_dir == 0) {
							log.info("");
							log.info("No se Copiaron Carpetas desde Servidor FTP...");							
						}
						
						log.info("");
						log.info("Ahora vamos a Copiar solo los Archivos desde Servidor FTP...");
						
						xLeidos = 0;
						jj = 0; jj1 = 0;
						
						//Segundo: Copiar los Archivos
						for (FTPFile archivo : archivos) {	
							xLeidos += 1;
							
							if (!archivo.isDirectory()) { //No Es un Directorio
								//Validar formato del Archivo
								gloRut_corto = archivo.getName();
								xHayError = funValidarArchivo(remotePath + "/" + archivo.getName(), xCar, xTipoVal, "S", tt, xLeidos);
								//La variable gloRut_correcto_Solo_Rut solo trae el RUT
								//Fin Validar
								
								if (xHayError.equals("N")) {
									tArchivos += 1;
											
									if (xTipoVal == "1") {
										xNombreArchivoDestino = archivo.getName();
									} else {
										xNombreArchivoDestino = gloRut_correcto;
									}
									
									//Eliminar Archivo a crear
									if (parIndicaFTP.equals("S")) { //S=Eliminación Puntual
										subEliminarArchivosEnDestino_SoloUno(localPath, localPath + "\\" + xNombreArchivoDestino, "E"); //Eliminar Archivo Destino si existe
									}
									//Fin
										
									downloadFileByFTP(server, user, pass, localPath + "\\" + xNombreArchivoDestino,
											remotePath + "/" + archivo.getName(), parIndicaFTP);
		
									if (gloHuboError == "No") {
										xCuentaOK += 1;
									} else {
										xCuentaError += 1;
									}
								} else {
									xCuentaErrorFmto += 1;
								}
							}
							
							jj += 1; jj1 += 1;
							if (jj >= 100) {
								jj = 0;
								System.out.println("Van " + jj1 + " archivos Copiados de " + tt + " ("
										+ "OK: " + xCuentaOK + " | Error en Copia: " + xCuentaError + " | Error x Fmto: " + xCuentaErrorFmto + ")...");
							}
						} //Fin For para copiar sólo Archivos					
						
						if (xCuentaOK == 0) {
							log.info("");
							log.info("*** No se copiaron archivos desde el FTP.");
							gloContinua = "No";
						}

						log.info("");
						log.info("*** Resultado de la copia en la carpeta: '" + dirCarpetaDestino + "':");
						log.info("***");
						log.info("*** Archivos procesados: " + tArchivos + ".");
						log.info("*** Archivos Copiados OK: " + xCuentaOK + ".");
						log.info("*** Archivos Copiados con Error: " + xCuentaError + ".");		
						log.info("***");
						log.info("*** Carpetas procesadas: " + tCarpetas + ".");
						log.info("*** Archivos en Carpetas procesados: " + tCarpetas + ".");
						log.info("*** Archivos en Carpetas Copiados OK: " + xCuentaOK_dir + ".");
						log.info("*** Archivos en Carpetas Copiados con Error: " + xCuentaError_dir + ".");						
					}
				} else {
					log.info("*** ERROR al iniciar sesión en el servidor FTP: Problema en las credenciales.");
					gloContinua = "No";
				}
			}
		} catch (IOException e) {
			log.info("***");
			log.info("*** ERROR en el servidor FTP: " + e.getMessage());
			log.info("*** Archivos leidos: " + xLeidos);
			log.info("***");
			gloContinua = "No";
		}
	}

	public void downloadFileByFTP(String server, String user, String pass, String localPath, String remotePath, String parIndicaFTP) {
		try {
			gloHuboError = "No";

			URL url = new URL("ftp://" + user + ":" + pass + "@" + server + remotePath + ";type=i");
			URLConnection urlc = url.openConnection();
			InputStream is = urlc.getInputStream();
			BufferedWriter bw = new BufferedWriter(new FileWriter(localPath));

			int c;
			while ((c = is.read()) != -1) {
				bw.write(c);
			}
			
			//Eliminar Archivo a crear
			if (parIndicaFTP.equals("S")) { //S=Eliminación Puntual
				log.error("***** Archivo copiado: " + localPath + ".");
			}
			//Fin

			is.close();
			bw.flush();
			bw.close();	
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("");
			log.error("ERROR al acceder al FTP Server: " + ex.getMessage());
			log.error("***** remotePath: " + remotePath + ".");
			log.error("***** localPath: " + localPath + ".");
			gloHuboError = "Si";
		}
	}
	
	public void subProcesar(String xTipo, String rutaProyecto, char xCar, String parPeriodo, String xTipoGlosa) {
		String rutaArchivosMonOrigen = "";
		String rutaArchivosMonDestino = "";
		String xTipoVal = "";

		try {			
			//Leer properties donde esta la Ruta Origen y Destino
			Properties properties = new Properties();

			try  {
				InputStream input = Rutas.class.getClassLoader().getResourceAsStream("application.properties");
				if (input == null) {
					System.out.println("Archivo 'application.properties' no encontrado");
				}
				properties.load(input);
				int xTipoint= Integer.parseInt(xTipo);
			    switch (xTipoint) {
			        case 1: //NC
			        	xTipoVal = "1";
			        	rutaArchivosMonOrigen = properties.getProperty("NC_ruta_origen");
			        	rutaArchivosMonDestino = properties.getProperty("NC_ruta_destino");
			            break;
			        case 2: //CF
			        	xTipoVal = "2";
			        	rutaArchivosMonOrigen = properties.getProperty("CF_ruta_origen");
			        	rutaArchivosMonDestino = properties.getProperty("CF_ruta_destino");
			            break;
			        case 3: //NL
			        	xTipoVal = "2";
			        	rutaArchivosMonOrigen = properties.getProperty("NL_ruta_origen");
			        	rutaArchivosMonDestino = properties.getProperty("NL_ruta_destino");
			            break;
			        case 4: //AT
			        	xTipoVal = "2";
			        	rutaArchivosMonOrigen = properties.getProperty("AT_ruta_origen");
			        	rutaArchivosMonDestino = properties.getProperty("AT_ruta_destino");
			            break;
			        default:
			            break;
			    }			
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}			
			//Fin			
			 
			log.info(" ");
			log.info("INICIO Proceso de Unificación de Nóminas.");
			log.info("*** TIPO = " + xTipo + " (" + xTipoGlosa + ")");

			log.info(" ");
			log.info("CARPETAS:");
			log.info("*** Carpeta Origen: " + rutaArchivosMonOrigen);
			log.info("*** Carpeta Destino: " + rutaArchivosMonDestino);
			log.info(" ");

			System.out.println("Espere por favor, ahora vamos a Unificar las Nóminas...");
			System.out.println("");

			// Crear Ruta Destino si no existe
			File f1 = new File(rutaArchivosMonDestino);
			if (!f1.exists()) {
				if (f1.mkdir()) {
					log.info("*** Carpeta Destino creada: " + f1);
				} else {
					log.info("*** Error al crear Carpeta Destino: " + f1);
				}
			}
			// Fin Ruta Destino
			
			//Indicar Periodos existentes, no incluyendo el actual
			log.info("Periodos existentes en '" + f1 + "', sin incluir el actual a procesar:");
			File listFile1[] = f1.listFiles();
			int jj1 = 0;
			for (int ii1 = 0; ii1 < listFile1.length; ii1++) {
				if (listFile1[ii1].isDirectory()) {
					jj1 += 1;
					log.info(jj1 + ") " + listFile1[ii1]);
				}
			}
			log.info("");
			//Fin					

			// Carpeta Periodo Destino
			File dirPeriodoDestino = new File(rutaArchivosMonDestino + "\\" + parPeriodo);
			String sdirPeriodoDestino = rutaArchivosMonDestino + "\\" + parPeriodo;

			if (!dirPeriodoDestino.exists()) { // Crear Carpeta Periodo Destino si NO existe
				log.info("CREAR CARPETA PERIODO:");
				log.info(" ");

				if (dirPeriodoDestino.mkdir()) {
					log.info("*** Carpeta Periodo creada: " + dirPeriodoDestino);
				} else {
					log.error("*** Error al crear Carpeta Periodo: " + dirPeriodoDestino);
				}
			} else { // Si Carpeta Periodo Destino existe, eliminar todas las Carpetas RUT
				File listFile[] = dirPeriodoDestino.listFiles();

				log.info("LIMPIAR CARPETA PERIODO EXISTENTE (" + listFile.length + " ítems a eliminar):");
				log.info(" ");

				for (int ii = 0; ii < listFile.length; ii++) {
					if (listFile[ii].isDirectory()) {
						// Primero: Eliminar los archivos que contiene Carpeta RUT
						subEliminar(listFile[ii], ii + 1, "S");

						// Segundo: Eliminar Carpeta RUT
						if (listFile[ii].delete()) {
							log.info("***    Carpeta RUT eliminada...: " + listFile[ii]);
						} else {
							log.info("*** " + (ii + 1) + ") Error eliminar Carpeta RUT: " + listFile[ii]);
						}
					} else {
						if (listFile[ii].delete()) {
							log.info("*** " + (ii + 1) + ") Archivo eliminado...: " + listFile[ii]);
						} else {
							log.info("*** " + (ii + 1) + ") Error eliminar Archivo: " + listFile[ii]);
						}
					}
				}
			}
			// Fin	

			// Almacenar en Lista todos los Archivos de la Carpeta Origen
			File rutaOrigen = new File(rutaArchivosMonOrigen);
			String[] rut_archivos = rutaOrigen.list();

			log.info(" ");
			log.info("UNIFICAR ARCHIVOS:");
			log.info("Se Unificarán [" + rut_archivos.length + "] archivos Nómina.");
			System.out.println(" ");

			// Unificar los Archivos
			subUnificar(rut_archivos, dirPeriodoDestino, rutaOrigen, xCar, sdirPeriodoDestino, rutaArchivosMonOrigen, xTipoVal);
			
			//Indicar Periodos existentes, incluyendo el actual
			File f2 = new File(rutaArchivosMonDestino);
			log.info("");
			log.info("Periodos existentes en '" + f2 + "', incluyendo el actual a procesado:");
			File listFile2[] = f2.listFiles();
			int jj2 = 0; int kk2 = listFile2.length;
			for (int ii2 = 0; ii2 < kk2; ii2++) {
				if (listFile2[ii2].isDirectory()) {
					jj2 += 1;
					log.info(jj2 + ") " + listFile2[ii2]);
				}
			}
			
			if (jj2 > 2) {
				int bb3 = jj2 - 2; //Periodos a eliminar
				
				log.info("");
				log.info("ELIMINAR " + bb3 + " PERIODOS SOBRANTES (Deben haber sólo 2):");
				log.info(" ");
				
				File listFile3[] = f2.listFiles();
				int jj3 = 0; int kk3 = listFile2.length;
				for (int ii3 = 0; ii3 < kk3; ii3++) {
					if (listFile3[ii3].isDirectory()) {
						jj3 += 1;
						if (jj3 <= bb3) { //Eliminar Periodo
							File listFile4[] = listFile3[ii3].listFiles();
							
							for (int ii4 = 0; ii4 < listFile4.length; ii4++) {
								if (listFile4[ii4].isDirectory()) {
									// Primero: Eliminar los archivos que contiene Carpeta RUT
									subEliminar(listFile4[ii4], ii4 + 1, "N");
				
									// Segundo: Eliminar Carpeta RUT
									if (listFile4[ii4].delete()) {
									} else {
										log.info("*** " + (ii4 + 1) + ") Error eliminar Carpeta RUT: " + listFile4[ii4]);
									}
								} else {
									if (listFile4[ii4].delete()) {
									} else {
										log.info("*** " + (ii4 + 1) + ") Error eliminar Archivo: " + listFile4[ii4]);
									}
								}
							}
							
							if (listFile3[ii3].delete()) {
								log.info(jj3 + ") '" + listFile3[ii3] + "' (ELIMINADO!)");
							} else {
								log.info("*** Error, Periodo No se pudo eliminar: '" + listFile3[ii3] + "'");
							}
						} else {
							log.info(jj3 + ") '" + listFile3[ii3] + "' (NO eliminado)");
						}
					}
				}						
			} else {
				log.info("");
				log.info("NO se elimina ningún Periodo, hay sólo 2 o menos.");		
			}
			//Fin				

			log.info(" ");
			log.info("PROCESO TERMINADO Ok! (Parámetro: Tipo = " + xTipo + " - " + xTipoGlosa + ")");
			String xHora = f_t.format(new Date());
			String xFecha = f_d.format(new Date());
			log.info("*** Fecha/Hora Fin Proceso: " + xFecha + " " + xHora);
			log.info(" ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void subEliminar(File f, int ii, String xIndica) {
		String xSiNo = "N";
		
		if (f.listFiles().length > 0) {
			File delFile[] = f.listFiles();

			int i = f.listFiles().length;

			for (int j = 0; j < i; j++) {
				xSiNo = "N";
				
				if (delFile[j].isDirectory()) { // Borrar primero los archivos
					subEliminar2(delFile[j], ii);
					xSiNo = "S";
				}

				delFile[j].delete(); // eliminar un archivo/directorio

				if (xIndica == "S") {
					if (xSiNo == "N") {
						log.info("*** " + ii + ") Archivo Nómina eliminado: " + delFile[j]);
					} else {
						log.info("*** " + ii + ") Carpeta especial eliminada: " + delFile[j]);
					}
				}
			}
		}
	}

	public void subEliminar2(File f, int ii) {
		if (f.listFiles().length > 0) {
			File delFile[] = f.listFiles();

			int i = f.listFiles().length;

			for (int j = 0; j < i; j++) {
				if (delFile[j].isDirectory()) {
					log.error("*** " + ii + ") Directorio MUY RARO: " + delFile[j]);
				} else {
					delFile[j].delete(); // eliminar un archivo
				}

				log.info("*** " + ii + ") Archivo Especial eliminado: " + delFile[j]);
			}
		}
	}
	
	public void subEliminar3(File f) {
		if (f.listFiles().length > 0) {
			File delFile[] = f.listFiles();

			int i = f.listFiles().length;

			for (int j = 0; j < i; j++) {
				if (delFile[j].isDirectory()) {
					log.error("*** Directorio MUY RARO: " + delFile[j]);
				} else {
					delFile[j].delete(); // eliminar un archivo
				}

				log.info("*** Archivo Especial eliminado: " + delFile[j]);
			}
		}
	}	

	public void subEspeciales(String xDirRut, String xRut, String xDirDescarga) throws IOException {

		log.info("Dir Rut para verificar Especiales: " + xDirRut);
		File dirEspecial = new File(xDirRut);

		if (dirEspecial.exists()) { // Verificar si existe Directorio con Nóminas especiales
			String[] xLista = dirEspecial.list();
			File dirDescarga = new File(xDirDescarga + "\\" + xRut + "_especiales");
			log.info("Agregar Carpeta Rut especiales: " + xDirDescarga + "\\" + xRut + "_especiales");
			if (dirDescarga.mkdir()) {
				int ii = xLista.length;
				
				log.info("*** Carpeta 'especiales' creada: " + dirDescarga + " (con " + ii + " archivos)");
				for (int jj = 0; jj < ii; jj++) {
					Path archivoEspecial_origen = Paths.get(xDirRut + "\\" + xLista[jj]);
					Path archivoEspecial_destino = Paths.get(dirDescarga + "\\" + xLista[jj]);

					Files.copy(archivoEspecial_origen, archivoEspecial_destino, StandardCopyOption.REPLACE_EXISTING);

					log.info("*** Archivo origen - " + (jj + 1) + ": " + archivoEspecial_origen);
					log.info("*** Archivo destino - " + (jj + 1) + ": " + archivoEspecial_destino);
				}
			}
		}
	}

	public void subUnificar(String[] rut_archivos, File rutaLlegada, File rutaOrigen, char xCar, String srutaLlegada,
		String srutaOrigen, String xTipoVal) throws IOException {
		int nn = 0, jj = 0, pp0 = 0, pp1 = 0, pp2 = 0, pp_Ant = 0, largoLinea = 0, largoLista = 0;
		String xTextoCorto1 = "", xTextoCorto2 = "", lineaConComas = "", lineaConPyComas = "";
		Long num_rut_corto = (long) 0;
		String xHayError = "", xTipoFormato = "";
		int xTotalErrores = 0;
		String rut_corto = "";

		String lineaLeida = "";

		for (int ii = 0; ii < rut_archivos.length; ii++) {
			// Sacar el RUT del nombre del archivo
			rut_corto = rut_archivos[ii];

			jj += 1;
			if (jj >= 100) {
				jj = 0;
				System.out.println("Van " + (ii + 1) + " archivos de " + rut_archivos.length + "...");
			}

			//Validar Formato del archivo
			//xTipoVal = "1" --> 001698346-105-001.txt
			//xTipoVal = "2" --> 00129239.901
			//
			if (xTipoVal == "1") {
				xTipoFormato = "999999999-999-999.txt";
			} else {
				xTipoFormato = "09999999.988, los 9 son el RUT y 88 la Oficina";
			}
			
			gloRut_corto = rut_corto;
			
			xHayError = funValidarArchivo(rutaOrigen + "\\" + rut_archivos[ii], xCar, xTipoVal, "N", 0, 0);
			
			if (xHayError == "S") {
				xTotalErrores += 1;
				
				log.error("");
				log.error(gloErrorArchivo);						
				log.error("### Error, archivo no se procesa, no corresponde al formato '" + xTipoFormato + "': " + rut_archivos[ii]);
			} else {
				//Sacar los Ceros a la izquierda
				num_rut_corto = Long.parseLong(gloRut_corto);
				gloRut_corto = Long.toString(num_rut_corto);
				// Fin
	
				// Borrar archivos, o bien, Crear Directorio del Periodo+Rut en Carpeta Destino
				File dirPeriodoDestinoRut = new File(rutaLlegada + "\\" + gloRut_corto);
	
				if (!dirPeriodoDestinoRut.exists()) {
					nn += 1;
					log.info(" ");
					if (dirPeriodoDestinoRut.mkdir()) {
						log.info("*** " + nn + ". Carpeta RUT creada: " + dirPeriodoDestinoRut);
					} else {
						log.error("*** " + nn + ". Error al crear Carpeta RUT: " + dirPeriodoDestinoRut);
					}
				}
				// Fin
	
				File archivoOriginal = new File(rutaOrigen + "\\" + rut_archivos[ii]);
	
				if (archivoOriginal.isDirectory()) {
					log.info("-D- [" + (ii + 1) + "] es Directorio: " + archivoOriginal);
					subEspeciales(rutaOrigen + "\\" + rut_archivos[ii], rut_archivos[ii], srutaLlegada + "\\" + gloRut_corto);
				} else {
					// Crear archivo donde se unificarán las Nóminas
					File ruta2 = new File(dirPeriodoDestinoRut + "\\1.txt");
					File ruta3 = new File(dirPeriodoDestinoRut + "\\1.csv");
					File ruta4 = new File(dirPeriodoDestinoRut + "\\1PC.csv");
					// Fin
	
					try {
						// Crear archivo con Nóminas unificadas (.TXT), Si ya existe NO lo crea.
						if (!ruta2.exists()) {
							ruta2.createNewFile();
							log.info("*** Archivo Unificado Creado (1.txt): " + ruta2);
						}
	
						// Crear archivo con Nóminas unificadas (.CSV), Si ya existe NO lo crea.
						if (!ruta3.exists()) {
							ruta3.createNewFile();
							log.info("*** Archivo Unificado Creado (1.csv): " + ruta2);
						}
	
						// System.out.println("Archivos 1.txt y 1.csv creados.");
	
						BufferedReader archivoLeer = new BufferedReader(new FileReader(archivoOriginal));
						
						BufferedWriter archivoEscribe = new BufferedWriter(
								new OutputStreamWriter(new FileOutputStream(ruta2, true), "iso-8859-1"));
						BufferedWriter archivoEscribe3 = new BufferedWriter(
								new OutputStreamWriter(new FileOutputStream(ruta3, true), "iso-8859-1"));
						BufferedWriter archivoEscribe4 = new BufferedWriter(
								new OutputStreamWriter(new FileOutputStream(ruta4, true), "iso-8859-1"));
						
	
						largoLista = gloListaComas.size();
	
						while ((lineaLeida = archivoLeer.readLine()) != null) {
							largoLinea = lineaLeida.length();
	
							if ((largoLinea >= gloLargoReg) && (largoLinea > 0)) {
								// Graba el 1.txt
								archivoEscribe.write(lineaLeida + "\r\n");
	
								lineaConComas = "";
								lineaConPyComas = "";
	
								// Graba el 1.csv
								for (int kk = 0; kk < largoLista; kk++) {
									pp0 = Integer.parseInt(gloListaComas.get(kk));
	
									if (kk == 0) {
										pp1 = 0;
										pp2 = pp0;
										xTextoCorto1 = lineaLeida.substring(pp1, pp2);
										lineaConComas = xTextoCorto1;
										lineaConPyComas = xTextoCorto1;
									} else {
										pp1 = pp_Ant;
										pp2 = pp0;
										xTextoCorto1 = lineaLeida.substring(pp1, pp2);
										lineaConComas += "," + xTextoCorto1;
										lineaConPyComas += ";" + xTextoCorto1;
									}
	
									pp_Ant = pp2;
								} // Fin 1.csv
	
								xTextoCorto2 = lineaLeida.substring(pp2, largoLinea);
								lineaConComas += xTextoCorto2;
								lineaConPyComas += xTextoCorto2;
								//escribe CSV con comas
								archivoEscribe3.write(lineaConComas + "\r\n");
								//escribe CSV con punto y coma
								archivoEscribe4.write(lineaConPyComas + "\r\n");
							} else {
								if (largoLinea > 0) {
									log.info("Largo linea menor a " + gloLargoReg + ": " + largoLinea + ": '"
											+ lineaLeida + "'");
								}
							}
						}
	
						archivoEscribe.close();
						archivoEscribe3.close();
						archivoEscribe4.close();
						archivoLeer.close();
	
						log.info("+++ [" + (ii + 1) + "] Archivo Unificado OK: " + archivoOriginal);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		log.error("***");
		log.error("*** Total Archivos no procesados por no corresponder al formato: " + xTotalErrores);
		log.error("***");
	}
	
	public String funValidarArchivo(String xArchivo, char xCar, String xTipoVal, String xIndica, int tt, long xLeidos) {
		String xHayError = "N";
		String xValorPaso = "";
		boolean esNumerico = true;
		int xLargo1 = 0;
		int xLargo2 = 0;
		String xEncontro_Guion = "";
		String xValorReal = "";
		String xRUT1 = "", xRUT2 = "", xOFICINA = "";
		
		xHayError = "N";
		gloErrorArchivo = "";
		
		int contador = 0;
		xEncontro_Guion = "N";
		for (int k = 0; k < gloRut_corto.length(); k++) {
			contador++;
			if (gloRut_corto.charAt(k) == xCar) {
				xEncontro_Guion = "S";
				break;
			}
		}

		xLargo1 = gloRut_corto.length();
		xValorReal = gloRut_corto;
		
		if (xEncontro_Guion.equals("S")) {
			gloRut_corto = gloRut_corto.substring(0, contador - 1);
		} else {
			gloRut_corto = gloRut_corto.substring(0, contador);
		}
		
		/*
		xLargo2 = gloErrorArchivo = ".length();
		if ((xLargo1 - xLargo2) == 12) {
			log.error("*** OJO_ValorReal: " + xValorReal);	
			log.error("*** OJO_rut_corto: " + rut_corto);	
			
			xValorPaso = xValorReal.substring(xLargo2 + 1, xLargo2 + 4); //Primeros 3 numeros
			log.error("*** OJO_ValorPaso: " + xValorPaso);		
			xValorPaso = xValorReal.substring(xLargo2 + 4, xLargo2 + 5); //Guion
			log.error("*** OJO_ValorPaso: " + xValorPaso);	
			xValorPaso = xValorReal.substring(xLargo2 + 5, xLargo2 + 8); //Segundos 3 numeros
			log.error("*** OJO_ValorPaso: " + xValorPaso);			
			xValorPaso = xValorReal.substring(xLargo2 + 8, xLargo2 + 12); //Primeros 3 numeros
			log.error("*** OJO_ValorPaso: " + xValorPaso);		
		}
		*/
		
		esNumerico =  gloRut_corto.matches("[+-]?\\d*(\\.\\d+)?");
		
		if (esNumerico == false) {
			xHayError = "S";
			gloErrorArchivo = "### RUT inicial no numerico: " + gloRut_corto;
		} else {			
			File archivoOriginal_paso = new File(xArchivo);
			
			if (!archivoOriginal_paso.isDirectory()) {					
				xLargo2 = gloRut_corto.length();
				
				if (xTipoVal == "1") {
					//El formato aqui es 999999999-999-999.txt
					//
					if ((xLargo1 - xLargo2) != 12) {
						xHayError = "S";
						gloErrorArchivo = "### Formato despues del RUT incorrecto.";
					} else { //999-999.txt
						gloRut_correcto_Solo_Rut = gloRut_corto;
						
						xValorPaso = xValorReal.substring(xLargo2 + 1, xLargo2 + 4); //Primeros 3 numeros
						esNumerico =  xValorPaso.matches("[+-]?\\d*(\\.\\d+)?");
						if (esNumerico == false) {
							xHayError = "S";
							gloErrorArchivo = "### Primeros 3 numeros: " + xValorPaso;
						} else {
							xValorPaso = xValorReal.substring(xLargo2 + 4, xLargo2 + 5); //Guion
							if (xValorPaso.charAt(0) != '-') {
								xHayError = "S";
								gloErrorArchivo = "### Guion: " + xValorPaso;
							} else {
								xValorPaso = xValorReal.substring(xLargo2 + 5, xLargo2 + 8); //Segundos 3 numeros
								esNumerico =  xValorPaso.matches("[+-]?\\d*(\\.\\d+)?");
								if (esNumerico == false) {
									xHayError = "S";
									gloErrorArchivo = "### Segundos 3 numeros: " + xValorPaso;
								} else {
									xValorPaso = xValorReal.substring(xLargo2 + 8, xLargo2 + 12); //Extension
									if (!xValorPaso.toUpperCase().equals(".TXT")) {
										xHayError = "S";
										gloErrorArchivo = "### Extension: '" + xValorPaso + "'";
									}
								}
							}
						}
					}
				} else {
					//El formato aqui es 099999999.988
					//Donde:
					//   99999999.9 es el RUT
					//   88 es la Oficina
					if ((xLargo1 - xLargo2) != 4) {
						xHayError = "S";
						gloErrorArchivo = "### Formato despues del RUT incorrecto.";
					} else { //999
						xValorPaso = xValorReal.substring(xLargo2 + 1, xLargo2 + 4); //3 numeros
						esNumerico =  xValorPaso.matches("[+-]?\\d*(\\.\\d+)?");
						if (esNumerico == false) {
							xHayError = "S";
							gloErrorArchivo = "### 3 numeros: " + xValorPaso;
						} else {
							if (xIndica == "S") {
								//El formato está correcto, ahora se armará el RUT correcto
								xRUT1 = xValorReal.substring(0, xLargo2);                        //RUT antes del punto
								xRUT2 = xValorReal.substring(xLargo2 + 1, xLargo2 + 2);          //Primer caracter despues del Punto
								xOFICINA = "0" + xValorReal.substring(xLargo2 + 2, xLargo2 + 4); //2 ultimos numeros
							
								gloRut_correcto = xRUT1 + xRUT2 + "." + xOFICINA;
								gloRut_correcto_Solo_Rut = xRUT1 + xRUT2;
							
								log.info("[" + xLeidos + " de " + tt + "] Archivo original: " + xValorReal + " | xRUT1: '" + xRUT1 + "' | xRUT2: '" + xRUT2 + "' | xOFICINA: '" + xOFICINA + "' | Archivo nuevo: " + gloRut_correcto);
							}
						}
					}
				}
			}		
		}
		
		if (xHayError == "S") {
			if (xIndica == "S") {
				log.info("[" + xLeidos + " de " + tt + "] Archivo original: " + xValorReal + " | Hay Error: " + gloErrorArchivo);
			}
		}
		
		return xHayError;
	}

	public void subCreaIndice(String rutaProyecto, String xTipo, String xPeriodo) {
		File xArchivo = new File("");
		String xTexto = "";
		String xRutaDestino = "";

		try {
			//Ruta Destino según el Tipo
			Properties properties = new Properties();

			try {
				InputStream input = Rutas.class.getClassLoader().getResourceAsStream("application.properties");
				if (input == null) {
					log.error("Archivo 'application.properties' no encontrado");
				}
				properties.load(input);
				
				int xTipoint= Integer.parseInt(xTipo);
		        switch (xTipoint) {
		        case 1: //NC
		        	xRutaDestino = properties.getProperty("NC_ruta_destino");
		            break;
		        case 2: //CF
		        	xRutaDestino = properties.getProperty("CF_ruta_destino");
		            break;
		        case 3: //NL
		        	xRutaDestino = properties.getProperty("NL_ruta_destino");
		            break;
		        case 4: //AT
		        	xRutaDestino = properties.getProperty("AT_ruta_destino");
		            break;
		        default:
		            break;
		    }	
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//Fin

			xArchivo = new File(xRutaDestino + "\\" + xPeriodo + "\\ArchivoIndice.txt");

			//Eliminar el archivo anterior
			if (xArchivo.exists()) {
				xArchivo.delete();
			}

			xArchivo.createNewFile();
			BufferedWriter archivoIndice = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(xArchivo, true), "iso-8859-1"));

			File dirPeriodoDestino = new File(xRutaDestino + "\\" + xPeriodo);
			File listFile[] = dirPeriodoDestino.listFiles();

			for (int ii = 0; ii < listFile.length; ii++) {
				if (listFile[ii].isDirectory()) {
					xTexto = listFile[ii].getName();
					archivoIndice.write(xTexto + "\r\n");
				}
			}

			archivoIndice.close();

			log.info("*** Archivo Indice creado: " + xArchivo);
			log.info("*** Fin Proceso!");
			log.info(" ");
		} catch (Exception e) {
			log.info("*** Error al crear Archivo Indice: " + e.getMessage() + " (" + xArchivo + ")");
		}
	}
}
