package cl.araucana.wslme.business.service.impl;

import org.apache.log4j.Logger;

import cl.araucana.wslme.business.service.OperadorService;
import cl.araucana.wslme.business.to.Operador;
import cl.araucana.wslme.common.exception.WSLMEException;
import cl.araucana.wslme.integration.dao.OperadorDao;
import cl.araucana.wslme.integration.dao.impl.OperadorDaoImpl;

public class OperadorServiceImpl implements OperadorService{
	private Logger log = Logger.getLogger(OperadorServiceImpl.class);
	
	public Boolean validaPermisoOperador(String codOperador, String claveOperador) throws WSLMEException{
		log.debug("Abriendo el archivo de operadores registrados");
		OperadorDao opeDao = new OperadorDaoImpl();
		
		log.debug("Buscando el operador codigo [" + codOperador + "] en el archivo de operadores registrados");
		Operador operador = opeDao.getOperador(codOperador);
		
		if(operador.getCodigoOperador() != null && operador.getClaveOperador() != null 
				&& codOperador.equals(operador.getCodigoOperador()) && claveOperador.equals(operador.getClaveOperador())){
			log.debug("Codigo operador y clave operador son correctos");
			return new Boolean(true);
		}
		
		log.debug("Codigo operador y/o clave operador no son correctos");
		return new Boolean(false);
	}
	
}