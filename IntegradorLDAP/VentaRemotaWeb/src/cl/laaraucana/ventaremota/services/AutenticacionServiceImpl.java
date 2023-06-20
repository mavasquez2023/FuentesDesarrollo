/**
 * 
 */
package cl.laaraucana.ventaremota.services;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.laaraucana.ventaremota.ibatis.dao.CreditosDao;
import cl.laaraucana.ventaremota.ibatis.dao.CreditosDaoImpl;
import cl.laaraucana.ventaremota.ibatis.vo.AutenticacionVO;

/**
 * @author J-Factory
 *
 */
@Service
public class AutenticacionServiceImpl implements AutenticacionService {
	
	private CreditosDao creditoDao= new CreditosDaoImpl();
	
	/* (non-Javadoc)
	 * @see cl.laaraucana.ventaremota.services.AutenticacionService#getAutenticacionHabilitada()
	 */
	@Override
	public List<AutenticacionVO> getAutenticacionHabilitada() throws Exception {
			
		return creditoDao.getAutenticacionHabilitada();
	}

}
