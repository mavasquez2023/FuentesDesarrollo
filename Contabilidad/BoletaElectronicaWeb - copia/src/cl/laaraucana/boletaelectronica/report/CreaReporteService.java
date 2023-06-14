package cl.laaraucana.boletaelectronica.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public interface CreaReporteService {

	public void generarReport(HttpServletRequest request,
			HttpServletResponse response, String id) throws Exception;

}
