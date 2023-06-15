package cl.laaraucana.botonpago.web.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * 
 * @version 1.0
 * @author
 */
public class Sinat03Form extends ActionForm

{

	/**
	 * 
	 */
	private static final long serialVersionUID = 650070194146269558L;
	private String tipcod;
	private String porcen;
	private String fecsis;
	private String horsis;
	private String iduser;

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		tipcod = "";
		porcen = "";
		fecsis = "";
		horsis = "";
		iduser = "";

	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		return errors;

	}

	public String getTipcod() {
		return tipcod;
	}

	public void setTipcod(String tipcod) {
		this.tipcod = tipcod;
	}

	public String getPorcen() {
		return porcen;
	}

	public void setPorcen(String porcen) {
		this.porcen = porcen;
	}

	public String getFecsis() {
		return fecsis;
	}

	public void setFecsis(String fecsis) {
		this.fecsis = fecsis;
	}

	public String getHorsis() {
		return horsis;
	}

	public void setHorsis(String horsis) {
		this.horsis = horsis;
	}

	public String getIduser() {
		return iduser;
	}

	public void setIduser(String iduser) {
		this.iduser = iduser;
	}

}
