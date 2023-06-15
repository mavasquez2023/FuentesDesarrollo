/**
 * QueryBPStatusOUTServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lautaro.xi.CRM.WEB_Mobile;

public class QueryBPStatusOUTServiceLocator extends org.apache.axis.client.Service implements com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusOUTService {

    public QueryBPStatusOUTServiceLocator() {
    }


    public QueryBPStatusOUTServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public QueryBPStatusOUTServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for QueryBPStatusOUTPort
    private java.lang.String QueryBPStatusOUTPort_address = "http://ARADECICAP.sap.novis.cl:50000/sap/xi/engine?type=entry&version=3.0&Sender.Service=WEB_Mobile&Interface=http%3A%2F%2Flautaro.com%2Fxi%2FCRM%2FWEB-Mobile%5EQueryBPStatusOUT";

    public java.lang.String getQueryBPStatusOUTPortAddress() {
        return QueryBPStatusOUTPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String QueryBPStatusOUTPortWSDDServiceName = "QueryBPStatusOUTPort";

    public java.lang.String getQueryBPStatusOUTPortWSDDServiceName() {
        return QueryBPStatusOUTPortWSDDServiceName;
    }

    public void setQueryBPStatusOUTPortWSDDServiceName(java.lang.String name) {
        QueryBPStatusOUTPortWSDDServiceName = name;
    }

    public com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusOUT getQueryBPStatusOUTPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(QueryBPStatusOUTPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getQueryBPStatusOUTPort(endpoint);
    }

    public com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusOUT getQueryBPStatusOUTPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusOUTBindingStub _stub = new com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusOUTBindingStub(portAddress, this);
            _stub.setPortName(getQueryBPStatusOUTPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setQueryBPStatusOUTPortEndpointAddress(java.lang.String address) {
        QueryBPStatusOUTPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusOUT.class.isAssignableFrom(serviceEndpointInterface)) {
                com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusOUTBindingStub _stub = new com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusOUTBindingStub(new java.net.URL(QueryBPStatusOUTPort_address), this);
                _stub.setPortName(getQueryBPStatusOUTPortWSDDServiceName());
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
        if ("QueryBPStatusOUTPort".equals(inputPortName)) {
            return getQueryBPStatusOUTPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://lautaro.com/xi/CRM/WEB-Mobile", "QueryBPStatusOUTService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://lautaro.com/xi/CRM/WEB-Mobile", "QueryBPStatusOUTPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("QueryBPStatusOUTPort".equals(portName)) {
            setQueryBPStatusOUTPortEndpointAddress(address);
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
