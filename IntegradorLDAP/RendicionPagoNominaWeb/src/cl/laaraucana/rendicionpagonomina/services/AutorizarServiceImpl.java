/**
 * 
 */
package cl.laaraucana.rendicionpagonomina.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.laaraucana.rendicionpagonomina.ibatis.dao.AutorizarDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.AutorizarDaoImpl;

/**
 * @author J-Factory
 *
 */
@Service
public class AutorizarServiceImpl implements AutorizarService {
	
	private AutorizarDao daoAut= new AutorizarDaoImpl();
	
	@Override
	public List<String> consultaUsuariosConvenio(int convenio) throws Exception {
		return daoAut.getUsuariosConvenio(convenio);
	}

	@Override
	public void insertUsuario(String idUsuario, String idConvenio) throws Exception {
		HashMap<String, String> params= new HashMap<String, String>();
		params.put("idUsuario", idUsuario);
		params.put("idConvenio", idConvenio);
		daoAut.insertUsuario(params);
	}

	@Override
	public void deleteUsuario(String idUsuario, String idConvenio) throws Exception {
		HashMap<String, String> params= new HashMap<String, String>();
		params.put("idUsuario", idUsuario);
		params.put("idConvenio", idConvenio);
		daoAut.deleteUsuario(params);
	}

}
