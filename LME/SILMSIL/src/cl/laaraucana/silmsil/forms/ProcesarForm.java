package cl.laaraucana.silmsil.forms;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * @version 	1.0
 * @author
 */
public class ProcesarForm extends ActionForm
{
	private String hd_Asignar = "";
	
	private String selAnno = "";
	private String chk_SIL01 = "";
	private String chk_SIL02 = "";
	private String chk_SIL03 = "";
	private String chk_SIL04 = "";
	private String chk_SIL05 = "";
	private String chk_SIL06 = "";
	private String chk_SIL07 = "";
	private String chk_SIL08 = "";
	private String chk_SIL09 = "";
	private String chk_SIL10 = "";
	private String chk_SIL11 = "";
	private String chk_SIL12 = "";
	private String chk_LM01 = "";
	private String chk_LM02 = "";
	private String chk_LM03 = "";
	private String chk_LM04 = "";
	private String chk_LM05 = "";
	private String chk_LM06 = "";
	private String chk_LM07 = "";
	private String chk_LM08 = "";
	private String chk_LM09 = "";
	private String chk_LM10 = "";
	private String chk_LM11 = "";
	private String chk_LM12 = "";
	
	//variable para descargar un archivo
	private String keyProcesoPeriodo;
	
    public String getHd_Asignar() {
		return hd_Asignar;
	}

	public void setHd_Asignar(String hd_Asignar) {
		this.hd_Asignar = hd_Asignar;
	}

	public String getSelAnno() {
		return selAnno;
	}

	public void setSelAnno(String selAnno) {
		this.selAnno = selAnno;
	}

	public String getChk_SIL01() {
		return chk_SIL01;
	}

	public void setChk_SIL01(String chk_SIL01) {
		this.chk_SIL01 = chk_SIL01;
	}

	public String getChk_SIL02() {
		return chk_SIL02;
	}

	public void setChk_SIL02(String chk_SIL02) {
		this.chk_SIL02 = chk_SIL02;
	}

	public String getChk_SIL03() {
		return chk_SIL03;
	}

	public void setChk_SIL03(String chk_SIL03) {
		this.chk_SIL03 = chk_SIL03;
	}

	public String getChk_SIL04() {
		return chk_SIL04;
	}

	public void setChk_SIL04(String chk_SIL04) {
		this.chk_SIL04 = chk_SIL04;
	}

	public String getChk_SIL05() {
		return chk_SIL05;
	}

	public void setChk_SIL05(String chk_SIL05) {
		this.chk_SIL05 = chk_SIL05;
	}

	public String getChk_SIL06() {
		return chk_SIL06;
	}

	public void setChk_SIL06(String chk_SIL06) {
		this.chk_SIL06 = chk_SIL06;
	}

	public String getChk_SIL07() {
		return chk_SIL07;
	}

	public void setChk_SIL07(String chk_SIL07) {
		this.chk_SIL07 = chk_SIL07;
	}

	public String getChk_SIL08() {
		return chk_SIL08;
	}

	public void setChk_SIL08(String chk_SIL08) {
		this.chk_SIL08 = chk_SIL08;
	}

	public String getChk_SIL09() {
		return chk_SIL09;
	}

	public void setChk_SIL09(String chk_SIL09) {
		this.chk_SIL09 = chk_SIL09;
	}

	public String getChk_SIL10() {
		return chk_SIL10;
	}

	public void setChk_SIL10(String chk_SIL10) {
		this.chk_SIL10 = chk_SIL10;
	}

	public String getChk_SIL11() {
		return chk_SIL11;
	}

	public void setChk_SIL11(String chk_SIL11) {
		this.chk_SIL11 = chk_SIL11;
	}

	public String getChk_SIL12() {
		return chk_SIL12;
	}

	public void setChk_SIL12(String chk_SIL12) {
		this.chk_SIL12 = chk_SIL12;
	}

	public String getChk_LM01() {
		return chk_LM01;
	}

	public void setChk_LM01(String chk_LM01) {
		this.chk_LM01 = chk_LM01;
	}

	public String getChk_LM02() {
		return chk_LM02;
	}

	public void setChk_LM02(String chk_LM02) {
		this.chk_LM02 = chk_LM02;
	}

	public String getChk_LM03() {
		return chk_LM03;
	}

	public void setChk_LM03(String chk_LM03) {
		this.chk_LM03 = chk_LM03;
	}

	public String getChk_LM04() {
		return chk_LM04;
	}

	public void setChk_LM04(String chk_LM04) {
		this.chk_LM04 = chk_LM04;
	}

	public String getChk_LM05() {
		return chk_LM05;
	}

	public void setChk_LM05(String chk_LM05) {
		this.chk_LM05 = chk_LM05;
	}

	public String getChk_LM06() {
		return chk_LM06;
	}

	public void setChk_LM06(String chk_LM06) {
		this.chk_LM06 = chk_LM06;
	}

	public String getChk_LM07() {
		return chk_LM07;
	}

	public void setChk_LM07(String chk_LM07) {
		this.chk_LM07 = chk_LM07;
	}

	public String getChk_LM08() {
		return chk_LM08;
	}

	public void setChk_LM08(String chk_LM08) {
		this.chk_LM08 = chk_LM08;
	}

	public String getChk_LM09() {
		return chk_LM09;
	}

	public void setChk_LM09(String chk_LM09) {
		this.chk_LM09 = chk_LM09;
	}

	public String getChk_LM10() {
		return chk_LM10;
	}

	public void setChk_LM10(String chk_LM10) {
		this.chk_LM10 = chk_LM10;
	}

	public String getChk_LM11() {
		return chk_LM11;
	}

	public void setChk_LM11(String chk_LM11) {
		this.chk_LM11 = chk_LM11;
	}

	public String getChk_LM12() {
		return chk_LM12;
	}

	public void setChk_LM12(String chk_LM12) {
		this.chk_LM12 = chk_LM12;
	}
			
	public String getKeyProcesoPeriodo() {
		return keyProcesoPeriodo;
	}

	public void setKeyProcesoPeriodo(String keyProcesoPeriodo) {
		this.keyProcesoPeriodo = keyProcesoPeriodo;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
    	
		String selAnno = "";
		selAnno = "";
    	chk_SIL01 = "";
    	chk_SIL02 = "";
    	chk_SIL03 = "";
    	chk_SIL04 = "";
    	chk_SIL05 = "";
    	chk_SIL06 = "";
    	chk_SIL07 = "";
    	chk_SIL08 = "";
    	chk_SIL09 = "";
    	chk_SIL10 = "";
    	chk_SIL11 = "";
    	chk_SIL12 = "";
    	chk_LM01 = "";
    	chk_LM02 = "";
    	chk_LM03 = "";
    	chk_LM04 = "";
    	chk_LM05 = "";
    	chk_LM06 = "";
    	chk_LM07 = "";
    	chk_LM08 = "";
    	chk_LM09 = "";
    	chk_LM10 = "";
    	chk_LM11 = "";
    	chk_LM12 = "";
    	
    	keyProcesoPeriodo="";
    }
	

	public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {

	ActionErrors errors = new ActionErrors();
	// Validate the fields in your form, adding
	// adding each error to this.errors as found, e.g.

	// if ((field == null) || (field.length() == 0)) {
	//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
	// }
	return errors;

    }
}
