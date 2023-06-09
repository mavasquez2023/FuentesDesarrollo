package cl.laaraucana.ventafullweb.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import cl.laaraucana.servicios.simulaCredito.Response;
import cl.laaraucana.ventafullweb.util.AfiliadoUtils;
import cl.laaraucana.ventafullweb.vo.AfiliadoVo;
import cl.laaraucana.ventafullweb.ws.ClienteSimulaCredito;
import cl.laaraucana.ventafullweb.ws.ClienteValidaCredito;

@Service
public class CreditoServiceImpl implements CreditoService {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public Response simularCredito(AfiliadoVo afiliado) throws Exception {
		logger.info("Consultando WSSimulaCredito para " + afiliado.getRutAfiliado());
		ClienteSimulaCredito clienteSimulaCredito = new ClienteSimulaCredito();
		try {
			Response resp = clienteSimulaCredito.getValidaCredito(afiliado);
			return resp;
		} catch(Exception e) {
			logger.error("Error al ejecutar WS SimulaCredito. " + e);
			return null;
		}
	}
	
	@Override
	public AfiliadoVo validarCredito(AfiliadoVo afiliado) throws Exception {
		//Llamada a WS WSValidaCredito
		logger.info("Consultando WSValidaCredito para " + afiliado.getRutAfiliado());
		ClienteValidaCredito clienteValidaCredito = new ClienteValidaCredito();
		try {
			cl.laaraucana.servicios.validaCredito.Response resp = clienteValidaCredito.getValidaCredito(afiliado);
			afiliado = AfiliadoUtils.agregaInfoCreditoAfiliado(afiliado, resp);
		} catch(Exception e) {
			logger.error("Error al ejecutar WS ValidaCredito. " + e);
		}	
		return afiliado;
	}
}
