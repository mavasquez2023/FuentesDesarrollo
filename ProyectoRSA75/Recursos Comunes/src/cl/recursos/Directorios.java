/**
 * 
 */
package cl.recursos;

/**
 * @author Usist24
 *
 */
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Directorios {
	static List mapapath= new ArrayList();
	static List estadisticas= new ArrayList();
	static int nivel= 0;
	public static void  getDirs(String path) {
		File dir = new File(path);
		
		FileFilter fileFilter = new FileFilter() {
	        public boolean accept(File file) {
	            return file.isDirectory();
	        }
	    };
	    String lastCarpeta="";
	    if(nivel>0){
	    	lastCarpeta= path.substring(path.lastIndexOf("\\")+1);
	    }
	    nivel++;
	    //File[] dirs = dir.listFiles(fileFilter);
	    File[] dirs = dir.listFiles();
	    
	    if (dirs != null) {
	    	int numfiles=0;
	    	boolean lastisFile=false;
	        for (int i=0; i < dirs.length; i++) {
	        	if(dirs[i].isDirectory()){
	        		String carpeta = dirs[i].getName();
	        		//Directorios subdir= new Directorios();
	        		if(!carpeta.equalsIgnoreCase("Formato Excel")){
	        			//System.out.print (filename + "\n");
	        			mapapath.add(carpeta + "\n");
	        			if(nivel>1){
	        				if(!lastCarpeta.trim().equals("")){
	        					estadisticas.add(lastCarpeta + ";" + carpeta + ";");
	        				}
	        			}
	        			getDirs(path + "\\" + carpeta);
	        		}
	        	}else{
	        		String filename = dirs[i].getName();
	        		//System.out.print (path + "\\" + filename + "\n");
	        		if(filename.indexOf(".xls")== -1 && filename.indexOf(".pdf")== -1 && filename.indexOf(".csv")== -1){
	        			mapapath.add(path + "\\" + filename + "\n");
	        			numfiles++;
	        		}
	        		lastisFile=true;
	        	}
	        }
	        	estadisticas.add("" + numfiles + "\n");
	    }	    
		
	}

public static void main(String[] args) {

	Directorios dir= new Directorios();
	String path="\\\\146.83.1.96\\envios\\RESPALDO ARCHIVO SII";
	dir.getDirs(path);
	System.out.println (getMapapath().toString());
	Archivo resultado= new Archivo();
    try {
		resultado.crearArchivo(path + "\\mapapath.txt", mapapath.toString());
		resultado.crearArchivo(path + "\\estadisticas.txt", estadisticas.toString());
	} catch (Exception e) {
		// TODO Bloque catch generado automáticamente
		e.printStackTrace();
	}
}

/**
 * @return el estadisticas
 */
public static List getEstadisticas() {
	return estadisticas;
}

/**
 * @param estadisticas el estadisticas a establecer
 */
public static void setEstadisticas(List estadisticas) {
	Directorios.estadisticas = estadisticas;
}

/**
 * @return el mapapath
 */
public static List getMapapath() {
	return mapapath;
}

/**
 * @param mapapath el mapapath a establecer
 */
public static void setMapapath(List mapapath) {
	Directorios.mapapath = mapapath;
}
}