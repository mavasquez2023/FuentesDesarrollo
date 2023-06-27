package cl.araucana.bienestar.bonificaciones.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class Producto implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	
	//Estados	
	public static final String STD_ACTIVO="ACTIVO";	
	public static final String STD_INACTIVO="INACTIVO";	
	
//	//bonificacion especial
//	public static final int BONIF_ESP_SOCIO=1;	//El socio cancela el co-pago
//	public static final int BONIF_ESP_CONVENIO=0;	//El Convenio cancela el co-pago

	private Cobertura cobertura;
	private double descuento = 0;
	private Date fechaIngreso = null;
	private String estado = STD_ACTIVO;
	private long cuentaGasto=0;
//	private int descuentoSocio=BONIF_ESP_SOCIO;
	private int porcentajeAporteConvenio=0;
	private ArrayList rango = new ArrayList();
	
	/**
	 * Agrega un rango
	 */
	public void addRango(Rango ran) {
		if (rango == null) {
			rango = new ArrayList();
		}
		rango.add(ran);
	}
	
	/**
	 * Elimina un rango
	 */
	public void removeRango(int index) {
		if (rango.size() > 0 && index < rango.size())
			rango.remove(index);
	}
	
	/**
	 * @return
	 */
	public Cobertura getCobertura() {
		return cobertura;
	}

	/**
	 * @return
	 */
	public double getDescuento() {
		return descuento;
	}

	/**
	 * @return
	 */
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @param cobertura
	 */
	public void setCobertura(Cobertura cobertura) {
		this.cobertura = cobertura;
	}


	/**
	 * @param i
	 */
	public void setDescuento(double i) {
		descuento = i;
	}

	/**
	 * @param date
	 */
	public void setFechaIngreso(Date date) {
		fechaIngreso = date;
	}

	/**
	 * @return
	 */
	public ArrayList getRango() {
		return rango;
	}

	/**
	 * @param list
	 */
	public void setRango(ArrayList list) {
		rango = list;
	}

	/**
	 * @return
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param string
	 */
	public void setEstado(String string) {
		estado = string;
	}

	/**
	 * @return
	 */
	public long getCuentaGasto() {
		return cuentaGasto;
	}

	/**
	 * @param l
	 */
	public void setCuentaGasto(long l) {
		cuentaGasto = l;
	}

/**
 * @return
 */
public int getPorcentajeAporteConvenio() {
	return porcentajeAporteConvenio;
}

/**
 * @param i
 */
public void setPorcentajeAporteConvenio(int i) {
	porcentajeAporteConvenio = i;
}

}
