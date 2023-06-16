package cl.laaraucana.simat.utiles;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
 * clase que pemrite manipular los tag de los archivos xml
 * */

public class Utilxml {

	public Document ReadSchema(String Ruta, String NameFile) throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(Ruta + NameFile));
		return doc;
	}

	public int cuentaTags(String nameTag, Document doc) {
		int total = 0;
		NodeList listaPersonas = doc.getElementsByTagName(nameTag);
		total = listaPersonas.getLength();
		return total;
	}

	public NodeList getAllElementByTag(String nameTag, Document doc) {
		NodeList lista = null;
		lista = doc.getElementsByTagName(nameTag);
		return lista;
	}

	public String getTagValue(String tag, Element elemento) {
		NodeList lista = elemento.getElementsByTagName(tag).item(0).getChildNodes();
		Node valor = (Node) lista.item(0);
		return valor.getNodeValue();
	}

	public String getOpenTag(String tagname) {
		return "\n" + "<" + tagname + ">";
	}

	public String getValueTag(String tagname, String dato) {
		return "\n" + "\t" + "<" + tagname + ">" + dato + "</" + tagname + ">";
	}

	public String getValueTag(String tagname, long dato) {
		return "\n" + "\t" + "<" + tagname + ">" + dato + "</" + tagname + ">";
	}

	public String getCloseTag(String tagname) {
		return "\n" + "</" + tagname + ">";
	}

	public String getShortTag(String tagname) {
		return "\n" + "<" + tagname + "/>";
	}

}//fin class
