/**
 * 
 */
package cl.laaraucana.mandato.services;

import java.util.HashMap;
import java.util.List;

import cl.laaraucana.mandato.ibatis.vo.RechazoVo;


/**
 * @author J-Factory Solutions
 *
 */
public interface RechazoService {
	public List<RechazoVo> consultaRechazados() throws Exception;
	
	public void updateRechazoByRut(HashMap<String, Integer> sets) throws Exception;
	
	public void insertRechazo(RechazoVo rechazoVo) throws Exception;
}
