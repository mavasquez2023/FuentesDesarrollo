/**
 * 
 */
package cl.laaraucana.rendicionpagonomina.services;

import java.util.List;

/**
 * @author IBM Software Factory
 *
 */
public interface AutorizarService {

	public List<String> consultaUsuariosConvenio(int idConvenio) throws Exception;
	
	public void insertUsuario(String idUsuario, String idConvenio)  throws Exception;
	
	public void deleteUsuario(String idUsuario, String idConvenio)  throws Exception;
}
