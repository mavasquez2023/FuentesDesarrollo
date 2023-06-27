package cl.laaraucana.capaservicios.webservices.service;

import cl.laaraucana.capaservicios.manager.CreditosVigentesMGR;
import cl.laaraucana.capaservicios.webservices.vo.ConsultaCreditosEspVigentes.ConsultaCreditosEspVigOut;

public class ConsultaCreditosEspVigentes {
	
	public ConsultaCreditosEspVigOut obtenerCreditosVigEsp(String rut){
		ConsultaCreditosEspVigOut salida = null;
		CreditosVigentesMGR manager = new CreditosVigentesMGR();
		salida = manager.getCreditosVigentes(rut);
		return salida;
	}
	
}
