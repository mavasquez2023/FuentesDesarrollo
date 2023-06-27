package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

public class CiudadVO extends AuditableVO
{
	private static final long serialVersionUID = -7645848306822781104L;

	private int idCiudad;
	private RegionVO region;
	private String nombre;
	private String codigoArea;
	
	public CiudadVO() {}
	
	public CiudadVO(int idCiudad)
	{
		super();
		this.idCiudad = idCiudad;
	}

	public String getCodigoArea()
	{
		return this.codigoArea;
	}
	
	public void setCodigoArea(String codigoArea)
	{
		this.codigoArea = codigoArea;
	}
	
	public int getIdCiudad()
	{
		return this.idCiudad;
	}
	
	public void setIdCiudad(int idCiudad)
	{
		this.idCiudad = idCiudad;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	
	public RegionVO getRegion()
	{
		return this.region;
	}
	
	public void setRegion(RegionVO region)
	{
		this.region = region;
	}
	
	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		
		parametros.put("1", String.valueOf(this.idCiudad));
		parametros.put("2", String.valueOf(this.region.getCodigo()));
		parametros.put("3", String.valueOf(this.nombre));
		parametros.put("4", String.valueOf(this.codigoArea));
		
		return parametros;
		
	}	
}
