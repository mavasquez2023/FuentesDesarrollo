package cl.laaraucana.simulacion.actions.simuladorsocial;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simulacion.utils.ConstantesFormalizar;

public class IngresarParametrosAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		request.getSession().setAttribute("resultadoPreAprobado", null);
		// request.setAttribute("oficinasList", ConstantesFormalizar.getOficinas());
		// request.setAttribute("afiliadosMap", ConstantesFormalizar.getTipoAfiliados());
		request.setAttribute("rutAfiliado", request.getParameter("rutAfiliado"));
		request.setAttribute("montoPreAprobado", request.getParameter("montoPreAprobado"));
		request.setAttribute("cod_social", ConstantesFormalizar.TIPO_PRO_SIMULACION_SOCIAL);
		request.setAttribute("max_social", ConstantesFormalizar.MAX_SOCIAL);
		request.setAttribute("min_social", ConstantesFormalizar.MIN_SOCIAL);
		request.setAttribute("cod_pensionado", ConstantesFormalizar.COD_PENSIONADO);
		request.setAttribute("min_tasa_mensual_social", ConstantesFormalizar.MIN_TASA_MENSUAL_SOCIAL);
		request.setAttribute("max_tasa_mensual_social", ConstantesFormalizar.MAX_TASA_MENSUAL_SOCIAL);
		
		/*
		String observaciones = ConstantesFormalizar.getObservaciones(ConstantesFormalizar.COD_SIMULADOR_SOCIAL, ConstantesFormalizar.SIMULADOR_SOCIAL_PARAM_OBS_INGRESO);
		if (observaciones != null) {
			request.setAttribute("observaciones", observaciones);
			forward = mapping.findForward("ingresarParametros");
		} else {
			request.setAttribute("errorMsg", "Error; Al obtener parámetros de simulación.");
			return mapping.findForward("error");
		}
		*/
		
		forward = mapping.findForward("ingresarParametros");
		
		return forward;
	}
}
