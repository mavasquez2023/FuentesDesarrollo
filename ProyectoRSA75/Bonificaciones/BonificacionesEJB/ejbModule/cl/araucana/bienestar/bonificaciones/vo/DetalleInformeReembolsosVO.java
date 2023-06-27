package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class DetalleInformeReembolsosVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	String codigoOficina=null;
	String oficina=null;
	private int cantidadFilas=0;
	private int montoTotalOficina=0;
	private ArrayList reembolsos=new ArrayList();

	/**
	 * @return
	 */
	public int getCantidadFilas() {
		return cantidadFilas;
	}

	/**
	 * @return
	 */
	public String getCodigoOficina() {
		return codigoOficina;
	}

	/**
	 * @return
	 */
	public int getMontoTotalOficina() {
		return montoTotalOficina;
	}

	/**
	 * @return
	 */
	public String getOficina() {
		return oficina;
	}

	/**
	 * @return
	 */
	public ArrayList getReembolsos() {
		return reembolsos;
	}

	/**
	 * @param i
	 */
	public void setCantidadFilas(int i) {
		cantidadFilas = i;
	}

	/**
	 * @param string
	 */
	public void setCodigoOficina(String string) {
		codigoOficina = string;
	}

	/**
	 * @param i
	 */
	public void setMontoTotalOficina(int i) {
		montoTotalOficina = i;
	}

	/**
	 * @param string
	 */
	public void setOficina(String string) {
		oficina = string;
	}

	/**
	 * @param list
	 */
	public void setReembolsos(ArrayList list) {
		reembolsos = list;
	}

}
