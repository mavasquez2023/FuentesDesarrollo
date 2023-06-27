package cl.araucana.cp.distribuidor.hibernate.beans;

public class CausaREVO extends CausaVO
{
	private static final long serialVersionUID = -4216877724787495590L;

	public CausaREVO()
	{
		super();
	}

	public CausaREVO(CotizacionPendienteREVO cp, int idCausa, int idTipoCausa, String texto)
	{
		super();
		this.rutEmpresa = cp.getRutEmpresa();
		this.idConvenio = cp.getIdConvenio();
		this.idCotizPendiente = cp.getIdCotizPendiente();
		this.idCausa = idCausa;
		this.idTipoCausa = idTipoCausa;
		this.texto = texto;
	}
}
