package cl.laaraucana.simat.entidades;

public class Control_Historico_VO {

	private String mes_informacion;

	public Control_Historico_VO() {

	}

	public Control_Historico_VO(String mes_informacion) {
		super();
		this.mes_informacion = mes_informacion;
	}

	public String getMes_informacion() {
		return mes_informacion;
	}

	public void setMes_informacion(String mes_informacion) {
		this.mes_informacion = mes_informacion;
	}

}
