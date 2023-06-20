/**
 * 
 */
package cl.araucana.ldap.ws;

import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.axis.AxisFault;
import org.apache.log4j.Logger;

import cl.araucana.ldap.api.UsuarioVO;
import cl.araucana.ldap.business.IngresaUsuarioLDAP;
import cl.araucana.ldap.mail.EnviaMail;
import cl.araucana.ldap.mail.FormatoMail;
import cl.araucana.ldap.ws.util.TokenFactory;
import cl.araucana.ldap.ws.util.Utiles;
import cl.araucana.ldap.ws.vo.CredentialWS;
import cl.araucana.ldap.ws.vo.NotificarWS;
import cl.araucana.ldap.ws.vo.RequestPasswordWS;
import cl.araucana.ldap.ws.vo.RequestUsuarioWS;
import cl.araucana.ldap.ws.vo.ResponseUsuarioWS;
import cl.laaraucana.sms.ws.MessageOutput;


/**
 * @author J-Factory
 *
 */
@WebService( portName = "usuariosLDAPPort",
serviceName = "usuariosLDAPService",
targetNamespace = "http://servicios.laaraucana.cl/usuariosLDAP"
)
public class RegistrarUsuarioImpl implements RegistrarUsuario {
	private static ResourceBundle credentials = ResourceBundle.getBundle("etc/cred_ws");
	private static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/mail");
	private static final int  ERROR_TOKEN= -1;
	private static final int  ERROR_USER= 0;
	private static final int  OK= 1;
	@Resource
    private WebServiceContext wsCtx;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	/* (non-Javadoc)
	 * @see cl.araucana.ldap.ws.GestionRolesAPP#autenticacionWS(cl.araucana.ldap.ws.vo.CredentialWS)
	 */

	@WebMethod(action="http://servicios.laaraucana.cl/usuariosLDAP/autenticacionUWS",  operationName="autenticacionUWS")
	public String autenticacionUWS(@WebParam(name="credentials") @XmlElement(name="credentials", required=true) CredentialWS param){
		MessageContext mctx = wsCtx.getMessageContext();
		log.info("EN autenticacionUWS");
		
		String usuario_settings= credentials.getString("credential.userName");
		String password_settings= credentials.getString("credential.password");
		String usuario_param= param.getUser();
		String password_param= param.getPassword();
		if(usuario_param.equals(usuario_settings) && password_param.equals(password_settings)){
			String remoteip= ((HttpServletRequest)(wsCtx.getMessageContext().get(MessageContext.SERVLET_REQUEST))).getRemoteAddr();
			String token_encode= Utiles.generaToken(remoteip, param.getUser());
			String fecha= Utiles.getFecha();
			TokenFactory.getInstance().addToken(param.getUser(), token_encode);
			return token_encode;

		}else{
			log.warn("Usuario o Contraseña No Válido");
		}
		return "Usuario o Contraseña No Válido";
		
	}

