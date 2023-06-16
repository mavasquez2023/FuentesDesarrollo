package cl.araucana.ctasfam.presentation.struts.actions;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import cl.araucana.ctasfam.business.to.AceptaPropuestaForm;

public class ArchivoempresaAction extends Action {

	// --alexis advise 15-06-2012--//

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages errors = new ActionMessages();
		ActionForward forward = new ActionForward(); // return value
		String mensaje = null;
		 Properties Param = new Properties();
		 
		 Param.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));

		 String periodo=Param.getProperty("PERIODO");
		 String proceso=Param.getProperty("PROCESO");
		 
		AceptaPropuestaForm aceptar= new AceptaPropuestaForm();
		aceptar.setProceso(proceso);

		try {

		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			mensaje="La sesión expiró o el sistema encontro una excepción";
			errors.add("name", new ActionMessage("id"));

		}

		if (!errors.isEmpty()) {
			 
			request.setAttribute("mensaje", mensaje);
			// Forward control to the appropriate 'failure' URI (change name as
			// desired)
			forward = mapping.findForward("onError");

		} else {
			 request.setAttribute("proceso", aceptar);
			forward = mapping.findForward("cargado");

		}

		// Finish with
		return (forward);

	}

}
