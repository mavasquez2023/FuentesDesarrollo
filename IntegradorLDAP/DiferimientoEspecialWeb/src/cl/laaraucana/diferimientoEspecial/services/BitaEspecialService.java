package cl.laaraucana.diferimientoEspecial.services;

import java.util.List;

import cl.laaraucana.diferimientoEspecial.entities.BitaEspecialEntiti;

public interface BitaEspecialService {

	public void save(BitaEspecialEntiti Entity) throws Exception;
	
	public List<BitaEspecialEntiti> findAllByNotAutorized(String fol, long cuota) throws Exception;
	
	public void update(BitaEspecialEntiti Entity) throws Exception;
		
	public List<BitaEspecialEntiti> findAllByAutorized(String fol, long cuota) throws Exception;
	
	public List<BitaEspecialEntiti> findAllByRutBita(long rut) throws Exception;

}
