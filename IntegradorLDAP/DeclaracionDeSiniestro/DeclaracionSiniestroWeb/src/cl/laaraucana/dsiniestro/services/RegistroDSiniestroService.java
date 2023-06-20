package cl.laaraucana.dsiniestro.services;

import java.util.List;

import cl.laaraucana.dsiniestro.entities.RegistroDSiniestro;

public interface RegistroDSiniestroService {
	
	public void save(RegistroDSiniestro Entity) throws Exception;
	
	public boolean existRut(String rut) throws Exception;
	
	public List<RegistroDSiniestro> findByRut(String rut) throws Exception;

}
