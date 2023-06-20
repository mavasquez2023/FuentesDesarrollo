package cl.laaraucana.cuentabancaria.servicio;



import cl.laaraucana.cuentabancaria.vo.CredencialesVO;
import cl.laaraucana.cuentabancaria.vo.CuentaRequestWSVO;
import cl.laaraucana.cuentabancaria.vo.CuentaRevocaVO;
import cl.laaraucana.cuentabancaria.vo.CuentaSearchVO;
import cl.laaraucana.cuentabancaria.vo.CuentasResponseVO;
import cl.laaraucana.cuentabancaria.vo.SalidaVO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

@WebService(targetNamespace = "http://servicio.laaraucana.cl/cuentabancaria")
public interface CuentaBancariaServicio {

	@WebMethod()
	public abstract String autenticacionWS(@WebParam(name="credentials") @XmlElement(name="credentials", required=true) CredencialesVO aut) ;
	
	@WebMethod()
	public abstract SalidaVO ingresarCuenta(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="cuenta") @XmlElement(name="cuenta", required=true) CuentaRequestWSVO cuenta) ;
	
	@WebMethod()
	public abstract SalidaVO revocarCuenta(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="cuenta") @XmlElement(name="cuenta", required=true) CuentaRevocaVO cuenta);
	
	@WebMethod()
	public abstract CuentasResponseVO consultaCuenta(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="cuenta") @XmlElement(name="cuenta", required=true) CuentaSearchVO cuenta);


}