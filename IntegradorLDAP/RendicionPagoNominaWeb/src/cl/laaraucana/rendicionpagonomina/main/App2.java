package cl.laaraucana.rendicionpagonomina.main;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class App2 {

	public static void main(String[] args) {
		 
		final String xmlStr = "<wtw3:tran>" +
				"<wtw3:respuesta>" +
				"<CodigoRetorno>OK</CodigoRetorno>" +
				"<NumeroNomina>123</NumeroNomina>" +
				"<FechaPagoDesde>18/03/2010</FechaPagoDesde>" +
				"<FechaPagoHasta>19/03/2010</FechaPagoHasta>" +
				"<GlosaError>Glosa</GlosaError>" +
				"<Archivo>Archivo</Archivo>" +
				"</wtw3:respuesta>" +
				"</wtw3:tran>";

				try {
				// Use method to convert XML string content to XML Document object
				Document doc = convertStringToXMLDocument(xmlStr);

				// Verify XML document is build correctly
				NodeList nList = doc.getElementsByTagName("wtw3:respuesta");
				
				for (int temp = 0; temp < nList.getLength(); temp++) {

					Node nNode = nList.item(temp);
							
					System.out.println("\nCurrent Element :" + nNode.getNodeName());
							
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;

						System.out.println("CodigoRetorno : " + eElement.getElementsByTagName("CodigoRetorno").item(0).getTextContent());
						System.out.println("NumeroNomina : " + eElement.getElementsByTagName("NumeroNomina").item(0).getTextContent());
						System.out.println("FechaPagoDesde : " + eElement.getElementsByTagName("FechaPagoDesde").item(0).getTextContent());
						System.out.println("FechaPagoHasta : " + eElement.getElementsByTagName("FechaPagoHasta").item(0).getTextContent());
						System.out.println("GlosaError : " + eElement.getElementsByTagName("GlosaError").item(0).getTextContent());
						System.out.println("Archivo : " + eElement.getElementsByTagName("Archivo").item(0).getTextContent());

					}
				}
			    } catch (Exception e) {
				e.printStackTrace();
			    }

	}
	
	private static Document convertStringToXMLDocument(String xmlString) {
		// Parser that produces DOM object trees from XML content
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// API to obtain DOM Document instance
		DocumentBuilder builder = null;
		try {
			// Create DocumentBuilder with default configuration
			builder = factory.newDocumentBuilder();

			// Parse the content to Document object
			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;

	}

}
