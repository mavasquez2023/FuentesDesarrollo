package cl.araucana.wsatento.integration.jaxrpc.ws;

import java.util.Iterator;
import java.util.List;

import cl.araucana.wsatento.business.service.LicenciaService;
import cl.araucana.wsatento.business.service.SeguridadService;
import cl.araucana.wsatento.business.service.impl.LicenciaServiceImpl;
import cl.araucana.wsatento.business.service.impl.SeguridadServiceImpl;
import cl.araucana.wsatento.business.to.LicenciaTO;
import cl.araucana.wsatento.common.util.RutUtil;
import cl.araucana.wsatento.integration.exception.WSAtentoException;
import cl.araucana.wsatento.integration.jaxrpc.bean.Licencia;

public class WSLicencia {
	private static final String NOMBRE_WS = "WSLICENCIA";
	
	public Licencia [] getLicencias(String usuario, String clave, String rut) throws WSAtentoException{
//		 Validacion de seguridad
		SeguridadService seguridad = new SeguridadServiceImpl();
		seguridad.login(usuario, clave, NOMBRE_WS);
		
		
//		 Validaciones generales de parametros
		
		if(rut == null || rut.trim().equals(""))
			throw new WSAtentoException("0201","Rut en blanco.");
		
		if(!RutUtil.validaRut(rut.trim()))
			throw new WSAtentoException("0231","Rut invalido.");
		
//		 Obtiene la licencias
		LicenciaService servLicencia = new LicenciaServiceImpl();
		List licencias = servLicencia.getLicenicas(rut);
		
		Licencia[] arrayLicencias = new Licencia[licencias.size()]; 
		Iterator iter = licencias.iterator();
		for(int i = 0; iter.hasNext(); i++){
			LicenciaTO obj = (LicenciaTO) iter.next();
			arrayLicencias[i] = new Licencia(obj.getCodSucPago(), obj.getFechaPago(), obj.getCompin(), obj.getTipo(), obj.getDias(), obj.getFechaDesde());
		}
		return arrayLicencias;
	}
}
