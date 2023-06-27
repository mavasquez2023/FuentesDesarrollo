package cl.araucana.autoconsulta.ws;

public class CertificadoProxy implements cl.araucana.autoconsulta.ws.Certificado {
  private String _endpoint = null;
  private cl.araucana.autoconsulta.ws.Certificado certificado = null;
  
  public CertificadoProxy() {
    _initCertificadoProxy();
  }
  
  private void _initCertificadoProxy() {
    try {
      certificado = (new cl.araucana.autoconsulta.ws.CertificadoServiceLocator()).getCertificado();
      if (certificado != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)certificado)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)certificado)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (certificado != null)
      ((javax.xml.rpc.Stub)certificado)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cl.araucana.autoconsulta.ws.Certificado getCertificado() {
    if (certificado == null)
      _initCertificadoProxy();
    return certificado;
  }
  
  public cl.araucana.autoconsulta.ws.to.ResultadoTO obtenerDataCertificado(java.lang.String rut, java.lang.String dv) throws java.rmi.RemoteException{
    if (certificado == null)
      _initCertificadoProxy();
    return certificado.obtenerDataCertificado(rut, dv);
  }
  
  
}