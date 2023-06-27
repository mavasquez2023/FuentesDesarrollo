package cl.araucana.cp.presentation.base;

import java.util.Hashtable;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.hibernate.exceptions.UsuarioNoEncontradoException;
import cl.araucana.cp.presentation.mgr.UsuarioMgr;

import com.bh.talon.User;
/*
* @(#) Usuario.java 1.14 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * 
 * @version 1.14
 */
public class Usuario implements User
{
	// INICIO LINKS
	public static String ROLES = "ROLS";
	public static String PERMISOS = "PERM";

	String login;
	String name;
	Map links = new Hashtable();
	private PersonaVO persona = null;

	static Logger logger = Logger.getLogger(Usuario.class);
	/**
	 * usuario
	 * @param l
	 */
	public Usuario(String l)
	{
		this.login = l;
	}
	/**
	 * usuario
	 * @param l
	 * @param session
	 * @throws UsuarioNoEncontradoException
	 */
	public Usuario(String l, Session session)
	{
		this.login = l;
		try
		{
			logger.info("LOGIN: " + this.login);
			this.persona = new UsuarioMgr(session).getPersona(this.login);
			logger.info("persona loggeada:" + this.persona.getApellidoPaterno() + "::" + this.persona.getIdPersona() + "::");
		} catch (DaoException e)
		{
			logger.error("problemas", e);
		}
	}
	/**
	 * link
	 */
	public Object getLink(String nombre)
	{
		return this.links.get(nombre);
	}
	/**
	 * link
	 */
	public void setLink(String name, Object value)
	{
		this.links.put(name, value);
	}

	// FIN LINKS

	/**
	 * Gets the login
	 * 
	 * @return this.Returns a String
	 */
	public String getLogin()
	{
		return this.login;
		//return this.login.split("-")[0];
}

	/**
	 * Gets the name
	 * 
	 * @return this.Returns a String
	 */
	public String getName()
	{
		if (this.name != null)
			return this.name;
		StringBuffer sb = new StringBuffer(this.persona.getNombres().trim()).append(' ').append(this.persona.getApellidoPaterno().trim());
		if ((this.persona.getApellidoMaterno() != null) && !this.persona.getApellidoMaterno().trim().equals(""))
		{
			sb.append(' ').append(this.persona.getApellidoMaterno().charAt(0)).append('.');
		}
		this.name = sb.length() > 1 ? sb.toString() : this.login;
		return this.name;
	}
	/**
	 * user referencia
	 */
	public Object getUserReference()
	{
		return this.persona;
	}
	/**
	 * login
	 */
	public String toString()
	{
		return "Login:" + this.login + " Name:" + this.name;
	}
}
