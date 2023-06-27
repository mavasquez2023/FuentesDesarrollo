package cl.araucana.cp.distribuidor.hibernate.beans;

import java.sql.Date;
import java.util.HashMap;

public class CotizacionDCVO extends CotizacionVO
{
	private static final long serialVersionUID = 4135669375432133975L;
	private int rentaImponible;
	private int depositoConvenido;
	private int tipoRegimenPrev; //1: INP 2: AFP
	private float tasaPactada;
	private int indemAporte;
	private Date indemInicio;
	private Date indemTermino;
	private int numPeriodos;
	private int idEntDep;

	public CotizacionDCVO(Integer rutEmpresa, Integer idConvenio, Integer idCotizante)
	{
		super(rutEmpresa.intValue(), idConvenio.intValue());
		this.rutEmpresa = rutEmpresa.intValue();
		this.idConvenio = idConvenio.intValue();
		this.idCotizante = idCotizante.intValue();
	}

	public CotizacionDCVO(int rutEmpresa, int idConvenio, int idCotizante)
	{
		super(rutEmpresa, idConvenio);
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizante = idCotizante;
	}

	public CotizacionDCVO(int rutEmpresa, int idConvenio)
	{
		super(rutEmpresa, idConvenio);
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
	}

	public CotizacionDCVO()
	{
		super();
	}

	public CotizacionDCVO(CotizacionDCVO otro)
	{
		super(otro.getRutEmpresa(), otro.getIdConvenio());
		this.idCotizante = otro.getIdCotizante();
		this.rutEmpresa = otro.getRutEmpresa();
		this.idConvenio = otro.getIdConvenio();
		this.idEstadoEdicion = otro.getIdEstadoEdicion();
		this.rentaImponible = otro.getRentaImponible();
		this.depositoConvenido = otro.getDepositoConvenido();
		this.tipoRegimenPrev = otro.getTipoRegimenPrev();
		this.tasaPactada = otro.getTasaPactada();
		this.indemAporte = otro.getIndemAporte();
		this.indemInicio = otro.getIndemInicio();
		this.indemTermino = otro.getIndemTermino();
		this.numPeriodos = otro.getNumPeriodos();
		this.idEntDep = otro.idEntDep;
	}

	public void setRenta(int monto)
	{
		this.rentaImponible = monto;
	}

	public int getRenta()
	{
		return this.rentaImponible;
	}

	public void setRentaImpInp(int monto)
	{
	}

	public int getRentaImpInp()
	{
		return 0;
	}

	public int getSaludObligatorio()
	{
		return 0;
	}

	public int getTotalPrevision()
	{
		return 0;
	}

	public void setTotalPrevision(int monto)
	{
	}

	public void setSaludObligatorio(int monto)
	{
	}

	public int setGetSumaSalud()
	{
		return 0;
	}

	public int getTotalSalud()
	{
		return 0;
	}

	public int getSumaTotalINP()
	{
		return 0;
	}

	public int getSumaTotalAFP()
	{
		return 0;
	}

	public void setPrevisionAhorro(int previsionAhorro)
	{
	}

	public void setPrevisionObligatorio(int prevision)
	{
	}

	public void setInpMutual(int monto)
	{
	}

	public int getMutualImp()
	{
		return 0;
	}

	public int getInpMutual()
	{
		return 0;
	}

	public void setMutualImp(int monto)
	{
	}

	public int getPrevisionAhorro()
	{
		return 0;
	}

	public int getPrevisionObligatorio()
	{
		return 0;
	}

	public int getDepositoConvenido()
	{
		return this.depositoConvenido;
	}
	public void setDepositoConvenido(int depositoConvenido)
	{
		this.depositoConvenido = depositoConvenido;
	}
	public int getIndemAporte()
	{
		return this.indemAporte;
	}
	public void setIndemAporte(int indemAporte)
	{
		this.indemAporte = indemAporte;
	}
	public Date getIndemInicio()
	{
		return this.indemInicio;
	}
	public void setIndemInicio(Date indemInicio)
	{
		this.indemInicio = indemInicio;
	}
	public Date getIndemTermino()
	{
		return this.indemTermino;
	}
	public void setIndemTermino(Date indemTermino)
	{
		this.indemTermino = indemTermino;
	}
	public int getNumPeriodos()
	{
		return this.numPeriodos;
	}
	public void setNumPeriodos(int numPeriodos)
	{
		this.numPeriodos = numPeriodos;
	}
	public int getRentaImponible()
	{
		return this.rentaImponible;
	}
	public void setRentaImponible(int rentaImponible)
	{
		this.rentaImponible = rentaImponible;
	}
	public float getTasaPactada()
	{
		return this.tasaPactada;
	}
	public void setTasaPactada(float tasaPactada)
	{
		this.tasaPactada = tasaPactada;
	}
	public int getTipoRegimenPrev()
	{
		return this.tipoRegimenPrev;
	}
	public void setTipoRegimenPrev(int tipoRegimenPrev)
	{
		this.tipoRegimenPrev = tipoRegimenPrev;
	}
	public int getIdEntDep()
	{
		return this.idEntDep;
	}

	public void setIdEntDep(int idEntDep)
	{
		this.idEntDep = idEntDep;
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.rutEmpresa));
		parametros.put("2", String.valueOf(this.idConvenio));
		parametros.put("3", String.valueOf(this.idCotizante));
		parametros.put("4", String.valueOf(this.idEstadoEdicion));
		parametros.put("5", String.valueOf(this.rentaImponible));
		parametros.put("6", String.valueOf(this.depositoConvenido));
		parametros.put("7", String.valueOf(this.tipoRegimenPrev));
		parametros.put("8", String.valueOf(this.tasaPactada));
		parametros.put("9", String.valueOf(this.indemAporte));
		parametros.put("10", String.valueOf(this.indemInicio));
		parametros.put("11", String.valueOf(this.indemTermino));
		parametros.put("12", String.valueOf(this.numPeriodos));
		parametros.put("13", String.valueOf(this.idEntDep));
		return parametros;
	}
}
