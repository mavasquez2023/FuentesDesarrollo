/**
 * ConsultaPagoEnExcesoServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.araucana.autoconsulta.ws.pagoenexceso;

public class ConsultaPagoEnExcesoServiceLocator extends org.apache.axis.client.Service implements cl.araucana.autoconsulta.ws.pagoenexceso.ConsultaPagoEnExcesoService {

    public ConsultaPagoEnExcesoServiceLocator() {
    }


    public ConsultaPagoEnExcesoServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ConsultaPagoEnExcesoServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ConsultaPagoEnExcesoPort
    private java.lang.String ConsultaPagoEnExcesoPort_address = "http://146.83.1.35:9080/PagoEnExcesoService/ConsultaPagoEnExcesoService";

    public java.lang.String getConsultaPagoEnExcesoPortAddress() {
        return ConsultaPagoEnExcesoPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ConsultaPagoEnExcesoPortWSDDServiceName = "ConsultaPagoEnExcesoPort";

    public java.lang.String getConsultaPagoEnExcesoPortWSDDServiceName() {
        return ConsultaPagoEnExcesoPortWSDDServiceName;
    }

    public void setConsultaPagoEnExcesoPortWSDDServiceName(java.lang.String name) {
        ConsultaPagoEnExcesoPortWSDDServiceName = name;
    }

    public cl.araucana.autoconsulta.ws.pagoenexceso.ConsultaPagoEnExcesoDelegate getConsultaPagoEnExcesoPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ConsultaPagoEnExcesoPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getConsultaPagoEnExcesoPort(endpoint);
    }

    public cl.araucana.autoconsulta.ws.pagoenexceso.ConsultaPagoEnExcesoDelegate getConsultaPagoEnExcesoPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cl.araucana.autoconsulta.ws.pagoenexceso.ConsultaPagoEnExcesoPortBindingStub _stub = new cl.araucana.autoconsulta.ws.pagoenexceso.ConsultaPagoEnExcesoPortBindingStub(portAddress, this);
            _stub.setPortName(getConsultaPagoEnExcesoPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setConsultaPagoEnExcesoPortEndpointAddress(java.lang.String address) {
        ConsultaPagoEnExcesoPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cl.araucana.autoconsulta.ws.pagoenexceso.ConsultaPagoEnExcesoDelegate.class.isAssignableFrom(serviceEndpointInterface)) {
                cl.araucana.autoconsulta.ws.pagoenexceso.ConsultaPagoEnExcesoPortBindingStub _stub = new cl.araucana.autoconsulta.ws.pagoenexceso.ConsultaPagoEnExcesoPortBindingStub(new java.net.URL(ConsultaPagoEnExcesoPort_address), this);
                _stub.setPortName(getConsultaPagoEnExcesoPortWSDDServiceName());
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
        if ("ConsultaPagoEnExcesoPort".equals(inputPortName)) {
            return getConsultaPagoEnExcesoPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://delegate.pagoenexceso.laaraucana.cl/", "ConsultaPagoEnExcesoService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://delegate.pagoenexceso.laaraucana.cl/", "ConsultaPagoEnExcesoPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ConsultaPagoEnExcesoPort".equals(portName)) {
            setConsultaPagoEnExcesoPortEndpointAddress(address);
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
