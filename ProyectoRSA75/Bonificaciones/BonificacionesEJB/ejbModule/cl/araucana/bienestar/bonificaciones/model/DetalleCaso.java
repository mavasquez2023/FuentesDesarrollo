package cl.araucana.bienestar.bonificaciones.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class DetalleCaso implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	
	private int idDetalle = 0;
	private double monto = 0;
	private double montoDescuento = 0;
	private double aporteIsapre = 0;
	private double aporteBienestar = 0;
	private double aporteSocio = 0;
	private double aporteConvenio = 0;
	private int cantidadOcurencias=1;
	private String documento=null;
	private Producto producto = null;
	private ArrayList aporteCobertura = new ArrayList(); //AporteCobertura
	
	
	
	/**
	 * @return
	 */
	public double getTotalMenosIsapreYDescuento() {
		return monto - montoDescuento - aporteIsapre;
	}

	public double getTotal() {
		return monto - montoDescuento;
	}

	/**
	 * @return
	 */
	public double getAporteBienestar() {
		return aporteBienestar;
	}

	/**
	 * @return
	 */
	public double getAporteIsapre() {
		return aporteIsapre;
	}

	/**
	 * @return
	 */
	public double getAporteSocio() {
		return aporteSocio;
	}


	/**
	 * @return
	 */
	public double getMonto() {
		return monto;
	}

	/**
	 * @return
	 */
	public double getMontoDescuento() {
		return montoDescuento;
	}

	/**
	 * @param d
	 */
	public void setAporteBienestar(double d) {
		aporteBienestar = d;
	}

	/**
	 * @param d
	 */
	public void setAporteIsapre(double d) {
		aporteIsapre = d;
	}

	/**
	 * @param d
	 */
	public void setAporteSocio(double d) {
		aporteSocio = d;
	}


	/**
	 * @param d
	 */
	public void setMonto(double d) {
		monto = d;
	}

	/**
	 * @param d
	 */
	public void setMontoDescuento(double d) {
		montoDescuento = d;
	}

	/**
	 * @return
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param producto
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	/**
	 * @return
	 */
	public int getIdDetalle() {
		return idDetalle;
	}

	/**
	 * @param i
	 */
	public void setIdDetalle(int i) {
		idDetalle = i;
	}

	/**
	 * @return
	 */
	public ArrayList getAporteCobertura() {
		return aporteCobertura;
	}

	/**
	 * @param list
	 */
	public void setAporteCobertura(ArrayList list) {
		aporteCobertura = list;
	}

	/**
	 * @return
	 */
	public String getDocumento() {
		return documento;
	}

	/**
	 * @param string
	 */
	public void setDocumento(String string) {
		documento = string;
	}

	/**
	 * @return
	 */
	public double getAporteConvenio() {
		return aporteConvenio;
	}

	/**
	 * @param d
	 */
	public void setAporteConvenio(double d) {
		aporteConvenio = d;
	}

	/**
	 * @return
	 */
	public int getCantidadOcurencias() {
		return cantidadOcurencias;
	}

	/**
	 * @param i
	 */
	public void setCantidadOcurencias(int i) {
		cantidadOcurencias = i;
	}

}
