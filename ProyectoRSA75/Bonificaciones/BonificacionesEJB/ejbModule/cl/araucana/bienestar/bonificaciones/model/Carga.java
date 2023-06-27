package cl.araucana.bienestar.bonificaciones.model;

import java.io.Serializable;
import java.util.Date;

import cl.araucana.personal.vo.CargaVO;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class Carga implements Serializable{
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	
	public static final String STD_INACTIVO = "I";
	public static final String STD_ACTIVO = "A";
	private String rutCarga = null;
	private String rutSocio= null;
	private String dvSocio= null;
	private String nombreSocio= null;
	private String dvCarga = null;
	private String numCarga= null;
	private String nombreCarga= null;
	private String apePatCarga= null;
	private String apeMatCarga= null;
	private Date fecNacCarga= null;
	private Date fecIngCarga= null;
	private Date fecEgrCarga= null;
	private String tipoCarga= null;
	private String sexoCarga= null;
	private String estadoCarga= null;

	public Carga(){
	}
	
	/**
	  * Constructor de una Carga a partir de un Value Object de Carga
	  * @param car: CargaVO
	  */
	 public Carga (CargaVO car) {
		rutCarga = formateaRut(String.valueOf(car.getCargaRut()));
		rutSocio= formateaRut(String.valueOf(car.getSocioRut()));
		dvSocio= null;
		nombreSocio= null;
		dvCarga = String.valueOf(car.getCargaDv());
		numCarga= String.valueOf(car.getCargaNum());
		nombreCarga= car.getCargaNombre();
		apePatCarga= car.getCargaApePat();
		apeMatCarga= car.getCargaApeMat();
		fecNacCarga= car.getCargaFecNac();
		fecIngCarga= car.getCargaFecIniBeneficios();
		fecEgrCarga= car.getCargaFecFin();
		tipoCarga= String.valueOf(car.getCargaTipo());
		sexoCarga= String.valueOf(car.getCargaSexo());
		estadoCarga= String.valueOf(car.getCargaEstado());
	 }
	 
	 public static final String formateaRut(String rut) {
		String formato = "00000000";
		return formato.substring(0,(formato.length() - rut.length())) + rut;
	 }
	 
	/**
	 * Retorna el rut compuesto de la Carga
	 * @return String con el rut
	 */
	public String getFullRutCarga() {
		return rutCarga + "-" + dvCarga;
	}

	/**
	 * Retorna el rut compuesto del Socio
	 * @return String con el rut
	 */
	public String getFullRutSocio() {
		return rutSocio + "-" + dvSocio;
	}

	/**
	 * @return
	 */
	public String getApeMatCarga() {
		return apeMatCarga;
	}

	/**
	 * @return
	 */
	public String getApePatCarga() {
		return apePatCarga;
	}

	/**
	 * @return
	 */
	public String getDvCarga() {
		return dvCarga;
	}

	/**
	 * @return
	 */
	public String getEstadoCarga() {
		return estadoCarga;
	}

	/**
	 * @return
	 */
	public Date getFecEgrCarga() {
		return fecEgrCarga;
	}

	/**
	 * @return
	 */
	public Date getFecIngCarga() {
		return fecIngCarga;
	}

	/**
	 * @return
	 */
	public Date getFecNacCarga() {
		return fecNacCarga;
	}

	/**
	 * @return
	 */
	public String getNombreCarga() {
		return nombreCarga;
	}

	/**
	 * @return
	 */
	public String getNumCarga() {
		return numCarga;
	}

	/**
	 * @return
	 */
	public String getRutCarga() {
		return rutCarga;
	}

	/**
	 * @return
	 */
	public String getRutSocio() {
		return rutSocio;
	}

	/**
	 * @return
	 */
	public String getSexoCarga() {
		return sexoCarga;
	}

	/**
	 * @return
	 */
	public String getTipoCarga() {
		return tipoCarga;
	}

	/**
	 * @param string
	 */
	public void setApeMatCarga(String string) {
		apeMatCarga = string;
	}

	/**
	 * @param string
	 */
	public void setApePatCarga(String string) {
		apePatCarga = string;
	}

	/**
	 * @param string
	 */
	public void setDvCarga(String string) {
		dvCarga = string;
	}

	/**
	 * @param string
	 */
	public void setEstadoCarga(String string) {
		estadoCarga = string;
	}

	/**
	 * @param date
	 */
	public void setFecEgrCarga(Date date) {
		fecEgrCarga = date;
	}

	/**
	 * @param date
	 */
	public void setFecIngCarga(Date date) {
		fecIngCarga = date;
	}

	/**
	 * @param date
	 */
	public void setFecNacCarga(Date date) {
		fecNacCarga = date;
	}

	/**
	 * @param string
	 */
	public void setNombreCarga(String string) {
		nombreCarga = string;
	}

	/**
	 * @param string
	 */
	public void setNumCarga(String string) {
		numCarga = string;
	}

	/**
	 * @param string
	 */
	public void setRutCarga(String string) {
		rutCarga = string;
	}

	/**
	 * @param string
	 */
	public void setRutSocio(String string) {
		rutSocio = string;
	}

	/**
	 * @param string
	 */
	public void setSexoCarga(String string) {
		sexoCarga = string;
	}

	/**
	 * @param string
	 */
	public void setTipoCarga(String string) {
		tipoCarga = string;
	}

	/**
	 * @return
	 */
	public String getDvSocio() {
		return dvSocio;
	}

	/**
	 * @param string
	 */
	public void setDvSocio(String string) {
		dvSocio = string;
	}

	/**
	 * @return
	 */
	public String getNombreSocio() {
		return nombreSocio;
	}

	/**
	 * @param string
	 */
	public void setNombreSocio(String string) {
		nombreSocio = string;
	}

}
