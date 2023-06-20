/**
 * W2W_IIILocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class W2W_IIILocator extends org.apache.axis.client.Service implements org.tempuri.W2W_III {

    public W2W_IIILocator() {
    }


    public W2W_IIILocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public W2W_IIILocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for w2w_IIISoap
    private java.lang.String w2w_IIISoap_address = "https://pagos.bancoestado.cl/W2W_III/W2W_WS/w2w_III.asmx";

    public java.lang.String getw2w_IIISoapAddress() {
        return w2w_IIISoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String w2w_IIISoapWSDDServiceName = "w2w_IIISoap";

    public java.lang.String getw2w_IIISoapWSDDServiceName() {
        return w2w_IIISoapWSDDServiceName;
    }

    public void setw2w_IIISoapWSDDServiceName(java.lang.String name) {
        w2w_IIISoapWSDDServiceName = name;
    }

    public org.tempuri.W2W_IIISoap getw2w_IIISoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(w2w_IIISoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getw2w_IIISoap(endpoint);
    }

    public org.tempuri.W2W_IIISoap getw2w_IIISoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.W2W_IIISoapStub _stub = new org.tempuri.W2W_IIISoapStub(portAddress, this);
            _stub.setPortName(getw2w_IIISoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setw2w_IIISoapEndpointAddress(java.lang.String address) {
        w2w_IIISoap_address = address;
    }


    // Use to get a proxy class for w2w_IIISoap12
    private java.lang.String w2w_IIISoap12_address = "https://pagos.bancoestado.cl/W2W_III/W2W_WS/w2w_III.asmx";

    public java.lang.String getw2w_IIISoap12Address() {
        return w2w_IIISoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String w2w_IIISoap12WSDDServiceName = "w2w_IIISoap12";

    public java.lang.String getw2w_IIISoap12WSDDServiceName() {
        return w2w_IIISoap12WSDDServiceName;
    }

    public void setw2w_IIISoap12WSDDServiceName(java.lang.String name) {
        w2w_IIISoap12WSDDServiceName = name;
    }

    public org.tempuri.W2W_IIISoap getw2w_IIISoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(w2w_IIISoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getw2w_IIISoap12(endpoint);
    }

    public org.tempuri.W2W_IIISoap getw2w_IIISoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.W2W_IIISoap12Stub _stub = new org.tempuri.W2W_IIISoap12Stub(portAddress, this);
            _stub.setPortName(getw2w_IIISoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setw2w_IIISoap12EndpointAddress(java.lang.String address) {
        w2w_IIISoap12_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.tempuri.W2W_IIISoap.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.W2W_IIISoapStub _stub = new org.tempuri.W2W_IIISoapStub(new java.net.URL(w2w_IIISoap_address), this);
                _stub.setPortName(getw2w_IIISoapWSDDServiceName());
                return _stub;
            }
            if (org.tempuri.W2W_IIISoap.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.W2W_IIISoap12Stub _stub = new org.tempuri.W2W_IIISoap12Stub(new java.net.URL(w2w_IIISoap12_address), this);
                _stub.setPortName(getw2w_IIISoap12WSDDServiceName());
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
        if ("w2w_IIISoap".equals(inputPortName)) {
            return getw2w_IIISoap();
        }
        else if ("w2w_IIISoap12".equals(inputPortName)) {
            return getw2w_IIISoap12();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "w2w_III");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "w2w_IIISoap"));
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "w2w_IIISoap12"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("w2w_IIISoap".equals(portName)) {
            setw2w_IIISoapEndpointAddress(address);
        }
        else 
if ("w2w_IIISoap12".equals(portName)) {
            setw2w_IIISoap12EndpointAddress(address);
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
