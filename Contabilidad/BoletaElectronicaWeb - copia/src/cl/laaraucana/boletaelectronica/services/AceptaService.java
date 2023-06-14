package cl.laaraucana.boletaelectronica.services;

import java.util.List;

import cl.laaraucana.boletaelectronica.entities.BoletaBase;
import cl.laaraucana.boletaelectronica.vo.OrigenBoletaVo;

public interface AceptaService {
	
	public List<OrigenBoletaVo> wsAcepta(List<BoletaBase> boletasEmitidas) throws Exception;

}
