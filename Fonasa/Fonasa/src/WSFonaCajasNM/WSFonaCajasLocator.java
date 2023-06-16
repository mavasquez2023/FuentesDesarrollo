/**
 * WSFonaCajasLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WSFonaCajasNM;

public class WSFonaCajasLocator extends org.apache.axis.client.Service implements WSFonaCajasNM.WSFonaCajas {

    public WSFonaCajasLocator() {
    }


    public WSFonaCajasLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WSFonaCajasLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WSFonaCajasSoap
    private java.lang.String WSFonaCajasSoap_address = "http://198.41.41.37/WsFonaCajas/WSFonaCajas.asmx";

    public java.lang.String getWSFonaCajasSoapAddress() {
        return WSFonaCajasSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSFonaCajasSoapWSDDServiceName = "WSFonaCajasSoap";

    public java.lang.String getWSFonaCajasSoapWSDDServiceName() {
        return WSFonaCajasSoapWSDDServiceName;
    }

    public void setWSFonaCajasSoapWSDDServiceName(java.lang.String name) {
        WSFonaCajasSoapWSDDServiceName = name;
    }

    public WSFonaCajasNM.WSFonaCajasSoap getWSFonaCajasSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSFonaCajasSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSFonaCajasSoap(endpoint);
    }

    public WSFonaCajasNM.WSFonaCajasSoap getWSFonaCajasSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            WSFonaCajasNM.WSFonaCajasSoapStub _stub = new WSFonaCajasNM.WSFonaCajasSoapStub(portAddress, this);
            _stub.setPortName(getWSFonaCajasSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSFonaCajasSoapEndpointAddress(java.lang.String address) {
        WSFonaCajasSoap_address = address;
    }


    // Use to get a proxy class for WSFonaCajasSoap12
    private java.lang.String WSFonaCajasSoap12_address = "http://198.41.41.37/WsFonaCajas/WSFonaCajas.asmx";

    public java.lang.String getWSFonaCajasSoap12Address() {
        return WSFonaCajasSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSFonaCajasSoap12WSDDServiceName = "WSFonaCajasSoap12";

    public java.lang.String getWSFonaCajasSoap12WSDDServiceName() {
        return WSFonaCajasSoap12WSDDServiceName;
    }

    public void setWSFonaCajasSoap12WSDDServiceName(java.lang.String name) {
        WSFonaCajasSoap12WSDDServiceName = name;
    }

    public WSFonaCajasNM.WSFonaCajasSoap getWSFonaCajasSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSFonaCajasSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSFonaCajasSoap12(endpoint);
    }

    public WSFonaCajasNM.WSFonaCajasSoap getWSFonaCajasSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            WSFonaCajasNM.WSFonaCajasSoap12Stub _stub = new WSFonaCajasNM.WSFonaCajasSoap12Stub(portAddress, this);
            _stub.setPortName(getWSFonaCajasSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSFonaCajasSoap12EndpointAddress(java.lang.String address) {
        WSFonaCajasSoap12_address = address;
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
            if (WSFonaCajasNM.WSFonaCajasSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                WSFonaCajasNM.WSFonaCajasSoapStub _stub = new WSFonaCajasNM.WSFonaCajasSoapStub(new java.net.URL(WSFonaCajasSoap_address), this);
                _stub.setPortName(getWSFonaCajasSoapWSDDServiceName());
                return _stub;
            }
            if (WSFonaCajasNM.WSFonaCajasSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                WSFonaCajasNM.WSFonaCajasSoap12Stub _stub = new WSFonaCajasNM.WSFonaCajasSoap12Stub(new java.net.URL(WSFonaCajasSoap12_address), this);
                _stub.setPortName(getWSFonaCajasSoap12WSDDServiceName());
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
        if ("WSFonaCajasSoap".equals(inputPortName)) {
            return getWSFonaCajasSoap();
        }
        else if ("WSFonaCajasSoap12".equals(inputPortName)) {
            return getWSFonaCajasSoap12();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://WSFonaCajasNM/", "WSFonaCajas");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "WSFonaCajasSoap"));
            ports.add(new javax.xml.namespace.QName("http://WSFonaCajasNM/", "WSFonaCajasSoap12"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WSFonaCajasSoap".equals(portName)) {
            setWSFonaCajasSoapEndpointAddress(address);
        }
        else 
if ("WSFonaCajasSoap12".equals(portName)) {
            setWSFonaCajasSoap12EndpointAddress(address);
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
