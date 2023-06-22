package cl.araucana.sivegam.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cl.araucana.sivegam.conexion.cobol.bo.ParametrosConexionBO;
import cl.araucana.sivegam.conexion.cobol.bo.ParametrosLlamadaBO;
import cl.araucana.sivegam.config.ConsumidorCobol;
import cl.araucana.sivegam.dao.GenerarReportesCesantiaDAO;
import cl.araucana.sivegam.dao.GenerarReportesDAO;
import cl.araucana.sivegam.helper.ExcelSheetReader;
import cl.araucana.sivegam.helper.Helper;
import cl.araucana.sivegam.helper.HelperAFCCesantia;
import cl.araucana.sivegam.helper.ReportDriver;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.helper.VOFillerDivPrevisional;
import cl.araucana.sivegam.vo.CesantiaVO;
import cl.araucana.sivegam.vo.ConexionAS400VO;
import cl.araucana.sivegam.vo.DetalleReporteSivegamVO;
import cl.araucana.sivegam.vo.LinCesantia041VO;
import cl.araucana.sivegam.vo.LinCesantia042VO;
import cl.araucana.sivegam.vo.LinCesantia043VO;
import cl.araucana.sivegam.vo.LinCesantia044VO;
import cl.araucana.sivegam.vo.MaestroSivegamVO;
import cl.araucana.sivegam.vo.ProcesosAFCCesantiaVO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.StatusProcesoVO;
import cl.araucana.sivegam.vo.ValoresConexionAS400VO;
import cl.araucana.sivegam.vo.param.ListadoParametros;

public class GenerarReportesCesantiaImpl {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    /** Funcion que obtiene los valores de conexion */
    public static ConexionAS400VO obtenerValoresConexion(int idReporteCesantia) {

        ListadoParametros listaParam1 = ListadoParametros.getInstancia();
        ValoresConexionAS400VO[] valoresConexion = listaParam1.getListValoresConexionToAS400();
        ProcesosAFCCesantiaVO[] tipoProcesos = listaParam1.getListProcesoAfcCesantia();
        ConexionAS400VO conexion = new ConexionAS400VO();

        for (int i = 0; i < valoresConexion.length; i++) {

            if (valoresConexion[i].getId_conexion_as400() == 1) {
                String ipServer = valoresConexion[i].getDescripcion_conexion_as400();
                conexion.setIpServer(ipServer);
            }

            if (valoresConexion[i].getId_conexion_as400() == 2) {
                String usuarioConexion = valoresConexion[i].getDescripcion_conexion_as400();
                conexion.setUsuarioConexion(usuarioConexion);
            }

            if (valoresConexion[i].getId_conexion_as400() == 3) {
                String claveConexion = valoresConexion[i].getDescripcion_conexion_as400();
                conexion.setClaveConexion(claveConexion);
            }
        }

        /*
         * ListadoParametros listaParam2 = ListadoParametros.getInstancia();
         * TipoProcesosVO[] tipoProcesos = listaParam2.getListTipoProcesos();
         */

        for (int j = 0; j < tipoProcesos.length; j++) {
            if (tipoProcesos[j].getId_tipo_proceso() == idReporteCesantia) {
                String nombrePrograma = tipoProcesos[j].getRuta_cl_as400();
                conexion.setNombrePrograma(nombrePrograma);
                break;
            }
        }

        return conexion;
    }

    /**
     * Funcion que retorna el estado de termino de un proceso, luego de recibir
     * la respuesta arrojada por el proceso cobol.
     */
    public static CesantiaVO obtenerEstadoTerminoProceso(int controlCobol) {

        CesantiaVO cesantia = new CesantiaVO();
        ListadoParametros listaParam = ListadoParametros.getInstancia();
        StatusProcesoVO[] statusProceso = listaParam.getListStatusProceso();

        for (int i = 0; i < statusProceso.length; i++) {
            if (statusProceso[i].getStatus_proceso() == controlCobol) {
                cesantia.setStatusProcesoCesantia(statusProceso[i].getDescripcion_status_proceso());
                break;
            }
        }

        return cesantia;
    }

