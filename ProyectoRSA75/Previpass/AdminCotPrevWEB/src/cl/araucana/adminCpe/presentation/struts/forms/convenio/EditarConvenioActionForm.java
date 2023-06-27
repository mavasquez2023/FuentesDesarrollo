package cl.araucana.adminCpe.presentation.struts.forms.convenio;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
/*
* @(#) EditarConvenioActionForm.java 1.3 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * 
 * @version 1.3
 */
public class EditarConvenioActionForm extends ActionForm
{
	private static final long serialVersionUID = 8155556336195564610L;

	private int rutEmpresa;
	private String rutEmpresaFmt;
	private String nombreEmpresa;
	private String nombreConvenio;
	private String codigoConvenio;
	private String opcActividadEconomica;
	private String opcActividadEconomicaMostrar;
	private String opcConvenio;
	private String opcGrupoConvenio;
	private String grupoConvenio;
	private String nomGrupoConvenio;
	private String opcCaja;
	private String opcHabilitado;
	private String nombreActividadEconomica;
	private int idConvenio;
	private String idConvenioEd;
	private List convenios;
	private List cajas;
	private List mutuales;
	private List grupos;
	private List actividadesEconomicas;
	
	private String codigoConvenioDesde;
	private String codigoConvenioHasta;
	
	private String opcOpcionProceso;
	private String nombreOpcionProceso;
	private List opcionesProcesos;
	private int opcCalculoMovPersonal;

	//Datos mutual
	private String opcMutual;
	private String opcCalculoIndividual;
	private String numAdherentesMutual;
	private String tasaAdicionalMutual;
	private String nombreMutual;

	/**
	 * reset
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		super.reset(mapping, request);
		
		this.rutEmpresaFmt = "";
		this.nombreEmpresa = "";
		this.nombreConvenio = "";
		this.codigoConvenio = "";
		this.opcActividadEconomica = "";
		this.opcActividadEconomicaMostrar = "";
		this.opcConvenio = null;
		this.opcGrupoConvenio = "";
		this.grupoConvenio = "";
		this.nomGrupoConvenio = "";
		this.opcCaja = "";
		this.opcHabilitado = "1";
		this.nombreActividadEconomica = "";
		this.idConvenioEd = "";
		this.convenios = null;
		this.cajas = null;
		this.mutuales = null;
		this.grupos = null;
		this.actividadesEconomicas = null;

		this.opcOpcionProceso = "";
		this.nombreOpcionProceso = "";
		this.opcionesProcesos = new ArrayList();
		this.opcCalculoMovPersonal = 1;

		this.opcMutual = "";
		this.opcCalculoIndividual = "";
		this.numAdherentesMutual = "";
		this.tasaAdicionalMutual = "";
		this.nombreMutual = "";
	}

	/**
	 * id covenio
	 * @return
	 */
	public int getIdConvenio()
	{
		return this.idConvenio;
	}
	
	/**
	 * id convenio
	 * @param idConvenio
	 */
	public void setIdConvenio(int idConvenio)
	{
		this.idConvenio = idConvenio;
	}
	
	/**
	 * rut empresas
	 * @return
	 */
	public int getRutEmpresa()
	{
		return this.rutEmpresa;
	}
	/**
	 * rur empresa
	 * @param rutEmpresa
	 */
	public void setRutEmpresa(int rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
	}

	/**
	 * cajas
	 * @return
	 */
	public List getCajas()
	{
		return this.cajas;
	}

	/**
	 * cajas
	 * @param cajas
	 */
	public void setCajas(List cajas)
	{
		this.cajas = cajas;
	}

	/**
	 * lista convenios
	 * @return
	 */
	public List getConvenios()
	{
		return this.convenios;
	}

	/**
	 * convenios
	 * @param convenios
	 */
	public void setConvenios(List convenios)
	{
		this.convenios = convenios;
	}

	/**
	 * grupos
	 * @return
	 */
	public List getGrupos()
	{
		return this.grupos;
	}

	/**
	 * grupos
	 * @param grupos
	 */
	public void setGrupos(List grupos)
	{
		this.grupos = grupos;
	}

	/**
	 * lista grupos
	 * @return
	 */
	public List getMutuales()
	{
		return this.mutuales;
	}

	/**
	 * mutuales
	 * @param mutuales
	 */
	public void setMutuales(List mutuales)
	{
		this.mutuales = mutuales;
	}

	/**
	 * Actividades Economicas
	 * @return
	 */
	public List getActividadesEconomicas()
	{
		return this.actividadesEconomicas;
	}

	/**
	 * actividades economicas
	 * @param actividadesEconomicas
	 */
	public void setActividadesEconomicas(List actividadesEconomicas)
	{
		this.actividadesEconomicas = actividadesEconomicas;
	}

