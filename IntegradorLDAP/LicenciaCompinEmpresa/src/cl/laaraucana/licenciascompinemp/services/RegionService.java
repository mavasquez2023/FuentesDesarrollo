package cl.laaraucana.licenciascompinemp.services;

import java.util.List;

import cl.laaraucana.licenciascompinemp.entities.Region;

public interface RegionService {
	
	public List<Region> findAll() throws Exception;
	
	public Region findById(long id) throws Exception;

}
