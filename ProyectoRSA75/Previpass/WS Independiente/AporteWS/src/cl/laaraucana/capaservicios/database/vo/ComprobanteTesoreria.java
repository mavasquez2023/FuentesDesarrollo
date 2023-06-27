package cl.laaraucana.capaservicios.database.vo;

public class ComprobanteTesoreria {
	private long folioTes;
	private String tipoPago;
	private String usuario;
	
	public String getTipoPago() {
		return tipoPago;
	}
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public long getFolioTes() {
		return folioTes;
	}
	public void setFolioTes(long folioTes) {
		this.folioTes = folioTes;
	}
}
