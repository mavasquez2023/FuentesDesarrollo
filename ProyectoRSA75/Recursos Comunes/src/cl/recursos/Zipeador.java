/**
 * 
 */
package cl.recursos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.CharConverter;
import com.ibm.as400.access.IFSFileInputStream;
import com.ibm.as400.access.IFSFileOutputStream;
import com.ibm.as400.access.IFSJavaFile;

/**
 * @author Usist24
 *
 */
public class Zipeador {
	private AS400 system=null;
	private int ccsid=284;
	/**
	 * @param system 
	 * 
	 */
	public Zipeador(AS400 system) {
		this.system= system;
	}

	public boolean zipearArchivos(String folderOfile, String pathfileout){
		File[] listFiles;
		File file;
		String fileout="";
		try{
			if (system==null){
				file = new File(folderOfile);
			}else{
				file = new IFSJavaFile(system, folderOfile);
			}
			
			//Se extrae lista de archivos dependiendo si 'folderOfile' es una carpeta 
			fileout= pathfileout;
			if (file.isDirectory()){
				listFiles= file.listFiles();
				//Se arma el nombre del archivo zip en caso que pathfileout no venga
				if (pathfileout==null || pathfileout.equals("")){
					fileout= folderOfile.substring(0, folderOfile.lastIndexOf('/')) + folderOfile.substring(folderOfile.lastIndexOf('/')) + ".zip";
				}
			}else{
				listFiles= new File[1];
	           	listFiles[0] = file.getAbsoluteFile();
	           	//Se arma el nombre del archivo zip en caso que pathfileout no venga
	           	if (pathfileout==null || pathfileout.equals("")){
	           		fileout= folderOfile + ".zip";
	           	}
			}
			zipearArchivos(listFiles, fileout);
	        return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean zipearArchivos(File[] listFiles, String pathfileout){
		ZipOutputStream zos;
		//DataOutputStream out;
		File file;
		String pathfile;
		try{
			if (!pathfileout.substring(pathfileout.length()-4).equals(".zip")){
				pathfileout.replaceAll(".", "_");
				pathfileout= pathfileout + ".zip";
			}
			//Se crea archivo zip
			if (system==null){
				zos =  new ZipOutputStream(new FileOutputStream(pathfileout));
			}else{
				file = new IFSJavaFile(system, pathfileout);
				zos =  new ZipOutputStream((OutputStream)new IFSFileOutputStream((IFSJavaFile)file));
			}
			//out = new DataOutputStream(zos);
			int cantidad_files=listFiles.length;
			for (int i = 0; i < cantidad_files; i++) {
				if (listFiles[i].isFile()){
					pathfile= replaceCaracter(listFiles[i].getAbsolutePath(), '\\', '/');
					zos.putNextEntry(new ZipEntry(listFiles[i].getName()));
					zos.write(leerArchivoBin(pathfile));
				}
			}
			zos.flush();
	        zos.close();
	        return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean zipearString(Vector texto, String nameentry, String pathfileout){
		ZipOutputStream zos;
		CharConverter conv=null;
		//File file;
		try{
			if (!pathfileout.substring(pathfileout.length()-4).equals(".zip")){
				pathfileout.replaceAll(".", "_");
				pathfileout= pathfileout + ".zip";
			}
			//Se crea archivo zip
			if (system==null){
				zos =  new ZipOutputStream(new FileOutputStream(pathfileout));
			}else{
				//file = new IFSJavaFile(systemout, pat	hfileout);
				zos =  new ZipOutputStream((OutputStream)new IFSFileOutputStream(system, pathfileout, getCCSID()));
			}
			conv = new CharConverter(getCCSID());
			//out = new DataOutputStream(zos);
			zos.putNextEntry(new ZipEntry(nameentry));
			int numlineas= texto.size();
			for (int i=0; i< numlineas; i++){
				zos.write(texto.elementAt(i).toString().getBytes());
				if (i<numlineas-1){
					zos.write(13);
				}			
			}
			zos.flush();
	        zos.close();
			return true;
		  } catch(Exception e) {
			System.out.println("CAI en zipearString()");
			e.printStackTrace();
			//throw new IOException();
			return false;
		}
	}

	public boolean zipearString(String texto, String nameentry, String pathfileout){
		Vector vec= new Vector();
		vec.addElement(texto);
		return zipearString(vec, nameentry, pathfileout);
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
}
