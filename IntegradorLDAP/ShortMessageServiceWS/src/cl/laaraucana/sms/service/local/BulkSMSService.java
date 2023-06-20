package cl.laaraucana.sms.service.local;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.sms.domain.altera.*;
import cl.laaraucana.sms.ibatis.dao.EstadoLoteSMSDAO;
import cl.laaraucana.sms.ibatis.dao.LoteSMSDAO;
import cl.laaraucana.sms.ibatis.dao.LoteSMSLogDAO;
import cl.laaraucana.sms.ibatis.model.EstadoLoteSMS;
import cl.laaraucana.sms.ibatis.model.LoteSMS;
import cl.laaraucana.sms.ibatis.model.LoteSMSLog;
import cl.laaraucana.sms.ibatis.model.Sistema;
import cl.laaraucana.sms.ibatis.model.filter.EstadoLoteSMSFilter;
import cl.laaraucana.sms.ibatis.model.filter.LoteSMSFilter;
import cl.laaraucana.sms.service.provider.MessagingServiceProvider;
import org.apache.log4j.Logger;

import com.google.gson.Gson;

import cl.laaraucana.sms.utils.Encryption;
import cl.laaraucana.sms.utils.Configuration;

public class BulkSMSService {

    private static final Logger logger = Logger.getLogger(BulkSMSService.class);
    private static final int taskProcessBatchSize = Integer.parseInt(Configuration.taskProcessBatchSize);
    private static final int taskProcessBatchThreshold = Integer.parseInt(Configuration.taskProcessBatchThreshold);

    public LoteSMSLog bulkSMS(List<EstadoLoteSMS> listEstadoLoteSMS, LoteSMSLog loteSMSLog) {
        final String sentStatusSuccess = "Sent";
        final String sentStatusError = "Error";

        // Error a no ser que resulte lo contrario y se sobre-escriba el estado
        loteSMSLog.setEstado(sentStatusError);

        // Listado de Mensajes
        ArrayList<SendSMSRequest> messages = new ArrayList<SendSMSRequest>();

        for (EstadoLoteSMS estadoLoteSMS : listEstadoLoteSMS) {
            String to = estadoLoteSMS.getCelular();
            String msg = estadoLoteSMS.getMensaje();
            SendSMSRequest message = new SendSMSRequest();
            message.setTo(to);
            message.setMsg(msg);
            messages.add(message);
        }

        // Respuesta del proveedor de servicio de mensajeria
        Object response = bulkSMS(messages);
        if (null != response) {
            if (response.getClass() == BulkSMSResponse.class) {
                BulkSMSResponse bulkSMSResponse = (BulkSMSResponse) response;
                BulkSMSBatchInfo batchInfo = bulkSMSResponse.getBatchInfo();

                String batchCode = bulkSMSResponse.getBatchCode();
                boolean success = bulkSMSResponse.getSuccess().equalsIgnoreCase("TRUE");
                if (success) {
                    int sentCount = batchInfo.getSentCount();
                    int invalidCount = batchInfo.getInvalidCount();
                    int errorCount = batchInfo.getErrorCount();
                    int totalCount = sentCount + invalidCount + errorCount;
                    loteSMSLog.setCodigoLoteSMS(batchCode);
                    loteSMSLog.setMensajesEnviados(sentCount);
                    loteSMSLog.setMensajesInvalidos(invalidCount);
                    loteSMSLog.setMensajesErroneos(errorCount);
                    loteSMSLog.setMensajesTotal(totalCount);
                    loteSMSLog.setEstado(sentStatusSuccess);

                    return loteSMSLog;
                }
            } else if (response.getClass() == BulkSMSError.class) {
                BulkSMSError bulkSMSError = (BulkSMSError) response;
                String errorCodigo = bulkSMSError.getErrorCode();
                String errorDescripcion = bulkSMSError.getError();
                logger.error(String.format("Error al procesar respuesta del proveedor de mensajeria en PID %s, codigo: %s descripcion: %s", loteSMSLog, errorCodigo, errorDescripcion));

                return loteSMSLog;
            } else {
                logger.error("Formato no reconocido en respuesta del proveedor de mensajeria");
            }
        } else {
            logger.error("Error desconocido al procesar respuesta del proveedor de mensajeria");
        }

        return loteSMSLog;
    }

