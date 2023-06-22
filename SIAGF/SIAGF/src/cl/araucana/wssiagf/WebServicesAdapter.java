

package cl.araucana.wssiagf;



import java.io.ByteArrayInputStream;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

import cl.araucana.core.util.AbsoluteDateTime;
import cl.araucana.core.util.xml.XMLUtils;
import cl.araucana.core.util.logging.LogManager;

import cl.araucana.wssiagf.wsservices.actualizarCausante.ActualizarCausanteStub;
import cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteDocument;
import cl.araucana.wssiagf.wsservices.actualizarCausante.xsd.ActualizarCausanteResponseDocument;

import cl.araucana.wssiagf.wsservices.autenticacion.AutenticacionStub;
import cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginDocument;
import cl.araucana.wssiagf.wsservices.autenticacion.xsd.LoginResponseDocument;

import cl.araucana.wssiagf.wsservices.consultaCausante.ConsultaCausanteStub;
import cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteDocument;
import cl.araucana.wssiagf.wsservices.consultaCausante.xsd.ConsultaCausanteResponseDocument;

import cl.araucana.wssiagf.wsservices.extincionReconocimiento.ExtincionReconocimientoStub;
import cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoDocument;
import cl.araucana.wssiagf.wsservices.extincionReconocimiento.xsd.ExtincionReconocimientoResponseDocument;

import cl.araucana.wssiagf.wsservices.ingresoReconocimiento.IngresoReconocimientoStub;
import cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoDocument;
import cl.araucana.wssiagf.wsservices.ingresoReconocimiento.xsd.IngresoReconocimientoResponseDocument;

import cl.araucana.wssiagf.wsservices.version.ExceptionException0;
import cl.araucana.wssiagf.wsservices.version.VersionStub;
import cl.araucana.wssiagf.wsservices.version.xsd.Exception;
import cl.araucana.wssiagf.wsservices.version.xsd.ExceptionDocument;
import cl.araucana.wssiagf.wsservices.version.xsd.GetVersionResponseDocument;


public class WebServicesAdapter implements Codes {

	private static Logger logger = LogManager.getLogger();

	//COMMENTED
	//    private AutenticacionStub autenticacionStub;
	//    private IngresoReconocimientoStub ingresoReconocimientoStub;
	//    private ExtincionReconocimientoStub extincionReconocimientoStub;
	//    private ConsultaCausanteStub consultaCausanteStub;
	//    private ActualizarCausanteStub actualizarCausanteStub;
	//    private VersionStub versionStub;

	private Credential credential;
	private HashMap serviceURLs;
	private long timeout;
	private int nRetries;

	private String token;

	//ADDED
	private static final int WS_AUTENTICACION = 0;
	private static final int WS_INGRESO_RECONOCIMIENTO = 1;
	private static final int WS_EXTINCION_RECONOCIMIENTO = 2;
	private static final int WS_CONSULTA_CAUSANTE = 3;
	private static final int WS_ACTUALIZAR_CAUSANTE = 4;
	private static final int WS_VERSION = 5;
	private static final int WS_WSNUMBER = 6;
	private String[] wsURLs;

