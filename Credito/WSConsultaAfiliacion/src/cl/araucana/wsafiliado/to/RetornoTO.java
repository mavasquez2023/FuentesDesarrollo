package cl.araucana.wsafiliado.to;

public class RetornoTO {

	public RetornoTO() {

	}

	private String id;
	private String codigo="";
	private String mensaje;
	private String codigoxml;
	private String idtupla;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoxml() {
		return codigoxml;
	}

	public void setCodigoxml(String codigoxml) {
		this.codigoxml = codigoxml;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the idtupla
	 */
	public String getIdtupla() {
		return idtupla;
	}

	/**
	 * @param idtupla the idtupla to set
	 */
	public void setIdtupla(String idtupla) {
		this.idtupla = idtupla;
	}
	
}
