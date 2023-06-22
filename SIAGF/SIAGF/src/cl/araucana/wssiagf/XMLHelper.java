

package cl.araucana.wssiagf;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cl.araucana.core.util.AbsoluteDate;
import cl.araucana.core.util.Padder;
import cl.araucana.core.util.logging.LogManager;
import cl.araucana.core.util.xml.XMLUtils;

/*
 * Modificado por David Espinosa el 8/04/08
 * Modificado por David Espinosa el 29/12/09
 * Modificado por David Espinosa el 13/05/10
 * Modificaciones con comentarios
*/

public class XMLHelper implements Operations {

	private static Logger logger = LogManager.getLogger();

	public XMLHelper() {
	}

	public String generateXML(int opID, SIAGFBusinessTO siagfTO, boolean modoTramosRetroactivos)
			throws WSSIAGFException {

		switch (opID) {

			case OP_INGRESO_RECONOCIMIENTO:
				return generateXML_IngresoReconocimiento(opID, siagfTO, modoTramosRetroactivos);

			case OP_EXTINCION_RECONOCIMIENTO:
				return generateXML_ExtincionReconocimiento(opID, siagfTO);

			case OP_ACTUALIZAR_CAUSANTE:
				return generateXML_ActualizarCausante(opID, siagfTO, modoTramosRetroactivos);

			default:
				return "";
		}
	}

	public String getText(int opID, String xmlMessage)
			throws WSSIAGFException {

		switch (opID) {

			case OP_CONSULTA_CAUSANTE:
				return getText_ConsultaCausante(xmlMessage);

			default:
				return "";
		}
	}
	
	public String getTextRetroconpatibilidad(int opID, String xmlMessage)
	throws WSSIAGFException {
	
		switch (opID) {
		
			case OP_CONSULTA_CAUSANTE:
				return getText_ConsultaCausanteRetroconpatibilidad(xmlMessage);
		
			default:
				return "";
		}
	}

	public ResponseTO getResponseTO(int opID, String xmlMessage) {
		switch (opID) {

			case OP_CONSULTA_CAUSANTE:
				return getResponseTO_ConsultaCausante(xmlMessage);

			default:
				return null;
		}
	}

