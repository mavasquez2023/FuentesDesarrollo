package cl.laaraucana.mandato.ws;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import cl.araucana.core.util.Rut;
import cl.laaraucana.transferencias.banco.vo.CredencialesVO;
import cl.laaraucana.transferencias.banco.vo.CuentaDescripcionVO;
import cl.laaraucana.transferencias.banco.vo.CuentaRequestWSVO;
import cl.laaraucana.transferencias.banco.vo.CuentaResponseWSVO;
import cl.laaraucana.transferencias.banco.vo.MandatoResponseWSVO;
import cl.laaraucana.transferencias.banco.vo.ParamConsultaVO;
import cl.laaraucana.transferencias.banco.vo.ParamRevocarVO;
import cl.laaraucana.transferencias.banco.vo.SalidaVO;
import cl.laaraucana.transferencias.banco.vo.UsuarioVo;
import cl.laaraucana.transferencias.ibatis.vo.MandatoAS400Vo;
import cl.laaraucana.transferencias.services.BancoService;
import cl.laaraucana.transferencias.services.BancoServiceImpl;
import cl.laaraucana.transferencias.services.MailService;
import cl.laaraucana.transferencias.services.MailServiceImpl;
import cl.laaraucana.transferencias.services.MandatoAS400Service;
import cl.laaraucana.transferencias.services.MandatoAS400ServiceImpl;
import cl.laaraucana.transferencias.services.ReporteService;
import cl.laaraucana.transferencias.services.ReporteServiceImpl;
import cl.laaraucana.transferencias.services.UsuarioService;
import cl.laaraucana.transferencias.services.UsuarioServiceImpl;
import cl.laaraucana.transferencias.util.Configuraciones;
import cl.laaraucana.transferencias.util.Utils;
import cl.laaraucana.transferencias.vo.DatosMandatoVo;

@WebService(targetNamespace = "http://servicio.laaraucana.cl/mandato", serviceName = "MandatoServicioService", portName = "MandatoServicioPort")
//@SchemaValidation
public class MandatoServicioImpl implements MandatoServicio {

	protected Logger logger = Logger.getLogger(MandatoServicio.class);

	private MailService mailService = new MailServiceImpl();

	private ReporteService reportService = new ReporteServiceImpl();
	
	private BancoService bancoService = new BancoServiceImpl();

	private MandatoAS400Service mandatoas400Service = new MandatoAS400ServiceImpl();
	
	private UsuarioVo dataUsuario=null;
	
	@Resource
	private WebServiceContext wsCtx;

	private boolean autenticacionWS(CredencialesVO aut) {
		boolean salida= false;
		try {
			if(dataUsuario==null){
				UsuarioService user = new UsuarioServiceImpl();
				dataUsuario = user.consultaCredenciales(aut);
			}
			if (dataUsuario != null) {
				String password = dataUsuario.getPassword();

				if (aut.getPassword().equals(password)) {

					salida= true;
				}
			} 

		} catch (Exception e) {
			logger.error("Error ", e);

		}
		return salida;
	}
	
