package cse.database.dao;

import cse.database.dao.exception.DAOException;
import cse.database.objects.Condicionotorgamiento;

public interface CondicionotorgamientoDAO {

	int deleteByPrimaryKey(Integer idcondicion) throws DAOException;

	void insert(Condicionotorgamiento record) throws DAOException;	

	Condicionotorgamiento selectByPrimaryKey(Integer idcondicion) throws DAOException;

	int updateByPrimaryKey(Condicionotorgamiento record) throws DAOException;
}