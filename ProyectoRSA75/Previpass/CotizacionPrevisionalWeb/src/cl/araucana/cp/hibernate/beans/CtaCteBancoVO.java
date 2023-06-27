package cl.araucana.cp.hibernate.beans;

import java.util.HashMap;

import cl.araucana.cp.distribuidor.hibernate.beans.AuditableVO;

public class CtaCteBancoVO extends AuditableVO
{
	private static final long serialVersionUID = 1L;
	private int idBanco;
	private int idCtaCte;
	
	
	public int getIdBanco()
	{
		return this.idBanco;
	}
	public void setIdBanco(int idBanco)
	{
		this.idBanco = idBanco;
	}
	public int getIdCtaCte()
	{
		return this.idCtaCte;
	}
	public void setIdCtaCte(int idCtaCte)
	{
		this.idCtaCte = idCtaCte;
	}
	
	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.idBanco));
		parametros.put("2", String.valueOf(this.idCtaCte));
		return parametros;
		
	}
}
