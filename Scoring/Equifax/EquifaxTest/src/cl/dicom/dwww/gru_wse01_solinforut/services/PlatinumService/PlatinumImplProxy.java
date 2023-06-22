package cl.dicom.dwww.gru_wse01_solinforut.services.PlatinumService;

public class PlatinumImplProxy implements cl.dicom.dwww.gru_wse01_solinforut.services.PlatinumService.PlatinumImpl {
  private String _endpoint = null;
  private cl.dicom.dwww.gru_wse01_solinforut.services.PlatinumService.PlatinumImpl platinumImpl = null;
  
  public PlatinumImplProxy() {
    _initPlatinumImplProxy();
  }
  
  public PlatinumImplProxy(String endpoint) {
    _endpoint = endpoint;
    _initPlatinumImplProxy();
  }
  
  private void _initPlatinumImplProxy() {
    try {
      platinumImpl = (new cl.dicom.dwww.gru_wse01_solinforut.services.PlatinumService.PlatinumImplServiceLocator()).getPlatinumService();
      if (platinumImpl != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)platinumImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)platinumImpl)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (platinumImpl != null)
      ((javax.xml.rpc.Stub)platinumImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public cl.dicom.dwww.gru_wse01_solinforut.services.PlatinumService.PlatinumImpl getPlatinumImpl() {
    if (platinumImpl == null)
      _initPlatinumImplProxy();
    return platinumImpl;
  }
  
  public cl.equifax.wse.gru01.platinum.PLATINUMOutput getInforme(java.lang.String RUT, java.lang.String numeroSerie, java.lang.String ICom, java.lang.String usoInternoDicom01, java.lang.String usoInternoDicom02, java.lang.String usoInternoDicom03, java.lang.String usuario, java.lang.String password) throws java.rmi.RemoteException{
    if (platinumImpl == null)
      _initPlatinumImplProxy();
    return platinumImpl.getInforme(RUT, numeroSerie, ICom, usoInternoDicom01, usoInternoDicom02, usoInternoDicom03, usuario, password);
  }
  
  
}