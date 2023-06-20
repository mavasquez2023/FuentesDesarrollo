package cl.laaraucana.transferencias.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.laaraucana.transferencias.vo.DatosMandatoVo;

public interface ReporteService {
	
	public String generarReport(HttpServletRequest request, HttpServletResponse response, DatosMandatoVo vo) throws Exception;
	
	public void generarReport(HttpServletRequest request, HttpServletResponse response, long id) throws Exception;
	
	public void generarReportRevocado(HttpServletRequest request, HttpServletResponse response, long id) throws Exception;
	
	public String generarReportejecutivo(DatosMandatoVo vo) throws Exception;

}
