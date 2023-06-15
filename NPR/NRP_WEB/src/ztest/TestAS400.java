package ztest;

import java.io.IOException;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.IFSFile;

import cl.liv.comun.utiles.PropertiesUtil;

public class TestAS400 {

	public static void main(String[] args) throws IOException {
		
	
		consultar();
		
	}
	
	
	public static void consultar(){
		AS400 conexion = new AS400(PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.pub.server"), PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.pub.user"), PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.pub.password"));
		
		String path = "/ea/files/EBCRED/NRP/";
		
		IFSFile file = new IFSFile(conexion, path);
		System.out.println("folder-> "+ file.getPath());
		IFSFile[] archivosEncontrados;
		try {
			archivosEncontrados = file.listFiles();

			System.out.println("archivos encontrados: "+ archivosEncontrados.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conexion.disconnectAllServices();
	}
}
