package cl.araucana.cp.adminHomologacion;

public class HomologacionTO {

	public int id;
	public String tipo = "";
	public String valorCaja = "";
	public String valorDT = "";
	public String desdcripcion = "";
	
	
	public int getId() {
		return id;
	}
	public void setId(int i) {
		this.id = i;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getValorCaja() {
		return valorCaja;
	}
	public void setValorCaja(String valorCaja) {
		this.valorCaja = valorCaja;
	}
	public String getValorDT() {
		return valorDT;
	}
	public void setValorDT(String valorDT) {
		this.valorDT = valorDT;
	}
	public String getDesdcripcion() {
		return desdcripcion;
	}
	public void setDesdcripcion(String desdcripcion) {
		this.desdcripcion = desdcripcion;
	}
	
	
}
