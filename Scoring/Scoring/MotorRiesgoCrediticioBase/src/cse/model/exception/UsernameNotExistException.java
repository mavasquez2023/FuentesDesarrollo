package cse.model.exception;
/**
 * Exception thrown when a user tries to use a non-existing username to log in.
 * 
 * @see ScoringModuleException
 */
public class UsernameNotExistException extends ScoringModuleException {
	private String username;

	/**
	 * Constructor with non-existing username.
	 * 
	 * @param newUserName the non-existing username
	 */
	public UsernameNotExistException(String newUsername) {
		super("Username " + newUsername + " does not exist");
		this.username = newUsername;
	}
	
	public String getUsername() {
		return this.username;
	}
}
