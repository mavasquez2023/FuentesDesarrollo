package cl.laaraucana.menudinamico.vo;

public class adm_MenuPorUsuarioVO {

private long codMenu;
	
    private long nodoPadre;
    
    private String flgHoja;
    
    private String enlace;
    
    private String etiqueta;
    
    private long nivel;
    
    private String descripcion;
    
    private String seguridad;
	
    private int id_user;
    
	private String rut_user;
	
	private String nombre_user;
	
	private String ape_paterno;
	
	private String ape_materno;
	
	private String email_user;	
    
    
    public adm_MenuPorUsuarioVO(){}

    

	public adm_MenuPorUsuarioVO(long codMenu, long nodoPadre, String flgHoja,
			String enlace, String etiqueta, long nivel, String descripcion,
			String seguridad, int id_user, String rut_user, String nombre_user,
			String ape_paterno, String ape_materno, String email_user) {
		super();
		this.codMenu = codMenu;
		this.nodoPadre = nodoPadre;
		this.flgHoja = flgHoja;
		this.enlace = enlace;
		this.etiqueta = etiqueta;
		this.nivel = nivel;
		this.descripcion = descripcion;
		this.seguridad = seguridad;
		this.id_user = id_user;
		this.rut_user = rut_user;
		this.nombre_user = nombre_user;
		this.ape_paterno = ape_paterno;
		this.ape_materno = ape_materno;
		this.email_user = email_user;
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


	public int getId_user() {
		return id_user;
	}


	public String getRut_user() {
		return rut_user;
	}


	public String getNombre_user() {
		return nombre_user;
	}


	public String getApe_paterno() {
		return ape_paterno;
	}


	public String getApe_materno() {
		return ape_materno;
	}


	public String getEmail_user() {
		return email_user;
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


	public void setId_user(int id_user) {
		this.id_user = id_user;
	}


	public void setRut_user(String rut_user) {
		this.rut_user = rut_user;
	}


	public void setNombre_user(String nombre_user) {
		this.nombre_user = nombre_user;
	}


	public void setApe_paterno(String ape_paterno) {
		this.ape_paterno = ape_paterno;
	}


	public void setApe_materno(String ape_materno) {
		this.ape_materno = ape_materno;
	}


	public void setEmail_user(String email_user) {
		this.email_user = email_user;
	}
    
}
