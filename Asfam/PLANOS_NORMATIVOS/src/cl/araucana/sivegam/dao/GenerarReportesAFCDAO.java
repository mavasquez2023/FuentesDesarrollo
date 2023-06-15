package cl.araucana.sivegam.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.araucana.sivegam.config.ConfiguracionSqlMap;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.vo.AfcVO;
import cl.araucana.sivegam.vo.AfentradVO;
import cl.araucana.sivegam.vo.GenerarReportesVO;
import cl.araucana.sivegam.vo.LinAfcAFF01EVO;
import cl.araucana.sivegam.vo.LinAfcAFF01VO;
import cl.araucana.sivegam.vo.LinCesantiaError041VO;
import cl.araucana.sivegam.vo.MaestroSivegamVO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.StatusProcesoVO;
import cl.araucana.sivegam.vo.TipoProcesosVO;
import cl.araucana.sivegam.vo.param.ListadoParametros;

public class GenerarReportesAFCDAO {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    public static RespuestaVO insertarLineaAFC(LinAfcAFF01VO vo, String flagTipoReporte) {
        //logger.debug("INI     : insertarLineaAFC");
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            sqlMap.insert("generacionAFC.insertAFF01", vo);
            resp.setCodRespuesta(0);
            return resp;

        } catch (SQLException e) {
            e.printStackTrace();
            resp.setCodRespuesta(99);
            resp.setMsgRespuesta(e.getMessage());
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
                resp.setCodRespuesta(99);
                resp.setMsgRespuesta(e.getMessage());
            }
        }
        //logger.debug("FIN     : insertarLineaAFC");
        return resp;
    }

    /**
     * Funcion que realiza borrado logico de la tabla, cuando el archivo es
     * retroactivo.
     */
    public static RespuestaVO deleteLogicoRetroactivo() {
        logger.debug("INI     : deleteLogicoRetroactivo");
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            sqlMap.update("generacionAFC.deleteRetroactivo");
            resp.setCodRespuesta(0);
            logger.debug("FIN     : deleteLogicoRetroactivo");
            return resp;

        } catch (SQLException e) {
            e.printStackTrace();
            resp.setCodRespuesta(99);
            resp.setMsgRespuesta(e.getMessage());
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
                resp.setCodRespuesta(99);
                resp.setMsgRespuesta(e.getMessage());
            }
        }
        logger.debug("FIN     : deleteLogicoRetroactivo");
        return resp;
    }

    /**
     * Funcion que realiza borrado logico de la tabla, cuando el archivo es de
     * tipo mensual.
     */
    public static RespuestaVO deleteLogicoMensual() {
        logger.debug("INI     : deleteLogicoMensual");
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            sqlMap.update("generacionAFC.deleteMensual");
            resp.setCodRespuesta(0);
            logger.debug("FIN     : deleteLogicoMensual");
            return resp;

        } catch (SQLException e) {
            e.printStackTrace();
            resp.setCodRespuesta(99);
            resp.setMsgRespuesta(e.getMessage());
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
                resp.setCodRespuesta(99);
                resp.setMsgRespuesta(e.getMessage());
            }
        }
        logger.debug("FIN     : deleteLogicoMensual");
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
            datos = sqlMap.queryForList("generacionAFC.actualizaStatusPerProAfc", respuesta);
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
            e.printStackTrace();
            respuesta.setCodRespuesta(99);
            respuesta.setStatus(1);
            respuesta.setMsgRespuesta(e.getMessage());
        } finally {
            try {
                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
                respuesta.setCodRespuesta(99);
                respuesta.setStatus(1);
                respuesta.setMsgRespuesta(e.getMessage());
            }
        }

        return respuesta;
    }

    public static long obtenerMaestroSivegamAFCRetro(String flagTipoReporte) {

        logger.debug("INI     : obtenerMaestroSivegamAFCRetro id [" + flagTipoReporte + "]");
        long idMaestroSivegam = 0;
        List datos = null;
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        AfcVO afc = new AfcVO();
        afc.setFlagReporteAfc(flagTipoReporte);

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("generacionAFC.obtenerSivegamAFC", afc);
            if (datos != null && datos.size() > 0) {
                if ((String) datos.get(0) == null) {
                    idMaestroSivegam = 0;
                } else {
                    idMaestroSivegam = Long.parseLong((String) datos.get(0));
                }
            }

            logger.debug("FIN     : obtenerMaestroSivegamAFCRetro id [" + flagTipoReporte + "]");
            return idMaestroSivegam;

        } catch (SQLException e) {
            e.printStackTrace();
            idMaestroSivegam = 0;
        } finally {
            try {
                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
                idMaestroSivegam = 0;
            }
        }

        logger.debug("FIN     : obtenerMaestroSivegamAFCRetro id [" + flagTipoReporte + "]");
        return idMaestroSivegam;
    }

    public static long obtenerMaestroSivegamAFCMensual(String flagTipoReporte) {

        logger.debug("INI     : obtenerMaestroSivegamAFCMensual id [" + flagTipoReporte + "]");
        long idMaestroSivegam = 0;
        List datos = null;
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        AfcVO afc = new AfcVO();
        afc.setFlagReporteAfc(flagTipoReporte);

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("generacionAFC.obtenerSivegamAFC", afc);
            if (datos != null && datos.size() > 0) {
                if ((String) datos.get(0) == null) {
                    idMaestroSivegam = 0;
                } else {
                    idMaestroSivegam = Long.parseLong((String) datos.get(0));
                }
            }

            logger.debug("FIN     : obtenerMaestroSivegamAFCMensual id [" + flagTipoReporte + "]");
            return idMaestroSivegam;

        } catch (SQLException e) {
            e.printStackTrace();
            idMaestroSivegam = 0;
        } finally {
            try {
                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
                idMaestroSivegam = 0;
            }
        }

        logger.debug("FIN     : obtenerMaestroSivegamAFCMensual id [" + flagTipoReporte + "]");
        return idMaestroSivegam;
    }

    public static RespuestaVO statusProcesoCarga(String periodo, String tipoReporte) {
        //logger.debug("INI     : statusProcesoCarga id [" + periodo + " - " + tipoReporte + "]");
        List datos = null;
        RespuestaVO respuesta = new RespuestaVO();

        respuesta.setArchivoCarga(tipoReporte);
        respuesta.setMesConsultado(Integer.parseInt(periodo));

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("generacionAFC.obtenerStatusProcesoCarga", respuesta);
            if (datos != null && datos.size() > 0) {
                if (datos.get(0) == null) {
                    respuesta.setCodRespuesta(99);
                    respuesta.setStatus(1);
                } else {
                    respuesta.setCodRespuesta(0);
                    respuesta.setStatus(Integer.parseInt((String) datos.get(0)));
                    respuesta.setStatusCarga(Integer.parseInt((String) datos.get(0)));
                }
            }

            //logger.debug("FIN     : statusProcesoCarga id [" + periodo + " - " + tipoReporte + "]");
            return respuesta;

        } catch (SQLException e) {
            e.printStackTrace();
            respuesta.setCodRespuesta(99);
            respuesta.setStatus(1);
            respuesta.setMsgRespuesta(e.getMessage());
        } finally {
            try {
                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
                respuesta.setCodRespuesta(99);
                respuesta.setStatus(1);
                respuesta.setMsgRespuesta(e.getMessage());
            }
        }

        //logger.debug("FIN     : statusProcesoCarga id [" + periodo + " - " + tipoReporte + "]");
        return respuesta;
    }

    public static AfentradVO insertaAFENTRAD(String tipoArchivo, String periodo, String fechaActual, String usuario) {

        logger.debug("INI     : insertaAFENTRAD id [" + tipoArchivo + " - " + periodo + "]");
        AfentradVO afenvo = new AfentradVO();
        List datos = null;
        String fecha = "";
        long idafenvo;
        Date dateProceso = new Date();
        String DATE_FORMAT2 = "dd/MM/yyyy";
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        int periodoI = Integer.parseInt(periodo);
        long usuarioI = Long.parseLong(usuario);
        try {
            sqlMap.startTransaction(0);
            fecha = fechaActual;
            dateProceso = sdf2.parse(fecha);

            datos = sqlMap.queryForList("generacionAFC.selectIdAFENTRAD", afenvo);

            if (datos != null && datos.size() > 0) {
                
                if (datos.get(0) == null){
                    idafenvo = 1;
                }else{
                    idafenvo = Long.parseLong((String) datos.get(0)) + 1;
                }
                
                afenvo.setIDESTCARGA(idafenvo);
                afenvo.setFECHACARGA(dateProceso);
                afenvo.setESTADOCRGA(1);
                afenvo.setPERIODOCRG(periodoI);
                afenvo.setTIPARCHCRG(tipoArchivo);
                afenvo.setUSUARIOCRG(usuarioI);

            } else {
                logger.debug("ERROR   : insertaAFENTRAD id [" + tipoArchivo + " - " + periodo + "] - ERROR AL TRAER AFENTRAD -- generacionAFC.selectIdAFENTRAD()");
                afenvo.setIDESTCARGA(0);
            }

            sqlMap.insert("generacionAFC.insertAFENTRAD", afenvo);
            sqlMap.commitTransaction();

        } catch (SQLException e) {

            logger.debug("ERROR   : insertaAFENTRAD id [" + tipoArchivo + " - " + periodo + "] - ERROR AL TRAER AFENTRAD -- generacionAFC.selectIdAFENTRAD()");
            afenvo.setIDESTCARGA(0);
            e.printStackTrace();

        } catch (ParseException e) {

            logger.debug("ERROR   : insertaAFENTRAD id [" + tipoArchivo + " - " + periodo + "] - ERROR AL TRAER AFENTRAD -- generacionAFC.selectIdAFENTRAD()");
            afenvo.setIDESTCARGA(0);
            e.printStackTrace();

        } finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                logger.debug("ERROR   : insertaAFENTRAD id [" + tipoArchivo + " - " + periodo + "] - ERROR AL TRAER AFENTRAD -- generacionAFC.selectIdAFENTRAD()");
                afenvo.setIDESTCARGA(0);
                e.printStackTrace();
            }
        }
        logger.debug("FIN     : insertaAFENTRAD id [" + tipoArchivo + " - " + periodo + "]");
        return afenvo;
    }

    public static RespuestaVO insertarLineaAFCErr(LinAfcAFF01EVO vo) {
//        logger.debug("INI     : insertarLineaAFCErr");
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            sqlMap.insert("generacionAFC.insertAFCE", vo);
            resp.setCodRespuesta(0);
            sqlMap.commitTransaction();
//            logger.debug("FIN     : insertarLineaAFCErr");
            return resp;

        } catch (SQLException e) {
            e.printStackTrace();
            resp.setCodRespuesta(99);
            resp.setMsgRespuesta(e.getMessage());
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                resp.setCodRespuesta(99);
                resp.setMsgRespuesta(e.getMessage());
                e.printStackTrace();
            }
        }
