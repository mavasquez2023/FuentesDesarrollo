package cl.araucana.ctasfam;

import java.io.File;

import cl.araucana.ctasfam.resources.util.ExplorerManagerAs400;

import com.ibm.as400.access.AS400;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AS400 system = new AS400("146.83.1.2", "schema", "schema");
		ExplorerManagerAs400 files= new ExplorerManagerAs400(system);
		System.out.println("AS400 Conectado...");
		//ExplorerManagerAs400 filein= new ExplorerManagerAs400();
		System.out.println("entrando");
		//ExplorerManagerAs400 files= new ExplorerManagerAs400();
		String ruta = "/files/Rentas";
		try {
			File[] archivos = null;
			if(files.existFile(ruta)) {
				archivos = files.getListaDeArchivos(ruta);
				System.out.println("Lista de archivos en " + ruta );
				for (int i = 0; i < archivos.length; i++) {
					System.out.println("" + archivos[i].getName());
				}
			}
			files.disconect();
			files.estatusAS400();
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}

}
