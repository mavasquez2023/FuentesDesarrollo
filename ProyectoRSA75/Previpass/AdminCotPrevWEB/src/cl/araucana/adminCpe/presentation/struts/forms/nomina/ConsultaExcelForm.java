package cl.araucana.adminCpe.presentation.struts.forms.nomina;

import java.util.Collection;
import java.util.List;

import org.apache.struts.action.ActionForm;

/*
 * @(#) ConsultaExcelForm.java 1.2 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author aacuña
 * @author cchamblas
 * 
 * @version 1.2
 */
public class ConsultaExcelForm extends ActionForm
{

	private static final long serialVersionUID = -4704281203425810728L;

	private Collection consulta;
	private String convenios;
	private String accion;
	private String idConvenioDesc;
	private String rutEmpresaDesc;
	private List detalleExcel;
	private List tiposProcesos;

	private String tipoProceso;

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
	 * id convenio
	 * 
	 * @return
	 */
	public String getIdConvenio()
	{
		return this.idConvenioDesc;
	}

	/**
	 * id convenio
	 * 
	 * @param idConvenio
	 */
	public void setIdConvenio(String idConvenio)
	{
		this.idConvenioDesc = idConvenio;
	}

	/**
	 * consulta
	 * 
	 * @return
	 */
	public Collection getConsulta()
	{
		return this.consulta;
	}

	/**
	 * consulta
	 * 
	 * @param consulta
	 */
	public void setConsulta(Collection consulta)
	{
		this.consulta = consulta;
	}

	/**
	 * convenio
	 * 
	 * @return
	 */
	public String getConvenio()
	{
		return this.convenios;
	}

	/**
	 * convenio
	 * 
	 * @param convenio
	 */
	public void setConvenio(String convenio)
	{
		this.convenios = convenio;
	}

	/**
	 * accion
	 * 
	 * @return
	 */
	public String getAccion()
	{
		return this.accion;
	}

	/**
	 * accion
	 * 
	 * @param accion
	 */
	public void setAccion(String accion)
	{
		this.accion = accion;
	}

	/**
	 * rut empresa
	 * 
	 * @return
	 */
	public String getRutEmpresa()
	{
		return this.rutEmpresaDesc;
	}

	/**
	 * rut empresa
	 * 
	 * @param rutEmpresa
	 */
	public void setRutEmpresa(String rutEmpresa)
	{
		this.rutEmpresaDesc = rutEmpresa;
	}

	/**
	 * detalle excel
	 * 
	 * @return
	 */
	public List getDetalleExcel()
	{
		return this.detalleExcel;
	}

	/**
	 * detalle excel
	 * 
	 * @param detalleExcel
	 */
	public void setDetalleExcel(List detalleExcel)
	{
		this.detalleExcel = detalleExcel;
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
}
