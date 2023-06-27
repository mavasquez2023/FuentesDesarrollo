package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

public class TipoMovimientoPersonalVO extends EntidadVO
{
	private static final long serialVersionUID = -8942272886403674589L;
	
	private int fechaTerminoObligatoria;
	
	public int getFechaTerminoObligatoria()
	{
		return this.fechaTerminoObligatoria;
	}
	public void setFechaTerminoObligatoria(int fechaTerminoObligatoria)
	{
		this.fechaTerminoObligatoria = fechaTerminoObligatoria;
	}
	
	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.id));
		parametros.put("2", String.valueOf(this.nombre));
		parametros.put("3", String.valueOf(this.fechaTerminoObligatoria));
		return parametros;
	}
}
