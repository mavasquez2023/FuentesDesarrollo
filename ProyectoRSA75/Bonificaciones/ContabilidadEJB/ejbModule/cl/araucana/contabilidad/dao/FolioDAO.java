package cl.araucana.contabilidad.dao;


import cl.araucana.common.BusinessException;

/**
 * @author aituarte
 * Metodos expuestos por FolioDAO
 */
public interface FolioDAO {
	
	/**
	 * Genera una número de folio para el asiento contable
	 * @return folio
	 * @throws Exception
	 * @throws BusinessException
	 */
	public long getFolio() throws Exception, BusinessException;

}
