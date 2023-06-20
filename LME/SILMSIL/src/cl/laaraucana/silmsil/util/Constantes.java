package cl.laaraucana.silmsil.util;
/**
 * Clase que soporta los valores como constantes para la aplicacion. 
 * 
 * **/
public class Constantes {

	/*Para instancia de clase*/
	private static Constantes c;

	public static Constantes getInstancia() throws Exception {

		if (c == null) {
			c = new Constantes();
			//Asignar los valores desde la BD
			cargarParametros();
		}
		return c;
	}

	public static void recargarParametros() throws Exception {
		if (c == null) {
			c = new Constantes();
		}
		//Reasignar los valores desde la BD
		cargarParametros();
	}

	public String RESOURCE_MAP_CONFIG = "cl/laaraucana/silmsil/dao/conf/IbatisConfig.xml";
	
	
	private static void cargarParametros() throws Exception {
	}

	/*Variables que obtienen sus valores desde tabla de parámetros.*/

	/*metodos set*/

}//end Class
