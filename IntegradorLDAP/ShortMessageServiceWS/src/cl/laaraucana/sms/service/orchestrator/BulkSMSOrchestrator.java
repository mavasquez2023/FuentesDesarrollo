package cl.laaraucana.sms.service.orchestrator;

import cl.laaraucana.sms.ibatis.model.EstadoLoteSMS;
import cl.laaraucana.sms.ibatis.model.LoteSMSLog;
import cl.laaraucana.sms.ibatis.model.Sistema;
import cl.laaraucana.sms.service.local.BulkSMSService;
import cl.laaraucana.sms.service.security.AuthorizationService;
import cl.laaraucana.sms.service.orchestrator.config.OrchestrationConstants;
import cl.laaraucana.sms.utils.Configuration;
import cl.laaraucana.sms.utils.Time;
import org.apache.log4j.Logger;

import java.util.List;

public class BulkSMSOrchestrator {
    // TODO: tabla con niveles de salida 1..n
    private static final Logger logger = Logger.getLogger(BulkSMSOrchestrator.class);
    private static final int maxIterationsForCycle = Integer.parseInt(Configuration.taskProcessBatchIterations);

    // Orquestacion (main)
    public int processBatch() {
        int iterations = 0;
        boolean go = go();
        if (go) {

            // Revision, previa a carga, de sistemas habilitados para envio masivo en tabla temporal
            List<Sistema> sistemas = checkSystemsStatus();
            if (null == sistemas || sistemas.size() == 0) return 1;

            // Agrupacion de mensajes en lotes por sistema
            for (Sistema sistema : sistemas) {
                if (!go) go = go(sistema.getSistema());

                // Mientras existan datos por procesar se seleccionan registros de acuerdo un limite definido en Configuracion
                while (go) {
                    // Maximo de iteraciones por ciclo de ejecucion del proceso batch
                    if (iterations++ == maxIterationsForCycle) return -1;

                    // Almacena log de proceso, creando un registro en LoteSMSLog
                    LoteSMSLog loteSMSLog = startBatchProcess();

                    if (null != loteSMSLog) {
                        logger.info(String.format("Batch process started with PID %s for system %s", loteSMSLog.getId(), sistema.getSistema()));
                    } else {
                        logger.error("Batch process failed to start");
                        return 2;
                    }

                    // Carga datos temporales en tabla de estado EstadoLoteSMS
                    // Actualiza informacion del proceso en LoteSMSLog cambia estado a Cargado
                    loadBatch(loteSMSLog, sistema);

                    // Elimina datos temporales
                    clearTemporalData();

                    // Envio de mensajes si el sistema esta habilitado
                    // Actualiza informacion del proceso en LoteSMSLog cambia estado a Pendiente
                    sendMessages(loteSMSLog, sistema);

                    // Continua si existen registros que procesar por sistema
                    go = go(sistema.getSistema());
                }
            }
            return 0;
        }
        return 3;
    }

    private boolean sendMessages(LoteSMSLog loteSMSLog, Sistema sistema) {
        boolean wereMessagesSent = false;

        BulkSMSService bulkSMSService = new BulkSMSService();
        if (sistema.getEstado().equals(OrchestrationConstants.statusSystemEnabled)) {
            List<EstadoLoteSMS> listEstadoLoteSMS = bulkSMSService.getListEstadoLoteSMS(loteSMSLog.getId(), OrchestrationConstants.statusBatchReportInProgress);
            if (listEstadoLoteSMS.size() > 0) {
                LoteSMSLog updatedLoteSMSLog = bulkSMSService.bulkSMS(listEstadoLoteSMS, loteSMSLog);
                wereMessagesSent = updatedLoteSMSLog.getEstado().equals(OrchestrationConstants.statusSendBulkSuccess);
                if (wereMessagesSent) {
                    loteSMSLog.setMensajesEnviados(updatedLoteSMSLog.getMensajesEnviados());
                    loteSMSLog.setMensajesInvalidos(updatedLoteSMSLog.getMensajesInvalidos());
                    loteSMSLog.setMensajesErroneos(updatedLoteSMSLog.getMensajesErroneos());
                    loteSMSLog.setEstado(OrchestrationConstants.statusBatchReportPending);
                    if (loteSMSLog.getMensajesTotal() != updatedLoteSMSLog.getMensajesTotal()) {
                        logger.warn(String.format("Total size of messages in response %s differs from recorded amount %s, for system %s with PID %s",
                                updatedLoteSMSLog.getMensajesTotal(), loteSMSLog.getMensajesTotal(), loteSMSLog.getSistema(), loteSMSLog.getId()));
                    }
                } else {
                    loteSMSLog.setEstado(OrchestrationConstants.batchProcessStateError);
                    loteSMSLog.setMensajesErroneos(loteSMSLog.getMensajesTotal());
                }
                loteSMSLog.setFechaTermino(Time.getCurrentTimestamp());
                bulkSMSService.updateLoteSMSLog(loteSMSLog);
            } else {
                logger.error(String.format("No messages were found in EstadoLoteSMS for system %s with PID %s", loteSMSLog.getSistema(), loteSMSLog.getId()));
            }

            // Graba en EstadoLoteSMS el codigo de respuesta batch_code entregado por el proveedor de mensajeria y actualiza estado.
            if (wereMessagesSent)
                bulkSMSService.updateEstadoLoteSMS(loteSMSLog.getCodigoLoteSMS(), loteSMSLog.getId(), OrchestrationConstants.statusBatchReportPending);
            else
                bulkSMSService.updateEstadoLoteSMS(loteSMSLog.getCodigoLoteSMS(), loteSMSLog.getId(), OrchestrationConstants.statusBatchReportError);
        } else {
            loteSMSLog.setEstado(OrchestrationConstants.statusSendBulkError);
            loteSMSLog.setFechaTermino(Time.getCurrentTimestamp());
            bulkSMSService.updateLoteSMSLog(loteSMSLog);
        }
        return wereMessagesSent;
    }

