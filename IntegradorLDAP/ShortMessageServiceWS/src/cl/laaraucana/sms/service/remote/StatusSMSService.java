package cl.laaraucana.sms.service.remote;

import cl.laaraucana.sms.service.security.AuthorizationService;
import cl.laaraucana.sms.service.provider.MessagingServiceProvider;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import cl.laaraucana.sms.domain.altera.StatusSMSData;
import cl.laaraucana.sms.domain.altera.StatusSMSError;
import cl.laaraucana.sms.domain.altera.StatusSMSRequest;
import cl.laaraucana.sms.domain.altera.StatusSMSResponse;
import cl.laaraucana.sms.domain.exchange.StatusList;
import cl.laaraucana.sms.domain.exchange.StatusSMSInput;
import cl.laaraucana.sms.domain.exchange.StatusSMSOutput;
import cl.laaraucana.sms.ibatis.dao.EstadoSMSDAO;
import cl.laaraucana.sms.ibatis.model.EstadoSMS;
import cl.laaraucana.sms.utils.Encryption;
import cl.laaraucana.sms.utils.Configuration;

public class StatusSMSService {

    private static final Logger logger = Logger.getLogger(StatusSMSService.class);

    public StatusSMSOutput statusSMS(StatusSMSInput input) {
	StatusSMSOutput output = new StatusSMSOutput();
	output.setUsername(input.getUsername());
	output.setId(input.getId());
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
	    boolean isUserAuthorized = authorizationService.isUserAuthorized(input.getUsername(), input.getPassword());
	    if (!isUserAuthorized) {
		output.setStatusCode(StatusList.ERR_USER_NOT_AUTHORIZED_STATUS_CODE);
		output.setStatusDescription(StatusList.ERR_USER_NOT_AUTHORIZED_STATUS_DESCRIPTION);
		logger.info(StatusList.ERR_USER_NOT_AUTHORIZED_STATUS_DESCRIPTION);
		return output;
	    }

	    // Verification
	    // Se obtiene de DAO EstadoSMS informacion que complementa datos en peticion del
	    // cliente
	    try {
		EstadoSMSDAO estadoSMSDAO = new EstadoSMSDAO();
		EstadoSMS estadoSMS = estadoSMSDAO.selectEstadoSMS(input.getId());
		if (null != estadoSMS) {
		    output.setRut(estadoSMS.getRut());
		    output.setDv(estadoSMS.getDigitoValidador());
		    output.setPhone(estadoSMS.getCelular());
		    output.setStatus(estadoSMS.getEstado());
		    output.setDateSend("");
		    output.setDateReceived("");

		    // Formatos para fechas de envio y recepcion
		    final String pattern = "yyyy-MM-dd HH:mm:ss";
		    final Timestamp fechaEnvioTimestamp = estadoSMS.getFechaEnvio();
		    final Timestamp fechaRecepcionTimestamp = estadoSMS.getFechaRecepcion();

		    if (null != fechaEnvioTimestamp) {
			Date fechaEnvioDate = new Date();
			fechaEnvioDate.setTime(fechaEnvioTimestamp.getTime());
			String fechaEnvio = new SimpleDateFormat(pattern).format(fechaEnvioDate);
			output.setDateSend(fechaEnvio);
		    }

		    if (null != fechaRecepcionTimestamp) {
			Date fechaRecepcionDate = new Date();
			fechaRecepcionDate.setTime(fechaRecepcionTimestamp.getTime());
			String fechaRecepcion = new SimpleDateFormat(pattern).format(fechaRecepcionDate);
			output.setDateReceived(fechaRecepcion);
		    }

		} else {
		    output.setStatusCode(StatusList.ERR_MESSAGE_NOT_FOUND_STATUS_CODE);
		    output.setStatusDescription(StatusList.ERR_MESSAGE_NOT_FOUND_STATUS_DESCRIPTION);
		    logger.error(StatusList.ERR_MESSAGE_NOT_FOUND_STATUS_DESCRIPTION);
		    return output;
		}
	    } catch (Exception e) {
		output.setStatusCode(StatusList.ERR_DATABASE_STATUS_CODE);
		output.setStatusDescription(StatusList.ERR_DATABASE_STATUS_DESCRIPTION);
		logger.error(StatusList.ERR_DATABASE_STATUS_DESCRIPTION, e);
		return output;
	    }

	    // No es necesario invocar al proveedor de servicio si ya existe registro con
	    // estado Entregado
	    if (output.getStatus().equals("Entregado")) {
		output.setStatusCode(StatusList.SUCCESS_DELIVERED_STATUS_CODE);
		output.setStatusDescription(StatusList.SUCCESS_DELIVERED_STATUS_DESCRIPTION);
		logger.info(StatusList.SUCCESS_DELIVERED_STATUS_DESCRIPTION);
		return output;

	    } else {
		Gson gson = new Gson();
		String requestBody;
		StatusSMSRequest statusSMSRequest = new StatusSMSRequest();
		statusSMSRequest.setCode(input.getId());
		requestBody = gson.toJson(statusSMSRequest);

		logger.info(String.format("StatusSMSRequest (preview): %s", requestBody));

		// Encriptacion
		// Codificacion base64 username:password
		Encryption crypto = new Encryption();
		String username = Configuration.username;
		String password = Configuration.password;
		String authorizationKey = crypto.base64Encode(String.format("%s:%s", username, password));

		// Llamada al servicio del proveedor de mensajeria
		MessagingServiceProvider provider = new MessagingServiceProvider();
		String endpoint = Configuration.endpointStatusSMS;
		String jsonResponse = provider.sendMessage(requestBody, authorizationKey, endpoint);
		boolean success;

		// Medida profilactica -en este punto de ejecucion.
		jsonResponse = jsonResponse.replace("sent_at", "sentAt");
		jsonResponse = jsonResponse.replace("received_at", "receivedAt");
		StatusSMSResponse response = new Gson().fromJson(jsonResponse, StatusSMSResponse.class);
		success = response.getSuccess().equalsIgnoreCase("TRUE");

		if (jsonResponse.equals("")) {
		    output.setStatusCode(StatusList.ERR_PROVIDER_STATUS_CODE);
		    output.setStatusDescription(StatusList.ERR_PROVIDER_STATUS_DESCRIPTION);
		    logger.info(StatusList.ERR_PROVIDER_STATUS_DESCRIPTION);
		    return output;
		}

		if (success) {
		    // DAO
		    StatusSMSData statusSMSData = response.getData();
		    final String codigoSMS = input.getId();
		    final String estado = statusSMSData.getStatus();

		    // Fechas de envio y recepcion
		    final String pattern = "yyyy-MM-dd HH:mm:ss";
		    final SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		    final Date fechaEnvioDate = dateFormat.parse(statusSMSData.getSentAt());
		    final Date fechaRecepcionDate = dateFormat.parse(statusSMSData.getReceivedAt());
		    final Timestamp fechaEnvio = new java.sql.Timestamp(fechaEnvioDate.getTime());
		    final Timestamp fechaRecepcion = new java.sql.Timestamp(fechaRecepcionDate.getTime());

		    try {
			EstadoSMS estadoSMS = new EstadoSMS();
			estadoSMS.setCodigoSMS(codigoSMS);
			estadoSMS.setEstado(estado);
			estadoSMS.setFechaEnvio(fechaEnvio);
			estadoSMS.setFechaRecepcion(fechaRecepcion);
			EstadoSMSDAO estadoSMSDAO = new EstadoSMSDAO();
			estadoSMSDAO.updateEstadoSMS(estadoSMS);
		    } catch (Exception e) {
			output.setStatusCode(StatusList.ERR_DATABASE_STATUS_CODE);
			output.setStatusDescription(StatusList.ERR_DATABASE_STATUS_DESCRIPTION);
			logger.error(StatusList.ERR_DATABASE_STATUS_DESCRIPTION, e);
			return output;
		    }

		    // Variable statusSMSData.getTag() excluida del requerimiento
		    // Variables en comun con DAO updateEstadoSMS
		    output.setStatus(estado);
		    output.setDateSend(statusSMSData.getSentAt());
		    output.setDateReceived(statusSMSData.getReceivedAt());

		    if (estado.equals("Entregado")) {
			output.setStatusCode(StatusList.SUCCESS_DELIVERED_STATUS_CODE);
			output.setStatusDescription(StatusList.SUCCESS_DELIVERED_STATUS_DESCRIPTION);
		    } else if (estado.equals("Enviado")) {
			output.setStatusCode(StatusList.SUCCESS_SENT_STATUS_CODE);
			output.setStatusDescription(StatusList.SUCCESS_SENT_STATUS_DESCRIPTION);
		    } else {
			output.setStatusCode(StatusList.SUCCESS_UNKNOWN_STATUS_CODE);
			output.setStatusDescription(StatusList.SUCCESS_UNKNOWN_STATUS_DESCRIPTION);
		    }
		    return output;
		} else {
		    jsonResponse = jsonResponse.replace("error-code", "errorCode");
		    StatusSMSError error = new Gson().fromJson(jsonResponse, StatusSMSError.class);
		    output.setStatusCode(error.getErrorCode());
		    output.setStatusDescription(error.getError());
		    logger.error(error.getError());
		    return output;
		}
	    }
	} catch (Exception e) {
	    output.setStatusCode(StatusList.ERR_UNKNOWN_STATUS_CODE);
	    output.setStatusDescription(StatusList.ERR_UNKNOWN_STATUS_DESCRIPTION);
	    logger.error(StatusList.ERR_UNKNOWN_STATUS_DESCRIPTION, e);
	    return output;
	}
    }
}
