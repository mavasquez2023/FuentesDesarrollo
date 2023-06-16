package cl.laaraucana.simat.entidades;

import java.io.Serializable;

public class User implements Serializable, Comparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String sex;

	public User() {
	}

	public User(String id) {
		this.id = id;
	}

	public String getID() {
		return this.id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getFullName() {
		return getFullName(true);
	}

	public String getFullName(boolean nameFirst) {
		if (nameFirst) {
			return getName() + " " + getFirstName() + " " + getLastName();
		}

		return getFirstName() + " " + getLastName() + " " + getName();
	}

	public int hashCode() {
		return this.id.hashCode();
	}

	public boolean equals(Object object) {
		return compareTo(object) == 0;
	}

	public int compareTo(Object object) {
		if (!(object instanceof User)) {
			String message = object != null ? object.toString() : "null";

			throw new IllegalArgumentException(message);
		}

		User user = (User) object;

		return this.id.compareTo(user.id);
	}

	public String toString() {
		return "id = " + getID() + ", " + "name = " + getName() + ", " + "first name = " + getFirstName() + ", " + "last name = " + getLastName() + ", " + "email = " + getEmail() + ", " + "phone = "
				+ getPhone() + ", " + "sex = " + getSex();
	}
}