	/* (non-Javadoc)
	 * @see cl.araucana.ldap.ws.GestionRolesAPP#getRoles_inApp(java.lang.String, cl.araucana.ldap.ws.vo.RequestRolesWS)
	 */
	@WebMethod(action="http://servicios.laaraucana.cl/usuariosLDAP/addUsuario",  operationName="addUsuario")
	public ResponseUsuarioWS addUsuario(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="datos") @XmlElement(name="datos", required=true) RequestUsuarioWS request, @WebParam(name="notificar") @XmlElement(name="notificar", required=true) NotificarWS notificar){
		ResponseUsuarioWS responseWS= new ResponseUsuarioWS();
		if(!isValidToken(token).equals("")){
			//Seteando parametros
			UsuarioVO usuario= new UsuarioVO();
			usuario.setUsername(request.getRut());
			String[] nombres= request.getNombre().split(" ");
			
			//Descomponiendo nombre en 2 para setar nombre y apellido paterno
			int medio= Math.round(nombres.length/2);
			String nombre="";
			for (int i = 0; i < medio; i++) {
				nombre+= nombres[i] + " ";
			}
			String apellidos="";
			for (int i = medio; i < nombres.length; i++) {
				apellidos+= nombres[i] + " ";
			}
			usuario.setNombres(nombre.trim());
			if(apellidos.indexOf(" ")>0){
				usuario.setApellidoPaterno(apellidos.substring(0, apellidos.indexOf(" ")).trim());
				usuario.setApellidoMaterno(apellidos.substring(apellidos.indexOf(" ")+1).trim());
			}else{
				usuario.setApellidoPaterno(apellidos);
			}
			
			usuario.setOrigen("WS_GC");
			usuario.setTelefono(request.getCelular());
			usuario.setEmail(request.getEmail());
			String clave= "000" + String.valueOf(Math.round(Math.random()* 9999));
			clave= clave.substring(clave.length()-4);
			usuario.setClave(String.valueOf(clave));
			
			//creando usuario en LDAP
			log.warn("Registrando usuario: " + usuario.getUsername());
			String resultado= IngresaUsuarioLDAP.grabarUsuario(usuario);
			
			//Notificando a usuario por correo y mensaje SMS
			boolean e_sms=false, e_email=false;
			String nuevosms= mailProperties.getString("app.envio.sms.nuevo");
			//String codigo_negocio= mailProperties.getString("app.envio.sms.codnegocio");
			if(resultado!=null && resultado.equals("")){
				log.info("Notificando clave a usuario" + usuario.getUsername());
				if(notificar.getMail().equalsIgnoreCase("x") || notificar.getMail().equalsIgnoreCase("si") || notificar.getMail().equals("1")){
					if( request.getEmail()!=null && !request.getEmail().trim().equals("")){
						e_email= EnviaMail.enviarMail("Cuenta creada en La Araucana. ",usuario.getEmail() , null,FormatoMail.obtenerTextoMailLdapOK(usuario.getUsername(), usuario.getClave(), usuario.getNombres(), usuario.getApellidoPaterno()));
						if(!e_email){
							log.warn("No se pudo enviar correo a usuario" + usuario.getUsername());
						}
					}
				}
				if(notificar.getSms().equalsIgnoreCase("x") || notificar.getSms().equalsIgnoreCase("si") || notificar.getSms().equals("1")){
					if( usuario.getTelefono()!=null && !usuario.getTelefono().equals("")){
						//ServicioSMSInternoProxy proxy= new ServicioSMSInternoProxy();
						//proxy.setEndpoint(urlsms);
						String msgsms= nuevosms.replaceAll("#clave#", usuario.getClave());

						ClienteSMSService clientesms= new ClienteSMSService();
						MessageOutput output= clientesms.sendMesage(usuario.getUsername(), usuario.getTelefono(), msgsms);
						if(output!=null & output.getStatusCode().equals("SENT")){
							e_sms=true;
							log.info("Mensaje SMS enviado correctamente");
						}

						/*try {
							e_sms= proxy.enviarSMS(usuario.getTelefono(), msgsms, "1", codigo_negocio);
						} catch (RemoteException e) {
							log.warn("No se pudo enviar mensaje SMS a usuario" + usuario.getUsername());
						}*/
					}
				}
				responseWS.setEstado(OK);
			}else{
				responseWS.setEstado(ERROR_USER);
			}
			responseWS.setCodigo_respuesta(OK);
		}else{
			responseWS.setCodigo_respuesta(ERROR_TOKEN);
			responseWS.setEstado(ERROR_USER);
		}
		return responseWS;
	}

	/* (non-Javadoc)
	 * @see cl.araucana.ldap.ws.GestionRolesAPP#getUsers_inRole(java.lang.String, cl.araucana.ldap.ws.vo.RequestUsersWS)
	 */
	@WebMethod(action="http://servicios.laaraucana.cl/usuariosLDAP/delUsuario",  operationName="delUsuario")
	public ResponseUsuarioWS delUsuario(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="rut") @XmlElement(name="rut", required=true) String rut) {

		ResponseUsuarioWS responseWS= new ResponseUsuarioWS();
		if(!isValidToken(token).equals("")){ 
			
			//eliminando usuario en LDAP
			log.warn("Eliminando usuario: " + rut);
			String respuesta= IngresaUsuarioLDAP.eliminarUsuario(rut);
			if(respuesta.equals("")){
				responseWS.setEstado(OK);
			}else{
				responseWS.setEstado(ERROR_USER);
			}
			responseWS.setCodigo_respuesta(OK);
		}else{
			responseWS.setCodigo_respuesta(ERROR_TOKEN);
			responseWS.setEstado(ERROR_USER);
		}
		
		return responseWS;
	}
	
	@WebMethod(action="http://servicios.laaraucana.cl/usuariosLDAP/changePassword",  operationName="changePassword")
	public ResponseUsuarioWS changePassword(@WebParam(name="token") @XmlElement(name="token", required=true) String token, @WebParam(name="request") @XmlElement(name="request", required=true) RequestPasswordWS request, @WebParam(name="notificar") @XmlElement(name="notificar", required=true) NotificarWS notificar) {

		ResponseUsuarioWS responseWS= new ResponseUsuarioWS();
		if(!isValidToken(token).equals("")){ 
			
			//Seteando parametros
			UsuarioVO usuario= new UsuarioVO();
			usuario.setUsername(request.getRut());
			
			String clave= "000" + String.valueOf(Math.round(Math.random()* 9999));
			clave= clave.substring(clave.length()-4);
			usuario.setClave(String.valueOf(clave));
			usuario.setEmail(request.getEmail());
			usuario.setTelefono(request.getCelular());
			
			//cabiando password en LDAP
			log.warn("Registrando usuario: " + usuario.getUsername());
			String resultado= IngresaUsuarioLDAP.grabarUsuario(usuario);
			
			//Notificando a usuario por correo y mensaje SMS
			boolean e_sms=false, e_email=false;
			String nuevosms= mailProperties.getString("app.envio.sms.nuevo");
			if(resultado==null){
				log.info("Error de camcio password retorno null");
				resultado="";
			}
			if(resultado.equals("")){
				log.info("Notificando clave a usuario" + usuario.getUsername());
				if(notificar.getMail().equalsIgnoreCase("x") || notificar.getMail().equalsIgnoreCase("si") || notificar.getMail().equals("1")){
					if( request.getEmail()!=null && !request.getEmail().trim().equals("")){
						EnviaMail.enviarMail("Recuperación clave La Araucana. ",usuario.getEmail() , null,FormatoMail.obtenerTextoMailCambioLdapOK(usuario.getUsername(), usuario.getClave(), usuario.getNombres(), usuario.getApellidoPaterno()));
						if(!e_email){
							log.warn("No se pudo enviar correo a usuario" + usuario.getUsername());
						}
					}
				}
				if(notificar.getSms().equalsIgnoreCase("x") || notificar.getSms().equalsIgnoreCase("si") || notificar.getSms().equals("1")){
					if( usuario.getTelefono()!=null && !usuario.getTelefono().equals("")){
						//ServicioSMSInternoProxy proxy= new ServicioSMSInternoProxy();
						//proxy.setEndpoint(urlsms);
						String msgsms= nuevosms.replaceAll("#clave#", usuario.getClave());
						ClienteSMSService clientesms= new ClienteSMSService();
						MessageOutput output= clientesms.sendMesage(usuario.getUsername(), usuario.getTelefono(), msgsms);
						if(output!=null & output.getStatusCode().equals("SENT")){
							e_sms=true;
							log.info("Mensaje SMS enviado correctamente");
						}
						
						/*try {
							e_sms= proxy.enviarSMS(usuario.getTelefono(), msgsms, "1", codigo_negocio);
						} catch (RemoteException e) {
							log.warn("No se pudo enviar mensaje SMS a usuario" + usuario.getUsername());
						}*/
					}
				}
				responseWS.setEstado(OK);
			}else{
				responseWS.setEstado(ERROR_USER);
			}
			responseWS.setCodigo_respuesta(OK);
		}else{
			responseWS.setCodigo_respuesta(ERROR_TOKEN);
			responseWS.setEstado(ERROR_USER);
		}
		return responseWS;
	}
	
	@WebMethod(exclude= true)
	private String isValidToken(String token){
		String usuario="";
		try {
			String[] data= Utiles.decodeToken(token);
			String remoteip= ((HttpServletRequest)(wsCtx.getMessageContext().get(MessageContext.SERVLET_REQUEST))).getRemoteAddr();
			
			String tokenFactory= TokenFactory.getInstance().getToken(data[1]);
			String dataTokenFacory[]= Utiles.decodeToken(tokenFactory);
			
			String fecha= dataTokenFacory[2];
			usuario=dataTokenFacory[1];
			if(fecha!=null){
				if(Utiles.validaFecha(fecha)){
					if(remoteip.equals(data[0])){
						return usuario;
					}
				}else{
					 TokenFactory.getInstance().delToken(data[0]);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
