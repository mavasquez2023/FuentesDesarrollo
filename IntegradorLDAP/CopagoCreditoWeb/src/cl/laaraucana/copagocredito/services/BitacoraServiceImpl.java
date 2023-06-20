package cl.laaraucana.copagocredito.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.copagocredito.dao.GenericDao;
import cl.laaraucana.copagocredito.entities.BitacoraEntiti;
import cl.laaraucana.copagocredito.entities.CreditoEntiti;

@Service
public class BitacoraServiceImpl implements BitacoraService{

	@Autowired
	private GenericDao<BitacoraEntiti> dao;
	
	@Override
	public void save(BitacoraEntiti Entity) throws Exception {
		// TODO Auto-generated method stub
		dao.save(Entity);
	}

	@Override
	public List<BitacoraEntiti> findAllByNotAutorized(long fol, long cuota) throws Exception {
		// TODO Auto-generated method stub
		return dao.findAllByNotAutorized(BitacoraEntiti.class, fol, cuota);
	}

	@Override
	public void update(BitacoraEntiti Entity) throws Exception {
		// TODO Auto-generated method stub
		dao.update(Entity);
	}
	
	@Override
	public List<BitacoraEntiti> findAllByAutorized(long fol, long cuota) throws Exception {
		// TODO Auto-generated method stub
		return dao.findAllByAutorized(BitacoraEntiti.class, fol, cuota);
	}

	@Override
	public List<BitacoraEntiti> findAllByRutBita(long rut) throws Exception {
		// TODO Auto-generated method stub
		return dao.findAllByRutBita(BitacoraEntiti.class, rut);
	}


}
