package cl.araucana.cp.distribuidor.hibernate.beans;

public class CausaDCVO extends CausaVO
{
	private static final long serialVersionUID = -3210957796526306210L;

	public CausaDCVO()
	{
		super();
	}

	public CausaDCVO(CotizacionPendienteDCVO cp, int idCausa, int idTipoCausa, String texto)
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
