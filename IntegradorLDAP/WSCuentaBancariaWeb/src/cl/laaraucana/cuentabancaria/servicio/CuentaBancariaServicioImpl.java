package cl.laaraucana.cuentabancaria.servicio;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import org.apache.log4j.Logger;

import cl.laaraucana.cuentabancaria.services.CanalService;
import cl.laaraucana.cuentabancaria.services.CanalServiceImpl;
import cl.laaraucana.cuentabancaria.services.CuentaService;
import cl.laaraucana.cuentabancaria.services.CuentaServiceImpl;
import cl.laaraucana.cuentabancaria.services.UsuarioService;
import cl.laaraucana.cuentabancaria.services.UsuarioServiceImpl;
import cl.laaraucana.cuentabancaria.util.TokenFactory;
import cl.laaraucana.cuentabancaria.util.Utils;
import cl.laaraucana.cuentabancaria.vo.CanalVO;
import cl.laaraucana.cuentabancaria.vo.CredencialesVO;
import cl.laaraucana.cuentabancaria.vo.CuentaDescripcionVO;
import cl.laaraucana.cuentabancaria.vo.CuentaRequestWSVO;
import cl.laaraucana.cuentabancaria.vo.CuentaRevocaVO;
import cl.laaraucana.cuentabancaria.vo.CuentaSearchVO;
import cl.laaraucana.cuentabancaria.vo.CuentaVO;
import cl.laaraucana.cuentabancaria.vo.CuentasResponseVO;
import cl.laaraucana.cuentabancaria.vo.SalidaVO;
import cl.laaraucana.cuentabancaria.vo.UsuarioVo;

@WebService(targetNamespace = "http://servicio.laaraucana.cl/cuentabancaria", serviceName = "CuentaBancariaServicioService", portName = "CuentaBancariaServicioPort")
public class CuentaBancariaServicioImpl implements CuentaBancariaServicio{

	protected Logger logger = Logger.getLogger(CuentaBancariaServicio.class);