	private String generateXML_IngresoReconocimiento(int opID,
			SIAGFBusinessTO siagfTO, boolean modoTramosRetroactivos) throws WSSIAGFException {

		String xmlData = "";

		xmlData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n";
		xmlData += "<CargaCausante version=\"1.0\">\n";
		xmlData += "    <Documento>\n";
		xmlData += "        " + genLeaf("FechaEmision", siagfTO.getFechaEmision());
		xmlData += "        <Causante>\n";
		xmlData += "            " + genLeaf("TipoCausante", Padder.lpad(siagfTO.getTipoCausante(), 2, '0'));
		xmlData += "            " + genLeaf("SexoCausante", siagfTO.getSexoCausante());
		xmlData += "            " + genLeaf("RutCausante", siagfTO.getRutCausante());
		xmlData += "            " + genLeaf("NomCausante", XMLUtils.encodeXMLContent(siagfTO.getNombreCausante()));

		if (!isOmmited(siagfTO.getFechaNacimientoCausante())) {
			xmlData += "            " + genLeaf("FecNacCausante", siagfTO.getFechaNacimientoCausante());
		}

		if (!isOmmited(siagfTO.getRegionCausante())) {
			xmlData += "            " + genLeaf("RegionCausante", siagfTO.getRegionCausante());
		}

		if (!isOmmited(siagfTO.getComunaCausante())) {
			xmlData += "            " + genLeaf("ComunaCausante", siagfTO.getComunaCausante());
		}

		xmlData += "        </Causante>\n";
		xmlData += "        <Beneficiario>\n";
		xmlData += "            " + genLeaf("TipoBeneficiario", siagfTO.getTipoBeneficiario());
		xmlData += "            " + genLeaf("RutBeneficiario", siagfTO.getRutBeneficiario());
		xmlData += "            " + genLeaf("NomBeneficiario", XMLUtils.encodeXMLContent(siagfTO.getNombreBeneficiario()));

		if (!isOmmited(siagfTO.getRegionBeneficiario())) {
			xmlData += "            " + genLeaf("RegionBeneficiario", siagfTO.getRegionBeneficiario());
		}

		if (!isOmmited(siagfTO.getComunaBeneficiario())) {
			xmlData += "            " + genLeaf("ComunaBeneficiario", siagfTO.getComunaBeneficiario());
		}

		xmlData += "            " + genLeaf("IngPromedio", siagfTO.getIngresoPromedio());
		xmlData += "            <Empleador>\n";
		xmlData += "                " + genLeaf("RutEmpleador", siagfTO.getRutEmpleador());
		xmlData += "                " + genLeaf("NomEmpleador", XMLUtils.encodeXMLContent(siagfTO.getNombreEmpleador()));

		if (!isOmmited(siagfTO.getActividadEconomicaEmpleador())) {
			xmlData += "                " + genLeaf("Acteco", siagfTO.getActividadEconomicaEmpleador());
		}

		if (!isOmmited(siagfTO.getRegionEmpleador())) {
			xmlData += "                " + genLeaf("RegionEmpleador", siagfTO.getRegionEmpleador());
		}

		if (!isOmmited(siagfTO.getComunaEmpleador())) {
			xmlData += "                " + genLeaf("ComunaEmpleador", siagfTO.getComunaEmpleador());
		}

		xmlData += "            </Empleador>\n";
		xmlData += "        </Beneficiario>\n";
		xmlData += "        <EntidadAdm>\n";
		xmlData += "            " + genLeaf("CodEntidadAdm", siagfTO.getCodigoEntidadAdministradora());
		xmlData += "            <Beneficio>\n";
		xmlData += "                " + genLeaf("IdTipoBeneficio", siagfTO.getIdTipoBeneficio());
		xmlData += "                " + genLeaf("FecRecCausante", siagfTO.getFechaReconocimientoCausante());
		xmlData += "                " + genLeaf("FecPagoBeneficio", siagfTO.getFechaPagoBeneficio());
		xmlData += "                " + genLeaf("MontoUnitarioBeneficio", siagfTO.getMontoUnitarioBeneficio());

		if (!isOmmited(siagfTO.getPuntajeFichaProtSocial())) {
			xmlData += "                " + genLeaf("PuntajeFichaProtSocial", siagfTO.getPuntajeFichaProtSocial());
		}

		xmlData += "                " + genLeaf("TramoAsigFam", siagfTO.getTramoASFAM());

		if (!isOmmited(siagfTO.getFechaExtincionCausante())) {
			xmlData += "                " + genLeaf("FecExtCausante", siagfTO.getFechaExtincionCausante());
		}

		if (!isOmmited(siagfTO.getCausaExtincionCausante())) {
			xmlData += "                " + genLeaf("CausaExtCausante", siagfTO.getCausaExtincionCausante());
		}

		xmlData += "            </Beneficio>\n";
		xmlData += "        </EntidadAdm>\n";
		xmlData += "    </Documento>\n";
		xmlData += "</CargaCausante>\n";
		
		if(modoTramosRetroactivos){
			BusinessLogic logic = new BusinessLogic();
			xmlData = logic.addTramosRetroactivos_ingresoReconocimiento(xmlData);
		}
		return xmlData;
	}

