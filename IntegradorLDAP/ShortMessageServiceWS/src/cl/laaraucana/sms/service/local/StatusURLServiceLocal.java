package cl.laaraucana.sms.service.local;

import cl.laaraucana.sms.ibatis.dao.EstadoSMSDAO;
import cl.laaraucana.sms.ibatis.model.EstadoSMS;
import cl.laaraucana.sms.domain.altera.StatusURLData;
import cl.laaraucana.sms.domain.altera.StatusURLRequest;
import cl.laaraucana.sms.domain.altera.StatusURLResponse;
import cl.laaraucana.sms.domain.altera.StatusURLTracker;
import cl.laaraucana.sms.ibatis.dao.EstadoURLDAO;
import cl.laaraucana.sms.ibatis.model.EstadoURL;
import cl.laaraucana.sms.service.provider.MessagingServiceProvider;
import cl.laaraucana.sms.utils.Encryption;
import cl.laaraucana.sms.utils.Configuration;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StatusURLServiceLocal {
    private static final Logger logger = Logger.getLogger(StatusURLServiceLocal.class);
    private static final int taskUpdateStatusURLMaxRetries = Integer
	    .parseInt(Configuration.taskUpdateStatusURLMaxRetries);

    private StatusURLResponse go(String urlKey) {
	Gson gson = new Gson();
	String requestBody;
	StatusURLRequest statusURLRequest = new StatusURLRequest();
	statusURLRequest.setUrlKey(urlKey);

	requestBody = gson.toJson(statusURLRequest);
	requestBody = requestBody.replace("urlKey", "url_key");

	logger.info(String.format("StatusURLRequest (preview): %s", requestBody));

	// Encriptacion
	// Codificacion base64 username:password
	Encryption crypto = new Encryption();
	String username = Configuration.username;
	String password = Configuration.password;
	String authorizationKey = crypto.base64Encode(String.format("%s:%s", username, password));

	// Llamada al servicio del proveedor de mensajeria
	MessagingServiceProvider provider = new MessagingServiceProvider();
	String endpoint = Configuration.endpointStatusURL;
	String jsonResponse = provider.sendMessage(requestBody, authorizationKey, endpoint);
	boolean success = false;

	if (jsonResponse.equals("")) {
	    logger.error("Error during service provider call");
	    return null;
	}

	// Medida profilactica -en este punto de ejecucion.
	jsonResponse = jsonResponse.replace("short_url", "shortUrl");
	jsonResponse = jsonResponse.replace("url_tracker", "urlTracker");
	jsonResponse = jsonResponse.replace("created_at", "createdAt");
	StatusURLResponse response = new Gson().fromJson(jsonResponse, StatusURLResponse.class);
	success = response.getSuccess().equalsIgnoreCase("TRUE");

	if (success)
	    return response;
	else
	    return null;
    }

    private boolean updateRetriesEstadoURL(EstadoURL estadoURL) {
	try {
	    EstadoURLDAO estadoURLDAO = new EstadoURLDAO();
	    estadoURLDAO.updateRetriesEstadoURL(estadoURL);
	    return true;
	} catch (Exception e) {
	    logger.error("Error during updateRetriesEstadoURL operation", e);
	    return false;
	}
    }

    private boolean updateStatusURL(EstadoURL estadoURL) {
	StatusURLResponse response = go(estadoURL.getCodigoURL());

	if (null != response) {
	    try {
		final StatusURLData statusURLData = response.getData();
		final String usuario = estadoURL.getUsuario();
		final String codigoURL = estadoURL.getCodigoURL();
		final String clicks = statusURLData.getCountClick();
		final int clicksCount = Integer.parseInt(clicks);

		estadoURL.setClicks(clicks);

		EstadoURLDAO estadoURLDAO = new EstadoURLDAO();

		if (clicksCount > 0) {
		    ArrayList<StatusURLTracker> urlTracker = statusURLData.getUrlTracker();
		    StatusURLTracker tracker = urlTracker.get(0);
		    estadoURL.setNavegador(tracker.getBrowser());
		    estadoURL.setSistemaOperativo(tracker.getOs());

		    // Fecha
		    final String pattern = "yyyy-MM-dd HH:mm:ss";
		    final SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		    final Date setFechaAperturaDate = dateFormat.parse(tracker.getCreatedAt());
		    final Timestamp setFechaApertura = new java.sql.Timestamp(setFechaAperturaDate.getTime());

		    estadoURL.setFechaApertura(setFechaApertura);
		    estadoURLDAO.updateEstadoURL(estadoURL);
		    logger.info(String.format("Clicks greater than zero for id %s", codigoURL));
		} else {
		    logger.info(String.format("Clicks equal to zero for id %s", codigoURL));
		}
		return true;
	    } catch (Exception e) {
		logger.error("Error during updateStatusURL operation", e);
		return false;
	    }
	}
	return false;
    }

    private boolean saveStatusURL(EstadoSMS estadoSMS) {
	StatusURLResponse response = go(estadoSMS.getCodigoURL());

	if (null != response) {
	    try {
		final StatusURLData statusURLData = response.getData();
		final String usuario = estadoSMS.getUsuario();
		final String codigoURL = estadoSMS.getCodigoURL();
		final String clicks = statusURLData.getCountClick();
		final int clicksCount = Integer.parseInt(clicks);

		EstadoURL estadoURL = new EstadoURL();
		estadoURL.setCodigoURL(codigoURL);
		estadoURL.setUsuario(usuario);
		estadoURL.setClicks(clicks);

		EstadoURLDAO estadoURLDAO = new EstadoURLDAO();

		if (clicksCount == 0) {
		    estadoURLDAO.saveZeroClicksEstadoURL(estadoURL);
		    logger.info(String.format("Clicks equal to zero for id %s", codigoURL));
		} else {
		    ArrayList<StatusURLTracker> urlTracker = statusURLData.getUrlTracker();
		    StatusURLTracker tracker = urlTracker.get(0);
		    estadoURL.setNavegador(tracker.getBrowser());
		    estadoURL.setSistemaOperativo(tracker.getOs());

		    // Fecha de apertura
		    final String pattern = "yyyy-MM-dd HH:mm:ss";
		    final SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		    final Date fechaAperturaDate = dateFormat.parse(tracker.getCreatedAt());
		    final Timestamp fechaApertura = new java.sql.Timestamp(fechaAperturaDate.getTime());

		    estadoURL.setFechaApertura(fechaApertura);
		    estadoURLDAO.saveEstadoURL(estadoURL);
		    logger.info(String.format("Clicks greater than zero for id %s", codigoURL));
		}
		return true;
	    } catch (Exception e) {
		logger.error("Error during saveStatusURL operation", e);
		return false;
	    }
	}
	return false;
    }

    public void checkStatusURL() {
	try {
	    EstadoSMSDAO estadoSMSDAO = new EstadoSMSDAO();
	    List<EstadoSMS> listEstadoSMS = estadoSMSDAO.selectListEstadoSMSForCheckStatusURL();
	    if (listEstadoSMS.size() > 0) {
		for (EstadoSMS estadoSMS : listEstadoSMS) {
		    logger.info(String.format("Task checkStatusURL processing id: %s", estadoSMS.getCodigoURL()));
		    saveStatusURL(estadoSMS);
		}
	    } else {
		logger.info("Task checkStatusURL has no items to process");
	    }
	} catch (Exception e) {
	    logger.error("Error during selectListEstadoSMSForCheckStatusURL DAO operation", e);
	}
    }

    public void updateStatusURL() {
	try {
	    EstadoURLDAO estadoURLDAO = new EstadoURLDAO();
	    List<EstadoURL> listEstadoURL = estadoURLDAO
		    .selectListEstadoSMSForUpdateStatusURL(taskUpdateStatusURLMaxRetries);
	    if (listEstadoURL.size() > 0) {
		for (EstadoURL estadoURL : listEstadoURL) {
		    logger.info(String.format("Task updateStatusURL processing id: %s", estadoURL.getCodigoURL()));
		    updateStatusURL(estadoURL);
		    updateRetriesEstadoURL(estadoURL);
		}
	    } else {
		logger.info("Task updateStatusURL has no items to process");
	    }
	} catch (Exception e) {
	    logger.error("Error during selectListEstadoSMSForUpdateStatusURL DAO operation", e);
	}
    }
}