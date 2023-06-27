package cl.laaraucana.cuentabancaria.servicio;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import org.apache.log4j.Logger;
import cl.laaraucana.cuentabancaria.out.SalidaVo;
import cl.laaraucana.cuentabancaria.services.CanalService;
import cl.laaraucana.cuentabancaria.services.CanalServiceImpl;
import cl.laaraucana.cuentabancaria.services.CuentaService;
import cl.laaraucana.cuentabancaria.services.CuentaServiceImpl;
import cl.laaraucana.cuentabancaria.services.UsuarioService;
import cl.laaraucana.cuentabancaria.services.UsuarioServiceImpl;
import cl.laaraucana.cuentabancaria.util.TokenFactory;
import cl.laaraucana.cuentabancaria.util.Utils;
import cl.laaraucana.cuentabancaria.vo.CanalVo;
import cl.laaraucana.cuentabancaria.vo.CredencialesVo;
import cl.laaraucana.cuentabancaria.vo.CuentaDescripcionVo;
import cl.laaraucana.cuentabancaria.vo.CuentaSaveVo;
import cl.laaraucana.cuentabancaria.vo.CuentaVo;
import cl.laaraucana.cuentabancaria.vo.UsuarioVo;


public class CuentaBancariaServicioImpl {

	protected static Logger logger = Logger.getLogger(CuentaBancariaServicio.class);

	@Resource
	private WebServiceContext wsCtx;

	private final UsuarioService user = new UsuarioServiceImpl();

