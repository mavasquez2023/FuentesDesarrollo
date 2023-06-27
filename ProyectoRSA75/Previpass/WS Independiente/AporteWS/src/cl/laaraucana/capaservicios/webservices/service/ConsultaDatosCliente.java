package cl.laaraucana.capaservicios.webservices.service;

import cl.laaraucana.capaservicios.manager.ConsultaDatosClienteMGR;
import cl.laaraucana.capaservicios.webservices.vo.ConsultaDatosCliente.ActDatosClienteVO;
import cl.laaraucana.capaservicios.webservices.vo.ConsultaDatosCliente.ActualizarDatosClienteOut;
import cl.laaraucana.capaservicios.webservices.vo.ConsultaDatosCliente.ConsultaDatosClienteIn;
import cl.laaraucana.capaservicios.webservices.vo.ConsultaDatosCliente.ConsultaDatosClienteOut;

public class ConsultaDatosCliente {
	
	public ConsultaDatosClienteOut obtenerDatosCliente(ConsultaDatosClienteIn entrada){
		
		ConsultaDatosClienteMGR mgr = new ConsultaDatosClienteMGR();
		ConsultaDatosClienteOut salida = mgr.obtenerDatosCliente(entrada);
		return salida;
	}
	
	public ActualizarDatosClienteOut actualizarDatosCliente(ActDatosClienteVO entrada){
		ConsultaDatosClienteMGR mgr = new ConsultaDatosClienteMGR();
		return mgr.actualizarDatosCliente(entrada);
	}
}
