/**
 * 
 */
package cl.laaraucana.muvu.services;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.muvu.dao.GenericDao;
import cl.laaraucana.muvu.entities.Bitacora;
import cl.laaraucana.muvu.entities.Resumen;
import cl.laaraucana.muvu.vo.AfiliadoVo;

/**
 * @author J-Factory
 *
 */
@Service
public class ResumenServiceImpl implements ResumenService {
	private static final Logger logger = Logger.getLogger(ResumenServiceImpl.class);
	@Autowired
	private GenericDao<Resumen> dao;
	
	/* (non-Javadoc)
	 * @see cl.laaraucana.muvu.services.BitacoraService#insertBitacora(cl.laaraucana.muvu.vo.AfiliadoVo)
	 */
	@Override
	public void insertResumen(AfiliadoVo data_afiliado) throws Exception {
		String rut= data_afiliado.getRut().split("-")[0];
		String dv= data_afiliado.getRut().split("-")[1];
		
		Resumen resumen= new Resumen();
		resumen.setRutAfiliado(Integer.parseInt(rut));
		resumen.setDvAfiliado(dv);
		resumen.setEmail(data_afiliado.getEmail());
		resumen.setFechaNacimiento(data_afiliado.getFechaNacimiento());
		resumen.setFechaEnrolamiento(new Date());
		if(data_afiliado.getEstado().equals("1")){
			resumen.setEstado("Vigente");
		}else{
			resumen.setEstado("No Vigente");
		}
		
		
		logger.info("Grabando bitacora para Rut= " + data_afiliado.getRut());
		dao.save(resumen);
	}

	@Override
	public void updateResumen(AfiliadoVo data_afiliado) throws Exception {
		// TODO Auto-generated method stub
		long rut= Long.parseLong(data_afiliado.getRut().split("-")[0]);
		List<Resumen> listaResumen= dao.findByRut(Resumen.class, rut);
		if(listaResumen!=null && listaResumen.size()>0){
			Resumen resumen= listaResumen.get(0);
			if(data_afiliado.getEstado().equals("1")){
				resumen.setEstado("Vigente");
			}else{
				resumen.setEstado("No Vigente");
			}
			resumen.setMotivo(data_afiliado.getCausa());
			resumen.setFechaEnrolamiento(new Date());
			dao.update(resumen);
		}else{
			insertResumen(data_afiliado);
		}
	}

	@Override
	public Resumen findByRut(int rut) throws Exception {
		List<Resumen> resumen= dao.findByRut(Resumen.class, rut);
		if(resumen!=null && resumen.size()>0){
			return resumen.get(0);
		}
		return null;
	}

	@Override
	public void updateResumen(Resumen resumen) throws Exception {
		dao.update(resumen);
		
	}



}
