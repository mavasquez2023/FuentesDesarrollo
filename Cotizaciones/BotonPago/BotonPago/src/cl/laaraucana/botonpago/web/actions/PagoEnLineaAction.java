package cl.laaraucana.botonpago.web.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.core.registry.User;
import cl.laaraucana.botonpago.web.database.dao.CuponDAO;
import cl.laaraucana.botonpago.web.forms.SelectCreditosForm;
import cl.laaraucana.botonpago.web.manager.ManagerCupon;
import cl.laaraucana.botonpago.web.spl.GeneraXMLEncriptado;
import cl.laaraucana.botonpago.web.spl.vo.Banco;
import cl.laaraucana.botonpago.web.spl.vo.Folio;
import cl.laaraucana.botonpago.web.spl.vo.Folios;
import cl.laaraucana.botonpago.web.spl.vo.Pago;
import cl.laaraucana.botonpago.web.utils.Constantes;
import cl.laaraucana.botonpago.web.utils.Util;
import cl.laaraucana.botonpago.web.vo.CreditoVO;

/**
 * @version 1.0
 * @author
 */
public class PagoEnLineaAction extends Action {

	protected Logger logger = Logger.getLogger(this.getClass());

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			SelectCreditosForm datosform = (SelectCreditosForm) form;
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			List<CreditoVO> listaCreditos = (List<CreditoVO>) session.getAttribute("listaCreditos");
			String op = datosform.getOp();
			String rutDeudor = ((String) session.getAttribute("rutDeudor")).trim().toUpperCase();
			String nombre = ((String) session.getAttribute("nombreDeudor")).trim().toUpperCase();
			//			System.out.println(rutDeudor);
			String correlativo = "";
			try {
				CuponDAO cdao = new CuponDAO();
				correlativo = cdao.getCorrelativo();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error al obtener el correlativo para SPL");
				throw new Exception("Error al obtener correlativo SPL");
			}

			String paramMonto = "";
			String[] paramListSelect = null;

			// llenar objeto para el xml enviado a SPL
			List<Folio> folios = new ArrayList<Folio>();

			if (op.equals("directo")) {
				paramListSelect = new String[1];
				paramListSelect[0] = datosform.getFolio();

			} else if (op.equals("monto")) {
				paramListSelect = new String[1];
				paramListSelect[0] = datosform.getFolio();

			} else if (op.equals("todos")) {
				int i = 0;
				paramListSelect = new String[listaCreditos.size()];
				for (CreditoVO creditoVO : listaCreditos) {
					paramListSelect[i] = creditoVO.getOperacion();
					i++;
				}
			} else if (op.equals("select")) {
				paramListSelect = datosform.getCreditos();
			}

			if (paramListSelect.length > 5) {
				request.setAttribute("tipo", Constantes.getInstancia().MSG_TIPO_ERROR);
				request.setAttribute("titulo", "Error");
				request.setAttribute("mensaje", "No es posible pagar mas de 5 creditos a la vez");
				return mapping.findForward("SoloMensajeForward");
			}

