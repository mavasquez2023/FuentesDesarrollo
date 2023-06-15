package cl.araucana.sivegam.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import cl.araucana.sivegam.conexion.cobol.bo.ParametrosConexionBO;
import cl.araucana.sivegam.conexion.cobol.bo.ParametrosLlamadaBO;
import cl.araucana.sivegam.config.ConsumidorCobol;
import cl.araucana.sivegam.dao.GenerarReportesDAO;
import cl.araucana.sivegam.dao.ParametrosDAO;
import cl.araucana.sivegam.helper.GlobalProperties;
import cl.araucana.sivegam.helper.Helper;
import cl.araucana.sivegam.helper.ReportDriver;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.vo.DetalleReporteSivegamVO;
import cl.araucana.sivegam.vo.FormatoReporteVO;
import cl.araucana.sivegam.vo.GenerarReportesVO;
import cl.araucana.sivegam.vo.MaestroSivegamVO;
import cl.araucana.sivegam.vo.PeriodoProcesoVO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.Sif018VO;
import cl.araucana.sivegam.vo.StatusProcesoVO;
import cl.araucana.sivegam.vo.TipoProcesosVO;
import cl.araucana.sivegam.vo.ValoresConexionAS400VO;
import cl.araucana.sivegam.vo.param.ListadoParametros;

public class GenerarReportesImpl {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();
    static GlobalProperties global = GlobalProperties.getInstance();

    /**
     * Funcion que realiza la inserción de los datos a la tabla maestrosivegam.
     * Para esto invoca a la funcion que inserta los datos, la cual esta en la
     * capa DAO.
     */
    public static GenerarReportesVO insertarMaestroSivegam(String fechaProceso, String usuarioSivegam, String idTipoReporte, String periodoMes) {

        return GenerarReportesDAO.insertarMaestroSivegam(fechaProceso, usuarioSivegam, idTipoReporte, periodoMes);
    }

    /**
     * Funcion que llama al proceso cobol, en este caso se encarga de llamar al
     * cl indicado por el idReporte
     */
    public static RespuestaVO llamarProcesoCobol(String idSecuencia, String idReporte, String periodo, String asfam) {

        /*
         * Retornos de la funcion: 0: si el proceso fue realizado con exito. 99:
         * si el proceso no fue realizado.
         */
        logger.debug("INI     : llamarProcesoCobol id [" + idReporte + "]");

        /*
         * PROCESO ASFAM
         * 
         * verifica asfam 1 = ejecutar 0 = pasar llamar a cobolSIP05ASFAM
         * 
         */
        RespuestaVO resp = new RespuestaVO();
        if ("1".equalsIgnoreCase(asfam)) {
            RespuestaVO respAsfam = new RespuestaVO();
            if ("1".equalsIgnoreCase(idReporte)) {
                respAsfam = actualizarStatusSegunPeriodoYProcesoDivPrev("3", periodo);
            }
            if ("3".equalsIgnoreCase(idReporte)) {
                respAsfam = actualizarStatusSegunPeriodoYProcesoDivPrev("1", periodo);
            }

            if (respAsfam.getStatus() == 1 || respAsfam.getStatus() == 0) {
                resp = cobolSIP05ASFAM(periodo, idReporte);
            } else {
                while (respAsfam.getStatus() == 4) {
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        if ("1".equalsIgnoreCase(idReporte)) {
                            respAsfam = actualizarStatusSegunPeriodoYProcesoDivPrev("3", periodo);
                        }
                        if ("3".equalsIgnoreCase(idReporte)) {
                            respAsfam = actualizarStatusSegunPeriodoYProcesoDivPrev("1", periodo);
                        }
                    }
                }

                resp.setCodRespuesta(3);
            }
        } else {
            resp.setCodRespuesta(3);
        }

        //if (resp.getCodRespuesta() == 3) {

            boolean actualiza = false;

            /*
             * SECCION DONDE SE DEBEN SETEAR LOS VALORES LUEGO DE REALIZAR EL
             * LLAMADO PARAMETRICO
             */
            String ipServer = "";
            String usuarioConexion = "";
            String claveConexion = "";

            /* parametrizacion de data de conexion. */
            ListadoParametros listaParam1 = ListadoParametros.getInstancia();
            ValoresConexionAS400VO[] valoresConexion = listaParam1.getListValoresConexionToAS400();

            for (int i = 0; i < valoresConexion.length; i++) {

                if (valoresConexion[i].getId_conexion_as400() == 1) {
                    ipServer = valoresConexion[i].getDescripcion_conexion_as400();
                }

                if (valoresConexion[i].getId_conexion_as400() == 2) {
                    usuarioConexion = valoresConexion[i].getDescripcion_conexion_as400();
                }

                if (valoresConexion[i].getId_conexion_as400() == 3) {
                    claveConexion = valoresConexion[i].getDescripcion_conexion_as400();
                }
            }

            /* parametrizacion de la ruta del programa a invocar. */
            String programa = "";
            ListadoParametros listaParam2 = ListadoParametros.getInstancia();
            TipoProcesosVO[] tipoProcesos = listaParam2.getListTipoProcesos();

