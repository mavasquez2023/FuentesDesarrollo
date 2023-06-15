package cl.laaraucana.ventafullweb.services;

import javax.servlet.http.HttpServletResponse;

import cl.laaraucana.ventafullweb.vo.AfiliadoVo;

public interface ReporteService {

	public String generarReport(HttpServletResponse response, AfiliadoVo afiliado) throws Exception;
}
