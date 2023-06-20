package cl.laaraucana.ventaremota.services;


import cl.laaraucana.ventaremota.entities.UsuarioEntiti;
import cl.laaraucana.ventaremota.ws.vo.CredencialesVO;




public interface UsuarioService {

	public UsuarioEntiti consultaCredenciales(CredencialesVO user) throws Exception;

}