    private Object bulkSMS(ArrayList<SendSMSRequest> messages) {
        Gson gson = new Gson();
        String requestBody;

        BulkSMSRequest bulkSMSRequest = new BulkSMSRequest();
        bulkSMSRequest.setMessages(messages);

        requestBody = gson.toJson(bulkSMSRequest);

        logger.info(String.format("BulkSMSRequest (preview): %s", requestBody));

        // Encryption
        // Encoding base64 username:password
        Encryption crypto = new Encryption();
        String username = Configuration.username;
        String password = Configuration.password;
        String authorizationKey = crypto.base64Encode(String.format("%s:%s", username, password));

        try {
            // Calling Service Provider
            MessagingServiceProvider provider = new MessagingServiceProvider();
            String endpoint = Configuration.endpointBulkSMS;
            String jsonResponse = provider.sendMessage(requestBody, authorizationKey, endpoint);

            // Prophylactic replacement (at this point)
            jsonResponse = jsonResponse.replace("batch_code", "batchCode");
            jsonResponse = jsonResponse.replace("batch_info", "batchInfo");
            jsonResponse = jsonResponse.replace("sent_count", "sentCount");
            jsonResponse = jsonResponse.replace("invalid_count", "invalidCount");
            jsonResponse = jsonResponse.replace("invalid_list", "invalidList");
            jsonResponse = jsonResponse.replace("error_count", "errorCount");
            jsonResponse = jsonResponse.replace("error_list", "errorList");

            jsonResponse = jsonResponse.replace("error-code", "errorCode");

            if (jsonResponse.contains("errorCode")) {
                return new Gson().fromJson(jsonResponse, BulkSMSError.class);
            } else {
                return new Gson().fromJson(jsonResponse, BulkSMSResponse.class);
            }
        } catch (Exception e) {
            logger.error("Error de conexion al servicio del proveedor de mensajeria");
            return null;
        }
    }

    public int clearLoteSMS(String estado) {
        try {
            LoteSMSDAO loteSMSDAO = new LoteSMSDAO();
            int result = loteSMSDAO.deleteLoteSMS(estado);
            logger.info(String.format("Cleared %s bulk record(s)", result));
            return result;
        } catch (Exception e) {
            logger.error("Error during deleteLoteSMS DAO operation", e);
        }
        return 0;
    }

    public int updateLoteSMS(String estado) {
        int batchSize = 0;
        try {
            LoteSMSDAO loteSMSDAO = new LoteSMSDAO();
            batchSize = loteSMSDAO.updateEstado(estado);
            logger.info(String.format("Bulk Updated %s", batchSize));
        } catch (Exception e) {
            logger.error("Error during deleteLoteSMS DAO operation", e);
        }
        return batchSize;
    }

    public boolean updateEstadoLoteSMS(int id, String codigoLoteSMS, String estado, String codigoSMS, Timestamp fechaEnvio, Timestamp fechaRecepcion) {
        EstadoLoteSMSFilter estadoLoteSMSFilter = new EstadoLoteSMSFilter();
        estadoLoteSMSFilter.setId(id);
        estadoLoteSMSFilter.setCodigoLoteSMS(codigoLoteSMS);
        estadoLoteSMSFilter.setEstado(estado);
        estadoLoteSMSFilter.setCodigoSMS(codigoSMS);
        estadoLoteSMSFilter.setFechaEnvio(fechaEnvio);
        estadoLoteSMSFilter.setFechaRecepcion(fechaRecepcion);

        return updateEstadoLoteSMS(estadoLoteSMSFilter);
    }

