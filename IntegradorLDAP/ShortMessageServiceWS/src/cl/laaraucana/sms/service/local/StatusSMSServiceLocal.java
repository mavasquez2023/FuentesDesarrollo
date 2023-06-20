package cl.laaraucana.sms.service.local;

import cl.laaraucana.sms.domain.altera.StatusSMSData;
import cl.laaraucana.sms.domain.altera.StatusSMSRequest;
import cl.laaraucana.sms.domain.altera.StatusSMSResponse;
import cl.laaraucana.sms.ibatis.dao.EstadoSMSDAO;
import cl.laaraucana.sms.ibatis.model.EstadoSMS;
import cl.laaraucana.sms.service.provider.MessagingServiceProvider;
import cl.laaraucana.sms.utils.Encryption;
import cl.laaraucana.sms.utils.Configuration;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StatusSMSServiceLocal {
    private static final Logger logger = Logger.getLogger(StatusSMSServiceLocal.class);

    private StatusSMSResponse go(String code) {
	Gson gson = new Gson();
	String requestBody;
	StatusSMSRequest statusSMSRequest = new StatusSMSRequest();
	statusSMSRequest.setCode(code);
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

	if (jsonResponse.equals("")) {
	    logger.error("Error during sendMessage operation");
	    return null;
	}

	// Medida profilactica -en este punto de ejecucion.
	jsonResponse = jsonResponse.replace("sent_at", "sentAt");
	jsonResponse = jsonResponse.replace("received_at", "receivedAt");
	StatusSMSResponse response = new Gson().fromJson(jsonResponse, StatusSMSResponse.class);
	success = response.getSuccess().equalsIgnoreCase("TRUE");

	if (success)
	    return response;
	else
	    return null;
    }

    private boolean updateStatusSMS(String codigoSMS) {
	StatusSMSResponse response = go(codigoSMS);
	if (null != response) {
	    try {
		StatusSMSData statusSMSData = response.getData();
		final String estado = "Entregado";
		
		// Fechas de envio y recepcion
		final String pattern = "yyyy-MM-dd HH:mm:ss";
		final SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		final Date fechaEnvioDate = dateFormat.parse(statusSMSData.getSentAt());
		final Date fechaRecepcionDate = dateFormat.parse(statusSMSData.getReceivedAt());
		final Timestamp fechaEnvio = new java.sql.Timestamp(fechaEnvioDate.getTime());
		final Timestamp fechaRecepcion = new java.sql.Timestamp(fechaRecepcionDate.getTime());
		
		EstadoSMS estadoSMS = new EstadoSMS();
		estadoSMS.setCodigoSMS(codigoSMS);
		estadoSMS.setFechaEnvio(fechaEnvio);
		estadoSMS.setFechaRecepcion(fechaRecepcion);
		estadoSMS.setEstado(estado);

		EstadoSMSDAO estadoSMSDAO = new EstadoSMSDAO();
		estadoSMSDAO.updateEstadoSMS(estadoSMS);
	    } catch (Exception e) {
		logger.error("Error during saveEstadoSMS operation", e);
		return false;
	    }
	    return true;
	}
	return false;
    }

    public void checkStatusSMS() {
	try {
	    EstadoSMSDAO estadoSMSDAO = new EstadoSMSDAO();
	    List<EstadoSMS> listEstadoSMS = estadoSMSDAO.selectListEstadoSMSForCheckStatusSMS();
	    if (listEstadoSMS.size() > 0) {
		for (EstadoSMS estadoSMS : listEstadoSMS) {
		    logger.info(String.format("Task checkStatusSMS processing id: %s", estadoSMS.getCodigoSMS()));
		    updateStatusSMS(estadoSMS.getCodigoSMS());
		}
	    } else {
		logger.info("Task task checkStatusSMS has no items to process");
	    }
	} catch (Exception e) {
	    logger.error("Error during selectListEstadoSMSForCheckStatusSMS DAO operation", e);
	}
    }
}