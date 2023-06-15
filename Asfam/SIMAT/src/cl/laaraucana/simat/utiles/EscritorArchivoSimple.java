package cl.laaraucana.simat.utiles;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import com.ibm.as400.access.AS400SecurityException;

/*
 * clase que realiza la escritura de archivo como texto simple.
 * */
public class EscritorArchivoSimple {

	public void writerByIFS(String periodo, String nombreArchivo) throws Exception {
		LectorPropiedades lp = new LectorPropiedades();
		System.out.println("* * * * * IFS: LOGIN  * * * * *");
		String serverIfs = lp.getAtributoRepositorio("serverIfs");
		String userIfs = lp.getAtributoRepositorio("userIfs");
		String passIfs = lp.getAtributoRepositorio("passIfs");
		Util_SendFileToAS400 usf4 = new Util_SendFileToAS400(serverIfs, userIfs, passIfs);

		System.out.println("* * * * * IFS: directorio * * * * *");
		String rutaLocalTemporales = lp.getAtributoRepositorio("rutaLocalTemporales");
		String rutaIfsdestino = lp.getAtributoRepositorio("rutaIfsdestino");
		String rutaIfsOrigen = lp.getAtributoRepositorio("rutaIfsOrigen");

		usf4.crearDirectorio(rutaIfsdestino + periodo);

		rutaIfsdestino += periodo + "/" + nombreArchivo;
		rutaLocalTemporales += nombreArchivo;
		final byte[] buf = new byte[16 * 1024 * 1024];
		FileInputStream fis = new FileInputStream(rutaLocalTemporales);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		for (int readNum; (readNum = fis.read(buf)) != -1;) {
			bos.write(buf, 0, readNum); //no doubt here is 0
			//Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
			// System.out.println("read " + readNum + " bytes,");
		}
		byte[] bytesArchivo = bos.toByteArray();
		usf4.crearArchivo(rutaIfsdestino, bytesArchivo);
		System.out.println("* * * * * IFS: END  writerByIFS * * * * *");
	}//END writerByIFS

	/**
	 * metodo que realiza una copia de archivo desde la carpeta local
	 * hacia una ruta remota en sistema de archivos integrado mediante jt400,
	 * recibe por parametros el periodo y el nombre archivo a copiar.
	 * **/
	public boolean setCopyByIFS(String carpetaDestino, String nameFile, String rutaArchivo) throws Exception {
		boolean result = false;
		LectorPropiedades lp = new LectorPropiedades();
		System.out.println("* * * * * IFS: LOGIN  * * * * *");
		String serverIfs = lp.getAtributoRepositorio("serverIfs");
		String userIfs = lp.getAtributoRepositorio("userIfs");
		String passIfs = lp.getAtributoRepositorio("passIfs");
		Util_SendFileToAS400 usf4 = new Util_SendFileToAS400(serverIfs, userIfs, passIfs);

		System.out.println("* * * * * IFS: directorios * * * * *");
		String rutaIfsdestino = lp.getAtributoRepositorio("rutaIfsdestino");
		//copiarArchivoToAS400( desde , hacia);		
		rutaIfsdestino += carpetaDestino;
		usf4.crearDirectorio(rutaIfsdestino);
		rutaIfsdestino += nameFile;

		result = usf4.copiarArchivoToAS400(rutaArchivo, rutaIfsdestino);
		System.out.println("* * * * * IFS: END copy [" + result + "]* * * * *");
		usf4.closeConnection();
		System.out.println("* * * * * IFS: END  copyByIFS * * * * *");
		return result;
	}//END setCopyByIFS

	/**
	 * metodo que realiza una copia de archivo desde la carpeta compartida
	 * hacia una ruta local mediante jt400
	 * recibe por parametros el nombre del archivo que debera existir en la ruta remota
	 * @throws AS400SecurityException 
	 * 
	 * **/
	public void getCopyByIFS(String nombreArchivo, String rutaTemporal) throws IOException, AS400SecurityException {
		LectorPropiedades lp = new LectorPropiedades();
		System.out.println("* * * * * IFS: LOGIN  * * * * *");
		String serverIfs = lp.getAtributoRepositorio("serverIfs");
		String userIfs = lp.getAtributoRepositorio("userIfs");
		String passIfs = lp.getAtributoRepositorio("passIfs");
		Util_SendFileToAS400 usf4 = new Util_SendFileToAS400(serverIfs, userIfs, passIfs);

		System.out.println("* * * * * IFS: directorios * * * * *");
		String rutaIfsOrigen = lp.getAtributoRepositorio("rutaIfsOrigen");

		rutaIfsOrigen += nombreArchivo;
		System.out.println("* * * * * IFS: rutaLocalTemporales [" + rutaTemporal + "]* * * * *");
		System.out.println("* * * * * IFS: rutaIfsOrigen [" + rutaIfsOrigen + "]* * * * *");

		FileOutputStream outputStream = new FileOutputStream(new File(rutaTemporal));
		outputStream.write(usf4.leerArchivoBin(rutaIfsOrigen));
		outputStream.flush();
		outputStream.close();
		System.out.println("* * * * * IFS: END  getCopyByIFS * * * * *");
	}//END getCopyByIFS
	
