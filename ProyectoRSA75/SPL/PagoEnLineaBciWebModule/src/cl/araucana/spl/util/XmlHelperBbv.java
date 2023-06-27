package cl.araucana.spl.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Iterator;

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
import org.dom4j.DocumentHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.DetalleTrxBBV;
import cl.araucana.spl.beans.TransaccionBbv;
import cl.araucana.spl.exceptions.PagoEnLineaException;

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
public class XmlHelperBbv {
	protected static Logger log = Logger.getLogger(XmlHelperBbv.class);
	
	public static TransaccionBbv parseMensajeNotificacion(String msg, TransaccionBbv trx) throws ParserConfigurationException, TransformerConfigurationException, 
		TransformerException, ParseException, UnsupportedEncodingException {
		return parseMessage(msg, trx);
	}

	public static TransaccionBbv parseMensajeFinal(String msg, TransaccionBbv trx) throws ParserConfigurationException, TransformerConfigurationException, 
		TransformerException, ParseException, UnsupportedEncodingException {
		return parseMessage(msg, trx);
	}

	public static TransaccionBbv parseRespuestaConfirmacion(String msg, TransaccionBbv trx) throws ParserConfigurationException, TransformerConfigurationException, 
		TransformerException, ParseException, UnsupportedEncodingException {
		return parseMessage(msg, trx);
	}
	
	
	 // Este método sólo se utiliza para pruebas.
	 
	public static TransaccionBbv parseMensajeConfirmacion(String msg, TransaccionBbv trx) throws ParserConfigurationException, TransformerConfigurationException, 
		TransformerException, ParseException, UnsupportedEncodingException {
		return parseMessage(msg, trx);
	}
	
	public static String parseMensajeSolicitarAcceso(String msg, TransaccionBbv trx) throws PagoEnLineaException{
		
		String resultado = "";
		String error = "";
		String llave = "";
		
		try {
			org.dom4j.Document document = DocumentHelper.parseText(msg);
			org.dom4j.Element root = document.getRootElement();
			
			resultado = getValueElement(root.element("RESULTADO"));
			error = getValueElement(root.element("ERROR"));
			llave = getValueElement(root.element("LLAVE"));
			
			if(resultado == null || !resultado.trim().equals("000")){
				log.debug("Problema al parsera XML Solicita Acceso :  Resultado = " + resultado + " Error = " + error) ;
				throw new PagoEnLineaException("Problemas en el Web Services: " + msg);
			}
		
		} catch (Exception e) {
			log.debug("Problema al parsera XML Solicita Acceso :  Resultado = " + resultado + " Error = " + error) ;
			throw new PagoEnLineaException("Problemas en el Web Services: " + e, e.getCause());
		}
		return llave;
	}
	
public static String parseMensajeEnviarTransaccion(String msg, TransaccionBbv trx) throws PagoEnLineaException{
		
		String resultado = "";
		String error = "";
		String url = "";
		
		try {
			org.dom4j.Document document = DocumentHelper.parseText(msg);
			org.dom4j.Element root = document.getRootElement();
			
			resultado = getValueElement(root.element("RESULTADO"));
			error = getValueElement(root.element("ERROR"));
			url = getValueElement(root.element("URL"));
			
			if(resultado  == null && !resultado.trim().equals("000")){
				log.debug("Problema al parsera XML Solicita Acceso :  Resultado = " + resultado + " Error = " + error) ;
				throw new PagoEnLineaException("Problemas en el Web Services: " + msg);
			}
		
		} catch (Exception e) {
			log.debug("Problema al parsera XML Enviar Transaccion :  Resultado = " + resultado + " Error = " + error) ;
			throw new PagoEnLineaException("Problemas en el Web Services: " + e, e.getCause());
		}
		return url;
	}
	
	public static String generaXMLPago(TransaccionBbv transaccionBbv) throws ParserConfigurationException, TransformerConfigurationException, TransformerException{
		Renderer render = new Renderer();

		Document document = XmlHelperBbv.createNewDocument();

		Element root = (Element) document.createElement("PAGOS"); 
		document.appendChild(root);
		
		for(Iterator it = transaccionBbv.getDetalles().iterator() ; it.hasNext();){
					DetalleTrxBBV detalleTrxBBV = (DetalleTrxBBV)it.next();
		
					Element detalle = document.createElement("PAGO");
					detalle.appendChild(XmlHelperBbv.createSimpleTag("SECUENCIA", render.formatInt(detalleTrxBBV.getSecuencia().longValue()), document));
					detalle.appendChild(XmlHelperBbv.createSimpleTag("NUMERO_CLIENTE", render.formatInt(detalleTrxBBV.getNumeroCliente().longValue()), document)) ;
					detalle.appendChild(XmlHelperBbv.createSimpleTag("NUMERO_DOCUMENTO", render.formatInt(detalleTrxBBV.getNumeroPDocumento().longValue()), document)) ;
					detalle.appendChild(XmlHelperBbv.createSimpleTag("MONTO_PAGO", render.formatInt(detalleTrxBBV.getMonto()), document)) ;
					detalle.appendChild(XmlHelperBbv.createSimpleTag("FECHA_VENCIMIENTO", render.formatDateMsg(detalleTrxBBV.getFechaVencimiento()), document)) ;
					detalle.appendChild(XmlHelperBbv.createSimpleTag("ESTADO", (detalleTrxBBV.isEstado() == true ? "1" : "0"), document)) ;
					
					root.appendChild(detalle);
		}
		
		return XmlHelperBbv.toString(document);
	}
	
	public static String getTextByTagName(String tagName, Document doc) {
		String text = null;
		NodeList nl = doc.getElementsByTagName(tagName);
		if (nl.item(0) != null)
			text = XmlHelperBbv.getNodeText(nl.item(0));
		return text;
	}
	
	private static TransaccionBbv parseMessage(String msg, TransaccionBbv trx) throws ParserConfigurationException, TransformerConfigurationException, 
	TransformerException, ParseException, UnsupportedEncodingException {
		Document document = XmlHelperBbv.createNewDocument();
				
		XmlHelperBbv.readFromString(msg, document);
		
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
	
	private static String getValueElement(org.dom4j.Element elementXml){
		String retorno = null;
		if (elementXml!=null) {
			retorno = elementXml.getText();
		}
		log.debug("VML valor retorno = "+retorno);
		return retorno;	
	}
	

}
