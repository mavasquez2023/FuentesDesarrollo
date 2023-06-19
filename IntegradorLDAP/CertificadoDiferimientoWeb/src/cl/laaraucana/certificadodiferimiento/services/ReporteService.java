package cl.laaraucana.certificadodiferimiento.services;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.laaraucana.certificadodiferimiento.model.CreditoVo;
import cl.laaraucana.certificadodiferimiento.vo.AfiliadoVo;

public interface ReporteService {

	public String generarReport(HttpServletRequest request, HttpServletResponse response, AfiliadoVo vo, Connection con) throws Exception;

	 

}
