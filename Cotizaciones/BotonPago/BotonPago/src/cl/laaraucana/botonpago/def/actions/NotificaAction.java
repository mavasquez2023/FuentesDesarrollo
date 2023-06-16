package cl.laaraucana.botonpago.def.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.botonpago.def.forms.NotiForm;
import cl.laaraucana.botonpago.web.cobol.call.RecuperacionCall;
import cl.laaraucana.botonpago.web.cobol.vo.EntradaSalidaRecuperaVO;
import cl.laaraucana.botonpago.web.database.dao.CuponDAO;
import cl.laaraucana.botonpago.web.database.ibatis.domain.BpagF002;
import cl.laaraucana.botonpago.web.database.ibatis.domain.Detpago;
import cl.laaraucana.botonpago.web.database.vo.SalidaSPL;
import cl.laaraucana.botonpago.web.manager.ManagerCupon;
import cl.laaraucana.botonpago.web.manager.ManagerSPL;
import cl.laaraucana.botonpago.web.utils.CompletaUtil;
import cl.laaraucana.botonpago.web.utils.Constantes;
import cl.laaraucana.botonpago.web.utils.EncriptUtils;
import cl.laaraucana.botonpago.web.utils.Util;
import cl.laaraucana.botonpago.web.webservices.clientes.loanContrPaymentRequest.ClienteLoanContrPaymentReq;
import cl.laaraucana.botonpago.web.webservices.clientes.loanContrPaymentRequest.vo.EntradaLoanPaymentVO;
import cl.laaraucana.botonpago.web.webservices.clientes.loanContrPaymentRequest.vo.SalidaLoanPaymentVO;

/**
 * @version 1.0
 * @author
 */
public class NotificaAction extends Action

