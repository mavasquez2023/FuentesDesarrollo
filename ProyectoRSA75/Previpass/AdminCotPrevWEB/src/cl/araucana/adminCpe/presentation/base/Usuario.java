package cl.araucana.adminCpe.presentation.base;
import java.util.Hashtable;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.adminCpe.presentation.mgr.UsuarioMgr;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

import com.bh.talon.User;
/*
* @(#) Usuario.java 1.3 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.3
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

	public Usuario(String l)
	{
		this.login = l;
	}

	public Usuario(String l, Session session)
	{
		this.login = l;

		try
		{
			this.persona = new UsuarioMgr(session).getPersona(this.login);
		} catch (DaoException e)
		{
			logger.error("problemas", e);
		}
	}

	public Object getLink(String nombre)
	{
		return this.links.get(nombre);
	}

	public void setLink(String name, Object value)
	{
		this.links.put(name, value);
	}

	/**
	 * Gets the login
	 * 
	 * @return Returns a String
	 */
	public String getLogin()
	{
		return this.login;
	}

	/**
	 * Gets the name
	 * 
	 * @return Returns a String
	 */
	public String getName()
	{
		if (this.name != null)
			return this.name;
		StringBuffer sb = new StringBuffer(this.persona.getNombres()).append(' ').append(this.persona.getApellidoPaterno());
		if ((this.persona.getApellidoMaterno() != null) && !this.persona.getApellidoMaterno().equals(""))
		{
			sb.append(' ').append(this.persona.getApellidoMaterno().charAt(0)).append('.');
		}
		this.name = sb.length() > 1 ? sb.toString() : this.login;
		return this.name;
	}

	public Object getUserReference()
	{
		return this.persona;
	}

	public String toString()
	{
		return "Login:" + this.login + " Name:" + this.name;
	}
}
