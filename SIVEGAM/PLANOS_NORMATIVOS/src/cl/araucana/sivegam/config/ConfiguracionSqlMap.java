package cl.araucana.sivegam.config;

import java.io.IOException;
import java.io.Reader;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class ConfiguracionSqlMap {

    public static SqlMapClient sqlMap;
    private static ConfiguracionSqlMap instancia;

    public ConfiguracionSqlMap configuracionSqlMap() {
        if (instancia == null)
            instancia = new ConfiguracionSqlMap();
        return instancia;
    }

    public static SqlMapClient cargarSqlMap() {

        if (sqlMap == null) {

            String resource = "cl/araucana/sivegam/config/sqlMap-config.xml";
            Reader reader = null;

            try {
                reader = Resources.getResourceAsReader(resource);
                sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
                return sqlMap;

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            return sqlMap;
        }
        return null;

    }

}
