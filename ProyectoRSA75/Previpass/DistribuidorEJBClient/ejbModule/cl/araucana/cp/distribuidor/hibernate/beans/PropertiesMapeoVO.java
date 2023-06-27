package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class PropertiesMapeoVO implements Serializable
{
	private static final long serialVersionUID = 8866494695257607359L;
	private int id;
	private int tipo;
	private String clave;
	private String valor;

	public PropertiesMapeoVO()
	{
		super();
	}

	public PropertiesMapeoVO(int id, int tipo, String clave, String valor)
	{
		super();
		this.id = id;
		this.tipo = tipo;
		this.clave = clave;
		this.valor = valor;
	}

	public String getClave()
	{
		return this.clave;
	}
	public void setClave(String clave)
	{
		this.clave = clave;
	}
	public int getId()
	{
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getTipo()
	{
		return this.tipo;
	}
	public void setTipo(int tipo)
	{
		this.tipo = tipo;
	}
	public String getValor()
	{
		return this.valor;
	}
	public void setValor(String valor)
	{
		this.valor = valor;
	}
}
