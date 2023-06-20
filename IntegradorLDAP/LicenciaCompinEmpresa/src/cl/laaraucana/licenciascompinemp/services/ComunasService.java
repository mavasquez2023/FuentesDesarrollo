package cl.laaraucana.licenciascompinemp.services;

import java.util.List;

import cl.laaraucana.licenciascompinemp.entities.Comuna;

public interface ComunasService {
	
	public List<Comuna> findAll() throws Exception;
	
	public Comuna findById(long id) throws Exception;
	
	public List<Comuna> findByComunaReg(int idRegion) throws Exception;

}
