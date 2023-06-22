package cl.araucana.sivegam.struts.dwr.actions;

import java.io.File;

import cl.araucana.sivegam.helper.Helper;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.impl.GenerarReportesCesantiaImpl;
import cl.araucana.sivegam.impl.GenerarReportesImpl;
import cl.araucana.sivegam.vo.CesantiaVO;
import cl.araucana.sivegam.vo.GenerarReportesVO;
import cl.araucana.sivegam.vo.MaestroSivegamVO;
import cl.araucana.sivegam.vo.PeriodoProcesoVO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.StatusProcesoVO;
import cl.araucana.sivegam.vo.TipoProcesosVO;
import cl.araucana.sivegam.vo.param.ListadoParametros;

public class GenerarReporteCesantiaDWR {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    /**
     * Funcion que sirve como enlace de comunicacion entre la capa vista y la
     * capa de implementacion. Tiene como funcionalidad invocar a la funcion que
     * inserta en la tabla maestrosivegam.
     */
    public GenerarReportesVO insertarMaestroSivegam(String fechaProceso, String usuarioSivegam, String idTipoReporte, String periodoMes) {

        return GenerarReportesImpl.insertarMaestroSivegam(fechaProceso, usuarioSivegam, idTipoReporte, periodoMes);

    }

    /**
     * Funcion que genera los reportes en xls. Sirve como puente de comunicacion
     * con la clase implement
     */
    public RespuestaVO generarReporteXLS(String idReporteXls, String periodoReporte, String idMaestroSiv, String mesReporte, String usser, String fechaReporte) {

        return GenerarReportesCesantiaImpl.generarReporteXLS(idReporteXls, periodoReporte, idMaestroSiv, mesReporte, usser, fechaReporte);
    }

    /** Funcion que genera reportes de errores en xls. */
    public static RespuestaVO generarReporteErroresXls(String idFlagReporteXls, String periodoReporte, String idMasterSivegam, String mesReporte, String usser, String fechaSistema) {

        return GenerarReportesCesantiaImpl.generarReporteErroresXls(idFlagReporteXls, periodoReporte, idMasterSivegam, mesReporte, usser, fechaSistema);
    }

    /**
     * Funcion que sirve como enlace de comunicacion entre la capa vista y la
     * capa de implementacion. Tiene como funcionalidad invocar a la funcion que
     * llama al proceso cobol, discriminando por el tipo de reporte.
     */
    public CesantiaVO llamarProcesoCobol(String idSecuencia, String idReporte, String periodo) {

        return GenerarReportesCesantiaImpl.llamarProcesoCobolCesantia(idSecuencia, idReporte, periodo);
    }

    /** Funcion que obtiene la descripcion del status inicial del proceso. */
    public StatusProcesoVO obtenerStatusProceso() {

        String descripcionStatusProceso = "";
        StatusProcesoVO status = new StatusProcesoVO();

        ListadoParametros listaParamStatus = ListadoParametros.getInstancia();
        StatusProcesoVO[] sp = listaParamStatus.getListStatusProceso();

        for (int i = 0; i < sp.length; i++) {

            if (sp[i].getStatus_proceso() == 1) {

                descripcionStatusProceso = sp[i].getDescripcion_status_proceso();
                break;
            }
        }
  //      logger.debug(descripcionStatusProceso);
        status.setDescripcion_status_proceso(descripcionStatusProceso);
        return status;
    }

    public static RespuestaVO insertarReporte(int tipoReporte, String nombreArchivo, String periodo) {
        return GenerarReportesCesantiaImpl.insertarReporte(tipoReporte, nombreArchivo, periodo);
    }

    public static MaestroSivegamVO selectMaxIdMaestroSivegam(String idReporte, String periodoReporte) {

        return GenerarReportesImpl.selectMaxIdMaestroSivegam(idReporte, periodoReporte);
    }

    /**
     * Funcion que retorna la ruta de un archivo txt en particular, dado su id
     * de proceso.
     */
    public static String retornarRutaArchivoTxt(int idTipoProceso, String nombreArchivo) {

        String rutaCarpeta = "";

        ListadoParametros lp = ListadoParametros.getInstancia();
        TipoProcesosVO[] tp = lp.getListTipoProcesos();

        for (int i = 0; i < tp.length; i++) {
            if (tp[i].getId_tipo_proceso() == idTipoProceso) {
                rutaCarpeta = tp[i].getDesc_carpeta_txt();
                break;
            }
        }
 //       logger.debug("rutaCarpeta:" + rutaCarpeta);
        File file = new File("");
        StringBuffer ruta = new StringBuffer();
        ruta.append(file.getAbsolutePath());
        ruta.append(rutaCarpeta);
        ruta.append(nombreArchivo);

        return ruta.toString();

    }

    /** Funcion que verifica si el archivo retornado existe en el servidor. */
    public static int verificaExisteArchivo(String ruta) {

        int existe = 0;
        File archivo = new File(ruta.toString());
        if (archivo.exists()) {
            existe = 1;
        }

        return existe;
    }

    public static RespuestaVO deleteArchivoTxt(int idReporte, String nombreArchivo) {

        String rutaFile = "";
        RespuestaVO resp = new RespuestaVO();

        rutaFile = retornarRutaArchivoTxt(idReporte, nombreArchivo);
  //      logger.debug("rutaFile desde delete archivo txt: " + rutaFile);

        if ((verificaExisteArchivo(rutaFile)) == 1) {

            int i = Helper.deleteFile(rutaFile);
            if (i == 1) {
                resp.setCodRespuesta(0);
            }
        } else {
            resp.setCodRespuesta(99);
        }

        return resp;
    }

