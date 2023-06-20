package cl.laaraucana.menudinamico.vo;

public class UsuarioVO {

	private int id_user;
	private String rut_user;
	private String nombre_user;
	private String ape_paterno;
	private String ape_materno;
	private String email_user;	
	
	public UsuarioVO(){}
	
	public UsuarioVO(int id_user, String rut_user, String nombre_user,
			String ape_paterno, String ape_materno, String email_user) 
	{
		super();
		this.id_user = id_user;
		this.rut_user = rut_user;
		this.nombre_user = nombre_user;
		this.ape_paterno = ape_paterno;
		this.ape_materno = ape_materno;
		this.email_user = email_user;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getRut_user() {
		return rut_user;
	}

	public void setRut_user(String rut_user) {
		this.rut_user = rut_user;
	}

	public String getNombre_user() {
		return nombre_user;
	}

	public void setNombre_user(String nombre_user) {
		this.nombre_user = nombre_user;
	}

	public String getApe_paterno() {
		return ape_paterno;
	}

	public void setApe_paterno(String ape_paterno) {
		this.ape_paterno = ape_paterno;
	}

	public String getApe_materno() {
		return ape_materno;
	}

	public void setApe_materno(String ape_materno) {
		this.ape_materno = ape_materno;
	}

	public String getEmail_user() {
		return email_user;
	}

	public void setEmail_user(String email_user) {
		this.email_user = email_user;
	}
}
