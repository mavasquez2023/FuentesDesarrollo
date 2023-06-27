package cl.araucana.bienestar.bonificaciones.dao;


import java.util.ArrayList;

import cl.araucana.common.BusinessException;

/**
 * @author asepulveda
 * Metodos expuestos por ParametrosDAO
 */
public interface ParametrosDAO {
 
	/**
	 * Retorna una lista que contiene:
	 * Tipo, codigo y descripción de un parametro utilizado en la aplicación
	 * @param int tipo
	 * @param String codigoPadre
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getParametros(int tipo, String codigoPadre) throws Exception,BusinessException;
	
	/**
	 * Retorna una lista que contiene:
	 * codigo, concepto y area-negocio de un parametro utilizado en la aplicación
	 * @param int tipo
	 * @param String codigoPadre
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getParametrosSaldoDeudaTotal() throws Exception,BusinessException;

}
