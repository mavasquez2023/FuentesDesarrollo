package cl.araucana.tupla2.struts.bussiness.TO;

import org.apache.struts.action.ActionForm;

public class TramosTO extends ActionForm {

	private static final long serialVersionUID = 1284335245228030241L;

	public TramosTO() {

	}
	private int tramo;
	private int valor;
	private int rentaMinima;
	private int rentaMaxima;
	private String fechaTramo;

	/**
	 * @return the tramo
	 */
	public int getTramo() {
		return tramo;
	}
	/**
	 * @param tramo the tramo to set
	 */
	public void setTramo(int tramo) {
		this.tramo = tramo;
	}
	/**
	 * @return the valor
	 */
	public int getValor() {
		return valor;
	}
	/**
	 * @param valor the valor to set
	 */
	public void setValor(int valor) {
		this.valor = valor;
	}
	/**
	 * @return the rentaMinima
	 */
	public int getRentaMinima() {
		return rentaMinima;
	}
	/**
	 * @param rentaMinima the rentaMinima to set
	 */
	public void setRentaMinima(int rentaMinima) {
		this.rentaMinima = rentaMinima;
	}
	/**
	 * @return the rentaMaxima
	 */
	public int getRentaMaxima() {
		return rentaMaxima;
	}
	/**
	 * @param rentaMaxima the rentaMaxima to set
	 */
	public void setRentaMaxima(int rentaMaxima) {
		this.rentaMaxima = rentaMaxima;
	}
	/**
	 * @return the fechaTramo
	 */
	public String getFechaTramo() {
		return fechaTramo;
	}
	/**
	 * @param fechaTramo the fechaTramo to set
	 */
	public void setFechaTramo(String fechaTramo) {
		this.fechaTramo = fechaTramo;
	}
	
}
