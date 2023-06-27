package cl.araucana.bienestar.bonificaciones.ui.actions.caso;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.model.Caso;
import cl.araucana.bienestar.bonificaciones.model.Evento;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;
import cl.araucana.bienestar.bonificaciones.vo.UsuarioVO;
import cl.araucana.common.ui.UserFriendlyException;

/**
 * @version 	1.0
 * @author
 */
public class RegistraEventoAction extends Action {

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
		Caso caso=(Caso)request.getSession(false).getAttribute("caso");
		Evento evento=(Evento)request.getSession(false).getAttribute("evento");
		evento.setComentario((String)dynaValidatorActionForm.get("comentario"));
		String target=null;
		ServicesCasosDelegate delegate = new ServicesCasosDelegate();
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		UsuarioVO user=new UsuarioVO();
		user.setNombre(userinformation.getNombres());
		user.setApellidoMaterno(userinformation.getApellidoMaterno());
		user.setApellidoPaterno(userinformation.getApellidoPaterno());
		user.setCodigoOficina(userinformation.getOficina());
		user.setUsuario(userinformation.getUsuario());
		
	
		switch(opcion){
			case 1:		delegate.registraEvento(caso.getCasoID(),evento,user);		
						target="success";
						break;
			default:	target="noservice";
						break;
		}
		//dynaValidatorActionForm.reset(mapping,request);
		// Guardar en memoria el resultado
		String referer="/getListaEventos.do";
		request.getSession(false).setAttribute("referer",referer);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.saveToken(request);
		return (forward);

	}
}