	@Override
	@WebMethod(action = "http://servicios.laaraucana.cl/mandato/ingresarMandato", operationName = "ingresarMandato")
	public MandatoResponseWSVO ingresarMandato(@WebParam(name = "autenticacion") @XmlElement(name="autenticacion", required=true) CredencialesVO autenticacion,
			@WebParam(name = "cuenta") @XmlElement(name="cuenta", required=true) CuentaRequestWSVO cuenta) {
		MandatoResponseWSVO cuentaWS= new MandatoResponseWSVO();
		SalidaVO sal = new SalidaVO();
		String ruta = "";

		try {

			boolean usuarioValido = autenticacionWS(autenticacion);

			if (!usuarioValido) {

				sal.setCodigo(2);
				sal.setDescripcion("Usuario o Clave no válido");

			} else {

				if (cuenta.getCodbanco() == 0 || cuenta.getCodtipocuenta() == 0 || cuenta.getRutafi() == 0
						|| cuenta.getDvafi().isEmpty() || cuenta.getNumcuenta().isEmpty() || cuenta.getEmail().isEmpty()
						|| cuenta.getNomafi().isEmpty()) {

					sal.setCodigo(3);
					sal.setDescripcion("Faltan parámetros obligatorios.");
					cuentaWS.setLog(sal);
					return cuentaWS;

				}
				
				if (new Rut(cuenta.getRutafi()).getDV()!=cuenta.getDvafi().charAt(0)) {

					sal.setCodigo(3);
					sal.setDescripcion("Rut no válido.");
					cuentaWS.setLog(sal);
					return cuentaWS;

				}
				
				String banco = bancoService.findBancoById(cuenta.getCodbanco()).getNombre();
				String nombreCuenta = bancoService.findAccountkById(cuenta.getCodtipocuenta()).getDescripcion();
				if(banco==null || banco.equals("") || nombreCuenta==null || nombreCuenta.equals("")){
					sal.setCodigo(4);
					sal.setDescripcion("Datos bancarios no válidos.");
					cuentaWS.setLog(sal);
					return cuentaWS;
				}
				
				String telefono=cuenta.getTelefono();
				if( telefono==null){
					telefono=("");
				}
				String celular=cuenta.getCelular();
				if( celular==null){
					celular="";
				}
				
				DatosMandatoVo vo = new DatosMandatoVo();

				vo.setBanco(String.valueOf(cuenta.getCodbanco()));
				vo.setCelular(celular);
				vo.setCuenta(cuenta.getNumcuenta());
				vo.setEmail(cuenta.getEmail());
				vo.setNameBanco(banco);
				vo.setNameCuenta(nombreCuenta);
				vo.setNombre(cuenta.getNomafi());
				vo.setRut(String.valueOf(cuenta.getRutafi() + "-" + cuenta.getDvafi()));
				vo.setTelefono(telefono);
				vo.setTipoCuenta(String.valueOf(cuenta.getCodtipocuenta()));
				vo.setSerie("0");

				// mandato as400

				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
				SimpleDateFormat sdh = new SimpleDateFormat("HH:mm:ss");

				String tipoProducto = Configuraciones.getConfig("sil.tipo.producto");

				long idMandato = mandatoas400Service.consultaMandatosGetId();
				String strIdMandato = String.valueOf(idMandato);
				int len = strIdMandato.length();
				strIdMandato= "00000000".substring(len) + strIdMandato;
				String id = sdf.format(new Date()) + strIdMandato;

				MandatoAS400Vo mandatos = new MandatoAS400Vo();

				mandatos.setCelular(celular);
				mandatos.setCodbanco(cuenta.getCodbanco());
				mandatos.setDvafi(cuenta.getDvafi());
				mandatos.setIdMandato(Long.parseLong(id));
				mandatos.setEmail(cuenta.getEmail());
				mandatos.setFechavig(new Date());
				mandatos.setNombre(cuenta.getNomafi());
				mandatos.setNumcuenta(cuenta.getNumcuenta());
				mandatos.setRutafi(cuenta.getRutafi());
				mandatos.setTelefono(telefono);
				mandatos.setIdtipcta(cuenta.getCodtipocuenta());
				mandatos.setIdtippro(dataUsuario.getIdCanal());
				mandatos.setObf002(new Date());
				mandatos.setObf003(sdh.parse(sdh.format(new Date())));
				mandatos.setFechater(null);
				mandatos.setSajkm94(autenticacion.getUsuario());


				mandatoas400Service.insertMandato(cuenta.getRutafi(), mandatos);

				logger.debug("Cuenta " + mandatos.getRutafi() + "," + " Guardada en AS400");

				// end
				vo.setIdMandato(Long.parseLong(id));

				try {
					ruta = reportService.generarReportejecutivo(vo);

					mailService.sendEmailMandato(cuenta.getEmail(), "Mandato único para transferencia - La Araucana",
							Utils.getbodyEjec(vo), cuenta.getRutafi() + "-" + cuenta.getDvafi(), ruta);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				sal.setCodigo(1);
				sal.setDescripcion("Ejecución exitosa.");
				cuentaWS.setLog(sal);
				cuentaWS.setIdMandato(id);
				return cuentaWS;
			}

		} catch (Exception e) {

			logger.error("Error ", e);

			sal.setCodigo(5);
			sal.setDescripcion("Error en la ejecución del servicio.");
		

		}
		cuentaWS.setLog(sal);
		return cuentaWS;
	}

	@Override
	@WebMethod(action = "http://servicios.laaraucana.cl/mandato/consultarMandato", operationName = "consultarMandato")
	public CuentaResponseWSVO consultarMandato(@WebParam(name = "autenticacion") @XmlElement(name="autenticacion", required=true) CredencialesVO autenticacion, 
			@WebParam(name = "cliente") @XmlElement(name="cliente", required=true) ParamConsultaVO param) {
		
	
		CuentaResponseWSVO cuenta= new CuentaResponseWSVO();
		SalidaVO sal= new SalidaVO();

		try {

			boolean usuarioValido = autenticacionWS(autenticacion);

			if (!usuarioValido) {

				sal.setCodigo(2);
				sal.setDescripcion("Usuario o Clave no válido");
			} else {

				if(param.getRutafi()==0 || param.getDvafi()==null || !Utils.validarRut(param.getRutafi() + "-" + param.getDvafi())) {
					sal.setCodigo(3);
					sal.setDescripcion("Parámetros no válidos.");
				}
				int rutCliente = param.getRutafi();
				List<MandatoAS400Vo> listaVigentes = mandatoas400Service.consultaMandatos(rutCliente);
				if(listaVigentes.size()>0){
					MandatoAS400Vo vigente= listaVigentes.get(0);

					CuentaDescripcionVO cuentaVO= new CuentaDescripcionVO();
					cuentaVO.setIdmandato(vigente.getIdMandato());
					cuentaVO.setNomafi(vigente.getNombre());
					cuentaVO.setCodigoBanco(vigente.getCodbanco());
					cuentaVO.setNumcuenta(vigente.getNumcuenta());
					cuentaVO.setTipoCuenta(vigente.getIdtipcta());
					cuentaVO.setTelefono(vigente.getTelefono());
					cuentaVO.setCelular(vigente.getCelular());
					cuentaVO.setEmail(vigente.getEmail());

					cuenta.setCuenta(cuentaVO);
					sal.setCodigo(1);
					sal.setDescripcion("Ejecución exitosa.");
					
				}else{
					sal.setCodigo(4);
					sal.setDescripcion("No existe mandato vigente para el RUT consultado.");
				}
			}

		} catch (Exception e) {

			logger.error("Error ", e);
			sal.setCodigo(5);
			sal.setDescripcion("Error en la ejecución del servicio.");

		}
		cuenta.setLog(sal);
		return cuenta;
	}

	@Override
	@WebMethod(action = "http://servicios.laaraucana.cl/mandato/revocarMandato", operationName = "revocarMandato")
	public MandatoResponseWSVO revocarMandato(@WebParam(name = "autenticacion") @XmlElement(name="autenticacion", required=true) CredencialesVO autenticacion, 
			@WebParam(name = "cliente") @XmlElement(name="cliente", required=true) ParamRevocarVO param) {
		MandatoResponseWSVO cuentaWS= new MandatoResponseWSVO();
		SalidaVO sal = new SalidaVO();

		try {

			boolean usuarioValido = autenticacionWS(autenticacion);

			if (!usuarioValido) {

				sal.setCodigo(2);
				sal.setDescripcion("Usuario o Clave no válido");
				logger.warn("Usuario o Clave no válido:" + usuarioValido);
			} else {

				if(param.getRutafi()==0 || param.getDvafi()==null || !Utils.validarRut(param.getRutafi() + "-" + param.getDvafi())) {

					sal = new SalidaVO();
					sal.setCodigo(3);
					sal.setDescripcion("Parámetros no válidos.");
					logger.warn("parámetros no válidos, Rut: " +  param.getRutafi() + "-" + param.getDvafi());
				}
				List<MandatoAS400Vo> listaVigentes= mandatoas400Service.consultaMandatos(param.getRutafi());
				
				if (listaVigentes.size()==0) {

					sal.setCodigo(4);
					sal.setDescripcion("No existe mandato vigente para el RUT consultado.");
					logger.info("No esxiste mandato vigente para Rut: " + param.getRutafi() + "-" + param.getDvafi());
				} else {
					long idMandato= listaVigentes.get(0).getIdMandato();
					logger.info("Eliminnando mandato vigente para Rut: " + param.getRutafi() + "-" + param.getDvafi());
					boolean resultado= mandatoas400Service.deleteMandato(param.getRutafi(), param.getMensaje());
					if(resultado){
						sal.setCodigo(1);
						sal.setDescripcion("Ejecución exitosa.");
						cuentaWS.setIdMandato(String.valueOf(idMandato));
						logger.info("Enviando notificación a correo : " + param.getEmail());
						logger.info("Mensaje error:" + param.getMensaje() );
						if(param.getEmail()!=null && !param.getEmail().equals("")){
							String body= Configuraciones.getConfig("envio.mail.revocacion.body");
							String subject= Configuraciones.getConfig("envio.mail.revocacion.subject");
							body= body.replaceAll("#nombre#", param.getNombre());
							body= body.replaceAll("#mensaje#", param.getMensaje());
							
							mailService.sendEmail(param.getEmail(), subject, body);
							logger.info("Mail enviado");
						}
					}else{
						sal.setCodigo(5);
						sal.setDescripcion("Error en la ejecución del servicio.");
					}

				}

			}

		} catch (Exception e) {

			logger.error("Error ", e);
			e.printStackTrace();
			sal.setCodigo(5);
			sal.setDescripcion("Error en la ejecución del servicio.");
		}
		cuentaWS.setLog(sal);
		return cuentaWS;
	}


}
