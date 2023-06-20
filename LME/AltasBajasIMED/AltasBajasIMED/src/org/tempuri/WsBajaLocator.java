/**
 * WsBajaLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class WsBajaLocator extends org.apache.axis.client.Service implements org.tempuri.WsBaja {

    public WsBajaLocator() {
    }


    public WsBajaLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WsBajaLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for wsBajaSoap
    private java.lang.String wsBajaSoap_address = "http://liquidador3pre.i-med.cl/wsImed/wsBajaBeneficiario.asmx";

    public java.lang.String getwsBajaSoapAddress() {
        return wsBajaSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String wsBajaSoapWSDDServiceName = "wsBajaSoap";

    public java.lang.String getwsBajaSoapWSDDServiceName() {
        return wsBajaSoapWSDDServiceName;
    }

    public void setwsBajaSoapWSDDServiceName(java.lang.String name) {
        wsBajaSoapWSDDServiceName = name;
    }

    public org.tempuri.WsBajaSoap getwsBajaSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(wsBajaSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getwsBajaSoap(endpoint);
    }

    public org.tempuri.WsBajaSoap getwsBajaSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.WsBajaSoapStub _stub = new org.tempuri.WsBajaSoapStub(portAddress, this);
            _stub.setPortName(getwsBajaSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setwsBajaSoapEndpointAddress(java.lang.String address) {
        wsBajaSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.tempuri.WsBajaSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.WsBajaSoapStub _stub = new org.tempuri.WsBajaSoapStub(new java.net.URL(wsBajaSoap_address), this);
                _stub.setPortName(getwsBajaSoapWSDDServiceName());
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
        if ("wsBajaSoap".equals(inputPortName)) {
            return getwsBajaSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "wsBaja");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "wsBajaSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("wsBajaSoap".equals(portName)) {
            setwsBajaSoapEndpointAddress(address);
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
