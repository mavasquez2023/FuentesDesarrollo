package cl.araucana.spl.beans;

import java.math.BigDecimal;
import java.util.Date;

import cl.araucana.spl.util.Renderer;
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
 *            <TD> 13-01-2014 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Gonzalo Mallea Lorca <BR> gmallea@schema.cl </TD>
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
 * @author Gonzalo Mallea Lorca (gmallea@schema.cl)
 *
 * @version 1.0
 */

public class FiltroConcluirPago {
	public static final String TIPO_FECHA_PAGO = "P";
	public static final String TIPO_FECHA_CONTABLE = "C";

	private static Renderer renderer = new Renderer();
	
	private BigDecimal numeroPago;
	private BigDecimal numeroFolio;
	private String tipoFecha;
	
	private Date fechaPagoDesde;
	private Date fechaPagoHasta;
	private Date fechaContableDesde;
	private Date fechaContableHasta;
	
	private BigDecimal estado;
	private BigDecimal banco;
	private BigDecimal sistema;

	private long offset;
	private int limit;
	
	private String trxConsiderar;
	
	public BigDecimal getBanco() {
		return banco;
	}
	public void setBanco(BigDecimal banco) {
		this.banco = banco;
	}
	public BigDecimal getEstado() {
		return estado;
	}
	public void setEstado(BigDecimal estado) {
		this.estado = estado;
	}
	public Date getFechaContableDesde() {
		return fechaContableDesde;
	}
	public String getStrFechaContableDesde() {
		return renderer.formatDate(fechaContableDesde); 
	}
	public void setFechaContableDesde(Date fechaContableDesde) {
		this.fechaContableDesde = fechaContableDesde;
	}
	public String getDbFechaContableDesde() {
		return renderer.formatDateForDb(fechaContableDesde);
	}
	public String getDbFechaContableHasta() {
		return renderer.formatDateForDb(fechaContableHasta);
	}
	public Date getFechaContableHasta() {
		return fechaContableHasta;
	}
	public String getStrFechaContableHasta() {
		return renderer.formatDate(fechaContableHasta); 
	}
	public void setFechaContableHasta(Date fechaContableHasta) {
		this.fechaContableHasta = fechaContableHasta;
	}
	public Date getFechaPagoDesde() {
		return fechaPagoDesde;
	}
	public String getStrFechaPagoDesde() {
		return renderer.formatDate(fechaPagoDesde);
	}
	public String getDbFechaPagoDesde() {
		return renderer.formatDateForDb(fechaPagoDesde);
	}
	public String getDbFechaPagoHasta() {
		return renderer.formatDateForDb(fechaPagoHasta);
	}
	public void setFechaPagoDesde(Date fechaPagoDesde) {
		this.fechaPagoDesde = fechaPagoDesde;
	}
	public Date getFechaPagoHasta() {
		return fechaPagoHasta;
	}
	public String getStrFechaPagoHasta() {
		return renderer.formatDate(fechaPagoHasta);
	}
	public void setFechaPagoHasta(Date fechaPagoHasta) {
		this.fechaPagoHasta = fechaPagoHasta;
	}
	public BigDecimal getNumeroFolio() {
		return numeroFolio;
	}
	public void setNumeroFolio(BigDecimal numeroFolio) {
		this.numeroFolio = numeroFolio;
	}
	public BigDecimal getNumeroPago() {
		return numeroPago;
	}
	public void setNumeroPago(BigDecimal numeroPago) {
		this.numeroPago = numeroPago;
	}
	public BigDecimal getSistema() {
		return sistema;
	}
	public void setSistema(BigDecimal sistema) {
		this.sistema = sistema;
	}
	public String getTipoFecha() {
		return tipoFecha;
	}
	public void setTipoFecha(String tipoFecha) {
		this.tipoFecha = tipoFecha;
	}
	
	public String toString() {
		return new StringBuffer("[FILTRO::numeroPago=").append(numeroPago)
			.append(",numeroFolio=").append(numeroFolio)
			.append(",tipoFecha=").append(tipoFecha)
			.append(",fechaPagoDesde=").append(fechaPagoDesde)
			.append(",fechaPagoHasta=").append(fechaPagoHasta)
			.append(",fechaContableDesde=").append(fechaContableDesde)
			.append(",fechaContableHasta=").append(fechaContableHasta)
			.append(",estado=").append(estado)
			.append(",banco=").append(banco)
			.append(",sistema=").append(sistema)
			.append(",limit=").append(limit)
			.append(",offset=").append(offset)
			.append("]").toString();
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public long getOffset() {
		return offset;
	}
	public void setOffset(long offset) {
		this.offset = offset;
	}
	public String getTrxConsiderar() {
		return trxConsiderar;
	}
	public void setTrxConsiderar(String trxConsiderar) {
		this.trxConsiderar = trxConsiderar;
	}

}

