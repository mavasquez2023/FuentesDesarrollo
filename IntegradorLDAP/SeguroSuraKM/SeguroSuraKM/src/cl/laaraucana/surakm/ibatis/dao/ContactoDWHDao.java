/**
 * 
 */
package cl.laaraucana.surakm.ibatis.dao;

import java.util.Date;
import java.util.List;

import cl.laaraucana.surakm.ibatis.vo.ParamContacto;

/**
 * @author IBM Software Factory
 *
 */
public interface ContactoDWHDao {
	public List<String> getMail(ParamContacto param) throws Exception;
	
	public List<String> getCelular(ParamContacto param) throws Exception;
	
	public Date getFechaCorte(int rut) throws Exception;
}
