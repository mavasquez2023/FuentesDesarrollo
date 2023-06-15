package cl.laaraucana.botonpago.web.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import cl.laaraucana.botonpago.web.database.dao.CuponDAO;
import cl.laaraucana.botonpago.web.database.ibatis.domain.BpagF002;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Detpago;
import cl.laaraucana.botonpago.web.database.vo.SalidaSPL;
import cl.laaraucana.botonpago.web.manager.ManagerCupon;
import cl.laaraucana.botonpago.web.manager.ManagerSPL;
import cl.laaraucana.botonpago.web.utils.CompletaUtil;
import cl.laaraucana.botonpago.web.utils.Constantes;
import cl.laaraucana.botonpago.web.utils.Util;
import cl.laaraucana.botonpago.web.webservices.clientes.loanContrPaymentRequest.ClienteLoanContrPaymentReq;
import cl.laaraucana.botonpago.web.webservices.clientes.loanContrPaymentRequest.vo.EntradaLoanPaymentVO;
import cl.laaraucana.botonpago.web.webservices.clientes.loanContrPaymentRequest.vo.SalidaLoanPaymentVO;

/**
 * @version 1.0
 * @author
 */
public class RegularizaAction extends DispatchAction

{
	static final Logger logSPL = Logger.getLogger("PagosSPL");

	public ActionForward ini(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("success");
	}

	public ActionForward recupera(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String mensaje = "";
		String tipo = "";
		String idPago = "";
		try {
			idPago = request.getParameter("idPago");
			logSPL.debug("Recibe idPago = " + idPago);

			// consultar info Pago
			SalidaSPL salidaSPL = ManagerSPL.getFlujoSPL(idPago);
			logSPL.debug("Consulta idPago = " + idPago + " estado= " + salidaSPL.getPago().getPagado());
			mensaje = "Id Pago No encontrado en SPL";
			tipo = Constantes.getInstancia().MSG_TIPO_ALERTA;
			if (salidaSPL.getPago() != null) {

				if (salidaSPL.getPago().getPagado().equals("1")) {
					Constantes.MUST_RELOAD_DEUDA.add(salidaSPL.getPago().getPagador().trim());

					logSPL.debug("El idPago = " + idPago + " se encuentra pagado!");
					// consulta DEtalle del PAGO
					ArrayList<Detpago> detalle = salidaSPL.getDetPago();

					CuponDAO daocup = new CuponDAO();
					for (Detpago detpago : detalle) {
						logSPL.debug("El idPago = " + idPago + " inicia pago a " + detpago.getDescripcion() + " = "
								+ detpago.getFolio());
						// consulta info cupon
						BpagF002 cupon = daocup.cuponByTesFol(detpago.getFolio());

						/*
						 * if (cupon.getEstProce().equals(Constantes.getInstancia().ESTADO_ANULADO)) {
						 * //crea nuevo folio tesoreria cursado y se asigna a cupon
						 * logSPL.debug("El idPago = " + idPago +
						 * " ingresa a reemplazo de cupon por anulacion"); BpagF002 newCupon =
						 * ManagerCupon.reemplazoCuponDePago(cupon); cupon = newCupon; }
						 */
						// CURSE solo cupon de pago, no Tesoreria
						ManagerCupon.cursaCupondePago(cupon, idPago);
						logSPL.debug("El idPago = " + idPago + " cursa folio " + detpago.getFolio());

						// RECUPERACION
						if (cupon.getOrigenCred().trim().equals(Constantes.getInstancia().ORIGEN_SAP)) {
							// recuperacion SAP

							ClienteLoanContrPaymentReq loanC = new ClienteLoanContrPaymentReq();
							EntradaLoanPaymentVO entrada = new EntradaLoanPaymentVO();

							entrada.setPviRut(salidaSPL.getPago().getPagador().trim());
							entrada.setItemNumber("1");
							entrada.setPiType("O");
							entrada.setFolioTesoreria(detpago.getFolio().trim());
							entrada.setId(CompletaUtil.llenaConCeros(cupon.getOfiPro().trim(), 3, true)
									+ CompletaUtil.llenaConCeros(cupon.getCreFol().trim(), 9, true));
							entrada.setInstallment("001");
							entrada.setAmount(detpago.getMonto().trim());
							entrada.setCurrency("CLP");
							entrada.setPostingDate(Util.getFechaSAP().trim());
							entrada.setValueDate(Util.getFechaSAP().trim());
							entrada.setOficinaPago("001");
							entrada.setCompExterno(idPago);

							logSPL.debug("Datos enviados a Recuperacion SAP");
							if (!cupon.getValCondGra().trim().equalsIgnoreCase("0")) {
								logSPL.debug("se envía con gravamenes");
								entrada.setArrearsContabtype("G");
								entrada.setArrearsId(CompletaUtil.llenaConCeros(cupon.getOfiPro().trim(), 3, true)
										+ CompletaUtil.llenaConCeros(cupon.getCreFol().trim(), 9, true));
								// entrada.setArrearsAmountsource(detpago.getMonto().trim());
								entrada.setArrearsAmountsource(cupon.getValGrava().trim());
								entrada.setArrearsCurrencysource("CLP");
								entrada.setArrearsAmount(cupon.getValCondGra().trim());
								entrada.setArrearsCurrency("CLP");
								entrada.setArrearsIscred("X");
							} else {
								logSPL.debug("no se envía gravamenes");
							}
							logSPL.debug("Ofipro:   " + cupon.getOfiPro());
							logSPL.debug("Crefol:   " + cupon.getCreFol());
							logSPL.debug("Id:       " + entrada.getId());
							logSPL.debug("Monto:    " + entrada.getAmount());
							logSPL.debug("Foltes:   " + entrada.getFolioTesoreria());
							logSPL.debug("Rut:      " + entrada.getPviRut());
							logSPL.debug("PostingDate: " + entrada.getPostingDate());
							logSPL.debug("ValueDate:   " + entrada.getValueDate());

							SalidaLoanPaymentVO salida = (SalidaLoanPaymentVO) loanC.call(entrada);

							logSPL.debug("Respuesta Recuperacion SAP");
							logSPL.debug("CodError: " + salida.getCodigoError());
							logSPL.debug("Mensaje: " + salida.getMensaje());

							logSPL.debug("El idPago = " + idPago + " recuperacion SAP folio " + detpago.getFolio()
									+ " con estado = " + salida.getCodigoError());
							if (salida.getCodigoError().equals("succes")) {
								mensaje = "Pago recuperado en SAP";
								tipo = Constantes.getInstancia().MSG_TIPO_EXITO;
							}
						}
					}

					logSPL.debug("<-Fin->");
				} else {
					mensaje = "IdPago NO se encuentra Pagado en SPL";
					tipo = Constantes.getInstancia().MSG_TIPO_ALERTA;
				}
			} else {
				mensaje = "IdPago NO existe en SPL";
				tipo = Constantes.getInstancia().MSG_TIPO_ALERTA;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logSPL.error("Error en idPago = " + idPago + " " + e.getMessage());
			logSPL.debug("<-Fin->");
			mensaje = "Pago NO recuperado en SAP";
			tipo = Constantes.getInstancia().MSG_TIPO_ERROR;
		}
		request.setAttribute("tipo", tipo);
		request.setAttribute("mensaje", mensaje);
		if (tipo.equals(Constantes.getInstancia().MSG_TIPO_EXITO)) {
			return mapping.findForward("success");
		}
		return mapping.findForward("error");

	}
}
