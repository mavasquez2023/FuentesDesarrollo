package cl.laaraucana.menudinamico.vo;

public class MenuPorUsuarioVO {

	private int id_proc;
	private String rut_user;
	private long codMenu;
    private long nodoPadre;
    private String flgHoja;
    private String enlace;
    private String etiqueta;    
    private long nivel;
    private String seguridad;
    private String linkInterno;
    
    
    public MenuPorUsuarioVO(){}


	public MenuPorUsuarioVO(int id_proc, String rut_user, long codMenu,
			long nodoPadre, String flgHoja, String enlace, String etiqueta,
			long nivel, String seguridad, String linkInterno) {
		super();
		this.id_proc = id_proc;
		this.rut_user = rut_user;
		this.codMenu = codMenu;
		this.nodoPadre = nodoPadre;
		this.flgHoja = flgHoja;
		this.enlace = enlace;
		this.etiqueta = etiqueta;
		this.nivel = nivel;
		this.seguridad = seguridad;
		this.linkInterno = linkInterno;
	}


	public int getId_proc() {
		return id_proc;
	}


	public void setId_proc(int id_proc) {
		this.id_proc = id_proc;
	}


	public String getRut_user() {
		return rut_user;
	}


	public void setRut_user(String rut_user) {
		this.rut_user = rut_user;
	}


	public long getCodMenu() {
		return codMenu;
	}


	public void setCodMenu(long codMenu) {
		this.codMenu = codMenu;
	}


	public long getNodoPadre() {
		return nodoPadre;
	}


	public void setNodoPadre(long nodoPadre) {
		this.nodoPadre = nodoPadre;
	}


	public String getFlgHoja() {
		return flgHoja;
	}


	public void setFlgHoja(String flgHoja) {
		this.flgHoja = flgHoja;
	}


	public String getEnlace() {
		return enlace;
	}


	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}


	public String getEtiqueta() {
		return etiqueta;
	}


	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}


	public long getNivel() {
		return nivel;
	}


	public void setNivel(long nivel) {
		this.nivel = nivel;
	}


	public String getSeguridad() {
		return seguridad;
	}


	public void setSeguridad(String seguridad) {
		this.seguridad = seguridad;
	}


	public String getLinkInterno() {
		return linkInterno;
	}


	public void setLinkInterno(String linkInterno) {
		this.linkInterno = linkInterno;
	}
}
