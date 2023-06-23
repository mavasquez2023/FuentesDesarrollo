package cl.laaraucana.capaservicios.webservices.service;

import cl.laaraucana.capaservicios.manager.CreditosVigentesMGR;

public class ConsultaCreditosEspVigentes {
	
	public Object obtenerCreditosVigentes(String rut){
		Object salida = null;
		CreditosVigentesMGR manager = new CreditosVigentesMGR();
		salida = manager.getCreditosVigentes(rut);
		return salida;
	}
	
}
