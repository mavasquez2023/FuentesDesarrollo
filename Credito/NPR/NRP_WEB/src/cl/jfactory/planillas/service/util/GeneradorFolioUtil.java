package cl.jfactory.planillas.service.util;

public class GeneradorFolioUtil {

	static int folio = 1;
	public static int obtenerFolio(){
		folio ++;
		return folio;
	}
	
}
