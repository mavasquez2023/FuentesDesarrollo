package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

public class MapeoGeneroVO extends MapeoVO 
{
	private static final long serialVersionUID = 5898857949338226394L;

	public MapeoGeneroVO() {}
	
	public MapeoGeneroVO(Integer idMapaCod, Integer id, String codigo)
	{
		super(idMapaCod, id, codigo);
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
