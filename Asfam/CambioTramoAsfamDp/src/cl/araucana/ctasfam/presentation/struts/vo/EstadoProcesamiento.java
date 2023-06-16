package cl.araucana.ctasfam.presentation.struts.vo;

public class EstadoProcesamiento {
	private Empresa empresa;
	private String estado;
	private int totalProcesados;
	
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
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
	
	public String getRutFormateado(){
		return empresa.getFormattedRut();
	}
	public String getEmpresaNombre(){
		return empresa.getName();
	}
	public String getTotalProcesadosStr(){
		return String.valueOf(totalProcesados);
	}
	public String getRut(){
		return String.valueOf(empresa.getRut());
	}
}
