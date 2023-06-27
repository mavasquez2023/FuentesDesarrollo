package cl.araucana.cp.presentation.struts.forms.cotizante;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.ListUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.ApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.presentation.beans.Cotizacion;
import cl.araucana.cp.distribuidor.presentation.beans.Cotizante;
import cl.araucana.cp.distribuidor.presentation.beans.MovtoPersonal;

/*
 * @(#) CotizacionActionForm.java 1.13 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * 
 * @version 1.13
 */
public class CotizacionActionForm extends ActionForm
{
	private static final long serialVersionUID = 6355558005515729491L;
	private String rutTrabFormat;
	private String rutTrabOrigin;
	private int idCotizPendiente;
	private int tipoPrevision;// 0: sin prevision, 1: AFP, 2: INP
	private String rutEmpresaFormat;
	private String razonSocial;
	private String convenio;
	private String idConvenio;
	private String rutEmpresa;
	private String tipoProceso;

	private String operacion;
	private String periodo;

	private String mostrar; // si == 'ap', cotizacion aprobada, si == 'new', nueva, si == 'pen', pendiente

	private String nomMutual;
	private int diasTopeAF;
	private float aporteCCAFFON;// porcentaje a pagar a INP, por FONASA, si convenio tiene caja. Si no hay caja, sumar porcentajes y asignar a INP
	private float aporteINPFON;// porcentaje a pagar a CCAF, por FONASA, si convenio tiene caja
	private double tasaMutual;
	private int idMutual;
	private EntidadCCAFVO caja;
	private int idCaja;
	private int idFONASA;
	private int idAFPNinguna;
	private int idSinAFP;
	private int numMaxMovimientos;
	private int tipoMovtoEntidadSIL;
	private int numMaxAPVs;
	private int tramoASigFamNinguno = Constants.TRAMO_ASIGFAM_NINGUNO;

	// parametros varios
	private int diasXMes;
	private float topeAFP;
	private float topeINP;
	private float topeIndemn;
	private float topeCesantia;
	private float minTasaIndemn;
	private float maxTasaIndemn;
	private float apTrabIndSegCes;
	private float apTrabPFSegCes;
	private float apEmpPFSegCes;
	private float apEmpIndSegCes;

	private Cotizante cotizante;
	private Cotizacion cotizacion;

	// data selects
	private List tiposProcesos;
	private List generos;
	private List sucursales;
	private List codRegImp;
	private List tramosAsigFam;
	private List tiposMovPersonal;
	private List entidadesSalud;
	private List entidadesPension;
	private List entidadesAFC;
	private List entidadesExCaja;
	private List entidadesApvs;
	private List entidadesSIL;
	private List regimenAPV;

	private List porcentajeTrabPesado;
	private List movtosPersonal;
	private List apvs;

	private List errores;
	private List avisos;

	private String tipoMovimiento;
	private String tipoMovimientoInicio;
	private String tipoMovimientoTermino;
	private String ordenIdMovimiento;
	private HashMap erroresCD;
	private HashMap mensajesErrores;
	private HashMap isAviso;

	private int tolerancia;
//	clillo 3-12-14 por Reliquidación
	private int periodoReli;
	private int periodoReli_old;
	
	//jlandero 30/08/2010
	private String fonasaCheck;
	private int idSaludNinguna;

	//csanchez 10/09/2010
	private int flgTrabNomina;
	private String respaldoOperacion;
	
	//gmallea 08/10/2012
	private boolean isPrimerCotizante;
	
	//clillo 21/04/2016
	private Boolean prodCaja;
	
	public int getTolerancia()
	{
		return this.tolerancia;
	}

	public void setTolerancia(int tolerancia)
	{
		this.tolerancia = tolerancia;
	}

	public HashMap getErroresCD()
	{
		return this.erroresCD;
	}

	public void setErroresCD(HashMap erroresCD)
	{
		this.erroresCD = erroresCD;
	}

	public List getErrores()
	{
		return this.errores;
	}

	public void setErrores(List errores)
	{
		this.errores = errores;
	}

	/**
	 * validate
	 * 
	 * @return
	 */
	public List validate()
	{
		List result = new ArrayList();

		if (this.cotizante.getApellidoPat().equals(""))
			result.add("110");
		return result;
	}

