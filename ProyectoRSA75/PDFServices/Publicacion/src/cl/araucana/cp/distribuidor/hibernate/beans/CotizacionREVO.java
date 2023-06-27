package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;

import cl.araucana.cp.distribuidor.base.Utils;

public class CotizacionREVO extends CotizacionVO
{
	private static Logger logger = Logger.getLogger(CotizacionREVO.class);
	private static final long serialVersionUID = -8134728790580413151L;
	private int rentaImp;
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
	private int ccafAporte;

	private int asigFamiliar;
	private int asigFamRetro;
	private int asigFamReint;
	private int totalAsigFam;
	private int saludPactado;
	private int saludAdicional;
	private int saludTotal;
	private int previsionAhorro;
	private int previsionTotal;
	private int inpBonificacion;
	private int inpDesahucio;
	private int inpMutual;
	private int ccafCredito;
	private int ccafDental;
	private int ccafLeasing;
	private int ccafSeguro;

	private String APV1;
	private String APV2;
	private String APV3;
	private String APV4;
	private String APV5;
	private String nombreMutual;

	private int apvcAporteEmpl;
	private int apvcAporteTrab;
	private String apvcNumContr = "";
	private int afpvRutDpndiente;
	private String afpvNombreDpndiente = "";
	private String afpvAplldioPatDpndiente = "";
	private String afpvAplldioMatDpndiente = "";
	
	private int previsionSIS;
	private int rentaImponibleSIS;

	// campos no utilizados
	private int fun = 0;
	private int rentaTributable = 0;
	private int porcentajeBonificacionSalud = 0;
	private int saludBonificacion = 0;
	private int afectoImpuesto = 0;
	private int impuestoUnico = 0;
	private int inpOtrosAportes = 0;
	private int ccafOtros = 0;
	private int segInvAporteTrab = 0;
	private int segInvAporteEmpl = 0;
	private int inpPension = 0;
	private int previsionAdicional = 0;

	private List movimientoPersonal = new ArrayList();
	private List movimientoPersonalAF = new ArrayList();

	public CotizacionREVO(int rutEmpresa, int idConvenio, int idCotizante)
	{
		super(rutEmpresa, idConvenio);
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizante = idCotizante;
	}

	public CotizacionREVO(Integer rutEmpresa, Integer idConvenio, Integer idCotizante)
	{
		super(rutEmpresa.intValue(), idConvenio.intValue());
		this.rutEmpresa = rutEmpresa.intValue();
		this.idConvenio = idConvenio.intValue();
		this.idCotizante = idCotizante.intValue();
	}

	public CotizacionREVO(int rutEmpresa, int idConvenio)
	{
		super(rutEmpresa, idConvenio);
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
	}

	public CotizacionREVO()
	{
		super();
	}

