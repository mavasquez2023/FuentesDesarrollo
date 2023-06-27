package cl.araucana.ea.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.commons.beanutils.PropertyUtils;
import java.security.Principal;
import java.util.Collection;

import cl.araucana.ea.ctacte.delegate.*;;


/**
 * @version 	1.0
 * @author
 */
public class HoldingAction extends Action {

	static BusinessDelegateFactory factory;
	
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value

		HttpSession session = null;
		Principal principal = null;

		BusinessDelegate delegate = null;
		String rutEmpresa = null;
		String[] keys = new String[1];
		
		String toPage = null;
		
		try {
				
			// do something here
			// getting business service delegator
			factory = BusinessDelegateFactory.singlton();
			delegate = factory.newInstance("cl.araucana.ea.ctacte.delegate.BusinessDelegateImpl");
			
			session = request.getSession();
			principal = request.getUserPrincipal();
			rutEmpresa = principal.getName();
			System.out.println(principal.getName());

			/*
			if(delegate.hasHolding(rutEmpresa)) {				
				// keys[0] = userCtx.getKey();
				PropertyUtils.setSimpleProperty(form, "rutEmpresa", "0");
				keys[0] = rutEmpresa;							
				session.setAttribute("opciones", cc.getOpciones(BusinessDelegate.OPCION_LIQUIDACION_HOLDING, keys, true));
				toPage = "holding";
				
			} else {
				PropertyUtils.setSimpleProperty(form, "idChanged", "EMPRESA");
				PropertyUtils.setSimpleProperty(form, "rutEmpresa", rutEmpresa);
				toPage = "noHolding";
			}							
			*/
		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));

		}

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}
		// Write logic determining how the user should be forwarded.
		forward = mapping.findForward(toPage);

		// Finish with
		return (forward);

	}
}
