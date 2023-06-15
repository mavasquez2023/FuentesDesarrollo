package cl.araucana.sivegam.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import cl.araucana.sivegam.config.ConfiguracionSqlMap;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.vo.LinCesantia041VO;
import cl.araucana.sivegam.vo.LinCesantia042VO;
import cl.araucana.sivegam.vo.LinCesantia043VO;
import cl.araucana.sivegam.vo.LinCesantia044VO;
import cl.araucana.sivegam.vo.LinCesantiaError041VO;
import cl.araucana.sivegam.vo.LinCesantiaError042VO;
import cl.araucana.sivegam.vo.LinCesantiaError043VO;
import cl.araucana.sivegam.vo.LinCesantiaError044VO;
import cl.araucana.sivegam.vo.MaestroSivegamVO;
import cl.araucana.sivegam.vo.RespuestaVO;

public class GenerarReportesCesantiaDAO {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    public static RespuestaVO insertarLineaCesantia041(LinCesantia041VO vo) {
        //logger.debug("INI     : insertarLineaCesantia041");
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            sqlMap.insert("generacionCesantia.insertSCF041", vo);
            resp.setCodRespuesta(0);
            sqlMap.commitTransaction();
            return resp;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //logger.debug("FIN     : insertarLineaCesantia041");
        return resp;
    }

    public static RespuestaVO insertarLineaCesantia042(LinCesantia042VO vo) {
        //logger.debug("INI     : insertarLineaCesantia042");
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            sqlMap.insert("generacionCesantia.insertSCF042", vo);
            resp.setCodRespuesta(0);
            sqlMap.commitTransaction();
            return resp;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //logger.debug("FIN     : insertarLineaCesantia042");
        return resp;
    }

    public static RespuestaVO insertarLineaCesantia043(LinCesantia043VO vo) {
        //logger.debug("INI     : insertarLineaCesantia043");
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            sqlMap.insert("generacionCesantia.insertSCF043", vo);
            resp.setCodRespuesta(0);
            sqlMap.commitTransaction();
            return resp;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //logger.debug("FIN     : insertarLineaCesantia043");
        return resp;
    }

    public static RespuestaVO insertarLineaCesantia044(LinCesantia044VO vo) {
        //logger.debug("INI     : insertarLineaCesantia044");
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            sqlMap.insert("generacionCesantia.insertSCF044", vo);
            resp.setCodRespuesta(0);
            sqlMap.commitTransaction();
            return resp;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //logger.debug("FIN     : insertarLineaCesantia044");
        return resp;
    }

    /** Funcion que realiza borrado logico de la tabla SCF041 */
    public static RespuestaVO deleteLogicoSC41() {

        //logger.debug("INI     : deleteLogicoSC41");
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            sqlMap.update("generacionCesantia.deleteLogicSC41");
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
        //logger.debug("FIN     : deleteLogicoSC41");
        return resp;
    }

    /** Funcion que realiza borrado logico de la tabla SCF042 */
    public static RespuestaVO deleteLogicoSC42() {

        //logger.debug("INI     : deleteLogicoSC42");
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            sqlMap.update("generacionCesantia.deleteLogicSC42");
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
        //logger.debug("FIN     : deleteLogicoSC42");
        return resp;
    }

    /** Funcion que realiza borrado logico de la tabla SCF043 */
    public static RespuestaVO deleteLogicoSC43() {

        //logger.debug("INI     : deleteLogicoSC43");
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            sqlMap.update("generacionCesantia.deleteLogicSC43");
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
        //logger.debug("FIN     : deleteLogicoSC43");
        return resp;
    }

    /** Funcion que realiza borrado logico de la tabla SCF044 */
    public static RespuestaVO deleteLogicoSC44() {

        //logger.debug("INI     : deleteLogicoSC44");
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            sqlMap.update("generacionCesantia.deleteLogicSC44");
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
        //logger.debug("FIN     : deleteLogicoSC44");
        return resp;
    }

