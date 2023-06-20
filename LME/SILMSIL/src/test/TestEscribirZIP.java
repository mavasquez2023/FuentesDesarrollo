package test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import cl.laaraucana.silmsil.dao.PLANOSDAO;
import cl.laaraucana.silmsil.util.Configuraciones;
import cl.laaraucana.silmsil.util.UtilZip;
import cl.laaraucana.silmsil.vo.ILFSIL052VO;

public class TestEscribirZIP {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String fecha = "201303";
		Configuraciones config=new Configuraciones();
		String aux1=config.getMainConfig("directorioEstadistico");
		String rutasOrigen[]= new String[3];
		String nombresOrigen[] = new String[3];
		String nombresCarpeta[] = new String[3];
		String rutaDestino=aux1;
		String nombreDestino="SILMSIL_"+fecha;
		
		System.out.println("* * [START: ZIP]* *");
		System.out.println("aux1: "+aux1);
		rutasOrigen[0]=aux1;
		rutasOrigen[1]=aux1;
		rutasOrigen[2]=aux1;
		nombresOrigen[0]="estadistico_201302.xls";
		nombresOrigen[1]="estadistico_201303.xls";
		nombresOrigen[2]="estadistico_201303.xls";
		nombresCarpeta[0]="SIL\\";
		nombresCarpeta[1]="LM\\";
		nombresCarpeta[2]="";//en blanco, se agregara en raiz del ZIP.
		
		UtilZip uZip=new UtilZip();
		
		//uZip.generarZip(rutasOrigen, nombresOrigen, rutaDestino, nombreDestino);
		uZip.generarZip(rutasOrigen, nombresOrigen, nombresCarpeta, rutaDestino, nombreDestino);
		
		
		System.out.println("* * [END: ZIP]* *");
	}
	
}
