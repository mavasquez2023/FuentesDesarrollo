package cl.araucana.independientes.helper;

import java.sql.*;
import java.util.HashMap;

import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.JasperReport;

import cl.araucana.independientes.vo.param.ListadoParametros;

/**
 * Driver program to connect to a database and to view a jasper report (.jrxml)
 * 
 * @author Oguzhan Topsakal
 * @since 23 March 2006
 * 
 * Required jar files to run this class: 1. jasperreports-1.2.0.jar 2.
 * classes12.jar (for Oracle JDBC connection) 3. commons-beanutils-1.5.jar 4.
 * commons-collections-2.1.jar 5. commons-digester-1.7.jar 6.
 * commons-logging-1.0.2.jar
 * 
 * @modified SToro para proyecto Afiliado Independiente de La Araucana
 * 
 */

public class ReportDriver {

    /**
     * Constructor for ReportDriver
     */
    public ReportDriver() {

    }

    /**
     * Takes 3 parameters: databaseName, userName, password and connects to the
     * database.
     * 
     * @param databaseName
     *            holds database name,
     * @param userName
     *            holds user name
     * @param password
     *            holds password to connect the database,
     * @return Returns the JDBC connection to the database
     */
    public static Connection connectDB(String databaseName, String userName,
            String password) {

        Connection jdbcConnection = null;

        // La DB para conectarse a Independientes es : "jdbc:as400://146.83.1.5;translate binary=true";

        try {
            //Class.forName("oracle.jdbc.driver.OracleDriver"); // Driver originalmente usado por la clase
            Class.forName("com.ibm.as400.access.AS400JDBCDriver");
            jdbcConnection = DriverManager.getConnection(databaseName,
                    userName, password);
        } catch (Exception ex) {
            String connectMsg = "Could not connect to the database: "
                + ex.getMessage() + " " + ex.getLocalizedMessage();
            System.out.println(connectMsg);
        }
        return jdbcConnection;
    }

    /**
     * Takes 4 parameters: databaseName, userName, password, reportFileLocation
     * and connects to the database and prepares and views the report.
     * 
     * @param databaseName
     *            holds database name,
     * @param userName
     *            holds user name
     * @param password
     *            holds password to connect the database,
     * @param reportFile
     *            holds the location of the Jasper Report file (.jrxml)
     */
    public static void runReport(String databaseName, String userName,
            String password, String reportFile) {
        try {

            ListadoParametros listaParam = ListadoParametros.getInstancia();

            HashMap hm = new HashMap();

            //Ejemplo de HasMap con parámetros de entrada
            hm.put("Fch_Inicio", "2012-04-25");
            hm.put("Fch_Fin", "2012-05-17");
            hm.put("Id_Oficina", "222");

            String path = listaParam.getServletContextRealPath() + "/jrxml/Nominas_Report_Parametros.jrxml";

            //JasperDesign jasperDesign = JRXmlLoader.load(reportFile); //Funciona
            JasperDesign jasperDesign = JRXmlLoader.load(path); //Funciona OK

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign); // Funciona

            Connection jdbcConnection = Helper.getConnection();

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hm, jdbcConnection); //Funciona

            //INICIO Bloque
            //Este bloque tiene como funcion probar la generacion de reportes en Excel sin pasar pro el applet
            JRExporter exporter = new JRXlsExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reporteExcel.xls"));
            exporter.exportReport();
            //Fin Bloque

            //JasperViewer.viewReport(jasperPrint); // Funciona, pero bota el server
            JasperViewer.viewReport(jasperPrint, false);// Funciona

        } catch (Exception ex) {
            String connectMsg = "No se pudo crear el reporte " + ex.getMessage() + " " + ex.getLocalizedMessage();
            System.out.println(connectMsg);
        }
    }


}
