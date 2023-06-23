package cl.araucana.spl.beans.xmlbsa;

public class DetallePagos {
	private String idCarro;
	private String idConvenio;
	private String numeroProducto;
	private String numeroCliente;
	private String expiracionProducto;
	private String descProducto;
	private String montoProducto;
	private String idAtributo;
	private String numeroOperacion;
	private String fechaHoraOperacion;
	
	public String toString() {
		return new StringBuffer("[DetallePagos::idCarro=").append(idCarro)
			.append(",idConvenio=").append(idConvenio)
			.append(",numeroProducto=").append(numeroProducto)
			.append(",numeroCliente=").append(numeroCliente)
			.append(",expiracionProducto=").append(expiracionProducto)
			.append(",descProducto=").append(descProducto)
			.append(",montoProducto=").append(montoProducto)
			.append(",idAtributo=").append(idAtributo)
			.append(",numeroOperacion=").append(numeroOperacion)			
			.append(",fechaHoraOperacion=").append(fechaHoraOperacion)
			.append("]").toString();
	}	
	
	
	public String getDescProducto() {
		return descProducto;
	}
	public void setDescProducto(String descProducto) {
		this.descProducto = descProducto;
	}
	public String getExpiracionProducto() {
		return expiracionProducto;
	}
	public void setExpiracionProducto(String expiracionProducto) {
		this.expiracionProducto = expiracionProducto;
	}
	public String getFechaHoraOperacion() {
		return fechaHoraOperacion;
	}
	public void setFechaHoraOperacion(String fechaHoraOperacion) {
		this.fechaHoraOperacion = fechaHoraOperacion;
	}
	public String getIdAtributo() {
		return idAtributo;
	}
	public void setIdAtributo(String idAtributo) {
		this.idAtributo = idAtributo;
	}
	public String getIdCarro() {
		return idCarro;
	}
	public void setIdCarro(String idCarro) {
		this.idCarro = idCarro;
	}
	public String getIdConvenio() {
		return idConvenio;
	}
	public void setIdConvenio(String idConvenio) {
		this.idConvenio = idConvenio;
	}
	public String getMontoProducto() {
		return montoProducto;
	}
	public void setMontoProducto(String montoProducto) {
		this.montoProducto = montoProducto;
	}
	public String getNumeroCliente() {
		return numeroCliente;
	}
	public void setNumeroCliente(String numeroCliente) {
		this.numeroCliente = numeroCliente;
	}
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	public String getNumeroProducto() {
		return numeroProducto;
	}
	public void setNumeroProducto(String numeroProducto) {
		this.numeroProducto = numeroProducto;
	}

}
