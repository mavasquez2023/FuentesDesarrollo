package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

public class RegionVO extends AuditableVO
{
	private static final long serialVersionUID = 4965678370675563349L;

	private int idRegion;
	private String nombre;
	private String codigo;
	
	public RegionVO() {}
	
	public RegionVO(int idRegion)
	{
		super();
		this.idRegion = idRegion;
	}
	
	public String getCodigo()
	{
		return this.codigo;
	}
	
	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}
	
	public int getIdRegion()
	{
		return this.idRegion;
	}
	
	public void setIdRegion(int idRegion)
	{
		this.idRegion = idRegion;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	
	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.idRegion));
		parametros.put("2", String.valueOf(this.nombre));
		parametros.put("3", String.valueOf(this.codigo));
		return parametros;
	}
}
