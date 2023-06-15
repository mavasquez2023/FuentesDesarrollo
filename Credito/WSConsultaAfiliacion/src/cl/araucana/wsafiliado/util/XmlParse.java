package cl.araucana.wsafiliado.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import cl.araucana.tupla2.exception.Tupla2Exception;
import cl.araucana.wsafiliado.to.RetornoTO;
import cl.araucana.wsafiliado.to.TramoTO;
import cl.araucana.wsafiliado.to.TuplaTO;


public class XmlParse {

	public String parsearXMLautentica(String xml) {

		String Mensaje = null;
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			InputSource is = new InputSource();

			is.setCharacterStream(new StringReader(xml));

			Document doc = dBuilder.parse(is);

			doc.getDocumentElement().normalize();

			NodeList nodeLista = doc.getElementsByTagName("Respuesta");
			Node primerNodo = nodeLista.item(0);
			Element primerElemento = (Element) primerNodo;
			NodeList primerNombreElementoLista = primerElemento.getElementsByTagName("Mensaje");

			Element primerNombreElemento = (Element) primerNombreElementoLista.item(0);
			NodeList primerNombre = primerNombreElemento.getChildNodes();
			Mensaje = ((Node) primerNombre.item(0)).getNodeValue().toString();
			//System.out.println("Mensaje : "  + Mensaje);

			Element segundoElemento = (Element) primerNodo;
			NodeList segundoNombreElementoLista = segundoElemento.getElementsByTagName("Codigo");

			Element segundoNombreElemento = (Element) segundoNombreElementoLista.item(0);
			NodeList segundoNombre = segundoNombreElemento.getChildNodes();
			String Codigo = ((Node) segundoNombre.item(0)).getNodeValue().toString();
			System.out.println("Codigo Autenticacion:" + Codigo);
			if (!"0".equals(Codigo))
				throw new Tupla2Exception("No es posible autenticarse en el SIAGF. Codigo retorno:" + Codigo);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return Mensaje;

	}

	public RetornoTO parsearXMLRetorno(String xml) throws Exception {

		RetornoTO oRetorno = new RetornoTO();

		String Mensaje = null;
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			InputSource is = new InputSource();

			is.setCharacterStream(new StringReader(xml));

			Document doc = dBuilder.parse(is);

			doc.getDocumentElement().normalize();

			NodeList nodeLista = doc.getElementsByTagName("Respuesta");
			Node primerNodo = nodeLista.item(0);

			Element segundoElemento = (Element) primerNodo;
			NodeList segundoNombreElementoLista = segundoElemento.getElementsByTagName("Codigo");

			Element segundoNombreElemento = (Element) segundoNombreElementoLista.item(0);
			NodeList segundoNombre = segundoNombreElemento.getChildNodes();
			String Codigo = ((Node) segundoNombre.item(0)).getNodeValue().toString();
			oRetorno.setCodigo(Codigo);

			Element primerElemento = (Element) primerNodo;
			NodeList primerNombreElementoLista = primerElemento.getElementsByTagName("Mensaje");

			Element primerNombreElemento = (Element) primerNombreElementoLista.item(0);
			NodeList primerNombre = primerNombreElemento.getChildNodes();
			Mensaje = ((Node) primerNombre.item(0)).getNodeValue().toString();
			oRetorno.setMensaje(Mensaje);

		} catch (Exception e) {
			throw e;
			//e.printStackTrace();

		}

