package cl.laaraucana.EnvioASFAMEmpresa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.EnvioASFAMEmpresa.dao.GenericDao;
import cl.laaraucana.EnvioASFAMEmpresa.entities.RegistroEntiti;


@Service
public class RegistroServiceImpl implements RegistroService {

	@Autowired
	private GenericDao<RegistroEntiti> dao;

	@Override
	public void save(RegistroEntiti entity) throws Exception {

		dao.save(entity);
	}

	@Override
	public List<RegistroEntiti> findByRut(long rut) throws Exception {
		// TODO Auto-generated method stub
		return dao.findByRut(RegistroEntiti.class, rut);
	}

}
