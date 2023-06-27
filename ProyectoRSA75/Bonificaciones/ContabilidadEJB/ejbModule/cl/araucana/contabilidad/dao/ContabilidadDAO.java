package cl.araucana.contabilidad.dao;


import cl.araucana.common.BusinessException;
import cl.araucana.contabilidad.model.Asiento;
import cl.araucana.contabilidad.model.Linea;

/**
 * @author aituarte
 * Metodos expuestos por ContabilidadDAO
 */
public interface ContabilidadDAO {

	/**
	 * Crea un Asiento Contable
	 * @param asiento
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void insertAsiento(Asiento asiento, int esquemaContable) throws Exception, BusinessException;
	
	/**
	 * Crea un Detalle para un Asiento Contable
	 * @param linea
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void insertLinea(Linea linea, int esquemaContable) throws Exception, BusinessException;

}
