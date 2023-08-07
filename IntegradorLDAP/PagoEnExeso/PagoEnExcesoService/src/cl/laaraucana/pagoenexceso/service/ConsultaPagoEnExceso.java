package cl.laaraucana.pagoenexceso.service;

import cl.laaraucana.pagoenexceso.manager.PagoEnExcesoMGR;
import cl.laaraucana.pagoenexceso.persistencia.vo.ConsultaPagoEnExcesoOut;

public class ConsultaPagoEnExceso {
	public ConsultaPagoEnExcesoOut consultarPagoEnExceso(String rut){
		PagoEnExcesoMGR mgr = new PagoEnExcesoMGR();
		ConsultaPagoEnExcesoOut salida = mgr.consultarPagoEnExceso(rut);
		return salida;
	}
}
