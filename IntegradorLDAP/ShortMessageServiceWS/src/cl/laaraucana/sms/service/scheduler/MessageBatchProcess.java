package cl.laaraucana.sms.service.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import cl.laaraucana.sms.service.orchestrator.BatchReportSMSOrchestrator;
import cl.laaraucana.sms.service.orchestrator.BulkSMSOrchestrator;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class MessageBatchProcess {
    private static final Logger logger = Logger.getLogger(MessageBatchProcess.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public void processBatch() {
        // Envio de mensajes en lotes con servicio send-bulk
        logger.info(String.format("Send LoteSMS started at %s", dateFormat.format(new Date())));
        BulkSMSOrchestrator batchOrchestrator = new BulkSMSOrchestrator();
        int result = batchOrchestrator.processBatch();
        logger.info(String.format("Send LoteSMS ended at %s with code [%s]", dateFormat.format(new Date()), result));
    }

    public void updateBatch() {
        // Actualiza los registros con los resultados de servicio batch-report
        logger.info(String.format("Update EstadoLoteSMS started at %s", dateFormat.format(new Date())));
        BatchReportSMSOrchestrator batchOrchestrator = new BatchReportSMSOrchestrator();
        int result = batchOrchestrator.updateBatch();
        logger.info(String.format("Update EstadoLoteSMS ended at %s with code [%s]", dateFormat.format(new Date()), result));
    }
}