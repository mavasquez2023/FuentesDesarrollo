package cl.laaraucana.licenciascompin.services;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.laaraucana.licenciascompin.entities.RegistroLicencias;


public interface ReporteService {

	public String generarReport(HttpServletRequest request, HttpServletResponse response, RegistroLicencias vo, String rut, String path) throws Exception;

	 

}
