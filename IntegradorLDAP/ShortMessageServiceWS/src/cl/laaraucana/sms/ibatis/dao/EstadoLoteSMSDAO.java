package cl.laaraucana.sms.ibatis.dao;

import cl.laaraucana.sms.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.sms.ibatis.model.EstadoLoteSMS;
import cl.laaraucana.sms.ibatis.model.LoteSMSLog;
import cl.laaraucana.sms.ibatis.model.filter.EstadoLoteSMSFilter;
import com.ibatis.sqlmap.client.SqlMapClient;
import org.apache.log4j.Logger;

import java.util.List;

public class EstadoLoteSMSDAO {
    protected static Logger logger = Logger.getLogger("EstadoLoteSMSDAO");

    public void saveEstadoLoteSMS(EstadoLoteSMS estadoLoteSMS) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in saveEstadoLoteSMS");
        }
        try {
            sqlMap.insert("estadoLoteSMS.saveEstadoLoteSMS", estadoLoteSMS);
        } catch (Exception e) {
            logger.error("Error saving saveEstadoLoteSMS", e);
        }
    }

    public List<EstadoLoteSMS> selectListEstadoLoteSMS(EstadoLoteSMSFilter estadoLoteSMSFilter) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in selectListEstadoLoteSMS");
        }
        try {
            @SuppressWarnings("unchecked")
            List<EstadoLoteSMS> result = sqlMap.queryForList("estadoLoteSMS.selectListEstadoLoteSMSWhereCodigoLoteSMSLogAndEstadoEqualTo", estadoLoteSMSFilter);
            return result;

        } catch (Exception e) {
            logger.error("Error selecting List<EstadoLoteSMS>", e);
        }
        return null;
    }

    public boolean updateEstadoLoteSMSStagePreProcessBulk() throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in updateEstadoLoteSMSStagePreProcessBulk");
        }
        try {
            sqlMap.update("estadoLoteSMS.updateEstadoLoteSMSStagePreProcessBulk", null);
        } catch (Exception e) {
            logger.error("Error updating EstadoLoteSMS", e);
        }
        return false;
    }

    public boolean updateCodigoLoteSMS(EstadoLoteSMSFilter estadoLoteSMSFilter) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in updateCodigoLoteSMS");
        }
        try {
            if (null != estadoLoteSMSFilter.getFechaEnvio() && null != estadoLoteSMSFilter.getFechaRecepcion()) {
                sqlMap.update("estadoLoteSMS.updateEstadoLoteSMS", estadoLoteSMSFilter);
            } else {
                sqlMap.update("estadoLoteSMS.updateCodigoLoteSMSWhereCodigoLoteSMSLogEqualTo", estadoLoteSMSFilter);
            }
        } catch (Exception e) {
            logger.error("Error updating EstadoLoteSMS", e);
        }
        return false;
    }

    public boolean loadEstadoLoteSMS(EstadoLoteSMSFilter estadoLoteSMSFilter) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in createEstadoLoteSMS");
        }
        try {
            if (estadoLoteSMSFilter.getMaxBulkSize() > 0) {
                sqlMap.insert("estadoLoteSMS.insertEstadoLoteSMSFromSelectLoteSMSWithMaxBulkSizeLimit", estadoLoteSMSFilter);
            } else {
                sqlMap.insert("estadoLoteSMS.insertEstadoLoteSMSFromSelectLoteSMS", estadoLoteSMSFilter);
            }
            return true;
        } catch (Exception e) {
            logger.error("Error loading table EstadoLoteSMS", e);
        }
        return false;
    }
}