	public static void main(String[] args) throws IOException, AS400SecurityException {
		LectorPropiedades lp=new LectorPropiedades();
		String rutaTemporal=lp.getAtributoRepositorio("rutaLocalTemporales");
		String filename=lp.getAtributoRepositorio("nombreArchivoBalance");
		EscritorArchivoSimple eas = new EscritorArchivoSimple();
		//eas.writerBySamba(carpetaDestino, nombreArchivo, archivoOrigen);
		eas.getCopyByIFS(filename, "C:/SIMAT/TEST/"+filename);
		
	}

	public void writerXml(StringBuffer archivo, String ruta) throws Exception {
		BufferedWriter archivoEscritura = null;
		String archivoString = archivo.toString();
		archivoEscritura = new BufferedWriter(new FileWriter(ruta, false));
		archivoEscritura.write(archivoString);
		archivoEscritura.flush();
		archivoEscritura.close();
	}

	/**
	 * metodo que se encarga de escribir un archivo en una carpeta compartida
	 * mediante protocolo SAMBA,recibe por parametros el nombre del archivo a escribir(nombre.extension)
	 * y el nombre de carpeta que se creara 
	 * y el Objeto File que se copiara. 
	 * **/
	public void writerBySamba(String carpetaDestino, String nombreArchivo, File archivoOrigen) throws Exception {
		LectorPropiedades lp = new LectorPropiedades();
		SmbImpl smb = new SmbImpl();
		boolean key = false;
		System.out.println("* * * * * LOGIN SAMBA * * * * *");
		String host = lp.getAtributoRepositorio("hostFile");
		String user = lp.getAtributoRepositorio("userFile");
		String pass = lp.getAtributoRepositorio("passFile");
		smb.login(host, user, pass);

		String remotePath = lp.getAtributoRepositorio("remotePath");
		String finalPath = lp.getAtributoRepositorio("finalPath");
		finalPath += host + remotePath + carpetaDestino;

		System.out.println("* * * * * CHECK SAMBA * * * * *");

		if (smb.checkDirectory(finalPath)) {
			key = true;
		} else {
			System.out.println("* * * * * WRITE FOLDER SAMBA * * * * *");
			smb.createDir(finalPath);
			key = true;
		}

		if (key) {
			System.out.println("* * * * * WRITE FILE SAMBA * * * * *");
			//smb.copyFile(finalPath, nombreArchivo, archivoOrigen);
			smb.copyBuffer(finalPath, nombreArchivo, archivoOrigen);
		} else {
			System.out.println("* * * * * problemas al escribir archivo [samba] * * * * *");
		}
		System.out.println("* * * * * END CHECK SAMBA * * * * *");

	}//end writerBySamba

	/*
	public void readerBySamba(String rutaDestino, String rutaOrigen)throws Exception{
		LectorPropiedades lp=new LectorPropiedades();
		SmbImpl smb = new SmbImpl();		
		boolean key=false;
		System.out.println("* * * * * LOGIN SAMBA * * * * *");
		String host=lp.getAtributoRepositorio("hostFile");
		String user=lp.getAtributoRepositorio("userFile");
		String pass=lp.getAtributoRepositorio("passFile");
		smb.login(host, user, pass);
		
		
		System.out.println("* * * * * READ FILE SAMBA * * * * *");
		
		smb.readerBuffer(rutaDestino, rutaOrigen);
			
		System.out.println("* * * * * END CHECK SAMBA * * * * *");
			
	}//end writerBySamba
	*/

	public void writerByFTP(String carpetaDestino, String nombreArchivo, File archivoOrigen) throws Exception {
		LectorPropiedades lp = new LectorPropiedades();
		SmbImpl smb = new SmbImpl();
		boolean key = false;
		System.out.println("* * * * * LOGIN SAMBA * * * * *");
		String host = lp.getAtributoRepositorio("hostFile");
		String user = lp.getAtributoRepositorio("userFile");
		String pass = lp.getAtributoRepositorio("passFile");
		smb.login(host, user, pass);

		String remotePath = lp.getAtributoRepositorio("remotePath");
		String finalPath = lp.getAtributoRepositorio("finalPath");
		finalPath += host + remotePath + carpetaDestino;

		System.out.println("* * * * * CHECK SAMBA * * * * *");

		if (smb.checkDirectory(finalPath)) {
			key = true;
		} else {
			System.out.println("* * * * * WRITE FOLDER SAMBA * * * * *");
			smb.createDir(finalPath);
			key = true;
		}

		if (key) {
			System.out.println("* * * * * WRITE FILE SAMBA * * * * *");
			//smb.copyFile(finalPath, nombreArchivo, archivoOrigen);
			smb.copyBuffer(finalPath, nombreArchivo, archivoOrigen);
		} else {
			System.out.println("* * * * * problemas al escribir archivo [samba] * * * * *");
		}
		System.out.println("* * * * * END CHECK SAMBA * * * * *");

	}//end writerBySamba

}//Escritor Archivo Simple

