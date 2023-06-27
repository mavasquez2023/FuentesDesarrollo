package cl.araucana.contabilidad.dao;

import org.apache.log4j.Logger;

import cl.araucana.common.BusinessException;
import cl.araucana.contabilidad.model.Asiento;
import cl.araucana.contabilidad.model.Linea;

/**
 * @author asepulveda
 *
 */
public class DummyContabilidadDAO implements ContabilidadDAO {
	Logger logger = Logger.getLogger(DummyContabilidadDAO.class);
//	private final static String PREFIX="Dummy-";
	
	public static final int CONTABILIDAD_BIENESTAR=0;
	
//	private String contabilidadBienestarDatabase;
//	private String contabilidadBienestarJNDIDataSource;
	
	/**
	 * Constructor de DAO
	 * Recupera nombre de Bases de Datos utilizadas
	 */
	public DummyContabilidadDAO(){
//		contabilidadBienestarDatabase=FileSettings.getValue(AppConfig.getInstance().settingsFileName,
//			 "/application-settings/databases/contabilidad-bienestar");
//		contabilidadBienestarJNDIDataSource=FileSettings.getValue(AppConfig.getInstance().settingsFileName,
//			 "/application-settings/jdbc/contabilidad-bienestar");
	}
	
	/**
	 * Crea un Asiento Contable
	 * @param asiento
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void insertAsiento(Asiento asiento, int esquemaContable) throws Exception, BusinessException {
		
		return;
		
	}

	/**
	 * Crea un Detalle para un Asiento Contable
	 * @param linea
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void insertLinea(Linea linea, int esquemaContable) throws Exception, BusinessException {

		return;
		
	}
}
