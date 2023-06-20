package cl.laaraucana.copagocredito.services;

import java.util.List;

import cl.laaraucana.copagocredito.entities.CreditoEntiti;

public interface CreditoService {

	public void save(CreditoEntiti Entity) throws Exception;

	public List<CreditoEntiti> findAllByRut(long rut) throws Exception;
	
	public List<CreditoEntiti> findAllByAutorizedCredito(long fol, long cuota) throws Exception;

	 
	
	

}
