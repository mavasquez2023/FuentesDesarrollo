/**
 * 
 */
package cl.laaraucana.ventaremota.services;

import java.util.List;

import cl.laaraucana.ventaremota.ibatis.vo.AutenticacionVO;

/**
 * @author IBM Software Factory
 *
 */
public interface AutenticacionService {
	public List<AutenticacionVO> getAutenticacionHabilitada() throws Exception;
	
}
