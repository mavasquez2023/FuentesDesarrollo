package cl.laaraucana.cuentabancaria.servicio;

import cl.laaraucana.cuentabancaria.out.SalidaVo;
import cl.laaraucana.cuentabancaria.vo.CredencialesVo;
import cl.laaraucana.cuentabancaria.vo.CuentaDescripcionVo;
import cl.laaraucana.cuentabancaria.vo.CuentaSaveVo;
import cl.laaraucana.cuentabancaria.vo.CuentaVo;

import java.util.List;

import javax.jws.WebService;

@WebService(targetNamespace = "http://servicio.cuentabancaria.laaraucana.cl/", serviceName = "CuentaBancariaServicioService", portName = "CuentaBancariaServicioPort")
public class CuentaBancariaServicioDelegate {

	cl.laaraucana.cuentabancaria.servicio.CuentaBancariaServicio _cuentaBancariaServicio = null;

	public String autenticacionWS(CredencialesVo aut) {
		return _cuentaBancariaServicio.autenticacionWS(aut);
	}

	public SalidaVo pushCuenta(CuentaSaveVo cuenta) {
		return _cuentaBancariaServicio.pushCuenta(cuenta);
	}

	public SalidaVo editStatusCuenta(CuentaVo cuenta) {
		return _cuentaBancariaServicio.editStatusCuenta(cuenta);
	}

	public List<CuentaDescripcionVo> getConsultaCuenta(CuentaVo cuenta) {
		return _cuentaBancariaServicio.getConsultaCuenta(cuenta);
	}

	public CuentaBancariaServicioDelegate() {
		_cuentaBancariaServicio = new cl.laaraucana.cuentabancaria.servicio.CuentaBancariaServicio();
	}

}