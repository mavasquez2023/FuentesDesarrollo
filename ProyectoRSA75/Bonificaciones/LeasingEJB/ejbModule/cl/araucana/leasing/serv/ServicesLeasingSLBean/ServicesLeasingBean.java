package cl.araucana.leasing.serv.ServicesLeasingSLBean;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;
import cl.araucana.leasing.dao.DAOFactory;
import cl.araucana.leasing.dao.LeasingDAO;
import cl.araucana.leasing.vo.CuentaAhorroVO;

import com.schema.util.FileSettings;
import com.schema.util.GeneralException;

/**
 * @author asepulveda
 * Bean implementation class for Enterprise Bean: ServicesLeasing
 * Servicios de Consulta a Información de La Araucana
 */
public class ServicesLeasingBean implements javax.ejb.SessionBean {
	
	/** Serial */
	private static final long serialVersionUID = 1L;	
	
	Logger logger = Logger.getLogger(ServicesLeasingBean.class);
	private LeasingDAO leasingDao;
	
	private javax.ejb.SessionContext mySessionCtx;
	/**
	 * getSessionContext
	 */
	
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}
	
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {		
		// Recurso DAO de Leasing
		int daoType = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/leasing/dao-type"));	
		try {
			DAOFactory daoFactory = (DAOFactory)DAOFactory.getDAOFactory(daoType);
			leasingDao = daoFactory.getLeasingDAO();
		} catch (Exception e) {
			throw new javax.ejb.CreateException(e.getMessage());
		}
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
	
	/** 
	 * Obtiene información de las cuentas de ahorro que tiene el rut consultado
	 * @param long rut
	 * @return Collection 
	 */
	public Collection getListaCuentaAhorroByRut(long rut) throws Exception, BusinessException,GeneralException{
		return leasingDao.getListaCuentaAhorroByRut(rut);
		
	}
	
	/** 
	 * Obtiene el detalle de una cartola de ahorro
	 * @param CuentaAhorroVO cuenta
	 * @return Collection DetalleMovimientosCuentaAhorroVO
	 */
	public Collection getDetalleCuentaAhorroByCuentaAhorro(CuentaAhorroVO cuenta) throws Exception, BusinessException, GeneralException {
		return leasingDao.getDetalleCuentaAhorroByCuentaAhorro(cuenta);
	}
	
	public Collection getValorUF(Date fechaCalculo) throws Exception{
		Calendar cal = new GregorianCalendar();
		cal.setTime(fechaCalculo);
		int anio = cal.get(Calendar.YEAR);
		int mes = cal.get(Calendar.MONTH) + 1;
		int dia = cal.get(Calendar.DATE);
		
		String sFecha = new Integer(anio).toString();
		sFecha += (mes <= 9) ? "0" + new Integer(mes).toString() : new Integer(mes).toString();
		sFecha += (dia <= 9) ? "0" + new Integer(dia).toString() : new Integer(dia).toString();
		
		return leasingDao.getValorUF(sFecha);
	}
	
}
