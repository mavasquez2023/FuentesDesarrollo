package cl.laaraucana.licenciasivr.services;

import cl.laaraucana.licenciasivr.vo.EmpresaVO;

public interface EmpresaService {
	
	public EmpresaVO consultaEmpresa(EmpresaVO empresa) throws Exception;
	
	public Integer existeILF3500(Integer folioPago) throws Exception;
	
	public String existeILF4500A(Integer folioPago) throws Exception;

	public String existeTE3YA(Integer folioPago) throws Exception;
}
