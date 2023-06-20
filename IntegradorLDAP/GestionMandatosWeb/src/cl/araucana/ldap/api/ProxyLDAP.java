package cl.araucana.ldap.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import cl.araucana.core.registry.AppRole;
import cl.araucana.core.registry.Application;
import cl.araucana.core.registry.Enterprise;
import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;
/*
* @(#) PoxyLDAP.java 1.4 10/05/2009
*
* Este cï¿½digo fuente pertenece a la Caja de Compensaciï¿½n de Asignaciï¿½n Familiar
* La Araucana (C.C.A.F.). Su utilizaciï¿½n y reproducciï¿½n es confidencial y estï¿½
* restringido a los sistemas de informaciï¿½n propios de la instituciï¿½n.
*/
/**
 * @author cchamblas
 * 
 * @version 1.4
 */
public class ProxyLDAP 
{
	private static Logger logger = Logger.getLogger(ProxyLDAP.class);
	
	public static String deleteUser(String username) 
	{	
		String messageError=null;
		User user =null;
		UserRegistryConnection connection = null;
		try 
		{	user=getUser(username);
			if (user != null){
				connection = new UserRegistryConnection();
				logger.info("conectado a LDAP, eliminando Usuario:" + username);
				connection.removeUser(username);
				logger.info("usuario '" + username + "' fue eliminado de LDAP");
				
			}
		
		} catch (UserRegistryException e) 
		{	
			messageError= e.getMessage();
			logger.error("createUser, error al crear/mod user LDAP:" + user.getID() + ", mensaje:", e);
		} finally 
		{
			if (connection != null) 
			{
				try 
				{
					connection.close();
				} catch (UserRegistryException e) 
				{
					logger.error("error al cerrar conexión LDAP:", e);
				}
			}
		}
		return messageError;
	}
	
	/**
	 * crea User
	 * @param password
	 * @param persona
	 */
	public static String procesaUser(UsuarioVO persona) 
	{	
		String messageError=null;
		String username = persona.getUsername().trim();
		boolean newuser= true;
		User user =null;
		UserRegistryConnection connection = null;
		try 
		{	user=getUser(username);
			if (user != null)
			{
				logger.info("usuario :" + username + ": ya existe en LDAP, se actualiza datos...");
				//return ProxyLDAP.changePassword(username, password);
				if(persona.getNombres()!=null && !persona.getNombres().equals("")){
					user.setName(persona.getNombres());
				}
				if(persona.getApellidoPaterno()!=null && !persona.getApellidoPaterno().equals("")){
					user.setFirstName(persona.getApellidoPaterno());
				}
				if(persona.getApellidoMaterno()!=null && !persona.getApellidoMaterno().equals("")){
					user.setLastName(persona.getApellidoMaterno());
				}
				if(persona.getEmail()!=null && !persona.getEmail().equals("")){
					user.setEmail(persona.getEmail());
				}
				if(persona.getTelefono()!=null && !persona.getTelefono().equals("")){
					user.setPhone(persona.getTelefono());
				}
				/*if(persona.getSexo()!=null){
					user.setSex(persona.getSexo());
				}
				if(persona.getQuestion()!=null){
					user.setQuestion(persona.getQuestion());
				}
				if(persona.getAnswer()!=null){
					user.setAnswer(persona.getAnswer());
				}
				user.setBlocked(persona.isBlocked());
				*/
				newuser= false;
			}else{
				user = new User();
				user.setID(username);
				user.setName(persona.getNombres().trim());
				user.setFirstName(persona.getApellidoPaterno().trim());
				user.setLastName(persona.getApellidoMaterno().trim());
				user.setEmail(persona.getEmail().trim());
				user.setPhone(persona.getTelefono().trim());
				user.setSex(persona.getSexo().trim());
				user.setQuestion(persona.getQuestion().trim());
				user.setAnswer(persona.getAnswer());
				user.setSituation("x");
				user.setServices("x");
				user.setDeleted(false);
				user.setBlocked(false);
			}
		
			connection = new UserRegistryConnection();
			
			logger.info("conectado a LDAP, creando o modificando Usuario...");
			logger.debug(ToStringBuilder.reflectionToString(user, ToStringStyle.MULTI_LINE_STYLE));
			String password= persona.getClave().trim();
			if(newuser){
				if(password.equals("")){
					password="1234";
				}
				connection.createUser(user, password);
				logger.info("usuario '" + user.getID() + "' fue creado en LDAP");
				messageError="NEW";
			}else{
				connection.modifyUser(user);
				if(!password.equals("")){
					messageError= ProxyLDAP.changePassword(username, password);
				}
				logger.info("usuario '" + user.getID() + "' fue modificado en LDAP");
			}
			
		} catch (UserRegistryException e) 
		{	
			messageError= e.getMessage();
			logger.error("createUser, error al crear/mod user LDAP:" + user.getID() + ", mensaje:", e);
		} finally 
		{
			if (connection != null) 
			{
				try 
				{
					connection.close();
				} catch (UserRegistryException e) 
				{
					logger.error("error al cerrar conexión LDAP:", e);
				}
			}
		}
		return messageError;
	}
	
