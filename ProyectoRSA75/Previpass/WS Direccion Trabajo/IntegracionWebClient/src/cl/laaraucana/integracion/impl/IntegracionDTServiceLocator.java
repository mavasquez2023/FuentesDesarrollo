/**
 * IntegracionDTServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package cl.laaraucana.integracion.impl;

public class IntegracionDTServiceLocator extends org.apache.axis.client.Service implements cl.laaraucana.integracion.impl.IntegracionDTService {

    public IntegracionDTServiceLocator() {
    }


    public IntegracionDTServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IntegracionDTServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for IntegracionDT
    private java.lang.String IntegracionDT_address = "http://localhost:9080/IntegracionWeb/services/IntegracionDT";

    public java.lang.String getIntegracionDTAddress() {
        return IntegracionDT_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String IntegracionDTWSDDServiceName = "IntegracionDT";

    public java.lang.String getIntegracionDTWSDDServiceName() {
        return IntegracionDTWSDDServiceName;
    }

    public void setIntegracionDTWSDDServiceName(java.lang.String name) {
        IntegracionDTWSDDServiceName = name;
    }

    public cl.laaraucana.integracion.impl.IntegracionDT getIntegracionDT() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(IntegracionDT_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getIntegracionDT(endpoint);
    }

    public cl.laaraucana.integracion.impl.IntegracionDT getIntegracionDT(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cl.laaraucana.integracion.impl.IntegracionDTSoapBindingStub _stub = new cl.laaraucana.integracion.impl.IntegracionDTSoapBindingStub(portAddress, this);
            _stub.setPortName(getIntegracionDTWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setIntegracionDTEndpointAddress(java.lang.String address) {
        IntegracionDT_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cl.laaraucana.integracion.impl.IntegracionDT.class.isAssignableFrom(serviceEndpointInterface)) {
                cl.laaraucana.integracion.impl.IntegracionDTSoapBindingStub _stub = new cl.laaraucana.integracion.impl.IntegracionDTSoapBindingStub(new java.net.URL(IntegracionDT_address), this);
                _stub.setPortName(getIntegracionDTWSDDServiceName());
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
        if ("IntegracionDT".equals(inputPortName)) {
            return getIntegracionDT();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://impl.integracion.laaraucana.cl", "IntegracionDTService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://impl.integracion.laaraucana.cl", "IntegracionDT"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("IntegracionDT".equals(portName)) {
            setIntegracionDTEndpointAddress(address);
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
