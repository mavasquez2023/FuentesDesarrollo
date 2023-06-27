package cl.laaraucana.RentasPrevired.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.laaraucana.RentasPrevired.dao.GenericDao;
import cl.laaraucana.RentasPrevired.entities.RespuestaAfiliacionEntity;

@Service
public class RepuestaAfiliadoServiceImpl implements RepuestaAfiliadoService {

	@Autowired
	private GenericDao<RespuestaAfiliacionEntity> dao;

	@Override
	public void saveRespAfi(RespuestaAfiliacionEntity entity) {

		dao.save(entity);

	}

	@Override
	public List<RespuestaAfiliacionEntity> getAllAfi() {

		return dao.findAll(RespuestaAfiliacionEntity.class);
	}

}
