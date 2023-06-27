package cl.araucana.cp.presentation.struts.forms.cotizante;

import java.util.Collection;
import java.util.List;

import org.apache.struts.action.ActionForm;

import cl.araucana.cp.distribuidor.presentation.beans.Cotizante;

/*
 * @(#)ListarActionForm.java 1.6 10/05/2009
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
public class ListarActionForm extends ActionForm
{
	private static final long serialVersionUID = 4104952975991885319L;
	private String tipoProceso;
	private String convenio;
	private String rutEmpresa;
	private String rutTrab;
	private String rutEmpresaFormat;
	private String razonSocial;

	private int numTrabTotal;

	private Cotizante cotizante;

	private List trabajadores;
	private List trabPendientes;
	private List trabAvisos;
	private List empresas;
	private List tiposProcesos;
	private List objEmpresas;

	private Collection numeroFilas;
	private List consulta;

	private int editNomina;// 0: no (no permitir edicion), 1: si (nomina editable)
	
//	clillo 3-12-14 por Reliquidación
	private int periodo;
	
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
	 * empresas
	 * 
	 * @return
	 */
	public List getEmpresas()
	{
		return this.empresas;
	}

	/**
	 * empresas
	 * 
	 * @param empresas
	 */
	public void setEmpresas(List empresas)
	{
		this.empresas = empresas;
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
	 * rut trabajador
	 * 
	 * @return
	 */
	public String getRutTrab()
	{
		return this.rutTrab;
	}

	/**
	 * rut trabajador
	 * 
	 * @param rutTrabajador
	 */
	public void setRutTrab(String rutTrabajador)
	{
		this.rutTrab = rutTrabajador;
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
	 * trabajadores pendientes
	 * 
	 * @return
	 */
	public List getTrabPendientes()
	{
		return this.trabPendientes;
	}

	/**
	 * trabajadores pendientes
	 * 
	 * @param trabPendientes
	 */
	public void setTrabPendientes(List trabPendientes)
	{
		this.trabPendientes = trabPendientes;
	}

	/**
	 * numero trabajadores total
	 * 
	 * @return
	 */
	public int getNumTrabTotal()
	{
		return this.numTrabTotal;
	}

	/**
	 * numero trabajadores total
	 * 
	 * @param numTrabTotal
	 */
	public void setNumTrabTotal(int numTrabTotal)
	{
		this.numTrabTotal = numTrabTotal;
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
	 * consulta
	 * 
	 * @return
	 */
	public List getConsulta()
	{
		return this.consulta;
	}

	/**
	 * consulta
	 * 
	 * @param consulta
	 */
	public void setConsulta(List consulta)
	{
		this.consulta = consulta;
	}

	/**
	 * object empresas
	 * 
	 * @return
	 */
	public List getObjEmpresas()
	{
		return this.objEmpresas;
	}

	/**
	 * object empresas
	 * 
	 * @param objEmpresas
	 */
	public void setObjEmpresas(List objEmpresas)
	{
		this.objEmpresas = objEmpresas;
	}

	public int getEditNomina()
	{
		return this.editNomina;
	}

	public void setEditNomina(int editNomina)
	{
		this.editNomina = editNomina;
	}

	public List getTrabAvisos()
	{
		return this.trabAvisos;
	}

	public void setTrabAvisos(List trabAvisos)
	{
		this.trabAvisos = trabAvisos;
	}
	/**
	 * @return el periodo
	 */
	public int getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo el periodo a establecer
	 */
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
}
