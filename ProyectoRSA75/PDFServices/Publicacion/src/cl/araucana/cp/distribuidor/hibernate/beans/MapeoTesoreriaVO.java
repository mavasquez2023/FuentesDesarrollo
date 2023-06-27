package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

public class MapeoTesoreriaVO extends AuditableVO
{
	private static final long serialVersionUID = 7500751887738074875L;
	private char idTipoNomina;
	private int idTipoSeccion;
	private int idDetalleSeccion;
	private int idConceptoTesoreria;
	private int idMontoDetSeccion;

	private String nombreNomina;
	private String nombreSeccion;
	private String nombreConcepto;

	private int idOrdenNomina;

	public String getNombreConcepto()
	{
		return this.nombreConcepto;
	}

	public void setNombreConcepto(String nombreConcepto)
	{
		this.nombreConcepto = nombreConcepto;
	}

	public String getNombreNomina()
	{
		return this.nombreNomina;
	}

	public void setNombreNomina(String nombreNomina)
	{
		this.nombreNomina = nombreNomina;
	}

	public String getNombreSeccion()
	{
		return this.nombreSeccion;
	}

	public void setNombreSeccion(String nombreSeccion)
	{
		this.nombreSeccion = nombreSeccion;
	}

	public MapeoTesoreriaVO()
	{
		super();
	}

	public MapeoTesoreriaVO(int idTipoSeccion, int idConceptoTesoreria)
	{
		super();
		this.idTipoSeccion = idTipoSeccion;
		this.idConceptoTesoreria = idConceptoTesoreria;
	}

	public MapeoTesoreriaVO(char idTipoNomina, int idTipoSeccion, int idDetalleSeccion, int idConceptoTesoreria, int idMontoDetSeccion)
	{
		super();
		this.idTipoNomina = idTipoNomina;
		this.idTipoSeccion = idTipoSeccion;
		this.idDetalleSeccion = idDetalleSeccion;
		this.idConceptoTesoreria = idConceptoTesoreria;
		this.idMontoDetSeccion = idMontoDetSeccion;
	}

	public MapeoTesoreriaVO(MapeoTesoreriaVO map)
	{
		super();
		this.idTipoNomina = map.getIdTipoNomina();
		this.idTipoSeccion = map.getIdTipoSeccion();
		this.idDetalleSeccion = map.getIdDetalleSeccion();
		this.idConceptoTesoreria = map.getIdConceptoTesoreria();
		this.idMontoDetSeccion = map.getIdMontoDetSeccion();
	}

	public int getIdConceptoTesoreria()
	{
		return this.idConceptoTesoreria;
	}

	public void setIdConceptoTesoreria(int idConceptoTesoreria)
	{
		this.idConceptoTesoreria = idConceptoTesoreria;
	}

	public int getIdMontoDetSeccion()
	{
		return this.idMontoDetSeccion;
	}

	public void setIdMontoDetSeccion(int idMontoDetSeccion)
	{
		this.idMontoDetSeccion = idMontoDetSeccion;
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

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.idTipoSeccion));
		parametros.put("2", String.valueOf(this.idTipoSeccion));
		parametros.put("3", String.valueOf(this.idDetalleSeccion));
		parametros.put("4", String.valueOf(this.idConceptoTesoreria));
		parametros.put("5", String.valueOf(this.idMontoDetSeccion));
		return parametros;
	}

	public boolean compareTo(MapeoTesoreriaVO _mapeoTesoreriaVO)
	{
		if (_mapeoTesoreriaVO.getIdTipoNomina() != this.getIdTipoNomina())
		{
			return false;
		}
		if (_mapeoTesoreriaVO.getIdConceptoTesoreria() != this.getIdConceptoTesoreria())
		{
			return false;
		}
		if (_mapeoTesoreriaVO.getIdTipoSeccion() != this.getIdTipoSeccion())
		{
			return false;
		}
		if (_mapeoTesoreriaVO.getIdDetalleSeccion() != this.getIdDetalleSeccion())
		{
			return false;
		}
		if (_mapeoTesoreriaVO.getIdMontoDetSeccion() != this.getIdMontoDetSeccion())
		{
			return false;
		}
		return true;
	}

	public int getIdOrdenNomina()
	{
		return this.idOrdenNomina;
	}

	public void setIdOrdenNomina(int idOrdenNomina)
	{
		this.idOrdenNomina = idOrdenNomina;
	}
}
