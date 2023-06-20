package cl.laaraucana.silmsil.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * @version 	1.0
 * @author
 */
public class EjecucionList_Form extends ActionForm{
	
	private String concat;
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		concat = "";
    }
	
    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {
	ActionErrors errors = new ActionErrors();
	return errors;
    }

	public String getConcat() {
		return concat;
	}

	public void setConcat(String concat) {
		this.concat = concat;
	}
}//end class SIL_Form