            for (int j = 0; j < tipoProcesos.length; j++) {
                if (tipoProcesos[j].getId_tipo_proceso() == Integer.parseInt(idReporte)) {
                    programa = tipoProcesos[j].getDesc_estruct_carpetas_as400();
                    break;
                }
            }
            
            //JLGN
            //String programa2 = "/QSYS.LIB/SVOBJD.LIB/SIVBATCH.PGM"; //--> desarrollo
            String programa2 = "/QSYS.LIB/SVOBJ.LIB/SIVBATCH.PGM"; //--> QA - PRO
            String job = "SVJOB"+ idReporte;

            String idMaestroSivegam = idSecuencia;

            /*
             * SECCION DONDE SE SETEAN LOS PARAMETROS DE LLAMADA PARA LA
             * INVOCACION DEL PROCESO COBOL
             */
            ParametrosConexionBO paramConexion = new ParametrosConexionBO();
            
            //JLGN
            //ParametrosLlamadaBO[] paramLlamada = new ParametrosLlamadaBO[4];
            ParametrosLlamadaBO[] paramLlamada = new ParametrosLlamadaBO[7];

            String idMaestroSivegamTemp = Helper.paddingString(idMaestroSivegam, 12, '0', true);
            String spaces = " ";
            String parametroRelleno3 = Helper.paddingString(spaces, 12, ' ', true);
            String parametroRelleno4 = Helper.paddingString(spaces, 2, ' ', true);

            logger.debug("SET     : llamarProcesoCobol id [" + idReporte + "] - seteando parametro de entrada...");

            /* Seteo de los parametros de conexion. */
            paramConexion.setIpServer(ipServer);
            paramConexion.setUsuarioConexion(usuarioConexion);
            paramConexion.setClaveConexion(claveConexion);
            //JLGN
            //paramConexion.setPrograma(programa);
            paramConexion.setPrograma(programa2);

            /*
             * Seteo de los parametros de entrada para el proceso COBOL,
             * mediante paramLlamada.
             */
            ParametrosLlamadaBO parametro1 = new ParametrosLlamadaBO();
            ParametrosLlamadaBO parametro2 = new ParametrosLlamadaBO();
            ParametrosLlamadaBO parametro3 = new ParametrosLlamadaBO();
            ParametrosLlamadaBO parametro4 = new ParametrosLlamadaBO();
            
            //JLGN
            ParametrosLlamadaBO parametro5 = new ParametrosLlamadaBO();
            ParametrosLlamadaBO parametro6 = new ParametrosLlamadaBO();
            ParametrosLlamadaBO parametro7 = new ParametrosLlamadaBO();

            /*JLGN
             * parametro1.setTipo("STRING");
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
            parametro3.setLargo(parametroRelleno3.length());
            parametro3.setValor(parametroRelleno3);
            parametro3.setDireccion("IN");
            paramLlamada[2] = parametro3;

            parametro4.setTipo("STRING");
            parametro4.setLargo(parametroRelleno4.length());
            parametro4.setValor(parametroRelleno4);
            parametro4.setDireccion("OUT");
            paramLlamada[3] = parametro4;*/
            
            parametro1.setTipo("STRING");
            parametro1.setLargo(21);
            parametro1.setValor(programa); //PROGRAMA
            parametro1.setDireccion("IN");
            paramLlamada[0] = parametro1;

            parametro2.setTipo("STRING");
            parametro2.setLargo(12);
            parametro2.setValor(idMaestroSivegamTemp);//PARAMETRO 1
            parametro2.setDireccion("IN");
            paramLlamada[1] = parametro2;

            parametro3.setTipo("STRING");
            parametro3.setLargo(6);
            parametro3.setValor(periodo);//PARAMETRO 2
            parametro3.setDireccion("IN");
            paramLlamada[2] = parametro3;

            parametro4.setTipo("STRING");
            parametro4.setLargo(12);
            parametro4.setValor(parametroRelleno3);//PARAMETRO 3
            parametro4.setDireccion("IN");
            paramLlamada[3] = parametro4;
            
            parametro5.setTipo("STRING");
            parametro5.setLargo(2);
            parametro5.setValor(parametroRelleno4);//PARAMETRO 4
            parametro5.setDireccion("IN");
            paramLlamada[4] = parametro5;
            
            parametro6.setTipo("STRING");
            parametro6.setLargo(10);
            parametro6.setValor(job);//JOB
            parametro6.setDireccion("IN");
            paramLlamada[5] = parametro6;
            
            parametro7.setTipo("STRING");
            parametro7.setLargo(10);
            parametro7.setValor("*JOBD");//COLA
            parametro7.setDireccion("IN");
            paramLlamada[6] = parametro7;


            for (int i = 0; i < paramLlamada.length; i++) {
                logger.debug("SET    : llamarProcesoCobol id [" + idReporte + "] - LARGO PARAMETRO: " + paramLlamada[i].getLargo());
                logger.debug("SET    : llamarProcesoCobol id [" + idReporte + "] - paramLlamada: " + paramLlamada[i].getValor());
            }

