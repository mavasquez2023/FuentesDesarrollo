package cse.model.service.impl;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import cse.model.businessobject.UsuarioSistema;
import cse.model.exception.ScoringModuleException;
import cse.model.exception.UsernameNotExistException;

//import model.dao.UserDao;
//import model.dao.exception.DAOException;
//import model.dao.manager.DAOManager;


public class SecurityServiceImpl implements cse.model.service.SecurityService {
	//the logger for this class
	private static Logger logger = Logger.getLogger(SecurityServiceImpl.class.getName());
		
	//TODO change UserDao into UserDAO. Create tables 
//	private UserDao userDao;
	
	public SecurityServiceImpl() throws ScoringModuleException{
//		try {
//			userDao = new DAOFactory().getUserDao();
//		} catch (DAOException e) {
//			this.logger.log(Level.WARNING, "Problems getting UsuarioSistema DAO >> SQLException", e);
//			throw new ScoringModuleException("Problems getting UsuarioSistema DAO : ", e);
//		}
	}
	
//	public void setUserDao(UserDao newUserDao) {
//		this.userDao = newUserDao;
//	}
	
	/**
	 * @see UserService#login(String, String)
	 */
	public UsuarioSistema login(String username, String password) throws ScoringModuleException {
		try {
//			UsuarioSistema user = this.userDao.getUser(username);
//			
//			if (user != null) {
//				if (!user.getPassword().equals(password)) {
//					user = null;
//				}
//			}			
//			return user;
			if (username.equalsIgnoreCase("pepito") && password.equalsIgnoreCase("pepito"))
				return new UsuarioSistema();
//		} catch (SQLException he) {
//			this.logger.log(Level.WARNING, "Could not login>>SQLException", he);
//			throw new UsernameNotExistException(username);
		} catch (Exception e) {
			this.logger.log(Level.WARNING, "Could not login", e);
			throw new ScoringModuleException("Could not login>>Exception", e);
		}
		return null;
	}
	

}
