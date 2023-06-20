package cl.laaraucana.sms.ibatis.dao;

import org.apache.log4j.Logger;

import cl.laaraucana.sms.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.sms.ibatis.model.EstadoSMS;
import cl.laaraucana.sms.ibatis.model.EstadoURL;

import com.ibatis.sqlmap.client.SqlMapClient;

import java.util.List;

public class EstadoURLDAO {
    protected static Logger logger = Logger.getLogger("EstadoURLDAO");

    public void saveEstadoURL(EstadoURL estadoURL) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in saveEstadoURL");
        }
        try {
            sqlMap.insert("estadoURL.insertEstadoURL", estadoURL);
        } catch (Exception e) {
            logger.error("Error saving EstadoURL", e);
        }
    }

    public void saveZeroClicksEstadoURL(EstadoURL estadoURL) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in saveZeroClicksEstadoURL");
        }
        try {
            sqlMap.insert("estadoURL.insertZeroClicksEstadoURL", estadoURL);
        } catch (Exception e) {
            logger.error("Error saving saveZeroClicksEstadoURL", e);
        }
    }

    public boolean updateRetriesEstadoURL(EstadoURL estadoURL) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in updateRetriesEstadoURL");
        }
        try {
            sqlMap.update("estadoURL.updateRetriesEstadoURL", estadoURL);
        } catch (Exception e) {
            logger.error("Error updating retries EstadoURL", e);
        }
        return false;
    }

    public EstadoURL selectEstadoURL(String codeURL) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in selectEstadoSMS");
        }
        try {
            return (EstadoURL) sqlMap.queryForObject("estadoURL.selectEstadoURL", codeURL);
        } catch (Exception e) {
            logger.error("Error selecting EstadoURL", e);
        }
        return null;
    }

    public EstadoSMS selectEstadoSMS(String codeURL) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in selectEstadoSMS");
        }
        try {
            return (EstadoSMS) sqlMap.queryForObject("estadoURL.selectEstadoSMS", codeURL);
        } catch (Exception e) {
            logger.error("Error selecting EstadoSMS", e);
        }
        return null;
    }

    public String selectUsernameEstadoURL(String codeURL) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in selectUsernameEstadoURL");
        }
        try {
            return (String) sqlMap.queryForObject("estadoURL.selectUsernameEstadoURL", codeURL);
        } catch (Exception e) {
            logger.error("Error selecting username for EstadoURL", e);
        }
        return null;
    }

    public String selectCountEstadoURL(String codeURL) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in selectCountEstadoURL");
        }
        try {
            return (String) sqlMap.queryForObject("estadoURL.selectCountEstadoURL", codeURL);
        } catch (Exception e) {
            logger.error("Error verifying codeURL for EstadoURL", e);
        }
        return null;
    }

    public List<EstadoURL> selectListEstadoSMSForUpdateStatusURL(int maxRetries) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in selectListEstadoSMSForUpdateStatusURL");
        }
        try {
            @SuppressWarnings("unchecked")
            List<EstadoURL> result = sqlMap.queryForList("estadoURL.selectListEstadoSMSForUpdateStatusURL", maxRetries);
            return result;

        } catch (Exception e) {
            logger.error("Error selecting List<EstadoURL>", e);
        }
        return null;
    }

    public boolean updateEstadoURL(EstadoURL estadoURL) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in updateEstadoURL");
        }
        try {
            sqlMap.update("estadoURL.updateEstadoURL", estadoURL);
        } catch (Exception e) {
            logger.error("Error updating EstadoURL", e);
        }
        return false;
    }
}