	public CotizacionREVO(CotizacionREVO otro)
	{
		super(otro.getRutEmpresa(), otro.getIdConvenio());
		this.idCotizante = otro.getIdCotizante();
		this.rutEmpresa = otro.getRutEmpresa();
		this.idConvenio = otro.getIdConvenio();
		this.idEstadoEdicion = otro.getIdEstadoEdicion();
		this.rentaImp = otro.getRentaImp();
		this.saludObligatorio = otro.getSaludObligatorio();
		this.previsionObligatorio = otro.getPrevisionObligatorio();
		this.segCesRemImp = otro.getSegCesRemImp();
		this.segCesTrab = otro.getSegCesTrab();
		this.segCesEmpl = otro.getSegCesEmpl();
		this.tasaTrabPesado = otro.getTasaTrabPesado();
		this.tipoTrabPesado = otro.getTipoTrabPesado();
		this.trabPesado = otro.getTrabPesado();
		this.mutualImp = otro.getMutualImp();
		this.ccafAporte = otro.getCcafAporte();
		this.asigFamiliar = otro.getAsigFamiliar();
		this.asigFamRetro = otro.getAsigFamRetro();
		this.asigFamReint = otro.getAsigFamReint();
		this.saludPactado = otro.getSaludPactado();
		this.saludAdicional = otro.getSaludAdicional();
		this.saludTotal = otro.getSaludTotal();
		this.previsionAhorro = otro.getPrevisionAhorro();
		this.previsionTotal = otro.getPrevisionTotal();
		this.inpBonificacion = otro.getInpBonificacion();
		this.inpDesahucio = otro.getInpDesahucio();
		this.inpMutual = otro.getInpMutual();
		this.ccafCredito = otro.getCcafCredito();
		this.ccafDental = otro.getCcafDental();
		this.ccafLeasing = otro.getCcafLeasing();
		this.ccafSeguro = otro.getCcafSeguro();

		this.apvcAporteEmpl = otro.getApvcAporteEmpl();
		this.apvcAporteTrab = otro.getApvcAporteTrab();
		this.apvcNumContr = otro.getApvcNumContr();
		this.afpvRutDpndiente = otro.getAfpvRutDpndiente();
		this.afpvNombreDpndiente = otro.getAfpvNombreDpndiente();
		this.afpvAplldioPatDpndiente = otro.getAfpvAplldioPatDpndiente();
		this.afpvAplldioMatDpndiente = otro.getAfpvAplldioMatDpndiente();
		
		this.previsionSIS = otro.getPrevisionSIS();
		this.rentaImponibleSIS = otro.getRentaImponibleSIS();
	}

	public void setRenta(int monto)
	{
		this.rentaImp = monto;
	}

	public int getRenta()
	{
		return this.rentaImp;
	}

	public int setGetSumaSalud()
	{
		this.saludTotal = this.saludObligatorio + this.saludAdicional;
		return this.saludTotal;	
	}

	public int getTotalSalud()
	{
		return this.saludTotal;
	}

	public int getTotalPrevision()
	{
		return this.previsionTotal;
	}

	public void setTotalPrevision(int monto)
	{
		this.previsionTotal = monto;
	}

	public int getSumaTotalINP()
	{
		if (this.previsionObligatorio >= 0 && this.inpDesahucio >= 0 && this.inpBonificacion >= 0 && this.asigFamiliar >= 0)
			return this.previsionObligatorio + this.inpDesahucio + this.inpBonificacion - this.asigFamiliar;
		return -1;
	}

	public int getSumaTotalAFP()
	{
		if (this.previsionObligatorio >= 0 && this.previsionAhorro >= 0 && this.segCesEmpl >= 0 && this.segCesTrab >= 0 && this.previsionSIS >= 0)
			return this.previsionObligatorio + this.previsionAhorro + this.segCesEmpl + this.segCesTrab + this.previsionSIS;
		return -1;
	}

	public boolean isReforma(Utils setter)
	{
		if (!setter.setString("rutDependiente").equals("") || !setter.setString("nombreDependiente").equals("") || !setter.setString("apellidoDep").equals(""))
			return true;
		return false;
	}

	public boolean isVoluntario()
	{
		logger.debug("isReforma:Rut:" + this.afpvRutDpndiente + ":Nombre:" + this.afpvNombreDpndiente + ":Pat:" + this.afpvAplldioPatDpndiente + ":Mat:" + this.afpvAplldioMatDpndiente + "::");
		if (this.afpvRutDpndiente > 0 || !this.afpvNombreDpndiente.trim().equals("") || !this.afpvAplldioPatDpndiente.trim().equals("") || !this.afpvAplldioMatDpndiente.trim().equals(""))
			return true;
		return false;
	}

