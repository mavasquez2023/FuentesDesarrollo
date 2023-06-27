package cl.araucana.personal.dao;


import java.util.HashMap;

import cl.araucana.common.BusinessException;

/**
 * @author asepulveda
 * Metodos expuestos por AxisDAO
 */
public interface AxisDAO {
 
    /** 
     * Entrega la lista de bancos en un HashMap
     * 
     * @return HashMap de BancoVO
     * @throws Exception
     */
	public HashMap getListaBancos() throws Exception,BusinessException;
 

}
