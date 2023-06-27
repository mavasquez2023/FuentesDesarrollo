package cl.araucana.cp.presentation.struts.forms;

import java.util.Collection;
import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) DetalleSabanaForm.java 1.3 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * @author cmeli
 * 
 * @version 1.3
 */
public class DetalleSabanaForm extends ActionForm 
{
	private static final long serialVersionUID = -4805288895405387269L;
	private Collection consulta;
	private String accion;
	private String idCodBarras;
	private String idConvenioDesc;
	private String rutEmpresaDesc;
	private List tiposProcesos;

	
	private Collection numeroFilas;
	
	private String tipoProceso;
	/**
	 * tipo proceso
	 * @return
	 */
	public String getTipoProceso() 
	{
		return this.tipoProceso;
	}
	/**
	 * tipo proceso
	 * @param tipoProceso
	 */
	public void setTipoProceso(String tipoProceso) {
		this.tipoProceso = tipoProceso;
	}
	/**
	 * id convenio
	 * @return
	 */
	public String getIdConvenio() {
		return this.idConvenioDesc;
	}
	/**
	 * id convenio
	 * @param idConvenio
	 */
	public void setIdConvenio(String idConvenio) {
		this.idConvenioDesc = idConvenio;
	}
	/**
	 * consulta
	 * @return
	 */
	public Collection getConsulta()
	{
		return this.consulta;
	}
	/**
	 * consulta
	 * @param consulta
	 */
	public void setConsulta(Collection consulta)
	{
		this.consulta = consulta;
	}
	/**
	 * accion
	 * @return
	 */
	public String getAccion() {
		return this.accion;
	}
	/**
	 * accion
	 * @param accion
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}
	/**
	 * rut empresa
	 * @return
	 */
	public String getRutEmpresa() {
		return this.rutEmpresaDesc;
	}
	/**
	 * rut empresa
	 * @param rutEmpresa
	 */
	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresaDesc = rutEmpresa;
	}
	/**
	 * tipos procesos
	 * @return
	 */
	public List getTiposProcesos() {
		return this.tiposProcesos;
	}
	/**
	 * tipos procesos
	 * @param tiposProcesos
	 */
	public void setTiposProcesos(List tiposProcesos) {
		this.tiposProcesos = tiposProcesos;
	}
	/**
	 * id codigo barras
	 * @return
	 */
	public String getIdCodBarras() {
		return this.idCodBarras;
	}
	/**
	 * id codigo barras
	 * @param idCodBarras
	 */
	public void setIdCodBarras(String idCodBarras) {
		this.idCodBarras = idCodBarras;
	}
	/**
	 * numero filas
	 * @return
	 */
	public Collection getNumeroFilas() {
		return this.numeroFilas;
	}

	/**
	 * numero filas
	 * @param numeroFilas
	 */
	public void setNumeroFilas(Collection numeroFilas) {
		this.numeroFilas = numeroFilas;
	}
}
