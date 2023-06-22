package cl.araucana.sivegam.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.ibatis.sqlmap.client.SqlMapClient;
import cl.araucana.sivegam.config.ConfiguracionSqlMap;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.vo.DetalleReporteSivegamVO;
import cl.araucana.sivegam.vo.GenerarReportesVO;
import cl.araucana.sivegam.vo.MaestroSivegamVO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.Sif018VO;
import cl.araucana.sivegam.vo.StatusProcesoVO;
import cl.araucana.sivegam.vo.TipoProcesosVO;
import cl.araucana.sivegam.vo.param.ListadoParametros;

public class GenerarReportesDAO {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    public static GenerarReportesVO insertarMaestroSivegam(String fechaProceso, String usuarioSivegam, String idTipoReporte, String periodoMes) {

        logger.debug("INI     : insertarMaestroSivegam id [" + idTipoReporte + "]");
        List datos = null;
        long idMaestroSivegam;
        String fecha = "";
        Date dateProceso = new Date();
        String DATE_FORMAT2 = "dd/MM/yyyy";
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);
        MaestroSivegamVO masterVO = new MaestroSivegamVO();
        GenerarReportesVO cotizaciones = new GenerarReportesVO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        int periodo_proceso = Integer.parseInt(periodoMes);
        String tipo_proceso = "";
        String status_proceso = "";
        try {
            sqlMap.startTransaction(0);
            fecha = fechaProceso;
            dateProceso = sdf2.parse(fecha);

            ListadoParametros listaParamProceso = ListadoParametros.getInstancia();
            TipoProcesosVO[] tipoProcesos = listaParamProceso.getListTipoProcesos();

            for (int i = 0; i < tipoProcesos.length; i++) {

                if (tipoProcesos[i].getId_tipo_proceso() == Integer.parseInt(idTipoReporte)) {
                    tipo_proceso = Integer.toString(tipoProcesos[i].getId_tipo_proceso());
                    break;
                }
            }

            logger.debug("SET     : insertarMaestroSivegam id [" + idTipoReporte + "] - tipo_proceso: " + tipo_proceso);
            logger.debug("SET     : insertarMaestroSivegam id [" + idTipoReporte + "] - idTipoReporte: " + idTipoReporte);
            logger.debug("SET     : insertarMaestroSivegam id [" + idTipoReporte + "] - PeriodoProceso: " + periodo_proceso);

            ListadoParametros listaParam = ListadoParametros.getInstancia();
            StatusProcesoVO[] statusProceso = listaParam.getListStatusProceso();

            for (int i = 0; i < statusProceso.length; i++) {
                if (statusProceso[i].getStatus_proceso() == 4) {
                    status_proceso = Long.toString(statusProceso[i].getStatus_proceso());
                    break;
                }
            }

            logger.debug("CALL IN : insertarMaestroSivegam id [" + idTipoReporte + "] - QUERY: selectIdMaestroSivegam");
            datos = sqlMap.queryForList("cotizacionesSiv.selectIdMaestroSivegam");
            logger.debug("CALL OUT: insertarMaestroSivegam id [" + idTipoReporte + "] - QUERY: selectIdMaestroSivegam");

            if (datos != null && datos.size() > 0) {

                idMaestroSivegam = (Long.parseLong((String) datos.get(0))) + 1;

                if ("".equals(tipo_proceso)) {
                    tipo_proceso = "0";
                }

                masterVO.setMaestro_sivegam(idMaestroSivegam);
                masterVO.setFechaProcesoDate(dateProceso);
                masterVO.setStatus_proceso(Integer.parseInt(status_proceso));
                masterVO.setPeriodo_proceso(periodo_proceso);
                if ("R".equalsIgnoreCase(tipo_proceso)) {
                    tipo_proceso = "8";
                }
                if ("M".equalsIgnoreCase(tipo_proceso)) {
                    tipo_proceso = "9";
                }
                masterVO.setTipo_archivo(Integer.parseInt(tipo_proceso));
                masterVO.setUsuario_sivegam(Long.parseLong(usuarioSivegam));

                /* Se debe devolver el idmaestrosivegam */
                cotizaciones.setIdMaestroSivegam(idMaestroSivegam);
            } else {
                logger.debug("ERROR   : insertarMaestroSivegam id [" + idTipoReporte + "] - ERROR AL TRAER IDMAESTROSIVEGAM");
                cotizaciones.setCodResultado(99);
                return cotizaciones;
            }

            logger.debug("CALL IN : insertarMaestroSivegam id [" + idTipoReporte + "] - QUERY: insertMaestroSivegam");
            logger.debug("IN      : insertarMaestroSivegam id [" + idTipoReporte + "] - getFecha_proceso          [" + masterVO.getFecha_proceso() + "]");
            logger.debug("IN      : insertarMaestroSivegam id [" + idTipoReporte + "] - getGlosaPeriodoProcesoMes [" + masterVO.getGlosaPeriodoProcesoMes() + "]");
            logger.debug("IN      : insertarMaestroSivegam id [" + idTipoReporte + "] - getGlosaStatusProceso     [" + masterVO.getGlosaStatusProceso() + "]");
            logger.debug("IN      : insertarMaestroSivegam id [" + idTipoReporte + "] - getMaestro_sivegam        [" + masterVO.getMaestro_sivegam() + "]");
            logger.debug("IN      : insertarMaestroSivegam id [" + idTipoReporte + "] - getStatus_proceso         [" + masterVO.getStatus_proceso() + "]");
            logger.debug("IN      : insertarMaestroSivegam id [" + idTipoReporte + "] - getPeriodo_proceso        [" + masterVO.getPeriodo_proceso() + "]");
            logger.debug("IN      : insertarMaestroSivegam id [" + idTipoReporte + "] - getTipo_archivo           [" + masterVO.getTipo_archivo() + "]");
            logger.debug("IN      : insertarMaestroSivegam id [" + idTipoReporte + "] - getUsuario_sivegam        [" + masterVO.getUsuario_sivegam() + "]");
            logger.debug("IN      : insertarMaestroSivegam id [" + idTipoReporte + "] - getFechaProcesoDate       [" + masterVO.getFechaProcesoDate() + "]");

            sqlMap.insert("cotizacionesSiv.insertMaestroSivegam", masterVO);
            logger.debug("CALL OUT: insertarMaestroSivegam id [" + idTipoReporte + "] - QUERY: insertMaestroSivegam");
            cotizaciones.setCodResultado(0);
            sqlMap.commitTransaction();

        } catch (SQLException e) {

            cotizaciones.setResultado("Error al insertar en la tabla MaestroIntercaja.");
            cotizaciones.setCodResultado(99);
            e.printStackTrace();

        } catch (ParseException e) {

            cotizaciones.setResultado("Error al insertar en la tabla MaestroIntercaja.");
            cotizaciones.setCodResultado(99);
            e.printStackTrace();

        } finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
                cotizaciones.setResultado("Error al insertar en la tabla MaestroIntercaja.");
                cotizaciones.setCodResultado(99);
            }
        }
        logger.debug("FIN     : insertarMaestroSivegam id [" + idTipoReporte + "]");
        return cotizaciones;
    }

    /**
     * Funcion que realiza el insert a la tabla detalle reportes sivegam cada
     * vez que se genera un reporte en xls como en txt
     */
    public static RespuestaVO insertDetalleReporteSivegam(DetalleReporteSivegamVO detalleReporte) {

        logger.debug("INI     : insertDetalleReporteSivegam");
        List datos = null;
        long idDetalleReporte;
        RespuestaVO resp = new RespuestaVO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);
            logger.debug("INI     :  insertDetalleReporteSivegam - QUERY selectIdDetalleReporte");
            datos = sqlMap.queryForList("cotizacionesSiv.selectIdDetalleReporte");
            if (datos != null && datos.size() > 0) {

                idDetalleReporte = Long.parseLong((String) datos.get(0));
                logger.debug("DATOS   : selectIdDetalleReporte: " + idDetalleReporte);
                detalleReporte.setDetalle_reporte(idDetalleReporte);
            }
            logger.debug("FIN     :  insertDetalleReporteSivegam - QUERY selectIdDetalleReporte");

            logger.debug("INI     :  insertDetalleReporteSivegam - QUERY insertDetalleReportesSivegam");
            logger.debug("ENTRADA :  insertDetalleReporteSivegam - getDetalle_reporte  [" + detalleReporte.getDetalle_reporte() + "]");
            logger.debug("ENTRADA :  insertDetalleReporteSivegam - getFecha_reporte    [" + detalleReporte.getFecha_reporte() + "]");
            logger.debug("ENTRADA :  insertDetalleReporteSivegam - getFomato_reporte   [" + detalleReporte.getFomato_reporte() + "]");
            logger.debug("ENTRADA :  insertDetalleReporteSivegam - getMaestro_sivegam  [" + detalleReporte.getMaestro_sivegam() + "]");
            logger.debug("ENTRADA :  insertDetalleReporteSivegam - getNombre_reporte   [" + detalleReporte.getNombre_reporte() + "]");
            logger.debug("ENTRADA :  insertDetalleReporteSivegam - getPeriodo_proceso  [" + detalleReporte.getPeriodo_proceso() + "]");
            logger.debug("ENTRADA :  insertDetalleReporteSivegam - getStatus_proceso   [" + detalleReporte.getStatus_proceso() + "]");
            logger.debug("ENTRADA :  insertDetalleReporteSivegam - getTipo_proceso     [" + detalleReporte.getTipo_proceso() + "]");
            logger.debug("ENTRADA :  insertDetalleReporteSivegam - getUsuario_sivegam  [" + detalleReporte.getUsuario_sivegam() + "]");
            logger.debug("ENTRADA :  insertDetalleReporteSivegam - getFechaReporteDate [" + detalleReporte.getFechaReporteDate() + "]");

            sqlMap.insert("cotizacionesSiv.insertDetalleReportesSivegam", detalleReporte);
            sqlMap.commitTransaction();
            sqlMap.endTransaction();
            resp.setCodRespuesta(0);
            logger.debug("FIN     :  insertDetalleReporteSivegam - QUERY insertDetalleReportesSivegam");

        } catch (SQLException e) {
            e.printStackTrace();
            resp.setCodRespuesta(99);
            resp.setMsgRespuesta(e.getMessage());
            logger.debug("ERROR   :   insertDetalleReporteSivegam - SQL");
        }
        logger.debug("FIN         : insertDetalleReporteSivegam");
        return resp;
    }

    /**
     * Funcion que obtiene el status del proceso dado el periodo y el tipo de
     * proceso.
     */
    public static Sif018VO obtenerStatus(String periodo, String tipoProceso) {
        //logger.debug("INI     : obtenerStatus id [" + tipoProceso + "]");

        List datos = null;
        Sif018VO sif018vo = new Sif018VO();
        MaestroSivegamVO maestroVo = new MaestroSivegamVO();

        maestroVo.setPeriodo_proceso(Integer.parseInt(periodo));
        maestroVo.setTipo_archivo(Integer.parseInt(tipoProceso));

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);
 //           logger.debug("INI     : obtenerStatus id [" + tipoProceso + "] - QUERY obtenerStatusProceso");
 //           logger.debug("IN      : obtenerStatus id [" + tipoProceso + "] - getPeriodo_proceso [" + maestroVo.getPeriodo_proceso() + "]");
 //           logger.debug("IN      : obtenerStatus id [" + tipoProceso + "] - getTipo_archivo    [" + maestroVo.getTipo_archivo() + "]");

            datos = sqlMap.queryForList("cotizacionesSiv.obtenerStatusProceso", maestroVo);
 //           logger.debug("FIN     : obtenerStatus id [" + tipoProceso + "] - QUERY obtenerStatusProceso");

            if (datos != null && datos.size() > 0) {

                int statusProceso = Integer.parseInt((String) datos.get(0));
                sif018vo.setStatusProceso(statusProceso);
                sif018vo.setCodResultado(0);
 //               logger.debug("RESULT  : obtenerStatus id [" + tipoProceso + "] - statusProceso    [" + statusProceso + "]");
  //              logger.debug("FIN     : obtenerStatus id [" + tipoProceso + "]");
                return sif018vo;

            } else {
 //              logger.debug("RESULT  : obtenerStatus id [" + tipoProceso + "] - SIN DATOS");
  //              logger.debug("FIN     : obtenerStatus id [" + tipoProceso + "]");
                sif018vo.setCodResultado(99);
                return sif018vo;
            }

        } catch (SQLException e) {

  //          logger.debug("ERROR   : obtenerStatus id [" + tipoProceso + "] - SQL1");
            sif018vo.setResultado("Error.");
            sif018vo.setCodResultado(99);
            e.printStackTrace();

        } finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
  //              logger.debug("ERROR   : obtenerStatus id [" + tipoProceso + "] - SQL2");
                e.printStackTrace();
                sif018vo.setResultado("Error.");
                sif018vo.setCodResultado(99);
            }
        }

  //      logger.debug("FIN     : obtenerStatus id [" + tipoProceso + "]");
        return sif018vo;
    }

    /**
     * Funcion que obtiene el maximo id de la tabla maestro sivegam cuando no se
     * requiere reprocesar para generar los reportes en xls o en txt. recibe
     * como parametros el periodo del procesamiento y el id del tipo de proceso.
     * retorna como resultado el maximo idmaestrosivegam.
     */
    public static MaestroSivegamVO selectMaxIdMaestroSivegam(String idReporte, String periodoReporte) {

        logger.debug("INI     : selectMaxIdMaestroSivegam id [" + idReporte + "]");
        List datos = null;
        long maestro_sivegam;
        MaestroSivegamVO vo = new MaestroSivegamVO();
        MaestroSivegamVO ms = new MaestroSivegamVO();

        vo.setPeriodo_proceso(Integer.parseInt(periodoReporte));
        vo.setTipo_archivo(Integer.parseInt(idReporte));
        
//        System.out.println("---------------------------------------------------------");
//        System.out.println("Datos: ");
//        System.out.println("idReporte = " + idReporte);
//        System.out.println("periodoReporte = " + periodoReporte);
//        System.out.println("---------------------------------------------------------");

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            logger.debug("IN      : selectMaxIdMaestroSivegam id [" + idReporte + "] - QUERY selectId");
            logger.debug("IN      : selectMaxIdMaestroSivegam id [" + idReporte + "] - getPeriodo_proceso [" + vo.getPeriodo_proceso() + "]");
            logger.debug("IN      : selectMaxIdMaestroSivegam id [" + idReporte + "] - getTipo_archivo    [" + vo.getTipo_archivo() + "]");
            datos = sqlMap.queryForList("cotizacionesSiv.selectId", vo);
            
//            System.out.println("---------------------------------------------------------");
//            System.out.println("Tamaño lista cotizacionesSiv.selectId :" + datos.size());
//            System.out.println("---------------------------------------------------------");
            
            logger.debug("FIN     : selectMaxIdMaestroSivegam id [" + idReporte + "] - QUERY selectId");
            if (datos != null && datos.size() > 0) {

                if ((String) datos.get(0) == null) {
                    logger.debug("RESULT  : selectMaxIdMaestroSivegam id [" + idReporte + "] - SIN DATOS");
                    ms.setMaestro_sivegam(Long.parseLong("0"));
                    logger.debug("FIN     : selectMaxIdMaestroSivegam id [" + idReporte + "]");
                    return ms;
                } else {
                    maestro_sivegam = Long.parseLong((String) datos.get(0));
                    logger.debug("RESULT  : selectMaxIdMaestroSivegam id [" + idReporte + "] - Maestro_sivegam [" + maestro_sivegam + "]");
                    vo.setMaestro_sivegam(maestro_sivegam);
                    logger.debug("FIN     : selectMaxIdMaestroSivegam id [" + idReporte + "]");
                    return vo;
                }
            }

        } catch (SQLException e) {
            logger.debug("ERROR   : selectMaxIdMaestroSivegam id [" + idReporte + "] - SQL1");
            ms.setMaestro_sivegam(Long.parseLong("0"));

            e.printStackTrace();

        } finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                logger.debug("ERROR   : selectMaxIdMaestroSivegam id [" + idReporte + "] - SQL2");
                e.printStackTrace();
                ms.setMaestro_sivegam(Long.parseLong("0"));
            }
        }

        logger.debug("FIN     : selectMaxIdMaestroSivegam id [" + idReporte + "]");
        return ms;
    }

    /**
     * Funcion que obtiene el idtipoarchivo dado el idtipoproceso...
     * homologacion para obtener el idmax de sivegam y poder insertar en detalle
     * reportes.
     */
    public static TipoProcesosVO selectTipoArchivoHomologado(String idTipoProceso) {

  //      logger.debug("INI     : selectTipoArchivoHomologado id [" + idTipoProceso + "]");
        int id_tipo_archivo;
        List datos = null;
        TipoProcesosVO tipoProceso = new TipoProcesosVO();

        tipoProceso.setId_tipo_proceso(Integer.parseInt(idTipoProceso));

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("cotizacionesSiv.selectTipoArchivoFromTipoProceso", tipoProceso);

            if (datos != null && datos.size() > 0) {

                id_tipo_archivo = Integer.parseInt((String) datos.get(0));
   //             logger.debug("RESULT  : selectTipoArchivoHomologado id [" + idTipoProceso + "] - tipo archivo: " + id_tipo_archivo);
                tipoProceso.setId_tipo_archivo(id_tipo_archivo);
                return tipoProceso;

            } else {

                tipoProceso.setId_tipo_archivo(0);
                return tipoProceso;
            }

        } catch (SQLException e) {

            tipoProceso.setId_tipo_archivo(0);
            e.printStackTrace();

        } finally {
            try {
                sqlMap.endTransaction();

            } catch (SQLException e) {
                tipoProceso.setId_tipo_archivo(0);
                e.printStackTrace();
            }
        }
 //       logger.debug("FIN     : selectTipoArchivoHomologado id [" + idTipoProceso + "]");

        return tipoProceso;
    }

    /**
     * Funcion que obtiene data especifica de maestrosivegam para actualizar
     * grilla con el periodo y el status del proceso. Dichas consultas se hacen
     * cada vez que se inicia la pantalla y cada vez que se selecciona o se
     * cambia el mes del combobox de la pantalla de generacion de reportes.
     */
    public static MaestroSivegamVO obtenerInformacionAActualizar(String idSivegam) {

 //       logger.debug("INI     : obtenerInformacionAActualizar id [" + idSivegam + "]");
        List datos = null;
        MaestroSivegamVO vo = new MaestroSivegamVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        vo.setMaestro_sivegam(Long.parseLong(idSivegam));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("cotizacionesSiv.selectPeriodoAndStatusSivegam", vo);
            
//            System.out.println("----------------------------------------------------------");
//            System.out.println("Tamaño lista cotizacionesSiv.selectPeriodoAndStatusSivegam : " +
//            		"" + datos.size());
//            System.out.println("----------------------------------------------------------");
            
            if (datos != null && datos.size() > 0) {
                vo = (MaestroSivegamVO) (datos.get(0));
                return vo;
            } else {
                if ((String) datos.get(0) == null) {
                    vo.setPeriodo_proceso(0);
                    vo.setStatus_proceso(1);
                    return vo;
                }
            }
        } catch (SQLException e) {

            vo.setPeriodo_proceso(0);
            vo.setStatus_proceso(1);
            e.printStackTrace();

        } finally {
            try {
                sqlMap.endTransaction();

            } catch (SQLException e) {
                vo.setPeriodo_proceso(0);
                vo.setStatus_proceso(1);
                e.printStackTrace();
            }
        }
 //       logger.debug("FIN     : obtenerInformacionAActualizar id [" + idSivegam + "]");

        return vo;
    }

    /**
     * Funcion que actualiza status proceso y fecha de procesamiento en maestro
     * sivegam, luego de ejecutar un proceso cobol.
     */
    public static RespuestaVO updateStatusProcesoSivegam(String idRespuestaCobol, String idMaestroSivegam, String fechaProceso) {

        /*
         * Si el retorno de una ejecucion COBOL es exitosa (es decir, retorna
         * valor 3) se setea codRespuesta = 0 Si el retorno de la ejecucion del
         * proceso cobol es fallida(es decir, retorna valor 2) se setea
         * codRespuesta = 1
         */
 //       logger.debug("INI     : updateStatusProcesoSivegam id [" + idMaestroSivegam + "]");
        String fecha = "";
        Date dateProceso = new Date();
        String DATE_FORMAT2 = "dd/MM/yyyy";
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);
        RespuestaVO resp = new RespuestaVO();
        MaestroSivegamVO vo = new MaestroSivegamVO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);
            fecha = fechaProceso;
            dateProceso = sdf2.parse(fecha);

            vo.setMaestro_sivegam(Long.parseLong(idMaestroSivegam));
            vo.setFechaProcesoDate(dateProceso);

            if ("3".equals(idRespuestaCobol)) {
                vo.setStatus_proceso(3);
                resp.setCodRespuesta(0);
            } else {
                if ("2".equals(idRespuestaCobol)) {
                    vo.setStatus_proceso(2);
                    resp.setCodRespuesta(1);
                }
            }

            sqlMap.update("cotizacionesSiv.updateStatusMaestroSivegam", vo);
            sqlMap.commitTransaction();

            return resp;

        } catch (SQLException e) {
 //           logger.debug("ERROR   : updateStatusProcesoSivegam id [" + idMaestroSivegam + "] - updateStatusProcesoSivegam : e.getMessage() = " + e.getMessage());

            resp.setCodRespuesta(1);
            resp.setMsgRespuesta(e.getMessage());
            e.printStackTrace();

        } catch (ParseException e) {
            resp.setCodRespuesta(1);
 //           logger.debug("ERROR   : updateStatusProcesoSivegam id [" + idMaestroSivegam + "] - updateStatusProcesoSivegam : e.getMessage() = " + e.getMessage());
            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                resp.setCodRespuesta(1);
//                logger.debug("ERROR   : updateStatusProcesoSivegam id [" + idMaestroSivegam + "] - updateStatusProcesoSivegam : e.getMessage() = " + e.getMessage());
                resp.setMsgRespuesta(e.getMessage());
                e.printStackTrace();
            }
        }
 //       logger.debug("FIN     : updateStatusProcesoSivegam id [" + idMaestroSivegam + "]");

        return resp;
    }

    public static RespuestaVO updateStatus12PorReproceso112(String idMaestroSivegam, String fechaProceso) {

        logger.debug("INI     : updateStatus12PorReproceso112 id [" + idMaestroSivegam + "]");
        RespuestaVO resp = new RespuestaVO();
        MaestroSivegamVO vo = new MaestroSivegamVO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);

            vo.setMaestro_sivegam(Long.parseLong(idMaestroSivegam));

            sqlMap.update("cotizacionesSiv.updateStatusPorReproceso112", vo);
            sqlMap.commitTransaction();
            resp.setCodRespuesta(0);

            return resp;

        } catch (SQLException e) {

            resp.setCodRespuesta(99);
            resp.setMsgRespuesta(e.getMessage());
            e.printStackTrace();

        } finally {

            try {

                sqlMap.endTransaction();

            } catch (SQLException e) {

                resp.setCodRespuesta(99);
                resp.setMsgRespuesta(e.getMessage());
                e.printStackTrace();
            }
        }
        logger.debug("FIN     : updateStatus12PorReproceso112 id [" + idMaestroSivegam + "]");

        return resp;
    }

    /** Funcion que actualiza status proceso cuando se requiera reprocesar. */
    public static RespuestaVO actualizarStatusSegunPeriodoYProceso(String tipoReporte, String mesPeriodo) {

        List datos = null;
        RespuestaVO respuesta = new RespuestaVO();

        respuesta.setIdTipoReporte(Integer.parseInt(tipoReporte));
        respuesta.setMesConsultado(Integer.parseInt(mesPeriodo));

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("cotizacionesSiv.actualizaStatusPerPro", respuesta);
            if (datos != null && datos.size() > 0) {
                if (datos.get(0) == null) {
                    respuesta.setCodRespuesta(99);
                    respuesta.setStatus(1);
                } else {
                    respuesta.setCodRespuesta(0);
                    respuesta.setStatus(3);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            respuesta.setCodRespuesta(99);
            respuesta.setStatus(1);
            respuesta.setMsgRespuesta(e.getMessage());

        } finally {
            try {
                sqlMap.endTransaction();

            } catch (SQLException e) {

                respuesta.setCodRespuesta(99);
                respuesta.setStatus(1);
                respuesta.setMsgRespuesta(e.getMessage());
                e.printStackTrace();
            }
        }

        return respuesta;
    }

    public static RespuestaVO actualizarStatusSegunPeriodoYProcesoDivPrev(String tipoProceso, String mesPeriodo) {

 //       logger.debug("INI     : actualizarStatusSegunPeriodoYProcesoDivPrev id [" + tipoProceso + "]");
        List datos = null;
        RespuestaVO respuesta = new RespuestaVO();

        respuesta.setIdTipoReporte(Integer.parseInt(tipoProceso));
        respuesta.setMesConsultado(Integer.parseInt(mesPeriodo));
        
        System.out.println("-------------------------------------------------------");
        System.out.println("actualizarStatusSegunPeriodoYProcesoDivPrev :");
        System.out.println("tipoProceso = " + tipoProceso);
        System.out.println("mesPeriodo = " + mesPeriodo);
        System.out.println("-------------------------------------------------------");

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("cotizacionesSiv.actualizaStatusPerDivPrev", respuesta);
            if (datos != null && datos.size() > 0) {
                if (datos.get(0) == null) {
                    respuesta.setCodRespuesta(99);
                    respuesta.setStatus(1);
                } else {
                    respuesta = (RespuestaVO) datos.get(0);
                    respuesta.setCodRespuesta(0);
                }
            }

            return respuesta;

        } catch (SQLException e) {
            respuesta.setCodRespuesta(99);
            respuesta.setStatus(1);
            respuesta.setMsgRespuesta(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                sqlMap.endTransaction();

            } catch (SQLException e) {

                respuesta.setCodRespuesta(99);
                respuesta.setStatus(1);
                respuesta.setMsgRespuesta(e.getMessage());
                e.printStackTrace();
            }
        }

  //      logger.debug("FIN     : actualizarStatusSegunPeriodoYProcesoDivPrev id [" + tipoProceso + "]");
        return respuesta;

    }

    /**
     * Funcion que realiza update al status de la tabla maestro sivegam, antes
     * de realizar el reproceso.
     */
    public static RespuestaVO updateStatusAntesDeReprocesar(long id, int status) {
 //       logger.debug("INI     : updateStatusAntesDeReprocesar id [" + id + "]");
        RespuestaVO respuesta = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        respuesta.setMaxId(id);
        respuesta.setStatus(status);

        try {
            sqlMap.startTransaction(0);
            sqlMap.update("cotizacionesSiv.updateStatusReprocesar", respuesta);
            sqlMap.commitTransaction();
            respuesta.setCodRespuesta(0);

            return respuesta;

        } catch (SQLException e) {
            respuesta.setCodRespuesta(99);
            respuesta.setStatus(1);
            respuesta.setMsgRespuesta(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                sqlMap.endTransaction();

            } catch (SQLException e) {

                respuesta.setCodRespuesta(99);
                respuesta.setStatus(1);
                respuesta.setMsgRespuesta(e.getMessage());
                e.printStackTrace();
            }
        }
   //     logger.debug("FIN     : updateStatusAntesDeReprocesar id [" + id + "]");

        return respuesta;
    }

}
