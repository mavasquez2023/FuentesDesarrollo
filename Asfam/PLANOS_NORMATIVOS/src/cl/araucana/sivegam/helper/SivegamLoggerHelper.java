package cl.araucana.sivegam.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class SivegamLoggerHelper {
    private final GlobalProperties global = GlobalProperties.getInstance();
    private static Logger logger3;
    private static SivegamLoggerHelper instance = new SivegamLoggerHelper();

    private SivegamLoggerHelper() {
        try {
            String ruta_propsfile = global.getValor("SVG.log.aplicacion");
            ruta_propsfile = System.getProperty("user.dir") + "/" + ruta_propsfile;

            /*
             * Instancio util.Properties para rescatar datos de configuracion de
             * log4j desde la ruta que se construyó (ruta_props_file)
             */
            Properties props = new Properties();
            FileInputStream fileInputStream = new FileInputStream(new File(ruta_propsfile));
            props.load(fileInputStream);
            PropertyConfigurator.configure(props);

            /*
             * Inicio configuracion log4j según procediimento standard
             */
            logger3 = Logger.getLogger(SivegamLoggerHelper.class);

            /*
             * PatternLayout layout = new
             * PatternLayout(props.getProperty("log4j.appender.LOG1.layout.ConversionPattern"));
             * RollingFileAppender appender = new
             * RollingFileAppender(layout,props.getProperty("log4j.appender.LOG1.File"),true);
             * 
             * logger3.addAppender(appender);
             * logger3.setLevel(Level.toLevel(props.getProperty("log4j.appender.LOG1.threshold")));
             */
            // Si quisiera fijar el nivel dentro del codigo ---->
            // logger3.setLevel((Level) Level.ALL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SivegamLoggerHelper getInstance() {
        return instance;
    }

    public void debug(Exception ex) {
        try {
            final Writer result = new StringWriter();
            final PrintWriter printWriter = new PrintWriter(result);
            ex.printStackTrace(printWriter);
            logger3.debug(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void debug(String logstr, String nombreClase) {
        try {
            logger3 = Logger.getLogger(nombreClase);
            logger3.debug(logstr);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void debug(String logstr) {
        try {
            logger3.debug("OUT: " + logstr);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void info(Exception ex) {
        try {
            final Writer result = new StringWriter();
            final PrintWriter printWriter = new PrintWriter(result);
            ex.printStackTrace(printWriter);
            logger3.debug(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void info(String logstr, String nombreClase) {
        try {
            logger3 = Logger.getLogger(nombreClase);
            logger3.info(logstr);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void error(Throwable e, String nombreClase) {
        try {
            logger3 = Logger.getLogger(nombreClase);
            logger3.error("Error: ", e);
        } catch (Throwable e1) {
            e.printStackTrace();
        }
    }

    public void error(String logstr, String nombreClase) {
        try {
            logger3 = Logger.getLogger(nombreClase);
            logger3.error(logstr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mess(String logstr) {
        try {
            logger3.debug("OUT: " + logstr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
