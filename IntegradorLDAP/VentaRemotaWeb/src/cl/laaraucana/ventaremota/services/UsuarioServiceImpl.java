package cl.laaraucana.ventaremota.services;

import cl.laaraucana.ventaremota.entities.UsuarioEntiti;
import cl.laaraucana.ventaremota.ibatis.dao.CredencialesDao;
import cl.laaraucana.ventaremota.ibatis.dao.CredencialesDaoImpl;
import cl.laaraucana.ventaremota.ws.vo.CredencialesVO;


public class UsuarioServiceImpl implements UsuarioService {
	

	public UsuarioEntiti consultaCredenciales(CredencialesVO user) throws Exception {
		CredencialesDao dao= new CredencialesDaoImpl();
		return  dao.consultaCredenciales(user);
	}

}
