/**
 * 
 */
package cl.laaraucana.apofam.services;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.apofam.dao.GenericDao;
import cl.laaraucana.apofam.entities.Cargas;

/**
 * @author J-Factory
 *
 */
@Service
public class CargasServiceImpl implements CargasService {
	private static final Logger logger = Logger.getLogger(CargasServiceImpl.class);
	@Autowired
	private GenericDao<Cargas> dao;
	
	/* (non-Javadoc)
	 * @see cl.laaraucana.muvu.services.BitacoraService#insertBitacora(cl.laaraucana.muvu.vo.AfiliadoVo)
	 */
	

	@Override
	public Cargas findByRut(int rut) throws Exception {
		List<Cargas> resumen= dao.findByRut(Cargas.class, rut);
		if(resumen!=null && resumen.size()>0){
			return resumen.get(0);
		}
		return null;
	}




}
