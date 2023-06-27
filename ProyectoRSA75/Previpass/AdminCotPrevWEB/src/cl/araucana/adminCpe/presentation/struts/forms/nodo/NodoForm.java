package cl.araucana.adminCpe.presentation.struts.forms.nodo;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
/*
* @(#) NodoForm.java 1.3 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author creyes
 * @author cchamblas
 * 
 * @version 1.3
 */
public class NodoForm extends ActionForm
{
	private static final long serialVersionUID = 2570695210435692489L;
	
	private List consulta;
	
	private int idNodo;
	private String desc;
	private String host;
	private int port;
	private int adminPort;
	private String 	url;
	private int distribuidor;
	private int habiliatado;
	private String 	initial_context_factory;
	private int numConDisponibles;
	private int numConMax;
	private int usoSistMin;
	private int usoSistMax;
	private String accion;

	private Collection numeroFilas;
	
	/**
	 * reset
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		super.reset(mapping, request);
		
		this.consulta = null;
	}

	/**
	 * consulta
	 * @return
	 */
	public List getConsulta() {
		return this.consulta;
	}

	/**
	 * consulta
	 * @param consulta
	 */
	public void setConsulta(List consulta) {
		this.consulta = consulta;
	}

	/**
	 * admin puerto
	 * @return this.the adminPort
	 */
	public int getAdminPort() {
		return this.adminPort;
	}

	/**
	 * admin puerto
	 * @param adminPort the adminPort to set
	 */
	public void setAdminPort(int adminPort) {
		this.adminPort = adminPort;
	}

	/**
	 * desc
	 * @return this.the desc
	 */
	public String getDesc() {
		return this.desc;
	}

	/**
	 * desc
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * distribuidor
	 * @return this.the distribuidor
	 */
	public int getDistribuidor() {
		return this.distribuidor;
	}

	/**
	 * distribuidor
	 * @param distribuidor the distribuidor to set
	 */
	public void setDistribuidor(int distribuidor) {
		this.distribuidor = distribuidor;
	}

	/**
	 * habilitato
	 * @return this.the habiliatado
	 */
	public int getHabiliatado() {
		return this.habiliatado;
	}

	/**
	 * habilitado
	 * @param habiliatado the habiliatado to set
	 */
	public void setHabiliatado(int habiliatado) {
		this.habiliatado = habiliatado;
	}

	/**
	 * host
	 * @return this.the host
	 */
	public String getHost() {
		return this.host;
	}

	/** 
	 * host
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * if nodo
	 * @return this.the idNodo
	 */
	public int getIdNodo() {
		return this.idNodo;
	}

	/**
	 * nodo
	 * @param idNodo the idNodo to set
	 */
	public void setIdNodo(int idNodo) {
		this.idNodo = idNodo;
	}

	/**
	 * initial context factory
	 * @return this.the initial_context_factory
	 */
	public String getInitial_context_factory() {
		return this.initial_context_factory;
	}

	/**
	 * initial context factory
	 * @param initial_context_factory the initial_context_factory to set
	 */
	public void setInitial_context_factory(String initial_context_factory) {
		this.initial_context_factory = initial_context_factory;
	}

	/**
	 * numero conexiones disponibles
	 * @return this.the numConDisponibles
	 */
	public int getNumConDisponibles() {
		return this.numConDisponibles;
	}

	/**
	 * numero conexiones disponibles
	 * @param numConDisponibles the numConDisponibles to set
	 */
	public void setNumConDisponibles(int numConDisponibles) {
		this.numConDisponibles = numConDisponibles;
	}

	/**
	 * numero maximo conexiones
	 * @return this.the numConMax
	 */
	public int getNumConMax() {
		return this.numConMax;
	}

	/**
	 * numero maximo conexiones
	 * @param numConMax the numConMax to set
	 */
	public void setNumConMax(int numConMax) {
		this.numConMax = numConMax;
	}

	/**
	 * puerto
	 * @return this.the port
	 */
	public int getPort() {
		return this.port;
	}

	/**
	 * puerto
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * url
	 * @return this.the url
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * url
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * uso sistema maximo
	 * @return this.the usoSistMax
	 */
	public int getUsoSistMax() {
		return this.usoSistMax;
	}

	/**
	 * uso sistema maximo
	 * @param usoSistMax the usoSistMax to set
	 */
	public void setUsoSistMax(int usoSistMax) {
		this.usoSistMax = usoSistMax;
	}

	/**
	 * uso sistema minimo
	 * @return this.the usoSistMin
	 */
	public int getUsoSistMin() {
		return this.usoSistMin;
	}

	/**
	 * uso sistema minimo
	 * @param usoSistMin the usoSistMin to set
	 */
	public void setUsoSistMin(int usoSistMin) {
		this.usoSistMin = usoSistMin;
	}

	/**
	 * accion
	 * @return this.the accion
	 */
	public String getAccion() {
		return this.accion;
	}

	/**
	 * accion
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}
	
	/**
	 * convierte a String
	 * @param Convierte a String todos los campos
	 */
	public String toString() {
		String toString = " \n **********************************Contenido de Nodo Form: " +
				"\n\tadminPort:"+this.adminPort+
				"\n\tadminPort:"+this.desc+
				"\n\tadminPort:"+this.distribuidor+
				"\n\tadminPort:"+this.habiliatado+
				"\n\tadminPort:"+this.host+
				"\n\tadminPort:"+this.host+
				"\n\tadminPort:"+this.idNodo+
				"\n\tadminPort:"+this.initial_context_factory+
				"\n\tadminPort:"+this.numConDisponibles+
				"\n\tadminPort:"+this.numConMax+
				"\n\tadminPort:"+this.port+
				"\n\tadminPort:"+this.url+
				"\n\tadminPort:"+this.usoSistMax+
				"\n\tadminPort:"+this.usoSistMin; 
		return toString;
	}

	/**
	 * numero filas
	 * @return
	 */
	public Collection getNumeroFilas() {
		return this.numeroFilas;
	}

	/**
	 * numero de filas
	 * @param numeroFilas
	 */
	public void setNumeroFilas(Collection numeroFilas) {
		this.numeroFilas = numeroFilas;
	}
}