	public static String newUser(UsuarioVO persona) 
	{	
		String messageError=null;
		String username = persona.getUsername().trim();
		boolean newuser= true;
		User user =null;
		UserRegistryConnection connection = null;
		try 
		{	user=getUser(username);
			if (user == null){
				user = new User();
				user.setID(username);
				user.setName(persona.getNombres().trim());
				user.setFirstName(persona.getApellidoPaterno().trim());
				user.setLastName(persona.getApellidoMaterno().trim());
				user.setEmail(persona.getEmail().trim());
				user.setPhone(persona.getTelefono().trim());
				user.setSex(persona.getSexo().trim());
				user.setQuestion(persona.getQuestion().trim());
				user.setAnswer(persona.getAnswer());
				user.setSituation("x");
				user.setServices("x");
				user.setDeleted(false);
				user.setBlocked(false);
				
				String password= persona.getClave().trim();
				if(password.equals("")){
					password="1234";
				}
				connection = new UserRegistryConnection();
				logger.info("conectado a LDAP, creando Usuario...");
				connection.createUser(user, password);
				logger.info("usuario '" + user.getID() + "' fue creado en LDAP");
			}
		
			
			logger.debug(ToStringBuilder.reflectionToString(user, ToStringStyle.MULTI_LINE_STYLE));
			
			
		} catch (UserRegistryException e) 
		{	
			messageError= e.getMessage();
			logger.error("createUser, error al crear user LDAP:" + user.getID() + ", mensaje:", e);
		} finally 
		{
			if (connection != null) 
			{
				try 
				{
					connection.close();
				} catch (UserRegistryException e) 
				{
					logger.error("error al cerrar conexión LDAP:", e);
				}
			}
		}
		return messageError;
	}
	
	/**
	 * user
	 * @param userID
	 * @return
	 */
	public static User getUser(String userID) 
	{
		UserRegistryConnection connection = null;
		try 
		{
			connection = new UserRegistryConnection();
			
			return connection.getUser(userID);
		} catch (UserRegistryException e) 
		{
			return null;
		} finally 
		{
			if (connection != null) 
			{
				try 
				{
					connection.close();
				} catch(UserRegistryException e) {}
			}
		}
	}
	
