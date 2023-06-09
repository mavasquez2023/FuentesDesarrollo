package cl.laaraucana.ventafullweb.services;

import cl.laaraucana.servicios.simulaCredito.Response;
import cl.laaraucana.ventafullweb.vo.AfiliadoVo;

public interface CreditoService {

	public AfiliadoVo validarCredito(AfiliadoVo afiliado) throws Exception;
	
	public Response simularCredito(AfiliadoVo afiliado) throws Exception;
}
