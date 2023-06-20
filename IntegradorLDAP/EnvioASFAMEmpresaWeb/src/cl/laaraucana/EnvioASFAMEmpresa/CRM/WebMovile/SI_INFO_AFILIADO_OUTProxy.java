package cl.laaraucana.EnvioASFAMEmpresa.CRM.WebMovile;

public class SI_INFO_AFILIADO_OUTProxy implements cl.laaraucana.EnvioASFAMEmpresa.CRM.WebMovile.SI_INFO_AFILIADO_OUT {
  private String _endpoint = null;
  private cl.laaraucana.EnvioASFAMEmpresa.CRM.WebMovile.SI_INFO_AFILIADO_OUT sI_INFO_AFILIADO_OUT = null;
  
  public SI_INFO_AFILIADO_OUTProxy() {
    _initSI_INFO_AFILIADO_OUTProxy();
  }
  
  public SI_INFO_AFILIADO_OUTProxy(String endpoint) {
    _endpoint = endpoint;
    _initSI_INFO_AFILIADO_OUTProxy();
  }
  
  private void _initSI_INFO_AFILIADO_OUTProxy() {
    try {
      sI_INFO_AFILIADO_OUT = (new cl.laaraucana.EnvioASFAMEmpresa.CRM.WebMovile.SI_INFO_AFILIADO_OUTServiceLocator()).getHTTPS_Port();
      if (sI_INFO_AFILIADO_OUT != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sI_INFO_AFILIADO_OUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sI_INFO_AFILIADO_OUT)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sI_INFO_AFILIADO_OUT != null)
      ((javax.xml.rpc.Stub)sI_INFO_AFILIADO_OUT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cl.laaraucana.EnvioASFAMEmpresa.CRM.WebMovile.SI_INFO_AFILIADO_OUT getSI_INFO_AFILIADO_OUT() {
    if (sI_INFO_AFILIADO_OUT == null)
      _initSI_INFO_AFILIADO_OUTProxy();
    return sI_INFO_AFILIADO_OUT;
  }
  
  public cl.laaraucana.EnvioASFAMEmpresa.CRM.WebMovile.DT_INFO_AFILIADO_RES SI_INFO_AFILIADO_OUT(cl.laaraucana.EnvioASFAMEmpresa.CRM.WebMovile.DT_INFO_AFILIADO_REQ MT_INFO_AFILIADO_REQ) throws java.rmi.RemoteException{
    if (sI_INFO_AFILIADO_OUT == null)
      _initSI_INFO_AFILIADO_OUTProxy();
    return sI_INFO_AFILIADO_OUT.SI_INFO_AFILIADO_OUT(MT_INFO_AFILIADO_REQ);
  }
  
  
}