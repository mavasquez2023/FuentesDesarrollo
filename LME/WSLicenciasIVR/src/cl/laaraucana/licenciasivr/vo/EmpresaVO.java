package cl.laaraucana.licenciasivr.vo;

import java.io.Serializable;

public class EmpresaVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer AFIRUT; 	
	private String LICIMPNUM;	
	private String LICEST;	
	private Integer PAGFOL;
	private String LICOBS1;
	
	public Integer getAFIRUT() {
		return AFIRUT;
	}
	public void setAFIRUT(Integer aFIRUT) {
		AFIRUT = aFIRUT;
	}
	public String getLICIMPNUM() {
		return LICIMPNUM;
	}
	public void setLICIMPNUM(String lICIMPNUM) {
		LICIMPNUM = lICIMPNUM;
	}
	public String getLICEST() {
		return LICEST;
	}
	public void setLICEST(String lICEST) {
		LICEST = lICEST;
	}
	public Integer getPAGFOL() {
		return PAGFOL;
	}
	public void setPAGFOL(Integer pAGFOL) {
		PAGFOL = pAGFOL;
	}
	public String getLICOBS1() {
		return LICOBS1;
	}
	public void setLICOBS1(String lICOBS1) {
		LICOBS1 = lICOBS1;
	}
	
	
}
