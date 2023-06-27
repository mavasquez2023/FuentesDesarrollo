package cl.araucana.tesoreria.serv.ServicesTesoreriaSLBean;

import cl.araucana.common.BusinessException;
import cl.araucana.tesoreria.model.Comprobante;

/**
 * Remote interface for Enterprise Bean: ServicesTesoreria
 */
public interface ServicesTesoreria extends javax.ejb.EJBObject {
	
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
	public long registrarMovimientoTesoreriaBienestar(Comprobante comprobante) throws Exception, BusinessException;
	
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
	public long registrarMovimientoTesoreriaAraucana(Comprobante comprobante) throws Exception, BusinessException;
	
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
	public String getEstadoComprobanteTesoreriaBienestar(long folio) throws Exception, BusinessException;
	
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
	public String getEstadoComprobanteTesoreriaAraucana(long folio) throws Exception, BusinessException;
	
	/**
	 * Anula un comprobante de ingreso
	 * utiliza el metodo anulaComprobanteIngreso
	 * @param long folio
	 * @param String usuario
	 * @return int que indica la cantidad de filas actualizadas
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int anulaComprobanteIngresoTesoreriaBienestar(long folio, String usuario) throws Exception, BusinessException;
	
	/**
	 * Anula un comprobante de ingreso
	 * utiliza el metodo anulaComprobanteIngreso
	 * @param long folio
	 * @param String usuario
	 * @return int que indica la cantidad de filas actualizadas
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int anulaComprobanteIngresoTesoreriaAraucana(long folio, String usuario) throws Exception, BusinessException;
	
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
	public boolean anulaComprobanteEgresoTesoreriaBienestar(long folio) throws Exception, BusinessException;
	
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
	public boolean anulaComprobanteEgresoTesoreriaAraucana(long folio) throws Exception, BusinessException;
		
}
