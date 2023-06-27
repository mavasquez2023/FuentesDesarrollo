package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author asepulveda
 *
 */
public class DatosProfesionalesVO implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;
	
	private String rut=null;
	private String digito=null;
	private String nombre=null;
	private String tipoPago="";
	private double monto=0;
	private Collection detalles = new ArrayList(); //DetalleMovimientoPreCasoVO
	
	/**
	 * Retorna el rut con el dv incluido
	 * @return String con el rut y dv
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
	public double getMonto() {
		return monto;
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
	 * @return
	 */
	public String getTipoPago() {
		return tipoPago;
	}

	/**
	 * @param string
	 */
	public void setDigito(String string) {
		digito = string;
	}

	/**
	 * @param d
	 */
	public void setMonto(double d) {
		monto = d;
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

	/**
	 * @param string
	 */
	public void setTipoPago(String string) {
		tipoPago = string;
	}

	/**
	 * @return
	 */
	public Collection getDetalles() {
		return detalles;
	}

	/**
	 * @param collection
	 */
	public void setDetalles(Collection collection) {
		detalles = collection;
	}

}
