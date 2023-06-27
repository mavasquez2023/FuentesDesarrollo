package cl.araucana.wsatento.business.service.impl;

import java.util.List;

import cl.araucana.wsatento.business.dao.LicenciaDao;
import cl.araucana.wsatento.business.dao.impl.LicenciaDaoImpl;
import cl.araucana.wsatento.business.service.LicenciaService;
import cl.araucana.wsatento.common.util.RutUtil;
import cl.araucana.wsatento.integration.exception.WSAtentoException;

public class LicenciaServiceImpl implements LicenciaService{
	
	public List getLicenicas(String rut) throws WSAtentoException{
		Integer rutEntero = RutUtil.getParteEnteraRut(rut);
		
		LicenciaDao licenciaDao = new LicenciaDaoImpl();
		List listaLicencias = licenciaDao.getLicenciasByRut(rutEntero);
	
		return listaLicencias;
	}
}