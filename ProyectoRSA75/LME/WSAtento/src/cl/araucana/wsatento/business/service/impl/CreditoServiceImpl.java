package cl.araucana.wsatento.business.service.impl;

import java.util.List;

import cl.araucana.wsatento.business.dao.CreditoDao;
import cl.araucana.wsatento.business.dao.impl.CreditoDaoImpl;
import cl.araucana.wsatento.business.service.CreditoService;
import cl.araucana.wsatento.common.util.RutUtil;
import cl.araucana.wsatento.integration.exception.WSAtentoException;

public class CreditoServiceImpl implements CreditoService{
	public List getCreditos(String rut) throws WSAtentoException{
		Integer rutEntero = RutUtil.getParteEnteraRut(rut);
		
		CreditoDao creditoDao = new CreditoDaoImpl();
		List listaCreditos = creditoDao.getCreditosByRut(rutEntero);
		
		return listaCreditos;
	}
}
