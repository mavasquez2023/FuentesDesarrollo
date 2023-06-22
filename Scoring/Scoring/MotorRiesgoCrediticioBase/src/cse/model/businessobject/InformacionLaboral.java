package cse.model.businessobject;


public class InformacionLaboral {

	private String antiguedadLaboral; //aaaammdd
	private Rut rutEmpleador;
	private short licenciasMedicas;
	
	public String getAntiguedadLaboral() {
		return antiguedadLaboral;
	}
	public void setAntiguedadLaboral(String antiguedadLaboral) {
		this.antiguedadLaboral = antiguedadLaboral;
	}
	public Rut getRutEmpleador() {
		return rutEmpleador;
	}
	public void setRutEmpleador(Rut rutEmpleador) {
		this.rutEmpleador = rutEmpleador;
	}
	public short getLicenciasMedicas() {
		return licenciasMedicas;
	}
	public void setLicenciasMedicas(short licenciasMedicas) {
		this.licenciasMedicas = licenciasMedicas;
	} 
	
	
	
}
