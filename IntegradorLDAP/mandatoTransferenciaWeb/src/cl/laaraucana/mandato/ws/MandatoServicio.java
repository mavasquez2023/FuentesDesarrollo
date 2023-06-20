package cl.laaraucana.mandato.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import cl.laaraucana.transferencias.banco.vo.CredencialesVO;
import cl.laaraucana.transferencias.banco.vo.CuentaRequestWSVO;
import cl.laaraucana.transferencias.banco.vo.CuentaResponseWSVO;
import cl.laaraucana.transferencias.banco.vo.MandatoResponseWSVO;
import cl.laaraucana.transferencias.banco.vo.ParamConsultaVO;
import cl.laaraucana.transferencias.banco.vo.ParamRevocarVO;
import cl.laaraucana.transferencias.banco.vo.SalidaVO;

@WebService(targetNamespace = "http://servicio.laaraucana.cl/mandato")
public interface MandatoServicio {

	@WebMethod()
	public CuentaResponseWSVO consultarMandato(CredencialesVO autenticacion, ParamConsultaVO param);
	
	@WebMethod()
	public MandatoResponseWSVO ingresarMandato(CredencialesVO autenticacion, CuentaRequestWSVO cuenta);
	
	@WebMethod()
	public MandatoResponseWSVO revocarMandato(CredencialesVO autenticacion, ParamRevocarVO param);

}