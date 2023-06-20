package cl.laaraucana.sms.service.remote;

import cl.laaraucana.sms.domain.altera.*;
import cl.laaraucana.sms.domain.exchange.StatusList;
import cl.laaraucana.sms.domain.exchange.StatusURLInput;
import cl.laaraucana.sms.domain.exchange.StatusURLOutput;
import cl.laaraucana.sms.ibatis.dao.EstadoURLDAO;
import cl.laaraucana.sms.ibatis.model.EstadoSMS;
import cl.laaraucana.sms.ibatis.model.EstadoURL;
import cl.laaraucana.sms.service.security.AuthorizationService;
import cl.laaraucana.sms.service.provider.MessagingServiceProvider;
import cl.laaraucana.sms.utils.Configuration;
import cl.laaraucana.sms.utils.Encryption;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class StatusURLService {

    private static final Logger logger = Logger.getLogger(StatusURLService.class);

    public StatusURLOutput statusURL(StatusURLInput input) {
	StatusURLOutput output = new StatusURLOutput();
	output.setUsername(input.getUsername());
	output.setId(input.getId());

	try {
	    EstadoURLDAO estadoURLDAO = new EstadoURLDAO();
	    EstadoURL estadoURL = estadoURLDAO.selectEstadoURL(input.getId());
	    if (null != estadoURL && Integer.parseInt(estadoURL.getClicks()) > 0) {
		EstadoSMS estadoSMS = estadoURLDAO.selectEstadoSMS(input.getId());
		output.setResult("URL Visitada");
		output.setRut(estadoSMS.getRut());
		output.setDv(estadoSMS.getDigitoValidador());
		output.setPhone(estadoSMS.getCelular());
		output.setClicks(estadoURL.getClicks());
		output.setBrowser(estadoURL.getNavegador());
		output.setSo(estadoURL.getSistemaOperativo());
		output.setIp("");
		
		final Date date = new Date();
		final String pattern = "yyyy-MM-dd HH:mm:ss";
		final String dateFormatted = new SimpleDateFormat(pattern).format(date);
		output.setDate(dateFormatted);
		output.setStatusCode(StatusList.SUCCESS_URL_VISITED_STATUS_CODE);
		output.setStatusDescription(StatusList.SUCCESS_URL_VISITED_STATUS_DESCRIPTION);
		return output;
	    } else {

		try {
		    // Validacion de parametros de entrada (peticion del cliente)
		    boolean areInputParametersValid = null != input.getUsername() && null != input.getPassword();
		    if (!areInputParametersValid) {
			output.setStatusCode(StatusList.ERR_INVALID_CREDENTIALS_STATUS_CODE);
			output.setStatusDescription(StatusList.ERR_INVALID_CREDENTIALS_STATUS_DESCRIPTION);
			logger.info(StatusList.ERR_INVALID_CREDENTIALS_STATUS_DESCRIPTION);
			return output;
		    }

		    // Autorizacion
		    AuthorizationService authorizationService = new AuthorizationService();
		    boolean isUserAuthorized = authorizationService.isUserAuthorized(input.getUsername(),
			    input.getPassword());
		    if (!isUserAuthorized) {
			output.setStatusCode(StatusList.ERR_USER_NOT_AUTHORIZED_STATUS_CODE);
			output.setStatusDescription(StatusList.ERR_USER_NOT_AUTHORIZED_STATUS_DESCRIPTION);
			logger.info(StatusList.ERR_USER_NOT_AUTHORIZED_STATUS_DESCRIPTION);
			return output;
		    }

		    // Datos en salida comunes para error y exito
		    output.setUsername(input.getUsername());
		    output.setId(input.getId());

		    String id = input.getId();
		    Gson gson = new Gson();
		    String requestBody;
		    StatusURLRequest statusURLRequest = new StatusURLRequest();
		    statusURLRequest.setUrlKey(id);

		    requestBody = gson.toJson(statusURLRequest);
		    requestBody = requestBody.replace("urlKey", "url_key");

		    logger.info("StatusURLService.statusURL StatusURLRequest (preview): " + requestBody);

		    // Encriptacion
		    // Codificacion base64 username:password
		    Encryption crypto = new Encryption();
		    String username = Configuration.username;
		    String password = Configuration.password;
		    String authorizationKey = crypto.base64Encode(String.format("%s:%s", username, password));

		    // Llamada al proveedor de servicio de mensajeria
		    MessagingServiceProvider provider = new MessagingServiceProvider();
		    String endpoint = Configuration.endpointStatusURL;
		    String jsonResponse = provider.sendMessage(requestBody, authorizationKey, endpoint);
		    boolean success;

		    if (jsonResponse.equals("")) {
			output.setStatusCode(StatusList.ERR_PROVIDER_STATUS_CODE);
			output.setStatusDescription(StatusList.ERR_PROVIDER_STATUS_DESCRIPTION);
			logger.info(StatusList.ERR_PROVIDER_STATUS_DESCRIPTION);
			return output;
		    }

		    // Medida profilactica -en este punto de ejecucion.
		    jsonResponse = jsonResponse.replace("short_url", "shortUrl");
		    jsonResponse = jsonResponse.replace("url_tracker", "urlTracker");
		    jsonResponse = jsonResponse.replace("created_at", "createdAt");
		    StatusURLResponse response = new Gson().fromJson(jsonResponse, StatusURLResponse.class);
		    success = response.getSuccess().equalsIgnoreCase("TRUE");

		    if (success) {
			// DAO
			try {
			    // String usr = estadoURLDAO.selectUsernameEstadoURL(id);
			    // int countEstadoURL = Integer.parseInt(estadoURLDAO.selectCountEstadoURL(id));
			    EstadoSMS estadoSMS = estadoURLDAO.selectEstadoSMS(id);
			    StatusURLData statusURLData = response.getData();
			    int clicks = Integer.parseInt(statusURLData.getCountClick());
			    String internetBrowser = "";
			    String operativeSystem = "";
			    String createdAt = "";

			    if (clicks > 0) {
				ArrayList<StatusURLTracker> urlTracker = statusURLData.getUrlTracker();
				StatusURLTracker tracker = urlTracker.get(0);
				internetBrowser = tracker.getBrowser();
				operativeSystem = tracker.getOs();
				createdAt = tracker.getCreatedAt();
			    }

			    if (null == estadoURL) {
				estadoURL = new EstadoURL();
				estadoURL.setCodigoURL(id);
				estadoURL.setUsuario(estadoSMS.getUsuario());
				estadoURL.setClicks(String.valueOf(clicks));
				// Fecha de apertura
				if (null != createdAt && createdAt.length() > 0) {
				    final String pattern = "yyyy-MM-dd HH:mm:ss";
				    final SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
				    final Date fechaAperturaDate = dateFormat.parse(createdAt);
				    final Timestamp fechaApertura = new java.sql.Timestamp(fechaAperturaDate.getTime());
				    estadoURL.setFechaApertura(fechaApertura);
				}
			    }
			    if (clicks == 0) {
				output.setResult("URL Ignorada");
				estadoURLDAO.saveZeroClicksEstadoURL(estadoURL);
				output.setStatusCode(StatusList.SUCCESS_URL_IGNORED_STATUS_CODE);
				output.setStatusDescription(StatusList.SUCCESS_URL_IGNORED_STATUS_DESCRIPTION);
				logger.info(StatusList.SUCCESS_URL_IGNORED_STATUS_DESCRIPTION);
			    } else {
				output.setResult("URL Visitada");
				estadoURL.setNavegador(internetBrowser);
				estadoURL.setSistemaOperativo(operativeSystem);
				estadoURLDAO.saveEstadoURL(estadoURL);
				output.setStatusCode(StatusList.SUCCESS_URL_VISITED_STATUS_CODE);
				output.setStatusDescription(StatusList.SUCCESS_URL_VISITED_STATUS_DESCRIPTION);
				logger.info(StatusList.SUCCESS_URL_VISITED_STATUS_DESCRIPTION);
			    }
			    output.setClicks(String.valueOf(clicks));
			    output.setRut(estadoSMS.getRut());
			    output.setDv(estadoSMS.getDigitoValidador());
			    output.setPhone(estadoSMS.getCelular());
			    output.setBrowser(internetBrowser);
			    output.setSo(operativeSystem);

			    // final Date date = new Date();
			    // final String pattern = "yyyy-MM-dd HH:mm:ss";
			    // final String dateFormatted = new SimpleDateFormat(pattern).format(date);

			    output.setDate(createdAt);
			    return output;
			} catch (Exception e) {
			    output.setStatusCode(StatusList.ERR_DATABASE_STATUS_CODE);
			    output.setStatusDescription(StatusList.ERR_DATABASE_STATUS_DESCRIPTION);
			    logger.error(StatusList.ERR_DATABASE_STATUS_DESCRIPTION, e);
			    return output;
			}
		    } else {
			jsonResponse = jsonResponse.replace("error-code", "errorCode");
			StatusURLError error = new Gson().fromJson(jsonResponse, StatusURLError.class);
			output.setStatusCode(error.getErrorCode());
			output.setStatusDescription(error.getError());
			logger.error(error.getError());
			return output;
		    }
		} catch (Exception e) {
		    output.setStatusCode(StatusList.ERR_UNKNOWN_STATUS_CODE);
		    output.setStatusDescription(StatusList.ERR_UNKNOWN_STATUS_DESCRIPTION);
		    logger.error(StatusList.ERR_UNKNOWN_STATUS_DESCRIPTION, e);
		    return output;
		}
	    }
	} catch (Exception e) {
	    output.setStatusCode(StatusList.ERR_DATABASE_STATUS_CODE);
	    output.setStatusDescription(StatusList.ERR_DATABASE_STATUS_DESCRIPTION);
	    logger.error(StatusList.ERR_DATABASE_STATUS_DESCRIPTION, e);
	    return output;
	}
    }
}
