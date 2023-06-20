/**
 * 
 */
package cl.laaraucana.rendicionpagonomina.services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cl.laaraucana.rendicionpagonomina.ibatis.dao.BancoDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.BancoDaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.BancoEntity;

/**
 * @author J-Factory
 *
 */
@Service
public class BancoServiceImpl implements BancoService {
	
	private BancoDao daoBanco= new BancoDaoImpl();
	
	@Override
	public List<String> consultaBancosConvenio() throws Exception {
		return daoBanco.consultaBancosConvenio();

	}

	@Override
	public List<BancoEntity> getBancos() throws Exception {
		// TODO Auto-generated method stub
		return daoBanco.getBancos();
	}

	@Override
	public Map<String, BancoEntity> getMapBancos() throws Exception {
		List<BancoEntity> listaBancos= daoBanco.getBancos();
		Map<String, BancoEntity> listaMap= new HashMap<String, BancoEntity>();
		
		for (Iterator iterator = listaBancos.iterator(); iterator.hasNext();) {
			BancoEntity bancoEntity = (BancoEntity) iterator.next();
			listaMap.put(bancoEntity.getCodigo(), bancoEntity);
		}
		return listaMap;
	}

}
