package cl.araucana.sivegam.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cl.araucana.sivegam.conexion.cobol.bo.ParametrosConexionBO;
import cl.araucana.sivegam.conexion.cobol.bo.ParametrosLlamadaBO;
import cl.araucana.sivegam.config.ConsumidorCobol;
import cl.araucana.sivegam.dao.GenerarReportesAFCDAO;
import cl.araucana.sivegam.dao.GenerarReportesDAO;
import cl.araucana.sivegam.helper.ExcelSheetReader;
import cl.araucana.sivegam.helper.Helper;
import cl.araucana.sivegam.helper.HelperAFCCesantia;
import cl.araucana.sivegam.helper.ReportDriver;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.helper.VOFillerDivPrevisional;
import cl.araucana.sivegam.vo.AfcVO;
import cl.araucana.sivegam.vo.AfentradVO;
import cl.araucana.sivegam.vo.ConexionAS400VO;
import cl.araucana.sivegam.vo.DetalleReporteSivegamVO;
import cl.araucana.sivegam.vo.LinAfcAFF01VO;
import cl.araucana.sivegam.vo.ProcesosAFCCesantiaVO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.StatusProcesoVO;
import cl.araucana.sivegam.vo.ValoresConexionAS400VO;
import cl.araucana.sivegam.vo.param.ListadoParametros;

public class GenerarReportesAFCImpl {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    /** Funcion que obtiene los valores de conexion para cobol de entrada */
    public static ConexionAS400VO obtenerValoresConexionEntrada(int idReporteAFC) {

        ListadoParametros listaParam1 = ListadoParametros.getInstancia();
        ValoresConexionAS400VO[] valoresConexion = listaParam1.getListValoresConexionToAS400();
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

        ListadoParametros listaParam2 = ListadoParametros.getInstancia();
        ProcesosAFCCesantiaVO[] procesoAfcCesantia = listaParam2.getListProcesoAfcCesantia();

        for (int j = 0; j < procesoAfcCesantia.length; j++) {

            int idProcesoAfcCesantia = 0;
            switch (idReporteAFC) {
            case 8:
                idProcesoAfcCesantia = 1;
                break;
            case 9:
                idProcesoAfcCesantia = 2;
                break;
            case 10:
                idProcesoAfcCesantia = 3;
                break;
            case 11:
                idProcesoAfcCesantia = 4;
                break;
            case 12:
                idProcesoAfcCesantia = 5;
                break;
            case 13:
                idProcesoAfcCesantia = 6;
                break;
            default:

            }

            if (procesoAfcCesantia[j].getId_proceso_afc_cesantia() == idProcesoAfcCesantia) {
                String nombrePrograma = procesoAfcCesantia[j].getRuta_cl_as400_entrada();
                conexion.setNombrePrograma(nombrePrograma);
            }
        }

        return conexion;
    }

