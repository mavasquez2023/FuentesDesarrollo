package cl.araucana.spl.beans;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

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
public class TransaccionBbv extends TransaccionEft {
	
	private String url;
	
	
	//Constructor
	public TransaccionBbv() {
		super();
	}
	
	protected String getNombreObjeto() {
		return Utiles.getNombreClase(TransaccionBbv.class.getName());
	}	
	
	public String toString() {
		return new StringBuffer("[" + getNombreObjeto() + "::id=").append(getId())
			.append(",pago=").append(getPago())
			.append(",codRetorno=").append(getCodRetorno())
			.append(",desRetorno=").append(getDescRetorno())
			.append(",idTransaccion=").append(getCodigoIdTrx())
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
		setNroComprobante(render.parseBigDecimal(XmlHelper.getTextByTagName("NUMCOMP", document)));
		
		setId(render.parseBigDecimal(XmlHelper.getTextByTagName("NUMCOMP", document)));
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
