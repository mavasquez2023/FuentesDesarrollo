/**
 * ValidaClienteServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicios.validaCliente;

public class ValidaClienteServiceLocator extends org.apache.axis.client.Service implements cl.laaraucana.servicios.validaCliente.ValidaClienteService {

    public ValidaClienteServiceLocator() {
    }


    public ValidaClienteServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ValidaClienteServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ValidaClientePort
    private java.lang.String ValidaClientePort_address = "https://qa-0020.laaraucana.cl:443/WSValidaCliente/ValidaClienteService";

    public java.lang.String getValidaClientePortAddress() {
        return ValidaClientePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ValidaClientePortWSDDServiceName = "ValidaClientePort";

    public java.lang.String getValidaClientePortWSDDServiceName() {
        return ValidaClientePortWSDDServiceName;
    }

    public void setValidaClientePortWSDDServiceName(java.lang.String name) {
        ValidaClientePortWSDDServiceName = name;
    }

    public cl.laaraucana.servicios.validaCliente.ValidaClienteImpl getValidaClientePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ValidaClientePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getValidaClientePort(endpoint);
    }

    public cl.laaraucana.servicios.validaCliente.ValidaClienteImpl getValidaClientePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cl.laaraucana.servicios.validaCliente.ValidaClientePortBindingStub _stub = new cl.laaraucana.servicios.validaCliente.ValidaClientePortBindingStub(portAddress, this);
            _stub.setPortName(getValidaClientePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setValidaClientePortEndpointAddress(java.lang.String address) {
        ValidaClientePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cl.laaraucana.servicios.validaCliente.ValidaClienteImpl.class.isAssignableFrom(serviceEndpointInterface)) {
                cl.laaraucana.servicios.validaCliente.ValidaClientePortBindingStub _stub = new cl.laaraucana.servicios.validaCliente.ValidaClientePortBindingStub(new java.net.URL(ValidaClientePort_address), this);
                _stub.setPortName(getValidaClientePortWSDDServiceName());
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
        if ("ValidaClientePort".equals(inputPortName)) {
            return getValidaClientePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicios.laaraucana.cl/validaCliente", "ValidaClienteService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicios.laaraucana.cl/validaCliente", "ValidaClientePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ValidaClientePort".equals(portName)) {
            setValidaClientePortEndpointAddress(address);
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
