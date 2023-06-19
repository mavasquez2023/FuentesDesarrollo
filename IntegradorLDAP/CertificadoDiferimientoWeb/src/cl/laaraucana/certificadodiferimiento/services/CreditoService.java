package cl.laaraucana.certificadodiferimiento.services;

import java.util.List;

import cl.laaraucana.certificadodiferimiento.entities.CreditoEntiti;

public interface CreditoService {

	public void save(CreditoEntiti Entity) throws Exception;

	public List<CreditoEntiti> findAllByRut(long rut) throws Exception;
	
	public List<CreditoEntiti> findAllByAutorizedCredito(String fol, int cuota) throws Exception;

	 
	
	

}
