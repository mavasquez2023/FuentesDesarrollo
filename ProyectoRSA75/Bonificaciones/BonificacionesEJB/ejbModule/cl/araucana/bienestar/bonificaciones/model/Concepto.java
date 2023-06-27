package cl.araucana.bienestar.bonificaciones.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class Concepto implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	
	private long codigo=0;
	private String descripcion=null;
	private Date fechaCreacion = null;
	private long cuentaDeudor=0;
	private long cuentaAcreedor=0;
	private long tesoreriaArea=0;
	private long tesoreriaConceptoIngreso=0;
	private long tesoreriaConceptoEgreso=0;

	/**
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}


	/**
	 * @param string
	 */
	public void setDescripcion(String string) {
		descripcion = string;
	}


	/**
	 * @return
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param date
	 */
	public void setFechaCreacion(Date date) {
		fechaCreacion = date;
	}

	/**
	 * @return
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * @param l
	 */
	public void setCodigo(long l) {
		codigo = l;
	}

	/**
	 * @return
	 */
	public long getCuentaAcreedor() {
		return cuentaAcreedor;
	}

	/**
	 * @return
	 */
	public long getCuentaDeudor() {
		return cuentaDeudor;
	}

	/**
	 * @param l
	 */
	public void setCuentaAcreedor(long l) {
		cuentaAcreedor = l;
	}

	/**
	 * @param l
	 */
	public void setCuentaDeudor(long l) {
		cuentaDeudor = l;
	}

	/**
	 * @return
	 */
	public long getTesoreriaArea() {
		return tesoreriaArea;
	}

	/**
	 * @return
	 */
	public long getTesoreriaConceptoEgreso() {
		return tesoreriaConceptoEgreso;
	}

	/**
	 * @return
	 */
	public long getTesoreriaConceptoIngreso() {
		return tesoreriaConceptoIngreso;
	}

	/**
	 * @param l
	 */
	public void setTesoreriaArea(long l) {
		tesoreriaArea = l;
	}

	/**
	 * @param l
	 */
	public void setTesoreriaConceptoEgreso(long l) {
		tesoreriaConceptoEgreso = l;
	}

	/**
	 * @param l
	 */
	public void setTesoreriaConceptoIngreso(long l) {
		tesoreriaConceptoIngreso = l;
	}

}