	public static String createEnterprise(EmpresaVO empresa) 
	{	
		String messageError=null;
		String rutEmpresa = empresa.getRutEmpresa().trim();
		boolean newemp= true;
		Enterprise enterprise=null;
		UserRegistryConnection connection = null;
		try 
		{
			if(!empresa.getRazonSocialEmpresa().trim().equals("")){
				enterprise= getEnterprise(rutEmpresa);
				if (enterprise != null)
				{
					logger.info("empresa :" + rutEmpresa + ": ya existe en LDAP, actualiza Razon Social");
					if(empresa.getRazonSocialEmpresa()!=null){
						enterprise.setRazonSocial(empresa.getRazonSocialEmpresa());
					}
					if(empresa.getHolding()!=null){
						enterprise.setDnHolding(empresa.getHolding());
					}
					if(empresa.getTipo()!=null){
						enterprise.setTipo(empresa.getTipo());
					}
					if(empresa.getCodigoActividadEconomica()!=null){
						enterprise.setCodigoActividadEconomica(empresa.getCodigoActividadEconomica());
					}
					if(empresa.getActividadEconomica()!=null){
						enterprise.setActividadEconomica(empresa.getActividadEconomica());
					}
					if(empresa.getDireccion()!=null ){
						enterprise.setDireccion(empresa.getDireccion());
					}
					if(empresa.getCiudad()!=null){
						enterprise.setCiudad(empresa.getCiudad());
					}
					if(empresa.getRegion()!=null){
						enterprise.setRegion(empresa.getRegion());
					}
					if(empresa.getRutRepLegal()!=null){
						enterprise.setRutRepLegal(empresa.getRutRepLegal());
					}
					if(empresa.getNombreRepLegal()!=null ){
						enterprise.setNombreRepLegal(empresa.getNombreRepLegal());
					}
					if(empresa.getApellidoPaternoRepLegal()!=null){
						enterprise.setApellidoPaternoRepLegal(empresa.getApellidoPaternoRepLegal());
					}
					if(empresa.getApellidoMaternoRepLegal()!=null){
						enterprise.setApellidoMaternoRepLegal(empresa.getApellidoMaternoRepLegal());
					}
					if(empresa.getFono()!=null){
						enterprise.setFono(empresa.getFono());
					}
					if(empresa.getEmail()!=null){
						enterprise.setEmail(empresa.getEmail());
					}
					enterprise.setAfiliada(empresa.getAfiliada());
					
					newemp= false;
				}else{
					enterprise = new Enterprise();
					//logger.debug(ToStringBuilder.reflectionToString(persona, ToStringStyle.MULTI_LINE_STYLE));
					logger.info("creando en LDAP::" + rutEmpresa + "::");
					enterprise.setID(rutEmpresa);
					enterprise.setRazonSocial(empresa.getRazonSocialEmpresa());
					enterprise.setActividadEconomica(empresa.getActividadEconomica());
					enterprise.setAfiliada(empresa.getAfiliada());
					enterprise.setApellidoMaternoRepLegal(empresa.getApellidoMaternoRepLegal());
					enterprise.setApellidoPaternoRepLegal(empresa.getApellidoPaternoRepLegal());
					enterprise.setCiudad(empresa.getCiudad());
					enterprise.setCodigoActividadEconomica(empresa.getCodigoActividadEconomica());
					enterprise.setComentario(empresa.getComentario());
					enterprise.setDireccion(empresa.getDireccion());
					enterprise.setDnHolding(empresa.getHolding());
					enterprise.setEmail(empresa.getEmail());
					enterprise.setFax(empresa.getFax());
					enterprise.setFono(empresa.getFono());
					//enterprise.setName(empresa.get);
					enterprise.setNombreRepLegal(empresa.getNombreRepLegal());
					enterprise.setRegion(empresa.getRegion());
					enterprise.setRutEmpresaLider(empresa.getRutEmpresaLider());
					enterprise.setRutRepLegal(empresa.getRutRepLegal());
					enterprise.setTipo(empresa.getTipo());
				}

				connection = new UserRegistryConnection();
				logger.info("conectado a LDAP, creando Empresa...");
				logger.debug(ToStringBuilder.reflectionToString(empresa, ToStringStyle.MULTI_LINE_STYLE));
				if(newemp){
					connection.createEnterprise(enterprise);
					logger.info("empresa '" + empresa.getRutEmpresa() + "' fue creado en LDAP");
				}else{
					connection.modifyEnterprise(enterprise);
					logger.info("empresa '" + empresa.getRutEmpresa() + "' fue modificado en LDAP");
				}
			}else{
				messageError="Razon Social de la Empresa no puede ser null o vacío";
			}
		} catch (UserRegistryException e) 
		{	
			messageError= e.getMessage();
			logger.error("createEnterprise, error al crear/mod empresa LDAP:" + empresa.getRutEmpresa() + ", mensaje:", e);
		} finally 
		{
			if (connection != null) 
			{
				try 
				{
					connection.close();
				} catch (UserRegistryException e) 
				{
					logger.error("error al cerrar conexión LDAP:", e);
				}
			}
		}
		return messageError;
	}
	
