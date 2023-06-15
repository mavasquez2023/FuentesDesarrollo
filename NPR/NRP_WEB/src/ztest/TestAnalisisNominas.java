package ztest;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

public class TestAnalisisNominas {

	static HashMap directorios = new HashMap();
	static HashMap nominas = new HashMap();
	public static void main(String[] args) {
		
		String PATH_PROD = "/home/clillo/nrp/output/201906_prod/";
		String PATH_LOCAL = "/home/clillo/nrp/output/201906_01/";
		
		
		File directorio = new File(PATH_PROD);
		
		File[] empresasProduccion = directorio.listFiles();
		
		for(int i=0; i< empresasProduccion.length; i++){
			directorios.put(empresasProduccion[i].getName(), "1");
		}
		
		
		File local =  new File(PATH_LOCAL);
		File[] empresasLocal = local.listFiles();
		
		for(int i=0; i< empresasLocal.length; i++){
			directorios.remove(empresasLocal[i].getName());
		}
		
		
		Iterator it = directorios.keySet().iterator();
		System.out.println("No encontradas: ");
		while(it.hasNext()){
			Object obj = it.next();
			System.out.println( "obj -> "+ obj);
		}
		
	}
	
}
