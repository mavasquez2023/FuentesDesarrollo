package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.util.HashMap;
import java.util.List;

import cl.laaraucana.rendicionpagonomina.entities.DetalleManualEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ArchivoManualVO;

public interface DetalleManualDao {
	
	public int rollbackTransferencia(ArchivoManualVO data) throws Exception;
	
	public int updatePendientes(ArchivoManualVO data) throws Exception;
	
	public int updateMandatoDetalle(DetalleManualEntity detalle) throws Exception;
	
	public int updateEstadoDetalleManual(HashMap<String, String> params) throws Exception;
	
	public List<DetalleManualEntity> getDetallesxConvenioProducto(String convenio, String producto)throws Exception;
	
	public List<DetalleManualEntity> findByIdCabecera(long idCabecera) throws Exception;
	
	public DetalleManualEntity findByIdDetalle(long idDetalle) throws Exception;
	
	public Long getIdCabeceraByIdDetalle(long idDetalle) throws Exception;
	
	public DetalleManualEntity findByRut(HashMap<String, Long> params) throws Exception;
	
	public boolean existenRegistrosPendientes(long idCabecera) throws Exception;
}
