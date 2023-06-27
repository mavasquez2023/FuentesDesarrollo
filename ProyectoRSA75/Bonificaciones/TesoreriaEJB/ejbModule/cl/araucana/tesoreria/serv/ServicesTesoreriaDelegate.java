package cl.araucana.tesoreria.serv;

import javax.naming.InitialContext;

import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;
import cl.araucana.tesoreria.model.Comprobante;
import cl.araucana.tesoreria.serv.ServicesTesoreriaSLBean.ServicesTesoreria;
import cl.araucana.tesoreria.serv.ServicesTesoreriaSLBean.ServicesTesoreriaHome;

import com.schema.patterns.BusinessDelegate;
import com.schema.util.FileSettings;
import com.schema.util.GeneralException;


/**
 * @author asepulveda
 * Business Delegate para Servicios de Tesoreria
 */
public class ServicesTesoreriaDelegate extends BusinessDelegate {
	// Home y Remote del EJB
	private final static Class homeClass=ServicesTesoreriaHome.class;
	private ServicesTesoreria services=null;
	
	/**
	 * Constructor del BDelegate. Establece la conexion con el EJB
	 * @throws GeneralException
	 */
	public ServicesTesoreriaDelegate() throws GeneralException {
		// Nombre JNDI del EJB
		String jndi = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
		              "/application-settings/ejbs/tesoreria/tesoreria-services");
		              
		try {
			InitialContext ic=new InitialContext();
			services = (ServicesTesoreria)super.getServiceBean(ic, jndi,homeClass);
		} catch (Exception e) {
			throw new GeneralException(this,e);
		}
	}
	
	/**
	 * Obtiene un numero de Folio
	 * Crea un Comprobante de Reembolso
	 * Crea los Detalle de Reembolso (Datos omunes)
	 * Crea los Detalle de Reembolso (Líneas de Detalle)
	 * @param comprobante
	 * @return long, número de folio generado
	 * @throws Exception
	 * @throws BusinessException
	 */
	public long registrarMovimientoTesoreriaBienestar(Comprobante comprobante) throws Exception, BusinessException {
		return services.registrarMovimientoTesoreriaBienestar(comprobante);
	}
	
	/**
	 * Obtiene un numero de Folio
	 * Crea un Comprobante de Reembolso
	 * Crea los Detalle de Reembolso (Datos omunes)
	 * Crea los Detalle de Reembolso (Líneas de Detalle)
	 * @param comprobante
	 * @return long, número de folio generado
	 * @throws Exception
	 * @throws BusinessException
	 */
	public long registrarMovimientoTesoreriaAraucana(Comprobante comprobante) throws Exception, BusinessException {
		return services.registrarMovimientoTesoreriaAraucana(comprobante);
	}
	
	/**
	 * Consulta por el estado de un comprobante dado un número de folio
	 * utiliza el metodo getEstadoComprobante
	 * @param long folio
	 * @return String:
	 * 				estado si encuentra el folio
	 * 				null si no encuentra el folio
	 * @throws Exception
	 * @throws BusinessException
	 */
	public String getEstadoComprobanteTesoreriaBienestar(long folio) throws Exception, BusinessException{
					
		return services.getEstadoComprobanteTesoreriaBienestar(folio);
	}
	
	/**
	 * Consulta por el estado de un comprobante dado un número de folio
	 * utiliza el metodo getEstadoComprobante
	 * @param long folio
	 * @return String:
	 * 				estado si encuentra el folio
	 * 				null si no encuentra el folio
	 * @throws Exception
	 * @throws BusinessException
	 */
	public String getEstadoComprobanteTesoreriaAraucana(long folio) throws Exception, BusinessException{
					
		return services.getEstadoComprobanteTesoreriaAraucana(folio);
	}
	
	/**
	 * Anula un comprobante de ingreso
	 * utiliza el metodo anulaComprobanteIngreso
	 * @param long folio
	 * @param String usuario
	 * @return int que indica la cantidad de filas actualizadas
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int anulaComprobanteIngresoTesoreriaBienestar(long folio, String usuario) throws Exception, BusinessException {
	
		return services.anulaComprobanteIngresoTesoreriaBienestar(folio, usuario);	
	}
	
	/**
	 * Anula un comprobante de ingreso
	 * utiliza el metodo anulaComprobanteIngreso
	 * @param long folio
	 * @param String usuario
	 * @return int que indica la cantidad de filas actualizadas
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int anulaComprobanteIngresoTesoreriaAraucana(long folio, String usuario) throws Exception, BusinessException {
	
		return services.anulaComprobanteIngresoTesoreriaAraucana(folio, usuario);	
	}
	
	/**
	 * Anula un comprobante de egreso
	 * utiliza el metodo anulaComprobanteEgreso
	 * @param long folio
	 * @return boolean:
	 * 		true: indica exito
	 * 		false: indica fracaso 
	 * @throws Exception
	 * @throws BusinessException
	 */
	public boolean anulaComprobanteEgresoTesoreriaBienestar(long folio) throws Exception, BusinessException {
	
		return services.anulaComprobanteEgresoTesoreriaBienestar(folio);	
	}
	
	/**
	 * Anula un comprobante de ingreso
	 * utiliza el metodo anulaComprobanteEgreso 
	 * @param long folio
	 * @return boolean:
	 * 		true: indica exito
	 * 		false: indica fracaso
	 * @throws Exception
	 * @throws BusinessException
	 */
	public boolean anulaComprobanteEgresoTesoreriaAraucana(long folio) throws Exception, BusinessException {
	
		return services.anulaComprobanteEgresoTesoreriaAraucana(folio);	
	}			
	
}
