package cl.laaraucana.botonpago.web.spl;

import java.io.StringWriter;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.commons.codec.binary.Hex;

import cl.laaraucana.botonpago.web.spl.vo.Pago;
import cl.laaraucana.botonpago.web.utils.Constantes;
import cl.laaraucana.botonpago.web.utils.EncriptUtils;

public class GeneraXMLEncriptado {

	public static HashMap<String, String> generarXML(Pago p) throws Exception {

		Pago pagos = p;

		String xmlHex = null;
		String vectorHex = null;

		// crea instancia de la clase Pago
		JAXBContext jaxbContext = JAXBContext.newInstance(Pago.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		// setea Propiedades del XML a Generar 
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);//xml formateado
		jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");//codificacion del xml

		// obtener el xml en String
		StringWriter writer = new StringWriter();
		jaxbMarshaller.marshal(pagos, writer);
		String theXML = writer.toString();

		HashMap<String, byte[]> map = EncriptUtils.encode(theXML.getBytes(), Constantes.getInstancia().KEY_ENCODE);

		xmlHex = String.valueOf(Hex.encodeHex(map.get("encriptado")));
		vectorHex = String.valueOf(Hex.encodeHex(map.get("vector")));

		HashMap<String, String> resp = new HashMap<String, String>();
		resp.put("xml", xmlHex);
		resp.put("vector", vectorHex);

		return resp;

	}

}
