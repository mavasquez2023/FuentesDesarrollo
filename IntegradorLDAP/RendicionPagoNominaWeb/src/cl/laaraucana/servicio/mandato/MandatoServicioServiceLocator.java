/**
 * MandatoServicioServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.laaraucana.servicio.mandato;

public class MandatoServicioServiceLocator extends org.apache.axis.client.Service implements cl.laaraucana.servicio.mandato.MandatoServicioService {

    public MandatoServicioServiceLocator() {
    }


    public MandatoServicioServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MandatoServicioServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MandatoServicioPort
    private java.lang.String MandatoServicioPort_address = "http://localhost:9083/mandatoTransferenciaWeb/MandatoServicioService";

    public java.lang.String getMandatoServicioPortAddress() {
        return MandatoServicioPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MandatoServicioPortWSDDServiceName = "MandatoServicioPort";

    public java.lang.String getMandatoServicioPortWSDDServiceName() {
        return MandatoServicioPortWSDDServiceName;
    }

    public void setMandatoServicioPortWSDDServiceName(java.lang.String name) {
        MandatoServicioPortWSDDServiceName = name;
    }

    public cl.laaraucana.servicio.mandato.MandatoServicioImpl getMandatoServicioPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MandatoServicioPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMandatoServicioPort(endpoint);
    }

    public cl.laaraucana.servicio.mandato.MandatoServicioImpl getMandatoServicioPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cl.laaraucana.servicio.mandato.MandatoServicioPortBindingStub _stub = new cl.laaraucana.servicio.mandato.MandatoServicioPortBindingStub(portAddress, this);
            _stub.setPortName(getMandatoServicioPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMandatoServicioPortEndpointAddress(java.lang.String address) {
        MandatoServicioPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cl.laaraucana.servicio.mandato.MandatoServicioImpl.class.isAssignableFrom(serviceEndpointInterface)) {
                cl.laaraucana.servicio.mandato.MandatoServicioPortBindingStub _stub = new cl.laaraucana.servicio.mandato.MandatoServicioPortBindingStub(new java.net.URL(MandatoServicioPort_address), this);
                _stub.setPortName(getMandatoServicioPortWSDDServiceName());
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
        if ("MandatoServicioPort".equals(inputPortName)) {
            return getMandatoServicioPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicio.laaraucana.cl/mandato", "MandatoServicioService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicio.laaraucana.cl/mandato", "MandatoServicioPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("MandatoServicioPort".equals(portName)) {
            setMandatoServicioPortEndpointAddress(address);
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
