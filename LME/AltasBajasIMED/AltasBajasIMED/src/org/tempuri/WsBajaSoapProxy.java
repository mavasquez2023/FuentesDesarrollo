package org.tempuri;

public class WsBajaSoapProxy implements org.tempuri.WsBajaSoap {
  private String _endpoint = null;
  private org.tempuri.WsBajaSoap wsBajaSoap = null;
  
  public WsBajaSoapProxy() {
    _initWsBajaSoapProxy();
  }
  
  public WsBajaSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initWsBajaSoapProxy();
  }
  
  private void _initWsBajaSoapProxy() {
    try {
      wsBajaSoap = (new org.tempuri.WsBajaLocator()).getwsBajaSoap();
      if (wsBajaSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wsBajaSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wsBajaSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wsBajaSoap != null)
      ((javax.xml.rpc.Stub)wsBajaSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.WsBajaSoap getWsBajaSoap() {
    if (wsBajaSoap == null)
      _initWsBajaSoapProxy();
    return wsBajaSoap;
  }
  
  public void wmImed_SrvBaja(org.tempuri.WmImed_SrvBajaCredenciales credenciales, org.tempuri.WmImed_SrvBajaBeneficiario beneficiario, javax.xml.rpc.holders.StringHolder estado, javax.xml.rpc.holders.StringHolder mensaje, javax.xml.rpc.holders.StringHolder codigoTransaccion) throws java.rmi.RemoteException{
    if (wsBajaSoap == null)
      _initWsBajaSoapProxy();
    wsBajaSoap.wmImed_SrvBaja(credenciales, beneficiario, estado, mensaje, codigoTransaccion);
  }
  
  
}