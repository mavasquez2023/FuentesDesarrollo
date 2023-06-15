package cl.laaraucana.botonpago.web.actions;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.DispatchAction;

import cl.laaraucana.botonpago.web.database.dao.DatosDeudorDAO;
import cl.laaraucana.botonpago.web.forms.RutForm;
import cl.laaraucana.botonpago.web.manager.ManagerSAP;
import cl.laaraucana.botonpago.web.utils.Constantes;
import cl.laaraucana.botonpago.web.utils.Util;
import cl.laaraucana.botonpago.web.vo.CreditoVO;
import cl.laaraucana.botonpago.web.vo.SalidaCreditosVO;

/**
 * @version 1.0
 * @author
 */
public class PagoAnticipadoAction extends DispatchAction {
	Logger logger = Logger.getLogger(this.getClass());

	public ActionForward busca(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// HttpSession session = request.getSession();

		List<CreditoVO> listaCreditos = null;
		try {

			// String userid = ((User) session.getAttribute("user")).getID();
			ActionErrors errors = new ActionErrors();
			String id = request.getParameter("id").replaceAll("\\.", "");

			if (id == null || id.length() == 0) {
				logger.error("id vacio");
				errors.add("rut", new ActionMessage("error.required"));
				saveErrors(request, errors);
				return mapping.findForward("error");
			}
			// if (id.equalsIgnoreCase(userid)) {
			// logger.error("Error, no es posible cambiar sus propios perfiles");
			// errors.add("rut", new ActionMessage("error.perfiles"));
			// saveErrors(request, errors);
			// return mapping.findForward("error");
			// }

			ManagerSAP mgSap = new ManagerSAP();
			SalidaCreditosVO salida = mgSap.obtenerCreditosAdm(id);
			listaCreditos = salida.getListaCreditos();

			request.setAttribute("listaCreditos", listaCreditos);
			request.setAttribute("id", id);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("mensaje", "No se encontro el usuario");
			request.setAttribute("tipo", Constantes.getInstancia().MSG_TIPO_INFO);
		} finally {

		}
		return mapping.findForward("success");
	}

	public ActionForward ini(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("success");
	}

	public ActionForward guardar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RutForm formu = (RutForm) form;
		String error = "";
		try {
			String folio = request.getParameter("folio");
			String fecha_futura = request.getParameter("fecha");
			if (fecha_futura == null || folio == null || fecha_futura.equals("") || folio.equals("")) {
				error = "-1";
				request.setAttribute("error", error);
				return mapping.findForward("success");
			}
			fecha_futura = fecha_futura.replaceAll("/", "-");
			fecha_futura = Util.pasaFechaWEBaAs400(fecha_futura);

			HashMap<String, String> param = new HashMap<String, String>();
			param.put("folio", folio);
			param.put("fecha", fecha_futura);

			DatosDeudorDAO dao = new DatosDeudorDAO();
			boolean exito = dao.insertFechaPagoFuturo(param);

			if (exito) {
				logger.info("Fecha pago anticipado insertada exitosamente, folio:" + folio + ", fecha:" + fecha_futura);
				error = "0";
			} else {
				logger.info("Error al insertar Fecha pago anticipado, folio:" + folio + ", fecha:" + fecha_futura);
				error = "-2";
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error al guardar fechas pago futuro para el usuario " + formu.getId());
			request.setAttribute("mensaje", "Error al insertar fecha pago");
			error = "-3";
		} finally {
			try {
				request.setAttribute("error", error);
			} catch (Exception a) {
				a.printStackTrace();
			}
		}
		return mapping.findForward("success");
	}
}
