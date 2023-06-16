package cl.laaraucana.menudinamico.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * @version 	1.0
 * @author
 */
public class MenuPrincipal_Form extends ActionForm
{
	private long codMenu;
	private long nivel;
    private String flgHoja;
    private String url;

    public void reset(ActionMapping mapping, HttpServletRequest request) {
    	codMenu = 0;
    	nivel = 0;
        flgHoja = "";
        url = "";
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
    
    

	public long getCodMenu() {
		return codMenu;
	}

	public void setCodMenu(long codMenu) {
		this.codMenu = codMenu;
	}
	
	public String getFlgHoja() {
		return flgHoja;
	}

	public void setFlgHoja(String flgHoja) {
		this.flgHoja = flgHoja;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getNivel() {
		return nivel;
	}

	public void setNivel(long nivel) {
		this.nivel = nivel;
	}
}
