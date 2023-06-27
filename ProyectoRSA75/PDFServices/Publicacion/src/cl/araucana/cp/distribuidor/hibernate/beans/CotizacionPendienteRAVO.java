package cl.araucana.cp.distribuidor.hibernate.beans;


public class CotizacionPendienteRAVO extends CotizacionPendienteVO
{
	private static final long serialVersionUID = -6214786015821284487L;
	
	public CotizacionPendienteRAVO()
	{
		super();
	}

	public CotizacionPendienteRAVO(int rutEmpresa, int idConvenio, int idCotizPendiente)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizPendiente = idCotizPendiente;
	}

	public CotizacionPendienteRAVO(int rutEmpresa, int idConvenio, String idCotizante, int idCotizPendiente, String detalle)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizante = idCotizante;
		this.idCotizPendiente = idCotizPendiente;
		this.detalle = detalle;
	}
}
