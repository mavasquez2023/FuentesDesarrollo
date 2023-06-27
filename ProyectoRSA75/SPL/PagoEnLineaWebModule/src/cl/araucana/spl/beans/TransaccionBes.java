
/*
 * @(#) TransaccionBes.java    1.0 06-08-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */

package cl.araucana.spl.beans;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import org.w3c.dom.Document;

import cl.araucana.spl.util.Renderer;
import cl.araucana.spl.util.Utiles;
import cl.araucana.spl.util.XmlHelper;

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
 *            <TD> 06-08-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Alejandro Sepúlveda Page <BR> asepulveda@schema.cl </TD>
 *            <TD> Versión inicial. Contiene la información específica del banco estado para la recepción de la respuesta a un pago en línea
 *            </TD>
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
 * @author Alejandro Sepúlveda Page (asepulveda@schema.cl)
 *
 * @version 1.0
 */

public class TransaccionBes implements Transaccion {

	private BigDecimal id;
	private Pago pago;
	private String resultado;
	private String glosaError;
	private String codTrx;
	private Date fechaPago;
	private Date fechaContable;
	private String rutCliente;
	
	//Constructor
	public TransaccionBes() {
		super();
	}
	
	protected String getNombreObjeto() {
		return Utiles.getNombreClase(TransaccionBes.class.getName());
	}	
	
	public String toString() {
		return new StringBuffer("[" + getNombreObjeto() + "::id=").append(id)
			.append(",pago=").append(pago)
			.append(",resultado=").append(resultado)
			.append(",glosaError=").append(glosaError)
			.append(",codTrx=").append(codTrx)
			.append(",fechaPago=").append(fechaPago)
			.append(",fechaContable=").append(fechaContable)			
			.append(",rutCliente=").append(rutCliente)
			.append("]").toString();
	}	
	
	public String getRutCliente() {
		return rutCliente;
	}

	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}

	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Pago getPago() {
		return pago;
	}
	public BigDecimal getIdPago() {
		return pago.getId();
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}
	public String getUrlCgi() {
		return getPago().getUrlCgi();
	}
	public String getCodigoBanco() {
		return getPago().getCodigoBanco();
	}
	
	public void readFrom(Document document) throws ParseException, UnsupportedEncodingException {
		Renderer render = new Renderer();
		Pago pagoAux = new Pago();
		pagoAux.setSistema(new Sistema());
		
		pagoAux.setId(new BigDecimal(XmlHelper.getTextByTagName("ID_SESION", document)));
		setId(new BigDecimal(XmlHelper.getTextByTagName("ID_MPAGO", document)));
		setResultado(XmlHelper.getTextByTagName("RESULT_MPAGO", document));
		setGlosaError(XmlHelper.getTextByTagName("GLOSA_ERR", document));
		setCodTrx(XmlHelper.getTextByTagName("TRX_MPAGO", document));
		setFechaPago(render.parseMsgDatetime(XmlHelper.getTextByTagName("FEC_MPAGO", document) + XmlHelper.getTextByTagName("HORA_MPAGO", document)));
		setFechaContable(render.parseMsgDate(XmlHelper.getTextByTagName("FEC_CNTBL_MPAGO", document)));
		setPago(pagoAux);
		setFechaTransaccionPago(getFechaPago());
		setFechaContablePago(getFechaContable());
	}

	public void setFechaTransaccionPago(Date fecha) {
		getPago().setFechaTransaccion(fecha);
	}
	
	public void setFechaContablePago(Date fecha) {
		getPago().setFechaContable(fecha);
	}

	public void setFechaContable(Date fechaContable) {
		this.fechaContable = fechaContable;
	}

	public String getCodTrx() {
		return codTrx;
	}

	public void setCodTrx(String codTrx) {
		this.codTrx = codTrx;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getGlosaError() {
		return glosaError;
	}

	public void setGlosaError(String glosaError) {
		this.glosaError = glosaError;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Date getFechaContable() {
		return fechaContable;
	}
	
}
