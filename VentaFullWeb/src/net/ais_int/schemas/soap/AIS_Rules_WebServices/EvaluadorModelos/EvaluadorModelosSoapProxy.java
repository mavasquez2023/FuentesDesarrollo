package net.ais_int.schemas.soap.AIS_Rules_WebServices.EvaluadorModelos;

public class EvaluadorModelosSoapProxy implements net.ais_int.schemas.soap.AIS_Rules_WebServices.EvaluadorModelos.EvaluadorModelosSoap {
  private String _endpoint = null;
  private net.ais_int.schemas.soap.AIS_Rules_WebServices.EvaluadorModelos.EvaluadorModelosSoap evaluadorModelosSoap = null;
  
  public EvaluadorModelosSoapProxy() {
    _initEvaluadorModelosSoapProxy();
  }
  
  public EvaluadorModelosSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initEvaluadorModelosSoapProxy();
  }
  
  private void _initEvaluadorModelosSoapProxy() {
    try {
      evaluadorModelosSoap = (new net.ais_int.schemas.soap.AIS_Rules_WebServices.EvaluadorModelos.EvaluadorModelosLocator()).getEvaluadorModelosSoap();
      if (evaluadorModelosSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)evaluadorModelosSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)evaluadorModelosSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (evaluadorModelosSoap != null)
      ((javax.xml.rpc.Stub)evaluadorModelosSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public net.ais_int.schemas.soap.AIS_Rules_WebServices.EvaluadorModelos.EvaluadorModelosSoap getEvaluadorModelosSoap() {
    if (evaluadorModelosSoap == null)
      _initEvaluadorModelosSoapProxy();
    return evaluadorModelosSoap;
  }
  
  public java.lang.String evaluarMotorGMR(int idModelo, java.lang.String contenidoPeticion) throws java.rmi.RemoteException{
    if (evaluadorModelosSoap == null)
      _initEvaluadorModelosSoapProxy();
    return evaluadorModelosSoap.evaluarMotorGMR(idModelo, contenidoPeticion);
  }
  
  
}