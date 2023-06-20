/**
 * 
 */
package cl.laaraucana.rendicionpagonomina.services;

import java.util.List;
import java.util.Map;

import cl.laaraucana.rendicionpagonomina.ibatis.vo.BancoEntity;

/**
 * @author IBM Software Factory
 *
 */
public interface BancoService {
	public List<String> consultaBancosConvenio()  throws Exception;
	
	public List<BancoEntity> getBancos()  throws Exception;
	
	public Map<String, BancoEntity> getMapBancos() throws Exception;
	 
}
