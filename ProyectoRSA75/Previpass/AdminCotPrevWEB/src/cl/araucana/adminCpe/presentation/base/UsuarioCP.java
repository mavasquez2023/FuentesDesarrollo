package cl.araucana.adminCpe.presentation.base;

import java.util.Map;

import org.hibernate.Session;
/*
* @(#) UsuarioCP.java 1.2 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.2
 */
public class UsuarioCP extends Usuario
{
	private Map credenciales;
	
	public UsuarioCP(String l) {
		super(l);
	}
	
	public UsuarioCP(String l, Session session)
	{
		super(l, session);
	}
	
	public UsuarioCP(Map credenciales, Session session)
	{
		super((String) credenciales.get("login"), session);
		
		this.credenciales = credenciales;
	}

	public Map getCredenciales()
	{
		return this.credenciales;
	}

	public void setCredenciales(Map credenciales)
	{
		this.credenciales = credenciales;
	}
	
	public String getRol() {
		return (String) this.credenciales.get("rol");
	}
}
