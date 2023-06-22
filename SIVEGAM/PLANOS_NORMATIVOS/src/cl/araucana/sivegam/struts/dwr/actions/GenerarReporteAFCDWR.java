package cl.araucana.sivegam.struts.dwr.actions;

import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.impl.GenerarReportesAFCImpl;
import cl.araucana.sivegam.impl.GenerarReportesImpl;
import cl.araucana.sivegam.vo.AfcVO;
import cl.araucana.sivegam.vo.GenerarReportesVO;
import cl.araucana.sivegam.vo.MaestroSivegamVO;
import cl.araucana.sivegam.vo.PeriodoProcesoVO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.StatusProcesoVO;
import cl.araucana.sivegam.vo.param.ListadoParametros;

public class GenerarReporteAFCDWR {

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
     * con la clase implement. flagReporte es para identificar si es de tipo
     * mensual o retroactivo. (M ó R).
     */
    public RespuestaVO generarReporteXLS(String idReporteXls, String periodoReporte, String idMaestroSiv, String mesReporte, String usser, String fechaReporte, String flagReporte) {

        return GenerarReportesAFCImpl.generarReporteXLS(idReporteXls, periodoReporte, idMaestroSiv, mesReporte, usser, fechaReporte, flagReporte);
    }

    /** Funcion que genera el reporte de errores en xls. */
    public static RespuestaVO generarReporteErroresXls(String idFlagReporteXls, String periodo, String idMaestroSiv, String mesReporte, String usser, String fechaReporte,
            String flagReporte) {

        return GenerarReportesAFCImpl.generarReporteErroresXls(idFlagReporteXls, periodo, idMaestroSiv, mesReporte, usser, fechaReporte, flagReporte);
    }

    /**
     * Funcion que sirve como enlace de comunicacion entre la capa vista y la
     * capa de implementacion. Tiene como funcionalidad invocar a la funcion que
     * llama al proceso cobol, discriminando por el tipo de reporte.
     */
    public static AfcVO llamarProcesoCobolAFC(int flagReproceso, String idSecuenciaSivegam, String idReport, String periodo, String tipoReporte) {

        return GenerarReportesAFCImpl.llamarProcesoCobolAFC(flagReproceso, idSecuenciaSivegam, idReport, periodo, tipoReporte);
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

    /**
     * Funcion que llama al proceso cobol para cargar el archivo de entrada a
     * las tablas de AFC.
     */
    public static AfcVO invocarProcesoEntradaAfc(String idReporteAfc, String tipoReporte, String periodo, String fechaActual, String usuario) {

        return GenerarReportesAFCImpl.invocarProcesoEntradaAfc(idReporteAfc, tipoReporte, periodo, fechaActual, usuario);
    }

    public static RespuestaVO statusProcesoCarga(String periodo, String tipoReporte) {
        return GenerarReportesAFCImpl.statusProcesoCarga(periodo, tipoReporte);
    }

    public static RespuestaVO insertarReporte(String flagTipoReporte, String nombreArchivo, String periodo) {
        return GenerarReportesAFCImpl.insertarReporte(flagTipoReporte, nombreArchivo, periodo);
    }

    public static MaestroSivegamVO selectMaxIdMaestroSivegam(String idReporte, String periodoReporte) {

        return GenerarReportesImpl.selectMaxIdMaestroSivegam(idReporte, periodoReporte);
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

    /** Funcion que actualiza status proceso cuando se requiera reprocesar. */
    public static RespuestaVO actualizarStatusSegunPeriodoYProceso(String tipoProceso, String mesPeriodo) {

        return GenerarReportesAFCImpl.actualizarStatusSegunPeriodoYProceso(tipoProceso, mesPeriodo);
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

    public static String statusProcesoCargaGlosa(String estado) {
        String label = null;
        if (estado == null) {
            estado = new String("1");
        }
        ListadoParametros lp = ListadoParametros.getInstancia();
        StatusProcesoVO[] status = lp.getListStatusProcesoCarga();
  //      logger.debug(estado);
        if (Long.parseLong(estado) == 0) {
            label = "Error SQL";
        } else {
            for (int i = 0; i < status.length; i++) {
 //               logger.debug("" + status[i].getStatus_proceso());
 //               logger.debug(status[i].getDescripcion_status_proceso());
                if (status[i].getStatus_proceso() == Long.parseLong(estado)) {
                    label = status[i].getDescripcion_status_proceso();
                    break;
                }
            }
        }
        return label;

    }

    /** Realiza una eliminacion logica de los registros. */
    public static RespuestaVO deleteLogicoSegunReporteAFC(int tipoReporte) {

        return GenerarReportesAFCImpl.deleteLogicoSegunReporteAFC(tipoReporte);
    }

    public static AfcVO reprocesarCobolValidacionAFC(int flagReproceso, String idMaestroSivegam, String idReporte, String periodo, String flagReporte) {

        return GenerarReportesAFCImpl.llamarProcesoCobolAFC(flagReproceso, idMaestroSivegam, idReporte, periodo, flagReporte);
    }

    public static String obtenerRutaReporteExcel(int tipoReporte, String periodo) {

        return GenerarReportesAFCImpl.obtenerRutaReporteExcel(tipoReporte, periodo);
    }

    public static String obtenerRutaReporteTxt(int idTipoReporte, String periodoReporte) {

        return GenerarReportesAFCImpl.obtenerRutaReporteTxt(idTipoReporte, periodoReporte);
    }

    public static String obtenerRutaReporteExcelErr(int idTipoReporte, String periodoReporte) {

        return GenerarReportesAFCImpl.obtenerRutaReporteExcelErr(idTipoReporte, periodoReporte);
    }

    /**
     * Funcion que realiza update al status de la tabla maestro sivegam, antes
     * de reprocesar.
     */
    public static RespuestaVO updateStatusAntesDeReprocesar(long id, int status) {

        return GenerarReportesImpl.updateStatusAntesDeReprocesar(id, status);
    }
    

}
