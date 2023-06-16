package cl.araucana.sivegam.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.araucana.sivegam.config.ConfiguracionSqlMap;
import cl.araucana.sivegam.helper.Helper;
import cl.araucana.sivegam.vo.LinSif011VO;
import cl.araucana.sivegam.vo.LinSif012VO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.Sif011VO;
import cl.araucana.sivegam.vo.Sif012VO;

public class GenerarListadoErroresDAO {

    public static LinSif011VO[] obtenerDataRetroSif011(String periodo, String flag) {

        List datos = null;
        Sif011VO sif011 = new Sif011VO();
        LinSif011VO[] linSif011 = new LinSif011VO[0];
        LinSif011VO[] resp = null;

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif011.setPeriodoArchivo(Integer.parseInt(periodo));
        sif011.setFlagModificado(Integer.parseInt(flag));

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("svListadoErrores.selectErroresSif011", sif011);
            if (datos != null && datos.size() > 0) {
                resp = (LinSif011VO[]) datos.toArray(new LinSif011VO[datos.size()]);
                for (int i = 0; i < resp.length; i++) {

                    String montoBeneficio = Helper.separadorDeMiles(Long.toString(resp[i].getMonto_beneficio()));

                    resp[i].setMontoBeneficioMiles(montoBeneficio);

                    return resp;
                }

            } else {
                return linSif011;
            }

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

        return linSif011;
    }

    public static LinSif012VO[] obtenerDataRetroSif012(String periodo, String flag) {

        List datos = null;
        Sif012VO sif012 = new Sif012VO();
        LinSif012VO[] linSif012 = new LinSif012VO[0];
        LinSif012VO[] resp = null;

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif012.setPeriodoArchivo(Integer.parseInt(periodo));
        sif012.setFlagModificado(Integer.parseInt(flag));

        try {
            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("svListadoErrores.selectErroresSif012", sif012);
            if (datos != null && datos.size() > 0) {
                resp = (LinSif012VO[]) datos.toArray(new LinSif012VO[datos.size()]);
                for (int i = 0; i < resp.length; i++) {

                    String montoBeneficioRetro = Helper.separadorDeMiles(Long.toString(resp[i].getMonto_beneficio()));

                    resp[i].setMontoBeneficioMiles(montoBeneficioRetro);
                }

                return resp;

            } else {

                return linSif012;
            }

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

    public static RespuestaVO eliminarDatoErroneoSif011(long correlativo) {

        RespuestaVO respuesta = new RespuestaVO();
        Sif011VO sif011VO = new Sif011VO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif011VO.setIdsif011(correlativo);

        try {
            sqlMap.startTransaction(0);
            sqlMap.update("svListadoErrores.updateSif011porId", sif011VO);
            respuesta.setCodRespuesta(0);

            return respuesta;

        } catch (SQLException e) {
            e.printStackTrace();
            respuesta.setMsgRespuesta(e.getMessage());
            respuesta.setCodRespuesta(99);
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                respuesta.setMsgRespuesta(e.getMessage());
                respuesta.setCodRespuesta(99);
                e.printStackTrace();
            }

        }

        return respuesta;
    }

    public static RespuestaVO eliminarDatoErroneoSif012(long correlativo) {

        RespuestaVO respuesta = new RespuestaVO();
        Sif012VO sif012VO = new Sif012VO();
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        sif012VO.setIdsif012(correlativo);

        try {
            sqlMap.startTransaction(0);
            sqlMap.update("svListadoErrores.updateSif012porId", sif012VO);
            respuesta.setCodRespuesta(0);

            return respuesta;

        } catch (SQLException e) {
            e.printStackTrace();
            respuesta.setMsgRespuesta(e.getMessage());
            respuesta.setCodRespuesta(99);
        } finally {

            try {
                sqlMap.endTransaction();
            } catch (SQLException e) {
                respuesta.setMsgRespuesta(e.getMessage());
                respuesta.setCodRespuesta(99);
                e.printStackTrace();
            }

        }

        return respuesta;
    }
}
