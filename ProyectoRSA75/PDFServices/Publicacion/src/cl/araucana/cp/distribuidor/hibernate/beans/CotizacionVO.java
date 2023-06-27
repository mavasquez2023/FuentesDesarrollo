package cl.araucana.cp.distribuidor.hibernate.beans;


public abstract class CotizacionVO extends AuditableVO
{
	private static final long serialVersionUID = 4836779471304752816L;
	protected int rutEmpresa;
	protected int idConvenio;
	protected int idCotizante;
	protected int idGrupoConvenio;

	protected int idEstadoEdicion;

	public CotizacionVO()
	{
		super();
	}

	public CotizacionVO(int rutEmpresa, int idConvenio)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
	}

	public CotizacionVO(int rutEmpresa, int idConvenio, int idCotizante)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizante = idCotizante;
	}

	public abstract void setRenta(int monto);
	public abstract int getRenta();
	public abstract void setRentaImpInp(int monto);
	public abstract int getRentaImpInp();
	public abstract void setSaludObligatorio(int monto);
	public abstract int getSaludObligatorio();
	public abstract int setGetSumaSalud();
	public abstract int getTotalSalud();
	public abstract int getTotalPrevision();
	public abstract void setTotalPrevision(int monto);
	public abstract int getSumaTotalINP();
	public abstract int getSumaTotalAFP();
	public abstract void setPrevisionAhorro(int monto);
	public abstract void setPrevisionObligatorio(int monto);
	public abstract int getPrevisionObligatorio();
	public abstract void setInpMutual(int monto);
	public abstract int getMutualImp();
	public abstract int getInpMutual();
	public abstract void setMutualImp(int monto);

	public int hashCode()
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.idConvenio;
		result = PRIME * result + this.idCotizante;
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
		final CotizacionVO other = (CotizacionVO) obj;
		if (this.idConvenio != other.idConvenio)
			return false;
		if (this.idCotizante != other.idCotizante)
			return false;
		if (this.rutEmpresa != other.rutEmpresa)
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

	public int getIdEstadoEdicion()
	{
		return this.idEstadoEdicion;
	}

	public void setIdEstadoEdicion(int idEstadoEdicion)
	{
		this.idEstadoEdicion = idEstadoEdicion;
	}

	public int getRutEmpresa()
	{
		return this.rutEmpresa;
	}

	public void setRutEmpresa(int rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
	}

	public int getIdGrupoConvenio() 
	{
		return this.idGrupoConvenio;
	}

	public void setIdGrupoConvenio(int idGrupoConvenio) 
	{
		this.idGrupoConvenio = idGrupoConvenio;
	}

	public static Class getTipoCotizacion(char tipoProceso)
	{
		if (tipoProceso == 'R')
			return CotizacionREVO.class;
		if (tipoProceso == 'G')
			return CotizacionGRVO.class;
		if (tipoProceso == 'A')
			return CotizacionRAVO.class;
		return CotizacionDCVO.class;
	}

	public static Class getTipoCotizPendiente(char tipoProceso)
	{
		if (tipoProceso == 'R')
			return CotizacionPendienteREVO.class;
		if (tipoProceso == 'G')
			return CotizacionPendienteGRVO.class;
		if (tipoProceso == 'A')
			return CotizacionPendienteRAVO.class;
		return CotizacionPendienteDCVO.class;
	}

	public static Class getTipoCausaAviso(String tipoProceso)
	{
		if (tipoProceso.equals("R"))
			return CausaAvisoREVO.class;
		if (tipoProceso.equals("G"))
			return CausaAvisoGRVO.class;
		if (tipoProceso.equals("A"))
			return CausaAvisoRAVO.class;
		return CausaAvisoDCVO.class;
	}
}
