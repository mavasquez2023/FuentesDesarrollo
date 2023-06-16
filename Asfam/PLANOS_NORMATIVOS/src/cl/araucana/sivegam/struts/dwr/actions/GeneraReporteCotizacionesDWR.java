package cl.araucana.sivegam.struts.dwr.actions;

import java.io.File;

import cl.araucana.sivegam.helper.Helper;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.impl.GenerarReportesImpl;
import cl.araucana.sivegam.vo.GenerarReportesVO;
import cl.araucana.sivegam.vo.MaestroSivegamVO;
import cl.araucana.sivegam.vo.PeriodoProcesoVO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.Sif018VO;
import cl.araucana.sivegam.vo.StatusProcesoVO;
import cl.araucana.sivegam.vo.TipoProcesosVO;
import cl.araucana.sivegam.vo.param.ListadoParametros;

public class GeneraReporteCotizacionesDWR {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    public GenerarReportesVO insertarMaestroSivegam(String fechaProceso, String usuarioSivegam, String idTipoReporte, String periodoMes) {

        return GenerarReportesImpl.insertarMaestroSivegam(fechaProceso, usuarioSivegam, idTipoReporte, periodoMes);

    }

    /**
     * Esta funcion sirve como capa de comunicacion con la capa IMPL para la
     * invocacion del proceso cobol. recibe como parametro el id del cl del
     * reporte que se quiere invocar
     */
    public RespuestaVO llamarProcesoCobol(String idSecuencia, String idReporte, String periodo) {

        return GenerarReportesImpl.llamarProcesoCobol(idSecuencia, idReporte, periodo, "0");
        /*
         * RespuestaVO resp = new RespuestaVO();
         * 
         * resp = GenerarReportesImpl.llamarProcesoCobol(idSecuencia, idReporte,
         * periodo);
         * 
         * if(resp.getCodRespuesta() == 0){ return "OK"; }
         * 
         * return "KO";
         */
    }

    /**
     * Funcion de enlace de comunicacion entre la capa vista y la capa de
     * implementacion. Tiene como funcionalidad la invocacion a la funcion que
     * genera el reporte usando las librerias de jasper report. Recibe como
     * parametro: @ param idFlagReporteXls Flag que contiene el id del reporte
     *            que se va a generar. La funcion retorna un objeto del tipo
     *            RespuestaVO, especificamente un código de retorno, si dicho
     *            codigo lleva el valor 0 significa que el reporte se generó de
     *            forma exitosa, de lo contrario no se generó dicho reporte.
     */
    public RespuestaVO generarReporteXLS(String idFlagReporteXls, String periodo, String idMaestroSiv, String mesReporte, String usser, String fechaReporte) {

        return GenerarReportesImpl.generarReporteXLS(idFlagReporteXls, periodo, idMaestroSiv, mesReporte, usser, fechaReporte);
    }

