package cl.araucana.ctasfam.business.to;

public class ErroresTO {
	
	 //--alexis advise 15-06-2012--//
	public ErroresTO(){
		
	}
	
	private int par;
	private String codigo=null;
	private String descripcion=null;
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getPar() {
		return par;
	}
	public void setPar(int par) {
		this.par = par;
	}
	 
	
	
	
}
