package cl.laaraucana.benef.services;

import java.util.List;

import cl.laaraucana.benef.vo.BeneficioRequestVO;
import cl.laaraucana.benef.vo.BeneficioVO;


public interface BeneficioService {
	
	public BeneficioVO consultarBeneficio(String codigo) throws Exception;
	
	public boolean confirmarBeneficio(BeneficioRequestVO beneficio) throws Exception;
	
	public List<BeneficioVO> obtenerPendientes() throws Exception;
	
	public boolean actualizaPendiente(String codigo) throws Exception;
	
}
