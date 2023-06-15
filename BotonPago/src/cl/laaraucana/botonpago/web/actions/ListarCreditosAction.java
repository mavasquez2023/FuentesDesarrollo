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

import cl.laaraucana.botonpago.web.database.dao.CuponDAO;
import cl.laaraucana.botonpago.web.database.ibatis.domain.BpagF002;
import cl.laaraucana.botonpago.web.manager.ManagerSAP;
import cl.laaraucana.botonpago.web.utils.Constantes;
import cl.laaraucana.botonpago.web.utils.Util;
import cl.laaraucana.botonpago.web.vo.CreditoVO;
import cl.laaraucana.botonpago.web.vo.SalidaCreditosVO;

public class ListarCreditosAction extends Action {

	Logger logger = Logger.getLogger(this.getClass());

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession();
			// UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
			// User user = (User) session.getAttribute("user");
			// String rut = user.getID();

			String rutDeudor = (String) session.getAttribute("rutDeudor");
			List<CreditoVO> listaCreditos = null;
			String code = null;
			String aviso = null;

			if (session.getAttribute("listaCreditos") != null
					&& !Constantes.MUST_RELOAD_DEUDA.contains(rutDeudor.trim())) {
				logger.debug("Obtiene listaCreditos desde la sesion");
				listaCreditos = (List<CreditoVO>) session.getAttribute("listaCreditos");
				code = (String) session.getAttribute("code");

			} else {
				logger.debug("Obtiene listaCreditos desde Webservice");

				if (Constantes.MUST_RELOAD_DEUDA.contains(rutDeudor.trim())) {
					aviso = "a";
					Constantes.MUST_RELOAD_DEUDA.remove(rutDeudor.trim());
				}

				String rutsdv = rutDeudor.split("-")[0];
				//String dv = rutDeudor.split("-")[1];

				logger.debug("Consulta webservice SAP");
				ManagerSAP mgSap = new ManagerSAP();
				SalidaCreditosVO salida = mgSap.obtenerCreditos(rutDeudor);
				listaCreditos = salida.getListaCreditos();
				code = salida.getCodError();
				if (!code.equals(Constantes.getInstancia().APP_COD_ERROR)) {
					session.setAttribute("listaCreditos", listaCreditos);
					session.setAttribute("code", code);
				}

				if (listaCreditos.size() > 0) {
					// se agrega al la lista de credito el monto pagado del dia
					CuponDAO dao = new CuponDAO();
					List<BpagF002> listaPagos = dao.getPagosDelDiaByRut(rutsdv);

					for (CreditoVO creditoVO : listaCreditos) {
						for (BpagF002 bpagF002 : listaPagos) {
							int ofipro = Integer
									.valueOf(Util.getOfiPro(creditoVO.getOperacion(), creditoVO.getOrigen()));
							int crefol = Integer
									.valueOf(Util.getCreFol(creditoVO.getOperacion(), creditoVO.getOrigen()));

							if (Integer.valueOf(bpagF002.getOfiPro().trim()) == ofipro
									&& Integer.valueOf(bpagF002.getCreFol().trim()) == crefol) {
								creditoVO.setMontoPagadoDia(bpagF002.getMtoPagar().trim());
							}
						}
					}
				}

			}

			request.setAttribute("aviso", aviso);
			request.setAttribute("code", code);
			request.setAttribute("listaCreditos", listaCreditos);
			request.setAttribute("rut", rutDeudor);
			request.setAttribute("nombre", session.getAttribute("nombreDeudor"));
			return mapping.findForward("success");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error en la consulta de Creditos" + e);
			request.setAttribute("tipo", Constantes.getInstancia().MSG_TIPO_ERROR);
			request.setAttribute("titulo", "Error");
			request.setAttribute("mensaje", "" + e.getMessage());
			return mapping.findForward("MensajeForward");

		}

	}
}