package cl.araucana.cp.distribuidor.hibernate.beans;


public class CotizacionPendienteRAVO extends CotizacionPendienteVO
{
	private static final long serialVersionUID = -6214786015821284487L;
	
	public CotizacionPendienteRAVO()
	{
		super();
	}
	
//	clillo 3-12-14 por Reliquidación
	public CotizacionPendienteRAVO(int rutEmpresa, int idConvenio, int idCotizPendiente, int periodo)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizPendiente = idCotizPendiente;
		this.periodo = periodo;
	}
	
	public CotizacionPendienteRAVO(int rutEmpresa, int idConvenio, int idCotizPendiente)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizPendiente = idCotizPendiente;
	}
//	clillo 3-12-14 por Reliquidación
	public CotizacionPendienteRAVO(int rutEmpresa, int idConvenio, String idCotizante, int idCotizPendiente, String detalle, int periodo)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizante = idCotizante;
		this.idCotizPendiente = idCotizPendiente;
		this.detalle = detalle;
//		clillo 3-12-14 por Reliquidación
		this.periodo = periodo;
	}
}
