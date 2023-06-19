/**
 * ServicioSMSInternoServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.araucana.ws;

public class ServicioSMSInternoServiceLocator extends org.apache.axis.client.Service implements cl.araucana.ws.ServicioSMSInternoService {

    public ServicioSMSInternoServiceLocator() {
    }


    public ServicioSMSInternoServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ServicioSMSInternoServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ServicioSMSInterno
    private java.lang.String ServicioSMSInterno_address = "http://localhost:9081/ServicioSMSInterno/services/ServicioSMSInterno";

    public java.lang.String getServicioSMSInternoAddress() {
        return ServicioSMSInterno_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ServicioSMSInternoWSDDServiceName = "ServicioSMSInterno";

    public java.lang.String getServicioSMSInternoWSDDServiceName() {
        return ServicioSMSInternoWSDDServiceName;
    }

    public void setServicioSMSInternoWSDDServiceName(java.lang.String name) {
        ServicioSMSInternoWSDDServiceName = name;
    }

    public cl.araucana.ws.ServicioSMSInterno getServicioSMSInterno() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ServicioSMSInterno_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getServicioSMSInterno(endpoint);
    }

    public cl.araucana.ws.ServicioSMSInterno getServicioSMSInterno(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cl.araucana.ws.ServicioSMSInternoSoapBindingStub _stub = new cl.araucana.ws.ServicioSMSInternoSoapBindingStub(portAddress, this);
            _stub.setPortName(getServicioSMSInternoWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setServicioSMSInternoEndpointAddress(java.lang.String address) {
        ServicioSMSInterno_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cl.araucana.ws.ServicioSMSInterno.class.isAssignableFrom(serviceEndpointInterface)) {
                cl.araucana.ws.ServicioSMSInternoSoapBindingStub _stub = new cl.araucana.ws.ServicioSMSInternoSoapBindingStub(new java.net.URL(ServicioSMSInterno_address), this);
                _stub.setPortName(getServicioSMSInternoWSDDServiceName());
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
        if ("ServicioSMSInterno".equals(inputPortName)) {
            return getServicioSMSInterno();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.araucana.cl", "ServicioSMSInternoService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.araucana.cl", "ServicioSMSInterno"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ServicioSMSInterno".equals(portName)) {
            setServicioSMSInternoEndpointAddress(address);
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