    /** Funcion que invoca proceso cobol para archivo de entrada. */
    public static AfcVO invocarProcesoEntradaAfc(String idReporteAfc, String tipoArchivo, String periodo, String fechaActual, String usuario) {

        /** El tipo de archivo puede ser M ó R (mensual o retroactivo) */
        logger.debug("INI     : invocarProcesoEntradaAfc id [" + idReporteAfc + "]");
        System.gc();
        AfcVO afc = new AfcVO();
        AfentradVO afenvo = new AfentradVO();
        RespuestaVO resp = new RespuestaVO();
        logger.debug("IN      : invocarProcesoEntradaAfc id [" + idReporteAfc + "] - Parametros de entrada: " + idReporteAfc + " - " + tipoArchivo + " - " + periodo);
        ConexionAS400VO conexion = new ConexionAS400VO();
        conexion = GenerarReportesAFCImpl.obtenerValoresConexionEntrada(Integer.parseInt(idReporteAfc));

        /*
         * SECCION DONDE SE SETEAN LOS PARAMETROS DE LLAMADA PARA LA INVOCACION
         * DEL PROCESO COBOL
         */
        ParametrosConexionBO paramConexion = new ParametrosConexionBO();
        ParametrosLlamadaBO[] paramLlamada = new ParametrosLlamadaBO[5];
        resp = GenerarReportesAFCDAO.updatePrevInsertaAFENTRAD(periodo);
        if (resp.getCodRespuesta() == 0) {
            afenvo = GenerarReportesAFCDAO.insertaAFENTRAD(tipoArchivo, periodo, fechaActual, usuario);
            String ceros = "0";
            String blancos = " ";
            String parametroSalidaUno = Helper.paddingString(ceros, 4, '0', true);
            String parametroSalidaDos = Helper.paddingString(blancos, 200, ' ', false);
            /*
             * String parametroEntradaUno =
             * String.valueOf(afenvo.getIDESTCARGA()); do{ if
             * (parametroEntradaUno.length()<12){ parametroEntradaUno=
             * "0"+parametroEntradaUno; } }while(parametroEntradaUno.length()<12);
             */
            String parametroEntradaUno = Helper.paddingString(String.valueOf(afenvo.getIDESTCARGA()), 12, '0', true);
            logger.debug("SET     : invocarProcesoEntradaAfc id [" + idReporteAfc + "] - seteando parametro de entrada...");

            /* Seteo de los parametros de conexion. */
            paramConexion.setIpServer(conexion.getIpServer());
            paramConexion.setUsuarioConexion(conexion.getUsuarioConexion());
            paramConexion.setClaveConexion(conexion.getClaveConexion());
            paramConexion.setPrograma(conexion.getNombrePrograma());
            logger.debug("SET     : invocarProcesoEntradaAfc id [" + idReporteAfc + "] - Programa : " + conexion.getNombrePrograma());

            /*
             * Seteo de los parametros de entrada para el proceso COBOL,
             * mediante paramLlamada.
             */
            ParametrosLlamadaBO parametro1 = new ParametrosLlamadaBO();
            ParametrosLlamadaBO parametro2 = new ParametrosLlamadaBO();
            ParametrosLlamadaBO parametro3 = new ParametrosLlamadaBO();
            ParametrosLlamadaBO parametro4 = new ParametrosLlamadaBO();
            ParametrosLlamadaBO parametro5 = new ParametrosLlamadaBO();

            parametro1.setTipo("STRING");
            parametro1.setLargo(parametroEntradaUno.length());
            parametro1.setValor(parametroEntradaUno);
            parametro1.setDireccion("IN");
            paramLlamada[0] = parametro1;
            String aux = "";
            if (idReporteAfc.equals("8")) {
                aux = "R";
            } else {
                aux = "M";
            }

            parametro2.setTipo("STRING");
            parametro2.setLargo(idReporteAfc.length());
            parametro2.setValor(aux);
            parametro2.setDireccion("IN");
            paramLlamada[1] = parametro2;

            parametro3.setTipo("STRING");
            parametro3.setLargo(periodo.length());
            parametro3.setValor(periodo);
            parametro3.setDireccion("IN");
            paramLlamada[2] = parametro3;

            parametro4.setTipo("STRING");
            parametro4.setLargo(parametroSalidaUno.length());
            parametro4.setValor(parametroSalidaUno);
            parametro4.setDireccion("OUT");
            paramLlamada[3] = parametro4;

            parametro5.setTipo("STRING");
            parametro5.setLargo(parametroSalidaDos.length());
            parametro5.setValor(parametroSalidaDos);
            parametro5.setDireccion("OUT");
            paramLlamada[4] = parametro5;

            for (int i = 0; i < paramLlamada.length; i++) {
                logger.debug("SET     : invocarProcesoEntradaAfc id [" + idReporteAfc + "] - LARGO PARAMETRO - " + i + " -: " + paramLlamada[i].getLargo());
                logger.debug("SET     : invocarProcesoEntradaAfc id [" + idReporteAfc + "] - paramLlamada - " + i + " -: " + paramLlamada[i].getValor());
            }

            try {

                logger.debug("CALL    : invocarProcesoEntradaAfc id [" + idReporteAfc + "] - LLAMANDO A CONSUMIDOR COBOL...");
                ParametrosLlamadaBO[] salida = ConsumidorCobol.call(paramConexion, paramLlamada);

                String control = "";
                String descripcionError = "";
                logger.debug("CALL    : invocarProcesoEntradaAfc id [" + idReporteAfc + "] - LISTO PARA SETEAR RESP... PROCESO FUE INVOCADO.");

                control = (String) salida[3].getValor();
                descripcionError = (String) salida[4].getValor();

                RespuestaVO Repu = GenerarReportesAFCDAO.statusProcesoCarga(periodo, aux);
                if (Repu.getStatus() == 1) {
                    control = "9999";
                    descripcionError = "El archivo se ha demorado demasiado en cargar, el estado ha quedado fallido e intente nuevamente.";
                }

                logger.debug("RESULT  : invocarProcesoEntradaAfc id [" + idReporteAfc + "] - variable control = " + control);
                logger.debug("RESULT  : invocarProcesoEntradaAfc id [" + idReporteAfc + "] - variable descripcionError = " + descripcionError);

                if (control.equals("0000")) {

                    afc.setCodResultado(2);
                    afc.setStatusProcesoAfc(GenerarReportesAFCImpl.retornarStatusProceso(afc.getCodResultado()));

                    return afc;

                } else {
                    afc.setCodResultado(3);
                    afc.setStatusProcesoAfc(GenerarReportesAFCImpl.retornarStatusProceso(afc.getCodResultado()));
                    afc.setCodError(control);
                    afc.setDescripcionError(descripcionError.trim());

                    return afc;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            afc.setCodResultado(999);
            afc.setCodError("999");
            afc.setDescripcionError("error en Update de estados anteriores.");
        }
        System.gc();
       logger.debug("FIN     : invocarProcesoEntradaAfc id [" + idReporteAfc + "]");
        return afc;
    }

    /** Funcion que retorna el status luego de haber invocado un proceso cobol. */
    public static String retornarStatusProceso(int respuestaCobol) {

        String status = "";

        ListadoParametros listaParam = ListadoParametros.getInstancia();
        StatusProcesoVO[] sp = listaParam.getListStatusProceso();
        for (int p = 0; p < sp.length; p++) {
            if (sp[p].getStatus_proceso() == respuestaCobol) {
                status = sp[p].getDescripcion_status_proceso();
                break;

            }
        }

        return status;
    }

    /**
     * Funcion que retorna el estado de termino de un proceso, luego de recibir
     * la respuesta arrojada por el proceso cobol.
     */
    public static AfcVO obtenerEstadoTerminoProceso(int controlCobol) {

        AfcVO afc = new AfcVO();
        ListadoParametros listaParam = ListadoParametros.getInstancia();
        StatusProcesoVO[] statusProceso = listaParam.getListStatusProceso();

        for (int i = 0; i < statusProceso.length; i++) {
            if (statusProceso[i].getStatus_proceso() == controlCobol) {
                afc.setStatusProcesoAfc(statusProceso[i].getDescripcion_status_proceso());
                break;
            }
        }

        return afc;
    }

    /** Funcion que obtiene los valores de conexion para cobol de entrada */
    public static ConexionAS400VO obtenerValoresConexionProcesos(int idReporteAFC) {

        ListadoParametros listaParam1 = ListadoParametros.getInstancia();
        ValoresConexionAS400VO[] valoresConexion = listaParam1.getListValoresConexionToAS400();
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

        ListadoParametros listaParam2 = ListadoParametros.getInstancia();
        ProcesosAFCCesantiaVO[] procesoAfcCesantia = listaParam2.getListProcesoAfcCesantia();

        for (int j = 0; j < procesoAfcCesantia.length; j++) {
            if (procesoAfcCesantia[j].getId_tipo_proceso() == idReporteAFC) {
                String nombrePrograma = procesoAfcCesantia[j].getRuta_cl_as400();
                conexion.setNombrePrograma(nombrePrograma);
                break;
            }
        }

        return conexion;
    }

    /** Establece los parametros de conexion para reproceso. */
    public static ConexionAS400VO obtenerValoresConexionReproceso(int idReporteAFC) {

        ListadoParametros listaParam1 = ListadoParametros.getInstancia();
        ValoresConexionAS400VO[] valoresConexion = listaParam1.getListValoresConexionToAS400();
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

        ListadoParametros listaParam2 = ListadoParametros.getInstancia();
        ProcesosAFCCesantiaVO[] procesoAfcCesantia = listaParam2.getListProcesoAfcCesantia();

        for (int j = 0; j < procesoAfcCesantia.length; j++) {
            if (procesoAfcCesantia[j].getId_tipo_proceso() == idReporteAFC) {
                String nombrePrograma = procesoAfcCesantia[j].getRuta_cl_as400_reprocesar();
                conexion.setNombrePrograma(nombrePrograma);
                break;
            }
        }

        return conexion;

    }

    public static AfcVO llamarProcesoCobolAFC(int flagReproceso, String idSecuenciaSivegam, String idReport, String periodo, String tipoReporte) {

        /*
         * Retornos de la funcion: 0: si el proceso fue realizado con exito. 99:
         * si el proceso no fue realizado. Tipo reporte puede ser M o R.
         */
        logger.debug("INI     : llamarProcesoCobolAFC id [" + idSecuenciaSivegam + " - " + idReport + "]");
        System.gc();
        AfcVO afc = new AfcVO();

        ConexionAS400VO conexionProceso = new ConexionAS400VO();
        ConexionAS400VO conexionReproceso = new ConexionAS400VO();
        conexionProceso = GenerarReportesAFCImpl.obtenerValoresConexionProcesos(Integer.parseInt(idReport));
        conexionReproceso = GenerarReportesAFCImpl.obtenerValoresConexionReproceso(Integer.parseInt(idReport));

        ParametrosConexionBO paramConexion = new ParametrosConexionBO();
        ParametrosLlamadaBO[] paramLlamada = new ParametrosLlamadaBO[7];
        String spaces = " ";
        String idMaestroSivegamTemp = Helper.paddingString(idSecuenciaSivegam, 12, '0', true);
        String idLog = Helper.paddingString(spaces, 12, ' ', true);
        String status = Helper.paddingString(spaces, 2, ' ', true);
        String codigoError = Helper.paddingString(spaces, 4, ' ', true);
        String mensajeError = Helper.paddingString(spaces, 200, ' ', false);

        logger.debug("SET     : llamarProcesoCobolAFC id [" + idSecuenciaSivegam + " - " + idReport + "] - seteando parametro de entrada...");

        /* Seteo de los parametros de conexion. */
        switch (flagReproceso) {
        case 0:
            paramConexion.setIpServer(conexionProceso.getIpServer());
            paramConexion.setUsuarioConexion(conexionProceso.getUsuarioConexion());
            paramConexion.setClaveConexion(conexionProceso.getClaveConexion());
            paramConexion.setPrograma(conexionProceso.getNombrePrograma());
            break;
        case 1:
            paramConexion.setIpServer(conexionReproceso.getIpServer());
            paramConexion.setUsuarioConexion(conexionReproceso.getUsuarioConexion());
            paramConexion.setClaveConexion(conexionReproceso.getClaveConexion());
            paramConexion.setPrograma(conexionReproceso.getNombrePrograma());
            break;
        default:
            logger.debug("ERROR   : llamarProcesoCobolAFC id [" + idSecuenciaSivegam + " - " + idReport + "] - Error, no es posible establecer los parámetros de conexión");
        }

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
        ParametrosLlamadaBO parametro7 = new ParametrosLlamadaBO();

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
        parametro3.setLargo(tipoReporte.length());
        parametro3.setValor(tipoReporte);
        parametro3.setDireccion("IN");
        paramLlamada[2] = parametro3;

        parametro4.setTipo("STRING");
        parametro4.setLargo(idLog.length());
        parametro4.setValor(idLog);
        parametro4.setDireccion("OUT");
        paramLlamada[3] = parametro4;

        parametro5.setTipo("STRING");
        parametro5.setLargo(status.length());
        parametro5.setValor(status);
        parametro5.setDireccion("OUT");
        paramLlamada[4] = parametro5;

        parametro6.setTipo("STRING");
        parametro6.setLargo(codigoError.length());
        parametro6.setValor(codigoError);
        parametro6.setDireccion("OUT");
        paramLlamada[5] = parametro6;

        parametro7.setTipo("STRING");
        parametro7.setLargo(mensajeError.length());
        parametro7.setValor(mensajeError);
        parametro7.setDireccion("OUT");
        paramLlamada[6] = parametro7;

        for (int i = 0; i < paramLlamada.length; i++) {
            logger.debug("SET     : llamarProcesoCobolAFC id [" + idSecuenciaSivegam + " - " + idReport + "] - LARGO PARAMETRO: " + paramLlamada[i].getLargo());
            logger.debug("SET     : llamarProcesoCobolAFC id [" + idSecuenciaSivegam + " - " + idReport + "] - paramLlamada: " + paramLlamada[i].getValor());
        }

        try {

            logger.debug("CALL    : llamarProcesoCobolAFC id [" + idSecuenciaSivegam + " - " + idReport + "] - LLAMANDO A CONSUMIDOR COBOL...");
            ParametrosLlamadaBO[] salida = ConsumidorCobol.call(paramConexion, paramLlamada);
            for (ParametrosLlamadaBO o : salida) {
                logger.debug("RESULT  : llamarProcesoCobolAFC id [" + idSecuenciaSivegam + " - " + idReport + "] - variable control = " + o.getValor().toString());
            }

            String control = "";
            control = (String) salida[4].getValor();
            logger.debug("RESULT  : llamarProcesoCobolAFC id [" + idSecuenciaSivegam + " - " + idReport + "] - PROCESO FUE INVOCADO.");
            logger.debug("RESULT  : llamarProcesoCobolAFC id [" + idSecuenciaSivegam + " - " + idReport + "] - variable control = " + control);

            if ("05".equals(control)) {
                logger.debug("RESULT  : llamarProcesoCobolAFC id [" + idSecuenciaSivegam + " - " + idReport + "] - codigo retornado: " + afc.getCodResultado());
                logger.debug("RESULT  : llamarProcesoCobolAFC id [" + idSecuenciaSivegam + " - " + idReport + "] - mensaje retornado: " + afc.getStatusProcesoAfc());
                afc = GenerarReportesAFCImpl.obtenerEstadoTerminoProceso(Integer.parseInt(control));
                afc.setCodResultado(5);
                return afc;
            }else if("03".equals(control)) {
                logger.debug("RESULT  : llamarProcesoCobolAFC id [" + idSecuenciaSivegam + " - " + idReport + "] - codigo retornado: " + afc.getCodResultado());
                logger.debug("RESULT  : llamarProcesoCobolAFC id [" + idSecuenciaSivegam + " - " + idReport + "] - mensaje retornado: " + afc.getStatusProcesoAfc());
                afc = GenerarReportesAFCImpl.obtenerEstadoTerminoProceso(Integer.parseInt(control));
                afc.setCodResultado(3);
            }else if ("02".equals(control)) {
                logger.debug("RESULT  : llamarProcesoCobolAFC id [" + idSecuenciaSivegam + " - " + idReport + "] - codigo retornado: " + afc.getCodResultado());
                logger.debug("RESULT  : llamarProcesoCobolAFC id [" + idSecuenciaSivegam + " - " + idReport + "] - mensaje retornado: " + afc.getStatusProcesoAfc());
                afc = GenerarReportesAFCImpl.obtenerEstadoTerminoProceso(Integer.parseInt(control));
                afc.setCodResultado(2);
            }else if ("07".equals(control)) {
                logger.debug("RESULT  : llamarProcesoCobolAFC id [" + idSecuenciaSivegam + " - " + idReport + "] - mensaje retornado: ERROR ARCHIVO TOMADO");
                logger.debug("RESULT  : llamarProcesoCobolAFC id [" + idSecuenciaSivegam + " - " + idReport + "] - codigo  retornado: " + control);
                afc = GenerarReportesAFCImpl.obtenerEstadoTerminoProceso(2);
                afc.setCodResultado(2);
            } else if ("08".equals(control)) {
                logger.debug("RESULT  : llamarProcesoCobolAFC id [" + idSecuenciaSivegam + " - " + idReport + "] - mensaje retornado: ERROR CANCELACION COBOL");
                logger.debug("RESULT  : llamarProcesoCobolAFC id [" + idSecuenciaSivegam + " - " + idReport + "] - codigo  retornado: " + control);
                afc = GenerarReportesAFCImpl.obtenerEstadoTerminoProceso(2);
                afc.setCodResultado(2);
            } else {
                logger.debug("RESULT  : llamarProcesoCobolAFC id [" + idSecuenciaSivegam + " - " + idReport + "] - mensaje retornado: ERROR GENERAL");
                logger.debug("RESULT  : llamarProcesoCobolAFC id [" + idSecuenciaSivegam + " - " + idReport + "] - codigo  retornado: " + control);
                afc = GenerarReportesAFCImpl.obtenerEstadoTerminoProceso(2);
                afc.setCodResultado(2);
            }

            return afc;

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.gc();
        logger.debug("FIN     : llamarProcesoCobol id [" + idSecuenciaSivegam + " - " + idReport + "]");
        return afc;
    }

    public static String retornaRutaCargarArchivo(int idReporte) {

        String ruta = "";

        ListadoParametros lp = ListadoParametros.getInstancia();
        ProcesosAFCCesantiaVO[] procAfcSc = lp.getListProcesoAfcCesantia();

        for (int i = 0; i < procAfcSc.length; i++) {
            long idProcAfcSc = 0;
            switch (idReporte) {
            case 8:
                idProcAfcSc = 1;
                break;
            case 9:
                idProcAfcSc = 2;
                break;
            default:
                logger.debug("Error, no es posible identificar idProcesoAFSC");
            }

            logger.debug("valor variable: " + idProcAfcSc);
            if (procAfcSc[i].getId_proceso_afc_cesantia() == idProcAfcSc) {
                ruta = procAfcSc[i].getRuta_xls_servidor();
            }
        }

        logger.debug("ruta: " + ruta);
        return ruta;
    }

    public static RespuestaVO insertarReporte(String flagTipoReporte, String nombreArchivo, String periodo) {
        int idReporte;
        RespuestaVO resp = new RespuestaVO();
        LinAfcAFF01VO vo;

        List cellDataList;

        if ("R".equals(flagTipoReporte)) {
            idReporte = 8;
        } else {
            idReporte = 9;
        }

        File file = new File("");
        StringBuffer fileName = new StringBuffer();
        fileName.append(file.getAbsolutePath());
        fileName.append(GenerarReportesAFCImpl.retornaRutaCargarArchivo(idReporte));
        fileName.append(nombreArchivo);

        ExcelSheetReader excelFile = new ExcelSheetReader();
        cellDataList = excelFile.readExcelFile(fileName.toString());

        resp.setCodRespuesta(0);
        resp.setMsgRespuesta("");

        GenerarReportesAFCDAO.deleteLineaAFCErr(flagTipoReporte);
        int k = 4;
        long idMaestroSivegam = 0;
        idMaestroSivegam = GenerarReportesAFCImpl.consultarSivegamTablasAFC(flagTipoReporte);

        while (k < cellDataList.size()) {
            int id = k - 3;
            List dataList = (List) cellDataList.get(k);
            //if ("".equals(dataList.get(0))) {
            //    resp.setCodRespuesta(0);
            //    resp.setMsgRespuesta("");
               // break;
            //} else {
                vo = VOFillerDivPrevisional.llenarLinAfcAFF01VO(id, (List) cellDataList.get(k), periodo, flagTipoReporte);

                vo.setId_archivo(id);
                vo.setId_maestro_Sivegam(idMaestroSivegam);
                vo.setFlag_eliminacion_registro(0);
                vo.setFlag_modificacion_registro(0);
                vo.setTipo_de_Archivo(flagTipoReporte);
                vo.setAfc_Periodo(periodo);

                resp = GenerarReportesAFCDAO.insertarLineaAFC(vo, flagTipoReporte);

                if (resp.getCodRespuesta() == 0) {
                    k++;
                } else {
                    resp.setMsgRespuesta("Error al insertar Linea de Archivo AFC");
                    resp.setCodRespuesta(99);
                    break;
                }
            }
        //}

        return resp;
    }

    /** Realiza eliminacion logica. */
    public static RespuestaVO deleteLogicoSegunReporteAFC(int tipoReporte) {

        RespuestaVO respuesta = new RespuestaVO();

        switch (tipoReporte) {
        case 8:
            respuesta = GenerarReportesAFCDAO.deleteLogicoRetroactivo();
            break;
        case 9:
            respuesta = GenerarReportesAFCDAO.deleteLogicoMensual();
            break;
        default:
            logger.debug("Error al tratar de eliminar, tipoReporte no coincide con archivos de afc.");

        }

        return respuesta;
    }

    /** Funcion que genera los reportes en excel de AFC. */
    public static RespuestaVO generarReporteXLS(String idReporteXls, String periodoReporte, String idMaestroSiv, String mesReporte, String usser, String fechaReporte,
            String flagReporte) {

        String rutaCarpeta = "";
        String nombreArchivo = "";
        String rutaJasper = "";

 //       logger.debug(" ");
 //       logger.debug(" ");
 //       logger.debug(" ");
 //       logger.debug(" ");
 //       logger.debug("periodoReporte: " + periodoReporte);
 //       logger.debug(" ");
 //       logger.debug(" ");
 //       logger.debug(" ");
 //       logger.debug(" ");

        RespuestaVO respuesta = new RespuestaVO();
        StringBuffer rutaFinalReporte = new StringBuffer();
        ConexionAS400VO conexion = new ConexionAS400VO();

        /* Se preparan los valores para la conexion */
        conexion = HelperAFCCesantia.obtenerDatosConexion();

        /* Se obtiene ruta y nombre precompilado jasper */
        rutaJasper = HelperAFCCesantia.obtenerRutaNombreJRXML(Integer.parseInt(idReporteXls));

        /* Se obtiene nombre de archivo y ruta de carpeta en servidor */
        nombreArchivo = HelperAFCCesantia.retornarNombreArchivo(Integer.parseInt(idReporteXls), periodoReporte);
        rutaCarpeta = HelperAFCCesantia.retornarRutaArchivo(Integer.parseInt(idReporteXls));

        /* Se forma ruta final del reporte */
        rutaFinalReporte.append(rutaCarpeta);
        rutaFinalReporte.append(nombreArchivo);
 //       logger.debug("rutaFinal: " + rutaFinalReporte.toString());

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

    public static RespuestaVO insertIntoDetalleReportes(String idReporteXls, String nombreReporte, String idMaestroSiv, String mesReporte, String usser, String fechaReporte) {

        Date dateProceso = new Date();
        String DATE_FORMAT2 = "dd/MM/yyyy";
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);
        DetalleReporteSivegamVO detalleReporte = new DetalleReporteSivegamVO();
        RespuestaVO resp = null;
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

            resp = GenerarReportesDAO.insertDetalleReporteSivegam(detalleReporte);
            if (resp.getCodRespuesta() != 99) {
                resp.setCodRespuesta(0);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resp;
    }

    /** Funcion que genera el reporte de errores. */
    public static RespuestaVO generarReporteErroresXls(String idFlagReporteXls, String periodo, String idMaestroSiv, String mesReporte, String usser, String fechaReporte,
            String flagReporte) {

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
        nombreArchivo = HelperAFCCesantia.retornarNombreArchivoErrores(Integer.parseInt(idFlagReporteXls), periodo);
        rutaCarpeta = HelperAFCCesantia.retornarRutaArchivoErrores(Integer.parseInt(idFlagReporteXls));

        /* Se forma ruta final del reporte */
        rutaFinalReporte.append(rutaCarpeta);
        rutaFinalReporte.append(nombreArchivo);
  //      logger.debug("rutaFinal: " + rutaFinalReporte.toString());

        /* Se llama a clase ReporDriver para generar reportes */
        ReportDriver.runReport(conexion.getIpServer(), conexion.getUsuarioConexion(), conexion.getClaveConexion(), rutaJasper, rutaFinalReporte.toString(), periodo);

        /* Se inserta evidencia de generacion de archivo en tabla DetalleReporte */
        respuesta = GenerarReportesAFCImpl.insertIntoDetalleReportes(idFlagReporteXls, nombreArchivo, idMaestroSiv, mesReporte, usser, fechaReporte);
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

        nombreArchivo = HelperAFCCesantia.retornarNombreArchivo(tipoReporte, periodoReporte);
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

 //       logger.debug("DESDE obtenerRutaReporteTxt: " + rutacompleta.toString());
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

    public static RespuestaVO actualizarStatusSegunPeriodoYProceso(String tipoProceso, String mesPeriodo) {

        return GenerarReportesAFCDAO.actualizarStatusSegunPeriodoYProceso(tipoProceso, mesPeriodo);
    }

    public static long consultarSivegamTablasAFC(String flagTipoReporte) {

        long idMaestroSivegam = 0;
        int tipoReporte = 0;
        if ("R".equals(flagTipoReporte)) {
            tipoReporte = 8;
        } else {
            tipoReporte = 9;
        }

        switch (tipoReporte) {
        case 8:
            idMaestroSivegam = GenerarReportesAFCDAO.obtenerMaestroSivegamAFCRetro(flagTipoReporte);
            break;
        case 9:
            idMaestroSivegam = GenerarReportesAFCDAO.obtenerMaestroSivegamAFCMensual(flagTipoReporte);
            break;
        default:
            tipoReporte = 0;
        }

        return idMaestroSivegam;
    }

    public static RespuestaVO statusProcesoCarga(String periodo, String tipoReporte) {
        return GenerarReportesAFCDAO.statusProcesoCarga(periodo, tipoReporte);

    }
}
