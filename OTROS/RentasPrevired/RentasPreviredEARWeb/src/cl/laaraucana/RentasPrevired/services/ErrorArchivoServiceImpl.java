package cl.laaraucana.RentasPrevired.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.RentasPrevired.dao.GenericDao;
import cl.laaraucana.RentasPrevired.entities.ArchivoErrorEntity;

@Service
public class ErrorArchivoServiceImpl implements ErrorArchivoService {

	@Autowired
	private GenericDao<ArchivoErrorEntity> dao;

	@Override
	public void saveError(ArchivoErrorEntity entity) {

		dao.save(entity);
	}

	@Override
	public List<ArchivoErrorEntity> getAll() {

		return dao.findAll(ArchivoErrorEntity.class);
	}

	@Override
	public boolean existName(String nombre) {

		List<ArchivoErrorEntity> ret = dao.findByName(ArchivoErrorEntity.class, nombre, "NOMARCH");

		if (ret.size() == 0) {
			return false;

		} else {

			return true;
		}
	}

}
