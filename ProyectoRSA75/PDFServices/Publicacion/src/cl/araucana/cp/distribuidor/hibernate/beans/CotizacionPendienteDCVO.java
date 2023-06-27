package cl.araucana.cp.distribuidor.hibernate.beans;

public class CotizacionPendienteDCVO extends CotizacionPendienteVO
{
	private static final long serialVersionUID = 6245183472914903492L;
	
	public CotizacionPendienteDCVO()
	{
		super();
	}

	public CotizacionPendienteDCVO(int rutEmpresa, int idConvenio, int idCotizPendiente)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizPendiente = idCotizPendiente;
	}

	public CotizacionPendienteDCVO(int rutEmpresa, int idConvenio, String idCotizante, int idCotizPendiente, String detalle)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizante = idCotizante;
		this.idCotizPendiente = idCotizPendiente;
		this.detalle = detalle;
	}	
}
