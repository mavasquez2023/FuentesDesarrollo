package cl.araucana.wslme.integration.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

import cl.araucana.wslme.common.exception.WSLMEException;
import cl.araucana.wslme.common.util.ConfigUtil;


public class FileAbstractDao {

	private File file;
	
	private Logger log = Logger.getLogger(FileAbstractDao.class);
	
	public void createFile(String path, String nameFile, boolean replaceIfExist) throws WSLMEException{
		File dirTemp = new File(path);
		
		if(!dirTemp.exists()){
			dirTemp.mkdir();
			log.debug("Se creo el directorio [" + dirTemp.getAbsolutePath() + "] correctamente");
		}else{
			if(!dirTemp.isDirectory()){
				log.error("Codigo 0013: El directorio temporal [" + path + "] no existe y no es posible crearlo");
				throw new WSLMEException("0013","Error, El directorio temporal [" + path + "] no existe y no es posible crearlo");
			}
		}
		
		try {
			file = new File(dirTemp.getAbsolutePath() + "\\" + nameFile);
			
			if(file.exists()){
				if(replaceIfExist){
					if(file.delete()){
						log.debug("Se elimino el archivo existente con nombre [" + nameFile + "] ");
						if(file.createNewFile()){
							log.debug("Se volvio a crear el archivo [" + file.getAbsolutePath() + "] correctamente");
						}else{
							log.error("Codigo 0013: No fue posible volver a crear el archivo por una razon desconosida");
		    				throw new WSLMEException("0013","Error, No fue posible volver a crear el archivo por una razon desconosida");
						}
					}else{
						log.error("Codigo 0013: No fue posible eliminar el archivo ya existente con nombre [" + nameFile + "] ");
						throw new WSLMEException("0013","Error, No fue posible eliminar el archivo ya existente con nombre [" + nameFile + "] ");
					}
				}else{
					log.error("Codigo 0013: El archivo ya existe y  no es posible volver a crearlo");
					throw new WSLMEException("0013","Error, El archivo ya existe y  no es posible volver a crearlo");
				}
			}else{
				if(file.createNewFile()){
					log.debug("Se creo el archivo [" + file.getAbsolutePath() + "] correctamente");
				}else{
					log.error("Codigo 0013: No fue posible crear el archivo por una razon desconosida");
    				throw new WSLMEException("0013","Error, No fue posible crear el archivo por una razon desconosida");
				}
			}
        }catch(Exception ex){
        	log.error("Codigo 0014: Se produjo un error desconosido");
			throw new WSLMEException("0014","Error, Se produjo un error desconosido");
        }finally{}
    }
	
	public void openFile(String path) throws WSLMEException{
    	if(file == null){
    		try {
    			file = new File(path);
    			
    			if(!file.exists() || !file.canRead()){
    				log.error("Codigo 0013: No existe o no se puede leer el archivo de propiedades externo [" + path + "]");
    				throw new WSLMEException("0013","Error, No existe o no se puede leer el archivo de propiedades externo [" + path + "]");
    			}
	        }catch(Exception ex){
	        	log.error("Codigo 0014: No es posible acceder al archivo de propiedades externo [" + path + "]");
				throw new WSLMEException("0014","Error, No es posible acceder al archivo de propiedades externo [" + path + "]");
	        }finally{}
    	}
    }
	
	public File getFile(){
		return file;
	}
	
	public FileReader getFileReader() throws WSLMEException{
		try {
			return new FileReader (file);
		} catch (FileNotFoundException e) {
			log.error("Codigo 0015: No es posible leer al archivo de propiedades externo [" + ConfigUtil.PATH_OPERADORES_REGISTRADOS + "]");
			throw new WSLMEException("0015","Error, No es posible leer al archivo de propiedades externo [" + ConfigUtil.PATH_OPERADORES_REGISTRADOS + "]");
		}
	}
	
	public PrintWriter getPrintWriter() throws WSLMEException{
		try {
			FileWriter writer = new FileWriter(file);
			
			return new PrintWriter(writer);
		} catch (FileNotFoundException e) {
			log.error("Codigo 0015: No es posible escribir en el archivo [" + file.getAbsolutePath() + "]");
			throw new WSLMEException("0015","Error, No es posible escribir en el archivo [" + file.getAbsolutePath() + "]");
		} catch (IOException e) {
			log.error("Codigo 0016: No es posible escribir en el archivo [" + file.getAbsolutePath() + "]");
			throw new WSLMEException("0016","Error, No es posible escribir en el archivo [" + file.getAbsolutePath() + "]");
		}
	}
}
