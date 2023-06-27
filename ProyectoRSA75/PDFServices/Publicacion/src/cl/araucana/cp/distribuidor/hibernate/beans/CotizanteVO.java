package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CotizanteVO extends AuditableVO implements Comparable
{
	private static final long serialVersionUID = 1926039670455346494L;
	private int rutEmpresa;
	private int idConvenio;
	private int idCotizante;
	private char tipoProceso;

	private int tieneAviso;
	private int tieneRemu;
	private int tieneGrat;
	private int tieneReli;
	private int tieneDepo;

	private int tipoPrevision;// 0: sin prevision, 1: AFP, 2: INP
	private boolean isapre;
	private boolean afpVoluntario = false;

	private String idEntSalud;
	private String idTramo;
	private int idEntExCaja;
	private int idRegimenImp;
	private String idSucursal = "";
	private String idEntPension;
	private int idEntSil;
	private String idEntAfc;
	private String idEntidadAPVC;
	private String idEntidadAFPV;
	private String idGenero;
	private int numCargaSimple;
	private int numCargaMaterna;
	private int numCargaInvalidez;
	private int numDiasTrabajados;
	private String nombre = "";
	private String apellidoPat = "";
	private String apellidoMat = "";
	
	//jlandero 23/08/2010
	private String formatRut;
	
	private String linea;
	private CotizacionVO cotizacion;
	private List apvList;
	private String nombreCaja;

	// campos con valores reales de la DB, donde se han aplicado las transformaciones para estandarizarlos (con valores entendidos por LA)
	private int idEntSaludReal;
	private int idTramoReal;
	private int idEntPensionReal;
	private int idEntAfcReal;
	private int idGeneroReal;
	private int idEntidadAPVCReal = 0;
	private int idEntidadAFPVReal = 0;

	public CotizanteVO(char tipoProceso, int rutEmpresa, int idConvenio)
	{
		super();
		this.tipoProceso = tipoProceso;
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
	}

	public CotizanteVO(int rutEmpresa, char tipoProceso, int idCotizante)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.tipoProceso = tipoProceso;
		this.idCotizante = idCotizante;
	}

	public CotizanteVO(int rutEmpresa, int idConvenio, char tipoProceso, int idCotizante)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.tipoProceso = tipoProceso;
		this.idCotizante = idCotizante;
	}

	public CotizanteVO(int rutEmpresa, int idConvenio, int idCotizante)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idCotizante = idCotizante;
	}

	public CotizanteVO(int rutEmpresa, int idConvenio, CotizanteVO cotizanteData)
	{
		super();
		this.rutEmpresa = rutEmpresa;
		this.idConvenio = idConvenio;
		this.idEntSaludReal = cotizanteData.getIdEntSaludReal();
		this.idTramoReal = cotizanteData.getIdTramoReal();
		this.idEntPensionReal = cotizanteData.getIdEntPensionReal();
		this.idEntAfcReal = cotizanteData.getIdEntAfcReal();
		this.idGeneroReal = cotizanteData.getIdGeneroReal();
	}

	public CotizanteVO(CotizanteVO cotOld)
	{
		super();
		this.rutEmpresa = cotOld.getRutEmpresa();
		this.idConvenio = cotOld.getIdConvenio();
		this.idCotizante = cotOld.getIdCotizante();
		this.idEntSaludReal = cotOld.getIdEntSaludReal();
		this.idTramoReal = cotOld.getIdTramoReal();
		this.idEntPensionReal = cotOld.getIdEntPensionReal();
		this.idEntAfcReal = cotOld.getIdEntAfcReal();
		this.idEntidadAPVCReal = cotOld.getIdEntidadAPVCReal();
		this.idEntidadAFPVReal = cotOld.getIdEntidadAFPVReal();
		this.idEntExCaja = cotOld.getIdEntExCaja();
		this.idRegimenImp = cotOld.getIdRegimenImp();
		this.idEntSil = cotOld.getIdEntSil();
		this.numCargaSimple = cotOld.getNumCargaSimple();
		this.numCargaMaterna = cotOld.getNumCargaMaterna();
		this.numCargaInvalidez = cotOld.getNumCargaInvalidez();
		this.numDiasTrabajados = cotOld.getNumDiasTrabajados();
	}

	public CotizanteVO()
	{
		super();
	}

	public void merge(CotizanteVO cotOld)
	{
		this.idEntSaludReal = cotOld.getIdEntSaludReal();
		this.idTramoReal = cotOld.getIdTramoReal();
		this.idEntPensionReal = cotOld.getIdEntPensionReal();
		this.idEntAfcReal = cotOld.getIdEntAfcReal();
		this.idEntidadAFPVReal = cotOld.getIdEntidadAFPVReal();
		this.idEntidadAPVCReal = cotOld.getIdEntidadAPVCReal();
		this.idEntExCaja = cotOld.getIdEntExCaja();
		this.idRegimenImp = cotOld.getIdRegimenImp();
		this.idEntSil = cotOld.getIdEntSil();
		this.numCargaSimple = cotOld.getNumCargaSimple();
		this.numCargaMaterna = cotOld.getNumCargaMaterna();
		this.numCargaInvalidez = cotOld.getNumCargaInvalidez();
		this.numDiasTrabajados = cotOld.getNumDiasTrabajados();
	}

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
		final CotizanteVO other = (CotizanteVO) obj;
		if (this.idConvenio != other.idConvenio)
			return false;
		if (this.idCotizante != other.idCotizante)
			return false;
		if (this.rutEmpresa != other.rutEmpresa)
			return false;
		return true;
	}

	public String getApellidoMat()
	{
		return this.apellidoMat;
	}

	public void setApellidoMat(String apellidoMat)
	{
		this.apellidoMat = apellidoMat;
	}

	public String getApellidoPat()
	{
		return this.apellidoPat;
	}

	public void setApellidoPat(String apellidoPat)
	{
		this.apellidoPat = apellidoPat;
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

	public String getIdEntAfc()
	{
		return this.idEntAfc;
	}

	public void setIdEntAfc(String idEntAfc)
	{
		this.idEntAfc = idEntAfc;
	}

	public int getIdEntExCaja()
	{
		return this.idEntExCaja;
	}

	public void setIdEntExCaja(int idEntExCaja)
	{
		this.idEntExCaja = idEntExCaja;
	}

	public String getIdEntPension()
	{
		return this.idEntPension;
	}

	public void setIdEntPension(String idEntPension)
	{
		this.idEntPension = idEntPension;
	}

	public String getIdEntSalud()
	{
		return this.idEntSalud;
	}

	public void setIdEntSalud(String idEntSalud)
	{
		this.idEntSalud = idEntSalud;
	}

	public int getIdEntSil()
	{
		return this.idEntSil;
	}

	public void setIdEntSil(int idEntSil)
	{
		this.idEntSil = idEntSil;
	}

	public String getIdGenero()
	{
		return this.idGenero;
	}

	public void setIdGenero(String idGenero)
	{
		this.idGenero = idGenero;
	}

	public int getIdRegimenImp()
	{
		return this.idRegimenImp;
	}

	public void setIdRegimenImp(int idRegimenImp)
	{
		this.idRegimenImp = idRegimenImp;
	}

	public String getIdSucursal()
	{
		return this.idSucursal;
	}

	public void setIdSucursal(String idSucursal)
	{
		this.idSucursal = idSucursal;
	}

	public String getIdTramo()
	{
		return this.idTramo;
	}

	public void setIdTramo(String idTramo)
	{
		this.idTramo = idTramo;
	}

	public String getNombre()
	{
		return this.nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public int getNumCargaInvalidez()
	{
		return this.numCargaInvalidez;
	}

	public void setNumCargaInvalidez(int numCargaInvalidez)
	{
		this.numCargaInvalidez = numCargaInvalidez;
	}

	public int getNumCargaMaterna()
	{
		return this.numCargaMaterna;
	}

	public void setNumCargaMaterna(int numCargaMaterna)
	{
		this.numCargaMaterna = numCargaMaterna;
	}

	public int getNumCargaSimple()
	{
		return this.numCargaSimple;
	}

	public void setNumCargaSimple(int numCargaSimple)
	{
		this.numCargaSimple = numCargaSimple;
	}

	public int getNumDiasTrabajados()
	{
		return this.numDiasTrabajados;
	}

	public void setNumDiasTrabajados(int numDiasTrabajados)
	{
		this.numDiasTrabajados = numDiasTrabajados;
	}

	public int getRutEmpresa()
	{
		return this.rutEmpresa;
	}

	public void setRutEmpresa(int rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
	}

	public CotizacionVO getCotizacion()
	{
		return this.cotizacion;
	}

	public void setCotizacion(CotizacionVO cotizacion)
	{
		this.cotizacion = cotizacion;
	}

	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getLinea()
	{
		return this.linea;
	}

	public void setLinea(String linea)
	{
		this.linea = linea;
	}

	public void addMovimientoPersonal(int idMPReal)
	{
		((CotizacionREVO) this.cotizacion).addMovimientoPersonal(new MovtoPersonalVO(this.rutEmpresa, this.idConvenio, this.idCotizante, 0, idMPReal));
	}

	public void addMovimientoPersonalAF(int idMPReal)
	{
		((CotizacionREVO) this.cotizacion).addMovimientoPersonalAF(new MvtoPersoAFVO(this.rutEmpresa, this.idConvenio, this.idCotizante, 0, idMPReal));
	}

	public void addApv(int idAPVReal)
	{
		if (this.apvList == null)
			this.apvList = new ArrayList();
		ApvVO apv = new ApvVO(this.rutEmpresa, this.idConvenio, this.idCotizante, idAPVReal);
		this.apvList.add(apv);
	}

	public void addApv(int idAPVReal, int monto)
	{
		if (this.apvList == null)
			this.apvList = new ArrayList();
		ApvVO apv = new ApvVO(this.rutEmpresa, this.idConvenio, this.idCotizante, idAPVReal, monto);
		this.apvList.add(apv);
	}

	public void addApv(ApvVO apv)
	{
		if (this.apvList == null)
			this.apvList = new ArrayList();
		this.apvList.add(apv);
	}

	public ApvVO getApv()
	{
		if (this.apvList == null || this.apvList.size() == 0)
			return null;
		if (this.apvList.size() > 0)
			return (ApvVO) this.apvList.get(this.apvList.size() - 1);
		return null;
	}

	public ApvVO getApv(int i)
	{
		if (this.apvList == null || this.apvList.size() <= i)
			return null;
		return (ApvVO) this.apvList.get(i);
	}

	public int getIdEntAfcReal()
	{
		return this.idEntAfcReal;
	}

	public void setIdEntAfcReal(int idEntAfcReal)
	{
		this.idEntAfcReal = idEntAfcReal;
	}

	public int getIdEntPensionReal()
	{
		return this.idEntPensionReal;
	}

	public void setIdEntPensionReal(int idEntPensionReal)
	{
		this.idEntPensionReal = idEntPensionReal;
	}

	public int getIdEntSaludReal()
	{
		return this.idEntSaludReal;
	}

	public void setIdEntSaludReal(int idEntSaludReal)
	{
		this.idEntSaludReal = idEntSaludReal;
	}

	public int getIdGeneroReal()
	{
		return this.idGeneroReal;
	}

	public void setIdGeneroReal(int idGeneroReal)
	{
		this.idGeneroReal = idGeneroReal;
	}

	public int getIdTramoReal()
	{
		return this.idTramoReal;
	}

	public void setIdTramoReal(int idTramoReal)
	{
		this.idTramoReal = idTramoReal;
	}

	public char getTipoProceso()
	{
		return this.tipoProceso;
	}

	public void setTipoProceso(char tipoProceso)
	{
		this.tipoProceso = tipoProceso;
	}

	public List getApvList()
	{
		return this.apvList;
	}

	public void setApvList(List apvList)
	{
		this.apvList = apvList;
	}

	public int getTipoPrevision()
	{
		return this.tipoPrevision;
	}

	public void setTipoPrevision(int tipoPrevision)
	{
		this.tipoPrevision = tipoPrevision;
	}

	public boolean isIsapre()
	{
		return this.isapre;
	}

	public void setIsapre(boolean isapre)
	{
		this.isapre = isapre;
	}

	public String getNombreCaja()
	{
		return this.nombreCaja;
	}

	public void setNombreCaja(String nombreCaja)
	{
		this.nombreCaja = nombreCaja;
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.rutEmpresa));
		parametros.put("2", String.valueOf(this.idConvenio));
		parametros.put("3", String.valueOf(this.idCotizante));
		parametros.put("4", String.valueOf(this.idEntSaludReal));
		parametros.put("5", String.valueOf(this.idTramoReal));
		parametros.put("6", String.valueOf(this.idEntExCaja));
		parametros.put("7", String.valueOf(this.idRegimenImp));
		parametros.put("8", String.valueOf(this.idSucursal));
		parametros.put("9", String.valueOf(this.idEntPensionReal));
		parametros.put("10", String.valueOf(this.idEntSil));
		parametros.put("11", String.valueOf(this.idEntAfcReal));
		parametros.put("12", String.valueOf(this.idGeneroReal));
		parametros.put("13", String.valueOf(this.nombre));
		parametros.put("14", String.valueOf(this.apellidoPat));
		parametros.put("15", String.valueOf(this.apellidoMat));
		parametros.put("16", String.valueOf(this.numCargaSimple));
		parametros.put("17", String.valueOf(this.numCargaMaterna));
		parametros.put("18", String.valueOf(this.numCargaInvalidez));
		parametros.put("19", String.valueOf(this.numDiasTrabajados));
		parametros.put("20", String.valueOf(this.idEntidadAFPVReal));
		parametros.put("21", String.valueOf(this.idEntidadAPVCReal));

		return parametros;
	}

	public String getIdEntidadAPVC()
	{
		return this.idEntidadAPVC;
	}

	public void setIdEntidadAPVC(String idEntidadAPVC)
	{
		this.idEntidadAPVC = idEntidadAPVC;
	}

	public int getIdEntidadAPVCReal()
	{
		return this.idEntidadAPVCReal;
	}

	public void setIdEntidadAPVCReal(int idEntidadAPVCReal)
	{
		this.idEntidadAPVCReal = idEntidadAPVCReal;
	}

	public String getIdEntidadAFPV()
	{
		return this.idEntidadAFPV;
	}

	public void setIdEntidadAFPV(String idEntidadAFPV)
	{
		this.idEntidadAFPV = idEntidadAFPV;
	}

	public int getIdEntidadAFPVReal()
	{
		return this.idEntidadAFPVReal;
	}

	public void setIdEntidadAFPVReal(int idEntidadAFPVReal)
	{
		this.idEntidadAFPVReal = idEntidadAFPVReal;
	}

	public boolean isAfpVoluntario()
	{
		return this.afpVoluntario;
	}

	public void setAfpVoluntario(boolean afpVoluntario)
	{
		this.afpVoluntario = afpVoluntario;
	}

	public void sumaDias(CotizanteVO c)
	{
		this.numDiasTrabajados += c.getNumDiasTrabajados();
	}

	public int getTieneAviso()
	{
		return this.tieneAviso;
	}

	public void setTieneAviso(int tieneAviso)
	{
		this.tieneAviso = tieneAviso;
	}

	public int getTieneDepo()
	{
		return this.tieneDepo;
	}

	public void setTieneDepo(int tieneDepo)
	{
		this.tieneDepo = tieneDepo;
	}

	public int getTieneGrat()
	{
		return this.tieneGrat;
	}

	public void setTieneGrat(int tieneGrat)
	{
		this.tieneGrat = tieneGrat;
	}

	public int getTieneReli()
	{
		return this.tieneReli;
	}

	public void setTieneReli(int tieneReli)
	{
		this.tieneReli = tieneReli;
	}

	public int getTieneRemu()
	{
		return this.tieneRemu;
	}

	public void setTieneRemu(int tieneRemu)
	{
		this.tieneRemu = tieneRemu;
	}

	public void updateTiene(char tp, CotizanteVO cotOld)
	{
		if (tp == 'R')
			this.tieneRemu = 1;
		else
			this.tieneRemu = cotOld.getTieneRemu();
		if (tp == 'G')
			this.tieneGrat = 1;
		else
			this.tieneGrat = cotOld.getTieneGrat();
		if (tp == 'A')
			this.tieneReli = 1;
		else
			this.tieneReli = cotOld.getTieneReli();
		if (tp == 'D')
			this.tieneDepo = 1;
		else
			this.tieneDepo = cotOld.getTieneDepo();
	}

	public static String getTipoTiene(char tp)
	{
		if (tp == 'R')
			return "tieneRemu";
		if (tp == 'G')
			return "tieneGrat";
		if (tp == 'A')
			return "tieneReli";
		return "tieneDepo";
	}

	public int compareTo(Object o)
	{
		Integer este = new Integer(this.idCotizante);
		return este.compareTo(new Integer(((CotizanteVO) o).getIdCotizante()));
	}

	public void setTiene(char tp)
	{
		if (tp == 'R')
			this.tieneRemu = 1;
		else if (tp == 'G')
			this.tieneGrat = 1;
		else if (tp == 'A')
			this.tieneReli = 1;
		else if (tp == 'D')
			this.tieneDepo = 1;
	}

	public void sacaTiene(char tp)
	{
		if (tp == 'R')
			this.tieneRemu = 0;
		else if (tp == 'G')
			this.tieneGrat = 0;
		else if (tp == 'A')
			this.tieneReli = 0;
		else if (tp == 'D')
			this.tieneDepo = 0;
	}

	public static String getTipoTieneReal(char tp)
	{
		switch (tp)
		{
		case 'R': 
			return "con_remu";
		case 'G': 
			return "con_grat";
		case 'A':
			return "con_reli";
		default:
			return "con_depo";
		}
	}

	public String getFormatRut()
	{
		return this.formatRut;
	}

	public void setFormatRut(String formatRut)
	{
		this.formatRut = formatRut;
	}

}
