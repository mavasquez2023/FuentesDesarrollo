package cl.araucana.ctasfam.business.to;

public class ResumenExcelTO {
	
	private String estado;
	private int totalProcesados;
	private int cantidadEmpresas;
	
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getTotalProcesados() {
		return totalProcesados;
	}
	public void setTotalProcesados(int totalProcesados) {
		this.totalProcesados = totalProcesados;
	}
	public int getCantidadEmpresas() {
		return cantidadEmpresas;
	}
	public void setCantidadEmpresas(int cantidadEmpresas) {
		this.cantidadEmpresas = cantidadEmpresas;
	}

}
