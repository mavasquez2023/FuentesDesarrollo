package cl.araucana.wsatento.integration.jaxrpc.ws;

import cl.araucana.wsatento.business.service.CalidadService;
import cl.araucana.wsatento.business.service.SeguridadService;
import cl.araucana.wsatento.business.service.impl.CalidadServiceImpl;
import cl.araucana.wsatento.business.service.impl.SeguridadServiceImpl;
import cl.araucana.wsatento.common.util.RutUtil;
import cl.araucana.wsatento.integration.exception.WSAtentoException;

public class WSCalidad {
	private static final String NOMBRE_WS = "WSCALIDAD";
	/**
	 * @author crivera
	 * @param String usuario: nombre del usuario que esta haciendo uso del servicio web
	 * @param String clave: permite verificar la identidad del usuario
	 * @param String rut: identificador alfanumerico del afiliado 
	 * @param int calidad: identificador numerico de la calidad del afiliado
	 * @return int: "0" en caso que no corresponda la calidad para el rut ingresado
	 * 				"1" en caso que si corresponda la calidad para el rut ingresado  
	 * @throws WSAtentoException
	 */
	public int valCalidad(String usuario, String clave, String rut, int calidad) throws WSAtentoException{
//		 Validacion de seguridad
		SeguridadService seguridad = new SeguridadServiceImpl();
		
		seguridad.login(usuario, clave, NOMBRE_WS);
		
//		 Validaciones generales de parametros 
		if(rut == null || rut.equals(""))
			throw new WSAtentoException("0101","Rut en blanco.");
		if(calidad < 1 || calidad > 5)
			throw new WSAtentoException("0102","Calidad invalida.");
		if(!RutUtil.validaRut(rut.trim()))
			throw new WSAtentoException("0131","Rut invalido.");
		
		// Valida la calidad
		CalidadService servCalidad = new CalidadServiceImpl();
		Boolean resultVal = servCalidad.valCalidad(rut, new Integer(calidad));
		
		return (resultVal.booleanValue())?1:0;
	}
}
