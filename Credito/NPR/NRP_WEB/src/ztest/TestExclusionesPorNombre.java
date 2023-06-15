package ztest;

import java.util.ResourceBundle;

public class TestExclusionesPorNombre {

	public static void main(String[] args) {
		
		String[] patrones = ResourceBundle.getBundle("etc/workflow_configuraciones").getString("config.excluir.disponibilizacion.archivos.comienzan.con").split(";");
		
		String[] archivos = new String[5];
		archivos[0]="ARCHIVO1.txt";
		archivos[1]="PRUEBA1.txt";
		archivos[2]="APCRE.txt";
		archivos[3]="NOMINA.txt";
		archivos[4]="BETA.txt";
		
		
		for(int i=0;i< archivos.length; i++){
			
			System.out.println("validando->"+ archivos[i] + " , "+ contienePatron(archivos[i], patrones));
			
		}
		
	}
	
	public static boolean contienePatron(String nombre, String[] patrones){
		if(patrones != null && patrones.length > 0){
			for(int i=0; i<patrones.length; i++){
					if(nombre.toLowerCase().startsWith(patrones[i].toLowerCase())){
						return true;
					}
			}
		}
		
		return false;
	}
	
}