    /** Funcion que actualiza status proceso cuando se requiera reprocesar. */
    public static RespuestaVO actualizarStatusSegunPeriodoYProceso(String tipoReporte, String mesPeriodo) {

        //logger.debug("INI     : actualizarStatusSegunPeriodoYProceso id [" + tipoReporte + " - " + mesPeriodo + "]");
        List datos = null;
        RespuestaVO respuesta = new RespuestaVO();

        respuesta.setIdTipoReporte(Integer.parseInt(tipoReporte));
        respuesta.setMesConsultado(Integer.parseInt(mesPeriodo));

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("generacionCesantia.actualizaStatusPerProCesantia", respuesta);
            if (datos != null && datos.size() > 0) {
                if (datos.get(0) == null) {
                    respuesta.setCodRespuesta(99);
                    respuesta.setStatus(1);
                } else {
                    respuesta = (RespuestaVO) datos.get(0);
                    //logger.debug("respuesta.status: " + respuesta.getStatus());
                    respuesta.setCodRespuesta(0);
                }
            }

            return respuesta;

        } catch (SQLException e) {
            e.printStackTrace();
            respuesta.setCodRespuesta(99);
            respuesta.setMsgRespuesta(e.getMessage());
            respuesta.setStatus(1);
        } finally {
            try {
                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
                respuesta.setCodRespuesta(99);
                respuesta.setMsgRespuesta(e.getMessage());
                respuesta.setStatus(1);
            }
        }

