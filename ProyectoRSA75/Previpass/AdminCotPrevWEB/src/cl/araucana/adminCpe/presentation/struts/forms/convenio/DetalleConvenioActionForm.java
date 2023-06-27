package cl.araucana.adminCpe.presentation.struts.forms.convenio;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.ListUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import cl.araucana.adminCpe.presentation.struts.javaBeans.Usuario;
import cl.araucana.cp.distribuidor.presentation.beans.Empresa;
import cl.araucana.cp.distribuidor.presentation.beans.GrupoConvenio;
import cl.araucana.cp.distribuidor.presentation.beans.PermEncargadoLector;

/*
 * @(#) DetalleConvenioActionForm.java 1.3 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.3
 */
public class DetalleConvenioActionForm extends ActionForm
{
	private static final long serialVersionUID = 1894309096453531207L;
	private int rutEmpresa;
	private String rutEmpresaFmt;
	private String nombreEmpresa;
	private String nombreConvenio;
	private String nombreGrupoConvenio;
	private String nombreCaja;
	private String nombreMapeoCodigo;
	private String codigoConvenio;
	private String opcActividadEconomica;
	private String opcActividadEconomicaMostrar;
	private String opcConvenio;
	private String opcGrupoConvenio;
	private String opcCaja;
	private String opcHabilitado;
	private String nombreActividadEconomica;
	private String fechaCreado;
	private String fechaUltAcceso;
	private String numNominas;
	private String numCotiz;
	private int idConvenio;
	private List convenios;
	private List cajas;
	private List mutuales;
	private List grupos;
	private List actividadesEconomicas;
	private List permisos;
	private int opcCalculoMovPersonal;

	private String descripcionR;
	private String descripcionA;
	private String descripcionG;
	private String descripcionD;

	/**
	 * reset
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.permisos = ListUtils.lazyList(new ArrayList(), new Factory()
		{
			public Object create()
			{
				Usuario user = new Usuario();
				user.setAsignaciones(ListUtils.lazyList(new ArrayList(), new Factory()
				{
					public Object create()
					{
						GrupoConvenio grupo = new GrupoConvenio();
						grupo.setEmpresas(ListUtils.lazyList(new ArrayList(), new Factory()
						{
							public Object create()
							{
								Empresa emp = new Empresa();
								emp.setConvenios(ListUtils.lazyList(new ArrayList(), new Factory()
								{
									public Object create()
									{
										return new PermEncargadoLector();
									}
								}));
								return emp;
							}
						}));
						return grupo;
					}
				}));
				return user;
			}
		});
	}

	/**
	 * Opc convenio
	 * 
	 * @return
	 */
	public String getOpcConvenio()
	{
		return this.opcConvenio;
	}

	/**
	 * Opc convenio
	 * 
	 * @param opcConvenio
	 */
	public void setOpcConvenio(String opcConvenio)
	{
		this.opcConvenio = opcConvenio;
	}

	/**
	 * Actividades economicas
	 * 
	 * @return
	 */
	public List getActividadesEconomicas()
	{
		return this.actividadesEconomicas;
	}

	/**
	 * actividades economicas
	 * 
	 * @param actividadesEconomicas
	 */
	public void setActividadesEconomicas(List actividadesEconomicas)
	{
		this.actividadesEconomicas = actividadesEconomicas;
	}

	/**
	 * cajas
	 * 
	 * @return
	 */
	public List getCajas()
	{
		return this.cajas;
	}

	/**
	 * cajas
	 * 
	 * @param cajas
	 */
	public void setCajas(List cajas)
	{
		this.cajas = cajas;
	}

	/**
	 * codigo convenio
	 * 
	 * @return
	 */
	public String getCodigoConvenio()
	{
		return this.codigoConvenio;
	}

	/**
	 * codigo convenio
	 * 
	 * @param codigoConvenio
	 */
	public void setCodigoConvenio(String codigoConvenio)
	{
		this.codigoConvenio = codigoConvenio;
	}

	/**
	 * convenios
	 * 
	 * @return
	 */
	public List getConvenios()
	{
		return this.convenios;
	}

	/**
	 * convenios
	 * 
	 * @param convenios
	 */
	public void setConvenios(List convenios)
	{
		this.convenios = convenios;
	}

	/**
	 * grupos
	 * 
	 * @return
	 */
	public List getGrupos()
	{
		return this.grupos;
	}

