package cl.araucana.sivegam.dao;

import java.sql.SQLException;
import java.util.List;
import com.ibatis.sqlmap.client.SqlMapClient;
import cl.araucana.sivegam.config.ConfiguracionSqlMap;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.vo.LinSif011VO;
import cl.araucana.sivegam.vo.LinSif012VO;
import cl.araucana.sivegam.vo.LinSif014VO;
import cl.araucana.sivegam.vo.PeriodoProcesoVO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.Sif011VO;
import cl.araucana.sivegam.vo.Sif012VO;
import cl.araucana.sivegam.vo.Sif014VO;
import cl.araucana.sivegam.vo.param.ListadoParametros;

public class AgregarRegistroDivisionPrevisionalDAO {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    public static RespuestaVO insertSif011(LinSif011VO linsif011) {

        List datos = null;
        String idSif = "";
        RespuestaVO respuesta = new RespuestaVO();
        Sif011VO sif011 = new Sif011VO();
        LinSif011VO lista = new LinSif011VO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            datos = sqlMap.queryForList("AgregarRegistroDivPrev.selectMaxIdsif011");
            if (datos != null && datos.size() > 0) {

                idSif = (String) datos.get(0);
                sif011.setIdsif011(Long.parseLong(idSif));

            } else {

                respuesta.setCodRespuesta(99);
                return respuesta;
            }

            datos = sqlMap.queryForList("AgregarRegistroDivPrev.obtenerDataTabla011", sif011);

            if (datos != null && datos.size() > 0) {
                lista = (LinSif011VO) datos.get(0);
            } else {

                respuesta.setCodRespuesta(99);
                return respuesta;
            }

            long id_sif011 = Long.parseLong(idSif) + 1;

            linsif011.setId_maestro_sivegam(lista.getId_maestro_sivegam());
            linsif011.setFecha_proceso(lista.getFecha_proceso());
            linsif011.setCodigo_entidad(lista.getCodigo_entidad());
            linsif011.setFlag_reg_eliminado(0);
            linsif011.setFlag_reg_modificado(0);
            linsif011.setId_sif011(id_sif011);
            linsif011.setMes_cotizaciones(lista.getMes_cotizaciones());
            linsif011.setCodigo_archivo(11);

            sqlMap.insert("AgregarRegistroDivPrev.insertNewSif011", linsif011);

            respuesta.setCodRespuesta(0);
            sqlMap.commitTransaction();

        } catch (SQLException e) {

            respuesta.setCodRespuesta(99);
            respuesta.setMsgRespuesta(e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                respuesta.setCodRespuesta(99);
                respuesta.setMsgRespuesta(e.getMessage());
                e.printStackTrace();
            }
        }

