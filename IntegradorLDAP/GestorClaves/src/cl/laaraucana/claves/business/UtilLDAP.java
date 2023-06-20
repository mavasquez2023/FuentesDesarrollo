/**
 * 
 */
package cl.laaraucana.claves.business;

import java.rmi.RemoteException;

import org.apache.axis.AxisFault;

import cl.laaraucana.claves.dao.vo.NotificaVO;
import cl.laaraucana.claves.dao.vo.RegistroVO;
import cl.laaraucana.claves.utils.Configuraciones;
import cl.laaraucana.claves.utils.Constantes;
import cl.laaraucana.servicios.usuariosLDAP.CredentialWS;
import cl.laaraucana.servicios.usuariosLDAP.RequestNotificarWS;
import cl.laaraucana.servicios.usuariosLDAP.RequestPasswordWS;
import cl.laaraucana.servicios.usuariosLDAP.RequestUsuarioWS;
import cl.laaraucana.servicios.usuariosLDAP.UsuariosLDAP;
import cl.laaraucana.servicios.usuariosLDAP.UsuariosLDAPPortBindingStub;

/**
 * @author IBM Software Factory
 *
 */
public class UtilLDAP {
	public static UsuariosLDAP addUsuario(RegistroVO registro, NotificaVO notifica){
		//Registrar afiliado en LDAP
		UsuariosLDAP resultado=null;
		try {
			String usuario= Configuraciones.getConfig("ldap.credentials.user");
			String password= Configuraciones.getConfig("ldap.credentials.password");
			String ep= Configuraciones.getConfig("ep.clavesLDAP");
			int timeout= Integer.parseInt(Configuraciones.getConfig("ldap.timeout"));
			
			CredentialWS credentials= new CredentialWS();
			credentials.setUser(usuario);
			credentials.setPassword(password);
			
			UsuariosLDAPPortBindingStub stub= new UsuariosLDAPPortBindingStub();
			
			stub._setProperty(UsuariosLDAPPortBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
			String token= stub.autenticacionUWS(credentials);
			RequestUsuarioWS datos= new RequestUsuarioWS();
			datos.setRut(registro.getRut_DV());
			datos.setNombre(registro.getNombre());
			datos.setCelular(registro.getCelular());
			datos.setTelefono(registro.getTelefono());
			datos.setEmail(registro.getEmail());
			
			RequestNotificarWS notificaWS= new RequestNotificarWS();
			notificaWS.setMail(notifica.getMail());
			notificaWS.setSms(notifica.getSms());
			
			//stub.setTimeout(timeout);
			resultado= stub.addUsuario(token, datos, notificaWS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
	public static UsuariosLDAP changePassword(RegistroVO registro, NotificaVO notifica){
		//Registrar afiliado en LDAP
		UsuariosLDAP resultado=null;
		try {
			String usuario= Configuraciones.getConfig("ldap.credentials.user");
			String password= Configuraciones.getConfig("ldap.credentials.password");
			String ep= Configuraciones.getConfig("ep.clavesLDAP");
			CredentialWS credentials= new CredentialWS();
			credentials.setUser(usuario);
			credentials.setPassword(password);
			
			UsuariosLDAPPortBindingStub stub= new UsuariosLDAPPortBindingStub();
			
			stub._setProperty(UsuariosLDAPPortBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
			String token= stub.autenticacionUWS(credentials);
			RequestPasswordWS datos= new RequestPasswordWS();
			datos.setRut(registro.getRut_DV());
			String celular= registro.getCelular();
			if(registro.getCelular().length()>9){
				celular= celular.substring(celular.length()-9);
			}else if(registro.getCelular().length()==8){
				celular= "9" + celular;
			}
			datos.setCelular(celular);
			datos.setEmail(registro.getEmail());
			
			RequestNotificarWS notificaWS= new RequestNotificarWS();
			notificaWS.setMail(notifica.getMail());
			notificaWS.setSms(notifica.getSms());
			
			resultado= stub.changePassword(token, datos, notificaWS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
}
