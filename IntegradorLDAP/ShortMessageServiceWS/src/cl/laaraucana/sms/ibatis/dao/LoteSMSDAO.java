package cl.laaraucana.sms.ibatis.dao;

import cl.laaraucana.sms.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.sms.ibatis.model.LoteSMS;
import cl.laaraucana.sms.ibatis.model.Sistema;
import cl.laaraucana.sms.ibatis.model.filter.LoteSMSFilter;
import com.ibatis.sqlmap.client.SqlMapClient;
import org.apache.log4j.Logger;

import java.util.List;

public class LoteSMSDAO {
    protected static Logger logger = Logger.getLogger("LoteSMSDAO");

    public int selectCountUnprocessedBatchSize(LoteSMSFilter loteSMSFilter) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in selectListSistema");
        }
        try {
            if (null != loteSMSFilter.getSistema()) {
                return (Integer) sqlMap.queryForObject("loteSMS.selectCountUnprocessedBatchSizeFilteredByEstadoAndSistema", loteSMSFilter);
            } else {
                return (Integer) sqlMap.queryForObject("loteSMS.selectCountUnprocessedBatchSizeFilteredByEstado", loteSMSFilter);
            }
        } catch (Exception e) {
            logger.error("Error selecting List<Sistema>", e);
        }
        return 0;
    }

    @SuppressWarnings("unchecked")
    public List<LoteSMS> selectListLoteSMS(LoteSMSFilter loteSMSFilter) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in selectListLoteSMS");
        }
        try {
            List<LoteSMS> result;
            String sistema = loteSMSFilter.getSistema();
            if (null != sistema && !sistema.equals("")) {
                result = sqlMap.queryForList("loteSMS.selectListLoteSMSWhereEstadoAndSistemaEqualTo", loteSMSFilter);
            } else {
                result = sqlMap.queryForList("loteSMS.selectListLoteSMSWhereEstadoEqualTo", loteSMSFilter);
            }
            return result;
        } catch (Exception e) {
            logger.error("Error selecting List<LoteSMS>", e);
        }
        return null;
    }

    public List<Sistema> selectListSistema() throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in selectListSistema");
        }
        try {
            @SuppressWarnings("unchecked")
            List<Sistema> result = sqlMap.queryForList("loteSMS.selectListSistema", null);
            return result;

        } catch (Exception e) {
            logger.error("Error selecting List<Sistema>", e);
        }
        return null;
    }

    public int deleteLoteSMS(int id) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in deleteLoteSMS");
        }
        try {
            return sqlMap.delete("loteSMS.deleteLoteSMSWhereIdEqualTo", id);
        } catch (Exception e) {
            logger.error("Error deleting LoteSMS", e);
        }
        return 0;
    }

    public int deleteLoteSMS(String estado) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in deleteLoteSMS");
        }
        try {
            return sqlMap.delete("loteSMS.deleteLoteSMS", estado);
        } catch (Exception e) {
            logger.error("Error deleting from LoteSMS", e);
        }
        return 0;
    }

    public int updateEstado(String estado) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in updateEstado");
        }
        try {
            return sqlMap.update("loteSMS.updateEstado", estado);
        } catch (Exception e) {
            logger.error("Error updating LoteSMS", e);
        }
        return 0;
    }

    public int updateEstado(LoteSMSFilter loteSMSFilter) throws Exception {
        SqlMapClient sqlMap;
        try {
            sqlMap = MyClassSqlConfig.getSqlMapInstance();
        } catch (Exception e) {
            throw new Exception("Error creating getSqlMapInstance in updateEstado");
        }
        try {
            return sqlMap.update("loteSMS.updateLoteSMSWhereEstadoEqualToAndExistsOnEstadoLoteSMS", loteSMSFilter);
        } catch (Exception e) {
            logger.error("Error updating LoteSMS", e);
        }
        return 0;
    }
}
