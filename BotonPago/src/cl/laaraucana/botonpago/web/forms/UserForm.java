package cl.laaraucana.botonpago.web.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import cl.laaraucana.botonpago.web.utils.MailValidation;

/**
 * Form bean for a Struts application.
 * Users may access 7 fields on this form:
 * <ul>
 * <li>id - [your comment here]
 * <li>nombre - [your comment here]
 * <li>apellidoPaterno - [your comment here]
 * <li>apellidoMaterno - [your comment here]
 * <li>fono - [your comment here]
 * <li>email - [your comment here]
 * <li>perfil - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class UserForm extends ActionForm

{

	/**
	 * 
	 */
	private static final long serialVersionUID = 250144257090306646L;
	private String id = null;
	private String nombre = null;
	private String apellidoPaterno = null;
	private String apellidoMaterno = null;
	private String fono = null;
	private String email = null;
	private String sexo = null;
	private String[] perfil = null;

	/**
	 * Get id
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set id
	 * @param <code>String</code>
	 */
	public void setId(String i) {
		this.id = i;
	}

	/**
	 * Get nombre
	 * @return String
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Set nombre
	 * @param <code>String</code>
	 */
	public void setNombre(String n) {
		this.nombre = n;
	}

	/**
	 * Get apellidoPaterno
	 * @return String
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	/**
	 * Set apellidoPaterno
	 * @param <code>String</code>
	 */
	public void setApellidoPaterno(String a) {
		this.apellidoPaterno = a;
	}

	/**
	 * Get apellidoMaterno
	 * @return String
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * Set apellidoMaterno
	 * @param <code>String</code>
	 */
	public void setApellidoMaterno(String a) {
		this.apellidoMaterno = a;
	}

	/**
	 * Get fono
	 * @return String
	 */
	public String getFono() {
		return fono;
	}

	/**
	 * Set fono
	 * @param <code>String</code>
	 */
	public void setFono(String f) {
		this.fono = f;
	}

	/**
	 * Get email
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set email
	 * @param <code>String</code>
	 */
	public void setEmail(String e) {
		this.email = e;
	}

	/**
	 * Get perfil
	 * @return String
	 */
	public String[] getPerfil() {
		return perfil;
	}

	/**
	 * Set perfil
	 * @param <code>String</code>
	 */
	public void setPerfil(String[] p) {
		this.perfil = p;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		id = null;
		nombre = null;
		apellidoPaterno = null;
		apellidoMaterno = null;
		fono = null;
		email = null;
		sexo = null;
		perfil = null;

	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		if ((id == null) || (id.length() == 0)) {
			errors.add("id", new ActionMessage("error.required"));
		}
		if ((nombre == null) || (nombre.length() == 0)) {
			errors.add("nombre", new ActionMessage("error.required"));
		}
		if ((apellidoPaterno == null) || (apellidoPaterno.length() == 0)) {
			errors.add("apellidoPaterno", new ActionMessage("error.required"));
		}
		if ((apellidoMaterno == null) || (apellidoMaterno.length() == 0)) {
			errors.add("apellidoMaterno", new ActionMessage("error.required"));
		}
		if ((fono == null) || (fono.length() == 0)) {
			errors.add("fono", new ActionMessage("error.required"));
		}
		if ((email == null) || (email.length() == 0)) {
			errors.add("email", new ActionMessage("error.required"));
		} else {
			if (!MailValidation.IsValid(email)) {
				errors.add("email", new ActionMessage("error.mail"));
			}
		}
		if ((sexo == null) || (sexo.length() == 0)) {
			errors.add("sexo", new ActionMessage("error.required"));
		}
		if ((perfil == null) || (perfil.length == 0)) {
			errors.add("perfil", new ActionMessage("error.required"));
		}

		return errors;

	}
}
