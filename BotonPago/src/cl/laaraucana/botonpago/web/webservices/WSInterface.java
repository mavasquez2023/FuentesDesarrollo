package cl.laaraucana.botonpago.web.webservices;

import cl.laaraucana.botonpago.web.webservices.model.AbstractEntradaVO;
import cl.laaraucana.botonpago.web.webservices.model.AbstractSalidaVO;

public interface WSInterface {
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception;
}
