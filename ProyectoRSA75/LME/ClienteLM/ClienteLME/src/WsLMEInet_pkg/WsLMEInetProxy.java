package WsLMEInet_pkg;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;
import javax.xml.rpc.Stub;

public class WsLMEInetProxy
implements WsLMEInet_PortType
{

public WsLMEInetProxy()
{
    _endpoint = null;
    wsLMEInet_PortType = null;
    _initWsLMEInetProxy();
}

public WsLMEInetProxy(String endpoint)
{
    _endpoint = null;
    wsLMEInet_PortType = null;
    _endpoint = endpoint;
    _initWsLMEInetProxy();
}

private void _initWsLMEInetProxy()
{
    try
    {
        wsLMEInet_PortType = (new WsLMEInet_ServiceLocator()).getWsLMEInetSOAP();
        if(wsLMEInet_PortType != null)
            if(_endpoint != null)
                ((Stub)wsLMEInet_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
            else
                _endpoint = (String)((Stub)wsLMEInet_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
    }
    catch(ServiceException serviceexception) { }
}

public String getEndpoint()
{
    return _endpoint;
}

public void setEndpoint(String endpoint)
{
    _endpoint = endpoint;
    if(wsLMEInet_PortType != null)
        ((Stub)wsLMEInet_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
}

public WsLMEInet_PortType getWsLMEInet_PortType()
{
    if(wsLMEInet_PortType == null)
        _initWsLMEInetProxy();
    return wsLMEInet_PortType;
}

public LMEEvenLccResponse LMEEvenLcc(LMEEvenLcc LMEEvenLcc)
    throws RemoteException
{
    if(wsLMEInet_PortType == null)
        _initWsLMEInetProxy();
    return wsLMEInet_PortType.LMEEvenLcc(LMEEvenLcc);
}

public LMEDetLccResponse LMEDetLcc(LMEDetLcc LMEDetLcc)
    throws RemoteException
{
    if(wsLMEInet_PortType == null)
        _initWsLMEInetProxy();
    return wsLMEInet_PortType.LMEDetLcc(LMEDetLcc);
}

public LMEInfResolResponse LMEInfResol(LMEInfResol LMEInfResolRequest)
    throws RemoteException
{
    if(wsLMEInet_PortType == null)
        _initWsLMEInetProxy();
    return wsLMEInet_PortType.LMEInfResol(LMEInfResolRequest);
}

public LMEDevEmpResponse LMEDevEmp(LMEDevEmp LMEDevEmp)
    throws RemoteException
{
    if(wsLMEInet_PortType == null)
        _initWsLMEInetProxy();
    return wsLMEInet_PortType.LMEDevEmp(LMEDevEmp);
}

public LMEInfValCCAFResponse LMEInfValCCAF(LMEInfValCCAF LMEInfValCCAF)
    throws RemoteException
{
    if(wsLMEInet_PortType == null)
        _initWsLMEInetProxy();
    return wsLMEInet_PortType.LMEInfValCCAF(LMEInfValCCAF);
}

public LMEInfLiquidResponse LMEInfLiquid(LMEInfLiquid LMEInfLiquid)
    throws RemoteException
{
    if(wsLMEInet_PortType == null)
        _initWsLMEInetProxy();
    return wsLMEInet_PortType.LMEInfLiquid(LMEInfLiquid);
}

public LMEInfSeccCResponse LMEInfSeccC(LMEInfSeccC LMEInfSeccC)
    throws RemoteException
{
    if(wsLMEInet_PortType == null)
        _initWsLMEInetProxy();
    return wsLMEInet_PortType.LMEInfSeccC(LMEInfSeccC);
}

public LMEVerTramEmpResponse LMEVerTramEmp(LMEVerTramEmp LMEVerTramEmp)
    throws RemoteException
{
    if(wsLMEInet_PortType == null)
        _initWsLMEInetProxy();
    return wsLMEInet_PortType.LMEVerTramEmp(LMEVerTramEmp);
}

public LMEInfTramEmpResponse LMEInfTramEmp(LMEInfTramEmp LMEInfTramEmp)
    throws RemoteException
{
    if(wsLMEInet_PortType == null)
        _initWsLMEInetProxy();
    return wsLMEInet_PortType.LMEInfTramEmp(LMEInfTramEmp);
}

public LMEVerLccTrabResponse LMEVerLccTrab(LMEVerLccTrab LMEVerLccTrab)
    throws RemoteException
{
    if(wsLMEInet_PortType == null)
        _initWsLMEInetProxy();
    return wsLMEInet_PortType.LMEVerLccTrab(LMEVerLccTrab);
}

private String _endpoint;
private WsLMEInet_PortType wsLMEInet_PortType;
public LMEEvenFecResponse LMEEvenFec(WsLMEInet_pkg.LMEEvenFec LMEEvenFec) throws RemoteException {
	// TODO Apéndice de método generado automáticamente
	return null;
}
}
