package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

import cl.araucana.cp.distribuidor.hibernate.beans.AuditableVO;

public class CtaCteBancoVO extends AuditableVO
{
	private static final long serialVersionUID = 1L;
	private int idBanco;
	private String idCtaCte;

	public CtaCteBancoVO() 
	{
		super();
	}

	public CtaCteBancoVO(int idBanco, String idCtaCte)
	{
		this.idBanco = idBanco;
		this.idCtaCte = idCtaCte;
	}
	public CtaCteBancoVO(Integer idBanco, String idCtaCte)
	{
		this.idBanco = idBanco.intValue();
		this.idCtaCte = idCtaCte;
	}
	public int getIdBanco()
	{
		return this.idBanco;
	}
	public void setIdBanco(int idBanco)
	{
		this.idBanco = idBanco;
	}
	public String getIdCtaCte()
	{
		return this.idCtaCte;
	}
	public void setIdCtaCte(String idCtaCte)
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
