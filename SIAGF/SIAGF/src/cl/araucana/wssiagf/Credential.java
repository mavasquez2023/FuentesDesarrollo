

package cl.araucana.wssiagf;


import java.io.Serializable;


public class Credential implements Serializable {

	private int id;
	private String userName;
	private String password;

	public Credential() {
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String toString() {
		return "id=" + getID() + " userName=" + userName + " password=" + password;
	}
}
