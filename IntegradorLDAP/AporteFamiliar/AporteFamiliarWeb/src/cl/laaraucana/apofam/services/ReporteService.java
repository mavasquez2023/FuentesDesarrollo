package cl.laaraucana.apofam.services;

import javax.servlet.http.HttpServletResponse;

import cl.laaraucana.apofam.entities.Cargas;


public interface ReporteService {

	public String generarReport(Cargas cargas, String path, HttpServletResponse response) throws Exception;

	 

}
