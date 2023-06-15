package cl.liv.archivos.comun;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import cl.jfactory.planillas.service.util.UtilLogWorkflow;
import cl.liv.archivos.comun.txt.ManejoArchivoTXT;
import cl.liv.comun.utiles.UtilesComunes;

public class ArchivosUtiles {

	
	public static String obtenerArchivoPorComando(String comando){
		comando = UtilesComunes.reemplazarVariables(comando);
		if(comando != null && comando.startsWith("FILE;")){
			String partes[] = comando.split(";");
			if(partes.length==2){
				if(existeArchivoPorRuta(partes[1]) != null){
					return partes[1];
				}
			}
		}
		else if(comando != null && comando.startsWith("FILE_REGEX;")){
			String salida = "";
			String partes[] = comando.split(";");
			if(partes.length==3){
				File[] archivos = obtenerArchivosPorExpresionRegular(partes[1], partes[2]);
				if(archivos != null && archivos.length>0){
					for(int i=0; i< archivos.length; i++){
						salida = salida +archivos[i].getAbsolutePath()+";";
					}
					return salida.substring(0,salida.length()-1);
				}
			}
		}
		
		return null;
	}
	public static String obtenerArchivosPorComando(String rutaArchivo){
		//return new File(rutaArchivo).exists();
		return null;
	}
	
	public static String existeArchivoPorRuta(String rutaArchivo){
		if(new File(rutaArchivo).exists()){
			return "OK";
		}
		return null;
	}
	
	public static boolean existeArchivoPorRutaAsBoolean(String rutaArchivo){
		String existeArchivo = existeArchivoPorRuta(rutaArchivo); 
		if( existeArchivo != null && !existeArchivo.toLowerCase().startsWith("error:")  ){
			return true;
		}
		return false;
	}
	

	public static String existeArchivoPorRutaFTP(String paramsFTP, String comando){
		
		String[] partesFTP = paramsFTP.split(";");
		UtilLogWorkflow.debug("buscando archivo...["+paramsFTP+"]["+comando+"]");
		String sFTP = partesFTP[1];
		String sUser = partesFTP[2];
		String sPassword = partesFTP[3];
		FTPClient client = new FTPClient();
		try {
		  UtilLogWorkflow.debug("antes de conectar...");
		  client.connect(sFTP,Integer.parseInt(partesFTP[4]));
		  UtilLogWorkflow.debug("conectado...");
		  
		  boolean login = client.login(sUser,sPassword);
		  
		  client.enterLocalPassiveMode();
		  client.setFileType(FTP.BINARY_FILE_TYPE);
		  if(login){
			  comando = UtilesComunes.reemplazarVariables(comando); 
			  boolean cambioDirectorio = client.changeWorkingDirectory(partesFTP[5]);
			  if(cambioDirectorio){
				  String[] partesArchivo = comando.split(";");
				  if(partesArchivo[0].equals("FILE")){
					  FTPFile[] archivos = client.listFiles();
					  if(archivos != null && archivos.length>0){
						  for(int i=0; i< archivos.length; i++){
							  if(archivos[i].getName().trim().equals(partesArchivo[1])){
							  	return "OK";
							  }
						  }
					  }
				  }
				  
			  }
		  }
		  else{
			  return "error:login error...";
		  }
		  client.logout();
		  client.disconnect();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			
		}
		
		return null;
	}
	
	public static String guardarArchivoPorRutaFTP(String paramsFTP, String comando, String rutaLocal){
		String retorno = null;
		String[] partesFTP = paramsFTP.split(";");
		UtilLogWorkflow.debug("buscando archivo...["+paramsFTP+"]["+comando+"]");
		String sFTP = partesFTP[1];
		String sUser = partesFTP[2];
		String sPassword = partesFTP[3];
		FTPClient client = new FTPClient();
		try {
		  client.connect(sFTP,Integer.parseInt(partesFTP[4]));
		  
		  boolean login = client.login(sUser,sPassword);
		  
		  client.enterLocalPassiveMode();
		  client.setFileType(FTP.BINARY_FILE_TYPE);
		  if(login){
			  comando = UtilesComunes.reemplazarVariables(comando); 
			  boolean cambioDirectorio = client.changeWorkingDirectory(partesFTP[5]);
			  String[] partesArchivo = comando.split(";");
			  boolean resultado = client.storeFile( partesFTP[5] +"/"+partesArchivo[1], new FileInputStream(new File(rutaLocal)));
			  if( resultado ){
				  retorno = partesFTP[5] +"/"+partesArchivo[1];
			  }
			  else{
				  retorno = "error:No se ha podido guardar el archivo en ["+partesFTP[5] +"/"+partesArchivo[1]+"]";
			  }
		  }
		  else{
			  retorno = "error: login FTP error ("+sUser+")";
		  }
		  client.logout();
		  client.disconnect();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			retorno = "error:"+ ioe.getMessage();
			
		} catch (Exception e){
			e.printStackTrace();
			retorno = "error:"+ e.getMessage();
			
		}
		
		return retorno;
	}
	
	
	public static String descargarArchivoPorRutaFTP(String paramsFTP, String comando, String pathSalida){
		
		String[] partesFTP = paramsFTP.split(";");
		UtilLogWorkflow.debug("buscando archivo...["+paramsFTP+"]["+comando+"]");
		String sFTP = partesFTP[1];
		String sUser = partesFTP[2];
		String sPassword = partesFTP[3];
		FTPClient client = new FTPClient();
		try {
		  client.connect(sFTP,Integer.parseInt(partesFTP[4]));
		  
		  boolean login = client.login(sUser,sPassword);
		  
		  client.enterLocalPassiveMode();
		  client.setFileType(FTP.BINARY_FILE_TYPE);
		  if(login){
			  comando = UtilesComunes.reemplazarVariables(comando); 
			  boolean cambioDirectorio = client.changeWorkingDirectory(partesFTP[5]);
			  if(cambioDirectorio){
				  String[] partesArchivo = comando.split(";");
				  if(partesArchivo[0].equals("FILE")){
					  FTPFile[] archivos = client.listFiles();
					  if(archivos != null && archivos.length>0){
						  for(int i=0; i< archivos.length; i++){
							  if(archivos[i].getName().trim().equals(partesArchivo[1])){
							  		String path =  pathSalida+archivos[i].getName()+"_"+Math.random();
								  	File downloadFile1 = new File(path);
						            
						            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
						            boolean success = client.retrieveFile(archivos[i].getName(), outputStream1);
						            outputStream1.close();
						 
						            if (success) {
						            	UtilLogWorkflow.debug("guardando archivo ["+path+"]");
						                return path;
						            }
								  
							  }
						  }
					  }
				  }
				  
			  }
		  }
		  else{
			  UtilLogWorkflow.debug("login error...");
		  }
		  client.logout();
		  client.disconnect();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			
		}
		
		return null;
		//return new File(rutaArchivo).exists();
	}
	
	
	public static boolean existeArchivoPorExpresionRegular(String rutaDirectorio, String regex){
		boolean existe = false;
		
		File[] files = ArchivosUtiles.obtenerArchivosPorExpresionRegular(rutaDirectorio,regex);
		if(files != null && files.length>0)
			existe = true;
		
		return existe;
	}
	
