package cl.laaraucana.satelites.main.VO;

public class UsuarioVO {

	private String rut;
	private String clave;

	private String primerNombre;
	private String apellido;

	private String rutEmpresa;
	private String nombreEmpresa;
	
	private int tipoAfiliado;

	private long especialPreaprobado;
	private long socialPreaprobado;
	
	public UsuarioVO(){
		
	}

	public UsuarioVO(String rut, String clave, long especialPreaprobado,
			long socialPreaprobado) {
		setRut(rut);
		setClave(clave);
		setEspecialPreaprobado(especialPreaprobado);
		setSocialPreaprobado(socialPreaprobado);
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public long getEspecialPreaprobado() {
		return especialPreaprobado;
	}

	public void setEspecialPreaprobado(long especialPreaprobado) {
		this.especialPreaprobado = especialPreaprobado;
	}

	public long getSocialPreaprobado() {
		return socialPreaprobado;
	}

	public void setSocialPreaprobado(long socialPreaprobado) {
		this.socialPreaprobado = socialPreaprobado;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getRutEmpresa() {
		return rutEmpresa;
	}

	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public int getTipoAfiliado() {
		return tipoAfiliado;
	}

	public void setTipoAfiliado(int tipoAfiliado) {
		this.tipoAfiliado = tipoAfiliado;
	}
	
	public String getTipoAfiliadoStr() {
		switch(this.tipoAfiliado){
		case 1:
			return "Afiliado";
		case 2:
			return "Pensionado";
		case 3:
			return "Independiente";
		default: 
			return "";
		}
	}
}
