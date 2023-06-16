package cl.araucana.cotfonasa.config;

import java.io.IOException;
import java.io.Reader;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class ConfiguracionSqlMap {

    public static SqlMapClient sqlMap = null;
    private static ConfiguracionSqlMap instancia = null;

    public ConfiguracionSqlMap ConfiguracionSqlMap(){
        if(instancia == null)
            instancia = new ConfiguracionSqlMap();
        return instancia;
    }

    public static SqlMapClient cargarSqlMap()
    {
        if(sqlMap == null)
        {
            String resource = "cl/araucana/cotfonasa/config/sqlMap-config.xml";
            Reader reader = null;

            try {
                reader = Resources.getResourceAsReader(resource);
                return sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        else{
            return sqlMap;
        }
        return null;

    }

}
