package cl.araucana.sivegam.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.araucana.sivegam.config.ConfiguracionSqlMap;
import cl.araucana.sivegam.vo.LinAfcAFF01VO;

public class GenerarPlanosAFCDAO {

    public static LinAfcAFF01VO[] generarPlanosAfcRetroactivo() {

        List datos = null;
        LinAfcAFF01VO[] resp = new LinAfcAFF01VO[0];
        LinAfcAFF01VO[] listAfc = null;

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("planosAFC.selectAfcRetroactivo");
            if (datos != null && datos.size() > 0) {

                listAfc = (LinAfcAFF01VO[]) datos.toArray(new LinAfcAFF01VO[datos.size()]);
                return listAfc;
            } else {
                return resp;
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
        return resp;
    }

    public static LinAfcAFF01VO[] generarPlanosAfcMensual() {

        List datos = null;
        LinAfcAFF01VO[] resp = new LinAfcAFF01VO[0];
        LinAfcAFF01VO[] listAfc = null;

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("planosAFC.selectAfcMensual");
            if (datos != null && datos.size() > 0) {

                listAfc = (LinAfcAFF01VO[]) datos.toArray(new LinAfcAFF01VO[datos.size()]);
                return listAfc;
            } else {
                return resp;
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
        return resp;
    }
}
