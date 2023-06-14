package cl.laaraucana.boletaelectronica.services;

import java.util.List;

import cl.laaraucana.boletaelectronica.entities.BoletaDetalle;

public interface DetalleService {

	public List<BoletaDetalle> getDetalle() throws Exception;
	
	public void save(BoletaDetalle detalle) throws Exception;
	
	
	 

}
