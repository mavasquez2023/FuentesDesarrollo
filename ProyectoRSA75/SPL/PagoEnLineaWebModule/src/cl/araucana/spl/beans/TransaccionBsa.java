package cl.araucana.spl.beans;

import cl.araucana.spl.util.Utiles;


public class TransaccionBsa extends TransaccionEft {

	protected String getNombreObjeto() {
		return Utiles.getNombreClase(TransaccionBsa.class.getName());
	}
	
}
