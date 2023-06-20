package cl.laaraucana.sms.domain.exchange;

public class StatusList {

    // ALL SERVICES
    public final static String SUCCESS_SENT_STATUS_CODE = "SENT";
    public final static String SUCCESS_SENT_STATUS_DESCRIPTION = "Mensaje enviado exitosamente";

    public final static String SUCCESS_DELIVERED_STATUS_CODE = "DELIVERED";
    public final static String SUCCESS_DELIVERED_STATUS_DESCRIPTION = "Mensaje entregado exitosamente";

    public final static String SUCCESS_UNKNOWN_STATUS_CODE = "UNKNOWN";
    public final static String SUCCESS_UNKNOWN_STATUS_DESCRIPTION = "Estado de mensaje desconocido";

    public final static String ERR_PROVIDER_STATUS_CODE = "ERR_PROVIDER";
    public final static String ERR_PROVIDER_STATUS_DESCRIPTION = "Problemas de conexion con el proveedor de servicio";

    public final static String ERR_USER_NOT_AUTHORIZED_STATUS_CODE = "ERR_UNAUTHORIZED";
    public final static String ERR_USER_NOT_AUTHORIZED_STATUS_DESCRIPTION = "Usuario no autorizado";

    public final static String ERR_INVALID_CREDENTIALS_STATUS_CODE = "ERR_CREDENTIALS";
    public final static String ERR_INVALID_CREDENTIALS_STATUS_DESCRIPTION = "Credenciales invalidas";

    public final static String ERR_INVALID_PARAMETERS_STATUS_CODE = "ERR_PARAMETERS";
    public final static String ERR_INVALID_PARAMETERS_STATUS_DESCRIPTION = "Parametros invalidos";

    public final static String ERR_DATABASE_STATUS_CODE = "ERR_DATABASE";
    public final static String ERR_DATABASE_STATUS_DESCRIPTION = "Error asociado a operacion con la base de datos";

    public final static String ERR_UNKNOWN_STATUS_CODE = "ERR_UNKNOWN";
    public final static String ERR_UNKNOWN_STATUS_DESCRIPTION = "Error desconocido";

    // SERVICE STATUS-SMS (exclusive)
    public final static String ERR_MESSAGE_NOT_FOUND_STATUS_CODE = "ERR_MESSAGE";
    public final static String ERR_MESSAGE_NOT_FOUND_STATUS_DESCRIPTION = "Codigo de mensaje no existe";

    // SERVICE STATUS-URL (exclusive)
    public final static String SUCCESS_URL_VISITED_STATUS_CODE = "URL_VISITED";
    public final static String SUCCESS_URL_VISITED_STATUS_DESCRIPTION = "URL visitada por cliente";

    public final static String SUCCESS_URL_IGNORED_STATUS_CODE = "URL_IGNORED";
    public final static String SUCCESS_URL_IGNORED_STATUS_DESCRIPTION = "URL no visitada por cliente";


    /*
    public final static String ERR_PARAM_STATUS_DESCRIPTION = "Parámetro faltante o invalido en la petición";
    public final static String ERR_NUM_STATUS_DESCRIPTION = "Numero de destinatario invalido";
    public final static String ERR_URL_STATUS_DESCRIPTION = "URL no valida";
    public final static String ERR_HTTP_STATUS_DESCRIPTION = "Método de URL para callback no valido";
    public final static String ERR_CREDIT_STATUS_DESCRIPTION = "Créditos insuficientes";
    public final static String ERR_FLASH_STATUS_DESCRIPTION = "Mensaje flash no disponible";
    public final static String ERR_CODE_STATUS_DESCRIPTION = "Código invalido";
    public final static String ERR_SYS_STATUS_DESCRIPTION = "Error interno del sistema";
    public final static String ERR_UNKNOWN_STATUS_DESCRIPTION = "Error desconocido, contactar con soporte";*/
}