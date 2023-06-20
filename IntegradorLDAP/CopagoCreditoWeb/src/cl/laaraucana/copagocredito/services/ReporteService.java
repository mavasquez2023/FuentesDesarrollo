package cl.laaraucana.copagocredito.services;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.laaraucana.copagocredito.model.CreditoVo;

public interface ReporteService {

	public String generarReport(HttpServletRequest request, HttpServletResponse response, CreditoVo vo, Connection con, boolean salida) throws Exception;

	 

}
