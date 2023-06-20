package cl.laaraucana.sms.service.remote;

import cl.laaraucana.sms.service.security.AuthorizationService;
import cl.laaraucana.sms.service.provider.MessagingServiceProvider;
import cl.laaraucana.sms.utils.Configuration;
import org.apache.log4j.Logger;

import com.google.gson.Gson;

import cl.laaraucana.sms.domain.altera.SendSMSError;
import cl.laaraucana.sms.domain.altera.SendSMSRequest;
import cl.laaraucana.sms.domain.altera.SendSMSResponse;
import cl.laaraucana.sms.domain.altera.SendURLRequest;
import cl.laaraucana.sms.domain.altera.SendURLResponse;
import cl.laaraucana.sms.domain.exchange.MessageInput;
import cl.laaraucana.sms.domain.exchange.MessageOutput;
import cl.laaraucana.sms.domain.exchange.StatusList;
import cl.laaraucana.sms.ibatis.dao.EstadoSMSDAO;
import cl.laaraucana.sms.ibatis.model.EstadoSMS;
import cl.laaraucana.sms.utils.Encryption;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SendSMSService {

    private static final Logger logger = Logger.getLogger(SendSMSService.class);

    public MessageOutput sendSMS(MessageInput input) {
        MessageOutput output = new MessageOutput();
        try {
            // Validaciones de parametros de entrada
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

            // Se incluye en caso de errores
            output.setPhone(input.getPhone());

            Gson gson = new Gson();
            String requestBody;
            String requestType = "SMS";
            final boolean isUrlEnabled = null != input.getUrlCondition() && input.getUrlCondition().equalsIgnoreCase("SI");
            final String msg = input.getMessage();
            final String phone = input.getPhone();
            final String shortUrl = isUrlEnabled ? input.getUrlText() : "";

            if (isUrlEnabled) {
                SendURLRequest request = new SendURLRequest();
                request.setMsg(msg);
                request.setTo(phone);
                request.setUrl(true);
                request.setShortUrl(shortUrl);
                requestBody = gson.toJson(request);
                requestType = "URL";
            } else {
                SendSMSRequest request = new SendSMSRequest();
                request.setMsg(msg);
                request.setTo(phone);
                requestBody = gson.toJson(request);
            }

            // Note: Como java no permite nombrar variables con guion, es necesario transformer reemploys cadenas.
            // logger.info("SendSMSRequest/SendURLRequest (preview): " + requestBody);
            requestBody = requestBody.replace("shortUrl", "short-url");

            // Encryption
            // Encoding base64 username:password
            Encryption crypto = new Encryption();
            String username = Configuration.username;
            String password = Configuration.password;
            String authorizationKey = crypto.base64Encode(String.format("%s:%s", username, password));

            // Calling Service Provider
            MessagingServiceProvider provider = new MessagingServiceProvider();
            String endpoint = Configuration.endpointSendSMS;
            String jsonResponse = provider.sendMessage(requestBody, authorizationKey, endpoint);
            if (jsonResponse.equals("")) {
                output.setStatusCode(StatusList.ERR_PROVIDER_STATUS_CODE);
                output.setStatusDescription(StatusList.ERR_PROVIDER_STATUS_DESCRIPTION);
                logger.info(StatusList.ERR_PROVIDER_STATUS_DESCRIPTION);
                return output;
            }

            boolean success;
            String responseCode;
            String responseUrlKey = "";

            // Parseo de la respuesta del proveedor de servicio de mensajeria
            if (requestType.equals("URL")) {
                jsonResponse = jsonResponse.replace("url_key", "urlKey");
                SendURLResponse response = new Gson().fromJson(jsonResponse, SendURLResponse.class);
                success = response.getSuccess().equalsIgnoreCase("TRUE");
                responseCode = response.getCode();
                responseUrlKey = response.getUrlKey();
            } else {
                SendSMSResponse response = new Gson().fromJson(jsonResponse, SendSMSResponse.class);
                success = response.getSuccess().equalsIgnoreCase("TRUE");
                responseCode = response.getCode();
            }

            // DAO
            if (success && !responseCode.equals("")) {
                // Variables para insertar registro en base de datos
                final String usuario = crypto.AESDecrypt(input.getUsername());
                final String rut = input.getRut();
                final String digitoValidador = input.getDv();
                final String celular = input.getPhone();
                final String estado = "Enviado";
                // Fecha Envio
                final String pattern = "yyyy-MM-dd HH:mm:ss";
                final SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
                final String fechaEnvioDateString = dateFormat.format(new Date());
                final Date fechaEnvioDate = dateFormat.parse(fechaEnvioDateString);
                final Timestamp fechaEnvio = new java.sql.Timestamp(fechaEnvioDate.getTime());
                
                final String codigoSMS = responseCode;
                final String codigoURL = requestType.equals("URL") ? responseUrlKey : "";

                // Modelo de datos para estado de mensaje SMS
                EstadoSMS estadoSMS = new EstadoSMS();
                estadoSMS.setUsuario(usuario);
                estadoSMS.setRut(rut);
                estadoSMS.setDigitoValidador(digitoValidador);
                estadoSMS.setCelular(celular);
                estadoSMS.setEstado(estado);
                estadoSMS.setFechaEnvio(fechaEnvio);
                estadoSMS.setCodigoSMS(codigoSMS);
                estadoSMS.setCodigoURL(codigoURL);

                // Inserta registro en base de datos
                try {
                    EstadoSMSDAO dao = new EstadoSMSDAO();
                    dao.saveEstadoSMS(estadoSMS);
                } catch (Exception e) {
                    output.setStatusCode(StatusList.ERR_DATABASE_STATUS_CODE);
                    output.setStatusDescription(StatusList.ERR_DATABASE_STATUS_DESCRIPTION);
                    logger.error(StatusList.ERR_DATABASE_STATUS_DESCRIPTION, e);
                    return output;
                }

                // Service Output
                final String result = "Enviado";
                final String idSMS = responseCode;
                final String idURL = requestType.equals("URL") ? responseUrlKey : "";
                final String log = StatusList.SUCCESS_SENT_STATUS_CODE;
                final String message = StatusList.SUCCESS_SENT_STATUS_DESCRIPTION;

                output.setResult(result);
                output.setIdSMS(idSMS);
                output.setIdURL(idURL);
                output.setStatusCode(log);
                output.setStatusDescription(message);
                return output;

            } else {
                try {
                    jsonResponse = jsonResponse.replace("error-code", "errorCode");
                    SendSMSError error = new Gson().fromJson(jsonResponse, SendSMSError.class);
                    output.setStatusCode(error.getErrorCode());
                    output.setStatusDescription(error.getError());
                    logger.error(error.getError());
                    return output;
                } catch (Exception e) {
                    output.setStatusCode(StatusList.ERR_UNKNOWN_STATUS_CODE);
                    output.setStatusDescription(StatusList.ERR_UNKNOWN_STATUS_DESCRIPTION);
                    logger.error(StatusList.ERR_UNKNOWN_STATUS_DESCRIPTION, e);
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