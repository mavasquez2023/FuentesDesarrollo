/**
 * 
 */
package cl.laaraucana.pubnominas.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.laaraucana.pubnominas.persistence.DaoEstadisticas;
import cl.laaraucana.pubnominas.vo.DescargasxTipoNomina;
import cl.laaraucana.pubnominas.vo.NominasPeriodoVO;


/**
 * @author J-Factory
 *
 */
@Service
public class EstadisticasServiceImpl implements EstadisticasService{
	
	@Autowired
	private DaoEstadisticas daoEst;
	

	@Override
	public List<NominasPeriodoVO> getNominasxMes() {
		return daoEst.getNominasxMes();
	}


	@Override
	public List<DescargasxTipoNomina> getDescargasxRol(String periodo)
			throws Exception {
		List<DescargasxTipoNomina> descargasxTipo= daoEst.getNominasxRol(periodo);
		return descargasxTipo;
	}


		
}
