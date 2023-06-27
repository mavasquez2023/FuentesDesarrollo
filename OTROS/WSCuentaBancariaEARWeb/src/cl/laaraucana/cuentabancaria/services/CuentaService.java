package cl.laaraucana.cuentabancaria.services;

import java.util.List;

import cl.laaraucana.cuentabancaria.vo.CuentaDescripcionVo;
import cl.laaraucana.cuentabancaria.vo.CuentaVo;

public interface CuentaService {
	
	public void setCuenta(CuentaVo cuenta) throws Exception;
	
	public boolean getCuentaByNum(CuentaVo cuenta) throws Exception;
	
	public void editStatusCuenta(CuentaVo cuenta) throws Exception;
	
	public List<CuentaDescripcionVo> getConsultaCuenta(CuentaVo cuenta) throws Exception;

}
