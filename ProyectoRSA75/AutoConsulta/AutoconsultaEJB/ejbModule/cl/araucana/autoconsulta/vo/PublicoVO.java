package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class PublicoVO implements Serializable {
	
	public static final String STD_ACTIVO="A";

	private long rut=0;
	private String dv=null;
	private String nombre=null;
	private String apellidoMaterno=null;
	private String apellidoPaterno=null;
	private String estadoEmpresa=null;
	private long codigoOficina=0;
	private long codigoSucursal=0;

	/**
	 * Devuelve el rut completo
	 * @return
	 */
	public String getFullRut() {
		return rut+" - " +dv;
	}
	
	/**
	 * Devuelve el npmbre completo
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
	 * @param l
	 */
	public void setRut(long l) {
		rut = l;
	}

	/**
	 * @return
	 */
	public String getEstadoEmpresa() {
		return estadoEmpresa;
	}

	/**
	 * @param string
	 */
	public void setEstadoEmpresa(String string) {
		estadoEmpresa = string;
	}

	/**
	 * @return
	 */
	public long getCodigoOficina() {
		return codigoOficina;
	}

	/**
	 * @return
	 */
	public long getCodigoSucursal() {
		return codigoSucursal;
	}

	/**
	 * @param l
	 */
	public void setCodigoOficina(long l) {
		codigoOficina = l;
	}

	/**
	 * @param l
	 */
	public void setCodigoSucursal(long l) {
		codigoSucursal = l;
	}

}
