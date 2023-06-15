package cl.laaraucana.satelites.certificados.deuda.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.laaraucana.satelites.Utils.CompletaUtil;
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.certificados.prepago.Utils.PrepagoUtil;
import cl.laaraucana.satelites.certificados.prepago.Utils.ServiciosPrepagoSap;
import cl.laaraucana.satelites.certificados.prepago.VO.CertificadoPrepagoVO;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaCreditoPrepagoVO;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaListaCreditoPrepagoFoliosVO;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaListaCreditoPrepagoVO;
import cl.laaraucana.satelites.webservices.model.UsuarioAfiliadoVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;
import cl.laaraucana.satelites.webservices.utils.UsuarioServiceUtilSinAS400;

/**
 * @version 	1.0
 * @author
 */
public class GetCreditosDeudaAction extends Action

{
	protected Logger logger = Logger.getLogger(this.getClass());
	
	private final static String FORWARD = "cargarDatos";
	
    public ActionForward execute(ActionMapping mapping, ActionForm _form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {

		ActionForward forward = new ActionForward(); // return value
		HttpSession sesion = request.getSession();
		
		String fechaAdmisibilidad= (String)sesion.getAttribute("fechaAdmisibilidad");
		String fechaparam= fechaAdmisibilidad.substring(6, 10) + "-" + fechaAdmisibilidad.substring(3, 5) + "-" + fechaAdmisibilidad.substring(0, 2);
		String rut = (String) sesion.getAttribute("rut");
		logger.debug("Rut: "+rut);
		
		SalidaListaCreditoPrepagoVO salidaVO = new SalidaListaCreditoPrepagoVO();
		SalidaListaCreditoPrepagoVO listaBanking = new SalidaListaCreditoPrepagoVO();
		List<SalidaCreditoPrepagoVO> listaCreditosSAP = new ArrayList<SalidaCreditoPrepagoVO>();
		List<SalidaCreditoPrepagoVO> listaCreditos = null;
		
		PrepagoUtil prepagoUtil = new PrepagoUtil();
		
		String fechaHoy = Utils.fechaSAP();

		logger.debug("Ingreso a GetCreditosPrepago SAP");
		//TODO agregar constantes de parametro de early pay off
		listaBanking = ServiciosPrepagoSap.obtenerCreditosVigentesBanking(rut, fechaparam, ServiciosConst.CREDITOS_CONDEUDA, ServiciosConst.CERTIFICADO_DEUDA);
		logger.debug("CODIGO DE ERROR DEL SERVICIO PREPAGO SAP: "+listaBanking.getCodigoError());

		if(listaBanking.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)){
			logger.debug("CODIGO DE ERROR SALIENDO DE CLIENTE SAP: "+listaBanking.getCodigoError());
			logger.debug("MENSAJE DE ERROR SALIENDO DE CLIENTE SAP: "+listaBanking.getMensaje());
		}else{
			if(null!=listaBanking.getListaCreditos() && listaBanking.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
				listaCreditosSAP.addAll(listaBanking.getListaCreditos());
				logger.debug("Datos pasados a Lista de SAP");
			}
		}


			if(!salidaVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR) && 
					!listaBanking.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)){
				
				if(listaBanking.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_VACIO)
						&& salidaVO.getListaCreditos().size()==0){
					String mensaje = listaBanking.getMensaje();
			    	request.setAttribute("title", Constants.CERTIFICATE_DATA_ERROR_TITLE);
					request.setAttribute("message", mensaje);
					return mapping.findForward("customError");
				}
				
				listaCreditos = new ArrayList<SalidaCreditoPrepagoVO>();
				
				listaCreditos.addAll(listaCreditosSAP);
				
				logger.debug("Ingresa datos al objeto CertificadoPrepagoVO");
				CertificadoPrepagoVO certificadoPrepago = new CertificadoPrepagoVO();
				certificadoPrepago.setFolio("001"); 	// Cambiar una vez que se obtenga dato
				certificadoPrepago.setNombreCompleto((String)sesion.getAttribute("nombre"));
				certificadoPrepago.setRut(rut);
				certificadoPrepago.setListaCreditos(listaCreditos);
				certificadoPrepago.setFechaAdmisibilidad(fechaAdmisibilidad);
				
				logger.debug("Termina de ingresa datos al objeto CertificadoPrepagoVO");
				for (Iterator iterator = listaCreditos.iterator(); iterator
						.hasNext();) {
					SalidaCreditoPrepagoVO salidaCreditoPrepagoVO = (SalidaCreditoPrepagoVO) iterator
							.next();
					if(fechaparam.equals(fechaHoy)){
						salidaCreditoPrepagoVO.setLiquidacion(true);
					}else{
						salidaCreditoPrepagoVO.setLiquidacion(false);
					}
					
				}
				if(fechaparam.equals(fechaHoy)){
					certificadoPrepago.setLiquidacion("1");
					sesion.setAttribute("liquidacion", "1");
				}else{
					certificadoPrepago.setLiquidacion("0");
					sesion.setAttribute("liquidacion", "0");
				}
				sesion.setAttribute("certificadoDeuda", certificadoPrepago);
				request.setAttribute("opcion", "0");
				
				SalidaListaCreditoPrepagoFoliosVO listaCreditoPrepagoFoliosVO = prepagoUtil.getCreditosDeudaConFolios(listaCreditos, true);

				sesion.setAttribute("listaCreditoPrepagoFoliosVO", listaCreditoPrepagoFoliosVO);
				//sesion.setAttribute("creditosDeuda", listaCreditoPrepagoFoliosVO);
				forward = mapping.findForward(FORWARD);

			}else				
			{
				if(listaBanking.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)){
					String mensaje = listaBanking.getMensaje();
			    	request.setAttribute("title", Constants.CERTIFICATE_DATA_ERROR_TITLE);
					request.setAttribute("message", mensaje);
					return mapping.findForward("customError");
				}else if (salidaVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)) {
			    	String mensaje = salidaVO.getMensaje();
			    	request.setAttribute("title", Constants.CERTIFICATE_DATA_ERROR_TITLE);
					request.setAttribute("message", mensaje);
					return mapping.findForward("customError");
				}
		    }
		/*}else{
			request.setAttribute("codError", "0");
			request.setAttribute("listaCreditoPrepagoFoliosVO", sesion.getAttribute("creditosDeuda"));
			request.setAttribute("opcion", "0");
			forward = mapping.findForward(FORWARD);
		}*/
	
		return (forward);
    }
}
