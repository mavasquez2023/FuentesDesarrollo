package test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import cl.laaraucana.silmsil.dao.SIL_DAO;
import cl.laaraucana.silmsil.manager.ManagerProcesar;
import cl.laaraucana.silmsil.util.ArchivoPlano;
import cl.laaraucana.silmsil.util.Configuraciones;
import cl.laaraucana.silmsil.vo.SIL_GlosaErrores_VO;

public class TestIFS {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		
		String fileOut = Configuraciones.getMainConfig("directorioEstadistico");
		
		String carpetaDestino = Configuraciones.getMainConfig("rutaEstadistico");
		//String carpetaDestino = Configuraciones.getMainConfig("rutaLM");
		
		//String nombreArchivo="estadistico_201301.xls";		
		String nombreArchivo="es201302.xls";
		//String dirTemplateCE=Configuraciones.getMainConfig("templateEstadistico");
		boolean respIFS=false;
		
		System.out.println("* fileOut: "+fileOut);
		System.out.println("* carpetaDestino: "+carpetaDestino);
		System.out.println("* nombreArchivo: "+nombreArchivo);
		System.out.println("* respIFS: "+respIFS);
		
		System.out.println("\n *test 1: ");
		System.out.println("* setCopyByIFS("+carpetaDestino+nombreArchivo+" , "+fileOut+nombreArchivo+"): ");		
		respIFS= ArchivoPlano.setCopyByIFS(carpetaDestino+nombreArchivo,fileOut+nombreArchivo);
		System.out.println("* respIFS: "+respIFS);
		
		
		
	}//end main

}//end class
