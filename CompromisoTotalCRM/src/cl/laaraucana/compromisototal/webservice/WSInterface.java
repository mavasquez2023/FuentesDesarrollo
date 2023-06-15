package cl.laaraucana.compromisototal.webservice;

import cl.laaraucana.compromisototal.webservice.models.AbstractEntradaVO;
import cl.laaraucana.compromisototal.webservice.models.AbstractSalidaVO;

public interface WSInterface {
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception;
}
