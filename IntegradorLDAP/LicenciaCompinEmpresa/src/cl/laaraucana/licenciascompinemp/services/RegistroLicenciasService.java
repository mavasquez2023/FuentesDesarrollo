package cl.laaraucana.licenciascompinemp.services;

import java.util.List;

import cl.laaraucana.licenciascompinemp.entities.RegistroLicencias;

public interface RegistroLicenciasService {
	
	public void save(RegistroLicencias Entity) throws Exception;
	
	public boolean existfolio(String folio) throws Exception;
	
	public List<RegistroLicencias> findByFolio(String folio, String rut) throws Exception;
	
	public List<RegistroLicencias> findByRut(String rut) throws Exception;

}
