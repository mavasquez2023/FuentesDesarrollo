package ztest;

import java.io.File;

import cl.jfactory.planillas.service.util.Utiles;
import cl.liv.comun.utiles.PropertiesUtil;

public class TestValidacionNominas {

	public static void main(String[] args) {
		
		
		String path = PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+"/"+Utiles.getPeriodoActual()+"/";
		
		File[] entidades = new File(path).listFiles();
		for(int i=0; i< entidades.length; i++){
			File[] archivos = new File(entidades[i].getAbsolutePath()).listFiles();
			if(archivos.length == 0){
				System.out.println("entidad sin nóminas: "+ entidades[i].getName());
			}
		}
	}
	
}
