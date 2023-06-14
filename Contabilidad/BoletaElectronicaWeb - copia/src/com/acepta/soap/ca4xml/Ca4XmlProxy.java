package com.acepta.soap.ca4xml;

public class Ca4XmlProxy implements com.acepta.soap.ca4xml.Ca4Xml_PortType {
  private String _endpoint = null;
  private com.acepta.soap.ca4xml.Ca4Xml_PortType ca4Xml_PortType = null;
  
  public Ca4XmlProxy() {
    _initCa4XmlProxy();
  }
  
  public Ca4XmlProxy(String endpoint) {
    _endpoint = endpoint;
    _initCa4XmlProxy();
  }
  
  private void _initCa4XmlProxy() {
    try {
      ca4Xml_PortType = (new com.acepta.soap.ca4xml.Ca4Xml_ServiceLocator()).getca4xml();
      if (ca4Xml_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)ca4Xml_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)ca4Xml_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (ca4Xml_PortType != null)
      ((javax.xml.rpc.Stub)ca4Xml_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.acepta.soap.ca4xml.Ca4Xml_PortType getCa4Xml_PortType() {
    if (ca4Xml_PortType == null)
      _initCa4XmlProxy();
    return ca4Xml_PortType;
  }
  
  public com.acepta.soap.ca4xml.Ca4XmlResponseRetval ca4Xml(java.lang.String docid, java.lang.String comando, java.lang.String parametros, com.acepta.soap.ca4xml.Ca4XmlDatos datos) throws java.rmi.RemoteException{
    if (ca4Xml_PortType == null)
      _initCa4XmlProxy();
    return ca4Xml_PortType.ca4Xml(docid, comando, parametros, datos);
  }
  
  
}