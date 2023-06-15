package cl.laaraucana.satelites.certificados.creditocancelado.actions;

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

import cl.laaraucana.satelites.certificados.creditocancelado.ServicioCreditosCancelados;
import cl.laaraucana.satelites.certificados.creditocancelado.VO.SalidaCreditoCanceladoVO;
import cl.laaraucana.satelites.certificados.creditocancelado.VO.SalidaListaCreditosCanceladosVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;


/**
 * @version 	1.0
 * @author
 */
public class GetCreditosCanceladosAction extends Action

{
	protected Logger logger = Logger.getLogger(this.getClass());
	
	private final static String FORWARD = "cargarDatos";
	
    public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	
	HttpSession sesion = request.getSession();
	String rut = (String) sesion.getAttribute("rut");
	//clillo 12-01-2018 se elimina acceso AS400
	//SalidaListaCreditosCanceladosVO salidaAS400 = new SalidaListaCreditosCanceladosVO();
	SalidaListaCreditosCanceladosVO salidaSAP = new SalidaListaCreditosCanceladosVO();	
	List<SalidaCreditoCanceladoVO> listaCreditos = new ArrayList<SalidaCreditoCanceladoVO>();
	
	if(sesion.getAttribute("creditosCancelados")==null){
		
		logger.debug("Ingreso a try getCreditosCanceladosAS400");
		//rut="010601236";//TODO Eliminar RUT MULA, crear metodo para validar RUT AS400
		
		//clillo 12-01-2018 se elimina acceso AS400
		//salidaAS400 = ServicioCreditosCancelados.obtenerCreditosCanceladosAs400(rut,"0","0","0");
		salidaSAP = ServicioCreditosCancelados.obtenerCreditosCanceladosSAP(rut, ServiciosConst.CREDITOS_DISUELTOS);
		//rut="10601236-9";//Eliminar Rut dummy
		
		//Si alguno de los 2 servicios presenta un error, envia a pagina de error
		
		/*if(salidaAS400.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)){
			sesion.removeAttribute("creditosCancelados");
			request.setAttribute("codError", "1");
			request.setAttribute("msg", salidaAS400.getMensaje());
			
		}else{*/
			if(salidaSAP.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)){
				sesion.removeAttribute("creditosCancelados");
				request.setAttribute("codError", "1");		
				request.setAttribute("msg", salidaSAP.getMensaje());
			}else{
				/*if(salidaAS400.getListaCreditos()!=null){
					listaCreditos.addAll(salidaAS400.getListaCreditos());
				}*/	
				if(salidaSAP.getListaCreditos()!=null){
					listaCreditos.addAll(salidaSAP.getListaCreditos());
				}
			    sesion.setAttribute("creditosCancelados", listaCreditos);
			}
		//}
		request.setAttribute("listaCreditosCancelados",  listaCreditos);
	}else{
		request.setAttribute("codError", "0");
		request.setAttribute("listaCreditosCancelados",  sesion.getAttribute("creditosCancelados"));
	}
	
	request.setAttribute("opcion", "0");
	return (mapping.findForward(FORWARD));
    }
}