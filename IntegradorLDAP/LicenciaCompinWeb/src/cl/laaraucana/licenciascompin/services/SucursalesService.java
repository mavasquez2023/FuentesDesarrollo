package cl.laaraucana.licenciascompin.services;

import java.util.List;

import cl.laaraucana.licenciascompin.entities.SucLicencias;
import cl.laaraucana.licenciascompin.entities.SucLicenciasDP;

public interface SucursalesService {
	
	public List<SucLicencias> findByComunaSuc(int idcomuna) throws Exception;
	
	public List<SucLicenciasDP> getAllSucursales() throws Exception;
	
	public SucLicenciasDP getSucursalByCodigo(String codigo) throws Exception;

}