        return respuesta;
    }

    public static RespuestaVO insertSif012(LinSif012VO linsif012) {

        List datos = null;
        String idSif = "";
        RespuestaVO respuesta = new RespuestaVO();
        Sif012VO sif012 = new Sif012VO();
        LinSif012VO lista = new LinSif012VO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            datos = sqlMap.queryForList("AgregarRegistroDivPrev.selectMaxIdsif012");
            if (datos != null && datos.size() > 0) {

                idSif = (String) datos.get(0);
                sif012.setIdsif012(Long.parseLong(idSif));

            } else {

                respuesta.setCodRespuesta(99);
                return respuesta;
            }

            datos = sqlMap.queryForList("AgregarRegistroDivPrev.obtenerDataTabla012", sif012);

            if (datos != null && datos.size() > 0) {
                lista = (LinSif012VO) datos.get(0);
            } else {

                respuesta.setCodRespuesta(99);
                return respuesta;
            }

            long id_sif012 = Long.parseLong(idSif) + 1;

            linsif012.setId_maestro_sivegam(lista.getId_maestro_sivegam());
            linsif012.setFecha_proceso(lista.getFecha_proceso());
            linsif012.setCodigo_entidad(lista.getCodigo_entidad());
            linsif012.setCodigo_archivo(12);
            linsif012.setFlag_reg_eliminado(0);
            linsif012.setFlag_reg_modificado(0);
            linsif012.setId_sif012(id_sif012);
            linsif012.setMes_recaudacion(lista.getMes_recaudacion());
            linsif012.setMes_remuneracion(lista.getMes_remuneracion());
            linsif012.setNumero_declaracion(lista.getNumero_declaracion());

            sqlMap.insert("AgregarRegistroDivPrev.insertNewSif012", linsif012);

            respuesta.setCodRespuesta(0);
            sqlMap.commitTransaction();

        } catch (SQLException e) {

            respuesta.setCodRespuesta(99);
            respuesta.setMsgRespuesta(e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                respuesta.setCodRespuesta(99);
                respuesta.setMsgRespuesta(e.getMessage());
                e.printStackTrace();
            }
        }

        return respuesta;

    }

    public static RespuestaVO insertSif014(LinSif014VO linsif014) {

        List datos = null;
        String idSif = "";
        RespuestaVO respuesta = new RespuestaVO();
        Sif014VO sif014 = new Sif014VO();
        LinSif014VO lista = new LinSif014VO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            datos = sqlMap.queryForList("AgregarRegistroDivPrev.selectMaxIdsif014");
            if (datos != null && datos.size() > 0) {

                idSif = (String) datos.get(0);
                sif014.setIdsif014(Long.parseLong(idSif));

            } else {

                respuesta.setCodRespuesta(99);
                return respuesta;
            }

            datos = sqlMap.queryForList("AgregarRegistroDivPrev.obtenerDataTabla014", sif014);

            if (datos != null && datos.size() > 0) {
                lista = (LinSif014VO) datos.get(0);
            } else {

                respuesta.setCodRespuesta(99);
                return respuesta;
            }

            long id_sif014 = Long.parseLong(idSif) + 1;

            linsif014.setId_maestro_sivegam(lista.getId_maestro_sivegam());
            linsif014.setFecha_proceso(lista.getFecha_proceso());
            linsif014.setCodigo_entidad(lista.getCodigo_entidad());
            linsif014.setFlag_reg_eliminado(0);
            linsif014.setFlag_reg_modificado(0);
            linsif014.setId_sif014(id_sif014);
            linsif014.setCodigo_archivo(14);
            sqlMap.insert("AgregarRegistroDivPrev.insertNewSif014", linsif014);

            respuesta.setCodRespuesta(0);
            sqlMap.commitTransaction();

        } catch (SQLException e) {

            respuesta.setCodRespuesta(99);
            respuesta.setMsgRespuesta(e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                respuesta.setCodRespuesta(99);
                respuesta.setMsgRespuesta(e.getMessage());
                e.printStackTrace();
            }
        }

        return respuesta;

    }

    public static RespuestaVO cargarMesProcesamiento(String idTipoReporte) {

        String fechaProcesamiento = "";
        String anio = "";
        String mes = "";
        String glosaMes = "";
        List datos = null;
        RespuestaVO respuesta = new RespuestaVO();

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        respuesta.setIdTipoReporte(Integer.parseInt(idTipoReporte));

        try {
            sqlMap.startTransaction(0);

            /* Obtener la fecha de proceso desde la tabla sif011. */
            switch (Integer.parseInt(idTipoReporte)) {

            case 11:
                datos = sqlMap.queryForList("AgregarRegistroDivPrev.obtenerFechaProcesoSif011");
                break;

            case 12:
                datos = sqlMap.queryForList("AgregarRegistroDivPrev.obtenerFechaProcesoSif012");
                break;

            case 14:
                datos = sqlMap.queryForList("AgregarRegistroDivPrev.obtenerFechaProcesoSif014");
                break;

            default:
                respuesta.setCodRespuesta(99);
            }

            if (datos != null && datos.size() > 0) {
                if (datos.get(0) == null) {
                    fechaProcesamiento = "";
                } else {
                    fechaProcesamiento = (String) datos.get(0);
                    StringBuffer sb = new StringBuffer("");

                    anio = fechaProcesamiento.substring(0, 4);
                    mes = fechaProcesamiento.substring(4, 6);

                    //logger.debug(anio + " - " + mes);

                    ListadoParametros listaParam = ListadoParametros.getInstancia();
                    PeriodoProcesoVO[] periodoProceso = listaParam.getListPeriodoProcesos();

                    for (int i = 0; i < periodoProceso.length; i++) {
                        if (periodoProceso[i].getPeriodo_proceso() == Long.parseLong(mes)) {
                            glosaMes = periodoProceso[i].getDescripcion_periodo_proceso();
                            break;
                        }
                    }

                    sb.append(glosaMes);
                    sb.append(' ');
                    sb.append(anio);

                    respuesta.setMesConsultado(Integer.parseInt(mes));
                    respuesta.setPeriodoProceso(sb.toString());
                    respuesta.setRutaArchivo(fechaProcesamiento);
                    respuesta.setCodRespuesta(0);

                }
            } else {
                respuesta.setCodRespuesta(99);
            }

            return respuesta;

        } catch (SQLException e) {
            respuesta.setCodRespuesta(99);
            respuesta.setMsgRespuesta(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                respuesta.setCodRespuesta(99);
                respuesta.setMsgRespuesta(e.getMessage());
                e.printStackTrace();
            }
        }

        return respuesta;
    }

    public static LinSif012VO obtenerDataSif012(long correlativo) {

        String fechaTmp = "";
        String fechaInicioBeneficio = "01/01/1900";
        ;
        String fechaTerminoBeneficio = "01/01/1900";
        ;
        String fechaEmisionDocumento = "01/01/1900";
        ;

        List datos = null;
        Sif012VO sif012 = new Sif012VO();
        LinSif012VO linSif012 = new LinSif012VO();
        sif012.setIdsif012(correlativo);

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("AgregarRegistroDivPrev.selectSif012PorId", sif012);
            if (datos != null && datos.size() > 0) {

                linSif012 = (LinSif012VO) datos.get(0);

                fechaTmp = Long.toString(linSif012.getFecha_inicio_benef());

                if (!"0".equals(fechaTmp)) {
                    fechaInicioBeneficio = fechaTmp.substring(6, 8) + "/" + fechaTmp.substring(4, 6) + "/" + fechaTmp.substring(0, 4);
                }
                linSif012.setFechaInicioBeneficioDate(fechaInicioBeneficio);

                fechaTmp = Long.toString(linSif012.getFecha_termino_benef());
                if (!"0".equals(fechaTmp)) {
                    fechaTerminoBeneficio = fechaTmp.substring(6, 8) + "/" + fechaTmp.substring(4, 6) + "/" + fechaTmp.substring(0, 4);
                }
                linSif012.setFechaTerminoBeneficioDate(fechaTerminoBeneficio);

                fechaTmp = Long.toString(linSif012.getFech_emision_doc());
                if (!"0".equals(fechaTmp)) {
                    fechaEmisionDocumento = fechaTmp.substring(6, 8) + "/" + fechaTmp.substring(4, 6) + "/" + fechaTmp.substring(0, 4);
                }
                linSif012.setFechaEmisionDocumentoDate(fechaEmisionDocumento);
            }

            return linSif012;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return linSif012;
    }

    public static LinSif011VO obtenerDataSif011(long correlativo) {

        String fechaTemp = "";
        String fechaInicioBeneficio = "";
        String fechaTerminoBeneficio = "";
        String fechaEmisionDocumento = "";

        List datos = null;
        LinSif011VO linSif011 = new LinSif011VO();
        Sif011VO sif011 = new Sif011VO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif011.setIdsif011(correlativo);

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("AgregarRegistroDivPrev.selectSif011PorId", sif011);

            if (datos != null && datos.size() > 0) {
                linSif011 = (LinSif011VO) datos.get(0);

                fechaTemp = Long.toString(linSif011.getFecha_inicio_benef());
                if ("0".equals(fechaTemp)) {
                    fechaInicioBeneficio = "01/01/1900";
                } else {
                    fechaInicioBeneficio = fechaTemp.substring(6, 8) + "/" + fechaTemp.substring(4, 6) + "/" + fechaTemp.substring(0, 4);
                }
                linSif011.setFechaInicioBeneficioDate(fechaInicioBeneficio);

                fechaTemp = Long.toString(linSif011.getFecha_termino_benef());
                if ("0".equals(fechaTemp)) {
                    fechaTerminoBeneficio = "01/01/1900";
                } else {
                    fechaTerminoBeneficio = fechaTemp.substring(6, 8) + "/" + fechaTemp.substring(4, 6) + "/" + fechaTemp.substring(0, 4);
                }
                linSif011.setFechaTerminoBeneficioDate(fechaTerminoBeneficio);

                fechaTemp = Long.toString(linSif011.getFecha_emision_documento());
                if ("0".equals(fechaTemp)) {
                    fechaEmisionDocumento = "01/01/1900";
                } else {
                    fechaEmisionDocumento = fechaTemp.substring(6, 8) + "/" + fechaTemp.substring(4, 6) + "/" + fechaTemp.substring(0, 4);
                }
                linSif011.setFechaEmisionDocumentoDate(fechaEmisionDocumento);
            }

            return linSif011;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return linSif011;
    }
}