    /**
     * Funcion que verifica el status del proceso. Recibe como parametros el
     * periodo y el tipo de reporte que se desea consultar Retorna el status del
     * proceso, correspondiente a los datos de la consulta
     */
    public Sif018VO obtenerStatus(String periodo, String tipoProceso) {

        Sif018VO vo = new Sif018VO();
        vo = GenerarReportesImpl.obtenerStatus(periodo, tipoProceso);

        return vo;
    }

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
        status.setDescripcion_status_proceso(descripcionStatusProceso);
        return status;
    }

    public StatusProcesoVO actualizaEstadoGrilla() {

        String descripcionStatusProceso = "";
        StatusProcesoVO status = new StatusProcesoVO();

        ListadoParametros listaParamStatus = ListadoParametros.getInstancia();
        StatusProcesoVO[] sp = listaParamStatus.getListStatusProceso();

        for (int i = 0; i < sp.length; i++) {

            if (sp[i].getStatus_proceso() == 4) {

                descripcionStatusProceso = sp[i].getDescripcion_status_proceso();
                break;
            }
        }

        status.setDescripcion_status_proceso(descripcionStatusProceso);
        return status;
    }

    /** Obtiene el periodo del proceso (meses) para actualizar la grilla */
    public PeriodoProcesoVO obtenerMesesParaPeriodoGrilla(String codigoMeses) {

        String glosaMes = "";
        PeriodoProcesoVO pp = new PeriodoProcesoVO();
        ListadoParametros listaParamPerPro = ListadoParametros.getInstancia();
        PeriodoProcesoVO[] periodoPro = listaParamPerPro.getListPeriodoProcesos();

        for (int i = 0; i < periodoPro.length; i++) {
            if (periodoPro[i].getPeriodo_proceso() == Long.parseLong(codigoMeses)) {
                glosaMes = periodoPro[i].getDescripcion_periodo_proceso();
                break;
            }
        }
        pp.setDescripcion_periodo_proceso(glosaMes);
        return pp;
    }

    public MaestroSivegamVO selectMaxIdMaestroSivegam(String idReporte, String periodoReporte) {

        return GenerarReportesImpl.selectMaxIdMaestroSivegam(idReporte, periodoReporte);
    }

    /**
     * Funcion que obtiene el idtipoarchivo dado el idtipoproceso...
     * homologacion para obtener el idmax de sivegam y poder insertar en detalle
     * reportes.
     */
    public TipoProcesosVO selectTipoArchivoHomologado(String idTipoProceso) {

        return GenerarReportesImpl.selectTipoArchivoHomologado(idTipoProceso);
    }

    /**
     * Funcion que obtiene los codigos para actualizar periodo y status de la
     * grilla al iniciar en pantalla.
     */
    public MaestroSivegamVO obtenerInformacionAActualizar(String idSivegam) {

        return GenerarReportesImpl.obtenerInformacionAActualizar(idSivegam);
    }

    /**
     * Funcion que obtiene la glosa de periodo y status de proceso para
     * actualizar la grilla al iniciar pantalla
     */
    public MaestroSivegamVO buscarGlosaStatusYGlosaMes(String idPeriodo, String idStatus) {

        return GenerarReportesImpl.buscarGlosaStatusYGlosaMes(idPeriodo, idStatus);
    }

    /**
     * Funcion que realiza el update a la tabla maestro sivegam, cuando el
     * proceso cobol ha enviado la respuesta dado el procesamiento
     */
    public RespuestaVO updateStatusProcesoSivegam(String idRespuestaCobol, String idMaestroSivegam, String fechaProceso) {

        return GenerarReportesImpl.updateStatusProcesoSivegam(idRespuestaCobol, idMaestroSivegam, fechaProceso);
    }

    /**
     * Funcion que retorna la ruta de un archivo txt en particular, dado su id
     * de proceso.
     */
    public static String retornarRutaArchivoTxt(String idTipoProceso, String nombreArchivo) {

        String rutaCarpeta = "";

        ListadoParametros lp = ListadoParametros.getInstancia();
        TipoProcesosVO[] tp = lp.getListTipoProcesos();

        for (int i = 0; i < tp.length; i++) {
            if (tp[i].getId_tipo_proceso() == Integer.parseInt(idTipoProceso)) {
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

    /** Funcion que retorna la ruta de un archivo xlsx */
    public static String retornarRutaArchivoXls(String idTipoProceso, String nombreArchivo) {

        String rutaCarpeta = "";
        ListadoParametros lp = ListadoParametros.getInstancia();
        TipoProcesosVO[] tp = lp.getListTipoProcesos();
        for (int i = 0; i < tp.length; i++) {
            if (tp[i].getId_tipo_proceso() == Integer.parseInt(idTipoProceso)) {
                rutaCarpeta = tp[i].getDesc_carpeta_xls();
                break;
            }
        }
  //      logger.debug("rutaCarpeta:" + rutaCarpeta);
        File file = new File("");
        StringBuffer ruta = new StringBuffer();
        ruta.append(file.getAbsolutePath());
        ruta.append(rutaCarpeta);
        ruta.append(nombreArchivo);

   //     logger.debug("Ruta en retornarRutaArchivoXls = " + ruta.toString());

        return ruta.toString();
    }

    /**
     * Funcion que verifica si un archivo esta en el server. La funcion retorna
     * 0 si el archivo existe, de lo contrario retorna 99.
     */
    /** Solo para archivos txt. */
    public RespuestaVO consultarExisteArchivoSegunPeriodo(String idTipoReporte, String nombreArchivo) {

        String rutaCarpeta = "";
        RespuestaVO resp = new RespuestaVO();
        ListadoParametros lp = ListadoParametros.getInstancia();
        TipoProcesosVO[] tp = lp.getListTipoProcesos();

        for (int i = 0; i < tp.length; i++) {
            if (tp[i].getId_tipo_proceso() == Integer.parseInt(idTipoReporte)) {
                rutaCarpeta = tp[i].getDesc_carpeta_txt();
                break;
            }
        }
  //      logger.debug("rutaCarpeta:" + rutaCarpeta);
        File file = new File("");
        StringBuffer ruta = new StringBuffer();
        ruta.append(file.getAbsolutePath());
        ruta.append(rutaCarpeta);
        ruta.append(nombreArchivo);

 //       logger.debug("ruta archivo: " + ruta.toString());
        File archivo = new File(ruta.toString());
        if (archivo.exists()) {
            resp.setCodRespuesta(0);
            resp.setRutaArchivo(ruta.toString());
        } else {
            resp.setCodRespuesta(99);
        }

        return resp;

    }

    /** Funcion que consulta en el server si existe el archivo xls. */
    public static RespuestaVO consultarExisteXLS(String idTipoReporte, String nombreArchivo) {

        RespuestaVO respuesta = new RespuestaVO();
        String rutaXLs = retornarRutaArchivoXls(idTipoReporte, nombreArchivo);

        File xls = new File(rutaXLs);
        if (xls.exists()) {
            respuesta.setCodRespuesta(0);
            respuesta.setRutaArchivo(rutaXLs);
        } else {
            respuesta.setCodRespuesta(99);
        }

        return respuesta;
    }

    /** Funcin que consulta en el server si existe el resumen generado */
    public static RespuestaVO consultarExisteResumen(String idReporte, String nombreArchivo) {

        RespuestaVO respuesta = new RespuestaVO();
        String rutaResumen = retornarRutaArchivoXls(idReporte, nombreArchivo);

        File resumen = new File(rutaResumen);
        if (resumen.exists()) {
            respuesta.setCodRespuesta(0);
            respuesta.setRutaArchivo(rutaResumen);
        } else {
            respuesta.setCodRespuesta(99);
        }

        return respuesta;
    }

    /**
     * Funcion que elimina un reporte en especifico, dado su id de reporte y su
     * nombre, en formato xls
     */
    public static RespuestaVO deleteXlsServer(String idTipoProceso, String nombreArchivo) {

        RespuestaVO resp = new RespuestaVO();
        String rutaXls = retornarRutaArchivoXls(idTipoProceso, nombreArchivo);
        int i = Helper.deleteFile(rutaXls);
        if (i == 1) {
            resp.setCodRespuesta(0);
        } else {
            resp.setCodRespuesta(99);
        }

        return resp;
    }

    /**
     * Funcion que elimina un archivo en formato txt, dado su id de proceso y su
     * nombre de archivo.
     */
    public static RespuestaVO deleteArchivoServer(String idTipoProceso, String nombreArchivo) {

        String rutaFile = "";
        RespuestaVO resp = new RespuestaVO();

        rutaFile = retornarRutaArchivoTxt(idTipoProceso, nombreArchivo);
  //      logger.debug("rutaFile : " + rutaFile);

        int i = Helper.deleteFile(rutaFile);
        if (i == 1) {
            resp.setCodRespuesta(0);
        } else {
            resp.setCodRespuesta(99);
        }

        return resp;
    }

    /** Funcion que actualiza status proceso cuando se requiera reprocesar. */
    public static RespuestaVO actualizarStatusSegunPeriodoYProceso(String tipoProceso, String mesPeriodo) {

        return GenerarReportesImpl.actualizarStatusSegunPeriodoYProceso(tipoProceso, mesPeriodo);
    }

    /**
     * Funcion que realiza update al status de la tabla maestro sivegam, antes
     * de reprocesar.
     */
    public static RespuestaVO updateStatusAntesDeReprocesar(long id, int status) {

        return GenerarReportesImpl.updateStatusAntesDeReprocesar(id, status);
    }
}
