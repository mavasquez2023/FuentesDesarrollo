package cl.araucana.ctasfam.business.to;

public class FlujoTO {
	
	public FlujoTO(){
		
	}
	
	private String periodo;
	private String rutempresa;
	private String rutencargado;
	private String etapa;
	private String operacion;
	private String ISAJKM94;
	private String ISAJKM92; 
	private int cantregistros;
	private String nombrearchivo;
	
	
	
	public String getEtapa() {
		return etapa;
	}
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getRutempresa() {
		return rutempresa;
	}
	public void setRutempresa(String rutempresa) {
		this.rutempresa = rutempresa;
	}
	public String getRutencargado() {
		return rutencargado;
	}
	public void setRutencargado(String rutencargado) {
		this.rutencargado = rutencargado;
	}
	public String getISAJKM92() {
		return ISAJKM92;
	}
	public void setISAJKM92(String isajkm92) {
		ISAJKM92 = isajkm92;
	}
	public String getISAJKM94() {
		return ISAJKM94;
	}
	public void setISAJKM94(String isajkm94) {
		ISAJKM94 = isajkm94;
	}
	public int getCantregistros() {
		return cantregistros;
	}
	public void setCantregistros(int cantregistros) {
		this.cantregistros = cantregistros;
	}
	public String getNombrearchivo() {
		return nombrearchivo;
	}
	public void setNombrearchivo(String nombrearchivo) {
		this.nombrearchivo = nombrearchivo;
	}
	
	

	
	
}
