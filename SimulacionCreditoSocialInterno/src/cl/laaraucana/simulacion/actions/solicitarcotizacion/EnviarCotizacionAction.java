package cl.laaraucana.simulacion.actions.solicitarcotizacion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simulacion.VO.ParametrosCotizacionVO;
import cl.laaraucana.simulacion.VO.ResultadoSim;
import cl.laaraucana.simulacion.forms.EnviarCotizacionForm;
import cl.laaraucana.simulacion.ibatis.dao.ConsultaMigracionDAO;
import cl.laaraucana.simulacion.utils.ConstantesRespuestasWS;
import cl.laaraucana.simulacion.utils.CotizacionUtil;
import cl.laaraucana.simulacion.webservices.client.ConsultaCreditoPreAprob.VO.ConsultaCreditoPreAprobSalidaVO;
import cl.laaraucana.simulacion.webservices.client.CreaCotizacion.VO.CreaCotizacionSalidaVO;

public class EnviarCotizacionAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	public ActionForward execute(ActionMapping mapping, ActionForm _form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		try {
			/**
			 * Valida el estado de migración del cliente
			 */
			EnviarCotizacionForm form = (EnviarCotizacionForm)_form;
			ResultadoSim resultado = (ResultadoSim)request.getSession().getAttribute("resultado");
			ConsultaCreditoPreAprobSalidaVO resultadoPreAprobado = (ConsultaCreditoPreAprobSalidaVO) request.getSession().getAttribute("resultadoPreAprobado");
			
			if (resultado == null) {
				request.setAttribute("errorMsg", "Error;Error al realizar la solicitud, vuelva a realizar la simulación.");
				System.out.println("=== el resultado es null");
				return mapping.findForward("error");
			}
			
			
			
			String rut = resultado.getRut();
			String rutL = rut.substring(0, rut.length()-2);
			ParametrosCotizacionVO parametrosCotiza = new ParametrosCotizacionVO();
			parametrosCotiza.setCalle(form.getCalle());
			parametrosCotiza.setCalleNro(form.getCalleNro());
			parametrosCotiza.setCelular(form.getCelular());
			parametrosCotiza.setComuna(form.getComuna());
			parametrosCotiza.setRegion(form.getRegion());
			parametrosCotiza.setEmail(form.getEmail());
			parametrosCotiza.setExtension(form.getExtension());
			parametrosCotiza.setFono(form.getFono());
			parametrosCotiza.setNroDpto(form.getNroDpto());
			parametrosCotiza.setPreMovil(form.getPreMovil());
			parametrosCotiza.setContacto(form.isContacto());
			
			//DUMMY borrar por pruebas
			//if(false){
			if(ConsultaMigracionDAO.consultaRutMigrado(rutL)){
				
				//Envia el formulario al servicio SAP
				CreaCotizacionSalidaVO salidaVO = CotizacionUtil.enviarCotizacionService(parametrosCotiza, resultado, resultadoPreAprobado);
				if(!ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS.equalsIgnoreCase(salidaVO.getCodigoError())){
					logger.debug("Error al enviar el formulario de contacto SAP: ");
					System.out.println("Error al enviar el formulario de contacto: ");
					request.setAttribute("errorMsg", "Error; Error al enviar la solicitud de evaluación de crédito");
					return mapping.findForward("error");
				}
				
				request.setAttribute("folio", salidaVO.getNroCotizacion());
				forward = mapping.findForward("cotizacion");
			}else{
			//Registra el formulario en una tabla AS400 
				CotizacionUtil.enviarCotizacionAS400(parametrosCotiza, resultado);
				forward = mapping.findForward("solicitudDeContacto");
			}
			
		} catch (Exception e) {
			logger.debug("Error al enviar el formulario de contacto: " + e.getMessage());
			System.out.println("Error al enviar el formulario de contacto: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("errorMsg", "Error; Error al enviar la solicitud de evaluación de crédito");
			return mapping.findForward("error");
		}
		return forward;
	}
		
}
