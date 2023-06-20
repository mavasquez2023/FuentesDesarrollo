package cl.laaraucana.diferimientoEspecial.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.laaraucana.diferimientoEspecial.dao.GenericDao;
import cl.laaraucana.diferimientoEspecial.entities.BitaEspecialEntiti;


@Service
public class BitaEspecialServiceImpl implements BitaEspecialService {

	@Autowired
	private GenericDao<BitaEspecialEntiti> dao;

	@Override
	public void save(BitaEspecialEntiti Entity) throws Exception {
		// TODO Auto-generated method stub
		dao.save(Entity);
	}

	@Override
	public List<BitaEspecialEntiti> findAllByNotAutorized(String fol, long cuota) throws Exception {
		// TODO Auto-generated method stub
		return dao.findAllByNotAutorized(BitaEspecialEntiti.class, fol, cuota);
	}

	@Override
	public void update(BitaEspecialEntiti Entity) throws Exception {
		// TODO Auto-generated method stub
		dao.update(Entity);
	}
	
	@Override
	public List<BitaEspecialEntiti> findAllByAutorized(String fol, long cuota) throws Exception {
		// TODO Auto-generated method stub
		return dao.findAllByAutorized(BitaEspecialEntiti.class, fol, cuota);
	}

	@Override
	public List<BitaEspecialEntiti> findAllByRutBita(long rut) throws Exception {
		// TODO Auto-generated method stub
		return dao.findAllByRutBita(BitaEspecialEntiti.class, rut);
	}
}
