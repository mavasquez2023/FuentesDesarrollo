/**
 * 
 */
package cl.laaraucana.apofam.services;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.apofam.dao.GenericDao;
import cl.laaraucana.apofam.entities.Bitacora;
import cl.laaraucana.apofam.entities.Cargas;


/**
 * @author J-Factory
 *
 */
@Service
public class BitacoraServiceImpl implements BitacoraService {
	private static final Logger logger = Logger.getLogger(BitacoraServiceImpl.class);
	@Autowired
	private GenericDao<Bitacora> dao;
	
	/* (non-Javadoc)
	 * @see cl.laaraucana.muvu.services.BitacoraService#insertBitacora(cl.laaraucana.muvu.vo.AfiliadoVo)
	 */
	@Override
	public void insertBitacora(Cargas data_afiliado, String rol) throws Exception {

		Bitacora bita= new Bitacora();
		bita.setRutAfiliado(data_afiliado.getRutAfiliado());
		bita.setDvAfiliado(data_afiliado.getDvAfiliado());
		bita.setNombreAfiliado(data_afiliado.getNombreAfiliado());
		bita.setNumeroCargas(data_afiliado.getNumeroCargas());
		bita.setFechaDescarga(new Date());
		bita.setUsuarioDescarga(rol);
		logger.info("Grabando bitacora para Rut= " + data_afiliado.getRutAfiliado());
		dao.save(bita);
	}


}
