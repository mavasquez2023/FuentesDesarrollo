package cl.laaraucana.compromisototal.webservice.client.asicom;

import org.apache.log4j.Logger;

import cl.laaraucana.compromisototal.utils.Codigo;
import cl.laaraucana.compromisototal.utils.Configuraciones;
import cl.laaraucana.compromisototal.webservice.WSInterface;
import cl.laaraucana.compromisototal.webservice.autogenerado.asicom.WsCCLaAraucanaInfoCreditoPortProxy;
import cl.laaraucana.compromisototal.webservice.client.asicom.VO.EntradaAsicomVO;
import cl.laaraucana.compromisototal.webservice.client.asicom.VO.SalidaListaAsicomVO;
import cl.laaraucana.compromisototal.webservice.models.AbstractEntradaVO;
import cl.laaraucana.compromisototal.webservice.models.AbstractSalidaVO;

public class ContratoAsicom implements WSInterface {
	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {

		logger.debug("Inicio Web Service: ContratoAsicom");

		// Se recibe la entrada en el VO propio
		EntradaAsicomVO entradaVO = (EntradaAsicomVO) entrada;

		// se obtien el end_point desde el archivo de configuracion
		String end_Point = Configuraciones.getConfig("ep.CreditoAsicom");

		// se crea la instancia del webservice y se asigna el end_point
		WsCCLaAraucanaInfoCreditoPortProxy ws = new WsCCLaAraucanaInfoCreditoPortProxy();
		ws._getDescriptor().setEndpoint(end_Point);

		String rut = entradaVO.getRut().toUpperCase();
		// se guarda el xml de respuesta
		logger.debug("recibre rut: " + rut);

		SalidaListaAsicomVO salidaVO = new SalidaListaAsicomVO();

		String xml = "";
		try {
			xml = ws.getDatosCredito(rut);

		} catch (Exception e) {
			salidaVO.setCodigoError(Codigo.ERROR);
			salidaVO.setMensaje("Error en Servicio getDatosCreditoASICOM: compruebe el servicio");
			return salidaVO;
		}

		logger.debug("obtiene XML");

		ParserXml parserXml = new ParserXml();

		salidaVO.setCodigoError(parserXml.getCodigoErrorXML(xml));
		salidaVO.setListaCreditos(parserXml.parseaDatosCreditos(xml));

		logger.debug("Salida WebService: Detalle Credito VO");

		return salidaVO;

	}

}
