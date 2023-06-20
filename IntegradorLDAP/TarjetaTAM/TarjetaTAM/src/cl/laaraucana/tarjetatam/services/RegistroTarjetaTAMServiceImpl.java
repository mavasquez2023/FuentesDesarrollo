package cl.laaraucana.tarjetatam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.tarjetatam.dao.GenericDao;
import cl.laaraucana.tarjetatam.entities.RegistroTarjetaTAM;

@Service
public class RegistroTarjetaTAMServiceImpl implements RegistroTarjetaTAMService{

	@Autowired
	private GenericDao<RegistroTarjetaTAM> dao;
	
	public void save(RegistroTarjetaTAM Entity) throws Exception{
		// TODO Auto-generated method stub
		dao.save(Entity);
		
	}

	@Override
	public boolean existRut(String rut) throws Exception {
		// TODO Auto-generated method stub
		return dao.existRut(RegistroTarjetaTAM.class, rut);
	}
	
	@Override
	public List<RegistroTarjetaTAM> findByRut(String rut) throws Exception {
		// TODO Auto-generated method stub
		return dao.findByRut(RegistroTarjetaTAM.class, rut);
	}

}
