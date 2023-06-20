package cl.laaraucana.cuentabancaria.services;

import java.util.List;

import cl.laaraucana.cuentabancaria.dao.CuentasDAO;
import cl.laaraucana.cuentabancaria.vo.CuentaDescripcionVO;
import cl.laaraucana.cuentabancaria.vo.CuentaRevocaVO;
import cl.laaraucana.cuentabancaria.vo.CuentaSearchVO;
import cl.laaraucana.cuentabancaria.vo.CuentaVO;

public class CuentaServiceImpl implements CuentaService {

	private final CuentasDAO dao = new CuentasDAO();

	@Override
	public void setCuenta(CuentaVO cuenta) throws Exception {

		dao.setCuenta(cuenta);

	}

	@Override
	public boolean getCuentaByNum(CuentaVO cuenta) throws Exception {

		return dao.getCuentaByNumCuenta(cuenta);
	}
	
	@Override
	public boolean getCuentaByNum(CuentaRevocaVO cuenta) throws Exception {

		return dao.getCuentaVigente(cuenta);
	}

	
	@Override
	public void revocarCuenta(CuentaRevocaVO cuenta) throws Exception {

		dao.revocarCuenta(cuenta);

	}
	
	@Override
	public List<CuentaDescripcionVO> getConsultaCuenta(CuentaSearchVO cuenta) throws Exception {
		// TODO Auto-generated method stub
		return dao.getConsultaCuenta(cuenta);
	}

}
