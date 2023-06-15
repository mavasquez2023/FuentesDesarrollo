package cl.laaraucana.simulacion.actions.simuladorrepro;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simulacion.utils.ConstantesFormalizar;
import cl.laaraucana.simulacion.webservices.client.DataServiceUtil;
import cl.laaraucana.simulacion.webservices.client.InfoAfiliado.VO.AnexoAfiliadoVO;

public class IngresarParametrosAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		request.getSession().setAttribute("resultadoPreAprobado", null);
		//request.getSession().setAttribute("oficinasList", DataServiceUtil.getOficinas());
		//Map<String, String> anexos= new HashMap<String, String>();
		//anexos.put("", "Seleccione");
		//request.setAttribute("listaAnexos", anexos);
		request.setAttribute("anexos", "");
		//request.setAttribute("afiliadosMap", ConstantesFormalizar.getTipoAfiliados());
		//request.getSession().setAttribute("rutAfiliado", request.getParameter("rutConsulta"));

		forward = mapping.findForward("ingresarParametros");
		
		return forward;
	}
}