            try {

                logger.debug("CALL    : llamarProcesoCobol id [" + idReporte + "] - LLAMANDO A CONSUMIDOR COBOL...");
                ParametrosLlamadaBO[] salida = ConsumidorCobol.call(paramConexion, paramLlamada);

                //JLGN
                //String control = "";
                String control = "04";
                
                String statusActual = "";

                logger.debug("CALL    : llamarProcesoCobol id [" + idReporte + "] - LISTO PARA SETEAR RESP... PROCESO FUE INVOCADO.");

                //JLGN
                //control = (String) salida[3].getValor();

                //logger.debug("RESULT  : llamarProcesoCobol id [" + idReporte + "] - variable control = " + control);

                /** Modificado para asociar reportes sin data. */
                if ("05".equals(control)) {

                    ListadoParametros paramStatus = ListadoParametros.getInstancia();
                    StatusProcesoVO[] statusProceso = paramStatus.getListStatusProceso();

                    for (int p = 0; p < statusProceso.length; p++) {
                        if (statusProceso[p].getStatus_proceso() == 5) {
                            statusActual = statusProceso[p].getDescripcion_status_proceso();
                            break;
                        }
                    }
                    resp.setCodRespuesta(5);
                    resp.setMsgRespuesta(statusActual);
                    logger.debug("RESULT  : llamarProcesoCobol id [" + idReporte + "] - mensaje retornado: " + resp.getMsgRespuesta());
                    logger.debug("RESULT  : llamarProcesoCobol id [" + idReporte + "] - codigo retornado: " + resp.getCodRespuesta());
                    return resp;
                }
                //Inicio JLGN
                if ("04".equals(control)) {

                    ListadoParametros paramStatus = ListadoParametros.getInstancia();
                    StatusProcesoVO[] statusProceso = paramStatus.getListStatusProceso();

                    for (int p = 0; p < statusProceso.length; p++) {
                        if (statusProceso[p].getStatus_proceso() == 4) {
                            statusActual = statusProceso[p].getDescripcion_status_proceso();
                            break;
                        }
                    }
                    resp.setCodRespuesta(4);
                    resp.setMsgRespuesta(statusActual);
                    logger.debug("RESULT  : llamarProcesoCobol id [" + idReporte + "] - mensaje retornado: " + resp.getMsgRespuesta());
                    logger.debug("RESULT  : llamarProcesoCobol id [" + idReporte + "] - codigo retornado: " + resp.getCodRespuesta());
                    return resp;

                }
                //Fin JLGN
                if ("03".equals(control)) {

                    ListadoParametros paramStatus = ListadoParametros.getInstancia();
                    StatusProcesoVO[] statusProceso = paramStatus.getListStatusProceso();

                    for (int p = 0; p < statusProceso.length; p++) {
                        if (statusProceso[p].getStatus_proceso() == 3) {
                            statusActual = statusProceso[p].getDescripcion_status_proceso();
                            break;
                        }
                    }
                    resp.setCodRespuesta(3);
                    resp.setMsgRespuesta(statusActual);
                    logger.debug("RESULT  : llamarProcesoCobol id [" + idReporte + "] - mensaje retornado: " + resp.getMsgRespuesta());
                    logger.debug("RESULT  : llamarProcesoCobol id [" + idReporte + "] - codigo retornado: " + resp.getCodRespuesta());
                    return resp;

                }else if ("02".equals(control)) {
                    resp.setCodRespuesta(2);
                    resp.setMsgRespuesta("2");
                    return resp;
                }

                if ("07".equals(control)) {
                    logger.debug("RESULT  : llamarProcesoCobol id [" + idReporte + "] - mensaje retornado: ERROR ARCHIVO TOMADO");
                    logger.debug("RESULT  : llamarProcesoCobol id [" + idReporte + "] - codigo  retornado: " + control);
                    resp.setCodRespuesta(2);
                    resp.setMsgRespuesta("El Proceso de validacion a fallado");
                    actualiza = true;

                } else if ("08".equals(control)) {
                    logger.debug("RESULT  : llamarProcesoCobol id [" + idReporte + "] - mensaje retornado: ERROR CANCELACION COBOL");
                    logger.debug("RESULT  : llamarProcesoCobol id [" + idReporte + "] - codigo  retornado: " + control);
                    resp.setCodRespuesta(2);
                    resp.setMsgRespuesta("El Proceso de validacion a fallado");
                    actualiza = true;

                }

                if (actualiza) {
                    String fechaProceso = ParametrosDAO.obtenerFechaSistema();
                    updateStatusProcesoSivegam("2", idMaestroSivegam, fechaProceso);
                }
            } catch (Exception e) {
                resp.setCodRespuesta(2);
                resp.setMsgRespuesta("KO");
                e.printStackTrace();
            }
        //} else {
        //    resp.setCodRespuesta(1);
        //    resp.setMsgRespuesta("El Proceso de CARGA ASFAM A FALLADO");

