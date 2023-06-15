package cl.araucana.sivegam.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.araucana.sivegam.config.ConfiguracionSqlMap;
import cl.araucana.sivegam.vo.LinSif016VO;
import cl.araucana.sivegam.vo.LinSif017VO;
import cl.araucana.sivegam.vo.LinSif018VO;
import cl.araucana.sivegam.vo.LinSif019VO;
import cl.araucana.sivegam.vo.PlanoVO;

public class GenerarPlanosCotizacionesDAO {

    public static LinSif016VO[] consultaRegistrosSif016(PlanoVO vo) {

        List datos = null;
        LinSif016VO[] resp = new LinSif016VO[0];
        LinSif016VO[] linsif016 = null;
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("GenerarPlanoCotizaciones.selectSif016",vo);
            if (datos != null && datos.size() > 0) {

                linsif016 = (LinSif016VO[]) datos.toArray(new LinSif016VO[datos.size()]);
                return linsif016;
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

    public static LinSif017VO[] consultaRegistrosSif017(PlanoVO vo) {

        List datos = null;
        LinSif017VO[] resp = new LinSif017VO[0];
        LinSif017VO[] linsif017 = null;
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("GenerarPlanoCotizaciones.selectSif017",vo);
            if (datos != null && datos.size() > 0) {

                linsif017 = (LinSif017VO[]) datos.toArray(new LinSif017VO[datos.size()]);
                return linsif017;
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

    public static LinSif018VO[] consultaRegistrosSif018(PlanoVO vo) {

        List datos = null;
        LinSif018VO[] resp = new LinSif018VO[0];
        LinSif018VO[] linsif018 = null;
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("GenerarPlanoCotizaciones.selectSif018",vo);
            if (datos != null && datos.size() > 0) {

                linsif018 = (LinSif018VO[]) datos.toArray(new LinSif018VO[datos.size()]);
                return linsif018;
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

    public static LinSif019VO[] consultaRegistrosSif019(PlanoVO vo) {

        List datos = null;
        LinSif019VO[] resp = new LinSif019VO[0];
        LinSif019VO[] linsif019 = null;
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("GenerarPlanoCotizaciones.selectSif019",vo);
            if (datos != null && datos.size() > 0) {

                linsif019 = (LinSif019VO[]) datos.toArray(new LinSif019VO[datos.size()]);
                return linsif019;
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