	public WebServicesAdapter(
		Credential credential,
		HashMap serviceURLs,
		long timeout,
		int nRetries)
		throws WSSIAGFException {

		this.credential = credential;
		this.serviceURLs = serviceURLs;
		this.timeout = timeout;
		this.nRetries = nRetries;

		//COMMENTED
		//String wsURL = "";

		logger.info("Starting AXIS2 WSAdapter Version 1.0 07/01/2008.");
		logger.info("");

		logger.config("Configuration:");
		logger.config("    credential = [" + credential + "]");
		logger.config("    timeout    = " + timeout + " ms");
		logger.config("    nRetries   = " + nRetries);
		logger.config("");

		logger.config("Initializing web service URLs:");
		
		//ADDED
		wsURLs = getWSURLs(serviceURLs);
		
		logger.config("    Autenticacion           = " + wsURLs[WS_AUTENTICACION]);
		logger.config("    IngresoReconocimiento   = " + wsURLs[WS_INGRESO_RECONOCIMIENTO]);
		logger.config("    ExtincionReconocimiento = " + wsURLs[WS_EXTINCION_RECONOCIMIENTO]);
		logger.config("    ConsultaCausante        = " + wsURLs[WS_CONSULTA_CAUSANTE]);
		logger.config("    ActualizarCausante      = " + wsURLs[WS_ACTUALIZAR_CAUSANTE]);
		logger.config("    Version                 = " + wsURLs[WS_VERSION]);

		//COMMENTED
		//		try {
		//			wsURL = getWSURL(serviceURLs, "Autenticacion");
		//			autenticacionStub =	new AutenticacionStub(wsURL);
		//
		//			logger.config("    Autenticacion           = " + wsURL);
		//
		//			wsURL = getWSURL(serviceURLs, "IngresoReconocimiento");
		//			ingresoReconocimientoStub =	new IngresoReconocimientoStub(wsURL);
		//
		//			logger.config("    IngresoReconocimiento   = " + wsURL);
		//
		//			wsURL = getWSURL(serviceURLs, "ExtincionReconocimiento");
		//
		//			extincionReconocimientoStub =
		//					new ExtincionReconocimientoStub(wsURL);
		//
		//			logger.config("    ExtincionReconocimiento = " + wsURL);
		//
		//			wsURL = getWSURL(serviceURLs, "ConsultaCausante");
		//			consultaCausanteStub = new ConsultaCausanteStub(wsURL);
		//
		//			logger.config("    ConsultaCausante        = " + wsURL);
		//
		//			wsURL = getWSURL(serviceURLs, "ActualizarCausante");
		//			actualizarCausanteStub = new ActualizarCausanteStub(wsURL);
		//
		//			logger.config("    ActualizarCausante      = " + wsURL);
		//
		//			wsURL = getWSURL(serviceURLs, "Version");
		//			versionStub = new VersionStub(wsURL);
		//
		//			logger.config("    Version                 = " + wsURL);
		//		} catch (AxisFault e) {
		//			String message =
		//					  "Web service cannot be initialized with URL "
		//					+ "'" + wsURL + "'.";
		//		
		//			logger.severe(message + " [cause=" + e.getMessage() + "]");
		//		
		//			throw new WSSIAGFException(message);
		//		}

		logger.config("web service URLs initializated.");

		logger.info("");
		logger.info("WSAdapter started.");
	}

	public WebServiceResponse getVersion() throws WSSIAGFException {

		logger.finest(">>");

		String version = "unknown";

		try {
			//ADDED
			VersionStub versionStub = new VersionStub(wsURLs[WS_VERSION]);

			Options options = versionStub._getServiceClient().getOptions();
				
			options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer((int) timeout));
			options.setProperty(HTTPConstants.SO_TIMEOUT, new Integer((int) timeout));

			GetVersionResponseDocument response = versionStub.getVersion();

			version = response.getGetVersionResponse().getReturn();
			
		//ADDED
		} catch (AxisFault ax) {
			String message =
					  "Version web service communication error.";
		
			logger.severe(message + " [cause=" + ax.getMessage() + "]");
		
			throw new WSSIAGFException(message);
			
		} catch (java.lang.Exception e) {
			String message = "getVersion() call failed.";

			logger.severe(">< " + message + " [cause=" + e.getMessage() + "]");

			throw new WSSIAGFException(message, e);
		}

		WebServiceResponse response = new WebServiceResponse();

		response.setCode(0);
		response.setMessage(version);

		logger.finest("<<");

