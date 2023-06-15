package ztest;

import com.ibm.as400.access.AS400;

import cl.liv.archivos.as400.impl.ArchivosAS400;
import cl.liv.comun.utiles.PropertiesUtil;

public class TestEnvioArchivoTESTDESA {
	public static void main(String[] args) {
		
		
		String pathInput = "/home/desarrollo/Descargas/71127200-104-001.txt";
		String pathOutput = "/ea/files/EBCRED/nrp_test/tres.txt";
		AS400 conexion = new AS400(PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.server"), PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.user"), PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.password"));
		System.out.println("copiando archivo a intranet ["+pathInput+"]");
		ArchivosAS400.copiarArchivoConEncoding( conexion  , pathInput, pathOutput);
		conexion.disconnectAllServices();
		
	}
}
