package cl.araucana.ctasfam.business.to;

public class AfiliadosErrorTO {
	
	 //--alexis advise 15-06-2012--//
	
	public AfiliadosErrorTO(){
		
	}
	
	private String codigoerror=null;
	private String nombrearchivo=null;
	private String numerolinea=null;
	private String numeroColumna = null;
	private String ruttrabajador=null;
	private String descripcionerror=null;
	private String par;
	 
	
	
	
	public String getCodigoerror() {
		return codigoerror;
	}
	public void setCodigoerror(String codigoerror) {
		this.codigoerror = codigoerror;
	}
	public String getNumeroColumna() {
		return numeroColumna;
	}
	public void setNumeroColumna(String numeroColumna) {
		this.numeroColumna = numeroColumna;
	}
	public String getDescripcionerror() {
		return descripcionerror;
	}
	public void setDescripcionerror(String descripcionerror) {
		this.descripcionerror = descripcionerror;
	}
	public String getNombrearchivo() {
		return nombrearchivo;
	}
	public void setNombrearchivo(String nombrearchivo) {
		this.nombrearchivo = nombrearchivo;
	}
	public String getNumerolinea() {
		return numerolinea;
	}
	public void setNumerolinea(String numerolinea) {
		this.numerolinea = numerolinea;
	}
	public String getRuttrabajador() {
		return ruttrabajador;
	}
	public void setRuttrabajador(String ruttrabajador) {
		this.ruttrabajador = ruttrabajador;
	}
	public String getPar() {
		return par;
	}
	public void setPar(String par) {
		this.par = par;
	}
	 
	
	
	
	 
	

}
