package cl.laaraucana.reportesil.actions;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.reportesil.dao.ConsultaServicesDAO;
import cl.laaraucana.reportesil.dao.vo.ResumenLicenciaVO;



/**
 * @version 1.0
 * @author
 */
public class SeleccionLicenciaAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
		try {	
			String licencia = request.getParameter("licencia");
			logger.info("Licencia seleccionada:" + licencia);
			Map listaLicencias= (HashMap)request.getSession().getAttribute("mapLicencias");
			ResumenLicenciaVO resumen= (ResumenLicenciaVO)listaLicencias.get(licencia);
			logger.info("Estado:" + resumen.getEstado() + ", licimpnum= " + resumen.getNuminterno());
			
			String nombre= (String)request.getSession().getAttribute("nombre");
			resumen.setNombre(nombre);
			request.getSession().setAttribute("licencia", resumen);
			forward = mapping.findForward("success");
			
		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en SeleccionLicenciaAction: " + e.getMessage());
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error mensaje:" + e.getMessage());
			request.setAttribute("error", "1");
			forward = mapping.findForward("error");
		}
		
		return (forward);

	}
	
	    
}
