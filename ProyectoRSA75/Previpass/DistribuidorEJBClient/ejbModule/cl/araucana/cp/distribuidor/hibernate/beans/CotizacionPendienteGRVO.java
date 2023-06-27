package cl.araucana.cp.distribuidor.hibernate.beans;


public class CotizacionPendienteGRVO extends CotizacionPendienteVO
{
	private static final long serialVersionUID = -9137611196558248719L;
	
	public CotizacionPendienteGRVO()
	{
		super();
	}

	public CotizacionPendienteGRVO(int rutEmpresa, int idConvenio, int idCotizPendiente)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizPendiente = idCotizPendiente;
	}

	public CotizacionPendienteGRVO(int rutEmpresa, int idConvenio, String idCotizante, int idCotizPendiente, String detalle)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizante = idCotizante;
		this.idCotizPendiente = idCotizPendiente;
		this.detalle = detalle;
	}
}
