/*
 * Created on 24-10-2011
 *
 */
package cl.araucana.lme.ibatis.domain;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * @author j-factory
 *
 */
public class Ilfe002VO implements Serializable {

	/**
	 * 
	 */
	protected Integer ideOpe; //IDEOPE
	protected Integer numImpre; //NUMIMPRE
	protected String dvImpre; //DVIMPRE
	protected Integer estadoLicencia; //ESTLICEN
	protected Integer codError; //CODERR
	protected Integer afiRut;
	protected String afiRutDv;
	protected Integer fechaOpr;
	protected String msgErr;
	protected Integer numimprela;
	protected String fechaEstado;
	protected String horaEstado;
	protected Integer tipoLicencia;
	protected String afiNom;
	protected String fechaInicio;
	protected Integer recuperabilidad;
	protected Integer inicioTramiteInvalidez;
	protected String jornadaReposo;
	protected String justificarOtro;
	protected Integer licperiodo1;
	protected Integer licperiodo2;
	protected Integer licperiodo3;
	protected Integer licperiodo4;
	protected Integer licperiodo5;
	protected Integer licperiodo6;
	protected Integer rentaImponible60;
	protected Integer rentaImponible90;
	
	/**
	 * @return el fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	public String getPeriodoLicencia() {
		if(fechaInicio.length()>6){
			return fechaInicio.substring(0, 6);
		}
		return fechaInicio;
	}

	
	/**
	 * @param fechaInicio el fechaInicio a establecer
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return el nombreAfiliado
	 */
	public String getAfiNom() {
		return afiNom;
	}

	/**
	 * @param nombreAfiliado el nombreAfiliado a establecer
	 */
	public void setAfiNom(String afiNom) {
		this.afiNom = afiNom;
	}

	/**
	 * @return el tipoLicencia
	 */
	public Integer getTipoLicencia() {
		return tipoLicencia;
	}

	/**
	 * @param tipoLicencia el tipoLicencia a establecer
	 */
	public void setTipoLicencia(Integer tipoLicencia) {
		this.tipoLicencia = tipoLicencia;
	}

	public String getFechaEstado() {
		return fechaEstado;
	}

	public String getHoraEstado() {
		return horaEstado;
	}

	public void setFechaEstado(String fechaEstado) {
		this.fechaEstado = fechaEstado;
	}

	public void setHoraEstado(String horaEstado) {
		this.horaEstado = horaEstado;
	}

	public Integer getNumimprela() {
		return numimprela;
	}

	public void setNumimprela(Integer numimprela) {
		this.numimprela = numimprela;
	}

	public Integer getFechaOpr() {
		return fechaOpr;
	}

	public void setFechaOpr(Integer fechaOpr) {
		this.fechaOpr = fechaOpr;
	}

	public String getMsgErr() {
		return msgErr;
	}

	public void setMsgErr(String msgErr) {
		this.msgErr = msgErr;
	}

	public Integer getAfiRut() {
		return afiRut;
	}

	public void setAfiRut(Integer afiRut) {
		this.afiRut = afiRut;
	}
	
	/**
	 * @return el afiRutDv
	 */
	public String getAfiRutDv() {
		return afiRutDv;
	}

	/**
	 * @param afiRutDv el afiRutDv a establecer
	 */
	public void setAfiRutDv(String afiRutDv) {
		this.afiRutDv = afiRutDv;
	}

	/**
	 * @return Returns the codError.
	 */
	public Integer getCodError() {
		return codError;
	}

	/**
	 * @param codError The codError to set.
	 */
	public void setCodError(Integer codError) {
		this.codError = codError;
	}

	/**
	 * @return Returns the estadoLicencia.
	 */
	public Integer getEstadoLicencia() {
		return estadoLicencia;
	}

	/**
	 * @param estadoLicencia The estadoLicencia to set.
	 */
	public void setEstadoLicencia(Integer estadoLicencia) {
		this.estadoLicencia = estadoLicencia;
	}

	/**
	 * @return Returns the ideOpe.
	 */
	public Integer getIdeOpe() {
		return ideOpe;
	}

	/**
	 * @param ideOpe The ideOpe to set.
	 */
	public void setIdeOpe(Integer ideOpe) {
		this.ideOpe = ideOpe;
	}

	/**
	 * @return Returns the numImpre.
	 */
	public Integer getNumImpre() {
		return numImpre;
	}

