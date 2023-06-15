package cl.laaraucana.botonpago.web.database.ibatis.domain;

public class Convenio {
	private String idConvenio;
	private String idMediopago;
	private String codigo;
	private String urlCgi;
	private String ctacte;
	private String srvrec;

	public String getIdConvenio() {
		return idConvenio;
	}

	public void setIdConvenio(String idConvenio) {
		this.idConvenio = idConvenio;
	}

	public String getIdMediopago() {
		return idMediopago;
	}

	public void setIdMediopago(String idMediopago) {
		this.idMediopago = idMediopago;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getUrlCgi() {
		return urlCgi;
	}

	public void setUrlCgi(String urlCgi) {
		this.urlCgi = urlCgi;
	}

	public String getCtacte() {
		return ctacte;
	}

	public void setCtacte(String ctacte) {
		this.ctacte = ctacte;
	}

	public String getSrvrec() {
		return srvrec;
	}

	public void setSrvrec(String srvrec) {
		this.srvrec = srvrec;
	}
}