/**
 * SimulaCreditoServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicios.simulaCredito;

public class SimulaCreditoServiceLocator extends org.apache.axis.client.Service implements cl.laaraucana.servicios.simulaCredito.SimulaCreditoService {

    public SimulaCreditoServiceLocator() {
    }


    public SimulaCreditoServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SimulaCreditoServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SimulaCreditoPort
    private java.lang.String SimulaCreditoPort_address = "http://localhost:9080/WSSimulaCredito/SimulaCreditoService";

    public java.lang.String getSimulaCreditoPortAddress() {
        return SimulaCreditoPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SimulaCreditoPortWSDDServiceName = "SimulaCreditoPort";

    public java.lang.String getSimulaCreditoPortWSDDServiceName() {
        return SimulaCreditoPortWSDDServiceName;
    }

    public void setSimulaCreditoPortWSDDServiceName(java.lang.String name) {
        SimulaCreditoPortWSDDServiceName = name;
    }

    public cl.laaraucana.servicios.simulaCredito.SimulaCreditoImpl getSimulaCreditoPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SimulaCreditoPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSimulaCreditoPort(endpoint);
    }

    public cl.laaraucana.servicios.simulaCredito.SimulaCreditoImpl getSimulaCreditoPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cl.laaraucana.servicios.simulaCredito.SimulaCreditoPortBindingStub _stub = new cl.laaraucana.servicios.simulaCredito.SimulaCreditoPortBindingStub(portAddress, this);
            _stub.setPortName(getSimulaCreditoPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSimulaCreditoPortEndpointAddress(java.lang.String address) {
        SimulaCreditoPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cl.laaraucana.servicios.simulaCredito.SimulaCreditoImpl.class.isAssignableFrom(serviceEndpointInterface)) {
                cl.laaraucana.servicios.simulaCredito.SimulaCreditoPortBindingStub _stub = new cl.laaraucana.servicios.simulaCredito.SimulaCreditoPortBindingStub(new java.net.URL(SimulaCreditoPort_address), this);
                _stub.setPortName(getSimulaCreditoPortWSDDServiceName());
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
        if ("SimulaCreditoPort".equals(inputPortName)) {
            return getSimulaCreditoPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicios.laaraucana.cl/simulaCredito", "SimulaCreditoService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicios.laaraucana.cl/simulaCredito", "SimulaCreditoPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SimulaCreditoPort".equals(portName)) {
            setSimulaCreditoPortEndpointAddress(address);
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
