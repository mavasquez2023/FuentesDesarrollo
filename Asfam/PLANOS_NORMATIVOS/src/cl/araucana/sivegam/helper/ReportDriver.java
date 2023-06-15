package cl.araucana.sivegam.helper;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import net.sf.jasperreports.engine.util.JRSwapFile;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;

import cl.araucana.sivegam.vo.param.ListadoParametros;

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

    static GlobalProperties global = GlobalProperties.getInstance();

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    public ReportDriver() {
    }

    public static Connection connectDB(String databaseName, String userName, String password) {

        Connection jdbcConnection = null;

        try {

            Class.forName("com.ibm.as400.access.AS400JDBCDriver");
            jdbcConnection = DriverManager.getConnection(databaseName, userName, password);
        } catch (Exception ex) {
            String connectMsg = "Could not connect to the database: " + ex.getMessage() + " " + ex.getLocalizedMessage();
 //           logger.debug(connectMsg);
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
     * @param reporteFile
     *            holds the location of the Jasper Report file (.jrxml)
     * @param pathReportFinally
     *            Indicate the location final of the excel report.(.xls)
     */
    public static void runReport(String databaseName, String userName, String password, String reporteFile, String pathReportFinally, String periodo) {

        try {
            System.gc();
            String ambiente = global.getValor("SVG.properties.ambiente");
            ListadoParametros listaParam = ListadoParametros.getInstancia();

            long start1 = System.currentTimeMillis();
            long timeFill = 0;
            long timeFillVirtua = 0;
            long timeFillExport = 0;
            HashMap hm = new HashMap();

 //           logger.debug("periodo: " + periodo);
            hm.put("Prdo", periodo);

            if ("desa".equalsIgnoreCase(ambiente)) {
                reporteFile = "//desa" + reporteFile;
            }
 //           logger.debug("reportFile = " + reporteFile);
            String path = listaParam.getServletContextRealPath() + reporteFile;
 //           logger.debug("path: " + path);

            File g = new File("");

            StringBuffer pathTemp = new StringBuffer("");
            pathTemp.append(g.getAbsolutePath());
            pathTemp.append("//archivos//tmp");

  //          logger.debug("path temp: " + pathTemp.toString());

            JRSwapFileVirtualizer virtualizer = null;

            try {

                JRSwapFile swapFile = new JRSwapFile(pathTemp.toString(), 512, 256);
                virtualizer = new JRSwapFileVirtualizer(512, swapFile, true);
                hm.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);

 //               logger.debug("path: " + path);
                Connection jdbcConnection = Helper.getConnection();
                JasperPrint jasperPrint = JasperFillManager.fillReport(path, hm, jdbcConnection); // Funciona

                JRXlsxExporter exporterXLS = new JRXlsxExporter();
                exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
                exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_FILE, new java.io.File(pathReportFinally));
                exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                exporterXLS.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
                exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);

                virtualizer.setReadOnly(true);
                timeFill =  System.currentTimeMillis();
                System.err.println("[" + reporteFile + "] Virtualizador Filling time : " + (timeFill - start1));
                timeFillVirtua = timeFill - start1;
                long start2 = System.currentTimeMillis();
                logger.debug("INICIO EXPORTREPORT [" + reporteFile + "]");
                exporterXLS.exportReport();
                logger.debug("FIN EXPORTREPORT [" + reporteFile + "]");

                timeFill =  System.currentTimeMillis();

                System.err.println("[" + reporteFile + "] EXPORT REPORT Filling time : " + (timeFill - start2));
                timeFillExport = timeFill - start2;

                System.err.println("[" + reporteFile + "] Filling time total: " + (timeFillExport + timeFillVirtua));
            } finally {
                if (virtualizer != null)
                    virtualizer.cleanup();
                    System.gc();
            }

        } catch (Exception ex) {
            String connectMsg = "No se pudo crear el reporte [" + reporteFile + "]" + ex.getMessage() + " " + ex.getLocalizedMessage();
            System.err.println(connectMsg);
            ex.printStackTrace();
        }
    }

}
