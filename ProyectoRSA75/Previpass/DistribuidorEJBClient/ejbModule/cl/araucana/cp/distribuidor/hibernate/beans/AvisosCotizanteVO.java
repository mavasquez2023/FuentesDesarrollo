package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class AvisosCotizanteVO implements Serializable
{
	private static final long serialVersionUID = -4778682246729226809L;
	private int rutEmpresa;
	private int idConvenio;
	private int idCotizante;
//	clillo 3-12-14 por Reliquidación
	private int periodo;
	
	public AvisosCotizanteVO()
	{
		super();
	}

//	clillo 3-12-14 por Reliquidación
	//public AvisosCotizanteVO(int rutEmpresa, int idConvenio, int idCotizante)
	public AvisosCotizanteVO(int rutEmpresa, int idConvenio, int idCotizante, int periodo)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizante = idCotizante;
//		clillo 3-12-14 por Reliquidación
		this.periodo = periodo;
	}

	public int hashCode()
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.idConvenio;
		result = PRIME * result + this.idCotizante;
		result = PRIME * result + this.rutEmpresa;
//		clillo 3-12-14 por Reliquidación
		result = PRIME * result + this.periodo;
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
		final AvisosCotizanteVO other = (AvisosCotizanteVO) obj;
		if (this.idConvenio != other.idConvenio)
			return false;
		if (this.idCotizante != other.idCotizante)
			return false;
		if (this.rutEmpresa != other.rutEmpresa)
			return false;
//		clillo 3-12-14 por Reliquidación
		if (this.periodo != other.periodo)
			return false;
		return true;
	}

	public int getIdConvenio()
	{
		return this.idConvenio;
	}

	public void setIdConvenio(int idConvenio)
	{
		this.idConvenio = idConvenio;
	}

	public int getIdCotizante()
	{
		return this.idCotizante;
	}

	public void setIdCotizante(int idCotizante)
	{
		this.idCotizante = idCotizante;
	}

	public int getRutEmpresa()
	{
		return this.rutEmpresa;
	}

	public void setRutEmpresa(int rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
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
