package cl.araucana.prestamo.serv.ServicesPrestamoSLBean;

import java.util.ArrayList;
import java.util.Collection;

import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;
import cl.araucana.prestamo.dao.DAOFactory;
import cl.araucana.prestamo.dao.PrestamoDAO;
import cl.araucana.prestamo.vo.ImpuestoVO;

import com.schema.util.FileSettings;


/**
 * @author aituarte
 * Bean implementation class for Enterprise Bean: ServicesPrestamo
 * Servicios de Consulta a Información de Prestamos de La Araucana
 */
public class ServicesPrestamoBean implements javax.ejb.SessionBean {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	private PrestamoDAO prestamoDao;
	
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
		// Recurso DAO de Prestamo
		int daoType = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/prestamo/dao-type"));	
		try {
			DAOFactory daoFactory = (DAOFactory)DAOFactory.getDAOFactory(daoType);
			prestamoDao = daoFactory.getPrestamoDAO();
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
	 * Calcula el valor del impuesto segun nº de cuotas y monto de dinero solicitado
	 * @param monto
	 * @param numCuotas
	 * @return valor del impuesto
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ImpuestoVO calcularImpuesto(double monto, int numCuotas) throws Exception,BusinessException{
		int numCuotasLt = numCuotas + 1;
		int valorParcial = (int)monto;
		int montoImpuesto = 0;
		
		double hasta12CotasLT = Double.parseDouble(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/prestamo/param/hasta-12-cuotasLT"));
		double masDe12CotasLT = Double.parseDouble(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/prestamo/param/mas-de-12-cuotasLT"));
		
		
		ImpuestoVO impuesto = new ImpuestoVO();
		
		do {
			if (numCuotasLt <= 12) {
				valorParcial = (int)(valorParcial*hasta12CotasLT*numCuotasLt)/100;
				montoImpuesto = montoImpuesto + valorParcial;
			}else {
				valorParcial = (int)(valorParcial*masDe12CotasLT)/100;
				montoImpuesto = montoImpuesto + valorParcial;
			}
		} while (valorParcial != 0);

		impuesto.setCapitalCredito((int)monto+montoImpuesto);
		impuesto.setCuotasLT(numCuotas+1);
		impuesto.setImpuesto(montoImpuesto);
		return impuesto;
	}

	/**
	 * Obtiene las cuotas por Cobrar
	 * @return ArrayList de Cuotas
	 * @throws Exception
	 * @throws BusinessException
	 */ 

	public ArrayList getCuotas() throws Exception, BusinessException {
		return prestamoDao.getCuotas();
	}
	
	/**
	 * @param rut
	 * @param numCuotaActual
	 * @param numTotalCuotas
	 * return Collection
	 */
	public Collection getMontoUFPrestamoActivo(String rut, int numCuota, int numTotalCuotas)  throws Exception, BusinessException{
		return prestamoDao.getMontoUFPrestamoActivo(rut,numCuota,numTotalCuotas);
	}


	/**
	 * @param idPrestamo
	 */
	public Collection getInteresesPagoAnticipado(long idPrestamo,int cuotaLimIni,int cuotaLimFinal)  throws Exception, BusinessException{
		return prestamoDao.getInteresesPagoAnticipado(idPrestamo,cuotaLimIni, cuotaLimFinal);
	}
	
	/**
	 * @param idPrestamo
	 */
	public Collection getInteresPagoAnticipadoPeriodo(long idPrestamo,int idCuota)  throws Exception, BusinessException{
		return prestamoDao.getInteresesPagoAnticipadoPeriodo(idPrestamo,idCuota);
	}
	
	/**
		 * Obtiene las cuotas por Cobrar de un afiliado
		 * @return ArrayList de Cuotas
		 * @throws Exception
		 * @throws BusinessException
		 */ 
	public ArrayList getCuotas(String rut) throws Exception, BusinessException {
		return prestamoDao.getCuotas(rut);
	}
}
