package cl.laaraucana.botonpago.web.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.botonpago.web.database.ibatis.domain.BpagF002;
import cl.laaraucana.botonpago.web.manager.ManagerCupon;
import cl.laaraucana.botonpago.web.utils.Constantes;

public class HistorialDePagoAction extends Action {
	Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("HistorialDePagoAction ejecutandose:");
		try {
			HttpSession session = request.getSession();
			String rutDeudor = (String) session.getAttribute("rutDeudor");

			List<BpagF002> cupones = null;
			try {
				cupones = ManagerCupon.cuponesByEstadoAndRut(rutDeudor, Constantes.getInstancia().ESTADO_CURSADO);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error al obtener listado de cupones:" + rutDeudor + " "
						+ Constantes.getInstancia().ESTADO_CURSADO);
				throw new Exception("Error al obtener listado de cupones");
			}

			if (cupones.size() == 0) {
				request.setAttribute("code", "vacio");
				logger.info("El usuario " + rutDeudor + " no registra pagos");
			} else {
				request.setAttribute("code", "success");
				logger.info("Se obtienen " + cupones.size() + "  pagos para el deudor " + rutDeudor);
			}
			request.setAttribute("listaCupones", cupones);
			request.setAttribute("rut", rutDeudor);
			String nombreDeudor = session.getAttribute("nombreDeudor").toString();
			request.setAttribute("nombre", nombreDeudor);
			return mapping.findForward("success");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("code", "error");
			request.setAttribute("tipo", Constantes.getInstancia().MSG_TIPO_ERROR);
			request.setAttribute("titulo", "Error");
			request.setAttribute("mensaje", "" + e.getMessage());
			return mapping.findForward("MensajeForward");
		}
	}
}