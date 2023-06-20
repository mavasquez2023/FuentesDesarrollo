package cl.laaraucana.pubnominas.services;

import java.util.List;

import cl.laaraucana.pubnominas.vo.DescargasxTipoNomina;
import cl.laaraucana.pubnominas.vo.NominasPeriodoVO;



public interface EstadisticasService {

	public List<NominasPeriodoVO> getNominasxMes() throws Exception;
	
	public List<DescargasxTipoNomina> getDescargasxRol(String periodo)throws Exception;
}
