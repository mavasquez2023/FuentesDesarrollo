package cl.araucana.tesoreria.dao;


import cl.araucana.common.BusinessException;
import cl.araucana.tesoreria.model.Comprobante;
import cl.araucana.tesoreria.model.Detalle;

/**
 * @author asepulveda
 * Metodos expuestos por TesoreriaDAO
 */
public interface ComprobanteDAO {

	/**
	 * Crea un nuevo Comprobante de Reembolso en Tesoreria
	 * @param comprobante: el Objeto Comprobante
	 */
	public void insertComprobante(Comprobante comprobante, int tesoreria) throws Exception, BusinessException;
	
	/**
	 * Crea un nuevo Detalle de Reembolso en Tesoreria
	 * @param Detalle: el Objeto Detalle
	 */
	public void insertDetalle(Detalle detalle, int tesoreria) throws Exception, BusinessException;
	
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
	public String getEstadoComprobante(long folio, int tesoreria) throws Exception, BusinessException;
	
	/**
	 * Anula un comprobante de ingreso
	 * @param long folio
	 * @param int tesoreria
	 * @param String usuario
	 * @return int que indica la cantidad de filas actualizadas
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int anulaComprobanteIngreso(long folio, int tesoreria, String usuario) throws Exception, BusinessException;

}
