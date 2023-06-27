package cl.araucana.cp.distribuidor.hibernate.beans;


public class CausaGRVO extends CausaVO
{
	private static final long serialVersionUID = 3442730564014417689L;

	public CausaGRVO()
	{
		super();
	}

	public CausaGRVO(CotizacionPendienteGRVO cp, int idCausa, int idTipoCausa, String texto)
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
