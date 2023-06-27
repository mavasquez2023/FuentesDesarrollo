package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;
import java.util.HashMap;

public class AuditableVO implements Serializable 
{
	private static final long serialVersionUID = -7571971314990592679L;

	public HashMap getParametrosOrdenados()
	{
		return new HashMap();
	}

	public boolean equals(Object obj) 
	{
		return super.equals(obj);
	}

	public int hashCode() 
	{
		return super.hashCode();
	}
	
}
