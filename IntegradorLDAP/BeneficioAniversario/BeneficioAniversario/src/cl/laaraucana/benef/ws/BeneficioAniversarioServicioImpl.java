package cl.laaraucana.benef.ws;


import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import org.apache.log4j.Logger;

import cl.laaraucana.benef.services.BeneficioService;
import cl.laaraucana.benef.services.BeneficioServiceImpl;
import cl.laaraucana.benef.services.BitacoraService;
import cl.laaraucana.benef.services.BitacoraServiceImpl;
import cl.laaraucana.benef.services.UsuarioService;
import cl.laaraucana.benef.services.UsuarioServiceImpl;
import cl.laaraucana.benef.utils.TokenFactory;
import cl.laaraucana.benef.utils.Utils;
import cl.laaraucana.benef.vo.BeneficioRequestVO;
import cl.laaraucana.benef.vo.BeneficioResponseVO;
import cl.laaraucana.benef.vo.BeneficioVO;
import cl.laaraucana.benef.vo.BitacoraVO;
import cl.laaraucana.benef.vo.CredencialesVO;
import cl.laaraucana.benef.vo.SalidaVO;
import cl.laaraucana.benef.vo.TokenVO;

@WebService(targetNamespace = "http://servicio.laaraucana.cl/benef", serviceName = "benefServicioService", portName = "benefServicioPort")
public class BeneficioAniversarioServicioImpl implements BeneficioAniversarioServicio{

	protected Logger logger = Logger.getLogger(BeneficioAniversarioServicio.class);

	@Resource
	private WebServiceContext wsCtx;

	
	@Override
	@WebMethod(action="http://servicios.laaraucana.cl/benef/autenticacionWS",  operationName="autenticacionWS")
	public TokenVO autenticacionWS(@WebParam(name="credentials") @XmlElement(name="credentials", required=true) CredencialesVO aut) {
		TokenVO tokenVO= new TokenVO();
		try {
			UsuarioService user = new UsuarioServiceImpl();
			CredencialesVO dataUsuario = user.consultaCredenciales(aut);

			if (dataUsuario != null) {
				String password = dataUsuario.getPassword();

				if (aut.getPassword().equals(password)) {
					String remoteip = ((HttpServletRequest) (wsCtx.getMessageContext()
					 .get(MessageContext.SERVLET_REQUEST))).getRemoteAddr();
					String token_encode = Utils.generaToken(remoteip, aut.getUsuario());
					String fecha = Utils.getFecha();
					TokenFactory.getInstance().addBaseUsuarios(aut.getUsuario(), "");
					TokenFactory.getInstance().addToken(aut.getUsuario(), token_encode);
					tokenVO.setToken(token_encode);
					return tokenVO;
				} else {
					tokenVO.setDescripcion("Credenciales inválidas");
					return tokenVO;
				}
			} else {
				tokenVO.setDescripcion("Credenciales inválidas");
				return tokenVO;
			}

		} catch (Exception e) {

			logger.error("Error ", e);
			tokenVO.setDescripcion("Error " + e.getMessage());
			return tokenVO;

		}

	}
	
