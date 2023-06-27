package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class LectorEmpresaSucursalVO implements Serializable
{
	private static final long serialVersionUID = 2743922932412574348L;

	private int idLectorEmpresa;
	private int idEmpresa;
	private int idConvenio;
	private String idSucursal;

	public LectorEmpresaSucursalVO()
	{
		super();
	}

	public LectorEmpresaSucursalVO(int idLectorEmpresa, int idEmpresa, int idConvenio, String idSucursal)
	{
		super();
		this.idLectorEmpresa = idLectorEmpresa;
		this.idEmpresa = idEmpresa;
		this.idConvenio = idConvenio;
		this.idSucursal = idSucursal;
	}

	public int getIdConvenio()
	{
		return this.idConvenio;
	}

	public void setIdConvenio(int idConvenio)
	{
		this.idConvenio = idConvenio;
	}

	public String getIdSucursal()
	{
		return this.idSucursal;
	}

	public void setIdSucursal(String idSucursal)
	{
		this.idSucursal = idSucursal;
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
		result = PRIME * result + ((this.idSucursal == null) ? 0 : this.idSucursal.hashCode());
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
		final LectorEmpresaSucursalVO other = (LectorEmpresaSucursalVO) obj;
		if (this.idConvenio != other.idConvenio)
			return false;
		if (this.idEmpresa != other.idEmpresa)
			return false;
		if (this.idLectorEmpresa != other.idLectorEmpresa)
			return false;
		if (this.idSucursal == null)
		{
			if (other.idSucursal != null)
				return false;
		} else if (!this.idSucursal.equals(other.idSucursal))
			return false;
		return true;
	}

}
