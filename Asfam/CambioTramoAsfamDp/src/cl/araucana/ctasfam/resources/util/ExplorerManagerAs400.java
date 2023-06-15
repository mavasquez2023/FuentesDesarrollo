package cl.araucana.ctasfam.resources.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Exception;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.CharConverter;
import com.ibm.as400.access.ExtendedIOException;
import com.ibm.as400.access.IFSFile;
import com.ibm.as400.access.IFSFileInputStream;
import com.ibm.as400.access.IFSFileOutputStream;
import com.ibm.as400.access.IFSFileReader;
import com.ibm.as400.access.IFSJavaFile;

public class ExplorerManagerAs400 {
	
	private static final Log log = LogFactory.getLog(ExplorerManagerAs400.class);

	private AS400 system = null;

	private int ccsid = 284;

	public ExplorerManagerAs400() {
		super();
	}

	public ExplorerManagerAs400(String server, String usuario, String password) {
		if (server != null && !server.equals("")) {
			this.system = new AS400(server, usuario, password);
			//System.out.println("*AS400 Conectado...*");
		}
	}

	public ExplorerManagerAs400(AS400 system) {
		this.system = system;
	}
	
	public void disconect(){
		this.system.disconnectAllServices();
	}
	
	public void estatusAS400(){
		if(!this.system.isConnected()){
			System.out.println("AS400 Desconectado...");
		}
	}

	public boolean crearArchivo(String pathfile, List texto) throws IOException {
		OutputStream out;
		
		System.out.println(pathfile);
		for(int i=0;i<texto.size();i++)
			{System.out.println(texto.get(i).toString());
			
			}
		try {
			if (system == null) {
				out = new FileOutputStream(pathfile);
				// BufferedWriter out = new BufferedWriter(new
				// FileWriter(pathfile));
			} else {
				IFSFile file = new IFSFile(system, pathfile);
				out = new IFSFileOutputStream(system, pathfile, getCCSID());
			}
			PrintStream flujo = new PrintStream(out);
			escribirOutput(texto, flujo);
			flujo.close();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error: al crear un archivo, " + e.getLocalizedMessage(), e);
			// throw new IOException();
			return false;
		}
	}

	public boolean crearArchivo(String pathfile, String texto)
			throws IOException {
		Vector vec = new Vector();
		vec.add(texto);
		System.out.println(texto);
		return crearArchivo(pathfile, vec);
	}