	/**
	 * reset
	 */
	public void reset(ActionMapping arg0, HttpServletRequest arg1)
	{
		super.reset(arg0, arg1);
		this.movtosPersonal = ListUtils.lazyList(new ArrayList(), new Factory()
		{
			public Object create()
			{
				return new MovtoPersonal();
			}
		});
		this.apvs = ListUtils.lazyList(new ArrayList(), new Factory()
		{
			public Object create()
			{
				return new ApvVO();
			}
		});
		// this.rutEmpresa = null;
		this.cotizacion = new Cotizacion();
		this.cotizante = new Cotizante();
	}

	/**
	 * cotizante
	 * 
	 * @return
	 */
	public Cotizante getCotizante()
	{
		return this.cotizante;
	}

	/**
	 * cotizante
	 * 
	 * @param cotizante
	 */
	public void setCotizante(Cotizante cotizante)
	{
		this.cotizante = cotizante;
	}

	/**
	 * entidad apvs
	 * 
	 * @return
	 */
	public List getEntidadesApvs()
	{
		return this.entidadesApvs;
	}

	/**
	 * entidades apvs
	 * 
	 * @param entidadesApvs
	 */
	public void setEntidadesApvs(List entidadesApvs)
	{
		this.entidadesApvs = entidadesApvs;
	}

	/**
	 * codigo regimen impositivo
	 * 
	 * @return
	 */
	public List getCodRegImp()
	{
		return this.codRegImp;
	}

	/**
	 * codigo regimen impositivo
	 * 
	 * @param codRegImp
	 */
	public void setCodRegImp(List codRegImp)
	{
		this.codRegImp = codRegImp;
	}

	/**
	 * entidades ex caja
	 * 
	 * @return
	 */
	public List getEntidadesExCaja()
	{
		return this.entidadesExCaja;
	}

	/**
	 * entidades ex caja
	 * 
	 * @param entidadesExCaja
	 */
	public void setEntidadesExCaja(List entidadesExCaja)
	{
		this.entidadesExCaja = entidadesExCaja;
	}

	/**
	 * entidades pension
	 * 
	 * @return
	 */
	public List getEntidadesPension()
	{
		return this.entidadesPension;
	}

	/**
	 * entidades pension
	 * 
	 * @param entidadesPension
	 */
	public void setEntidadesPension(List entidadesPension)
	{
		this.entidadesPension = entidadesPension;
	}

	/**
	 * entidades salud
	 * 
	 * @return
	 */
	public List getEntidadesSalud()
	{
		return this.entidadesSalud;
	}

	/**
	 * entidades salud
	 * 
	 * @param entidadesSalud
	 */
	public void setEntidadesSalud(List entidadesSalud)
	{
		this.entidadesSalud = entidadesSalud;
	}

	/**
	 * entidades afc
	 * 
	 * @return
	 */
	public List getEntidadesAFC()
	{
		return this.entidadesAFC;
	}

	/**
	 * entidades afc
	 * 
	 * @param entidadesAFC
	 */
	public void setEntidadesAFC(List entidadesAFC)
	{
		this.entidadesAFC = entidadesAFC;
	}

	/**
	 * entidades sil
	 * 
	 * @return
	 */
	public List getEntidadesSIL()
	{
		return this.entidadesSIL;
	}

	/**
	 * entidades sil
	 * 
	 * @param entidadesSIL
	 */
	public void setEntidadesSIL(List entidadesSIL)
	{
		this.entidadesSIL = entidadesSIL;
	}

	/**
	 * generos
	 * 
	 * @return
	 */
	public List getGeneros()
	{
		return this.generos;
	}

	/**
	 * generos
	 * 
	 * @param generos
	 */
	public void setGeneros(List generos)
	{
		this.generos = generos;
	}

	/**
	 * razon social
	 * 
	 * @return
	 */
	public String getRazonSocial()
	{
		return this.razonSocial;
	}

	/**
	 * razon social
	 * 
	 * @param razonSocial
	 */
	public void setRazonSocial(String razonSocial)
	{
		this.razonSocial = razonSocial;
	}

	/**
	 * rut empresa format
	 * 
	 * @return
	 */
	public String getRutEmpresaFormat()
	{
		return this.rutEmpresaFormat;
	}

	/**
	 * rut empresa format
	 * 
	 * @param rutEmpresaFormat
	 */
	public void setRutEmpresaFormat(String rutEmpresaFormat)
	{
		this.rutEmpresaFormat = rutEmpresaFormat;
	}

	/**
	 * rut trabajador format
	 * 
	 * @return
	 */
	public String getRutTrabFormat()
	{
		return this.rutTrabFormat;
	}

