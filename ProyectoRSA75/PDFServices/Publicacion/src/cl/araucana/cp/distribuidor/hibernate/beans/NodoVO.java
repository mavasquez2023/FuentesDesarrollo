package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

public class NodoVO extends AuditableVO
{
	private static final long serialVersionUID = -1391872483338048332L;
	private int idNodo;
	private int port;
	private int adminPort;
	private int distribuidor;
	private int habilitado;

	private int numConnDisponibles; 
	private int numConnMaximas; 
	private int usoSistMinimo; 
	private int usoSistMaximo;

	private String host;
	private String descripcion;
	private String url;
	private String contextFactory;

	public NodoVO() 
	{
		super();
	}

	public NodoVO(int idNodo, int port, int adminPort, int distribuidor, int habilitado, String host, String descripcion, String url, String contextFactory) 
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
	
	public void asigna()
	{
		this.numConnDisponibles--;
	}
	
	public void libera()
	{
		this.numConnDisponibles++;
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
		this.descripcion = descripcion;
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

	public int getNumConnDisponibles()
	{
		return this.numConnDisponibles;
	}

	public void setNumConnDisponibles(int numConnDisponibles)
	{
		this.numConnDisponibles = numConnDisponibles;
	}

	public int getNumConnMaximas()
	{
		return this.numConnMaximas;
	}

	public void setNumConnMaximas(int numConnMaximas)
	{
		this.numConnMaximas = numConnMaximas;
	}

	public int getUsoSistMaximo()
	{
		return this.usoSistMaximo;
	}

	public void setUsoSistMaximo(int usoSistMaximo)
	{
		this.usoSistMaximo = usoSistMaximo;
	}

	public int getUsoSistMinimo()
	{
		return this.usoSistMinimo;
	}

	public void setUsoSistMinimo(int usoSistMinimo)
	{
		this.usoSistMinimo = usoSistMinimo;
	}

	public int getDistribuidor()
	{
		return this.distribuidor;
	}

	public int getHabilitado()
	{
		return this.habilitado;
	}
	
	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.idNodo));
		parametros.put("2", String.valueOf(this.descripcion));
		parametros.put("3", String.valueOf(this.host));
		parametros.put("4", String.valueOf(this.port));
		parametros.put("5", String.valueOf(this.adminPort));
		parametros.put("6", String.valueOf(this.url));
		parametros.put("7", String.valueOf(this.distribuidor));
		parametros.put("8", String.valueOf(this.habilitado));
		parametros.put("9", String.valueOf(this.contextFactory));
		parametros.put("10", String.valueOf(this.numConnDisponibles));
		parametros.put("11", String.valueOf(this.numConnMaximas));
		parametros.put("12", String.valueOf(this.usoSistMinimo));
		parametros.put("13", String.valueOf(this.usoSistMaximo));
		return parametros;
	}
}
