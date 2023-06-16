package cl.araucana.fonasa.main.actions;



import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.fonasa.dao.VO.BitacoraVO;
import cl.araucana.fonasa.main.dao.ConsultaServicesDAO;
import cl.araucana.fonasa.utils.Utils;



/**
 * @version 1.0
 * @author
 */
public class ConsultaBitacoraAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());
		

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value

		request.setAttribute("menu", "fonasa");
				
		try {
			ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
			String fechames= Utils.getFechaxMes(-1);
			List<BitacoraVO> bitacora= consultaDAO.consultaBitacora(Utils.stringToDateAS(fechames));
			logger.info("Fecha consulta bitacora:" + fechames);
			request.setAttribute("bitacora", bitacora);
			request.setAttribute("error", "0");

		} catch (Exception e) {
			logger.error("Error en Consulta Bitacora:" + e.getMessage());
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error al consultar datos:" + e.getMessage());
			request.setAttribute("error", "1");
							
		}
		forward = mapping.findForward("success");
		return (forward);

	}
	
	
	    
	    
}
