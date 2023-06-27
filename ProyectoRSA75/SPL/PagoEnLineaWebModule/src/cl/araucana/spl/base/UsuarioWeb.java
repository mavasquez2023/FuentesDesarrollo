package cl.araucana.spl.base;

import java.util.Hashtable;
import java.util.Map;

import com.bh.talon.User;

public class UsuarioWeb implements User  {
	private String login;
	private String name;

	Map links=new Hashtable(); 
	
	public UsuarioWeb(String login, String name){
		this.login = login;
		this.name = name;
	}

	public Object getLink(String name){
		return links.get(name);
	}
	public void setLink(String name, Object value){
		links.put(name,value);
	}
	
	/**
	 * Gets the login
	 * @return Returns a String
	 */
	public String getLogin() {
		return login;
	}
	
	/**
	 * Gets the name
	 * @return Returns a String
	 */
	public String getName() {
		return name;
	}	
	
	/* (non-Javadoc)
	 * @see com.bh.talon.User#getUserReference()
	 */
	public Object getUserReference() {
		return login;
	}	

	public String toString() {
		return UsuarioWeb.class.getName()+"[login:"+login+", name=" + name + "]";
	}

}