	/**
	 * Nombre actividad economica
	 * @return
	 */
	public String getNombreActividadEconomica()
	{
		return this.nombreActividadEconomica;
	}

	/**
	 * nombre actividad economica
	 * @param nombreActividadEconomica
	 */
	public void setNombreActividadEconomica(String nombreActividadEconomica)
	{
		this.nombreActividadEconomica = nombreActividadEconomica;
	}

	/**
	 * opc actividad economica
	 * @return
	 */
	public String getOpcActividadEconomica()
	{
		return this.opcActividadEconomica;
	}

	/**
	 * opc actividad economica
	 * @param opcActividadEconomica
	 */
	public void setOpcActividadEconomica(String opcActividadEconomica)
	{
		this.opcActividadEconomica = opcActividadEconomica;
	}

	/**
	 * opc actividad economica mostrar
	 * @return
	 */
	public String getOpcActividadEconomicaMostrar()
	{
		return this.opcActividadEconomicaMostrar;
	}

	/**
	 * opc actividad economica mostrar
	 * @param opcActividadEconomicaMostrar
	 */
	public void setOpcActividadEconomicaMostrar(String opcActividadEconomicaMostrar)
	{
		this.opcActividadEconomicaMostrar = opcActividadEconomicaMostrar;
	}

	/**
	 * opc convenio
	 * @return
	 */
	public String getOpcConvenio()
	{
		return this.opcConvenio;
	}

	/**
	 * opc convenio
	 * @param opcConvenio
	 */
	public void setOpcConvenio(String opcConvenio)
	{
		this.opcConvenio = opcConvenio;
	}

	/**
	 * nombre empresa
	 * @return
	 */
	public String getNombreEmpresa()
	{
		return this.nombreEmpresa;
	}

	/**
	 * nombre empresa
	 * @param nombreEmpresa
	 */
	public void setNombreEmpresa(String nombreEmpresa)
	{
		this.nombreEmpresa = nombreEmpresa;
	}

	/**
	 * string rutempresa fmt
	 * @return
	 */
	public String getRutEmpresaFmt()
	{
		return this.rutEmpresaFmt;
	}

	/**
	 * rut empresa fmt
	 * @param rutEmpresaFmt
	 */
	public void setRutEmpresaFmt(String rutEmpresaFmt)
	{
		this.rutEmpresaFmt = rutEmpresaFmt;
	}

	/**
	 * opc grupo convenio
	 * @return
	 */
	public String getOpcGrupoConvenio()
	{
		return this.opcGrupoConvenio;
	}

	/**
	 * opc grupo convenio
	 * @param opcGrupoConvenio
	 */
	public void setOpcGrupoConvenio(String opcGrupoConvenio)
	{
		this.opcGrupoConvenio = opcGrupoConvenio;
	}

	/**
	 * opc caja
	 * @return
	 */
	public String getOpcCaja()
	{
		return this.opcCaja;
	}

	/**
	 * opc caja
	 * @param opcCaja
	 */
	public void setOpcCaja(String opcCaja)
	{
		this.opcCaja = opcCaja;
	}

	/**
	 * codigo convenio
	 * @return
	 */
	public String getCodigoConvenio()
	{
		return this.codigoConvenio;
	}

	/**
	 * codigo convenio
	 * @param codigoConvenio
	 */
	public void setCodigoConvenio(String codigoConvenio)
	{
		this.codigoConvenio = codigoConvenio;
	}

	/**
	 * nombre convenio
	 * @return
	 */
	public String getNombreConvenio()
	{
		return this.nombreConvenio;
	}

	/**
	 * nombre convenio
	 * @param nombreConvenio
	 */
	public void setNombreConvenio(String nombreConvenio)
	{
		this.nombreConvenio = nombreConvenio;
	}

	/**
	 * opc habilitado
	 * @return
	 */
	public String getOpcHabilitado()
	{
		return this.opcHabilitado;
	}

	/**
	 * opc habilitado
	 * @param opcHabilitado
	 */
	public void setOpcHabilitado(String opcHabilitado)
	{
		this.opcHabilitado = opcHabilitado;
	}

	/**
	 * grupo convenio
	 * @return
	 */
	public String getGrupoConvenio()
	{
		return this.grupoConvenio;
	}

	/**
	 * grupo convenio
	 * @param grupoConvenio
	 */
	public void setGrupoConvenio(String grupoConvenio)
	{
		this.grupoConvenio = grupoConvenio;
	}

	/**
	 * nombre grupo convenio
	 * @return
	 */
	public String getNomGrupoConvenio()
	{
		return this.nomGrupoConvenio;
	}

	/**
	 * nombrupo convenio
	 * @param nomGrupoConvenio
	 */
	public void setNomGrupoConvenio(String nomGrupoConvenio)
	{
		this.nomGrupoConvenio = nomGrupoConvenio;
	}

