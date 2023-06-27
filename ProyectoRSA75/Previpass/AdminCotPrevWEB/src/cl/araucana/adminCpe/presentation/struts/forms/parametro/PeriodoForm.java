package cl.araucana.adminCpe.presentation.struts.forms.parametro;

import org.apache.struts.action.ActionForm;
/*
* @(#) PeriodoForm.java 1.1 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * 
 * @version 1.1
 */
public class PeriodoForm extends ActionForm 
{
	private static final long serialVersionUID = 6979008495684279506L;
	String numTrabajadores;
	String numNominasRE;
	String numNominasGR;
	String numNominasRA;
	String numNominasDC;
	String numComprobantes;
	String operacion;
	String msg;

	String numTrabajadoresInd;
	String numNominasREInd;
	String numNominasGRInd;
	String numNominasRAInd;
	String numNominasDCInd;
	String numComprobantesInd;
	/**
	 * numero comprobantes
	 * @return
	 */
	public String getNumComprobantes() {
		return this.numComprobantes;
	}
	/**
	 * numero comprobantes
	 * @param numComprobantes
	 */
	public void setNumComprobantes(String numComprobantes) {
		this.numComprobantes = numComprobantes;
	}
	/**
	 * numero niminas deposito conv
	 * @return
	 */
	public String getNumNominasDC() {
		return this.numNominasDC;
	}
	/**
	 * numero nominas deposito conv
	 * @param numNominasDC
	 */
	public void setNumNominasDC(String numNominasDC) {
		this.numNominasDC = numNominasDC;
	}
	/**
	 * numero nominas gratificacion
	 * @return
	 */
	public String getNumNominasGR() {
		return this.numNominasGR;
	}
	/**
	 * numero nominas gratificacion
	 * @param numNominasGR
	 */
	public void setNumNominasGR(String numNominasGR) {
		this.numNominasGR = numNominasGR;
	}
	/**
	 * numero nominas remuneracion
	 * @return
	 */
	public String getNumNominasRA() {
		return this.numNominasRA;
	}
	/**
	 * numero nominas remuneracion
	 * @param numNominasRA
	 */
	public void setNumNominasRA(String numNominasRA) {
		this.numNominasRA = numNominasRA;
	}
	/**
	 * numero nominas reliquidacion
	 * @return
	 */
	public String getNumNominasRE() {
		return this.numNominasRE;
	}
	/**
	 * numero nominas reliquidacion
	 * @param numNominasRE
	 */
	public void setNumNominasRE(String numNominasRE) {
		this.numNominasRE = numNominasRE;
	}
	/**
	 * numero trabajadores
	 * @return
	 */
	public String getNumTrabajadores() {
		return this.numTrabajadores;
	}
	/**
	 * numero trabajadores
	 * @param numTrabajadores
	 */
	public void setNumTrabajadores(String numTrabajadores) {
		this.numTrabajadores = numTrabajadores;
	}
	/**
	 * operacion
	 * @return
	 */
	public String getOperacion() {
		return this.operacion;
	}
	/**
	 * operacion
	 * @param operacion
	 */
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	/**
	 * mensaje
	 * @return
	 */
	public String getMsg() {
		return this.msg;
	}
	/**
	 * mensaje
	 * @param msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getNumComprobantesInd() {
		return numComprobantesInd;
	}
	public void setNumComprobantesInd(String numComprobantesInd) {
		this.numComprobantesInd = numComprobantesInd;
	}
	public String getNumNominasDCInd() {
		return numNominasDCInd;
	}
	public void setNumNominasDCInd(String numNominasDCInd) {
		this.numNominasDCInd = numNominasDCInd;
	}
	public String getNumNominasGRInd() {
		return numNominasGRInd;
	}
	public void setNumNominasGRInd(String numNominasGRInd) {
		this.numNominasGRInd = numNominasGRInd;
	}
	public String getNumNominasRAInd() {
		return numNominasRAInd;
	}
	public void setNumNominasRAInd(String numNominasRAInd) {
		this.numNominasRAInd = numNominasRAInd;
	}
	public String getNumNominasREInd() {
		return numNominasREInd;
	}
	public void setNumNominasREInd(String numNominasREInd) {
		this.numNominasREInd = numNominasREInd;
	}
	public String getNumTrabajadoresInd() {
		return numTrabajadoresInd;
	}
	public void setNumTrabajadoresInd(String numTrabajadoresInd) {
		this.numTrabajadoresInd = numTrabajadoresInd;
	}
	
}