	//Metodo no usado aún
	public String setdnHolding(String holding){
		if(holding!=null && (holding.indexOf("cn=")>-1 || holding.equals(""))){
			return holding;
		}else if(holding!=null){ 
			return "cn=" + holding + ",ou=holdings,o=araucana,c=cl";
		}else{
			return "";
		}
	}
	
	public static String assignAppRolEnterprise(RolUsuarioEmpVO registro) 
	{	
		String messageError=null;
		String rutEmpresa = registro.getRutEmpresa().trim();
		String rutUsuario = registro.getRutUsuario().trim();
		String app = registro.getApp();
		String approl = registro.getApprol();
		
		UserRegistryConnection connection = null;

		try 
		{
			connection = new UserRegistryConnection();
			logger.info("conectado a LDAP");
			Enterprise enterprise;
			try {
				enterprise = connection.getEnterprise(rutEmpresa);
			} catch (Exception e) {
				messageError= "Error al crear rol '" + approl + "' a usuario " + rutUsuario + " en empresa " + rutEmpresa + ", motivo: empresa no existe en LDAP";
			}
			if(messageError==null){
				boolean existRol= connection.isUserInRole(rutUsuario, app, approl, rutEmpresa);
				if(!existRol){
					if(registro.isAdd()){
						connection.assignAppRole(rutUsuario, app, approl, rutEmpresa);
						logger.info("rol '" + approl + "' fue asignado a " + rutUsuario + " en empresa " + rutEmpresa + " en LDAP");
					}
				}else{
					if(!registro.isAdd()){
						logger.info("Quitando rol '" + approl + "' de aplicación:'" + app + "' de usuario '" + rutUsuario + "' y empresa '" + rutEmpresa + "' en LDAP");
						connection.unassignAppRole(rutUsuario, app, approl, rutEmpresa);
						logger.info("rol '" + approl + "' fue quitado a " + rutUsuario + " en empresa " + rutEmpresa + " en LDAP"); 
					}
				}	
			}
			
		} catch (UserRegistryException e) 
		{	
			messageError= e.getMessage();
			logger.error("assignAppRolEnterprise, error al crear/elim rol '" + approl + "' fue quitado a " + rutUsuario + " en empresa " + rutEmpresa +  ", mensaje:", e);
		} finally 
		{
			if (connection != null) 
			{
				try 
				{
					connection.close();
				} catch (UserRegistryException e) 
				{
					logger.error("error al cerrar conexión LDAP:", e);
				}
			}
		}
		return messageError;
	}
	
