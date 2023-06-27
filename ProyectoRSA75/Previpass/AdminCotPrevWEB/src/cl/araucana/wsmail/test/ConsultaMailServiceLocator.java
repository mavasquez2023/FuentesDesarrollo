/**
 * ConsultaMailServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package cl.araucana.wsmail.test;

import cl.araucana.cp.distribuidor.base.Constants;

public class ConsultaMailServiceLocator extends org.apache.axis.client.Service implements cl.araucana.wsmail.test.ConsultaMailService {

    public ConsultaMailServiceLocator() {
    }


    public ConsultaMailServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ConsultaMailServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ConsultaMail
    private java.lang.String ConsultaMail_address = Constants.URL_WS_MAIL_SIS; //"http://cpe-test.cp.cl:9080/WSConsultaMail/services/ConsultaMail";

    public java.lang.String getConsultaMailAddress() {
        return ConsultaMail_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ConsultaMailWSDDServiceName = "ConsultaMail";

    public java.lang.String getConsultaMailWSDDServiceName() {
        return ConsultaMailWSDDServiceName;
    }

    public void setConsultaMailWSDDServiceName(java.lang.String name) {
        ConsultaMailWSDDServiceName = name;
    }

    public cl.araucana.wsmail.test.ConsultaMail getConsultaMail() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ConsultaMail_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getConsultaMail(endpoint);
    }

    public cl.araucana.wsmail.test.ConsultaMail getConsultaMail(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cl.araucana.wsmail.test.ConsultaMailSoapBindingStub _stub = new cl.araucana.wsmail.test.ConsultaMailSoapBindingStub(portAddress, this);
            _stub.setPortName(getConsultaMailWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setConsultaMailEndpointAddress(java.lang.String address) {
        ConsultaMail_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cl.araucana.wsmail.test.ConsultaMail.class.isAssignableFrom(serviceEndpointInterface)) {
                cl.araucana.wsmail.test.ConsultaMailSoapBindingStub _stub = new cl.araucana.wsmail.test.ConsultaMailSoapBindingStub(new java.net.URL(ConsultaMail_address), this);
                _stub.setPortName(getConsultaMailWSDDServiceName());
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
        if ("ConsultaMail".equals(inputPortName)) {
            return getConsultaMail();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://test.wsmail.araucana.cl", "ConsultaMailService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://test.wsmail.araucana.cl", "ConsultaMail"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ConsultaMail".equals(portName)) {
            setConsultaMailEndpointAddress(address);
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
