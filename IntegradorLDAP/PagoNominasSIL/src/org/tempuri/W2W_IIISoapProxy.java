package org.tempuri;

public class W2W_IIISoapProxy implements org.tempuri.W2W_IIISoap {
  private String _endpoint = null;
  private org.tempuri.W2W_IIISoap w2W_IIISoap = null;
  
  public W2W_IIISoapProxy() {
    _initW2W_IIISoapProxy();
  }
  
  public W2W_IIISoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initW2W_IIISoapProxy();
  }
  
  private void _initW2W_IIISoapProxy() {
    try {
      w2W_IIISoap = (new org.tempuri.W2W_IIILocator()).getw2w_IIISoap();
      if (w2W_IIISoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)w2W_IIISoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)w2W_IIISoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (w2W_IIISoap != null)
      ((javax.xml.rpc.Stub)w2W_IIISoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.W2W_IIISoap getW2W_IIISoap() {
    if (w2W_IIISoap == null)
      _initW2W_IIISoapProxy();
    return w2W_IIISoap;
  }
  
  public java.lang.String WS_W2WEntrada(java.lang.String xmlIntegracion) throws java.rmi.RemoteException{
    if (w2W_IIISoap == null)
      _initW2W_IIISoapProxy();
    return w2W_IIISoap.WS_W2WEntrada(xmlIntegracion);
  }
  
  
}