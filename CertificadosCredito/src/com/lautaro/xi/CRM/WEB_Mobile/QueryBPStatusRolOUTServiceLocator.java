/**
 * QueryBPStatusRolOUTServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.CRM.WEB_Mobile;

public class QueryBPStatusRolOUTServiceLocator extends org.apache.axis.client.Service implements com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRolOUTService {

    public QueryBPStatusRolOUTServiceLocator() {
    }


    public QueryBPStatusRolOUTServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public QueryBPStatusRolOUTServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for QueryBPStatusRolOUTPort
    private java.lang.String QueryBPStatusRolOUTPort_address = "http://ARAQACIPIT.laaraucana.local:50000/sap/xi/engine?type=entry&version=3.0&Sender.Service=WEB_Mobile&Interface=http%3A%2F%2Flautaro.com%2Fxi%2FCRM%2FWEB-Mobile%5EQueryBPStatusRolOUT";

    public java.lang.String getQueryBPStatusRolOUTPortAddress() {
        return QueryBPStatusRolOUTPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String QueryBPStatusRolOUTPortWSDDServiceName = "QueryBPStatusRolOUTPort";

    public java.lang.String getQueryBPStatusRolOUTPortWSDDServiceName() {
        return QueryBPStatusRolOUTPortWSDDServiceName;
    }

    public void setQueryBPStatusRolOUTPortWSDDServiceName(java.lang.String name) {
        QueryBPStatusRolOUTPortWSDDServiceName = name;
    }

    public com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRolOUT getQueryBPStatusRolOUTPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(QueryBPStatusRolOUTPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getQueryBPStatusRolOUTPort(endpoint);
    }

    public com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRolOUT getQueryBPStatusRolOUTPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRolOUTBindingStub _stub = new com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRolOUTBindingStub(portAddress, this);
            _stub.setPortName(getQueryBPStatusRolOUTPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setQueryBPStatusRolOUTPortEndpointAddress(java.lang.String address) {
        QueryBPStatusRolOUTPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRolOUT.class.isAssignableFrom(serviceEndpointInterface)) {
                com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRolOUTBindingStub _stub = new com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRolOUTBindingStub(new java.net.URL(QueryBPStatusRolOUTPort_address), this);
                _stub.setPortName(getQueryBPStatusRolOUTPortWSDDServiceName());
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
        if ("QueryBPStatusRolOUTPort".equals(inputPortName)) {
            return getQueryBPStatusRolOUTPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://lautaro.com/xi/CRM/WEB-Mobile", "QueryBPStatusRolOUTService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://lautaro.com/xi/CRM/WEB-Mobile", "QueryBPStatusRolOUTPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("QueryBPStatusRolOUTPort".equals(portName)) {
            setQueryBPStatusRolOUTPortEndpointAddress(address);
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
