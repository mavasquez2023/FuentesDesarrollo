package cl.laaraucana.tarjetatam.services;

import java.util.List;

import cl.laaraucana.tarjetatam.entities.Comuna;
import cl.laaraucana.tarjetatam.entities.ViewComuna;

public interface ComunasService {
	
	public List<Comuna> findAll() throws Exception;
	
	public Comuna findById(long id) throws Exception;
	
	public ViewComuna findByComunaSuc(String idcomuna) throws Exception;
	
	public List<Comuna> findByComunaReg(int idRegion) throws Exception;

}
