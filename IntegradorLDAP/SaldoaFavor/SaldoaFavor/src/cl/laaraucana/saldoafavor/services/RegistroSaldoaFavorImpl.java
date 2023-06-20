package cl.laaraucana.saldoafavor.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.saldoafavor.dao.GenericDao;
import cl.laaraucana.saldoafavor.entities.RegistroSaldoaFavor;


@Service
public class RegistroSaldoaFavorImpl implements RegistroSaldoaFavorService{

	@Autowired
	private GenericDao<RegistroSaldoaFavor> dao;
	
	@Override
	public void save(RegistroSaldoaFavor Entity) throws Exception{
		// TODO Auto-generated method stub
		dao.save(Entity);
		
	}

}
