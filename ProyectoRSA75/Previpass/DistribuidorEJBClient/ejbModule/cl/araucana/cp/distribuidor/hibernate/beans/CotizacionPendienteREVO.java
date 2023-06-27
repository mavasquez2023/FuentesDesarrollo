package cl.araucana.cp.distribuidor.hibernate.beans;


public class CotizacionPendienteREVO extends CotizacionPendienteVO
{
	private static final long serialVersionUID = -4559754309025716057L;

	public CotizacionPendienteREVO()
	{
		super();
	}

	public CotizacionPendienteREVO(int rutEmpresa, int idConvenio, int idCotizPendiente)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizPendiente = idCotizPendiente;
	}

	public CotizacionPendienteREVO(int rutEmpresa, int idConvenio, String idCotizante, int idCotizPendiente, String detalle)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizante = idCotizante;
		this.idCotizPendiente = idCotizPendiente;
		this.detalle = detalle;
	}
}
