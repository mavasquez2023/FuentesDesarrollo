package cl.laaraucana.pubnominas.services;

import java.util.Map;

import cl.laaraucana.pubnominas.vo.EmpresasVO;



public interface EmpresasService {

	public Map<String, EmpresasVO> getEmpresas() throws Exception;
	

}
