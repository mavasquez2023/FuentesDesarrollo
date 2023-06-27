package cl.araucana.spl.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;

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
import cl.araucana.spl.beans.DetalleTrxEft;
import cl.araucana.spl.beans.TransaccionEft;

public class XmlHelper {
	protected static Logger log = Logger.getLogger(XmlHelper.class);
	
	public static String formatMensajeInicial(TransaccionEft trx) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
		Renderer render = new Renderer();

		Document document = XmlHelper.createNewDocument();

		Element root = (Element) document.createElement("MPINI"); 
		document.appendChild(root);

		root.appendChild(XmlHelper.createSimpleTag("IDCOM", render.formatInt(new Long(trx.getIdContrato()).longValue()), document));
		root.appendChild(XmlHelper.createSimpleTag("IDTRX", trx.getCodigoIdTrx(), document));
		root.appendChild(XmlHelper.createSimpleTag("TOTAL", render.formatInt(trx.getTotal()), document));
		root.appendChild(XmlHelper.createSimpleTag("NROPAGOS", render.formatInt(trx.getNroPagos()), document));
			
		List detalles = trx.getDetalles();
		Element detalleElem = document.createElement("DETALLE");
		for (int i = 0; i < detalles.size(); i++) {
			DetalleTrxEft detalle = (DetalleTrxEft)detalles.get(i);
			if (Constants.MEDIO_CODIGO_BCH.equals(trx.getCodigoBanco()))
				detalleElem.appendChild(XmlHelper.createSimpleTag("SRVREC", render.formatInt5(detalle.getServRecaudacion()), document)) ;
			else if (Constants.MEDIO_CODIGO_BSA.equals(trx.getCodigoBanco()))
				detalleElem.appendChild(XmlHelper.createSimpleTag("SRVREC", render.formatInt6(detalle.getServRecaudacion()), document)) ;
			detalleElem.appendChild(XmlHelper.createSimpleTag("MONTO", render.formatInt(detalle.getMonto()), document));
			detalleElem.appendChild(XmlHelper.createSimpleTag("GLOSA", trx.getPago().getGlosa(), document)) ;
			detalleElem.appendChild(XmlHelper.createSimpleTag("CANTIDAD", detalle.getCantidad(), document)) ;
			detalleElem.appendChild(XmlHelper.createSimpleTag("PRECIO", detalle.getPrecio(), document)) ;
			detalleElem.appendChild(XmlHelper.createSimpleTag("DATOADIC", detalle.getDatosAdicionales(), document)) ;
		}
		root.appendChild(detalleElem);

