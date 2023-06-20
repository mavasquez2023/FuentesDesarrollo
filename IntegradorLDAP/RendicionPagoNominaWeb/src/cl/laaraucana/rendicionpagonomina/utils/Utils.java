package cl.laaraucana.rendicionpagonomina.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import cl.laaraucana.rendicionpagonomina.cliente.W2W_IIISoap12Stub;
import cl.laaraucana.rendicionpagonomina.cliente.W2W_IIISoapStub;
import cl.laaraucana.rendicionpagonomina.vo.AbstractEntradaVO;
import cl.laaraucana.rendicionpagonomina.vo.AbstractSalidaVO;
import cl.laaraucana.rendicionpagonomina.vo.ConstantesRespuestasWS;
import cl.laaraucana.rendicionpagonomina.vo.EntradaWSBES;
import cl.laaraucana.rendicionpagonomina.vo.EnvioNominaRespuestaVo;
import cl.laaraucana.rendicionpagonomina.vo.EnvioNominaVo;
import cl.laaraucana.rendicionpagonomina.vo.RespuestaBanco;
import cl.laaraucana.rendicionpagonomina.vo.RespuestaServicioVo;
import cl.laaraucana.rendicionpagonomina.vo.SalidaWSBES;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Utils {

	private static final Logger logger = Logger.getLogger(Utils.class);
	private static DateFormat formatoWeb = new SimpleDateFormat("dd-MM-yyyy");
	private static DateFormat formatoAS = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat formatoInt = new SimpleDateFormat("yyyyMMdd");
	private static DateFormat formatoBES = new SimpleDateFormat("dd/MM/yyyy");
	private static DecimalFormat formatoMiles = new DecimalFormat("#,###.##");
	private static DateFormat formatHora_sf = new SimpleDateFormat("HHmmss");
	
	
	/**
	 * Devuelve fecha actual en formatoAs400 "yyyyMMdd"
	 * */
	public static String getFechaAAAAMMDD() {
		return formatoInt.format(new Date());
	}
	
	public static String getTimeHHmmss() {
		return formatHora_sf.format(new Date());
	}
	
	public static String getFechaAS() {
		return formatoAS.format(new Date());
	}
	
	public static String getfechaWeb() {
		return formatoWeb.format(new Date());
	}
	
	public static String formatoMiles(double importe) {
		String valorFormateado = formatoMiles.format(importe);
		return valorFormateado;
	}
	
	public static Date stringIntToDate(String fecha) throws ParseException {
		return formatoInt.parse(fecha);
	}
	
	public static Date stringToDate(String fecha) throws ParseException {
		return formatoWeb.parse(fecha);
	}
	
	public static Date stringToDateBES(String fecha) throws ParseException {
		return formatoBES.parse(fecha);
	}
	
	public static String dateToString(Date fecha) {
		return formatoWeb.format(fecha);
	}
	
	public static String dateToStringBES(Date fecha) {
		return formatoBES.format(fecha);
	}
	
	public static Date sumDays(Date fecha, int sum) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.DATE, sum);
		return cal.getTime();
	}
	
	@SuppressWarnings("resource")
	public static void descargar(String path, String filename, byte[] bytes) throws IOException, FileNotFoundException {
		// Initialize a pointer
		// in file using OutputStream
		OutputStream os;

		os = new FileOutputStream(path + "\\" + filename);

		// Starts writing the bytes in it
		os.write(bytes);
		os.flush();
		os.close();
	}
	
	public static AbstractSalidaVO call(AbstractEntradaVO entradaEncriptada) throws Exception {
		logger.info("Inicio Web Service: Banco estado");
		String ep = Configuraciones.getConfig("ep.BancoEstado");
		
		logger.info("Estableciendo conexión a Cliente Banco estado, endpoint:" + ep);
		W2W_IIISoapStub stub = new W2W_IIISoapStub();
		stub._setProperty(W2W_IIISoap12Stub.ENDPOINT_ADDRESS_PROPERTY, ep);

		EntradaWSBES entradaVO = (EntradaWSBES) entradaEncriptada;
		SalidaWSBES salidaVO = new SalidaWSBES();
		try {
			logger.info("Mensaje encriptado a enviar: " + entradaVO.getMensaje());
			String respuestaEncriptada = stub.WS_W2WEntrada(entradaVO.getMensaje());
			salidaVO.setMensaje(respuestaEncriptada);
			logger.info("Respuesta webServices:" + respuestaEncriptada);
			if(respuestaEncriptada.indexOf("Error")>-1){
				salidaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
				salidaVO.setMensaje(respuestaEncriptada);
			}else{
				salidaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS);
			}
			// salidaVO.setMensaje("OK");
		} catch (Exception e) {

			logger.error("Error: "+ e.getMessage());

			salidaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje("Error en servicio Banco estado: compruebe el servicio: " + e.getMessage());
			return salidaVO;
		}

		logger.info(">> Salida Web Service: Banco Estado");
		return salidaVO;

	}

	public static String enterVerifyXml(RespuestaBanco vo) {

		final String xmlStr = "<wtw3:metodo>" + vo.getMetodo() + "</wtw3:metodo><wtw3:mensaje>" + "<Convenio>"
				+ vo.getConvenio() + "</Convenio>" + "</wtw3:mensaje>";

		// Use method to convert XML string content to XML Document object
		return xmlStr;
	}

	public static String getNominaRendicion(RespuestaBanco vo) {

		StringBuilder str = new StringBuilder();

		str.append("<wtw3:tran>");
		str.append("<wtw3:metodo>" + vo.getMetodo() + "</wtw3:metodo>");
		str.append("<wtw3:mensaje><Convenio>" + vo.getConvenio() + "</Convenio><Nomina>" + vo.getNumeroNomina()
				+ "</Nomina>");
		str.append("<FechaPagoDesde>" + vo.getFechaPagoDesde() + "</FechaPagoDesde>");
		str.append("<FechaPagoHasta>" + vo.getFechaPagoHasta() + "</FechaPagoHasta><PlantillaRendicion>"
				+ vo.getPlantillaRendicion() + "</PlantillaRendicion>");
		str.append("<Estados></Estados></wtw3:mensaje>");
		str.append("</wtw3:tran>");

		return str.toString();
	}

	public static String sendNomina(EnvioNominaVo vo) {

		StringBuilder str = new StringBuilder();

		str.append("<wtw3:tran>");
		str.append("<wtw3:metodo>" + vo.getMetodo() + "</wtw3:metodo>");
		str.append("<wtw3:mensaje>");
		str.append("<Convenio>" + vo.getConvenio() + "</Convenio>");
		str.append("<FechaPago>" + vo.getFechaPago() + "</FechaPago>");
		str.append("<TipoNomina>" + vo.getTipoNomina() + "</TipoNomina>");
		str.append("<ConceptoPago>" + vo.getConceptoPago() + "</ConceptoPago>");
		str.append("<NombreNomina>" + vo.getNombreNomina() + "</NombreNomina>");
		str.append("<Plantilla>" + vo.getPlantilla() + "</Plantilla>");
		str.append("<Archivo>" + vo.getArchivo() + "</Archivo>");
		str.append("<Monto>" + vo.getMonto() + "</Monto>");
		str.append("<Cantidad>" + vo.getCantidad() + "</Cantidad>");
		str.append("</wtw3:mensaje>");
		str.append("</wtw3:tran>");

		return str.toString();
	}

	public static String prepareXml(String xml) {

		return "<wtw3:tran xmlns:wtw3=\"http://www.w3.org/2001/XMLSchema/\">" + xml + "</wtw3:tran>";
	}

	public static String extraeEncrypt(String encrypt) {

		encrypt = encrypt.replace("<wtw3:tran xmlns:wtw3=\"http://www.w3.org/2001/XMLSchema/\">", "");
		return encrypt.replace("</wtw3:tran>", "");
	}

	public static List<RespuestaBanco> parseXml(String xmlStr) {

		List<RespuestaBanco> respuesta = new ArrayList<RespuestaBanco>();

		try {
			// Use method to convert XML string content to XML Document object
			Document doc = convertStringToXMLDocument(xmlStr);

			// Verify XML document is build correctly
			NodeList nList = doc.getElementsByTagName("wtw3:respuesta");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				//System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					RespuestaBanco rb = new RespuestaBanco();

					Element eElement = (Element) nNode;

					rb.setCodigoRetorno(eElement.getElementsByTagName("CodigoRetorno").item(0).getTextContent());
					rb.setNumeroNomina(eElement.getElementsByTagName("NumeroNomina").item(0).getTextContent());
					rb.setFechaPagoDesde(eElement.getElementsByTagName("FechaPagoDesde").item(0).getTextContent());
					rb.setFechaPagoHasta(eElement.getElementsByTagName("FechaPagoHasta").item(0).getTextContent());
					rb.setGlosaError(eElement.getElementsByTagName("GlosaError").item(0).getTextContent());
					rb.setArchivo(eElement.getElementsByTagName("Archivo").item(0).getTextContent());

					respuesta.add(rb);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respuesta;
	}
	
	public static EnvioNominaRespuestaVo parseXmlEnvio(String xmlStr) {

		EnvioNominaRespuestaVo respuesta_envio = new EnvioNominaRespuestaVo();

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

					respuesta_envio.setNumNomina(eElement.getElementsByTagName("Nomina").item(0).getTextContent());
					respuesta_envio.setGlsErrorEnvio(eElement.getElementsByTagName("GlosaError").item(0).getTextContent());
					respuesta_envio.setCodigoRetorno(eElement.getElementsByTagName("CodigoRetorno").item(0).getTextContent());

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respuesta_envio;
	}

	public static List<RespuestaServicioVo> parseXmlValidate(String xmlStr) {

		List<RespuestaServicioVo> respuesta = new ArrayList<RespuestaServicioVo>();

		try {
			// Use method to convert XML string content to XML Document object
			Document doc = convertStringToXMLDocument(xmlStr);

			// Verify XML document is build correctly
			NodeList nList = doc.getElementsByTagName("wtw3:respuesta");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					RespuestaServicioVo rb = new RespuestaServicioVo();

					Element eElement = (Element) nNode;

					rb.setConvenio(eElement.getElementsByTagName("Convenio").item(0).getTextContent());
					rb.setCodigoRetorno(eElement.getElementsByTagName("CodigoRetorno").item(0).getTextContent());

					respuesta.add(rb);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respuesta;
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

	public String ceros9Left(String rut) {

		String aux = "0";

		for (int i = 0; i < 9 - rut.length(); i++) {

			aux = aux + rut;
		}

		return aux;
	}

}