	/**
	 * rut trabajador format
	 * 
	 * @param rutTrabFormat
	 */
	public void setRutTrabFormat(String rutTrabFormat)
	{
		this.rutTrabFormat = rutTrabFormat;
	}

	/**
	 * rut trabajador origin
	 * 
	 * @return
	 */
	public String getRutTrabOrigin()
	{
		return this.rutTrabOrigin;
	}

	/**
	 * rut trabajador origin
	 * 
	 * @param rutTrabOrigin
	 */
	public void setRutTrabOrigin(String rutTrabOrigin)
	{
		this.rutTrabOrigin = rutTrabOrigin;
	}

	/**
	 * sucursales
	 * 
	 * @return
	 */
	public List getSucursales()
	{
		return this.sucursales;
	}

	/**
	 * sucursales
	 * 
	 * @param sucursales
	 */
	public void setSucursales(List sucursales)
	{
		this.sucursales = sucursales;
	}

	/**
	 * tipo proceso
	 * 
	 * @return
	 */
	public String getTipoProceso()
	{
		return this.tipoProceso;
	}

	/**
	 * tipo proceso
	 * 
	 * @param tipoProceso
	 */
	public void setTipoProceso(String tipoProceso)
	{
		this.tipoProceso = tipoProceso;
	}

	/**
	 * tipos movimiento personal
	 * 
	 * @return
	 */
	public List getTiposMovPersonal()
	{
		return this.tiposMovPersonal;
	}

	/**
	 * tipos movimiento personal
	 * 
	 * @param tiposMovPersonal
	 */
	public void setTiposMovPersonal(List tiposMovPersonal)
	{
		this.tiposMovPersonal = tiposMovPersonal;
	}

	/**
	 * tipos procesos
	 * 
	 * @return
	 */
	public List getTiposProcesos()
	{
		return this.tiposProcesos;
	}

	/**
	 * tipos procesos
	 * 
	 * @param tiposProcesos
	 */
	public void setTiposProcesos(List tiposProcesos)
	{
		this.tiposProcesos = tiposProcesos;
	}

	/**
	 * tramos asignacion familiar
	 * 
	 * @return
	 */
	public List getTramosAsigFam()
	{
		return this.tramosAsigFam;
	}

	/**
	 * tramos asignacion familiar
	 * 
	 * @param tramosAsigFam
	 */
	public void setTramosAsigFam(List tramosAsigFam)
	{
		this.tramosAsigFam = tramosAsigFam;
	}

	/**
	 * rut empresa
	 * 
	 * @return
	 */
	public String getRutEmpresa()
	{
		return this.rutEmpresa;
	}

