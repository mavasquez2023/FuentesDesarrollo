package cl.laaraucana.mandatocesantia.services;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.laaraucana.mandatocesantia.model.CesantiaVo;

public interface ReporteService {

	public String generarReport(HttpServletRequest request, HttpServletResponse response, CesantiaVo vo, Connection con, boolean salida) throws Exception;

	 

}
