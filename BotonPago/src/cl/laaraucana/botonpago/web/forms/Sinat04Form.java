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
public class Sinat04Form extends ActionForm

{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4318356055232182049L;
	private String anopro;
	private String codpro;
	private String nrodes;
	private String nrohas;
	private String porcen;
	private String fecsis;
	private String horsis;
	private String iduser;
	private String anoproEdit;
	private String codproEdit;
	private String nrodesEdit;
	private String nrohasEdit;

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		anopro = "";
		codpro = "";
		nrodes = "";
		nrohas = "";
		porcen = "";
		fecsis = "";
		horsis = "";
		iduser = "";
		anoproEdit = "";
		codproEdit = "";
		nrodesEdit = "";
		nrohasEdit = "";

	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		return errors;

	}

	public String getAnopro() {
		return anopro;
	}

	public void setAnopro(String anopro) {
		this.anopro = anopro;
	}

	public String getCodpro() {
		return codpro;
	}

	public void setCodpro(String codpro) {
		this.codpro = codpro;
	}

	public String getNrodes() {
		return nrodes;
	}

	public void setNrodes(String nrodes) {
		this.nrodes = nrodes;
	}

	public String getNrohas() {
		return nrohas;
	}

	public void setNrohas(String nrohas) {
		this.nrohas = nrohas;
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

	public String getAnoproEdit() {
		return anoproEdit;
	}

	public void setAnoproEdit(String anoproEdit) {
		this.anoproEdit = anoproEdit;
	}

	public String getCodproEdit() {
		return codproEdit;
	}

	public void setCodproEdit(String codproEdit) {
		this.codproEdit = codproEdit;
	}

	public String getNrodesEdit() {
		return nrodesEdit;
	}

	public void setNrodesEdit(String nrodesEdit) {
		this.nrodesEdit = nrodesEdit;
	}

	public String getNrohasEdit() {
		return nrohasEdit;
	}

	public void setNrohasEdit(String nrohasEdit) {
		this.nrohasEdit = nrohasEdit;
	}

}
