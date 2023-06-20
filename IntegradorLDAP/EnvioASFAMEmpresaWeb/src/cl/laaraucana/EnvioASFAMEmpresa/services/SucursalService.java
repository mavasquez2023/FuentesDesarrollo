package cl.laaraucana.EnvioASFAMEmpresa.services;

import java.util.List;

import cl.laaraucana.EnvioASFAMEmpresa.entities.SucAsfam;
import cl.laaraucana.EnvioASFAMEmpresa.entities.Sucursales;


public interface SucursalService {
	
	public List<Sucursales> findAllSucursal() throws Exception;
	
	public List<SucAsfam> findAnalistaByIdSucursal(String id) throws Exception;
	
	public Sucursales findByIdSucursal(String id) throws Exception;

}