//        logger.debug("FIN     : insertarLineaAFCErr");
        return resp;
    }

    public static RespuestaVO updatePrevInsertaAFENTRAD(String periodo) {
        logger.debug("INI     : updatePrevInsertaAFENTRAD");
        RespuestaVO resp = new RespuestaVO();
        AfentradVO afenvo = new AfentradVO();
        afenvo.setPERIODOCRG(Long.parseLong(periodo));
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            sqlMap.update("generacionAFC.updatePrevInsertaAFENTRAD", afenvo);
            resp.setCodRespuesta(0);
            sqlMap.commitTransaction();
            logger.debug("FIN     : updatePrevInsertaAFENTRAD");
            return resp;

        } catch (SQLException e) {
            resp.setCodRespuesta(0);
            e.printStackTrace();
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                resp.setCodRespuesta(99);
                resp.setMsgRespuesta(e.toString());
                e.printStackTrace();
            }
        }
        logger.debug("FIN     : updatePrevInsertaAFENTRAD");
        return resp;
    }

    public static RespuestaVO deleteLineaAFCErr(String tipoArchivo) {
        logger.debug("INI     : deleteLineaAFCErr");
        RespuestaVO resp = new RespuestaVO();
        LinAfcAFF01EVO vo = new LinAfcAFF01EVO();
        vo.setTipArch(tipoArchivo);
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            sqlMap.delete("generacionAFC.deleteACFE", vo);
            resp.setCodRespuesta(0);
            return resp;

        } catch (SQLException e) {
            e.printStackTrace();
            resp.setCodRespuesta(99);
            resp.setMsgRespuesta(e.getMessage());
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
                resp.setCodRespuesta(99);
                resp.setMsgRespuesta(e.getMessage());
            }
        }
        logger.debug("FIN     : deleteLineaAFCErr");
        return resp;
    }


}
