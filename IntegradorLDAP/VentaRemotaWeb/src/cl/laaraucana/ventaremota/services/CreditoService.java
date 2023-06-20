package cl.laaraucana.ventaremota.services;

import java.util.HashMap;
import java.util.List;

import cl.laaraucana.ventaremota.entities.BitacoraEntiti;
import cl.laaraucana.ventaremota.entities.CreditoEntiti;

public interface CreditoService {
	
	public void save(CreditoEntiti Entity) throws Exception;
	
	public List<CreditoEntiti> getAllCreditByRut(long rut) throws Exception;
	
	public List<BitacoraEntiti> getAllRechazadoByRut(long rut, long numOferta) throws Exception;
	
	public CreditoEntiti getCreditById(long id) throws Exception;
	
	public void caducar() throws Exception;
	
	public void updateCredit(long id, String estado);

}