	/**
	 * rut empresa
	 * 
	 * @param rutEmpresa
	 */
	public void setRutEmpresa(String rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
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
	 * id convenio
	 * 
	 * @return
	 */
	public String getIdConvenio()
	{
		return this.idConvenio;
	}

	/**
	 * id convenio
	 * 
	 * @param idConvenio
	 */
	public void setIdConvenio(String idConvenio)
	{
		this.idConvenio = idConvenio;
	}

	/**
	 * convenio
	 * 
	 * @return
	 */
	public String getConvenio()
	{
		return this.convenio;
	}

	/**
	 * convenio
	 * 
	 * @param convenio
	 */
	public void setConvenio(String convenio)
	{
		this.convenio = convenio;
	}

	/**
	 * nombre mutual
	 * 
	 * @return
	 */
	public String getNomMutual()
	{
		return this.nomMutual;
	}

	/**
	 * nombre mutual
	 * 
	 * @param nomMutual
	 */
	public void setNomMutual(String nomMutual)
	{
		this.nomMutual = nomMutual;
	}

	/**
	 * id mutual
	 * 
	 * @return
	 */
	public int getIdMutual()
	{
		return this.idMutual;
	}

	/**
	 * id mutual
	 * 
	 * @param idMutual
	 */
	public void setIdMutual(int idMutual)
	{
		this.idMutual = idMutual;
	}

	/**
	 * dias tope af
	 * 
	 * @return
	 */
	public int getDiasTopeAF()
	{
		return this.diasTopeAF;
	}

	/**
	 * dias tope af
	 * 
	 * @param diasTopeAF
	 */
	public void setDiasTopeAF(int diasTopeAF)
	{
		this.diasTopeAF = diasTopeAF;
	}

	/**
	 * tasa mutual
	 * 
	 * @return
	 */
	public double getTasaMutual()
	{
		return this.tasaMutual;
	}

	/**
	 * tasa mutual
	 * 
	 * @param tasaMutual
	 */
	public void setTasaMutual(double tasaMutual)
	{
		this.tasaMutual = tasaMutual;
	}

	/**
	 * entidad ccaf vo
	 * 
	 * @return
	 */
	public EntidadCCAFVO getCaja()
	{
		return this.caja;
	}

	/**
	 * caja
	 * 
	 * @param caja
	 */
	public void setCaja(EntidadCCAFVO caja)
	{
		this.caja = caja;
	}

	/**
	 * id caja
	 * 
	 * @return
	 */
	public int getIdCaja()
	{
		return this.idCaja;
	}

	/**
	 * id caja
	 * 
	 * @param idCaja
	 */
	public void setIdCaja(int idCaja)
	{
		this.idCaja = idCaja;
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
	 * aporte ccaf fon
	 * 
	 * @return
	 */
	public float getAporteCCAFFON()
	{
		return this.aporteCCAFFON;
	}

	/**
	 * aporte ccaf fon
	 * 
	 * @param aporteCCAFFON
	 */
	public void setAporteCCAFFON(float aporteCCAFFON)
	{
		this.aporteCCAFFON = aporteCCAFFON;
	}

	/**
	 * aporte inp fon
	 * 
	 * @return
	 */
	public float getAporteINPFON()
	{
		return this.aporteINPFON;
	}

	/**
	 * aporte inp fon
	 * 
	 * @param aporteINPFON
	 */
	public void setAporteINPFON(float aporteINPFON)
	{
		this.aporteINPFON = aporteINPFON;
	}

	/**
	 * porcentaje trabajo pesado
	 * 
	 * @return
	 */
	public List getPorcentajeTrabPesado()
	{
		return this.porcentajeTrabPesado;
	}

	/**
	 * procentaje trabajo pesado
	 * 
	 * @param porcentajeTrabPesado
	 */
	public void setPorcentajeTrabPesado(List porcentajeTrabPesado)
	{
		this.porcentajeTrabPesado = porcentajeTrabPesado;
	}

	/**
	 * movimientos personal
	 * 
	 * @return
	 */
	public List getMovtosPersonal()
	{
		return this.movtosPersonal;
	}

	/**
	 * movimientos personal
	 * 
	 * @param movtosPersonal
	 */
	public void setMovtosPersonal(List movtosPersonal)
	{
		this.movtosPersonal = movtosPersonal;
	}

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

	/**
	 * mostrar
	 * 
	 * @return
	 */
	public String getMostrar()
	{
		return this.mostrar;
	}

	/**
	 * mostrar
	 * 
	 * @param mostrar
	 */
	public void setMostrar(String mostrar)
	{
		this.mostrar = mostrar;
	}

	/**
	 * operacion
	 * 
	 * @return
	 */
	public String getOperacion()
	{
		return this.operacion;
	}

	/**
	 * operacion
	 * 
	 * @param operacion
	 */
	public void setOperacion(String operacion)
	{
		this.operacion = operacion;
	}

	/**
	 * ap emp ind seguro cesantia
	 * 
	 * @return
	 */
	public float getApEmpIndSegCes()
	{
		return this.apEmpIndSegCes;
	}

	/**
	 * ap emp ind seguro cesantia
	 * 
	 * @param apEmpIndSegCes
	 */
	public void setApEmpIndSegCes(float apEmpIndSegCes)
	{
		this.apEmpIndSegCes = apEmpIndSegCes;
	}

	/**
	 * ap emp pf seguro cesantia
	 * 
	 * @return
	 */
	public float getApEmpPFSegCes()
	{
		return this.apEmpPFSegCes;
	}

	/**
	 * ap emp pf seguro cesantia
	 * 
	 * @param apEmpPFSegCes
	 */
	public void setApEmpPFSegCes(float apEmpPFSegCes)
	{
		this.apEmpPFSegCes = apEmpPFSegCes;
	}

	/**
	 * ap trab ind seguro cesantia
	 * 
	 * @return
	 */
	public float getApTrabIndSegCes()
	{
		return this.apTrabIndSegCes;
	}

	/**
	 * ap trab ind seguro cesantia
	 * 
	 * @param apTrabIndSegCes
	 */
	public void setApTrabIndSegCes(float apTrabIndSegCes)
	{
		this.apTrabIndSegCes = apTrabIndSegCes;
	}

	/**
	 * ap trab pf seguro cesantia
	 * 
	 * @return
	 */
	public float getApTrabPFSegCes()
	{
		return this.apTrabPFSegCes;
	}

	/**
	 * ap trab pf seguro cesantia
	 * 
	 * @param apTrabPFSegCes
	 */
	public void setApTrabPFSegCes(float apTrabPFSegCes)
	{
		this.apTrabPFSegCes = apTrabPFSegCes;
	}

	/**
	 * dias por mes
	 * 
	 * @return
	 */
	public int getDiasXMes()
	{
		return this.diasXMes;
	}

	/**
	 * dias por mes
	 * 
	 * @param diasXMes
	 */
	public void setDiasXMes(int diasXMes)
	{
		this.diasXMes = diasXMes;
	}

	/**
	 * maximo tasa indemnizacion
	 * 
	 * @return
	 */
	public float getMaxTasaIndemn()
	{
		return this.maxTasaIndemn;
	}

	/**
	 * maximo tasa indemnizacion
	 * 
	 * @param maxTasaIndemn
	 */
	public void setMaxTasaIndemn(float maxTasaIndemn)
	{
		this.maxTasaIndemn = maxTasaIndemn;
	}

	/**
	 * minimo tasa indemnizacion
	 * 
	 * @return
	 */
	public float getMinTasaIndemn()
	{
		return this.minTasaIndemn;
	}

	/**
	 * minimo tasa indemnizacion
	 * 
	 * @param minTasaIndemn
	 */
	public void setMinTasaIndemn(float minTasaIndemn)
	{
		this.minTasaIndemn = minTasaIndemn;
	}

	/**
	 * tope afp
	 * 
	 * @return
	 */
	public float getTopeAFP()
	{
		return this.topeAFP;
	}

	/**
	 * tope afp
	 * 
	 * @param topeAFP
	 */
	public void setTopeAFP(float topeAFP)
	{
		this.topeAFP = topeAFP;
	}

	/**
	 * tope cesantia
	 * 
	 * @return
	 */
	public float getTopeCesantia()
	{
		return this.topeCesantia;
	}

	/**
	 * tope cesantia
	 * 
	 * @param topeCesantia
	 */
	public void setTopeCesantia(float topeCesantia)
	{
		this.topeCesantia = topeCesantia;
	}

	/**
	 * tope indemnizacion
	 * 
	 * @return
	 */
	public float getTopeIndemn()
	{
		return this.topeIndemn;
	}

	/**
	 * tope indemnizacion
	 * 
	 * @param topeIndemn
	 */
	public void setTopeIndemn(float topeIndemn)
	{
		this.topeIndemn = topeIndemn;
	}

	/**
	 * tope inp
	 * 
	 * @return
	 */
	public float getTopeINP()
	{
		return this.topeINP;
	}

	/**
	 * tope inp
	 * 
	 * @param topeINP
	 */
	public void setTopeINP(float topeINP)
	{
		this.topeINP = topeINP;
	}

	/**
	 * periodo
	 * 
	 * @return
	 */
	public String getPeriodo()
	{
		return this.periodo;
	}

	/**
	 * periodo
	 * 
	 * @param periodo
	 */
	public void setPeriodo(String periodo)
	{
		this.periodo = periodo;
	}

	/**
	 * tipo prevision
	 * 
	 * @return
	 */
	public int getTipoPrevision()
	{
		return this.tipoPrevision;
	}

	/**
	 * tipo prevision
	 * 
	 * @param tipoPrevision
	 */
	public void setTipoPrevision(int tipoPrevision)
	{
		this.tipoPrevision = tipoPrevision;
	}

	/**
	 * lista de avisos asociada al cotizante
	 * 
	 * @return
	 */
	public List getAvisos()
	{
		return this.avisos;
	}

	/**
	 * lista de avisos asociada al cotizante
	 * 
	 * @param avisos
	 */
	public void setAvisos(List avisos)
	{
		this.avisos = avisos;
	}

	public String getTipoMovimiento()
	{
		return this.tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento)
	{
		this.tipoMovimiento = tipoMovimiento;
	}

	public HashMap getIsAviso()
	{
		return this.isAviso;
	}

	public void setIsAviso(HashMap isAviso)
	{
		this.isAviso = isAviso;
	}

	public int getIdAFPNinguna()
	{
		return this.idAFPNinguna;
	}

	public void setIdAFPNinguna(int idAFPNinguna)
	{
		this.idAFPNinguna = idAFPNinguna;
	}

	public int getIdFONASA()
	{
		return this.idFONASA;
	}

	public void setIdFONASA(int idFONASA)
	{
		this.idFONASA = idFONASA;
	}

	public int getNumMaxMovimientos()
	{
		return this.numMaxMovimientos;
	}

	public void setNumMaxMovimientos(int numMaxMovimientos)
	{
		this.numMaxMovimientos = numMaxMovimientos;
	}

	public int getNumMaxAPVs()
	{
		return this.numMaxAPVs;
	}

	public void setNumMaxAPVs(int numMaxAPVs)
	{
		this.numMaxAPVs = numMaxAPVs;
	}

	public int getTipoMovtoEntidadSIL()
	{
		return this.tipoMovtoEntidadSIL;
	}

	public void setTipoMovtoEntidadSIL(int tipoMovtoEntidadSIL)
	{
		this.tipoMovtoEntidadSIL = tipoMovtoEntidadSIL;
	}

	public String getTipoMovimientoInicio()
	{
		return this.tipoMovimientoInicio;
	}

	public void setTipoMovimientoInicio(String tipoMovimientoInicio)
	{
		this.tipoMovimientoInicio = tipoMovimientoInicio;
	}

	public String getTipoMovimientoTermino()
	{
		return this.tipoMovimientoTermino;
	}

	public void setTipoMovimientoTermino(String tipoMovimientoTermino)
	{
		this.tipoMovimientoTermino = tipoMovimientoTermino;
	}

	public String getOrdenIdMovimiento()
	{
		return this.ordenIdMovimiento;
	}

	public void setOrdenIdMovimiento(String ordenIdMovimiento)
	{
		this.ordenIdMovimiento = ordenIdMovimiento;
	}

	public HashMap getMensajesErrores()
	{
		return this.mensajesErrores;
	}

	public void setMensajesErrores(HashMap mensajesErrores)
	{
		this.mensajesErrores = mensajesErrores;
	}

	public int getIdSinAFP()
	{
		return this.idSinAFP;
	}

	public void setIdSinAFP(int idSinAFP)
	{
		this.idSinAFP = idSinAFP;
	}

	public int getTramoASigFamNinguno()
	{
		return this.tramoASigFamNinguno;
	}

	public void setTramoASigFamNinguno(int tramoASigFamNinguno)
	{
		this.tramoASigFamNinguno = tramoASigFamNinguno;
	}

	public String getFonasaCheck()
	{
		return fonasaCheck;
	}

	public void setFonasaCheck(String fonasaCheck)
	{
		this.fonasaCheck = fonasaCheck;
	}

	public int getIdSaludNinguna()
	{
		return idSaludNinguna;
	}

	public void setIdSaludNinguna(int idSaludNinguna)
	{
		this.idSaludNinguna = idSaludNinguna;
	}

	public int getFlgTrabNomina()
	{
		return flgTrabNomina;
	}

	public void setFlgTrabNomina(int flgTrabNomina)
	{
		this.flgTrabNomina = flgTrabNomina;
	}

	public String getRespaldoOperacion()
	{
		return respaldoOperacion;
	}

	public void setRespaldoOperacion(String respaldoOperacion)
	{
		this.respaldoOperacion = respaldoOperacion;
	}

	public boolean isPrimerCotizante() {
		return isPrimerCotizante;
	}

	public void setPrimerCotizante(boolean isPrimerCotizante) {
		this.isPrimerCotizante = isPrimerCotizante;
	}

	public List getRegimenAPV() {
		return regimenAPV;
	}

	public void setRegimenAPV(List regimenAPV) {
		this.regimenAPV = regimenAPV;
	}
	/**
	 * @return el periodoReli
	 */
	public int getPeriodoReli() {
		return periodoReli;
	}

	/**
	 * @param periodoReli el periodoReli a establecer
	 */
	public void setPeriodoReli(int periodoReli) {
		this.periodoReli = periodoReli;
	}

	/**
	 * @return el periodoReli_old
	 */
	public int getPeriodoReli_old() {
		return periodoReli_old;
	}

	/**
	 * @param periodoReli_old el periodoReli_old a establecer
	 */
	public void setPeriodoReli_old(int periodoReli_old) {
		this.periodoReli_old = periodoReli_old;
	}

	/**
	 * @return el prodCaja
	 */
	public Boolean getProdCaja() {
		return prodCaja;
	}

	/**
	 * @param prodCaja el prodCaja a establecer
	 */
	public void setProdCaja(Boolean prodCaja) {
		this.prodCaja = prodCaja;
	}
}
