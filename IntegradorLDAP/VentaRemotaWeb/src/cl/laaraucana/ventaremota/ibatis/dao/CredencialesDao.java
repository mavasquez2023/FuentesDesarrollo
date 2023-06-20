/**
 * 
 */
package cl.laaraucana.ventaremota.ibatis.dao;

import cl.laaraucana.ventaremota.entities.UsuarioEntiti;
import cl.laaraucana.ventaremota.ws.vo.CredencialesVO;

/**
 * @author IBM Software Factory
 *
 */
public interface CredencialesDao {
	public UsuarioEntiti consultaCredenciales(CredencialesVO user) throws Exception;
}
