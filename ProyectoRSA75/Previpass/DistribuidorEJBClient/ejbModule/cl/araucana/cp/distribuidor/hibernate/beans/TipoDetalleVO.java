package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class TipoDetalleVO extends AuditableVO
{
	private static final long serialVersionUID = -4155365388957936309L;
	private char idTipoNomina;
	private int idTipoSeccion;
	private int idDetalleSeccion;
	private int idEntPagadora;
	private String nombre;
	private TipoSeccionVO tipoSeccion;

	List mapeoTesoreria;

	public TipoDetalleVO()
	{
		super();
	}

	public TipoDetalleVO(char idTipoNomina, int idTipoSeccion, int idDetalleSeccion, int idEntPagadora, String nombre, List mapeoTesoreria)
	{
		super();
		this.idTipoNomina = idTipoNomina;
		this.idTipoSeccion = idTipoSeccion;
		this.idDetalleSeccion = idDetalleSeccion;
		this.idEntPagadora = idEntPagadora;
		this.nombre = nombre;
		this.mapeoTesoreria = mapeoTesoreria;
	}

	public TipoDetalleVO(TipoDetalleVO tipo)
	{
		super();
		this.idTipoNomina = tipo.getIdTipoNomina();
		this.idTipoSeccion = tipo.getIdTipoSeccion();
		this.idDetalleSeccion = tipo.getIdDetalleSeccion();
		this.idEntPagadora = tipo.getIdEntPagadora();
		//this.nombre = tipo.getNombre();
		//this.mapeoTesoreria = tipo.getMapeoTesoreria();
	}

	public TipoDetalleVO(char idTipoNomina, int idTipoSeccion, int idDetalleSeccion, String nombre, int idEntPagadora)
	{
		super();
		this.idTipoNomina = idTipoNomina;
		this.idTipoSeccion = idTipoSeccion;
		this.idDetalleSeccion = idDetalleSeccion;
		this.idEntPagadora = idEntPagadora;
		this.nombre = nombre;
	}

	public TipoDetalleVO(char idTipoNomina, int idTipoSeccion, int idDetalleSeccion)
	{
		super();
		this.idTipoNomina = idTipoNomina;
		this.idTipoSeccion = idTipoSeccion;
		this.idDetalleSeccion = idDetalleSeccion;
	}

	public int getIdDetalleSeccion()
	{
		return this.idDetalleSeccion;
	}

	public void setIdDetalleSeccion(int idDetalleSeccion)
	{
		this.idDetalleSeccion = idDetalleSeccion;
	}

	public char getIdTipoNomina()
	{
		return this.idTipoNomina;
	}

	public void setIdTipoNomina(char idTipoNomina)
	{
		this.idTipoNomina = idTipoNomina;
	}

	public int getIdTipoSeccion()
	{
		return this.idTipoSeccion;
	}

	public void setIdTipoSeccion(int idTipoSeccion)
	{
		this.idTipoSeccion = idTipoSeccion;
	}

	public String getNombre()
	{
		return this.nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public int getIdEntPagadora()
	{
		return this.idEntPagadora;
	}

	public void setIdEntPagadora(int idEntPagadora)
	{
		this.idEntPagadora = idEntPagadora;
	}

	public List getMapeoTesoreria()
	{
		return this.mapeoTesoreria;
	}

	public void setMapeoTesoreria(List mapeoTesoreria)
	{
		this.mapeoTesoreria = mapeoTesoreria;
	}

	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}

	public TipoSeccionVO getTipoSeccion()
	{
		return this.tipoSeccion;
	}

	public void setTipoSeccion(TipoSeccionVO tipoSeccion)
	{
		this.tipoSeccion = tipoSeccion;
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.idTipoNomina));
		parametros.put("2", String.valueOf(this.idTipoSeccion));
		parametros.put("3", String.valueOf(this.idDetalleSeccion));
		parametros.put("4", String.valueOf(this.idEntPagadora));
		return parametros;
	}

	public int hashCode()
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.idDetalleSeccion;
		result = PRIME * result + this.idTipoNomina;
		result = PRIME * result + this.idTipoSeccion;
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
		final TipoDetalleVO other = (TipoDetalleVO) obj;
		if (this.idDetalleSeccion != other.idDetalleSeccion)
			return false;
		if (this.idTipoNomina != other.idTipoNomina)
			return false;
		if (this.idTipoSeccion != other.idTipoSeccion)
			return false;
		return true;
	}
}
