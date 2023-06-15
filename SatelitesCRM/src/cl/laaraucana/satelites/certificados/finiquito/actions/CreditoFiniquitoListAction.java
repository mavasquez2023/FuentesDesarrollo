
package cl.laaraucana.satelites.certificados.finiquito.actions;

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
import cl.laaraucana.satelites.certificados.finiquito.forms.CertificadoFiniquitoForm;
import cl.laaraucana.satelites.certificados.finiquito.utils.FiniquitoLocalUtil;
import cl.laaraucana.satelites.certificados.prepago.Utils.PrepagoUtil;
import cl.laaraucana.satelites.certificados.prepago.Utils.ServiciosPrepagoAs400;
import cl.laaraucana.satelites.certificados.prepago.Utils.ServiciosPrepagoSap;
import cl.laaraucana.satelites.certificados.prepago.VO.CertificadoPrepagoVO;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaCreditoPrepagoVO;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaListaCreditoPrepagoFoliosVO;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaListaCreditoPrepagoVO;
import cl.laaraucana.satelites.webservices.client.EarlyPayOffSimulation2.VO.SalidaDetalleCuotasEarlyPayOff2;
import cl.laaraucana.satelites.webservices.model.DetalleEmpresaAfiliado;
import cl.laaraucana.satelites.webservices.model.UsuarioAfiliadoVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;
import cl.laaraucana.satelites.webservices.utils.UsuarioServiceUtil;
import cl.laaraucana.satelites.webservices.utils.UsuarioServiceUtilSinAS400;

/**
 * @version 1.0
 * @author
 */
public class CreditoFiniquitoListAction extends Action

{
	protected Logger log = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		//ActionMessages errors = new ActionMessages();
		ActionForward forward = new ActionForward(); // return value
		CertificadoFiniquitoForm form = (CertificadoFiniquitoForm) _form;
		FiniquitoLocalUtil finiquitoUtil = new FiniquitoLocalUtil();
		
		HttpSession sesion = request.getSession();
		
		// listas de distintos tipos de creditos


