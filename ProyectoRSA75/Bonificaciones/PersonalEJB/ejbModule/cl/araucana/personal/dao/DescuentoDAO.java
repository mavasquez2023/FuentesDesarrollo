package cl.araucana.personal.dao;


import java.util.ArrayList;

import cl.araucana.common.BusinessException;

/**
 * @author asepulveda
 * Metodos expuestos por DescuentoDAO
 */
public interface DescuentoDAO {
 
	/**
	 * Registra los descuentos en el archivo txt especificado
	 * @param ArrayList descuento
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void insertDescuento(ArrayList descuentos, String fileName) throws Exception,BusinessException;

}
