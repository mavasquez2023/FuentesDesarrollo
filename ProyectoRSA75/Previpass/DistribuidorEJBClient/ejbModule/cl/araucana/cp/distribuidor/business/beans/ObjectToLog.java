package cl.araucana.cp.distribuidor.business.beans;

import java.io.Serializable;
import java.util.HashMap;

public class ObjectToLog implements Serializable 
{
	private static final long serialVersionUID = 188083750534018661L;
	private String className;
	private HashMap parametros;

	public ObjectToLog() 
	{
		super();
	}

	public ObjectToLog(String className, HashMap parametros) 
	{
		super();
		this.className = className;
		this.parametros = parametros;
	}

	public String getClassName() 
	{
		return this.className;
	}

	public void setClassName(String className) 
	{
		this.className = className;
	}

	public HashMap getParametros() 
	{
		return this.parametros;
	}

	public void setParametros(HashMap parametros) 
	{
		this.parametros = parametros;
	}

	public String toString() 
	{
		return "className:" + this.className + ":numParam:" + this.parametros.size() + "::";
	}
}
