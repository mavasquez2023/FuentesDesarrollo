package cl.laaraucana.tarjetatam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.tarjetatam.dao.GenericDao;
import cl.laaraucana.tarjetatam.entities.Oficina;

@Service
public class OficinaServiceImpl implements OficinaService{
	
	@Autowired
	private GenericDao<Oficina> dao;

	@Override
	public List<Oficina> findAll() throws Exception {
		// TODO Auto-generated method stub
		return dao.findAll(Oficina.class);
	}

	@Override
	public Oficina findById(long id) throws Exception {
		// TODO Auto-generated method stub
		return dao.findById(Oficina.class, id);
	}

	@Override
	public Oficina findByOficina(String idOficina) throws Exception {
		// TODO Auto-generated method stub
		return dao.findByOficina(Oficina.class, idOficina);
	}

}
