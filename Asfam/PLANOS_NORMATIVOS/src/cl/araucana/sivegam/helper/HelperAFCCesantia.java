package cl.araucana.sivegam.helper;

import java.io.File;
import java.util.StringTokenizer;

import cl.araucana.sivegam.vo.ConexionAS400VO;
import cl.araucana.sivegam.vo.FormatoReporteVO;
import cl.araucana.sivegam.vo.ProcesosAFCCesantiaVO;
import cl.araucana.sivegam.vo.ValoresConexionAS400VO;
import cl.araucana.sivegam.vo.param.ListadoParametros;

public class HelperAFCCesantia {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    /** Funcion que obtiene el nombre del archivo desde la tabla parametrica. */
    public static String retornarNombreArchivo(int tipoReporte, String periodoReporte) {

        String nombreTmp1 = "";
        String nombreTmp2 = "";
        String extension = "";

        StringBuffer nombre = new StringBuffer();
        ListadoParametros param = ListadoParametros.getInstancia();
        ProcesosAFCCesantiaVO[] procesosAFC = param.getListProcesoAfcCesantia();
        FormatoReporteVO[] formato = param.getListFormatoReportes();

        for (int i = 0; i < procesosAFC.length; i++) {
            if (procesosAFC[i].getId_tipo_proceso() == tipoReporte) {
                nombreTmp1 = procesosAFC[i].getNomenclatura_nombre_xls();
                break;
            }
        }

        StringTokenizer st = new StringTokenizer(nombreTmp1, "#");
        while (st.hasMoreTokens()) {
            nombreTmp1 = st.nextToken();
            nombreTmp2 = st.nextToken();
        }

        for (int i = 0; i < formato.length; i++) {

            if (formato[i].getFormato_reporte() == 1) {
                extension = formato[i].getDescripcion_formato_reporte();
                break;
            }
        }
        nombre.append(nombreTmp1);
        nombre.append(periodoReporte);
        nombre.append(nombreTmp2);
        nombre.append(".");
        nombre.append(extension);
 //       logger.debug("Nombre archivo: " + nombre.toString());
        return nombre.toString();
    }

    /** Funcion que retorna nombre reporte excel para cesantia. */
    public static String retornarNombreArchivoExcelCesantia(int tipoReporte, String periodoReporte) {

        String nombreTmp1 = "";
        String extension = "";

        StringBuffer nombre = new StringBuffer();
        ListadoParametros param = ListadoParametros.getInstancia();
        ProcesosAFCCesantiaVO[] procesosAFC = param.getListProcesoAfcCesantia();
        FormatoReporteVO[] formato = param.getListFormatoReportes();

        for (int i = 0; i < procesosAFC.length; i++) {
            if (procesosAFC[i].getId_tipo_proceso() == tipoReporte) {
                nombreTmp1 = procesosAFC[i].getNomenclatura_nombre_xls();
                break;
            }
        }

        StringTokenizer st = new StringTokenizer(nombreTmp1, "#");
        while (st.hasMoreTokens()) {
            nombreTmp1 = st.nextToken();
        }

        for (int i = 0; i < formato.length; i++) {
            if (formato[i].getFormato_reporte() == 1) {
                extension = formato[i].getDescripcion_formato_reporte();
                break;
            }
        }
        nombre.append(nombreTmp1);
        nombre.append(periodoReporte);
        nombre.append(".");
        nombre.append(extension);
 //       logger.debug("Nombre archivo: " + nombre.toString());
        return nombre.toString();
    }

    /**
     * Funcion que retorna la ruta del archivo que se va a generar,
     * correspondiente a AFC
     */
    public static String retornarRutaArchivo(int tipoArchivo) {

        String rutaTmp = "";
        File archivo = new File("");
        StringBuffer ruta = new StringBuffer();

        ListadoParametros param = ListadoParametros.getInstancia();
        ProcesosAFCCesantiaVO[] procesosAfc = param.getListProcesoAfcCesantia();

        for (int i = 0; i < procesosAfc.length; i++) {
            if (procesosAfc[i].getId_tipo_proceso() == tipoArchivo) {
                rutaTmp = procesosAfc[i].getRuta_xls_servidor();
            }
        }

        ruta.append(archivo.getAbsolutePath());
        ruta.append(rutaTmp);

 //       logger.debug("ruta archivo1: " + ruta.toString());

        return ruta.toString();
    }

