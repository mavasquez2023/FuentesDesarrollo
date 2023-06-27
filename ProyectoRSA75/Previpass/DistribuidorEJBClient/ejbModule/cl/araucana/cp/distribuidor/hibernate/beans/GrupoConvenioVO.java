package cl.araucana.cp.distribuidor.hibernate.beans;

import java.sql.Timestamp;
import java.util.HashMap;

public class GrupoConvenioVO extends AuditableVO implements Comparable
{
	private static final long serialVersionUID = 7185938325097480888L;

	private int idGrupoConvenio;
	private int idEmpresa;
	private int idMapaNomRem;
	private int idMapaNomGra;
	private int idMapaNomDep;
	private int idMapaNomRel;
	private int idMapaCod;
	private int idOpcion;
	private String nombre;
	private Timestamp creado;
	private int habilitado;
	private int numBloqueos;
	private boolean previred;
	private int configBase;
	private boolean prodCaja;

	public Timestamp getCreado()
	{
		return this.creado;
	}

	public void setCreado(Timestamp creado)
	{
		this.creado = creado;
	}

	public int getHabilitado()
	{
		return this.habilitado;
	}

	public void setHabilitado(int habilitado)
	{
		this.habilitado = habilitado;
	}

	public int getIdEmpresa()
	{
		return this.idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa)
	{
		this.idEmpresa = idEmpresa;
	}

	public int getIdGrupoConvenio()
	{
		return this.idGrupoConvenio;
	}

	public void setIdGrupoConvenio(int idGrupoConvenio)
	{
		this.idGrupoConvenio = idGrupoConvenio;
	}

	public int getIdMapaCod()
	{
		return this.idMapaCod;
	}

	public void setIdMapaCod(int idMapaCod)
	{
		this.idMapaCod = idMapaCod;
	}

	public int getIdMapaNomDep()
	{
		return this.idMapaNomDep;
	}

	public void setIdMapaNomDep(int idMapaNomDep)
	{
		this.idMapaNomDep = idMapaNomDep;
	}

	public int getIdMapaNomGra()
	{
		return this.idMapaNomGra;
	}

	public void setIdMapaNomGra(int idMapaNomGra)
	{
		this.idMapaNomGra = idMapaNomGra;
	}

	public int getIdMapaNomRel()
	{
		return this.idMapaNomRel;
	}

	public void setIdMapaNomRel(int idMapaNomRel)
	{
		this.idMapaNomRel = idMapaNomRel;
	}

	public int getIdMapaNomRem()
	{
		return this.idMapaNomRem;
	}

	public void setIdMapaNomRem(int idMapaNomRem)
	{
		this.idMapaNomRem = idMapaNomRem;
	}

	public int getIdOpcion()
	{
		return this.idOpcion;
	}

	public void setIdOpcion(int idOpcion)
	{
		this.idOpcion = idOpcion;
	}

	public String getNombre()
	{
		return this.nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public int getNumBloqueos()
	{
		return this.numBloqueos;
	}

	public void setNumBloqueos(int numBloqueos)
	{
		this.numBloqueos = numBloqueos;
	}

	public int hashCode()
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.idGrupoConvenio;
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
		final GrupoConvenioVO other = (GrupoConvenioVO) obj;
		if (this.idGrupoConvenio != other.idGrupoConvenio)
			return false;
		return true;
	}

	public int compareTo(Object o)
	{
		Integer yo = new Integer(this.idGrupoConvenio);
		Integer otro = new Integer(((GrupoConvenioVO) o).idGrupoConvenio);
		return yo.compareTo(otro);
	}

	public String toString()
	{
		return "GrupoConvenioVO[idGrupoConvenio: " + this.idGrupoConvenio + ", idEmpresa: " + this.idEmpresa + ", nombre: \"" + this.nombre + "\", idMapaCod: " + this.idMapaCod + ", idMapaNomRem: "
				+ this.idMapaNomRem + ", idMapaNomGra: " + this.idMapaNomGra + ", idMapaNomRel: " + this.idMapaNomRel + ", idMapaNomDep: " + this.idMapaNomDep + ", idOpcion: " + this.idOpcion
				+ ", creado: " + (this.creado == null ? "" : this.creado.toString()) + ", habilitado: " + this.habilitado + ", numBloqueos: " + this.numBloqueos + ", previred: " + this.previred + ", prodCaja: " + this.prodCaja +"]";
	}

	public int getIdMapaNom(char tipoProceso)
	{
		if (tipoProceso == 'R' || tipoProceso == 'r')
			return this.idMapaNomRem;
		else if (tipoProceso == 'G' || tipoProceso == 'g')
			return this.idMapaNomGra;
		else if (tipoProceso == 'A' || tipoProceso == 'a')
			return this.idMapaNomRel;
		else if (tipoProceso == 'D' || tipoProceso == 'D')
			return this.idMapaNomDep;
		return 0;
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.idGrupoConvenio));
		parametros.put("2", String.valueOf(this.idEmpresa));
		parametros.put("3", String.valueOf(this.idMapaNomRem));
		parametros.put("4", String.valueOf(this.idMapaNomGra));
		parametros.put("5", String.valueOf(this.idMapaNomDep));
		parametros.put("6", String.valueOf(this.idMapaNomRel));
		parametros.put("7", String.valueOf(this.idMapaCod));
		parametros.put("8", String.valueOf(this.idOpcion));
		parametros.put("9", String.valueOf(this.nombre));
		parametros.put("10", String.valueOf(this.creado));
		parametros.put("11", String.valueOf(this.habilitado));
		parametros.put("12", String.valueOf(this.numBloqueos));
		parametros.put("13", String.valueOf(this.previred));
		parametros.put("14", String.valueOf(this.prodCaja));
		return parametros;
	}

	public boolean isPrevired()
	{
		return this.previred;
	}

	public void setPrevired(boolean previred)
	{
		this.previred = previred;
	}

	public int getConfigBase()
	{
		return this.configBase;
	}

	public void setConfigBase(int configBase)
	{
		this.configBase = configBase;
	}

	/**
	 * @return el prodCaja
	 */
	public boolean isProdCaja() {
		return prodCaja;
	}

	/**
	 * @param prodCaja el prodCaja a establecer
	 */
	public void setProdCaja(boolean prodCaja) {
		this.prodCaja = prodCaja;
	}
}