package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

public class RutEspecialesVO extends AuditableVO
{
	private static final long serialVersionUID = 4181849394530450620L;
	private int rutEspecial;
	private String nombre;
	private String apellidoMaterno;
	private String apellidoPaterno;
	
	public RutEspecialesVO()
	{}

	public int getRutEspecial()
	{
		return this.rutEspecial;
	}
	public void setRutEspecial(int rutEspecial)
	{
		this.rutEspecial = rutEspecial;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	
	public String getApellidoMaterno()
	{
		return this.apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno)
	{
		this.apellidoMaterno = apellidoMaterno;
	}
	
	public String getApellidoPaterno()
	{
		return this.apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno)
	{
		this.apellidoPaterno = apellidoPaterno;
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.rutEspecial));
		parametros.put("2", String.valueOf(this.nombre));
		parametros.put("3", String.valueOf(this.apellidoMaterno));
		parametros.put("4", String.valueOf(this.apellidoMaterno));
		return parametros;
	}
}
