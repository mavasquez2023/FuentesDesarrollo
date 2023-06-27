

/*
 * @(#) ResumenCierrePlanillas.java    1.0 01-11-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.to;


/**
 * ...
 *
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 01-11-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> CLAUDIO LILLO AZORÍN <BR> clillo@laaraucana.cl </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author CLAUDIO LILLO AZORÍN (clillo@laaraucana.cl)
 *
 * @version 1.0
 */
public class ResumenCierreComprobantesTO {
	private int cierre;
	private String tipoNomina;
	private int numeroComprobantes;
	private int numeroComprobantesTGR;
	private int numeroComprobantesAIA;
	private int numeroComprobantesCEN;
	private int sinPlanillas;
	private int sinTGR;
	private int sinAIA;
	private int sinCEN;
	private long total;
	private int numTrabajadores;
	private int formaPago;
	/**
	 * @return el cierre
	 */
	public int getCierre() {
		return cierre;
	}
	/**
	 * @param cierre el cierre a establecer
	 */
	public void setCierre(int cierre) {
		this.cierre = cierre;
	}
	/**
	 * @return el numeroComprobantes
	 */
	public int getNumeroComprobantes() {
		return numeroComprobantes;
	}
	/**
	 * @param numeroComprobantes el numeroComprobantes a establecer
	 */
	public void setNumeroComprobantes(int numeroComprobantes) {
		this.numeroComprobantes = numeroComprobantes;
	}
	/**
	 * @return el numeroComprobantesAIA
	 */
	public int getNumeroComprobantesAIA() {
		return numeroComprobantesAIA;
	}
	/**
	 * @param numeroComprobantesAIA el numeroComprobantesAIA a establecer
	 */
	public void setNumeroComprobantesAIA(int numeroComprobantesAIA) {
		this.numeroComprobantesAIA = numeroComprobantesAIA;
	}
	/**
	 * @return el numeroComprobantesTGR
	 */
	public int getNumeroComprobantesTGR() {
		return numeroComprobantesTGR;
	}
	/**
	 * @param numeroComprobantesTGR el numeroComprobantesTGR a establecer
	 */
	public void setNumeroComprobantesTGR(int numeroComprobantesTGR) {
		this.numeroComprobantesTGR = numeroComprobantesTGR;
	}
	/**
	 * @return el numTrabajadores
	 */
	public int getNumTrabajadores() {
		return numTrabajadores;
	}
	/**
	 * @param numTrabajadores el numTrabajadores a establecer
	 */
	public void setNumTrabajadores(int numTrabajadores) {
		this.numTrabajadores = numTrabajadores;
	}
	/**
	 * @return el sinAIA
	 */
	public int getSinAIA() {
		return sinAIA;
	}
	/**
	 * @param sinAIA el sinAIA a establecer
	 */
	public void setSinAIA(int sinAIA) {
		this.sinAIA = sinAIA;
	}
	/**
	 * @return el sinPlanillas
	 */
	public int getSinPlanillas() {
		return sinPlanillas;
	}
	/**
	 * @param sinPlanillas el sinPlanillas a establecer
	 */
	public void setSinPlanillas(int sinPlanillas) {
		this.sinPlanillas = sinPlanillas;
	}
	/**
	 * @return el sinTGR
	 */
	public int getSinTGR() {
		return sinTGR;
	}
	/**
	 * @param sinTGR el sinTGR a establecer
	 */
	public void setSinTGR(int sinTGR) {
		this.sinTGR = sinTGR;
	}
	/**
	 * @return el total
	 */
	public long getTotal() {
		return total;
	}
	/**
	 * @param total el total a establecer
	 */
	public void setTotal(long total) {
		this.total = total;
	}
	/**
	 * @return el numeroComprobantesCEN
	 */
	public int getNumeroComprobantesCEN() {
		return numeroComprobantesCEN;
	}
	/**
	 * @param numeroComprobantesCEN el numeroComprobantesCEN a establecer
	 */
	public void setNumeroComprobantesCEN(int numeroComprobantesCEN) {
		this.numeroComprobantesCEN = numeroComprobantesCEN;
	}
	/**
	 * @return el sinCEN
	 */
	public int getSinCEN() {
		return sinCEN;
	}
	/**
	 * @param sinCEN el sinCEN a establecer
	 */
	public void setSinCEN(int sinCEN) {
		this.sinCEN = sinCEN;
	}
	/**
	 * @return el tipoNomina
	 */
	public String getTipoNomina() {
		return tipoNomina;
	}
	/**
	 * @param tipoNomina el tipoNomina a establecer
	 */
	public void setTipoNomina(String tipoNomina) {
		this.tipoNomina = tipoNomina;
	}
	/**
	 * @return el formaPago
	 */
	public int getFormaPago() {
		return formaPago;
	}
	/**
	 * @param formaPago el formaPago a establecer
	 */
	public void setFormaPago(int formaPago) {
		this.formaPago = formaPago;
	}
	}

