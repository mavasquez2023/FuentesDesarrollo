package cl.araucana.cp.distribuidor.hibernate.beans;


public class CausaAvisoRAVO extends CausaVO
{
	private static final long serialVersionUID = 7777791216113687525L;

	public CausaAvisoRAVO()
	{
		super();
	}

	public CausaAvisoRAVO(CausaVO cp)
	{
		super();
		this.rutEmpresa = cp.getRutEmpresa();
		this.idConvenio = cp.getIdConvenio();
		this.idCotizPendiente = cp.getIdCotizPendiente();
		this.idCausa = cp.getIdCausa();
		this.idTipoCausa = cp.getIdTipoCausa();
		this.texto = cp.getTexto();
//		clillo 3-12-14 por Reliquidación
		this.periodo= cp.getPeriodo();
	}
}
