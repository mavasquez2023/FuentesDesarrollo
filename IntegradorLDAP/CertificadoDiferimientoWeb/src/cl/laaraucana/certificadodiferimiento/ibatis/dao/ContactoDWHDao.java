/**
 * 
 */
package cl.laaraucana.certificadodiferimiento.ibatis.dao;

import java.util.List;

/**
 * @author IBM Software Factory
 *
 */
public interface ContactoDWHDao {
	public List<String> getMail(int rut) throws Exception;
	
	public List<String> getTelefono(int rut) throws Exception;
	
	public List<String> getCelular(int rut) throws Exception;
}
