
/*
 * @(#) XmlHelperBes.java    1.0 06-08-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */

package cl.araucana.spl.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.TransaccionBes;

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
 *            <TD> Versión inicial. Clase desarrollada para el manejo de xml que se utilizan en el intercambio
 *            de información con banco estado. 
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
public class XmlHelperBes {
	protected static Logger log = Logger.getLogger(XmlHelperBes.class);
	
	public static String formatMensajeInicial(TransaccionBes trx) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
		Renderer render = new Renderer();

		Document document = XmlHelperBes.createNewDocument();

		Element root = (Element) document.createElement("INICIO"); 
		document.appendChild(root);
		
		Element encabezado = document.createElement("ENCABEZADO");
		encabezado.appendChild(XmlHelperBes.createSimpleTag("ID_SESION", render.formatInt(trx.getPago().getId().longValue()), document));
		encabezado.appendChild(XmlHelperBes.createSimpleTag("RUT_DV_CON", Constants.RUT_ARAUCANA, document)) ;
		encabezado.appendChild(XmlHelperBes.createSimpleTag("CONV_CON", trx.getPago().getConvenio().getCodigo(), document)) ;
		encabezado.appendChild(XmlHelperBes.createSimpleTag("SERVICIO", Constants.GLOSA_SERVICIO_BES, document)) ;
		encabezado.appendChild(XmlHelperBes.createSimpleTag("RUT_DV_CLIENTE", trx.getRutCliente(), document)) ;
		encabezado.appendChild(XmlHelperBes.createSimpleTag("PAG_RET", trx.getPago().getConvenio().getMedio().getUrlRetornoPago() + "?retorno=" + render.formatInt(trx.getId().longValue()), document)) ;
		encabezado.appendChild(XmlHelperBes.createSimpleTag("TIPO_CONF", "nomq", document)) ;
//TODO Asepulveda 28-08-2009 Comentado a petición del Banco 		
//		encabezado.appendChild(XmlHelperBes.createSimpleTag("METODO_REND", "post", document)) ;
		encabezado.appendChild(XmlHelperBes.createSimpleTag("PAG_REND", trx.getPago().getConvenio().getMedio().getUrlNotificacionPago(), document)) ;
		encabezado.appendChild(XmlHelperBes.createSimpleTag("BANCO", render.formatInt4(new BigDecimal(Constants.CODIGO_BANCO_BES)), document)) ;
		encabezado.appendChild(XmlHelperBes.createSimpleTag("CANT_MPAGO", render.formatInt(1), document)) ;
		encabezado.appendChild(XmlHelperBes.createSimpleTag("TOTAL", render.formatInt(trx.getPago().getMontoTotal()), document)) ;
		
		root.appendChild(encabezado);

		Element multiPago = document.createElement("MULTIPAGO");
		multiPago.appendChild(XmlHelperBes.createSimpleTag("GLOSA_MPAGO", trx.getPago().getGlosa(), document));
		multiPago.appendChild(XmlHelperBes.createSimpleTag("ID_MPAGO", render.formatInt(trx.getId().longValue()), document)) ;
		Element pago = document.createElement("PAGO");
		pago.appendChild(XmlHelperBes.createSimpleTag("RUT_DV_EMP", Constants.RUT_ARAUCANA, document));
		pago.appendChild(XmlHelperBes.createSimpleTag("NUM_CONV", trx.getPago().getConvenio().getCodigo(), document)) ;
		pago.appendChild(XmlHelperBes.createSimpleTag("FEC_TRX", render.formatDateMsg(trx.getPago().getFechaTransaccion()), document)) ;
		pago.appendChild(XmlHelperBes.createSimpleTag("HOR_TRX", render.formatHourMsg(trx.getPago().getFechaTransaccion()), document)) ;
//		pago.appendChild(XmlHelperBes.createSimpleTag("FEC_VENC", "", document)) ;
		pago.appendChild(XmlHelperBes.createSimpleTag("FEC_VENC", render.formatDateMsg(trx.getPago().getFechaTransaccion()), document)) ;		
		pago.appendChild(XmlHelperBes.createSimpleTag("GLOSA", trx.getPago().getGlosa(), document)) ;
		pago.appendChild(XmlHelperBes.createSimpleTag("COD_PAGO", render.formatInt(trx.getPago().getId().longValue()), document)) ;
		pago.appendChild(XmlHelperBes.createSimpleTag("MONTO",render.formatInt(trx.getPago().getMontoTotal()), document)) ;		
		multiPago.appendChild(pago);		
		
