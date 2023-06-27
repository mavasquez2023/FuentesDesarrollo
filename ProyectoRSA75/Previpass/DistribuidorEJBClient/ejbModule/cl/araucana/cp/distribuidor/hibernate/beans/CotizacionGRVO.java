package cl.araucana.cp.distribuidor.hibernate.beans;

import java.sql.Date;
import java.util.HashMap;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CotizacionGRVO extends CotizacionVO
{
	private static final long serialVersionUID = -1356372128120423109L;
	private int gratificacion;
	private int rentaImpInp;
	private int saludObligatorio;
	private int previsionObligatorio;
	private int segCesRemImp;
	private int segCesTrab;
	private int segCesEmpl;
	private float tasaTrabPesado;
	private String tipoTrabPesado = "";
	private int trabPesado;
	private int mutualImp;
	private int inpMutual;
	private int ccafAporte;
	private Date inicio;
	private Date termino;
	
	private int previsionSIS;
	private int rentaImponibleSIS;	

	private int previsionTotal;
	
	public CotizacionGRVO(Integer rutEmpresa, Integer idConvenio, Integer idCotizante)
	{
		super(rutEmpresa.intValue(), idConvenio.intValue());
		this.rutEmpresa = rutEmpresa.intValue();
		this.idConvenio = idConvenio.intValue();
		this.idCotizante = idCotizante.intValue();
	}

	public CotizacionGRVO(int rutEmpresa, int idConvenio, int idCotizante)
	{
		super(rutEmpresa, idConvenio);
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizante = idCotizante;
	}

	public CotizacionGRVO(int rutEmpresa, int idConvenio)
	{
		super(rutEmpresa, idConvenio);
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
	}

	public CotizacionGRVO()
	{
		super();
	}

	public CotizacionGRVO(CotizacionGRVO otro)
	{
		super(otro.getRutEmpresa(), otro.getIdConvenio());
		this.idCotizante = otro.getIdCotizante();
		this.rutEmpresa = otro.getRutEmpresa();
		this.idConvenio = otro.getIdConvenio();
		this.idEstadoEdicion = otro.getIdEstadoEdicion();
		this.gratificacion = otro.getGratificacion();
		this.saludObligatorio = otro.getSaludObligatorio();
		this.previsionObligatorio = otro.getPrevisionObligatorio();
		this.segCesRemImp = otro.getSegCesRemImp();
		this.segCesTrab = otro.getSegCesTrab();
		this.segCesEmpl = otro.getSegCesEmpl();
		this.tasaTrabPesado = otro.getTasaTrabPesado();
		this.tipoTrabPesado = otro.getTipoTrabPesado();
		this.trabPesado = otro.getTrabPesado();
		this.mutualImp = otro.getMutualImp();
		this.inpMutual = otro.getInpMutual();
		this.ccafAporte = otro.getCcafAporte();
		this.inicio = otro.getInicio();
		this.termino = otro.getTermino();
		
		this.previsionSIS = otro.getPrevisionSIS();
		this.rentaImponibleSIS = otro.getRentaImponibleSIS();		
	}

	public void setRenta(int monto)
	{
		this.gratificacion = monto;
	}

	public int getRenta()
	{
		return this.gratificacion;
	}

	public int setGetSumaSalud()
	{
		return this.saludObligatorio;
	}

	public int getTotalSalud()
	{
		return this.saludObligatorio;
	}

	public int getTotalPrevision()
	{
		//return this.previsionObligatorio;
		return this.previsionTotal;
	}

	public void setTotalPrevision(int monto)
	{
		//this.previsionObligatorio = monto;
		this.previsionTotal = monto;
	}

	public int getSumaTotalINP()
	{
		if (this.previsionObligatorio >= 0)
			return this.previsionObligatorio;
		return -1;
	}

	public int getSumaTotalAFP()
	{
		if (this.previsionObligatorio >= 0 && this.segCesEmpl >= 0 && this.segCesTrab >= 0 && this.previsionSIS >= 0)
			return this.previsionObligatorio + this.segCesEmpl + this.segCesTrab + this.previsionSIS;
		return -1;
	}

	public void setPrevisionAhorro(int previsionAhorro)
	{
	}

	public int hashCode()
	{
		final int PRIME = 31;
		int result = super.hashCode();
		result = PRIME * result + this.ccafAporte;
		result = PRIME * result + this.gratificacion;
		result = PRIME * result + ((this.inicio == null) ? 0 : this.inicio.hashCode());
		result = PRIME * result + this.mutualImp;
		result = PRIME * result + this.previsionObligatorio;
		result = PRIME * result + this.saludObligatorio;
		result = PRIME * result + this.segCesEmpl;
		result = PRIME * result + this.segCesRemImp;
		result = PRIME * result + this.segCesTrab;
		result = PRIME * result + Float.floatToIntBits(this.tasaTrabPesado);
		result = PRIME * result + ((this.termino == null) ? 0 : this.termino.hashCode());
		result = PRIME * result + ((this.tipoTrabPesado == null) ? 0 : this.tipoTrabPesado.hashCode());
		result = PRIME * result + this.trabPesado;
		return result;
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final CotizacionGRVO other = (CotizacionGRVO) obj;
		if (this.ccafAporte != other.ccafAporte)
			return false;
		if (this.gratificacion != other.gratificacion)
			return false;
		if (this.inicio == null)
		{
			if (other.inicio != null)
				return false;
		} else if (!this.inicio.equals(other.inicio))
			return false;
		if (this.mutualImp != other.mutualImp)
			return false;
		if (this.previsionObligatorio != other.previsionObligatorio)
			return false;
		if (this.saludObligatorio != other.saludObligatorio)
			return false;
		if (this.segCesEmpl != other.segCesEmpl)
			return false;
		if (this.segCesRemImp != other.segCesRemImp)
			return false;
		if (this.segCesTrab != other.segCesTrab)
			return false;
		if (Float.floatToIntBits(this.tasaTrabPesado) != Float.floatToIntBits(other.tasaTrabPesado))
			return false;
		if (this.termino == null)
		{
			if (other.termino != null)
				return false;
		} else if (!this.termino.equals(other.termino))
			return false;
		if (this.tipoTrabPesado == null)
		{
			if (other.tipoTrabPesado != null)
				return false;
		} else if (!this.tipoTrabPesado.equals(other.tipoTrabPesado))
			return false;
		if (this.trabPesado != other.trabPesado)
			return false;
		return true;
	}

	public int getCcafAporte()
	{
		return this.ccafAporte;
	}
	public void setCcafAporte(int ccafAporte)
	{
		this.ccafAporte = ccafAporte;
	}
	public int getGratificacion()
	{
		return this.gratificacion;
	}
	public void setGratificacion(int gratificacion)
	{
		this.gratificacion = gratificacion;
	}
	public Date getInicio()
	{
		return this.inicio;
	}
	public void setInicio(Date inicio)
	{
		this.inicio = inicio;
	}
	public int getMutualImp()
	{
		return this.mutualImp;
	}
	public void setMutualImp(int mutualImp)
	{
		this.mutualImp = mutualImp;
	}
	public int getPrevisionObligatorio()
	{
		return this.previsionObligatorio;
	}
	public void setPrevisionObligatorio(int previsionObligatorio)
	{
		this.previsionObligatorio = previsionObligatorio;
	}
	public int getSaludObligatorio()
	{
		return this.saludObligatorio;
	}
	public void setSaludObligatorio(int saludObligatorio)
	{
		this.saludObligatorio = saludObligatorio;
	}
	public int getSegCesEmpl()
	{
		return this.segCesEmpl;
	}
	public void setSegCesEmpl(int segCesEmpl)
	{
		this.segCesEmpl = segCesEmpl;
	}
	public int getSegCesRemImp()
	{
		return this.segCesRemImp;
	}
	public void setSegCesRemImp(int segCesRemImp)
	{
		this.segCesRemImp = segCesRemImp;
	}
	public int getSegCesTrab()
	{
		return this.segCesTrab;
	}
	public void setSegCesTrab(int segCesTrab)
	{
		this.segCesTrab = segCesTrab;
	}
	public float getTasaTrabPesado()
	{
		return this.tasaTrabPesado;
	}
	public void setTasaTrabPesado(float tasaTrabPesado)
	{
		this.tasaTrabPesado = tasaTrabPesado;
	}
	public Date getTermino()
	{
		return this.termino;
	}
	public void setTermino(Date termino)
	{
		this.termino = termino;
	}
	public String getTipoTrabPesado()
	{
		return this.tipoTrabPesado;
	}
	public void setTipoTrabPesado(String tipoTrabPesado)
	{
		this.tipoTrabPesado = tipoTrabPesado;
	}
	public int getTrabPesado()
	{
		return this.trabPesado;
	}
	public void setTrabPesado(int trabPesado)
	{
		this.trabPesado = trabPesado;
	}	
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public int getInpMutual()
	{
		return this.inpMutual;
	}

	public void setInpMutual(int inpMutual)
	{
		this.inpMutual = inpMutual;
	}
	
	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.rutEmpresa));
		parametros.put("2", String.valueOf(this.idConvenio));
		parametros.put("3", String.valueOf(this.idCotizante));
		parametros.put("4", String.valueOf(this.idEstadoEdicion));
		parametros.put("5", String.valueOf(this.gratificacion));
		parametros.put("6", String.valueOf(this.saludObligatorio));
		parametros.put("7", String.valueOf(this.previsionObligatorio));
		parametros.put("8", String.valueOf(this.segCesRemImp));
		parametros.put("9", String.valueOf(this.segCesTrab));
		parametros.put("10", String.valueOf(this.segCesEmpl));
		parametros.put("11", String.valueOf(this.tasaTrabPesado));
		parametros.put("12", String.valueOf(this.tipoTrabPesado));
		parametros.put("13", String.valueOf(this.trabPesado));
		parametros.put("14", String.valueOf(this.mutualImp));
		parametros.put("15", String.valueOf(this.inpMutual));
		parametros.put("16", String.valueOf(this.ccafAporte));
		parametros.put("17", String.valueOf(this.inicio));
		parametros.put("18", String.valueOf(this.termino));
		parametros.put("19", String.valueOf(this.previsionSIS));
		parametros.put("20", String.valueOf(this.rentaImponibleSIS));
		return parametros;
	}

	public int getRentaImpInp()
	{
		return this.rentaImpInp;
	}

	public void setRentaImpInp(int rentaImpInp)
	{
		this.rentaImpInp = rentaImpInp;
	}

	public int getPrevisionSIS()
	{
		return this.previsionSIS;
	}

	public void setPrevisionSIS(int previsionSIS)
	{
		this.previsionSIS = previsionSIS;
	}

	public int getRentaImponibleSIS()
	{
		return this.rentaImponibleSIS;
	}

	public void setRentaImponibleSIS(int rentaImponibleSIS)
	{
		this.rentaImponibleSIS = rentaImponibleSIS;
	}

	public int getPrevisionTotal()
	{
		return this.previsionTotal;
	}

	public void setPrevisionTotal(int previsionTotal)
	{
		this.previsionTotal = previsionTotal;
	}
}
