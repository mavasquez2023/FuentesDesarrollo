package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.util.HashMap;
import java.util.List;

import cl.laaraucana.rendicionpagonomina.entities.CabeceraEntity;
import cl.laaraucana.rendicionpagonomina.entities.CabeceraManualEntity;
import cl.laaraucana.rendicionpagonomina.vo.NominaManualVo;


public interface CabeceraManualDao {

	public List<CabeceraManualEntity> findManualByParams(NominaManualVo params) throws Exception;
	
	public CabeceraManualEntity findById(long idCabecera) throws Exception;
	
	public int updateMontoPendiente(long idCabecera) throws Exception;
	
	public HashMap<String, Object> getSumTotalesPorEstadoPago(Integer idCabecera, Integer estadoPago  ) throws Exception;
	
	public boolean actualizarTotalesRendicion(CabeceraManualEntity cabecera) throws Exception;
}
