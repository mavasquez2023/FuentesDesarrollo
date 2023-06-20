package cl.laaraucana.copagocredito.services;

import java.util.List;

import cl.laaraucana.copagocredito.entities.BitacoraEntiti;

public interface BitacoraService {
	
	public void save(BitacoraEntiti Entity) throws Exception;
	
	public List<BitacoraEntiti> findAllByNotAutorized(long fol, long cuota) throws Exception;
	
	public void update(BitacoraEntiti Entity) throws Exception;
		
	public List<BitacoraEntiti> findAllByAutorized(long fol, long cuota) throws Exception;
	
	public List<BitacoraEntiti> findAllByRutBita(long rut) throws Exception;

}
