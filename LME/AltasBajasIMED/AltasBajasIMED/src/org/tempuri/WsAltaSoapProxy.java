package org.tempuri;

public class WsAltaSoapProxy implements org.tempuri.WsAltaSoap {
  private String _endpoint = null;
  private org.tempuri.WsAltaSoap wsAltaSoap = null;
  
  public WsAltaSoapProxy() {
    _initWsAltaSoapProxy();
  }
  
  public WsAltaSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initWsAltaSoapProxy();
  }
  
  private void _initWsAltaSoapProxy() {
    try {
      wsAltaSoap = (new org.tempuri.WsAltaLocator()).getwsAltaSoap();
      if (wsAltaSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wsAltaSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wsAltaSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wsAltaSoap != null)
      ((javax.xml.rpc.Stub)wsAltaSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.WsAltaSoap getWsAltaSoap() {
    if (wsAltaSoap == null)
      _initWsAltaSoapProxy();
    return wsAltaSoap;
  }
  
  public void wmImed_SrvAlta(org.tempuri.WmImed_SrvAltaCredenciales credenciales, org.tempuri.WmImed_SrvAltaBeneficiario beneficiario, javax.xml.rpc.holders.StringHolder estado, javax.xml.rpc.holders.StringHolder mensaje, javax.xml.rpc.holders.StringHolder codigoTransaccion) throws java.rmi.RemoteException{
    if (wsAltaSoap == null)
      _initWsAltaSoapProxy();
    wsAltaSoap.wmImed_SrvAlta(credenciales, beneficiario, estado, mensaje, codigoTransaccion);
  }
  
  
}