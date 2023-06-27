package cl.araucana.wslme.integration.jaxrpc.ws;

public class Empleador {
	private String rutEmpleador;
	private String nomRazSoc;
	private Integer ultimaCotizacion;
	
	public Empleador(){}
	
	public Empleador(String rutEmpleador, String nomRazSoc, Integer ultimaCotizacion) {
		super();
		this.rutEmpleador = rutEmpleador;
		this.nomRazSoc = nomRazSoc;
		this.ultimaCotizacion = ultimaCotizacion;
	}
	public String getNomRazSoc() {
		return nomRazSoc;
	}
	public void setNomRazSoc(String nomRazSoc) {
		this.nomRazSoc = nomRazSoc;
	}
	public String getRutEmpleador() {
		return rutEmpleador;
	}
	public void setRutEmpleador(String rutEmpleador) {
		this.rutEmpleador = rutEmpleador;
	}
	public Integer getUltimaCotizacion() {
		return ultimaCotizacion;
	}
	public void setUltimaCotizacion(Integer ultimaCotizacion) {
		this.ultimaCotizacion = ultimaCotizacion;
	}
	
}
