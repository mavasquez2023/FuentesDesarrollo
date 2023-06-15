package cl.araucana.sivegam.dao;

import java.sql.SQLException;
import java.util.List;
import com.ibatis.sqlmap.client.SqlMapClient;
import cl.araucana.sivegam.config.ConfiguracionSqlMap;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.vo.LinSif016VO;
import cl.araucana.sivegam.vo.LinSif017VO;
import cl.araucana.sivegam.vo.LinSif018VO;
import cl.araucana.sivegam.vo.LinSif019VO;
import cl.araucana.sivegam.vo.PeriodoProcesoVO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.Sif016VO;
import cl.araucana.sivegam.vo.Sif017VO;
import cl.araucana.sivegam.vo.Sif018VO;
import cl.araucana.sivegam.vo.Sif019VO;
import cl.araucana.sivegam.vo.param.ListadoParametros;

public class AgregarRegistroCotizacionesDAO {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();
    
    /* Funcion que agrega un registro completo a la tabla sif018 */
    public static RespuestaVO insertSif018(LinSif018VO linsif018) {
        logger.debug("INI : insertSif018");
        List datos = null;
        String idSif = "";
        RespuestaVO respuesta = new RespuestaVO();
        Sif018VO sif018 = new Sif018VO();
        LinSif018VO lista = new LinSif018VO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();
        
        try {
            sqlMap.startTransaction(0);

            logger.debug("ANTES   : [insertSif018] selectMaxIdsif018");
            datos = sqlMap.queryForList("AgregarRegistroCot.selectMaxIdsif018");
            logger.debug("DESPUES : [insertSif018] selectMaxIdsif018");
            if (datos != null && datos.size() > 0) {

                idSif = (String) datos.get(0);
                sif018.setIdsif018(Long.parseLong(idSif));
                logger.debug("OUT     : [insertSif018] Idsif018 [" + sif018.getIdsif018() + "]");
            }

            logger.debug("ANTES   : [insertSif018] obtenerDataTabla018");
            logger.debug("IN      : [insertSif018] Idsif018 [" + sif018.getIdsif018() + "]");
            datos = sqlMap.queryForList("AgregarRegistroCot.obtenerDataTabla018", sif018);
            logger.debug("DESPUES : [insertSif018] obtenerDataTabla018");

            if (datos != null && datos.size() > 0) {
                lista = (LinSif018VO) datos.get(0);
            }

            long id_sif018 = Long.parseLong(idSif) + 1;

            linsif018.setId_maestro_sivegam(lista.getId_maestro_sivegam());
            linsif018.setFecha_proceso(lista.getFecha_proceso());
            linsif018.setCodigo_entidad(lista.getCodigo_entidad());
            linsif018.setFlag_reg_eliminado(0);
            linsif018.setFlag_reg_modificado(0);
            linsif018.setId_sif018(id_sif018);

            logger.debug("OUT     : [insertSif018] getId_maestro_sivegam [" + linsif018.getId_maestro_sivegam() + "]");
            logger.debug("OUT     : [insertSif018] getFecha_proceso      [" + linsif018.getFecha_proceso() + "]");
            logger.debug("OUT     : [insertSif018] Codigo_entidad        [" + linsif018.getCodigo_entidad() + "]");
            logger.debug("OUT     : [insertSif018] Flag_reg_eliminado    [" + linsif018.getFlag_reg_eliminado() + "]");
            logger.debug("OUT     : [insertSif018] Flag_reg_modificado   [" + linsif018.getFlag_reg_modificado() + "]");
            logger.debug("OUT     : [insertSif018] Id_sif018             [" + linsif018.getId_sif018() + "]");

            logger.debug("ANTES   : [insertSif018] insertNewSif018");
            sqlMap.insert("AgregarRegistroCot.insertNewSif018", linsif018);
            logger.debug("DESPUES : [insertSif018] insertNewSif018");

            respuesta.setCodRespuesta(0);
            sqlMap.commitTransaction();

        } catch (SQLException e) {
            logger.debug("ERROR   : [insertSif018] " + e.getMessage());
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
        logger.debug("INI : insertSif018");
        return respuesta;
    }

    /* Funcion que agrega un registro completo a la tabla sif018 */
    public static RespuestaVO insertSif019(LinSif019VO linsif019) {

        List datos = null;
        String idSif = "";
        RespuestaVO respuesta = new RespuestaVO();
        Sif019VO sif019 = new Sif019VO();
        LinSif019VO lista = new LinSif019VO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            datos = sqlMap.queryForList("AgregarRegistroCot.selectMaxIdsif019");
            if (datos != null && datos.size() > 0) {

                idSif = (String) datos.get(0);
                sif019.setIdSif019(Long.parseLong(idSif));
            }

            datos = sqlMap.queryForList("AgregarRegistroCot.obtenerDataTabla019", sif019);

            if (datos != null && datos.size() > 0) {
                lista = (LinSif019VO) datos.get(0);
            }

            long id_sif019 = Long.parseLong(idSif) + 1;

            linsif019.setId_maestro_sivegam(lista.getId_maestro_sivegam());
            linsif019.setFecha_proceso(lista.getFecha_proceso());
            linsif019.setCodigo_entidad(lista.getCodigo_entidad());
            linsif019.setCodigo_archivo(lista.getCodigo_archivo());
            linsif019.setFlag_reg_eliminado(0);
            linsif019.setFlag_reg_modificado(0);
            linsif019.setEstado_doc_orig(102);
            linsif019.setId_sif019(id_sif019);

            sqlMap.insert("AgregarRegistroCot.insertNewSif019", linsif019);

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

    public static RespuestaVO insertSif017(LinSif017VO linsif017) {

        List datos = null;
        String idSif = "";
        RespuestaVO respuesta = new RespuestaVO();
        Sif017VO sif017 = new Sif017VO();
        LinSif017VO lista = new LinSif017VO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            datos = sqlMap.queryForList("AgregarRegistroCot.selectMaxIdsif017");
            if (datos != null && datos.size() > 0) {

                idSif = (String) datos.get(0);
                sif017.setIdsif017(Long.parseLong(idSif));
            }

            datos = sqlMap.queryForList("AgregarRegistroCot.obtenerDataTabla017", sif017);

            if (datos != null && datos.size() > 0) {
                lista = (LinSif017VO) datos.get(0);
            }

            long id_sif017 = Long.parseLong(idSif) + 1;

            linsif017.setId_maestro_sivegam(lista.getId_maestro_sivegam());
            linsif017.setFecha_proceso(lista.getFecha_proceso());
            linsif017.setCodigo_entidad(lista.getCodigo_entidad());
            linsif017.setCodigo_archivo(lista.getCodigo_archivo());
            linsif017.setFlag_reg_eliminado(0);
            linsif017.setFlag_reg_modificado(0);
            linsif017.setId_sif017(id_sif017);

            sqlMap.insert("AgregarRegistroCot.insertNewSif017", linsif017);

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

    /* funcion que inserta en la tabla svdtad.sif016 */
    public static RespuestaVO insertSif016(LinSif016VO linsif016) {

        List datos = null;
        String idSif = "";
        RespuestaVO respuesta = new RespuestaVO();
        Sif016VO sif016 = new Sif016VO();
        LinSif016VO lista = new LinSif016VO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            datos = sqlMap.queryForList("AgregarRegistroCot.selectMaxIdsif016");
            if (datos != null && datos.size() > 0) {

                idSif = (String) datos.get(0);
                sif016.setIdsif016(Long.parseLong(idSif));

            } else {

                respuesta.setCodRespuesta(99);
                return respuesta;
            }

            datos = sqlMap.queryForList("AgregarRegistroCot.obtenerDataTabla016", sif016);

            if (datos != null && datos.size() > 0) {

                lista = (LinSif016VO) datos.get(0);

            } else {

                respuesta.setCodRespuesta(99);
                return respuesta;

            }

            long id_sif016 = Long.parseLong(idSif) + 1;

            linsif016.setId_maestro_sivegam(lista.getId_maestro_sivegam());
            linsif016.setFecha_proceso(lista.getFecha_proceso());
            linsif016.setCodigo_entidad(lista.getCodigo_entidad());
            linsif016.setCodigo_archivo(lista.getCodigo_archivo());
            linsif016.setFlag_reg_eliminado(0);
            linsif016.setFlag_reg_modificado(0);
            linsif016.setId_sif016(id_sif016);
            linsif016.setMes_recaudacion(lista.getMes_recaudacion());
            linsif016.setMes_remuneracion(lista.getMes_remuneracion());
            linsif016.setCod_tipo_declaracion(lista.getCod_tipo_declaracion());
            linsif016.setCampo_contingencia(0);
            linsif016.setFuente_de_origen(0);

            sqlMap.insert("AgregarRegistroCot.insertNewSif016", linsif016);

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

            case 16:
                datos = sqlMap.queryForList("AgregarRegistroCot.obtenerFechaProcesoSif016");
                break;

            case 17:
                datos = sqlMap.queryForList("AgregarRegistroCot.obtenerFechaProcesoSif017");
                break;

            case 18:
                datos = sqlMap.queryForList("AgregarRegistroCot.obtenerFechaProcesoSif018");
                break;

            case 19:
                datos = sqlMap.queryForList("AgregarRegistroCot.obtenerFechaProcesoSif019");
                break;

            default:
                respuesta.setCodRespuesta(99);
                return respuesta;
            }

            if (datos != null && datos.size() > 0) {
                if (datos.get(0) == null) {

                    fechaProcesamiento = "";

                } else {

                    fechaProcesamiento = (String) datos.get(0);
                    StringBuffer sb = new StringBuffer("");

                    anio = fechaProcesamiento.substring(0, 4);
                    mes = fechaProcesamiento.substring(4, 6);

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
}
