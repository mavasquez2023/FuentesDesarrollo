/*
 * Creado el 17-07-2006
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.recursos;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
import java.io.*;
import java.util.*;

import com.ibm.as400.access.*;
import com.lowagie.text.pdf.PdfReader;

public class Archivo {
private AS400 system=null;
private int ccsid=284;

public Archivo(){
}
public Archivo(AS400 system){
   	this.system = system;
}
public Archivo(String server, String usuario, String password){
	if (server!=null && !server.equals("")){
		this.system = new AS400(server, usuario, password);
	}
}

public void copiarArchivoToAS400(String origen, String destino){

	try {
		if(this.system != null){
			AS400 system= this.system;
			this.system= null;
			byte[] archivo= leerArchivoBin(origen);
			this.system= system;
			crearArchivo(destino, archivo);
		}else{
			throw new Exception("No se han definido credenciales para AS400");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public  boolean crearArchivo(String pathfile, List texto) throws IOException{
	OutputStream out;
	try{
		if (system==null){
			out = new FileOutputStream(pathfile);
			//BufferedWriter out = new BufferedWriter(new FileWriter(pathfile));
		}else{
			IFSFile file = new IFSFile(system, pathfile);
			out = new IFSFileOutputStream(system, pathfile, getCCSID());
		}
		PrintStream flujo= new PrintStream(out);
		escribirOutput(texto, flujo);
		flujo.close();
		out.close();
		return true;
	  } catch(Exception e) {
		System.out.println("CAI en crearArchivo()");
		e.printStackTrace();
		//throw new IOException();
		return false;
	}
}

public  boolean crearArchivo(String pathfile, byte[] archivo) throws IOException{
	OutputStream out;
	try{
		if (system==null){
			out = new FileOutputStream(pathfile);
			//BufferedWriter out = new BufferedWriter(new FileWriter(pathfile));
		}else{
			IFSFile file = new IFSFile(system, pathfile);
			out = new IFSFileOutputStream(system, pathfile, getCCSID());
		}
		PrintStream flujo= new PrintStream(out);
		escribirOutput(archivo, flujo);
		flujo.close();
		out.close();
		return true;
	  } catch(Exception e) {
		System.out.println("CAI en crearArchivo()");
		e.printStackTrace();
		//throw new IOException();
		return false;
	}
}

public boolean crearArchivo(String pathfile, String texto) throws IOException{
	Vector vec= new Vector();
	vec.add(texto);
	return crearArchivo(pathfile, vec);
}

public  boolean escribirOutput(List texto, PrintStream out) throws IOException{
	CharConverter conv=null;
	//byte[] buff;
	try{
		conv = new CharConverter(getCCSID());
		for (Iterator iter = texto.iterator(); iter.hasNext();) {
			String linea = (String) iter.next();
			if (system==null){
				out.println(linea);
			}else{
				//out.println(conv.stringToByteArray(linea));
				out.write(conv.stringToByteArray(linea));
				out.write(conv.stringToByteArray("\n"));
			}
		}
		return true;
	  } catch(Exception e) {
		System.out.println("CAI en escribirOutput()");
		e.printStackTrace();
		//throw new IOException();
		return false;
	}
}

public  boolean escribirOutput(byte[] archivo, PrintStream out) throws IOException{
	//byte[] buff;
	try{
		out.write(archivo);
		return true;
	  } catch(Exception e) {
		System.out.println("CAI en escribirOutput()");
		e.printStackTrace();
		//throw new IOException();
		return false;
	}
}

public void addLinea(String pathfile, String newline) throws IOException {
	Vector vec= new Vector();
	leerArchivo(pathfile, vec);
	if (vec.size()>0){
		int pos= vec.indexOf("</table>");
		if (pos > -1){
			vec.insertElementAt(newline, pos);
		}else{
			vec.addElement(newline);
		}
	}else{
		if(newline.indexOf("<tr>")>=0){
			vec.addElement("<table>");
			vec.addElement(newline);
			vec.addElement("</table>");
		}else{
			vec.addElement(newline);
		}
	}
	crearArchivo(pathfile, vec);
}

public boolean existFile(String pathfile){
	File file;
	if (system==null){
		file = new File(pathfile);
	}else{
		file = new IFSJavaFile(system, pathfile);
	}
	return file.exists();
}

public void leerArchivo(String pathfile, Vector retorno) {
	BufferedReader f1;
	IFSFile file;
	String buf = "";
	try {
		//leyendo archivo, usando BufferedReader
		if (system==null){
			f1 = new BufferedReader(new FileReader(pathfile));
		}else{
			file = new IFSFile(system, pathfile);
			f1 = new BufferedReader(new IFSFileReader(file));
		}
		while ((buf = f1.readLine()) != null) {
				retorno.addElement(buf);
		}
		f1.close();
	} catch (Exception e) {
		e.printStackTrace();
		//retorno.removeAllElements();
	}
}

public String leerArchivo(String pathfile) {
	BufferedReader f1;
	IFSFile file;
	String buf = "";
	String texto="";
	try {
		//leyendo archivo, usando BufferedReader
		if (system==null){
			f1 = new BufferedReader(new FileReader(pathfile));
		}else{
			file = new IFSFile(system, pathfile);
			f1 = new BufferedReader(new IFSFileReader(file));
		}
		while ((buf = f1.readLine()) != null) {
				texto+= buf +"\n";
		}
		f1.close();
	} catch (Exception e) {
		e.printStackTrace();
		//retorno.removeAllElements();
	}
	return texto;
}

public byte[] leerArchivoBin(String pathfile) {
	FileInputStream f1=null;
	IFSFileInputStream ifsf1=null;
	IFSJavaFile file=null;
	byte[] buffer = new byte[1024];
	try {
		//leyendo archivo, usando BufferedReader
		if (system==null){
			f1 = new FileInputStream(pathfile);
			//se determina largo del archivo
			int largo = f1.available();
			//se crea una matriz de byte del largo del archivo
			buffer = new byte[largo];
			while (f1.read(buffer)>0) {
			}
			f1.close();
		}else{
			file = new IFSJavaFile(system, pathfile);
			ifsf1 = new IFSFileInputStream(file);
			//se determina largo del archivo
			int largo = ifsf1.available();
			//se crea una matriz de byte del largo del archivo
			buffer = new byte[largo];
			ifsf1.read(buffer);
			ifsf1.close();
		}
		
		return buffer;
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
}


public boolean borrarArchivo(String folderOfile ){
	File file;
	File[] list;
	try{
		if (system==null){
			file = new File(folderOfile);
		}else{
			file = new IFSJavaFile(system, folderOfile);
		}
		if (file.isDirectory()){
			list= file.listFiles();
			for(int i=0; i< list.length ; i++){
				if (list[i].isFile()){
					list[i].delete();
				}
			}
		}else{
			file.delete();
		}
		return true;
	}catch(Exception e){
		e.printStackTrace();
		return false;
	}
}

public File[] getListaDeArchivos(String folderOfile){
	File file;
	File[] listFiles=null;
	try{
		if (system==null){
			file = new File(folderOfile);
		}else{
			file = new IFSJavaFile(system, folderOfile);
		}
		
		//Se extrae lista de archivos dependiendo si 'folderOfile' es una carpeta 
		if (file.isDirectory()){
			listFiles= file.listFiles();
			//Se arma el nombre del archivo zip en caso que pathfileout no venga
		}else{
			listFiles= new File[1];
			listFiles[0] = file.getAbsoluteFile();
			//Se arma el nombre del archivo zip en caso que pathfileout no venga
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return listFiles;
}

public static String replaceCaracter(String texto, char oldchar, char newchar){
	int pos= texto.indexOf('\\');
	while (pos >= 0) {
		texto=texto.substring(0, pos) + "/" + texto.substring(pos +1);
		pos= texto.indexOf('\\');
	}
	return texto;
}

public void setCCSID(int ccsid){
	try{
		this.ccsid= ccsid;
	}catch(Exception e){
   		e.printStackTrace();
   	}
}

public int getCCSID(){
	return ccsid;
}

public void closeConnection(){
	this.system.disconnectAllServices();
}
static int pro;
public static void main(String[] args) {
	/*int corte= 50;
	String texto="sjadhasd hkjashdakjsdhas kjdhasjkdhasjkdhasjkdhas jkdhaskj dhasjkdhasjkdhkjasdhjkashdjkashdjkashdjkashdkjasaskasjk kjaskhdjkashdasjash   sjakdhas jkdhsa sjak sjahd ksjahdaskj h ksa hsa kshadkashda skjdhka";
	int largo= texto.length();
	int iter= Math.round(largo/corte);
	for(int j=0; j<=iter; j++){
			int tope= corte*(j+1);
			if(tope>largo){
				tope=largo;	
			}
			System.out.println(">>>Lista empresas:" +  texto.substring(j*corte, tope));
	}*/
	String proceso="S";
	
	if ( proceso.equals("R") )	pro	= 0;
	 if ( proceso.equals("G") )	pro	= 1;
	 if ( proceso.equals("D") )	pro	= 2;
	System.out.println(pro);
}

/**
 * @return el system
 */
public AS400 getSystem() {
	return system;
}

/**
 * @param system el system a establecer
 */
public void setSystem(AS400 system) {
	if(this.system!= null){
		this.system.disconnectAllServices();
	}
} 


}

