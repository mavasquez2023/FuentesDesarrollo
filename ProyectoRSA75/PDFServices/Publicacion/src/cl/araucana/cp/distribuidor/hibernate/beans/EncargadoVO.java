package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;


public class EncargadoVO extends AuditableVO
{
	private static final long serialVersionUID = -7846910847883932441L;
	
	protected int idEncargado;
	protected String idFmt;
	protected int habilitado;
	protected PersonaVO persona;
	
	public EncargadoVO() {}
	
	public EncargadoVO(int idEncargado) 
	{
		this.idEncargado = idEncargado;
	}

	public int getHabilitado()
	{
		return this.habilitado;
	}
	
	public void setHabilitado(int habilitado)
	{
		this.habilitado = habilitado;
	}
	
	public int getIdEncargado()
	{
		return this.idEncargado;
	}
	
	public void setIdEncargado(int idEncargado)
	{
		this.idEncargado = idEncargado;
	}

	public String getIdFmt()
	{
		return this.idFmt;
	}

	public void setIdFmt(String idFmt)
	{
		this.idFmt = idFmt;
	}

	public PersonaVO getPersona()
	{
		return this.persona;
	}

	public void setPersona(PersonaVO persona)
	{
		this.persona = persona;
	}

	public int hashCode()
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.idEncargado;
		return result;
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final EncargadoVO other = (EncargadoVO) obj;
		if (this.idEncargado != other.idEncargado)
			return false;
		return true;
	}
	
	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.idEncargado));
		parametros.put("2", String.valueOf(this.habilitado));
		return parametros;
	}
}
