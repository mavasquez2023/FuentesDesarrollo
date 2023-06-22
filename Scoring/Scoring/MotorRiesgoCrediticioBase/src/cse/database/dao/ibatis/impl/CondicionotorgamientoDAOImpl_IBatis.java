package cse.database.dao.ibatis.impl;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import cse.database.dao.CondicionotorgamientoDAO;
import cse.database.dao.exception.DAOException;
import cse.database.objects.Condicionotorgamiento;

public class CondicionotorgamientoDAOImpl_IBatis implements CondicionotorgamientoDAO {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table MotorCreditScoring.dbo.CondicionOtorgamiento
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    private SqlMapClient sqlMapClient;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MotorCreditScoring.dbo.CondicionOtorgamiento
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public CondicionotorgamientoDAOImpl_IBatis(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MotorCreditScoring.dbo.CondicionOtorgamiento
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public int deleteByPrimaryKey(Integer idcondicion) throws DAOException {
        Condicionotorgamiento key = new Condicionotorgamiento();
        key.setIdcondicion(idcondicion);
        int rows;
		try {
			rows = sqlMapClient.delete("MotorCreditScoring_dbo_CondicionOtorgamiento.ibatorgenerated_deleteByPrimaryKey", key);
		} catch (SQLException e) {
			throw new DAOException(e);			
		}
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MotorCreditScoring.dbo.CondicionOtorgamiento
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public void insert(Condicionotorgamiento record) throws DAOException {
        try {
			sqlMapClient.insert("MotorCreditScoring_dbo_CondicionOtorgamiento.ibatorgenerated_insert", record);
		} catch (SQLException e) {
			throw new DAOException(e);			
		}
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MotorCreditScoring.dbo.CondicionOtorgamiento
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public Condicionotorgamiento selectByPrimaryKey(Integer idcondicion) throws DAOException {
        Condicionotorgamiento key = new Condicionotorgamiento();
        key.setIdcondicion(idcondicion);
        Condicionotorgamiento record;
		try {
			record = (Condicionotorgamiento) sqlMapClient.queryForObject("MotorCreditScoring_dbo_CondicionOtorgamiento.ibatorgenerated_selectByPrimaryKey", key);
		} catch (SQLException e) {
			throw new DAOException(e);			
		}
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MotorCreditScoring.dbo.CondicionOtorgamiento
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    public int updateByPrimaryKey(Condicionotorgamiento record) throws DAOException {
        int rows;
		try {
			rows = sqlMapClient.update("MotorCreditScoring_dbo_CondicionOtorgamiento.ibatorgenerated_updateByPrimaryKey", record);
		} catch (SQLException e) {
			throw new DAOException(e);			
		}
        return rows;
    }
	

  
  
}