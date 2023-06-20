/**
 * 
 */
package cl.laaraucana.mandato.ibatis.dao;

import java.util.HashMap;
import java.util.List;

import cl.laaraucana.mandato.ibatis.vo.RechazoVo;


/**
 * @author IBM Software Factory
 *
 */
public interface RechazoDao {
	public List<RechazoVo> consultaRechazos() throws Exception;
	
	public void updateRechazoByRut(HashMap<String, Integer> sets) throws Exception;
	
	public void insertRechazo(RechazoVo rechazo) throws Exception;
}
