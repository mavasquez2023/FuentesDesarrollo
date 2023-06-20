package cl.laaraucana.reportesil.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cl.laaraucana.reportesil.dao.vo.FormularioCalculoSILVO;
import cl.laaraucana.reportesil.dao.vo.ResumenLicenciaVO;




public interface CreaReporteService {

	public String generarReport(HttpServletRequest request,
			HttpServletResponse response, List<FormularioCalculoSILVO> formularios) throws Exception;
	public List<FormularioCalculoSILVO> completarFormulario(ResumenLicenciaVO resumen) throws Exception;
}
