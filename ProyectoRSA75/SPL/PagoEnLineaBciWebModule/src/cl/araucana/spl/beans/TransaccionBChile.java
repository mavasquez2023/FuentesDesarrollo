package cl.araucana.spl.beans;

import cl.araucana.spl.util.Utiles;


public class TransaccionBChile extends TransaccionEft {

	protected String getNombreObjeto() {
		return Utiles.getNombreClase(TransaccionBChile.class.getName());
	}
	
}