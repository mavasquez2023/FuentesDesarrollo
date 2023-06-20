package cl.laaraucana.licenciascompin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.licenciascompin.dao.GenericDao;
import cl.laaraucana.licenciascompin.entities.Comuna;

@Service
public class ComunasServiceImpl implements ComunasService{
	
	@Autowired
	private GenericDao<Comuna> dao;

	@Override
	public List<Comuna> findAll() throws Exception {
		// TODO Auto-generated method stub
		return dao.findAll(Comuna.class);
	}

	@Override
	public Comuna findById(long id) throws Exception {
		// TODO Auto-generated method stub
		return dao.findById(Comuna.class, id);
	}

	@Override
	public List<Comuna> findByComunaReg(int idRegion) throws Exception {
		// TODO Auto-generated method stub
		return dao.findByComunaReg(Comuna.class, idRegion);
	}

}
