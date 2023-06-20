package cl.laaraucana.sms.utils;

import java.util.ResourceBundle;

public class Configuration {
    // Configuracion de variables asociadas a proveedor de servicio de mensajeria
    public static final String endpointSendSMS = Configuration.getProperty("endpoint.sms.send");
    public static final String endpointStatusSMS = Configuration.getProperty("endpoint.sms.status.sms");
    public static final String endpointStatusURL = Configuration.getProperty("endpoint.sms.status.url");
    public static final String endpointBulkSMS = Configuration.getProperty("endpoint.sms.bulk");
    public static final String endpointBatchReport = Configuration.getProperty("endpoint.sms.report");
    public static final String apiKey = Configuration.getProperty("api.key");
    public static final String username = Configuration.getProperty("auth.username");
    public static final String password = Configuration.getProperty("auth.password");

    // Configuracion de tareas programadas
    public static final String taskUpdateStatusURLMaxRetries = Configuration.getProperty("task.update.status.url.retries");
    public static final String taskProcessBatchSize = Configuration.getProperty("task.process.batch.size");
    public static final String taskProcessBatchThreshold = Configuration.getProperty("task.process.batch.threshold");
    public static final String taskProcessBatchIterations = Configuration.getProperty("task.process.batch.iterations");

    // Roles de usuario
    public static final String securityUserRoleSingle = Configuration.getProperty("security.user.rol.single");
    public static final String securityUserRoleBulk = Configuration.getProperty("security.user.rol.bulk");
    public static final String securityUserRoleConsole = Configuration.getProperty("security.user.rol.console");

    public static String getProperty(String key) {
        ResourceBundle configuration = ResourceBundle.getBundle("main");
        String environment = configuration.getString("environment");
        ResourceBundle resourceBundle = ResourceBundle.getBundle(environment);
        return resourceBundle.getString(key);
    }
}