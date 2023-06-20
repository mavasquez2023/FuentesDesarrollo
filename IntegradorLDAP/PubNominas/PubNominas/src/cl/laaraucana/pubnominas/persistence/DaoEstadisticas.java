package cl.laaraucana.pubnominas.persistence;

import java.util.List;

import cl.laaraucana.pubnominas.vo.DescargasxTipoNomina;
import cl.laaraucana.pubnominas.vo.NominasPeriodoVO;

public interface DaoEstadisticas {

	public List<NominasPeriodoVO> getNominasxMes();
	
	public List<DescargasxTipoNomina> getNominasxRol(String periodo);

}
