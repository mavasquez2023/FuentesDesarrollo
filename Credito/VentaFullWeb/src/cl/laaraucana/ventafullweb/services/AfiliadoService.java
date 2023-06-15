package cl.laaraucana.ventafullweb.services;

import cl.laaraucana.ventafullweb.vo.AfiliadoVo;
import cl.laaraucana.ventafullweb.vo.SolicitanteVo;

public interface AfiliadoService {
	
	public AfiliadoVo getDataAfiliado(SolicitanteVo solicitante) throws Exception;
	
	public boolean isAfiliadoVigente(AfiliadoVo afiliado);
	
	public boolean isFuncionario(AfiliadoVo afiliado);
	
	public boolean isFallecido(AfiliadoVo afiliado);
}
