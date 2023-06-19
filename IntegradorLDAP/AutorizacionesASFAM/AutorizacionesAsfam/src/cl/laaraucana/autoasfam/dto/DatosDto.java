package cl.laaraucana.autoasfam.dto;

public class DatosDto {

	private String rutEmpresa;
	private String periodo;
	private String tipomov;


	public String getRutEmpresa() {
		return rutEmpresa;
	}

	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	/**
	 * @return the tipomov
	 */
	public String getTipomov() {
		return tipomov;
	}

	/**
	 * @param tipomov the tipomov to set
	 */
	public void setTipomov(String tipomov) {
		this.tipomov = tipomov;
	}
	
	
}
