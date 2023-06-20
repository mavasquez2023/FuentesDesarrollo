/**
 * 
 */
package cl.laaraucana.ventaremota.ibatis.dao;

import java.util.List;

import cl.laaraucana.ventaremota.entities.CreditoEntiti;
import cl.laaraucana.ventaremota.ibatis.vo.AutenticacionVO;

/**
 * @author IBM Software Factory
 *
 */
public interface CreditosDao {
	public void insertCredito(CreditoEntiti credito) throws Exception;
	
	public List<AutenticacionVO> getAutenticacionHabilitada() throws Exception;
}
