package cl.laaraucana.simat.utiles;

import java.io.FileInputStream;
import java.io.IOException;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;
import org.apache.commons.io.IOUtils;

public class Util_SendFileToAS400Samba {

	//static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

	//private AS400 system;
	//private int ccsid = 284;
	private String usuario;
	private String password;
	private String maquina;

	public Util_SendFileToAS400Samba() {
	}



	public Util_SendFileToAS400Samba(String server, String usuario, String password) {
		this.maquina = server;
		this.usuario = usuario;
		this.password = password;
	}

	public boolean copiarArchivoToAS400(String origen, String destino) {
		try {
			FileInputStream f1 = null;
			String path = "smb://" + maquina + "/" + destino;
			NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("", this.usuario, this.password);
			SmbFile smbFile = new SmbFile(path, auth);
			SmbFileOutputStream smbfos = new SmbFileOutputStream(smbFile);
			f1 = new FileInputStream(origen);
			byte[] archivoLocal = IOUtils.toByteArray(f1);
			smbfos.write(archivoLocal);
			f1.close();
			smbfos.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void crearDirectorio(String rutaDir) {
		try {
			//Validar si ya existe
			String path = "smb://" + maquina + "/" + rutaDir;
			NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("", this.usuario, this.password);
			SmbFile smbFile = new SmbFile(path, auth);
			
			
			if(!smbFile.exists()){
				smbFile.mkdir();
			}else{
				System.out.println("El directorio " + rutaDir + " ya existe");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("* * * * * util_IFS: CATCH: [" + ex + "] * * * * *");
		}
	}

	public byte[] leerArchivoBin(String pathfile) {
		SmbFileInputStream sfis = null;
		byte[] buffer = new byte[1024];
		try {
		//Validar si el archivo existe
			String path = "smb://" + maquina + "/" + pathfile;
			NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("", this.usuario, this.password);
			SmbFile smbFile = new SmbFile(path, auth);
			if(smbFile.exists()){
				sfis = new SmbFileInputStream(smbFile);
			}
			buffer = IOUtils.toByteArray(sfis);
			return buffer;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean crearArchivo(String pathfile, byte[] archivo) throws Exception {
		try {
			String path = "smb://" + maquina + "/" + pathfile;
			NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("", this.usuario, this.password);
			SmbFile smbFile = new SmbFile(path, auth);
			SmbFileOutputStream smbfos = new SmbFileOutputStream(smbFile);
			smbfos.write(archivo);
			smbfos.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
/*	public boolean crearArchivo(String pathfile, List texto) throws IOException {
		OutputStream out;
		try {
			if (system == null) {
				out = new FileOutputStream(pathfile);
			} else {
				//out = new IFSFileOutputStream(system, pathfile, getCCSID());

			}
			//PrintStream flujo = new PrintStream(out);
			//escribirOutput(texto, flujo);
			//flujo.close();
			//out.close();
			return true;
		} catch (Exception e) {
			//            logger.debug("CAI en crearArchivo()");
			e.printStackTrace();
			return false;
		}
	}*/
	
/*	public boolean crearArchivo(String pathfile, String texto) throws IOException {
		Vector vec = new Vector();
		vec.add(texto);
		return crearArchivo(pathfile, vec);
	}
*/
/*	public boolean escribirOutput(List texto, PrintStream out) throws IOException {
		CharConverter conv = null;
		try {
			//conv = new CharConverter(getCCSID());
			for (Iterator iter = texto.iterator(); iter.hasNext();) {
				String linea = (String) iter.next();
				if (system == null) {
					out.println(linea);
				} else {
					out.write(conv.stringToByteArray(linea));
					out.write(conv.stringToByteArray("\n"));

				}
			}
			return true;
		} catch (Exception e) {
			//           logger.debug("CAI en escribirOutput()");
			e.printStackTrace();
			return false;
		}
	}*/
	
	
	/*	public boolean escribirOutput(byte[] archivo, PrintStream out) throws IOException {
	try {
		out.write(archivo);
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
}*/	

/*	public void addLinea(String pathfile, String newline) throws IOException {
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
	}*/

/*	public boolean existFile(String pathfile) {
		File file;
		if (system == null) {
			file = new File(pathfile);
		} else {
			file = new IFSJavaFile(system, pathfile);
		}
		return file.exists();
	}*/

/*	public void leerArchivo(String pathfile, Vector retorno) {
		BufferedReader f1;
		IFSFile file;
		String buf = "";
		try {
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
			e.printStackTrace();
		}
	}*/

	public void closeConnection() {
		//ahora nada
	}
/*	public byte[] readFile(String path) throws IOException, AS400SecurityException {
		byte[] data;
		IFSFile file = null;
		if (system == null) {
			IFSFileInputStream fis = new IFSFileInputStream(file, IFSFileInputStream.SHARE_READERS);
			data = new byte[fis.available()];
			fis.read(data);
			fis.close();
		} else {
			file = new IFSFile(system, path);
			// creates a file input stream
			IFSFileInputStream fis = new IFSFileInputStream(file, IFSFileInputStream.SHARE_READERS);
			data = new byte[fis.available()];
			fis.read(data);
			fis.close();
		}

		return data;
	}*/

/*	public boolean borrarArchivo(String folderOfile) {
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
			e.printStackTrace();
			return false;
		}
	}*/

/*	public File[] getListaDeArchivos(String folderOfile) {
		File file;
		File[] listFiles = null;
		try {
			if (system == null) {
				file = new File(folderOfile);
			} else {
				file = new IFSJavaFile(system, folderOfile);
			}

			if (file.isDirectory()) {
				listFiles = file.listFiles();
			} else {
				listFiles = new File[1];
				listFiles[0] = file.getAbsoluteFile();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listFiles;
	}*/

/*	public static String replaceCaracter(String textoIn, char oldchar, char newchar) {
		int pos = textoIn.indexOf('\\');
		while (pos >= 0) {
			textoIn = textoIn.substring(0, pos) + "/" + textoIn.substring(pos + 1);
			pos = textoIn.indexOf('\\');
		}
		return textoIn;
	}*/

/*	public void setCCSID(int ccsidIn) {
		try {
			this.ccsid = ccsidIn;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

/*	public int getCCSID() {
		return ccsid;
	}

	*/

/*
	public AS400 getSystem() {
		return system;
	}

	public void setSystem(AS400 systemIn) {
		this.system = systemIn;
	}*/
	
	/*	
	public Util_SendFileToAS400(AS400 systemIn) {
		this.system = systemIn;
	}*/
	
	public static void main(String[] args) throws IOException {
		String server = "146.83.1.3";
		String usuario = "usrwassebu".toUpperCase();
		String password ="usr60sebus".toUpperCase();
		String path= "smb://146.83.1.3/SIMAT/201512/";
		Util_SendFileToAS400Samba send = new Util_SendFileToAS400Samba(server, usuario, password);
		//send.copiarArchivoToAS400("C:\\SIMAT\\test\\PreBalance.xls", "simat/201512");
		
		send.crearDirectorio("SIMAT/201512/");
	}
}
