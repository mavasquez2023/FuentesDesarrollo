package cl.araucana.contabilidad.serv.ServicesContabilidadSLBean;

import org.apache.log4j.Logger;

import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;
import cl.araucana.contabilidad.dao.ContabilidadDAO;
import cl.araucana.contabilidad.dao.DAOFactory;
import cl.araucana.contabilidad.dao.DB2ContabilidadDAO;
import cl.araucana.contabilidad.dao.FolioDAO;
import cl.araucana.contabilidad.model.Asiento;
import cl.araucana.contabilidad.model.Linea;

import com.schema.util.FileSettings;

/**
 * Bean implementation class for Enterprise Bean: ServicesContabilidad
 */
public class ServicesContabilidadBean implements javax.ejb.SessionBean {
	
	/** Serial */
	private static final long serialVersionUID = 1L;
	
	private ContabilidadDAO contabilidadDao;
	private FolioDAO folioDao;
	
	Logger logger = Logger.getLogger(ServicesContabilidadBean.class);	
	
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
		// Recurso DAO de Contabilidad
		int daoType = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/contabilidad/dao-type"));	
		try {
			DAOFactory daoFactory = (DAOFactory)DAOFactory.getDAOFactory(daoType);
			//DAO de Contabilidad
			contabilidadDao = daoFactory.getContabilidadDAO();
			//DAO para obtención de Folios para Contabilidad (Asientos Contables)
			folioDao = daoFactory.getFolioDAO();
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
	 * Crea un Asiento contable en el esquema de Bienestar
	 * con sus líneas de detalle
	 * @param asiento
	 * @param lineas
	 * @throws Exception
	 * @throws BusinessException
	 */
	public long creaAsientoContableBienestar(Asiento asiento) throws Exception, BusinessException {
		return creaAsientoContable(asiento, DB2ContabilidadDAO.CONTABILIDAD_BIENESTAR);
	}
	
	/**
	 * Crea un Asiento contable con sus líneas de detalle
	 * es generico, se le puede pasar como parametro el esquema contable
	 * @param asiento
	 * @param lineas
	 * @throws Exception
	 * @throws BusinessException
	 */
	public long creaAsientoContable(Asiento asiento, int esquemaContable) throws Exception, BusinessException {
		
		long folio=0;
		
		//Valido información del Asiento Contable
		if (asiento==null)
			throw new BusinessException("CCAF_CONTA_ASIENTOINVALIDO",
				"La información del Asiento Contable es Nula");
		//Valido información del Detalle del Asiento Contable		
		if (asiento.getLineas()==null || asiento.getLineas().size()==0)
			throw new BusinessException("CCAF_CONTA_LINEAINVALIDO",
				"La información del detalle del Asiento Contable es Nula");
		
		//Obtiene un Folio
		folio = folioDao.getFolio();
		if (folio==0)
			throw new BusinessException("CCAF_CONTA_FOLIOINVALIDO",
			   "No se pudo obtener número de Folio");
		
		asiento.setBhSeq(folio);
		//Crea el registro del Asiento contable 
		contabilidadDao.insertAsiento(asiento,esquemaContable);
		
		//Crea los registros de detalle del Asiento contable
		for (int x=0;x<asiento.getLineas().size();x++) {
			Linea linea = (Linea) asiento.getLineas().get(x);
			linea.setBlSeq(folio);
			linea.setBlLnum(x+1);
			contabilidadDao.insertLinea(linea,esquemaContable);
		}
		return folio;
	}

}
