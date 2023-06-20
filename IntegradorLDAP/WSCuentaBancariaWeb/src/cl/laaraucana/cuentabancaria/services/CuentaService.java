package cl.laaraucana.cuentabancaria.services;

import java.util.List;

import cl.laaraucana.cuentabancaria.vo.CuentaDescripcionVO;
import cl.laaraucana.cuentabancaria.vo.CuentaRevocaVO;
import cl.laaraucana.cuentabancaria.vo.CuentaSearchVO;
import cl.laaraucana.cuentabancaria.vo.CuentaVO;

public interface CuentaService {
	
	public void setCuenta(CuentaVO cuenta) throws Exception;
	
	public boolean getCuentaByNum(CuentaVO cuenta) throws Exception;
	
	public boolean getCuentaByNum(CuentaRevocaVO cuenta) throws Exception;
	
	public void revocarCuenta(CuentaRevocaVO cuenta) throws Exception;
	
	public List<CuentaDescripcionVO> getConsultaCuenta(CuentaSearchVO cuenta) throws Exception;

}
