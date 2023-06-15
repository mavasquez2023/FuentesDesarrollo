package cl.araucana.sivegam.dao;

import java.sql.SQLException;
import java.util.List;
import cl.araucana.sivegam.config.ConfiguracionSqlMap;
import cl.araucana.sivegam.vo.LinCesantia041VO;
import cl.araucana.sivegam.vo.LinCesantia042VO;
import cl.araucana.sivegam.vo.LinCesantia043VO;
import cl.araucana.sivegam.vo.LinCesantia044VO;

import com.ibatis.sqlmap.client.SqlMapClient;

public class GenerarPlanoCesantiaDAO {

    public static LinCesantia041VO[] generarPlanoCesantia041() {

        List datos = null;
        LinCesantia041VO[] resp = new LinCesantia041VO[0];
        LinCesantia041VO[] listCesantia = null;

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("planosCesantia.selectSCF041");
            if (datos != null && datos.size() > 0) {

                listCesantia = (LinCesantia041VO[]) datos.toArray(new LinCesantia041VO[datos.size()]);
                return listCesantia;
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

    public static LinCesantia042VO[] generarPlanoCesantia042() {

        List datos = null;
        LinCesantia042VO[] resp = new LinCesantia042VO[0];
        LinCesantia042VO[] listCesantia = null;

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("planosCesantia.selectSCF042");
            if (datos != null && datos.size() > 0) {

                listCesantia = (LinCesantia042VO[]) datos.toArray(new LinCesantia042VO[datos.size()]);
                return listCesantia;
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

    public static LinCesantia043VO[] generarPlanoCesantia043() {

        List datos = null;
        LinCesantia043VO[] resp = new LinCesantia043VO[0];
        LinCesantia043VO[] listCesantia = null;

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("planosCesantia.selectSCF043");
            if (datos != null && datos.size() > 0) {

                listCesantia = (LinCesantia043VO[]) datos.toArray(new LinCesantia043VO[datos.size()]);
                return listCesantia;
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

    public static LinCesantia044VO[] generarPlanoCesantia044() {

        List datos = null;
        LinCesantia044VO[] resp = new LinCesantia044VO[0];
        LinCesantia044VO[] listCesantia = null;

        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("planosCesantia.selectSCF044");
            if (datos != null && datos.size() > 0) {

                listCesantia = (LinCesantia044VO[]) datos.toArray(new LinCesantia044VO[datos.size()]);
                return listCesantia;
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
