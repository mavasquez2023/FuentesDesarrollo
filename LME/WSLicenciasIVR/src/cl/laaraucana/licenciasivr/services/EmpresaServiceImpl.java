package cl.laaraucana.licenciasivr.services;

import cl.laaraucana.licenciasivr.ibatis.dao.EmpresaDAO;
import cl.laaraucana.licenciasivr.vo.EmpresaVO;

public class EmpresaServiceImpl implements EmpresaService {

	private final EmpresaDAO dao = new EmpresaDAO();
	
	@Override
	public EmpresaVO consultaEmpresa(EmpresaVO empresa) throws Exception {
		return dao.getEmpresa(empresa);
	}

	@Override
	public Integer existeILF3500(Integer folioPago) throws Exception {
		return dao.getILF3500(folioPago);
	}

	@Override
	public String existeILF4500A(Integer folioPago) throws Exception {
		return dao.getILF4500A(folioPago);
	}

	@Override
	public String existeTE3YA(Integer folioPago) throws Exception {
		return dao.getTE07F1(folioPago);
	}

	
}
