package cl.laaraucana.simat.utiles;

import java.io.File;

/*
 * clase que permite comprobar existencia de la ruta utilizada para escribir archivos
 * */
public class ComprobadorRutaPeriodo {

	public String validarRuta(String ruta, String periodo) {

		//comprobamos si existe la carpeta
		File folder = null;
		folder = new File(ruta);

		if (folder.exists()) {
			// si existe los ficheros 
			System.out.println("ruta existe.");
			//entonces preguntamos si la carpeta periodo() ya existe, para no sobreescribirla.
			folder = new File(ruta + periodo);
			if (folder.exists()) {
				System.out.println("folder periodo ya existe.");
			} else {
				System.out.println("folder periodo NO existe. se procedera a crear carpeta.");
				folder.mkdir();
			}
		} else {
			System.out.println("no encontrado, se procedera a crear carpeta. ");
			folder = new File(ruta + periodo);
			folder.mkdir(); // esto crea la carpeta java			
		}
		System.out.println("folder=" + folder);

		return (ruta + periodo + "\\");
	}
}
