package cl.laaraucana.licenciagestion.services;


import java.util.List;
import java.util.Map;

import cl.laaraucana.licenciagestion.vo.OficinasLicenciasVO;
import cl.laaraucana.licenciagestion.vo.LicenciasPeriodoVO;
import cl.laaraucana.licenciagestion.vo.LicenciasViaIngresoVO;



public interface RegistroLicenciasService {
	
	public int rangoLicencias(String periodo) throws Exception;
	
	public List<LicenciasPeriodoVO> licenciasxMes() throws Exception;
	
	public List<OficinasLicenciasVO> OficinasxRango(Map<String, Integer> rangos, String periodo) throws Exception;
	
	public List<LicenciasViaIngresoVO> licenciasxViaIngeso(String periodo) throws Exception;

}