	public String autenticacionWS(CredencialesVo aut) {

		try {

			CredencialesVo dataUsuario = user.consultaCredenciales(aut);

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

	public SalidaVo pushCuenta(CuentaSaveVo cuenta) {

		CuentaService cuserv = new CuentaServiceImpl();

		SalidaVo sal = new SalidaVo();
		String usuario = "";

		try {

			Utils util = new Utils();

			usuario = util.isValidToken(cuenta.getToken());

			if (usuario.equals("")) {

				sal.setTipoRespuesta(3);
				sal.setDescripcion("Token inválido");

				return sal;
			} else {

				 Method m = null;

				 BeanInfo beanInfo = Introspector.getBeanInfo(cuenta.getClass());

		            for (PropertyDescriptor  pd : beanInfo.getPropertyDescriptors()) {

		                m = pd.getReadMethod();

		                logger.info(m.invoke(cuenta, null));

		                 if(m.invoke(cuenta, null).toString().trim().equals("0") || m.invoke(cuenta, null).equals("?")) {

		                	sal.setTipoRespuesta(3);
		 					sal.setDescripcion("Faltan datos para la ejecución del servicio.");

		 					return sal;

		                 }

		            }

		         CuentaVo cu = new CuentaVo();

		         cu.setCodbanco(cuenta.getCodbanco());
		         cu.setCodtipocuenta(cuenta.getCodtipocuenta());
		         cu.setDvafi(cuenta.getDvafi());
		         cu.setEmail(cuenta.getEmail());
		         cu.setNomafi(cuenta.getNomafi());
		         cu.setNumcuenta(cuenta.getNumcuenta());
		         cu.setRutafi(cuenta.getRutafi());
		         cu.setTipoprod(cuenta.getTipoprod());
		         cu.setToken(cuenta.getToken());


				if (!cuserv.getCuentaByNum(cu)) {



					UsuarioService user = new UsuarioServiceImpl();

					UsuarioVo users = new UsuarioVo();

					users.setUsuario(usuario);

					cu.setCodcanal(user.consultaCodigoCanal(users));

					cuserv.setCuenta(cu);

					sal.setTipoRespuesta(1);
					sal.setDescripcion("Ejecución exitosa.");

					return sal;

				} else {

					sal.setTipoRespuesta(2);
					sal.setDescripcion("Cuenta ya existe.");

					return sal;

				}

			}

		} catch (Exception e) {

			logger.error("Error ", e);

			sal.setTipoRespuesta(3);
			sal.setDescripcion("Error en la ejecución del servicio.");

			return sal;

		}
	}

	public SalidaVo editStatusCuenta(CuentaVo cuenta) {

		CuentaService cuserv = new CuentaServiceImpl();

		SalidaVo sal = new SalidaVo();
		String usuario = "";

		try {

			Utils util = new Utils();

			usuario = util.isValidToken(cuenta.getToken());

			if (usuario.equals("")) {

				sal.setTipoRespuesta(3);
				sal.setDescripcion("Token inválido");

				return sal;
			} else {

				if(cuenta.getRutafi() == 0 || cuenta.getDvafi().equals("?") ||
						cuenta.getCodbanco() == 0 || cuenta.getNumcuenta().equals("?")) {

					sal.setTipoRespuesta(3);
 					sal.setDescripcion("Faltan datos para la ejecución del servicio.");
 					return sal;
				}

				if (!cuserv.getCuentaByNum(cuenta)) {

					sal.setTipoRespuesta(2);
					sal.setDescripcion("Cuenta no existe en el sistema.");

					return sal;

				} else {

					cuserv.editStatusCuenta(cuenta);

					sal.setTipoRespuesta(1);
					sal.setDescripcion("Ejecución exitosa.");

					return sal;

				}

			}

		} catch (Exception e) {

			logger.error("Error ", e);

			sal.setTipoRespuesta(3);
			sal.setDescripcion("Error en la ejecución del servicio.");

			return sal;

		}

	}

	public List<CuentaDescripcionVo> getConsultaCuenta(CuentaVo cuenta) {

		CuentaService cuserv = new CuentaServiceImpl();
		CanalService canal = new CanalServiceImpl();

		List<CuentaDescripcionVo> sal = new ArrayList<CuentaDescripcionVo>();

		SalidaVo cu;
		CuentaDescripcionVo cuentaDes;

		String usuario = "";

		try {

			Utils util = new Utils();

			usuario = util.isValidToken(cuenta.getToken());

			if (usuario.equals("")) {

				cu = new SalidaVo();
				cuentaDes = new CuentaDescripcionVo();

				cu.setTipoRespuesta(3);
				cu.setDescripcion("Token inválido");
				cuentaDes.setSalida(cu);
				sal.add(cuentaDes);

				return sal;
			} else {

				if(!Utils.validarRut(cuenta.getRutafi() + "-" + cuenta.getDvafi())) {

					cu = new SalidaVo();
					cuentaDes = new CuentaDescripcionVo();

					cu.setTipoRespuesta(1);
					cu.setDescripcion("Parámetros no válidos.");
					cuentaDes.setSalida(cu);
					sal.add(cuentaDes);

					return sal;
				}

				List<CuentaDescripcionVo> cuentaResp = cuserv.getConsultaCuenta(cuenta);

				UsuarioService user = new UsuarioServiceImpl();

				CanalVo canalvo = new CanalVo();

				cu = new SalidaVo();
				cuentaDes = new CuentaDescripcionVo();

				cu.setTipoRespuesta(2);
				cu.setDescripcion("Ejecución exitosa.");

				cuentaDes.setSalida(cu);

				cuentaResp.add(cuentaDes);

				UsuarioVo users = new UsuarioVo();

				users.setUsuario(usuario);

				canalvo.setCanal(user.consultaCanal(users));
				canalvo.setRut(cuenta.getRutafi());
				canalvo.setDv(cuenta.getDvafi());

				canal.setCanal(canalvo);

				return cuentaResp;

			}

		} catch (Exception e) {

			logger.error("Error ", e);

			cu = new SalidaVo();
			cuentaDes = new CuentaDescripcionVo();

			cu.setTipoRespuesta(3);
			cu.setDescripcion("Error en la ejecución del servicio.");

			cuentaDes.setSalida(cu);

			sal.add(cuentaDes);

			return sal;

		}

	}

}