    /** Funciones genericas para eliminacion de archivos xls de cesantia. */
    public static String retornarRutaArchivosXls(int idTipoProceso, String nombreArchivo) {

        logger.debug("************** DESDE RETORNAR RUTA XLS *******************");
        String rutaCarpeta = "";

        ListadoParametros lp = ListadoParametros.getInstancia();
        TipoProcesosVO[] tp = lp.getListTipoProcesos();

        for (int i = 0; i < tp.length; i++) {
            if (tp[i].getId_tipo_proceso() == idTipoProceso) {
                rutaCarpeta = tp[i].getDesc_carpeta_xls();
                break;
            }
        }
        logger.debug("rutaCarpeta:" + rutaCarpeta);
        File file = new File("");
        StringBuffer ruta = new StringBuffer();
        ruta.append(file.getAbsolutePath());
        ruta.append(rutaCarpeta);
        ruta.append(nombreArchivo);

        return ruta.toString();
    }

    public static RespuestaVO deleteArchivoXls(int idReporte, String nombreArchivo) {

        logger.debug("************* DESDE DELETE ARCHIVO XLS *******************");
        String rutaFile = "";
        RespuestaVO resp = new RespuestaVO();

        rutaFile = retornarRutaArchivosXls(idReporte, nombreArchivo);
        logger.debug("rutaFile desde delete archivo xls: " + rutaFile);

        if ((verificaExisteArchivo(rutaFile)) == 1) {
            int i = Helper.deleteFile(rutaFile);
            if (i == 1) {
                resp.setCodRespuesta(0);
            }
        } else {
            resp.setCodRespuesta(99);
        }

        return resp;
    }

    /** Funcion que obtiene los meses correspondiente a la tabla periodoprocesos. */
    public PeriodoProcesoVO[] obtenerDataPeriodoProceso(String glosa) {

        ListadoParametros listaParam = ListadoParametros.getInstancia();
        PeriodoProcesoVO[] periodoProceso = new PeriodoProcesoVO[0];

        if ("PeriodoProceso".equals(glosa)) {
            periodoProceso = listaParam.getListPeriodoProcesos();

            return periodoProceso;
        }

        return periodoProceso;
    }

    public static CesantiaVO reprocesarCobolValidacionCesantia(String idSecuenciaSivegam, String idReporte, String periodo) {

        return GenerarReportesCesantiaImpl.reprocesarCobolValidacionCesantia(idSecuenciaSivegam, idReporte, periodo);
    }

    /**
     * Funcion que invoca el borrado logico de las tablas de cesantia
     * discriminando por reporte.
     */
    public static RespuestaVO deleteLogicoSegunReporte(int tipoReporte) {

        logger.debug("Borrado logico cesantia dwr");
        return GenerarReportesCesantiaImpl.deleteLogicoSegunReporte(tipoReporte);
    }

    /** Funcion que actualiza status proceso cuando se requiera reprocesar. */
    /*
     * public static RespuestaVO actualizarStatusSegunPeriodoYProceso(String
     * tipoProceso, String mesPeriodo){
     * 
     * return
     * GenerarReportesImpl.actualizarStatusSegunPeriodoYProceso(tipoProceso,
     * mesPeriodo); }
     */
    public static RespuestaVO actualizarStatusSegunPeriodoYProceso(String tipoProceso, String mesPeriodo) {

        return GenerarReportesCesantiaImpl.actualizarStatusSegunPeriodoYProceso(tipoProceso, mesPeriodo);
    }

    /**
     * Actualiza el estado, luego de haber realizado una invocacion a un proceso
     * cobol.
     */
    public static StatusProcesoVO actualizacionEstado(int control) {

        StatusProcesoVO sp = new StatusProcesoVO();
        ListadoParametros lp = ListadoParametros.getInstancia();
        StatusProcesoVO[] status = lp.getListStatusProceso();

        for (int i = 0; i < status.length; i++) {
            if (status[i].getStatus_proceso() == control) {
                sp.setDescripcion_status_proceso(status[i].getDescripcion_status_proceso());
                break;
            }
        }

        return sp;
    }

    /**
     * Obtiene el maximo idmaestrosivegam cuando se requiere reprocesar, tomando
     * como parametros el tipo de reporte y el periodo.
     */
    public static MaestroSivegamVO selectMaxIdMaestroSivegamCesantia(int tipoArchivo, String periodoMes) {

        return GenerarReportesCesantiaImpl.selectMaxIdMaestroSivegamCesantia(tipoArchivo, periodoMes);

    }

    public static String obtenerRutaReporteExcel(int tipoReporte, String periodo) {

        return GenerarReportesCesantiaImpl.obtenerRutaReporteExcel(tipoReporte, periodo);
    }

    public static String obtenerRutaReporteTxt(int idTipoReporte, String periodoReporte) {

        return GenerarReportesCesantiaImpl.obtenerRutaReporteTxt(idTipoReporte, periodoReporte);
    }

    public static String obtenerRutaReporteExcelErr(int idTipoReporte, String periodoReporte) {

        return GenerarReportesCesantiaImpl.obtenerRutaReporteExcelErr(idTipoReporte, periodoReporte);
    }

    /**
     * Funcion que realiza update al status de la tabla maestro sivegam, antes
     * de reprocesar.
     */
    public static RespuestaVO updateStatusAntesDeReprocesar(long id, int status) {

        return GenerarReportesImpl.updateStatusAntesDeReprocesar(id, status);
    }
}
