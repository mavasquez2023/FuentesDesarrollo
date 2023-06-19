package cl.laaraucana.contratocr.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ReporteService {
	
	public String generarReport(HttpServletRequest request, HttpServletResponse response, String rut, String nombre) throws Exception;
	
}