    public boolean updateEstadoLoteSMS(String codigoLoteSMS, int codigoLoteSMSLog, String estado) {
        EstadoLoteSMSFilter estadoLoteSMSFilter = new EstadoLoteSMSFilter();
        estadoLoteSMSFilter.setCodigoLoteSMS(codigoLoteSMS);
        estadoLoteSMSFilter.setCodigoLoteSMSLog(codigoLoteSMSLog);
        estadoLoteSMSFilter.setEstado(estado);
        return updateEstadoLoteSMS(estadoLoteSMSFilter);
    }

    public boolean updateEstadoLoteSMS(EstadoLoteSMSFilter estadoLoteSMSFilter) {
        try {
            EstadoLoteSMSDAO estadoLoteSMSDAO = new EstadoLoteSMSDAO();
            estadoLoteSMSDAO.updateCodigoLoteSMS(estadoLoteSMSFilter);
            return true;
        } catch (Exception e) {
            logger.error("Error during updateCodigoLoteSMS DAO operation", e);
        }
        return false;
    }

    public int updateLoteSMS(List<LoteSMS> listLoteSMS, String estado) {
        int result = 0;
        try {
            EstadoLoteSMSDAO estadoLoteSMSDAO = new EstadoLoteSMSDAO();

            for (LoteSMS loteSMS : listLoteSMS) {
                // final String estado = loteSMS.getEstado();
                final String sistema = loteSMS.getSistema();
                final String rut = loteSMS.getRut();
                final String digitoValidador = loteSMS.getDigitoValidador();
                final String celular = loteSMS.getCelular();
                final String mensaje = loteSMS.getMensaje();
                final Timestamp fechaCarga = loteSMS.getFechaCarga();
                final String referencia = loteSMS.getReferencia();

                EstadoLoteSMS estadoLoteSMS = new EstadoLoteSMS();
                estadoLoteSMS.setSistema(sistema);
                estadoLoteSMS.setEstado(estado);
                estadoLoteSMS.setRut(rut);
                estadoLoteSMS.setDigitoValidador(digitoValidador);
                estadoLoteSMS.setCelular(celular);
                estadoLoteSMS.setMensaje(mensaje);
                estadoLoteSMS.setFechaCarga(fechaCarga);
                estadoLoteSMS.setReferencia(referencia);

                estadoLoteSMSDAO.saveEstadoLoteSMS(estadoLoteSMS);
            }
        } catch (Exception e) {
            logger.error("Error during deleteLoteSMS DAO operation", e);
        }
        return result;
    }

    public List<LoteSMS> getListLoteSMS(String estado, String sistema) {
        LoteSMSFilter loteSMSFilter = new LoteSMSFilter();
        loteSMSFilter.setEstado(estado);
        loteSMSFilter.setSistema(sistema);
        return getListLoteSMS(loteSMSFilter);
    }

    public List<LoteSMS> getListLoteSMS(String estado) {
        LoteSMSFilter loteSMSFilter = new LoteSMSFilter();
        loteSMSFilter.setEstado(estado);
        return getListLoteSMS(loteSMSFilter);
    }

    public List<LoteSMS> getListLoteSMS(LoteSMSFilter loteSMSFilter) {
        loteSMSFilter.setMaxBulkSize(taskProcessBatchSize); // irrelevant
        List<LoteSMS> listLoteSMS;
        try {
            LoteSMSDAO loteSMSDAO = new LoteSMSDAO();
            listLoteSMS = loteSMSDAO.selectListLoteSMS(loteSMSFilter);
            return listLoteSMS;
        } catch (Exception e) {
            logger.error("Error during selectListLoteSMS DAO operation", e);
        }
        return null;
    }

