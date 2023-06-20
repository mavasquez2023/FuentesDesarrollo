package cl.laaraucana.diferimientoEspecial.services;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.laaraucana.diferimientoEspecial.model.CreditoVo;

public interface ReporteService {

	public String generarReport(HttpServletRequest request, HttpServletResponse response, CreditoVo vo, Connection con) throws Exception;

	 

}
