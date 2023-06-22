package cse.model.service;

import cse.model.businessobject.UsuarioSistema;
import cse.model.exception.ScoringModuleException;


/**
 * The user business service interface.
 * <p>
 * It contains all user management related business logic.
 * 
 */
public interface SecurityService {
	/**
	 * Login a user using username and password.
	 * 
	 * @param username the username to be used
	 * @param password the password to be used
	 * @return the user associated with the username and password
	 * @throws ScoringModuleException
	 */
	public UsuarioSistema login(String username, String password) throws ScoringModuleException;
	
	
}
