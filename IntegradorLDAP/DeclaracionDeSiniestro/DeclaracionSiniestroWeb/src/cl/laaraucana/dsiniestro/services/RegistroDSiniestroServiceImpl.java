package cl.laaraucana.dsiniestro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.dsiniestro.dao.GenericDao;
import cl.laaraucana.dsiniestro.entities.RegistroDSiniestro;

@Service
public class RegistroDSiniestroServiceImpl implements RegistroDSiniestroService{

	@Autowired
	private GenericDao<RegistroDSiniestro> dao;
	
	public void save(RegistroDSiniestro Entity) throws Exception{
		// TODO Auto-generated method stub
		dao.save(Entity);
		
	}

	@Override
	public boolean existRut(String rut) throws Exception {
		// TODO Auto-generated method stub
		return dao.existRut(RegistroDSiniestro.class, rut);
	}
	
	@Override
	public List<RegistroDSiniestro> findByRut(String rut) throws Exception {
		// TODO Auto-generated method stub
		return dao.findByRut(RegistroDSiniestro.class, rut);
	}

}