    /**
     * Funcion que obtiene los datos de conexion a la base de datos que sera
     * usado como parametro en la funcion que genera el XLS
     */
    public static ConexionAS400VO obtenerDatosConexion() {

        ListadoParametros param = ListadoParametros.getInstancia();
        ValoresConexionAS400VO[] conexion = param.getListValoresConexionToAS400();
        ConexionAS400VO datosConexion = new ConexionAS400VO();

        for (int i = 0; i < conexion.length; i++) {
            if (conexion[i].getId_conexion_as400() == 1) {
                datosConexion.setIpServer(conexion[i].getDescripcion_conexion_as400());
            }

            if (conexion[i].getId_conexion_as400() == 2) {
                datosConexion.setUsuarioConexion(conexion[i].getDescripcion_conexion_as400());
            }

            if (conexion[i].getId_conexion_as400() == 3) {
                datosConexion.setClaveConexion(conexion[i].getDescripcion_conexion_as400());
            }
        }

        return datosConexion;
    }

    /**
     * Funcion que obtiene la ruta del proyecto y nombre de los reportes
     * precompilados en jasperreport.
     */
    public static String obtenerRutaNombreJRXML(int tipoReporte) {

        String rutaJasper = "";
        ListadoParametros param = ListadoParametros.getInstancia();
        ProcesosAFCCesantiaVO[] procesos = param.getListProcesoAfcCesantia();

        for (int i = 0; i < procesos.length; i++) {
            if (procesos[i].getId_tipo_proceso() == tipoReporte) {
                rutaJasper = procesos[i].getDescripcion_jasper();
            }
        }

 //       logger.debug("carpeta jasper: " + rutaJasper);
        return rutaJasper;
    }

    public static String retornarNombreArchivoErrores(int tipoReporte, String periodoReporte) {

        String nombreTmp1 = "";
        String nombreTmp2 = "";
        String extension = "";

        StringBuffer nombre = new StringBuffer();
        ListadoParametros param = ListadoParametros.getInstancia();
        ProcesosAFCCesantiaVO[] procesosAFC = param.getListProcesoAfcCesantia();
        FormatoReporteVO[] formato = param.getListFormatoReportes();

        for (int i = 0; i < procesosAFC.length; i++) {
            if (procesosAFC[i].getId_tipo_proceso() == tipoReporte) {
                nombreTmp1 = procesosAFC[i].getNomenclatura_nombre_xls_err();
            }
        }

        StringTokenizer st = new StringTokenizer(nombreTmp1, "#");
        while (st.hasMoreTokens()) {
            nombreTmp1 = st.nextToken();
            nombreTmp2 = st.nextToken();
        }

        for (int i = 0; i < formato.length; i++) {

            if (formato[i].getFormato_reporte() == 1) {
                extension = formato[i].getDescripcion_formato_reporte();
            }
        }
        nombre.append(nombreTmp1);
        nombre.append(periodoReporte);
        nombre.append(nombreTmp2);
        nombre.append(".");
        nombre.append(extension);
 //       logger.debug("Nombre archivo: " + nombre.toString());
        return nombre.toString();
    }

    /**
     * Funcion que retorna la ruta del archivo que se va a generar,
     * correspondiente a AFC
     */
    public static String retornarRutaArchivoErrores(int tipoArchivo) {

        String rutaTmp = "";
        File archivo = new File("");
        StringBuffer ruta = new StringBuffer();

        ListadoParametros param = ListadoParametros.getInstancia();
        ProcesosAFCCesantiaVO[] procesosAfc = param.getListProcesoAfcCesantia();

        for (int i = 0; i < procesosAfc.length; i++) {
            if (procesosAfc[i].getId_tipo_proceso() == tipoArchivo) {
                rutaTmp = procesosAfc[i].getRuta_xls_errores_servidor();
            }
        }

        ruta.append(archivo.getAbsolutePath());
        ruta.append(rutaTmp);

 //       logger.debug("ruta archivo: " + ruta.toString());

        return ruta.toString();
    }

