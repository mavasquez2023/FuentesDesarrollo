package cl.laaraucana.ventafullweb.services;

import com.lautaro.xi.CRM.Legacy.OfertasVigentes_DT;

import cl.laaraucana.ventafullweb.vo.AfiliadoVo;
import cl.laaraucana.ventafullweb.vo.SalidaEvaluadorModeloAISVo;

public interface AgendaService {

	//Si existe ejecutivo disponible, le asigna el caso en Genesys
	public String asignaCasoGenesys(AfiliadoVo datos_afiliado, SalidaEvaluadorModeloAISVo respuestaMotorAIS, OfertasVigentes_DT respuestaWSOfertasVigentes, String fechaSeleccionada, String horario);
	
	//campaña outbaund
	public boolean campagnaOutbaund(String afiliado);
}