	@Resource
	private WebServiceContext wsCtx;

	
	@Override
	@WebMethod(action="http://servicios.laaraucana.cl/cuentabancaria/autenticacionWS",  operationName="autenticacionWS")
	public String autenticacionWS(@WebParam(name="credentials") @XmlElement(name="credentials", required=true) CredencialesVO aut) {

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

					return token_encode;
				} else {
					return "Credenciales inválidas";
				}
			} else {

				return "Credenciales inválidas";
			}

		} catch (Exception e) {

			logger.error("Error ", e);

			return "error " + e.getMessage();

		}

	}

	@Override
	@WebMethod(action="http://servicios.laaraucana.cl/cuentabancaria/ingresarCuenta",  operationName="ingresarCuenta")
	public SalidaVO ingresarCuenta(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="cuenta") @XmlElement(name="cuenta", required=true) CuentaRequestWSVO cuenta) {

		CuentaService cuserv = new CuentaServiceImpl();

		SalidaVO sal = new SalidaVO();
		String usuario = "";

		try {

			Utils util = new Utils();
			HttpServletRequest request= ((HttpServletRequest)(wsCtx.getMessageContext().get(MessageContext.SERVLET_REQUEST)));
			usuario = util.isValidToken(request, token);

			if (usuario.equals("")) {

				sal.setCodigo(3);
				sal.setDescripcion("Token inválido");

				return sal;
			} else {

				CuentaVO cu = new CuentaVO();
				 cu.setIdmandato(cuenta.getIdmandato());
		         cu.setCodbanco(cuenta.getCodbanco());
		         cu.setNumcuenta(cuenta.getNumcuenta());
		         cu.setCodtipocuenta(cuenta.getCodtipocuenta());
		         cu.setRutafi(cuenta.getRutafi());
		         cu.setDvafi(cuenta.getDvafi());
		         cu.setNomafi(cuenta.getNomafi());
		         cu.setTelefono(cuenta.getTelefono());
		         cu.setCelular(cuenta.getCelular());
		         cu.setEmail(cuenta.getEmail());
		         cu.setTipoprod(cuenta.getTipoprod());


				if (!cuserv.getCuentaByNum(cu)) {

					 Method m = null;

					 BeanInfo beanInfo = Introspector.getBeanInfo(cuenta.getClass());

			            for (PropertyDescriptor  pd : beanInfo.getPropertyDescriptors()) {

			                m = pd.getReadMethod();
			                
			                logger.info(m.invoke(cuenta, null));
			                
			                 if(m.invoke(cuenta, null).toString().trim().equals("0") || m.invoke(cuenta, null).equals("?")) {

			                	sal.setCodigo(3);
			 					sal.setDescripcion("Faltan datos para la ejecución del servicio.");

			 					return sal;

			                 }

			            }

					UsuarioService user = new UsuarioServiceImpl();
					UsuarioVo users = new UsuarioVo();
					users.setUsuario(usuario);
					
					cu.setCodcanal(user.consultaCodigoCanal(users));

					cuserv.setCuenta(cu);

					sal.setCodigo(1);
					sal.setDescripcion("Ejecución exitosa.");

					return sal;

				} else {

					sal.setCodigo(2);
					sal.setDescripcion("Cuenta ya existe.");

					return sal;

				}

		         

			}

		} catch (Exception e) {

			logger.error("Error ", e);

			sal.setCodigo(3);
			sal.setDescripcion("Error en la ejecución del servicio.");

			return sal;

		}
	}
	
	@Override
	@WebMethod(action="http://servicios.laaraucana.cl/cuentabancaria/revocarCuenta",  operationName="revocarCuenta")
	public SalidaVO revocarCuenta(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="cuenta") @XmlElement(name="cuenta", required=true) CuentaRevocaVO cuenta) {

		CuentaService cuserv = new CuentaServiceImpl();

		SalidaVO sal = new SalidaVO();
		String usuario = "";

		try {

			Utils util = new Utils();

			HttpServletRequest request= ((HttpServletRequest)(wsCtx.getMessageContext().get(MessageContext.SERVLET_REQUEST)));
			usuario = util.isValidToken(request, token);

			if (usuario.equals("")) {

				sal.setCodigo(3);
				sal.setDescripcion("Token inválido");

				return sal;
			} else {

				if(cuenta.getRutafi() == 0 || cuenta.getDvafi().equals("?") ||
						cuenta.getCodbanco() == 0 || cuenta.getNumcuenta().equals("?")) {

					sal.setCodigo(3);
 					sal.setDescripcion("Faltan datos para la ejecución del servicio.");
 					return sal;
				}

				if (!cuserv.getCuentaByNum(cuenta)) {

					sal.setCodigo(2);
					sal.setDescripcion("Cuenta no existe en el sistema.");

					return sal;

				} else {

					cuserv.revocarCuenta(cuenta);

					sal.setCodigo(1);
					sal.setDescripcion("Ejecución exitosa.");

					return sal;

				}

			}

		} catch (Exception e) {

			logger.error("Error ", e);

			sal.setCodigo(3);
			sal.setDescripcion("Error en la ejecución del servicio.");

			return sal;

		}

	}
	
	@Override
	@WebMethod(action="http://servicios.laaraucana.cl/cuentabancaria/consultaCuenta",  operationName="consultaCuenta")
	public CuentasResponseVO consultaCuenta(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="cuenta") @XmlElement(name="cuenta", required=true) CuentaSearchVO cuenta) {

		CuentaService cuserv = new CuentaServiceImpl();
		CanalService canal = new CanalServiceImpl();

		List<CuentaDescripcionVO> sal = new ArrayList<CuentaDescripcionVO>();
		CuentasResponseVO cuentas= new CuentasResponseVO();
		SalidaVO cu;
		
		//CuentaDescripcionVO cuentaDes= new CuentaDescripcionVO();

		String usuario = "";

		try {

			Utils util = new Utils();

			HttpServletRequest request= ((HttpServletRequest)(wsCtx.getMessageContext().get(MessageContext.SERVLET_REQUEST)));
			usuario = util.isValidToken(request, token);

			if (usuario.equals("")) {

				cu = new SalidaVO();
				cu.setCodigo(3);
				cu.setDescripcion("Token inválido");
				cuentas.setLog(cu);
				return cuentas;
			} else {

				if(!Utils.validarRut(cuenta.getRutafi() + "-" + cuenta.getDvafi())) {

					cu = new SalidaVO();
					cu.setCodigo(1);
					cu.setDescripcion("Parámetros no válidos.");
					cuentas.setLog(cu);
					return cuentas;
				}

				List<CuentaDescripcionVO> cuentaResp = cuserv.getConsultaCuenta(cuenta);

				UsuarioService user = new UsuarioServiceImpl();

				CanalVO canalvo = new CanalVO();

				cu = new SalidaVO();
				cu.setCodigo(2);
				cu.setDescripcion("Ejecución exitosa.");
				
				cuentas.setCuentas(cuentaResp);

				/*UsuarioVo users = new UsuarioVo();

				users.setUsuario(usuario);

				canalvo.setCanal(user.consultaCanal(users));
				canalvo.setRut(cuenta.getRutafi());
				canalvo.setDv(cuenta.getDvafi());

				canal.setCanal(canalvo);*/
				cuentas.setLog(cu);
				
				return cuentas;

			}

		} catch (Exception e) {

			logger.error("Error ", e);

			cu = new SalidaVO();
			cu.setCodigo(3);
			cu.setDescripcion("Error en la ejecución del servicio.");
			cuentas.setLog(cu);
			return cuentas;

		}

	}

}
