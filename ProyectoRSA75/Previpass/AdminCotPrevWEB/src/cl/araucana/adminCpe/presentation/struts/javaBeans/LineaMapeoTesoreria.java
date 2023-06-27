package cl.araucana.adminCpe.presentation.struts.javaBeans;

import java.io.Serializable;
/*
* @(#) LineaMapeoTesoreria.java 1.1 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author jdelgado
 * 
 * @version 1.1
 */
public class LineaMapeoTesoreria implements Serializable
{
	private static final long serialVersionUID = -1;
	
	private String nomina;
	private String tipoSeccion;
	private String tipoDetalle;
	private String concepto;
	private int monto;
	
	private char idNomina;
	private int idTipoSeccion;
	private int idTipoDetalle;
	private int idConcepto;
	private int idMonto;
	private int valorMonto;	
	
	/**
	 * concepto
	 * @return
	 */
	public String getConcepto() {
		return this.concepto;
	}
	/**
	 * id concepto
	 * @return
	 */
	public int getIdConcepto() {
		return this.idConcepto;
	}
	/**
	 * id nomina
	 * @return
	 */
	public char getIdNomina() {
		return this.idNomina;
	}
	/**
	 * id tipo detalle
	 * @return
	 */
	public int getIdTipoDetalle() {
		return this.idTipoDetalle;
	}
	/**
	 * id tipo seccion
	 * @return
	 */
	public int getIdTipoSeccion() {
		return this.idTipoSeccion;
	}
	/**
	 * monto
	 * @return
	 */
	public int getMonto() {
		return this.monto;
	}
	/**
	 * nomina
	 * @return
	 */
	public String getNomina() {
		return this.nomina;
	}
	/**
	 * tipo detalle
	 * @return
	 */
	public String getTipoDetalle() {
		return this.tipoDetalle;
	}
	/**
	 * tipo seccion
	 * @return
	 */
	public String getTipoSeccion() {
		return this.tipoSeccion;
	}
	public int getValorMonto() {
		return this.valorMonto;
	}
	/**
	 * concepto
	 * @param concepto
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
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
	 * @param idNomina
	 */
	public void setIdNomina(char idNomina) {
		this.idNomina = idNomina;
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
	 * @param idTipoSeccion
	 */
	public void setIdTipoSeccion(int idTipoSeccion) {
		this.idTipoSeccion = idTipoSeccion;
	}
	/**
	 * monto
	 * @param monto
	 */
	public void setMonto(int monto) {
		this.monto = monto;
	}
	/**
	 * nomina
	 * @param nomina
	 */
	public void setNomina(String nomina) {
		this.nomina = nomina;
	}
	/**
	 * tipo detalle
	 * @param tipoDetalle
	 */
	public void setTipoDetalle(String tipoDetalle) {
		this.tipoDetalle = tipoDetalle;
	}
	/**
	 * tipo seccion
	 * @param tipoSeccion
	 */
	public void setTipoSeccion(String tipoSeccion) {
		this.tipoSeccion = tipoSeccion;
	}
	/**
	 * valor monto
	 * @param valorMonto
	 */
	public void setValorMonto(int valorMonto) {
		this.valorMonto = valorMonto;
	}
	/**
	 * id monto
	 * @return
	 */
	public int getIdMonto() {
		return this.idMonto;
	}
	/**
	 * id monto
	 * @param idMonto
	 */
	public void setIdMonto(int idMonto) {
		this.idMonto = idMonto;
	}	
}
