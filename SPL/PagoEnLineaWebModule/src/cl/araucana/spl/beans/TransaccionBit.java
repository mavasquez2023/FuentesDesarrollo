package cl.araucana.spl.beans;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

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
 *            <TD align="center"> <B>Versi�n</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripci�n</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 13-01-2014 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Gonzalo Mallea Lorca <BR> gmallea@schema.cl </TD>
 *            <TD> Versi�n inicial. </TD>
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
public class TransaccionBit extends TransaccionEft {

	
	private Date fechaTransaccion;
	private Date fechaContable;
	
	private List detalleTrxBit;
	
	//Constructor
	public TransaccionBit() {
		super();
	}
	
	protected String getNombreObjeto() {
		return Utiles.getNombreClase(TransaccionBit.class.getName());
	}	
	
	public String toString() {
		return new StringBuffer("[" + getNombreObjeto() + "::id=").append(getId())
			.append(",pago=").append(getPago())
			.append(",codRetorno=").append(getCodRetorno())
			.append(",desRetorno=").append(getDescRetorno())
			.append(",idTransaccion=").append(getCodigoIdTrx())
			.append(",fechaTransaccion=").append(fechaTransaccion)
			.append(",fechaContable=").append(fechaContable)			
			.append("]").toString();
	}

	public void readFrom(Document document) throws ParseException, UnsupportedEncodingException {
		Renderer render = new Renderer();
		setCodRetorno(render.parseBigDecimal(XmlHelper.getTextByTagName("CODRET", document)));
		setDescRetorno(XmlHelper.getTextByTagName("DESCRET", document));
		setIdContrato(XmlHelper.getTextByTagName("IDCOM", document));
		setCodigoIdTrx(XmlHelper.getTextByTagName("IDTRX", document));
		setTotal(render.parseBigDecimal(XmlHelper.getTextByTagName("TOTAL", document)));
		setNroPagos(render.parseBigDecimal(XmlHelper.getTextByTagName("NROPAGOS", document)));
		setFechaTransaccion(render.parseMsgDatetime(XmlHelper.getTextByTagName("FECHATRX", document)));
		setFechaContable(render.parseMsgDate(XmlHelper.getTextByTagName("FECHACONT", document)));
		
		String numComprobate = (XmlHelper.getTextByTagName("NUMCOMP", document)) != null ? XmlHelper.getTextByTagName("NUMCOMP", document) : "0" ;
		setNroComprobante(render.parseBigDecimal(numComprobate));
			
	}
	public List getDetalleTrxBit() {
		return detalleTrxBit;
	}

	public void setDetalleTrxBit(List detalleTrxBit) {
		this.detalleTrxBit = detalleTrxBit;
	}

	public Date getFechaContable() {
		return fechaContable;
	}

	public void setFechaContable(Date fechaContable) {
		this.fechaContable = fechaContable;
	}

	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}	
	
			
}
