/**
 * PlatinumImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.dicom.dwww.gru_wse01_solinforut.services.PlatinumService;

public class PlatinumImplServiceLocator extends org.apache.axis.client.Service implements cl.dicom.dwww.gru_wse01_solinforut.services.PlatinumService.PlatinumImplService {

    public PlatinumImplServiceLocator() {
    }


    public PlatinumImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PlatinumImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PlatinumService
    private java.lang.String PlatinumService_address = "http://dwww.dicom.cl/gru.wse01.solinforut/services/PlatinumService";

    public java.lang.String getPlatinumServiceAddress() {
        return PlatinumService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PlatinumServiceWSDDServiceName = "PlatinumService";

    public java.lang.String getPlatinumServiceWSDDServiceName() {
        return PlatinumServiceWSDDServiceName;
    }

    public void setPlatinumServiceWSDDServiceName(java.lang.String name) {
        PlatinumServiceWSDDServiceName = name;
    }

    public cl.dicom.dwww.gru_wse01_solinforut.services.PlatinumService.PlatinumImpl getPlatinumService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PlatinumService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPlatinumService(endpoint);
    }

    public cl.dicom.dwww.gru_wse01_solinforut.services.PlatinumService.PlatinumImpl getPlatinumService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cl.dicom.dwww.gru_wse01_solinforut.services.PlatinumService.PlatinumServiceSoapBindingStub _stub = new cl.dicom.dwww.gru_wse01_solinforut.services.PlatinumService.PlatinumServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getPlatinumServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPlatinumServiceEndpointAddress(java.lang.String address) {
        PlatinumService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cl.dicom.dwww.gru_wse01_solinforut.services.PlatinumService.PlatinumImpl.class.isAssignableFrom(serviceEndpointInterface)) {
                cl.dicom.dwww.gru_wse01_solinforut.services.PlatinumService.PlatinumServiceSoapBindingStub _stub = new cl.dicom.dwww.gru_wse01_solinforut.services.PlatinumService.PlatinumServiceSoapBindingStub(new java.net.URL(PlatinumService_address), this);
                _stub.setPortName(getPlatinumServiceWSDDServiceName());
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
        if ("PlatinumService".equals(inputPortName)) {
            return getPlatinumService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://dwww.dicom.cl/gru.wse01.solinforut/services/PlatinumService", "PlatinumImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://dwww.dicom.cl/gru.wse01.solinforut/services/PlatinumService", "PlatinumService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PlatinumService".equals(portName)) {
            setPlatinumServiceEndpointAddress(address);
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
