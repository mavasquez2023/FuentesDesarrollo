package cl.laaraucana.rendicionpagonomina.services;

import java.util.List;

import cl.laaraucana.rendicionpagonomina.entities.CabeceraManualEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ResumenCargaPagoManualVo;
import cl.laaraucana.rendicionpagonomina.vo.NominaManualVo;

public interface CabeceraManualService {

	public CabeceraManualEntity save(CabeceraManualEntity entity) throws Exception;

	public void update(CabeceraManualEntity entity) throws Exception;
	
	public List<CabeceraManualEntity> findByParams(NominaManualVo params) throws Exception;
	
	public CabeceraManualEntity findById(long id) throws Exception;
	
	public void updateMontoPendiente(long idCabecera) throws Exception;
	
	public boolean updateTotalesNomina(long idCabecera) throws Exception;
	
}
