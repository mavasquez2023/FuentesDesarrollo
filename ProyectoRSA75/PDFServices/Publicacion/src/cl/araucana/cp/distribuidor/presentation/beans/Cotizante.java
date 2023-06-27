package cl.araucana.cp.distribuidor.presentation.beans;

import java.io.Serializable;
import java.util.List;

import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;

/*
 * @(#) Cotizante.java 1.13 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.13
 */
public class Cotizante implements Serializable, Comparable
{
	private static final long serialVersionUID = -5547203699208950913L;

	private String idCotizante;
	private int idConvenio;
	private int rutEmpresa;
	private char tipoProceso;
	private int idCotizPendiente;
	private int nivelError = 0;
	private boolean afpVoluntario = false;

	private String rut;
	private String nombre;
	private String apellidos;
	private String sucursal;

	// //////////////////////////////
	private int idEntSaludReal = -1;
	private int idTramoReal = -1;
	private int idTramoRealINP = -1;
	private int idEntPensionReal = -1;
	private int idEntAFCReal = -1;
	private String idGeneroReal = "-1";
	private String idEntSalud = "";
	private String idTramo;
	private String idEntPension = "";
	private String idEntAFC = "";
	private String idGenero;

	private int idEntExCaja;
	private int idRegimenImp;
	private String idSucursal = "";
	private int idEntSil;
	private String numCargaSimple;
	private String numCargaMaterna;
	private String numCargaInvalidez;
	private String numCargaSimpleINP;
	private String numCargaMaternaINP;
	private String numCargaInvalidezINP;
	private String numDiasTrabajados;
	private String apellidoPat = "";
	private String apellidoMat = "";

	private String tasaCotizacion; // parametros INP: ex-caja y cod reg imp

	private Cotizacion cotizacion;
	private List apvs;

	// reforma
	private String idEntidadAPVC;
	private int idEntidadAPVCReal;
	private String idEntidadAFPV;
	private int idEntidadAFPVReal;

	/**
	 * apvs
	 * 
	 * @return
	 */
	public List getApvs()
	{
		return this.apvs;
	}

	/**
	 * apvs
	 * 
	 * @param apvs
	 */
	public void setApvs(List apvs)
	{
		this.apvs = apvs;
	}

	public Cotizante()
	{
		super();
	}

	/**
	 * cotizante
	 * 
	 * @param idCotizante
	 * @param idConvenio
	 * @param rutEmpresa
	 * @param tipoProceso
	 */
	public Cotizante(String idCotizante, int idConvenio, int rutEmpresa, char tipoProceso)
	{
		super();
		this.idCotizante = idCotizante;
		this.idConvenio = idConvenio;
		this.rutEmpresa = rutEmpresa;
		this.tipoProceso = tipoProceso;
	}

	/**
	 * apellidos
	 * 
	 * @return
	 */
	public String getApellidos()
	{
		return this.apellidos;
	}

	/**
	 * apellidos
	 * 
	 * @param apellidos
	 */
	public void setApellidos(String apellidos)
	{
		this.apellidos = apellidos;
	}

	/**
	 * nombre
	 * 
	 * @return
	 */
	public String getNombre()
	{
		return this.nombre;
	}

	/**
	 * nombre
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	/**
	 * rut
	 * 
	 * @return
	 */
	public String getRut()
	{
		return this.rut;
	}

	/**
	 * rut
	 * 
	 * @param rut
	 */
	public void setRut(String rut)
	{
		this.rut = rut;
	}

	/**
	 * id convenio
	 * 
	 * @return
	 */
	public int getIdConvenio()
	{
		return this.idConvenio;
	}

	/**
	 * id convenio
	 * 
	 * @param idConvenio
	 */
	public void setIdConvenio(int idConvenio)
	{
		this.idConvenio = idConvenio;
	}

	/*
	 * id cotizante
	 */
	public String getIdCotizante()
	{
		return this.idCotizante;
	}

	/**
	 * id cotizante
	 * 
	 * @param idCotizante
	 */
	public void setIdCotizante(String idCotizante)
	{
		this.idCotizante = idCotizante;
	}

	/**
	 * rut empresa
	 * 
	 * @return
	 */
	public int getRutEmpresa()
	{
		return this.rutEmpresa;
	}

