/**
 * SNPV1801Locator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.sinacofi.WebServices;

public class SNPV1801Locator extends org.apache.axis.client.Service implements cl.sinacofi.WebServices.SNPV1801 {

    public SNPV1801Locator() {
    }


    public SNPV1801Locator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SNPV1801Locator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SNPV1801Soap
    private java.lang.String SNPV1801Soap_address = "https://www.sinacofi.cl/SinacofiWS_SNPV/SNPV1801.asmx";

    public java.lang.String getSNPV1801SoapAddress() {
        return SNPV1801Soap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SNPV1801SoapWSDDServiceName = "SNPV1801Soap";

    public java.lang.String getSNPV1801SoapWSDDServiceName() {
        return SNPV1801SoapWSDDServiceName;
    }

    public void setSNPV1801SoapWSDDServiceName(java.lang.String name) {
        SNPV1801SoapWSDDServiceName = name;
    }

    public cl.sinacofi.WebServices.SNPV1801Soap getSNPV1801Soap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SNPV1801Soap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSNPV1801Soap(endpoint);
    }

    public cl.sinacofi.WebServices.SNPV1801Soap getSNPV1801Soap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cl.sinacofi.WebServices.SNPV1801SoapStub _stub = new cl.sinacofi.WebServices.SNPV1801SoapStub(portAddress, this);
            _stub.setPortName(getSNPV1801SoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSNPV1801SoapEndpointAddress(java.lang.String address) {
        SNPV1801Soap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cl.sinacofi.WebServices.SNPV1801Soap.class.isAssignableFrom(serviceEndpointInterface)) {
                cl.sinacofi.WebServices.SNPV1801SoapStub _stub = new cl.sinacofi.WebServices.SNPV1801SoapStub(new java.net.URL(SNPV1801Soap_address), this);
                _stub.setPortName(getSNPV1801SoapWSDDServiceName());
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
        if ("SNPV1801Soap".equals(inputPortName)) {
            return getSNPV1801Soap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "SNPV1801");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "SNPV1801Soap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SNPV1801Soap".equals(portName)) {
            setSNPV1801SoapEndpointAddress(address);
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
