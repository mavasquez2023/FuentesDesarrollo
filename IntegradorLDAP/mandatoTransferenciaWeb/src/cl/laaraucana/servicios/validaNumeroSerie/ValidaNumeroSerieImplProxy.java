package cl.laaraucana.servicios.validaNumeroSerie;

public class ValidaNumeroSerieImplProxy implements cl.laaraucana.servicios.validaNumeroSerie.ValidaNumeroSerieImpl {
  private String _endpoint = null;
  private cl.laaraucana.servicios.validaNumeroSerie.ValidaNumeroSerieImpl validaNumeroSerieImpl = null;
  
  public ValidaNumeroSerieImplProxy() {
    _initValidaNumeroSerieImplProxy();
  }
  
  public ValidaNumeroSerieImplProxy(String endpoint) {
    _endpoint = endpoint;
    _initValidaNumeroSerieImplProxy();
  }
  
  private void _initValidaNumeroSerieImplProxy() {
    try {
      validaNumeroSerieImpl = (new cl.laaraucana.servicios.validaNumeroSerie.ValidaNumeroSerieServiceLocator()).getValidaNumeroSeriePort();
      if (validaNumeroSerieImpl != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)validaNumeroSerieImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)validaNumeroSerieImpl)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (validaNumeroSerieImpl != null)
      ((javax.xml.rpc.Stub)validaNumeroSerieImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cl.laaraucana.servicios.validaNumeroSerie.ValidaNumeroSerieImpl getValidaNumeroSerieImpl() {
    if (validaNumeroSerieImpl == null)
      _initValidaNumeroSerieImplProxy();
    return validaNumeroSerieImpl;
  }
  
  public cl.laaraucana.servicios.validaNumeroSerie.ResponseWS validaNumeroSerie(cl.laaraucana.servicios.validaNumeroSerie.CredencialesWS credenciales, cl.laaraucana.servicios.validaNumeroSerie.DatosCedula cedula) throws java.rmi.RemoteException, cl.laaraucana.servicios.validaNumeroSerie.SOAPException{
    if (validaNumeroSerieImpl == null)
      _initValidaNumeroSerieImplProxy();
    return validaNumeroSerieImpl.validaNumeroSerie(credenciales, cedula);
  }
  
  
}