package cl.laaraucana.mandatocesantia.ws;

import cl.laaraucana.mandatocesantia.vo.AbstractEntradaVO;
import cl.laaraucana.mandatocesantia.vo.AbstractSalidaVO;



public interface WSInterfaceSAP {
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception;
}