    public List<LoteSMS> getListLoteSMSWithThresholdEnabled(LoteSMSFilter loteSMSFilter) {
        loteSMSFilter.setMaxBulkSize(taskProcessBatchSize); // irrelevant
        List<LoteSMS> listLoteSMS = null;
        try {
            LoteSMSDAO loteSMSDAO = new LoteSMSDAO();
            listLoteSMS = loteSMSDAO.selectListLoteSMS(loteSMSFilter);
            if (listLoteSMS.size() > taskProcessBatchThreshold) {
                throw new Exception(String.format("Batch size %s exceeds the limit established by the service provider", taskProcessBatchThreshold));
            } else logger.info(String.format("Bulk Size %s", listLoteSMS.size()));
        } catch (Exception e) {
            logger.error("Error during selectListLoteSMS DAO operation", e);
        }
        return listLoteSMS;
    }

    public List<Sistema> getListSistema() {
        List<Sistema> listSistema;
        try {
            LoteSMSDAO loteSMSDAO = new LoteSMSDAO();
            listSistema = loteSMSDAO.selectListSistema();
            return listSistema;
        } catch (Exception e) {
            logger.error("Error during selectListSistema DAO operation", e);
        }
        return null;
    }

    public List<EstadoLoteSMS> getListEstadoLoteSMS(int codigoLoteSMSLog, String estado) {
        List<EstadoLoteSMS> listEstadoLoteSMS;
        try {
            EstadoLoteSMSFilter estadoLoteSMSFilter = new EstadoLoteSMSFilter();
            estadoLoteSMSFilter.setCodigoLoteSMSLog(codigoLoteSMSLog);
            estadoLoteSMSFilter.setEstado(estado);
            EstadoLoteSMSDAO estadoLoteSMSDAO = new EstadoLoteSMSDAO();
            listEstadoLoteSMS = estadoLoteSMSDAO.selectListEstadoLoteSMS(estadoLoteSMSFilter);
            return listEstadoLoteSMS;
        } catch (Exception e) {
            logger.error("Error during selectListEstadoLoteSMS DAO operation", e);
        }
        return null;
    }

    public int countEstadoLoteSMS(int codigoLoteSMSLog) {
        try {
            LoteSMSLogDAO loteSMSLogDAO = new LoteSMSLogDAO();
            return loteSMSLogDAO.selectCountEstadoLoteSMS(codigoLoteSMSLog);
        } catch (Exception e) {
            logger.error("Error during countEstadoLoteSMS DAO operation", e);
        }
        return 0;
    }

    public LoteSMSLog createLoteSMSLog(LoteSMSLog loteSMSLog) {
        try {
            LoteSMSLogDAO loteSMSLogDAO = new LoteSMSLogDAO();
            loteSMSLogDAO.createLoteSMSLog(loteSMSLog);
            int maxId = loteSMSLogDAO.selectMaxId();
            LoteSMSLog newLoteSMSLog = loteSMSLogDAO.selectLoteSMSLog(maxId);
            // if (newLoteSMSLog.getEstado().equals(loteSMSLog.getEstado()) && newLoteSMSLog.getFechaInicio().compareTo(loteSMSLog.getFechaInicio()) == 0) {
            if (newLoteSMSLog.getEstado().equals(loteSMSLog.getEstado())) {
                logger.info("Batch process log saved");
                return newLoteSMSLog;
            } else {
                // Error grave
                logger.error("Error saving batch process log");
                return null;
            }
        } catch (Exception e) {
            logger.error("Error during createLoteSMSLog DAO operation", e);
        }
        return null;
    }

    public List<LoteSMSLog> getListLoteSMSLog(String estado) {
        try {
            LoteSMSLogDAO loteSMSLogDAO = new LoteSMSLogDAO();
            return loteSMSLogDAO.selectListLoteSMSLog(estado);
        } catch (Exception e) {
            logger.error("Error during selectListLoteSMSLog DAO operation", e);
        }
        return null;
    }

