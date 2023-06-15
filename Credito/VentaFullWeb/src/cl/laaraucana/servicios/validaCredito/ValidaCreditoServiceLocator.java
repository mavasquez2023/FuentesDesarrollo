/**
 * ValidaCreditoServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicios.validaCredito;

public class ValidaCreditoServiceLocator extends org.apache.axis.client.Service implements cl.laaraucana.servicios.validaCredito.ValidaCreditoService {

    public ValidaCreditoServiceLocator() {
    }


    public ValidaCreditoServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ValidaCreditoServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ValidaCreditoPort
    private java.lang.String ValidaCreditoPort_address = "https://qa-0020.laaraucana.cl:443/WSValidaCredito/ValidaCreditoService";

    public java.lang.String getValidaCreditoPortAddress() {
        return ValidaCreditoPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ValidaCreditoPortWSDDServiceName = "ValidaCreditoPort";

    public java.lang.String getValidaCreditoPortWSDDServiceName() {
        return ValidaCreditoPortWSDDServiceName;
    }

    public void setValidaCreditoPortWSDDServiceName(java.lang.String name) {
        ValidaCreditoPortWSDDServiceName = name;
    }

    public cl.laaraucana.servicios.validaCredito.ValidaCreditoImpl getValidaCreditoPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ValidaCreditoPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getValidaCreditoPort(endpoint);
    }

    public cl.laaraucana.servicios.validaCredito.ValidaCreditoImpl getValidaCreditoPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cl.laaraucana.servicios.validaCredito.ValidaCreditoPortBindingStub _stub = new cl.laaraucana.servicios.validaCredito.ValidaCreditoPortBindingStub(portAddress, this);
            _stub.setPortName(getValidaCreditoPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setValidaCreditoPortEndpointAddress(java.lang.String address) {
        ValidaCreditoPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cl.laaraucana.servicios.validaCredito.ValidaCreditoImpl.class.isAssignableFrom(serviceEndpointInterface)) {
                cl.laaraucana.servicios.validaCredito.ValidaCreditoPortBindingStub _stub = new cl.laaraucana.servicios.validaCredito.ValidaCreditoPortBindingStub(new java.net.URL(ValidaCreditoPort_address), this);
                _stub.setPortName(getValidaCreditoPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
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
        if ("ValidaCreditoPort".equals(inputPortName)) {
            return getValidaCreditoPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicios.laaraucana.cl/validaCredito", "ValidaCreditoService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicios.laaraucana.cl/validaCredito", "ValidaCreditoPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ValidaCreditoPort".equals(portName)) {
            setValidaCreditoPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
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
