package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class DistribuidorVO implements Serializable
{
	private static final long serialVersionUID = -1391872483338048332L;
	private int idNodo;
	private int port;
	private int adminPort;
	private int distribuidor;
	private int habilitado;
	private String host;
	private String descripcion;
	private String url;
	private String contextFactory;

	public DistribuidorVO() 
	{
		super();
	}

	public DistribuidorVO(int idNodo, int port, int adminPort, int distribuidor, int habilitado, String host, String descripcion, String url, String contextFactory) 
	{
		super();
		this.idNodo = idNodo;
		this.port = port;
		this.adminPort = adminPort;
		this.distribuidor = distribuidor;
		this.habilitado = habilitado;
		this.host = host;
		this.descripcion = descripcion;
		this.url = url;
		this.contextFactory = contextFactory;
	}

	public int getAdminPort() {
		return this.adminPort;
	}

	public void setAdminPort(int adminPort) {
		this.adminPort = adminPort;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;//.trim();
	}

	public int isDistribuidor() {
		return this.distribuidor;
	}

	public void setDistribuidor(int distribuidor) {
		this.distribuidor = distribuidor;
	}

	public int isHabilitado() {
		return this.habilitado;
	}

	public void setHabilitado(int habilitado) {
		this.habilitado = habilitado;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getIdNodo() {
		return this.idNodo;
	}

	public void setIdNodo(int idNodo) {
		this.idNodo = idNodo;
	}

	public int getPort() {
		return this.port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContextFactory() {
		return this.contextFactory;
	}

	public void setContextFactory(String contextFactory) {
		this.contextFactory = contextFactory;
	}
}