	/**
	 * rut empresa
	 * 
	 * @param rutEmpresa
	 */
	public void setRutEmpresa(int rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
	}

	/**
	 * tipo proceso
	 * 
	 * @return
	 */
	public char getTipoProceso()
	{
		return this.tipoProceso;
	}

	/**
	 * tipo proceso
	 * 
	 * @param tipoProceso
	 */
	public void setTipoProceso(char tipoProceso)
	{
		this.tipoProceso = tipoProceso;
	}

	/**
	 * apellido materno
	 * 
	 * @return
	 */
	public String getApellidoMat()
	{
		return this.apellidoMat;
	}

	/**
	 * apellido materno
	 * 
	 * @param apellidoMat
	 */
	public void setApellidoMat(String apellidoMat)
	{
		this.apellidoMat = apellidoMat;
	}

	/**
	 * apellido paterno
	 * 
	 * @return
	 */
	public String getApellidoPat()
	{
		return this.apellidoPat;
	}

	/**
	 * apellido paterno
	 * 
	 * @param apellidoPat
	 */
	public void setApellidoPat(String apellidoPat)
	{
		this.apellidoPat = apellidoPat;
	}

	/**
	 * id entidad ex caja
	 * 
	 * @return
	 */
	public int getIdEntExCaja()
	{
		return this.idEntExCaja;
	}

	/**
	 * id entidad ex caja
	 * 
	 * @param idEntExCaja
	 */
	public void setIdEntExCaja(int idEntExCaja)
	{
		this.idEntExCaja = idEntExCaja;
	}

	/**
	 * id entidad sil
	 * 
	 * @return
	 */
	public int getIdEntSil()
	{
		return this.idEntSil;
	}

	/**
	 * id entidad sil
	 * 
	 * @param idEntSil
	 */
	public void setIdEntSil(int idEntSil)
	{
		this.idEntSil = idEntSil;
	}

	/**
	 * id genero real
	 * 
	 * @return
	 */
	public String getIdGeneroReal()
	{
		return this.idGeneroReal;
	}

	/**
	 * id genero real
	 * 
	 * @param idGeneroReal
	 */
	public void setIdGeneroReal(String idGeneroReal)
	{
		this.idGeneroReal = idGeneroReal;
	}

	/**
	 * id regimen imp
	 * 
	 * @return
	 */
	public int getIdRegimenImp()
	{
		return this.idRegimenImp;
	}

	/**
	 * id regimen imp
	 * 
	 * @param idRegimenImp
	 */
	public void setIdRegimenImp(int idRegimenImp)
	{
		this.idRegimenImp = idRegimenImp;
	}

	/**
	 * is sucursal
	 * 
	 * @return
	 */
	public String getIdSucursal()
	{
		return this.idSucursal;
	}

	/**
	 * id sucursal
	 * 
	 * @param idSucursal
	 */
	public void setIdSucursal(String idSucursal)
	{
		this.idSucursal = idSucursal;
	}

	/**
	 * id entidad pension real
	 * 
	 * @return
	 */
	public int getIdEntPensionReal()
	{
		return this.idEntPensionReal;
	}

	/**
	 * id entidad pension real
	 * 
	 * @param idEntPensionReal
	 */
	public void setIdEntPensionReal(int idEntPensionReal)
	{
		this.idEntPensionReal = idEntPensionReal;
	}

	/**
	 * id entidad salud real
	 * 
	 * @return
	 */
	public int getIdEntSaludReal()
	{
		return this.idEntSaludReal;
	}

	/**
	 * id entidad salud real
	 * 
	 * @param idEntSaludReal
	 */
	public void setIdEntSaludReal(int idEntSaludReal)
	{
		this.idEntSaludReal = idEntSaludReal;
	}

	/**
	 * id tramo real
	 * 
	 * @return
	 */
	public int getIdTramoReal()
	{
		return this.idTramoReal;
	}

	/**
	 * id tramo real
	 * 
	 * @param idTramoReal
	 */
	public void setIdTramoReal(int idTramoReal)
	{
		this.idTramoReal = idTramoReal;
	}