	/**
	 * grupos
	 * 
	 * @param grupos
	 */
	public void setGrupos(List grupos)
	{
		this.grupos = grupos;
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

	/**
	 * mutuales
	 * 
	 * @return
	 */
	public List getMutuales()
	{
		return this.mutuales;
	}

	/**
	 * mutuales
	 * 
	 * @param mutuales
	 */
	public void setMutuales(List mutuales)
	{
		this.mutuales = mutuales;
	}

	/**
	 * nombre Actividad Economica
	 * 
	 * @return
	 */
	public String getNombreActividadEconomica()
	{
		return this.nombreActividadEconomica;
	}

	/**
	 * Nombre actividad economica
	 * 
	 * @param nombreActividadEconomica
	 */
	public void setNombreActividadEconomica(String nombreActividadEconomica)
	{
		this.nombreActividadEconomica = nombreActividadEconomica;
	}

	/**
	 * Nombre convenio
	 * 
	 * @return
	 */
	public String getNombreConvenio()
	{
		return this.nombreConvenio;
	}

	/**
	 * nombre convenio
	 * 
	 * @param nombreConvenio
	 */
	public void setNombreConvenio(String nombreConvenio)
	{
		this.nombreConvenio = nombreConvenio;
	}

	/**
	 * nombre empresa
	 * 
	 * @return
	 */
	public String getNombreEmpresa()
	{
		return this.nombreEmpresa;
	}

	/**
	 * nombre empresa
	 * 
	 * @param nombreEmpresa
	 */
	public void setNombreEmpresa(String nombreEmpresa)
	{
		this.nombreEmpresa = nombreEmpresa;
	}

	/**
	 * opc actividad economica
	 * 
	 * @return
	 */
	public String getOpcActividadEconomica()
	{
		return this.opcActividadEconomica;
	}

	/**
	 * Opc actividad economica
	 * 
	 * @param opcActividadEconomica
	 */
	public void setOpcActividadEconomica(String opcActividadEconomica)
	{
		this.opcActividadEconomica = opcActividadEconomica;
	}

	/**
	 * opc actividad economica mostrar
	 * 
	 * @return
	 */
	public String getOpcActividadEconomicaMostrar()
	{
		return this.opcActividadEconomicaMostrar;
	}

	/**
	 * opc actividad economica mostrar
	 * 
	 * @param opcActividadEconomicaMostrar
	 */
	public void setOpcActividadEconomicaMostrar(String opcActividadEconomicaMostrar)
	{
		this.opcActividadEconomicaMostrar = opcActividadEconomicaMostrar;
	}

	/**
	 * opc caja
	 * 
	 * @return
	 */
	public String getOpcCaja()
	{
		return this.opcCaja;
	}

	/**
	 * opc caja
	 * 
	 * @param opcCaja
	 */
	public void setOpcCaja(String opcCaja)
	{
		this.opcCaja = opcCaja;
	}

	/**
	 * opc grupo convenio
	 * 
	 * @return
	 */
	public String getOpcGrupoConvenio()
	{
		return this.opcGrupoConvenio;
	}

	/**
	 * opc grupo convenio
	 * 
	 * @param opcGrupoConvenio
	 */
	public void setOpcGrupoConvenio(String opcGrupoConvenio)
	{
		this.opcGrupoConvenio = opcGrupoConvenio;
	}

	/**
	 * opc habilitado
	 * 
	 * @return
	 */
	public String getOpcHabilitado()
	{
		return this.opcHabilitado;
	}

	/**
	 * opc habilitado
	 * 
	 * @param opcHabilitado
	 */
	public void setOpcHabilitado(String opcHabilitado)
	{
		this.opcHabilitado = opcHabilitado;
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
	 * rut empresa fmt
	 * 
	 * @return
	 */
	public String getRutEmpresaFmt()
	{
		return this.rutEmpresaFmt;
	}

	/**
	 * rut empresa fmt
	 * 
	 * @param rutEmpresaFmt
	 */
	public void setRutEmpresaFmt(String rutEmpresaFmt)
	{
		this.rutEmpresaFmt = rutEmpresaFmt;
	}

	/**
	 * nombre grupo convenio
	 * 
	 * @return
	 */
	public String getNombreGrupoConvenio()
	{
		return this.nombreGrupoConvenio;
	}

	/**
	 * nombre grupo convenio
	 * 
	 * @param nombreGrupoConvenio
	 */
	public void setNombreGrupoConvenio(String nombreGrupoConvenio)
	{
		this.nombreGrupoConvenio = nombreGrupoConvenio;
	}

	/**
	 * fecha creado
	 * 
	 * @return
	 */
	public String getFechaCreado()
	{
		return this.fechaCreado;
	}

	/**
	 * fecha creado
	 * 
	 * @param fechaCreado
	 */
	public void setFechaCreado(String fechaCreado)
	{
		this.fechaCreado = fechaCreado;
	}

	/**
	 * fecha ult acceso
	 * 
	 * @return
	 */
	public String getFechaUltAcceso()
	{
		return this.fechaUltAcceso;
	}

	/**
	 * fecha ultimo acceso
	 * 
	 * @param fechaUltAcceso
	 */
	public void setFechaUltAcceso(String fechaUltAcceso)
	{
		this.fechaUltAcceso = fechaUltAcceso;
	}

	/**
	 * nombre caja
	 * 
	 * @return
	 */
	public String getNombreCaja()
	{
		return this.nombreCaja;
	}

	/**
	 * nombre caja
	 * 
	 * @param nombreCaja
	 */
	public void setNombreCaja(String nombreCaja)
	{
		this.nombreCaja = nombreCaja;
	}

	/**
	 * numcotiz
	 * 
	 * @return
	 */
	public String getNumCotiz()
	{
		return this.numCotiz;
	}

	/**
	 * num cotiz
	 * 
	 * @param numCotiz
	 */
	public void setNumCotiz(String numCotiz)
	{
		this.numCotiz = numCotiz;
	}

	/**
	 * num nominas
	 * 
	 * @return
	 */
	public String getNumNominas()
	{
		return this.numNominas;
	}

	/**
	 * num nominas
	 * 
	 * @param numNominas
	 */
	public void setNumNominas(String numNominas)
	{
		this.numNominas = numNominas;
	}

	/**
	 * nombre mapeo codigo
	 * 
	 * @return
	 */
	public String getNombreMapeoCodigo()
	{
		return this.nombreMapeoCodigo;
	}

	/**
	 * nombrar mapeo codigo
	 * 
	 * @param nombreMapeoCodigo
	 */
	public void setNombreMapeoCodigo(String nombreMapeoCodigo)
	{
		this.nombreMapeoCodigo = nombreMapeoCodigo;
	}

	/**
	 * lista permisos
	 * 
	 * @return
	 */
	public List getPermisos()
	{
		return this.permisos;
	}

	/**
	 * lista permisos
	 * 
	 * @param permisos
	 */
	public void setPermisos(List permisos)
	{
		this.permisos = permisos;
	}

	/**
	 * administracion
	 * 
	 * @return
	 */
	public String getDescripcionA()
	{
		return this.descripcionA;
	}

	/**
	 * descripcionA
	 * 
	 * @param descripcionA
	 */
	public void setDescripcionA(String descripcionA)
	{
		this.descripcionA = descripcionA;
	}

	/**
	 * descripcionD
	 * 
	 * @return
	 */
	public String getDescripcionD()
	{
		return this.descripcionD;
	}

	/**
	 * descripcion D
	 * 
	 * @param descripcionD
	 */
	public void setDescripcionD(String descripcionD)
	{
		this.descripcionD = descripcionD;
	}

	/**
	 * descripcionG
	 * 
	 * @return
	 */
	public String getDescripcionG()
	{
		return this.descripcionG;
	}

	/**
	 * descripcionG
	 * 
	 * @param descripcionG
	 */
	public void setDescripcionG(String descripcionG)
	{
		this.descripcionG = descripcionG;
	}

	/**
	 * descripcionR
	 * 
	 * @return
	 */
	public String getDescripcionR()
	{
		return this.descripcionR;
	}

	/**
	 * descripcionR
	 * 
	 * @param descripcionR
	 */
	public void setDescripcionR(String descripcionR)
	{
		this.descripcionR = descripcionR;
	}

	/**
	 * opc calculoMov Personal
	 * 
	 * @return
	 */
	public int getOpcCalculoMovPersonal()
	{
		return this.opcCalculoMovPersonal;
	}

	/**
	 * opc calculo mov personal
	 * 
	 * @param opcCalculoMovPersonal
	 */
	public void setOpcCalculoMovPersonal(int opcCalculoMovPersonal)
	{
		this.opcCalculoMovPersonal = opcCalculoMovPersonal;
	}
}
