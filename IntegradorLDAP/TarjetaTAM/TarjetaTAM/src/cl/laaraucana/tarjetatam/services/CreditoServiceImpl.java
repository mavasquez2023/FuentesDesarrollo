package cl.laaraucana.tarjetatam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.tarjetatam.dao.GenericDao;
import cl.laaraucana.tarjetatam.entities.TarjetaTAM;

@Service
public class CreditoServiceImpl implements CreditoService {

	@Autowired
	private GenericDao<TarjetaTAM> dao;

	@Override
	public List<TarjetaTAM> findAllByRut(long rut) throws Exception {
		// TODO Auto-generated method stub
		return dao.findAllByRut(TarjetaTAM.class, rut);
	}

	@Override
	public void save(TarjetaTAM Entity) throws Exception {
		// TODO Auto-generated method stub
		dao.save(Entity);
	}

	@Override
	public List<TarjetaTAM> findAllByAutorizedCredito(long fol, long cuota) throws Exception {
		// TODO Auto-generated method stub
		return dao.findAllByAutorizedCredito(TarjetaTAM.class, fol, cuota);
	}


}
