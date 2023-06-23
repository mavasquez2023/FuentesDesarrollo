package cl.araucana.spl.mgr;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.ibatis.dao.client.DaoManager;

import cl.araucana.spl.beans.Sistema;
import cl.araucana.spl.dao.SistemaDAO;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.exceptions.PagoEnLineaException;

public class SistemaManager {
	private static final Logger logger = Logger.getLogger(SistemaManager.class);
	
	private SistemaDAO sistemaDAO;
	public SistemaManager() {
		DaoManager daoManager = DaoConfig.getDaoManager();
		sistemaDAO = (SistemaDAO) daoManager.getDao(SistemaDAO.class); 
	}
	
	public Sistema getSistemaByCodigo(String codigo) throws PagoEnLineaException {
		logger.debug("Recuperando sistema " + codigo);
		Sistema sistema = sistemaDAO.findSistemaOrigenByCodigo(codigo);
		if (sistema == null) {
			throw new PagoEnLineaException("No se encontro sistema origen " + codigo);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Sistema recuperado: " + sistema);
		}
		return sistema;
	}

	public Sistema getSistemaByPago(BigDecimal idPago) throws PagoEnLineaException {
		if (logger.isDebugEnabled()) {
			logger.debug("Recuperando sistema asociado a pago " + idPago);
		}
		Sistema sistema = sistemaDAO.findSistemaOrigenByIdPago(idPago);
		if (sistema == null) {
			throw new PagoEnLineaException("No se encontro sistema de origen para pago " + idPago);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Sistema recuperado " + sistema);
		}
		return sistema;
	}

	public String getClaveSistema(String codSistema) throws PagoEnLineaException {
		if (logger.isDebugEnabled()) {
			logger.debug("Recuperando clave de sistema " + codSistema);
		}
		return getSistemaByCodigo(codSistema).getClave();
	}

	public List getSistemas() {
		logger.debug("Recuperando sistemas");
		List sistemas = sistemaDAO.findSistemas();
		if (logger.isDebugEnabled()) {
			logger.debug("Cantidad de sistemas recuperados " + sistemas.size());
		}
		return sistemas;
	}

	public Sistema getSistema(BigDecimal idSistema) {
		List sistemas = getSistemas();
		for (Iterator it = sistemas.iterator(); it.hasNext(); ) {
			Sistema sistema = (Sistema) it.next();
			if (sistema.getId().equals(idSistema)) {
				return sistema;
			}
		}
		return null;
	}

}
