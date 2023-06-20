package cl.laaraucana.licenciascompinemp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.licenciascompinemp.dao.GenericDao;
import cl.laaraucana.licenciascompinemp.entities.CorreoBalanceo;
import cl.laaraucana.licenciascompinemp.entities.RegistroDocPendientes;


@Service
public class RegistroDocPendientesServiceImpl implements RegistroDocPendientesService {

	@Autowired
	private GenericDao<RegistroDocPendientes> dao;
	
	@Autowired
	private GenericDao<CorreoBalanceo> daocorreo;
	
	public void save(RegistroDocPendientes Entity) throws Exception {
		// TODO Auto-generated method stub
		dao.save(Entity);
	}

	public CorreoBalanceo getCorreoEjecutivo() throws Exception {
		 CorreoBalanceo registro= daocorreo.getCorreoEjecutivo(CorreoBalanceo.class).get(0);
		 registro.setBalanceo(registro.getBalanceo()+1);
		 registro.setNumlicasigna(registro.getNumlicasigna()+1);
		 daocorreo.update(registro);
		 return registro;
	}
	
	@Override
	public List<RegistroDocPendientes> findByRut(String rut) throws Exception {
		// TODO Auto-generated method stub
		return dao.findByRut(RegistroDocPendientes.class, rut);
	}
}
