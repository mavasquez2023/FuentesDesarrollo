package cl.araucana.cp.presentation.struts.forms.cotizante;

import java.util.Collection;
import java.util.List;

import org.apache.struts.action.ActionForm;

/*
 * @(#) ListarAllActionForm.java 1.6 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.6
 */
public class ListarAllActionForm extends ActionForm
{
	private static final long serialVersionUID = 6687264303807600020L;

	private String rut;
	private String nombre;
	private String apellidos;
	private Collection numeroFilas;
	private List trabajadores;
	private List pendientes;
	private List avisos;

	private String rutOculto;
	private String nombreOculto;
	private String apellidosOculto;

	//Buscador por Empresa
	private String rutEmpresa;
	private String dvRutEmpresa;
	private String razonSocial;
	private String tipoProceso;

	private String opcGrupoConvenio;
	private String nombreGrupoConvenio;
	private List   gruposConvenio;

	private String mostrarLista;

	private int estadoNomina;

	public int getEstadoNomina()
	{
		return this.estadoNomina;
	}

	public void setEstadoNomina(int estadoNomina)
	{
		this.estadoNomina = estadoNomina;
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
	 * trabajadores
	 * 
	 * @return
	 */
	public List getTrabajadores()
	{
		return this.trabajadores;
	}

	/**
	 * trabajadores
	 * 
	 * @param trabajadores
	 */
	public void setTrabajadores(List trabajadores)
	{
		this.trabajadores = trabajadores;
	}

	/**
	 * numero filas
	 * 
	 * @return
	 */
	public Collection getNumeroFilas()
	{
		return this.numeroFilas;
	}

	/**
	 * numero filas
	 * 
	 * @param numeroFilas
	 */
	public void setNumeroFilas(Collection numeroFilas)
	{
		this.numeroFilas = numeroFilas;
	}

	/**
	 * pendientes
	 * 
	 * @return
	 */
	public List getPendientes()
	{
		return this.pendientes;
	}

	/**
	 * pendientes
	 * 
	 * @param pendientes
	 */
	public void setPendientes(List pendientes)
	{
		this.pendientes = pendientes;
	}

	/**
	 * apellidos oculto
	 * 
	 * @return
	 */
	public String getApellidosOculto()
	{
		return this.apellidosOculto;
	}

	/**
	 * apellidos oculto
	 * 
	 * @param apellidosOculto
	 */
	public void setApellidosOculto(String apellidosOculto)
	{
		this.apellidosOculto = apellidosOculto;
	}

	/**
	 * nombre oculto
	 * 
	 * @return
	 */
	public String getNombreOculto()
	{
		return this.nombreOculto;
	}

	/**
	 * nombre oculto
	 * 
	 * @param nombreOculto
	 */
	public void setNombreOculto(String nombreOculto)
	{
		this.nombreOculto = nombreOculto;
	}

	/**
	 * rut oculto
	 * 
	 * @return
	 */
	public String getRutOculto()
	{
		return this.rutOculto;
	}

	/**
	 * rut oculto
	 * 
	 * @param rutOculto
	 */
	public void setRutOculto(String rutOculto)
	{
		this.rutOculto = rutOculto;
	}

	public List getAvisos()
	{
		return this.avisos;
	}

	public void setAvisos(List avisos)
	{
		this.avisos = avisos;
	}

	public String getDvRutEmpresa()
	{
		return dvRutEmpresa;
	}

	public void setDvRutEmpresa(String dvRutEmpresa)
	{
		this.dvRutEmpresa = dvRutEmpresa;
	}

	public List getGruposConvenio()
	{
		return gruposConvenio;
	}

	public void setGruposConvenio(List gruposConvenio)
	{
		this.gruposConvenio = gruposConvenio;
	}

	public String getNombreGrupoConvenio()
	{
		return nombreGrupoConvenio;
	}

	public void setNombreGrupoConvenio(String nombreGrupoConvenio)
	{
		this.nombreGrupoConvenio = nombreGrupoConvenio;
	}

	public String getOpcGrupoConvenio()
	{
		return opcGrupoConvenio;
	}

	public void setOpcGrupoConvenio(String opcGrupoConvenio)
	{
		this.opcGrupoConvenio = opcGrupoConvenio;
	}

	public String getRazonSocial()
	{
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial)
	{
		this.razonSocial = razonSocial;
	}

	public String getRutEmpresa()
	{
		return rutEmpresa;
	}

	public void setRutEmpresa(String rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
	}

	public String getTipoProceso()
	{
		return tipoProceso;
	}

	public void setTipoProceso(String tipoProceso)
	{
		this.tipoProceso = tipoProceso;
	}

	public String getMostrarLista()
	{
		return mostrarLista;
	}

	public void setMostrarLista(String mostrarLista)
	{
		this.mostrarLista = mostrarLista;
	}
}
