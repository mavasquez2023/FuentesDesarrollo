package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class DetalleCasosDescontadosConvenioVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	private String rut=null;
	private String dv=null;
	private String nombre=null;
	private String apellidoPaterno=null;
	private String apellidoMaterno=null;
	private int aporteConvenio=0;
	private Date fechaOcurrencia=null;
	private long casoID = 0;
	private String detalleCobertura=null; 

	/**
	 * Retorna el rut compuesto del Socio
	 * @return String con el rut
	 */
	public String getFullRut() {
		return rut+"-"+dv;
	}
	
	/**
	 * Retorna el nombre completo del Socio
	 * @return String
	 */
	public String getFullNombre() {
		return nombre+" "+apellidoPaterno+" "+apellidoMaterno;
	}
	

	/**
	 * @return
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * @return
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
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
	public void setApellidoMaterno(String string) {
		apellidoMaterno = string;
	}

	/**
	 * @param string
	 */
	public void setApellidoPaterno(String string) {
		apellidoPaterno = string;
	}

	/**
	 * @param string
	 */
	public void setDv(String string) {
		dv = string;
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
	 * @return
	 */
	public int getAporteConvenio() {
		return aporteConvenio;
	}

	/**
	 * @return
	 */
	public long getCasoID() {
		return casoID;
	}

	/**
	 * @return
	 */
	public Date getFechaOcurrencia() {
		return fechaOcurrencia;
	}

	/**
	 * @param i
	 */
	public void setAporteConvenio(int i) {
		aporteConvenio = i;
	}

	/**
	 * @param l
	 */
	public void setCasoID(long l) {
		casoID = l;
	}

	/**
	 * @param date
	 */
	public void setFechaOcurrencia(Date date) {
		fechaOcurrencia = date;
	}

	/**
	 * @return
	 */
	public String getDetalleCobertura() {
		return detalleCobertura;
	}

	/**
	 * @param string
	 */
	public void setDetalleCobertura(String string) {
		detalleCobertura = string;
	}

}
