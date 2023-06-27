package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

public class SucursalVO extends AuditableVO implements Comparable 
{
	private static final long serialVersionUID = 5111634663595913374L;

	private int idEmpresa;
	private String idSucursal;
	private ComunaVO comuna;
	private String nombre;
	private String email;
	private String telefono;
	private int celular;
	private String fax;
	private String direccion;
	private String numero;
	private String departamento;
	private Boolean eliminable;
	private Boolean editable;

	private String esCasaMatriz;

	public SucursalVO()
	{
		super();
	}

	public SucursalVO(int idEmpresa, String idSucursal)
	{
		super();
		this.idEmpresa = idEmpresa;
		this.idSucursal = idSucursal;
	}

	public SucursalVO(int idEmpresa, String idSucursal, ComunaVO comuna, String nombre, String email, String telefono, int celular, String fax, String direccion, String numero, String departamento)
	{
		super();
		this.idEmpresa = idEmpresa;
		this.idSucursal = idSucursal;
		this.comuna = comuna;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.celular = celular;
		this.fax = fax;
		this.direccion = direccion;
		this.numero = numero;
		this.departamento = departamento;
	}

	public int getCelular()
	{
		return this.celular;
	}

	public void setCelular(int celular)
	{
		this.celular = celular;
	}

	public String getDepartamento()
	{
		return this.departamento;
	}

	public void setDepartamento(String departamento)
	{
		this.departamento = departamento;
	}

	public String getDireccion()
	{
		return this.direccion;
	}

	public void setDireccion(String direccion)
	{
		this.direccion = direccion;
	}

	public String getEmail()
	{
		return this.email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getFax()
	{
		return this.fax;
	}

	public void setFax(String fax)
	{
		this.fax = fax;
	}

	public int getIdEmpresa()
	{
		return this.idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa)
	{
		this.idEmpresa = idEmpresa;
	}

	public String getIdSucursal()
	{
		return this.idSucursal;
	}

	public void setIdSucursal(String idSucursal)
	{
		this.idSucursal = idSucursal;
	}

	public String getNombre()
	{
		return this.nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public String getNumero()
	{
		return this.numero;
	}

	public void setNumero(String numero)
	{
		this.numero = numero;
	}

	public String getTelefono()
	{
		return this.telefono;
	}

	public void setTelefono(String telefono)
	{
		this.telefono = telefono;
	}

	public ComunaVO getComuna()
	{
		return this.comuna;
	}

	public void setComuna(ComunaVO comuna)
	{
		this.comuna = comuna;
	}

	public String toString()
	{
		return "SucursalVO[idSucursal:" + this.idSucursal.trim() + ":, idEmpresa:" + this.idEmpresa + ":, nombre:" + this.nombre.trim() + ":, direccion:" + this.direccion.trim() + ":, numero:"
				+ this.numero.trim() + ":, departamento:" + this.departamento.trim() + ":, comuna:" + this.comuna + ":, email:" + this.email.trim() + ":, celular:" + this.celular + ":, telefono:"
				+ this.telefono.trim() + ":, fax:" + this.fax.trim() + ":]";
	}

	public String getEsCasaMatriz()
	{
		return this.esCasaMatriz;
	}

	public void setEsCasaMatriz(String esCasaMatriz)
	{
		this.esCasaMatriz = esCasaMatriz;
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.idEmpresa));
		parametros.put("2", this.idSucursal.trim());
		parametros.put("3", String.valueOf(this.comuna.getIdComuna()));
		parametros.put("4", this.nombre.trim());
		parametros.put("5", this.email.trim());
		parametros.put("6", this.telefono);
		parametros.put("7", String.valueOf(this.celular));
		parametros.put("8", this.fax.trim());
		parametros.put("9", this.direccion.trim());
		parametros.put("10", this.numero.trim());
		parametros.put("11", this.departamento.trim());
		return parametros;
	}

	public Boolean getEditable()
	{
		return this.editable;
	}

	public void setEditable(Boolean editable)
	{
		this.editable = editable;
	}

	public Boolean getEliminable()
	{
		return this.eliminable;
	}

	public void setEliminable(Boolean eliminable)
	{
		this.eliminable = eliminable;
	}

	public int hashCode()
	{
		final int PRIME = 31;
		int result = super.hashCode();
		result = PRIME * result + this.idEmpresa;
		result = PRIME * result + ((this.idSucursal == null) ? 0 : this.idSucursal.hashCode());
		return result;
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final SucursalVO other = (SucursalVO) obj;
		if (this.idEmpresa != other.idEmpresa)
			return false;
		if (this.idSucursal == null)
		{
			if (other.idSucursal != null)
				return false;
		} else if (!this.idSucursal.equals(other.idSucursal))
			return false;
		return true;
	}

	public int compareTo(Object o)
	{		
		return this.idSucursal.compareTo(((SucursalVO)o).getIdSucursal());
	}
}
