package cl.laaraucana.tarjetatam.services;

import java.util.List;

import cl.laaraucana.tarjetatam.entities.Region;

public interface RegionService {
	
	public List<Region> findAll() throws Exception;
	
	public Region findById(long id) throws Exception;

}
