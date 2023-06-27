package cl.araucana.adminCpe.presentation.struts.forms.informes;

import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) ControlProcesosListarActionForm.java 1.1 10/05/2009
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
public class ControlProcesosListarActionForm extends ActionForm
{
	private static final long serialVersionUID = 2853065679225574902L;

	private List listaPagadas;
	private List listaNoPagadas;
	private List listaConDeclaro;
	private List listaNoPago;
	private int numEmpresasPA,numTrabajadoresPA;
	private int numEmpresasNP,numTrabajadoresNP;
	private int numEmpresasCD,numTrabajadoresCD;
	private int numEmpresasNN,numTrabajadoresNN;
	private int numEmpresasTotal,numTrabajadoresTotal;
	private double totPagadoPA, totPagadoNP, totPagadoCD, totPagadoNN, totPagadoTotal;
	private String totPagadoPAMonto, totPagadoNPMonto, totPagadoCDMonto, totPagadoNNMonto,totPagadoTotalMonto;
	
	/**
	 * lista control declaro
	 * @return
	 */
	public List getListaConDeclaro() {
		return this.listaConDeclaro;
	}
	/**
	 * lista control declaro
	 * @param listaConDeclaro
	 */
	public void setListaConDeclaro(List listaConDeclaro) {
		this.listaConDeclaro = listaConDeclaro;
	}
	/**
	 * lista No pagadas
	 * @return
	 */
	public List getListaNoPagadas() {
		return this.listaNoPagadas;
	}
	/**
	 * lis no pagadas
	 * @param listaNoPagadas
	 */
	public void setListaNoPagadas(List listaNoPagadas) {
		this.listaNoPagadas = listaNoPagadas;
	}
	/**
	 * lista no pago
	 * @return
	 */
	public List getListaNoPago() {
		return this.listaNoPago;
	}
	/**
	 * lista no pago
	 * @param listaNoPago
	 */
	public void setListaNoPago(List listaNoPago) {
		this.listaNoPago = listaNoPago;
	}
	/**
	 * lista pagadas
	 * @return
	 */
	public List getListaPagadas() {
		return this.listaPagadas;
	}
	/**
	 * lista pagadas
	 * @param listaPagadas
	 */
	public void setListaPagadas(List listaPagadas) {
		this.listaPagadas = listaPagadas;
	}
	/**
	 * numero empresas cd
	 * @return
	 */
	public int getNumEmpresasCD() {
		return this.numEmpresasCD;
	}
	/**
	 * numero empresas cd
	 * @param numEmpresasCD
	 */
	public void setNumEmpresasCD(int numEmpresasCD) {
		this.numEmpresasCD = numEmpresasCD;
	}
	/**
	 * numero empresas nn
	 * @return
	 */
	public int getNumEmpresasNN() {
		return this.numEmpresasNN;
	}
	/**
	 * numero empresas nn
	 * @param numEmpresasNN
	 */
	public void setNumEmpresasNN(int numEmpresasNN) {
		this.numEmpresasNN = numEmpresasNN;
	}
	/**
	 * numero empresas np
	 * @return
	 */
	public int getNumEmpresasNP() {
		return this.numEmpresasNP;
	}
	/**
	 * numero empresas np
	 * @param numEmpresasNP
	 */
	public void setNumEmpresasNP(int numEmpresasNP) {
		this.numEmpresasNP = numEmpresasNP;
	}
	/**
	 * numero empresas pa
	 * @return
	 */
	public int getNumEmpresasPA() {
		return this.numEmpresasPA;
	}
	/**
	 * numero emprsas pa
	 * @param numEmpresasPA
	 */
	public void setNumEmpresasPA(int numEmpresasPA) {
		this.numEmpresasPA = numEmpresasPA;
	}
	/**
	 * numero empresas total
	 * @return
	 */
	public int getNumEmpresasTotal() {
		return this.numEmpresasTotal;
	}
	/**
	 * numero empresas total
	 * @param numEmpresasTotal
	 */
	public void setNumEmpresasTotal(int numEmpresasTotal) {
		this.numEmpresasTotal = numEmpresasTotal;
	}
	/**
	 * numero trabajadores cd
	 * @return
	 */
	public int getNumTrabajadoresCD() {
		return this.numTrabajadoresCD;
	}
	/**
	 * numero trabajadores cd
	 * @param numTrabajadoresCD
	 */
	public void setNumTrabajadoresCD(int numTrabajadoresCD) {
		this.numTrabajadoresCD = numTrabajadoresCD;
	}
	/**
	 * numero trabajadores nn
	 * @return
	 */
	public int getNumTrabajadoresNN() {
		return this.numTrabajadoresNN;
	}
	/**
	 * numero trabajadores nn
	 * @param numTrabajadoresNN
	 */
	public void setNumTrabajadoresNN(int numTrabajadoresNN) {
		this.numTrabajadoresNN = numTrabajadoresNN;
	}
	/**
	 * numero trabajadores np
	 * @return
	 */
	public int getNumTrabajadoresNP() {
		return this.numTrabajadoresNP;
	}
	/**
	 * numero trabajadores np
	 * @param numTrabajadoresNP
	 */
	public void setNumTrabajadoresNP(int numTrabajadoresNP) {
		this.numTrabajadoresNP = numTrabajadoresNP;
	}
	/**
	 * numero trabajadores pa
	 * @return
	 */
	public int getNumTrabajadoresPA() {
		return this.numTrabajadoresPA;
	}
	/**
	 * numero trabajadores pa
	 * @param numTrabajadoresPA
	 */
	public void setNumTrabajadoresPA(int numTrabajadoresPA) {
		this.numTrabajadoresPA = numTrabajadoresPA;
	}
	/**
	 *numero trabajadores total
	 * @return
	 */
	public int getNumTrabajadoresTotal() {
		return this.numTrabajadoresTotal;
	}
	/**
	 * numero trabajadores total
	 * @param numTrabajadoresTotal
	 */
	public void setNumTrabajadoresTotal(int numTrabajadoresTotal) {
		this.numTrabajadoresTotal = numTrabajadoresTotal;
	}
	/**
	 * total pagado cd
	 * @return
	 */
	public double getTotPagadoCD() {
		return this.totPagadoCD;
	}
	/**
	 * total pagado cd
	 * @param totPagadoCD
	 */
	public void setTotPagadoCD(double totPagadoCD) {
		this.totPagadoCD = totPagadoCD;
	}
	/**
	 * total pagado nn
	 * @return
	 */
	public double getTotPagadoNN() {
		return this.totPagadoNN;
	}
	/**
	 * total pagado nn
	 * @param totPagadoNN
	 */
	public void setTotPagadoNN(double totPagadoNN) {
		this.totPagadoNN = totPagadoNN;
	}
	/**
	 * total pagado np
	 * @return
	 */
	public double getTotPagadoNP() {
		return this.totPagadoNP;
	}
	/**
	 * total pagado np
	 * @param totPagadoNP
	 */
	public void setTotPagadoNP(double totPagadoNP) {
		this.totPagadoNP = totPagadoNP;
	}
	/**
	 * total pagado pa
	 * @return
	 */
	public double getTotPagadoPA() {
		return this.totPagadoPA;
	}
	/**
	 * total pagado pa
	 * @param totPagadoPA
	 */
	public void setTotPagadoPA(double totPagadoPA) {
		this.totPagadoPA = totPagadoPA;
	}
	/**
	 * total pagado total
	 * @return
	 */
	public double getTotPagadoTotal() {
		return this.totPagadoTotal;
	}
	/**
	 * total pagado total
	 * @param totPagadoTotal
	 */
	public void setTotPagadoTotal(double totPagadoTotal) {
		this.totPagadoTotal = totPagadoTotal;
	}
	/**
	 * total pagado total monto
	 * @return
	 */
	public String getTotPagadoTotalMonto() {
		return this.totPagadoTotalMonto;
	}
	/**
	 * total pagado total monto
	 * @param totPagadoTotalMonto
	 */
	public void setTotPagadoTotalMonto(String totPagadoTotalMonto) {
		this.totPagadoTotalMonto = totPagadoTotalMonto;
	}
	/**
	 * total pagado cd monto
	 * @return
	 */
	public String getTotPagadoCDMonto() {
		return this.totPagadoCDMonto;
	}
	/**
	 * total pagado cd monto
	 * @param totPagadoCDMonto
	 */
	public void setTotPagadoCDMonto(String totPagadoCDMonto) {
		this.totPagadoCDMonto = totPagadoCDMonto;
	}
	/**
	 * total pagado nn monto
	 * @return
	 */
	public String getTotPagadoNNMonto() {
		return this.totPagadoNNMonto;
	}
	/**
	 * total pagado nn monto
	 * @param totPagadoNNMonto
	 */
	public void setTotPagadoNNMonto(String totPagadoNNMonto) {
		this.totPagadoNNMonto = totPagadoNNMonto;
	}
	/**
	 * total pagado np monto
	 * @return
	 */
	public String getTotPagadoNPMonto() {
		return this.totPagadoNPMonto;
	}
	/**
	 * total pagado np monto
	 * @param totPagadoNPMonto
	 */
	public void setTotPagadoNPMonto(String totPagadoNPMonto) {
		this.totPagadoNPMonto = totPagadoNPMonto;
	}
	/**
	 * total pagado pa monto
	 * @return
	 */
	public String getTotPagadoPAMonto() {
		return this.totPagadoPAMonto;
	}
	/**
	 * total pagado pa monto
	 * @param totPagadoPAMonto
	 */
	public void setTotPagadoPAMonto(String totPagadoPAMonto) {
		this.totPagadoPAMonto = totPagadoPAMonto;
	}
}
