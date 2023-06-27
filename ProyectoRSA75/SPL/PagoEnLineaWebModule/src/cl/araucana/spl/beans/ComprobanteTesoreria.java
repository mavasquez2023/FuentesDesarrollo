package cl.araucana.spl.beans;

public class ComprobanteTesoreria {
	private String folioTes;
	private String tipoPago;
	private String usuario;
	private String estMovimiento;
	private int cantActualizados;
	
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
	public String getFolioTes() {
		return folioTes;
	}
	public void setFolioTes(String folioTes) {
		this.folioTes = folioTes;
	}
	public String getEstMovimiento() {
		return estMovimiento;
	}
	public void setEstMovimiento(String estMovimiento) {
		this.estMovimiento = estMovimiento;
	}
	public int getCantActualizados() {
		return cantActualizados;
	}
	public void setCantActualizados(int cantActualizados) {
		this.cantActualizados = cantActualizados;
	}
}
