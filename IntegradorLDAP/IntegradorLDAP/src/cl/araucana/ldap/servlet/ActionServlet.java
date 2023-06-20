package cl.araucana.ldap.servlet;


import java.io.IOException;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import cl.araucana.ldap.api.AppRolesVO;
import cl.araucana.ldap.api.EmpresaVO;
import cl.araucana.ldap.api.ProxyLDAP;
import cl.araucana.ldap.api.RolUsuarioEmpVO;
import cl.araucana.ldap.api.UsuarioVO;
import cl.araucana.ldap.business.IngresaUsuarioLDAP;
import cl.araucana.ldap.dao.LdapDB2DAO;
import cl.araucana.ldap.jcrontab.EstadoCronta;
import cl.araucana.ldap.mail.EnviaMail;
import cl.araucana.ldap.mail.FormatoMail;
import cl.araucana.ldap.util.GeneratorXLS;
import cl.araucana.ldap.util.Utiles;
import cl.araucana.ldap.ws.ClienteSMSService;
import cl.laaraucana.sms.ws.MessageOutput;


public class ActionServlet extends HttpServlet {
	private static Logger log = Logger.getLogger(ActionServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 9061711754376149030L;
	private static String ESTADOCRONTA = "ECLDAP";
	private static String CONSULTAR_USUARIO = "CULDAP";
	private static String GUARDAR_USUARIO = "GULDAP";
	private static String CONSULTAR_EMPRESA = "CELDAP";
	private static String CONSULTAR_ANEXO= "CANXBD";
	private static String GUARDAR_EMPRESA = "GELDAP";
	private static String GUARDAR_ANEXO = "GANXBD";
	private static String CONSULTAR_APPS = "CALDAP";
	private static String CONSULTAR_APPROLES = "CARLDAP";
	private static String CONSULTAR_USUAPPROLES = "CUARLDAP";
	private static String GUARDAR_USUAPPROLES = "GUARLDAP";
	private static String GUARDAR_USUROLEMP = "GURELDAP";
	private static String GUARDAR_USUANXBD = "GUANXBD";
	private static String PROCESAR_PENDIENTES = "PPLDAP";
	private static String CONTABILIZAR_PENDIENTES = "CPLDAP";
	private static String REINYECTAR_ERRONEOS = "RELDAP";
	private static String CONSULTAR_ESTADISTICAS = "EELDAP";
	private static String ENCODE_LDAP = "ENCODE";
	private static String DECODE_LDAP = "DECODE";
	private static String SET_PASSWORD = "PASS";
	private static String DOWNLOAD_AUDITORIA = "DOWNAUDI";
	static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/mail");
	private String pathCronta="WEB-INF/classes/etc/estado_cronta.properties";

	private String sendmail="";
	private String mailerror="";
	private String mailusu="";
	private String mensajesms="", nuevosms="";
	
	public void init(){
		log.info("Parametros");
	
		//Se rescata variable para identificar si se debe enviar correo a cliente luego de crear cuenta LDAP
		sendmail = mailProperties.getString("app.envio.mail.cliente");
		//Se rescata lista de correo usuarios en caso de error
		mailerror = mailProperties.getString("app.mail.soporte.error");
		mailusu = mailProperties.getString("app.mail.usuario.error");
		
		nuevosms= mailProperties.getString("app.envio.sms.nuevo");
		mensajesms= mailProperties.getString("app.envio.sms.mensaje");
		log.info("Mensaje SMS:" + mensajesms);
		//List roles= LdapDB2DAO.obtenerRegistros("lista_roles_LDAP3000", null);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Properties propObj = new Properties();
		HttpSession session = request.getSession();
		
		String html = "";
		
		//Se recupera action a ejecutar
		String action = request.getParameter("action");
		log.info("Action:" + action);
		String app = request.getParameter("appid");
		String rol = request.getParameter("rolid");
		String rutuser= (String)session.getAttribute("rutuser");
		log.info("RUT Ejecutivo en sesion=" + rutuser);
		if (rutuser== null){
			request.getSession().invalidate();
			request.getRequestDispatcher("Iniciar").forward(request, response);
			return;
		}
		
		String rolsession= (String)request.getSession().getAttribute("rol");
		
		if (action.equals(ESTADOCRONTA)) {
			String estado="";
			String cronta = request.getParameter("cronta");
			JSONObject salida = new JSONObject();
			if(cronta==null || cronta.equals("")){
				estado= EstadoCronta.getEstado();
			}else{
				String ip= request.getRemoteHost();
				if(EstadoCronta.setEstado(cronta, ip)){
					estado=cronta;
					salida.put("save", "OK");
				}else{
					estado="";
				}
			}
			salida.put("estado_cronta", estado);
			html = salida.toJSONString();
			//html = html.toUpperCase();

		}
		else if (action.equals(CONSULTAR_APPROLES)) {
			JSONObject salida = new JSONObject();
			List roles= (List)ProxyLDAP.getAppRoles(app);
			if(rolsession.equals("Administrador")){
				roles.remove("Gerente");
			}
			salida.put("approles", roles);
			html = salida.toJSONString();
			//html = html.toUpperCase();

		}
		else if (action.equals(CONSULTAR_APPS)) {
			JSONObject salida = new JSONObject();
			//String rol_user= (String)session.getAttribute("rol");
			List apps= (List)ProxyLDAP.getApplications();
			salida.put("apps", apps);
			html = salida.toJSONString();
			//html = html.toUpperCase();

		}else if (action.equals(CONSULTAR_USUAPPROLES)) {
			JSONObject salida = new JSONObject();
			List apps= (List)ProxyLDAP.getInforUserAppRoles(app, rol);
			salida.put("usuapproles", apps);
			html = salida.toJSONString();
			//html = html.toUpperCase();

		}
else if (action.equals(GUARDAR_USUAPPROLES)) {
			
			String appID = request.getParameter("appid");
			String rolID = request.getParameter("rolid");
			String rutUsuario = request.getParameter("userid");
			rutUsuario= rutUsuario.replaceAll("\\.", "");
			//rutUsuario= Integer.parseInt(rutUsuario.split("-")[0]) + "-" + rutUsuario.split("-")[1];

			String estado = request.getParameter("estado");
			AppRolesVO approlVO= new AppRolesVO();
			approlVO.setAppID(appID);
			approlVO.setRolID(rolID);
			approlVO.setRutUsuario(rutUsuario);
			approlVO.setEstado(estado);
			
			JSONObject salida = new JSONObject();
			String resultado= IngresaUsuarioLDAP.grabarUsuarioRolApp(approlVO);
			if(resultado.equals("")){
				salida.put("estado", "1");
			}else{
				salida.put("estado", "0");
			}
//			se graba campos de auditoria
			//insertAuditoria(session.getAttribute("rutuser").toString(), rutUsuario, "GUARDAR", "RA", "", "");
			
			html = salida.toJSONString();
			//html = html.toUpperCase();

	}
else if (action.equals(GUARDAR_USUROLEMP)) {
	
	String appID = request.getParameter("appid");
	String rolID = request.getParameter("rolid");
	String rutUsuario = request.getParameter("userid");
	String rutEmpresa = request.getParameter("rutEmpresa");
	String estado = request.getParameter("estado");
	rutUsuario= rutUsuario.replaceAll("\\.", "");
	rutEmpresa= rutEmpresa.replaceAll("\\.", "");
	rutUsuario= Integer.parseInt(rutUsuario.split("-")[0]) + "-" + rutUsuario.split("-")[1];
	
	UsuarioVO persona= IngresaUsuarioLDAP.consultarUsuario(rutUsuario, null, null, true);
	String resultado="";
	int x=1;
	JSONObject salida = new JSONObject();
	if(persona.getEstado().equals("N")){
		salida.put("estado", "-1");
		salida.put("usuario", persona.toHashMap());
		salida.put("appid", appID);
		salida.put("rolID", rolID);
		session.setAttribute("rutemp", rutEmpresa);
	}else if(persona.getEstado().equals("E")){
		salida.put("estado", "-2");
		session.setAttribute("rutemp", rutEmpresa);
	}else{
		session.removeAttribute("rutemp");
		RolUsuarioEmpVO rolempVO= new RolUsuarioEmpVO();
		rolempVO.setApp(appID);
		rolempVO.setApprol(rolID);
		rolempVO.setRutEmpresa(rutEmpresa);
		rolempVO.setRutUsuario(rutUsuario);
		rolempVO.setEstado(estado);
		
		resultado= IngresaUsuarioLDAP.grabarUsuarioRolEmp(rolempVO);
		if(resultado.equals("")){
			salida.put("estado", "1");
		}else{
			salida.put("estado", "0");
		}
//		se graba campos de auditoria
		insertAuditoria(session.getAttribute("rutuser").toString(), rutUsuario, rutEmpresa, "RE", rolsession.substring(0, 3).toUpperCase(), "", "");
	}
	

	
	html = salida.toJSONString();
	//html = html.toUpperCase();

}

else if (action.equals(GUARDAR_USUANXBD)) {
	
	String rutUsuario = request.getParameter("userid");
	String rutEmpresa = request.getParameter("rutEmpresa");
	String estado = request.getParameter("estado");
	rutUsuario= rutUsuario.replaceAll("\\.", "");
	rutEmpresa= rutEmpresa.replaceAll("\\.", "");
	rutUsuario= Integer.parseInt(rutUsuario.split("-")[0]) + "-" + rutUsuario.split("-")[1];
	
	int resultado=0;
	if (estado.equals("E")){
		resultado= IngresaUsuarioLDAP.deleteUsuarioAnexo(rutEmpresa, rutUsuario);
	}else if (estado.equals("N")){
		resultado= IngresaUsuarioLDAP.insertUsuarioAnexo(rutEmpresa, rutUsuario);
//		se graba campos de auditoria
		insertAuditoria(session.getAttribute("rutuser").toString(), rutUsuario, rutEmpresa, "RA", rolsession.substring(0, 3).toUpperCase(), "", "");
	}
	
	int x=1;
	JSONObject salida = new JSONObject();
	salida.put("estado", String.valueOf(resultado));
	html = salida.toJSONString();
	//html = html.toUpperCase();

}
		
else if (action.equals(CONSULTAR_USUARIO)) {
	String idUser = request.getParameter("username");
	idUser= idUser.replaceAll("\\.", "");
	JSONObject salida = new JSONObject();
	UsuarioVO persona= IngresaUsuarioLDAP.consultarUsuario(idUser, app, rol, true);
	if(persona.getTelefono()!=null && !persona.getTelefono().equals("") && !persona.getTelefono().substring(0, 1).equals("9") && !persona.getTelefono().substring(0, 3).equals("569")){
		persona.setTelefono("");
	}
	salida.put("usuario", persona.toHashMap());
	salida.put("empresas_autorizadas", persona.getEmpresasAutorizadas());
	//se graba campos de auditoria
	//insertAuditoria(session.getAttribute("rutuser").toString(), idUser, "CONSULTAR", "U", "", "");
	html = salida.toJSONString();
	//html = html.toUpperCase();

}
		
else if (action.equals(GUARDAR_USUARIO)) {
			
			String username = request.getParameter("username");
			username= username.replaceAll("\\.", "");
			username= Integer.parseInt(username.split("-")[0]) + "-" + username.split("-")[1];
			
			String nombres = request.getParameter("nombres");
			String apellidoPaterno = request.getParameter("apellido_paterno");
			String apellidoMaterno = request.getParameter("apellido_materno");
			String telefono = request.getParameter("telefono");
			String email = request.getParameter("email");
			String nuevo = request.getParameter("nuevo");
			//String sexo = request.getParameter("sexo");
			//int blocked = Integer.parseInt(request.getParameter("blocked"));
			//String question = request.getParameter("question");
			//String answer = request.getParameter("answer");
			
			UsuarioVO usuario= new UsuarioVO();
			usuario.setUsername(username);
			usuario.setNombres(nombres);
			usuario.setApellidoPaterno(apellidoPaterno);
			usuario.setApellidoMaterno(apellidoMaterno);
			usuario.setTelefono(telefono);
			usuario.setEmail(email);
			usuario.setEstado("M");
			if(nuevo.equals("true")){
				String password= "000" + String.valueOf(Math.round(Math.random()* 9999));
				password= password.substring(password.length()-4);
				usuario.setClave(password);
				usuario.setEstado("N");
			}
			
			/*usuario.setSexo(sexo);
			if(blocked==1){
				usuario.setBlocked(true);
			}else{
				usuario.setBlocked(false);
			}
			usuario.setQuestion(question);
			usuario.setAnswer(answer);
			*/
			JSONObject salida = new JSONObject();
			String resultado= IngresaUsuarioLDAP.grabarUsuario(usuario);
			boolean e_sms=false, e_email=false;
			if(resultado!=null && resultado.equals("")){
				salida.put("estado", "1");
				String rutemp= (String)session.getAttribute("rutemp");
				if (rutemp== null){
					rutemp="";
				}
				salida.put("rutempresa", rutemp);
				salida.put("username", username);
				if( nuevo.equals("true")){
					if( usuario.getEmail()!=null && !usuario.getEmail().equals("")){
						EnviaMail.enviarMail("Cuenta creada en La Araucana. ",usuario.getEmail() , null,FormatoMail.obtenerTextoMailLdapOK(usuario.getUsername(), usuario.getClave(), usuario.getNombres(), usuario.getApellidoPaterno()));
					}
					if( usuario.getTelefono()!=null && !usuario.getTelefono().equals("")){
						//ServicioSMSInternoProxy proxy= new ServicioSMSInternoProxy();
						//proxy.setEndpoint(urlsms);
						String msgsms= nuevosms.replaceAll("#clave#", usuario.getClave());
						//e_sms= proxy.enviarSMS(usuario.getTelefono(), msgsms, "1", codigo_negocio);
						ClienteSMSService clientesms= new ClienteSMSService();
						MessageOutput output= clientesms.sendMesage(username, usuario.getTelefono(), msgsms);
						if(output!=null & output.getStatusCode().equals("SENT")){
							log.info("Mensaje SMS enviado correctamente");
						}else{
							String mensaje="Respuesta null en servicio SMS Interno para celular " + usuario.getTelefono();
							if(output!=null){
								mensaje= "StatusCode=" + output.getStatusCode() + ", descripción=" + output.getStatusDescription() + ", celular=" + usuario.getTelefono();
							}
							EnviaMail.enviarMail("Error en Servicio SMS ",mailusu , null,FormatoMail.obtenerTextoMailSMSError(username, mensaje));
						}
					}
				}
			}else{
				salida.put("estado", "0");
			}
			//se graba campos de auditoria
			insertAuditoria(session.getAttribute("rutuser").toString(), username, "", "CP", rolsession.substring(0, 3).toUpperCase(),  (nuevo.equals("true"))?((e_sms)?"1":"0"):"", (nuevo.equals("true"))?((e_email)?"1":"0"):"");
			html = salida.toJSONString();
			//html = html.toUpperCase();

		}
		
		else if (action.equals(CONSULTAR_EMPRESA)) {
			String idEmpresa = request.getParameter("rutempresa");
			idEmpresa= idEmpresa.replaceAll("\\.", "");
			JSONObject salida = new JSONObject();
			EmpresaVO empresa= IngresaUsuarioLDAP.consultarEmpresa(idEmpresa, app, rol);
			salida.put("empresa", empresa.toHashMap());
			salida.put("usuarios_registrados", empresa.getUsers());
			
//			se graba campos de auditoria
			//insertAuditoria(session.getAttribute("rutuser").toString(), idEmpresa, "CONSULTAR", "E", "", "");
			
			html = salida.toJSONString();
			//html = html.toUpperCase();

		}
		
		else if (action.equals(CONSULTAR_ANEXO)) {
			String idEmpresa = request.getParameter("rutempresa");
			idEmpresa= idEmpresa.replaceAll("\\.", "");
			JSONObject salida = new JSONObject();
			EmpresaVO empresa= IngresaUsuarioLDAP.consultarAnexo(idEmpresa);
			salida.put("empresa", empresa.toHashMap());
			salida.put("usuarios_registrados", empresa.getUsers());
			
//			se graba campos de auditoria
			//insertAuditoria(session.getAttribute("rutuser").toString(), idEmpresa, "CONSULTAR", "E", "", "");
			
			html = salida.toJSONString();
			//html = html.toUpperCase();

		}
		
		else if (action.equals(GUARDAR_EMPRESA)) {
			
			String rutEmpresa = request.getParameter("rutempresa");
			rutEmpresa= rutEmpresa.replaceAll("\\.", "");
			rutEmpresa= Integer.parseInt(rutEmpresa.split("-")[0]) + "-" + rutEmpresa.split("-")[1];
			
			String razonSocialEmpresa = request.getParameter("razonSocialEmpresa");
			/*int afiliada = Integer.parseInt(request.getParameter("afiliada"));
			String holding = request.getParameter("holding");
			String tipo = request.getParameter("tipo");
			String codigoActividadEconomica = request.getParameter("codigoActividadEconomica");
			String actividadEconomica = request.getParameter("actividadEconomica");
			String direccion = request.getParameter("direccion");
			String comuna = request.getParameter("comuna");
			String ciudad = request.getParameter("ciudad");
			String region = request.getParameter("region");
			String rutRepLegal = request.getParameter("rutRepLegal");
			String nombreRepLegal = request.getParameter("nombreRepLegal");
			String apellidoPaternoRepLegal = request.getParameter("apellidoPaternoRepLegal");
			String apellidoMaternoRepLegal = request.getParameter("apellidoMaternoRepLegal");
			String fono = request.getParameter("fono");
			String email = request.getParameter("email");
			*/
			EmpresaVO empresa= new EmpresaVO();
			empresa.setRutEmpresa(rutEmpresa);
			empresa.setRazonSocialEmpresa(razonSocialEmpresa);
			/*empresa.setAfiliada(afiliada);
			empresa.setHolding(holding);
			empresa.setTipo(tipo);
			empresa.setCodigoActividadEconomica(codigoActividadEconomica);
			empresa.setActividadEconomica(actividadEconomica);
			empresa.setDireccion(direccion);
			empresa.setComuna(comuna);
			empresa.setCiudad(ciudad);
			empresa.setRegion(region);
			empresa.setRutRepLegal(rutRepLegal);
			empresa.setNombreRepLegal(nombreRepLegal);
			empresa.setApellidoPaternoRepLegal(apellidoPaternoRepLegal);
			empresa.setApellidoMaternoRepLegal(apellidoMaternoRepLegal);
			*/
			
			JSONObject salida = new JSONObject();
			String resultado= IngresaUsuarioLDAP.grabarEmpresa(empresa);
			if(resultado.equals("")){
				salida.put("estado", "1");
			}else{
				salida.put("estado", "0");
			}
//			se graba campos de auditoria
			//insertAuditoria(session.getAttribute("rutuser").toString(), rutEmpresa, "GUARDAR", "E", "", "");
			
			html = salida.toJSONString();
			//html = html.toUpperCase();

		}
		
		else if (action.equals(PROCESAR_PENDIENTES)) {
			
			JSONObject salida = new JSONObject();
			
			HashMap dataUsuarios = IngresaUsuarioLDAP.procesarUsuarios(sendmail, mailerror);
			log.info("data proceso usuarios: "+dataUsuarios);
			salida.put("procesoU", dataUsuarios);
			HashMap dataEmpresas = IngresaUsuarioLDAP.procesarEmpresas(mailerror);
			log.info("data proceso empresas: "+dataEmpresas);
			salida.put("procesoE", dataEmpresas);
			HashMap dataRoles = IngresaUsuarioLDAP.procesarRolesEnterprise(mailerror);
			log.info("data proceso roles: "+dataRoles);
			salida.put("procesoR", dataRoles);
			HashMap dataAppRoles = IngresaUsuarioLDAP.procesarAppRoles(mailerror);
			log.info("data proceso app-roles: "+dataAppRoles);
			salida.put("procesoA", dataAppRoles);
			
//			se graba campos de auditoria
			//insertAuditoria(session.getAttribute("rutuser").toString(), " ", "PROCESAR", "UER", "", "");
			
			html = salida.toJSONString();
		}
		
		else if (action.equals(CONTABILIZAR_PENDIENTES)) {
			
			JSONObject salida = new JSONObject();
			
			List data = IngresaUsuarioLDAP.consultarRegistrosPendientes();
			salida.put("pendientes", data);
			HashMap ultimo= IngresaUsuarioLDAP.consultarDato("custom.ultima_ejecucion_LDAP1000", null);
			salida.put("ultimo", ultimo);
			log.info("salida: "+salida);
			html = salida.toJSONString();
		}
		
		else if (action.equals(REINYECTAR_ERRONEOS)) {
			int resultado=0;
			JSONObject salida = new JSONObject();
			resultado= LdapDB2DAO.ejecutarUpdate("custom.reinyectar_erroneos_LDAP1000", null);
			resultado+= LdapDB2DAO.ejecutarUpdate("custom.reinyectar_erroneos_LDAP2000", null);
			resultado+= LdapDB2DAO.ejecutarUpdate("custom.reinyectar_erroneos_LDAP2500", null);
			resultado+= LdapDB2DAO.ejecutarUpdate("custom.reinyectar_erroneos_LDAP3500", null);
			salida.put("resultado", new Integer(resultado));
//			se graba campos de auditoria
			//insertAuditoria(session.getAttribute("rutuser").toString(), " ", "REINYECTAR", "UER", "", "");
			html = salida.toJSONString();
		}
		else if (action.equals(CONSULTAR_ESTADISTICAS)) {
			JSONObject salida = new JSONObject();
			HashMap meses_estadisticas= (HashMap)LdapDB2DAO.obtenerRegistro("custom.meses_estadisticas_ldap", null);
			List meses= new ArrayList();
			HashMap mes =null;
			for (int i = 12; i >= 0; i--) {
				mes= new HashMap();
				mes.put("MES" + i, meses_estadisticas.get("MES"+i).toString());
				meses.add(mes);
			}
			
			salida.put("meses", meses);
			List estadisticas1000= (List)LdapDB2DAO.obtenerRegistros("custom.estadisticas_LDAP1000", null);
			List estadisticas2000= (List)LdapDB2DAO.obtenerRegistros("custom.estadisticas_LDAP2000", null);
			List estadisticas2500= (List)LdapDB2DAO.obtenerRegistros("custom.estadisticas_LDAP2500", null);
			List estadisticas3500= (List)LdapDB2DAO.obtenerRegistros("custom.estadisticas_LDAP3500", null);
			salida.put("estadisticas_usuarios", estadisticas1000);
			salida.put("estadisticas_empresas", estadisticas2000);
			salida.put("estadisticas_roles_empresas", estadisticas2500);
			salida.put("estadisticas_approles", estadisticas3500);
			html = salida.toJSONString();
			//html = html.toUpperCase();

		}else if (action.equals(ENCODE_LDAP)) {
			String username = request.getParameter("username");
			String password= request.getParameter("password");
			String base= request.getParameter("base");
			//System.out.println("Base" + base);
			JSONObject salida = new JSONObject();
			if(base.equals("128")){
				salida.put("credential", Utiles.encode128(username, password) );
			}else{
				salida.put("credential", Utiles.encode(username, password) );
			}
			html = salida.toJSONString();
		}else if (action.equals(DECODE_LDAP)) {
			String credential = request.getParameter("credential");
			String base= request.getParameter("base");
			//System.out.println("Base" + base);
			JSONObject salida = new JSONObject();
			if(base.equals("128")){
				salida.put("credential", Utiles.decode128(credential) );
			}else{
				salida.put("credential", Utiles.decode(credential) );
			}
			html = salida.toJSONString();
		}else if (action.equals(SET_PASSWORD)) {
			String username = request.getParameter("username");
			String clave = request.getParameter("clave");
			String nombres = request.getParameter("nombres");
			String apellidoPaterno = request.getParameter("apellido_paterno");
			
			username= username.replaceAll("\\.", "");
			String password="";
			if(clave!=null && !clave.equals("")){
				password= clave;
			}else{
				password= "000" + String.valueOf(Math.round(Math.random()* 9999));
				password= password.substring(password.length()-4);
			}
			JSONObject salida = new JSONObject();
			log.info("Clave a asignar:" + password);
			String resultado= ProxyLDAP.changePassword(username, password);
			boolean sms=false, email=false;
			String celular= request.getParameter("telefono");
			if (resultado==null && celular != null && !celular.equals("")){
				//ServicioSMSInternoProxy proxy= new ServicioSMSInternoProxy();
				//log.info("Codigo Negocio:" + codigo_negocio);
				//proxy.setEndpoint(urlsms);
				//log.info("Endpoint SMS:" + proxy.getEndpoint());
				String msgsms= mensajesms.replaceAll("#clave#", password);
				log.info("clave en SMS:" + password);
				
				//sms= proxy.enviarSMS(celular, msgsms, "1", codigo_negocio);
				ClienteSMSService clientesms= new ClienteSMSService();
				MessageOutput output=null;
				try {
					output = clientesms.sendMesage(username, celular, msgsms);
				} catch (Exception e) {
					log.warn("Error en servicio sms: " + e.getMessage());
					//e.printStackTrace();
				}
				if(output!=null & output.getStatusCode().equals("SENT")){
					log.info("Mensaje SMS enviado correctamente");
					sms=true;
				}else{
					String mensaje="Respuesta null en servicio SMS Interno , celular=" + celular;
					if(output!=null){
						mensaje= "StatusCode=" + output.getStatusCode() + ", descripción=" + output.getStatusDescription()+ ", celular=" + celular;
					}
					EnviaMail.enviarMail("Error en Servicio SMS ",mailusu , null,FormatoMail.obtenerTextoMailSMSError(username, mensaje));
				}

				log.info("Respuesta SMS:" + sms);
			}
			String mailusuario= request.getParameter("email");
			if(mailusuario!= null && !mailusuario.trim().equals("") && resultado==null){
				email= EnviaMail.enviarMail("Cambio de Clave La Araucana. ",mailusuario , null,FormatoMail.obtenerTextoMailCambioLdapOK(username, password, nombres, apellidoPaterno));
			}
			//Se verifica si se modificó email o celular para guardar cambios
			String telefono_old = request.getParameter("telefono_old");
			String email_old = request.getParameter("email_old");
			telefono_old = telefono_old==null?"":telefono_old;
			email_old = email_old==null?"":email_old;
			if(!email_old.equals(mailusuario) || !telefono_old.equals(celular)){
				log.info("Se detecta cambio en email o celular, se invoca guardarUsuario");
				guardarUsuario(request, false);
			}else{
				//solo se actualiza bitácora
				guardarUsuario(request, true);
			}
			
			if(resultado==null && (sms || email)){
				salida.put("save", "1");
			}else{
				salida.put("save", "0");
			}
			
//			se graba campos de auditoria
			log.info("grabando auditoria PASSWORD");
			insertAuditoria(session.getAttribute("rutuser").toString(), username, "", "CP", rolsession.substring(0, 3).toUpperCase(), (sms)?"1":"0", (email)?"1":"0");
			
			html = salida.toJSONString();
		}else if (action.equals(DOWNLOAD_AUDITORIA)) {
			String diasAuditoria = request.getParameter("diasAuditoria");
			Date rango= Utiles.sumDays(new Date(), Integer.parseInt(diasAuditoria)*-1);
			List auditoria= (List)LdapDB2DAO.obtenerRegistros("custom.infoAuditoria", rango);
			
			//String carpeta="C:/tmp/";
			String filename= "Auditoria Usuarios Fecha " + Utiles.dateToStringSAP(rango) + ".csv";
			//Generando la salida
			log.info("Nombre archivo:" + filename);
			
			//Generando la salida
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			response.setContentType("application/text");
			response.setHeader("Content-Disposition", "inline; filename=" + filename);
			ServletOutputStream out = response.getOutputStream();
			PrintStream flujo= new PrintStream(out);
			GeneratorXLS xls= new GeneratorXLS(flujo);

			//Configurando columnas a desplegar y titulos de estas.
			String[] columnas={"rutEjecutivo", "rutUsuario", "rutEmpresa", "accion", "tipoEjecutivo", "envioSMS", "envioEMAIL", "fecha", "hora"};
			String[] titulos={"RUT Ejecutivo", "RUT Usuario", "RUT Empresa", "Accion", "Tipo Ejecutivo", "envio SMS", "envio EMAIL", "Fecha Creacion", "Hora Creacion"};

			xls.generarCSVfromCollection(auditoria, columnas, titulos);
			log.info("Archivo ha sido generado.");
			//Cerrando salida
			out.flush();
			out.close();
			
		}
		if (!action.equals(DOWNLOAD_AUDITORIA)){
			registrarSalida(response, html);
		}
	}

	public void registrarSalida(HttpServletResponse response, String result) {
		response.setCharacterEncoding("iso-8859-1");
		response.setContentType("text/html");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean guardarUsuario(HttpServletRequest request, boolean solobitacora){
		boolean salida=false;
		String username = request.getParameter("username");
		username= username.replaceAll("\\.", "");
		String nombres = request.getParameter("nombres");
		nombres= nombres==null ? "" : nombres;
		String apellidoPaterno = request.getParameter("apellido_paterno");
		apellidoPaterno= apellidoPaterno==null ? "" : apellidoPaterno;
		String apellidoMaterno = request.getParameter("apellido_materno");
		apellidoMaterno= apellidoMaterno==null ? "" : apellidoMaterno;
		String telefono = request.getParameter("telefono");
		String email = request.getParameter("email");
		
		UsuarioVO usuario= new UsuarioVO();
		usuario.setUsername(username);
		usuario.setNombres(nombres);
		usuario.setApellidoPaterno(apellidoPaterno);
		usuario.setApellidoMaterno(apellidoMaterno);
		usuario.setTelefono(telefono);
		usuario.setEmail(email);
		usuario.setEstado("M");
		
		String resultado=null;
		if(solobitacora){
			resultado= IngresaUsuarioLDAP.updateBitacoraClave(usuario);
		}else{
			resultado= IngresaUsuarioLDAP.grabarUsuario(usuario);
		}
		
		if(resultado.equals("")){
			salida= true;
		}
		//se graba campos de auditoria
		//insertAuditoria(request.getSession().getAttribute("rutuser").toString(), username, "", "CP", "U",  "", "");
		return salida;
	}
	
	public void insertAuditoria(String rutEjecutivo, String username, String rutEmpresa, String action, String tipo, String sms, String email){
		try {
			HashMap auditoria= new HashMap();
			auditoria.put("username", username);
			auditoria.put("rutejecutivo", rutEjecutivo);
			auditoria.put("rutempresa", rutEmpresa);
			auditoria.put("accion", action);
			auditoria.put("tipo", tipo);
			auditoria.put("sms", sms);
			auditoria.put("email", email);
			log.info("Params auditoria, username=" + username + ", rutEjecutivo" + rutEjecutivo + ", action=" + action + ", tipo=" + tipo + ", sms=" + sms + ", email= " + email);
			if(rutEjecutivo!=null){
				int resultado= IngresaUsuarioLDAP.grabarAuditoria("custom.insertLDAP4500", auditoria);
				log.info(">>Resultado grabacion Auditoria:" + resultado);
			}
		} catch (Exception e) {
			log("Error al grabar tabla auditoria");
			e.printStackTrace();
		}
	}
	
}
