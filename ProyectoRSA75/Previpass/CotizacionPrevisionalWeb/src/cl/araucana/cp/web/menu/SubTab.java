package cl.araucana.cp.web.menu;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class SubTab 
{
	private String nombre;
	private String subSubAccion;
	private String rolesPermitidos;

	public String getRolesPermitidos()
	{
		return this.rolesPermitidos;
	}
	public void setRolesPermitidos(String rolesPermitidos)
	{
		this.rolesPermitidos = rolesPermitidos;
	}
	public String getNombre() 
	{
		return this.nombre;
	}
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	public String toString() 
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public String getSubSubAccion() 
	{
		return this.subSubAccion;
	}
	public void setSubSubAccion(String subSubAccion) 
	{
		this.subSubAccion = subSubAccion;
	}
}

