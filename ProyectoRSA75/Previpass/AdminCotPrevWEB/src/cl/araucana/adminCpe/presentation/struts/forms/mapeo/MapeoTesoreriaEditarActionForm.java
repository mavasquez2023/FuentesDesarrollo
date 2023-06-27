package cl.araucana.adminCpe.presentation.struts.forms.mapeo;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) MapeoTesoreriaEditarActionForm.java 1.1 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author malvarez
 * 
 * @version 1.1
 */
public class MapeoTesoreriaEditarActionForm extends ActionForm implements Serializable
{
	private static final long serialVersionUID = -1L;
	
	private List listaNomina;
	private List listaSeccion;
	private List listaDetalle;
	private List listaConcepto;
	private List listaMonto;
	
	private String idNomina;
	private int idTipoSeccion;
	private int idTipoDetalle;
	private int idConcepto;
	private int valorMonto;
	
	private String idNominaActual;
	private int idTipoSeccionActual;
	private int idTipoDetalleActual;
	private int idConceptoActual;
	private int valorMontoActual;
	
	private String accion; 
	
	/**
	 * Accion
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
	 * id concepto
	 * @return
	 */
	public int getIdConcepto() {
		return this.idConcepto;
	}
	/**
	 * id concepto
	 * @param idConcepto
	 */
	public void setIdConcepto(int idConcepto) {
		this.idConcepto = idConcepto;
	}
	/**
	 * id nomina
	 * @return
	 */
	public String getIdNomina() {
		return this.idNomina;
	}
	/**
	 * id nomina
	 * @param idNomina
	 */
	public void setIdNomina(String idNomina) {
		this.idNomina = idNomina;
	}
	/**
	 * id tipo detalle
	 * @return
	 */
	public int getIdTipoDetalle() {
		return this.idTipoDetalle;
	}
	/**
	 * id tipo detalle
	 * @param idTipoDetalle
	 */
	public void setIdTipoDetalle(int idTipoDetalle) {
		this.idTipoDetalle = idTipoDetalle;
	}
	/**
	 * id tipo seccion
	 * @return
	 */
	public int getIdTipoSeccion() {
		return this.idTipoSeccion;
	}
	/**
	 * id tipo seccion
	 * @param idTipoSeccion
	 */
	public void setIdTipoSeccion(int idTipoSeccion) {
		this.idTipoSeccion = idTipoSeccion;
	}
	/**
	 * lista concepto
	 * @return
	 */
	public List getListaConcepto() {
		return this.listaConcepto;
	}
	/**
	 * lista concepto
	 * @param listaConcepto
	 */
	public void setListaConcepto(List listaConcepto) {
		this.listaConcepto = listaConcepto;
	}
	/**
	 * lista detalle
	 * @return
	 */
	public List getListaDetalle() {
		return this.listaDetalle;
	}
	/**
	 * lista detalle
	 * @param listaDetalle
	 */
	public void setListaDetalle(List listaDetalle) {
		this.listaDetalle = listaDetalle;
	}
	/**
	 * lista monto
	 * @return
	 */
	public List getListaMonto() {
		return this.listaMonto;
	}
	/**
	 * lista monto
	 * @param listaMonto
	 */
	public void setListaMonto(List listaMonto) {
		this.listaMonto = listaMonto;
	}
	/**
	 * lista nomina
	 * @return
	 */
	public List getListaNomina() {
		return this.listaNomina;
	}
	/**
	 * lista nomina
	 * @param listaNomina
	 */
	public void setListaNomina(List listaNomina) {
		this.listaNomina = listaNomina;
	}
	/**
	 * lista seccion
	 * @return
	 */
	public List getListaSeccion() {
		return this.listaSeccion;
	}
	/**
	 * lista seccion
	 * @param listaSeccion
	 */
	public void setListaSeccion(List listaSeccion) {
		this.listaSeccion = listaSeccion;
	}
	/**
	 * valor monto
	 * @return
	 */
	public int getValorMonto() {
		return this.valorMonto;
	}
	/**
	 * valor monto
	 * @param valorMonto
	 */
	public void setValorMonto(int valorMonto) {
		this.valorMonto = valorMonto;
	}
	/**
	 * id concepto actual
	 * @return
	 */
	public int getIdConceptoActual() {
		return this.idConceptoActual;
	}
	/**
	 * id concepto actual
	 * @param idConceptoActual
	 */
	public void setIdConceptoActual(int idConceptoActual) {
		this.idConceptoActual = idConceptoActual;
	}
	/**
	 * id nomina actual
	 * @return
	 */
	public String getIdNominaActual() {
		return this.idNominaActual;
	}
	/**
	 * id nomina actual
	 * @param idNominaActual
	 */
	public void setIdNominaActual(String idNominaActual) {
		this.idNominaActual = idNominaActual;
	}
	/**
	 * id tipo detalle actual
	 * @return
	 */
	public int getIdTipoDetalleActual() {
		return this.idTipoDetalleActual;
	}
	/**
	 * id tipo detalle actual
	 * @param idTipoDetalleActual
	 */
	public void setIdTipoDetalleActual(int idTipoDetalleActual) {
		this.idTipoDetalleActual = idTipoDetalleActual;
	}
	/**
	 * id tipo seccion actual
	 * @return
	 */
	public int getIdTipoSeccionActual() {
		return this.idTipoSeccionActual;
	}
	/**
	 * id tipo seccion actual
	 * @param idTipoSeccionActual
	 */
	public void setIdTipoSeccionActual(int idTipoSeccionActual) {
		this.idTipoSeccionActual = idTipoSeccionActual;
	}
	/**
	 * valor nomento Actual
	 * @return
	 */
	public int getValorMontoActual() {
		return this.valorMontoActual;
	}
	/**
	 * valor monto actual
	 * @param valorMontoActual
	 */
	public void setValorMontoActual(int valorMontoActual) {
		this.valorMontoActual = valorMontoActual;
	}
	
	
	}
