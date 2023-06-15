package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class CargaFamiliarVO implements Serializable {
		 
	public static final String STD_ACTIVA="A";
	public static final String STD_INACTIVA="I";

	private long rut=0;;
	private String dv=null;
	private String nombre=null;
	private String apellidoMaterno=null;
	private String apellidoPaterno=null;
	private String codigoParentezco=null;
	private Date fechaNacimiento=null;
	private Date fechaVencimiento=null;
	private String codigoCondicionInvalidez=null;
	private String codigoEstado=null;
	private Date fechaAutorizacion=null;
	private Date fechaAnulacion=null;
	private Date fechaProceso=null;
	private int numeroCarga=0;

	/**
	 * Devuelve el rut completo
	 * @return
	 */
	public String getFullRut() {
		return rut+" - " +dv;
	}
	
	/**
	 * Devuelve el nombre completo
	 * @return
	 */
	public String getFullNombre() {
		return nombre+" " +apellidoPaterno+" "+apellidoMaterno;
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
	public String getCodigoCondicionInvalidez() {
		return codigoCondicionInvalidez;
	}

	/**
	 * @return
	 */
	public String getCodigoEstado() {
		return codigoEstado;
	}

	/**
	 * @return
	 */
	public String getCodigoParentezco() {
		return codigoParentezco;
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
	public Date getFechaAnulacion() {
		return fechaAnulacion;
	}

	/**
	 * @return
	 */
	public Date getFechaAutorizacion() {
		return fechaAutorizacion;
	}

	/**
	 * @return
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @return
	 */
	public Date getFechaProceso() {
		return fechaProceso;
	}

	/**
	 * @return
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento;
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
	public int getNumeroCarga() {
		return numeroCarga;
	}

	/**
	 * @return
	 */
	public long getRut() {
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
	public void setCodigoCondicionInvalidez(String string) {
		codigoCondicionInvalidez = string;
	}

	/**
	 * @param string
	 */
	public void setCodigoEstado(String string) {
		codigoEstado = string;
	}

	/**
	 * @param string
	 */
	public void setCodigoParentezco(String string) {
		codigoParentezco = string;
	}

	/**
	 * @param string
	 */
	public void setDv(String string) {
		dv = string;
	}

	/**
	 * @param date
	 */
	public void setFechaAnulacion(Date date) {
		fechaAnulacion = date;
	}

	/**
	 * @param date
	 */
	public void setFechaAutorizacion(Date date) {
		fechaAutorizacion = date;
	}

	/**
	 * @param date
	 */
	public void setFechaNacimiento(Date date) {
		fechaNacimiento = date;
	}

	/**
	 * @param date
	 */
	public void setFechaProceso(Date date) {
		fechaProceso = date;
	}

	/**
	 * @param date
	 */
	public void setFechaVencimiento(Date date) {
		fechaVencimiento = date;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}

	/**
	 * @param i
	 */
	public void setNumeroCarga(int i) {
		numeroCarga = i;
	}

	/**
	 * @param l
	 */
	public void setRut(long l) {
		rut = l;
	}

}
