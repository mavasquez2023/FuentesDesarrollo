package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class DetallePagoConvenioVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	private long codigoConvenio=0;
	private String nombreConvenio=null;
	private String rut=null;
	private String dv=null;
	private long folioTesoreria=0;
	private int monto=0;
	
	
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
	public long getFolioTesoreria() {
		return folioTesoreria;
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
	 * @param l
	 */
	public void setFolioTesoreria(long l) {
		folioTesoreria = l;
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

}
