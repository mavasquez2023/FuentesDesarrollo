package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.util.HashMap;
import java.util.List;

import cl.laaraucana.rendicionpagonomina.ibatis.vo.ArchivoManualVO;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ConvenioEntity;

public interface ConvenioDao {

	public List<ConvenioEntity> consultaConvenios() throws Exception;
	
	public List<ConvenioEntity> consultaConveniosActivos(HashMap<String, String> param) throws Exception;
	
	public List<ConvenioEntity> consultaConveniosconPlantilla(HashMap<String, String> param) throws Exception;
	
	public List<ConvenioEntity> consultaConveniosActivosManual(HashMap<String, String> param) throws Exception;
	
	public ConvenioEntity getConvenio(int codigo) throws Exception;
	
	public List<ArchivoManualVO> getConvenioTransferencia() throws Exception;
	
}