	private String generateXML_ExtincionReconocimiento(int opID,
			SIAGFBusinessTO siagfTO) throws WSSIAGFException {

		String xmlData = "";

		xmlData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n";
		xmlData += "<ExtincionReconocimiento>\n";
		xmlData += "    <Documento version=\"1.0\">\n";
		xmlData += "        " + genLeaf("FechaEmision", siagfTO.getFechaEmision());
		xmlData += "        " + genLeaf("FechaExtCausante", siagfTO.getFechaExtincionCausante());

		if (!isOmmited(siagfTO.getCausaExtincionCausante())) {
			xmlData += "        " + genLeaf("CausaExtCausante", siagfTO.getCausaExtincionCausante());
		}

		xmlData += "        <Tupla>\n";
		xmlData += "            " + genLeaf("RutCausante", siagfTO.getRutCausante());
		xmlData += "            " + genLeaf("TipoCausante", Padder.lpad(siagfTO.getTipoCausante(), 2, '0'));
		xmlData += "            " + genLeaf("CodEntidadAdm", siagfTO.getCodigoEntidadAdministradora());
		xmlData += "            " + genLeaf("FecRecCausante", siagfTO.getFechaReconocimientoCausante());
		xmlData += "            " + genLeaf("RutBeneficiario", siagfTO.getRutBeneficiario());
		xmlData += "            " + genLeaf("IdTipoBeneficio", siagfTO.getIdTipoBeneficio());
		xmlData += "        </Tupla>\n";
		xmlData += "    </Documento>\n";
		xmlData += "</ExtincionReconocimiento>\n";

		return xmlData;
	}