{
	static final Logger logSPL = Logger.getLogger("PagosSPL");

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		NotiForm formd = (NotiForm) form;
		String idPago = "";
		try {
			//recibir idPAGO desde SPL
			String trxHex = formd.getTrx();
			char[] trxChar = trxHex.toCharArray();
			byte[] trxBytes = Hex.decodeHex(trxChar);
			String vectorHex = formd.getVector();
			char[] vectorChar = vectorHex.toCharArray();
			byte[] vectorBytes = Hex.decodeHex(vectorChar);
			byte[] id = EncriptUtils.decode(trxBytes, Constantes.getInstancia().KEY_ENCODE, vectorBytes);
			idPago = new String(id, "UTF-8");
			logSPL.debug("<-Inicio->");
			logSPL.debug("Recibe idPago = " + idPago);

			//consultar info Pago
			SalidaSPL salidaSPL = ManagerSPL.getFlujoSPL(idPago);
			logSPL.debug("Consulta idPago = " + idPago + " estado= " + salidaSPL.getPago().getPagado());
			if (salidaSPL.getPago() != null && salidaSPL.getPago().getPagado().equals("1")) {

				Constantes.MUST_RELOAD_DEUDA.add(salidaSPL.getPago().getPagador().trim());

				logSPL.debug("El idPago = " + idPago + " se encuentra pagado!");
				/*
					//INSERTAR REGISTRO LIBRO BANCO
					DatosTransferenciaVO transf = new DatosTransferenciaVO();
					transf.setBanco(String.valueOf(salidaSPL.getMedioPago().getCodbanco()).trim());
					transf.setNumCtaCte(salidaSPL.getConvenio().getCtacte().trim());
					transf.setFechaMovimiento(Util.formatFecha(salidaSPL.getPago().getFchContable().trim()));
					transf.setRutCliente(salidaSPL.getPago().getPagador().trim().split("-")[0]);
					transf.setDvCliente(salidaSPL.getPago().getPagador().trim().split("-")[1]);
					transf.setMonto(salidaSPL.getPago().getMonto().trim());
					transf.setTipoAbono("A");
					transf.setCodigoOperacionInterna("90");
					transf.setNumeroDeposito(idPago);

					transf = ManagerLibroBanco.registrarLibroBanco(transf);
					logSPL.debug("El idPago = " + idPago + " se registra en libro banco con resultado: " + transf.getFlag());
				*/
				//consulta DEtalle del PAGO
				ArrayList<Detpago> detalle = salidaSPL.getDetPago();

				CuponDAO daocup = new CuponDAO();
				for (Detpago detpago : detalle) {
					logSPL.debug("El idPago = " + idPago + " inicia pago a " + detpago.getDescripcion() + " = " + detpago.getFolio());
					//consulta info cupon
					BpagF002 cupon = daocup.cuponByTesFol(detpago.getFolio());

					/*
						if (cupon.getEstProce().equals(Constantes.getInstancia().ESTADO_ANULADO)) {
							//crea nuevo folio tesoreria cursado y se asigna a cupon
							logSPL.debug("El idPago = " + idPago + " ingresa a reemplazo de cupon por anulacion");
							BpagF002 newCupon = ManagerCupon.reemplazoCuponDePago(cupon);
							cupon = newCupon;
						}
					 */
					//CURSE solo cupon de pago, no Tesoreria
					ManagerCupon.cursaCupondePago(cupon, idPago);
					logSPL.debug("El idPago = " + idPago + " cursa folio " + detpago.getFolio());

					//RECUPERACION
					if (cupon.getOrigenCred().trim().equals(Constantes.getInstancia().ORIGEN_SAP)) {
						//recuperacion SAP

						ClienteLoanContrPaymentReq loanC = new ClienteLoanContrPaymentReq();
						EntradaLoanPaymentVO entrada = new EntradaLoanPaymentVO();

						entrada.setPviRut(salidaSPL.getPago().getPagador().trim());
						entrada.setItemNumber("1");
						entrada.setPiType("O");
						entrada.setFolioTesoreria(detpago.getFolio().trim());
						entrada.setId(CompletaUtil.llenaConCeros(cupon.getOfiPro().trim(), 3, true) + CompletaUtil.llenaConCeros(cupon.getCreFol().trim(), 9, true));
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
							entrada.setArrearsId(CompletaUtil.llenaConCeros(cupon.getOfiPro().trim(), 3, true) + CompletaUtil.llenaConCeros(cupon.getCreFol().trim(), 9, true));
							//entrada.setArrearsAmountsource(detpago.getMonto().trim());
							entrada.setArrearsAmountsource(cupon.getValGrava().trim());
							entrada.setArrearsCurrencysource("CLP");
							entrada.setArrearsAmount(cupon.getValCondGra().trim());
							entrada.setArrearsCurrency("CLP");
							entrada.setArrearsIscred("X");
						}else{
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

						logSPL.debug("El idPago = " + idPago + " recuperacion SAP folio " + detpago.getFolio() + " con estado = " + salida.getCodigoError());

					} else {
						//recuperacion AS400
						RecuperacionCall recuperaAs = new RecuperacionCall();
						EntradaSalidaRecuperaVO entrada = new EntradaSalidaRecuperaVO();
						entrada.setOfipro(cupon.getOfiPro().trim());
						entrada.setCrefol(cupon.getCreFol().trim());
						entrada.setMonpag(detpago.getMonto().trim());
						entrada.setTipocr(Util.isAcuerdo(cupon.getTipoCredito().trim()));
						entrada.setFoltes(cupon.getTesFol().trim());
						entrada.setCoderr("0");

						logSPL.debug("Datos enviados a Recuperacion As400");
						logSPL.debug("Ofipro:   " + entrada.getOfipro());
						logSPL.debug("Crefol:   " + entrada.getCrefol());
						logSPL.debug("Monto:    " + entrada.getMonpag());
						logSPL.debug("Tipocr:   " + entrada.getTipocr());
						logSPL.debug("Foltes:   " + entrada.getFoltes());
						logSPL.debug("CodError: " + entrada.getCoderr());

						entrada = recuperaAs.recuperaCredito(entrada);

						logSPL.debug("Respuesta Recuperacion As400");
						logSPL.debug("Ofipro:   " + entrada.getOfipro());
						logSPL.debug("Crefol:   " + entrada.getCrefol());
						logSPL.debug("Monto:    " + entrada.getMonpag());
						logSPL.debug("Tipocr:   " + entrada.getTipocr());
						logSPL.debug("Foltes:   " + entrada.getFoltes());
						logSPL.debug("CodError: " + entrada.getCoderr());

						logSPL.debug("El idPago = " + idPago + " recuperacion AS400 folio " + detpago.getFolio() + "con estado = " + entrada.getCoderr());

					}

				}

				logSPL.debug("<-Fin->");
			}

		} catch (Exception e) {
			e.printStackTrace();
			logSPL.error("Error en idPago = " + idPago + " " + e.getMessage());
			logSPL.debug("<-Fin->");
		}
		return null;

	}
}
