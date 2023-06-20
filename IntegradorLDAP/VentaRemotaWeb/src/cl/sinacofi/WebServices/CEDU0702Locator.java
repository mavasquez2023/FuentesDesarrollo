/**
 * CEDU0702Locator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.sinacofi.WebServices;

public class CEDU0702Locator extends org.apache.axis.client.Service implements cl.sinacofi.WebServices.CEDU0702 {

    public CEDU0702Locator() {
    }


    public CEDU0702Locator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CEDU0702Locator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CEDU0702Soap
    private java.lang.String CEDU0702Soap_address = "https://www.sinacofi.cl/SinacofiWS_CEDU/CEDU0702.asmx";

    public java.lang.String getCEDU0702SoapAddress() {
        return CEDU0702Soap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CEDU0702SoapWSDDServiceName = "CEDU0702Soap";

    public java.lang.String getCEDU0702SoapWSDDServiceName() {
        return CEDU0702SoapWSDDServiceName;
    }

    public void setCEDU0702SoapWSDDServiceName(java.lang.String name) {
        CEDU0702SoapWSDDServiceName = name;
    }

    public cl.sinacofi.WebServices.CEDU0702Soap getCEDU0702Soap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CEDU0702Soap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCEDU0702Soap(endpoint);
    }

    public cl.sinacofi.WebServices.CEDU0702Soap getCEDU0702Soap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cl.sinacofi.WebServices.CEDU0702SoapStub _stub = new cl.sinacofi.WebServices.CEDU0702SoapStub(portAddress, this);
            _stub.setPortName(getCEDU0702SoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCEDU0702SoapEndpointAddress(java.lang.String address) {
        CEDU0702Soap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cl.sinacofi.WebServices.CEDU0702Soap.class.isAssignableFrom(serviceEndpointInterface)) {
                cl.sinacofi.WebServices.CEDU0702SoapStub _stub = new cl.sinacofi.WebServices.CEDU0702SoapStub(new java.net.URL(CEDU0702Soap_address), this);
                _stub.setPortName(getCEDU0702SoapWSDDServiceName());
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
        if ("CEDU0702Soap".equals(inputPortName)) {
            return getCEDU0702Soap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "CEDU0702");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "CEDU0702Soap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CEDU0702Soap".equals(portName)) {
            setCEDU0702SoapEndpointAddress(address);
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
