package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.util.HashMap;
import java.util.List;

import cl.laaraucana.rendicionpagonomina.entities.CabeceraEntity;


public interface CabeceraTefDao {

	public List<CabeceraEntity> findNominasSeguimiento(HashMap<String, String> params) throws Exception;
	
	public CabeceraEntity findById(long idCabecera) throws Exception;
	
	public CabeceraEntity findByCodigoNomina(long codigoNomina) throws Exception;
	
	public List<CabeceraEntity> findNominasRendicion(HashMap<String, String> params) throws Exception;
	
	public void deleteByCodigoNomina(long codigoNomina) throws Exception;
	
	public int updateNominaTEF(CabeceraEntity cabeceraTEF) throws Exception;
	
	public int validaCRC(HashMap<String, String> params)throws Exception;
	
	public CabeceraEntity insert(CabeceraEntity cabecera) throws Exception;
	
	public HashMap<String, Object> getIdCabeceraPorNombreArchivo(String nombreArchivo ) throws Exception;
	
	public HashMap<String, Object> getSumTotalesPorEstadoPago(Long idCabecera, Integer estadoPago  ) throws Exception;

	public boolean existenRegistrosEnEstado3(Long idCabecera) throws Exception;

	public boolean actualizarTotalesRendicion(CabeceraEntity cabecera) throws Exception;
	
	public int rollbackNominaTEF(long condigoNomina) throws Exception;
}
