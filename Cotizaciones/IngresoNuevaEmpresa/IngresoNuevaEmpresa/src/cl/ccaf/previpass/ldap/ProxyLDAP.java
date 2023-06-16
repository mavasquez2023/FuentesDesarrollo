package cl.ccaf.previpass.ldap;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;
/*
* @(#) PoxyLDAP.java 1.4 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
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
		String idUser = "" + persona.getIdPersona().intValue() + "-" + generaDV(persona.getIdPersona().intValue());
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
	/**
	 * cambia password
	 * @param idPersona
	 * @param newPassword
	 * @return
	 */
	public static boolean changePassword(int idPersona, String newPassword)
	{
		logger.info("cambio password LDAP::" + idPersona + "::");
		String login = "" + idPersona + "-" + generaDV(idPersona);
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
		System.out.println( ProxyLDAP.getUser("15684526-4") );
		
	}
	public static char generaDV(int rut)
	{
		int M = 0, S = 1, T = rut;
		for (; T != 0; T /= 10)
			S = (S + T % 10 * (9 - M++ % 6)) % 11;
		return (char) (S != 0 ? S + 47 : 75);
	}

}
