package cl.laaraucana.menudinamico.util.menuArbol;

import java.util.ResourceBundle;

public class Constantes {
	private static Constantes c;
	private static ResourceBundle parametros = ResourceBundle.getBundle("constantes");
	
	public String URL_MENUDINAMICO = "";

	public static Constantes getInstancia() throws Exception {
		if (c == null) {
			c = new Constantes();

			// asignar los valores desde la BD
			c.cargarParametros();
		} else {
		}
		return c;
	}

	private void cargarParametros() {
		setURL_MENUDINAMICO();
	}

	private  void setURL_MENUDINAMICO() {
		//se obtiene url desde el archivo properties  
		URL_MENUDINAMICO = parametros.getString("url.menudinamico");
	}

}
