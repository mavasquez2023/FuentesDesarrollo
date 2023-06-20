/**
 * 
 */
package cl.laaraucana.muvu.services;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.muvu.dao.GenericDao;
import cl.laaraucana.muvu.entities.Bitacora;
import cl.laaraucana.muvu.vo.AfiliadoVo;

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
	public void insertBitacora(AfiliadoVo data_afiliado) throws Exception {
		String rut= data_afiliado.getRut().split("-")[0];
		String dv= data_afiliado.getRut().split("-")[1];
		
		Bitacora bita= new Bitacora();
		bita.setRutAfiliado(Long.parseLong(rut));
		bita.setDvAfiliado(dv);
		bita.setEmail(data_afiliado.getEmail());
		bita.setFechaNacimiento(data_afiliado.getFechaNacimiento());
		bita.setFechaEnrolamiento(new Date());
		if(data_afiliado.getEstado().equals("1")){
			bita.setEstado("Vigente");
		}else{
			bita.setEstado("No Vigente");
		}
		bita.setMotivo(data_afiliado.getCausa());
		
		logger.info("Grabando bitacora para Rut= " + data_afiliado.getRut());
		dao.save(bita);
	}


}
