/**
 * CEDU0102Locator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.diferimientoEspecial.sinacofi;

import java.rmi.Remote;

public class CEDU0102Locator extends org.apache.axis.client.Service implements cl.laaraucana.diferimientoEspecial.sinacofi.CEDU0102 {

    public CEDU0102Locator() {
    }


    public CEDU0102Locator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CEDU0102Locator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CEDU0102Soap
    private java.lang.String CEDU0102Soap_address = "https://168.231.200.127/SinacofiWS_CEDU/CEDU0102.asmx";

    public java.lang.String getCEDU0102SoapAddress() {
        return CEDU0102Soap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CEDU0102SoapWSDDServiceName = "CEDU0102Soap";

    public java.lang.String getCEDU0102SoapWSDDServiceName() {
        return CEDU0102SoapWSDDServiceName;
    }

    public void setCEDU0102SoapWSDDServiceName(java.lang.String name) {
        CEDU0102SoapWSDDServiceName = name;
    }

    public cl.laaraucana.diferimientoEspecial.sinacofi.CEDU0102Soap getCEDU0102Soap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CEDU0102Soap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCEDU0102Soap(endpoint);
    }

    public cl.laaraucana.diferimientoEspecial.sinacofi.CEDU0102Soap getCEDU0102Soap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
        	cl.laaraucana.diferimientoEspecial.sinacofi.CEDU0102SoapStub _stub = new cl.laaraucana.diferimientoEspecial.sinacofi.CEDU0102SoapStub(portAddress, this);
            _stub.setPortName(getCEDU0102SoapWSDDServiceName());
            return (CEDU0102Soap) _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCEDU0102SoapEndpointAddress(java.lang.String address) {
        CEDU0102Soap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cl.laaraucana.diferimientoEspecial.sinacofi.CEDU0102Soap.class.isAssignableFrom(serviceEndpointInterface)) {
                cl.laaraucana.diferimientoEspecial.sinacofi.CEDU0102SoapStub _stub = new cl.laaraucana.diferimientoEspecial.sinacofi.CEDU0102SoapStub(new java.net.URL(CEDU0102Soap_address), this);
                _stub.setPortName(getCEDU0102SoapWSDDServiceName());
                return (Remote) _stub;
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
        if ("CEDU0102Soap".equals(inputPortName)) {
            return getCEDU0102Soap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "CEDU0102");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://sinacofi.cl/WebServices", "CEDU0102Soap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CEDU0102Soap".equals(portName)) {
            setCEDU0102SoapEndpointAddress(address);
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
