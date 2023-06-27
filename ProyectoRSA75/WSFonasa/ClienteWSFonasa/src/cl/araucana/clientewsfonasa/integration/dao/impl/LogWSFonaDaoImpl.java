package cl.araucana.clientewsfonasa.integration.dao.impl;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import cl.araucana.clientewsfonasa.business.to.LogWSFonasaTO;
import cl.araucana.clientewsfonasa.common.exception.DaoException;
import cl.araucana.clientewsfonasa.common.util.FechaUtil;
import cl.araucana.clientewsfonasa.integration.dao.AbstractDao;
import cl.araucana.clientewsfonasa.integration.dao.LogWSFonaDao;

public class LogWSFonaDaoImpl extends AbstractDao implements LogWSFonaDao {
	public LogWSFonaDaoImpl() {}

	public Integer saveLogWSFona(LogWSFonasaTO logTo) throws DaoException{
		Integer idLog = null;
		String call = "{ call LIEXP.SET_LOGWSFONA(?, ?, ?, ?, ?, ?, ?) }";
		try{
			CallableStatement cstmt = getConnection().prepareCall(call);
			cstmt.setInt(1, logTo.getIdCall().intValue());
			cstmt.setString(2, FechaUtil.formatearFecha("yyyyMMdd", logTo.getFechaHora()));
			cstmt.setString(3, FechaUtil.formatearFecha("HH:mm:ss", logTo.getFechaHora()));
			cstmt.setShort(4, logTo.getCodLog().shortValue());
			cstmt.setString(5, logTo.getDescLog());
			cstmt.registerOutParameter(6, Types.INTEGER);
			cstmt.registerOutParameter(7, Types.INTEGER);
			cstmt.execute();
			
			if(cstmt.getInt(7) > 0){
				idLog = new Integer(cstmt.getInt(6));
			}else{
				throw new DaoException();
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}catch (Exception e){
			e.printStackTrace();
			throw new DaoException();
		}finally{
			closeConnection();
		}
		
		return idLog;
	}
}
