package cl.laaraucana.satelites.certificados.prepago.actions;

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
public class GetCreditosPrepagoAction extends Action

{
	protected Logger logger = Logger.getLogger(this.getClass());
	
	private final static String FORWARD = "cargarDatos";
	
    public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {

		ActionForward forward = new ActionForward(); // return value
		
		HttpSession sesion = request.getSession();
		UsuarioVO usuario = (UsuarioVO) sesion.getAttribute("datosUsuario");

		//sesion.setAttribute("rut",usuario.getRut()+"-"+usuario.getDv());
		//sesion.setAttribute("nombre", (usuario.getNombre()).toUpperCase());
	
		String rut = (String) sesion.getAttribute("rut");
			
//		EntradaCreditoPrepagoVO entradaVO = new EntradaCreditoPrepagoVO();
		SalidaListaCreditoPrepagoVO salidaVO = new SalidaListaCreditoPrepagoVO();
		SalidaListaCreditoPrepagoVO listaBanking = new SalidaListaCreditoPrepagoVO();
		//List<SalidaCreditoPrepagoVO> listaCreditosAs400 = new ArrayList<SalidaCreditoPrepagoVO>();
		List<SalidaCreditoPrepagoVO> listaCreditosSAP = new ArrayList<SalidaCreditoPrepagoVO>();
		List<SalidaCreditoPrepagoVO> listaCreditos = null;
		/*
		 * List<SalidaCreditoPrepagoVO> listaCreditoSocial = new ArrayList<SalidaCreditoPrepagoVO>();
		List<SalidaCreditoPrepagoVO> listaCreditoCes = new ArrayList<SalidaCreditoPrepagoVO>();
		List<SalidaCreditoPrepagoVO> listaCreditoEspecial = new ArrayList<SalidaCreditoPrepagoVO>();
		*/
		PrepagoUtil prepagoUtil = new PrepagoUtil();

		logger.debug("Rut: "+rut);

		String fechaHoy = Utils.fechaSAP();
		//UsuarioAfiliadoVO user = new UsuarioAfiliadoVO();
		
//		ListadoCreditosForm formPrepago = (ListadoCreditosForm) form;
		
		//if(sesion.getAttribute("creditosPrepago")==null){

			//	logger.debug("Ingreso a BPStatus");

			logger.debug("Ingreso a GetCreditosPrepago SAP");
			//TODO agregar constantes de parametro de early pay off
			listaBanking = ServiciosPrepagoSap.obtenerCreditosVigentesBanking(rut, fechaHoy, ServiciosConst.CREDITOS_CONDEUDA, ServiciosConst.CERTIFICADO_PREPAGO);
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
			//clillo 25-09-2017 Se elimina lectura de créditos AS400

			//}

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
				//clillo 25-09-2017 Se elimina lectura de créditos AS400
				
				logger.debug("Ingresa datos al objeto CertificadoPrepagoVO");
				CertificadoPrepagoVO certificadoPrepago = new CertificadoPrepagoVO();
				certificadoPrepago.setFolio("001"); 	// Cambiar una vez que se obtenga dato
				certificadoPrepago.setNombreCompleto((String)sesion.getAttribute("nombre"));
				certificadoPrepago.setRut(rut);
				certificadoPrepago.setListaCreditos(listaCreditos);
				
				logger.debug("Termina de ingresa datos al objeto CertificadoPrepagoVO");
			
				sesion.setAttribute("certificadoPrepago", certificadoPrepago);
				request.setAttribute("opcion", "0");
				
				
				SalidaListaCreditoPrepagoFoliosVO listaCreditoPrepagoFoliosVO = prepagoUtil.getCreditosConFolios(listaCreditos, true);
				
				sesion.setAttribute("listaCreditoPrepagoFoliosVO", listaCreditoPrepagoFoliosVO);
				sesion.setAttribute("creditosPrepago", listaCreditoPrepagoFoliosVO);
				
				
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
			request.setAttribute("listaCreditoPrepagoFoliosVO", sesion.getAttribute("creditosPrepago"));
			request.setAttribute("opcion", "0");
			forward = mapping.findForward(FORWARD);
		}*/
	
		return (forward);
    }
}