        //logger.debug("FIN     : actualizarStatusSegunPeriodoYProceso id [" + tipoReporte + " - " + mesPeriodo + "]");
        return respuesta;
    }

    public static MaestroSivegamVO selectMaxIdMaestroSivegamCesantia(int tipoArchivo, String periodoMes) {

        //logger.debug("INI     : selectMaxIdMaestroSivegamCesantia id [" + tipoArchivo + " - " + periodoMes + "]");
        List datos = null;
        String idMaestroSivegam = "";
        MaestroSivegamVO maestro = new MaestroSivegamVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        maestro.setTipo_archivo(tipoArchivo);
        maestro.setPeriodo_proceso(Integer.parseInt(periodoMes));

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("generacionCesantia.obtenerMaxSivegamCesantia", maestro);
            if (datos != null && datos.size() > 0) {
                if (datos.get(0) == null) {
                    maestro.setMaestro_sivegam(0);
                } else {
                    idMaestroSivegam = (String) datos.get(0);
                    maestro.setMaestro_sivegam(Long.parseLong(idMaestroSivegam));
                }
            }

            return maestro;

        } catch (SQLException e) {
            e.printStackTrace();
            maestro.setMaestro_sivegam(0);
        } finally {
            try {
                sqlMap.endTransaction();

            } catch (SQLException e) {

                e.printStackTrace();
                maestro.setMaestro_sivegam(0);
            }
        }
        //logger.debug("FIN     : selectMaxIdMaestroSivegamCesantia id [" + tipoArchivo + " - " + periodoMes + "]");
        return maestro;
    }

    public static long consultarSivegamTablasCesantia041() {

        //logger.debug("INI     : consultarSivegamTablasCesantia041");
        long idMaestroSivegam = 0;
        List datos = null;
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("generacionCesantia.obtenerMaestroSivegamCesantia041");
            if (datos != null && datos.size() > 0) {
                if ((String) datos.get(0) == null) {
                    idMaestroSivegam = 0;
                } else {
                    idMaestroSivegam = Long.parseLong((String) datos.get(0));
                }
            }

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

        //logger.debug("FIN     : consultarSivegamTablasCesantia041");
        return idMaestroSivegam;
    }

    public static long consultarSivegamTablasCesantia042() {

       // logger.debug("INI     : consultarSivegamTablasCesantia042");
        long idMaestroSivegam = 0;
        List datos = null;
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("generacionCesantia.obtenerMaestroSivegamCesantia042");
            if (datos != null && datos.size() > 0) {
                if ((String) datos.get(0) == null) {
                    idMaestroSivegam = 0;
                } else {
                    idMaestroSivegam = Long.parseLong((String) datos.get(0));
                }
            }

            return idMaestroSivegam;

        } catch (SQLException e) {
            e.printStackTrace();
            idMaestroSivegam = 0;
        } finally {
            try {
                sqlMap.endTransaction();

            } catch (SQLException e) {
                idMaestroSivegam = 0;
                e.printStackTrace();
            }
        }

        //logger.debug("FIN     : consultarSivegamTablasCesantia042");
        return idMaestroSivegam;
    }

    public static long consultarSivegamTablasCesantia043() {

        //logger.debug("INI     : consultarSivegamTablasCesantia043");
        long idMaestroSivegam = 0;
        List datos = null;
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("generacionCesantia.obtenerMaestroSivegamCesantia043");
            if (datos != null && datos.size() > 0) {
                if ((String) datos.get(0) == null) {
                    idMaestroSivegam = 0;
                } else {
                    idMaestroSivegam = Long.parseLong((String) datos.get(0));
                }
            }

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

        //logger.debug("FIN     : consultarSivegamTablasCesantia043");
        return idMaestroSivegam;
    }

    public static long consultarSivegamTablasCesantia044() {

       // logger.debug("INI     : consultarSivegamTablasCesantia044");
        long idMaestroSivegam = 0;
        List datos = null;
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("generacionCesantia.obtenerMaestroSivegamCesantia044");
            if (datos != null && datos.size() > 0) {
                if ((String) datos.get(0) == null) {
                    idMaestroSivegam = 0;
                } else {
                    idMaestroSivegam = Long.parseLong((String) datos.get(0));
                }
            }

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

      //  logger.debug("FIN     : consultarSivegamTablasCesantia044");
        return idMaestroSivegam;
    }

    public static RespuestaVO insertarLineaCesantia041Err(LinCesantiaError041VO vo) {
       // logger.debug("INI     : insertarLineaCesantia041Err");
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            sqlMap.insert("generacionCesantia.insertSCF041E", vo);
            resp.setCodRespuesta(0);
            sqlMap.commitTransaction();
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
       // logger.debug("FIN     : insertarLineaCesantia041Err");
        return resp;
    }

    public static RespuestaVO insertarLineaCesantia042Err(LinCesantiaError042VO vo) {
       // logger.debug("INI     : insertarLineaCesantia042Err");
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            sqlMap.insert("generacionCesantia.insertSCF042E", vo);
            resp.setCodRespuesta(0);
            sqlMap.commitTransaction();
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
        //logger.debug("FIN     : insertarLineaCesantia042Err");
        return resp;
    }

    public static RespuestaVO insertarLineaCesantia043Err(LinCesantiaError043VO vo) {
        //logger.debug("INI     : insertarLineaCesantia043Err");
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            sqlMap.insert("generacionCesantia.insertSCF043E", vo);
            resp.setCodRespuesta(0);
            sqlMap.commitTransaction();
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
       // logger.debug("FIN     : insertarLineaCesantia043Err");
        return resp;
    }

    public static RespuestaVO insertarLineaCesantia044Err(LinCesantiaError044VO vo) {
       // logger.debug("INI     : insertarLineaCesantia044Err");
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            sqlMap.insert("generacionCesantia.insertSCF044E", vo);
            resp.setCodRespuesta(0);
            sqlMap.commitTransaction();
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
     //   logger.debug("FIN     : insertarLineaCesantia044Err");
        return resp;
    }

    public static RespuestaVO deleteLineaCesantia041Err() {
   //     logger.debug("INI     : deleteLineaCesantia041Err");
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            sqlMap.delete("generacionCesantia.deleteSCF041E");
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
     //   logger.debug("FIN     : deleteLineaCesantia041Err");
        return resp;
    }

    public static RespuestaVO deleteLineaCesantia042Err() {
     //   logger.debug("INI     : deleteLineaCesantia042Err");
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            sqlMap.delete("generacionCesantia.deleteSCF042E");
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
   //     logger.debug("FIN     : deleteLineaCesantia042Err");
        return resp;
    }

    public static RespuestaVO deleteLineaCesantia043Err() {
   //     logger.debug("INI     : deleteLineaCesantia043Err");
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            sqlMap.delete("generacionCesantia.deleteSCF043E");
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
  //      logger.debug("FIN     : deleteLineaCesantia043Err");
        return resp;
    }

    public static RespuestaVO deleteLineaCesantia044Err() {
  //      logger.debug("INI     : deleteLineaCesantia044Err");
        RespuestaVO resp = new RespuestaVO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            sqlMap.delete("generacionCesantia.deleteSCF044E");
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
   //     logger.debug("FIN     : deleteLineaCesantia044Err");
        return resp;
    }

}
