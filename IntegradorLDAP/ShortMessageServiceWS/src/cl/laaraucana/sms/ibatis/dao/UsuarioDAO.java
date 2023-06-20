package cl.laaraucana.sms.ibatis.dao;

import org.apache.log4j.Logger;

import cl.laaraucana.sms.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.sms.ibatis.model.Usuario;

import com.ibatis.sqlmap.client.SqlMapClient;

public class UsuarioDAO {
    protected static Logger logger = Logger.getLogger("UsuarioDAO");

    public Usuario selectUsuario(Usuario usuario) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in selectUsuario");
        }
        try {
            Usuario result = (Usuario) sqlMap.queryForObject("usuario.selectUsuario", usuario);
            if (result != null) {
                return result;
            }
        } catch (Exception e) {
            logger.error("Error selecting selectUsuario", e);
        }
        return null;
    }
}