    private void clearTemporalData() {
        final String estado = OrchestrationConstants.statusTemporalLoaded;
        BulkSMSService bulkSMSService = new BulkSMSService();
        bulkSMSService.updateEstado(OrchestrationConstants.statusTemporalLoading, OrchestrationConstants.statusTemporalLoaded);
        int result = bulkSMSService.clearLoteSMS(estado);
        // return result > 0;
    }

    private void loadBatch(LoteSMSLog loteSMSLog, Sistema sistema) {
        // Carga datos temporales en tabla de estado (LoteSMS -> LoteEstadoSMS)
        // El PID del proceso es id en LoteSMSLog
        BulkSMSService bulkSMSService = new BulkSMSService();
        logger.info(String.format("Loading batch for system %s", sistema.getSistema()));
        boolean isSystemEnabled = sistema.getEstado().equals(OrchestrationConstants.statusSystemEnabled);
        String estado = isSystemEnabled ? OrchestrationConstants.statusBatchReportInProgress : OrchestrationConstants.batchProcessStateUnauthorized;
        String estadoCodigo = isSystemEnabled ? "" : OrchestrationConstants.batchProcessStateCodeUnauthorized;
        String estadoDescripcion = isSystemEnabled ? "" : OrchestrationConstants.batchProcessStateDescriptionUnauthorized;
        loteSMSLog.setSistema(sistema.getSistema());
        bulkSMSService.loadEstadoLoteSMS(loteSMSLog, estado, estadoCodigo, estadoDescripcion);

        // Mensajes
        int countEstadoLoteSMS = bulkSMSService.countEstadoLoteSMS(loteSMSLog.getId());

        // Actualiza estado en log del proceso
        loteSMSLog.setEstado(OrchestrationConstants.statusBatchReportLoaded);
        loteSMSLog.setSistema(sistema.getSistema());
        loteSMSLog.setMensajesTotal(countEstadoLoteSMS);
        loteSMSLog.setMensajesErroneos(isSystemEnabled ? 0 : countEstadoLoteSMS);
        bulkSMSService.updateLoteSMSLog(loteSMSLog);
    }

    private List<Sistema> checkSystemsStatus() {
        // Retorna sistemas en LoteSMS con carga pendiente de envio
        try {
            BulkSMSService bulkSMSService = new BulkSMSService();
            AuthorizationService authorizationService = new AuthorizationService();
            List<Sistema> sistemas = bulkSMSService.getListSistema();

            for (Sistema sistema : sistemas) {
                String estado = authorizationService.isSystemAuthorized(sistema.getSistema())
                        ? OrchestrationConstants.statusSystemEnabled
                        : OrchestrationConstants.statusSystemDisabled;
                sistema.setEstado(estado);
                logger.debug(String.format("Sistema %s esta %s para envios", sistema.getSistema(), estado.toLowerCase()));
            }
            return sistemas;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private LoteSMSLog startBatchProcess() {
        // Inicia el proceso y crea registro LoteSMSLog
        try {
            BulkSMSService bulkSMSService = new BulkSMSService();
            LoteSMSLog loteSMSLog = new LoteSMSLog();
            loteSMSLog.setEstado(OrchestrationConstants.statusBatchReportStarted);
            loteSMSLog.setFechaInicio(Time.getCurrentTimestamp());
            return bulkSMSService.createLoteSMSLog(loteSMSLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean go() {
        try {
            BulkSMSService bulkSMSService = new BulkSMSService();
            return bulkSMSService.selectCountUnprocessedBatchSize(OrchestrationConstants.statusTemporalLoading) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean go(String sistema) {
        try {
            BulkSMSService bulkSMSService = new BulkSMSService();
            return bulkSMSService.selectCountUnprocessedBatchSize(OrchestrationConstants.statusTemporalLoading, sistema) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}