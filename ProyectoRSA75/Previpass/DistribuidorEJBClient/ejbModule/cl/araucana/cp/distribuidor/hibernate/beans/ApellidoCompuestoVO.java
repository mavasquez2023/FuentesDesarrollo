package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class ApellidoCompuestoVO implements Serializable
{
	private static final long serialVersionUID = 4143301871939736791L;
	private String apellido;

	public ApellidoCompuestoVO()
	{
		super();
	}

	public ApellidoCompuestoVO(String apellido)
	{
		super();
		this.apellido = apellido;
	}

	public String getApellido()
	{
		return this.apellido;
	}

	public void setApellido(String apellido)
	{
		this.apellido = apellido;
	} 
}
