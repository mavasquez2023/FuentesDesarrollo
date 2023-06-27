/*
 * Created on 11-10-2011
 *
 */
package com.microsystem.lme.ibatis.domain;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * @author microsystem
 *
 */
public class Ilfe031VO implements Serializable {

	/**
	 * 
	 */
	protected static final long serialVersionUID = -9162147821451945705L;
	protected Short ideOpe;
	protected String codOpe;
	protected String urlOpe;
	protected String codCcaf;
	protected String pwdCcaf;
	protected Integer afiRut;
	protected Long numImpre;
	protected String numImpdv;
	protected String empNombre; //C1EMPNOM
	protected Integer c1empRut;
	protected String c1emprutDv;
	protected String empFechaRecepcion;//C1EMPFECRE

	protected String empDireccionCalle;//C1EMPDIR;
	protected Integer codigoComunaCompin; //C1COCOCP
	protected Integer codigoActividadLaboral;// C1COACLA;
	protected Integer codigoOcupacion;//C1COOCUP
	protected String empOtraOcupacion;//C1EMOTOC
	protected Integer prevFechaRecepcionCcaf;//C2FERECCAF

	protected Integer codigoRegimenPrevisional;//C2COREPREV
	protected String codigoLetraCaja;//C2COLETCAJ
	protected String prevNombre;//C2PREVNOM
	protected Integer codigoCalidadTrabajador;//C2COCAAFIL
	protected Integer codigoSeguroAfc;//C2SEGAFC
	protected Integer codigoSeguroIndef;//C2COSEINDE
	protected String fechaAfiliacion; //C2FECFILIA
	protected String fechaContrato;//C2FECCONTR
	protected String codigoEntidadPagadora;//C2CODENTPA
	protected String prevNombrePagador;//C2PRENOMAF
	protected BigDecimal porcenDesahucio;//C3PORCDES
	protected Integer montoImponibleMesAnterior;//C3MTOIMPO
	protected String lmaLicenciasAnt;//C4LICANT
	protected Integer codigoTramitacionCCAF;//C5COTRCCAF
	protected String tieneMas100;//C5EMP100
	protected String enviada;
	protected String fechaProceso;//FECPROCE
	protected String respWs;
	protected String marca;
	protected Integer codigoTipoRegimenPrevisional;//C2CODTIPRE
	protected String empDireccionCiudad;//C1EMPCIU
	protected String empDireccionPais;//C1EMPPAI
	protected String empDireccionComuna;//C1EMPCOM

	protected String glosaEstado;//GLORESP
	protected String fechaConsulta;//FECRESP
	protected String hora; //HORRESP

	/**
	 * @return Returns the afiRut.
	 */
	public Integer getAfiRut() {
		return afiRut;
	}

	/**
	 * @param afiRut The afiRut to set.
	 */
	public void setAfiRut(Integer afiRut) {
		this.afiRut = afiRut;
	}

	/**
	 * @return Returns the c1empFecre.
	 */
	public String getEmpFechaRecepcion() {
		return empFechaRecepcion;
	}

	/**
	 * @param fecre The c1empFecre to set.
	 */
	public void setEmpFechaRecepcion(String fecre) {
		empFechaRecepcion = fecre;
	}

	/**
	 * @return Returns the c1empNom.
	 */
	public String getEmpNombre() {
		return empNombre;
	}

	/**
	 * @param nom The c1empNom to set.
	 */
	public void setEmpNombre(String nom) {
		empNombre = nom;
	}

	/**
	 * @return Returns the c1empRut.
	 */
	public Integer getC1empRut() {
		return c1empRut;
	}

	/**
	 * @param rut The c1empRut to set.
	 */
	public void setC1empRut(Integer rut) {
		c1empRut = rut;
	}

	/**
	 * @return Returns the c1emprutDv.
	 */
	public String getC1emprutDv() {
		return c1emprutDv;
	}

	/**
	 * @param dv The c1emprutDv to set.
	 */
	public void setC1emprutDv(String dv) {
		c1emprutDv = dv;
	}

	/**
	 * @return Returns the codCcaf.
	 */
	public String getCodCcaf() {
		return codCcaf;
	}

	/**
	 * @param codCcaf The codCcaf to set.
	 */
	public void setCodCcaf(String codCcaf) {
		this.codCcaf = codCcaf;
	}

