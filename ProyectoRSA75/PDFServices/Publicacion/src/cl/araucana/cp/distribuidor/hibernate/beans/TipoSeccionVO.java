package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;
import java.util.List;

public class TipoSeccionVO extends AuditableVO
{
	private static final long serialVersionUID = 837176666679943784L;
	private int id;
	private int dnp;
	private int pagoIndividual;
	private int muestraVertical;
	private String descripcion;
	private String clave;
	private String claseSeccionador;

	private String m1Nombre;
	private String m2Nombre;
	private String m3Nombre;
	private String m4Nombre;
	private String m5Nombre;
	private String m6Nombre;
	private String m7Nombre;
	private String m8Nombre;
	private String m9Nombre;
	private String m10Nombre;
	private String m11Nombre;
	private String m12Nombre;

	private String[] mNombre = new String[12];
	List tipoDetalle;

	public TipoSeccionVO()
	{
		super();
	}

	public TipoSeccionVO(int id, int dnp, int pagoIndividual, String descripcion, List tipoDetalle)
	{
		super();
		this.id = id;
		this.dnp = dnp;
		this.pagoIndividual = pagoIndividual;
		this.descripcion = descripcion;
		this.tipoDetalle = tipoDetalle;
	}

	public TipoSeccionVO(int id, int dnp, int pagoIndividual, String descripcion)
	{
		super();
		this.id = id;
		this.dnp = dnp;
		this.pagoIndividual = pagoIndividual;
		this.descripcion = descripcion;
	}
	
	public void refreshListasM()
	{
		this.mNombre[0] = this.m1Nombre;
		this.mNombre[1] = this.m2Nombre;
		this.mNombre[2] = this.m3Nombre;
		this.mNombre[3] = this.m4Nombre;
		this.mNombre[4] = this.m5Nombre;
		this.mNombre[5] = this.m6Nombre;
		this.mNombre[6] = this.m7Nombre;
		this.mNombre[7] = this.m8Nombre;
		this.mNombre[8] = this.m9Nombre;
		this.mNombre[9] = this.m10Nombre;
		this.mNombre[10] = this.m11Nombre;
		this.mNombre[11] = this.m12Nombre;
	}
	
	public String getNombre(int index)
	{
		return this.mNombre[index];
	}

	public String getDescripcion()
	{
		return this.descripcion;
	}
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	public int getDnp()
	{
		return this.dnp;
	}
	public void setDnp(int dnp)
	{
		this.dnp = dnp;
	}
	public int getId()
	{
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getPagoIndividual()
	{
		return this.pagoIndividual;
	}
	public void setPagoIndividual(int pagoIndividual)
	{
		this.pagoIndividual = pagoIndividual;
	}

	public List getTipoDetalle()
	{
		return this.tipoDetalle;
	}

	public void setTipoDetalle(List tipoDetalle)
	{
		this.tipoDetalle = tipoDetalle;
	}

	public String getM10Nombre()
	{
		return this.m10Nombre;
	}

	public void setM10Nombre(String nombre)
	{
		this.m10Nombre = nombre;
	}

	public String getM11Nombre()
	{
		return this.m11Nombre;
	}

	public void setM11Nombre(String nombre)
	{
		this.m11Nombre = nombre;
	}

	public String getM12Nombre()
	{
		return this.m12Nombre;
	}

	public void setM12Nombre(String nombre)
	{
		this.m12Nombre = nombre;
	}

	public String getM1Nombre()
	{
		return this.m1Nombre;
	}

	public void setM1Nombre(String nombre)
	{
		this.m1Nombre = nombre;
	}

	public String getM2Nombre()
	{
		return this.m2Nombre;
	}

	public void setM2Nombre(String nombre)
	{
		this.m2Nombre = nombre;
	}

	public String getM3Nombre()
	{
		return this.m3Nombre;
	}

	public void setM3Nombre(String nombre)
	{
		this.m3Nombre = nombre;
	}

	public String getM4Nombre()
	{
		return this.m4Nombre;
	}

	public void setM4Nombre(String nombre)
	{
		this.m4Nombre = nombre;
	}

	public String getM5Nombre()
	{
		return this.m5Nombre;
	}

	public void setM5Nombre(String nombre)
	{
		this.m5Nombre = nombre;
	}

	public String getM6Nombre()
	{
		return this.m6Nombre;
	}

	public void setM6Nombre(String nombre)
	{
		this.m6Nombre = nombre;
	}

	public String getM7Nombre()
	{
		return this.m7Nombre;
	}

	public void setM7Nombre(String nombre)
	{
		this.m7Nombre = nombre;
	}

	public String getM8Nombre()
	{
		return this.m8Nombre;
	}

	public void setM8Nombre(String nombre)
	{
		this.m8Nombre = nombre;
	}

	public String getM9Nombre()
	{
		return this.m9Nombre;
	}

	public void setM9Nombre(String nombre)
	{
		this.m9Nombre = nombre;
	}

	public int getMuestraVertical()
	{
		return this.muestraVertical;
	}

	public void setMuestraVertical(int muestraVertical)
	{
		this.muestraVertical = muestraVertical;
	}

	public String getClaseSeccionador()
	{
		return this.claseSeccionador;
	}

	public void setClaseSeccionador(String claseSeccionador)
	{
		this.claseSeccionador = claseSeccionador;
	}

	public String getClave()
	{
		return this.clave;
	}

	public void setClave(String clave)
	{
		this.clave = clave;
	}
	
	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.id));
		parametros.put("2", String.valueOf(this.dnp));
		parametros.put("3", String.valueOf(this.pagoIndividual));
		parametros.put("4", String.valueOf(this.muestraVertical));
		parametros.put("5", String.valueOf(this.descripcion));
		parametros.put("6", String.valueOf(this.m1Nombre));
		parametros.put("7", String.valueOf(this.m2Nombre));
		parametros.put("8", String.valueOf(this.m3Nombre));
		parametros.put("9", String.valueOf(this.m4Nombre));
		parametros.put("10", String.valueOf(this.m5Nombre));
		parametros.put("11", String.valueOf(this.m6Nombre));
		parametros.put("12", String.valueOf(this.m7Nombre));
		parametros.put("13", String.valueOf(this.m8Nombre));
		parametros.put("14", String.valueOf(this.m9Nombre));
		parametros.put("15", String.valueOf(this.m10Nombre));
		parametros.put("16", String.valueOf(this.m11Nombre));
		parametros.put("17", String.valueOf(this.m12Nombre));
		parametros.put("18", String.valueOf(this.clave));
		parametros.put("19", String.valueOf(this.claseSeccionador));
		return parametros;
	}

	public String getM(int index)
	{
		return this.mNombre[index];
	}
}
