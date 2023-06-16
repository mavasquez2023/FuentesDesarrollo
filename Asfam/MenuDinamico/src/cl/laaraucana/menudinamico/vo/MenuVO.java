package cl.laaraucana.menudinamico.vo;

public class MenuVO {
    
	private long codMenu;
    private long nodoPadre;
    private String flgHoja;
    private String enlace;
    private String etiqueta;    
    private long nivel;
    private String descripcion;
    private String seguridad;
    private String linkInterno;
    
    public String toString(){
    	return String.format("codmenu:%d, nodoPadre:%d, flgHoja:%s, etiqueta:%s, nivel:%d, desc:%s, seguridad:%s, linkInterno:%s", codMenu, nodoPadre, flgHoja, etiqueta, nivel, descripcion, seguridad, linkInterno);
    }
	
	public MenuVO(){
		
	}
			
	public MenuVO(long codMenu, long nodoPadre, String flgHoja, String enlace,
			String etiqueta, long nivel, String descripcion, String seguridad,
			String linkInterno) {
		super();
		this.codMenu = codMenu;
		this.nodoPadre = nodoPadre;
		this.flgHoja = flgHoja;
		this.enlace = enlace;
		this.etiqueta = etiqueta;
		this.nivel = nivel;
		this.descripcion = descripcion;
		this.seguridad = seguridad;
		this.linkInterno = linkInterno;
	}

	public long getCodMenu() {
		return codMenu;
	}

	public long getNodoPadre() {
		return nodoPadre;
	}

	public String getFlgHoja() {
		return flgHoja;
	}

	public String getEnlace() {
		return enlace;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public long getNivel() {
		return nivel;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getSeguridad() {
		return seguridad;
	}

	public String getLinkInterno() {
		return linkInterno;
	}

	public void setCodMenu(long codMenu) {
		this.codMenu = codMenu;
	}

	public void setNodoPadre(long nodoPadre) {
		this.nodoPadre = nodoPadre;
	}

	public void setFlgHoja(String flgHoja) {
		this.flgHoja = flgHoja;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public void setNivel(long nivel) {
		this.nivel = nivel;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setSeguridad(String seguridad) {
		this.seguridad = seguridad;
	}

	public void setLinkInterno(String linkInterno) {
		this.linkInterno = linkInterno;
	}
	
}
