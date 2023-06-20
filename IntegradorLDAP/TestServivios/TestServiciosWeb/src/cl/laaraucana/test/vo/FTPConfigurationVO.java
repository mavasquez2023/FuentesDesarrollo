package cl.laaraucana.test.vo;

public class FTPConfigurationVO {

	private String host= null;
	private int port = 21;
	private String user = null;
	private String pass = null;
	private String pathToGet = null;
	private String pathToPut = null;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPathToGet() {
		return pathToGet;
	}
	public void setPathToGet(String pathToGet) {
		this.pathToGet = pathToGet;
	}
	public String getPathToPut() {
		return pathToPut;
	}
	public void setPathToPut(String pathToPut) {
		this.pathToPut = pathToPut;
	}
	
	
	
}