	/**
	 * @param numImpre The numImpre to set.
	 */
	public void setNumImpre(Integer numImpre) {
		this.numImpre = numImpre;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		try {
			Class c = Class.forName(this.getClass().getName());
			Field[] f = c.getDeclaredFields();
			for (int i = 0; i < f.length; i++)
				sb.append(f[i].getName() + "=").append(f[i].get(this)).append("\n");

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public String getDvImpre() {
		return dvImpre;
	}

	public void setDvImpre(String dvImpre) {
		this.dvImpre = dvImpre;
	}

	/**
	 * @return el inicioTramiteInvalidez
	 */
	public Integer getInicioTramiteInvalidez() {
		return inicioTramiteInvalidez;
	}

	/**
	 * @param inicioTramiteInvalidez el inicioTramiteInvalidez a establecer
	 */
	public void setInicioTramiteInvalidez(Integer inicioTramiteInvalidez) {
		this.inicioTramiteInvalidez = inicioTramiteInvalidez;
	}

	/**
	 * @return el jornadaReposo
	 */
	public String getJornadaReposo() {
		return jornadaReposo;
	}

	/**
	 * @param jornadaReposo el jornadaReposo a establecer
	 */
	public void setJornadaReposo(String jornadaReposo) {
		this.jornadaReposo = jornadaReposo;
	}

	/**
	 * @return el recuperabilidad
	 */
	public Integer getRecuperabilidad() {
		return recuperabilidad;
	}

	/**
	 * @param recuperabilidad el recuperabilidad a establecer
	 */
	public void setRecuperabilidad(Integer recuperabilidad) {
		this.recuperabilidad = recuperabilidad;
	}

	/**
	 * @return el justificarOtro
	 */
	public String getJustificarOtro() {
		return justificarOtro;
	}

	/**
	 * @param justificarOtro el justificarOtro a establecer
	 */
	public void setJustificarOtro(String justificarOtro) {
		this.justificarOtro = justificarOtro;
	}

	/**
	 * @return el licperiodo1
	 */
	public Integer getLicperiodo1() {
		return licperiodo1;
	}

	/**
	 * @param licperiodo1 el licperiodo1 a establecer
	 */
	public void setLicperiodo1(Integer licperiodo1) {
		this.licperiodo1 = licperiodo1;
	}

	/**
	 * @return el licperiodo2
	 */
	public Integer getLicperiodo2() {
		return licperiodo2;
	}

	/**
	 * @param licperiodo2 el licperiodo2 a establecer
	 */
	public void setLicperiodo2(Integer licperiodo2) {
		this.licperiodo2 = licperiodo2;
	}

	/**
	 * @return el licperiodo3
	 */
	public Integer getLicperiodo3() {
		return licperiodo3;
	}

	/**
	 * @param licperiodo3 el licperiodo3 a establecer
	 */
	public void setLicperiodo3(Integer licperiodo3) {
		this.licperiodo3 = licperiodo3;
	}

	/**
	 * @return el licperiodo4
	 */
	public Integer getLicperiodo4() {
		return licperiodo4;
	}

	/**
	 * @param licperiodo4 el licperiodo4 a establecer
	 */
	public void setLicperiodo4(Integer licperiodo4) {
		this.licperiodo4 = licperiodo4;
	}

	/**
	 * @return el licperiodo5
	 */
	public Integer getLicperiodo5() {
		return licperiodo5;
	}

	/**
	 * @param licperiodo5 el licperiodo5 a establecer
	 */
	public void setLicperiodo5(Integer licperiodo5) {
		this.licperiodo5 = licperiodo5;
	}

	/**
	 * @return el licperiodo6
	 */
	public Integer getLicperiodo6() {
		return licperiodo6;
	}

	/**
	 * @param licperiodo6 el licperiodo6 a establecer
	 */
	public void setLicperiodo6(Integer licperiodo6) {
		this.licperiodo6 = licperiodo6;
	}

	/**
	 * @return el rentaImponible60
	 */
	public Integer getRentaImponible60() {
		return rentaImponible60;
	}

	/**
	 * @param rentaImponible60 el rentaImponible60 a establecer
	 */
	public void setRentaImponible60(Integer rentaImponible60) {
		this.rentaImponible60 = rentaImponible60;
	}

	/**
	 * @return el rentaImponible90
	 */
	public Integer getRentaImponible90() {
		return rentaImponible90;
	}

	/**
	 * @param rentaImponible90 el rentaImponible90 a establecer
	 */
	public void setRentaImponible90(Integer rentaImponible90) {
		this.rentaImponible90 = rentaImponible90;
	}

	

	}