		return response;
	}

	public WebServiceResponse ingresoReconocimiento(String xmlDetalle)
		throws WSSIAGFException {

		logger.finest(">>");

		WebServiceResponse wsResponse = null;
		int retry = 0;

		do {
			String xmlResponse = "";

			try {
				String iToken = getToken();

				IngresoReconocimientoDocument ingresoDoc =
					IngresoReconocimientoDocument.Factory.newInstance();

				IngresoReconocimientoDocument.IngresoReconocimiento ingreso =
					ingresoDoc.addNewIngresoReconocimiento();

				ingreso.setToken(iToken);
				ingreso.setXmlDetalle(xmlDetalle);

				//ADDED
				IngresoReconocimientoStub ingresoReconocimientoStub =
					new IngresoReconocimientoStub(wsURLs[WS_INGRESO_RECONOCIMIENTO]);

				Options options = ingresoReconocimientoStub._getServiceClient().getOptions();
				
				options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer((int) timeout));
				options.setProperty(HTTPConstants.SO_TIMEOUT, new Integer((int) timeout));

				IngresoReconocimientoResponseDocument response =
					ingresoReconocimientoStub.ingresoReconocimiento(ingresoDoc);

				xmlResponse =
					response.getIngresoReconocimientoResponse().getReturn();
			
			//ADDED
			} catch (AxisFault ax) {
				String message =
						  "IngresoReconocimiento web service communication error.";
	
				logger.severe(message + " [cause=" + ax.getMessage() + "]");
	
				throw new WSSIAGFException(message);
		
			} catch (java.lang.Exception e) {
				String message = "ingresoReconocimiento() call failed.";

				logger.severe(
					">< " + message + " [cause=" + e.getMessage() + "]");

				throw new WSSIAGFException(message, e);
			}

			wsResponse = getWebServiceResponse(xmlResponse);
		} while (
			wsResponse.getCode() == WS_RC_BAD_ACCESS_TOKEN
				&& clearToken()
				&& ++retry < nRetries);

		if (wsResponse.getCode() == WS_RC_BAD_ACCESS_TOKEN) {
			throw new WSSIAGFException("Cannot renew access token.");
		}

		logger.finest("<<");

		return wsResponse;
	}

	public WebServiceResponse extincionReconocimiento(String xmlDetalle)
		throws WSSIAGFException {

		logger.finest(">>");

		WebServiceResponse wsResponse = null;
		int retry = 0;

		do {
			String xmlResponse = "";

			try {
				String iToken = getToken();

				ExtincionReconocimientoDocument extincionDoc =
					ExtincionReconocimientoDocument.Factory.newInstance();

				ExtincionReconocimientoDocument
					.ExtincionReconocimiento extincion =
					extincionDoc.addNewExtincionReconocimiento();

				extincion.setToken(iToken);
				extincion.setXmlDetalle(xmlDetalle);

				//ADDED
				ExtincionReconocimientoStub extincionReconocimientoStub =
					new ExtincionReconocimientoStub(wsURLs[WS_EXTINCION_RECONOCIMIENTO]);

				Options options = extincionReconocimientoStub._getServiceClient().getOptions();
				
				options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer((int) timeout));
				options.setProperty(HTTPConstants.SO_TIMEOUT, new Integer((int) timeout));

				ExtincionReconocimientoResponseDocument response =
					extincionReconocimientoStub.extincionReconocimiento(
						extincionDoc);

				xmlResponse =
					response.getExtincionReconocimientoResponse().getReturn();
		
			//ADDED
			} catch (AxisFault ax) {
				String message =
						  "ExtincionReconocimiento web service communication error.";

				logger.severe(message + " [cause=" + ax.getMessage() + "]");

				throw new WSSIAGFException(message);

			} catch (java.lang.Exception e) {
				String message = "extincionReconocimiento() call failed.";

				logger.severe(
					">< " + message + " [cause=" + e.getMessage() + "]");

				throw new WSSIAGFException(message, e);
			}

			wsResponse = getWebServiceResponse(xmlResponse);
		} while (
			wsResponse.getCode() == WS_RC_BAD_ACCESS_TOKEN
				&& clearToken()
				&& ++retry < nRetries);

		if (wsResponse.getCode() == WS_RC_BAD_ACCESS_TOKEN) {
			throw new WSSIAGFException("Cannot renew access token.");
		}

		logger.finest("<<");

		return wsResponse;
	}

	public WebServiceResponse consultaCausante(String rutCausante)
		throws WSSIAGFException {

		logger.finest(">>");

		WebServiceResponse wsResponse = null;
		int retry = 0;

		do {
			String xmlResponse = "";

			try {
				String iToken = getToken();

				ConsultaCausanteDocument consultaDoc =
					ConsultaCausanteDocument.Factory.newInstance();

				ConsultaCausanteDocument.ConsultaCausante consulta =
					consultaDoc.addNewConsultaCausante();

				consulta.setToken(iToken);
				consulta.setRutCausante(rutCausante);

				//ADDED
				ConsultaCausanteStub consultaCausanteStub =
					new ConsultaCausanteStub(wsURLs[WS_CONSULTA_CAUSANTE]);

				Options options = consultaCausanteStub._getServiceClient().getOptions();
				
				options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer((int) timeout));
				options.setProperty(HTTPConstants.SO_TIMEOUT, new Integer((int) timeout));

				ConsultaCausanteResponseDocument response =
					consultaCausanteStub.consultaCausante(consultaDoc);

				xmlResponse =
					response.getConsultaCausanteResponse().getReturn();
			//ADDED
			} catch (AxisFault ax) {
				String message =
						  "ConsultaCausante web service communication error.";

				logger.severe(message + " [cause=" + ax.getMessage() + "]");

				throw new WSSIAGFException(message);

			} catch (java.lang.Exception e) {
				String message = "consultaReconocimiento() call failed.";

				logger.severe(
					">< " + message + " [cause=" + e.getMessage() + "]");

				throw new WSSIAGFException(message, e);
			}

			wsResponse = getWebServiceResponse(xmlResponse);
		} while (
			wsResponse.getCode() == WS_RC_BAD_ACCESS_TOKEN
				&& clearToken()
				&& ++retry < nRetries);

		if (wsResponse.getCode() == WS_RC_BAD_ACCESS_TOKEN) {
			throw new WSSIAGFException("Cannot renew access token.");
		}

		logger.finest("<<");

		return wsResponse;
	}

	public WebServiceResponse actualizarCausante(String xmlDetalle)
		throws WSSIAGFException {

		logger.finest(">>");

		WebServiceResponse wsResponse = null;
		int retry = 0;

		do {
			String xmlResponse = "";

			try {
				String iToken = getToken();

				ActualizarCausanteDocument actualizarDoc =
					ActualizarCausanteDocument.Factory.newInstance();

				ActualizarCausanteDocument.ActualizarCausante actualizar =
					actualizarDoc.addNewActualizarCausante();

				actualizar.setToken(iToken);
				actualizar.setXmlDetalle(xmlDetalle);

				// ADDED
				ActualizarCausanteStub actualizarCausanteStub =
					new ActualizarCausanteStub(wsURLs[WS_ACTUALIZAR_CAUSANTE]);

				Options options = actualizarCausanteStub._getServiceClient().getOptions();
				
				options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer((int) timeout));
				options.setProperty(HTTPConstants.SO_TIMEOUT, new Integer((int) timeout));
				
				ActualizarCausanteResponseDocument response =
					actualizarCausanteStub.actualizarCausante(actualizarDoc);

				xmlResponse =
					response.getActualizarCausanteResponse().getReturn();
					
			//ADDED
			} catch (AxisFault ax) {
				String message =
						  "ActualizarCausante web service communication error.";

				logger.severe(message + " [cause=" + ax.getMessage() + "]");

				throw new WSSIAGFException(message);
				
			} catch (java.lang.Exception e) {
				String message = "actualizarReconocimiento() call failed.";

				logger.severe(
					">< " + message + " [cause=" + e.getMessage() + "]");

				throw new WSSIAGFException(message, e);
			}

			wsResponse = getWebServiceResponse(xmlResponse);
		} while (
			wsResponse.getCode() == WS_RC_BAD_ACCESS_TOKEN
				&& clearToken()
				&& ++retry < nRetries);

		if (wsResponse.getCode() == WS_RC_BAD_ACCESS_TOKEN) {
			throw new WSSIAGFException("Cannot renew access token.");
		}

		logger.finest("<<");

		return wsResponse;
	}

	private String getWSURL(HashMap serviceURLs, String key)
		throws WSSIAGFException {

		String wsURL = (String) serviceURLs.get(key);

		if (wsURL == null) {
			String message = "URL not found for web service '" + key + "'.";

			logger.severe(message);

			throw new WSSIAGFException(message);
		}

		return wsURL;
	}

	private synchronized String getToken() throws WSSIAGFException {

		if (token == null) {
			WebServiceResponse response = login();
			int code = response.getCode();
			String message = response.getMessage();

			if (code == WS_RC_OK) {
				token = message;

				logger.info("new token: " + token);
			} else {
				token = null;

				logger.severe(
					"authentication failed. "
						+ "[code="
						+ code
						+ " message="
						+ message
						+ "]");

				throw new WSSIAGFException("Authentication failed");
			}
		}

		return token;
	}

	private synchronized boolean clearToken() {
		token = null;

		return true;
	}

	private WebServiceResponse login() throws WSSIAGFException {

		logger.finest(">>");

		String xmlResponse = "";

		try {
			LoginDocument loginDoc = LoginDocument.Factory.newInstance();
			LoginDocument.Login login = loginDoc.addNewLogin();
			AutenticacionStub autenticacionStub =
				new AutenticacionStub(wsURLs[WS_AUTENTICACION]);

			//ADDED
			Options options = autenticacionStub._getServiceClient().getOptions();
				
			options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer((int) timeout));
			options.setProperty(HTTPConstants.SO_TIMEOUT, new Integer((int) timeout));

			login.setCodigoEntidad(credential.getID());
			login.setLoginUsuario(credential.getUserName());
			login.setClaveUsuario(credential.getPassword());

			LoginResponseDocument response = autenticacionStub.login(loginDoc);

			xmlResponse = response.getLoginResponse().getReturn();

		//ADDED
		} catch (AxisFault ax) {
			String message =
					  "Autenticacion web service communication error.";

			logger.severe(message + " [cause=" + ax.getMessage() + "]");

			throw new WSSIAGFException(message);

		} catch (java.lang.Exception e) {
			String message = "login() call failed.";

			logger.severe(message + " [cause=" + e.getMessage() + "]");

			throw new WSSIAGFException(message, e);
		}

		WebServiceResponse response = getWebServiceResponse(xmlResponse);

		logger.finest("<<");

		return response;
	}

	/*
	 *  XML Response Syntax:
	 *
	 *      <Respuesta>
	 *          <Codigo>...</Codigo>
	 *          <Mensaje>...</Mensaje>
	 *      </Respuesta>)
	 */
	private WebServiceResponse getWebServiceResponse(String xmlResponse) {
		logger.finest("Parsing xmlResponse=(\n" + xmlResponse + "\n)");

		if (xmlResponse == null || xmlResponse.trim().equals("")) {
			return generateUnexpectedWebServiceResponse("Unexpected XML response");
		}

		Document xmlDocument = null;

		try {
			DocumentBuilderFactory docBuilderFactory =
				DocumentBuilderFactory.newInstance();

			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

			xmlDocument =
				docBuilder.parse(
					new ByteArrayInputStream(xmlResponse.getBytes()));
		} catch (java.lang.Exception e) {
			String message = "Cannot parse XML response.";

			logger.severe(message + " [cause=" + e.getMessage() + "]");

			return generateUnexpectedWebServiceResponse(message);
		}

		NodeList children = xmlDocument.getChildNodes();

		if (children == null
			|| children.getLength() != 1
			|| !children.item(0).getNodeName().equals("Respuesta")) {

			return generateUnexpectedWebServiceResponse("Unexpected XML response");
		}

		children = children.item(0).getChildNodes();

		if (children == null) {
			return generateUnexpectedWebServiceResponse("Empty XML response");
		}

		boolean hasSeqNo = false;
		boolean hasDocNo = false;
		boolean hasCode = false;
		boolean hasMessage = false;

		int seqNo = 0;
		int docNo = 0;
		int code = 0;
		String message = null;

		for (int i = 0; i < children.getLength(); i++) {
			Node xmlNode = children.item(i);
			short nodeType = xmlNode.getNodeType();

			if (nodeType != Node.ELEMENT_NODE) {
				continue;
			}

			String nodeName = xmlNode.getNodeName();

			logger.finest("nodeName = " + nodeName);

			if (nodeName.equals("NroAtencion")) {
				if (hasSeqNo) {
					return generateUnexpectedWebServiceResponse("Duplicated 'NroAtencion'");
				}

				try {
					String value = getTrimmedText(xmlResponse, xmlNode);

					logger.finest("value = |" + value + "|");

					if (value == null) {
						throw new NumberFormatException();
					}

					seqNo = Integer.parseInt(value);
				} catch (NumberFormatException e) {
					return generateUnexpectedWebServiceResponse("Unexpected value for 'NroAtencion'");
				}

				hasSeqNo = true;
			} else if (nodeName.equals("NroDocumento")) {
				if (hasDocNo) {
					return generateUnexpectedWebServiceResponse("Duplicated 'NroDocumento'");
				}

				try {
					String value = getTrimmedText(xmlResponse, xmlNode);

					logger.finest("value = |" + value + "|");

					if (value == null) {
						throw new NumberFormatException();
					}

					docNo = Integer.parseInt(value);
				} catch (NumberFormatException e) {
					return generateUnexpectedWebServiceResponse("Unexpected value for 'NroDocumento'");
				}

				hasDocNo = true;
			} else if (nodeName.equals("Codigo")) {
				if (hasCode) {
					return generateUnexpectedWebServiceResponse("Duplicated 'Codigo'.");
				}

				try {
					String value = getTrimmedText(xmlResponse, xmlNode);

					logger.finest("value = |" + value + "|");

					if (value == null) {
						throw new NumberFormatException();
					}

					code = Integer.parseInt(value);
				} catch (NumberFormatException e) {
					return generateUnexpectedWebServiceResponse("Unexpected value for 'Codigo'");
				}

				hasCode = true;
			} else if (nodeName.equals("Mensaje")) {
				if (hasMessage) {
					return generateUnexpectedWebServiceResponse("Duplicated 'Mensaje'");
				}

				StringBuffer xmlContent = new StringBuffer();

				dumpXMLBranch(xmlResponse, xmlNode, xmlContent);

				message = trimText(xmlContent.toString());

				logger.finest("message = |" + message + "|");

				hasMessage = true;
			} else if (!nodeName.equals("TrazaError")) {
				return generateUnexpectedWebServiceResponse(
					"Unexpected XML element '" + nodeName + "'");
			}
		}

		if (!hasCode || !hasMessage) {
			return generateUnexpectedWebServiceResponse("Unexpected XML response");
		}

		WebServiceResponse response = new WebServiceResponse();

		response.setSeqNo(seqNo);
		response.setDocNo(docNo);
		response.setCode(code);
		response.setMessage(message);

		return response;
	}

	private WebServiceResponse generateUnexpectedWebServiceResponse(String message) {

		WebServiceResponse response = new WebServiceResponse();

		response.setCode(WS_RC_UNEXPECTED_ERROR);
		response.setMessage(message);

		return response;
	}

	private String getTrimmedText(String xmlResponse, Node xmlNode) {
		return trimText(getText(xmlResponse, xmlNode));
	}

	private String trimText(String text) {
		if (text != null) {
			text = text.replaceAll("\\\\r\\\\n", " ");
			text = text.replaceAll("\\\\n", " ").trim();
		}

		return text;
	}

	private String getText(String xmlResponse, Node xmlNode) {
		NodeList elementChildren = xmlNode.getChildNodes();

		if (elementChildren == null
			|| elementChildren.getLength() != 1
			|| elementChildren.item(0).getNodeType() != Node.TEXT_NODE) {

			return null;
		}

		return elementChildren.item(0).getNodeValue();
	}

	private void dumpXMLBranch(
		String xmlResponse,
		Node xmlNode,
		StringBuffer xmlContent) {

		NodeList elementChildren = xmlNode.getChildNodes();

		if (elementChildren != null) {
			for (int i = 0; i < elementChildren.getLength(); i++) {
				Node child = elementChildren.item(i);

				switch (child.getNodeType()) {
					case Node.ELEMENT_NODE :
						String nodeName = child.getNodeName();

						xmlContent.append("<" + nodeName + ">");
						dumpXMLBranch(xmlResponse, child, xmlContent);
						xmlContent.append("</" + nodeName + ">");

						break;

					case Node.TEXT_NODE :
						xmlContent.append(
							XMLUtils.encodeXMLContent(child.getNodeValue()));
				}
			}
		}
	}

	//ADDED
	private String[] getWSURLs(HashMap serviceURLs) throws WSSIAGFException {

		String[] wsURLs = new String[WS_WSNUMBER];

		wsURLs[WS_AUTENTICACION] = getWSURL(serviceURLs, "Autenticacion");
		wsURLs[WS_INGRESO_RECONOCIMIENTO] =
			getWSURL(serviceURLs, "IngresoReconocimiento");
		wsURLs[WS_EXTINCION_RECONOCIMIENTO] =
			getWSURL(serviceURLs, "ExtincionReconocimiento");
		wsURLs[WS_CONSULTA_CAUSANTE] = getWSURL(serviceURLs, "ConsultaCausante");
		wsURLs[WS_ACTUALIZAR_CAUSANTE] =
			getWSURL(serviceURLs, "ActualizarCausante");
		wsURLs[WS_VERSION] = getWSURL(serviceURLs, "Version");

		return wsURLs;
	}
}
