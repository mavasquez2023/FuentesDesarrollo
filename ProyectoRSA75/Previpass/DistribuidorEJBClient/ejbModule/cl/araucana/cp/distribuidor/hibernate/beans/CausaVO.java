package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class CausaVO implements Serializable
{
	private static final long serialVersionUID = 1274130473214346284L;

	protected int rutEmpresa;
	protected int idConvenio;
	protected int idCotizPendiente;
	protected int idCausa;

	protected int idTipoCausa;
	protected String texto;
//	clillo 3-12-14 por Reliquidación
	protected int periodo;
	protected String valorInformado;

	public CausaVO()
	{
		super();
	}

//	clillo 3-12-14 por Reliquidación
	//public CausaVO(int rutEmpresa, int idConvenio, int idCotizPendiente, int idCausa, int idTipoCausa, String texto)
	public CausaVO(int rutEmpresa, int idConvenio, int idCotizPendiente, int idCausa, int idTipoCausa, String texto, int periodo)
	{
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizPendiente = idCotizPendiente;
		this.idCausa = idCausa;
		this.idTipoCausa = idTipoCausa;
		this.texto = texto;
//		clillo 3-12-14 por Reliquidación
		this.periodo = periodo;
	}

	public int getIdCausa()
	{
		return this.idCausa;
	}

	public void setIdCausa(int idCausa)
	{
		this.idCausa = idCausa;
	}

	public int getIdTipoCausa()
	{
		return this.idTipoCausa;
	}

	public void setIdTipoCausa(int idTipoCausa)
	{
		this.idTipoCausa = idTipoCausa;
	}

	public String getTexto()
	{
		return this.texto;
	}

	public void setTexto(String texto)
	{
		this.texto = texto;
	}

	public String toString()
	{
		return ":rutEmpresa:" + this.rutEmpresa + ":idConvenio:" + this.idConvenio + ":idCotizPendiente:" + this.idCotizPendiente + ":idCausa:" + this.idCausa + ":idTipoCausa:" + this.idTipoCausa
				+ ":texto:" + this.texto + "::";
	}

	public int getIdConvenio()
	{
		return this.idConvenio;
	}

	public void setIdConvenio(int idConvenio)
	{
		this.idConvenio = idConvenio;
	}

	public int getIdCotizPendiente()
	{
		return this.idCotizPendiente;
	}

	public void setIdCotizPendiente(int idCotizPendiente)
	{
		this.idCotizPendiente = idCotizPendiente;
	}

	public int getRutEmpresa()
	{
		return this.rutEmpresa;
	}

	public void setRutEmpresa(int rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
	}

	public int hashCode()
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.idCausa;
		result = PRIME * result + this.idConvenio;
		result = PRIME * result + this.idCotizPendiente;
		result = PRIME * result + this.rutEmpresa;
		return result;
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final CausaVO other = (CausaVO) obj;
		if (this.idCausa != other.idCausa)
			return false;
		if (this.idConvenio != other.idConvenio)
			return false;
		if (this.idCotizPendiente != other.idCotizPendiente)
			return false;
		if (this.rutEmpresa != other.rutEmpresa)
			return false;
		return true;
	}

	public String getValorInformado()
	{
		return this.valorInformado;
	}

	public void setValorInformado(String valorInformado)
	{
		this.valorInformado = valorInformado;
	}
	/**
	 * @return el periodo
	 */
	public int getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo el periodo a establecer
	 */
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
}
