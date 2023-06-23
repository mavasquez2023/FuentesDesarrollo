package cl.araucana.spl.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
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
import cl.araucana.spl.beans.DetalleTrxBit;
import cl.araucana.spl.beans.TransaccionBit;

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
public class XmlHelperBit {
	protected static Logger log = Logger.getLogger(XmlHelperBit.class);
	
	public static String formatMensajeInicial(TransaccionBit trx) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
		Renderer render = new Renderer();

		Document document = XmlHelperBit.createNewDocument();

		Element root = (Element) document.createElement("MPINI"); 
		document.appendChild(root);
		
		root.appendChild(XmlHelperBit.createSimpleTag("IDCOM", trx.getIdContrato(), document));
		root.appendChild(XmlHelperBit.createSimpleTag("IDTRX", trx.getCodigoIdTrx(), document)) ;
		root.appendChild(XmlHelperBit.createSimpleTag("TOTAL", render.formatInt(trx.getTotal().longValue()), document)) ;
		root.appendChild(XmlHelperBit.createSimpleTag("NROPAGOS", render.formatInt(1), document)) ;// Marcos vasques siempres es 1 pago

		if(trx.getDetalleTrxBit().size() > 0){
			DetalleTrxBit detalleTrxBit = (DetalleTrxBit)trx.getDetalleTrxBit().get(0);
		
			Element detalle = document.createElement("DETALLE");
			detalle.appendChild(XmlHelperBit.createSimpleTag("SRVREC", detalleTrxBit.getServRecaudacion(), document));
			detalle.appendChild(XmlHelperBit.createSimpleTag("MONTO", render.formatInt(trx.getTotal().longValue()), document)) ;
			detalle.appendChild(XmlHelperBit.createSimpleTag("GLOSA", detalleTrxBit.getGlosa(), document)) ;
			detalle.appendChild(XmlHelperBit.createSimpleTag("CANTIDAD", detalleTrxBit.getCantidad(), document)) ;
			detalle.appendChild(XmlHelperBit.createSimpleTag("PRECIO", render.formatInt(trx.getTotal().longValue()), document)) ;
			detalle.appendChild(XmlHelperBit.createSimpleTag("DATOADIC", detalleTrxBit.getDatosAdicionales(), document)) ;
			
			root.appendChild(detalle);
		}
		
		
		//gmalle 11-09-2014 Se comenta el codigo ya que el banco espera un detalle del pago no el detalle de cada comprobante..
		
		/*for(Iterator it = trx.getDetalleTrxBit().iterator() ; it.hasNext();){
					DetalleTrxBit detalleTrxBit = (DetalleTrxBit)it.next();
		
					Element detalle = document.createElement("DETALLE");
					detalle.appendChild(XmlHelperBit.createSimpleTag("SRVREC", detalleTrxBit.getServRecaudacion(), document));
					detalle.appendChild(XmlHelperBit.createSimpleTag("MONTO", render.formatInt(detalleTrxBit.getMonto()), document)) ;
					detalle.appendChild(XmlHelperBit.createSimpleTag("GLOSA", detalleTrxBit.getGlosa(), document)) ;
					detalle.appendChild(XmlHelperBit.createSimpleTag("CANTIDAD", detalleTrxBit.getCantidad(), document)) ;
					detalle.appendChild(XmlHelperBit.createSimpleTag("PRECIO", detalleTrxBit.getPrecio(), document)) ;
					detalle.appendChild(XmlHelperBit.createSimpleTag("DATOADIC", detalleTrxBit.getDatosAdicionales(), document)) ;
					
					root.appendChild(detalle);
		}*/
		
		return XmlHelperBit.toString(document);
	}
	
	public static TransaccionBit parseMensajeNotificacion(String msg, TransaccionBit trx) throws ParserConfigurationException, TransformerConfigurationException, 
		TransformerException, ParseException, UnsupportedEncodingException {
		return parseMessage(msg, trx);
	}

	public static TransaccionBit parseMensajeFinal(String msg, TransaccionBit trx) throws ParserConfigurationException, TransformerConfigurationException, 
		TransformerException, ParseException, UnsupportedEncodingException {
		return parseMessage(msg, trx);
	}

	public static TransaccionBit parseRespuestaConfirmacion(String msg, TransaccionBit trx) throws ParserConfigurationException, TransformerConfigurationException, 
		TransformerException, ParseException, UnsupportedEncodingException {
		return parseMessage(msg, trx);
	}
	
	
	 // Este método sólo se utiliza para pruebas.
	 
	public static TransaccionBit parseMensajeConfirmacion(String msg, TransaccionBit trx) throws ParserConfigurationException, TransformerConfigurationException, 
		TransformerException, ParseException, UnsupportedEncodingException {
		return parseMessage(msg, trx);
	}
	
	
	
	public static String getTextByTagName(String tagName, Document doc) {
		String text = null;
		NodeList nl = doc.getElementsByTagName(tagName);
		if (nl.item(0) != null)
			text = XmlHelperBit.getNodeText(nl.item(0));
		return text;
	}
	
	private static TransaccionBit parseMessage(String msg, TransaccionBit trx) throws ParserConfigurationException, TransformerConfigurationException, 
	TransformerException, ParseException, UnsupportedEncodingException {
		Document document = XmlHelperBit.createNewDocument();
				
		XmlHelperBit.readFromString(msg, document);
		
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


	
	public static String toString(Node node) throws TransformerConfigurationException, TransformerException {
		StringWriter output = new StringWriter();
		Transformer t = TransformerFactory.newInstance().newTransformer();
		t.setOutputProperty(OutputKeys.ENCODING, Constants.CHARSET_XML);
		t.setOutputProperty(OutputKeys.METHOD, "xml"); //xml, html, text
		t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");		
		t.transform(new DOMSource(node), new StreamResult(output));
		
		return output.toString();
	}
	
	private static Element createSimpleTag(String tagName, String tagValue, Document doc) {
		Element elem = doc.createElement(tagName);
		elem.appendChild(doc.createTextNode(tagValue));
		return elem;
	}
	
	private static Document createNewDocument() throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.newDocument();
	}
	
	

}
