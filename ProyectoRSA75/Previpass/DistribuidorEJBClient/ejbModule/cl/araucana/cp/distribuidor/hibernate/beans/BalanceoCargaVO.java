package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;
import java.util.Set;

public class BalanceoCargaVO implements Serializable
{
	private static final long serialVersionUID = -2178836838077170454L;
	private int rutEmpresa;
	private int idConvenio;
	private char tipoProceso;

	private int numPeriodos;
	private int numLineas;
	private float cargaPromedio;
	
	Set descriptores;

	public Set getDescriptores()
	{
		return this.descriptores;
	}

	public void setDescriptores(Set descriptores)
	{
		this.descriptores = descriptores;
	}

	public BalanceoCargaVO(char tipoProceso, int rutEmpresa, int idConvenio)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.tipoProceso = tipoProceso;
	}

	public BalanceoCargaVO(int rutEmpresa, int idConvenio, char tipoProceso, int numPeriodos, int numLineas, int cargaPromedio)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.tipoProceso = tipoProceso;
		this.numPeriodos = numPeriodos;
		this.numLineas = numLineas;
		this.cargaPromedio = cargaPromedio;
	}

	public BalanceoCargaVO()
	{
		super();
	}

	public float getCargaPromedio()
	{
		return this.cargaPromedio;
	}
	public void setCargaPromedio(float cargaPromedio)
	{
		this.cargaPromedio = cargaPromedio;
	}
	public int getIdConvenio()
	{
		return this.idConvenio;
	}
	public void setIdConvenio(int idConvenio)
	{
		this.idConvenio = idConvenio;
	}
	public int getNumLineas()
	{
		return this.numLineas;
	}
	public void setNumLineas(int numLineas)
	{
		this.numLineas = numLineas;
	}
	public void addNumLineas(int numLin)
	{
		this.numLineas += numLin;
	}
	public int getNumPeriodos()
	{
		return this.numPeriodos;
	}
	public void setNumPeriodos(int numPeriodos)
	{
		this.numPeriodos = numPeriodos;
	}
	public void sumNumPeriodos()
	{
		this.numPeriodos++;
	}
	public int getRutEmpresa()
	{
		return this.rutEmpresa;
	}
	public void setRutEmpresa(int rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
	}
	public char getTipoProceso()
	{
		return this.tipoProceso;
	}
	public void setTipoProceso(char tipoProceso)
	{
		this.tipoProceso = tipoProceso;
	}

	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int)Double.doubleToLongBits(this.cargaPromedio);
		result = PRIME * result + ((this.descriptores == null) ? 0 : this.descriptores.hashCode());
		result = PRIME * result + this.idConvenio;
		result = PRIME * result + this.numLineas;
		result = PRIME * result + this.numPeriodos;
		result = PRIME * result + this.rutEmpresa;
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
		final BalanceoCargaVO other = (BalanceoCargaVO) obj;
		if ((int)Double.doubleToLongBits(this.cargaPromedio) != (int)Double.doubleToLongBits(other.cargaPromedio))
			return false;
		if (this.descriptores == null) {
			if (other.descriptores != null)
				return false;
		} else if (!this.descriptores.equals(other.descriptores))
			return false;
		if (this.idConvenio != other.idConvenio)
			return false;
		if (this.numLineas != other.numLineas)
			return false;
		if (this.numPeriodos != other.numPeriodos)
			return false;
		if (this.rutEmpresa != other.rutEmpresa)
			return false;
		if (this.tipoProceso != other.tipoProceso)
			return false;
		return true;
	}
}
