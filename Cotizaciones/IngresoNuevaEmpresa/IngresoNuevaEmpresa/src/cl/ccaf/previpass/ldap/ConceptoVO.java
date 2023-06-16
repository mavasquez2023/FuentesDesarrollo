package cl.ccaf.previpass.ldap;

import java.io.Serializable;

public class ConceptoVO implements Serializable
{
	private static final long serialVersionUID = -5447771714944867917L;
	private char tipoProceso;
	private int id;
	
	private int obligatorio;
	private int largoMin;
	private int largoMax;
	private String valor;
	private String nombre;
	private String descripcion;

	public String getDescripcion()
	{
		return this.descripcion;
	}
	/**
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	public int getId()
	{
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getLargoMax()
	{
		return this.largoMax;
	}
	public void setLargoMax(int largoMax)
	{
		this.largoMax = largoMax;
	}
	public int getLargoMin()
	{
		return this.largoMin;
	}
	public void setLargoMin(int largoMin)
	{
		this.largoMin = largoMin;
	}
	public String getNombre()
	{
		return this.nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	public int getObligatorio()
	{
		return this.obligatorio;
	}
	public void setObligatorio(int obligatorio)
	{
		this.obligatorio = obligatorio;
	}
	public char getTipoProceso()
	{
		return this.tipoProceso;
	}
	public void setTipoProceso(char tipoProceso)
	{
		this.tipoProceso = tipoProceso;
	}
	public String getValor()
	{
		return this.valor;
	}
	public void setValor(String valor)
	{
		this.valor = valor;
	}
	public String toString()
	{
		return this.id + ":"  + this.tipoProceso + ":" + this.nombre + ":" + this.obligatorio + ":" + this.largoMin + ":" + 
				this.largoMax + ":" + this.descripcion + ":" + this.valor + "::";
	}

	public int hashCode() 
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.id;
		result = PRIME * result + this.tipoProceso;
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
		final ConceptoVO other = (ConceptoVO) obj;
		if (this.id != other.id)
			return false;
		if (this.tipoProceso != other.tipoProceso)
			return false;
		return true;
	}

	public StringBuffer toLine() throws Exception
	{
		return new StringBuffer().append(this.id).append('#').append(this.obligatorio).append('#').append(this.nombre.trim());
	}
}
