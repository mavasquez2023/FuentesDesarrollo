package cl.laaraucana.tarjetatam.services;

import java.util.List;

import cl.laaraucana.tarjetatam.entities.Oficina;

public interface OficinaService {
	
	public List<Oficina> findAll() throws Exception;
	
	public Oficina findById(long id) throws Exception;
	
	public Oficina findByOficina(String idsucursal) throws Exception;

}
