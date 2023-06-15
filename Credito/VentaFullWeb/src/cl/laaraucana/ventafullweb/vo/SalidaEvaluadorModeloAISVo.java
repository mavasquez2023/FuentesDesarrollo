package cl.laaraucana.ventafullweb.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class SalidaEvaluadorModeloAISVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String RutAfiliado;
	private String RutEmpresa;
	private int Dictamen;
	private int MontoAprobado;
	private int PlazoAprobado;
	private int OpcionMonto1;
	private int OpcionPlazo1;
	private int OpcionMonto2;
	private int OpcionPlazo2;
	private int OpcionMonto3;
	private int OpcionPlazo3;
	private String EqEstadoCivil;
	private int EqFechaNacimient;
	private String EqNacionalidad;
	private String Politicas;
	private String ReglasNegocio;
	private String Alerta;
	private String AAPerfilRiesEmpr;
	private String AASegmento;
	private String Segmento3;
	private String VCPerfilPersona;
	private int Priorizacion;
	private int VecesRenta;
	private int Renta;
	private int MaxMonto;
	private int MontoSimulado;
	private int PlazoSimulado;
	private int Cotizacion;
	private int MaxPorcDesc;
	private int Monto;
	private int MontoCuotaSol;
	private int MontoCuotaSim;
	private int MontoCuota12;
	private int MontoCuota24;
	private int MontoCuota36;
	private int MontoCuota48;
	private int MontoCuota60;
	private int MaxDescuentoSol;
	private int MaxDescuentoSim;
	private int MaxDescuento12;
	private int MaxDescuento24;
	private int MaxDescuento36;
	private int MaxDescuento48;
	private int MaxDescuento60;
	private String AAPensionadoPBS;
	private String AASexo;
	private String CodigoError;
	
	/**
	 * @return the rutAfiliado
	 */
	public String getRutAfiliado() {
		return RutAfiliado;
	}
	/**
	 * @param rutAfiliado the rutAfiliado to set
	 */
	public void setRutAfiliado(String rutAfiliado) {
		RutAfiliado = rutAfiliado;
	}
	/**
	 * @return the rutEmpresa
	 */
	public String getRutEmpresa() {
		return RutEmpresa;
	}
	/**
	 * @param rutEmpresa the rutEmpresa to set
	 */
	public void setRutEmpresa(String rutEmpresa) {
		RutEmpresa = rutEmpresa;
	}
	/**
	 * @return the dictamen
	 */
	public int getDictamen() {
		return Dictamen;
	}
	/**
	 * @param dictamen the dictamen to set
	 */
	public void setDictamen(int dictamen) {
		Dictamen = dictamen;
	}
	/**
	 * @return the montoAprobado
	 */
	public int getMontoAprobado() {
		return MontoAprobado;
	}
	/**
	 * @param montoAprobado the montoAprobado to set
	 */
	public void setMontoAprobado(int montoAprobado) {
		MontoAprobado = montoAprobado;
	}
	/**
	 * @return the plazoAprobado
	 */
	public int getPlazoAprobado() {
		return PlazoAprobado;
	}
	/**
	 * @param plazoAprobado the plazoAprobado to set
	 */
	public void setPlazoAprobado(int plazoAprobado) {
		PlazoAprobado = plazoAprobado;
	}
	/**
	 * @return the opcionMonto1
	 */
	public int getOpcionMonto1() {
		return OpcionMonto1;
	}
	/**
	 * @param opcionMonto1 the opcionMonto1 to set
	 */
	public void setOpcionMonto1(int opcionMonto1) {
		OpcionMonto1 = opcionMonto1;
	}
	/**
	 * @return the opcionPlazo1
	 */
	public int getOpcionPlazo1() {
		return OpcionPlazo1;
	}
	/**
	 * @param opcionPlazo1 the opcionPlazo1 to set
	 */
	public void setOpcionPlazo1(int opcionPlazo1) {
		OpcionPlazo1 = opcionPlazo1;
	}
	/**
	 * @return the opcionMonto2
	 */
	public int getOpcionMonto2() {
		return OpcionMonto2;
	}
	/**
	 * @param opcionMonto2 the opcionMonto2 to set
	 */
	public void setOpcionMonto2(int opcionMonto2) {
		OpcionMonto2 = opcionMonto2;
	}
	/**
	 * @return the opcionPlazo2
	 */
	public int getOpcionPlazo2() {
		return OpcionPlazo2;
	}
	/**
	 * @param opcionPlazo2 the opcionPlazo2 to set
	 */
	public void setOpcionPlazo2(int opcionPlazo2) {
		OpcionPlazo2 = opcionPlazo2;
	}
	/**
	 * @return the opcionMonto3
	 */
	public int getOpcionMonto3() {
		return OpcionMonto3;
	}
	/**
	 * @param opcionMonto3 the opcionMonto3 to set
	 */
	public void setOpcionMonto3(int opcionMonto3) {
		OpcionMonto3 = opcionMonto3;
	}
	/**
	 * @return the opcionPlazo3
	 */
	public int getOpcionPlazo3() {
		return OpcionPlazo3;
	}
	/**
	 * @param opcionPlazo3 the opcionPlazo3 to set
	 */
	public void setOpcionPlazo3(int opcionPlazo3) {
		OpcionPlazo3 = opcionPlazo3;
	}
	/**
	 * @return the eqEstadoCivil
	 */
	public String getEqEstadoCivil() {
		return EqEstadoCivil;
	}
	/**
	 * @param eqEstadoCivil the eqEstadoCivil to set
	 */
	public void setEqEstadoCivil(String eqEstadoCivil) {
		EqEstadoCivil = eqEstadoCivil;
	}
	/**
	 * @return the eqFechaNacimient
	 */
	public int getEqFechaNacimient() {
		return EqFechaNacimient;
	}
	/**
	 * @param eqFechaNacimient the eqFechaNacimient to set
	 */
	public void setEqFechaNacimient(int eqFechaNacimient) {
		EqFechaNacimient = eqFechaNacimient;
	}
	/**
	 * @return the eqNacionalidad
	 */
	public String getEqNacionalidad() {
		return EqNacionalidad;
	}
	/**
	 * @param eqNacionalidad the eqNacionalidad to set
	 */
	public void setEqNacionalidad(String eqNacionalidad) {
		EqNacionalidad = eqNacionalidad;
	}
	/**
	 * @return the politicas
	 */
	public String getPoliticas() {
		return Politicas;
	}
	/**
	 * @param politicas the politicas to set
	 */
	public void setPoliticas(String politicas) {
		Politicas = politicas;
	}
	/**
	 * @return the reglasNegocio
	 */
	public String getReglasNegocio() {
		return ReglasNegocio;
	}
	/**
	 * @param reglasNegocio the reglasNegocio to set
	 */
	public void setReglasNegocio(String reglasNegocio) {
		ReglasNegocio = reglasNegocio;
	}
	/**
	 * @return the alerta
	 */
	public String getAlerta() {
		return Alerta;
	}
	/**
	 * @param alerta the alerta to set
	 */
	public void setAlerta(String alerta) {
		Alerta = alerta;
	}
	/**
	 * @return the aAPerfilRiesEmpr
	 */
	public String getAAPerfilRiesEmpr() {
		return AAPerfilRiesEmpr;
	}
	/**
	 * @param aAPerfilRiesEmpr the aAPerfilRiesEmpr to set
	 */
	public void setAAPerfilRiesEmpr(String aAPerfilRiesEmpr) {
		AAPerfilRiesEmpr = aAPerfilRiesEmpr;
	}
	/**
	 * @return the aASegmento
	 */
	public String getAASegmento() {
		return AASegmento;
	}
	/**
	 * @param aASegmento the aASegmento to set
	 */
	public void setAASegmento(String aASegmento) {
		AASegmento = aASegmento;
	}
	/**
	 * @return the segmento3
	 */
	public String getSegmento3() {
		return Segmento3;
	}
	/**
	 * @param segmento3 the segmento3 to set
	 */
	public void setSegmento3(String segmento3) {
		Segmento3 = segmento3;
	}
	/**
	 * @return the vCPerfilPersona
	 */
	public String getVCPerfilPersona() {
		return VCPerfilPersona;
	}
	/**
	 * @param vCPerfilPersona the vCPerfilPersona to set
	 */
	public void setVCPerfilPersona(String vCPerfilPersona) {
		VCPerfilPersona = vCPerfilPersona;
	}
	/**
	 * @return the priorizacion
	 */
	public int getPriorizacion() {
		return Priorizacion;
	}
	/**
	 * @param priorizacion the priorizacion to set
	 */
	public void setPriorizacion(int priorizacion) {
		Priorizacion = priorizacion;
	}
	/**
	 * @return the vecesRenta
	 */
	public int getVecesRenta() {
		return VecesRenta;
	}
	/**
	 * @param vecesRenta the vecesRenta to set
	 */
	public void setVecesRenta(int vecesRenta) {
		VecesRenta = vecesRenta;
	}
	/**
	 * @return the renta
	 */
	public int getRenta() {
		return Renta;
	}
	/**
	 * @param renta the renta to set
	 */
	public void setRenta(int renta) {
		Renta = renta;
	}
	/**
	 * @return the maxMonto
	 */
	public int getMaxMonto() {
		return MaxMonto;
	}
	/**
	 * @param maxMonto the maxMonto to set
	 */
	public void setMaxMonto(int maxMonto) {
		MaxMonto = maxMonto;
	}
	/**
	 * @return the montoSimulado
	 */
	public int getMontoSimulado() {
		return MontoSimulado;
	}
	/**
	 * @param montoSimulado the montoSimulado to set
	 */
	public void setMontoSimulado(int montoSimulado) {
		MontoSimulado = montoSimulado;
	}
	/**
	 * @return the plazoSimulado
	 */
	public int getPlazoSimulado() {
		return PlazoSimulado;
	}
	/**
	 * @param plazoSimulado the plazoSimulado to set
	 */
	public void setPlazoSimulado(int plazoSimulado) {
		PlazoSimulado = plazoSimulado;
	}
	/**
	 * @return the cotizacion
	 */
	public int getCotizacion() {
		return Cotizacion;
	}
	/**
	 * @param cotizacion the cotizacion to set
	 */
	public void setCotizacion(int cotizacion) {
		Cotizacion = cotizacion;
	}
	/**
	 * @return the maxPorcDesc
	 */
	public int getMaxPorcDesc() {
		return MaxPorcDesc;
	}
	/**
	 * @param maxPorcDesc the maxPorcDesc to set
	 */
	public void setMaxPorcDesc(int maxPorcDesc) {
		MaxPorcDesc = maxPorcDesc;
	}
	/**
	 * @return the monto
	 */
	public int getMonto() {
		return Monto;
	}
	/**
	 * @param monto the monto to set
	 */
	public void setMonto(int monto) {
		Monto = monto;
	}
	/**
	 * @return the montoCuotaSol
	 */
	public int getMontoCuotaSol() {
		return MontoCuotaSol;
	}
	/**
	 * @param montoCuotaSol the montoCuotaSol to set
	 */
	public void setMontoCuotaSol(int montoCuotaSol) {
		MontoCuotaSol = montoCuotaSol;
	}
	/**
	 * @return the montoCuotaSim
	 */
	public int getMontoCuotaSim() {
		return MontoCuotaSim;
	}
	/**
	 * @param montoCuotaSim the montoCuotaSim to set
	 */
	public void setMontoCuotaSim(int montoCuotaSim) {
		MontoCuotaSim = montoCuotaSim;
	}
	/**
	 * @return the montoCuota12
	 */
	public int getMontoCuota12() {
		return MontoCuota12;
	}
	/**
	 * @param montoCuota12 the montoCuota12 to set
	 */
	public void setMontoCuota12(int montoCuota12) {
		MontoCuota12 = montoCuota12;
	}
	/**
	 * @return the montoCuota24
	 */
	public int getMontoCuota24() {
		return MontoCuota24;
	}
	/**
	 * @param montoCuota24 the montoCuota24 to set
	 */
	public void setMontoCuota24(int montoCuota24) {
		MontoCuota24 = montoCuota24;
	}
	/**
	 * @return the montoCuota36
	 */
	public int getMontoCuota36() {
		return MontoCuota36;
	}
	/**
	 * @param montoCuota36 the montoCuota36 to set
	 */
	public void setMontoCuota36(int montoCuota36) {
		MontoCuota36 = montoCuota36;
	}
	/**
	 * @return the montoCuota48
	 */
	public int getMontoCuota48() {
		return MontoCuota48;
	}
	/**
	 * @param montoCuota48 the montoCuota48 to set
	 */
	public void setMontoCuota48(int montoCuota48) {
		MontoCuota48 = montoCuota48;
	}
	/**
	 * @return the montoCuota60
	 */
	public int getMontoCuota60() {
		return MontoCuota60;
	}
	/**
	 * @param montoCuota60 the montoCuota60 to set
	 */
	public void setMontoCuota60(int montoCuota60) {
		MontoCuota60 = montoCuota60;
	}
	/**
	 * @return the maxDescuentoSol
	 */
	public int getMaxDescuentoSol() {
		return MaxDescuentoSol;
	}
	/**
	 * @param maxDescuentoSol the maxDescuentoSol to set
	 */
	public void setMaxDescuentoSol(int maxDescuentoSol) {
		MaxDescuentoSol = maxDescuentoSol;
	}
	/**
	 * @return the maxDescuentoSim
	 */
	public int getMaxDescuentoSim() {
		return MaxDescuentoSim;
	}
	/**
	 * @param maxDescuentoSim the maxDescuentoSim to set
	 */
	public void setMaxDescuentoSim(int maxDescuentoSim) {
		MaxDescuentoSim = maxDescuentoSim;
	}
	/**
	 * @return the maxDescuento12
	 */
	public int getMaxDescuento12() {
		return MaxDescuento12;
	}
	/**
	 * @param maxDescuento12 the maxDescuento12 to set
	 */
	public void setMaxDescuento12(int maxDescuento12) {
		MaxDescuento12 = maxDescuento12;
	}
	/**
	 * @return the maxDescuento24
	 */
	public int getMaxDescuento24() {
		return MaxDescuento24;
	}
	/**
	 * @param maxDescuento24 the maxDescuento24 to set
	 */
	public void setMaxDescuento24(int maxDescuento24) {
		MaxDescuento24 = maxDescuento24;
	}
	/**
	 * @return the maxDescuento36
	 */
	public int getMaxDescuento36() {
		return MaxDescuento36;
	}
	/**
	 * @param maxDescuento36 the maxDescuento36 to set
	 */
	public void setMaxDescuento36(int maxDescuento36) {
		MaxDescuento36 = maxDescuento36;
	}
	/**
	 * @return the maxDescuento48
	 */
	public int getMaxDescuento48() {
		return MaxDescuento48;
	}
	/**
	 * @param maxDescuento48 the maxDescuento48 to set
	 */
	public void setMaxDescuento48(int maxDescuento48) {
		MaxDescuento48 = maxDescuento48;
	}
	/**
	 * @return the maxDescuento60
	 */
	public int getMaxDescuento60() {
		return MaxDescuento60;
	}
	/**
	 * @param maxDescuento60 the maxDescuento60 to set
	 */
	public void setMaxDescuento60(int maxDescuento60) {
		MaxDescuento60 = maxDescuento60;
	}
	/**
	 * @return the aAPensionadoPBS
	 */
	public String getAAPensionadoPBS() {
		return AAPensionadoPBS;
	}
	/**
	 * @param aAPensionadoPBS the aAPensionadoPBS to set
	 */
	public void setAAPensionadoPBS(String aAPensionadoPBS) {
		AAPensionadoPBS = aAPensionadoPBS;
	}
	/**
	 * @return the aASexo
	 */
	public String getAASexo() {
		return AASexo;
	}
	/**
	 * @param aASexo the aASexo to set
	 */
	public void setAASexo(String aASexo) {
		AASexo = aASexo;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCodigoError() {
		return CodigoError;
	}
	public void setCodigoError(String codigoError) {
		CodigoError = codigoError;
	}
	
	
	
}
