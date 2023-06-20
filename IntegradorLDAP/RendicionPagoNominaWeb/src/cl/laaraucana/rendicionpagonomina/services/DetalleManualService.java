package cl.laaraucana.rendicionpagonomina.services;

import java.util.HashMap;
import java.util.List;

import cl.laaraucana.rendicionpagonomina.entities.DetalleManualEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ArchivoManualVO;

public interface DetalleManualService {

	public void save(DetalleManualEntity entity) throws Exception;

	public void saveOrupdate(DetalleManualEntity entity) throws Exception;
	
	public List<DetalleManualEntity> findByIdCabecera(long id) throws Exception;
	
	public DetalleManualEntity findByIdDetalle(long id) throws Exception;
	
	public DetalleManualEntity findByRut(HashMap<String, Long> params) throws Exception;
	
	public void updatePendientes(ArchivoManualVO data) throws Exception;
	
	public void rollbackTransferencia(ArchivoManualVO data) throws Exception;
	
	public void updateMandatoDetalle(DetalleManualEntity detalle) throws Exception;
	
	public List<DetalleManualEntity> findByConvenio_Producto(String convenio, String producto) throws Exception;
	
	public int updateEstadoDetalleManual(HashMap<String, String> params) throws Exception;
	
	public Long getIdCabeceraByIdDetalle(long idDetalle) throws Exception;

}
