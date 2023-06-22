package cl.araucana.sivegam.dao;

import java.sql.SQLException;
import java.util.List;

import cl.araucana.sivegam.config.ConfiguracionSqlMap;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.vo.LinSif011VO;
import cl.araucana.sivegam.vo.LinSif012VO;
import cl.araucana.sivegam.vo.LinSif014VO;
import cl.araucana.sivegam.vo.PlanoVO;

import com.ibatis.sqlmap.client.SqlMapClient;

public class GenerarPlanosDivisionPrevisionalDAO {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    public static LinSif011VO[] consultaRegistrosSif011(PlanoVO vo) {

        List datos = null;
        LinSif011VO[] resp = new LinSif011VO[0];
        LinSif011VO[] linsif011 = null;
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("GenerarPlanoDivisionPrevisional.selectSif011",vo);

            //logger.debug("largo de datos: " + datos.size());
            if (datos != null && datos.size() > 0) {

                linsif011 = (LinSif011VO[]) datos.toArray(new LinSif011VO[datos.size()]);
                return linsif011;
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

    public static LinSif012VO[] consultaRegistrosSif012(PlanoVO vo) {

        List datos = null;
        LinSif012VO[] resp = new LinSif012VO[0];
        LinSif012VO[] linsif012 = null;
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("GenerarPlanoDivisionPrevisional.selectSif012",vo);
            if (datos != null && datos.size() > 0) {

                linsif012 = (LinSif012VO[]) datos.toArray(new LinSif012VO[datos.size()]);
                return linsif012;
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

    public static LinSif014VO[] consultaRegistrosSif014(PlanoVO vo) {

        List datos = null;
        LinSif014VO[] resp = new LinSif014VO[0];
        LinSif014VO[] linsif014 = null;
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {

            sqlMap.startTransaction(0);
            datos = sqlMap.queryForList("GenerarPlanoDivisionPrevisional.selectSif014",vo);

            //logger.debug("largo de datos: " + datos.size());
            if (datos != null && datos.size() > 0) {

                linsif014 = (LinSif014VO[]) datos.toArray(new LinSif014VO[datos.size()]);
                return linsif014;
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
