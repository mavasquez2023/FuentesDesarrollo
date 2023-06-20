package cl.laaraucana.sms.ibatis.dao;

import cl.laaraucana.sms.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.sms.ibatis.model.Sistema;
import com.ibatis.sqlmap.client.SqlMapClient;
import org.apache.log4j.Logger;

public class SistemaDAO {
    protected static Logger logger = Logger.getLogger("UsuarioDAO");

    public Sistema selectSistema(Sistema sistema) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in selectSistema");
        }
        try {
            Sistema result = (Sistema) sqlMap.queryForObject("sistema.selectSistema", sistema);
            if (result != null) {
                return result;
            }
        } catch (Exception e) {
            logger.error("Error selecting selectSistema", e);
        }
        return null;
    }
}