		root.appendChild(multiPago);
		
		return Constants.XML_VERSION_BES + XmlHelperBes.toString(document);
	}
	
	public static TransaccionBes parseMensajeNotificacion(String msg, TransaccionBes trx) throws ParserConfigurationException, TransformerConfigurationException, 
		TransformerException, ParseException, UnsupportedEncodingException {
		return parseMessage(msg, trx);
	}

	public static TransaccionBes parseMensajeFinal(String msg, TransaccionBes trx) throws ParserConfigurationException, TransformerConfigurationException, 
		TransformerException, ParseException, UnsupportedEncodingException {
		return parseMessage(msg, trx);
	}

	public static TransaccionBes parseRespuestaConfirmacion(String msg, TransaccionBes trx) throws ParserConfigurationException, TransformerConfigurationException, 
		TransformerException, ParseException, UnsupportedEncodingException {
		return parseMessage(msg, trx);
	}
	
	/*
	 * Este método sólo se utiliza para pruebas.
	 */
	public static TransaccionBes parseMensajeConfirmacion(String msg, TransaccionBes trx) throws ParserConfigurationException, TransformerConfigurationException, 
		TransformerException, ParseException, UnsupportedEncodingException {
		return parseMessage(msg, trx);
	}
	
	public static String toString(Node node) throws TransformerConfigurationException, TransformerException {
		StringWriter output = new StringWriter();
		Transformer t = TransformerFactory.newInstance().newTransformer();
		t.setOutputProperty(OutputKeys.ENCODING, Constants.CHARSET_XML);
		t.setOutputProperty(OutputKeys.METHOD, "xml"); //xml, html, text
		t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");		
		t.transform(new DOMSource(node), new StreamResult(output));
		
		return output.toString();
	}
	
	public static String getTextByTagName(String tagName, Document doc) {
		String text = null;
		NodeList nl = doc.getElementsByTagName(tagName);
		if (nl.item(0) != null)
			text = XmlHelperBes.getNodeText(nl.item(0));
		return text;
	}
	
	private static TransaccionBes parseMessage(String msg, TransaccionBes trx) throws ParserConfigurationException, TransformerConfigurationException, 
	TransformerException, ParseException, UnsupportedEncodingException {
		Document document = XmlHelperBes.createNewDocument();
				
		XmlHelperBes.readFromString(msg, document);
		
		trx.readFrom(document);
		return trx;
	}
	
	private static void readFromString(String str, Node node) throws TransformerConfigurationException, TransformerException {
		StringReader input = new StringReader(str);
		Transformer t = TransformerFactory.newInstance().newTransformer();
		t.setOutputProperty(OutputKeys.ENCODING, Constants.CHARSET_XML);
		t.setOutputProperty(OutputKeys.METHOD, "xml"); //xml, html, text
			
		t.transform(new StreamSource(input), new DOMResult(node));
	}
	
	private static Document createNewDocument() throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.newDocument();
	}
	
	private static Element createSimpleTag(String tagName, String tagValue, Document doc) {
		Element elem = doc.createElement(tagName);
		elem.appendChild(doc.createTextNode(tagValue));
		return elem;
	}
	
	private static String getNodeText(Node node) {
		StringBuffer sb = new StringBuffer();
		NodeList nl = node.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node child = nl.item(i);
			if (Node.TEXT_NODE == child.getNodeType()) {
				sb.append(child.getNodeValue().trim());
			}
		}
		return sb.toString();	
	}
	

}
