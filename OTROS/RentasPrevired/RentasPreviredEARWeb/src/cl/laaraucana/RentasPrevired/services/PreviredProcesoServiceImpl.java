package cl.laaraucana.RentasPrevired.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.RentasPrevired.dao.GenericDao;
import cl.laaraucana.RentasPrevired.dao.GenericDaoImpl;
import cl.laaraucana.RentasPrevired.entities.PreviredArchivosEntity;

@Service
public class PreviredProcesoServiceImpl implements PreviredProcesoService {

	@Autowired
	private GenericDao<PreviredArchivosEntity> dao;

	@Override
	public void saveProcess(PreviredArchivosEntity entity) {

		dao.save(entity);
	}

	@Override
	public List<PreviredArchivosEntity> findAll() {

		return dao.findAll(PreviredArchivosEntity.class);
	}

	@Override
	public List<PreviredArchivosEntity> findByError() {

		return dao.findByError(PreviredArchivosEntity.class);
	}

	@Override
	public boolean existName(String name) {
		  
		List<PreviredArchivosEntity> arch = dao.findByName(PreviredArchivosEntity.class, name, "NOMBRE");
		if (arch.size() == 0) {
			return false;
		} else {
			return true;
		}

	}

}