	/**
	 * sucursal
	 * 
	 * @return
	 */
	public String getSucursal()
	{
		return this.sucursal;
	}

	/**
	 * sucursal
	 * 
	 * @param sucursal
	 */
	public void setSucursal(String sucursal)
	{
		this.sucursal = sucursal;
	}

	/**
	 * id cotizacion pendiente
	 * 
	 * @return
	 */
	public int getIdCotizPendiente()
	{
		return this.idCotizPendiente;
	}

	/**
	 * id cotizacion pendiente
	 * 
	 * @param idCotizPendiente
	 */
	public void setIdCotizPendiente(int idCotizPendiente)
	{
		this.idCotizPendiente = idCotizPendiente;
	}

	/**
	 * id entidad pension
	 * 
	 * @return
	 */
	public String getIdEntPension()
	{
		return this.idEntPension;
	}

	/**
	 * id entidad pension
	 * 
	 * @param idEntPension
	 */
	public void setIdEntPension(String idEntPension)
	{
		this.idEntPension = idEntPension;
	}

	/**
	 * id entidad salud
	 * 
	 * @return
	 */
	public String getIdEntSalud()
	{
		return this.idEntSalud;
	}

	/**
	 * id entidad salud
	 * 
	 * @param idEntSalud
	 */
	public void setIdEntSalud(String idEntSalud)
	{
		this.idEntSalud = idEntSalud;
	}

	/**
	 * id genero
	 * 
	 * @return
	 */
	public String getIdGenero()
	{
		return this.idGenero;
	}

	/**
	 * id genero
	 * 
	 * @param idGenero
	 */
	public void setIdGenero(String idGenero)
	{
		this.idGenero = idGenero;
	}

	/**
	 * id tramo
	 * 
	 * @return
	 */
	public String getIdTramo()
	{
		return this.idTramo;
	}

	/**
	 * id tramo
	 * 
	 * @param idTramo
	 */
	public void setIdTramo(String idTramo)
	{
		this.idTramo = idTramo;
	}

	/**
	 * id tramo real inp
	 * 
	 * @return
	 */
	public int getIdTramoRealINP()
	{
		return this.idTramoRealINP;
	}

	/**
	 * id tramo real inp
	 * 
	 * @param idTramoRealINP
	 */
	public void setIdTramoRealINP(int idTramoRealINP)
	{
		this.idTramoRealINP = idTramoRealINP;
	}

	public String getNumCargaInvalidez()
	{
		return this.numCargaInvalidez;
	}

	public void setNumCargaInvalidez(String numCargaInvalidez)
	{
		this.numCargaInvalidez = numCargaInvalidez;
	}

	public String getNumCargaInvalidezINP()
	{
		return this.numCargaInvalidezINP;
	}

	public void setNumCargaInvalidezINP(String numCargaInvalidezINP)
	{
		this.numCargaInvalidezINP = numCargaInvalidezINP;
	}

	public String getNumCargaMaterna()
	{
		return this.numCargaMaterna;
	}

	public void setNumCargaMaterna(String numCargaMaterna)
	{
		this.numCargaMaterna = numCargaMaterna;
	}

	public String getNumCargaMaternaINP()
	{
		return this.numCargaMaternaINP;
	}

	public void setNumCargaMaternaINP(String numCargaMaternaINP)
	{
		this.numCargaMaternaINP = numCargaMaternaINP;
	}

	public String getNumCargaSimple()
	{
		return this.numCargaSimple;
	}

	public void setNumCargaSimple(String numCargaSimple)
	{
		this.numCargaSimple = numCargaSimple;
	}

	public String getNumCargaSimpleINP()
	{
		return this.numCargaSimpleINP;
	}

	public void setNumCargaSimpleINP(String numCargaSimpleINP)
	{
		this.numCargaSimpleINP = numCargaSimpleINP;
	}

	public String getNumDiasTrabajados()
	{
		return this.numDiasTrabajados;
	}

	public void setNumDiasTrabajados(String numDiasTrabajados)
	{
		this.numDiasTrabajados = numDiasTrabajados;
	}

	/**
	 * cotizacion
	 * 
	 * @return
	 */
	public Cotizacion getCotizacion()
	{
		return this.cotizacion;
	}