	public static String createAppRolUsers(AppRolesVO registro) 
	{	
		String messageError=null;
		String rutUsuario = registro.getRutUsuario();
		String app = registro.getAppID();
		String rol = registro.getRolID();
		String appdesc = registro.getAppDescripcion();
		String roldesc = registro.getRolDescripcion();
		
		UserRegistryConnection connection = null;
	
		try 
		{		connection = new UserRegistryConnection();
				logger.info("conectado a LDAP");
				if(rol.equals("") && rutUsuario.equals("")){
					Application appTO= new Application(app);
					appTO.setDescription(appdesc);
					if(registro.isAdd()){
						connection.createApplication(appTO);
						logger.info("app '" + app + "' fue agregado a LDAP");
					}else{
						Application applica= connection.getApplication(app);
						applica.getConcepts().removeAll(applica.getConcepts());
						connection.removeApplication(app);
						logger.info("app '" + app + "' fue removido de LDAP");
					}

				}
				if(!rol.equals("") && rutUsuario.equals("")){
					AppRole rolTO= new AppRole(app, rol);
					rolTO.setDescription(roldesc);
					registro.getEstado();
					if(registro.isAdd()){
						connection.createAppRole(rolTO);
						logger.info("rol " + rol + " de " + app + "' fue agregado a LDAP");
					}else{
						connection.removeAppRole(app, rol);
						logger.info("rol " + rol + " de " + app + "' fue removido de LDAP");
					}

				}
				if(!rol.equals("") && !rutUsuario.equals("")){
					if(registro.isAdd()){
						connection.assignAppRole(rutUsuario, app, rol);
						logger.info("usuario " + rutUsuario + " fue agregado a rol " + rol + " de " + app + "' en LDAP");
					}else{
						connection.unassignAppRole(rutUsuario, app, rol);
						logger.info("usuario " + rutUsuario + " fue removido a rol " + rol + " de " + app + "' en LDAP");
					}
				}

		} catch (UserRegistryException e) 
		{	
			messageError= e.getMessage();
			logger.error("createAppRolUsers, error al crear/elim app-rol-usu LDAP, mensaje:", e);
		} finally 
		{
			if (connection != null) 
			{
				try 
				{
					connection.close();
				} catch (UserRegistryException e) 
				{
					logger.error("error al cerrar conexión LDAP:", e);
				}
			}
		}
		return messageError;
		
	}
	
	public static Enterprise getEnterprise(String enterprisesID) 
	{
		UserRegistryConnection connection = null;
		try 
		{
			connection = new UserRegistryConnection();
			
			return connection.getEnterprise(enterprisesID);
		} catch (UserRegistryException e) 
		{	logger.error("getEnterprise, error al consultar empresa:" + enterprisesID + ", mensaje:", e);
			return null;
		} finally 
		{
			if (connection != null) 
			{
				try 
				{
					connection.close();
				} catch(UserRegistryException e) {}
			}
		}
	}
	
	public static Collection getEnterpriseUsers(String enterpriseID, String appID, String rolID) 
	{
		UserRegistryConnection connection = null;
		List listusers= new ArrayList();
		try 
		{
			connection = new UserRegistryConnection();
			Collection users= connection.getUsers(appID, rolID, enterpriseID);
			for (Iterator iter = users.iterator(); iter.hasNext();) {
				String rut = (String) iter.next();
				String rutfmt= rut.replaceAll("\\.", "");
				HashMap usuario= new HashMap();
				usuario.put("RUT", rut);
				try {
					User user = connection.getUser(rutfmt);
					usuario.put("NOMBRE", user.getName() + " " + user.getFirstName());
					
				} catch (Exception e) {
					usuario.put("NOMBRE", "");
				}
				listusers.add(usuario);
				
			}
			return listusers;
		} 
		catch (Exception e) 
		{	logger.error("getEnterpriseUsers, error al consultar usuarios de empresa:" + enterpriseID + " y rol:" + rolID + ", mensaje:", e);
			e.printStackTrace();
			listusers.add("Error en consulta");
			return listusers;
		} finally 
		{
			if (connection != null) 
			{
				try 
				{
					connection.close();
				} catch(UserRegistryException e) {}
			}
		}
	}
	
	public static Collection getUserEnterprisesAut(String userID, String appID, String rolID) 
	{
		UserRegistryConnection connection = null;
		List salida= new ArrayList();
		try 
		{
			connection = new UserRegistryConnection();
			return connection.getEnterprises(userID, appID, rolID);
			
		} catch (UserRegistryException e) 
		{	logger.error("getUserEnterprisesAut, error al consultar empresa autorizadas de usuario:" + userID + " y rol:" + rolID + ", mensaje:", e);
			salida.add("Error en consulta");
			return salida;
		} finally 
		{
			if (connection != null) 
			{
				try 
				{
					connection.close();
				} catch(UserRegistryException e) {}
			}
		}
	}
	
