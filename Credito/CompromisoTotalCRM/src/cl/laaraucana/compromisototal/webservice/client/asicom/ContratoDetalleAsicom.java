package cl.laaraucana.compromisototal.webservice.client.asicom;

import org.apache.log4j.Logger;

import cl.laaraucana.compromisototal.utils.Configuraciones;
import cl.laaraucana.compromisototal.webservice.WSInterface;
import cl.laaraucana.compromisototal.webservice.autogenerado.asicom.WsCCLaAraucanaInfoCreditoPortProxy;
import cl.laaraucana.compromisototal.webservice.client.asicom.VO.DetalleEntradaAsicomVO;
import cl.laaraucana.compromisototal.webservice.client.asicom.VO.DetalleSalidaListaAsicomVO;
import cl.laaraucana.compromisototal.webservice.models.AbstractEntradaVO;
import cl.laaraucana.compromisototal.webservice.models.AbstractSalidaVO;

public class ContratoDetalleAsicom implements WSInterface {
	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {

		logger.debug("Inicio Web Service: ContratoDetalleAsicom");

		// Se recibe la entrada en el VO propio
		DetalleEntradaAsicomVO entradaVO = (DetalleEntradaAsicomVO) entrada;

		// se obtien el end_point desde el archivo de configuracion
		String end_Point = Configuraciones.getConfig("ep.CreditoAsicom");

		// se crea la instancia del webservice y se asigna el end_point
		WsCCLaAraucanaInfoCreditoPortProxy ws = new WsCCLaAraucanaInfoCreditoPortProxy();
		ws._getDescriptor().setEndpoint(end_Point);

		logger.debug("recibe folio " + entradaVO.getOperacion());

		String xml = ws.getDetalleCredito(entradaVO.getOperacion());

		logger.debug("obtiene XML");

		ParserXml parserXml = new ParserXml();
		DetalleSalidaListaAsicomVO salidaVO = new DetalleSalidaListaAsicomVO();
		salidaVO.setListaDetalleCreditos(parserXml.parseaDetalleCreditos(xml));

		// logger.debug("Salida WebService: Detalle Credito VO");

		return salidaVO;
	}

}
