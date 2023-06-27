package cl.araucana.cp.seguridad;

import java.io.Serializable;

public class PermisoEmpresaConvenio implements Serializable {

	private static final long serialVersionUID = 29232468249827349L;
	
	private int idEmpresa;
	private int idConvenio;

	public PermisoEmpresaConvenio() 
	{

		this.idEmpresa = 0;
		this.idConvenio = 0;
	}
	
	public PermisoEmpresaConvenio(int idEmpresa, int idConvenio) 
	{
		this.idEmpresa = idEmpresa;
		this.idConvenio = idConvenio;
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

	public String toString() {
		return "PermisoEmpresaConvenio[idEmpresa:" + this.idEmpresa + ", idConvenio:" + this.idConvenio + "]";
	}

	public int hashCode()
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.idConvenio;
		result = PRIME * result + this.idEmpresa;
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
		final PermisoEmpresaConvenio other = (PermisoEmpresaConvenio) obj;
		if (this.idConvenio != other.idConvenio)
			return false;
		if (this.idEmpresa != other.idEmpresa)
			return false;
		return true;
	}
}
