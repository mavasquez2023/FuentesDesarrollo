package cl.laaraucana.ventaremota.services;

import cl.laaraucana.ventaremota.entities.BitacoraContratoCREntiti;
import cl.laaraucana.ventaremota.entities.BitacoraEntiti;
import cl.laaraucana.ventaremota.entities.CreditoEntiti;

public interface BitacoraService {
	
	public void save(BitacoraEntiti entity) throws Exception;
	
	public void save(BitacoraContratoCREntiti entity) throws Exception;
	
	public void insertBitacora(CreditoEntiti credito, String idChallenge, String codigoRetorno, String resultadoValidacion, String ipRemota) throws Exception;
	
	public void insertBitacoraContratoCR(long rut, String dv, String idChallenge, String codigoRetorno, String resultadoValidacion, String ipRemota) throws Exception;
}
