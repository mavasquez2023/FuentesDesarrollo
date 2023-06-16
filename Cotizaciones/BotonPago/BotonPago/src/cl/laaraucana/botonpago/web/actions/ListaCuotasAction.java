package cl.laaraucana.botonpago.web.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.botonpago.web.forms.CuotaForm;
import cl.laaraucana.botonpago.web.manager.ManagerSAP;
import cl.laaraucana.botonpago.web.utils.Constantes;
import cl.laaraucana.botonpago.web.vo.CreditoVO;
import cl.laaraucana.botonpago.web.vo.CuotasVO;
import cl.laaraucana.botonpago.web.vo.SalidaCuotasVO;

public class ListaCuotasAction extends Action {

	Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession();
			String rut = (String) session.getAttribute("rutDeudor");
			String nombre = (String) session.getAttribute("nombreDeudor");

			SalidaCuotasVO salida = new SalidaCuotasVO();
			CreditoVO infoCredito = new CreditoVO();
			List<CuotasVO> listaCuotas = new ArrayList<CuotasVO>();

			CuotaForm cuotaForm = (CuotaForm) form;
			logger.debug("Consulta cuotas webservice SAP");
			ManagerSAP mgSap = new ManagerSAP();
			salida = mgSap.obtenerCuotas(cuotaForm.getFolio());
			listaCuotas = salida.getListaCuotas();

			infoCredito = new CreditoVO();
			@SuppressWarnings("unchecked")
			List<CreditoVO> listaCreditos = (List<CreditoVO>) session.getAttribute("listaCreditos");
			for (CreditoVO creditoVO : listaCreditos) {
				if (creditoVO.getOperacion().equals(cuotaForm.getFolio())) {
					infoCredito = creditoVO;
					logger.debug("Obtiene información del crédito");
				}
			}

			request.setAttribute("infoCredito", infoCredito);
			request.setAttribute("code", salida.getCodError());
			request.setAttribute("listaCuotas", listaCuotas);
			request.setAttribute("rut", rut);
			request.setAttribute("nombre", nombre);
			return mapping.findForward("success");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error en la consulta de cuotas" + e);
			request.setAttribute("tipo", Constantes.getInstancia().MSG_TIPO_ERROR);
			request.setAttribute("titulo", "Error");
			request.setAttribute("mensaje", "" + e.getMessage());
			return mapping.findForward("MensajeForward");
		}

	}

}