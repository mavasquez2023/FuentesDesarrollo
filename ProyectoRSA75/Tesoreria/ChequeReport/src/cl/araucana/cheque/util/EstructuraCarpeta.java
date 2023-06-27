/**
 * 
 */
package cl.araucana.cheque.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.IFSFile;
import com.ibm.as400.access.IFSFileOutputStream;

/**
 * @author usist199
 *
 */
public class EstructuraCarpeta {
	private AS400 system;
	private static Logger log = Logger.getLogger(EstructuraCarpeta.class);
	private String rutasalida;
	
	public EstructuraCarpeta(String server, String usuario, String password){
		if (server!=null && !server.equals("")){
			log.debug("Seteando system as400, server:" + server +", usuario:" + usuario);
			system = new AS400(server, usuario, password);
		}
	}
	public boolean existFile(String pathfile){
		try {
			if(system==null){
				log.debug("verificando ruta:" + pathfile);
				File file = new File(pathfile);
				return file.exists();
			}else{
				log.debug("verificando ruta ifs:" + pathfile);
				IFSFile file = new IFSFile(system, pathfile);
				return file.exists();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean crearCarpeta(String pathcarpeta){
		try {
			if(system==null){
				log.debug("creando carpeta:" + pathcarpeta);
				File file = new File(pathcarpeta);
				return file.mkdirs();
			}else{
				log.debug("creando carpeta ifs:" + pathcarpeta);
				IFSFile file = new IFSFile(system, pathcarpeta);
				return file.mkdirs();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.error("Mensaje:" + e.getMessage());
			return false;
		}
	}
	public OutputStream getOutput(String pathfile) throws IOException{
		OutputStream out;
		try{
			if (system==null){
				log.debug("creando archivo:" + pathfile);
				out = new FileOutputStream(pathfile);
				//BufferedWriter out = new BufferedWriter(new FileWriter(pathfile));
			}else{
				log.debug("creando archivo ifs:" + pathfile);
				IFSFile file = new IFSFile(system, pathfile);
				out = new IFSFileOutputStream(system, pathfile, 284);
			}
			return out;
		  } catch(Exception e) {
			log.error("Mensaje:" + e.getMessage());
			e.printStackTrace();
			//throw new IOException();
			return null;
		}
	}
	

	public void closeConnection(){
		if(system!=null){
			log.debug("Cerrando conexión IFS");
			system.disconnectAllServices();
		}
	}
	/**
	 * @return el system
	 */
	public AS400 getSystem() {
		return system;
	}
	/**
	 * @return el rutasalida
	 */
	public String getRutasalida() {
		return rutasalida;
	}
	/**
	 * @param rutasalida el rutasalida a establecer
	 */
	public void setRutasalida(String rutasalida) {
		this.rutasalida = rutasalida;
	}

}
