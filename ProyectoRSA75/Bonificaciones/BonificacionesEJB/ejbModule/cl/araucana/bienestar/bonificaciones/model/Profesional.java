package cl.araucana.bienestar.bonificaciones.model;

import java.io.Serializable;

/**
 * @author asepulveda
 * Representa la informacion de un Profesional para los egresos en tesoreria
 */
public class Profesional implements Serializable {

	/** Serial */
	private static final long serialVersionUID = 1L;

	
   private String rut=null;
   private String digito=null;
   private String nombre=null;
   
   /**
	* Retorna el rut compuesto del Socio
	* @return String con el rut
	*/
   public String getFullRut() {
	   return rut+"-"+digito;
   }
	
	/**
	 * @return
	 */
	public String getDigito() {
		return digito;
	}
	
	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @return
	 */
	public String getRut() {
		return rut;
	}
	
	/**
	 * @param string
	 */
	public void setDigito(String string) {
		digito = string;
	}
	
	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}
	
	/**
	 * @param string
	 */
	public void setRut(String string) {
		rut = string;
	}

}
