package cl.araucana.cp.distribuidor.hibernate.beans;


public class CausaAvisoGRVO extends CausaVO
{
	private static final long serialVersionUID = 3442730564014417689L;

	public CausaAvisoGRVO()
	{
		super();
	}

	public CausaAvisoGRVO(CausaVO cp)
	{
		super();
		this.rutEmpresa = cp.getRutEmpresa();
		this.idConvenio = cp.getIdConvenio();
		this.idCotizPendiente = cp.getIdCotizPendiente();
		this.idCausa = cp.getIdCausa();
		this.idTipoCausa = cp.getIdTipoCausa();
		this.texto = cp.getTexto();
	}
}
