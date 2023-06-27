package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class NodoSiguienteVO implements Serializable
{
	private static final long serialVersionUID = 2743922932412574348L;
	private String idValidacion;
	private char tipoProceso;
	private String idSiguiente;

	private String valor;

	public String getIdSiguiente()
	{
		return this.idSiguiente;
	}

	public void setIdSiguiente(String idSiguiente)
	{
		this.idSiguiente = idSiguiente;
	}

	public String getIdValidacion()
	{
		return this.idValidacion;
	}

	public void setIdValidacion(String idValidacion)
	{
		this.idValidacion = idValidacion;
	}

	public String getValor()
	{
		return this.valor;
	}

	public void setValor(String valor)
	{
		this.valor = valor;
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
		return "\t:" + this.getIdValidacion() + ":" + this.getTipoProceso() + ":" + this.getValor() + "=" + this.getIdSiguiente() + ":";
	}

	public StringBuffer toLine()
	{
		return new StringBuffer(this.idSiguiente.trim()).append('#').append(this.valor.trim()).append('&');
	}

	public int hashCode()
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((this.idValidacion == null) ? 0 : this.idValidacion.hashCode());
		result = PRIME * result + this.tipoProceso;
		result = PRIME * result + ((this.valor == null) ? 0 : this.valor.hashCode());
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
		final NodoSiguienteVO other = (NodoSiguienteVO) obj;
		if (this.idValidacion == null)
		{
			if (other.idValidacion != null)
				return false;
		} else if (!this.idValidacion.equals(other.idValidacion))
			return false;
		if (this.tipoProceso != other.tipoProceso)
			return false;
		if (this.valor == null)
		{
			if (other.valor != null)
				return false;
		} else if (!this.valor.equals(other.valor))
			return false;
		return true;
	}
}
