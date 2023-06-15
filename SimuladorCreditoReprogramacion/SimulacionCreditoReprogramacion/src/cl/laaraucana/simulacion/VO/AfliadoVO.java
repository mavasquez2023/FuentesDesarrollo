package cl.laaraucana.simulacion.VO;

public class AfliadoVO {

	private String rut;
	private String nombreCompleto;
	private boolean esEmpresa;
	
	public AfliadoVO(){
		
	}

	public AfliadoVO(String rut, String clave, long especialPreaprobado,
			long socialPreaprobado) {
		setRut(rut);
	}

	public String getRut() {
		return rut;
	}
	
	/** No borrar getters **/
	public String getRutSinDv() {
		String rutS =  rut.substring(0, (rut.length()-2));
		return rutS;
	}
	
	public Long getRutLong() {
		Long rutL =  Long.parseLong(rut.substring(0, (rut.length()-2)));
		return rutL;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}

	public boolean isEsEmpresa() {
		return esEmpresa;
	}

	public void setEsEmpresa(boolean esEmpresa) {
		this.esEmpresa = esEmpresa;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
}
