package WSFonaCajasNM;

public class WSFonaCajasSoapProxy implements WSFonaCajasNM.WSFonaCajasSoap {
  private String _endpoint = null;
  private WSFonaCajasNM.WSFonaCajasSoap wSFonaCajasSoap = null;
  
  public WSFonaCajasSoapProxy() {
    _initWSFonaCajasSoapProxy();
  }
  
  public WSFonaCajasSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSFonaCajasSoapProxy();
  }
  
  private void _initWSFonaCajasSoapProxy() {
    try {
      wSFonaCajasSoap = (new WSFonaCajasNM.WSFonaCajasLocator()).getWSFonaCajasSoap();
      if (wSFonaCajasSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSFonaCajasSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSFonaCajasSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSFonaCajasSoap != null)
      ((javax.xml.rpc.Stub)wSFonaCajasSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public WSFonaCajasNM.WSFonaCajasSoap getWSFonaCajasSoap() {
    if (wSFonaCajasSoap == null)
      _initWSFonaCajasSoapProxy();
    return wSFonaCajasSoap;
  }
  
  public WSFonaCajasNM.RespActEstLicCCAF actEstLicCCAF(java.lang.String codigoUsuario, java.lang.String claveUsuario, short codigoAsegurador, java.lang.String codigoOperador, WSFonaCajasNM.EntrActEstLicCCAF[] listaEstLCC) throws java.rmi.RemoteException{
    if (wSFonaCajasSoap == null)
      _initWSFonaCajasSoapProxy();
    return wSFonaCajasSoap.actEstLicCCAF(codigoUsuario, claveUsuario, codigoAsegurador, codigoOperador, listaEstLCC);
  }
  
  public WSFonaCajasNM.RespInfEstLicCCAF infEstLicCCAF(java.lang.String codigoUsuario, java.lang.String claveUsuario, short codigoAsegurador, java.lang.String codigoOperador, WSFonaCajasNM.EntrInfEstLicCCAF[] listaEstLCC) throws java.rmi.RemoteException{
    if (wSFonaCajasSoap == null)
      _initWSFonaCajasSoapProxy();
    return wSFonaCajasSoap.infEstLicCCAF(codigoUsuario, claveUsuario, codigoAsegurador, codigoOperador, listaEstLCC);
  }
  
  public WSFonaCajasNM.RespLicCertifTrab licCertifTrab(java.lang.String rutBeneficiario, java.lang.String fecCertifica, java.lang.String rutInstitucion, java.lang.String codigoUsuario, java.lang.String claveUsuario) throws java.rmi.RemoteException{
    if (wSFonaCajasSoap == null)
      _initWSFonaCajasSoapProxy();
    return wSFonaCajasSoap.licCertifTrab(rutBeneficiario, fecCertifica, rutInstitucion, codigoUsuario, claveUsuario);
  }
  
  public WSFonaCajasNM.RespVerEstLicCCAF verEstLicCCAF(java.lang.String codigoUsuario, java.lang.String claveUsuario, short codigoAsegurador, java.lang.String codigoOperador, short tipFormulario, int numFormulario) throws java.rmi.RemoteException{
    if (wSFonaCajasSoap == null)
      _initWSFonaCajasSoapProxy();
    return wSFonaCajasSoap.verEstLicCCAF(codigoUsuario, claveUsuario, codigoAsegurador, codigoOperador, tipFormulario, numFormulario);
  }
  
  public WSFonaCajasNM.RespConFormLCC conFormuLCC(java.lang.String codigoUsuario, java.lang.String claveUsuario, short codigoAsegurador, java.lang.String codigoOperador, short tipFormulario, int numFormulario) throws java.rmi.RemoteException{
    if (wSFonaCajasSoap == null)
      _initWSFonaCajasSoapProxy();
    return wSFonaCajasSoap.conFormuLCC(codigoUsuario, claveUsuario, codigoAsegurador, codigoOperador, tipFormulario, numFormulario);
  }
  
  
}