

package cl.araucana.wssiagf.wsinterfaces;


import java.beans.IntrospectionException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.araucana.core.util.AbsoluteDate;
import cl.araucana.core.util.AbsoluteDateTime;
import cl.araucana.core.util.AbsoluteTime;
import cl.araucana.core.util.BeanLoader;
import cl.araucana.core.util.CallProfiler;
import cl.araucana.core.util.logging.LogManager;
import cl.araucana.core.util.xml.XMLUtils;
import cl.araucana.wssiagf.BusinessLogic;
import cl.araucana.wssiagf.Codes;
import cl.araucana.wssiagf.Operations;
import cl.araucana.wssiagf.RequestStatus;
import cl.araucana.wssiagf.SIAGFBusinessTO;
import cl.araucana.wssiagf.WSSIAGFConnectorConfig;
import cl.araucana.wssiagf.WSSIAGFException;
import cl.araucana.wssiagf.WebServiceRequest;
import cl.araucana.wssiagf.WebServiceResponse;
import cl.araucana.wssiagf.WebServicesManager;
import cl.araucana.wssiagf.XMLHelper;


public class HTTPWebServicesInterface extends HttpServlet
		implements Operations, RequestStatus, Codes {

	private static Logger logger = LogManager.getLogger();

	private boolean started = false;

	private WSSIAGFConnectorConfig config;

	private int entidadAdministradora;

	private HashMap xmlHelpedSystemIDs = new HashMap();
	private HashMap dummyModeMap = new HashMap();

	private WebServicesManager wsManager;
	private XMLHelper xmlHelper = new XMLHelper();
	private BeanLoader beanLoader;

	//Lógica de negocio
	private BusinessLogic businessLogic;
	private String xmlHelpedSystemIDRetrocompatible;
	private boolean modoTramosRetroactivos;
	
	public void init(ServletConfig servletConfig) throws ServletException {

		ServletContext servletContext = servletConfig.getServletContext();

		config = (WSSIAGFConnectorConfig)
				servletContext.getAttribute("wssiagf.connector.config");

		if (config != null && config.isHTTPInterfaceEnabled()) {
			logger.info("Starting HTTP WSInterface Version 1.0 07/01/2008.");

			entidadAdministradora = config.getEntidadAdministradora();

			String xmlHelpedSystemIDs = config.getXmlHelpedSystemIDs();
			String ids = "";

			if (!xmlHelpedSystemIDs.equals("")) {
				String[] systemIDs = xmlHelpedSystemIDs.split(":");

				for (int i = 0; i < systemIDs.length; i++) {
					this.xmlHelpedSystemIDs.put(systemIDs[i], new Object());

					if (i + 1 < systemIDs.length) {
						ids += systemIDs[i] + ", ";
					} else {
						ids += systemIDs[i];
					}
				}
			}
			
			xmlHelpedSystemIDRetrocompatible = config.getXmlHempedSystemIDRetrocompatible();
			modoTramosRetroactivos = config.getModoTramosRetroactivos();
			
			String opDummyModes = config.getOpDummyModes();

			logger.config("Configuration:");
			logger.config("    entidadAdministradora = " + entidadAdministradora);
			logger.config("    xmlHelpedSystemIDs    = [" + ids + "]");
			logger.config("    opDummyModes          = " + opDummyModes);
			logger.config("");

			String[] modes = opDummyModes.split(":");

			for (int opID = 0; opID < modes.length; opID++) {
				boolean mode = Boolean.valueOf(modes[opID]).booleanValue();

				setDummyMode(opID, mode);
			}

			try {
				beanLoader = new BeanLoader(SIAGFBusinessTO.class);
			} catch (IntrospectionException e) {
				logger.log(
						Level.SEVERE,
						">< HTTP WSInterface cannot be initialized:",
						e);

				return;
			}

			try {
				WebServicesManager.setInstance(
						config.getCredential(),
						config.getServiceURLs(),
						config.getTimeout(),
						config.getNRetries());
			} catch (WSSIAGFException e) {
				logger.log(
						Level.SEVERE,
						">< HTTP WSInterface cannot be initialized:",
						e);

				return;
			}

			wsManager = WebServicesManager.getInstance();
			
			businessLogic = new BusinessLogic();
			logger.info("Business Logic Object created.");
			logger.info("");
			logger.info("HTTP WSInterface started.");

			started = true;
		}
	}

	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		logger.info("new posted request arrived.");

		String contentType = request.getContentType();

		if (!contentType.equals("text/plain")) {
			logger.warning("unexpected request content type.");

			return;
		}

		BufferedReader reader = null;
		PrintWriter writer = null;

		try {
			reader = request.getReader();
			writer = response.getWriter();

			WebServiceRequest postedRequest = getWebServiceRequest(reader);

			processPostedRequest(postedRequest);
			writeWebServiceResponse(postedRequest, writer);
		} catch (Exception e) {
			logger.log(Level.SEVERE, ">< request processing failed:", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {}
			}

			if (writer != null) {
				writer.close();
			}
		}
	}

	private WebServiceRequest getWebServiceRequest(BufferedReader reader)
			throws IOException {

		logger.finest(">>");

		/*
		 * request:
		 *
		 * ------------------------
		 * <sysID>::<reqID>::<opID>
		 * <parameter>
		 * ------------------------
		 *
		 * <parameter> es un documento XML o un string. En caso de ser un
		 * string, su sintaxis debe ser una de las dos siguientes:
		 *
		 * (1)
		 *    <name1>=<value1> newline
		 *          ...
		 *    <nameN>=<valueN> newline
		 *
		 * (2)
		 *    <free text line> newline
		 */
		String line = reader.readLine();

		if (line == null) {
			logger.finest("><");

			throw new IOException("unexpected end of text.");
		}

		String[] tokens = line.split("::");

		if (tokens.length != 3) {
			logger.finest("><");

			throw new IOException("line1 has " + tokens.length + "tokens.");
		}

		WebServiceRequest request = new WebServiceRequest();

		request.setSysID(tokens[0]);
		request.setReqID(getID(tokens[1]));
		request.setOpID(getID(tokens[2]));

		String parameter = "";
		int index = 0;

		if ((line = reader.readLine()) != null) {
			if (line.startsWith("<?xml ")) {
				do {
					parameter += line + "\n";
				} while ((line = reader.readLine()) != null);
			} else if ((index = line.indexOf('=')) > 0) {
				SIAGFBusinessTO siagfTO = new SIAGFBusinessTO();

				do {
					String name = line.substring(0, index);
					String value = line.substring(index + 1);

					parameter += line + "\n";

					try {
						// logger.finest("setProperty: " + name + " " + value);

						beanLoader.setProperty(siagfTO, name, value);
					} catch (IntrospectionException e) {
						logger.finest("><");

						throw new IOException(e.getMessage());
					}
				} while ((line = reader.readLine()) != null
						&& (index = line.indexOf('=')) > 0);

				request.setSiagfTO(siagfTO);
			} else {
				parameter = line;
			}
		}

		// logger.finest("parameter = |" + parameter + "|");

		request.setParameter(parameter);

		logger.finest("<<");

		return request;
	}

	private void writeWebServiceResponse(WebServiceRequest postedRequest,
			PrintWriter writer)	throws IOException {

		/*
		 * response:
		 *
		 * ------------------------------------------------------------
		 * <sysID>::<reqID>::<opID>::<code>::<timestamp>::<serviceTime>
		 * <message>
		 * ------------------------------------------------------------
		 *
		 * <message> es un documento XML o un string. En caso de ser un
		 * string, su sintaxis debe ser una de las dos siguientes:
		 *
		 * (1)
		 *    <name1>=<value1> newline
		 *          ...
		 *    <nameN>=<valueN> newline
		 *
		 * (2)
		 *    <free text line> newline
		 */
		 writer.println(
		 		  postedRequest.getSysID() + "::"
				+ postedRequest.getReqID() + "::"
				+ postedRequest.getOpID() + "::"
				+ postedRequest.getCode() + "::"
				+ postedRequest.getTimestamp() + "::"
				+ postedRequest.getServiceTime() + "\n"
		 		+ postedRequest.getMessage());
	}

	private int getID(String s) throws IOException {

		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			throw new IOException("unexpected id '" + s + "'.");
		}
	}

	private void processPostedRequest(WebServiceRequest postedRequest) {
		logger.finest(">> posted request " + postedRequest.getID());

		if (!started) {
			postedRequest.setCode(WS_RC_INTERFACE_NOT_AVAILABLE);
			postedRequest.setMessage("HTTP WSInterface unavailable.");

			return;
		}

		setInProgressRequest(postedRequest);

		int opID = postedRequest.getOpID();

		if (isDummyMode(opID)) {
			postedRequest.setCode(0);
			postedRequest.setMessage("dummy ok");

			setCompletedRequest(postedRequest);
			logger.finest("<<");

			return;
		}

		try {
			boolean validOperation = true;
			CallProfiler profiler = null;
			WebServiceResponse response;

			switch (opID) {

				case OP_GET_VERSION:
					profiler = wsManager.getVersion();

					break;

				case OP_INGRESO_RECONOCIMIENTO:
					if (isXmlHelpedSystem(postedRequest.getSysID())) {
						SIAGFBusinessTO siagfTO = postedRequest.getSiagfTO();

						if (siagfTO == null) {
							setInvalidRequest(postedRequest);
							validOperation = false;

							break;
						}

						String xmlData =
								xmlHelper.generateXML(
										OP_INGRESO_RECONOCIMIENTO, siagfTO, modoTramosRetroactivos);
						postedRequest.setParameter(xmlData);
					}
					else{
						if(modoTramosRetroactivos){
							//Lógica de negocio para añadir tramos retroactivos a XML
							String xmlData = businessLogic.addTramosRetroactivos_ingresoReconocimiento(postedRequest.getParameter());
							postedRequest.setParameter(xmlData);
						}
					}
					profiler =
							wsManager.ingresoReconocimiento(
									postedRequest.getParameter());

					break;

				case OP_CONSULTA_CAUSANTE:
					profiler =
							wsManager.consultaCausante(
									postedRequest.getParameter());

					response =
							(WebServiceResponse)
									profiler.getReturnedObject();

					if (isXmlHelpedSystem(postedRequest.getSysID())) {
						if (response.getCode() == 0) {
							String message = response.getMessage();
							if(isXmlHelpedSystemRetrocompatible(postedRequest.getSysID())){
								message = xmlHelper.getTextRetroconpatibilidad(
												OP_CONSULTA_CAUSANTE, message);
							}
							else{
								message =
										xmlHelper.getText(
												OP_CONSULTA_CAUSANTE, message);
							}	
							response.setMessage(message);
						}
					}

					break;

				case OP_EXTINCION_RECONOCIMIENTO:
					if (isXmlHelpedSystem(postedRequest.getSysID())) {
						SIAGFBusinessTO siagfTO = postedRequest.getSiagfTO();

						if (siagfTO == null) {
							setInvalidRequest(postedRequest);
							validOperation = false;

							break;
						}

						String xmlData =
								xmlHelper.generateXML(
										OP_EXTINCION_RECONOCIMIENTO, siagfTO, modoTramosRetroactivos);

						postedRequest.setParameter(xmlData);
					}

					profiler =
							wsManager.extincionReconocimiento(
									postedRequest.getParameter());

					break;

				case OP_ACTUALIZAR_CAUSANTE:
					if (isXmlHelpedSystem(postedRequest.getSysID())) {
						SIAGFBusinessTO siagfTO = postedRequest.getSiagfTO();

						if (siagfTO == null) {
							setInvalidRequest(postedRequest);
							validOperation = false;

							break;
						}

						String xmlData =
								xmlHelper.generateXML(
										OP_ACTUALIZAR_CAUSANTE, siagfTO, modoTramosRetroactivos);

						postedRequest.setParameter(xmlData);
					}
					else{
						if(modoTramosRetroactivos){
							//Lógica de negocio para añadir tramos retroactivos a XML
							String xmlData = businessLogic.addTramosRetroactivos_actualizarCausante(postedRequest.getParameter());
							postedRequest.setParameter(xmlData);
						}
					}
					profiler =
							wsManager.actualizarCausante(
									postedRequest.getParameter());

					break;

				default:

					postedRequest.setCode(WS_RC_BAD_OPID);
					postedRequest.setMessage("bad operation id");

					validOperation = false;
			}

			if (validOperation) {
				response = (WebServiceResponse) profiler.getReturnedObject();

				logger.fine("+++ " + opNames[opID] + ": ");

				String parameter = postedRequest.getParameter();

				if (opID == OP_CONSULTA_CAUSANTE) {
					logger.fine("    parameter = (" + parameter + ")");
				} else {
					logger.fine("    parameter = (\n" + parameter + "\n)");
				}

				int code = response.getCode();

				logger.fine("    code      = " + code);

				String message = response.getMessage();

				if (code != WS_RC_OK) {
					logger.fine(
							  "    message   = "
							+ XMLUtils.decodeXMLContent(message));
				} else {
					logger.fine("    message   = " + message);
				}

				logger.fine("    timestamp = " + profiler.timestamp());

				logger.fine(
						"    dt        = " + profiler.elapsedTime() + " ms");

				logger.fine("");

				postedRequest.setCode(response.getCode());
				postedRequest.setMessage(response.getMessage());
				postedRequest.setTimestamp(profiler.timestamp());
				postedRequest.setServiceTime(profiler.elapsedTime());
			}
		} catch (WSSIAGFException e) {
			logger.log(Level.SEVERE, ">< SIAGF call failed:", e);

			postedRequest.setCode(WS_RC_UNEXPECTED_ERROR);
			postedRequest.setMessage("SIAGF call failed");
		}

		setCompletedRequest(postedRequest);
		logger.finest("<<");
	}

	private void setDummyMode(boolean dummyMode) {
		setDummyMode(OP_GET_VERSION, dummyMode);
		setDummyMode(OP_INGRESO_RECONOCIMIENTO, dummyMode);
		setDummyMode(OP_CONSULTA_CAUSANTE, dummyMode);
		setDummyMode(OP_EXTINCION_RECONOCIMIENTO, dummyMode);
		setDummyMode(OP_ACTUALIZAR_CAUSANTE, dummyMode);
	}

	private void setDummyMode(int opID, boolean dummyMode) {
		if (opID < 0 || opID >= opNames.length) {
			return;
		}

		if (dummyMode) {
			dummyModeMap.put(new Integer(opID), new Boolean(true));

			logger.info("'" + opNames[opID] + "' will operate in DUMMY mode.");
		} else {
			dummyModeMap.remove(new Integer(opID));

			logger.info("'" + opNames[opID] + "' will operate in NORMAL mode.");
		}
	}

	private boolean isDummyMode(int opID) {
		return dummyModeMap.get(new Integer(opID)) != null;
	}

	private void setInvalidRequest(WebServiceRequest request) {
		request.setCode(WS_RC_BAD_REQID);
		request.setMessage("invalid request id");

		logger.warning("invalid request '" + request.getID() + "'.");
	}

	private boolean isXmlHelpedSystem(String sysID) {
		return xmlHelpedSystemIDs.get(sysID) != null;
	}
	
	private boolean isXmlHelpedSystemRetrocompatible(String sysID) {
		return xmlHelpedSystemIDRetrocompatible.equals(sysID);
	}
	
	private void setPostedRequest(WebServiceRequest request) {
		request.setStatus(REQ_STAT_POSTED);
		request.setCode(0);
		request.setMessage("unavailable");
		request.setTimestamp(getCurrentTimestamp());
		request.setServiceTime(0L);
	}

	private void setInProgressRequest(WebServiceRequest request) {
		request.setStatus(REQ_STAT_IN_PROGRESS);
	}

	private void setCompletedRequest(WebServiceRequest request) {
		request.setStatus(REQ_STAT_COMPLETED);
	}

	private String getCurrentTimestamp() {
		AbsoluteDateTime aDateTime = new AbsoluteDateTime();
		AbsoluteDate aDate = aDateTime.getAbsoluteDate();
		AbsoluteTime aTime = aDateTime.getAbsoluteTime();

		return aDate.toString(AbsoluteDate.YMD, "-") + " " + aTime.toString();
	}
}
