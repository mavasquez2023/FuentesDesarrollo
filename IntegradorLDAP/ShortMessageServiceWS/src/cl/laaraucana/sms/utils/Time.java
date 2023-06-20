package cl.laaraucana.sms.utils;

import java.sql.Timestamp;
import java.util.Date;

public class Time {

    public static Timestamp getCurrentTimestamp() {
        Date date = new Date();
        long time = date.getTime();
        return new Timestamp(time);
    }
}
