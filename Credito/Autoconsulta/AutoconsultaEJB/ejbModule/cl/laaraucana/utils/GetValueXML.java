package cl.laaraucana.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class GetValueXML {
	private String file; 
	Document doc;
	NodeList nodes;

	private void initGetValueXML() {
		InputStream conf;
		conf = getClass().getClassLoader().getResourceAsStream(file);
		String sData;
	
	    InputSource is = new InputSource () ;
		
		try {
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		    domFactory.setNamespaceAware(true);
		    DocumentBuilder db = domFactory.newDocumentBuilder() ;
			sData = convertStreamToString(conf);
			is.setCharacterStream ( new StringReader ( sData )) ;
			doc = db.parse( is );
			//doc.normalize();
			
		} catch (SAXException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	
	}
	public GetValueXML() {
		this.file = "cl/laaraucana/config/arau-sql-settings.xml";
	}

	public GetValueXML(String file) {
		this.file = file;
	}

	public String getValue(String key) {
	    return getValue(this.file , key);
	}
	
	public String getValue(String xmlFile, String key) { 
		initGetValueXML();
		StringTokenizer st = new  StringTokenizer(key, "/");
		String token = st.nextToken();
		String value="";
		Element root = (Element)doc.getElementsByTagName(token).item(0);
		String selected="";
		
		while (st.hasMoreTokens()) {
			token = st.nextToken();
			if (!st.hasMoreTokens()) {
				selected = token;
			}
			root = (Element)root.getElementsByTagName(token).item(0);
			if (root!=null) {
				if (root.getNodeName().equals(selected)) {
					value = getCharacterDataFromElement( root ).trim();
				}
			}
		}
	    return value;
	}
	
	private static String getCharacterDataFromElement ( Element e ) {
		     Node child = e.getFirstChild () ;
		     if ( child instanceof CharacterData ) {
		       CharacterData cd = ( CharacterData ) child;
		       return cd.getData () ;
		     }
		     return "" ;
	}

	
	private String convertStreamToString(InputStream is)
	throws IOException {
	if (is != null) {
		Writer writer = new StringWriter();
	 
		char[] buffer = new char[1024];
		try {
			Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			int n;
			while ((n = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, n);
			}
		} finally {
			is.close();
		}
		return writer.toString();
	} else {       
		return "";
	}
	}
}
