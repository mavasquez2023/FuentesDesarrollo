package cl.laaraucana.muvu.services;

import cl.laaraucana.muvu.entities.Resumen;


public interface ReporteService {

	public String generarReport(Resumen resumen, String path) throws Exception;

	 

}
