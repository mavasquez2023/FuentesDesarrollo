package cl.laaraucana.ventafullweb.services;

import org.springframework.stereotype.Service;

import com.lautaro.xi.CRM.Legacy.CampWebFDRes_DT;

import org.apache.log4j.Logger;
import cl.laaraucana.servicios.validaCliente.Response;
import cl.laaraucana.ventafullweb.util.AfiliadoUtils;
import cl.laaraucana.ventafullweb.vo.AfiliadoVo;
import cl.laaraucana.ventafullweb.vo.SolicitanteVo;
import cl.laaraucana.ventafullweb.ws.ClienteCampWeb;
import cl.laaraucana.ventafullweb.ws.ClienteValidaCliente;


@Service
public class AfiliadoServiceImpl implements AfiliadoService {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public AfiliadoVo getDataAfiliado(SolicitanteVo solicitante) throws Exception {
		AfiliadoVo afiliado = new AfiliadoVo();
		afiliado.setCelular(solicitante.getCelular());
		afiliado.setRutAfiliado(solicitante.getRut());
		afiliado.setSerieCedula(solicitante.getNumeroSerie());

		//Llamada a WSCampWeb
		logger.info("Consultando WSCampWeb con rut: " + solicitante.getRut());
		ClienteCampWeb clienteCampWeb = new ClienteCampWeb();
		try {
			CampWebFDRes_DT respuestaWSCampWeb = clienteCampWeb.getValidaRut(solicitante.getRut());
			String rutEmpresa = respuestaWSCampWeb.getEV_RUT_EMPRESA();
			if(rutEmpresa == null) {
				logger.error("Error al ejecutar WSCampWeb. Retorno NULL");
				return null;
			} else {
				solicitante.setRutEmpresa(rutEmpresa);
				afiliado.setRutEmpresa(rutEmpresa);
				afiliado.setInscripcionPension(respuestaWSCampWeb.getEV_NRO_INSCRIPCION());
			}
		} catch(Exception e) {
			logger.error("Error al ejecutar WSCampWeb. " + e);
			return null;
		}
		//Llamada a WS WSValidaCliente
		logger.info("Consultando WSValidaCliente");
		ClienteValidaCliente clienteValidaCliente = new ClienteValidaCliente();
		try {
			Response salidaValidaCliente = clienteValidaCliente.getValidaCliente(solicitante);
			afiliado = AfiliadoUtils.agregaInfoAfiliado(afiliado, salidaValidaCliente);
		} catch(Exception e) {
			logger.error("Error al ejecutar WS ValidaCliente. " + e);
			return null;
		}
		
		return afiliado;
	}
	

	@Override
	public boolean isAfiliadoVigente(AfiliadoVo afiliado){
		int vigente = afiliado.getAfiliadoVigente();
		if(vigente == 1) {
			return true;
		} else {
			return false;
		}
	}

	
	@Override
	public boolean isFuncionario(AfiliadoVo afiliado) {
		int funcionario = afiliado.getAfiliadoFuncionario();
		if(funcionario == 1) {
			return false;
		} else {
			return true;
		}
	}

	
	@Override
	public boolean isFallecido(AfiliadoVo afiliado) {
		int fallecido = afiliado.getAfiliadoFallecido();
		if(fallecido == 1) {
			return false;
		} else {
			return true;
		}
	}


}
