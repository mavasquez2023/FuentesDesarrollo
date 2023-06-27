package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;


public class MapeoVO extends AuditableVO implements Comparable
{
	private static final long serialVersionUID = 833344881870425601L;
	protected int idMapaCod;
	protected int id;
	protected String codigo;
	protected EntidadVO entidad;

	public MapeoVO(Integer idMapaCod, Integer id, String codigo)
	{
		super();
		this.idMapaCod = idMapaCod.intValue();
		this.id = id.intValue();
		this.codigo = codigo;
	}

	public MapeoVO(int idMapaCod, int id, String codigo)
	{
		super();
		this.idMapaCod = idMapaCod;
		this.id = id;
		this.codigo = codigo;
	}

	public MapeoVO()
	{
		super();
	}

	public String getCodigo()
	{
		return this.codigo;
	}
	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}
	public int getId()
	{
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getIdMapaCod()
	{
		return this.idMapaCod;
	}
	public void setIdMapaCod(int idMapaCod)
	{
		this.idMapaCod = idMapaCod;
	}

	public EntidadVO getEntidad()
	{
		return this.entidad;
	}

	public void setEntidad(EntidadVO entidad)
	{
		this.entidad = entidad;
	}
	
	public String toString() 
	{
		return "MapeoVO[idMapaCod:" + this.idMapaCod + ":id:" + this.id + ":codigo:" + this.codigo + ":]";
	}

	public int compareTo(Object o) 
	{
		if (this.entidad.nombre.equals(((MapeoVO) o).entidad.nombre))
			return this.entidad.nombre.compareTo(o.toString());
		return this.codigo.compareTo(o.toString());
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.idMapaCod));
		parametros.put("2", String.valueOf(this.id));
		parametros.put("3", String.valueOf(this.codigo));
		return parametros;
	}
}
