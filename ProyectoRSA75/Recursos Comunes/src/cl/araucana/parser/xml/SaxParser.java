/**
 * 
 */
package cl.araucana.parser.xml;

/**
 * @author usist24
 *
 */
import java.io.ByteArrayInputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
 
public class SaxParser {
 
   public static void parsearDocumento(String pathxml) {
 
    try {
 
	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser = factory.newSAXParser();
 
	DefaultHandler handler = new DefaultHandler() {
 
	boolean bfname = false;
	boolean blname = false;
	boolean bnname = false;
	boolean bsalary = false;
 
	public void startElement(String uri, String localName,String qName, 
                Attributes attributes) throws SAXException {
 
		System.out.println("Start Element :" + qName);
 
		if (qName.equalsIgnoreCase("FIRSTNAME")) {
			bfname = true;
		}
 
		if (qName.equalsIgnoreCase("LASTNAME")) {
			blname = true;
		}
 
		if (qName.equalsIgnoreCase("NICKNAME")) {
			bnname = true;
		}
 
		if (qName.equalsIgnoreCase("SALARY")) {
			bsalary = true;
		}
 
	}
 
	public void endElement(String uri, String localName,
		String qName) throws SAXException {
 
		System.out.println("End Element :" + qName);
 
	}
 
	public void characters(char ch[], int start, int length) throws SAXException {
		
		System.out.println("Value : " + new String(ch, start, length));
		
		if (bfname) {
			System.out.println("First Name : " + new String(ch, start, length));
			bfname = false;
		}
 
		if (blname) {
			System.out.println("Last Name : " + new String(ch, start, length));
			blname = false;
		}
 
		if (bnname) {
			System.out.println("Nick Name : " + new String(ch, start, length));
			bnname = false;
		}
 
		if (bsalary) {
			System.out.println("Salary : " + new String(ch, start, length));
			bsalary = false;
		}
 
	}
 
     };
     //Para leer XMl en encoding UTF-8
     /*File file = new File(pathxml);
     InputStream inputStream= new FileInputStream(file);
     Reader reader = new InputStreamReader(inputStream,"UTF-8");

     InputSource is = new InputSource(reader);
     is.setEncoding("UTF-8");
     */
       saxParser.parse(new ByteArrayInputStream(pathxml.getBytes()), handler);
 
     } catch (Exception e) {
       e.printStackTrace();
     }
 
   }
 
}
