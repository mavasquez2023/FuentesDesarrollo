package cl.laaraucana.sms.service.local;

import cl.laaraucana.sms.domain.altera.BatchReportResponse;
import cl.laaraucana.sms.domain.altera.BatchReportSentData;
import cl.laaraucana.sms.ibatis.model.EstadoLoteSMS;
import cl.laaraucana.sms.service.provider.MessagingServiceProvider;
import org.apache.log4j.Logger;

import com.google.gson.Gson;

import cl.laaraucana.sms.domain.altera.BatchReportRequest;
import cl.laaraucana.sms.domain.altera.BulkSMSResponse;
import cl.laaraucana.sms.utils.Encryption;
import cl.laaraucana.sms.utils.Configuration;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BatchReportSMSService {

    private static final Logger logger = Logger.getLogger(BatchReportSMSService.class);

    public List<EstadoLoteSMS> batchReportSMS(List<EstadoLoteSMS> listEstadoLoteSMS, String codigoLoteSMS) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<EstadoLoteSMS> listEstadoLoteSMSUpdated = new ArrayList<EstadoLoteSMS>();
        BatchReportResponse batchReportResponse = batchReportSMS(codigoLoteSMS);
        boolean success = batchReportResponse.getSuccess().equalsIgnoreCase("TRUE");

        if (success) {
            ArrayList<BatchReportSentData> sentData = batchReportResponse.getSentData();

            int recordedCount = listEstadoLoteSMS.size();
            int reportedCount = sentData.size();
            boolean areDataSetsEqualSize = recordedCount == reportedCount;

            logger.debug(String.format("Size comparison between data sets recorded and reported is %s (%s%s%s)",
                    areDataSetsEqualSize, recordedCount, areDataSetsEqualSize ? "=" : "!=", reportedCount));
            // Best case scenario (optimistic) because of database setup and constraints.. plus providers sorting configuration

            int index = 0;
            int matches = 0;
            for (BatchReportSentData data : sentData) {
                if (areDataSetsEqualSize) {
                    // :)
                    EstadoLoteSMS estadoLoteSMS = listEstadoLoteSMS.get(index);
                    if (data.getMessage().equals(estadoLoteSMS.getMensaje()) &&
                            data.getTo().equals(estadoLoteSMS.getCelular())) {
                        matches++;
                        logger.debug(String.format("Match for codigoSMS %s", data.getCode()));

                        estadoLoteSMS.setCodigoSMS(data.getCode());

                        String dateSentAt = data.getSentAt();
                        String dateReceivedAt = data.getReceivedAt();

                        try {
                            Date dateSentAtParsed = simpleDateFormat.parse(dateSentAt);
                            Date dateReceivedAtParsed = simpleDateFormat.parse(dateReceivedAt);
                            Timestamp timestampSentAt = new Timestamp(dateSentAtParsed.getTime());
                            Timestamp timestampReceivedAt = new Timestamp(dateReceivedAtParsed.getTime());

                            estadoLoteSMS.setFechaEnvio(timestampSentAt);
                            estadoLoteSMS.setFechaRecepcion(timestampReceivedAt);
                            estadoLoteSMS.setEstado(data.getStatus());

                        } catch (Exception e) {
                            logger.error(String.format("Error formatting dates %s %s", dateSentAt, dateReceivedAt));
                        }

                        listEstadoLoteSMSUpdated.add(estadoLoteSMS);
                    }
                } else {
                    return null;
                }
                index++;
            }
            if (areDataSetsEqualSize && matches == reportedCount) {
                logger.debug(String.format("Matches (count) for data sets report/records are equal to the size of the batch (%s) with id %s", matches, codigoLoteSMS));
            } else {
                return null;
            }
        }
        return listEstadoLoteSMSUpdated;
    }

    private BatchReportResponse batchReportSMS(String id) {
        Gson gson = new Gson();
        String requestBody;

        BatchReportRequest batchReportRequest = new BatchReportRequest();
        batchReportRequest.setBatchCode(id);

        requestBody = gson.toJson(batchReportRequest);
        requestBody = requestBody.replace("batchCode", "batch_code");

        logger.info(String.format("BatchReportRequest (preview): %s ", requestBody));

        // Encryption
        // Encoding base64 username:password
        Encryption crypto = new Encryption();
        String username = Configuration.username;
        String password = Configuration.password;
        String authorizationKey = crypto.base64Encode(String.format("%s:%s", username, password));

        // Calling Service Provider
        MessagingServiceProvider provider = new MessagingServiceProvider();
        String endpoint = Configuration.endpointBatchReport;
        String jsonResponse = provider.sendMessage(requestBody, authorizationKey, endpoint);
        boolean success;

        // Prophylactic replacement (at this point)
        jsonResponse = jsonResponse.replace("batch_size", "batchSize");
        jsonResponse = jsonResponse.replace("count_sent", "countSent");
        jsonResponse = jsonResponse.replace("sent_data", "sentData");
        jsonResponse = jsonResponse.replace("sent_at", "sentAt");
        jsonResponse = jsonResponse.replace("received_at", "receivedAt");

        return new Gson().fromJson(jsonResponse, BatchReportResponse.class);

        // success = response.getSuccess().equalsIgnoreCase("TRUE");
    }
}
