package cl.laaraucana.sms.service.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import cl.laaraucana.sms.service.local.StatusSMSServiceLocal;
import cl.laaraucana.sms.service.local.StatusURLServiceLocal;
import org.apache.log4j.Logger;

public class MessageStatusUpdate {
    private static final Logger logger = Logger.getLogger(MessageStatusUpdate.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public void checkStatusSMS() {
        logger.info(String.format("Checking SMS Status at %s", dateFormat.format(new Date())));
        StatusSMSServiceLocal statusSMSServiceLocal = new StatusSMSServiceLocal();
        statusSMSServiceLocal.checkStatusSMS();
    }

    public void checkStatusURL() {
        logger.info(String.format("Checking URL Status at %s", dateFormat.format(new Date())));
        StatusURLServiceLocal statusURLServiceLocal = new StatusURLServiceLocal();
        statusURLServiceLocal.checkStatusURL();
    }

    public void updateStatusURL() {
        logger.info(String.format("Updating URL Status at %s", dateFormat.format(new Date())));
        StatusURLServiceLocal statusURLServiceLocal = new StatusURLServiceLocal();
        statusURLServiceLocal.updateStatusURL();
    }
}