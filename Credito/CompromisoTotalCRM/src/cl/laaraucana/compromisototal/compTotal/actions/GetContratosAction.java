package cl.laaraucana.compromisototal.compTotal.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.compromisototal.VO.ContratoVO;
import cl.laaraucana.compromisototal.compTotal.managerSAP.SalidaUnida;
import cl.laaraucana.compromisototal.compTotal.managerSAP.UnificadorClientesSAP;
import cl.laaraucana.compromisototal.utils.Codigo;
import cl.laaraucana.compromisototal.utils.MapeoCodigosBanking;

/**
 * @version 1.0
 * @author
 */
public class GetContratosAction extends Action

{
	protected Logger logger = Logger.getLogger(this.getClass());

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward(); // return value
		HttpSession sesion = request.getSession();

		String rut = null;
		rut= request.getParameter("rut");
		if(rut==null || rut.equals("")){
			rut = (String) sesion.getAttribute("rut");
		}
		logger.debug("Entro a getContratoAs400 con rut: " + rut);
		
		String codErrorConsumo = Codigo.OK;
		ArrayList<ContratoVO> listaCompletaConsumo = new ArrayList<ContratoVO>();
		//ArrayList<Otorgamiento> listaFechaOtorgaAs400= new ArrayList<Otorgamiento>();
		String mensajeSAP = "0";
		String codErrorListaBS = "";

		if (rut == null) {
			codErrorConsumo = Codigo.SESSION;
		} else {
			if (sesion.getAttribute("listaCompletaConsumo") == null) {
				try {
					logger.debug("consulta Unuficador servicios SAP");
					UnificadorClientesSAP unificador = new UnificadorClientesSAP();
					SalidaUnida salidaUn = unificador.llamaServiciosBS(rut);
					codErrorListaBS = salidaUn.getCodigoError();

					if (codErrorListaBS.equals(Codigo.OK)) {
						listaCompletaConsumo = salidaUn.getListaBS();
						listaCompletaConsumo = new MapeoCodigosBanking().replaceCampos(listaCompletaConsumo);

					} else if (codErrorListaBS.equals(Codigo.ERROR)) {
						mensajeSAP = salidaUn.getMensaje();
					}

				} catch (Exception e) {
					e.printStackTrace();
					mensajeSAP = "Error inesperado en servicios Banking";
					logger.error("error banking " + e.getMessage());
				}

				logger.debug("codigo de error listaBS: " + codErrorListaBS);
				if (codErrorListaBS.equals(Codigo.VACIO)) {
					codErrorConsumo = Codigo.VACIO;
				}

				if (!codErrorListaBS.equals(Codigo.ERROR)) {
					// guarda en sesion solo si no existen errores
					sesion.setAttribute("listaCompletaConsumo", listaCompletaConsumo);
					sesion.setAttribute("codErrorConsumo", codErrorConsumo);

					logger.debug("guarda en sesion!!!");
				}

			} else {
				logger.debug("obtiene datos de la sesion!!!");
				listaCompletaConsumo = (ArrayList<ContratoVO>) sesion.getAttribute("listaCompletaConsumo");
				codErrorConsumo = (String) sesion.getAttribute("codErrorConsumo");
			}

		}
		request.setAttribute("opcion", "consumo");
		sesion.setAttribute("rut", rut);
		request.setAttribute("listaContratos", listaCompletaConsumo);
		request.setAttribute("error", codErrorConsumo);
		request.setAttribute("mensajeSAP", mensajeSAP);
		forward = mapping.findForward("datosTablas");

		return (forward);

	}
}
