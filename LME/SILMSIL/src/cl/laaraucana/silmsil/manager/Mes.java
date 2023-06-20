package cl.laaraucana.silmsil.manager;

import java.util.ArrayList;

import cl.laaraucana.silmsil.vo.EstadoVO;

public class Mes {
	//Mes del período procesado.
	private String mes;
	//Campo para período en fecha  AAAAMM.
	private int periodo;
	//Proceso SIL, identificador = 1.
	private String procSIL;
	//Proceso LM, identificador = 2.
	private String procLM;
	//Estado proceso SIL.
	private ArrayList<EstadoVO> lsEstadoSIL;
	//Estado proceso LM.
	private ArrayList<EstadoVO> lsEstadoLM;
		
	public Mes(){}

	public Mes(String mes, String procSIL, String procLM,
			ArrayList<EstadoVO> lsEstadoSIL, ArrayList<EstadoVO> lsEstadoLM) {
		super();
		this.mes = mes;
		this.procSIL = procSIL;
		this.procLM = procLM;
		this.lsEstadoSIL = lsEstadoSIL;
		this.lsEstadoLM = lsEstadoLM;
	}
	public Mes(String mes, int periodo, String procSIL, String procLM,
			ArrayList<EstadoVO> lsEstadoSIL, ArrayList<EstadoVO> lsEstadoLM) {
		super();
		this.mes = mes;
		this.periodo = periodo;
		this.procSIL = procSIL;
		this.procLM = procLM;
		this.lsEstadoSIL = lsEstadoSIL;
		this.lsEstadoLM = lsEstadoLM;
	}
	
	

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getProcSIL() {
		return procSIL;
	}

	public void setProcSIL(String procSIL) {
		this.procSIL = procSIL;
	}

	public String getProcLM() {
		return procLM;
	}

	public void setProcLM(String procLM) {
		this.procLM = procLM;
	}

	public ArrayList<EstadoVO> getLsEstadoSIL() {
		return lsEstadoSIL;
	}

	public void setLsEstadoSIL(ArrayList<EstadoVO> lsEstadoSIL) {
		this.lsEstadoSIL = lsEstadoSIL;
	}

	public ArrayList<EstadoVO> getLsEstadoLM() {
		return lsEstadoLM;
	}

	public void setLsEstadoLM(ArrayList<EstadoVO> lsEstadoLM) {
		this.lsEstadoLM = lsEstadoLM;
	}

	
}
