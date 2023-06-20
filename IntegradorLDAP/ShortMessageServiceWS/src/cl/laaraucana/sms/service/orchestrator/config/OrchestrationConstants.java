package cl.laaraucana.sms.service.orchestrator.config;

// Constantes de para reglas del proceso
public class OrchestrationConstants {
    // Estados del proceso de envio de mensajes
    public static final String statusSendBulkSuccess = "Sent";
    public static final String statusSendBulkError = "Error";

    // Estados del proceso de carga de datos temporales, en tabla LoteSMS
    public static final String statusTemporalLoading = "Pendiente";
    public static final String statusTemporalLoaded = "Procesado";

    // Estados de Sistema
    public static final String statusSystemEnabled = "Habilitado";
    public static final String statusSystemDisabled = "Deshabilitado";

    // Estados internos del proceso en ejecucion
    public static final String statusBatchReportStarted = "Iniciado";
    public static final String statusBatchReportLoaded = "Cargado";

    // Estados del proceso de envio notificados por el servicio Batch Report del proveedor de mensajeria
    public static final String statusBatchReportPending = "Pendiente";
    public static final String statusBatchReportInProgress = "Procesando";
    public static final String statusBatchReportDelivered = "Entregado";
    public static final String statusBatchReportError = "Error";

    public static final String batchProcessStateSent = "Enviado";
    public static final String batchProcessStateError = "Error";
    public static final String batchProcessStateUnauthorized = "No Autorizado";

    // Tuplas codigo/descripcion para estados en EstadoLoteSMS
    // Sistema no autorizado para envio
    public static final String batchProcessStateCodeUnauthorized = "ERR_UNAUTHORIZED";
    public static final String batchProcessStateDescriptionUnauthorized = "Sistema no autorizado para envio";
    // Problemas de conexion a proveedor de servicio o respuesta invalida, ejemplo: status 500
    public static final String batchProcessStateCodeProviderError = "ERR_PROVIDER";
    public static final String batchProcessStateDescriptionProviderError = "Problemas con proveedor de servicio de mensajeria";

    // Sequencias de estado en orden de proceso externo descrito por el proveedor de mensajeria
    public static final String[] batchReportStatuses = new String[]{
            statusBatchReportPending,
            statusBatchReportInProgress,
            statusBatchReportDelivered
    };
}