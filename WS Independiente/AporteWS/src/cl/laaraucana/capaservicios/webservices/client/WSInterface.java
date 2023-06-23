package cl.laaraucana.capaservicios.webservices.client;

import cl.laaraucana.capaservicios.webservices.model.AbstractEntradaVO;
import cl.laaraucana.capaservicios.webservices.model.AbstractSalidaVO;

public interface WSInterface {
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception;
}
