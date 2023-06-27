package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

public class DescriptorNominaVO extends AuditableVO
{
	private static final long serialVersionUID = 9028261452683642440L;
	private int idEnvio;
	private int idConvenio;
	private char tipoProceso;
	private int rutEmpresa;
	
	private String nombre;
	private int idRechazo;
	private int idGrupoConvenio;
	private int normalSize;
	private int comprimidoSize;
	private int numRegistros;

	public DescriptorNominaVO()
	{
		super();
	}

	public DescriptorNominaVO(int idEnvio, int idConvenio, char tipoProceso, int rutEmpresa)
	{
		super();
		this.idEnvio = idEnvio;
		this.idConvenio = idConvenio;
		this.tipoProceso = tipoProceso;
		this.rutEmpresa = rutEmpresa;
	}

	public DescriptorNominaVO(int idEnvio, int idConvenio, char tipoProceso, int rutEmpresa, int idRechazo, int idGrupoConvenio, int normalSize, int comprimidoSize, int numRegistros)
	{
		super();
		this.idEnvio = idEnvio;
		this.idConvenio = idConvenio;
		this.tipoProceso = tipoProceso;
		this.rutEmpresa = rutEmpresa;
		this.idRechazo = idRechazo;
		this.idGrupoConvenio = idGrupoConvenio;
		this.normalSize = normalSize;
		this.comprimidoSize = comprimidoSize;
		this.numRegistros = numRegistros;
	}

	public int hashCode() 
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.idConvenio;
		result = PRIME * result + this.idEnvio;
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
		final DescriptorNominaVO other = (DescriptorNominaVO) obj;
		if (this.idConvenio != other.idConvenio)
			return false;
		if (this.idEnvio != other.idEnvio)
			return false;
		if (this.rutEmpresa != other.rutEmpresa)
			return false;
		if (this.tipoProceso != other.tipoProceso)
			return false;
		return true;
	}

	public int getComprimidoSize()
	{
		return this.comprimidoSize;
	}
	public void setComprimidoSize(int comprimidoSize)
	{
		this.comprimidoSize = comprimidoSize;
	}
	public int getIdConvenio()
	{
		return this.idConvenio;
	}
	public void setIdConvenio(int idConvenio)
	{
		this.idConvenio = idConvenio;
	}
	public int getIdEnvio()
	{
		return this.idEnvio;
	}
	public void setIdEnvio(int idEnvio)
	{
		this.idEnvio = idEnvio;
	}
	public int getIdGrupoConvenio()
	{
		return this.idGrupoConvenio;
	}
	public void setIdGrupoConvenio(int idGrupoConvenio)
	{
		this.idGrupoConvenio = idGrupoConvenio;
	}
	public int getIdRechazo()
	{
		return this.idRechazo;
	}
	public void setIdRechazo(int idRechazo)
	{
		this.idRechazo = idRechazo;
	}
	public int getNormalSize()
	{
		return this.normalSize;
	}
	public void setNormalSize(int normalSize)
	{
		this.normalSize = normalSize;
	}
	public int getNumRegistros()
	{
		return this.numRegistros;
	}
	public void setNumRegistros(int numRegistros)
	{
		this.numRegistros = numRegistros;
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

	public void setTipoProceso(char tipoNomina)
	{
		this.tipoProceso = tipoNomina;
	}
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.idConvenio));
		parametros.put("2", String.valueOf(this.idEnvio));
		parametros.put("3", String.valueOf(this.tipoProceso));
		parametros.put("4", String.valueOf(this.rutEmpresa));
		parametros.put("5", String.valueOf(this.idRechazo));
		parametros.put("6", String.valueOf(this.idGrupoConvenio));
		parametros.put("7", String.valueOf(this.normalSize));
		parametros.put("8", String.valueOf(this.comprimidoSize));
		parametros.put("9", String.valueOf(this.numRegistros));
		return parametros;
	}
}
