package cl.laaraucana.RentasPrevired.services;

import java.util.List;

import cl.laaraucana.RentasPrevired.entities.RespuestaAfiliacionEntity;

public interface RepuestaAfiliadoService {

	public void saveRespAfi(RespuestaAfiliacionEntity entity);
	
	public List<RespuestaAfiliacionEntity> getAllAfi();
	
}
