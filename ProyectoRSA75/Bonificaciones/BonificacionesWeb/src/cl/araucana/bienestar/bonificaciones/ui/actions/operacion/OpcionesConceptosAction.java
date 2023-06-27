package cl.araucana.bienestar.bonificaciones.ui.actions.operacion;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.model.Concepto;
import cl.araucana.bienestar.bonificaciones.serv.ServicesOperacionesDelegate;
import cl.araucana.common.ui.UserFriendlyException;

/**
 * @version 	1.0
 * @author
 */
public class OpcionesConceptosAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		if (!this.isTokenValid(request))
			throw new UserFriendlyException("errors.token");

		Logger logger=Logger.getLogger(OpcionesConceptosAction.class);
		DynaValidatorActionForm dynaValidatorActionForm =(DynaValidatorActionForm) form;

		int opcion=(int)Integer.parseInt((String)dynaValidatorActionForm.get("opcion"));
		Concepto concept=(Concepto)request.getSession(false).getAttribute("concepto");
		if(opcion==1 || opcion==2){
			concept.setDescripcion((String)dynaValidatorActionForm.get("descripcion"));
			concept.setCuentaAcreedor(Long.parseLong((String)dynaValidatorActionForm.get("cuentaAcreedora")));
			concept.setCuentaDeudor(Long.parseLong((String)dynaValidatorActionForm.get("cuentaDeudora")));
			concept.setTesoreriaArea(Long.parseLong((String)dynaValidatorActionForm.get("areaTesoreria")));
			concept.setTesoreriaConceptoIngreso(Long.parseLong((String)dynaValidatorActionForm.get("conceptoTesoreriaIngreso")));
			concept.setTesoreriaConceptoEgreso(Long.parseLong((String)dynaValidatorActionForm.get("conceptoTesoreriaEgreso")));
		}
		
		// Cambio Concepto: Solo un tipo
		//concept.setTipo((String)dynaValidatorActionForm.get("tipo"));
		
		String target=null;

		logger.debug("concept.getDescripcion():"+concept.getDescripcion());
		logger.debug("concept.getCodigo():"+concept.getCodigo());
		ServicesOperacionesDelegate delegate = new ServicesOperacionesDelegate();
		String referer="/getListaConceptos.do";
		
		switch(opcion){
			case 1:			delegate.creaConcepto(concept);	
							target="success";
							break;
			case 2:			delegate.actualizaConcepto(concept);		
							target="success";
							break;
			case 3:			delegate.eliminaConcepto(concept);
							target="success";
							break;
		}
		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("referer",referer);
		
		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.resetToken(request);
		return (forward);

	}
}
