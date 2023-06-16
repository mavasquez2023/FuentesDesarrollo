package cl.araucana.sivegam.struts.dwr.actions;

import java.io.IOException;
import java.sql.SQLException;

import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.impl.GeneraRepDivPrevImpl;
import cl.araucana.sivegam.impl.GenerarReportesImpl;
import cl.araucana.sivegam.vo.GenerarReportesVO;
import cl.araucana.sivegam.vo.MaestroSivegamVO;
import cl.araucana.sivegam.vo.PeriodoProcesoVO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.Sif018VO;
import cl.araucana.sivegam.vo.StatusProcesoVO;
import cl.araucana.sivegam.vo.TipoProcesosVO;
import cl.araucana.sivegam.vo.param.ListadoParametros;

public class GeneraRepDivPrevDWR {

    SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    /**
     * Funcion que sirve como enlace de comunicacion entre la capa vista y la
     * capa de implementacion. Tiene como funcionalidad invocar a la funcion que
     * inserta en la tabla maestrosivegam.
     */
    public GenerarReportesVO insertarMaestroSivegam(String fechaProceso, String usuarioSivegam, String idTipoReporte, String periodoMes) {

        return GenerarReportesImpl.insertarMaestroSivegam(fechaProceso, usuarioSivegam, idTipoReporte, periodoMes);

    }

    /**
     * Funcion que sirve como enlace de comunicacion entre la capa vista y la
     * capa de implementacion. Tiene como funcionalidad invocar a la funcion que
     * llama al proceso cobol, discriminando por el tipo de reporte.
     */
    public RespuestaVO llamarProcesoCobol(String idSecuencia, String idReporte, String periodo, String asfam) {

        return GenerarReportesImpl.llamarProcesoCobol(idSecuencia, idReporte, periodo, asfam);
        /*
         * RespuestaVO resp = new RespuestaVO();
         * 
         * resp = GenerarReportesImpl.llamarProcesoCobol(idSecuencia, idReporte,
         * periodo);
         * 
         * if(resp.getCodRespuesta() == 3){ return "OK"; }
         * 
         * return "KO";
         */
    }

    public Sif018VO consultaRegistrosSif018() throws IOException, SQLException {

        return GeneraRepDivPrevImpl.consultaRegistrosSif018();
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

        return GenerarReportesImpl.obtenerStatus(periodo, tipoProceso);
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
   //     logger.debug(descripcionStatusProceso);
        status.setDescripcion_status_proceso(descripcionStatusProceso);
        return status;
    }

    /**
     * Funcion que obtiene la data de periodo proceso para ser cargada en el
     * arreglo de meses del formulario.
     */
    public PeriodoProcesoVO[] obtenerDataPeriodoProceso(String parametro) {

        ListadoParametros listaParam = ListadoParametros.getInstancia();
        PeriodoProcesoVO[] periodoProceso = new PeriodoProcesoVO[0];

        if ("PeriodoProceso".equals(parametro)) {
            periodoProceso = listaParam.getListPeriodoProcesos();

            return periodoProceso;
        }
        
        System.out.println("-------------------------------------------");
        System.out.println("PeriodoProcesoVO[] obtenerDataPeriodoProceso :");
        System.out.println("PERIODO = " + periodoProceso);
        System.out.println("-------------------------------------------");

        return periodoProceso;

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

    /**
     * Funcion que obtiene el maximo id de la tabla maestro sivegam cuando no se
     * requiere reprocesar para generar los reportes en xls o en txt. recibe
     * como parametros el periodo del procesamiento y el id del tipo de proceso.
     * retorna como resultado el maximo idmaestrosivegam.
     */
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

    public MaestroSivegamVO obtenerInformacionAActualizar(String idSivegam) {

        return GenerarReportesImpl.obtenerInformacionAActualizar(idSivegam);
    }

    public MaestroSivegamVO buscarGlosaStatusYGlosaMes(String idPeriodo, String idStatus) {

        return GenerarReportesImpl.buscarGlosaStatusYGlosaMes(idPeriodo, idStatus);
    }

    /** funcion que actualiza maestro sivegam luego de ejecutado un proceso.* */
    public RespuestaVO updateStatusProcesoSivegam(String idRespuestaCobol, String idMaestroSivegam, String fechaProceso) {

        return GenerarReportesImpl.updateStatusProcesoSivegam(idRespuestaCobol, idMaestroSivegam, fechaProceso);
    }

    /**
     * funcion que actualiza estado de archivos despues de un reproceso de los
     * archivos 11 o 12.*
     */
    public RespuestaVO updateStatus12PorReproceso112(String idMaestroSivegam, String fechaProceso) {

        return GenerarReportesImpl.updateStatus12PorReproceso112(idMaestroSivegam, fechaProceso);
    }

    /** Funcion que actualiza status proceso cuando se requiera reprocesar. */
    public static RespuestaVO actualizarStatusSegunPeriodoYProceso(String tipoProceso, String mesPeriodo) {

        return GenerarReportesImpl.actualizarStatusSegunPeriodoYProcesoDivPrev(tipoProceso, mesPeriodo);
    }

    /**
     * Funcion que realiza update al status de la tabla maestro sivegam, antes
     * de reprocesar.
     */
    public static RespuestaVO updateStatusAntesDeReprocesar(long id, int status) {

        return GenerarReportesImpl.updateStatusAntesDeReprocesar(id, status);
    }

    public static RespuestaVO cobolSIP05ASFAM(String periodo, String id) {

        return GenerarReportesImpl.cobolSIP05ASFAM(periodo, id);
    }

    public static RespuestaVO llamarProcesoCobolCLVAL(String idReport, String periodo) {

        return GenerarReportesImpl.llamarProcesoCobolCLVAL(idReport, periodo);
    }
}
