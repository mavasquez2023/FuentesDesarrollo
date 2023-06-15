package cl.laaraucana.simulacion.actions.simuladorrepro;


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
import cl.laaraucana.simulacion.utils.ConstantesRespuestasWS;
import cl.laaraucana.simulacion.utils.SimuladorServicesUtil;
import cl.laaraucana.simulacion.webservices.client.DataServiceUtil;


//simulacion social
public class ResultadoSimulacionAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		try {
			int j=0;
			String rutEjecutivo= (String)request.getSession().getAttribute("nameuser");
			if (rutEjecutivo== null){
				response.sendRedirect("menu.jsp");
			}
			
			SimularSocialForm form = (SimularSocialForm) _form;
			String rut = form.getRutAfiliado().replaceAll("\\.", "");
			request.getSession().setAttribute("resultado", null);
			
			String montoAbono= form.getMontoAbono().replaceAll("\\.", "");
			if(montoAbono.equals("")){
				montoAbono="0";
			}
			String renta= form.getRenta().replaceAll("\\.", "");
			
			//Se guardan parámetros
			ParametrosSimulacionVO paramVO= new ParametrosSimulacionVO();
			paramVO.setRutEjecutivo(rutEjecutivo);
			paramVO.setRutAfiliado(rut);
			paramVO.setNombreAfiliado(form.getNombreAfiliado());
			paramVO.setContrato(form.getContrato());
			paramVO.setTipoAfiliado(form.getTipoAfiliado());
			paramVO.setDescripcionAfiliado(DataServiceUtil.obtenerTipoAfiliadoSap(form.getTipoAfiliado()));
			String[] anexo= form.getAnexos().split("#");
			paramVO.setAnexo(anexo[0]);
			if(anexo.length==2){
				paramVO.setNumeroInscripcion(anexo[1]);
			}else{
				paramVO.setNumeroInscripcion("");
			}
			paramVO.setProductoReprogramacion(form.getProductos());
			String oficina= "A" + form.getContrato().substring(0, 3);
			paramVO.setOficina(oficina);
			paramVO.setPlazo(form.getCuotas());
			paramVO.setSeguroCesantia(form.getSegCesantia());
			paramVO.setSeguroDesgravamen(form.getSegDesgravamen());
			paramVO.setMesesGracia(form.getMesesGracia());		
			paramVO.setPorcentajeCapital("0");
			paramVO.setPorcentajeGastosCobranza(form.getDctoGascob());
			paramVO.setPorcentajeGravamenes(form.getDctoGravam());
			paramVO.setPorcentajeHonorarios(form.getDctoGascob());
			paramVO.setPorcentajeIntereses(form.getDctoGasint());
			paramVO.setMontoAbono(montoAbono);
			paramVO.setTasaInteres("SI");
			paramVO.setRenta(renta);
			
			ResultadoSim resultado = SimuladorServicesUtil.getResultadoSimulacion(paramVO);
			//Se cambia código Tipo Afiliado por su descricpción
			paramVO.setTipoAfiliado(DataServiceUtil.obtenerTipoAfiliadoSap(form.getTipoAfiliado()));
			//Se agregan los parámetros de entrda y endeudamiento
			resultado.setParamEntrada(paramVO);

			//Insertando Bitácora
			BitacoraServiceImpl bitacora= new BitacoraServiceImpl();
			bitacora.insertBitacoraReprogramacion(resultado);
			
			//Error del servicio 
			if (resultado.getCodigoError().equals(ConstantesRespuestasWS.COD_RESPUESTA_ERROR)) {
				logger.debug("Error en servicio simulaciónReprogramación: " + resultado.getMensaje());
				request.setAttribute("errorMsg", "Error;Error al realizar la simulación de crédito.");
				return mapping.findForward("error");
			}
			
			if (resultado.getCodigoError().equals(ConstantesRespuestasWS.COD_RESPUESTA_VACIO)) {
				logger.debug("Error en servicio simulaciónReprogramación: " + resultado.getMensaje());
				request.setAttribute("errorMsg", "Error, Monto Abono supera Capital Adeudado");
				response.getWriter().write("Error, Monto Abono supera Capital Adeudado");
				request.setAttribute("rutAfiliado", rut);
				request.setAttribute("montoAbono", montoAbono);
				return mapping.findForward("ingresarParametros");
			}
			
			request.getSession().setAttribute("resultado", resultado);
			request.setAttribute("fechaEmision", new Date());


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
