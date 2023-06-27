package cl.araucana.cp.presentation.struts.forms.comprobante;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.ListUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import cl.araucana.cp.presentation.struts.javaBeans.LineaComprobantesAPagar;
/*
* @(#) PagarActionForm.java 1.6 10/05/2009
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
public class PagarActionForm extends ActionForm
{
	private static final long serialVersionUID = 4452130965247974414L;
	private String[] codigos;
	private String codigoBarra, rutFmt, rut, nombreEmpresa, convenio, tipoNomina, operacion;
	private long total;
	private List tiposNomina, consulta, empresas; 
	private List objEmpresas;
	/**
	 * reset
	 */
	public void reset(ActionMapping arg0, HttpServletRequest arg1) 
	{
		super.reset(arg0, arg1);
		
		this.codigoBarra = "";
		this.rutFmt = "";
		this.rut = "";
		this.nombreEmpresa = "";
		this.convenio = "";
		this.tipoNomina = "";
		this.objEmpresas = new ArrayList();
		this.tiposNomina = new ArrayList();
		this.empresas = new ArrayList();
		this.operacion = null;
		this.codigos = new String[0];
		this.consulta = ListUtils.lazyList(new ArrayList(),
				new Factory() 
				{
            		public Object create() 
            		{
            			return new LineaComprobantesAPagar();
            		}
				}
		);
	}
	/**
	 * convenio
	 * @return
	 */
	public String getConvenio() {
		return this.convenio;
	}
	/**
	 * convenio
	 * @param convenio
	 */
	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}
	/**
	 * nombre empresa
	 * @return
	 */
	public String getNombreEmpresa() {
		return this.nombreEmpresa;
	}
	/**
	 * nombre empresa
	 * @param nombreEmpresa
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	/**
	 * rut
	 * @return
	 */
	public String getRut() {
		return this.rut;
	}
	/**
	 * rut
	 * @param rut
	 */
	public void setRut(String rut) {
		this.rut = rut;
	}
	/**
	 * rut fmt
	 * @return
	 */
	public String getRutFmt() {
		return this.rutFmt;
	}
	/**
	 * rut fmt
	 * @param rutFmt
	 */
	public void setRutFmt(String rutFmt) {
		this.rutFmt = rutFmt;
	}
	/**
	 * tipo nomina
	 * @return
	 */
	public String getTipoNomina() {
		return this.tipoNomina;
	}
	/**
	 * tipo nomina
	 * @param tipoNomina
	 */
	public void setTipoNomina(String tipoNomina) {
		this.tipoNomina = tipoNomina;
	}
	/**
	 * object empresas
	 * @return
	 */
	public List getObjEmpresas() {
		return this.objEmpresas;
	}
	/**
	 * object empresas
	 * @param objEmpresas
	 */
	public void setObjEmpresas(List objEmpresas) {
		this.objEmpresas = objEmpresas;
	}
	/**
	 * tipos nomina
	 * @return
	 */
	public List getTiposNomina() {
		return this.tiposNomina;
	}
	/**
	 * tipos nomina
	 * @param tiposNomina
	 */
	public void setTiposNomina(List tiposNomina) {
		this.tiposNomina = tiposNomina;
	}
	/**
	 * codigo barra
	 * @return
	 */
	public String getCodigoBarra() {
		return this.codigoBarra;
	}
	/**
	 * codigo barra
	 * @param codigoBarra
	 */
	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	/**
	 * consulta
	 * @return
	 */
	public List getConsulta() {
		return this.consulta;
	}
	/**
	 * consulta
	 * @param consulta
	 */
	public void setConsulta(List consulta) {
		this.consulta = consulta;
	}
	/**
	 * total
	 * @return
	 */
	public long getTotal()
	{
		return this.total;
	}
	/**
	 * total
	 * @param total
	 */
	public void setTotal(long total)
	{
		this.total = total;
	}
	/**
	 * operacion
	 * @return
	 */
	public String getOperacion()
	{
		return this.operacion;
	}
	/**
	 * operacion
	 * @param operacion
	 */
	public void setOperacion(String operacion)
	{
		this.operacion = operacion;
	}
	/**
	 * codigos
	 * @return
	 */
	public String[] getCodigos()
	{
		return this.codigos;
	}
	/**
	 * codigos
	 * @param codigos
	 */
	public void setCodigos(String[] codigos)
	{
		this.codigos = codigos;
	}
	/**
	 * empresas
	 * @return
	 */
	public List getEmpresas()
	{
		return this.empresas;
	}
	/**
	 * empresas
	 * @param empresas
	 */
	public void setEmpresas(List empresas)
	{
		this.empresas = empresas;
	}
}