			for (CreditoVO creditoVO : listaCreditos) {
				for (int i = 0; i < paramListSelect.length; i++) {
					if (creditoVO.getOperacion().equals(paramListSelect[i])) {
						Folio folio = new Folio();

						if (op.equals("monto")) {
							paramMonto = datosform.getMonto().trim().replace(".", "").replace(",", ".");// obtengo monto
						} else {
							paramMonto = creditoVO.getTotalPagar().trim().replace(".", "").replace(",", ".");// obtengo monto
						}
						//validar monto
						int monto = Integer.parseInt(paramMonto);
						int min = Integer.parseInt(creditoVO.getPagoMinimo());
						int max = Integer.parseInt(creditoVO.getTotalDeuda());
						if (monto < min || monto > max) {
							//error
							request.setAttribute("tipo", Constantes.getInstancia().MSG_TIPO_ALERTA);
							request.setAttribute("titulo", "Mensaje");
							request.setAttribute("mensaje", "El monto ingresado debe ser mayor o igual al \"Pago mínimo\" y menor o igual al \"Total deuda\"");
							return mapping.findForward("SoloMensajeForward");
						}
						if (monto == 0) {
							request.setAttribute("tipo", Constantes.getInstancia().MSG_TIPO_ALERTA);
							request.setAttribute("titulo", "Mensaje");
							request.setAttribute("mensaje", "El monto a pagar no puede ser igual a cero");
							return mapping.findForward("SoloMensajeForward");

						}
						//valida el pago d el dia
						if(creditoVO.getMontoPagadoDia()!=null){
							if (!creditoVO.getMontoPagadoDia().trim().isEmpty()) {
								request.setAttribute("tipo", Constantes.getInstancia().MSG_TIPO_ALERTA);
								request.setAttribute("titulo", "Mensaje");
								request.setAttribute("mensaje", "No es posible realizar más de un pago al día por cada crédito.");
								return mapping.findForward("SoloMensajeForward");
							}
						}

						HashMap<String, String> ma = null;
						try {
							ma = ManagerCupon.crearCuponDePago(creditoVO, rutDeudor, Constantes.getInstancia().TIPO_PAGO_LINEA, paramMonto);
						} catch (Exception e) {
							e.printStackTrace();
							logger.error("Error al generar cupon de pago:" + creditoVO + " " + rutDeudor + " " + Constantes.getInstancia().TIPO_PAGO_LINEA + " " + paramMonto);
							throw new Exception("Error al generar cupón de pago en linea");
						}
						String tesFolio = ma.get("folioTesoreria");

						folio.setNumero(tesFolio);
						folio.setMonto(paramMonto);
						folio.setDetalle("Pago " + (i + 1) + " de " + paramListSelect.length);
						folios.add(folio);
						continue;
					}
				}
			}

			Pago pagos = new Pago();

			List<String> banco = new ArrayList<String>();
			banco.add("BCI");
			banco.add("TBC");
			banco.add("BCH");
			banco.add("BSA");
			banco.add("BES");
			banco.add("BIT");

			Folios foliosPadre = new Folios();
			foliosPadre.setFolio(folios);

			pagos.setGlosa(Constantes.getInstancia().GLOSA_PAGO);
			pagos.setPagador(rutDeudor);
			pagos.setRutCliente(rutDeudor.replaceAll("-", ""));
			pagos.setCorrelativo(correlativo);
			pagos.setFecha(Util.getFechaSPL());
//			pagos.setUrlRetorno("http://172.16.137.230:9082/BotonPago/retorno.do");
//			pagos.setUrlNotificacion("http://172.16.137.230:9082/BotonPago/notifica.do");
			pagos.setUrlRetorno(Constantes.getInstancia().URL_RETORNO);
			pagos.setUrlNotificacion(Constantes.getInstancia().URL_NOTIFICACION);
			pagos.setBancos(new Banco(banco));
			pagos.setFolios(foliosPadre);
			HashMap<String, String> resp = null;
			try {
				resp = GeneraXMLEncriptado.generarXML(pagos);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error al generar xml encriptado SPL");
				throw new Exception("Error al generar xml encriptado SPL");
			}

			String contextPath = Constantes.getInstancia().URL_SPL + "sistema=PDD&xml=" + resp.get("xml").toUpperCase() + "&vector=" + resp.get("vector").toUpperCase() + "";
			// response.sendRedirect(response.encodeRedirectURL(contextPath));

			return new ActionForward(contextPath, true);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("tipo", Constantes.getInstancia().MSG_TIPO_ERROR);
			request.setAttribute("titulo", "Error");
			request.setAttribute("mensaje", "" + e.getMessage());
			return mapping.findForward("MensajeForward");
		}
	}
}
