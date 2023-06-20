/**
 * 
 */
package cl.laaraucana.mandato.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.laaraucana.mandato.ibatis.dao.RechazoDao;
import cl.laaraucana.mandato.ibatis.dao.RechazoDaoImpl;
import cl.laaraucana.mandato.ibatis.vo.RechazoVo;


@Service
public class RechazoServiceImpl implements RechazoService {

	/* (non-Javadoc)
	 * @see cl.laaraucana.transferencias.services.RechazoService#consultaRechazados()
	 */
	private RechazoDao dao= new RechazoDaoImpl();
	
	@Override
	public List<RechazoVo> consultaRechazados() throws Exception {
		// TODO Auto-generated method stub
		return dao.consultaRechazos();
	}

	/* (non-Javadoc)
	 * @see cl.laaraucana.transferencias.services.RechazoService#findRechazoByRut(int)
	 */
	@Override
	public void updateRechazoByRut(HashMap<String, Integer> sets) throws Exception {
		// TODO Auto-generated method stub
		dao.updateRechazoByRut(sets);
	}

	@Override
	public void insertRechazo(RechazoVo rechazoVo) throws Exception {
		// TODO Auto-generated method stub
		dao.insertRechazo(rechazoVo);
	}

}
