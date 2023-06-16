package cl.araucana.ctasfam.business.to;

public class DetalleExcelTO {
	
	private int rut;
	private String nombre;
	private String oficina;
	private String sucursal;
	private int total;
	private String estado;
	
	public int getRut() {
		return rut;
	}
	public void setRut(int rut) {
		this.rut = rut;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getOficina() {
		return oficina;
	}
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getDescEstado(){
		if("F".equalsIgnoreCase(estado)){
			return "Procesada";
		}else{
			return "En proceso";
		}
	}

}
