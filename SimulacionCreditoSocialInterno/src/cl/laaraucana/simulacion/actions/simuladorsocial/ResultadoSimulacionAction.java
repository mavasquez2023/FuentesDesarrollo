package cl.laaraucana.simulacion.actions.simuladorsocial;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simulacion.VO.ParametrosSimulacionVO;
import cl.laaraucana.simulacion.VO.ResultadoSim;
import cl.laaraucana.simulacion.forms.SimularSocialForm;
import cl.laaraucana.simulacion.utils.ConstantesFormalizar;
import cl.laaraucana.simulacion.utils.ConstantesRespuestasWS;
import cl.laaraucana.simulacion.utils.SimuladorServicesUtil;
import cl.laaraucana.simulacion.webservices.client.UsuarioServiceUtil;
import cl.laaraucana.simulacion.webservices.model.UsuarioAfiliadoVO;

//simulacion social
public class ResultadoSimulacionAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		try {
			SimularSocialForm form = (SimularSocialForm) _form;
			String rut = form.getRutAfiliado().replace(".", "");
			request.getSession().setAttribute("resultado", null);
			//Consultar si es afiliado
			//Obtener datos de usuario
			UsuarioAfiliadoVO bp = UsuarioServiceUtil.obtenerAfiliado(rut);
			if (bp.getCodigoError().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)) {
				// request.setAttribute("nombreAfiliado",
				// bp.getNombreAfiliado());
				// request.setAttribute("rut", rut);
				request.setAttribute("esAfiliado", 1);
			} else
				request.setAttribute("esAfiliado", 0);
			//llamada al servicio creaCotizacion y QuerySimWeb
			ParametrosSimulacionVO parametros = new ParametrosSimulacionVO();
			parametros.setCuotas(form.getCuotas());
			parametros.setMonto(form.getMonto());
			parametros.setOficina(form.getOficina());
			parametros.setRutAfiliado(rut);
			parametros.setNombreAfiliado(bp.getNombreAfiliado());
			parametros.setTipoAfiliado(form.getTipoAfiliado());
			parametros.setSeguroCesantia(form.getSegCesantia());
			parametros.setTasaMensual(form.getTasaMensual());
			System.out.println("es pensionado el seguro de cesantia es " + form.getSegCesantia());
			if (!(parametros.getTipoAfiliado().equals("ZFSO") && parametros.getSeguroCesantia().equals("S"))) {
				ResultadoSim resultado = SimuladorServicesUtil.getResultadoSimulacion(parametros,
					ConstantesFormalizar.TIPO_PRO_SIMULACION_SOCIAL);
				//Error del servicio creaCotizacion
				if (!resultado.getCodigoError().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)) {
					logger.debug("Error en servicio creaCotizacion: " + resultado.getMensaje());
					request.setAttribute("errorMsg", "Error;Error al realizar la simulación de crédito.");
					return mapping.findForward("error");
				}
				request.getSession().setAttribute("resultado", resultado);
				request.setAttribute("fechaEmision", new Date());
				String observaciones = ConstantesFormalizar.getObservaciones(ConstantesFormalizar.COD_SIMULADOR_SOCIAL,
					ConstantesFormalizar.SIMULADOR_SOCIAL_PARAM_OBS_RESULTADO);
				if (observaciones == null) {
					request.setAttribute("errorMsg", "Error; Al obtener parámetros para resultado de simulación.");
					return mapping.findForward("error");
				} else {
					request.setAttribute("observaciones", observaciones);
				}
			} else {
				request.setAttribute("errorMsg", "Error;Selección de Seguro de Cesantia no disponible para Pensionado");
				return mapping.findForward("error");
			}
		} catch (Exception e) {
			logger.debug("Error al realizar la simulación de crédito: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("errorMsg", "Error;Error al realizar la simulación de crédito");
			return mapping.findForward("error");
		}
		forward = mapping.findForward("resultadoSimulacion");
		return forward;
	}
}
