package cl.laaraucana.silmsil.vo;

public class ListadoPrincipalVO {
	private String mes;
	private String estadoSIL;
	private String estadoLM;
	private String valorRadio;
	
	public ListadoPrincipalVO(){}
	
	

	public ListadoPrincipalVO(String mes, String estadoSIL, String estadoLM) {
		super();
		this.mes = mes;
		this.estadoSIL = estadoSIL;
		this.estadoLM = estadoLM;
	}

	public ListadoPrincipalVO(String mes, String estadoSIL, String estadoLM,
			String valorRadio) {
		super();
		this.mes = mes;
		this.estadoSIL = estadoSIL;
		this.estadoLM = estadoLM;
		this.valorRadio = valorRadio;
	}



	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getEstadoSIL() {
		return estadoSIL;
	}

	public void setEstadoSIL(String estadoSIL) {
		this.estadoSIL = estadoSIL;
	}

	public String getEstadoLM() {
		return estadoLM;
	}

	public void setEstadoLM(String estadoLM) {
		this.estadoLM = estadoLM;
	}



	public String getValorRadio() {
		return valorRadio;
	}



	public void setValorRadio(String valorRadio) {
		this.valorRadio = valorRadio;
	}
	
	

	
}
