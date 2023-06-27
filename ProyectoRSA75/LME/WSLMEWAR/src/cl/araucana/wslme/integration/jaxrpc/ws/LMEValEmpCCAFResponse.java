package cl.araucana.wslme.integration.jaxrpc.ws;


public class LMEValEmpCCAFResponse {
	private Short estado;
	private String gloEstado;
	private Empleador [] listaEmpleadores;
	
	public LMEValEmpCCAFResponse(){}
	
	public LMEValEmpCCAFResponse(Short estado, String gloEstado, Empleador[] listaEmpleadores) {
		super();
		this.estado = estado;
		this.gloEstado = gloEstado;
		this.listaEmpleadores = listaEmpleadores;
	}
	public Short getEstado() {
		return estado;
	}
	public void setEstado(Short estado) {
		this.estado = estado;
	}
	public String getGloEstado() {
		return gloEstado;
	}
	public void setGloEstado(String gloEstado) {
		this.gloEstado = gloEstado;
	}
	public Empleador[] getListaEmpleadores() {
		return listaEmpleadores;
	}
	public void setListaEmpleadores(Empleador[] listaEmpleadores) {
		this.listaEmpleadores = listaEmpleadores;
	}
}
