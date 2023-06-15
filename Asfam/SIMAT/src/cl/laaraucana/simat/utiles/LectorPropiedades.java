package cl.laaraucana.simat.utiles;

import java.util.ResourceBundle;

/*
 * clase que permite leer los archivos properties
 * */
public class LectorPropiedades {

	public String getAtributoRepositorio(String key) {
		String p = null;
		ResourceBundle resource = ResourceBundle.getBundle("cl/laaraucana/simat/config/ConfiguracionRepositorio");
		p = resource.getString(key);
		return p;
	}

	public String getAtributoCuentasIF(String key) {
		String p = null;
		ResourceBundle resource = ResourceBundle.getBundle("cl/laaraucana/simat/config/ConfiguracionCuentasIF");
		p = resource.getString(key);
		return p;
	}

}