		return oRetorno;

	}

	/**
	 * Convierte el xml de respuesta del WS ConsultaCausante en una lista de objetos
	 * @param xml Respuesta del WebService ConsultaCausante
	 * @return ArrayList de objetos tipo TuplaTO
	 * @throws IOException
	 * @throws SAXException
	 * @throws Tupla2Exception
	 */
	public ArrayList parseaXmlTupla(String xml) throws IOException, SAXException, Tupla2Exception {
		ArrayList tupla = new ArrayList();

		TuplaTO oTupla = new TuplaTO();
		TramoTO oTramo = new TramoTO();
		String result = null;
		String fechaemision = null;

		try {
			int kk=0;
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			InputSource is = new InputSource();

			is.setCharacterStream(new StringReader(xml));

			Document doc = dBuilder.parse(is);

			doc.getDocumentElement().normalize();

			NodeList nodeLista = doc.getElementsByTagName("Respuesta");

			Node Codigo = nodeLista.item(0);
			Element CodigoElement = (Element) Codigo;
			NodeList CodigoLista = CodigoElement.getElementsByTagName("Codigo");
			NodeList mensajeResp = CodigoElement.getElementsByTagName("Mensaje");

			Element CodigoElemento = (Element) CodigoLista.item(0);
			NodeList CodigoChild = CodigoElemento.getChildNodes();
			result = ((Node) CodigoChild.item(0)).getNodeValue().toString();

			Element MsgElemento = (Element) mensajeResp.item(0);
			NodeList MsgChild = MsgElemento.getChildNodes();
			String msgResp = ((Node) MsgChild.item(0)).getNodeValue().toString();

			oTupla.setCodigo(result);
			if (!"0".equals(result))
				//throw new Tupla2Exception("Error en la consulta al SIAGF: ", result);
				throw new Tupla2Exception(msgResp, result);

			NodeList nodeLista2 = doc.getElementsByTagName("RespuestaCausante");
			try {
				Node FechaEmision = nodeLista2.item(0);
				Element FechaEmisionElement = (Element) FechaEmision;
				NodeList FechaEmisionLista = FechaEmisionElement.getElementsByTagName("FechaEmision");

				Element FechaEmisionElemento = (Element) FechaEmisionLista.item(0);
				NodeList FechaEmisionChild = FechaEmisionElemento.getChildNodes();
				fechaemision = ((Node) FechaEmisionChild.item(0)).getNodeValue().toString();

			} catch (NullPointerException ignored) {
			}
			NodeList nodeTupla = doc.getElementsByTagName("Tupla");

			for (int i = 0; i < nodeTupla.getLength(); i++) {

				oTupla = new TuplaTO();
				ArrayList tramo = new ArrayList();

				oTupla.setFechaEmision(fechaemision);
				oTupla.setTupla((i + 1) + "");
				try {
					Node TrackID = nodeTupla.item(i);
					Element TrackIDElement = (Element) TrackID;
					NodeList TrackIDLista = TrackIDElement.getElementsByTagName("TrackID");

					Element TrackIDElemento = (Element) TrackIDLista.item(0);
					NodeList TrackIDChild = TrackIDElemento.getChildNodes();
					result = ((Node) TrackIDChild.item(0)).getNodeValue().toString();

					oTupla.setTrackID(result);
				} catch (NullPointerException ignored) {
				}
				try {
					Node CodEstadoTupla = nodeTupla.item(i);
					Element CodEstadoTuplaElement = (Element) CodEstadoTupla;
					NodeList CodEstadoTuplaLista = CodEstadoTuplaElement.getElementsByTagName("CodEstadoTupla");

					Element CodEstadoTuplaElemento = (Element) CodEstadoTuplaLista.item(0);
					NodeList CodEstadoTuplaChild = CodEstadoTuplaElemento.getChildNodes();
					result = ((Node) CodEstadoTuplaChild.item(0)).getNodeValue().toString();

					oTupla.setCodEstadoTupla(result);
				} catch (NullPointerException ignored) {
				}
				try {
					Node NomEstadoTupla = nodeTupla.item(i);
					Element NomEstadoTuplaElement = (Element) NomEstadoTupla;
					NodeList NomEstadoTuplaLista = NomEstadoTuplaElement.getElementsByTagName("NomEstadoTupla");

					Element NomEstadoTuplaElemento = (Element) NomEstadoTuplaLista.item(0);
					NodeList NomEstadoTuplaChild = NomEstadoTuplaElemento.getChildNodes();
					result = ((Node) NomEstadoTuplaChild.item(0)).getNodeValue().toString();

					oTupla.setNomEstadoTupla(result);
				} catch (NullPointerException ignored) {
				}
				try {
					Node CodTipoCausante = nodeTupla.item(i);
					Element CodTipoCausanteElement = (Element) CodTipoCausante;
					NodeList CodTipoCausanteLista = CodTipoCausanteElement.getElementsByTagName("CodTipoCausante");

					Element CodTipoCausanteElemento = (Element) CodTipoCausanteLista.item(0);
					NodeList CodTipoCausanteChild = CodTipoCausanteElemento.getChildNodes();
					result = ((Node) CodTipoCausanteChild.item(0)).getNodeValue().toString();

					oTupla.setCodTipoCausante(result);
				} catch (NullPointerException ignored) {
				}
				try {
					Node NomTipoCausante = nodeTupla.item(i);
					Element NomTipoCausanteElement = (Element) NomTipoCausante;
					NodeList NomTipoCausanteLista = NomTipoCausanteElement.getElementsByTagName("NomTipoCausante");

					Element NomTipoCausanteElemento = (Element) NomTipoCausanteLista.item(0);
					NodeList NomTipoCausanteChild = NomTipoCausanteElemento.getChildNodes();
					result = ((Node) NomTipoCausanteChild.item(0)).getNodeValue().toString();

					oTupla.setNomTipoCausante(result);
				} catch (NullPointerException ignored) {
				}
				try {
					Node RutCausante = nodeTupla.item(i);
					Element RutCausanteElement = (Element) RutCausante;
					NodeList RutCausanteLista = RutCausanteElement.getElementsByTagName("RutCausante");

					Element RutCausanteElemento = (Element) RutCausanteLista.item(0);
					NodeList RutCausanteChild = RutCausanteElemento.getChildNodes();
					result = ((Node) RutCausanteChild.item(0)).getNodeValue().toString();
					String[] rutdv= result.split("-");
					oTupla.setRutCausante(rutdv[0]);
					oTupla.setDVCausante(rutdv[1]);
				} catch (NullPointerException ignored) {
				}
				try {
					Node NomCausante = nodeTupla.item(i);
					Element NomCausanteElement = (Element) NomCausante;
					NodeList NomCausanteLista = NomCausanteElement.getElementsByTagName("NomCausante");

					Element NomCausanteElemento = (Element) NomCausanteLista.item(0);
					NodeList NomCausanteChild = NomCausanteElemento.getChildNodes();
					result = ((Node) NomCausanteChild.item(0)).getNodeValue().toString();

					oTupla.setNomCausante(result);
				} catch (NullPointerException ignored) {
				}
				try {
					Node SexoCausante = nodeTupla.item(i);
					Element SexoCausanteElement = (Element) SexoCausante;
					NodeList SexoCausanteLista = SexoCausanteElement.getElementsByTagName("SexoCausante");

					Element SexoCausanteElemento = (Element) SexoCausanteLista.item(0);
					NodeList SexoCausanteChild = SexoCausanteElemento.getChildNodes();
					result = ((Node) SexoCausanteChild.item(0)).getNodeValue().toString();

					oTupla.setSexoCausante(result);
				} catch (NullPointerException ignored) {
				}
				try {
					Node FecNacCausante = nodeTupla.item(i);
					Element FecNacCausanteElement = (Element) FecNacCausante;
					NodeList FecNacCausanteLista = FecNacCausanteElement.getElementsByTagName("FecNacCausante");

					Element FecNacCausanteElemento = (Element) FecNacCausanteLista.item(0);
					NodeList FecNacCausanteChild = FecNacCausanteElemento.getChildNodes();
					result = ((Node) FecNacCausanteChild.item(0)).getNodeValue().toString();

					oTupla.setFecNacCausante(result);
				} catch (NullPointerException ignored) {
				}

				try {
					Node FecExtCausante = nodeTupla.item(i);
					Element FecExtCausanteElement = (Element) FecExtCausante;
					NodeList FecExtCausanteLista = FecExtCausanteElement.getElementsByTagName("FecExtCausante");

					Element FecExtCausanteElemento = (Element) FecExtCausanteLista.item(0);
					NodeList FecExtCausanteChild = FecExtCausanteElemento.getChildNodes();
					result = ((Node) FecExtCausanteChild.item(0)).getNodeValue().toString();

					oTupla.setFecExtCausante(result);
				} catch (NullPointerException ignored) {
				}

				try {
					Node CausaExtCausante = nodeTupla.item(i);
					Element CausaExtCausanteElement = (Element) CausaExtCausante;
					NodeList CausaExtCausanteLista = CausaExtCausanteElement.getElementsByTagName("CausaExtCausante");

					Element CausaExtCausanteElemento = (Element) CausaExtCausanteLista.item(0);
					NodeList CausaExtCausanteChild = CausaExtCausanteElemento.getChildNodes();
					result = ((Node) CausaExtCausanteChild.item(0)).getNodeValue().toString();

					oTupla.setCausaExtCausante(result);
				} catch (NullPointerException ignored) {
				}

				try {
					Node CodRegionCausante = nodeTupla.item(i);
					Element CodRegionCausanteElement = (Element) CodRegionCausante;
					NodeList CodRegionCausanteLista = CodRegionCausanteElement.getElementsByTagName("CodRegionCausante");

					Element CodRegionCausanteElemento = (Element) CodRegionCausanteLista.item(0);
					NodeList CodRegionCausanteChild = CodRegionCausanteElemento.getChildNodes();
					result = ((Node) CodRegionCausanteChild.item(0)).getNodeValue().toString();

					oTupla.setCodRegionCausante(result);
				} catch (NullPointerException ignored) {
				}
				try {
					Node NomRegionCausante = nodeTupla.item(i);
					Element NomRegionCausanteElement = (Element) NomRegionCausante;
					NodeList NomRegionCausanteLista = NomRegionCausanteElement.getElementsByTagName("NomRegionCausante");

					Element NomRegionCausanteElemento = (Element) NomRegionCausanteLista.item(0);
					NodeList NomRegionCausanteChild = NomRegionCausanteElemento.getChildNodes();
					result = ((Node) NomRegionCausanteChild.item(0)).getNodeValue().toString();

					oTupla.setNomRegionCausante(result);
				} catch (NullPointerException ignored) {
				}
				try {
					Node CodComunaCausante = nodeTupla.item(i);
					Element CodComunaCausanteElement = (Element) CodComunaCausante;
					NodeList CodComunaCausanteLista = CodComunaCausanteElement.getElementsByTagName("CodComunaCausante");

					Element CodComunaCausanteElemento = (Element) CodComunaCausanteLista.item(0);
					NodeList CodComunaCausanteChild = CodComunaCausanteElemento.getChildNodes();
					result = ((Node) CodComunaCausanteChild.item(0)).getNodeValue().toString();

					oTupla.setCodComunaCausante(result);
				} catch (NullPointerException ignored) {
				}
				try {
					Node NomComunaCausante = nodeTupla.item(i);
					Element NomComunaCausanteElement = (Element) NomComunaCausante;
					NodeList NomComunaCausanteLista = NomComunaCausanteElement.getElementsByTagName("NomComunaCausante");

					Element NomComunaCausanteElemento = (Element) NomComunaCausanteLista.item(0);
					NodeList NomComunaCausanteChild = NomComunaCausanteElemento.getChildNodes();
					result = ((Node) NomComunaCausanteChild.item(0)).getNodeValue().toString();

					oTupla.setNomComunaCausante(result);
				} catch (NullPointerException ignored) {
				}
				try {
					Node CodTipoBeneficiario = nodeTupla.item(i);
					Element CodTipoBeneficiarioElement = (Element) CodTipoBeneficiario;
					NodeList CodTipoBeneficiarioLista = CodTipoBeneficiarioElement.getElementsByTagName("CodTipoBeneficiario");

					Element CodTipoBeneficiarioElemento = (Element) CodTipoBeneficiarioLista.item(0);
					NodeList CodTipoBeneficiarioChild = CodTipoBeneficiarioElemento.getChildNodes();
					result = ((Node) CodTipoBeneficiarioChild.item(0)).getNodeValue().toString();

					oTupla.setCodTipoBeneficiario(result);
				} catch (NullPointerException ignored) {
				}
				try {
					Node NomTipoBeneficiario = nodeTupla.item(i);
					Element NomTipoBeneficiarioElement = (Element) NomTipoBeneficiario;
					NodeList NomTipoBeneficiarioLista = NomTipoBeneficiarioElement.getElementsByTagName("NomTipoBeneficiario");

					Element NomTipoBeneficiarioElemento = (Element) NomTipoBeneficiarioLista.item(0);
					NodeList NomTipoBeneficiarioChild = NomTipoBeneficiarioElemento.getChildNodes();
					result = ((Node) NomTipoBeneficiarioChild.item(0)).getNodeValue().toString();

					oTupla.setNomTipoBeneficiario(result);
				} catch (NullPointerException ignored) {
				}

				try {
					Node RutBeneficiario = nodeTupla.item(i);
					Element RutBeneficiarioElement = (Element) RutBeneficiario;
					NodeList RutBeneficiarioLista = RutBeneficiarioElement.getElementsByTagName("RutBeneficiario");

					Element RutBeneficiarioElemento = (Element) RutBeneficiarioLista.item(0);
					NodeList RutBeneficiarioChild = RutBeneficiarioElemento.getChildNodes();
					result = ((Node) RutBeneficiarioChild.item(0)).getNodeValue().toString();

					String[] rutdv= result.split("-");
					oTupla.setRutBeneficiario(rutdv[0]);
					oTupla.setDVBeneficiario(rutdv[1]);
				} catch (NullPointerException ignored) {
				}

				try {
					Node NomBeneficiario = nodeTupla.item(i);
					Element NomBeneficiarioElement = (Element) NomBeneficiario;
					NodeList NomBeneficiarioLista = NomBeneficiarioElement.getElementsByTagName("NomBeneficiario");

					Element NomBeneficiarioElemento = (Element) NomBeneficiarioLista.item(0);
					NodeList NomBeneficiarioChild = NomBeneficiarioElemento.getChildNodes();
					result = ((Node) NomBeneficiarioChild.item(0)).getNodeValue().toString();

					oTupla.setNomBeneficiario(result);
				} catch (NullPointerException ignored) {
				}
				try {
					Node CodRegionBeneficiario = nodeTupla.item(i);
					Element CodRegionBeneficiarioElement = (Element) CodRegionBeneficiario;
					NodeList CodRegionBeneficiarioLista = CodRegionBeneficiarioElement.getElementsByTagName("CodRegionBeneficiario");

					Element CodRegionBeneficiarioElemento = (Element) CodRegionBeneficiarioLista.item(0);
					NodeList CodRegionBeneficiarioChild = CodRegionBeneficiarioElemento.getChildNodes();
					result = ((Node) CodRegionBeneficiarioChild.item(0)).getNodeValue().toString();

					oTupla.setCodRegionBeneficiario(result);
				} catch (NullPointerException ignored) {
				}

				try {
					Node NomRegionBeneficiario = nodeTupla.item(i);
					Element NomRegionBeneficiarioElement = (Element) NomRegionBeneficiario;
					NodeList NomRegionBeneficiarioLista = NomRegionBeneficiarioElement.getElementsByTagName("NomRegionBeneficiario");

					Element NomRegionBeneficiarioElemento = (Element) NomRegionBeneficiarioLista.item(0);
					NodeList NomRegionBeneficiarioChild = NomRegionBeneficiarioElemento.getChildNodes();
					result = ((Node) NomRegionBeneficiarioChild.item(0)).getNodeValue().toString();

					oTupla.setNomRegionBeneficiario(result);
				} catch (NullPointerException ignored) {
				}

				try {
					Node CodComunaBeneficiario = nodeTupla.item(i);
					Element CodComunaBeneficiarioElement = (Element) CodComunaBeneficiario;
					NodeList CodComunaBeneficiarioLista = CodComunaBeneficiarioElement.getElementsByTagName("CodComunaBeneficiario");

					Element CodComunaBeneficiarioElemento = (Element) CodComunaBeneficiarioLista.item(0);
					NodeList CodComunaBeneficiarioChild = CodComunaBeneficiarioElemento.getChildNodes();
					result = ((Node) CodComunaBeneficiarioChild.item(0)).getNodeValue().toString();

					oTupla.setCodComunaBeneficiario(result);
				} catch (NullPointerException ignored) {
				}

				try {
					Node NomComunaBeneficiario = nodeTupla.item(i);
					Element NomComunaBeneficiarioElement = (Element) NomComunaBeneficiario;
					NodeList NomComunaBeneficiarioLista = NomComunaBeneficiarioElement.getElementsByTagName("NomComunaBeneficiario");

					Element NomComunaBeneficiarioElemento = (Element) NomComunaBeneficiarioLista.item(0);
					NodeList NomComunaBeneficiarioChild = NomComunaBeneficiarioElemento.getChildNodes();
					result = ((Node) NomComunaBeneficiarioChild.item(0)).getNodeValue().toString();

					oTupla.setNomComunaBeneficiario(result);
				} catch (NullPointerException ignored) {
				}
				try {
					Node IngPromedio = nodeTupla.item(i);
					Element IngPromedioElement = (Element) IngPromedio;
					NodeList IngPromedioLista = IngPromedioElement.getElementsByTagName("IngPromedio");

					Element IngPromedioElemento = (Element) IngPromedioLista.item(0);
					NodeList IngPromedioChild = IngPromedioElemento.getChildNodes();
					result = ((Node) IngPromedioChild.item(0)).getNodeValue().toString();

					oTupla.setIngPromedio(result);
				} catch (NullPointerException ignored) {
				}
				try {
					Node RutEmpleador = nodeTupla.item(i);
					Element RutEmpleadorElement = (Element) RutEmpleador;
					NodeList RutEmpleadorLista = RutEmpleadorElement.getElementsByTagName("RutEmpleador");

					Element RutEmpleadorElemento = (Element) RutEmpleadorLista.item(0);
					NodeList RutEmpleadorChild = RutEmpleadorElemento.getChildNodes();
					result = ((Node) RutEmpleadorChild.item(0)).getNodeValue().toString();

					String[] rutdv= result.split("-");
					oTupla.setRutEmpleador(rutdv[0]);
					oTupla.setDVEmpleador(rutdv[1]);
				} catch (NullPointerException ignored) {
				}

				try {
					Node NomEmpleador = nodeTupla.item(i);
					Element NomEmpleadorElement = (Element) NomEmpleador;
					NodeList NomEmpleadorLista = NomEmpleadorElement.getElementsByTagName("NomEmpleador");

					Element NomEmpleadorElemento = (Element) NomEmpleadorLista.item(0);
					NodeList NomEmpleadorChild = NomEmpleadorElemento.getChildNodes();
					result = ((Node) NomEmpleadorChild.item(0)).getNodeValue().toString();

					oTupla.setNomEmpleador(result);
				} catch (NullPointerException ignored) {
				}

				try {
					Node Acteco = nodeTupla.item(i);
					Element ActecoElement = (Element) Acteco;
					NodeList ActecoLista = ActecoElement.getElementsByTagName("Acteco");

					Element ActecoElemento = (Element) ActecoLista.item(0);
					NodeList ActecoChild = ActecoElemento.getChildNodes();
					result = ((Node) ActecoChild.item(0)).getNodeValue().toString();

					oTupla.setActeco(result);
				} catch (NullPointerException ignored) {
				}

				try {
					Node CodRegionEmpleador = nodeTupla.item(i);
					Element CodRegionEmpleadorElement = (Element) CodRegionEmpleador;
					NodeList CodRegionEmpleadorLista = CodRegionEmpleadorElement.getElementsByTagName("CodRegionEmpleador");

					Element CodRegionEmpleadorElemento = (Element) CodRegionEmpleadorLista.item(0);
					NodeList CodRegionEmpleadorChild = CodRegionEmpleadorElemento.getChildNodes();
					result = ((Node) CodRegionEmpleadorChild.item(0)).getNodeValue().toString();

					oTupla.setCodRegionEmpleador(result);
				} catch (NullPointerException ignored) {
				}

				try {
					Node NomRegionEmpleador = nodeTupla.item(i);
					Element NomRegionEmpleadorElement = (Element) NomRegionEmpleador;
					NodeList NomRegionEmpleadorLista = NomRegionEmpleadorElement.getElementsByTagName("NomRegionEmpleador");

					Element NomRegionEmpleadorElemento = (Element) NomRegionEmpleadorLista.item(0);
					NodeList NomRegionEmpleadorChild = NomRegionEmpleadorElemento.getChildNodes();
					result = ((Node) NomRegionEmpleadorChild.item(0)).getNodeValue().toString();

					oTupla.setNomRegionEmpleador(result);
				} catch (NullPointerException ignored) {
				}

				try {
					Node CodComunaEmpleador = nodeTupla.item(i);
					Element CodComunaEmpleadorElement = (Element) CodComunaEmpleador;
					NodeList CodComunaEmpleadorLista = CodComunaEmpleadorElement.getElementsByTagName("CodComunaEmpleador");

					Element CodComunaEmpleadorElemento = (Element) CodComunaEmpleadorLista.item(0);
					NodeList CodComunaEmpleadorChild = CodComunaEmpleadorElemento.getChildNodes();
					result = ((Node) CodComunaEmpleadorChild.item(0)).getNodeValue().toString();

					oTupla.setCodComunaEmpleador(result);

				} catch (NullPointerException ignored) {
				}
				try {
					Node NomComunaEmpleador = nodeTupla.item(i);
					Element NomComunaEmpleadorElement = (Element) NomComunaEmpleador;
					NodeList NomComunaEmpleadorLista = NomComunaEmpleadorElement.getElementsByTagName("NomComunaEmpleador");

					Element NomComunaEmpleadorElemento = (Element) NomComunaEmpleadorLista.item(0);
					NodeList NomComunaEmpleadorChild = NomComunaEmpleadorElemento.getChildNodes();
					result = ((Node) NomComunaEmpleadorChild.item(0)).getNodeValue().toString();

					oTupla.setNomComunaEmpleador(result);
				} catch (NullPointerException ignored) {
				}

				try {
					Node FechaTransaccion = nodeTupla.item(i);
					Element FechaTransaccionElement = (Element) FechaTransaccion;
					NodeList FechaTransaccionLista = FechaTransaccionElement.getElementsByTagName("FechaTransaccion");

					Element FechaTransaccionElemento = (Element) FechaTransaccionLista.item(0);
					NodeList FechaTransaccionChild = FechaTransaccionElemento.getChildNodes();
					result = ((Node) FechaTransaccionChild.item(0)).getNodeValue().toString();

					oTupla.setFechaTransaccion(result);
				} catch (NullPointerException ignored) {
				}

				try {
					Node CodEntidadAdm = nodeTupla.item(i);
					Element CodEntidadAdmElement = (Element) CodEntidadAdm;
					NodeList CodEntidadAdmLista = CodEntidadAdmElement.getElementsByTagName("CodEntidadAdm");

					Element CodEntidadAdmElemento = (Element) CodEntidadAdmLista.item(0);
					NodeList CodEntidadAdmChild = CodEntidadAdmElemento.getChildNodes();
					result = ((Node) CodEntidadAdmChild.item(0)).getNodeValue().toString();

					oTupla.setCodEntidadAdm(result);
				} catch (NullPointerException ignored) {
				}
				try {
					Node NomEntidadAdm = nodeTupla.item(i);
					Element NomEntidadAdmElement = (Element) NomEntidadAdm;
					NodeList NomEntidadAdmLista = NomEntidadAdmElement.getElementsByTagName("NomEntidadAdm");

					Element NomEntidadAdmElemento = (Element) NomEntidadAdmLista.item(0);
					NodeList NomEntidadAdmChild = NomEntidadAdmElemento.getChildNodes();
					result = ((Node) NomEntidadAdmChild.item(0)).getNodeValue().toString();

					oTupla.setNomEntidadAdm(result);

				} catch (NullPointerException ignored) {
				}
				try {
					Node CodTipoBeneficio = nodeTupla.item(i);
					Element CodTipoBeneficioElement = (Element) CodTipoBeneficio;
					NodeList CodTipoBeneficioLista = CodTipoBeneficioElement.getElementsByTagName("CodTipoBeneficio");

					Element CodTipoBeneficioElemento = (Element) CodTipoBeneficioLista.item(0);
					NodeList CodTipoBeneficioChild = CodTipoBeneficioElemento.getChildNodes();
					result = ((Node) CodTipoBeneficioChild.item(0)).getNodeValue().toString();

					oTupla.setCodTipoBeneficio(result);
				} catch (NullPointerException ignored) {
				}
				try {
					Node NomTipoBeneficio = nodeTupla.item(i);
					Element NomTipoBeneficioElement = (Element) NomTipoBeneficio;
					NodeList NomTipoBeneficioLista = NomTipoBeneficioElement.getElementsByTagName("NomTipoBeneficio");

					Element NomTipoBeneficioElemento = (Element) NomTipoBeneficioLista.item(0);
					NodeList NomTipoBeneficioChild = NomTipoBeneficioElemento.getChildNodes();
					result = ((Node) NomTipoBeneficioChild.item(0)).getNodeValue().toString();

					oTupla.setNomTipoBeneficio(result);
				} catch (NullPointerException ignored) {
				}

				try {
					Node FecRecCausante = nodeTupla.item(i);
					Element FecRecCausanteElement = (Element) FecRecCausante;
					NodeList FecRecCausanteLista = FecRecCausanteElement.getElementsByTagName("FecRecCausante");

					Element FecRecCausanteElemento = (Element) FecRecCausanteLista.item(0);
					NodeList FecRecCausanteChild = FecRecCausanteElemento.getChildNodes();
					result = ((Node) FecRecCausanteChild.item(0)).getNodeValue().toString();

					oTupla.setFecRecCausante(result);
				} catch (NullPointerException ignored) {
				}

				try {
					Node FecPagoBeneficio = nodeTupla.item(i);
					Element FecPagoBeneficioElement = (Element) FecPagoBeneficio;
					NodeList FecPagoBeneficioLista = FecPagoBeneficioElement.getElementsByTagName("FecPagoBeneficio");

					Element FecPagoBeneficioElemento = (Element) FecPagoBeneficioLista.item(0);
					NodeList FecPagoBeneficioChild = FecPagoBeneficioElemento.getChildNodes();
					result = ((Node) FecPagoBeneficioChild.item(0)).getNodeValue().toString();

					oTupla.setFecPagoBeneficio(result);
				} catch (NullPointerException ignored) {
				}

				try {
					Node GlosaExtCausante = nodeTupla.item(i);
					Element GlosaExtCausanteElement = (Element) GlosaExtCausante;
					NodeList GlosaExtCausanteLista = GlosaExtCausanteElement.getElementsByTagName("GlosaExtCausante");

					Element GlosaExtCausanteElemento = (Element) GlosaExtCausanteLista.item(0);
					NodeList GlosaExtCausanteChild = GlosaExtCausanteElemento.getChildNodes();
					result = ((Node) GlosaExtCausanteChild.item(0)).getNodeValue().toString();

					oTupla.setGlosaExtCausante(result);
				} catch (NullPointerException ignored) {
				}

				try {
					Node MontoUnitarioBeneficio2 = nodeTupla.item(i);
					Element MontoUnitarioBeneficio2Element = (Element) MontoUnitarioBeneficio2;
					NodeList MontoUnitarioBeneficio2Lista = MontoUnitarioBeneficio2Element.getElementsByTagName("MontoUnitarioBeneficio");

					Element MontoUnitarioBeneficio2Elemento = (Element) MontoUnitarioBeneficio2Lista.item(0);
					NodeList MontoUnitarioBeneficio2Child = MontoUnitarioBeneficio2Elemento.getChildNodes();
					result = ((Node) MontoUnitarioBeneficio2Child.item(0)).getNodeValue().toString();

					oTupla.setMontoUnitarioBeneficio(result);
				} catch (NullPointerException ignored) {
				}
				try {

					Node PuntajeFichaProtSocial = nodeTupla.item(i);
					Element PuntajeFichaProtSocialElement = (Element) PuntajeFichaProtSocial;
					NodeList PuntajeFichaProtSocialLista = PuntajeFichaProtSocialElement.getElementsByTagName("PuntajeFichaProtSocial");

					Element PuntajeFichaProtSocialElemento = (Element) PuntajeFichaProtSocialLista.item(0);
					NodeList PuntajeFichaProtSocialChild = PuntajeFichaProtSocialElemento.getChildNodes();
					result = ((Node) PuntajeFichaProtSocialChild.item(0)).getNodeValue().toString();

					oTupla.setPuntajeFichaProtSocial(result);
				} catch (NullPointerException ignored) {
				}
				try {

					Node TramoAsigFam = nodeTupla.item(i);
					Element TramoAsigFamElement = (Element) TramoAsigFam;
					NodeList TramoAsigFamLista = TramoAsigFamElement.getElementsByTagName("TramoAsigFam");

					Element TramoAsigFamElemento = (Element) TramoAsigFamLista.item(0);
					NodeList TramoAsigFamChild = TramoAsigFamElemento.getChildNodes();
					result = ((Node) TramoAsigFamChild.item(0)).getNodeValue().toString();

					oTupla.setTramoAsigFam(result);
				} catch (NullPointerException ignored) {
				}

				Node nodeTramo = nodeTupla.item(i);
				Element tramoElement = (Element) nodeTramo;
				NodeList tramoLista = tramoElement.getElementsByTagName("Tramo");

				for (int j = 0; j < tramoLista.getLength(); j++) {

					oTramo = new TramoTO();
					oTramo.setTupla(oTupla.getTupla());
					oTramo.setRutCausante(oTupla.getRutCausante());

					try {

						Node Periodo = tramoLista.item(j);
						Element PeriodoElement = (Element) Periodo;
						NodeList PeriodoLista = PeriodoElement.getElementsByTagName("Periodo");

						Element PeriodoElemento = (Element) PeriodoLista.item(0);
						NodeList PeriodoChild = PeriodoElemento.getChildNodes();
						result = ((Node) PeriodoChild.item(0)).getNodeValue().toString();

						oTramo.setPeriodo(result);

					} catch (NullPointerException ignored) {
					}

					try {

						Node NumTramo = tramoLista.item(j);
						Element NumTramoElement = (Element) NumTramo;
						NodeList NumTramoLista = NumTramoElement.getElementsByTagName("NumTramo");

						Element NumTramoElemento = (Element) NumTramoLista.item(0);
						NodeList NumTramoChild = NumTramoElemento.getChildNodes();
						result = ((Node) NumTramoChild.item(0)).getNodeValue().toString();

						oTramo.setNumTramo(result);
					} catch (NullPointerException ignored) {
					}

					try {
						Node IngPromedio2 = tramoLista.item(j);
						Element IngPromedio2Element = (Element) IngPromedio2;
						NodeList IngPromedio2Lista = IngPromedio2Element.getElementsByTagName("IngPromedio");

						Element IngPromedio2Elemento = (Element) IngPromedio2Lista.item(0);
						NodeList IngPromedio2Child = IngPromedio2Elemento.getChildNodes();
						result = ((Node) IngPromedio2Child.item(0)).getNodeValue().toString();

						oTramo.setIngPromedio(result);
					} catch (NullPointerException ignored) {
					}

					try {

						Node MontoUnitarioBeneficio = tramoLista.item(j);
						Element MontoUnitarioBeneficioElement = (Element) MontoUnitarioBeneficio;
						NodeList MontoUnitarioBeneficioLista = MontoUnitarioBeneficioElement.getElementsByTagName("MontoUnitarioBeneficio");

						Element MontoUnitarioBeneficioElemento = (Element) MontoUnitarioBeneficioLista.item(0);
						NodeList MontoUnitarioBeneficioChild = MontoUnitarioBeneficioElemento.getChildNodes();
						result = ((Node) MontoUnitarioBeneficioChild.item(0)).getNodeValue().toString();

						oTramo.setMontoUnitarioBeneficio(result);
					} catch (NullPointerException ignored) {
					}

					tramo.add(oTramo);

				}
				oTupla.setTramo(tramo);
				tupla.add(oTupla);
			}

		} catch (Tupla2Exception ex) {
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		//oTupla.setTramo(tramo);

		return tupla;
	}

}
