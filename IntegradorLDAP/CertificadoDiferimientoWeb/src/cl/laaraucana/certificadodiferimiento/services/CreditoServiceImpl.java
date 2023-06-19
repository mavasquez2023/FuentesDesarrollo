package cl.laaraucana.certificadodiferimiento.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.certificadodiferimiento.dao.GenericDao;
import cl.laaraucana.certificadodiferimiento.entities.CreditoEntiti;

@Service
public class CreditoServiceImpl implements CreditoService {

	@Autowired
	private GenericDao<CreditoEntiti> dao;

	@Override
	public List<CreditoEntiti> findAllByRut(long rut) throws Exception {
		// TODO Auto-generated method stub
		return dao.findAllByRut(CreditoEntiti.class, rut);
	}

	@Override
	public void save(CreditoEntiti Entity) throws Exception {
		// TODO Auto-generated method stub
		dao.save(Entity);
	}

	@Override
	public List<CreditoEntiti> findAllByAutorizedCredito(String fol, int cuota) throws Exception {
		// TODO Auto-generated method stub
		return dao.findAllByAutorizedCredito(CreditoEntiti.class, fol, cuota);
	}


}
