package cl.laaraucana.boletaelectronica.services;

import java.util.List;
import cl.laaraucana.boletaelectronica.entities.OrigenBoleta;

public interface OrigenBoletaService {

	public List<OrigenBoleta> getOrigenByNumber(int folio) throws Exception;

	public List<OrigenBoleta> findAllNoEmitidas() throws Exception;
	
	public List<OrigenBoleta> findByNumBolAndTipoDocOrigen(int folio, int tipodoc) throws Exception;
	
	public void saveOrUpdate(OrigenBoleta entity) throws Exception;
	
	public void updateOrigen(long numero, int folio) throws Exception;
	
 


}
