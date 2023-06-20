package cl.laaraucana.sms.ibatis.dao;

import cl.laaraucana.sms.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.sms.ibatis.model.EstadoSMS;
import cl.laaraucana.sms.ibatis.model.LoteSMSLog;
import com.ibatis.sqlmap.client.SqlMapClient;
import org.apache.log4j.Logger;

import java.util.List;

public class LoteSMSLogDAO {
    protected static Logger logger = Logger.getLogger("LoteSMSLogDAO");

    public boolean createLoteSMSLog(LoteSMSLog loteSMSLog) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in saveLoteSMSLog");
        }
        try {
            sqlMap.insert("loteSMSLog.insertLoteSMSLogReturningIdentity", loteSMSLog);
            return true;
        } catch (Exception e) {
            logger.error("Error saving LoteSMSLog", e);
        }
        return false;
    }

    public int updateLoteSMSLog(LoteSMSLog loteSMSLog) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in updateLoteSMSLog");
        }
        try {
            return sqlMap.update("loteSMSLog.updateLoteSMSLog", loteSMSLog);
        } catch (Exception e) {
            logger.error("Error saving LoteSMSLog", e);
        }
        return 0;
    }

    public LoteSMSLog selectLoteSMSLog(int id) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in selectLoteSMSLog");
        }
        try {
            return (LoteSMSLog) sqlMap.queryForObject("loteSMSLog.selectLoteSMSLogWhereIdEqualTo", id);
        } catch (Exception e) {
            logger.error("Error selecting LoteSMSLog", e);
        }
        return null;
    }

    public List<LoteSMSLog> selectListLoteSMSLog(String estado) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in selectListLoteSMSLog");
        }
        try {
            @SuppressWarnings("unchecked")
            List<LoteSMSLog> result = sqlMap.queryForList("loteSMSLog.selectLoteSMSLogWhereEstadoEqualTo", estado);
            return result;
        } catch (Exception e) {
            logger.error("Error selecting List<LoteSMSLog>", e);
        }
        return null;
    }

    public int selectMaxId() throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in selectMaxId");
        }
        try {
            return (Integer) sqlMap.queryForObject("loteSMSLog.selectMaxId", null);
        } catch (Exception e) {
            logger.error("Error selecting max value for id", e);
        }
        return 0;
    }

    public int selectCountEstadoLoteSMS(int codigoLoteSMSLog) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in selectCountEstadoLoteSMS");
        }
        try {
            return (Integer) sqlMap.queryForObject("loteSMSLog.selectCountEstadoLoteSMSWhereCodigoLoteSMSLogEqualTo", codigoLoteSMSLog);
        } catch (Exception e) {
            logger.error("Error counting EstadoLoteSMS values for codigoLoteSMSLog", e);
        }
        return 0;
    }

    public List<LoteSMSLog> selectListLoteSMSLog() throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in selectListLoteSMSLog");
        }
        try {
            @SuppressWarnings("unchecked")
            List<LoteSMSLog> result = sqlMap.queryForList("loteSMSLog.selectListLoteSMSLog", null);
            return result;

        } catch (Exception e) {
            logger.error("Error selecting List<LoteSMSLog>", e);
        }
        return null;
    }
}
