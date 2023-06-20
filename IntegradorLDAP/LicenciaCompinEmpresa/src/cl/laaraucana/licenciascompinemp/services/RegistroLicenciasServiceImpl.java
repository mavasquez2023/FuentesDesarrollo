package cl.laaraucana.licenciascompinemp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.licenciascompinemp.dao.GenericDao;
import cl.laaraucana.licenciascompinemp.entities.RegistroLicencias;

@Service
public class RegistroLicenciasServiceImpl implements RegistroLicenciasService{

	@Autowired
	private GenericDao<RegistroLicencias> dao;
	
	@Override
	public void save(RegistroLicencias Entity) throws Exception{
		// TODO Auto-generated method stub
		dao.save(Entity);
		
	}

	@Override
	public boolean existfolio(String folio) throws Exception {
		// TODO Auto-generated method stub
		return dao.existfolio(RegistroLicencias.class, folio);
	}
	
	@Override
	public List<RegistroLicencias> findByFolio(String folio, String rut) throws Exception {
		// TODO Auto-generated method stub
		return dao.findByFolio(RegistroLicencias.class, folio, rut);
	}
	
	@Override
	public List<RegistroLicencias> findByRut(String rut) throws Exception {
		// TODO Auto-generated method stub
		return dao.findByRut(RegistroLicencias.class, rut);
	}
}