	public boolean escribirOutput(List texto, PrintStream out)
			throws IOException {
		CharConverter conv = null;
		// byte[] buff;
		try {
			conv = new CharConverter(getCCSID());
			for (Iterator iter = texto.iterator(); iter.hasNext();) {
				String linea = (String) iter.next();
				if (system == null) {
					out.println(linea);
				} else {
					// out.println(conv.stringToByteArray(linea));
					out.write(conv.stringToByteArray(linea));
					out.write(conv.stringToByteArray("\n"));
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error: al escribir un archivo, " + e.getLocalizedMessage(), e);
			// throw new IOException();
			return false;
		}
	}

	public void addLinea(String pathfile, String newline) throws IOException {
		Vector vec = new Vector();
		leerArchivo(pathfile, vec);
		if (vec.size() > 0) {
			int pos = vec.indexOf("</table>");
			if (pos > -1) {
				vec.insertElementAt(newline, pos);
			} else {
				vec.addElement(newline);
			}
		} else {
			if (newline.indexOf("<tr>") >= 0) {
				vec.addElement("<table>");
				vec.addElement(newline);
				vec.addElement("</table>");
			} else {
				vec.addElement(newline);
			}
		}
		crearArchivo(pathfile, vec);
	}

	public boolean existFile(String pathfile) {
		File file;
		if (system == null) {
			file = new File(pathfile);
		} else {
			file = new IFSJavaFile(system, pathfile);
		}
		return file.exists();
	}

	public void leerArchivo(String pathfile, Vector retorno) {
		BufferedReader f1;
		IFSFile file;
		String buf = "";
		try {
			// leyendo archivo, usando BufferedReader
			if (system == null) {
				f1 = new BufferedReader(new FileReader(pathfile));
			} else {
				file = new IFSFile(system, pathfile);
				f1 = new BufferedReader(new IFSFileReader(file));
			}
			while ((buf = f1.readLine()) != null) {
				retorno.addElement(buf);
			}
			f1.close();
		} catch (Exception e) {
			log.error("Error: al leer un archivo, " + e.getLocalizedMessage(), e);
			// e.printStackTrace();
			// retorno.removeAllElements();
		}
	}
	
	public void leerArchivograva(String pathfile, Vector retorno,String rutEmpresa, String formato) {
		BufferedReader f1;
		IFSFile file;
		String buf = "";
		try {
			// leyendo archivo, usando BufferedReader
			if (system == null) {
				f1 = new BufferedReader(new FileReader(pathfile));
			} else {
				file = new IFSFile(system, pathfile);
				f1 = new BufferedReader(new IFSFileReader(file));
			}
			while ((buf = f1.readLine()) != null) {
				retorno.addElement(buf);
			}
			f1.close();
			
			FileWriter fw =new FileWriter("c:/windows/temp/" + rutEmpresa + "." + formato);
			for(int i=0;i<retorno.size();i++)
			fw.write(retorno.get(i).toString() + "\n");
		} catch (Exception e) {
			log.error("Error: al leer un archivo, " + e.getLocalizedMessage(), e);
			// e.printStackTrace();
			// retorno.removeAllElements();
		}
	}

	
	public void leerArchivoBin2(String pathfile, String destino) {
		IFSFileInputStream f1=null;
		IFSJavaFile file=null;
		byte[] buffer     = new byte[1024 * 64];
		try {
		file = new IFSJavaFile(system, pathfile);
		f1 = new IFSFileInputStream(file);
//		se determina largo del archivo
		int largo = f1.available();


		     IFSFileOutputStream target = null;
		target = new IFSFileOutputStream(system, destino, IFSFileOutputStream.SHARE_NONE, false);
		            int bytesRead = f1.read(buffer);
		            while (bytesRead > 0)
		            {
		               target.write(buffer, 0, bytesRead);
		               bytesRead = f1.read(buffer);
		            }
		        
		f1.close();
		target.close();






		//return buffer;
		} catch (Exception e) {
		e.printStackTrace();
		 
		}
		}
	
	public void leerArchivoBintemp(String pathfile, String destino) {
		IFSFileInputStream f1=null;
		IFSJavaFile file=null;
		
		File path=new File(pathfile);
		
		byte[] buffer= new byte[1024 * 64];
		try {
		 
		System.out.println("pathfile " + pathfile);
		file = new IFSJavaFile(system, pathfile);
		f1 = new IFSFileInputStream(file);
//		se determina largo del archivo
		int largo = f1.available();


		     FileOutputStream target = null;
		target = new FileOutputStream(destino);
		            int bytesRead = f1.read(buffer);
		            while (bytesRead > 0)
		            {
		               target.write(buffer, 0, bytesRead);
		               bytesRead = f1.read(buffer);
		            }
		        
		f1.close();
		target.close();


	 
		 



		//return buffer;
		} catch (Exception e) {
		e.printStackTrace();
		 
		}
		}
	
	 

	public void leerArchivoBinas400(String pathfile, String destino) {
		FileInputStream f1=null;
		File file=null;
		
		
		
		byte[] buffer= new byte[1024 * 64];
		try {
		 
		System.out.println("pathfile " + pathfile);
		file = new File(pathfile);
		f1 = new FileInputStream(file);
//		se determina largo del archivo
		int largo = f1.available();
		String path=destino.substring(0, destino.lastIndexOf("/"));
		System.out.println("carpeta " + path);
		IFSJavaFile file2 = new IFSJavaFile(system, path);
		if(!file2.exists()){
			file2.mkdir();
		}
         
		     IFSFileOutputStream target = null;
		     try{
		     target = new IFSFileOutputStream(system, destino, IFSFileOutputStream.SHARE_NONE, false);
		    
		     int bytesRead = f1.read(buffer);
		            while (bytesRead > 0)
		            {
		               target.write(buffer, 0, bytesRead);
		               bytesRead = f1.read(buffer);
		            }
		        
		f1.close();
		target.close();

		     }
		     catch(ExtendedIOException ignored)
		     {System.out.println("Archivo ya existe");
		     }
	 
		 



		//return buffer;
		} catch (Exception e) {
		e.printStackTrace();
		 
		}
		}
	
	 











	 



	public byte[] leerArchivoBin(String pathfile) {
		FileInputStream f1 = null;
		IFSFileInputStream ifsf1 = null;
		IFSJavaFile file = null;
		byte[] buffer = new byte[1024];
		try {
			// leyendo archivo, usando BufferedReader
			if (system == null) {
				f1 = new FileInputStream(pathfile);
				// se determina largo del archivo
				int largo = f1.available();
				// se crea una matriz de byte del largo del archivo
				buffer = new byte[largo];
				while (f1.read(buffer) > 0) {
				}
				f1.close();
			} else {
				file = new IFSJavaFile(system, pathfile);
				ifsf1 = new IFSFileInputStream(file);
				// se determina largo del archivo
				int largo = ifsf1.available();
				// se crea una matriz de byte del largo del archivo
				buffer = new byte[largo];
				ifsf1.read(buffer);
				ifsf1.close();
			}
			return buffer;
		} catch (Exception e) {
			log.error("Error: al leer un archivo binario, " + e.getLocalizedMessage(), e);
			return null;
		}
	}

	public boolean borrarArchivo(String folderOfile) {
		File file;
		File[] list;
		try {
			if (system == null) {
				file = new File(folderOfile);
			} else {
				file = new IFSJavaFile(system, folderOfile);
			}
			if (file.isDirectory()) {
				list = file.listFiles();
				for (int i = 0; i < list.length; i++) {
					if (list[i].isFile()) {
						list[i].delete();
					}
				}
			} else {
				file.delete();
			}
			return true;
		} catch (Exception e) {
			log.error("Error: al borrar un archivo, " + e.getLocalizedMessage(), e);
			return false;
		}
	}

	public File[] getListaDeArchivos(String folderOfile) {
		File file;
		File[] listFiles = null;
		try {
			if (system == null) {
				file = new File(folderOfile);
			} else {
				file = new IFSJavaFile(system, folderOfile);
			}
			// Se extrae lista de archivos dependiendo si 'folderOfile' es una
			// carpeta
			if (file.isDirectory()) {
				listFiles = file.listFiles();
				// Se arma el nombre del archivo zip en caso que pathfileout no
				// venga
			} else {
				listFiles = new File[1];
				listFiles[0] = file.getAbsoluteFile();
				// Se arma el nombre del archivo zip en caso que pathfileout no
				// venga
			}
		} catch (Exception e) {
			log.error("Error: al obtener lista de archivos, " + e.getLocalizedMessage(), e);
		}
		return listFiles;
	}

	public static String replaceCaracter(String texto, char oldchar,
			char newchar) {
		int pos = texto.indexOf('\\');
		while (pos >= 0) {
			texto = texto.substring(0, pos) + "/" + texto.substring(pos + 1);
			pos = texto.indexOf('\\');
		}
		return texto;
	}

	public void setCCSID(int ccsid) {
		try {
			this.ccsid = ccsid;
		} catch (Exception e) {
			log.error("Error: al setCCSID un archivo, " + e.getLocalizedMessage(), e);
		}
	}

	public int getCCSID() {
		return ccsid;
	}
}