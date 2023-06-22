package cl.laaraucana.simulacion.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import cl.laaraucana.simulacion.utils.FormUtils;

/**
 * Form bean for a Struts application.
 * 
 * @version 1.0
 * @author
 */
public class EnviarCotizacionForm extends ActionForm

{

	private static final long serialVersionUID = 5357431981139689677L;

	private String extension;
	private String preMovil;
	private String fono;
	private String celular;
	private String email;
	private String calle;
	private String calleNro;
	private String nroDpto;

	private String region;
	private String comuna;
	private boolean contacto;

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		extension = "";
		fono = "";
		celular = "";
		email = "";
		calle = "";
		comuna = "";
		preMovil = "";
		region = "";
		contacto = false;
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		if (preMovil.isEmpty())
			errors.add("preMovil", new ActionMessage("errors.formalizar.required"));
		if (!FormUtils.isInt(preMovil) || preMovil.length() > 2)
			errors.add("preMovil", new ActionMessage("errors.extension.formato"));

		if (extension.isEmpty())
			errors.add("extension", new ActionMessage("errors.formalizar.required"));
		if (!FormUtils.isInt(extension) || extension.length() > 2)
			errors.add("extension", new ActionMessage("errors.extension.formato"));

		if (fono.isEmpty())
			errors.add("fono", new ActionMessage("errors.formalizar.required"));
		if (!FormUtils.isLong(fono) || fono.length() > 8)
			errors.add("fono", new ActionMessage("errors.fono.formato"));

		if (celular.isEmpty())
			errors.add("celular", new ActionMessage("errors.formalizar.required"));
		if (!FormUtils.isLong(celular) || celular.length() > 8)
			errors.add("celular", new ActionMessage("errors.celular.formato"));

		if (email.isEmpty())
			errors.add("email", new ActionMessage("errors.formalizar.required"));
		if (email.length() > 50)
			errors.add("email", new ActionMessage("errors.caracter.largo", 50));

		if (calle.isEmpty())
			errors.add("calle", new ActionMessage("errors.formalizar.required"));
		if (calle.length() > 80)
			errors.add("calle", new ActionMessage("errors.caracter.largo", 80));

		if (calleNro.isEmpty())
			errors.add("calleNro", new ActionMessage("errors.formalizar.required"));
		if (!FormUtils.isInt(calleNro))
			errors.add("calleNro", new ActionMessage("errors.calleNro.formato"));

		if (nroDpto.length() > 5)
			errors.add("nroDpto", new ActionMessage("errors.caracter.largo", 5));

		if (comuna.isEmpty())
			errors.add("comuna", new ActionMessage("errors.formalizar.required"));
		if (comuna.length() > 50)
			errors.add("comuna", new ActionMessage("errors.caracter.largo", 50));

		return errors;

	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getFono() {
		return fono;
	}

	public void setFono(String fono) {
		this.fono = fono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getComuna() {
		return comuna;
	}

	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	public String getCalleNro() {
		return calleNro;
	}

	public void setCalleNro(String calleNro) {
		this.calleNro = calleNro;
	}

	public String getNroDpto() {
		return nroDpto;
	}

	public void setNroDpto(String nroDpto) {
		this.nroDpto = nroDpto;
	}

	public String getPreMovil() {
		return preMovil;
	}

	public void setPreMovil(String preMovil) {
		this.preMovil = preMovil;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public boolean isContacto() {
		return contacto;
	}

	public void setContacto(boolean contacto) {
		this.contacto = contacto;
	}

}
