package cl.laaraucana.satelites.certificados.prepago.VO;

import java.util.List;


public class CertificadoPrepagoVO {
	
	private String folio;
	private String logo;
	private String nombreCompleto;
	private String rut;
	private String nombreEmpresa;
	private String rutEmpresa;
	private String fechaMMYY; //se ocupa 2 veces en el certificado de prepago
	List<SalidaCreditoPrepagoVO> listaCreditos;
	List<SalidaCreditoPrepagoVO> creditoSocial;
	List<SalidaCreditoPrepagoVO> creditoCes;
	List<SalidaCreditoPrepagoVO> creditoEspecial;
	private String fechaDDMM;
	private double sumaTotal;
	private String fechaCompleta; 
	private String firma;
	private String codigoValidacion;
	private String tipoAfiliado;
	private String fechaAdmisibilidad;
	
	public CertificadoPrepagoVO (){}
	
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	
	public String getFechaMMYY() {
		return fechaMMYY;
	}
	public void setFechaMMYY(String fechaMMYY) {
		this.fechaMMYY = fechaMMYY;
	}

	public List<SalidaCreditoPrepagoVO> getListaCreditos() {
		return listaCreditos;
	}

	public void setListaCreditos(List<SalidaCreditoPrepagoVO> listaCreditos) {
		this.listaCreditos = listaCreditos;
	}

	public List<SalidaCreditoPrepagoVO> getCreditoSocial() {
		return creditoSocial;
	}
	public void setCreditoSocial(List<SalidaCreditoPrepagoVO> creditoSocial) {
		this.creditoSocial = creditoSocial;
	}
	
	public List<SalidaCreditoPrepagoVO> getCreditoCes() {
		return creditoCes;
	}
	public void setCreditoCes(List<SalidaCreditoPrepagoVO> creditoCes) {
		this.creditoCes = creditoCes;
	}
	
	public List<SalidaCreditoPrepagoVO> getCreditoEspecial() {
		return creditoEspecial;
	}
	public void setCreditoEspecial(List<SalidaCreditoPrepagoVO> creditoEspecial) {
		this.creditoEspecial = creditoEspecial;
	}
	
	public String getFechaDDMM() {
		return fechaDDMM;
	}
	public void setFechaDDMM(String fechaDDMM) {
		this.fechaDDMM = fechaDDMM;
	}
	
	public double getSumaTotal() {
		return sumaTotal;
	}
	public void setSumaTotal(double sumaTotal) {
		this.sumaTotal = sumaTotal;
	}
	
	public String getFechaCompleta() {
		return fechaCompleta;
	}
	public void setFechaCompleta(String fechaCompleta) {
		this.fechaCompleta = fechaCompleta;
	}
	
	public String getFirma() {
		return firma;
	}
	public void setFirma(String firma) {
		this.firma = firma;
	}
	
	public String getCodigoValidacion() {
		return codigoValidacion;
	}
	public void setCodigoValidacion(String codigoValidacion) {
		this.codigoValidacion = codigoValidacion;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getRutEmpresa() {
		return rutEmpresa;
	}

	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	public String getTipoAfiliado() {
		return tipoAfiliado;
	}

	public void setTipoAfiliado(String tipoAfiliado) {
		this.tipoAfiliado = tipoAfiliado;
	}

	/**
	 * @return the fechaAdmisibilidad
	 */
	public String getFechaAdmisibilidad() {
		return fechaAdmisibilidad;
	}

	/**
	 * @param fechaAdmisibilidad the fechaAdmisibilidad to set
	 */
	public void setFechaAdmisibilidad(String fechaAdmisibilidad) {
		this.fechaAdmisibilidad = fechaAdmisibilidad;
	}
	
}
