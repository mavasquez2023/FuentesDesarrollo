package cl.laaraucana.silmsil.vo;

import java.util.ArrayList;

/**
 * clase que soporta estructura de cuadro estadistico SILMSIL
 * 
 * */
public class EstadisticoSILMSIL_VO {

	private int periodo;
	private int clasificacion;
	private String tipo;
	private String titulo;
	private long tipoLic1;
	private long tipoLic2;
	private long tipoLic3;
	private long tipoLic4;
	private long tipoLic5;
	private long tipoLic6;
	private long tipoLic7;
	private long tipoLic8;
	private long tipoLic9;
	
	public EstadisticoSILMSIL_VO() {
		this.periodo=0;
		this.tipo="";
		this.titulo="";
		this.clasificacion = 0;
		this.tipoLic1 = 0;
		this.tipoLic2 = 0;
		this.tipoLic3 = 0;
		this.tipoLic4 = 0;
		this.tipoLic5 = 0;
		this.tipoLic6 = 0;
		this.tipoLic7 = 0;
		this.tipoLic8 = 0;
		this.tipoLic9 = 0;
	}
	public EstadisticoSILMSIL_VO(int periodo) {
		this.periodo=periodo;
		this.tipo="";
		this.titulo="";
		this.clasificacion = 0;
		this.tipoLic1 = 0;
		this.tipoLic2 = 0;
		this.tipoLic3 = 0;
		this.tipoLic4 = 0;
		this.tipoLic5 = 0;
		this.tipoLic6 = 0;
		this.tipoLic7 = 0;
		this.tipoLic8 = 0;
		this.tipoLic9 = 0;
	}
	
	
	public ArrayList getListaEstadisticoCero(int periodo){
		ArrayList ceList=new ArrayList();
		EstadisticoSILMSIL_VO ceVO=null;
		ceVO=new EstadisticoSILMSIL_VO(periodo);
		ceVO.setTipo("LM");
		ceVO.setTitulo("Aprobadas");
		ceList.add(ceVO);
		ceVO=new EstadisticoSILMSIL_VO(periodo);
		ceVO.setTitulo("Rechazadas");
		ceList.add(ceVO);
		ceVO=new EstadisticoSILMSIL_VO(periodo);
		ceVO.setTipo("SIL");
		ceVO.setTitulo("Liquidadas");
		ceList.add(ceVO);
		ceVO=new EstadisticoSILMSIL_VO(periodo);
		ceVO.setTitulo("Total Subsidios Liquidados");
		ceList.add(ceVO);
		
		return ceList;
	}
	
	public int getPeriodo() {
		return periodo;
	}

	public int getClasificacion() {
		return clasificacion;
	}

	public String getTipo() {
		return tipo;
	}

	public String getTitulo() {
		return titulo;
	}

	public long getTipoLic1() {
		return tipoLic1;
	}

	public long getTipoLic2() {
		return tipoLic2;
	}

	public long getTipoLic3() {
		return tipoLic3;
	}

	public long getTipoLic4() {
		return tipoLic4;
	}

	public long getTipoLic5() {
		return tipoLic5;
	}

	public long getTipoLic6() {
		return tipoLic6;
	}

	public long getTipoLic7() {
		return tipoLic7;
	}

	public long getTipoLic8() {
		return tipoLic8;
	}

	public long getTipoLic9() {
		return tipoLic9;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public void setClasificacion(int clasificacion) {
		this.clasificacion = clasificacion;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setTipoLic1(long tipoLic1) {
		this.tipoLic1 = tipoLic1;
	}

	public void setTipoLic2(long tipoLic2) {
		this.tipoLic2 = tipoLic2;
	}

	public void setTipoLic3(long tipoLic3) {
		this.tipoLic3 = tipoLic3;
	}

	public void setTipoLic4(long tipoLic4) {
		this.tipoLic4 = tipoLic4;
	}

	public void setTipoLic5(long tipoLic5) {
		this.tipoLic5 = tipoLic5;
	}

	public void setTipoLic6(long tipoLic6) {
		this.tipoLic6 = tipoLic6;
	}

	public void setTipoLic7(long tipoLic7) {
		this.tipoLic7 = tipoLic7;
	}

	public void setTipoLic8(long tipoLic8) {
		this.tipoLic8 = tipoLic8;
	}

	public void setTipoLic9(long tipoLic9) {
		this.tipoLic9 = tipoLic9;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return(
				"["+this.periodo+
				"|"+this.tipo+
				"|"+this.titulo+
				"|"+this.clasificacion+
				"|"+this.tipoLic1+
				"|"+this.tipoLic2+
				"|"+this.tipoLic3+
				"|"+this.tipoLic4+
				"|"+this.tipoLic5+
				"|"+this.tipoLic6+
				"|"+this.tipoLic7+
				"|"+this.tipoLic8+
				"|"+this.tipoLic9+
				"]"
			);
	}
}
