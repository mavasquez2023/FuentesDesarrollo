package cl.laaraucana.servicios.simuladorCreditoSocial;

import java.io.IOException;
import java.util.Properties;

public class SimuladorCreditoSocialServiceLocator extends org.apache.axis.client.Service implements cl.laaraucana.servicios.simuladorCreditoSocial.SimuladorCreditoSocialService {
	public SimuladorCreditoSocialServiceLocator() {}

	public SimuladorCreditoSocialServiceLocator(org.apache.axis.EngineConfiguration config) {
		super(config);
	}

	public SimuladorCreditoSocialServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
		super(wsdlLoc, sName);
	}
	// Use to get a proxy class for SimuladorCreditoSocialPort
	private java.lang.String SimuladorCreditoSocialPort_address;

	public void setSimuladorCreditoSocialPortAddress(String endpoint) {
		SimuladorCreditoSocialPort_address = endpoint; 
	}

	public java.lang.String getSimuladorCreditoSocialPortAddress() {
		return SimuladorCreditoSocialPort_address;
	}
	// The WSDD service name defaults to the port name.
	private java.lang.String SimuladorCreditoSocialPortWSDDServiceName = "SimuladorCreditoSocialPort";

	public java.lang.String getSimuladorCreditoSocialPortWSDDServiceName() {
		return SimuladorCreditoSocialPortWSDDServiceName;
	}

	public void setSimuladorCreditoSocialPortWSDDServiceName(java.lang.String name) {
		SimuladorCreditoSocialPortWSDDServiceName = name;
	}

	public cl.laaraucana.servicios.simuladorCreditoSocial.SimuladorCreditoSocialImpl getSimuladorCreditoSocialPort() throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(SimuladorCreditoSocialPort_address);
		} catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getSimuladorCreditoSocialPort(endpoint);
	}

	public cl.laaraucana.servicios.simuladorCreditoSocial.SimuladorCreditoSocialImpl getSimuladorCreditoSocialPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
		try {
			cl.laaraucana.servicios.simuladorCreditoSocial.SimuladorCreditoSocialPortBindingStub _stub = new cl.laaraucana.servicios.simuladorCreditoSocial.SimuladorCreditoSocialPortBindingStub(
					portAddress, this);
			_stub.setPortName(getSimuladorCreditoSocialPortWSDDServiceName());
			return _stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setSimuladorCreditoSocialPortEndpointAddress(java.lang.String address) {
		SimuladorCreditoSocialPort_address = address;
	}

	/**
	 * For the given interface, get the stub implementation.
	 * If this service has no port for the given interface,
	 * then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
		try {
			if (cl.laaraucana.servicios.simuladorCreditoSocial.SimuladorCreditoSocialImpl.class.isAssignableFrom(serviceEndpointInterface)) {
				cl.laaraucana.servicios.simuladorCreditoSocial.SimuladorCreditoSocialPortBindingStub _stub = new cl.laaraucana.servicios.simuladorCreditoSocial.SimuladorCreditoSocialPortBindingStub(
						new java.net.URL(SimuladorCreditoSocialPort_address), this);
				_stub.setPortName(getSimuladorCreditoSocialPortWSDDServiceName());
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
		if ("SimuladorCreditoSocialPort".equals(inputPortName)) {
			return getSimuladorCreditoSocialPort();
		} else {
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public javax.xml.namespace.QName getServiceName() {
		return new javax.xml.namespace.QName("http://servicios.laaraucana.cl/simuladorCreditoSocial", "SimuladorCreditoSocialService");
	}
	private java.util.HashSet ports = null;

	public java.util.Iterator getPorts() {
		if (ports == null) {
			ports = new java.util.HashSet();
			ports.add(new javax.xml.namespace.QName("http://servicios.laaraucana.cl/simuladorCreditoSocial", "SimuladorCreditoSocialPort"));
		}
		return ports.iterator();
	}

	/**
	* Set the endpoint address for the specified port name.
	*/
	public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
		if ("SimuladorCreditoSocialPort".equals(portName)) {
			setSimuladorCreditoSocialPortEndpointAddress(address);
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
