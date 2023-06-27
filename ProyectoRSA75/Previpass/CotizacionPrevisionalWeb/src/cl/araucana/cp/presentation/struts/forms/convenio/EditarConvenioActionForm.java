package cl.araucana.cp.presentation.struts.forms.convenio;

import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) EditarConvenioActionForm.java 1.7 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author pfrigolett
 * 
 * @version 1.7
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
	private String opcCaja;
	private String opcHabilitado;
	private String nombreActividadEconomica;
	private int idConvenio;
	private List convenios;
	private List cajas;
	private List mutuales;
	private List grupos;
	private List actividadesEconomicas;
	private int opcCalculoMovPersonal;
	
	private String codigoGrupoConvenio;
	private String nombreGrupoConvenio;
	/**
	 * codigo grupo convenio
	 * @return
	 */
	public String getCodigoGrupoConvenio()
	{
		return this.codigoGrupoConvenio;
	}
	/**
	 * codigo grupo convenio
	 * @param codigoGrupoConvenio
	 */
	public void setCodigoGrupoConvenio(String codigoGrupoConvenio)
	{
		this.codigoGrupoConvenio = codigoGrupoConvenio;
	}
	/**
	 * nombre grupo convenio
	 * @return
	 */
	public String getNombreGrupoConvenio()
	{
		return this.nombreGrupoConvenio;
	}
	/**
	 * nombre grupo convenio
	 * @param nombreGrupoConvenio
	 */
	public void setNombreGrupoConvenio(String nombreGrupoConvenio)
	{
		this.nombreGrupoConvenio = nombreGrupoConvenio;
	}
	/**
	 * id convenio
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
	 * rut empresa
	 * @return
	 */
	public int getRutEmpresa()
	{
		return this.rutEmpresa;
	}
	/**
	 * rut empresa
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
	 * convenios
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
	 * mutuales
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
	 * actividades economicas
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
	 * nombre actividad economica
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
	 * opc actividad econimica
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
	 * rut empresa fmt
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
	 * opc grupo conevio
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
