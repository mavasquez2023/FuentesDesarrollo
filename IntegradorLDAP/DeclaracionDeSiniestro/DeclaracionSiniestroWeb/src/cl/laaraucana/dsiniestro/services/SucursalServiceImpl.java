package cl.laaraucana.dsiniestro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.dsiniestro.dao.GenericDao;
import cl.laaraucana.dsiniestro.entities.Sucursal;

@Service
public class SucursalServiceImpl implements SucursalService{
	
	@Autowired
	private GenericDao<Sucursal> dao;

	@Override
	public List<Sucursal> findAll() throws Exception {
		// TODO Auto-generated method stub
		return dao.findAll(Sucursal.class);
	}

	@Override
	public Sucursal findById(long id) throws Exception {
		// TODO Auto-generated method stub
		return dao.findById(Sucursal.class, id);
	}

	@Override
	public Sucursal findBySucursal(String idSucursal) throws Exception {
		// TODO Auto-generated method stub
		return dao.findBySucursal(Sucursal.class, idSucursal);
	}

}
