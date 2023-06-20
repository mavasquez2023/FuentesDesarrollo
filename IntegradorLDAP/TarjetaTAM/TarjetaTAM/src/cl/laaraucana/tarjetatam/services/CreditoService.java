package cl.laaraucana.tarjetatam.services;

import java.util.List;

import cl.laaraucana.tarjetatam.entities.TarjetaTAM;

public interface CreditoService {

	public void save(TarjetaTAM Entity) throws Exception;

	public List<TarjetaTAM> findAllByRut(long rut) throws Exception;
	
	public List<TarjetaTAM> findAllByAutorizedCredito(long fol, long cuota) throws Exception;

	 
	
	

}