	public static Collection getApplications() 
	{
		UserRegistryConnection connection = null;
		try 
		{
			connection = new UserRegistryConnection();
			List appsID= new ArrayList();

			List apps= (List)connection.getApplications();
			for (Iterator iter = apps.iterator(); iter.hasNext();) {
				Application aplicacion = (Application) iter.next();
				appsID.add(aplicacion.getID());

			}

			//Collections.sort(appsID);
			return appsID;
		} catch (UserRegistryException e) 
		{	logger.error("getApplications, error al consultar aplicciones de LDAP, mensaje:", e);
			return null;
		} finally 
		{
			if (connection != null) 
			{
				try 
				{
					connection.close();
				} catch(UserRegistryException e) {}
			}
		}
	}
	
	public static Collection getAppRoles(String app) 
	{
		UserRegistryConnection connection = null;
		try 
		{
			connection = new UserRegistryConnection();
			List rolesID= new ArrayList();
			List roles= (List)connection.getAppRoles(app);
			for (Iterator iter = roles.iterator(); iter.hasNext();) {
				AppRole rol = (AppRole) iter.next();
				rolesID.add(rol.getID());
				
			}
			return rolesID;
		} catch (UserRegistryException e) 
		{	logger.error("getApplications, error al consultar roles de aplicción:"  + app + ", mensaje:", e);
			return null;
		} finally 
		{
			if (connection != null) 
			{
				try 
				{
					connection.close();
				} catch(UserRegistryException e) {}
			}
		}
	}
	
	public static Collection getInforUserAppRoles(String app, String rol) 
	{
		UserRegistryConnection connection = null;
		List listusers= new ArrayList();
		try 
		{
			connection = new UserRegistryConnection();
			List users= (List)connection.getUsers(app, rol);
			for (Iterator iter = users.iterator(); iter.hasNext();) {
				String rut = (String) iter.next();
				String rutfmt= rut.replaceAll("\\.", "");
				HashMap usuario= new HashMap();
				usuario.put("RUT", rut);
				try {
					User user = connection.getUser(rutfmt);
					usuario.put("NOMBRE", user.getName() + " " + user.getFirstName());
					
				} catch (Exception e) {
					usuario.put("NOMBRE", "");
				}
				listusers.add(usuario);
			}
			return listusers;
		} catch (UserRegistryException e) 
		{	logger.error("getApplications, error al consultar usuarios de rol: " + rol + " de aplicción:"  + app + ", mensaje:", e);
			listusers.add("Error en consulta");
			return listusers;
		} finally 
		{
			if (connection != null) 
			{
				try 
				{
					connection.close();
				} catch(UserRegistryException e) {}
			}
		}
	}
	
	public static Collection getUsersAppRoles(String app, String rol) 
	{
		UserRegistryConnection connection = null;
		List listusers= new ArrayList();
		try 
		{
			connection = new UserRegistryConnection();
			List users= (List)connection.getUsers(app, rol);
			for (Iterator iter = users.iterator(); iter.hasNext();) {
				String rut = (String) iter.next();
				listusers.add(rut.replaceAll("\\.", ""));
			}
			return listusers;
		} catch (UserRegistryException e) 
		{	logger.error("getApplications, error al consultar usuarios de rol: " + rol + " de aplicción:"  + app + ", mensaje:", e);
			listusers.add("Error en consulta");
			return listusers;
		} finally 
		{
			if (connection != null) 
			{
				try 
				{
					connection.close();
				} catch(UserRegistryException e) {}
			}
		}
	}
	
	public static Collection getRolesUserinApp(String userID, String appID) 
	{
		UserRegistryConnection connection = null;
		List listroles= new ArrayList();
		try 
		{
			connection = new UserRegistryConnection();
			List roles= (List)connection.getUserRoles(userID, appID);
			for (Iterator iter = roles.iterator(); iter.hasNext();) {
				String rol = (String) iter.next();
				listroles.add(rol);
			}
			return listroles;
		} catch (UserRegistryException e) 
		{	logger.error("getApplications, error al consultar rol de usuario: " + userID + " de aplicción:"  + appID + ", mensaje:", e);
		listroles.add("Error en consulta");
			return listroles;
		} finally 
		{
			if (connection != null) 
			{
				try 
				{
					connection.close();
				} catch(UserRegistryException e) {}
			}
		}
	}
	
