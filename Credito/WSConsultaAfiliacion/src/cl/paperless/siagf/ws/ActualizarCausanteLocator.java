/**
 * ActualizarCausanteLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.paperless.siagf.ws;

public class ActualizarCausanteLocator extends org.apache.axis.client.Service implements cl.paperless.siagf.ws.ActualizarCausante {

	public ActualizarCausanteLocator() {
	}

	public ActualizarCausanteLocator(org.apache.axis.EngineConfiguration config) {
		super(config);
	}

	public ActualizarCausanteLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
		super(wsdlLoc, sName);
	}

	// Use to get a proxy class for ActualizarCausanteSOAP12port_http
	private java.lang.String ActualizarCausanteSOAP12port_http_address = "http://siagfqa.paperless.cl:8083/axis2/services/ActualizarCausante";

	public java.lang.String getActualizarCausanteSOAP12port_httpAddress() {
		return ActualizarCausanteSOAP12port_http_address;
	}

	// The WSDD service name defaults to the port name.
	private java.lang.String ActualizarCausanteSOAP12port_httpWSDDServiceName = "ActualizarCausanteSOAP12port_http";

	public java.lang.String getActualizarCausanteSOAP12port_httpWSDDServiceName() {
		return ActualizarCausanteSOAP12port_httpWSDDServiceName;
	}

	public void setActualizarCausanteSOAP12port_httpWSDDServiceName(java.lang.String name) {
		ActualizarCausanteSOAP12port_httpWSDDServiceName = name;
	}

	public cl.paperless.siagf.ws.ActualizarCausantePortType getActualizarCausanteSOAP12port_http() throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(ActualizarCausanteSOAP12port_http_address);
		} catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getActualizarCausanteSOAP12port_http(endpoint);
	}

	public cl.paperless.siagf.ws.ActualizarCausantePortType getActualizarCausanteSOAP12port_http(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
		try {
			cl.paperless.siagf.ws.ActualizarCausanteSOAP12BindingStub _stub = new cl.paperless.siagf.ws.ActualizarCausanteSOAP12BindingStub(portAddress, this);
			_stub.setPortName(getActualizarCausanteSOAP12port_httpWSDDServiceName());
			return _stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setActualizarCausanteSOAP12port_httpEndpointAddress(java.lang.String address) {
		ActualizarCausanteSOAP12port_http_address = address;
	}

	// Use to get a proxy class for ActualizarCausanteSOAP11port_http
	private java.lang.String ActualizarCausanteSOAP11port_http_address = "http://siagfqa.paperless.cl:8083/axis2/services/ActualizarCausante";

	public java.lang.String getActualizarCausanteSOAP11port_httpAddress() {
		return ActualizarCausanteSOAP11port_http_address;
	}

	// The WSDD service name defaults to the port name.
	private java.lang.String ActualizarCausanteSOAP11port_httpWSDDServiceName = "ActualizarCausanteSOAP11port_http";

	public java.lang.String getActualizarCausanteSOAP11port_httpWSDDServiceName() {
		return ActualizarCausanteSOAP11port_httpWSDDServiceName;
	}

	public void setActualizarCausanteSOAP11port_httpWSDDServiceName(java.lang.String name) {
		ActualizarCausanteSOAP11port_httpWSDDServiceName = name;
	}

	public cl.paperless.siagf.ws.ActualizarCausantePortType getActualizarCausanteSOAP11port_http() throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(ActualizarCausanteSOAP11port_http_address);
		} catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getActualizarCausanteSOAP11port_http(endpoint);
	}

	public cl.paperless.siagf.ws.ActualizarCausantePortType getActualizarCausanteSOAP11port_http(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
		try {
			cl.paperless.siagf.ws.ActualizarCausanteSOAP11BindingStub _stub = new cl.paperless.siagf.ws.ActualizarCausanteSOAP11BindingStub(portAddress, this);
			_stub.setPortName(getActualizarCausanteSOAP11port_httpWSDDServiceName());
			return _stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setActualizarCausanteSOAP11port_httpEndpointAddress(java.lang.String address) {
		ActualizarCausanteSOAP11port_http_address = address;
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
			if (cl.paperless.siagf.ws.ActualizarCausantePortType.class.isAssignableFrom(serviceEndpointInterface)) {
				cl.paperless.siagf.ws.ActualizarCausanteSOAP12BindingStub _stub = new cl.paperless.siagf.ws.ActualizarCausanteSOAP12BindingStub(new java.net.URL(ActualizarCausanteSOAP12port_http_address), this);
				_stub.setPortName(getActualizarCausanteSOAP12port_httpWSDDServiceName());
				return _stub;
			}
			if (cl.paperless.siagf.ws.ActualizarCausantePortType.class.isAssignableFrom(serviceEndpointInterface)) {
				cl.paperless.siagf.ws.ActualizarCausanteSOAP11BindingStub _stub = new cl.paperless.siagf.ws.ActualizarCausanteSOAP11BindingStub(new java.net.URL(ActualizarCausanteSOAP11port_http_address), this);
				_stub.setPortName(getActualizarCausanteSOAP11port_httpWSDDServiceName());
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
		if ("ActualizarCausanteSOAP12port_http".equals(inputPortName)) {
			return getActualizarCausanteSOAP12port_http();
		} else if ("ActualizarCausanteSOAP11port_http".equals(inputPortName)) {
			return getActualizarCausanteSOAP11port_http();
		} else {
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public javax.xml.namespace.QName getServiceName() {
		return new javax.xml.namespace.QName("http://ws.siagf.paperless.cl", "ActualizarCausante");
	}

	private java.util.HashSet ports = null;

	public java.util.Iterator getPorts() {
		if (ports == null) {
			ports = new java.util.HashSet();
			ports.add(new javax.xml.namespace.QName("http://ws.siagf.paperless.cl", "ActualizarCausanteSOAP12port_http"));
			ports.add(new javax.xml.namespace.QName("http://ws.siagf.paperless.cl", "ActualizarCausanteSOAP11port_http"));
		}
		return ports.iterator();
	}

	/**
	* Set the endpoint address for the specified port name.
	*/
	public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

		if ("ActualizarCausanteSOAP12port_http".equals(portName)) {
			setActualizarCausanteSOAP12port_httpEndpointAddress(address);
		} else if ("ActualizarCausanteSOAP11port_http".equals(portName)) {
			setActualizarCausanteSOAP11port_httpEndpointAddress(address);
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
