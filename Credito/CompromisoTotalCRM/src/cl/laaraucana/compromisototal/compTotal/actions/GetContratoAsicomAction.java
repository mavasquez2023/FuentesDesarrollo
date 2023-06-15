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

import cl.laaraucana.compromisototal.utils.Codigo;
import cl.laaraucana.compromisototal.utils.MapeoCodigosAsicom;
import cl.laaraucana.compromisototal.webservice.client.asicom.ContratoAsicom;
import cl.laaraucana.compromisototal.webservice.client.asicom.VO.EntradaAsicomVO;
import cl.laaraucana.compromisototal.webservice.client.asicom.VO.SalidaAsicomVO;
import cl.laaraucana.compromisototal.webservice.client.asicom.VO.SalidaListaAsicomVO;

/**
 * @version 1.0
 * @author
 */

public class GetContratoAsicomAction extends Action

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
		logger.debug("entro a GetContratoAsicom con rut = " + rut);
		ArrayList<SalidaAsicomVO> listaAsicom = new ArrayList<SalidaAsicomVO>();
		String codError = "0";
		String mensajeASICOM = "0";
		// validacion rut null obtenido de la session
		if (rut == null) {
			codError = Codigo.SESSION;
		} else {

			if (sesion.getAttribute("listaAsicom") == null) {

					ContratoAsicom ws = new ContratoAsicom();
					EntradaAsicomVO entrada = new EntradaAsicomVO();
					entrada.setRut(rut);
					try {
					SalidaListaAsicomVO salida = (SalidaListaAsicomVO) ws.call(entrada);
					codError = salida.getCodigoError();
					logger.debug("se obtiene codigo de error: " + codError);

					if (codError.equals("0")) {
						// reemplazo de codigos
						listaAsicom = salida.getListaCreditos();
						listaAsicom = new MapeoCodigosAsicom().replaceCampos(listaAsicom);
						sesion.setAttribute("listaAsicom", listaAsicom);

					}else if(codError.equals(Codigo.ERROR)) {
						
						mensajeASICOM = salida.getMensaje();
					}
				} catch (Exception e) {
					mensajeASICOM = "Error inesperado servicio de ASICOM";
					logger.error("error al consultar servicio ASICOM" + e.getMessage());

				}

			} else {
				listaAsicom = (ArrayList<SalidaAsicomVO>) sesion.getAttribute("listaAsicom");

			}

			logger.debug("Salida getContratoAsicom");

		}

		request.setAttribute("rut", rut);
		request.setAttribute("opcion", "hipotecario");
		request.setAttribute("listaAsicom", listaAsicom);
		request.setAttribute("mensajeASICOM", mensajeASICOM);
		request.setAttribute("error", codError);

		forward = mapping.findForward("datosTablas");
		// Finish with
		return (forward);

	}
}
