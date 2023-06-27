package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class LectorEmpresaVO implements Serializable
{
	private static final long serialVersionUID = 2743922932412574348L;
	
	private int idLectorEmpresa;
	private int habilitado;

	public LectorEmpresaVO(int idLectorEmpresa, int habilitado) 
	{
		super();
		this.idLectorEmpresa = idLectorEmpresa;
		this.habilitado = habilitado;
	}

	public LectorEmpresaVO() 
	{
		super();
	}
	
	public LectorEmpresaVO(int idLectorEmpresa) 
	{
		this.idLectorEmpresa = idLectorEmpresa;
	}
	public int getHabilitado() {
		return this.habilitado;
	}
	public void setHabilitado(int habilitado) {
		this.habilitado = habilitado;
	}
	public int getIdLectorEmpresa() {
		return this.idLectorEmpresa;
	}
	public void setIdLectorEmpresa(int idLectorEmpresa) {
		this.idLectorEmpresa = idLectorEmpresa;
	}	
}
