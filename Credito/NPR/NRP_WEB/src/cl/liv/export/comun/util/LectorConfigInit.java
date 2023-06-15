package cl.liv.export.comun.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cl.liv.export.comun.util.file.Util;

public class LectorConfigInit {
	
	
	public static ArrayList<String> getDataXML(){
		ArrayList<String> data = new ArrayList<String>();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder docBuilder;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
			String fileConf = PropertiesComunUtil.getProperty("export.path.resources")+"comun/on_init.xml";
			Document doc = docBuilder.parse (fileConf);

			doc.getDocumentElement ().normalize ();
			
			
			NodeList resources = doc.getElementsByTagName("comando");
			for (int i = 0; i < resources.getLength(); i++) {
				Element resource = (Element) resources.item(i);
				if (resource.getNodeType() == Node.ELEMENT_NODE){
					data.add( resource.getAttribute("data"));
				}
				
			}
			
			return data;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
		
	public static String getTagValue(String tag, Element elemento) {

		NodeList lista = elemento.getElementsByTagName(tag).item(0).getChildNodes();

		Node valor = (Node) lista.item(0);

		return valor.getNodeValue();

		}


}
