package cl.laaraucana.simulacion.webservices;

import cl.laaraucana.simulacion.webservices.model.AbstractEntradaVO;
import cl.laaraucana.simulacion.webservices.model.AbstractSalidaVO;

public interface WSInterface {
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception;
}
