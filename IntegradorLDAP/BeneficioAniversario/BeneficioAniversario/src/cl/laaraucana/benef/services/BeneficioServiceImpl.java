package cl.laaraucana.benef.services;

import java.util.List;

import cl.laaraucana.benef.dao.BeneficiosDAO;
import cl.laaraucana.benef.vo.BeneficioRequestVO;
import cl.laaraucana.benef.vo.BeneficioVO;


public class BeneficioServiceImpl implements BeneficioService {

	private final BeneficiosDAO dao = new BeneficiosDAO();

	@Override
	public BeneficioVO consultarBeneficio(String codigo) throws Exception {

		return dao.getBeneficioByCodigo(codigo);
	}
	
	@Override
	public boolean confirmarBeneficio(BeneficioRequestVO beneficio) throws Exception {

		try {
			return dao.actualizaBeneficio(beneficio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
	
	@Override
	public List<BeneficioVO> obtenerPendientes() throws Exception {

		return dao.getPendientes();
	}
	
	@Override
	public boolean actualizaPendiente(String codigo) throws Exception {

		try {
			return dao.actualizaPendiente(codigo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
}
