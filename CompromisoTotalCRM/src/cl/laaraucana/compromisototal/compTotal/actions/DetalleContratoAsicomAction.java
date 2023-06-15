package cl.laaraucana.compromisototal.compTotal.actions;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.compromisototal.compTotal.forms.ContratoForm;
import cl.laaraucana.compromisototal.utils.Utils;
import cl.laaraucana.compromisototal.webservice.client.asicom.ContratoDetalleAsicom;
import cl.laaraucana.compromisototal.webservice.client.asicom.VO.DetalleEntradaAsicomVO;
import cl.laaraucana.compromisototal.webservice.client.asicom.VO.DetalleSalidaAsicomVO;
import cl.laaraucana.compromisototal.webservice.client.asicom.VO.DetalleSalidaListaAsicomVO;

/**
 * 
 * @version 1.0
 * @author
 */
public class DetalleContratoAsicomAction extends Action

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

				logger.debug("Entro a detalleContratoAsicom con rut:" + rut + ", y idCredito: " + idContrato);

				DetalleEntradaAsicomVO entrada = new DetalleEntradaAsicomVO();
				entrada.setOperacion(idContrato);

				ContratoDetalleAsicom ws = new ContratoDetalleAsicom();
				logger.debug("se obtiene la salida del webservice asicom");
				DetalleSalidaListaAsicomVO salida = (DetalleSalidaListaAsicomVO) ws.call(entrada);
				List<DetalleSalidaAsicomVO> listaAsicom = salida.getListaDetalleCreditos();

				String codError = "0";
				if (listaAsicom.isEmpty()) {
					codError = "1";
				}
				Date date = new Date();

				logger.debug("envia datos al JSP rut=" + rut + "; idContrato=" + idContrato + "; fecha=" + date + "; listaAsicom" + "error="
						+ codError);

				request.setAttribute("rut", rut);
				request.setAttribute("idContrato", idContrato);
				request.setAttribute("fechaEmision", date);
				request.setAttribute("lista", listaAsicom);
				request.setAttribute("error", codError);

				forward = mapping.findForward("detAsicomOk");
				logger.debug("Salio de detalleContratoAsicom ");
			}
		} catch (Exception e) {
			// logea la excepcion y la envia a la pagina de errores.
			forward = Utils.returnErrorForward(mapping, e);
			logger.debug("error detalle asicom " + e.getMessage());
		}

		// Finish with
		return (forward);

	}
}
