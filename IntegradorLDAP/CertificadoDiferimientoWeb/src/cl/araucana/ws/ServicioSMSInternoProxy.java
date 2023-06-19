package cl.araucana.ws;

public class ServicioSMSInternoProxy implements cl.araucana.ws.ServicioSMSInterno {
  private String _endpoint = null;
  private cl.araucana.ws.ServicioSMSInterno servicioSMSInterno = null;
  
  public ServicioSMSInternoProxy() {
    _initServicioSMSInternoProxy();
  }
  
  public ServicioSMSInternoProxy(String endpoint) {
    _endpoint = endpoint;
    _initServicioSMSInternoProxy();
  }
  
  private void _initServicioSMSInternoProxy() {
    try {
      servicioSMSInterno = (new cl.araucana.ws.ServicioSMSInternoServiceLocator()).getServicioSMSInterno();
      if (servicioSMSInterno != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)servicioSMSInterno)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)servicioSMSInterno)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (servicioSMSInterno != null)
      ((javax.xml.rpc.Stub)servicioSMSInterno)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cl.araucana.ws.ServicioSMSInterno getServicioSMSInterno() {
    if (servicioSMSInterno == null)
      _initServicioSMSInternoProxy();
    return servicioSMSInterno;
  }
  
  public boolean enviarSMS(java.lang.String telefono, java.lang.String mensaje, java.lang.String servicio, java.lang.String cod_negocio) throws java.rmi.RemoteException{
    if (servicioSMSInterno == null)
      _initServicioSMSInternoProxy();
    return servicioSMSInterno.enviarSMS(telefono, mensaje, servicio, cod_negocio);
  }
  
  
}