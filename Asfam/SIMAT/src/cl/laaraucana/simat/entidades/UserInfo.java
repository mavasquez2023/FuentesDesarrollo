package cl.laaraucana.simat.entidades;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userID;
	private boolean blocked;
	private boolean mustChangePassword;
	private Timestamp lastPasswordChange;
	private int loginCount;
	private int failedLoginCount;
	private int repeatedFailedLoginCount;
	private Timestamp lastLogin;
	private Timestamp lastFailedLogin;

	public String getUserID() {
		return this.userID;
	}

	public boolean isBlocked() {
		return this.blocked;
	}

	public boolean mustChangePassword() {
		return this.mustChangePassword;
	}

	public Timestamp getLastPasswordChange() {
		return this.lastPasswordChange;
	}

	public int getLoginCount() {
		return this.loginCount;
	}

	public int getFailedLoginCount() {
		return this.failedLoginCount;
	}

	public int getRepeatedFailedLoginCount() {
		return this.repeatedFailedLoginCount;
	}

	public Timestamp getLastLogin() {
		return this.lastLogin;
	}

	public Timestamp getLastFailedLogin() {
		return this.lastFailedLogin;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public void setMustChangePassword(boolean mustChangePassword) {
		this.mustChangePassword = mustChangePassword;
	}

	public void setLastPasswordChange(Timestamp lastPasswordChange) {
		this.lastPasswordChange = lastPasswordChange;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public void setFailedLoginCount(int failedLoginCount) {
		this.failedLoginCount = failedLoginCount;
	}

	public void setRepeatedFailedLoginCount(int repeatedFailedLoginCount) {
		this.repeatedFailedLoginCount = repeatedFailedLoginCount;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public void setLastFailedLogin(Timestamp lastFailedLogin) {
		this.lastFailedLogin = lastFailedLogin;
	}

	public String toString() {
		return "user ID               = " + getUserID() + "\n" + "blocked               = " + isBlocked() + "\n" + "need change password  = " + mustChangePassword() + "\n"
				+ "last password change  = " + getLastPasswordChange() + "\n" + "login count           = " + getLoginCount() + "\n" + "failed login count    = " + getFailedLoginCount() + "\n"
				+ "repeated failed login = " + getRepeatedFailedLoginCount() + "\n" + "last login            = " + getLastLogin() + "\n" + "last failed login     = " + getLastFailedLogin() + "\n";
	}
}