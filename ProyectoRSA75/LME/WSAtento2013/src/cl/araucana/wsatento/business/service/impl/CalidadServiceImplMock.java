package cl.araucana.wsatento.business.service.impl;

import cl.araucana.wsatento.business.service.CalidadService;
import cl.araucana.wsatento.integration.exception.WSAtentoException;

public class CalidadServiceImplMock implements CalidadService{
	
	public Boolean valCalidad(String rut, Integer calidad) throws WSAtentoException{
		
		if(rut.trim().equals("23358823-7") && calidad.intValue() == 1)
			return new Boolean(true);
		if(rut.trim().equals("13511818-4") && calidad.intValue() == 2)
			return new Boolean(true);
		if(rut.trim().equals("7579391-k") && calidad.intValue() == 3)
			return new Boolean(true);
		if(rut.trim().equals("14038741-k") && calidad.intValue() == 4)
			return new Boolean(true);
		if(rut.trim().equals("10225281-0") && calidad.intValue() == 5)
			return new Boolean(true);
		
		return new Boolean(false);
	}
}
