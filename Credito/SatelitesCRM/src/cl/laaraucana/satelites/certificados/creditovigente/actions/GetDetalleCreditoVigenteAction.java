package cl.laaraucana.satelites.certificados.creditovigente.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.satelites.certificados.creditovigente.ServicioCreditosVigentes;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaCreditoVigenteVO;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaListaDetalleCreditoVigenteVO;

/**
 * @version 	1.0
 * @author
 */
public class GetDetalleCreditoVigenteAction extends Action

{
	private final static String FORWARD = "cargarDatos";
	protected Logger logger = Logger.getLogger(this.getClass());

    public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {

	ActionForward forward = new ActionForward();

	HttpSession sesion = request.getSession();
	
	try {
		String folio = request.getParameter("folio");

		/**
		 * Identificar Credito
		 * 0: As400
		 * 1: Banking
		 */
		SalidaListaDetalleCreditoVigenteVO salidaVO = new SalidaListaDetalleCreditoVigenteVO();
		String flagCredito = ((SalidaCreditoVigenteVO)(sesion.getAttribute("credito"))).getFlagTipoCredito();
		
		if(flagCredito.equals("0")){
			logger.debug("Ingreso al metodo cargarDetalleAs400 con el folio: "+folio);
//			salidaVO = ServicioCreditosVigentes.obtenerDetalleConsultaCreditosPorRutEnAs400(folio);
			
		}else
			if(flagCredito.equals("1")){			
			logger.debug("Ingreso al metodo cargarDetalleSAP con el folio: "+folio);
			salidaVO = ServicioCreditosVigentes.obtenerDetalleCreditosVigentesBanking(folio);
		}

		logger.debug(">> Salida llamada de nuestro clienteCuotasCreditoVigente");

		sesion.setAttribute("listaCuotas", salidaVO.getListaCuotas());
		request.setAttribute("opcion", "1");//para saber en que metodo entrar en la pagina datosCredito.jsp
		request.setAttribute("listaCuotas", salidaVO.getListaCuotas());
		request.setAttribute("codError", salidaVO.getCodigoError());
	    request.setAttribute("msg", salidaVO.getMensaje());
	    sesion.setAttribute("cuotasPendientes", String.valueOf(salidaVO.getListaCuotas().size()));
	    logger.debug("Codigo salida: "+salidaVO.getCodigoError()+", Mensaje:"+salidaVO.getMensaje());
	    
	    forward = mapping.findForward(FORWARD);	    
	} catch (Exception e) {
		String mensaje = "Error al cargar las cuotas del crédito";
		if(sesion.getAttribute("datosUsuario") == null){
			mensaje += ": Se ha terminado la sesión.";
		}
		request.setAttribute("mensaje", mensaje);
		forward = mapping.findForward("errorCarga");
	}

	return (forward);

    }
}
