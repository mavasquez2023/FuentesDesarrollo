package cl.laaraucana.botonpago.web.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import cl.laaraucana.botonpago.web.utils.Constantes;

/**
 * Form bean for a Struts application.
 * Users may access 2 fields on this form:
 * <ul>
 * <li>folio - [your comment here]
 * <li>origen - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class ComprobantePagadoForm extends ActionForm

{
	private static final long serialVersionUID = -1346478887700440986L;
	private String folio = null;

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		folio = null;
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		try {
			request.setAttribute("titulo", Constantes.getInstancia().MSG_TIPO_ERROR);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		ActionErrors errors = new ActionErrors();
		if (folio == null) {
			try {
				request.setAttribute("tipo", Constantes.getInstancia().MSG_TIPO_ERROR);
			} catch (Exception e) {
				e.printStackTrace();
			}
			errors.add("field", new ActionMessage("error.seleccione.comprobante"));
			return errors;
		}

		try {
			Long.parseLong(folio);
		} catch (Exception e) {
			try {
				request.setAttribute("tipo", Constantes.getInstancia().MSG_TIPO_ERROR);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			errors.add("field", new ActionMessage("error.seleccione.comprobante"));
			return errors;
		}
		return errors;
	}

	/**
	 * Get folio
	 * @return String
	 */
	public String getFolio() {
		return folio;
	}

	/**
	 * Set folio
	 * @param <code>String</code>
	 */
	public void setFolio(String f) {
		this.folio = f;
	}

}
