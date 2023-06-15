package cl.jfactory.planillas.service.util;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import cl.liv.archivos.comun.ArchivosUtiles;
import cl.liv.archivos.comun.txt.ManejoArchivoTXT;
import cl.liv.cargas.masivas.util.UtilLogCargas;

public class ResultadosUtil {

	private static HashMap dataResultados = new HashMap();
	
	
	public static void limpiar(String key, String texto){
		if(((ArrayList)dataResultados.get(key)) != null)
		((ArrayList)dataResultados.get(key)).clear();
	}
	
	public static void addRegistro(String key, String texto){
		if(dataResultados.get(key)!= null){
			//
		}
		else{
			dataResultados.put(key, new ArrayList());
		}
		
		((ArrayList)dataResultados.get(key)).add(texto);
	}
	
	public static synchronized void registrarResultados(String key, String ruta){
		
		ArchivosUtiles.crearDirectorioCompletoSiNoExiste(ruta);
		ArrayList data = ((ArrayList)dataResultados.get(key));
		
		if(data != null && data.size()>0){
			UtilLogCargas.debug("escribiendo log resultados ["+ruta+"] "+ data.size() +" registros");
			PrintWriter pw = ManejoArchivoTXT.getOpenedFileToWrite(ruta);
			
			for(int i=0; i<data.size(); i++){
				ManejoArchivoTXT.putLineFromFileOpened(pw, data.get(i).toString());
			}

			ManejoArchivoTXT.closeFileToWrite(pw);
			((ArrayList)dataResultados.get(key)).clear();
			
		}
		else{
			PrintWriter pw = ManejoArchivoTXT.getOpenedFileToWrite(ruta);
			
			ManejoArchivoTXT.putLineFromFileOpened(pw, "");
			

			ManejoArchivoTXT.closeFileToWrite(pw);
		}
		UtilLogCargas.debug("saliendo escritura logs");
	}
	
}
