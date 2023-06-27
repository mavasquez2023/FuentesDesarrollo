package cl.laaraucana.cuentabancaria.services;

import java.util.List;

import cl.laaraucana.cuentabancaria.dao.CuentasDAO;
import cl.laaraucana.cuentabancaria.vo.CuentaDescripcionVo;
import cl.laaraucana.cuentabancaria.vo.CuentaVo;

public class CuentaServiceImpl implements CuentaService {

	private final CuentasDAO dao = new CuentasDAO();

	@Override
	public void setCuenta(CuentaVo cuenta) throws Exception {

		dao.setCuenta(cuenta);

	}

	@Override
	public boolean getCuentaByNum(CuentaVo cuenta) throws Exception {

		return dao.getCuentaByNumCuenta(cuenta);
	}

	@Override
	public void editStatusCuenta(CuentaVo cuenta) throws Exception {

		dao.editStatusCuenta(cuenta);

	}

	@Override
	public List<CuentaDescripcionVo> getConsultaCuenta(CuentaVo cuenta) throws Exception {
		// TODO Auto-generated method stub
		return dao.getConsultaCuenta(cuenta);
	}

}
