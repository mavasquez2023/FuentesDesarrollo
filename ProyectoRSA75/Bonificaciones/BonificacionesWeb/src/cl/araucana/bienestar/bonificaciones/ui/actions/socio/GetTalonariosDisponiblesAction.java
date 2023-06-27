package cl.araucana.bienestar.bonificaciones.ui.actions.socio;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosDelegate;
import cl.araucana.bienestar.bonificaciones.vo.TalonarioVO;

/**
 * @version 	1.0
 * @author
 */
public class GetTalonariosDisponiblesAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		Logger logger = Logger.getLogger(GetTalonariosDisponiblesAction.class);
//		Socio socio=(Socio)request.getSession(false).getAttribute("socio");
		DynaValidatorActionForm dynaValidatorActionForm =(DynaValidatorActionForm) form;
										

		TalonarioVO talonarioVO = new TalonarioVO();
		if(((String)dynaValidatorActionForm.get("codigoConvenio")).trim().equals("")){
			talonarioVO.setCodigoConvenio(0);
		}
		else{
			talonarioVO.setCodigoConvenio((long)Long.parseLong((String)dynaValidatorActionForm.get("codigoConvenio")));
		}
		if(((String)dynaValidatorActionForm.get("codigoTalonario")).trim().equals("")){
			talonarioVO.setCodigoTalonario(0);
		}
		else{
			talonarioVO.setCodigoTalonario((long)Long.parseLong((String)dynaValidatorActionForm.get("codigoTalonario")));
		}
		talonarioVO.setNombreConvenio((String)dynaValidatorActionForm.get("nombreConvenio"));
		ServicesConveniosDelegate delegate = new ServicesConveniosDelegate();
		ArrayList listaTalonariosVO=delegate.getTalonariosDisponibles(talonarioVO);
		logger.debug("lista.size: "+listaTalonariosVO.size());

		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("lista.talonariosVO",listaTalonariosVO);

		// Write logic determining how the user should be forwarded.

		ActionForward forward = new ActionForward();
		forward = mapping.findForward("listaTalonariosVO");
		return (forward);

	}
}
