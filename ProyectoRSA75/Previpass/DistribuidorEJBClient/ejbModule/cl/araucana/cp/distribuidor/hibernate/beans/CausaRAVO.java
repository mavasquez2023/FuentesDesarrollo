package cl.araucana.cp.distribuidor.hibernate.beans;


public class CausaRAVO extends CausaVO
{
	private static final long serialVersionUID = 7777791216113687525L;

	public CausaRAVO()
	{
		super();
	}

	public CausaRAVO(CotizacionPendienteRAVO cp, int idCausa, int idTipoCausa, String texto, String valorInformado, int periodo)
	{
		super();
		this.rutEmpresa = cp.getRutEmpresa();
		this.idConvenio = cp.getIdConvenio();
		this.idCotizPendiente = cp.getIdCotizPendiente();
		this.idCausa = idCausa;
		this.idTipoCausa = idTipoCausa;
		this.texto = texto;
		this.valorInformado = valorInformado;
//		clillo 3-12-14 por Reliquidación
		this.periodo = periodo;
	}
}
