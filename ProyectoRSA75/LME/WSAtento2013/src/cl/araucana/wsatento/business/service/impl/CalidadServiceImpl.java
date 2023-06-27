package cl.araucana.wsatento.business.service.impl;

import java.util.Iterator;
import java.util.List;

import cl.araucana.wsatento.business.dao.CalidadDao;
import cl.araucana.wsatento.business.dao.impl.CalidadDaoImpl;
import cl.araucana.wsatento.business.service.CalidadService;
import cl.araucana.wsatento.business.to.CalidadTO;
import cl.araucana.wsatento.common.util.RutUtil;
import cl.araucana.wsatento.integration.exception.WSAtentoException;

public class CalidadServiceImpl implements CalidadService{
	
	public Boolean valCalidad(String rut, Integer calidad) throws WSAtentoException{
		Integer rutEntero = RutUtil.getParteEnteraRut(rut);
		
		CalidadDao calidadDao = new CalidadDaoImpl();
		List listaCalidad = calidadDao.getCalidadesByRut(rutEntero);
		
		for(Iterator it = listaCalidad.iterator(); it.hasNext();){
			CalidadTO objCal = (CalidadTO)it.next();
			if(objCal.getCodCalidad().intValue() == calidad.intValue()){
				return new Boolean(true);
			}
		}
		return new Boolean(false);
	}
	
//	public CalidadTO getCalidad(String rut){
//		
//		if(rut.equals("16352857-6")){
//			return new CalidadTO(new Integer(1)); 
//		}if(rut.equals("10280373-6")){
//			return new CalidadTO(new Integer(2)); 
//		}if(rut.equals("14970230-k")){
//			return new CalidadTO(new Integer(3)); 
//		}if(rut.equals("6551133-9")){
//			return new CalidadTO(new Integer(4)); 
//		}
//		
//		return new CalidadTO(new Integer(0));
//	}
}