	/**
	 * @return Returns the codigoActividadLaboral.
	 */
	public Integer getCodigoActividadLaboral() {
		return codigoActividadLaboral;
	}

	/**
	 * @param codigoActividadLaboral The codigoActividadLaboral to set.
	 */
	public void setCodigoActividadLaboral(Integer codigoActividadLaboral) {
		this.codigoActividadLaboral = codigoActividadLaboral;
	}

	/**
	 * @return Returns the codigoCalidadTrabajador.
	 */
	public Integer getCodigoCalidadTrabajador() {
		return codigoCalidadTrabajador;
	}

	/**
	 * @param codigoCalidadTrabajador The codigoCalidadTrabajador to set.
	 */
	public void setCodigoCalidadTrabajador(Integer codigoCalidadTrabajador) {
		this.codigoCalidadTrabajador = codigoCalidadTrabajador;
	}

	/**
	 * @return Returns the codigoComunaCompin.
	 */
	public Integer getCodigoComunaCompin() {
		return codigoComunaCompin;
	}

	/**
	 * @param codigoComunaCompin The codigoComunaCompin to set.
	 */
	public void setCodigoComunaCompin(Integer codigoComunaCompin) {
		this.codigoComunaCompin = codigoComunaCompin;
	}

	/**
	 * @return Returns the codigoEntidadPagadora.
	 */
	public String getCodigoEntidadPagadora() {
		return codigoEntidadPagadora;
	}

	/**
	 * @param codigoEntidadPagadora The codigoEntidadPagadora to set.
	 */
	public void setCodigoEntidadPagadora(String codigoEntidadPagadora) {
		this.codigoEntidadPagadora = codigoEntidadPagadora;
	}

	/**
	 * @return Returns the codigoLetraCaja.
	 */
	public String getCodigoLetraCaja() {
		return codigoLetraCaja;
	}

	/**
	 * @param codigoLetraCaja The codigoLetraCaja to set.
	 */
	public void setCodigoLetraCaja(String codigoLetraCaja) {
		this.codigoLetraCaja = codigoLetraCaja;
	}

	/**
	 * @return Returns the codigoOcupacion.
	 */
	public Integer getCodigoOcupacion() {
		return codigoOcupacion;
	}

	/**
	 * @param codigoOcupacion The codigoOcupacion to set.
	 */
	public void setCodigoOcupacion(Integer codigoOcupacion) {
		this.codigoOcupacion = codigoOcupacion;
	}

	/**
	 * @return Returns the codigoRegimenPrevisional.
	 */
	public Integer getCodigoRegimenPrevisional() {
		return codigoRegimenPrevisional;
	}

	/**
	 * @param codigoRegimenPrevisional The codigoRegimenPrevisional to set.
	 */
	public void setCodigoRegimenPrevisional(Integer codigoRegimenPrevisional) {
		this.codigoRegimenPrevisional = codigoRegimenPrevisional;
	}

	/**
	 * @return Returns the codigoSeguroAfc.
	 */
	public Integer getCodigoSeguroAfc() {
		return codigoSeguroAfc;
	}

	/**
	 * @param codigoSeguroAfc The codigoSeguroAfc to set.
	 */
	public void setCodigoSeguroAfc(Integer codigoSeguroAfc) {
		this.codigoSeguroAfc = codigoSeguroAfc;
	}

	/**
	 * @return Returns the codigoSeguroIndef.
	 */
	public Integer getCodigoSeguroIndef() {
		return codigoSeguroIndef;
	}

	/**
	 * @param codigoSeguroIndef The codigoSeguroIndef to set.
	 */
	public void setCodigoSeguroIndef(Integer codigoSeguroIndef) {
		this.codigoSeguroIndef = codigoSeguroIndef;
	}

	/**
	 * @return Returns the codigoTipoRegimenPrevisional.
	 */
	public Integer getCodigoTipoRegimenPrevisional() {
		return codigoTipoRegimenPrevisional;
	}

	/**
	 * @param codigoTipoRegimenPrevisional The codigoTipoRegimenPrevisional to set.
	 */
	public void setCodigoTipoRegimenPrevisional(Integer codigoTipoRegimenPrevisional) {
		this.codigoTipoRegimenPrevisional = codigoTipoRegimenPrevisional;
	}

	/**
	 * @return Returns the codigoTramitacionCCAF.
	 */
	public Integer getCodigoTramitacionCCAF() {
		return codigoTramitacionCCAF;
	}

