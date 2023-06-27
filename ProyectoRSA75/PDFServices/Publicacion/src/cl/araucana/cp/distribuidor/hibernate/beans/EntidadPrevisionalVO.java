package cl.araucana.cp.distribuidor.hibernate.beans;

import cl.araucana.cp.distribuidor.hibernate.beans.AuditableVO;

public class EntidadPrevisionalVO extends AuditableVO
{
	private static final long serialVersionUID = -1L;
	private int idEntPagadora;
	
	
	public EntidadPrevisionalVO(Integer idEntPagadora)
	{
		super();
		this.idEntPagadora = idEntPagadora.intValue();
		
	}
		
	public EntidadPrevisionalVO(int idEntPagadora)
	{
		super();
		this.idEntPagadora = idEntPagadora;

	}
	public EntidadPrevisionalVO()
	{
		super();
	}
	
	public int getidEntPagadora()
	{
		return this.idEntPagadora;
	}
	public void setIdEntPagadora(int idEntPagadora)
	{
		this.idEntPagadora = idEntPagadora;
	}
	

}