    public boolean loadEstadoLoteSMS(LoteSMSLog loteSMSLog, String estado, String estadoCodigo, String estadoDescripcion) {
        if (taskProcessBatchSize > taskProcessBatchThreshold) {
            logger.error(String.format("Bulk size %s exceeds the limit of %s", taskProcessBatchSize, taskProcessBatchThreshold));
            return false;
        }
        try {
            EstadoLoteSMSFilter estadoLoteSMSFilter = new EstadoLoteSMSFilter();
            estadoLoteSMSFilter.setMaxBulkSize(taskProcessBatchSize);
            estadoLoteSMSFilter.setEstado(estado);
            estadoLoteSMSFilter.setEstadoCodigo(estadoCodigo);
            estadoLoteSMSFilter.setEstadoDescripcion(estadoDescripcion);
            estadoLoteSMSFilter.setSistema(loteSMSLog.getSistema());
            estadoLoteSMSFilter.setCodigoLoteSMSLog(loteSMSLog.getId());
            estadoLoteSMSFilter.setFechaProceso(loteSMSLog.getFechaInicio());

            EstadoLoteSMSDAO estadoLoteSMSDAO = new EstadoLoteSMSDAO();
            estadoLoteSMSDAO.loadEstadoLoteSMS(estadoLoteSMSFilter);

            logger.info(String.format("Batch loaded for system %s with PID %s", loteSMSLog.getSistema(), loteSMSLog.getId()));

            return true;
        } catch (Exception e) {
            logger.error("Error during loadEstadoLoteSMS DAO operation", e);
        }
        return false;
    }

    public boolean updateLoteSMSLog(LoteSMSLog loteSMSLog) {
        try {
            LoteSMSLogDAO loteSMSLogDAO = new LoteSMSLogDAO();
            int loteSMSLogInserted = loteSMSLogDAO.updateLoteSMSLog(loteSMSLog);
            boolean result = (loteSMSLogInserted == 1);
            if (result) {
                logger.info(String.format("Processed batch log updated for PID %s", loteSMSLog.getId()));
            } else {
                // Error grave
                logger.error(String.format("Error updating processed batch log for PID %s", loteSMSLog.getId()));
            }
            return result;
        } catch (Exception e) {
            logger.error("Error during updateLoteSMSLog DAO operation", e);
        }
        return false;
    }

    public int selectCountUnprocessedBatchSize(String estado) {
        LoteSMSFilter loteSMSFilter = new LoteSMSFilter();
        loteSMSFilter.setEstado(estado);
        return selectCountUnprocessedBatchSize(loteSMSFilter);
    }

    public int selectCountUnprocessedBatchSize(String estado, String sistema) {
        LoteSMSFilter loteSMSFilter = new LoteSMSFilter();
        loteSMSFilter.setEstado(estado);
        loteSMSFilter.setSistema(sistema);
        return selectCountUnprocessedBatchSize(loteSMSFilter);
    }

    public int selectCountUnprocessedBatchSize(LoteSMSFilter loteSMSFilter) {
        try {
            LoteSMSDAO loteSMSDAO = new LoteSMSDAO();
            return loteSMSDAO.selectCountUnprocessedBatchSize(loteSMSFilter);
        } catch (Exception e) {
            logger.error("Error during selectCountUnprocessedBatchSize DAO operation", e);
        }
        return 0;
    }

    public boolean updateEstado(String estadoActual, String estadoActualizado) {
        LoteSMSFilter loteSMSFilter = new LoteSMSFilter();
        loteSMSFilter.setEstadoActual(estadoActual);
        loteSMSFilter.setEstadoActualizado((estadoActualizado));
        try {
            LoteSMSDAO loteSMSDAO = new LoteSMSDAO();
            loteSMSDAO.updateEstado(loteSMSFilter);
            return true;
        } catch (Exception e) {
            logger.error("Error during updateEstado DAO operation", e);
        }
        return false;
    }
}