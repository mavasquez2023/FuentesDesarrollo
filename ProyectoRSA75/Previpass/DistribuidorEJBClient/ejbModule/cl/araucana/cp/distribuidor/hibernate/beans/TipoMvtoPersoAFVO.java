package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

public class TipoMvtoPersoAFVO extends EntidadVO
{
	private static final long serialVersionUID = -1801356618124414293L;
	private int fechaTerminoObligatoria;
	private int fechaInicioObligatoria;
	
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
		parametros.put("4", String.valueOf(this.fechaInicioObligatoria));
		return parametros;
	}
	public int getFechaInicioObligatoria() {
		return this.fechaInicioObligatoria;
	}
	public void setFechaInicioObligatoria(int fechaInicioObligatoria) {
		this.fechaInicioObligatoria = fechaInicioObligatoria;
	}	
}
