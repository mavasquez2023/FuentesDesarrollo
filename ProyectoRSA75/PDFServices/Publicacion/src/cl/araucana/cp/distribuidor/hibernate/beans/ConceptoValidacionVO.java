package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class ConceptoValidacionVO implements Serializable
{
	private static final long serialVersionUID = -3093244111669240726L;
	private String idValidacion;
	private char tipoProceso;
	private int idConcepto;

	private int orden;

	public int getIdConcepto()
	{
		return this.idConcepto;
	}

	public void setIdConcepto(int idConcepto)
	{
		this.idConcepto = idConcepto;
	}

	public String getIdValidacion()
	{
		return this.idValidacion;
	}

	public void setIdValidacion(String idValidacion)
	{
		this.idValidacion = idValidacion;
	}

	public int getOrden()
	{
		return this.orden;
	}

	public void setOrden(int orden)
	{
		this.orden = orden;
	}

	public char getTipoProceso()
	{
		return this.tipoProceso;
	}

	public void setTipoProceso(char tipoProceso)
	{
		this.tipoProceso = tipoProceso;
	}

	public String toString()
	{
		return "\t:" + this.getIdValidacion() + ":" + this.getTipoProceso() + ":" + this.getIdConcepto() + ":" + this.getOrden() + ":";
	}

	public StringBuffer toLine()
	{
		return new StringBuffer().append(this.idConcepto).append("&");
	}

	public int hashCode()
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.idConcepto;
		result = PRIME * result + ((this.idValidacion == null) ? 0 : this.idValidacion.hashCode());
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
		final ConceptoValidacionVO other = (ConceptoValidacionVO) obj;
		if (this.idConcepto != other.idConcepto)
			return false;
		if (this.idValidacion == null)
		{
			if (other.idValidacion != null)
				return false;
		} else if (!this.idValidacion.equals(other.idValidacion))
			return false;
		if (this.tipoProceso != other.tipoProceso)
			return false;
		return true;
	}
}
