package cl.araucana.independientes.config;

import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class ConfiguracionSqlMapCajas {

    public static SqlMapClient sqlMap = null;
    //private static Log log = LogFactory.getLog(ConfiguracionSqlMap.class);
    private static ConfiguracionSqlMapCajas instancia = null;

    public ConfiguracionSqlMapCajas configuracionSqlMapCajas(){
        if(instancia == null)
            instancia = new ConfiguracionSqlMapCajas();
        return instancia;
    }

    public static SqlMapClient cargarSqlMap(){

        if(sqlMap == null){

            String resource = "cl/araucana/independientes/config/sqlMap2-config.xml";
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
