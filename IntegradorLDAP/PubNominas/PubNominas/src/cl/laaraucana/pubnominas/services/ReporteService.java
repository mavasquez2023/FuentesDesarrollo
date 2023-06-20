package cl.laaraucana.pubnominas.services;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.laaraucana.pubnominas.dto.asfam.CargasAUTDto;
import cl.laaraucana.pubnominas.dto.asfam.TotalesAUTDto;
import cl.laaraucana.pubnominas.dto.cotizacion.NormalDto;
import cl.laaraucana.pubnominas.dto.pex.CuotaPEXDto;
import cl.laaraucana.pubnominas.vo.ModificacionesVO;


public interface ReporteService {

	public void generarReportAUT(HttpServletRequest request, HttpServletResponse response, List<CargasAUTDto> cargas, TotalesAUTDto totales) throws Exception;
	
	public void generarReportMOD(HttpServletRequest request, HttpServletResponse response, ModificacionesVO modificacionesVO)throws Exception;
	
	public void generarReportCOT(HttpServletRequest request, HttpServletResponse response, List<NormalDto> datos) throws Exception;
	
	public void generarReportPEX(HttpServletRequest request, HttpServletResponse response, List<CuotaPEXDto> datos) throws Exception;

}
