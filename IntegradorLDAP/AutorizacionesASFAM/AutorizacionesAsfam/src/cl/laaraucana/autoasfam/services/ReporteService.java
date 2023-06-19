package cl.laaraucana.autoasfam.services;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.laaraucana.autoasfam.dto.CargasAUTDto;
import cl.laaraucana.autoasfam.dto.TotalesAUTDto;
import cl.laaraucana.autoasfam.vo.ModificacionesVO;

public interface ReporteService {

	public void generarReportAUT(HttpServletRequest request, HttpServletResponse response, List<CargasAUTDto> cargas, TotalesAUTDto totales) throws Exception;
	
	public void generarReportMOD(HttpServletRequest request, HttpServletResponse response, ModificacionesVO modificacionesVO)throws Exception;

}
