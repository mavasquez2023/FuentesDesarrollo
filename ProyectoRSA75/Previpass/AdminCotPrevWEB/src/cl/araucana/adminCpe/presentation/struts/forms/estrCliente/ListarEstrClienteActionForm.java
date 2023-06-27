package cl.araucana.adminCpe.presentation.struts.forms.estrCliente;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;

/*
 * @(#) ListarEstrClienteActionForm.java 1.4 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author malvarez
 * @author aacuña
 * 
 * @version 1.4
 */
public class ListarEstrClienteActionForm extends ActionForm
{
	private static final long serialVersionUID = 2853065679225574902L;

	private List listaAdmin;
	private List listaEncargadoConvenio;
	private List listaLectorPlanilla;
	private String resultado;
	private String busquedaEmpresa;
	private String busquedaGrupo;
	private HashMap resultadoGrupo;

	private List filas;

	/**
	 * lista adinistrador
	 * 
	 * @return
	 */
	public List getListaAdmin()
	{
		return this.listaAdmin;
	}

	/**
	 * lista administrador
	 * 
	 * @param listaAdmin
	 */
	public void setListaAdmin(List listaAdmin)
	{
		this.listaAdmin = listaAdmin;
	}

	/**
	 * lista encargado convenio
	 * 
	 * @return
	 */
	public List getListaEncargadoConvenio()
	{
		return this.listaEncargadoConvenio;
	}

	/**
	 * lista encargado convenio
	 * 
	 * @param listaEncargadoConvenio
	 */
	public void setListaEncargadoConvenio(List listaEncargadoConvenio)
	{

		this.listaEncargadoConvenio = listaEncargadoConvenio;
	}

	/**
	 * resultado
	 * 
	 * @return
	 */
	public String getResultado()
	{
		return this.resultado;
	}

	/**
	 * resultado
	 * 
	 * @param resultado
	 */
	public void setResultado(String resultado)
	{
		this.resultado = resultado;
	}

	/**
	 * lista lector planilla
	 * 
	 * @return
	 */
	public List getListaLectorPlanilla()
	{
		return this.listaLectorPlanilla;
	}

	/**
	 * lista lector planilla
	 * 
	 * @param listaLectorPlanilla
	 */
	public void setListaLectorPlanilla(List listaLectorPlanilla)
	{
		this.listaLectorPlanilla = listaLectorPlanilla;
	}

	/**
	 * resultado grupo
	 * 
	 * @return
	 */
	public HashMap getResultadoGrupo()
	{
		return this.resultadoGrupo;
	}

	/**
	 * resultado grupo
	 * 
	 * @param resultadoGrupo
	 */
	public void setResultadoGrupo(HashMap resultadoGrupo)
	{
		this.resultadoGrupo = resultadoGrupo;
	}

	/**
	 * filas
	 * 
	 * @return
	 */
	public List getFilas()
	{
		return this.filas;
	}

	/**
	 * filas
	 * 
	 * @param filas
	 */
	public void setFilas(List filas)
	{
		this.filas = filas;
	}

	public String getBusquedaEmpresa()
	{
		return this.busquedaEmpresa;
	}

	public void setBusquedaEmpresa(String busquedaEmpresa)
	{
		this.busquedaEmpresa = busquedaEmpresa;
	}

	public String getBusquedaGrupo()
	{
		return this.busquedaGrupo;
	}

	public void setBusquedaGrupo(String busquedaGrupo)
	{
		this.busquedaGrupo = busquedaGrupo;
	}

}
