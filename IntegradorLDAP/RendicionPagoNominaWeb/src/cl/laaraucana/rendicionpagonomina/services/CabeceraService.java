package cl.laaraucana.rendicionpagonomina.services;

import java.util.HashMap;
import java.util.List;

import cl.laaraucana.rendicionpagonomina.entities.CabeceraEntity;
import cl.laaraucana.rendicionpagonomina.entities.DetalleEntity;
import cl.laaraucana.rendicionpagonomina.vo.NominaVo;

public interface CabeceraService {

	public CabeceraEntity save(CabeceraEntity entity) throws Exception;

	public void update(CabeceraEntity entity) throws Exception;
	
	public void delete(CabeceraEntity entity) throws Exception;
	
	public List<CabeceraEntity> findAll() throws Exception;
	
	public List<CabeceraEntity> getNominasCabecera(NominaVo nomina) throws Exception;
	
	public void deleteBycodNom(long codNom) throws Exception;
	
	public List<CabeceraEntity> findConvenioAndNomina(long convenio, long nomina) throws Exception;
	
	//ibatis
	public List<CabeceraEntity> findNominasTEF(HashMap<String, String> params) throws Exception;
	
	//ibatis
	public CabeceraEntity findById(long id) throws Exception;
	
	public List<CabeceraEntity> findNominasRendicion(HashMap<String, String> params) throws Exception;
	
	public CabeceraEntity findByCodigoNomina(long codigoNomina) throws Exception;
	
	public void updateNomina(CabeceraEntity entity) throws Exception;
	
	public int validaCRC(HashMap<String, String> params) throws Exception;
	
	public boolean updateTotalesNomina(long idCabecera, long codigoNomina) throws Exception;
	
	public int rollbackNominaTEF(long condigoNomina) throws Exception;
	
}