	public int hashCode()
	{
		final int PRIME = 31;
		int result = super.hashCode();
		result = PRIME * result + this.asigFamReint;
		result = PRIME * result + this.asigFamRetro;
		result = PRIME * result + this.asigFamiliar;
		result = PRIME * result + this.ccafAporte;
		result = PRIME * result + this.ccafCredito;
		result = PRIME * result + this.ccafDental;
		result = PRIME * result + this.ccafLeasing;
		result = PRIME * result + this.ccafSeguro;
		result = PRIME * result + this.inpBonificacion;
		result = PRIME * result + this.inpDesahucio;
		result = PRIME * result + this.inpMutual;
		result = PRIME * result + ((this.movimientoPersonal == null) ? 0 : this.movimientoPersonal.hashCode());
		result = PRIME * result + this.mutualImp;
		// result = PRIME * result + previsionAdicional;
		result = PRIME * result + this.previsionAhorro;
		result = PRIME * result + this.previsionObligatorio;
		result = PRIME * result + this.previsionTotal;
		result = PRIME * result + this.rentaImp;
		result = PRIME * result + this.saludAdicional;
		result = PRIME * result + this.saludObligatorio;
		result = PRIME * result + this.saludPactado;
		result = PRIME * result + this.saludTotal;
		result = PRIME * result + this.segCesEmpl;
		result = PRIME * result + this.segCesRemImp;
		result = PRIME * result + this.segCesTrab;
		result = PRIME * result + Float.floatToIntBits(this.tasaTrabPesado);
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
		final CotizacionREVO other = (CotizacionREVO) obj;
		if (this.asigFamReint != other.asigFamReint)
			return false;
		if (this.asigFamRetro != other.asigFamRetro)
			return false;
		if (this.asigFamiliar != other.asigFamiliar)
			return false;
		if (this.ccafAporte != other.ccafAporte)
			return false;
		if (this.ccafCredito != other.ccafCredito)
			return false;
		if (this.ccafDental != other.ccafDental)
			return false;
		if (this.ccafLeasing != other.ccafLeasing)
			return false;
		if (this.ccafSeguro != other.ccafSeguro)
			return false;
		if (this.inpBonificacion != other.inpBonificacion)
			return false;
		if (this.inpDesahucio != other.inpDesahucio)
			return false;
		if (this.inpMutual != other.inpMutual)
			return false;
		if (this.movimientoPersonal == null)
		{
			if (other.movimientoPersonal != null)
				return false;
		} else if (!this.movimientoPersonal.equals(other.movimientoPersonal))
			return false;
		if (this.mutualImp != other.mutualImp)
			return false;
		if (this.previsionAhorro != other.previsionAhorro)
			return false;
		if (this.previsionObligatorio != other.previsionObligatorio)
			return false;
		if (this.previsionTotal != other.previsionTotal)
			return false;
		if (this.rentaImp != other.rentaImp)
			return false;
		if (this.saludAdicional != other.saludAdicional)
			return false;
		if (this.saludObligatorio != other.saludObligatorio)
			return false;
		if (this.saludPactado != other.saludPactado)
			return false;
		if (this.saludTotal != other.saludTotal)
			return false;
		if (this.segCesEmpl != other.segCesEmpl)
			return false;
		if (this.segCesRemImp != other.segCesRemImp)
			return false;
		if (this.segCesTrab != other.segCesTrab)
			return false;
		if (Float.floatToIntBits(this.tasaTrabPesado) != Float.floatToIntBits(other.tasaTrabPesado))
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

	public int getAsigFamiliar()
	{
		return this.asigFamiliar;
	}

	public void setAsigFamiliar(int asigFamiliar)
	{
		this.asigFamiliar = asigFamiliar;
	}

	public int getAsigFamReint()
	{
		return this.asigFamReint;
	}

	public void setAsigFamReint(int asigFamReint)
	{
		this.asigFamReint = asigFamReint;
	}

	public int getAsigFamRetro()
	{
		return this.asigFamRetro;
	}

	public void setAsigFamRetro(int asigFamRetro)
	{
		this.asigFamRetro = asigFamRetro;
	}

	public int getCcafAporte()
	{
		return this.ccafAporte;
	}

	public void setCcafAporte(int ccafAporte)
	{
		this.ccafAporte = ccafAporte;
	}

	public int getCcafCredito()
	{
		return this.ccafCredito;
	}

	public void setCcafCredito(int ccafCredito)
	{
		this.ccafCredito = ccafCredito;
	}

	public int getCcafDental()
	{
		return this.ccafDental;
	}

	public void setCcafDental(int ccafDental)
	{
		this.ccafDental = ccafDental;
	}

	public int getCcafLeasing()
	{
		return this.ccafLeasing;
	}

	public void setCcafLeasing(int ccafLeasing)
	{
		this.ccafLeasing = ccafLeasing;
	}

	public int getCcafSeguro()
	{
		return this.ccafSeguro;
	}

	public void setCcafSeguro(int ccafSeguro)
	{
		this.ccafSeguro = ccafSeguro;
	}

	public int getInpBonificacion()
	{
		return this.inpBonificacion;
	}

	public void setInpBonificacion(int inpBonificacion)
	{
		this.inpBonificacion = inpBonificacion;
	}

	public int getInpDesahucio()
	{
		return this.inpDesahucio;
	}

	public void setInpDesahucio(int inpDesahucio)
	{
		this.inpDesahucio = inpDesahucio;
	}

	public int getInpMutual()
	{
		return this.inpMutual;
	}

	public void setInpMutual(int inpMutual)
	{
		this.inpMutual = inpMutual;
	}

	public void setInpMutual(String inpMutual)
	{
		this.nombreMutual = inpMutual;
	}

	public int getMutualImp()
	{
		return this.mutualImp;
	}

	public void setMutualImp(int mutualImp)
	{
		this.mutualImp = mutualImp;
	}

	public int getPrevisionAdicional()
	{
		return this.previsionAdicional;
	}

	public void setPrevisionAdicional(int previsionAdicional)
	{
		this.previsionAdicional = previsionAdicional;
	}

	public int getPrevisionAhorro()
	{
		return this.previsionAhorro;
	}

	public void setPrevisionAhorro(int previsionAhorro)
	{
		this.previsionAhorro = previsionAhorro;
	}

	public int getPrevisionObligatorio()
	{
		return this.previsionObligatorio;
	}

	public void setPrevisionObligatorio(int previsionObligatorio)
	{
		this.previsionObligatorio = previsionObligatorio;
	}

	public int getPrevisionTotal()
	{
		return this.previsionTotal;
	}

	public void setPrevisionTotal(int previsionTotal)
	{
		this.previsionTotal = previsionTotal;
	}

	public int getRentaImp()
	{
		return this.rentaImp;
	}

	public void setRentaImp(int rentaImp)
	{
		this.rentaImp = rentaImp;
	}

	public int getSaludAdicional()
	{
		return this.saludAdicional;
	}

	public void setSaludAdicional(int saludAdicional)
	{
		this.saludAdicional = saludAdicional;
	}

	public int getSaludObligatorio()
	{
		return this.saludObligatorio;
	}

	public void setSaludObligatorio(int saludObligatorio)
	{
		this.saludObligatorio = saludObligatorio;
	}

	public int getSaludPactado()
	{
		return this.saludPactado;
	}

	public void setSaludPactado(int saludPactado)
	{
		this.saludPactado = saludPactado;
	}

	public int getSaludTotal()
	{
		return this.saludTotal;
	}

	public void setSaludTotal(int saludTotal)
	{
		this.saludTotal = saludTotal;
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

	public List getMovimientoPersonal()
	{
		return this.movimientoPersonal;
	}

	public void setMovimientoPersonal2(List movimientoPersonal)
	{
		if (this.movimientoPersonal == null)
			this.movimientoPersonal = new ArrayList();
		for (Iterator it = movimientoPersonal.iterator(); it.hasNext();)
		{
			MovtoPersonalVO mp = (MovtoPersonalVO) it.next();
			mp.setCotizacion(this);
			this.movimientoPersonal.add(mp);
		}
	}

	public void setMovimientoPersonal2AF(List movimientoPersonalAF)
	{
		if (this.movimientoPersonalAF == null)
			this.movimientoPersonalAF = new ArrayList();
		for (Iterator it = movimientoPersonalAF.iterator(); it.hasNext();)
		{
			MvtoPersoAFVO mp = (MvtoPersoAFVO) it.next();
			mp.setCotizacion(this);
			this.movimientoPersonalAF.add(mp);
		}
	}

	public void setMovimientoPersonal(List movimientoPersonal)
	{
		this.movimientoPersonal = movimientoPersonal;
	}

	public void addMovimientoPersonal(MovtoPersonalVO mp)
	{
		if (this.movimientoPersonal == null)
			this.movimientoPersonal = new ArrayList();

		mp.setIdMovimiento(this.movimientoPersonal.size() + 1);
		mp.setCotizacion(this);
		this.movimientoPersonal.add(mp);
	}

	public void addMovimientoPersonalAF(MvtoPersoAFVO mp)
	{
		if (this.movimientoPersonalAF == null)
			this.movimientoPersonalAF = new ArrayList();

		mp.setIdMovimiento(this.movimientoPersonalAF.size() + 1);
		mp.setCotizacion(this);
		this.movimientoPersonalAF.add(mp);
	}

	public void addMovtosPersonal(List lista)
	{
		this.movimientoPersonal.addAll(lista);
	}

	public void addMovtosPersonalAF(List lista)
	{
		this.movimientoPersonalAF.addAll(lista);
	}

	public MovtoPersonalVO getMovtoPers()
	{
		if (this.movimientoPersonal == null || this.movimientoPersonal.size() == 0)
			return null;
		return (MovtoPersonalVO) this.movimientoPersonal.get(0);

	}

	public MvtoPersoAFVO getMovtoPersAF()
	{
		if (this.movimientoPersonalAF == null || this.movimientoPersonalAF.size() == 0)
			return null;
		return (MvtoPersoAFVO) this.movimientoPersonalAF.get(0);

	}

	public void sumaMontos(CotizacionREVO c)
	{
		this.rentaImp += c.getRentaImp();
		this.rentaImpInp = this.rentaImp;
		this.asigFamiliar += c.getAsigFamiliar();
		this.asigFamRetro += c.getAsigFamRetro();
		this.asigFamReint += c.getAsigFamReint();
		this.saludObligatorio += c.getSaludObligatorio();
		this.saludPactado += c.getSaludPactado();
		this.saludAdicional += c.getSaludAdicional();
		this.saludTotal += c.getSaludTotal();
		this.previsionObligatorio += c.getPrevisionObligatorio();
		this.previsionTotal += c.getPrevisionTotal();
		this.segCesTrab += c.getSegCesTrab();
		this.segCesEmpl += c.getSegCesEmpl();
		this.trabPesado += c.getTrabPesado();
		this.mutualImp += c.getMutualImp();
		this.ccafAporte += c.getCcafAporte();
		this.segCesRemImp += c.getSegCesRemImp();
	}

	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getAPV1()
	{
		return this.APV1;
	}

	public void setAPV1(String apv1)
	{
		this.APV1 = apv1;
	}

	public String getAPV2()
	{
		return this.APV2;
	}

	public void setAPV2(String apv2)
	{
		this.APV2 = apv2;
	}

	public String getAPV3()
	{
		return this.APV3;
	}

	public void setAPV3(String apv3)
	{
		this.APV3 = apv3;
	}

	public String getAPV4()
	{
		return this.APV4;
	}

	public void setAPV4(String apv4)
	{
		this.APV4 = apv4;
	}

	public String getAPV5()
	{
		return this.APV5;
	}

	public void setAPV5(String apv5)
	{
		this.APV5 = apv5;
	}

	public String getNombreMutual()
	{
		return this.nombreMutual;
	}

	public void setNombreMutual(String nombreMutual)
	{
		this.nombreMutual = nombreMutual;
	}

	public int getTotalAsigFam()
	{
		return this.totalAsigFam;
	}

	public void setTotalAsigFam(int totalAsigFam)
	{
		this.totalAsigFam = totalAsigFam;
	}

	public String getAfpvAplldioMatDpndiente()
	{
		return this.afpvAplldioMatDpndiente;
	}

	public void setAfpvAplldioMatDpndiente(String afpvAplldioMatDpndiente)
	{
		this.afpvAplldioMatDpndiente = afpvAplldioMatDpndiente;
	}

	public String getAfpvAplldioPatDpndiente()
	{
		return this.afpvAplldioPatDpndiente;
	}

	public void setAfpvAplldioPatDpndiente(String afpvAplldioPatDpndiente)
	{
		this.afpvAplldioPatDpndiente = afpvAplldioPatDpndiente;
	}

	public String getAfpvNombreDpndiente()
	{
		return this.afpvNombreDpndiente;
	}

	public void setAfpvNombreDpndiente(String afpvNombreDpndiente)
	{
		this.afpvNombreDpndiente = afpvNombreDpndiente;
	}

	public int getAfpvRutDpndiente()
	{
		return this.afpvRutDpndiente;
	}

	public void setAfpvRutDpndiente(int afpvRutDpndiente)
	{
		this.afpvRutDpndiente = afpvRutDpndiente;
	}

	public int getApvcAporteEmpl()
	{
		return this.apvcAporteEmpl;
	}

	public void setApvcAporteEmpl(int apvcAporteEmpl)
	{
		this.apvcAporteEmpl = apvcAporteEmpl;
	}

	public int getApvcAporteTrab()
	{
		return this.apvcAporteTrab;
	}

	public void setApvcAporteTrab(int apvcAporteTrab)
	{
		this.apvcAporteTrab = apvcAporteTrab;
	}

	public String getApvcNumContr()
	{
		return this.apvcNumContr;
	}

	public void setApvcNumContr(String apvcNumContr)
	{
		this.apvcNumContr = apvcNumContr;
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.rutEmpresa));
		parametros.put("2", String.valueOf(this.idConvenio));
		parametros.put("3", String.valueOf(this.idCotizante));
		parametros.put("4", String.valueOf(this.idEstadoEdicion));
		parametros.put("5", String.valueOf(0));
		parametros.put("6", String.valueOf(0));
		parametros.put("7", String.valueOf(this.rentaImp));
		parametros.put("8", String.valueOf(this.asigFamiliar));
		parametros.put("9", String.valueOf(this.asigFamRetro));
		parametros.put("10", String.valueOf(this.asigFamReint));
		parametros.put("11", String.valueOf(this.saludObligatorio));
		parametros.put("12", String.valueOf(0));
		parametros.put("13", String.valueOf(0));
		parametros.put("14", String.valueOf(this.saludPactado));
		parametros.put("15", String.valueOf(this.saludAdicional));
		parametros.put("16", String.valueOf(this.saludTotal));
		parametros.put("17", String.valueOf(this.previsionObligatorio));
		parametros.put("18", String.valueOf(this.previsionAdicional));
		parametros.put("19", String.valueOf(this.previsionAhorro));
		parametros.put("20", String.valueOf(this.previsionTotal));
		parametros.put("21", String.valueOf(0));
		parametros.put("22", String.valueOf(0));
		parametros.put("23", String.valueOf(this.inpBonificacion));
		parametros.put("24", String.valueOf(this.inpDesahucio));
		parametros.put("25", String.valueOf(0));
		parametros.put("26", String.valueOf(0));
		parametros.put("27", String.valueOf(0));
		parametros.put("28", String.valueOf(this.ccafCredito));
		parametros.put("29", String.valueOf(this.ccafDental));
		parametros.put("30", String.valueOf(this.ccafLeasing));
		parametros.put("31", String.valueOf(this.ccafSeguro));
		parametros.put("32", String.valueOf(0));
		parametros.put("33", String.valueOf(this.ccafAporte));
		parametros.put("34", String.valueOf(this.segCesRemImp));
		parametros.put("35", String.valueOf(this.segCesTrab));
		parametros.put("36", String.valueOf(this.segCesEmpl));
		parametros.put("37", String.valueOf(0));
		parametros.put("38", String.valueOf(0));
		parametros.put("39", String.valueOf(this.tasaTrabPesado));
		parametros.put("40", String.valueOf(this.tipoTrabPesado));
		parametros.put("41", String.valueOf(this.trabPesado));
		parametros.put("42", String.valueOf(this.mutualImp));
		parametros.put("43", String.valueOf(this.apvcAporteEmpl));
		parametros.put("44", String.valueOf(this.apvcAporteTrab));
		parametros.put("45", String.valueOf(this.apvcNumContr));
		parametros.put("46", String.valueOf(this.afpvRutDpndiente));
		parametros.put("47", String.valueOf(this.afpvNombreDpndiente));
		parametros.put("48", String.valueOf(this.afpvAplldioPatDpndiente));
		parametros.put("49", String.valueOf(this.afpvAplldioMatDpndiente));
		
		parametros.put("50", String.valueOf(this.previsionSIS));
		parametros.put("51", String.valueOf(this.rentaImponibleSIS));		
		return parametros;
	}

