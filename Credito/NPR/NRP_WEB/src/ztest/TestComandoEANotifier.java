package ztest;

import cl.liv.comun.utiles.PropertiesUtil;
import cl.liv.comun.utiles.UtilesComunes;

public class TestComandoEANotifier {

	public static void main(String[] args) {
		
		
		String comandoPublicacion = PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.comando.publicacion.nominas");
		if(comandoPublicacion != null && comandoPublicacion.length()>0){
			comandoPublicacion = UtilesComunes.reemplazarVariables(comandoPublicacion);
			System.out.println("comando -> "+ comandoPublicacion);
		}
		
	}
	
}