		return XmlHelper.toString(document);
	}

	public static String formatRespuestaNotificacion_old(boolean ok) throws ParserConfigurationException, TransformerException, TransformerConfigurationException {
		Document document = XmlHelper.createNewDocument();
		Element root = (Element) document.createElement("NOTIFICA");

		if (ok)
			root.setNodeValue(Constants.NOTIFICACION_OK);
		else
			root.setNodeValue(Constants.NOTIFICACION_NOK);

		document.appendChild(root);
		return XmlHelper.toString(document);
	}
	
	public static String formatRespuestaNotificacion(boolean ok) {
		StringBuffer sb = new StringBuffer();
		sb.append("<NOTIFICA>");
		if (ok)
			sb.append(Constants.NOTIFICACION_OK);
		else
			sb.append(Constants.NOTIFICACION_NOK);	
		sb.append("</NOTIFICA>");
		return sb.toString();
	}	
	
	public static String formatMensajeConfirmacion(TransaccionEft trx) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
		Renderer render = new Renderer();

		Document document = XmlHelper.createNewDocument();

		Element root = (Element) document.createElement("MPCON"); 
		document.appendChild(root);

		root.appendChild(XmlHelper.createSimpleTag("IDCOM", render.formatInt(new Long(trx.getIdContrato()).longValue()), document));
		root.appendChild(XmlHelper.createSimpleTag("IDTRX", trx.getCodigoIdTrx(), document));
		root.appendChild(XmlHelper.createSimpleTag("TOTAL", render.formatInt(trx.getTotal()), document));
		root.appendChild(XmlHelper.createSimpleTag("IDREG", render.formatInt(trx.getIdRegistro()), document));
			
		return XmlHelper.toString(document);
	}

	/*
	 * Este método sólo se utiliza para pruebas.
	 */
	public static String formatRespuestaConfirmacion(TransaccionEft trx) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
		Renderer render = new Renderer();

		Document document = XmlHelper.createNewDocument();

		Element root = (Element) document.createElement("MPRES"); 
		document.appendChild(root);

		root.appendChild(XmlHelper.createSimpleTag("CODRET", render.formatInt(trx.getCodRetorno()), document));
		root.appendChild(XmlHelper.createSimpleTag("DESCRET", trx.getDescRetorno(), document));
		root.appendChild(XmlHelper.createSimpleTag("IDCOM", render.formatInt(new Long(trx.getIdContrato()).longValue()), document));
		root.appendChild(XmlHelper.createSimpleTag("IDTRX", trx.getCodigoIdTrx().toString(), document));
		root.appendChild(XmlHelper.createSimpleTag("TOTAL", render.formatInt(trx.getTotal()), document));
		root.appendChild(XmlHelper.createSimpleTag("NROPAGOS", render.formatInt(trx.getNroPagos()), document));
		root.appendChild(XmlHelper.createSimpleTag("FECHATRX", render.formatDatetimeMsg(trx.getPago().getFechaTransaccion()), document));
		root.appendChild(XmlHelper.createSimpleTag("FECHACONT", render.formatDateMsg(trx.getPago().getFechaContable()), document));
		root.appendChild(XmlHelper.createSimpleTag("NUMCOMP", render.formatInt(trx.getNroComprobante()), document));
		root.appendChild(XmlHelper.createSimpleTag("IDREG", render.formatInt(trx.getIdRegistro()), document));
			
		return XmlHelper.toString(document);
	}
	
	public static TransaccionEft parseMensajeNotificacion(String msg, TransaccionEft trx) throws ParserConfigurationException, TransformerConfigurationException, 
		TransformerException, ParseException, UnsupportedEncodingException {
		return parseMessage(msg, trx);
	}

	public static TransaccionEft parseMensajeFinal(String msg, TransaccionEft trx) throws ParserConfigurationException, TransformerConfigurationException, 
		TransformerException, ParseException, UnsupportedEncodingException {
		return parseMessage(msg, trx);
	}

	public static TransaccionEft parseRespuestaConfirmacion(String msg, TransaccionEft trx) throws ParserConfigurationException, TransformerConfigurationException, 
		TransformerException, ParseException, UnsupportedEncodingException {
		return parseMessage(msg, trx);
	}
	
	/*
	 * Este método sólo se utiliza para pruebas.
	 */
	public static TransaccionEft parseMensajeConfirmacion(String msg, TransaccionEft trx) throws ParserConfigurationException, TransformerConfigurationException, 
		TransformerException, ParseException, UnsupportedEncodingException {
		return parseMessage(msg, trx);
	}
	
	public static String toString(Node node) throws TransformerConfigurationException, TransformerException {
		StringWriter output = new StringWriter();
		Transformer t = TransformerFactory.newInstance().newTransformer();
		t.setOutputProperty(OutputKeys.ENCODING, Constants.CHARSET_XML);
		t.setOutputProperty(OutputKeys.METHOD, "xml"); //xml, html, text
		t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		//t.setOutputProperty(OutputKeys.INDENT, "yes");
		//t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		
		t.transform(new DOMSource(node), new StreamResult(output));
		
		return output.toString();
	}
	
	public static String getTextByTagName(String tagName, Document doc) {
		String text = null;
		NodeList nl = doc.getElementsByTagName(tagName);
		if (nl.item(0) != null)
			text = XmlHelper.getNodeText(nl.item(0));
		return text;
	}
	
	private static TransaccionEft parseMessage(String msg, TransaccionEft trx) throws ParserConfigurationException, TransformerConfigurationException, 
	TransformerException, ParseException, UnsupportedEncodingException {
		Document document = XmlHelper.createNewDocument();
				
		XmlHelper.readFromString(msg, document);
		
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
