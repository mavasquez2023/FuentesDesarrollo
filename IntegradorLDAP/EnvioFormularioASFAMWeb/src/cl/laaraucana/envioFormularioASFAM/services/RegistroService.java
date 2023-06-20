package cl.laaraucana.envioFormularioASFAM.services;

import java.util.List;

import cl.laaraucana.envioFormularioASFAM.entities.RegistroEntiti;

public interface RegistroService {

	public void save(RegistroEntiti entity) throws Exception;
	
	public List<RegistroEntiti> findByRut(long rut) throws Exception;

}
