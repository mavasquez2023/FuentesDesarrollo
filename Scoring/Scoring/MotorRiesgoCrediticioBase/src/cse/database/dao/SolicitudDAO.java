package cse.database.dao;

import java.sql.SQLException;

import cse.database.dao.exception.DAOException;
import cse.database.objects.Solicitud;

public interface SolicitudDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MotorCreditScoring.dbo.Solicitud
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    int deleteByPrimaryKey(String idsolicitud) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MotorCreditScoring.dbo.Solicitud
     * @throws DAOException 
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    void insert(Solicitud record) throws SQLException, DAOException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MotorCreditScoring.dbo.Solicitud
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    void insertSelective(Solicitud record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MotorCreditScoring.dbo.Solicitud
     * @throws DAOException 
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    Solicitud selectByPrimaryKey(String idsolicitud) throws DAOException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table MotorCreditScoring.dbo.Solicitud
     * @throws DAOException 
     *
     * @ibatorgenerated Wed Apr 25 16:42:26 ADT 2012
     */
    int updateByPrimaryKey(Solicitud record) throws SQLException, DAOException;

	String createNewSolicitud() throws SQLException, DAOException;
}