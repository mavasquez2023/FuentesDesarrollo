package cl.laaraucana.capaservicios.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class ValidaXMLToXSD {

	public HashMap<String, String> validaXml(String xml, File xsd) throws UnsupportedEncodingException, SAXException {

		HashMap<String, String> resp = new HashMap<String, String>();
		// 1. Se debe definir el lenguaje del esquema, en este caso W3C XML
		// Schema 1.0.
		// esta es otra opcion valida de define el lenguaje del esquema.
		// SchemaFactory factory =
		// SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		// 2. crear el esquema con el archivo file correspondiente al XSD.
		Schema schema = null;
		schema = factory.newSchema(xsd);
		System.out.println("carga xsd");
		// 3. crear el validador para el esquema creado.
		Validator validator = schema.newValidator();
		// 4. se crea el objeto sourse a partir del file correspondiente al XML.
		Source source = new StreamSource(new ByteArrayInputStream(xml.getBytes("UTF-8")));
		System.out.println("carga xml");
		try {
			// 5. se valida el documento.
			validator.validate(source);
			resp.put("valido", "si");
			resp.put("detalle", "El XML es Valido.");
		} catch (SAXException ex) {
			resp.put("valido", "no");
			resp.put("detalle", "EL XML no es valido (" + ex.getMessage() + ")");
			System.out.println("error: " + ex.getMessage());
		} catch (IOException e) {
			resp.put("valido", "no");
			resp.put("detalle", "Error al validar xml");
		}

		return resp;
	}
}
