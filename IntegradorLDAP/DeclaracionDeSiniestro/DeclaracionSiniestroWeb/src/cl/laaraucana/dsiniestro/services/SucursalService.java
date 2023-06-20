package cl.laaraucana.dsiniestro.services;

import java.util.List;

import cl.laaraucana.dsiniestro.entities.Sucursal;

public interface SucursalService {
	
	public List<Sucursal> findAll() throws Exception;
	
	public Sucursal findById(long id) throws Exception;
	
	public Sucursal findBySucursal(String idsucursal) throws Exception;

}
