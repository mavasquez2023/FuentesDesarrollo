/**
 * 
 */
package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.util.HashMap;
import java.util.List;

/**
 * @author IBM Software Factory
 *
 */
public interface AutorizarDao {
	
	public List<String> getUsuariosConvenio(int convenio) throws Exception;
	
	public void insertUsuario(HashMap<String, String> params) throws Exception;
	
	public void deleteUsuario(HashMap<String, String> params) throws Exception;
}