    public static CesantiaVO llamarProcesoCobolCesantia(String idSecuenciaSivegam, String idReport, String periodo) {

        /*
         * Retornos de la funcion: 0: si el proceso fue realizado con exito. 99:
         * si el proceso no fue realizado.
         */
        logger.debug("INI     : llamarProcesoCobol id [" + idSecuenciaSivegam + " - " + idReport + "]");
        System.gc();
        CesantiaVO cesantia = new CesantiaVO();

        ConexionAS400VO conexion = new ConexionAS400VO();
        conexion = GenerarReportesCesantiaImpl.obtenerValoresConexion(Integer.parseInt(idReport));

        ParametrosConexionBO paramConexion = new ParametrosConexionBO();
        ParametrosLlamadaBO[] paramLlamada = new ParametrosLlamadaBO[6];

        String spaces = " ";
        String idMaestroSivegamTemp = Helper.paddingString(idSecuenciaSivegam, 12, '0', true);
        String idLog = Helper.paddingString(spaces, 12, ' ', true);
        String status = Helper.paddingString(spaces, 2, ' ', true);
        String codigoError = Helper.paddingString(spaces, 4, ' ', true);
        String mensajeError = Helper.paddingString(spaces, 200, ' ', false);

        logger.debug("SET     : llamarProcesoCobol id [" + idSecuenciaSivegam + " - " + idReport + "] - seteando parametro de entrada...");

        /* Seteo de los parametros de conexion. */
        paramConexion.setIpServer(conexion.getIpServer());
        paramConexion.setUsuarioConexion(conexion.getUsuarioConexion());
        paramConexion.setClaveConexion(conexion.getClaveConexion());
        paramConexion.setPrograma(conexion.getNombrePrograma());

        logger.debug("Programa a invocar: " + paramConexion.getPrograma());
        /*
         * Seteo de los parametros de entrada para el proceso COBOL, mediante
         * paramLlamada.
         */
        ParametrosLlamadaBO parametro1 = new ParametrosLlamadaBO();
        ParametrosLlamadaBO parametro2 = new ParametrosLlamadaBO();
        ParametrosLlamadaBO parametro3 = new ParametrosLlamadaBO();
        ParametrosLlamadaBO parametro4 = new ParametrosLlamadaBO();
        ParametrosLlamadaBO parametro5 = new ParametrosLlamadaBO();
        ParametrosLlamadaBO parametro6 = new ParametrosLlamadaBO();

        parametro1.setTipo("STRING");
        parametro1.setLargo(idMaestroSivegamTemp.length());
        parametro1.setValor(idMaestroSivegamTemp);
        parametro1.setDireccion("IN");
        paramLlamada[0] = parametro1;

        parametro2.setTipo("STRING");
        parametro2.setLargo(periodo.length());
        parametro2.setValor(periodo);
        parametro2.setDireccion("IN");
        paramLlamada[1] = parametro2;

        parametro3.setTipo("STRING");
        parametro3.setLargo(idLog.length());
        parametro3.setValor(idLog);
        parametro3.setDireccion("OUT");
        paramLlamada[2] = parametro3;

        parametro4.setTipo("STRING");
        parametro4.setLargo(status.length());
        parametro4.setValor(status);
        parametro4.setDireccion("OUT");
        paramLlamada[3] = parametro4;

        parametro5.setTipo("STRING");
        parametro5.setLargo(codigoError.length());
        parametro5.setValor(codigoError);
        parametro5.setDireccion("OUT");
        paramLlamada[4] = parametro5;

        parametro6.setTipo("STRING");
        parametro6.setLargo(mensajeError.length());
        parametro6.setValor(mensajeError);
        parametro6.setDireccion("OUT");
        paramLlamada[5] = parametro6;

        for (int i = 0; i < paramLlamada.length; i++) {
            logger.debug("SET     : llamarProcesoCobol id [" + idSecuenciaSivegam + " - " + idReport + "] - LARGO PARAMETRO: " + paramLlamada[i].getLargo());
            logger.debug("SET     : llamarProcesoCobol id [" + idSecuenciaSivegam + " - " + idReport + "] - paramLlamada: " + paramLlamada[i].getValor());
        }

        try {

            logger.debug("CALL    : llamarProcesoCobol id [" + idSecuenciaSivegam + " - " + idReport + "] - LLAMANDO A CONSUMIDOR COBOL...");
            ParametrosLlamadaBO[] salida = ConsumidorCobol.call(paramConexion, paramLlamada);

            String control = "";
            String codError = "";
            String descripcionError = "";
            control = (String) salida[3].getValor();

            logger.debug("CALL    : llamarProcesoCobol id [" + idSecuenciaSivegam + " - " + idReport + "] - PROCESO FUE INVOCADO.");
            logger.debug("RESULT  : llamarProcesoCobol id [" + idSecuenciaSivegam + " - " + idReport + "] - variable control = " + control);

            if ("05".equals(control)) {

                cesantia = GenerarReportesCesantiaImpl.obtenerEstadoTerminoProceso(Integer.parseInt(control));
                cesantia.setCodResultado(5);
            }else if ("03".equals(control)) {

                cesantia = GenerarReportesCesantiaImpl.obtenerEstadoTerminoProceso(Integer.parseInt(control));
                cesantia.setCodResultado(3);
            }else if ("02".equals(control)) {

                cesantia = GenerarReportesCesantiaImpl.obtenerEstadoTerminoProceso(Integer.parseInt(control));
                cesantia.setCodResultado(2);
                codError = (String) salida[4].getValor();
                descripcionError = (String) salida[5].getValor();
                cesantia.setCodError(codError);
                cesantia.setDescripcionError(descripcionError);
            }else if ("07".equals(control)) {
                logger.debug("RESULT  : llamarProcesoCobol id [" + idSecuenciaSivegam + " - " + idReport + "] - mensaje retornado: ERROR ARCHIVO TOMADO");
                logger.debug("RESULT  : llamarProcesoCobol id [" + idSecuenciaSivegam + " - " + idReport + "] - codigo  retornado: " + control);

                cesantia = GenerarReportesCesantiaImpl.obtenerEstadoTerminoProceso(Integer.parseInt(control));
                cesantia.setCodResultado(2);
                codError = (String) salida[4].getValor();
                descripcionError = (String) salida[5].getValor();
                cesantia.setCodError(codError);
                cesantia.setDescripcionError(descripcionError);

            } else if ("08".equals(control)) {
                logger.debug("RESULT  : llamarProcesoCobol id [" + idSecuenciaSivegam + " - " + idReport + "] - mensaje retornado: ERROR CANCELACION COBOL");
                logger.debug("RESULT  : llamarProcesoCobol id [" + idSecuenciaSivegam + " - " + idReport + "] - codigo  retornado: " + control);
                cesantia = GenerarReportesCesantiaImpl.obtenerEstadoTerminoProceso(Integer.parseInt(control));
                cesantia.setCodResultado(2);
                codError = (String) salida[4].getValor();
                descripcionError = (String) salida[5].getValor();
                cesantia.setCodError(codError);
                cesantia.setDescripcionError(descripcionError);

            } else {
            logger.debug("RESULT  : llamarProcesoCobol id [" + idSecuenciaSivegam + " - " + idReport + "] - mensaje retornado: ERROR General");
            logger.debug("RESULT  : llamarProcesoCobol id [" + idSecuenciaSivegam + " - " + idReport + "] - codigo  retornado: " + control);
            cesantia = GenerarReportesCesantiaImpl.obtenerEstadoTerminoProceso(Integer.parseInt(control));
            cesantia.setCodResultado(2);
            codError = (String) salida[4].getValor();
            descripcionError = (String) salida[5].getValor();
            cesantia.setCodError(codError);
            cesantia.setDescripcionError(descripcionError);

        }

            logger.debug("RESULT  : llamarProcesoCobol id [" + idSecuenciaSivegam + " - " + idReport + "] - mensaje retornado: " + cesantia.getCodResultado());
            logger.debug("RESULT  : llamarProcesoCobol id [" + idSecuenciaSivegam + " - " + idReport + "] - mensaje retornado: " + cesantia.getStatusProcesoCesantia());
            logger.debug("FIN     : llamarProcesoCobol id [" + idSecuenciaSivegam + " - " + idReport + "]");
            return cesantia;

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.gc();
        logger.debug("FIN     : llamarProcesoCobol id [" + idSecuenciaSivegam + " - " + idReport + "]");
        return cesantia;
    }

    public static StringBuffer retornaRutaExcelXLS(int tipoReporte, String nombreArchivo) {

        String rutaTmp = "";
        File f = new File("");
        StringBuffer ruta = new StringBuffer();
        ListadoParametros param = ListadoParametros.getInstancia();
        ProcesosAFCCesantiaVO[] procesos = param.getListProcesoAfcCesantia();

        for (int i = 0; i < procesos.length; i++) {
            if (procesos[i].getId_tipo_proceso() == tipoReporte) {
                rutaTmp = procesos[i].getRuta_xls_servidor();
                break;
            }
        }

        ruta.append(f.getAbsolutePath());
        ruta.append(rutaTmp);
        ruta.append(nombreArchivo);

  //      logger.debug("RUTA REPORTE: " + ruta.toString());
        return ruta;
    }

    public static RespuestaVO insertarReporte(int tipoReporte, String nombreArchivo, String periodo) {
        RespuestaVO resp = new RespuestaVO();
        LinCesantia041VO vo1;
        LinCesantia042VO vo2;
        LinCesantia043VO vo3;
        LinCesantia044VO vo4;

        List cellDataList;

        StringBuffer fileName = new StringBuffer();
        fileName = GenerarReportesCesantiaImpl.retornaRutaExcelXLS(tipoReporte, nombreArchivo);

        ExcelSheetReader excelFile = new ExcelSheetReader();
        cellDataList = excelFile.readExcelFile(fileName.toString());

        resp.setCodRespuesta(0);
        resp.setMsgRespuesta("");

        int k = 4;
        long maestroSivegam = 0;
        maestroSivegam = GenerarReportesCesantiaImpl.consultarSivegamTablasCesantia(tipoReporte);
        switch (tipoReporte) {
        case 10:
            resp = GenerarReportesCesantiaDAO.deleteLineaCesantia041Err();
            break;
        case 11:
            resp = GenerarReportesCesantiaDAO.deleteLineaCesantia042Err();
            break;
        case 12:
            resp = GenerarReportesCesantiaDAO.deleteLineaCesantia043Err();
            break;
        case 13:
            resp = GenerarReportesCesantiaDAO.deleteLineaCesantia044Err();
            break;
        default:
            logger.debug("tipo reporte no corresponde");
            break;
        }
        while (k < cellDataList.size()) {
            int id = k - 3;
            List dataList = (List) cellDataList.get(k);
//            if ("".equals(dataList.get(0))) {
//                resp.setCodRespuesta(0);
//                resp.setMsgRespuesta("");
//                break;
//            } else {
                switch (tipoReporte) {
                case 10:

                    vo1 = VOFillerDivPrevisional.llenarLinCesatia041VO(id, (List) cellDataList.get(k), periodo);
                    if (null == vo1) {
                        resp.setCodRespuesta(99);
                        resp.setMsgRespuesta("Error al insertar archivo de Egresos por Pagos de Subsidios de Cesantia");
                        break;
                    }

                    vo1.setId_sc041(id);
                    vo1.setId_maestro_sivegam(maestroSivegam);
                    vo1.setFlag_eliminacion(0);
                    vo1.setFlag_modificacion(0);

                    resp = GenerarReportesCesantiaDAO.insertarLineaCesantia041(vo1);
                    break;
                case 11:
                    vo2 = VOFillerDivPrevisional.llenarLinCesantia042VO(id, (List) cellDataList.get(k), periodo);

                    if (null == vo2) {
                        resp.setCodRespuesta(99);
                        resp.setMsgRespuesta("Error al insertar archivo de Egresos por Pagos de Subsidios de Cesantia");
                        break;
                    }
                    vo2.setId_sc042(k);
                    vo2.setId_maestro_sivegam(maestroSivegam);
                    vo2.setFlag_eliminacion(0);
                    vo2.setFlag_modificacion(0);

                    resp = GenerarReportesCesantiaDAO.insertarLineaCesantia042(vo2);
                    break;
                case 12:
                    vo3 = VOFillerDivPrevisional.llenarLinCesantia043VO(id, (List) cellDataList.get(k), periodo);

                    if (null == vo3) {
                        resp.setCodRespuesta(99);
                        resp.setMsgRespuesta("Error al insertar archivo de Egresos por Pagos de Subsidios de Cesantia");
                        break;
                    }
                    vo3.setId_sc043(k);
                    vo3.setId_maestro_sivegam(maestroSivegam);
                    vo3.setFlag_eliminacion(0);
                    vo3.setFlag_modificacion(0);

                    resp = GenerarReportesCesantiaDAO.insertarLineaCesantia043(vo3);
                    break;
                case 13:
                    vo4 = VOFillerDivPrevisional.llenarLinCesantia044VO(id, (List) cellDataList.get(k), periodo);

                    if (null == vo4) {
                        resp.setCodRespuesta(99);
                        resp.setMsgRespuesta("Error al insertar archivo de Egresos por Pagos de Subsidios de Cesantia");
                        break;
                    }
                    vo4.setId_sc044(k);
                    vo4.setId_mtr_sivegam(maestroSivegam);
                    vo4.setFlg_eli(0);
                    vo4.setFlg_mod(0);

                    resp = GenerarReportesCesantiaDAO.insertarLineaCesantia044(vo4);
                    break;
                default:
                    resp.setMsgRespuesta("Error, el tipo de reporte no corresponde a un código de archivo de Cesantía");
                    resp.setCodRespuesta(99);
                }

                if (resp.getCodRespuesta() == 0) {
                    k++;
                } else {
                    break;
                }

            }
//        }
        return resp;
    }

    /** Funcion que obtiene los datos para reprocesamiento */
    public static ConexionAS400VO obtenerValoresConexionReprocesamiento(int idReporteCesantia) {

        ListadoParametros listaParam1 = ListadoParametros.getInstancia();
        ValoresConexionAS400VO[] valoresConexion = listaParam1.getListValoresConexionToAS400();
        ProcesosAFCCesantiaVO[] tipoProcesos = listaParam1.getListProcesoAfcCesantia();
        ConexionAS400VO conexion = new ConexionAS400VO();

        for (int i = 0; i < valoresConexion.length; i++) {

            if (valoresConexion[i].getId_conexion_as400() == 1) {
                String ipServer = valoresConexion[i].getDescripcion_conexion_as400();
                conexion.setIpServer(ipServer);

            }

            if (valoresConexion[i].getId_conexion_as400() == 2) {
                String usuarioConexion = valoresConexion[i].getDescripcion_conexion_as400();
                conexion.setUsuarioConexion(usuarioConexion);
            }

            if (valoresConexion[i].getId_conexion_as400() == 3) {
                String claveConexion = valoresConexion[i].getDescripcion_conexion_as400();
                conexion.setClaveConexion(claveConexion);
            }
        }

        /*
         * ListadoParametros listaParam2 = ListadoParametros.getInstancia();
         * TipoProcesosVO[] tipoProcesos = listaParam2.getListTipoProcesos();
         */

        for (int j = 0; j < tipoProcesos.length; j++) {
            if (tipoProcesos[j].getId_tipo_proceso() == idReporteCesantia) {
                String nombrePrograma = tipoProcesos[j].getRuta_cl_as400_reprocesar();
                conexion.setNombrePrograma(nombrePrograma);
                break;
            }
        }

        return conexion;
    }

    /**
     * Funcion que invoca al proceso cobol para reprocesar. Cada vez que se
     * carga la tabla desde un excel se debe invocar este cobol de validacion
     */
    public static CesantiaVO reprocesarCobolValidacionCesantia(String idSecuenciaSivegam, String idReport, String periodo) {

        /*
         * Retornos de la funcion: 0: si el proceso fue realizado con exito. 99:
         * si el proceso no fue realizado.
         */
        CesantiaVO cesantia = new CesantiaVO();

        ConexionAS400VO conexion = new ConexionAS400VO();
        conexion = GenerarReportesCesantiaImpl.obtenerValoresConexionReprocesamiento(Integer.parseInt(idReport));

        /*
         * SECCION DONDE SE SETEAN LOS PARAMETROS DE LLAMADA PARA LA INVOCACION
         * DEL PROCESO COBOL
         */
        ParametrosConexionBO paramConexion = new ParametrosConexionBO();
        ParametrosLlamadaBO[] paramLlamada = new ParametrosLlamadaBO[6];

        String spaces = " ";
        String idMaestroSivegamTemp = Helper.paddingString(idSecuenciaSivegam, 12, '0', true);
        String periodoRepr = Helper.paddingString(periodo, 6, '0', true);
        String idLog = Helper.paddingString(spaces, 12, ' ', true);
        String status = Helper.paddingString(spaces, 2, ' ', true);
        String codigoError = Helper.paddingString(spaces, 4, ' ', true);
        String mensajeError = Helper.paddingString(spaces, 200, ' ', false);

        logger.debug("seteando parametro de entrada...");

        /* Seteo de los parametros de conexion. */
        paramConexion.setIpServer(conexion.getIpServer());
        paramConexion.setUsuarioConexion(conexion.getUsuarioConexion());
        paramConexion.setClaveConexion(conexion.getClaveConexion());
        paramConexion.setPrograma(conexion.getNombrePrograma());

        /*
         * Seteo de los parametros de entrada para el proceso COBOL, mediante
         * paramLlamada.
         */
        ParametrosLlamadaBO parametro1 = new ParametrosLlamadaBO();
        ParametrosLlamadaBO parametro2 = new ParametrosLlamadaBO();
        ParametrosLlamadaBO parametro3 = new ParametrosLlamadaBO();
        ParametrosLlamadaBO parametro4 = new ParametrosLlamadaBO();
        ParametrosLlamadaBO parametro5 = new ParametrosLlamadaBO();
        ParametrosLlamadaBO parametro6 = new ParametrosLlamadaBO();

        parametro1.setTipo("STRING");
        parametro1.setLargo(idMaestroSivegamTemp.length());
        parametro1.setValor(idMaestroSivegamTemp);
        parametro1.setDireccion("IN");
        paramLlamada[0] = parametro1;

        parametro2.setTipo("STRING");
        parametro2.setLargo(periodoRepr.length());
        parametro2.setValor(periodoRepr);
        parametro2.setDireccion("IN");
        paramLlamada[1] = parametro2;

        parametro3.setTipo("STRING");
        parametro3.setLargo(idLog.length());
        parametro3.setValor(idLog);
        parametro3.setDireccion("OUT");
        paramLlamada[2] = parametro3;

        parametro4.setTipo("STRING");
        parametro4.setLargo(status.length());
        parametro4.setValor(status);
        parametro4.setDireccion("OUT");
        paramLlamada[3] = parametro4;

        parametro5.setTipo("STRING");
        parametro5.setLargo(codigoError.length());
        parametro5.setValor(codigoError);
        parametro5.setDireccion("OUT");
        paramLlamada[4] = parametro5;

        parametro6.setTipo("STRING");
        parametro6.setLargo(mensajeError.length());
        parametro6.setValor(mensajeError);
        parametro6.setDireccion("OUT");
        paramLlamada[5] = parametro6;

        for (int i = 0; i < paramLlamada.length; i++) {
            logger.debug("LARGO PARAMETRO: " + paramLlamada[i].getLargo());
            logger.debug("paramLlamada: " + paramLlamada[i].getValor());
        }

        try {

            logger.debug("LLAMANDO A CONSUMIDOR COBOL...");
            ParametrosLlamadaBO[] salida = ConsumidorCobol.call(paramConexion, paramLlamada);

            String control = "";
            String codError = "";
            String descripcionError = "";
            control = (String) salida[3].getValor();
            logger.debug("PROCESO FUE INVOCADO.");
            logger.debug("variable control = " + control);

            if ("05".equals(control)) {
                cesantia = GenerarReportesCesantiaImpl.obtenerEstadoTerminoProceso(Integer.parseInt(control));
                cesantia.setCodResultado(5);

                return cesantia;
            }

            if ("03".equals(control)) {
                cesantia = GenerarReportesCesantiaImpl.obtenerEstadoTerminoProceso(Integer.parseInt(control));
                cesantia.setCodResultado(3);

            }

            if ("02".equals(control)) {

                cesantia = GenerarReportesCesantiaImpl.obtenerEstadoTerminoProceso(Integer.parseInt(control));
                cesantia.setCodResultado(2);
                codError = (String) salida[3].getValor();
                descripcionError = (String) salida[4].getValor();
                logger.debug(codError + " - " + descripcionError);
                cesantia.setCodError(codError);
                cesantia.setDescripcionError(descripcionError);
            }

            logger.debug("mensaje retornado: " + cesantia.getCodResultado());
            logger.debug("mensaje retornado: " + cesantia.getStatusProcesoCesantia());
            return cesantia;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cesantia;
    }

    /**
     * Funcion que realiza borrado logico de las tablas de cesantia,
     * discriminando por tipo Reporte
     */
    public static RespuestaVO deleteLogicoSegunReporte(int tipoReporte) {

        RespuestaVO resp = new RespuestaVO();

        switch (tipoReporte) {
        case 10:
            resp = GenerarReportesCesantiaDAO.deleteLogicoSC41();
            break;
        case 11:
            resp = GenerarReportesCesantiaDAO.deleteLogicoSC42();
            break;
        case 12:
            resp = GenerarReportesCesantiaDAO.deleteLogicoSC43();
            break;
        case 13:
            resp = GenerarReportesCesantiaDAO.deleteLogicoSC44();
            break;
        default:
            resp.setCodRespuesta(99);

        }

        return resp;
    }

    public static RespuestaVO generarReporteXLS(String idReporteXls, String periodoReporte, String idMaestroSiv, String mesReporte, String usser, String fechaReporte) {

        String rutaCarpeta = "";
        String nombreArchivo = "";
        String rutaJasper = "";

        RespuestaVO respuesta = new RespuestaVO();
        StringBuffer rutaFinalReporte = new StringBuffer();
        ConexionAS400VO conexion = new ConexionAS400VO();

        /* Se preparan los valores para la conexion */
        conexion = HelperAFCCesantia.obtenerDatosConexion();

        /* Se obtiene ruta y nombre precompilado jasper */
        rutaJasper = HelperAFCCesantia.obtenerRutaNombreJRXML(Integer.parseInt(idReporteXls));

        /* Se obtiene nombre de archivo y ruta de carpeta en servidor */
        nombreArchivo = HelperAFCCesantia.retornarNombreArchivoExcelCesantia(Integer.parseInt(idReporteXls), periodoReporte);
        rutaCarpeta = HelperAFCCesantia.retornarRutaArchivo(Integer.parseInt(idReporteXls));

        /* Se forma ruta final del reporte */
        rutaFinalReporte.append(rutaCarpeta);
        rutaFinalReporte.append(nombreArchivo);
       //logger.debug("rutaFinal: " + rutaFinalReporte.toString());

        /* Se llama a clase ReporDriver para generar reportes */
        ReportDriver.runReport(conexion.getIpServer(), conexion.getUsuarioConexion(), conexion.getClaveConexion(), rutaJasper, rutaFinalReporte.toString(), periodoReporte);

        /* Se inserta evidencia de generacion de archivo en tabla DetalleReporte */
        respuesta = GenerarReportesAFCImpl.insertIntoDetalleReportes(idReporteXls, nombreArchivo, idMaestroSiv, mesReporte, usser, fechaReporte);
        if (respuesta.getCodRespuesta() != 99) {
            respuesta.setCodRespuesta(0);
            respuesta.setRutaArchivo(rutaFinalReporte.toString());
        }
        return respuesta;
    }

    public static void insertIntoDetalleReportes(String idReporteXls, String nombreReporte, String idMaestroSiv, String mesReporte, String usser, String fechaReporte) {

        Date dateProceso = new Date();
        String DATE_FORMAT2 = "dd/MM/yyyy";
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);
        DetalleReporteSivegamVO detalleReporte = new DetalleReporteSivegamVO();

        try {
            detalleReporte.setMaestro_sivegam(Long.parseLong(idMaestroSiv));
            detalleReporte.setNombre_reporte(nombreReporte);

            dateProceso = sdf2.parse(fechaReporte);
            detalleReporte.setFechaReporteDate(dateProceso);

            detalleReporte.setStatus_proceso(3);
            detalleReporte.setTipo_proceso(Integer.parseInt(idReporteXls));
            detalleReporte.setPeriodo_proceso(Integer.parseInt(mesReporte));
            detalleReporte.setFomato_reporte(3);
            detalleReporte.setUsuario_sivegam(Long.parseLong(usser));

            GenerarReportesDAO.insertDetalleReporteSivegam(detalleReporte);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static RespuestaVO generarReporteErroresXls(String idFlagReporteXls, String periodoReporte, String idMasterSivegam, String mesReporte, String usser, String fechaSistema) {

        String rutaCarpeta = "";
        String nombreArchivo = "";
        String rutaJasper = "";

        RespuestaVO respuesta = new RespuestaVO();
        StringBuffer rutaFinalReporte = new StringBuffer();
        ConexionAS400VO conexion = new ConexionAS400VO();

        /* Se preparan los valores para la conexion */
        conexion = HelperAFCCesantia.obtenerDatosConexion();

        /* Se obtiene ruta y nombre precompilado jasper */
        rutaJasper = HelperAFCCesantia.obtenerRutaNombreJRXMLErrores(Integer.parseInt(idFlagReporteXls));

        /* Se obtiene nombre de archivo y ruta de carpeta en servidor */
        nombreArchivo = HelperAFCCesantia.retornarNombreArchivoErrores(Integer.parseInt(idFlagReporteXls), periodoReporte);
        rutaCarpeta = HelperAFCCesantia.retornarRutaArchivoErrores(Integer.parseInt(idFlagReporteXls));

        /* Se forma ruta final del reporte */
        rutaFinalReporte.append(rutaCarpeta);
        rutaFinalReporte.append(nombreArchivo);
   //     logger.debug("rutaFinal: " + rutaFinalReporte.toString());

        /* Se llama a clase ReporDriver para generar reportes */
        ReportDriver.runReport(conexion.getIpServer(), conexion.getUsuarioConexion(), conexion.getClaveConexion(), rutaJasper, rutaFinalReporte.toString(), periodoReporte);

        /* Se inserta evidencia de generacion de archivo en tabla DetalleReporte */
        respuesta = GenerarReportesAFCImpl.insertIntoDetalleReportes(idFlagReporteXls, nombreArchivo, idMasterSivegam, mesReporte, usser, fechaSistema);
        if (respuesta.getCodRespuesta() != 99) {
            respuesta.setCodRespuesta(0);
            respuesta.setRutaArchivo(rutaFinalReporte.toString());
        }
        return respuesta;
    }

    public static String obtenerRutaReporteExcel(int tipoReporte, String periodoReporte) {

        int existeArchivo = 0;
        String nombreArchivo = "";
        String rutaReporte = "";
        String rutaVacia = "";
        StringBuffer rutaCompleta = new StringBuffer();

        nombreArchivo = HelperAFCCesantia.retornarNombreArchivoExcelCesantia(tipoReporte, periodoReporte);
        rutaReporte = HelperAFCCesantia.retornarRutaArchivo(tipoReporte);

        rutaCompleta.append(rutaReporte);
        rutaCompleta.append(nombreArchivo);

        existeArchivo = HelperAFCCesantia.existeArchivo(rutaCompleta.toString());
        if (existeArchivo == 1) {
            return rutaCompleta.toString();
        } else {
            return rutaVacia;
        }
    }

    public static String obtenerRutaReporteTxt(int tipoReporte, String periodoReporte) {

        int existe = 0;
        String nombreArchivo = "";
        String rutaReporte = "";
        String rutaVacia = "";
        StringBuffer rutacompleta = new StringBuffer();

        nombreArchivo = HelperAFCCesantia.retornaNombreArchivoTxt(tipoReporte, periodoReporte);
        rutaReporte = HelperAFCCesantia.ontenerRutaArchivoTxt(tipoReporte);

        rutacompleta.append(rutaReporte);
        rutacompleta.append(nombreArchivo);

   //     logger.debug("DESDE obtenerRutaReporteTxt: " + rutacompleta.toString());
        existe = HelperAFCCesantia.existeArchivo(rutacompleta.toString());
        if (existe == 1) {
            return rutacompleta.toString();
        } else {
            return rutaVacia;
        }

    }

    public static String obtenerRutaReporteExcelErr(int tipoReporte, String periodoReporte) {

        int existe = 0;
        String nombreArchivo = "";
        String rutaReporte = "";
        String rutaVacia = "";
        StringBuffer rutacompleta = new StringBuffer();

        nombreArchivo = HelperAFCCesantia.retornarNombreArchivoErrores(tipoReporte, periodoReporte);
        rutaReporte = HelperAFCCesantia.retornarRutaArchivoErrores(tipoReporte);

        rutacompleta.append(rutaReporte);
        rutacompleta.append(nombreArchivo);

        existe = HelperAFCCesantia.existeArchivo(rutacompleta.toString());
        if (existe == 1) {
            return rutacompleta.toString();
        } else {
            return rutaVacia;
        }

    }

    public static RespuestaVO actualizarStatusSegunPeriodoYProceso(String tipoReporte, String mesPeriodo) {

        return GenerarReportesCesantiaDAO.actualizarStatusSegunPeriodoYProceso(tipoReporte, mesPeriodo);
    }

    public static MaestroSivegamVO selectMaxIdMaestroSivegamCesantia(int tipoArchivo, String periodoMes) {

        return GenerarReportesCesantiaDAO.selectMaxIdMaestroSivegamCesantia(tipoArchivo, periodoMes);
    }

    /**
     * Funcion que consulta por idmaestro sivegam de acuerdo al tipo de reporte,
     * en las tablas correspondientes de cesantia.
     */
    public static long consultarSivegamTablasCesantia(int tipoReporte) {

        long idMaestroSivegam = 0;

        switch (tipoReporte) {
        case 10:
            idMaestroSivegam = GenerarReportesCesantiaDAO.consultarSivegamTablasCesantia041();
            break;

        case 11:
            idMaestroSivegam = GenerarReportesCesantiaDAO.consultarSivegamTablasCesantia042();
            break;

        case 12:
            idMaestroSivegam = GenerarReportesCesantiaDAO.consultarSivegamTablasCesantia043();
            break;

        case 13:
            idMaestroSivegam = GenerarReportesCesantiaDAO.consultarSivegamTablasCesantia044();
            break;

        default:
            idMaestroSivegam = 0;
        }

        return idMaestroSivegam;
    }
}
