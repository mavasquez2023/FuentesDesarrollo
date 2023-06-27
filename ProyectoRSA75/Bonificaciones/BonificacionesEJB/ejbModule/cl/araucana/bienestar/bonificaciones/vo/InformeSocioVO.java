package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.ArrayList;

import cl.araucana.bienestar.bonificaciones.model.Socio;

/**
 * @author Andrés Bilbao B.
 *
 */
public class InformeSocioVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	private Socio socio=null;
	private ArrayList cargas=null; 
	private ArrayList vales=null; 
	private ArrayList prestamos=null; 
	private ArrayList descuentos=null; 
	private ArrayList reembolsos=null; 

	/**
	 * @return
	 */
	public ArrayList getCargas() {
		return cargas;
	}

	/**
	 * @return
	 */
	public ArrayList getDescuentos() {
		return descuentos;
	}

	/**
	 * @return
	 */
	public ArrayList getPrestamos() {
		return prestamos;
	}

	/**
	 * @return
	 */
	public ArrayList getReembolsos() {
		return reembolsos;
	}

	/**
	 * @return
	 */
	public Socio getSocio() {
		return socio;
	}

	/**
	 * @return
	 */
	public ArrayList getVales() {
		return vales;
	}

	/**
	 * @param list
	 */
	public void setCargas(ArrayList list) {
		cargas = list;
	}

	/**
	 * @param list
	 */
	public void setDescuentos(ArrayList list) {
		descuentos = list;
	}

	/**
	 * @param list
	 */
	public void setPrestamos(ArrayList list) {
		prestamos = list;
	}

	/**
	 * @param list
	 */
	public void setReembolsos(ArrayList list) {
		reembolsos = list;
	}

	/**
	 * @param socio
	 */
	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	/**
	 * @param list
	 */
	public void setVales(ArrayList list) {
		vales = list;
	}

}
