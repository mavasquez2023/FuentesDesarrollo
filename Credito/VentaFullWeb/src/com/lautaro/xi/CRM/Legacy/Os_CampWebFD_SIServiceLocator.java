/**
 * Os_CampWebFD_SIServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.CRM.Legacy;

public class Os_CampWebFD_SIServiceLocator extends org.apache.axis.client.Service implements com.lautaro.xi.CRM.Legacy.Os_CampWebFD_SIService {

    public Os_CampWebFD_SIServiceLocator() {
    }


    public Os_CampWebFD_SIServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Os_CampWebFD_SIServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for os_CampWebFD_SIPort
    private java.lang.String os_CampWebFD_SIPort_address = "http://ARAQACIPIT.laaraucana.local:50000/sap/xi/engine?type=entry&version=3.0&Sender.Service=WEB_Mobile&Interface=http%3A%2F%2Flautaro.com%2Fxi%2FCRM%2FLegacy%5Eos_CampWebFD_SI";

    public java.lang.String getos_CampWebFD_SIPortAddress() {
        return os_CampWebFD_SIPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String os_CampWebFD_SIPortWSDDServiceName = "os_CampWebFD_SIPort";

    public java.lang.String getos_CampWebFD_SIPortWSDDServiceName() {
        return os_CampWebFD_SIPortWSDDServiceName;
    }

    public void setos_CampWebFD_SIPortWSDDServiceName(java.lang.String name) {
        os_CampWebFD_SIPortWSDDServiceName = name;
    }

    public com.lautaro.xi.CRM.Legacy.Os_CampWebFD_SI getos_CampWebFD_SIPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(os_CampWebFD_SIPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getos_CampWebFD_SIPort(endpoint);
    }

    public com.lautaro.xi.CRM.Legacy.Os_CampWebFD_SI getos_CampWebFD_SIPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.lautaro.xi.CRM.Legacy.Os_CampWebFD_SIBindingStub _stub = new com.lautaro.xi.CRM.Legacy.Os_CampWebFD_SIBindingStub(portAddress, this);
            _stub.setPortName(getos_CampWebFD_SIPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setos_CampWebFD_SIPortEndpointAddress(java.lang.String address) {
        os_CampWebFD_SIPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.lautaro.xi.CRM.Legacy.Os_CampWebFD_SI.class.isAssignableFrom(serviceEndpointInterface)) {
                com.lautaro.xi.CRM.Legacy.Os_CampWebFD_SIBindingStub _stub = new com.lautaro.xi.CRM.Legacy.Os_CampWebFD_SIBindingStub(new java.net.URL(os_CampWebFD_SIPort_address), this);
                _stub.setPortName(getos_CampWebFD_SIPortWSDDServiceName());
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
        if ("os_CampWebFD_SIPort".equals(inputPortName)) {
            return getos_CampWebFD_SIPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://lautaro.com/xi/CRM/Legacy", "os_CampWebFD_SIService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://lautaro.com/xi/CRM/Legacy", "os_CampWebFD_SIPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("os_CampWebFD_SIPort".equals(portName)) {
            setos_CampWebFD_SIPortEndpointAddress(address);
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
