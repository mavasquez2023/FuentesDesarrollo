/*
 * @(#) MyClassSqlConfig.java 1.0 04/06/2013
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 *
 *
 */
package cl.laaraucana.sms.ibatis.config;

import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class MyClassSqlConfig {
    private static MyClassSqlConfig instance = null;
    private static final SqlMapClient sqlMap;

    static {
        try {
            String resource = "cl/laaraucana/sms/ibatis/config/IbatisConfig.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
        } catch (Exception e) {
            throw new RuntimeException("Error MyClassSqlConfig: " + e);
        }
    }

    public static SqlMapClient getSqlMapInstance() {
        if (instance == null)
            instance = new MyClassSqlConfig();
        return sqlMap;
    }
}