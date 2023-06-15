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
import cl.laaraucana.satelites.certificados.prepago.Utils.ServiciosPrepagoAs400;
import cl.laaraucana.satelites.certificados.prepago.Utils.ServiciosPrepagoSap;
import cl.laaraucana.satelites.certificados.prepago.VO.CertificadoPrepagoVO;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaCreditoPrepagoVO;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaListaCreditoPrepagoFoliosVO;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaListaCreditoPrepagoVO;
import cl.laaraucana.satelites.webservices.model.UsuarioAfiliadoVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;
import cl.laaraucana.satelites.webservices.utils.UsuarioServiceUtil;
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

		sesion.setAttribute("rut",usuario.getRut()+"-"+usuario.getDv());
		sesion.setAttribute("nombre", (usuario.getNombre()).toUpperCase());
	
		String rut = (String) sesion.getAttribute("rut");
//		String rut = "14143159-5";
			
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
		//rut="10601236-9";//TODO Eliminar RUT MULA
		String rut2 = CompletaUtil.llenaConCeros(rut, 11, true);
		logger.debug("Rut: "+rut);
		logger.debug("Rut arreglado: "+rut2);
		String fechaHoy = Utils.fechaSAP();
		UsuarioAfiliadoVO user = new UsuarioAfiliadoVO();
		
//		ListadoCreditosForm formPrepago = (ListadoCreditosForm) form;
		
		if(sesion.getAttribute("creditosPrepago")==null){
			
			logger.debug("Ingreso a BPStatus");
			
//			user = UsuarioServiceUtil.obtenerAfiliado("13723447-3"); //cambiar por linea inferior
			//clillo 12-01-2018 se elimina acceso AS400
			user = UsuarioServiceUtilSinAS400.obtenerAfiliado(rut);
			if(user == null || !user.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
//				String mensaje = "El usuario no ha sido encontrado para realizar el Certificado de Prepago";
				String mensaje = user.getMensaje();
				return new ActionForward(mapping.findForward("error").getPath()+"?errorMsg="+mensaje, false);
			}else{
				
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
				/*if (!listaBanking.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)) {
					logger.debug("Ingreso a GetCreditosPrepago AS400");
					salidaVO = ServiciosPrepagoAs400.obtenerCreditosPrepagoAs400(CompletaUtil.llenaConCeros(rut, 11, true).trim());
					logger.debug("CODIGO DE ERROR DEL SERVICIO PREPAGO AS400: "+ salidaVO.getCodigoError());
					if (salidaVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)) {
						logger.debug("CODIGO DE ERROR SALIENDO DE CLIENTE AS400: "
								+ salidaVO.getCodigoError());
						logger.debug("MENSAJE DE ERROR SALIENDO DE CLIENTE AS400: "
								+ salidaVO.getMensaje());
					} else {
						if (null != salidaVO.getListaCreditos()&& salidaVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
							listaCreditosAs400.addAll(salidaVO.getListaCreditos());
							logger.debug("Datos pasados a Lista de AS400");
						}
					}
				}*/
				
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
				//clillo 25-09-2017 Se elimina lectura de créditos AS400
				//listaCreditos.addAll(listaCreditosAs400);
				
				/*listaCreditoSocial = prepagoUtil.getCreditosPrepagoSocialesList(listaCreditos);
				if(listaCreditoSocial.isEmpty())
					listaCreditoSocial = null;
				
				listaCreditoCes = prepagoUtil.getCreditosPrepagoCesList(listaCreditos);
				if(listaCreditoCes.isEmpty())
					listaCreditoCes = null;
				
				listaCreditoEspecial = prepagoUtil.getCreditosPrepagoEspecialList(listaCreditos);
				if(listaCreditoEspecial.isEmpty())
					listaCreditoEspecial = null;
				 */
				logger.debug("Ingresa datos al objeto CertificadoPrepagoVO");
				CertificadoPrepagoVO certificadoPrepago = new CertificadoPrepagoVO();
				certificadoPrepago.setFolio("001"); 	// Cambiar una vez que se obtenga dato
				certificadoPrepago.setNombreCompleto(user.getNombreAfiliado());
				certificadoPrepago.setRut(rut);
				certificadoPrepago.setListaCreditos(listaCreditos);
				
				/*
				certificadoPrepago.setFechaMMYY(PrepagoUtil.getMesYAno());
				certificadoPrepago.setFechaDDMM(PrepagoUtil.getUltimoDiaYMes());
				certificadoPrepago.setFechaCompleta(Utils.getFechaCompleta());
				certificadoPrepago.setCreditoSocial(listaCreditoSocial);
				certificadoPrepago.setCreditoCes(listaCreditoCes);
				certificadoPrepago.setCreditoEspecial(listaCreditoEspecial);
				*/
				logger.debug("Termina de ingresa datos al objeto CertificadoPrepagoVO");
			
				sesion.setAttribute("certificadoPrepago", certificadoPrepago);
				request.setAttribute("opcion", "0");
				
				
				SalidaListaCreditoPrepagoFoliosVO listaCreditoPrepagoFoliosVO = prepagoUtil.getCreditosConFolios(listaCreditos, true);
				
				//List<SalidaDetalleCuotasEarlyPayOff2> pensionadosList = prepagoUtil.getDetalleCuotasTrabajadorIndependiente(listaCreditos, false);
				//List<SalidaDetalleCuotasEarlyPayOff2> trabajadoresList = prepagoUtil.getDetalleCuotasTrabajadorIndependiente(listaCreditos, true);
				
				sesion.setAttribute("listaCreditoPrepagoFoliosVO", listaCreditoPrepagoFoliosVO);
				//sesion.setAttribute("pensionadosList", pensionadosList);
				//sesion.setAttribute("trabajadoresList", trabajadoresList);
				
				
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
		}else{
			request.setAttribute("codError", "0");
			request.setAttribute("listaCreditosPrepago", sesion.getAttribute("creditosPrepago"));
			request.setAttribute("opcion", "0");
			forward = mapping.findForward(FORWARD);
		}
	
		return (forward);
    }
}
