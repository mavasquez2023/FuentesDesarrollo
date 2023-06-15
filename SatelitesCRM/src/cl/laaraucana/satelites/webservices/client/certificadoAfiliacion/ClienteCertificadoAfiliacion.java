package cl.laaraucana.satelites.webservices.client.certificadoAfiliacion;

import org.apache.log4j.Logger;

import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.certificados.creditovigente.VO.EntradaCreditoVigenteVO;
import cl.laaraucana.satelites.integracion.ConsultaCreditosPorRutEnAs400.ConsultaCreditoPorRutEnAs400PortProxy;
import cl.laaraucana.satelites.integracion.ConsultaCreditosPorRutEnAs400.ConsultaCreditosPorRutEntradaVO;
import cl.laaraucana.satelites.webservices.WSInterface;
import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;
import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class ClienteCertificadoAfiliacion implements WSInterface{
	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		logger.debug("<< Ingreso a clienteCreditoAs400");

		//VO's propios
		EntradaCreditoVigenteVO entradaVO = (EntradaCreditoVigenteVO) entrada;
		//VO's WS
		ConsultaCreditosPorRutEntradaVO entradaWs = new ConsultaCreditosPorRutEntradaVO();
		entradaWs.setRut(entradaVO.getRut());
		ConsultaCreditoPorRutEnAs400PortProxy ws = new ConsultaCreditoPorRutEnAs400PortProxy();
		ws._getDescriptor().setEndpoint(Configuraciones.getConfig("ep.CreditoAs400"));
		logger.debug("LLamada a ws CreditosAS400 con rut de entrada :"+entradaWs.getRut());



		return null;
	}
	
	

}
