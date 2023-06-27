package cl.araucana.cp.distribuidor.presentation.beans;

public class DTOcomprobanteData {
	private String fecha = null;
	private String proceso = null;
	private String rut = null;
	private String rsocial = null;
	private String convenio = null;
	private String code = null;
	
	
	public DTOcomprobanteData() {
		super();
		// TODO Apéndice de constructor generado automáticamente
	}

	public DTOcomprobanteData(String fecha, String proceso, String rut, String rsocial, String convenio, String code) {
		super();
		this.fecha = fecha;
		this.proceso = proceso;
		this.rut = rut;
		this.rsocial = rsocial;
		this.convenio = convenio;
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getConvenio() {
		return convenio;
	}
	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getProceso() {
		return proceso;
	}
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}
	public String getRsocial() {
		return rsocial;
	}
	public void setRsocial(String rsocial) {
		this.rsocial = rsocial;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	
	
}
