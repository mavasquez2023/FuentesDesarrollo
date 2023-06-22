package cl.araucana.sivegam.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import com.ibatis.sqlmap.client.SqlMapClient;
import cl.araucana.sivegam.config.ConfiguracionSqlMap;
import cl.araucana.sivegam.helper.Helper;
import cl.araucana.sivegam.helper.IND_Constants;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.vo.LinSif018VO;

public class GeneraRepDivPrevDAO {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    public LinSif018VO[] consultaRegistrosSif018() {

        List datos = null;
        LinSif018VO[] result = null;
        LinSif018VO[] resp = new LinSif018VO[0];
        SqlMapClient sqlMap = ConfiguracionSqlMap.cargarSqlMap();

        try {
            sqlMap.startTransaction(0);

            HashMap parametros = new HashMap();
            parametros.put("SIVEGAM", Helper.getVarPorAmbiente(IND_Constants.Libreria_SIVEGAM));

            //logger.debug("PREPARANDO PARA REALIZAR CONSULTA...");
            datos = sqlMap.queryForList("Sif018Sivegam.obtenerDataTabla018", parametros);
            //logger.debug("largo de datos: " + datos.size());
            if (datos != null && datos.size() > 0) {

                result = (LinSif018VO[]) datos.toArray(new LinSif018VO[datos.size()]);
                //logger.debug("largo de datos: " + result.length);
                return result;
            } else {
                //logger.debug("NO HAY DATOS.");
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

        return result;
    }
}
