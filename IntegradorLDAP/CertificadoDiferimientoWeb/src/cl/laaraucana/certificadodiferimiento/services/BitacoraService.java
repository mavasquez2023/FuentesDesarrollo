package cl.laaraucana.certificadodiferimiento.services;

import java.util.List;

import cl.laaraucana.certificadodiferimiento.entities.BitacoraEntiti;

public interface BitacoraService {
	
	public void save(BitacoraEntiti Entity) throws Exception;
	
	public List<BitacoraEntiti> findAllByNotAutorized(String fol, int cuota) throws Exception;
	
	public void update(BitacoraEntiti Entity) throws Exception;
		
	public List<BitacoraEntiti> findAllByAutorized(String fol, int cuota) throws Exception;
	
	public List<BitacoraEntiti> findAllByRutBita(long rut) throws Exception;

}
