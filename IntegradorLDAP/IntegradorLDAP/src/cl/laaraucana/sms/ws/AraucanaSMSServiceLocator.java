/**
 * AraucanaSMSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.sms.ws;

public class AraucanaSMSServiceLocator extends org.apache.axis.client.Service implements cl.laaraucana.sms.ws.AraucanaSMSService {

    public AraucanaSMSServiceLocator() {
    }


    public AraucanaSMSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AraucanaSMSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AraucanaSMSPort
    private java.lang.String AraucanaSMSPort_address = "http://172.22.6.137:9080/ShortMessageService/AraucanaSMSService";

    public java.lang.String getAraucanaSMSPortAddress() {
        return AraucanaSMSPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AraucanaSMSPortWSDDServiceName = "AraucanaSMSPort";

    public java.lang.String getAraucanaSMSPortWSDDServiceName() {
        return AraucanaSMSPortWSDDServiceName;
    }

    public void setAraucanaSMSPortWSDDServiceName(java.lang.String name) {
        AraucanaSMSPortWSDDServiceName = name;
    }

    public cl.laaraucana.sms.ws.AraucanaSMS getAraucanaSMSPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AraucanaSMSPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAraucanaSMSPort(endpoint);
    }

    public cl.laaraucana.sms.ws.AraucanaSMS getAraucanaSMSPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cl.laaraucana.sms.ws.AraucanaSMSPortBindingStub _stub = new cl.laaraucana.sms.ws.AraucanaSMSPortBindingStub(portAddress, this);
            _stub.setPortName(getAraucanaSMSPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAraucanaSMSPortEndpointAddress(java.lang.String address) {
        AraucanaSMSPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cl.laaraucana.sms.ws.AraucanaSMS.class.isAssignableFrom(serviceEndpointInterface)) {
                cl.laaraucana.sms.ws.AraucanaSMSPortBindingStub _stub = new cl.laaraucana.sms.ws.AraucanaSMSPortBindingStub(new java.net.URL(AraucanaSMSPort_address), this);
                _stub.setPortName(getAraucanaSMSPortWSDDServiceName());
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
        if ("AraucanaSMSPort".equals(inputPortName)) {
            return getAraucanaSMSPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.sms.laaraucana.cl/", "AraucanaSMSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.sms.laaraucana.cl/", "AraucanaSMSPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AraucanaSMSPort".equals(portName)) {
            setAraucanaSMSPortEndpointAddress(address);
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