	public static boolean isUserInRoleEnterprise(String rutUsuario, String app, String approl, String rutEmpresa) 
	{
		UserRegistryConnection connection = null;
		try 
		{
			connection = new UserRegistryConnection();
			
			return connection.isUserInRole(rutUsuario, app, approl, rutEmpresa);
		} catch (UserRegistryException e) 
		{
			return false;
		} finally 
		{
			if (connection != null) 
			{
				try 
				{
					connection.close();
				} catch(UserRegistryException e) {}
			}
		}
	}
	
	public static boolean isApplication(String app) 
	{
		boolean estado=true;
		UserRegistryConnection connection = null;
		try 
		{
			connection = new UserRegistryConnection();
			Application application= connection.getApplication(app);
			if(application==null){
				estado= false;
			}
		} catch (UserRegistryException e) 
		{
			estado= false;
		} finally 
		{
			if (connection != null) 
			{
				try 
				{
					connection.close();
				} catch(UserRegistryException e) {}
			}
		}
		return estado;
	}
	
	public static boolean isRolApp(String app, String rol) 
	{
		boolean estado=true;
		UserRegistryConnection connection = null;
		try 
		{
			connection = new UserRegistryConnection();
			AppRole approl= connection.getAppRole(app, rol);
			if(approl==null){
				estado= false;
			}
		} catch (UserRegistryException e) 
		{
			estado= false;
		} finally 
		{
			if (connection != null) 
			{
				try 
				{
					connection.close();
				} catch(UserRegistryException e) {}
			}
		}
		return estado;
	}
	
	public static boolean isUserRolApp(String user, String app, String rol) 
	{
		boolean estado=true;
		UserRegistryConnection connection = null;
		try 
		{
			connection = new UserRegistryConnection();
			estado= connection.isUserInRole(user, app, rol);
		} catch (UserRegistryException e) 
		{
			estado= false;
		} finally 
		{
			if (connection != null) 
			{
				try 
				{
					connection.close();
				} catch(UserRegistryException e) {}
			}
		}
		return estado;
	}
	
	/**
	 * cambia password
	 * @param idPersona
	 * @param newPassword
	 * @return
	 */
	public static String changePassword(String username, String newPassword)
	{
		String messageError=null;
		logger.info("cambio password LDAP::" + username + "::");
		UserRegistryConnection connection = null;

		try 
		{
			connection = new UserRegistryConnection();
			logger.info("conectado a LDAP");
			connection.changeUserPassword(username, null, newPassword);
			logger.info("usuario '" + username + "' cambio password en LDAP");
		} catch (UserRegistryException e) 
		{
			logger.error("error al cambiar pass para usuario:" + username + ", mensaje:" +  e);
			messageError= e.getMessage();
		} finally 
		{
			if (connection != null) 
			{
				try 
				{
					connection.close();
				} catch (UserRegistryException e) 
				{
					logger.error("changePassword, error al cerrar conexión LDAP:", e);
				}
			}
		}
		return messageError;
	}
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) 
	{
		 
		//PersonaVO p = new PersonaVO();
		//p.setIdPersona(new Integer(555));
		//p.setNombres("aa");
		//ProxyLDAP.createUser("1234", p);
		//System.out.println("Usuario Encontrado: " + getUser("555-K"));
		
		
		//int idPersona = 12345678-5;
		//System.out.println(generaDV(555));
		/*System.out.println("ACA");
		for(int i=10000000;i<9000100;i++){
			System.out.println(i+"-"+generaDV(i));
		}
		*/
		//System.out.println("30000001-"+generaDV(1000001));
		//System.out.println("30000002-"+generaDV(1000002));
		//System.out.println("30000003-"+generaDV(1000003));
		//System.out.println("30000004-"+generaDV(1000004));
		//System.out.println("30000005-"+generaDV(1000005));
		//System.out.println(ProxyLDAP.changePassword("76021396-9", "8866"));
		System.out.println( ProxyLDAP.getUser("76021396-9") );
		
	}

}
