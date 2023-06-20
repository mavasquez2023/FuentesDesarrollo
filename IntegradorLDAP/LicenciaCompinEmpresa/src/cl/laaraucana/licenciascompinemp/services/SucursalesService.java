package cl.laaraucana.licenciascompinemp.services;

import java.util.List;

import cl.laaraucana.licenciascompinemp.entities.SucLicencias;
import cl.laaraucana.licenciascompinemp.entities.SucLicenciasDP;

public interface SucursalesService {
	
	public List<SucLicencias> findByComunaSuc(int idcomuna) throws Exception;
	
	public List<SucLicenciasDP> getAllSucursales() throws Exception;
	
	public SucLicenciasDP getSucursalByCodigo(String codigo) throws Exception;

}
