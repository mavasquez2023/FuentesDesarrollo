package cl.laaraucana.compromisototal.compTotal.actions;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.lautaro.xi.BS.WEB_Mobile.PORC_CONDONACION;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeaderResponse;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractCashFlowRequest;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractCashFlowResponse;

import cl.laaraucana.compromisototal.compTotal.forms.ContratoForm;
import cl.laaraucana.compromisototal.utils.Codigo;
import cl.laaraucana.compromisototal.utils.Utils;
import cl.laaraucana.compromisototal.webservice.client.queryCompContHeader.ClienteCompContHeader;
import cl.laaraucana.compromisototal.webservice.client.queryCompContHeader.VO.EntradaCompContHeaderVO;
import cl.laaraucana.compromisototal.webservice.client.queryCompContHeader.VO.SalidaListaCompContHeaderVO;
import cl.laaraucana.compromisototal.webservice.client.queryContractCashFlow.ClienteContractCashFlow;
import cl.laaraucana.compromisototal.webservice.client.queryContractCashFlow.VO.CondonacionCashFlowVO;
import cl.laaraucana.compromisototal.webservice.client.queryContractCashFlow.VO.EntradaCashFlowVO;
import cl.laaraucana.compromisototal.webservice.client.queryContractCashFlow.VO.SalidaCashFlowVO;
import cl.laaraucana.compromisototal.webservice.client.queryContractCashFlow.VO.SalidaListaCashFlowVO;

/**
 * @version 1.0
 * @author
 */
public class DetalleContratoBankingAction extends Action

{
	protected Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward(); // return value
		HttpSession sesion = request.getSession();

		try {

			ContratoForm datosForm = (ContratoForm) form;
			String rut = (String) sesion.getAttribute("rut");

			if(rut==null){
				rut= request.getParameter("rut");
			}
			if (rut == null) {
				Exception e = new Exception("Error con la sesion rut vacio");
				forward = Utils.returnErrorForward(mapping, e);
				logger.error("error detalle as400 " + e.getMessage());
			} else {
				String idContrato = datosForm.getId();
				// idContrato = "000000066";

				logger.debug("Entro a detalleContratoBanking con rut:" + rut + ", y idContrato: " + idContrato);

				// ContratoBankingDummyWs BankingWs = new
				// ContratoBankingDummyWs();
				// List<DetalleContratoBankingVO> detalle =
				// BankingWs.obtenerDetalleContratoBanking(rut, idContrato);

				QueryContractCashFlowRequest entrada = new QueryContractCashFlowRequest();
				entrada.setNroCuenta(idContrato);
				entrada.setINCLUYE_EPO("X");
				
				ClienteContractCashFlow bankingWS = new ClienteContractCashFlow();
				QueryContractCashFlowResponse salida = bankingWS.call(entrada);
				
				EntradaCompContHeaderVO entradaCCH= new EntradaCompContHeaderVO();
				entradaCCH.setRut(rut);
				entradaCCH.setCreditStatus("1");
				
				ClienteCompContHeader clienteCCH= new ClienteCompContHeader();
				SalidaListaCompContHeaderVO salidaCCH= (SalidaListaCompContHeaderVO)clienteCCH.call(entradaCCH);
				
				ArrayList<SalidaCashFlowVO> detalle = new ArrayList<SalidaCashFlowVO>();
				String codError = salida.getResultCode();
				
				CondonacionCashFlowVO condonacion= null;
				
				if (codError.equals("3")) {
					detalle = (ArrayList<SalidaCashFlowVO>) bankingWS.mapeo(salida).getListaBs();
					condonacion= bankingWS.mapeoCondonacion(salida);
					codError="ok";
				}
				
				
				Date date = new Date();

				logger.debug("envia datos al JSP rut=" + rut + "; idContrato=" + idContrato + "; fecha=" + date + "; listaAsicom" + "error="
						+ codError);

				request.setAttribute("rut", rut);
				request.setAttribute("idContrato", idContrato);
				request.setAttribute("fechaEmision", date);
				request.setAttribute("detalle", detalle);
				request.setAttribute("condonacion", condonacion);
				request.setAttribute("error", codError);
				request.setAttribute("mensaje", "");
				request.setAttribute("insolvencia", salidaCCH.getDetalle().get(0).getInsolvencia());

				forward = mapping.findForward("detBankingOk");
				logger.debug("Salio de detalleContratoBankig ");
			}
		} catch (Exception e) {
			// logea la excepcion y la envia a la pagina de errores.
			forward = Utils.returnErrorForward(mapping, e);
			logger.error("error detalle banking" + e.getMessage());
		}

		// Finish with
		return (forward);

	}
}
