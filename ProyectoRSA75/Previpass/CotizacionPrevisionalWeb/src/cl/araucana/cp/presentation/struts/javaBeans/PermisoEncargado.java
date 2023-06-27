package cl.araucana.cp.presentation.struts.javaBeans;
/*
* @(#) PermisoEncargado.java 1.1 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
*/
/**
 * @author cchamblas
 * 
 * @version 1.1
 */
public class PermisoEncargado
{
	private String rutUsuario;
	private String nombre;
	private String nombrePermiso;
	
	public PermisoEncargado(String rutUsuario) 
	{
		super();
		this.rutUsuario = rutUsuario;
	}
	/**
	 * permiso encargado
	 * @param rutUsuario
	 * @param nombre
	 * @param nombrePermiso
	 */
	public PermisoEncargado(String rutUsuario, String nombre, String nombrePermiso) 
	{
		super();
		this.rutUsuario = rutUsuario;
		this.nombre = nombre;
		this.nombrePermiso = nombrePermiso;
	}
	
	public PermisoEncargado() 
	{
		super();
	}
	/**
	 * nombre
	 * @return
	 */
	public String getNombre() {
		return this.nombre;
	}
	/**
	 * nombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * nombre permiso
	 * @return
	 */
	public String getNombrePermiso() {
		return this.nombrePermiso;
	}
	/**
	 * nombre permiso
	 * @param nombrePermiso
	 */
	public void setNombrePermiso(String nombrePermiso) {
		this.nombrePermiso = nombrePermiso;
	}
	/**
	 * rut usuario
	 * @return
	 */
	public String getRutUsuario() {
		return this.rutUsuario;
	}
	/**
	 * rut usuario
	 * @param rutUsuario
	 */
	public void setRutUsuario(String rutUsuario) {
		this.rutUsuario = rutUsuario;
	}
	/*
	 * hash code
	 */
	public int getHashCode() 
	{
		return this.hashCode();
	}
}
