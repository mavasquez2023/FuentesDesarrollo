package cl.laaraucana.sms.service.orchestrator;

import cl.laaraucana.sms.ibatis.model.EstadoLoteSMS;
import cl.laaraucana.sms.ibatis.model.LoteSMSLog;
import cl.laaraucana.sms.service.local.BatchReportSMSService;
import cl.laaraucana.sms.service.local.BulkSMSService;
import cl.laaraucana.sms.service.orchestrator.config.OrchestrationConstants;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BatchReportSMSOrchestrator {
    // TODO: tabla con niveles de salida 1..n
    private static final Logger logger = Logger.getLogger(BatchReportSMSOrchestrator.class);

    // Funcion compactada, una dependencia
    public int updateBatch() {
        // Listado de registros a procesar
        int warnings = 0;
        int errors = 0;
        BulkSMSService bulkSMSService = new BulkSMSService();
        BatchReportSMSService batchReportSMSService = new BatchReportSMSService();
        List<LoteSMSLog> listLoteSMSLog = getListEstadoLoteSMS();
        for (LoteSMSLog loteSMSLog : listLoteSMSLog) {
            logger.debug(String.format("Processing PID %s with state %s and %s sent message(s)", loteSMSLog.getId(), loteSMSLog.getEstado(), loteSMSLog.getMensajesEnviados()));

            // Registros a actualizar, todos deberian estar en estados: Pendiente y Progreso
            List<EstadoLoteSMS> listEstadoLoteSMS = bulkSMSService.getListEstadoLoteSMS(loteSMSLog.getId(), loteSMSLog.getEstado());
            for (EstadoLoteSMS estadoLoteSMS : listEstadoLoteSMS) {
                if (!estadoLoteSMS.getEstado().equals(OrchestrationConstants.statusBatchReportPending) &&
                        !estadoLoteSMS.getEstado().equals(OrchestrationConstants.statusBatchReportInProgress))
                    logger.warn(String.format("Inconsistent data record in table EstadoLoteSMS with Estado %s, Id %s and PID %s", estadoLoteSMS.getEstado(), estadoLoteSMS.getId(), loteSMSLog.getId()));
                warnings++;
            }

            if (listEstadoLoteSMS.size() == 0) {
                // TODO: Idealmente definir y marcar proceso como Error o un estado que describa problema.. por ahora log
                logger.warn(String.format("Inconsistent data record in table LoteSMSLog with PID %s", loteSMSLog.getId()));
                warnings++;
                continue;
            }

            // Comparacion entre respuesta de batch-report y cada uno de los registros en el listado para luego actualizar su estado
            List<EstadoLoteSMS> listEstadoLoteSMSUpdated = batchReportSMSService.batchReportSMS(listEstadoLoteSMS, loteSMSLog.getCodigoLoteSMS());

            // Actualizacion de LoteSMSLog estado Entregado
            if (null != listEstadoLoteSMSUpdated && listEstadoLoteSMSUpdated.size() == listEstadoLoteSMS.size()) {
                // Estado es el mismo todos los registros
                EstadoLoteSMS estadoLoteSMSSample = listEstadoLoteSMSUpdated.get(0);
                loteSMSLog.setEstado(estadoLoteSMSSample.getEstado());
                bulkSMSService.updateLoteSMSLog(loteSMSLog);

                // Actualizacion de EstadoLoteSMS
                for (EstadoLoteSMS estadoLoteSMS : listEstadoLoteSMSUpdated) {
                    int id = estadoLoteSMS.getId();
                    String codigoLoteSMS = estadoLoteSMS.getCodigoLoteSMS();
                    String estado = estadoLoteSMS.getEstado();
                    String codigoSMS = estadoLoteSMS.getCodigoSMS();
                    Timestamp fechaEnvio = estadoLoteSMS.getFechaEnvio();
                    Timestamp fechaRecepcion = estadoLoteSMS.getFechaRecepcion();

                    bulkSMSService.updateEstadoLoteSMS(id, codigoLoteSMS, estado, codigoSMS, fechaEnvio, fechaRecepcion);
                }
            } else {
                logger.error(String.format("Inconsistent data results after calling messaging service provider with PID %s", loteSMSLog.getId()));
                errors++;
            }
        }

        logger.info(String.format("Batch report process ended with %s warnings and %s errors", warnings, errors));
        if (warnings > 0 && errors > 0) return 1;
        else if (warnings > 0) return 2;
        else if (errors > 0) return 3;
        if (listLoteSMSLog.size() > 0) return 0;
        return 4;
    }

    private List<LoteSMSLog> getListEstadoLoteSMS() {
        BulkSMSService bulkSMSService = new BulkSMSService();
        List<LoteSMSLog> listLoteSMSLog = new ArrayList<LoteSMSLog>();
        List<LoteSMSLog> listLoteSMSLogStatusBatchReportPending = bulkSMSService.getListLoteSMSLog(OrchestrationConstants.statusBatchReportPending);
        List<LoteSMSLog> listLoteSMSLogStatusBatchReportInProgress = bulkSMSService.getListLoteSMSLog(OrchestrationConstants.statusBatchReportInProgress);
        listLoteSMSLog.addAll(listLoteSMSLogStatusBatchReportPending);
        listLoteSMSLog.addAll(listLoteSMSLogStatusBatchReportInProgress);
        return listLoteSMSLog;
    }
}