		//webService creditos vigentes As400
		try {
			
			if(sesion.getAttribute("certificadoFiniquito") != null){
				sesion.removeAttribute("certificadoFiniquito");
			}
			
			/*******************************************************Dummy***************************************************************/
			
			
			/*FiniquitoDummy dummy = new FiniquitoDummy();
			
			CertificadoFiniquitoVO certificadoFiniquito = dummy.getFiniquitoDummyVO("17308684-9", form.getFechaFiniquito());
			sesion.setAttribute("certificadoFiniquito", certificadoFiniquito);
			if(true){
				return forward = mapping.findForward("success");
			}*/
			
			
			
			/*******************************************************FIN Dummy***************************************************************/
			
			
			
			/*******************************************************QueryBPStatus*******************************************************/
						
			UsuarioVO usuarioActual = (UsuarioVO)sesion.getAttribute("datosUsuario");
			String rutEmpleado = usuarioActual.getRut()+"-"+usuarioActual.getDv();
			if(usuarioActual == null){
				request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
				request.setAttribute("message", Constants.SESION_EXPIRED);
				return mapping.findForward("customError");
			}
			
			//clillo 12-01-2018 se elimina acceso AS400			
			UsuarioAfiliadoVO user = UsuarioServiceUtilSinAS400.obtenerAfiliado(rutEmpleado);
			System.out.println("el rut del empleado es "+rutEmpleado);
			String rutEmpresa = form.getRutEmpleado().replace(".", "");
			
			//System.out.println("imprimiendo el codigo de error y el mensaje "+user.getCodigoError());
			//System.out.println(user.getMensaje());
			
			if(!user.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
				request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
				request.setAttribute("message", user.getMensaje());
				return mapping.findForward("customError");
			}
			
			//valida si la empresa logeada contiene al bp con el rut ingresado para generar el certificado
			
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////DUMMY cambiar rutEmpresa 
			//as400 70016160-9  sap 70000410-4
			
			
			
			DetalleEmpresaAfiliado detalleEmpresa = finiquitoUtil.obtenerDetalleEmpresa(rutEmpresa, user);
			if(detalleEmpresa == null){
				request.setAttribute("error", "El rut ingresado no pertenece a la empresa");
				return mapping.findForward("inicio");
			}else{
				System.out.println("==================================");
				System.out.println(ServiciosConst.RES_SERVICIOS.getString("afiliado.trabajador"));
				System.out.println(detalleEmpresa.getTipoAfiliado());
				if(!ServiciosConst.RES_SERVICIOS.getString("afiliado.trabajador").equals(detalleEmpresa.getTipoAfiliado())){
					request.setAttribute("error", "El rut ingresado no es trabajador dependiente activo");
					return mapping.findForward("inicio");
				}
			}
			
						
			/*SalidaListaAfiliadoVO salidaBPStatus = ServicioQueryBPStatus.obtenerAfiliadoByRutSap("13723448-3");
			//UsuarioVO user = UsuarioServiceUtil.obtenerDatosAfiliadoGeneric("10245999");
			//SalidaListaConsultaDatosAfiliacionAs400 salida = ServicioListaConsultaDatosAfiliacion.obtenerAfiliadoByRutAs400("10245999");
			if(salidaBPStatus == null){
				request.setAttribute("msg", "El usuario no ha sido encontrado para realizar el certificado de finiquito.");
			}
			log.debug("obitene informacion del usuario nombre completo "+salidaBPStatus.getMensaje());*/
			
			
			/******************************************************* FIN QueryBPStatus*******************************************************/
			
			/******************************************************SAP*****************************************************************/
			
			
	
			
			
			
			SalidaListaCreditoPrepagoVO salidaVO = new SalidaListaCreditoPrepagoVO();
			SalidaListaCreditoPrepagoVO listaBanking = new SalidaListaCreditoPrepagoVO();
			//clillo 12-01-2018 se elimina acceso AS400
			//List<SalidaCreditoPrepagoVO> listaCreditosAs400 = new ArrayList<SalidaCreditoPrepagoVO>();
			List<SalidaCreditoPrepagoVO> listaCreditosSAP = new ArrayList<SalidaCreditoPrepagoVO>();
			List<SalidaCreditoPrepagoVO> listaCreditos = null;
			//List<SalidaCreditoPrepagoVO> listaCreditoSocial = new ArrayList<SalidaCreditoPrepagoVO>();
			//List<SalidaCreditoPrepagoVO> listaCreditoCes = new ArrayList<SalidaCreditoPrepagoVO>();
			//List<SalidaCreditoPrepagoVO> listaCreditoEspecial = new ArrayList<SalidaCreditoPrepagoVO>();
			PrepagoUtil prepagoUtil = new PrepagoUtil();
			//rut="10601236-9";//TODO Eliminar RUT MULA
			String rut2 = CompletaUtil.llenaConCeros(rutEmpleado, 11, true);
			log.debug("Rut: "+rutEmpleado);
			log.debug("Rut arreglado: "+rut2);
			
//			ListadoCreditosForm formPrepago = (ListadoCreditosForm) form;
			
			if(sesion.getAttribute("creditosPrepago")==null){
				
					
					log.debug("Ingreso a GetCreditosPrepago SAP");
					//TODO agregar constantes de parametro de early pay off
					String fechaSap = Utils.pasaFechaWEBaSAP(form.getFechaFiniquito());
					listaBanking = ServiciosPrepagoSap.obtenerCreditosVigentesBanking(rutEmpleado,fechaSap , ServiciosConst.CREDITOS_CONDEUDA, ServiciosConst.CERTIFICADO_FINIQUITO);
					log.debug("CODIGO DE ERROR DEL SERVICIO PREPAGO SAP: "+listaBanking.getCodigoError());
					
					if(listaBanking.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)){
						log.debug("CODIGO DE ERROR SALIENDO DE CLIENTE SAP: "+listaBanking.getCodigoError());
						log.debug("MENSAJE DE ERROR SALIENDO DE CLIENTE SAP: "+listaBanking.getMensaje());
					}else{
						if(null!=listaBanking.getListaCreditos() && listaBanking.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
							listaCreditosSAP.addAll(listaBanking.getListaCreditos());
							log.debug("Datos pasados a Lista de SAP");
						}
					}
					//clillo 12-01-2018 se elimina acceso AS400
					/*if (!listaBanking.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)) {
						log.debug("Ingreso a GetCreditosPrepago AS400");
						salidaVO = ServiciosPrepagoAs400.obtenerCreditosPrepagoAs400(CompletaUtil.llenaConCeros(rutEmpleado, 11, true).trim());
						log.debug("CODIGO DE ERROR DEL SERVICIO PREPAGO AS400: "+ salidaVO.getCodigoError());
						if (salidaVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)) {
							log.debug("CODIGO DE ERROR SALIENDO DE CLIENTE AS400: "
									+ salidaVO.getCodigoError());
							log.debug("MENSAJE DE ERROR SALIENDO DE CLIENTE AS400: "
									+ salidaVO.getMensaje());
						} else {
							if (null != salidaVO.getListaCreditos()&& salidaVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
								listaCreditosAs400.addAll(salidaVO.getListaCreditos());
								log.debug("Datos pasados a Lista de AS400");
							}
						}
					}*/
	
			
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
				
					log.debug("Ingresa datos al objeto CertificadoPrepagoVO");
					CertificadoPrepagoVO certificadoFiniquito = new CertificadoPrepagoVO();
					certificadoFiniquito.setFolio("001"); 	// Cambiar una vez que se obtenga dato
					certificadoFiniquito.setNombreCompleto(user.getNombreAfiliado());
					certificadoFiniquito.setRut(rutEmpleado);
					
					certificadoFiniquito.setNombreEmpresa(detalleEmpresa.getNombreEmpresa());
					certificadoFiniquito.setRutEmpresa(rutEmpresa);
					certificadoFiniquito.setTipoAfiliado(detalleEmpresa.getTipoAfiliado());
					
					//certificadoFiniquito.setFechaMMYY(PrepagoUtil.getMesYAno());
					//certificadoFiniquito.setFechaDDMM(PrepagoUtil.getUltimoDiaYMes());
					//certificadoFiniquito.setFechaCompleta(Utils.getFechaCompleta());
					
					certificadoFiniquito.setListaCreditos(listaCreditos);
					//certificadoFiniquito.setCreditoSocial(listaCreditoSocial);
					//certificadoFiniquito.setCreditoCes(listaCreditoCes);
					//certificadoFiniquito.setCreditoEspecial(listaCreditoEspecial);
					log.debug("Termina de ingresa datos al objeto certificadoFiniquitoVO");
				
					sesion.setAttribute("certificadoFiniquito", certificadoFiniquito);
					request.setAttribute("opcion", "0");
					
					
					SalidaListaCreditoPrepagoFoliosVO listaCreditoFinFoliosVO = prepagoUtil.getCreditosConFolios(listaCreditos, false);
					
					List<SalidaDetalleCuotasEarlyPayOff2> pensionadosList = prepagoUtil.getDetalleCuotasTrabajadorIndependiente(listaCreditos, true);
					List<SalidaDetalleCuotasEarlyPayOff2> trabajadoresList = prepagoUtil.getDetalleCuotasTrabajadorIndependiente(listaCreditos, false);
					
					
					sesion.setAttribute("fechaFiniquito", form.getFechaFiniquito());
					sesion.setAttribute("fechaSolicitud", Utils.fechaWeb());
					sesion.setAttribute("listaCreditoFinFoliosVO", listaCreditoFinFoliosVO);
					sesion.setAttribute("pensionadosList", pensionadosList);
					sesion.setAttribute("trabajadoresList", trabajadoresList);

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
			}

//			sesion.setAttribute("certificadoFiniquito", certificadoFiniquito);
			
			
			forward = mapping.findForward("success");
		} catch (Exception e) {
			e.printStackTrace();
			forward = Utils.returnErrorForward(mapping, e, this.getClass());
		}

		return (forward);

	}
}