package cl.laaraucana.simulacion.actions.solicitarcotizacion;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simulacion.utils.ConstantesFormalizar;
import cl.laaraucana.simulacion.utils.OrdenaUtil;
import cl.laaraucana.simulacion.xml.DatosComunas;

public class SolicitarCotizacionAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();

		/**
		 * Obtiene la información del cliente y la simulación realizada
		 * para desplegar el formulario de contacto
		 * Este action asume que el rut que se está enviando es afiliado
		 */
		
		try {
			
			
			if(request.getSession().getAttribute("resultado") == null){
				logger.debug("Error al desplegar el formulario de contacto resultado null: ");
				request.setAttribute("errorMsg", "Error; Ha ocurrido un error, vuelva a realizar la simulación");
				return mapping.findForward("error");
			}			
						
			Map<String, DatosComunas> sorted = OrdenaUtil.sortByValuesComunas(ConstantesFormalizar.COMUNAS);
			request.setAttribute("regiones", ConstantesFormalizar.REGIONES);
			request.setAttribute("comunas", sorted);
			forward = mapping.findForward("solicitarcotizacion");
		} catch (Exception e) {
			logger.debug("Error al desplegar el formulario de contacto: " + e.getMessage());
			request.setAttribute("errorMsg", "Error; Ha ocurrido un error al desplegar el formulario de solicitud");
			return mapping.findForward("error");
		}
		return forward;
	}
}
