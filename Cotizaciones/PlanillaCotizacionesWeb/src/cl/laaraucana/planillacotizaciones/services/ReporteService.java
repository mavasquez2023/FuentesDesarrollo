package cl.laaraucana.planillacotizaciones.services;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.laaraucana.planillacotizaciones.dto.NormalDto;

public interface ReporteService {

	public void generarReport(HttpServletRequest request, HttpServletResponse response, List<NormalDto> datos) throws Exception;

}
