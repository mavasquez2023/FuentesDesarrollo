package cl.araucana.tesoreria.dao;

import org.apache.log4j.Logger;

import cl.araucana.common.BusinessException;
import cl.araucana.tesoreria.model.Comprobante;
import cl.araucana.tesoreria.model.Detalle;

import com.schema.util.InstanceGenerator;

/**
 * @author asepulveda
 *
 */
public class DummyComprobanteDAO implements ComprobanteDAO {
	Logger logger = Logger.getLogger(DummyComprobanteDAO.class);
//	private final static String PREFIX="DB2-";
	
	public static final int TESORERIA_BIENESTAR=0;
	public static final int TESORERIA_ARAUCANA=1;
	
//	private String tesoreriaBienestarDatabase;
//	private String tesoreriaAraucanaDatabase;
//	private String tesoreriaBienestarJNDIDataSource;
//	private String tesoreriaAraucanaJNDIDataSource;
	
	/**
	 * Constructor de DAO
	 * Recupera nombre de Bases de Datos utilizadas
	 */
	public DummyComprobanteDAO(){
//		tesoreriaBienestarDatabase=FileSettings.getValue(AppConfig.getInstance().settingsFileName,
//			 "/application-settings/databases/tesoreria-bienestar");		
//		tesoreriaBienestarJNDIDataSource=FileSettings.getValue(AppConfig.getInstance().settingsFileName,
//			 "/application-settings/jdbc/tesoreria-bienestar");
//		tesoreriaAraucanaDatabase=FileSettings.getValue(AppConfig.getInstance().settingsFileName,
//			 "/application-settings/databases/tesoreria-araucana");		
//		tesoreriaAraucanaJNDIDataSource=FileSettings.getValue(AppConfig.getInstance().settingsFileName,
//			 "/application-settings/jdbc/tesoreria-araucana");
	}
	
	/**
	 * Crea un nuevo Comprobante de Reembolso en Tesoreria
	 * @param comprobante: el Objeto Comprobante
	 */
	public void insertComprobante(Comprobante comprobante,int tesoreria) throws Exception, BusinessException {

		return;
		
	}
	
	/**
	 * Crea un nuevo Detalle de Reembolso en Tesoreria
	 * @param Detalle: el Objeto Detalle
	 */
	public void insertDetalle(Detalle detalle, int tesoreria) throws Exception, BusinessException {

		return;
		
	}
	
	/**
	 * Consulta por el estado de un comprobante dado un número de folio
	 * Consulta por el folio en la Tesoreria indicada en el parametro
	 * @param long folio
	 * @param int tesoreria
	 * @return String:
	 * 				estado si encuentra el folio
	 * 				null si no encuentra el folio
	 * @throws Exception
	 * @throws BusinessException
	 */
	public String getEstadoComprobante(long folio, int tesoreria) throws Exception, BusinessException {

		return (String)InstanceGenerator.build(String.class);
		
	} 	

	/**
	 * Anula un comprobante de ingreso
	 * @param long folio
	 * @param int tesoreria
	 * @param String usuario
	 * @return int que indica la cantidad de filas actualizadas
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int anulaComprobanteIngreso(long folio, int tesoreria, String usuario) throws Exception, BusinessException {

		return 1;
		
	} 	

}
