package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

public class ParametroVO extends AuditableVO
{
	private static final long serialVersionUID = -66268122106193133L;
	private int id;
	private String nombre;
	private String descripcion;
	private String valor;
	private String tipoParametro;
	private String tipoValor;
	
	public String getTipoParametro() {
		return this.tipoParametro;
	}

	public void setTipoParametro(String tipoParametro) {
		this.tipoParametro = tipoParametro;
	}

	public String getTipoValor() {
		return this.tipoValor;
	}

	public void setTipoValor(String tipoValor) {
		this.tipoValor = tipoValor;
	}

	public ParametroVO()
	{
		super();
	}

	public void setValor(String valor)
	{
		this.valor = valor;
	}

	public String getDescripcion()
	{
		return this.descripcion;
	}
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
	public String getNombre()
	{
		return this.nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	public String getValor()
	{
		return this.valor;
	}
	public ParametroVO(int id, String nombre, String descripcion, String valor)
	{
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.valor = valor;
	}
	
	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.id));
		parametros.put("2", String.valueOf(this.nombre));
		parametros.put("3", String.valueOf(this.descripcion));
		parametros.put("4", String.valueOf(this.valor));
		return parametros;
	}
}
