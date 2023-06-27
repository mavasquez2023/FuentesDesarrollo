package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class LectorEmpresaEmpresaVO implements Serializable
{
	private static final long serialVersionUID = 2743922932412574348L;
	
	private int idLectorEmpresa;
	private int idEmpresa;

	public LectorEmpresaEmpresaVO() 
	{
		super();
	}

	public LectorEmpresaEmpresaVO(int idLectorEmpresa, int idEmpresa) 
	{
		super();
		this.idLectorEmpresa = idLectorEmpresa;
		this.idEmpresa = idEmpresa;
	}

	public int getIdEmpresa() {
		return this.idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public int getIdLectorEmpresa() {
		return this.idLectorEmpresa;
	}
	public void setIdLectorEmpresa(int idLectorEmpresa) {
		this.idLectorEmpresa = idLectorEmpresa;
	}

	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.idEmpresa;
		result = PRIME * result + this.idLectorEmpresa;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final LectorEmpresaEmpresaVO other = (LectorEmpresaEmpresaVO) obj;
		if (this.idEmpresa != other.idEmpresa)
			return false;
		if (this.idLectorEmpresa != other.idLectorEmpresa)
			return false;
		return true;
	}	
}