	/**
	 * cotizacion
	 * 
	 * @param cotizacion
	 */
	public void setCotizacion(Cotizacion cotizacion)
	{
		this.cotizacion = cotizacion;
	}

	/**
	 * id entidad afc
	 * 
	 * @return
	 */
	public String getIdEntAFC()
	{
		return this.idEntAFC;
	}

	/**
	 * id entidad afc
	 * 
	 * @param idEntAFC
	 */
	public void setIdEntAFC(String idEntAFC)
	{
		this.idEntAFC = idEntAFC;
	}

	/**
	 * tasa cotizacion
	 * 
	 * @return
	 */
	public String getTasaCotizacion()
	{
		return this.tasaCotizacion;
	}

	/**
	 * tasa cotizacion
	 * 
	 * @param tasaCotizacion
	 */
	public void setTasaCotizacion(String tasaCotizacion)
	{
		this.tasaCotizacion = tasaCotizacion;
	}

	/**
	 * id entidad afc real
	 * 
	 * @return
	 */
	public int getIdEntAFCReal()
	{
		return this.idEntAFCReal;
	}

	/**
	 * + id entidad afc real
	 * 
	 * @param idEntAFCReal
	 */
	public void setIdEntAFCReal(int idEntAFCReal)
	{
		this.idEntAFCReal = idEntAFCReal;
	}

	/**
	 * id entidad afpv
	 * 
	 * @return
	 */
	public String getIdEntidadAFPV()
	{
		return this.idEntidadAFPV;
	}

	/**
	 * id entidad afpv
	 * 
	 * @param idEntidadAFPV
	 */
	public void setIdEntidadAFPV(String idEntidadAFPV)
	{
		this.idEntidadAFPV = idEntidadAFPV;
	}

	/**
	 * id entidad afpv real
	 * 
	 * @return
	 */
	public int getIdEntidadAFPVReal()
	{
		return this.idEntidadAFPVReal;
	}

	/**
	 * id entidad afpv real
	 * 
	 * @param idEntidadAFPVReal
	 */
	public void setIdEntidadAFPVReal(int idEntidadAFPVReal)
	{
		this.idEntidadAFPVReal = idEntidadAFPVReal;
	}

	/**
	 * id entidad apvc
	 * 
	 * @return
	 */
	public String getIdEntidadAPVC()
	{
		return this.idEntidadAPVC;
	}

	/**
	 * id entidad apvc
	 * 
	 * @param idEntidadAPVC
	 */
	public void setIdEntidadAPVC(String idEntidadAPVC)
	{
		this.idEntidadAPVC = idEntidadAPVC;
	}

	/**
	 * id entidad apvc real
	 * 
	 * @return
	 */
	public int getIdEntidadAPVCReal()
	{
		return this.idEntidadAPVCReal;
	}

	/**
	 * id entidad apvc real
	 * 
	 * @param idEntidadAPVCReal
	 */
	public void setIdEntidadAPVCReal(int idEntidadAPVCReal)
	{
		this.idEntidadAPVCReal = idEntidadAPVCReal;
	}

	/**
	 * comora cotizante
	 */
	public int compareTo(Object otroCotizante)
	{
		Cotizante otro = (Cotizante) otroCotizante;
		if (this.idCotizante.length() < otro.getIdCotizante().length())
			return -1;
		return (this.idCotizante).compareTo(otro.getIdCotizante());
	}

	/**
	 * afp voluntario
	 * 
	 * @return
	 */
	public boolean isAfpVoluntario()
	{
		return this.afpVoluntario;
	}

	/**
	 * afp voluntario
	 * 
	 * @param afpVoluntario
	 */
	public void setAfpVoluntario(boolean afpVoluntario)
	{
		this.afpVoluntario = afpVoluntario;
	}

	public int getNivelError()
	{
		return this.nivelError;
	}

	public void setNivelError(int nivelError)
	{
		this.nivelError = nivelError;
	}

	public void sumaDias(CotizanteVO cotizanteGuardado)
	{
		try
		{
			this.numDiasTrabajados = "" + (new Integer(this.numDiasTrabajados).intValue() + cotizanteGuardado.getNumDiasTrabajados());
		} catch (Exception e)
		{
		}
	}
}