	/**
	 * @param codigoTramitacionCCAF The codigoTramitacionCCAF to set.
	 */
	public void setCodigoTramitacionCCAF(Integer codigoTramitacionCCAF) {
		this.codigoTramitacionCCAF = codigoTramitacionCCAF;
	}

	/**
	 * @return Returns the codOpe.
	 */
	public String getCodOpe() {
		return codOpe;
	}

	/**
	 * @param codOpe The codOpe to set.
	 */
	public void setCodOpe(String codOpe) {
		this.codOpe = codOpe;
	}

	/**
	 * @return Returns the empDireccionCalle.
	 */
	public String getEmpDireccionCalle() {
		return empDireccionCalle;
	}

	/**
	 * @param empDireccionCalle The empDireccionCalle to set.
	 */
	public void setEmpDireccionCalle(String empDireccionCalle) {
		this.empDireccionCalle = empDireccionCalle;
	}

	/**
	 * @return Returns the empDireccionCiudad.
	 */
	public String getEmpDireccionCiudad() {
		return empDireccionCiudad;
	}

	/**
	 * @param empDireccionCiudad The empDireccionCiudad to set.
	 */
	public void setEmpDireccionCiudad(String empDireccionCiudad) {
		this.empDireccionCiudad = empDireccionCiudad;
	}

	/**
	 * @return Returns the empDireccionComuna.
	 */
	public String getEmpDireccionComuna() {
		return empDireccionComuna;
	}

	/**
	 * @param empDireccionComuna The empDireccionComuna to set.
	 */
	public void setEmpDireccionComuna(String empDireccionComuna) {
		this.empDireccionComuna = empDireccionComuna;
	}

	/**
	 * @return Returns the empDireccionPais.
	 */
	public String getEmpDireccionPais() {
		return empDireccionPais;
	}

	/**
	 * @param empDireccionPais The empDireccionPais to set.
	 */
	public void setEmpDireccionPais(String empDireccionPais) {
		this.empDireccionPais = empDireccionPais;
	}

	/**
	 * @return Returns the empOtraOcupacion.
	 */
	public String getEmpOtraOcupacion() {
		return empOtraOcupacion;
	}

	/**
	 * @param empOtraOcupacion The empOtraOcupacion to set.
	 */
	public void setEmpOtraOcupacion(String empOtraOcupacion) {
		this.empOtraOcupacion = empOtraOcupacion;
	}

	/**
	 * @return Returns the enviada.
	 */
	public String getEnviada() {
		return enviada;
	}

	/**
	 * @param enviada The enviada to set.
	 */
	public void setEnviada(String enviada) {
		this.enviada = enviada;
	}

	/**
	 * @return Returns the fechaAfiliacion.
	 */
	public String getFechaAfiliacion() {
		return fechaAfiliacion;
	}

	/**
	 * @param fechaAfiliacion The fechaAfiliacion to set.
	 */
	public void setFechaAfiliacion(String fechaAfiliacion) {
		this.fechaAfiliacion = fechaAfiliacion;
	}

	/**
	 * @return Returns the fechaContrato.
	 */
	public String getFechaContrato() {
		return fechaContrato;
	}

	/**
	 * @param fechaContrato The fechaContrato to set.
	 */
	public void setFechaContrato(String fechaContrato) {
		this.fechaContrato = fechaContrato;
	}

	/**
	 * @return Returns the fechaProceso.
	 */
	public String getFechaProceso() {
		return fechaProceso;
	}

	/**
	 * @param fechaProceso The fechaProceso to set.
	 */
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	/**
	 * @return Returns the ideOpe.
	 */
	public Short getIdeOpe() {
		return ideOpe;
	}

	/**
	 * @param ideOpe The ideOpe to set.
	 */
	public void setIdeOpe(Short ideOpe) {
		this.ideOpe = ideOpe;
	}

	/**
	 * @return Returns the lmaLicenciasAnt.
	 */
	public String getLmaLicenciasAnt() {
		return lmaLicenciasAnt;
	}

	/**
	 * @param lmaLicenciasAnt The lmaLicenciasAnt to set.
	 */
	public void setLmaLicenciasAnt(String lmaLicenciasAnt) {
		this.lmaLicenciasAnt = lmaLicenciasAnt;
	}

