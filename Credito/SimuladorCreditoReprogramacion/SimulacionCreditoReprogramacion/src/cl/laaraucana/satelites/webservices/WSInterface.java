package cl.laaraucana.satelites.webservices;

import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;
import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public interface WSInterface {
	public AbstractSalidaVO call(AbstractEntradaVO entrada, boolean castigado) throws Exception;
}
