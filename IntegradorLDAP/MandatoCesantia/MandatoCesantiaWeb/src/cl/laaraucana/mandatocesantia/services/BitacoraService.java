package cl.laaraucana.mandatocesantia.services;

import java.util.List;

import cl.laaraucana.mandatocesantia.entities.BitacoraEntiti;

public interface BitacoraService {
	
	public void save(BitacoraEntiti Entity) throws Exception;
	
	public void update(BitacoraEntiti Entity) throws Exception;
		
	public List<BitacoraEntiti> findAllByRutBita(long rut) throws Exception;

}