	private String generateXML_ActualizarCausante(int opID,
			SIAGFBusinessTO siagfTO, boolean modoTramosRetroactivos) throws WSSIAGFException {

		String xmlData = "";

		xmlData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n";
		xmlData += "<ActualizacionCausante version=\"1.0\">\n";
		xmlData += "    " + genLeaf("FechaEmision", siagfTO.getFechaEmision());
		xmlData += "    <Tupla>\n";
		xmlData += "        " + genLeaf("RutCausante", siagfTO.getRutCausante());
		//Se modifica TipoCausante a CodTipoCausante 8/04/08
		xmlData += "        " + genLeaf("CodTipoCausante", Padder.lpad(siagfTO.getTipoCausante(), 2, '0'));
		xmlData += "        " + genLeaf("CodEntidadAdm", siagfTO.getCodigoEntidadAdministradora());
		xmlData += "        " + genLeaf("FecRecCausante", siagfTO.getFechaReconocimientoCausante());
		xmlData += "        " + genLeaf("RutBeneficiario", siagfTO.getRutBeneficiario());
		xmlData += "        " + genLeaf("CodTipoBeneficio", Padder.lpad(siagfTO.getIdTipoBeneficio(), 2, '0'));
		xmlData += "    </Tupla>\n";
		xmlData += "    <Modificar>\n";

		// Se hace que Ingreso Promedio sea obligatorio
		// despinosa@laaraucana.cl
		// 21/07/09
		//if (!isOmmited(siagfTO.getIngresoPromedio())) {
			xmlData += "        " + genLeaf("IngPromedio", siagfTO.getIngresoPromedio());
		//}

		// Se hace que Monto Unitario de Beneficio sea obligatorio
		// despinosa@laaraucana.cl
		// 28/07/08
		//if (!isOmmited(siagfTO.getMontoUnitarioBeneficio())) {
			xmlData += "        " + genLeaf("MontoUnitarioBeneficio", siagfTO.getMontoUnitarioBeneficio());
		//}

		if (!isOmmited(siagfTO.getTramoASFAM())) {
			xmlData += "        " + genLeaf("TramoAsigFam", siagfTO.getTramoASFAM());
		}
		
		// Se agrega Puntaje de la Ficha de Proteccion social
		/* ADDED Modificación RAC 11606 - Actualiza Carga - Hijos mayores de 18 años
		 28/12/09
		 Se comenta este trozo de código, pues el campo "PuntajeFichaProtSocial" será
		 utilizado para setear el código TipoCausante
		*/
		//if (!isOmmited(siagfTO.getPuntajeFichaProtSocial())) {
		//	xmlData += "        " + genLeaf("PuntajeFichaProtSocial", siagfTO.getPuntajeFichaProtSocial());
		//}

		// Se agrega Fecha a contar de la cual se ha pagado el Beneficio
		if (!isOmmited(siagfTO.getFechaPagoBeneficio())) {
			xmlData += "        " + genLeaf("FecPagoBeneficio", siagfTO.getFechaPagoBeneficio());
		}
		
		// Se agrega Tipo Causante
		/* ADDED Modificación RAC 11606 - Actualiza Carga - Hijos mayores de 18 años
				 28/12/09
		   Se reemplaza el siguiente código: 
		*/
		//if (!isOmmited(siagfTO.getTipoCausante())) {
		//	xmlData += "        " + genLeaf("TipoCausante", siagfTO.getTipoCausante());
		//}
		/*
		 * Por este otro
		 */
		if (!isOmmited(siagfTO.getPuntajeFichaProtSocial())) {
			xmlData += "        " + genLeaf("TipoCausante", siagfTO.getPuntajeFichaProtSocial());
		}

		// Se agrega Tipo Beneficiario
		if (!isOmmited(siagfTO.getTipoBeneficiario())) {
			xmlData += "        " + genLeaf("TipoBeneficiario", siagfTO.getTipoBeneficiario());
		}

		// Se hace opcional el Nombre del Beneficiario
		if (!isOmmited(siagfTO.getTramoASFAM())) {
			xmlData += "        " + genLeaf("NombreBeneficiario", XMLUtils.encodeXMLContent(siagfTO.getNombreBeneficiario()));
		}	
	
		if (!isOmmited(siagfTO.getRegionBeneficiario())) {
			xmlData += "            " + genLeaf("RegionBeneficiario", siagfTO.getRegionBeneficiario());
		}

		if (!isOmmited(siagfTO.getComunaBeneficiario())) {
			xmlData += "            " + genLeaf("ComunaBeneficiario", siagfTO.getComunaBeneficiario());
		}

		if (!isOmmited(siagfTO.getRegionCausante())) {
			xmlData += "            " + genLeaf("RegionCausante", siagfTO.getRegionCausante());
		}

		if (!isOmmited(siagfTO.getComunaCausante())) {
			xmlData += "            " + genLeaf("ComunaCausante", siagfTO.getComunaCausante());
		}

		// Se agrega RUT del empleador
		if (!isOmmited(siagfTO.getRutEmpleador())) {
			xmlData += "        " + genLeaf("RutEmpleador", siagfTO.getRutEmpleador());
		}

		// Se agrega Nombre del empleador
		if (!isOmmited(siagfTO.getNombreEmpleador())) {
			xmlData += "        " + genLeaf("NomEmpleador", XMLUtils.encodeXMLContent(siagfTO.getNombreEmpleador()));
		}
		
		// Se agrega Codigo de la actividad economica del empleador
		if (!isOmmited(siagfTO.getActividadEconomicaEmpleador())) {
			xmlData += "        " + genLeaf("Acteco", siagfTO.getActividadEconomicaEmpleador());
		}
		
		// Se agrega Region del empleador
		if (!isOmmited(siagfTO.getRegionEmpleador())) {
			xmlData += "        " + genLeaf("RegionEmpleador", siagfTO.getRegionEmpleador());
		}

		// Se agrega Comuna del empleador
		if (!isOmmited(siagfTO.getComunaEmpleador())) {
			xmlData += "        " + genLeaf("ComunaEmpleador", siagfTO.getComunaEmpleador());
		}

		xmlData += "    </Modificar>\n";
		xmlData += "</ActualizacionCausante>\n";

		if(modoTramosRetroactivos){
			BusinessLogic logic = new BusinessLogic();
			xmlData = logic.addTramosRetroactivos_actualizarCausante(xmlData);
		}
		return xmlData;
	}

	private boolean isOmmited(int value) {
		return value == 0;
	}

	private boolean isOmmited(String value) {
		return value == null || value.trim().equals("");
	}

	private String genLeaf(String name, int value) {
		return "<" + name + ">" + value + "</" + name + ">\n";
	}

	private String genLeaf(String name, String value) {
		return "<" + name + ">" + value + "</" + name + ">\n";
	}

	private String genLeaf(String name, char value) {
		return "<" + name + ">" + value + "</" + name + ">\n";
	}

	private String getText_ConsultaCausanteRetroconpatibilidad(String xmlMessage)
	throws WSSIAGFException {

		return plainXMLText_getText_ConsultaCausanteRetroconpatibilidad(
				  "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n"
				+ xmlMessage);
	}
	
