package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.util.HashMap;
import java.util.List;

import cl.laaraucana.rendicionpagonomina.ibatis.vo.BecasEntity;

public interface BecasDao {
	
	public List<BecasEntity> consultaBecadosBES() throws Exception;
	
	public int updateBecado(HashMap<String, String> params) throws Exception;
	
	public int updateNominaBecados(HashMap<String, String> params) throws Exception;
	
	public int rollbackBecados(Integer idCabecera) throws Exception;
	
	public int updateBecadoRendicion(HashMap<String, String> params) throws Exception;
	
}
