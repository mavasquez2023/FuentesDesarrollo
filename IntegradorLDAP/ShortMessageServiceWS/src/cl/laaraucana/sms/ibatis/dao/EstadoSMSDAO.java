package cl.laaraucana.sms.ibatis.dao;

import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.sms.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.sms.ibatis.model.EstadoSMS;

import com.ibatis.sqlmap.client.SqlMapClient;

public class EstadoSMSDAO {
    protected static Logger logger = Logger.getLogger("EstadoSMSDAO");

    public void saveEstadoSMS(EstadoSMS estadoSMS) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in saveEstadoSMS");
        }
        try {
            sqlMap.insert("estadoSMS.insertEstadoSMS", estadoSMS);
        } catch (Exception e) {
            logger.error("Error saving EstadoSMS", e);
        }
    }

    public boolean updateRetriesEstadoSMS(EstadoSMS estadoSMS) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in updateRetriesEstadoSMS");
        }
        try {
            sqlMap.update("estadoSMS.updateRetriesEstadoSMS", estadoSMS);
        } catch (Exception e) {
            logger.error("Error updating retries EstadoSMS", e);
        }
        return false;
    }

    public int updateEstadoSMS(EstadoSMS estadoSMS) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in updateEstadoSMS");
        }
        try {
            return sqlMap.update("estadoSMS.updateEstadoSMS", estadoSMS);
        } catch (Exception e) {
            logger.error("Error updating EstadoSMS", e);
        }
        return 0;
    }

    public EstadoSMS selectEstadoSMS(String codeSMS) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in selectEstadoSMS");
        }
        try {
            return (EstadoSMS) sqlMap.queryForObject("estadoSMS.selectEstadoSMS", codeSMS);
        } catch (Exception e) {
            logger.error("Error selecting EstadoSMS", e);
        }
        return null;
    }

    public List<EstadoSMS> selectListEstadoSMSForCheckStatusSMS() throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in selectListEstadoSMSForCheckStatusSMS");
        }
        try {
            @SuppressWarnings("unchecked")
            List<EstadoSMS> result = sqlMap.queryForList("estadoSMS.selectListEstadoSMSForCheckStatusSMS", null);
            return result;

        } catch (Exception e) {
            logger.error("Error selecting List<EstadoSMS>", e);
        }
        return null;
    }

    public List<EstadoSMS> selectListEstadoSMSForCheckStatusURL() throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in selectListEstadoSMSForCheckStatusURL");
        }
        try {
            @SuppressWarnings("unchecked")
            List<EstadoSMS> result = sqlMap.queryForList("estadoSMS.selectListEstadoSMSForCheckStatusURL", null);
            return result;

        } catch (Exception e) {
            logger.error("Error selecting List<EstadoSMS>", e);
        }
        return null;
    }
}
