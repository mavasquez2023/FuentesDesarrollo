/**
 * WSLMEValEmpCCAFServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package lme.cl.gov.lme.www;

public class WSLMEValEmpCCAFServiceLocator extends org.apache.axis.client.Service implements lme.cl.gov.lme.www.WSLMEValEmpCCAFService {

    public WSLMEValEmpCCAFServiceLocator() {
    }


    public WSLMEValEmpCCAFServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WSLMEValEmpCCAFServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WSLMEValEmpCCAF
    private java.lang.String WSLMEValEmpCCAF_address = "http://146.83.1.66:9080/WSLME/services/WSLMEValEmpCCAF";

    public java.lang.String getWSLMEValEmpCCAFAddress() {
        return WSLMEValEmpCCAF_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSLMEValEmpCCAFWSDDServiceName = "WSLMEValEmpCCAF";

    public java.lang.String getWSLMEValEmpCCAFWSDDServiceName() {
        return WSLMEValEmpCCAFWSDDServiceName;
    }

    public void setWSLMEValEmpCCAFWSDDServiceName(java.lang.String name) {
        WSLMEValEmpCCAFWSDDServiceName = name;
    }

    public lme.cl.gov.lme.www.WSLMEValEmpCCAF getWSLMEValEmpCCAF() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSLMEValEmpCCAF_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSLMEValEmpCCAF(endpoint);
    }

    public lme.cl.gov.lme.www.WSLMEValEmpCCAF getWSLMEValEmpCCAF(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            lme.cl.gov.lme.www.WSLMEValEmpCCAFSoapBindingStub _stub = new lme.cl.gov.lme.www.WSLMEValEmpCCAFSoapBindingStub(portAddress, this);
            _stub.setPortName(getWSLMEValEmpCCAFWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSLMEValEmpCCAFEndpointAddress(java.lang.String address) {
        WSLMEValEmpCCAF_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (lme.cl.gov.lme.www.WSLMEValEmpCCAF.class.isAssignableFrom(serviceEndpointInterface)) {
                lme.cl.gov.lme.www.WSLMEValEmpCCAFSoapBindingStub _stub = new lme.cl.gov.lme.www.WSLMEValEmpCCAFSoapBindingStub(new java.net.URL(WSLMEValEmpCCAF_address), this);
                _stub.setPortName(getWSLMEValEmpCCAFWSDDServiceName());
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
        if ("WSLMEValEmpCCAF".equals(inputPortName)) {
            return getWSLMEValEmpCCAF();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "WSLMEValEmpCCAFService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:www:lme:gov:cl:lme", "WSLMEValEmpCCAF"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WSLMEValEmpCCAF".equals(portName)) {
            setWSLMEValEmpCCAFEndpointAddress(address);
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
