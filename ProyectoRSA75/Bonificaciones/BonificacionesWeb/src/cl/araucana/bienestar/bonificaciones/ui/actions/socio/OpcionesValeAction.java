package cl.araucana.bienestar.bonificaciones.ui.actions.socio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.model.Vale;
import cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosDelegate;
import cl.araucana.common.ui.UserFriendlyException;

/**
 * @version 	1.0
 * @author
 */
public class OpcionesValeAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		if (!this.isTokenValid(request))
			throw new UserFriendlyException("errors.token");

		DynaValidatorActionForm dynaValidatorActionForm =
			(DynaValidatorActionForm) form;
									
		int opcion=(int)Integer.parseInt((String)dynaValidatorActionForm.get("opcion"));
		Socio socio=(Socio)request.getSession(false).getAttribute("socio");
		Vale vale=(Vale)request.getSession(false).getAttribute("vale");
		String target=null;
		ServicesConveniosDelegate delegate = new ServicesConveniosDelegate();
		
		switch(opcion){
			case 1:		delegate.asociaValeSocio(socio,vale);		
						target="success";
						break;
			case 2:		delegate.anulaVale(socio,vale);		
						target="success";
						break;
			default:	target="noservice";
						break;
		}
		//dynaValidatorActionForm.reset(mapping,request);
		// Guardar en memoria el resultado
		String referer="/getListaValesSocio.do";
		request.getSession(false).setAttribute("referer",referer);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.resetToken(request);
		return (forward);

	}
}