	@Override
	@WebMethod(action="http://servicios.laaraucana.cl/benef/confirmaBeneficio",  operationName="confirmaBeneficio")
	public BeneficioResponseVO confirmaBeneficio(@WebParam(name="TOKEN") @XmlElement(name="TOKEN", required=true) String token, @WebParam(name="BENEFICIO") @XmlElement(name="BENEFICIO", required=true) BeneficioRequestVO beneficio) {

		BeneficioService benserv = new BeneficioServiceImpl();
		BeneficioResponseVO responseVO= new BeneficioResponseVO();
		SalidaVO sal = new SalidaVO();
		String usuario = "";

		try {

			Utils util = new Utils();

			HttpServletRequest request= ((HttpServletRequest)(wsCtx.getMessageContext().get(MessageContext.SERVLET_REQUEST)));
			usuario = util.isValidToken(request, token);

			if (usuario.equals("")) {

				sal.setCodigo(3);
				sal.setDescripcion("Token inválido");
				responseVO.setLog(sal);
				return responseVO;
			} else {

				if(beneficio.getCodigoBeneficio() == null || beneficio.getCodigoBeneficio().equals("?") || beneficio.getCodigoBeneficio().equals("")
						|| beneficio.getHotel() == null || beneficio.getHotel().equals("?") || beneficio.getHotel().equals("")) {

					sal.setCodigo(3);
 					sal.setDescripcion("Parámetros no válidos.");
 					responseVO.setLog(sal);
 					return responseVO;
				}
				BeneficioVO beneVO= benserv.consultarBeneficio(beneficio.getCodigoBeneficio());
				if (beneVO==null) {

					sal.setCodigo(2);
					sal.setDescripcion("Codigo Beneficio no existe en el sistema.");

					responseVO.setLog(sal);
					return responseVO;

				} else {

					boolean respuesta= benserv.confirmarBeneficio(beneficio);
					if(respuesta){
						sal.setCodigo(1);
						sal.setDescripcion("Ejecución exitosa.");
					}else{
						sal.setCodigo(4);
						sal.setDescripcion("Fallo en la grabación.");
					}
					responseVO.setLog(sal);
					return responseVO;

				}

			}

		} catch (Exception e) {

			logger.error("Error ", e);

			sal.setCodigo(4);
			sal.setDescripcion("Error en la ejecución del servicio.");

			responseVO.setLog(sal);
			return responseVO;

		}

	}
	
	@Override
	@WebMethod(action="http://servicios.laaraucana.cl/benef/consultaBeneficio",  operationName="consultaBeneficio")
	public BeneficioResponseVO consultaBeneficio(@WebParam(name="TOKEN") @XmlElement(name="TOKEN", required=true) String token, @WebParam(name="CODIGO_BENEFICIO") @XmlElement(name="CODIGO_BENEFICIO", required=true) String codigo) {

		BeneficioService benserv = new BeneficioServiceImpl();

		BeneficioResponseVO responseVO= new BeneficioResponseVO();
		SalidaVO sal= new SalidaVO();
		
		//CuentaDescripcionVO cuentaDes= new CuentaDescripcionVO();

		String usuario = "";

		try {

			Utils util = new Utils();

			HttpServletRequest request= ((HttpServletRequest)(wsCtx.getMessageContext().get(MessageContext.SERVLET_REQUEST)));
			usuario = util.isValidToken(request, token);

			if (usuario.equals("")) {
				sal.setCodigo(3);
				sal.setDescripcion("Token inválido");
				responseVO.setLog(sal);
			} else {

				if(codigo == null || codigo.equals("?") || codigo.equals("")){

					sal.setCodigo(3);
					sal.setDescripcion("Parámetros no válidos.");
					responseVO.setLog(sal);
				}else{

					BeneficioVO beneVO= benserv.consultarBeneficio(codigo);
					if (beneVO==null) {
						sal.setCodigo(2);
						sal.setDescripcion("Codigo Beneficio no existe en el sistema.");
						responseVO.setLog(sal);

					} else {
						sal.setCodigo(1);
						sal.setDescripcion("Ejecución exitosa.");
						responseVO.setLog(sal);
						responseVO.setBeneficio(beneVO);
					}
					BitacoraVO bitacora= new BitacoraVO();
					bitacora.setUsuario(usuario);
					bitacora.setCodigo(codigo);
					BitacoraService bitaService= new BitacoraServiceImpl();
					boolean resultado= bitaService.guardarBitacora(bitacora);
				}
			}

		} catch (Exception e) {

			logger.error("Error ", e);

			sal.setCodigo(4);
			sal.setDescripcion("Error en la ejecución del servicio.");
			responseVO.setLog(sal);
			

		}
		return responseVO;

	}

}
