package cl.araucana.cp.presentation.struts.javaBeans;

import java.io.Serializable;
/*
* @(#) LineaListaUsuarios.java 1.1 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * 
 * @version 1.1
 */
public class LineaListaUsuarios implements Serializable
{
	private static final long serialVersionUID = 3860091259426796082L;

	private String idUsuario;
	private String idUsuarioFmt;
	private String nombre;
	private String apellido;
	/**
	 * apellido
	 * @return
	 */
	public String getApellido()
	{
		return this.apellido;
	}
	/**
	 * apellido
	 * @param apellido
	 */
	public void setApellido(String apellido)
	{
		this.apellido = apellido;
	}
	/**
	 * ud usuario
	 * @return
	 */
	public String getIdUsuario()
	{
		return this.idUsuario;
	}
	/**
	 * id usuario
	 * @param idUsuario
	 */
	public void setIdUsuario(String idUsuario)
	{
		this.idUsuario = idUsuario;
	}
	/**
	 * id usuario fmt
	 * @return
	 */
	public String getIdUsuarioFmt()
	{
		return this.idUsuarioFmt;
	}
	/**
	 * id usurio fmt
	 * @param idUsuarioFmt
	 */
	public void setIdUsuarioFmt(String idUsuarioFmt)
	{
		this.idUsuarioFmt = idUsuarioFmt;
	}
	/**
	 * nombre
	 * @return
	 */
	public String getNombre()
	{
		return this.nombre;
	}
	/**
	 * nombre
	 * @param nombre
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
}
