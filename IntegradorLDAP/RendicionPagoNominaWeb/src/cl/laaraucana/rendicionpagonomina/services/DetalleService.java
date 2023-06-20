package cl.laaraucana.rendicionpagonomina.services;

import java.util.HashMap;
import java.util.List;

import cl.laaraucana.rendicionpagonomina.entities.DetalleEntity;
import cl.laaraucana.rendicionpagonomina.vo.NominaVo;

public interface DetalleService {

	public void save(DetalleEntity entity) throws Exception;

	public void saveOrupdate(DetalleEntity entity) throws Exception;

	public void delete(DetalleEntity entity) throws Exception;
	
	public void deleteByRut(long rut) throws Exception;
	
	public boolean existRut(long rut) throws Exception;
	
	public List<DetalleEntity> findAll() throws Exception;
	
	public List<DetalleEntity> getNominasDetalle(NominaVo nomina) throws Exception;
	
	public List<DetalleEntity> findPendientesByIdCabecera(long id) throws Exception;

	//ibatis
	public List<DetalleEntity> findByIdCabecera(long id) throws Exception;
	
	public DetalleEntity findByIdDetalle(long id) throws Exception;
	
	public DetalleEntity findByRut(HashMap<String, Long> params) throws Exception;
	
	public List<DetalleEntity> findByEstado(HashMap<String, Long> params) throws Exception;
	
	public void deleteByCodigoNomina(long codigoNomina) throws Exception;
	
	public List<DetalleEntity> seguimientoAfiliado(HashMap<String, String> params) throws Exception;
	
	public void updateDetallePendientedePago(long id, long codigoNomina) throws Exception;
	
}
