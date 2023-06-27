package cl.araucana.spl.beans;

import java.math.BigDecimal;
import java.util.Date;

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
 *            <TD> 17-01-2014 </TD>
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

public class DetalleTrxBBV{
	
	private BigDecimal id;
	private TransaccionBbv transaccionBbv;
	private BigDecimal secuencia;
	private BigDecimal numeroCliente;
	private BigDecimal numeroPDocumento;
	private Date fechaVencimiento;
	private boolean estado;
	private BigDecimal monto;
	
	public BigDecimal getMonto() {
		return monto;
	}


	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}


	public BigDecimal getId() {
		return id;
	}


	public void setId(BigDecimal id) {
		this.id = id;
	}


	public boolean isEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}


	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}


	public BigDecimal getNumeroCliente() {
		return numeroCliente;
	}


	public void setNumeroCliente(BigDecimal numeroCliente) {
		this.numeroCliente = numeroCliente;
	}


	public BigDecimal getNumeroPDocumento() {
		return numeroPDocumento;
	}


	public void setNumeroPDocumento(BigDecimal numeroPDocumento) {
		this.numeroPDocumento = numeroPDocumento;
	}


	public BigDecimal getSecuencia() {
		return secuencia;
	}


	public void setSecuencia(BigDecimal secuencia) {
		this.secuencia = secuencia;
	}


	protected String getNombreObjeto() {
		// TODO Apéndice de método generado automáticamente
		return null;
	}


	public TransaccionBbv getTransaccionBbv() {
		return transaccionBbv;
	}


	public void setTransaccionBbv(TransaccionBbv transaccionBbv) {
		this.transaccionBbv = transaccionBbv;
	}
	
}
