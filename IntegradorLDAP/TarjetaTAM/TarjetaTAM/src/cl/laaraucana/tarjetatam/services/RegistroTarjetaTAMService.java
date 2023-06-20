package cl.laaraucana.tarjetatam.services;

import java.util.List;

import cl.laaraucana.tarjetatam.entities.RegistroTarjetaTAM;

public interface RegistroTarjetaTAMService {
	
	public void save(RegistroTarjetaTAM Entity) throws Exception;
	
	public boolean existRut(String rut) throws Exception;
	
	public List<RegistroTarjetaTAM> findByRut(String rut) throws Exception;

}
