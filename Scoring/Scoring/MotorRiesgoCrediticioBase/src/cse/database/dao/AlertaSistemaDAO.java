package cse.database.dao;

import cse.database.dao.exception.DAOException;


public interface AlertaSistemaDAO {

	public int insert(String idSolicitud, String component, String bussMessageHeader, String bussMessageDetail, String techMessage) throws DAOException;

}
