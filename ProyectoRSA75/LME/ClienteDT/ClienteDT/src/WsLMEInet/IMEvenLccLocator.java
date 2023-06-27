/**
 * IMEvenLccLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package WsLMEInet;

public class IMEvenLccLocator extends org.apache.axis.client.Service implements WsLMEInet.IMEvenLcc {

    public IMEvenLccLocator() {
    }


    public IMEvenLccLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IMEvenLccLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WsLMEInetPort
    private java.lang.String WsLMEInetPort_address = "http://10.11.87.27:8080/LME/IMEvenLcc";

    public java.lang.String getWsLMEInetPortAddress() {
        return WsLMEInetPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WsLMEInetPortWSDDServiceName = "WsLMEInetPort";

    public java.lang.String getWsLMEInetPortWSDDServiceName() {
        return WsLMEInetPortWSDDServiceName;
    }

    public void setWsLMEInetPortWSDDServiceName(java.lang.String name) {
        WsLMEInetPortWSDDServiceName = name;
    }

    public WsLMEInet.WsLMEInetPortType getWsLMEInetPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WsLMEInetPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWsLMEInetPort(endpoint);
    }

    public WsLMEInet.WsLMEInetPortType getWsLMEInetPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            WsLMEInet.WsLMEInetBindingStub _stub = new WsLMEInet.WsLMEInetBindingStub(portAddress, this);
            _stub.setPortName(getWsLMEInetPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWsLMEInetPortEndpointAddress(java.lang.String address) {
        WsLMEInetPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (WsLMEInet.WsLMEInetPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                WsLMEInet.WsLMEInetBindingStub _stub = new WsLMEInet.WsLMEInetBindingStub(new java.net.URL(WsLMEInetPort_address), this);
                _stub.setPortName(getWsLMEInetPortWSDDServiceName());
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
        if ("WsLMEInetPort".equals(inputPortName)) {
            return getWsLMEInetPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:WsLMEInet", "IMEvenLcc");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:WsLMEInet", "WsLMEInetPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WsLMEInetPort".equals(portName)) {
            setWsLMEInetPortEndpointAddress(address);
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