	/**
	 * id convenio Ed
	 * @return
	 */
	public String getIdConvenioEd()
	{
		return this.idConvenioEd;
	}

	/**
	 * set id convenio ed
	 * @param idConvenioEd
	 */
	public void setIdConvenioEd(String idConvenioEd)
	{
		this.idConvenioEd = idConvenioEd;
	}

	/**
	 * nombre mutual
	 * @return
	 */
	public String getNombreMutual()
	{
		return this.nombreMutual;
	}

	/**
	 * nombre mutual
	 * @param nombreMutual
	 */
	public void setNombreMutual(String nombreMutual)
	{
		this.nombreMutual = nombreMutual;
	}
    /**
     * nombre opcion proceso
     * @return
     */
	public String getNombreOpcionProceso()
	{
		return this.nombreOpcionProceso;
	}

	/**
	 * nombre opcion proceso
	 * @param nombreOpcionProceso
	 */
	public void setNombreOpcionProceso(String nombreOpcionProceso)
	{
		this.nombreOpcionProceso = nombreOpcionProceso;
	}

	/**
	 * num adherentes mutual
	 * @return
	 */
	public String getNumAdherentesMutual()
	{
		return this.numAdherentesMutual;
	}

	/**
	 * num adherentes mutual
	 * @param numAdherentesMutual
	 */
	public void setNumAdherentesMutual(String numAdherentesMutual)
	{
		this.numAdherentesMutual = numAdherentesMutual;
	}

	/**
	 * opc caculo individual
	 * @return
	 */
	public String getOpcCalculoIndividual()
	{
		return this.opcCalculoIndividual;
	}

	/**
	 * opc calculo individual
	 * @param opcCalculoIndividual
	 */
	public void setOpcCalculoIndividual(String opcCalculoIndividual)
	{
		this.opcCalculoIndividual = opcCalculoIndividual;
	}

	/**
	 * opc mutual
	 * @return
	 */
	public String getOpcMutual()
	{
		return this.opcMutual;
	}

	/**
	 * opc mutual
	 * @param opcMutual
	 */
	public void setOpcMutual(String opcMutual)
	{
		this.opcMutual = opcMutual;
	}

	/**
	 * opc opcion proceso
	 * @return
	 */
	public String getOpcOpcionProceso()
	{
		return this.opcOpcionProceso;
	}

	/**
	 * opc opcion proceso
	 * @param opcOpcionProceso
	 */
	public void setOpcOpcionProceso(String opcOpcionProceso)
	{
		this.opcOpcionProceso = opcOpcionProceso;
	}

	/**
	 * tasa adicional mutual
	 * @return
	 */
	public String getTasaAdicionalMutual()
	{
		return this.tasaAdicionalMutual;
	}

	/**
	 * tasa adicional mutual
	 * @param tasaAdicionalMutual
	 */
	public void setTasaAdicionalMutual(String tasaAdicionalMutual)
	{
		this.tasaAdicionalMutual = tasaAdicionalMutual;
	}

	/**
	 * opc opciones procesos
	 * @return
	 */
	public List getOpcionesProcesos()
	{
		return this.opcionesProcesos;
	}

	/**
	 * opc opciones proceso
	 * @param opcionesProcesos
	 */
	public void setOpcionesProcesos(List opcionesProcesos)
	{
		this.opcionesProcesos = opcionesProcesos;
	}

	/**
	 * codigo convenio desde
	 * @return
	 */
	public String getCodigoConvenioDesde()
	{
		return this.codigoConvenioDesde;
	}

	/**
	 * codigo convenio desde
	 * @param codigoConvenioDesde
	 */
	public void setCodigoConvenioDesde(String codigoConvenioDesde)
	{
		this.codigoConvenioDesde = codigoConvenioDesde;
	}

	/**
	 * codigo convenio hasta
	 * @return
	 */
	public String getCodigoConvenioHasta()
	{
		return this.codigoConvenioHasta;
	}

	/**
	 * codigo convenio hasta
	 * @param codigoConvenioHasta
	 */
	public void setCodigoConvenioHasta(String codigoConvenioHasta)
	{
		this.codigoConvenioHasta = codigoConvenioHasta;
	}

	/**
	 * opc calculo movimiento personal
	 * @return
	 */
	public int getOpcCalculoMovPersonal()
	{
		return this.opcCalculoMovPersonal;
	}

	/**
	 * opc calculo movimiento personal
	 * @param opcCalculoMovPersonal
	 */
	public void setOpcCalculoMovPersonal(int opcCalculoMovPersonal)
	{
		this.opcCalculoMovPersonal = opcCalculoMovPersonal;
	}
}
