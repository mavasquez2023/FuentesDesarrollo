package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.util.HashMap;
import java.util.List;

import cl.laaraucana.rendicionpagonomina.entities.DetalleEntity;

public interface DetalleTefDao {

	
	public List<DetalleEntity> findByIdCabecera(long idCabecera) throws Exception;
	
	public DetalleEntity findByIdDetalle(long idDetalle) throws Exception;
	
	public List<DetalleEntity> findByEstadoPago(HashMap<String, Long> params) throws Exception;
	
	public DetalleEntity findByRut(HashMap<String, Long> params) throws Exception;
	
	public void deleteByCodigoNomina(long codigoNomina) throws Exception;
	
	public List<DetalleEntity> seguimientoAfiliado(HashMap<String, String> params) throws Exception;
	
	public Long getIdCabeceraPorDetalle(HashMap<String, Long> params) throws Exception;
	
	public void updateDetallePendientedePago(HashMap<String, Long> params) throws Exception;
	
}
