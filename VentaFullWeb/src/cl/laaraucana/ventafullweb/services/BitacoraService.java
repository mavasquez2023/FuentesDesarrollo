package cl.laaraucana.ventafullweb.services;

import cl.laaraucana.ventafullweb.dto.BitacoraEvaluadorAISDto;
import cl.laaraucana.ventafullweb.dto.BitacoraGenesysDto;
import cl.laaraucana.ventafullweb.vo.AfiliadoVo;
import cl.laaraucana.ventafullweb.vo.SalidaEvaluadorModeloAISVo;

public interface BitacoraService {
	
	public void saveBitacoraSimulacion(AfiliadoVo entity) throws Exception;
	
	public BitacoraGenesysDto saveBitacoraGenesys(AfiliadoVo entity, String fechaSeleccionada) throws Exception;
	
	public void updateBitacoraGenesys(BitacoraGenesysDto entity) throws Exception;
	
	public void saveBitacoraEvaluadorAIS(SalidaEvaluadorModeloAISVo entity) throws Exception;
	
	public void saveBitacoraSeguimiento(AfiliadoVo afiliado, String Accion, String Servicio, String Resultado) throws Exception;

}