        //}
        return resp;
    }

    /* Funcion que */
    public static RespuestaVO generarReporteXLS(String idFlagReporteXls, String periodo, String idMaestroSiv, String mesReporte, String usser, String fechaReporte) {

        RespuestaVO resp = new RespuestaVO();
        /*
         * Estos valores se deben eliminar una vez invocado los valores
         * parametricos.
         */

        String databaseName = "";
        String userName = "";
        String password = "";
        String reportFile = "";
        String nombreArchivoEnXls = "";
        String cabeceraArchivo1 = "";
        String cabeceraArchivo2 = "";
        String formatoArchivo = "";
        String punto = ".";
        /*
         * if(periodo.length() == 1){
         * 
         * periodo = "0" + periodo; }
         */

        ListadoParametros listaParamConexion = ListadoParametros.getInstancia();
        ValoresConexionAS400VO[] valoresConexion = listaParamConexion.getListValoresConexionToAS400();

        for (int i = 0; i < valoresConexion.length; i++) {

            if (valoresConexion[i].getId_conexion_as400() == 1) {
                databaseName = valoresConexion[i].getDescripcion_conexion_as400();
            }

            if (valoresConexion[i].getId_conexion_as400() == 2) {
                userName = valoresConexion[i].getDescripcion_conexion_as400();
            }

            if (valoresConexion[i].getId_conexion_as400() == 3) {
                password = valoresConexion[i].getDescripcion_conexion_as400();
            }
        }

        ListadoParametros listaParamProcesos = ListadoParametros.getInstancia();
        TipoProcesosVO[] tipoProcesosReport = listaParamProcesos.getListTipoProcesos();

        for (int j = 0; j < tipoProcesosReport.length; j++) {

            if (tipoProcesosReport[j].getId_tipo_proceso() == Integer.parseInt(idFlagReporteXls)) {

                reportFile = tipoProcesosReport[j].getDesc_estruct_carpetas_reporte();
                break;
            }
        }

        /** Parametrizacion de los nombres de los reportes */
        ListadoParametros listaParamNombreArchivo = ListadoParametros.getInstancia();
        TipoProcesosVO[] tipoProcesoNombreArchivo = listaParamNombreArchivo.getListTipoProcesos();

        for (int k = 0; k < tipoProcesoNombreArchivo.length; k++) {
            if (tipoProcesoNombreArchivo[k].getId_tipo_proceso() == Integer.parseInt(idFlagReporteXls)) {

                nombreArchivoEnXls = tipoProcesoNombreArchivo[k].getDesc_nombre_archivo_xls();
                break;
            }
        }

        /** Obtiene el formato del reporte */
        ListadoParametros listaParamFormat = ListadoParametros.getInstancia();
        FormatoReporteVO[] formatoReporte = listaParamFormat.getListFormatoReportes();

        for (int f = 0; f < formatoReporte.length; f++) {
            if (formatoReporte[f].getFormato_reporte() == 1) {
                formatoArchivo = formatoReporte[f].getDescripcion_formato_reporte();
                break;
            }
        }

        File f = new File("");
        StringBuffer pathReportFinally = new StringBuffer("");
        StringBuffer nombreArchivo = new StringBuffer("");

        if (Integer.parseInt(idFlagReporteXls) >= 14) {
            StringTokenizer st = new StringTokenizer(nombreArchivoEnXls, "#/");
            while (st.hasMoreTokens()) {
                cabeceraArchivo1 = st.nextToken();
                cabeceraArchivo2 = st.nextToken();
            }

            nombreArchivo.append(cabeceraArchivo1);
            nombreArchivo.append(periodo);
            nombreArchivo.append(cabeceraArchivo2);
            nombreArchivo.append(punto);
            nombreArchivo.append(formatoArchivo);

        } else {
            StringTokenizer st = new StringTokenizer(nombreArchivoEnXls, "#");
            while (st.hasMoreTokens()) {
                cabeceraArchivo1 = st.nextToken();
            }

            nombreArchivo.append(cabeceraArchivo1);
            nombreArchivo.append(periodo);
            nombreArchivo.append(punto);
            nombreArchivo.append(formatoArchivo);
        }

        String nombreFormateado = nombreArchivo.toString();

        String ruta = GenerarReportesImpl.retornarRutaCarpetasXLS(Integer.parseInt(idFlagReporteXls));
        pathReportFinally.append(f.getAbsolutePath());
        pathReportFinally.append(ruta);
        pathReportFinally.append(nombreFormateado);

        ReportDriver.runReport(databaseName, userName, password, reportFile, pathReportFinally.toString(), periodo);

        String statusActual = "";
        ListadoParametros paramStatus = ListadoParametros.getInstancia();
        StatusProcesoVO[] statusProceso = paramStatus.getListStatusProceso();

        for (int p = 0; p < statusProceso.length; p++) {
            if (statusProceso[p].getStatus_proceso() == 3) {
                statusActual = statusProceso[p].getDescripcion_status_proceso();
                break;
            }
        }

        /**
         * Seteo de valores y llamada a funcion que inserta en la tabla detalle
         * reportes sivegam - modificado por FRM
         */
        DetalleReporteSivegamVO detalleReporte = new DetalleReporteSivegamVO();

        String fecha = "";
        Date dateProceso = new Date();
        String DATE_FORMAT2 = "dd/MM/yyyy";
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        try {
            detalleReporte.setMaestro_sivegam(Long.parseLong(idMaestroSiv));
            detalleReporte.setNombre_reporte(nombreFormateado);

            fecha = fechaReporte;
            dateProceso = sdf2.parse(fecha);

            detalleReporte.setFechaReporteDate(dateProceso);
            detalleReporte.setStatus_proceso(3);
            detalleReporte.setTipo_proceso(Integer.parseInt(idFlagReporteXls));
            detalleReporte.setPeriodo_proceso(Integer.parseInt(mesReporte));
            detalleReporte.setFomato_reporte(1);
            detalleReporte.setUsuario_sivegam(Long.parseLong(usser));

            resp = GenerarReportesDAO.insertDetalleReporteSivegam(detalleReporte);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        /**
         * Fin seteo para insercion en tabla detalle reporte y llamada a funcion
         * que realiza insercion.
         */
        if (resp.getCodRespuesta() != 99) {
            resp.setCodRespuesta(0);
            resp.setMsgRespuesta(statusActual);
            resp.setRutaArchivo(pathReportFinally.toString());
        }
        return resp;

    }

    public static Sif018VO obtenerStatus(String periodo, String tipoProceso) {

        return GenerarReportesDAO.obtenerStatus(periodo, tipoProceso);
    }

    /**
     * Funcion que obtiene el maximo id de la tabla maestro sivegam cuando no se
     * requiere reprocesar para generar los reportes en xls o en txt. recibe
     * como parametros el periodo del procesamiento y el id del tipo de proceso.
     * retorna como resultado el maximo idmaestrosivegam.
     */
    public static MaestroSivegamVO selectMaxIdMaestroSivegam(String idReporte, String periodoReporte) {

        return GenerarReportesDAO.selectMaxIdMaestroSivegam(idReporte, periodoReporte);
    }

    /**
     * Funcion que obtiene el idtipoarchivo dado el idtipoproceso...
     * homologacion para obtener el idmax de sivegam y poder insertar en detalle
     * reportes.
     */
    public static TipoProcesosVO selectTipoArchivoHomologado(String idTipoProceso) {

        return GenerarReportesDAO.selectTipoArchivoHomologado(idTipoProceso);
    }

    public static MaestroSivegamVO obtenerInformacionAActualizar(String idSivegam) {

        return GenerarReportesDAO.obtenerInformacionAActualizar(idSivegam);
    }

    /**
     * Funcion que retorna glosa de periodo y status de la tabla maestrosivegam,
     * por medio de sus respectivas claves.
     */
    public static MaestroSivegamVO buscarGlosaStatusYGlosaMes(String idPeriodo, String idStatus) {

        String glosaPeriodoProcesoMes = "";
        String glosaStatusProceso = "";
        MaestroSivegamVO msvo = new MaestroSivegamVO();

        if ("0".equals(idPeriodo)) {

            glosaPeriodoProcesoMes = "SIN PERIODO";

        } else {
            ListadoParametros listaParamMes = ListadoParametros.getInstancia();
            PeriodoProcesoVO[] periodoProceso = listaParamMes.getListPeriodoProcesos();

            for (int i = 0; i < periodoProceso.length; i++) {
                if (periodoProceso[i].getPeriodo_proceso() == Integer.parseInt(idPeriodo)) {
                    glosaPeriodoProcesoMes = periodoProceso[i].getDescripcion_periodo_proceso();
                    break;
                }
            }
        }

        ListadoParametros listaParamStatus = ListadoParametros.getInstancia();
        StatusProcesoVO[] statusProceso = listaParamStatus.getListStatusProceso();

        for (int i = 0; i < statusProceso.length; i++) {
            if (statusProceso[i].getStatus_proceso() == Integer.parseInt(idStatus)) {
                glosaStatusProceso = statusProceso[i].getDescripcion_status_proceso();
                break;
            }
        }
        
        System.out.println("-----------------------------------------------------------------");
        System.out.println("MaestroSivegamVO buscarGlosaStatusYGlosaMes :");
        System.out.println("glosaPeriodoProcesoMes :" + glosaPeriodoProcesoMes);
        System.out.println("glosaStatusProceso :" + glosaStatusProceso);
        System.out.println("-----------------------------------------------------------------");

        msvo.setGlosaPeriodoProcesoMes(glosaPeriodoProcesoMes);
        msvo.setGlosaStatusProceso(glosaStatusProceso);

        return msvo;

    }

    public static RespuestaVO updateStatusProcesoSivegam(String idRespuestaCobol, String idMaestroSivegam, String fechaProceso) {

        return GenerarReportesDAO.updateStatusProcesoSivegam(idRespuestaCobol, idMaestroSivegam, fechaProceso);
    }

    public static RespuestaVO updateStatus12PorReproceso112(String idMaestroSivegam, String fechaProceso) {

        return GenerarReportesDAO.updateStatus12PorReproceso112(idMaestroSivegam, fechaProceso);
    }

    public static RespuestaVO actualizarStatusSegunPeriodoYProceso(String tipoReporte, String mesPeriodo) {

        return GenerarReportesDAO.actualizarStatusSegunPeriodoYProceso(tipoReporte, mesPeriodo);
    }

    /**
     * Retorna la ruta de las carpetas del servidor. sirve para saber donde se
     * van a generar los reportes.
     */
    public static String retornarRutaCarpetasXLS(int tipoReporte) {

        String ruta = "";
        ListadoParametros param = ListadoParametros.getInstancia();
        TipoProcesosVO[] tipoProcesos = param.getListTipoProcesos();

        for (int i = 0; i < tipoProcesos.length; i++) {
            if (tipoProcesos[i].getId_tipo_proceso() == tipoReporte) {
                ruta = tipoProcesos[i].getDesc_carpeta_xls();
                break;
            }
        }

        return ruta;
    }

    public static RespuestaVO actualizarStatusSegunPeriodoYProcesoDivPrev(String tipoProceso, String mesPeriodo) {

        return GenerarReportesDAO.actualizarStatusSegunPeriodoYProcesoDivPrev(tipoProceso, mesPeriodo);
    }

    /** Funcion que realiza update a status antes de reprocesar. */
    public static RespuestaVO updateStatusAntesDeReprocesar(long id, int status) {

        return GenerarReportesDAO.updateStatusAntesDeReprocesar(id, status);
    }

    /*
     * Retornos de la funcion: 0: si el proceso fue realizado con exito. 99: si
     * el proceso no fue realizado.
     */
    public static RespuestaVO cobolSIP05ASFAM(String periodo, String id) {

        logger.debug("INI     : cobolSIP05ASFAM id [" + id + "]");
        RespuestaVO resp = new RespuestaVO();

        String ipServer = "";
        String usuarioConexion = "";
        String claveConexion = "";

        ListadoParametros listaParam1 = ListadoParametros.getInstancia();
        ValoresConexionAS400VO[] valoresConexion = listaParam1.getListValoresConexionToAS400();

        for (int i = 0; i < valoresConexion.length; i++) {

            if (valoresConexion[i].getId_conexion_as400() == 1) {
                ipServer = valoresConexion[i].getDescripcion_conexion_as400();
            }

            if (valoresConexion[i].getId_conexion_as400() == 2) {
                usuarioConexion = valoresConexion[i].getDescripcion_conexion_as400();
            }

            if (valoresConexion[i].getId_conexion_as400() == 3) {
                claveConexion = valoresConexion[i].getDescripcion_conexion_as400();
            }
        }

        /* parametrizacion de la ruta del programa a invocar. */
        String ambiente = global.getValor("SVG.properties.ambiente");
        String programa = "";
        if ("desa".equals(ambiente)) {
            programa = global.getValor("SVG.cl.asfam.desa");
        } else {
            programa = global.getValor("SVG.cl.asfam.pro");
        }

        ParametrosConexionBO paramConexion = new ParametrosConexionBO();
        ParametrosLlamadaBO[] paramLlamada = new ParametrosLlamadaBO[2];
        String spaces = " ";
        String parametroRelleno2 = Helper.paddingString(spaces, 2, ' ', true);

        logger.debug("SET     : cobolSIP05ASFAM id [" + id + "] - seteando parametro de entrada...");

        /* Seteo de los parametros de conexion. */
        paramConexion.setIpServer(ipServer);
        paramConexion.setUsuarioConexion(usuarioConexion);
        paramConexion.setClaveConexion(claveConexion);
        paramConexion.setPrograma(programa);

        /*
         * Seteo de los parametros de entrada para el proceso COBOL, mediante
         * paramLlamada.
         */
        ParametrosLlamadaBO parametro1 = new ParametrosLlamadaBO();
        ParametrosLlamadaBO parametro2 = new ParametrosLlamadaBO();

        parametro1.setTipo("STRING");
        parametro1.setLargo(periodo.length());
        parametro1.setValor(periodo);
        parametro1.setDireccion("IN");
        paramLlamada[0] = parametro1;

        parametro2.setTipo("STRING");
        parametro2.setLargo(parametroRelleno2.length());
        parametro2.setValor(parametroRelleno2);
        parametro2.setDireccion("OUT");
        paramLlamada[1] = parametro2;

        for (int i = 0; i < paramLlamada.length; i++) {
            logger.debug("SET     : cobolSIP05ASFAM id [" + id + "] - LARGO PARAMETRO: " + paramLlamada[i].getLargo());
            logger.debug("SET     : cobolSIP05ASFAM id [" + id + "] - paramLlamada: " + paramLlamada[i].getValor());
        }

        try {

            logger.debug("CALL    : cobolSIP05ASFAM id [" + id + "] - LLAMANDO A CONSUMIDOR COBOL...");
            ParametrosLlamadaBO[] salida = ConsumidorCobol.call(paramConexion, paramLlamada);

            String control = "";
            String statusActual = "";

            logger.debug("CALL    : cobolSIP05ASFAM id [" + id + "] - LISTO PARA SETEAR RESP... PROCESO FUE INVOCADO.");

            control = (String) salida[1].getValor();

            logger.debug("RESULT  : cobolSIP05ASFAM id [" + id + "] - variable control = [" + control + "]");

            /** Modificado para asociar reportes sin data. */
            if ("05".equals(control)) {

                ListadoParametros paramStatus = ListadoParametros.getInstancia();
                StatusProcesoVO[] statusProceso = paramStatus.getListStatusProceso();

                for (int p = 0; p < statusProceso.length; p++) {
                    if (statusProceso[p].getStatus_proceso() == 5) {
                        statusActual = statusProceso[p].getDescripcion_status_proceso();
                        break;
                    }
                }
                resp.setCodRespuesta(5);
                resp.setMsgRespuesta(statusActual);

                logger.debug("RESULT  : cobolSIP05ASFAM id [" + id + "] - mensaje retornado: [" + resp.getMsgRespuesta() + "]");
                logger.debug("RESULT  : cobolSIP05ASFAM id [" + id + "] - codigo retornado:  [" + resp.getCodRespuesta() + "]");
                return resp;
            }

            if ("03".equals(control)) {

                ListadoParametros paramStatus = ListadoParametros.getInstancia();
                StatusProcesoVO[] statusProceso = paramStatus.getListStatusProceso();

                for (int p = 0; p < statusProceso.length; p++) {
                    if (statusProceso[p].getStatus_proceso() == 3) {
                        statusActual = statusProceso[p].getDescripcion_status_proceso();
                        break;
                    }
                }
                resp.setCodRespuesta(3);
                resp.setMsgRespuesta(statusActual);

                logger.debug("RESULT  : cobolSIP05ASFAM id [" + id + "] - mensaje retornado: [" + resp.getMsgRespuesta() + "]");
                logger.debug("RESULT  : cobolSIP05ASFAM id [" + id + "] - codigo retornado:  [" + resp.getCodRespuesta() + "]");
                return resp;

            } else if ("02".equals(control)) {
                resp.setCodRespuesta(2);
                resp.setMsgRespuesta("2");
                return resp;
            }

            if ("07".equals(control)) {
                logger.debug("RESULT  : cobolSIP05ASFAM id [" + id + "] - mensaje retornado: ERROR ARCHIVO TOMADO");
                logger.debug("RESULT  : cobolSIP05ASFAM id [" + id + "] - codigo  retornado: " + control);
                resp.setCodRespuesta(2);
                resp.setMsgRespuesta("El Proceso de validacion a fallado");
            } else if ("08".equals(control)) {
                logger.debug("RESULT  : cobolSIP05ASFAM id [" + id + "] - mensaje retornado: ERROR ARCHIVO TOMADO");
                logger.debug("RESULT  : cobolSIP05ASFAM id [" + id + "] - codigo  retornado: " + control);
                resp.setCodRespuesta(2);
                resp.setMsgRespuesta("El Proceso de validacion a fallado");
            }

        } catch (Exception e) {
            resp.setCodRespuesta(2);
            resp.setMsgRespuesta("KO");
            e.printStackTrace();
        }

        logger.debug("FIN     : cobolSIP05ASFAM id [" + id + "]");

        return resp;
    }

    public static RespuestaVO llamarProcesoCobolCLVAL(String idReport, String periodo) {

        logger.debug("INI     : llamarProcesoCobolCLVAL id [" + idReport + "]");
        RespuestaVO resp = new RespuestaVO();

        String ipServer = "";
        String usuarioConexion = "";
        String claveConexion = "";
        int iArch = Integer.parseInt(idReport);
        ListadoParametros listaParam1 = ListadoParametros.getInstancia();
        ValoresConexionAS400VO[] valoresConexion = listaParam1.getListValoresConexionToAS400();

        for (int i = 0; i < valoresConexion.length; i++) {

            if (valoresConexion[i].getId_conexion_as400() == 1) {
                ipServer = valoresConexion[i].getDescripcion_conexion_as400();
            }

            if (valoresConexion[i].getId_conexion_as400() == 2) {
                usuarioConexion = valoresConexion[i].getDescripcion_conexion_as400();
            }

            if (valoresConexion[i].getId_conexion_as400() == 3) {
                claveConexion = valoresConexion[i].getDescripcion_conexion_as400();
            }
        }

        /* parametrizacion de la ruta del programa a invocar. */
        TipoProcesosVO[] valoresPrograma = listaParam1.getListTipoProcesos();
        String programa = "";
        for (int i = 0; i < valoresPrograma.length; i++) {
            if (valoresPrograma[i].getId_tipo_archivo() == iArch) {

                programa = valoresPrograma[i].getDesc_estruct_carpetas_as400();

            }
        }

        ParametrosConexionBO paramConexion = new ParametrosConexionBO();
        ParametrosLlamadaBO[] paramLlamada = new ParametrosLlamadaBO[2];
        String spaces = " ";
        String parametroRelleno2 = Helper.paddingString(spaces, 2, ' ', true);

        logger.debug("SET     : llamarProcesoCobolCLVAL id [" + idReport + "] - seteando parametro de entrada...");

        /* Seteo de los parametros de conexion. */
        paramConexion.setIpServer(ipServer);
        paramConexion.setUsuarioConexion(usuarioConexion);
        paramConexion.setClaveConexion(claveConexion);
        paramConexion.setPrograma(programa);

        /*
         * Seteo de los parametros de entrada para el proceso COBOL, mediante
         * paramLlamada.
         */
        ParametrosLlamadaBO parametro1 = new ParametrosLlamadaBO();
        ParametrosLlamadaBO parametro2 = new ParametrosLlamadaBO();

        parametro1.setTipo("STRING");
        parametro1.setLargo(periodo.length());
        parametro1.setValor(periodo);
        parametro1.setDireccion("IN");
        paramLlamada[0] = parametro1;

        parametro2.setTipo("STRING");
        parametro2.setLargo(parametroRelleno2.length());
        parametro2.setValor(parametroRelleno2);
        parametro2.setDireccion("OUT");
        paramLlamada[1] = parametro2;

        for (int i = 0; i < paramLlamada.length; i++) {
            logger.debug("SET     : llamarProcesoCobolCLVAL id [" + idReport + "] - LARGO PARAMETRO: " + paramLlamada[i].getLargo());
            logger.debug("SET     : llamarProcesoCobolCLVAL id [" + idReport + "] - paramLlamada: " + paramLlamada[i].getValor());
        }

        try {

            logger.debug("CALL    : llamarProcesoCobolCLVAL id [" + idReport + "] - LLAMANDO A CONSUMIDOR COBOL...");
            ParametrosLlamadaBO[] salida = ConsumidorCobol.call(paramConexion, paramLlamada);

            String control = "";
            String statusActual = "";

            logger.debug("RESULT  : llamarProcesoCobolCLVAL id [" + idReport + "] - LISTO PARA SETEAR RESP... PROCESO FUE INVOCADO.");

            control = (String) salida[1].getValor();

            logger.debug("RESULT  : llamarProcesoCobolCLVAL id [" + idReport + "] - variable control = " + control);

            /** Modificado para asociar reportes sin data. */
            if ("05".equals(control)) {

                ListadoParametros paramStatus = ListadoParametros.getInstancia();
                StatusProcesoVO[] statusProceso = paramStatus.getListStatusProceso();

                for (int p = 0; p < statusProceso.length; p++) {
                    if (statusProceso[p].getStatus_proceso() == 5) {
                        statusActual = statusProceso[p].getDescripcion_status_proceso();
                        break;
                    }
                }
                resp.setCodRespuesta(5);
                resp.setMsgRespuesta(statusActual);

                logger.debug("RESULT  : llamarProcesoCobolCLVAL id [" + idReport + "] - mensaje retornado: " + resp.getMsgRespuesta());
                logger.debug("RESULT  : llamarProcesoCobolCLVAL id [" + idReport + "] - codigo  retornado: " + resp.getCodRespuesta());
                return resp;
            }

            if ("03".equals(control)) {

                ListadoParametros paramStatus = ListadoParametros.getInstancia();
                StatusProcesoVO[] statusProceso = paramStatus.getListStatusProceso();

                for (int p = 0; p < statusProceso.length; p++) {
                    if (statusProceso[p].getStatus_proceso() == 3) {
                        statusActual = statusProceso[p].getDescripcion_status_proceso();
                        break;
                    }
                }
                resp.setCodRespuesta(3);
                resp.setMsgRespuesta(statusActual);

                logger.debug("RESULT  : llamarProcesoCobolCLVAL id [" + idReport + "] - mensaje retornado: " + resp.getMsgRespuesta());
                logger.debug("RESULT  : llamarProcesoCobolCLVAL id [" + idReport + "] - codigo  retornado: " + resp.getCodRespuesta());
                return resp;

            } else if ("02".equals(control)) {
                resp.setCodRespuesta(2);
                resp.setMsgRespuesta("El Proceso de validacion a fallado");
                return resp;
            }

            if ("07".equals(control)) {
                logger.debug("RESULT  : llamarProcesoCobolCLVAL id [" + idReport + "] - mensaje retornado: ERROR ARCHIVO TOMADO");
                logger.debug("RESULT  : llamarProcesoCobolCLVAL id [" + idReport + "] - codigo  retornado: " + control);
                resp.setCodRespuesta(2);
                resp.setMsgRespuesta("El Proceso de validacion a fallado");

            } else if ("08".equals(control)) {
                logger.debug("RESULT  : llamarProcesoCobolCLVAL id [" + idReport + "] - mensaje retornado: ERROR CANCELACION COBOL");
                logger.debug("RESULT  : llamarProcesoCobolCLVAL id [" + idReport + "] - codigo  retornado: " + control);
                resp.setCodRespuesta(2);
                resp.setMsgRespuesta("El Proceso de validacion a fallado");

            }

        } catch (Exception e) {
            resp.setCodRespuesta(2);
            resp.setMsgRespuesta("KO");
            e.printStackTrace();
        }
        logger.debug("FIN     : llamarProcesoCobolCLVAL id [" + idReport + "]");
        return resp;

    }

}
