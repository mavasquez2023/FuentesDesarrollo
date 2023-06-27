package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class LectorEmpresaGrupoConvenioVO implements Serializable
{
	private static final long serialVersionUID = 2743922932412574348L;
	
	private int idLectorEmpresa;
	private int idGrupoConvenio;

	public LectorEmpresaGrupoConvenioVO() 
	{
		super();
	}

	public LectorEmpresaGrupoConvenioVO(int idLectorEmpresa, int idGrupoConvenio) 
	{
		super();
		this.idLectorEmpresa = idLectorEmpresa;
		this.idGrupoConvenio = idGrupoConvenio;
	}

	public int getIdGrupoConvenio() {
		return this.idGrupoConvenio;
	}
	public void setIdGrupoConvenio(int idGrupoConvenio) {
		this.idGrupoConvenio = idGrupoConvenio;
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
		result = PRIME * result + this.idGrupoConvenio;
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
		final LectorEmpresaGrupoConvenioVO other = (LectorEmpresaGrupoConvenioVO) obj;
		if (this.idGrupoConvenio != other.idGrupoConvenio)
			return false;
		if (this.idLectorEmpresa != other.idLectorEmpresa)
			return false;
		return true;
	}
	
}