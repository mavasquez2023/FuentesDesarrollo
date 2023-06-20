/**
 * WsAltaLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class WsAltaLocator extends org.apache.axis.client.Service implements org.tempuri.WsAlta {

    public WsAltaLocator() {
    }


    public WsAltaLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WsAltaLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for wsAltaSoap
    private java.lang.String wsAltaSoap_address = "http://liquidador3pre.i-med.cl/wsImed/wsAltaBeneficiario.asmx";

    public java.lang.String getwsAltaSoapAddress() {
        return wsAltaSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String wsAltaSoapWSDDServiceName = "wsAltaSoap";

    public java.lang.String getwsAltaSoapWSDDServiceName() {
        return wsAltaSoapWSDDServiceName;
    }

    public void setwsAltaSoapWSDDServiceName(java.lang.String name) {
        wsAltaSoapWSDDServiceName = name;
    }

    public org.tempuri.WsAltaSoap getwsAltaSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(wsAltaSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getwsAltaSoap(endpoint);
    }

    public org.tempuri.WsAltaSoap getwsAltaSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.WsAltaSoapStub _stub = new org.tempuri.WsAltaSoapStub(portAddress, this);
            _stub.setPortName(getwsAltaSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setwsAltaSoapEndpointAddress(java.lang.String address) {
        wsAltaSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.tempuri.WsAltaSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.WsAltaSoapStub _stub = new org.tempuri.WsAltaSoapStub(new java.net.URL(wsAltaSoap_address), this);
                _stub.setPortName(getwsAltaSoapWSDDServiceName());
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
        if ("wsAltaSoap".equals(inputPortName)) {
            return getwsAltaSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "wsAlta");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "wsAltaSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("wsAltaSoap".equals(portName)) {
            setwsAltaSoapEndpointAddress(address);
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
