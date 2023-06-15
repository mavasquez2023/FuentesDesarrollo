package cl.araucana.autoconsulta.bo.impl;

import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.schema.util.FileSettings;

import cl.araucana.autoconsulta.bo.ICreditoBO;
import cl.araucana.autoconsulta.dao.DAOFactory;
import cl.araucana.autoconsulta.dao.credito.DB2CreditoDAO;
import cl.araucana.autoconsulta.dao.credito.ICreditoDAO;
import cl.araucana.common.env.AppConfig;
import cl.laaraucana.credito.to.CreditoTO;
import cl.laaraucana.credito.to.SeguroComprometidoTO;
/*
 * modificado 2013 07 01 - se agrega UsuarioVO 
*/

public class CreditoBO implements ICreditoBO {
	ICreditoDAO creditoDao;
	private CreditoTO creditoTO = new CreditoTO();
	private static Logger logger = Logger.getLogger(CreditoBO.class);
	private boolean isPrimarySet=false;

	public CreditoBO() {
		int daoType =
			Integer.parseInt(
				FileSettings.getValue(
					AppConfig.getInstance().settingsFileName,
					"/application-settings/autoconsulta/dao-type"));
		try {
			DAOFactory daoFactory =
				(DAOFactory) DAOFactory.getDAOFactory(daoType);
			creditoDao = daoFactory.getCreditoDAO();
		} catch (Exception e) {
			
		}

	}

	public void setRutTitular (long rutTitular) {
		this.isPrimarySet = true;
		this.creditoTO.setRutTitular(rutTitular);
	}
	
	public void setRut(long rutAfiliado) {
		this.isPrimarySet = true;
		this.creditoTO.setRutEmpresa(rutAfiliado);
		this.creditoTO.setRutTitular(rutAfiliado);
	}
	
	public void setRut(cl.araucana.autoconsulta.vo.UsuarioVO usrVo) {
		this.isPrimarySet = true;
		this.creditoTO.setRutTitular(usrVo.getRutAfiliado());
		this.creditoTO.setRutEmpresa(usrVo.getRutEmpresa());
	}

	public void setRut(long rutEmpleador, long rutAfiliado) {
		this.isPrimarySet = true;
		this.creditoTO.setRutTitular(rutAfiliado);
		this.creditoTO.setRutEmpresa(rutEmpleador);
	}

	public void setCredito (CreditoTO credito) {
		this.isPrimarySet = true;
		this.creditoTO = credito;
	}

	public Collection getCreditos() {
		Collection lstCreditos = null;
		if (!isPrimarySet) {
			return null;
		}
		try {
			
			if (creditoTO.getRutTitular()==creditoTO.getRutEmpresa()) {
				logger.info("Buscando lista de creditos getCreditosTitular");
				lstCreditos = creditoDao.getCreditosTitular(creditoTO.getRutTitular());
			} else {
				logger.info("Buscando lista de creditos getCreditosEmpresaAfiliado");
				lstCreditos = creditoDao.getCreditosEmpresaAfiliado(creditoTO.getRutEmpresa(), creditoTO.getRutTitular());
			}
		} catch (Exception e) {

		}
		return lstCreditos;

	}
	
	public Collection getCredito() {
		Collection lstResultado = null;
		if (!isPrimarySet) {
			return null;
		}
		try {
			lstResultado = creditoDao.getCreditosFolio(creditoTO.getOficina(), ""+creditoTO.getFolio());
			if (lstResultado != null && lstResultado.size()>=1) {
				Collection seguros = obtenerSegurosCredito();
				if (seguros!=null && seguros.size()>0) {
					Iterator it = seguros.iterator();
					while (it.hasNext()) {
						SeguroComprometidoTO seguro = (SeguroComprometidoTO) it.next();
						if (seguro.getTipoSeguro().equals("SC"))  ((CreditoTO)lstResultado.iterator().next()).setIndicadorSeguroCesantia("SI");
						if (seguro.getTipoSeguro().equals("SD"))  ((CreditoTO)lstResultado.iterator().next()).setIndicadorSeguroDeshaucio("SI");
						if (seguro.getTipoSeguro().equals("SV"))  ((CreditoTO)lstResultado.iterator().next()).setIndicadorSeguroInvalidez("SI");
					}
				}
			}
		} catch (Exception e) {

		}
		return lstResultado;
	}
	
	public Collection obtenerCuotas() {
		if (!isPrimarySet || this.creditoTO==null) {
			return null;
		}
		return creditoDao.getCuotas(creditoTO.getOficina(), ""+creditoTO.getFolio());		
	}

	public Collection obtenerPagos() {
		if (!isPrimarySet || this.creditoTO==null) {
			return null;
		}
		return creditoDao.getPagos(creditoTO.getOficina(), ""+creditoTO.getFolio());
	}

	/**
	 * Objeto: Consulta de seguros comprometidos
	 * @param String oficina, String folio
	 * @return coleccion de SeguroComprometidoTO
	 * @author adiaz ( Artered Chile )
	 * @version 1.0
	 */
	public Collection obtenerSegurosCredito() {
		if (!isPrimarySet || this.creditoTO==null) {
			return null;
		}
		Collection seguros = creditoDao.getSegurosCredito(creditoTO.getOficina(), ""+creditoTO.getFolio());
		if (seguros!=null && seguros.size()>0) {
			Iterator it = seguros.iterator();
			while (it.hasNext()) {
				SeguroComprometidoTO seguro = (SeguroComprometidoTO) it.next();
				logger.info("getTipoSeguro() [" +  seguro.getTipoSeguro() + "]");
				if (seguro.getTipoSeguro().equals("SC"))  creditoTO.setIndicadorSeguroCesantia("SI");
				if (seguro.getTipoSeguro().equals("SD"))  creditoTO.setIndicadorSeguroDeshaucio("SI");
				if (seguro.getTipoSeguro().equals("SV"))  creditoTO.setIndicadorSeguroInvalidez("SI");
			}
		}
		return seguros;
	}

}
