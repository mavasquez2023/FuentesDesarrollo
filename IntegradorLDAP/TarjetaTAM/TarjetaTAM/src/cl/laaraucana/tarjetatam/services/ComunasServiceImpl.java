package cl.laaraucana.tarjetatam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.tarjetatam.dao.GenericDao;
import cl.laaraucana.tarjetatam.entities.Comuna;
import cl.laaraucana.tarjetatam.entities.ViewComuna;

@Service
public class ComunasServiceImpl implements ComunasService{
	
	@Autowired
	private GenericDao<Comuna> dao;
	
	@Autowired
	private GenericDao<ViewComuna> daoview;

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
	public ViewComuna findByComunaSuc(String idcomuna) throws Exception {
		// TODO Auto-generated method stub
		return daoview.findByComunaSuc(ViewComuna.class, idcomuna);
	}
	
	@Override
	public List<Comuna> findByComunaReg(int idRegion) throws Exception {
		// TODO Auto-generated method stub
		return dao.findByComunaReg(Comuna.class, idRegion);
	}

}
