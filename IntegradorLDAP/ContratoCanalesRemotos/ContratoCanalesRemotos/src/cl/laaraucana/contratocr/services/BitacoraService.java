package cl.laaraucana.contratocr.services;

import java.util.List;

import cl.laaraucana.contratocr.entities.BitacoraEntiti;

public interface BitacoraService {
	
	public List<BitacoraEntiti> getCotratoByRut(long rutCliente) throws Exception;
	
	public void save(BitacoraEntiti entity) throws Exception;
	
	public void insertBitacora(long rut, String dv, String idChallenge, String codigoRetorno, String resultadoValidacion, String ipRemota) throws Exception;

}