	public int getAfectoImpuesto()
	{
		return this.afectoImpuesto;
	}

	public void setAfectoImpuesto(int afectoImpuesto)
	{
		this.afectoImpuesto = afectoImpuesto;
	}

	public int getCcafOtros()
	{
		return this.ccafOtros;
	}

	public void setCcafOtros(int ccafOtros)
	{
		this.ccafOtros = ccafOtros;
	}

	public int getFun()
	{
		return this.fun;
	}

	public void setFun(int fun)
	{
		this.fun = fun;
	}

	public int getImpuestoUnico()
	{
		return this.impuestoUnico;
	}

	public void setImpuestoUnico(int impuestoUnico)
	{
		this.impuestoUnico = impuestoUnico;
	}

	public int getInpOtrosAportes()
	{
		return this.inpOtrosAportes;
	}

	public void setInpOtrosAportes(int inpOtrosAportes)
	{
		this.inpOtrosAportes = inpOtrosAportes;
	}

	public int getPorcentajeBonificacionSalud()
	{
		return this.porcentajeBonificacionSalud;
	}

	public void setPorcentajeBonificacionSalud(int porcentajeBonificacionSalud)
	{
		this.porcentajeBonificacionSalud = porcentajeBonificacionSalud;
	}

	public int getRentaTributable()
	{
		return this.rentaTributable;
	}

	public void setRentaTributable(int rentaTributable)
	{
		this.rentaTributable = rentaTributable;
	}

	public int getSaludBonificacion()
	{
		return this.saludBonificacion;
	}

	public void setSaludBonificacion(int saludBonificacion)
	{
		this.saludBonificacion = saludBonificacion;
	}

	public int getSegInvAporteEmpl()
	{
		return this.segInvAporteEmpl;
	}

	public void setSegInvAporteEmpl(int segInvAporteEmpl)
	{
		this.segInvAporteEmpl = segInvAporteEmpl;
	}

	public int getSegInvAporteTrab()
	{
		return this.segInvAporteTrab;
	}

	public void setSegInvAporteTrab(int segInvAporteTrab)
	{
		this.segInvAporteTrab = segInvAporteTrab;
	}

	public int getInpPension()
	{
		return this.inpPension;
	}

	public void setInpPension(int inpPension)
	{
		this.inpPension = inpPension;
	}

	public List getMovimientoPersonalAF()
	{
		return this.movimientoPersonalAF;
	}

	public void setMovimientoPersonalAF(List movimientoPersonalAF)
	{
		this.movimientoPersonalAF = movimientoPersonalAF;
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
}
