package cl.araucana.spl.beans;

import cl.araucana.spl.util.Utiles;


public class DetalleTrxBCH extends DetalleTrxEft {
	protected String getNombreObjeto() {
		return Utiles.getNombreClase(DetalleTrxBCH.class.getName());
	}	
}