	public static File[] obtenerArchivosPorExpresionRegular(String rutaDirectorio, final String regex){
		 
		
		return new File(rutaDirectorio).listFiles(new FileFilter() {
			
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				return pathname.getName().matches(regex);
				
			}
		});
		
	}
	
	public static boolean crearDirectorioPorRuta(String rutaDirectorio ){
		File folder = new File(rutaDirectorio);
		folder.setWritable(true);
		folder.setWritable(true, false);
		folder.setExecutable(true, false);
		folder.setReadable(true, false);
		return folder.mkdir();
	}
	
	public static boolean crearArchivoPorRuta(String rutaArchivo ){
		try {
			File f = new File(rutaArchivo);
			f.setWritable(true);
			return f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean crearDirectorioCompletoSiNoExiste(String path){
		boolean estado = true;
		String[] partes = path.split("/");
		String rutaAcumulada = "";
		for(int i=0; i<partes.length - 1; i++){
			rutaAcumulada = rutaAcumulada + partes[i] + "/" ;
			if(!new File(rutaAcumulada).exists()){
				crearDirectorioPorRuta(rutaAcumulada);
			}
		}
		
		
		return new File(path).exists();
	}
	
	public static boolean eliminarArchivoPorRuta(String rutaArchivo ){
		try {
			File f = new File(rutaArchivo);
			return f.delete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean eliminarDirectorioRecursivo(String rutaArchivo ){
		try {
			File f = new File(rutaArchivo);
			if(f.isDirectory()){
				
				File[] archivos = f.listFiles();
				for(int i=0; i<archivos.length; i++){
					if(archivos[i].isDirectory()){
						eliminarDirectorioRecursivo(archivos[i].getAbsolutePath());
					}
					eliminarArchivoPorRuta(archivos[i].getAbsolutePath());
				}
			}
			
			
			
			return f.delete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static void ssh(){
		JSch jsch = new JSch();
		try {
			Session session = jsch.getSession("root","139.99.192.71",22);
			session.setPassword("teA1znze");
			Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
			session.connect();
			UtilLogWorkflow.debug("ok");
			Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp c = (ChannelSftp) channel;
            
            c.put("/home/clillo/nrp/output/comprimidos/201805_427822.zip", "/home/libacacheftp");
            channel.disconnect();
            session.disconnect();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SftpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String sumarArchivos(ArrayList rutaArchivos, String rutaSalida){
		PrintWriter pw = ManejoArchivoTXT.getOpenedFileToWrite(rutaSalida);
		if(rutaArchivos != null && rutaArchivos.size() > 0 & pw != null){
			for(int i=0; i< rutaArchivos.size(); i++){
				BufferedReader br = ManejoArchivoTXT.getOpenedFileToRead(rutaArchivos.get(i).toString(), 0, null);
				String registro = ManejoArchivoTXT.getLineFromFileOpened(br);
				
				while(registro != null && registro.length() > 0){
					ManejoArchivoTXT.putLineFromFileOpened(pw, registro);
					registro = ManejoArchivoTXT.getLineFromFileOpened(br);
					
				}
				ManejoArchivoTXT.closeFileToRead(br);
				
			}
			
			
		}
		
		if(pw != null){
			ManejoArchivoTXT.closeFileToWrite(pw);
		}
		if(new File(rutaSalida).exists())
			return rutaSalida;
		
		return null;
	}
}
