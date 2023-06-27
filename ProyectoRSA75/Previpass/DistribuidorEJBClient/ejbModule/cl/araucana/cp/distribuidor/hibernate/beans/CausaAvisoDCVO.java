package cl.araucana.cp.distribuidor.hibernate.beans;

public class CausaAvisoDCVO extends CausaVO
{
	private static final long serialVersionUID = -3210957796526306210L;

	public CausaAvisoDCVO()
	{
		super();
	}

	public CausaAvisoDCVO(CausaVO cp)
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
