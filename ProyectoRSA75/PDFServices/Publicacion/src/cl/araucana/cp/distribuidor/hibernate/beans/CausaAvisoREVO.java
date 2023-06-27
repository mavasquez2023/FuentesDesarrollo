package cl.araucana.cp.distribuidor.hibernate.beans;

public class CausaAvisoREVO extends CausaVO
{
	private static final long serialVersionUID = -4216877724787495590L;

	public CausaAvisoREVO()
	{
		super();
	}

	public CausaAvisoREVO(CausaVO cp)
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