    /**
     * Funcion que obtiene la ruta del proyecto y nombre de los reportes
     * precompilados en jasperreport.
     */
    public static String obtenerRutaNombreJRXMLErrores(int tipoReporte) {

        String rutaJasper = "";
        ListadoParametros param = ListadoParametros.getInstancia();
        ProcesosAFCCesantiaVO[] procesos = param.getListProcesoAfcCesantia();

        for (int i = 0; i < procesos.length; i++) {
            if (procesos[i].getId_tipo_proceso() == tipoReporte) {
                rutaJasper = procesos[i].getDescripcion_jasper_errores();
            }
        }

 //       logger.debug("carpeta jasper: " + rutaJasper);
        return rutaJasper;
    }

    public static String retornaNombreArchivoTxt(int tipoReporte, String periodoReporte) {

        String nombreTmp1 = "";
        String nombreTmp2 = "";
        String extension = "";

        StringBuffer nombre = new StringBuffer();
        ListadoParametros param = ListadoParametros.getInstancia();
        ProcesosAFCCesantiaVO[] procesosAFC = param.getListProcesoAfcCesantia();
        FormatoReporteVO[] formato = param.getListFormatoReportes();

        for (int i = 0; i < procesosAFC.length; i++) {
            if (procesosAFC[i].getId_tipo_proceso() == tipoReporte) {
                nombreTmp1 = procesosAFC[i].getNomenclatura_nombre_txt();
            }
        }

        StringTokenizer st = new StringTokenizer(nombreTmp1, "#");

        for (int i = 0; i < formato.length; i++) {
            if (formato[i].getFormato_reporte() == 2) {
                extension = formato[i].getDescripcion_formato_reporte();
            }
        }

        if (tipoReporte == 8 || tipoReporte == 9) {

            while (st.hasMoreTokens()) {
                nombreTmp1 = st.nextToken();
                nombreTmp2 = st.nextToken();
            }

            if (tipoReporte == 8) {
                nombreTmp2 = "13" + nombreTmp2;
            } else {
                nombreTmp2 = "30" + nombreTmp2;
            }

            nombre.append(nombreTmp1);
            nombre.append(periodoReporte);
            nombre.append(nombreTmp2);
            nombre.append(retornarFormatoReporteAfc().toLowerCase());
  //          logger.debug("Nombre archivo: " + nombre.toString());

        } else {

            while (st.hasMoreTokens()) {
                nombreTmp1 = st.nextToken();
            }

            nombre.append(nombreTmp1);
            nombre.append(periodoReporte);
            nombre.append(nombreTmp2);
            nombre.append(".");
            nombre.append(extension.toLowerCase());
 //           logger.debug("Nombre archivo: " + nombre.toString());
        }

        return nombre.toString();

    }

    public static String ontenerRutaArchivoTxt(int tipoArchivo) {

        String rutaTmp = "";
        File archivo = new File("");
        StringBuffer ruta = new StringBuffer();

        ListadoParametros param = ListadoParametros.getInstancia();
        ProcesosAFCCesantiaVO[] procesosAfc = param.getListProcesoAfcCesantia();

        for (int i = 0; i < procesosAfc.length; i++) {
            if (procesosAfc[i].getId_tipo_proceso() == tipoArchivo) {
                rutaTmp = procesosAfc[i].getRuta_txt_servidor();
            }
        }

        ruta.append(archivo.getAbsolutePath());
        ruta.append(rutaTmp);

 //       logger.debug("ruta archivo: " + ruta.toString());

        return ruta.toString();
    }

    public static String retornarFormatoReporteAfc() {

        String extensionTmp = "";
        StringBuffer extension = new StringBuffer();
        ListadoParametros param = ListadoParametros.getInstancia();
        FormatoReporteVO[] formato = param.getListFormatoReportes();

        for (int i = 0; i < formato.length; i++) {
            if (formato[i].getFormato_reporte() == 3) {
                extensionTmp = formato[i].getDescripcion_formato_reporte();
            }
        }

        extension.append(".");
        extension.append(extensionTmp);

        return extension.toString();
    }

    public static int existeArchivo(String rutaCompleta) {

 //       logger.debug("DESDE existeArchivo: " + rutaCompleta);
        int existe = 0;
        File f = new File(rutaCompleta);

        if (f.exists()) {
            existe = 1;
        }

        return existe;
    }
}
