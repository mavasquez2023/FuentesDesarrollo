package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class LectorEmpresaConvenioVO implements Serializable
{
	private static final long serialVersionUID = 2743922932412574348L;

	private int idLectorEmpresa;
	private int idConvenio;
	private int idEmpresa;

	public LectorEmpresaConvenioVO()
	{
		super();
	}

	public LectorEmpresaConvenioVO(int idLectorEmpresa, int idConvenio, int idEmpresa)
	{
		super();
		this.idLectorEmpresa = idLectorEmpresa;
		this.idConvenio = idConvenio;
		this.idEmpresa = idEmpresa;
	}

	public int getIdConvenio()
	{
		return this.idConvenio;
	}

	public void setIdConvenio(int idConvenio)
	{
		this.idConvenio = idConvenio;
	}

	public int getIdEmpresa()
	{
		return this.idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa)
	{
		this.idEmpresa = idEmpresa;
	}

	public int getIdLectorEmpresa()
	{
		return this.idLectorEmpresa;
	}

	public void setIdLectorEmpresa(int idLectorEmpresa)
	{
		this.idLectorEmpresa = idLectorEmpresa;
	}

	public int hashCode()
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.idConvenio;
		result = PRIME * result + this.idEmpresa;
		result = PRIME * result + this.idLectorEmpresa;
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
		final LectorEmpresaConvenioVO other = (LectorEmpresaConvenioVO) obj;
		if (this.idConvenio != other.idConvenio)
			return false;
		if (this.idEmpresa != other.idEmpresa)
			return false;
		if (this.idLectorEmpresa != other.idLectorEmpresa)
			return false;
		return true;
	}

}
