

package cl.araucana.wssiagf.wsinterfaces;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import cl.araucana.core.util.AbsoluteDate;
import cl.araucana.core.util.AbsoluteDateTime;
import cl.araucana.core.util.AbsoluteTime;
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
import cl.araucana.wssiagfclient.business.model.ActualizaReconocimientoSiagfDTO;
import cl.araucana.wssiagfclient.business.model.ExtincionReconocimientoSiagfDTO;
import cl.araucana.wssiagfclient.business.model.IngresoReconocimientoSiagfDTO;
import cl.araucana.wssiagfclient.business.model.RespuestaConsultaSiagfDTO;
import cl.araucana.wssiagfclient.business.model.TramoSiagfDTO;
import cl.araucana.wssiagfclient.business.model.TuplaSiagfDTO;
import cl.araucana.wssiagfclient.business.services.WSSiagfClientService;
import cl.araucana.wssiagfclient.business.services.impl.WSSiagfClientServiceImpl;
import cl.araucana.wssiagfclient.common.exception.ServiceException;
import cl.araucana.wssiagfclient.common.exception.XmlSiagfException;
import cl.araucana.wssiagfclient.commons.utils.FechaUtil;
import cl.araucana.wssiagfclient.commons.utils.XmlSiagfUtil;


public class DB2WebServicesInterface implements Operations, RequestStatus,
		Codes, Runnable {

	private static Logger logger = LogManager.getLogger();

	private static final int SLEEP_TIME_MAX_FACTOR = 10;

	private WSSIAGFConnectorConfig config;

	private int entidadAdministradora;
	private DataSource dataSource;
	private long sleepTime;
	private String requestTableName;
	private String businessDataTableName;
	private int maxParameterLength;
	private int maxMessageLength;
	private int maxReqPorRevision = 10;
	
	private Connection connection;
	private boolean end;
	private Thread monitor;
	private long currentSleepTime;
	private long maxSleepTime;

	// Request/Response Interface.
	private PreparedStatement getPostedRequestsPreparedStmt;
	private PreparedStatement setInProgressRequestPreparedStmt;
	private PreparedStatement setCompletedRequestPreparedStmt;
	private PreparedStatement cleanupUncompletedRequestsPreparedStmt;
	private PreparedStatement countUncompletedRequestsPreparedStmt;
	private PreparedStatement getUncompletedRequestsPreparedStmt;
	private PreparedStatement setResultPosValidacionPreparedStmt;
	// SIAGF Business Data.
	private PreparedStatement getIngresoReconocimientoPreparedStmt;
	private PreparedStatement getExtincionReconocimientoPreparedStmt;
	private PreparedStatement getActualizarCausantePreparedStmt;

	private HashMap xmlHelpedSystemIDs = new HashMap();
	private HashMap dummyModeMap = new HashMap();

	private WebServicesManager wsManager;
	private XMLHelper xmlHelper = new XMLHelper();

	//Lógica de negocio
	private BusinessLogic businessLogic;
	private String xmlHelpedSystemIDRetrocompatible;
	private boolean modoTramosRetroactivos;
	
	public DB2WebServicesInterface(WSSIAGFConnectorConfig config)
			throws WSSIAGFException {

		logger.info("Starting DB/2 WSInterface Version 1.0 07/01/2008.");
		logger.info("");

		this.config = config;

		this.dataSource = config.getDataSource();
		this.sleepTime = config.getSleepTime();
		this.requestTableName = config.getRequestTableName();
		this.businessDataTableName = config.getBusinessDataTableName();

		maxSleepTime = SLEEP_TIME_MAX_FACTOR * sleepTime;

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

		maxParameterLength = config.getMaxParameterLength();
		maxMessageLength = config.getMaxMessageLength();

		logger.config("Configuration:");
		logger.config("    entidadAdministradora = " + entidadAdministradora);
		logger.config("    sleepTime             = " + sleepTime + " ms");
		logger.config("    requestTableName      = " + requestTableName);
		logger.config("    businessDataTableName = " + businessDataTableName);
		logger.config("    xmlHelpedSystemIDs    = [" + ids + "]");
		logger.config("    opDummyModes          = " + opDummyModes);
		logger.config("    maxParameterLength    = " + maxParameterLength);
		logger.config("    maxMessageLength      = " + maxMessageLength);
		logger.config("");


		String[] modes = opDummyModes.split(":");

		for (int opID = 0; opID < modes.length; opID++) {
			boolean mode = Boolean.valueOf(modes[opID]).booleanValue();

			setDummyMode(opID, mode);
		}

		WebServicesManager.setInstance(
				config.getCredential(),
				config.getServiceURLs(),
				config.getTimeout(),
				config.getNRetries());

		wsManager = WebServicesManager.getInstance();

		businessLogic = new BusinessLogic();
		logger.info("Business Logic Object created.");
		
		logger.info("");
		logger.info("DB/2 WSInterface started.");
	}

	private void openConnection() {
		openConnection(true);
	}

	private void openConnection(boolean logged) {
		try {
			connection = dataSource.getConnection();
		} catch (Exception e) {
			if (logged) {
				logger.log(
						Level.SEVERE, ">< connection to data source failed:", e);
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}

		if (connection != null) {
			initPreparedStmts();
			cleanupUncompletedRequests();
		}
	}

	private void closeConnection() {
		closeConnection(true);
	}

	private void closeConnection(boolean logged) {
		if (connection != null) {
			try {
				connection.close();

				if (logged) {
					logger.info("connection to data source closed.");
				}
			} catch (SQLException e) {}

			connection = null;
		}
	}

	private void resetConnection() {
		if (connection != null) {
			logger.warning("resetting connection to data source ...");

			closeConnection(false);
		}

		openConnection(false);
	}

	private boolean isConnectionClosed() {
		return connection == null;
	}

	private boolean isConnectionOpened() {
		return connection != null;
	}

	private void initPreparedStmts() {
		try {
			getPostedRequestsPreparedStmt =
					connection.prepareStatement(
							  " SELECT CMSYSID, CMREQID, CMOPID, CMPARAM"
							+ "   FROM " + requestTableName
							+ "  WHERE CMSTATUS = 0"
							+ " Fetch first "+maxReqPorRevision+" rows only");

			setInProgressRequestPreparedStmt =
					connection.prepareStatement(
							  " UPDATE " + requestTableName
							+ "    SET CMSTATUS = 1"
							+ "  WHERE CMSYSID = ?"
							+ "    AND CMREQID = ?"
							+ "    AND CMSTATUS = 0");

			setCompletedRequestPreparedStmt =
					connection.prepareStatement(
							  " UPDATE " + requestTableName
							+ "    SET CMSTATUS = 2,"
							+ "        CMPARAM = ?,"
							+ "        CMRETCOD = ?,"
							+ "        CMMESSGE = ?,"
							+ "        CMTS = ?,"
							+ "        CMST = ?"
							+ "  WHERE CMSYSID = ?"
							+ "    AND CMREQID = ?"
							+ "    AND CMSTATUS = 1");
			
			setResultPosValidacionPreparedStmt = 
				connection.prepareStatement(
						  " UPDATE " + requestTableName
						+ "    SET CMRETCOD = ?"
						+ "  WHERE CMSYSID = ?"
						+ "    AND CMREQID = ?");

			cleanupUncompletedRequestsPreparedStmt =
					connection.prepareStatement(
							  " UPDATE " + requestTableName
							+ "    SET CMSTATUS = 2,"
							+ "        CMRETCOD = -1,"
							+ "        CMMESSGE = 'CLEANUP',"
							+ "        CMTS = '" + getCurrentTimestamp() + "',"
							+ "        CMST = 0"
							+ "  WHERE CMSTATUS = 1");

			countUncompletedRequestsPreparedStmt =
					connection.prepareStatement(
							  " SELECT CMSTATUS, COUNT(*)"
							+ "   FROM " + requestTableName
							+ "  WHERE CMSTATUS = 1"
							+ "  GROUP BY CMSTATUS");
			
			getUncompletedRequestsPreparedStmt =
				connection.prepareStatement(
						  " SELECT CMSYSID,CMREQID, CMOPID, CMPARAM "
						+ "   FROM " + requestTableName
						+ "  WHERE CMSTATUS = 1");

			getIngresoReconocimientoPreparedStmt =
					connection.prepareStatement(
							  " SELECT AFSIFEGE, AFSITICA, AFSISECA, AFSIRUCA,"
							+ "        AFSINOCA, AFSIFECA, AFSIREBE, AFSICOBE,"
							+ "        AFSITIBF, AFSIRUBE, AFSINOBE, AFSIREBE,"
							+ "        AFSICOBE, AFSIINPR, AFSIRUEB, AFSINOEB,"
							+ "        AFSIACEE, AFSIREEM, AFSICOEM, AFSITIBE,"
							+ "        AFSIFEAU, AFSIFEPA, AFSIMOBE, AFSIPUFI,"
							+ "        AFSITRAS, AFFEEXTC, AFCAEXTC"
							+ "   FROM " + businessDataTableName
							+ "  WHERE CMSYSID = ?"
							+ "    AND CMREQID = ?");

			getExtincionReconocimientoPreparedStmt =
					connection.prepareStatement(
							  " SELECT AFSIFEGE, AFFEEXTC, AFCAEXTC, AFSIRUCA,"
							+ "        AFSITICA, AFSIFEAU, AFSIRUBE, AFSITIBE"
							+ "   FROM " + businessDataTableName
							+ "  WHERE CMSYSID = ?"
							+ "    AND CMREQID = ?");

			getActualizarCausantePreparedStmt =
					connection.prepareStatement(
							  "  SELECT AFSIFEGE, AFSIRUCA, AFSITICA, AFSIFEAU,"
							+ "         AFSIRUBE, AFSITIBE, AFSIINPR, AFSIMOBE,"
							+ "         AFSITRAS, AFSINOBE, AFSIREBE, AFSICOBE,"
							+ "         AFSIREBE, AFSICOBE, "
							
			//ADDED Modificación REQ 4974 - Mantención Traspaso Afiliados por Empresa
			// 01/09/09
							+ "	        AFSIRUEB, AFSINOEB, AFSIACEE, AFSIREEM,"
							+ "         AFSICOEM,"
							
			//ADDED Modificación RAC 11606 - Actualiza Carga - Hijos mayores de 18 años
			// 28/12/09
			/*
			 * Por requerimiento de la SUSESO, todas las cargas mayores a 18 años
			 * deberán aparecer actualizadas según su nuevo tipo desde el 1° día hábil
			 * de cada año.
			 * El problema que existe para poder realizar esta actualización, radica
			 * en que el tipo de carga forma parte de la "llave primaria", es decir, para 
			 * poder buscar una carga, se debe enviar el código antiguo, y luego de esto
			 * recien poder setearle el nuevo tipo
			 * Debido a que no es posible realizar esta funcionalidad utilizando
			 * el esquema actual de la BD de SIAGF (AFDTA/AFP52L1, es muy costoso
			 * reformatear una tabla para los sistemas basados en AS400)
			 * se considerará el campo AFSIPUFI de la misma tabla, campo que corresponde a
			 * "Puntaje Ficha INP" y que actualmente no está en uso
			 * En este campo deberá aparecer un valor (codigo 6 para los mayores de 18).
			 * Tal valor deberá ser enviado en la tupla de Actualización de Causante
			 */
							+ "         AFSIPUFI"
							
							+ "    FROM " + businessDataTableName
							+ "   WHERE CMSYSID = ?"
							+ "     AND CMREQID = ?");

			logger.info("prepared statements initialized.");
		} catch (SQLException e) {
			logger.log(
					Level.SEVERE,
					">< prepared statements initialization failed:",
					e);

			closePreparedStmts();
			closeConnection();
		}
	}

	private void closePreparedStmts() {
		if (getPostedRequestsPreparedStmt != null) {
			try {
				getPostedRequestsPreparedStmt.close();

				getPostedRequestsPreparedStmt = null;
			} catch (SQLException e) {}
		}

		if (setInProgressRequestPreparedStmt != null) {
			try {
				setInProgressRequestPreparedStmt.close();

				setInProgressRequestPreparedStmt = null;
			} catch (SQLException e) {}
		}

		if (setCompletedRequestPreparedStmt != null) {
			try {
				setCompletedRequestPreparedStmt.close();

				setCompletedRequestPreparedStmt = null;
			} catch (SQLException e) {}
		}

		if (cleanupUncompletedRequestsPreparedStmt != null) {
			try {
				cleanupUncompletedRequestsPreparedStmt.close();

				cleanupUncompletedRequestsPreparedStmt = null;
			} catch (SQLException e) {}
		}

		if (countUncompletedRequestsPreparedStmt != null) {
			try {
				countUncompletedRequestsPreparedStmt.close();

				countUncompletedRequestsPreparedStmt = null;
			} catch (SQLException e) {}
		}
		
		if (getUncompletedRequestsPreparedStmt != null) {
			try {
				getUncompletedRequestsPreparedStmt.close();

				getUncompletedRequestsPreparedStmt = null;
			} catch (SQLException e) {}
		}
		
		
		if (setResultPosValidacionPreparedStmt != null) {
			try {
				setResultPosValidacionPreparedStmt.close();

				setResultPosValidacionPreparedStmt = null;
			} catch (SQLException e) {}
		}
	}

	public void setDummyMode(boolean dummyMode) {
		setDummyMode(OP_GET_VERSION, dummyMode);
		setDummyMode(OP_INGRESO_RECONOCIMIENTO, dummyMode);
		setDummyMode(OP_CONSULTA_CAUSANTE, dummyMode);
		setDummyMode(OP_EXTINCION_RECONOCIMIENTO, dummyMode);
		setDummyMode(OP_ACTUALIZAR_CAUSANTE, dummyMode);
	}

	public void setDummyMode(int opID, boolean dummyMode) {
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

	public boolean isDummyMode(int opID) {
		return dummyModeMap.get(new Integer(opID)) != null;
	}

	public void start() {
		end = false;
		monitor = new Thread(this);

		monitor.setName("DB2WSSIAGF Monitor");
		monitor.setDaemon(true);
		monitor.start();
	}

	public void join() {
		try {
			monitor.join();
		} catch (InterruptedException e) {}
	}

	public void stop() {
		end = true;

		monitor.interrupt();

		try {
			monitor.join(2 * maxSleepTime);
		} catch (InterruptedException e) {}
	}

	public void run() {
		logger.info("started.");

		currentSleepTime = sleepTime;

		openConnection();

		if (isConnectionOpened()) {
			logger.info("waiting requests ...");
		}
		
		WebServiceRequestThread[] wsThreads = new WebServiceRequestThread[maxReqPorRevision];
		
		while (!end) {
			try {
				if (isConnectionClosed()) {
					openConnection(false);

					if (isConnectionOpened()) {
						logger.info(
								  "connection to data source "
								+ "was reestablished.");

						logger.info("waiting requests ...");

						currentSleepTime = sleepTime;
					} else if (currentSleepTime < maxSleepTime) {
						currentSleepTime += sleepTime;

						Thread.sleep(currentSleepTime);

						continue;
					}
				}

				List postedRequests = getPostedRequests();

				if (postedRequests.size() == 0) {
					Thread.sleep(currentSleepTime);

					continue;
				}

				//ADDED ciclo para setear "in progress"
				logger.info("number of posted requests: " + postedRequests.size());
				for (int i = 0; i < postedRequests.size(); i++) {
					setInProgressRequest(
							(WebServiceRequest) postedRequests.get(i));
				}

								
				//ADDED
				for (int i = 0; i < postedRequests.size(); i++) {
					wsThreads[i] = new WebServiceRequestThread((WebServiceRequest) postedRequests.get(i));
					wsThreads[i].setDaemon(true);
					wsThreads[i].start();
				}
				
				//se espera que terminen las hebras clientes. Tiene que ser otro for para que se ejecuten los threads en paralelo.
				for (int i = 0; i < postedRequests.size(); i++) {
					try {          
						wsThreads[i].join();       
					} catch (InterruptedException ignore) {
						ignore.printStackTrace();
						
					}   
				}

			} catch (SQLException e) {
				logger.log(
						Level.SEVERE,
						">< connection to data source failed:",
						e);

				resetConnection();
			} catch (InterruptedException e) {
				logger.warning("interrupted.");
			} catch(Exception e) {
				logger.log(Level.SEVERE, ">< unexpected exception:", e);
			}
		}

		closeConnection();
		logger.info("stopped.");
	}

	private void processPostedRequest(WebServiceRequest postedRequest)
			throws SQLException {

		logger.finest(">> posted request " + postedRequest.getID());

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
						SIAGFBusinessTO siagTO =
								getIngresoReconocimiento(postedRequest);
						if (siagTO == null) {
							setInvalidRequest(postedRequest);
							validOperation = false;

							break;
						}

						String xmlData =
								xmlHelper.generateXML(
										OP_INGRESO_RECONOCIMIENTO, siagTO, modoTramosRetroactivos);
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
						SIAGFBusinessTO siagTO =
								getExtincionReconocimiento(postedRequest);
						if (siagTO == null) {
							setInvalidRequest(postedRequest);
							validOperation = false;

							break;
						}

						String xmlData =
								xmlHelper.generateXML(
										OP_EXTINCION_RECONOCIMIENTO, siagTO, modoTramosRetroactivos);
						postedRequest.setParameter(xmlData);
					}

					profiler =
							wsManager.extincionReconocimiento(
									postedRequest.getParameter());

					break;

				case OP_ACTUALIZAR_CAUSANTE:
					if (isXmlHelpedSystem(postedRequest.getSysID())) {
						SIAGFBusinessTO siagTO =
								getActualizarCausante(postedRequest);
						if (siagTO == null) {
							setInvalidRequest(postedRequest);
							validOperation = false;

							break;
						}

						String xmlData =
								xmlHelper.generateXML(
										OP_ACTUALIZAR_CAUSANTE, siagTO, modoTramosRetroactivos);
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

				logger.fine("    req id    = " + postedRequest.getReqID());

				int code = response.getCode();

				logger.fine("    code      = " + code);

				String message = response.getMessage();

				if (code != WS_RC_OK) {
					logger.fine(
							  "    message   = "
							+ XMLUtils.decodeXMLContent(message));
				} else {
//					Inicio validacion pos llamada a WSSiagf
					if(opID==1 || opID==3 || opID==4){
						PosValidacionThread posValidacion = new PosValidacionThread(postedRequest);
						posValidacion.start();
					}
//					Fin validacion pos llamada a WSSiagf
					
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
			logger.log(Level.SEVERE, ">< SIAGF call failed: reqid=" + postedRequest.getReqID(), e);

			postedRequest.setCode(WS_RC_UNEXPECTED_ERROR);
			postedRequest.setMessage("SIAGF call failed");
		}

		setCompletedRequest(postedRequest);
		logger.finest("<<");
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
	// ADDED modificador synchronized
	private synchronized void setInProgressRequest(WebServiceRequest request)
			throws SQLException {

		request.setStatus(REQ_STAT_IN_PROGRESS);

		setInProgressRequestPreparedStmt.setString(1, request.getSysID());
		setInProgressRequestPreparedStmt.setInt(2, request.getReqID());

		setInProgressRequestPreparedStmt.executeUpdate();
	}

	// ADDED modificador synchronized
	private synchronized void setCompletedRequest(WebServiceRequest request)
			throws SQLException {

		request.setStatus(REQ_STAT_COMPLETED);

		String parameter = request.getParameter();

		if (parameter.length() > maxParameterLength) {
			logger.warning(
					  "parameter to request " + request.getID() + " "
					+ "was truncated. [length=" + parameter.length() + "]");

			parameter = parameter.substring(0, maxMessageLength);
		}

		String message = request.getMessage();

		if (message.length() > maxMessageLength) {
			logger.warning(
					  "response to request " + request.getID() + " "
					+ "was truncated. [length=" + message.length() + "]");

			message = message.substring(0, maxMessageLength);
		}

		setCompletedRequestPreparedStmt.setString(1, parameter);
		setCompletedRequestPreparedStmt.setInt(2, request.getCode());
		setCompletedRequestPreparedStmt.setString(3, message);
		setCompletedRequestPreparedStmt.setString(4, request.getTimestamp());

		//ADDED trunca serviceTime mayores a 6 dígitos, dejándolos en valor 999999
		int serviceTime = (int) request.getServiceTime();
		if (serviceTime > 999999){
			serviceTime = 999999;
		}
		setCompletedRequestPreparedStmt.setInt(
				5, serviceTime);

		setCompletedRequestPreparedStmt.setString(6, request.getSysID());
		setCompletedRequestPreparedStmt.setInt(7, request.getReqID());

		setCompletedRequestPreparedStmt.executeUpdate();
	}

	//ADDED modificador synchronized
	private synchronized List getPostedRequests() throws SQLException {

		ResultSet rs = getPostedRequestsPreparedStmt.executeQuery();
		List postedRequests = new ArrayList();

		try {
			while (rs.next()) {
				WebServiceRequest postedRequest = new WebServiceRequest();

				postedRequest.setSysID(rs.getString(1));
				postedRequest.setReqID(rs.getInt(2));
				postedRequest.setOpID(rs.getInt(3));
				postedRequest.setStatus(REQ_STAT_POSTED);
				postedRequest.setParameter(rs.getString(4).trim());
				postedRequest.setCode(0);
				postedRequest.setMessage("unavailable");
				postedRequest.setTimestamp(getCurrentTimestamp());
				postedRequest.setServiceTime(0L);

				postedRequests.add(postedRequest);
			}
		} finally {
			rs.close();
		}

		return postedRequests;
	}

	private String getCurrentTimestamp() {
		AbsoluteDateTime aDateTime = new AbsoluteDateTime();
		AbsoluteDate aDate = aDateTime.getAbsoluteDate();
		AbsoluteTime aTime = aDateTime.getAbsoluteTime();

		return aDate.toString(AbsoluteDate.YMD, "-") + " " + aTime.toString();
	}
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	//	ADDED modificador synchronized
	private synchronized SIAGFBusinessTO getIngresoReconocimiento(WebServiceRequest request)
			throws SQLException {

		ResultSet rs = null;
		SIAGFBusinessTO siagfTO = new SIAGFBusinessTO();

		try {
			getIngresoReconocimientoPreparedStmt.setString(1, request.getSysID());
			getIngresoReconocimientoPreparedStmt.setInt(2, request.getReqID());

			rs = getIngresoReconocimientoPreparedStmt.executeQuery();

			if (!rs.next()) {
				return null;
			}

			siagfTO.setFechaEmision(rs.getString(1));
			siagfTO.setTipoCausante(rs.getInt(2));
			siagfTO.setSexoCausante(rs.getString(3).charAt(0));
			siagfTO.setRutCausante(rs.getString(4));
			siagfTO.setNombreCausante(rs.getString(5));
			siagfTO.setFechaNacimientoCausante(rs.getString(6));
			siagfTO.setRegionCausante(rs.getInt(7));
			siagfTO.setComunaCausante(rs.getInt(8));
			siagfTO.setTipoBeneficiario(rs.getInt(9));
			siagfTO.setRutBeneficiario(rs.getString(10));
			siagfTO.setNombreBeneficiario(rs.getString(11));
			siagfTO.setRegionBeneficiario(rs.getInt(12));
			siagfTO.setComunaBeneficiario(rs.getInt(13));
			siagfTO.setIngresoPromedio(rs.getInt(14));
			siagfTO.setRutEmpleador(rs.getString(15));
			siagfTO.setNombreEmpleador(rs.getString(16));
			siagfTO.setActividadEconomicaEmpleador(rs.getInt(17));
			siagfTO.setRegionEmpleador(rs.getInt(18));
			siagfTO.setComunaEmpleador(rs.getInt(19));
			siagfTO.setCodigoEntidadAdministradora(entidadAdministradora);
			siagfTO.setIdTipoBeneficio(rs.getInt(20));
			siagfTO.setFechaReconocimientoCausante(rs.getString(21));
			siagfTO.setFechaPagoBeneficio(rs.getString(22));
			siagfTO.setMontoUnitarioBeneficio(rs.getInt(23));
			siagfTO.setPuntajeFichaProtSocial(rs.getInt(24));
			siagfTO.setTramoASFAM(rs.getInt(25));
			siagfTO.setFechaExtincionCausante(rs.getString(26));
			siagfTO.setCausaExtincionCausante(rs.getInt(27));
			
			//Contingencia (DEBE SER ELIMINADO EN FUTURAS VERSIONES)
			if(siagfTO.getFechaExtincionCausante() != null && 
				!siagfTO.getFechaExtincionCausante().equals("") &&
				siagfTO.getFechaReconocimientoCausante() != null &&
				!siagfTO.getFechaReconocimientoCausante().equals("")
			){
				try{
					
					
					AbsoluteDate fechaRecAux = new AbsoluteDate(sdf.parse(siagfTO.getFechaReconocimientoCausante()));
					AbsoluteDate fechaExtAux = new AbsoluteDate(sdf.parse(siagfTO.getFechaExtincionCausante()));
					
					if(fechaRecAux.after(fechaExtAux))
						siagfTO.setFechaExtincionCausante("");
				}catch(Exception e){
					logger.info(e.getMessage());
				}
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
		}

		return siagfTO;
	}

	//	ADDED modificador synchronized
	private synchronized SIAGFBusinessTO getExtincionReconocimiento(WebServiceRequest request)
			throws SQLException {

		ResultSet rs = null;
		SIAGFBusinessTO siagfTO = new SIAGFBusinessTO();

		try {
			getExtincionReconocimientoPreparedStmt.setString(1, request.getSysID());
			getExtincionReconocimientoPreparedStmt.setInt(2, request.getReqID());

			rs = getExtincionReconocimientoPreparedStmt.executeQuery();

			if (!rs.next()) {
				throw new SQLException("Record cannot be found unexpectlly.");
			}

			siagfTO.setFechaEmision(rs.getString(1));
			siagfTO.setFechaExtincionCausante(rs.getString(2));
			siagfTO.setCausaExtincionCausante(rs.getInt(3));
			siagfTO.setRutCausante(rs.getString(4));
			siagfTO.setTipoCausante(rs.getInt(5));
			siagfTO.setCodigoEntidadAdministradora(entidadAdministradora);
			siagfTO.setFechaReconocimientoCausante(rs.getString(6));
			siagfTO.setRutBeneficiario(rs.getString(7));
			siagfTO.setIdTipoBeneficio(rs.getInt(8));
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
		}

		return siagfTO;
	}

	//	ADDED modificador synchronized
	private synchronized SIAGFBusinessTO getActualizarCausante(WebServiceRequest request)
			throws SQLException {

		ResultSet rs = null;
		SIAGFBusinessTO siagfTO = new SIAGFBusinessTO();

		try {
			getActualizarCausantePreparedStmt.setString(1, request.getSysID());
			getActualizarCausantePreparedStmt.setInt(2, request.getReqID());

			rs = getActualizarCausantePreparedStmt.executeQuery();

			if (!rs.next()) {
				throw new SQLException("Record cannot be found unexpectlly.");
			}

			siagfTO.setFechaEmision(rs.getString(1));
			siagfTO.setRutCausante(rs.getString(2));
			siagfTO.setTipoCausante(rs.getInt(3));
			siagfTO.setCodigoEntidadAdministradora(entidadAdministradora);
			siagfTO.setFechaReconocimientoCausante(rs.getString(4));
			siagfTO.setRutBeneficiario(rs.getString(5));
			siagfTO.setIdTipoBeneficio(rs.getInt(6));
			siagfTO.setIngresoPromedio(rs.getInt(7));
			siagfTO.setMontoUnitarioBeneficio(rs.getInt(8));
			siagfTO.setTramoASFAM(rs.getInt(9));
			siagfTO.setNombreBeneficiario(rs.getString(10));
			siagfTO.setRegionBeneficiario(rs.getInt(11));
			siagfTO.setComunaBeneficiario(rs.getInt(12));
			siagfTO.setRegionCausante(rs.getInt(13));
			siagfTO.setComunaCausante(rs.getInt(14));
			
			// ADDED Modificación REQ 4974 - Mantención Traspaso Afiliados por Empresa
			// 01/09/09
			siagfTO.setRutEmpleador(rs.getString(15));
			siagfTO.setNombreEmpleador(rs.getString(16));
			siagfTO.setActividadEconomicaEmpleador(rs.getInt(17));
			siagfTO.setRegionEmpleador(rs.getInt(18));
			siagfTO.setComunaEmpleador(rs.getInt(19));
			
			//ADDED Modificación RAC 11606 - Actualiza Carga - Hijos mayores de 18 años
			// 28/12/09
			//Ver más arriba el detalle de esta modificación
			siagfTO.setPuntajeFichaProtSocial(rs.getInt(20));
			
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
		}

		return siagfTO;
	}

	private void cleanupUncompletedRequests() {
		ResultSet rs2 = null;
		List listRequest = new ArrayList();
		try{
			rs2 = getUncompletedRequestsPreparedStmt.executeQuery();
			while (rs2.next()) {
				String cmSysId = rs2.getString(1);
				int cmReqId = rs2.getInt(2);
				int cmOpId = rs2.getInt(3);
				String cmParam = rs2.getString(4);
				
				WebServiceRequest postedRequest = new WebServiceRequest();
				postedRequest.setSysID(cmSysId);
				postedRequest.setReqID(cmReqId);
				postedRequest.setOpID(cmOpId);
				postedRequest.setParameter(cmParam);
				listRequest.add(postedRequest);
			}
		}catch (SQLException e) {
			logger.log(Level.SEVERE, ">< get request posValidacion failed:", e);
		} finally {
			if (rs2 != null) {
				try {
					rs2.close();
				} catch (SQLException e) {}
			}
		}
		
		ResultSet rs = null;

		logger.info("starting cleanup of expired requests ...");

		try {
			rs = countUncompletedRequestsPreparedStmt.executeQuery();

			while (rs.next()) {
				int status = rs.getInt(1);
				int count = rs.getInt(2);

				switch (status) {

					case REQ_STAT_POSTED:
						logger.warning(
								  "cleaning " + count + " "
								+ "posted requests ...");

						break;

					case REQ_STAT_IN_PROGRESS:
						logger.warning(
								  "cleaning " + count + " "
								+ "in progress requests ...");

						break;

					case REQ_STAT_ABORTED:
						logger.warning(
								  "cleaning " + count + " "
								+ "aborted requests ...");

						break;
				}
			}

			cleanupUncompletedRequestsPreparedStmt.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, ">< cleanup failed:", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
		}
		
		Iterator it = listRequest.iterator();
		while(it.hasNext()){
			WebServiceRequest req = (WebServiceRequest)it.next();
			PosValidacionThread posValidacion = new PosValidacionThread(req);
			posValidacion.start();			
		}

		logger.info("cleanup ended successfully.");
	}

	//ADDED clase WebServiceRequestThread
	private class WebServiceRequestThread extends Thread {

		private WebServiceRequest postedRequest;

		public WebServiceRequestThread(WebServiceRequest request){

			postedRequest = request;
			this.setDaemon(true);
			this.setName(request.getID());
		}

		public void run() {

			try {

				processPostedRequest(postedRequest);

			} catch (SQLException sqle){

				logger.log(
						Level.SEVERE, ">< Thread Name: " + this.getName() +
						". Connection to data source failed:",
						sqle);
			}
		}
	}
	
	private class PosValidacionThread extends Thread {
		private WebServiceRequest request;

		public PosValidacionThread(WebServiceRequest request){
			this.request = request;
 
			this.setDaemon(true);
//			this.setName("posValidacion" + request.getID());
		}

		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			try{
				WSSiagfClientService wsSiagfClientService = new WSSiagfClientServiceImpl();
				RespuestaConsultaSiagfDTO respWSSiagf = null;
				
				switch(request.getOpID()){
//					Ingreso de reconocimiento
					case 1:
//						Parcea el xml enviado al SIAGF
						IngresoReconocimientoSiagfDTO ingRecDTO = XmlSiagfUtil.parseIngresoReconocimiento(request.getParameter());
//						Consulta el causante al SIAGF
						respWSSiagf = wsSiagfClientService.consultaSiagf(ingRecDTO.getRutCausante());
						
						if(respWSSiagf != null && respWSSiagf.getTuplas() != null){
//							Selecciona la tupla 
							TuplaSiagfDTO tuplaSel = seleccionaTupla(respWSSiagf.getTuplas(), ingRecDTO.getRutCausante(),
									ingRecDTO.getRutBeneficiario(), ingRecDTO.getIdTipoBeneficio(), ingRecDTO.getFecRecCausante());

							if(tuplaSel != null){
//								Prepara los datos a comparar
								int codTipoBenefXml = (ingRecDTO.getTipoBeneficiario()!=null)?
										Integer.parseInt(ingRecDTO.getTipoBeneficiario().trim()):0; 
								int codTipoCauXml = (ingRecDTO.getTipoCausante()!=null)?
										Integer.parseInt(ingRecDTO.getTipoCausante().trim()):0;
								int codEntAdminXml = (ingRecDTO.getCodEntidadAdm()!=null)?
										Integer.parseInt(ingRecDTO.getCodEntidadAdm().trim()):0;
								int codTramoActXml = (ingRecDTO.getTramoAsigFam()!=null)?
										Integer.parseInt(ingRecDTO.getTramoAsigFam().trim()):0;
								String rutEmpresaXml = (ingRecDTO.getRutEmpleador()!=null)?
										ingRecDTO.getRutEmpleador().trim():"";
										
								int codTipoBenefTuplaSel = (tuplaSel.getCodTipoBeneficiario()!=null)?
										tuplaSel.getCodTipoBeneficiario().intValue():0;
								int codTipoCauTuplaSel = (tuplaSel.getCodTipoCausante()!=null)?
										tuplaSel.getCodTipoCausante().intValue():0;
								int codEntAdminTuplaSel = (tuplaSel.getCodEntidadAdm()!=null)?
										tuplaSel.getCodEntidadAdm().intValue():0;
								int codTramoActTuplaSel = (tuplaSel.getTramoAsigFam()!=null)?
										tuplaSel.getTramoAsigFam().intValue():0;
								String rutEmpresaTuplaSel = (tuplaSel.getRutEmpleador()!=null)?
										tuplaSel.getRutEmpleador().trim():"";
										
								if(codTipoBenefXml != codTipoBenefTuplaSel
									|| codTipoCauXml != codTipoCauTuplaSel
									|| codEntAdminXml != codEntAdminTuplaSel
									|| codTramoActXml != codTramoActTuplaSel
									|| !rutEmpresaXml.equals(rutEmpresaTuplaSel)){
//									Marcar la operacion como no ejecutada en siagf
									try {
										setResultPosValidacion(request, -45);
									} catch (SQLException e) {
										logger.warning("No fue posible marcar la solicitud con el codigo de error -45");
									}
								}else{
//									Compara los tramos retroactivos
									List listTramoIngreso = ingRecDTO.getTramos();
									List listTramoTupla = tuplaSel.getTramos();
									if(listTramoIngreso==null) listTramoIngreso = new ArrayList();
									if(listTramoTupla==null) listTramoTupla = new ArrayList();
									
									Iterator it = listTramoIngreso.iterator();
									while(it.hasNext()){
										TramoSiagfDTO tramoIngreso = (TramoSiagfDTO)it.next();
										int anoTramoIngreso = tramoIngreso.getPeriodo().intValue();
										
										TramoSiagfDTO tramoTupla = null;
										Iterator it2 = listTramoTupla.iterator();
										while(it2.hasNext()){
											TramoSiagfDTO tramoTuplaTemp = (TramoSiagfDTO)it2.next();
											int anoTramoTupla = tramoTuplaTemp.getPeriodo().intValue();
											if(anoTramoIngreso == anoTramoTupla){
												tramoTupla = tramoTuplaTemp;
												break;
											}
										}
										
										if(tramoTupla != null){
											int numTramoTupla = tramoTupla.getNumTramo().intValue();
											int numTramoIngreso = tramoIngreso.getNumTramo().intValue();
											
											if(numTramoIngreso != numTramoTupla){
												try {
													setResultPosValidacion(request, -45);
													break;
												} catch (SQLException e) {
													logger.warning("No fue posible marcar la solicitud con el codigo de error -45");
												}
											}
										}else{
											try {
												setResultPosValidacion(request, -45);
												break;
											} catch (SQLException e) {
												logger.warning("No fue posible marcar la solicitud con el codigo de error -45");
											}
										}
											
											
									}
								}
							}else{
//								Marcar la operacion como no ejecutada en siagf
								try {
									setResultPosValidacion(request, -45);
								} catch (SQLException e) {
									logger.warning("No fue posible marcar la solicitud con el codigo de error -45");
								}
							}
						}
						break;

//					Extincion de reconocimiento
					case 3:
						ExtincionReconocimientoSiagfDTO extRecDTO = XmlSiagfUtil.parseExtincionReconocimiento(request.getParameter());
						respWSSiagf = wsSiagfClientService.consultaSiagf(extRecDTO.getRutCausante());

						if(respWSSiagf != null && respWSSiagf.getTuplas() != null){
							TuplaSiagfDTO tuplaSel = seleccionaTupla(respWSSiagf.getTuplas(), extRecDTO.getRutCausante(), 
									extRecDTO.getRutBeneficiario(), extRecDTO.getIdTipoBeneficio(), extRecDTO.getFecRecCausante());

							if(tuplaSel != null ){
								if(tuplaSel.getCodEstadoTupla().intValue() != 3){
//									Extincion de reconocimiento		
									try {
										setResultPosValidacion(request, -45);
									} catch (SQLException e) {
										logger.warning("No fue posible marcar la solicitud con el codigo de error -45");
									}
								}
							}else{
//								Extincion de reconocimiento		
								try {
									setResultPosValidacion(request, -45);
								} catch (SQLException e) {
									logger.warning("No fue posible marcar la solicitud con el codigo de error -45");
								}
							}
						}
						break;
						
//					Actualizacion de reconocimiento
					case 4:
						ActualizaReconocimientoSiagfDTO actRecDTO = XmlSiagfUtil.parseActualizaReconocimiento(request.getParameter());
						respWSSiagf = wsSiagfClientService.consultaSiagf(actRecDTO.getRutCausante());
						
						if(respWSSiagf != null && respWSSiagf.getTuplas() != null){
							TuplaSiagfDTO tuplaSel = seleccionaTupla(respWSSiagf.getTuplas(), actRecDTO.getRutCausante(), 
									actRecDTO.getRutBeneficiario(), actRecDTO.getCodTipoBeneficio(), actRecDTO.getFecRecCausante()); 

							if(tuplaSel != null){
//								Prepara los datos a comparar
								int codTipoCauXml = (actRecDTO.getCodTipoCausante()!=null)?
										Integer.parseInt(actRecDTO.getCodTipoCausante().trim()):0;
								int codEntAdminXml = (actRecDTO.getCodEntidadAdm()!=null)?
										Integer.parseInt(actRecDTO.getCodEntidadAdm().trim()):0;
								int codTramoActXml = (actRecDTO.getTramoAsigFam()!=null)?
										Integer.parseInt(actRecDTO.getTramoAsigFam().trim()):0;
								String rutEmpresaXml = (actRecDTO.getRutEmpleador()!=null)?
										actRecDTO.getRutEmpleador().trim():"";
										
								int codTipoCauTuplaSel = (tuplaSel.getCodTipoCausante()!=null)?
										tuplaSel.getCodTipoCausante().intValue():0;
								int codEntAdminTuplaSel = (tuplaSel.getCodEntidadAdm()!=null)?
										tuplaSel.getCodEntidadAdm().intValue():0;
								int codTramoActTuplaSel = (tuplaSel.getTramoAsigFam()!=null)?
										tuplaSel.getTramoAsigFam().intValue():0;
								String rutEmpresaTuplaSel = (tuplaSel.getRutEmpleador()!=null)?
										tuplaSel.getRutEmpleador().trim():"";
										
								if(codTipoCauXml != codTipoCauTuplaSel
									|| codEntAdminXml != codEntAdminTuplaSel
									|| codTramoActXml != codTramoActTuplaSel
									|| !rutEmpresaXml.equals(rutEmpresaTuplaSel)){
//											Marcar la operacion como no ejecutada en siagf
									try {
										setResultPosValidacion(request, -45);
									} catch (SQLException e) {
										logger.warning("No fue posible marcar la solicitud con el codigo de error -45");
									}
								}else{
									List listTramoIngreso = actRecDTO.getTramos();
									List listTramoTupla = tuplaSel.getTramos();

//									List listTramoIngreso = ingRecDTO.getTramos();
//									List listTramoTupla = tuplaSel.getTramos();
									
									if(listTramoIngreso==null) listTramoIngreso = new ArrayList();
									if(listTramoTupla==null) listTramoTupla = new ArrayList();
									
									Iterator it = listTramoIngreso.iterator();
									while(it.hasNext()){
										TramoSiagfDTO tramoIngreso = (TramoSiagfDTO)it.next();
										int anoTramoIngreso = tramoIngreso.getPeriodo().intValue();
										
										TramoSiagfDTO tramoTupla = null;
										Iterator it2 = listTramoTupla.iterator();
										while(it2.hasNext()){
											TramoSiagfDTO tramoTuplaTemp = (TramoSiagfDTO)it2.next();
											int anoTramoTupla = tramoTuplaTemp.getPeriodo().intValue();
											if(anoTramoIngreso == anoTramoTupla){
												tramoTupla = tramoTuplaTemp;
												break;
											}
										}
										
										if(tramoTupla != null){
											int numTramoTupla = tramoTupla.getNumTramo().intValue();
											int numTramoIngreso = tramoIngreso.getNumTramo().intValue();
											
											if(numTramoIngreso != numTramoTupla){
												try {
													setResultPosValidacion(request, -45);
													break;
												} catch (SQLException e) {
													logger.warning("No fue posible marcar la solicitud con el codigo de error -45");
												}
											}
										}else{
											try {
												setResultPosValidacion(request, -45);
												break;
											} catch (SQLException e) {
												logger.warning("No fue posible marcar la solicitud con el codigo de error -45");
											}
										}
											
											
									}
								}
							}else{
//								Marcar la operacion como no ejecutada en siagf
								try {
									setResultPosValidacion(request, -45);
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
						}
						break;
				}
				
			} catch (ServiceException e) {
				e.printStackTrace();
			} catch (XmlSiagfException e) {
				e.printStackTrace();
			}
		}
		
		public TuplaSiagfDTO seleccionaTupla(List tuplas, String rutCausante, String rutBeneficiario, 
				String codBeneficio, String fecRecCausante){
			if(tuplas!=null){
				rutCausante = (rutCausante!=null)?rutCausante.trim():"";
				rutBeneficiario = (rutBeneficiario!=null)?rutBeneficiario.trim():"";
				int codigoBeneficio = (codBeneficio!=null)?Integer.parseInt(codBeneficio.trim()):0;
				fecRecCausante = (fecRecCausante!=null)?fecRecCausante.trim():"";
				
				Iterator it = tuplas.iterator();
				while(it.hasNext()){
					TuplaSiagfDTO tupla = (TuplaSiagfDTO) it.next();
					
					String rutCauTupla = (tupla.getRutCausante()!=null)?tupla.getRutCausante().trim():"";
					String rutBeneTupla = (tupla.getRutBeneficiario()!=null)?tupla.getRutBeneficiario().trim():"";
					int codTipoBenTupla = (tupla.getCodTipoBeneficio()!=null)?tupla.getCodTipoBeneficio().intValue():0;
					String fecRecCauTupla = FechaUtil.formatearFecha("yyyy-MM-dd",tupla.getFecRecCausante());
					fecRecCauTupla = (fecRecCauTupla!=null)?fecRecCauTupla:"";
					
					if(rutCausante.equals(rutCauTupla)
						&& rutBeneficiario.equals(rutBeneTupla)
						&& codigoBeneficio == codTipoBenTupla
						&& fecRecCausante.equals(fecRecCauTupla)){
						return tupla;
					}
				}
			}
			return null;
		}
	}
	
	private synchronized void setResultPosValidacion(WebServiceRequest request, int cod) throws SQLException {

		setResultPosValidacionPreparedStmt.setInt(1, cod);
		setResultPosValidacionPreparedStmt.setString(2, request.getSysID());
		setResultPosValidacionPreparedStmt.setInt(3, request.getReqID());

		setResultPosValidacionPreparedStmt.executeUpdate();
	}
}
