package cl.araucana.bienestar.bonificaciones.ui.actions.convenio;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.model.Convenio;
import cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosDelegate;
import cl.araucana.common.ui.UserFriendlyException;

/**
 * @version 	1.0
 * @author
 */
public class OpcionesConvenioAction extends Action {

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
		Convenio convenio=(Convenio)request.getSession(false).getAttribute("convenio");
		if(opcion==1 || opcion==4){
			convenio.setNombre((String)dynaValidatorActionForm.get("nombre"));
			convenio.setCodigoConcepto(Long.parseLong((String)dynaValidatorActionForm.get("concepto")));
			convenio.setRut((String)dynaValidatorActionForm.get("rut"));
			convenio.setDvRut((String)dynaValidatorActionForm.get("dv"));
			convenio.setNumeroMaximoCuotas(Integer.parseInt((String)dynaValidatorActionForm.get("numCuotasExternas")));
		}

		String target=null;

		ServicesConveniosDelegate delegate = new ServicesConveniosDelegate();
		switch(opcion){
			case 1:		delegate.registraConvenio(convenio);	
						target="success";
						break;
			case 2:		delegate.activaConvenio(convenio);		
						target="success";
						break;
			case 3:		delegate.desactivaConvenio(convenio);
						target="success";
						break;
			case 4:		delegate.actualizaConvenio(convenio);
						target="success";
						break;
			default:	target="noservice";
						break;
		}
		//dynaValidatorActionForm.reset(mapping,request);
		// Guardar en memoria el resultado
		String referer="/getListaConvenios.do";
		request.getSession(false).setAttribute("referer",referer);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.resetToken(request);
		return (forward);


	}
}
