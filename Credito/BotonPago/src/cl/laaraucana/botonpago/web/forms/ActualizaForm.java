package cl.laaraucana.botonpago.web.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import cl.laaraucana.botonpago.web.utils.MailValidation;

/**
 * Form bean for a Struts application. Users may access 8 fields on this form:
 * <ul>
 * <li>email - [your comment here]
 * <li>enviaEECC - [your comment here]
 * <li>fono1 - [your comment here]
 * <li>fono2 - [your comment here]
 * <li>fono3 - [your comment here]
 * <li>direccion - [your comment here]
 * <li>comuna - [your comment here]
 * <li>provincia - [your comment here]
 * </ul>
 * 
 * @version 1.0
 * @author
 */
public class ActualizaForm extends ActionForm

{

	private static final long serialVersionUID = 9142736194187446597L;

	private String email = null;
	private String recibirECVM = null;
	private String fono1 = null;
	private String fono2 = null;
	private String fono3 = null;
	private String estadoCivil = null;
	private String direccion = null;
	private String comuna = null;
	private String provincia = null;
	private String region = null;

	/**
	 * Get email
	 * 
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set email
	 * 
	 * @param <code>String</code>
	 */
	public void setEmail(String e) {
		this.email = e;
	}

	public String getRecibirECVM() {
		return recibirECVM;
	}

	public void setRecibirECVM(String recibirECVM) {
		this.recibirECVM = recibirECVM;
	}

	/**
	 * Get fono1
	 * 
	 * @return String
	 */
	public String getFono1() {
		return fono1;
	}

	/**
	 * Set fono1
	 * 
	 * @param <code>String</code>
	 */
	public void setFono1(String f) {
		this.fono1 = f;
	}

	/**
	 * Get fono2
	 * 
	 * @return String
	 */
	public String getFono2() {
		return fono2;
	}

	/**
	 * Set fono2
	 * 
	 * @param <code>String</code>
	 */
	public void setFono2(String f) {
		this.fono2 = f;
	}

	/**
	 * Get fono3
	 * 
	 * @return String
	 */
	public String getFono3() {
		return fono3;
	}

	/**
	 * Set fono3
	 * 
	 * @param <code>String</code>
	 */
	public void setFono3(String f) {
		this.fono3 = f;
	}

	/**
	 * @return el estadoCivil
	 */
	public String getEstadoCivil() {
		return estadoCivil;
	}

	/**
	 * @param estadoCivil el estadoCivil a establecer
	 */
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	/**
	 * Get direccion
	 * 
	 * @return String
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Set direccion
	 * 
	 * @param <code>String</code>
	 */
	public void setDireccion(String d) {
		this.direccion = d;
	}

	/**
	 * Get comuna
	 * 
	 * @return String
	 */
	public String getComuna() {
		return comuna;
	}

	/**
	 * Set comuna
	 * 
	 * @param <code>String</code>
	 */
	public void setComuna(String c) {
		this.comuna = c;
	}

	/**
	 * Get provincia
	 * 
	 * @return String
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * Set provincia
	 * 
	 * @param <code>String</code>
	 */
	public void setProvincia(String p) {
		this.provincia = p;
	}

	/**
	 * @return el region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region el region a establecer
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		email = "";
		recibirECVM = "";
		fono1 = "";
		fono2 = null;
		fono3 = null;
		estadoCivil = null;
		direccion = null;
		comuna = null;
		provincia = null;
		region = null;

	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		if ((email == null) || (email.length() == 0)) {
			errors.add("email", new ActionMessage("error.email.required"));
		} else {
			if (!MailValidation.IsValid(email)) {
				errors.add("email", new ActionMessage("error.mail"));
			}
		}
		if ((fono1 == null) || (fono1.length() == 0)) {
			errors.add("fono1", new ActionMessage("error.fono1.required"));
		}
		if ((estadoCivil == null) || (estadoCivil.length() == 0)) {
			errors.add("estadoCivil", new ActionMessage("error.estadoCivil.required"));
		}
		if ((direccion == null) || (direccion.length() == 0)) {
			errors.add("direccion", new ActionMessage("error.direccion.required"));
		}
		if ((comuna == null) || (comuna.length() == 0)) {
			errors.add("comuna", new ActionMessage("error.comuna.required"));
		}
		return errors;
	}
}
