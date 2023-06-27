package cl.araucana.adminCpe.presentation.base;

import java.util.Map;

import org.hibernate.Session;
/*
* @(#) UsuarioCP.java 1.2 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
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
