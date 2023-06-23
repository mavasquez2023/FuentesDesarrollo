package cl.araucana.spl.beans;

import cl.araucana.spl.util.Utiles;


public class DetalleTrxBSA extends DetalleTrxEft {

	protected String getNombreObjeto() {
		return Utiles.getNombreClase(DetalleTrxBSA.class.getName());
	}	
	
}
