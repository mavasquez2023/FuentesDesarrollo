package cl.araucana.spl.cliente.webServices.bbva;

public class ComercioSoapProxy implements cl.araucana.spl.cliente.webServices.bbva.ComercioSoap {
  private String _endpoint = null;
  private cl.araucana.spl.cliente.webServices.bbva.ComercioSoap comercioSoap = null;
  
  public ComercioSoapProxy() {
    _initComercioSoapProxy();
  }
  
  private void _initComercioSoapProxy() {
    try {
      comercioSoap = (new cl.araucana.spl.cliente.webServices.bbva.ComercioLocator()).getComercioSoap();
      if (comercioSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)comercioSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)comercioSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (comercioSoap != null)
      ((javax.xml.rpc.Stub)comercioSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cl.araucana.spl.cliente.webServices.bbva.ComercioSoap getComercioSoap() {
    if (comercioSoap == null)
      _initComercioSoapProxy();
    return comercioSoap;
  }
  
  public java.lang.String solicitarAcceso(int comercioID, java.lang.String clave) throws java.rmi.RemoteException{
    if (comercioSoap == null)
      _initComercioSoapProxy();
    return comercioSoap.solicitarAcceso(comercioID, clave);
  }
  
  public java.lang.String enviarTransaccion(int comercioID, java.lang.String llave, java.lang.String transaccion, int monto, int cantidadPagos, java.lang.String pagos) throws java.rmi.RemoteException{
    if (comercioSoap == null)
      _initComercioSoapProxy();
    return comercioSoap.enviarTransaccion(comercioID, llave, transaccion, monto, cantidadPagos, pagos);
  }
  
  
}