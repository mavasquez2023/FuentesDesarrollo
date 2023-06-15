package cl.laaraucana.satelites.certificados.creditovigente.actions;

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

import cl.laaraucana.satelites.certificados.creditovigente.ServicioCreditosVigentes;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaCreditoVigenteVO;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaListaCreditoVigenteVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;


/**
 * @version 	1.0
 * @author
 */
public class GetCreditosVigentesAction extends Action

{
	protected Logger logger = Logger.getLogger(this.getClass());
	
	private final static String FORWARD = "cargarDatos";
	
    public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	
	HttpSession sesion = request.getSession();
	String rut = (String) sesion.getAttribute("rut");
	//clillo 12-01-2018 se elimina acceso AS400
	//SalidaListaCreditoVigenteVO salidaAS400 = new SalidaListaCreditoVigenteVO();
	SalidaListaCreditoVigenteVO salidaSAP = new SalidaListaCreditoVigenteVO();
	
	List<SalidaCreditoVigenteVO> listaCreditos = new ArrayList<SalidaCreditoVigenteVO>();
		
//	if(sesion.getAttribute("creditosVigentes")==null){
			// Si los datos no estan en sesion
	//clillo 12-01-2018 se elimina acceso AS400
			//salidaAS400 = ServicioCreditosVigentes.obtenerConsultaCreditosPorRutEnAs400(rut);
			// Si falla el servicio AS400
			/*if (salidaAS400.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)) {
				sesion.removeAttribute("creditosVigentes");
				request.setAttribute("codError", "1");
				request.setAttribute("msg", salidaAS400.getMensaje());
			}else{*/
				
				salidaSAP = ServicioCreditosVigentes.obtenerCreditosVigentesBanking(rut, ServiciosConst.CREDITOS_CONDEUDA);
				// Si falla el servicio SAP
				if (salidaSAP.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)) {
					sesion.removeAttribute("creditosVigentes");
					request.setAttribute("codError", "1");
					request.setAttribute("msg", salidaSAP.getMensaje());
				}else{
					// Si no hubo error al obtener los datos
					/*if (salidaAS400.getListaCreditos() != null) {
						listaCreditos.addAll(salidaAS400.getListaCreditos());
					}*/
					if (salidaSAP.getListaCreditos() != null) {
						listaCreditos.addAll(salidaSAP.getListaCreditos());
					}
					sesion.setAttribute("creditosVigentes", listaCreditos);
					request.setAttribute("codError", "0");					
				}
			//}
			request.setAttribute("listaCreditosVigentes", listaCreditos);
//	}else{
//		//Si los datos estan en sesion
//		request.setAttribute("listaCreditosVigentes", sesion.getAttribute("creditosVigentes"));
//		request.setAttribute("codError", "0");
//	}	
		
		request.setAttribute("opcion", "0");
		return (mapping.findForward(FORWARD));
    }
}