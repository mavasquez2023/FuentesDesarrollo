package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class PagoConvenioPendienteVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	private long codigoConvenio=0;
	private String nombreConvenio=null;
	private String rut=null;
	private String dv=null;
	private int monto=0;
	private long area=0;
	private long conceptoEgreso=0;
	private long folioTesoreria=0;
	private long codigoPago;
	private Date fechaPago=null;

	/**
	 * Retorna el rut compuesto del Convenio
	 * @return String con el rut
	 */
	public String getFullRut() {
		return rut+"-"+dv;
	}


	/**
	 * @return
	 */
	public long getCodigoConvenio() {
		return codigoConvenio;
	}

	/**
	 * @return
	 */
	public String getDv() {
		return dv;
	}

	/**
	 * @return
	 */
	public int getMonto() {
		return monto;
	}

	/**
	 * @return
	 */
	public String getNombreConvenio() {
		return nombreConvenio;
	}

	/**
	 * @return
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * @param l
	 */
	public void setCodigoConvenio(long l) {
		codigoConvenio = l;
	}

	/**
	 * @param string
	 */
	public void setDv(String string) {
		dv = string;
	}

	/**
	 * @param i
	 */
	public void setMonto(int i) {
		monto = i;
	}

	/**
	 * @param string
	 */
	public void setNombreConvenio(String string) {
		nombreConvenio = string;
	}

	/**
	 * @param string
	 */
	public void setRut(String string) {
		rut = string;
	}

	/**
	 * @return
	 */
	public long getFolioTesoreria() {
		return folioTesoreria;
	}

	/**
	 * @param l
	 */
	public void setFolioTesoreria(long l) {
		folioTesoreria = l;
	}

	/**
	 * @return
	 */
	public long getCodigoPago() {
		return codigoPago;
	}

	/**
	 * @param l
	 */
	public void setCodigoPago(long l) {
		codigoPago = l;
	}

	/**
	 * @return
	 */
	public Date getFechaPago() {
		return fechaPago;
	}

	/**
	 * @param date
	 */
	public void setFechaPago(Date date) {
		fechaPago = date;
	}

	/**
	 * @return
	 */
	public long getArea() {
		return area;
	}

	/**
	 * @param l
	 */
	public void setArea(long l) {
		area = l;
	}

	/**
	 * @return
	 */
	public long getConceptoEgreso() {
		return conceptoEgreso;
	}

	/**
	 * @param l
	 */
	public void setConceptoEgreso(long l) {
		conceptoEgreso = l;
	}



}
