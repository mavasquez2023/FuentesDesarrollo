package cl.araucana.bienestar.bonificaciones.ui.actions.convenio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.model.Convenio;
import cl.araucana.bienestar.bonificaciones.model.Talonario;
import cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosDelegate;
import cl.araucana.common.ui.UserFriendlyException;

/**
 * @version 	1.0
 * @author
 */
public class OpcionesTalonarioAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		if (!this.isTokenValid(request))
			throw new UserFriendlyException("errors.token");

		DynaValidatorActionForm dynaValidatorActionForm =(DynaValidatorActionForm) form;
		int opcion=(int)Integer.parseInt((String)dynaValidatorActionForm.get("opcion"));

		Talonario talonario=(Talonario)request.getSession(false).getAttribute("talonario");
		talonario.setInicioSecuencia((long)Long.parseLong((String)dynaValidatorActionForm.get("inicioSecuencia")));
		talonario.setFinSecuencia((long)Long.parseLong((String)dynaValidatorActionForm.get("finSecuencia")));
 		Convenio convenio=(Convenio)request.getSession(false).getAttribute("convenio");
		String target=null;
		ServicesConveniosDelegate delegate = new ServicesConveniosDelegate();

		switch(opcion){
			case 1:		delegate.registraTalonario(convenio,talonario);	
						target="success";
						break;
			case 2:		delegate.eliminaTalonario(convenio,talonario);		
						target="success";
						break;
			case 3:		delegate.actualizaTalonario(convenio,talonario);		
						target="success";
						break;
			default:	target="noservice";
						break;
		}

		String referer="/getListaTalonarios.do";
		request.getSession(false).setAttribute("referer",referer);
		request.getSession(false).setAttribute("convenio",convenio);
		// Guardar en memoria el resultado
		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.resetToken(request);
		return (forward);

	}
}
