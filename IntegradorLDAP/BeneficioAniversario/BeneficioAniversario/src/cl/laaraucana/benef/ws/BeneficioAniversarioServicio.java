package cl.laaraucana.benef.ws;



import cl.laaraucana.benef.vo.BeneficioRequestVO;
import cl.laaraucana.benef.vo.BeneficioResponseVO;
import cl.laaraucana.benef.vo.CredencialesVO;
import cl.laaraucana.benef.vo.TokenVO;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "http://servicio.laaraucana.cl/beneficioaniversario")
public interface BeneficioAniversarioServicio {

	@WebMethod()
	public abstract TokenVO autenticacionWS(CredencialesVO aut) ;
	
	@WebMethod()
	public abstract BeneficioResponseVO confirmaBeneficio(String token, BeneficioRequestVO beneficio);
	
	@WebMethod()
	public abstract BeneficioResponseVO consultaBeneficio(String token, String codigo);


}