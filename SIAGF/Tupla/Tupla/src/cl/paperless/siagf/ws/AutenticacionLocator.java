/**
 * AutenticacionLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package cl.paperless.siagf.ws;

public class AutenticacionLocator extends org.apache.axis.client.Service implements cl.paperless.siagf.ws.Autenticacion {

	public AutenticacionLocator() {
	}

	public AutenticacionLocator(org.apache.axis.EngineConfiguration config) {
		super(config);
	}

	public AutenticacionLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
		super(wsdlLoc, sName);
	}

	// Use to get a proxy class for AutenticacionSOAP11port_http
	private java.lang.String AutenticacionSOAP11port_http_address = "http://siagf.paperless.cl:8081/axis2/services/Autenticacion";

	public java.lang.String getAutenticacionSOAP11port_httpAddress() {
		return AutenticacionSOAP11port_http_address;
	}

	// The WSDD service name defaults to the port name.
	private java.lang.String AutenticacionSOAP11port_httpWSDDServiceName = "AutenticacionSOAP11port_http";

	public java.lang.String getAutenticacionSOAP11port_httpWSDDServiceName() {
		return AutenticacionSOAP11port_httpWSDDServiceName;
	}

	public void setAutenticacionSOAP11port_httpWSDDServiceName(java.lang.String name) {
		AutenticacionSOAP11port_httpWSDDServiceName = name;
	}

	public cl.paperless.siagf.ws.AutenticacionPortType getAutenticacionSOAP11port_http() throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(AutenticacionSOAP11port_http_address);
		} catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getAutenticacionSOAP11port_http(endpoint);
	}

	public cl.paperless.siagf.ws.AutenticacionPortType getAutenticacionSOAP11port_http(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
		try {
			cl.paperless.siagf.ws.AutenticacionSOAP11BindingStub _stub = new cl.paperless.siagf.ws.AutenticacionSOAP11BindingStub(portAddress, this);
			_stub.setPortName(getAutenticacionSOAP11port_httpWSDDServiceName());
			return _stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setAutenticacionSOAP11port_httpEndpointAddress(java.lang.String address) {
		AutenticacionSOAP11port_http_address = address;
	}

	// Use to get a proxy class for AutenticacionSOAP12port_http
	private java.lang.String AutenticacionSOAP12port_http_address = "http://siagf.paperless.cl:8081/axis2/services/Autenticacion";

	public java.lang.String getAutenticacionSOAP12port_httpAddress() {
		return AutenticacionSOAP12port_http_address;
	}

	// The WSDD service name defaults to the port name.
	private java.lang.String AutenticacionSOAP12port_httpWSDDServiceName = "AutenticacionSOAP12port_http";

	public java.lang.String getAutenticacionSOAP12port_httpWSDDServiceName() {
		return AutenticacionSOAP12port_httpWSDDServiceName;
	}

	public void setAutenticacionSOAP12port_httpWSDDServiceName(java.lang.String name) {
		AutenticacionSOAP12port_httpWSDDServiceName = name;
	}

	public cl.paperless.siagf.ws.AutenticacionPortType getAutenticacionSOAP12port_http() throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(AutenticacionSOAP12port_http_address);
		} catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getAutenticacionSOAP12port_http(endpoint);
	}

	public cl.paperless.siagf.ws.AutenticacionPortType getAutenticacionSOAP12port_http(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
		try {
			cl.paperless.siagf.ws.AutenticacionSOAP12BindingStub _stub = new cl.paperless.siagf.ws.AutenticacionSOAP12BindingStub(portAddress, this);
			_stub.setPortName(getAutenticacionSOAP12port_httpWSDDServiceName());
			return _stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setAutenticacionSOAP12port_httpEndpointAddress(java.lang.String address) {
		AutenticacionSOAP12port_http_address = address;
	}

	/**
	 * For the given interface, get the stub implementation.
	 * If this service has no port for the given interface,
	 * then ServiceException is thrown.
	 * This service has multiple ports for a given interface;
	 * the proxy implementation returned may be indeterminate.
	 */
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
		try {
			if (cl.paperless.siagf.ws.AutenticacionPortType.class.isAssignableFrom(serviceEndpointInterface)) {
				cl.paperless.siagf.ws.AutenticacionSOAP11BindingStub _stub = new cl.paperless.siagf.ws.AutenticacionSOAP11BindingStub(new java.net.URL(AutenticacionSOAP11port_http_address), this);
				_stub.setPortName(getAutenticacionSOAP11port_httpWSDDServiceName());
				return _stub;
			}
			if (cl.paperless.siagf.ws.AutenticacionPortType.class.isAssignableFrom(serviceEndpointInterface)) {
				cl.paperless.siagf.ws.AutenticacionSOAP12BindingStub _stub = new cl.paperless.siagf.ws.AutenticacionSOAP12BindingStub(new java.net.URL(AutenticacionSOAP12port_http_address), this);
				_stub.setPortName(getAutenticacionSOAP12port_httpWSDDServiceName());
				return _stub;
			}
		} catch (java.lang.Throwable t) {
			throw new javax.xml.rpc.ServiceException(t);
		}
		throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
	}

	/**
	 * For the given interface, get the stub implementation.
	 * If this service has no port for the given interface,
	 * then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
		if (portName == null) {
			return getPort(serviceEndpointInterface);
		}
		java.lang.String inputPortName = portName.getLocalPart();
		if ("AutenticacionSOAP11port_http".equals(inputPortName)) {
			return getAutenticacionSOAP11port_http();
		} else if ("AutenticacionSOAP12port_http".equals(inputPortName)) {
			return getAutenticacionSOAP12port_http();
		} else {
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public javax.xml.namespace.QName getServiceName() {
		return new javax.xml.namespace.QName("http://ws.siagf.paperless.cl", "Autenticacion");
	}

	private java.util.HashSet ports = null;

	public java.util.Iterator getPorts() {
		if (ports == null) {
			ports = new java.util.HashSet();
			ports.add(new javax.xml.namespace.QName("http://ws.siagf.paperless.cl", "AutenticacionSOAP11port_http"));
			ports.add(new javax.xml.namespace.QName("http://ws.siagf.paperless.cl", "AutenticacionSOAP12port_http"));
		}
		return ports.iterator();
	}

	/**
	* Set the endpoint address for the specified port name.
	*/
	public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

		if ("AutenticacionSOAP11port_http".equals(portName)) {
			setAutenticacionSOAP11port_httpEndpointAddress(address);
		} else if ("AutenticacionSOAP12port_http".equals(portName)) {
			setAutenticacionSOAP12port_httpEndpointAddress(address);
		} else { // Unknown Port Name
			throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
		}
	}

	/**
	* Set the endpoint address for the specified port name.
	*/
	public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
		setEndpointAddress(portName.getLocalPart(), address);
	}

}
