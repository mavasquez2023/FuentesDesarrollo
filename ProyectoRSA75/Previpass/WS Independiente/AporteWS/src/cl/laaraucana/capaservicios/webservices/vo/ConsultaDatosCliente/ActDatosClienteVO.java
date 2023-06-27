package cl.laaraucana.capaservicios.webservices.vo.ConsultaDatosCliente;

public class ActDatosClienteVO {
	private String rutCliente;
	private String sexo;
	private String estadoCivil;
	private String fechaNac;
	private String codComuna;
	private String direccion;
	private String nroDpto;
	
	private int codAreaTelFijo; 
	private String telFijo;
	private int prefijoCelular;
	private String nroCelular;
	private String email;
	
	public String getRutCliente() {
		return rutCliente;
	}
	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getNroDpto() {
		return nroDpto;
	}
	public void setNroDpto(String nroDpto) {
		this.nroDpto = nroDpto;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCodComuna() {
		return codComuna;
	}
	public void setCodComuna(String codComuna) {
		this.codComuna = codComuna;
	}
	public int getCodAreaTelFijo() {
		return codAreaTelFijo;
	}
	public void setCodAreaTelFijo(int codAreaTelFijo) {
		this.codAreaTelFijo = codAreaTelFijo;
	}
	public int getPrefijoCelular() {
		return prefijoCelular;
	}
	public void setPrefijoCelular(int prefijoCelular) {
		this.prefijoCelular = prefijoCelular;
	}
	public String getTelFijo() {
		return telFijo;
	}
	public void setTelFijo(String telFijo) {
		this.telFijo = telFijo;
	}
	public String getNroCelular() {
		return nroCelular;
	}
	public void setNroCelular(String nroCelular) {
		this.nroCelular = nroCelular;
	}
}
