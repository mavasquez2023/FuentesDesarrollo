package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;


public class EstadoNominaVO extends EstadoVO
{
	private static final long serialVersionUID = -7002353974298364931L;

	public EstadoNominaVO()
	{
		super();
	}

	public EstadoNominaVO(int id, String descripcion)
	{
		super(id, descripcion);
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.id));
		parametros.put("2", String.valueOf(this.descripcion));
		parametros.put("3", String.valueOf(this.acciones));
		return parametros;
	}
}
