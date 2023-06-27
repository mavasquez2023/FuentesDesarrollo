package cl.araucana.bienestar.bonificaciones.ui.actions.caso;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.exception.SinAtributosException;
import cl.araucana.bienestar.bonificaciones.model.Caso;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;

/**
 * @version 	1.0
 * @author
 */
public class LiberarTopeCoberturaAction extends Action {

	Logger logger = Logger.getLogger(LiberarTopeCoberturaAction.class);
	
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		logger.info(">> Liberar tope cobertura");
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		String target = "success";
	

		
			
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		try {
			ServicesCasosDelegate servCasos = new ServicesCasosDelegate();
			
			Caso caso=(Caso)request.getSession(false).getAttribute("caso");	
				
			//se valida que este el caso.
			if(caso == null)
				throw new SinAtributosException();
			
			if(servCasos.liberarTopeCobertura(caso.getCasoID(),userinformation.getUsuario())){

				String msg = "Su operaci&oacute;n ha sido realizada con &eacute;xito.";
				//String subMsg = "Se marc&oacute;n como 'eliminado' el caso y si tiene egreso, este fue anulado.Favor gestionar reintegro.";
				String subMsg = "Se liber&oacute; el tope de la cobertura asociada al caso.";
				request.getSession(false).setAttribute("msg",msg);
				request.getSession(false).setAttribute("subMsg",subMsg);
				target="genericMsg";
			}else{
				//String msg = "El reembolso no puede ser eliminado porque el egreso tiene asociado más de un caso o su estado es distinto de impreso / generado";
				String msg = "El tope de cobertura no pudo ser eliminado, comunicarse con Sistemas.";
				request.getSession(false).setAttribute("msg",msg);

				String subMsg = "Ver la posibilidad de eliminarlo por DFU.";
				request.getSession(false).setAttribute("subMsg",subMsg);
				target="genericMsg"; 				
			}
			
		}catch(SinAtributosException attrE){
			String msg = "No se puede procesar debido a que no se entrego caso.";
			request.getSession(false).setAttribute("msg",msg);

			String subMsg = "Avisar a División Sistemas.";
			request.getSession(false).setAttribute("subMsg",subMsg);
			target="genericMsg"; 
		}catch (Exception e) {
			
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));

		}

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		} else {
			forward = mapping.findForward(target);

		}
		logger.info("<< Liberar tope cobertura");
		// Finish with
		return (forward);

	}
}
