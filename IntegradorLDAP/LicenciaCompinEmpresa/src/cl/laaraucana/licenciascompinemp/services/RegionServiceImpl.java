package cl.laaraucana.licenciascompinemp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.licenciascompinemp.dao.GenericDao;
import cl.laaraucana.licenciascompinemp.entities.Region;

@Service
public class RegionServiceImpl implements RegionService{

	@Autowired
	private GenericDao<Region> dao;
	
	@Override
	public List<Region> findAll() throws Exception {
		// TODO Auto-generated method stub
		return dao.findAll(Region.class);
	}

	@Override
	public Region findById(long id) throws Exception {
		// TODO Auto-generated method stub
		return dao.findById(Region.class, id);
	}

}
