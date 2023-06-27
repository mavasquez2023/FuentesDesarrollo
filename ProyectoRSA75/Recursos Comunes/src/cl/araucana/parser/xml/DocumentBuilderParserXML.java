/**
 * 
 */
package cl.araucana.parser.xml;

/**
 * @author usist24
 *
 */
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class DocumentBuilderParserXML {
	
	public static void listNodos(NodeList nodes){
		for (int i = 1; i < nodes.getLength(); i++) {
			//System.out.println("" + nodes.item(i).getNodeType());
			NodeList subnodes = nodes.item(i).getChildNodes();
			//leyendo los atributos del nodo
			
			if(nodes.item(i).getAttributes()!=null){
				for (int j = 0; j < nodes.item(i).getAttributes().getLength(); j++) {
					//Se muestra el nombre del atributo
					System.out.println("" + nodes.item(i).getAttributes().item(j).getNodeName());
					//Se muestra el valor del atributo
					System.out.println("" + nodes.item(i).getAttributes().item(j).getNodeValue());
				}
			}
			//Si nodo tiene subnodos se llama recursivamente para leer los subnodos
			if(subnodes.getLength()>1){
				listNodos(subnodes);
			//Se excluye nodos del tipo #text que aparecen por cada nodo
			}else if(nodes.item(i).getNodeType()==Node.ELEMENT_NODE){
				//Se muestra el nombre del nodo
				System.out.println("" + nodes.item(i).getNodeName());
				//Se muestra el valor del nodo
				System.out.println("" + nodes.item(i).getTextContent());
			}
        }
	}
	public static void parsearDocumento(String pathxml)
    {
		System.out.println("XML entrada= " + pathxml);
				
		Calendar ahora1 = Calendar.getInstance();
		long tiempo1 = ahora1.getTimeInMillis();

		DocumentBuilder builder = null;
		Document doc= null;
		

		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			doc=builder.parse(new ByteArrayInputStream(pathxml.getBytes()));
			
			//Get First Nodo
			Node nodo = doc.getFirstChild();
			
			// get all child nodes
			listNodos(nodo.getChildNodes());
			
		} catch (ParserConfigurationException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}

		Calendar ahora2 = Calendar.getInstance();
		long tiempo2 = ahora2.getTimeInMillis();
		long dif= tiempo2-tiempo1;
		System.out.println("Tiempo respuesta: " + (double)dif/1000  + " seg.");
		
		System.out.println("FIN DocumentBuilder Parser");

    }


	
}	

