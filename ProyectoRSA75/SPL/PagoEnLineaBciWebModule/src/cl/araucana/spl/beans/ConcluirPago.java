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

public class ConcluirPago {
	private static Renderer renderer = new Renderer();
	private BigDecimal idPago;
	private String pagador;
	private Date fch_contable;
	private String id_Contrato;
	private BigDecimal cod_idtransaccion;
	private BigDecimal total;
	private BigDecimal nro_pagos;
	private Date fch_inicio;
	private String glosa;
	private String rutCliente;
	
	public String getId_Contrato() {
		return id_Contrato;
	}

	public void setId_Contrato(String id_Contrato) {
		this.id_Contrato = id_Contrato;
	}

	public Date getFch_inicio() {
		return fch_inicio;
	}

	public void setFch_inicio(Date fch_inicio) {
		this.fch_inicio = fch_inicio;
	}

	public String getGlosa() {
		return glosa.trim();
	}

	public void setGlosa(String glosa) {
		this.glosa = glosa.trim();
	}

	public BigDecimal getIdPago() {
		return idPago;
	}

	public void setIdPago(BigDecimal idPago) {
		this.idPago = idPago;
	}

	public BigDecimal getNro_pagos() {
		return nro_pagos;
	}

	public void setNro_pagos(BigDecimal nro_pagos) {
		this.nro_pagos = nro_pagos;
	}

	public String getPagador() {
		return pagador;
	}

	public void setPagador(String pagador) {
		this.pagador = pagador;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public static Renderer getRenderer() {
		return renderer;
	}

	public static void setRenderer(Renderer renderer) {
		ConcluirPago.renderer = renderer;
	}

	public Date getFch_contable() {
		return fch_contable;
	}

	public void setFch_contable(Date fch_contable) {
		this.fch_contable = fch_contable;
	}

	public BigDecimal getCod_idtransaccion() {
		return cod_idtransaccion;
	}

	public void setCod_idtransaccion(BigDecimal cod_idtransaccion) {
		this.cod_idtransaccion = cod_idtransaccion;
	}

	public String getRutCliente() {
		return rutCliente;
	}

	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}
	

}
