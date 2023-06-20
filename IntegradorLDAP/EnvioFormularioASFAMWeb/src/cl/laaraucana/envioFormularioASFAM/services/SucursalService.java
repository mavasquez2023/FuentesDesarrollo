package cl.laaraucana.envioFormularioASFAM.services;

import java.util.List;

import cl.laaraucana.envioFormularioASFAM.entities.SucAsfam;
import cl.laaraucana.envioFormularioASFAM.entities.Sucursales;

public interface SucursalService {
	
	public List<Sucursales> findAllSucursal() throws Exception;
	
	public List<SucAsfam> findAnalistaByIdSucursal(String id) throws Exception;
	
	public Sucursales findByIdSucursal(String id) throws Exception;

}