	private String getText_ConsultaCausante(String xmlMessage)
			throws WSSIAGFException {

		return plainXMLText(
				  "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n"
				+ xmlMessage);
	}

	public ResponseTO getResponseTO_ConsultaCausante(String xmlMessage) {
		return null;
	}

	private String plainXMLText(String xmlContent) throws WSSIAGFException {

		Document xmlDocument = getDocument(xmlContent);
		StringBuffer text = new StringBuffer();

		try {
			dumpTextNodes(xmlDocument, text);

		} catch (IOException e) {
			String message = "Extract plain text from XML failed.";

			logger.severe(message + " [cause=" + e.getMessage() + "]");

			throw new WSSIAGFException(message, e);
		}

		return text.toString();
	}
	
	private String plainXMLText_getText_ConsultaCausanteRetroconpatibilidad(String xmlContent) throws WSSIAGFException {

		Document xmlDocument = getDocument(xmlContent);
		StringBuffer text = new StringBuffer();

		try {
			dumpTextNodes(xmlDocument, text);
			
			//Se cambia el nombre de los tags IngPromedio para diferenciar el ingreso promedio vigente de los retroactivos. 
			//Esto permite que la consulta desde ASFAM pueda operar sin cambios.
			
			//el primer IngPromedio encontrado es el vigente, no debe ser modificado. 
			//La modificación consiste en ir desde el ultimo trackid (marca de tupla) hasta el primero.
			
			int indexTupla = text.lastIndexOf("TrackID") - 7;
			int indexTuplaAnterior = text.length();
			while(indexTupla >= 0){
				int index = text.indexOf("IngPromedio",indexTupla) + 11;
				int cont=0;
				while(index <= indexTuplaAnterior && index > -1){
					index = text.indexOf("IngPromedio", index);
					if(index <= indexTuplaAnterior  && index > -1)
						text = text.replace(index, index + 11, "IngProm"+(++cont));
				}
				indexTuplaAnterior = indexTupla;
				indexTupla = text.lastIndexOf("TrackID", indexTupla) - 7;
			}
		} catch (IOException e) {
			String message = "Extract plain text from XML failed.";

			logger.severe(message + " [cause=" + e.getMessage() + "]");

			throw new WSSIAGFException(message, e);
		}

		return text.toString();
	}
	
	
	private void dumpTextNodes(Node node, StringBuffer text)
			throws IOException {

		switch (node.getNodeType()) {
			case Node.ELEMENT_NODE:
			case Node.DOCUMENT_NODE:
				NodeList children = node.getChildNodes();

				if (children == null) {
					return;
				}

				if (children.getLength() == 1
						&& children.item(0).getNodeType() == Node.TEXT_NODE) {

					String nodeValue = children.item(0).getNodeValue();

					nodeValue = nodeValue.replaceAll("\\n", " ").trim();

					text.append(
							  node.getNodeName()
							+ "="
							//Cambié fin de linea de "\n" a "||"
							+ XMLUtils.encodeXMLContent(nodeValue) + "||");
				} else {
					for (int i = 0; i < children.getLength(); i++) {
						dumpTextNodes(children.item(i), text);
					}
				}
		}
	}

	private Document getDocument(String xmlContent) throws WSSIAGFException {

		logger.finest("Parsing xmlContent=(\n" + xmlContent + "\n)");

		Document xmlDocument = null;

		try {
			DocumentBuilderFactory docBuilderFactory =
					DocumentBuilderFactory.newInstance();

			DocumentBuilder docBuilder =
					docBuilderFactory.newDocumentBuilder();

			xmlDocument =
					docBuilder.parse(
							new ByteArrayInputStream(xmlContent.getBytes()));
		} catch (java.lang.Exception e) {
			String message = "Cannot initilize DOM API.";

			logger.severe(message + " [cause=" + e.getMessage() + "]");

			throw new WSSIAGFException(message, e);
		}

		return xmlDocument;
	}

	private String encodeXMLDate(String content) {
		AbsoluteDate aDate = new AbsoluteDate(content, AbsoluteDate.YMD);

		return aDate.toString(AbsoluteDate.YMD, "-");
	}
}
