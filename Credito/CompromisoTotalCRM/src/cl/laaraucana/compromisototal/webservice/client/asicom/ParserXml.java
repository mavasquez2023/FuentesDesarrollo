package cl.laaraucana.compromisototal.webservice.client.asicom;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import cl.laaraucana.compromisototal.utils.Utils;
import cl.laaraucana.compromisototal.webservice.client.asicom.VO.DetalleSalidaAsicomVO;
import cl.laaraucana.compromisototal.webservice.client.asicom.VO.SalidaAsicomVO;

public class ParserXml {
	protected Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 
	 * @param String xml
	 * @return ArrayList<CreditoSalidaVO> realiza el parseo de los datos de
	 *         credito a partir de un xml(obtenido desde el webservice asicom) y
	 *         lo retorna como lista
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public ArrayList<SalidaAsicomVO> parseaDatosCreditos(String xml) throws ParserConfigurationException, SAXException, IOException {

		logger.debug("entra a parseaDatosCreditos()");
	
		ArrayList<SalidaAsicomVO> arreglo = new ArrayList<SalidaAsicomVO>();

		// repara el xml que se obtiene
		// String xmllimpio = xml.replaceAll("codigo=0", "codigo=\"0\"");
		// xmllimpio = xmllimpio.replaceAll("codigo=1", "codigo=\"1\"");

		InputSource iSourse = new InputSource();
		iSourse.setByteStream(new ByteArrayInputStream(xml.getBytes()));
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		dBuilder = dbFactory.newDocumentBuilder();

		Document doc = dBuilder.parse(iSourse);
		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("fila");

		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				arreglo.add(new SalidaAsicomVO(
						eElement.getElementsByTagName("folio_credito").item(0).getTextContent(),
						eElement.getElementsByTagName("tipo_credito").item(0).getTextContent(),
						eElement.getElementsByTagName("tipo_producto").item(0).getTextContent(), 
						eElement.getElementsByTagName("tasa_interes").item(0).getTextContent(), 
						eElement.getElementsByTagName("monto_adeudado").item(0).getTextContent(), 
						eElement.getElementsByTagName("monto_impuestos").item(0).getTextContent(), 
						eElement.getElementsByTagName("monto_cuota").item(0).getTextContent(), 
						eElement.getElementsByTagName("estado_credito").item(0).getTextContent(), 
						eElement.getElementsByTagName("indicador_reprogramacion").item(0).getTextContent(), 
						Utils.eliminaCeros(eElement.getElementsByTagName("rol_asociado_relacion").item(0).getTextContent()), 
						Utils.eliminaCeros(eElement.getElementsByTagName("rol_pagador").item(0).getTextContent()), 
						eElement.getElementsByTagName("cantidad_cuotas").item(0).getTextContent(), 
						eElement.getElementsByTagName("oficina_otorgamiento").item(0).getTextContent(), 
						eElement.getElementsByTagName("oficina_originadora").item(0).getTextContent()));
			}

		}

		return arreglo;
	}

	/**
	 * 
	 * @param String
	 *            xml
	 * @return ArrayList<DetalleCreditoSalidaVO> realiza el parseo de los datos
	 *         detalle de credito a partir de un xml(obtenido desde el
	 *         webservice asicom) y lo retorna como lista
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParseException 
	 * @throws DOMException 
	 */
	public ArrayList<DetalleSalidaAsicomVO> parseaDetalleCreditos(String xml) throws ParserConfigurationException, SAXException, IOException, DOMException, ParseException {

		logger.debug("entra a parseaDetalleCreditos()");
		ArrayList<DetalleSalidaAsicomVO> arreglo = new ArrayList<DetalleSalidaAsicomVO>();

		InputSource iSourse = new InputSource();
		
		
		iSourse.setByteStream(new ByteArrayInputStream(xml.getBytes()));

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		dBuilder = dbFactory.newDocumentBuilder();

		Document doc = dBuilder.parse(iSourse);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("fila");

		for (int i = 0; i < nList.getLength(); i++) {

			Node nNode = nList.item(i);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;

				arreglo.add(new DetalleSalidaAsicomVO(
						eElement.getElementsByTagName("numero_cuota").item(0).getTextContent(), 
						Utils.stringToDateAsicom(eElement.getElementsByTagName("fecha_vencimiento").item(0).getTextContent()), 
						eElement.getElementsByTagName("monto_capital_amortizado").item(0).getTextContent(), 
						eElement.getElementsByTagName("monto_seguros").item(0).getTextContent(),
						eElement.getElementsByTagName("monto_interes").item(0).getTextContent(), 
						eElement.getElementsByTagName("cuota_morosa").item(0).getTextContent()));

			}

		}

		return arreglo;
	}

	public String getCodigoErrorXML(String xml) throws ParserConfigurationException, SAXException, IOException {
		logger.debug("entra a getCodigoErrorXML()");

		String codigoError = "";
		InputSource iSourse = new InputSource();

		iSourse.setCharacterStream(new StringReader(xml));
		iSourse.setByteStream(new ByteArrayInputStream(xml.getBytes()));

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		dBuilder = dbFactory.newDocumentBuilder();

		Document doc = dBuilder.parse(iSourse);
		doc.getDocumentElement().normalize();

		NodeList nodo = doc.getElementsByTagName("error");
		for (int i = 0; i < nodo.getLength(); i++) {
			Node nNode = nodo.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) nNode;
				codigoError = e.getAttribute("codigo");
			}

		}
		return codigoError;
	}

}
