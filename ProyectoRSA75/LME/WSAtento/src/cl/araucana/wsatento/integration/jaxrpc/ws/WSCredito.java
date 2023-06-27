package cl.araucana.wsatento.integration.jaxrpc.ws;

import java.util.Iterator;
import java.util.List;

import cl.araucana.wsatento.business.service.CreditoService;
import cl.araucana.wsatento.business.service.SeguridadService;
import cl.araucana.wsatento.business.service.impl.CreditoServiceImpl;
import cl.araucana.wsatento.business.service.impl.SeguridadServiceImpl;
import cl.araucana.wsatento.business.to.CreditoTO;
import cl.araucana.wsatento.common.util.RutUtil;
import cl.araucana.wsatento.integration.exception.WSAtentoException;
import cl.araucana.wsatento.integration.jaxrpc.bean.Credito;

public class WSCredito {
	private static final String NOMBRE_WS = "WSCREDITO";
	
	public Credito [] getCreditos(String usuario, String clave, String rut) throws WSAtentoException{
//		 Validacion de seguridad
		SeguridadService seguridad = new SeguridadServiceImpl();
		seguridad.login(usuario, clave, NOMBRE_WS);

//		Validaciones generales
		if(rut == null || rut.equals(""))
			throw new WSAtentoException("0301","Rut en blanco.");
		
		if(!RutUtil.validaRut(rut.trim()))
			throw new WSAtentoException("0331","Rut invalido.");
		
//		Obtiene el credito 
		CreditoService servCredito = new CreditoServiceImpl();
		List creditos = servCredito.getCreditos(rut);
		
		Credito[] arrayCreditos = new Credito[creditos.size()]; 
		Iterator iter = creditos.iterator();
		for(int i = 0; iter.hasNext(); i++){
			CreditoTO obj = (CreditoTO) iter.next();
			arrayCreditos[i] = new Credito(obj.getFechaPago());
		}
		return arrayCreditos;
	}
}
