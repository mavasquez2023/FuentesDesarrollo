package cl.sinacofi.WebServices;

public class SNPV1801SoapProxy implements cl.sinacofi.WebServices.SNPV1801Soap {
  private String _endpoint = null;
  private cl.sinacofi.WebServices.SNPV1801Soap sNPV1801Soap = null;
  
  public SNPV1801SoapProxy() {
    _initSNPV1801SoapProxy();
  }
  
  public SNPV1801SoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initSNPV1801SoapProxy();
  }
  
  private void _initSNPV1801SoapProxy() {
    try {
      sNPV1801Soap = (new cl.sinacofi.WebServices.SNPV1801Locator()).getSNPV1801Soap();
      if (sNPV1801Soap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sNPV1801Soap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sNPV1801Soap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sNPV1801Soap != null)
      ((javax.xml.rpc.Stub)sNPV1801Soap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cl.sinacofi.WebServices.SNPV1801Soap getSNPV1801Soap() {
    if (sNPV1801Soap == null)
      _initSNPV1801SoapProxy();
    return sNPV1801Soap;
  }
  
  public cl.sinacofi.WebServices.RespuestaSNPV1801 consulta(java.lang.String usuario, java.lang.String claveUsuario, java.lang.String rutCliente, java.lang.String canalInstitucion, java.lang.String idChallenge, cl.sinacofi.wsdl.SDN122REQ.RESPUESTAS[] desafio) throws java.rmi.RemoteException{
    if (sNPV1801Soap == null)
      _initSNPV1801SoapProxy();
    return sNPV1801Soap.consulta(usuario, claveUsuario, rutCliente, canalInstitucion, idChallenge, desafio);
  }
  
  
}