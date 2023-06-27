package cl.araucana.adminCpe.utils;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
/*
* @(#) PoxyLDAP.java 1.4 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * 
 * @version 1.4
 */
public class ProxyLDAP 
{
	private static Logger logger = Logger.getLogger(ProxyLDAP.class);

	/**
	 * crea User
	 * @param password
	 * @param persona
	 */
	public static boolean createUser(String password, PersonaVO persona) 
	{
		String idUser = "" + persona.getIdPersona().intValue() + "-" + Utils.generaDV(persona.getIdPersona().intValue());
		try 
		{
			if (getUser(idUser) != null)
			{
				logger.info("usuario :" + idUser + ": ya existe en LDAP, solo cambia pass y retorna");
				return ProxyLDAP.changePassword(persona.getIdPersona().intValue(), password);
			}
		} catch (Exception e)
		{}
		
		User user = new User();
		logger.debug(ToStringBuilder.reflectionToString(persona, ToStringStyle.MULTI_LINE_STYLE));
		logger.info("creando en LDTA::" + persona.getIdPersona().intValue() + "::");
		user.setID(idUser);
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
			connection = new UserRegistryConnection();
			logger.info("conectado a LDTA");
			logger.debug(ToStringBuilder.reflectionToString(user, ToStringStyle.MULTI_LINE_STYLE));
			connection.createUser(user, password);
			logger.info("usuario '" + user.getID() + "' fue creado en LDAP");
			return true;
		} catch (UserRegistryException e) 
		{
			logger.error("error al crear user LDAP:", e);
		} finally 
		{
			if (connection != null) 
			{
				try 
				{
					connection.close();
				} catch (UserRegistryException e) 
				{
					logger.error("error al crear user LDAP:", e);
				}
			}
		}
		return false;
	}

	/**
	 * user
	 * @param userID
	 * @return
	 */
	private static User getUser(String userID) 
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
	/**
	 * cambia password
	 * @param idPersona
	 * @param newPassword
	 * @return
	 */
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
			logger.error("error al cambiar pass LDAP:", e);
		} finally 
		{
			if (connection != null) 
			{
				try 
				{
					connection.close();
				} catch (UserRegistryException e) 
				{
					logger.error("error al cambiar pass LDAP:", e);
				}
			}
		}
		return false;
	}
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) 
	{
		BasicConfigurator.configure(); 
		PersonaVO p = new PersonaVO();
		p.setIdPersona(new Integer(11777668));
		p.setNombres("aa");
		ProxyLDAP.createUser("1234", p);
		User user = getUser("11777668-9");
		logger.debug(ToStringBuilder.reflectionToString(user, ToStringStyle.MULTI_LINE_STYLE));
		//ProxyLDAP.changePassword(9973040, "9865");
	}
}
