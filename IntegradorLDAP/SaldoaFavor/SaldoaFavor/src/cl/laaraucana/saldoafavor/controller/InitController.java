package cl.laaraucana.saldoafavor.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import cl.laaraucana.saldoafavor.entities.RegistroSaldoaFavor;
import cl.laaraucana.saldoafavor.services.RegistroSaldoaFavorService;
import cl.laaraucana.saldoafavor.utils.Utils;
import cl.laaraucana.saldoafavor.vo.SalidaSaldoaFavorVO;
import cl.laraucana.saldoafavor.ws.ClientePagoenExceso;
import cl.laraucana.saldoafavor.ws.ConstantesRespuestasWS;


@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);
	
	@Autowired
	private RegistroSaldoaFavorService regService;
	

	@RequestMapping(value = { "/init.do" }, method = RequestMethod.GET)
	public String getInit(ModelMap model, HttpServletRequest request) {

		try {
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return "index";
	}

	@RequestMapping(value = { "consulta.do" }, method = RequestMethod.POST)
	public String getList(ModelMap model, @ModelAttribute RegistroSaldoaFavor form, HttpServletRequest request) {
		String forward="";
		try {
			
			String rut=form.getRut().replaceAll("\\.", "").toUpperCase();
			//String serie= form.getSerie();
			logger.info("Consultando Saldo a favor Rut: " + rut);
			
			ClientePagoenExceso client = new ClientePagoenExceso();

			SalidaSaldoaFavorVO salida = (SalidaSaldoaFavorVO) client.call(rut);

		
			if(salida==null || salida.getCodigoError()==ConstantesRespuestasWS.COD_RESPUESTA_ERROR ){
				logger.warn("Error en respuesta servicio web Pago en Exceso");
				forward= "registro_error";	
			}else if(salida.getMonto()<=0){
				logger.warn("Sin monto devolución para Rut " + rut);
				forward= "index--error";
			}else{
				logger.warn("Monto devolución Rut " + rut + " : " + salida.getMonto());
				forward= "index--exito";
			}
			
			//Guardando bitácora
			request.setAttribute("consultaSF", salida);
			request.setAttribute("fechahoy", Utils.getFecha());
			RegistroSaldoaFavor reg = new RegistroSaldoaFavor();
			reg.setRut(rut.split("-")[0]);
			reg.setDvConsultado(rut.split("-")[1]);
			reg.setFechaConsulta(Utils.getFechaDate());
			reg.setHoraConsulta(Utils.getHoraDate());
			
			regService.save(reg);
			
			
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Consultando Cliente Pago en Exceso ", e);

			forward= "registro_error";

		}

		return forward;
	}
	
	

	

}