	/**
	 * @return Returns the marca.
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca The marca to set.
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return Returns the montoImponibleMesAnterior.
	 */
	public Integer getMontoImponibleMesAnterior() {
		return montoImponibleMesAnterior;
	}

	/**
	 * @param montoImponibleMesAnterior The montoImponibleMesAnterior to set.
	 */
	public void setMontoImponibleMesAnterior(Integer montoImponibleMesAnterior) {
		this.montoImponibleMesAnterior = montoImponibleMesAnterior;
	}

	/**
	 * @return Returns the numImpdv.
	 */
	public String getNumImpdv() {
		return numImpdv;
	}

	/**
	 * @param numImpdv The numImpdv to set.
	 */
	public void setNumImpdv(String numImpdv) {
		this.numImpdv = numImpdv;
	}

	/**
	 * @return Returns the numImpre.
	 */
	public Long getNumImpre() {
		return numImpre;
	}

	/**
	 * @param numImpre The numImpre to set.
	 */
	public void setNumImpre(Long numImpre) {
		this.numImpre = numImpre;
	}

	/**
	 * @return Returns the porcenDesahucio.
	 */
	public BigDecimal getPorcenDesahucio() {
		return porcenDesahucio;
	}

	/**
	 * @param porcenDesahucio The porcenDesahucio to set.
	 */
	public void setPorcenDesahucio(BigDecimal porcenDesahucio) {
		this.porcenDesahucio = porcenDesahucio;
	}

	/**
	 * @return Returns the prevFechaRecepcionCcaf.
	 */
	public Integer getPrevFechaRecepcionCcaf() {
		return prevFechaRecepcionCcaf;
	}

	/**
	 * @param prevFechaRecepcionCcaf The prevFechaRecepcionCcaf to set.
	 */
	public void setPrevFechaRecepcionCcaf(Integer prevFechaRecepcionCcaf) {
		this.prevFechaRecepcionCcaf = prevFechaRecepcionCcaf;
	}

	/**
	 * @return Returns the prevNombre.
	 */
	public String getPrevNombre() {
		return prevNombre;
	}

	/**
	 * @param prevNombre The prevNombre to set.
	 */
	public void setPrevNombre(String prevNombre) {
		this.prevNombre = prevNombre;
	}

	/**
	 * @return Returns the prevNombrePagador.
	 */
	public String getPrevNombrePagador() {
		return prevNombrePagador;
	}

	/**
	 * @param prevNombrePagador The prevNombrePagador to set.
	 */
	public void setPrevNombrePagador(String prevNombrePagador) {
		this.prevNombrePagador = prevNombrePagador;
	}

	/**
	 * @return Returns the pwdCcaf.
	 */
	public String getPwdCcaf() {
		return pwdCcaf;
	}

	/**
	 * @param pwdCcaf The pwdCcaf to set.
	 */
	public void setPwdCcaf(String pwdCcaf) {
		this.pwdCcaf = pwdCcaf;
	}

	/**
	 * @return Returns the respWs.
	 */
	public String getRespWs() {
		return respWs;
	}

	/**
	 * @param respWs The respWs to set.
	 */
	public void setRespWs(String respWs) {
		this.respWs = respWs;
	}

	/**
	 * @return Returns the tieneMas100.
	 */
	public String getTieneMas100() {
		return tieneMas100;
	}

	/**
	 * @param tieneMas100 The tieneMas100 to set.
	 */
	public void setTieneMas100(String tieneMas100) {
		this.tieneMas100 = tieneMas100;
	}

	/**
	 * @return Returns the urlOpe.
	 */
	public String getUrlOpe() {
		return urlOpe;
	}

	/**
	 * @param urlOpe The urlOpe to set.
	 */
	public void setUrlOpe(String urlOpe) {
		this.urlOpe = urlOpe;
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

	/**
	 * @return Returns the glosaEstado.
	 */
	public String getGlosaEstado() {
		return glosaEstado;
	}

	/**
	 * @param glosaEstado The glosaEstado to set.
	 */
	public void setGlosaEstado(String glosaEstado) {
		this.glosaEstado = glosaEstado;
	}

	/**
	 * @return Returns the fechaConsulta.
	 */
	public String getFechaConsulta() {
		return fechaConsulta;
	}

	/**
	 * @param fechaConsulta The fechaConsulta to set.
	 */
	public void setFechaConsulta(String fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

	/**
	 * @return Returns the hora.
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * @param hora The hora to set.
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}
}
