package cl.araucana.cp.utils;

import org.apache.log4j.Logger;

import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserNotFoundUserRegistryException;
import cl.araucana.core.registry.exception.UserRegistryException;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;

public class ProxyLDAP 
{
	private static Logger logger = Logger.getLogger(ProxyLDAP.class);
	
	/**
	 * Genera una nueva clave al azar. 
	 * 
	 * @return
	 * @throws DaoException
	 */
	public static String getNewPassword() {
		
//		Session session = HibernateUtil.getSession();
//		ParametroMgr parametroMgr = new ParametroMgr(session);
//		
//		List listParam = new ArrayList();
//		listParam.add(new Integer(Constants.PARAM_PASSWORD_DEFAULT));
//		ParametrosHash param = parametroMgr.getParametrosHash(listParam);
//		String passWordDefault = param.get("" + Constants.PARAM_PASSWORD_DEFAULT);
//		
//		return passWordDefault;
		
		String passwordInicial;
		
		int min=1000;
		int max=9999;
		
		int f= (int)(Math.random()*(max-min))+min;
		
		passwordInicial = String.valueOf( f );
		
		return passwordInicial;	
		
		
		
	}

	public static boolean createUser(PersonaVO persona, String password)
	{
		String idUser = "" + persona.getIdPersona().intValue() + "-" + Utils.generaDV(persona.getIdPersona().intValue());
		
		User user = null;
		try{
			user = getUser(idUser);
		} catch (UserRegistryException e) 
		{
			logger.error("error get user LDTA", e);
			return false;
		}		
		
		if (user != null)
		{
			//Asepulveda 26-08-2010
			//Si el usuario ya existe en LDAP, entonces no se hace nada.
			logger.info("usuario :" + idUser + ": ya existe en LDAP");
			return true;
			
			//Anteriormente se reseteaba la clave
			//logger.info("usuario :" + idUser + ": ya existe en LDAP, solo cambia pass y retorna");
			//return ProxyLDAP.changePassword(persona.getIdPersona().intValue(), password);
			
		}else{
			
			user = new User();
			logger.info("creando en LDTA::" + persona.getIdPersona().intValue() + "::");
			user.setID("" + persona.getIdPersona().intValue() + "-" + Utils.generaDV(persona.getIdPersona().intValue()));
			user.setName(persona.getNombres());
			user.setFirstName(persona.getApellidoPaterno());
			user.setLastName(persona.getApellidoMaterno());
			user.setEmail(persona.getEmail());
			user.setPhone(persona.getTelefono());
			user.setSex("");
			user.setQuestion("");
			user.setAnswer("");

			user.setSituation("x");
			user.setServices("x");
			user.setDeleted(false);
			user.setBlocked(false);

			UserRegistryConnection connection = null;

			try 
			{
				//String password = ProxyLDAP.getNewPassword();				
				
				connection = new UserRegistryConnection();
				logger.info("conectado a LDTA");
				connection.createUser(user, password);
				logger.info("usuario '" + user.getID() + "' fue creado en LDAP");
				return true;
//			} catch (DaoException e)
//			{
//				logger.error("error al obtener clave por defecto", e);		
			} catch (UserRegistryException e) 
			{
				logger.error("error creando user LDTA", e);
			} finally 
			{
				if (connection != null) 
				{
					try 
					{
						connection.close();
					} catch (UserRegistryException e) 
					{
						logger.error("error creando user LDTA", e);
					}
				}
			}
			return false;			
		}
	}

	private static User getUser(String userID) throws UserRegistryException
	{
		UserRegistryConnection connection = null;
		try 
		{
			connection = new UserRegistryConnection();

			return connection.getUser(userID);
		}catch (UserNotFoundUserRegistryException e) 
		{
			return null;
		}finally
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

	public static boolean changePassword(int idPersona, String newPassword) 
	{
		logger.info("cambio password LDAP::" + idPersona + "::");
		String login = "" + idPersona + "-" + Utils.generaDV(idPersona);
		UserRegistryConnection connection = null;

		try 
		{
			connection = new UserRegistryConnection();
			logger.info("conectado a LDAP:" + login + "::");
			connection.changeUserPassword(login, null, newPassword);
			logger.info("usuario '" + login + "' cambio password en LDAP");
			return true;
		} catch (UserRegistryException e) 
		{
			logger.error("error cambio pass LDTA", e);
		} finally 
		{
			if (connection != null) 
			{
				try 
				{
					connection.close();
				} catch (UserRegistryException e) 
				{
					logger.error("error cambio pass user LDTA", e);
				}
			}
		}
		return false;
	}
	
	
	/**
	 * Indica si se debe forzar el cambio de password por parte del usuario.
	 * @param userID
	 * @return
	 * @throws UserRegistryException
	 */
	public static boolean mustUserChangePassword(String userID) throws UserRegistryException 
	{
		UserRegistryConnection connection = null;
		try 
		{
			boolean respuesta = false;
			connection = new UserRegistryConnection();
			
			respuesta = connection.mustUserChangePassword(userID);
			return respuesta; 
		}
//		catch(UserRegistryException e){
//			throw e;
//		}
		finally 
		{
			if (connection != null) 
			{
					connection.close();
			}
		}
	}
	
	public static boolean changePassword(int idPersona, String oldPassword,  String newPassword, String reNewPassword) 
	{
		logger.info("cambio password LDAP::" + idPersona + "::");
		String login = "" + idPersona + "-" + Utils.generaDV(idPersona);
		UserRegistryConnection connection = null;

		try 
		{
			connection = new UserRegistryConnection();
			logger.info("conectado a LDAP:" + login + "::");
			connection.changeUserPassword(login, oldPassword, newPassword);
			logger.info("usuario '" + login + "' cambio password en LDAP");
			return true;
		} catch (UserRegistryException e) 
		{
			logger.error("error cambio pass LDTA", e);
		} finally 
		{
			if (connection != null) 
			{
				try 
				{
					connection.close();
				} catch (UserRegistryException e) 
				{
					logger.error("error cambio pass user LDTA", e);
				}
			}
		}
		return false;
	}	
	
}
