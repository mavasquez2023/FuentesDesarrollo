package cl.laaraucana.licenciascompin.services;

import java.util.List;

import cl.laaraucana.licenciascompin.entities.Region;

public interface RegionService {
	
	public List<Region> findAll() throws Exception;
	
	public Region findById(long id) throws Exception;

}
