package cl.paperless.siagf.ws;

public class ExtincionReconocimientoPortTypeProxy implements cl.paperless.siagf.ws.ExtincionReconocimientoPortType {
	private String _endpoint = null;
	private cl.paperless.siagf.ws.ExtincionReconocimientoPortType extincionReconocimientoPortType = null;

	public ExtincionReconocimientoPortTypeProxy() {
		_initExtincionReconocimientoPortTypeProxy();
	}

	private void _initExtincionReconocimientoPortTypeProxy() {
		try {
			extincionReconocimientoPortType = (new cl.paperless.siagf.ws.ExtincionReconocimientoLocator()).getExtincionReconocimientoSOAP11port_http();
			if (extincionReconocimientoPortType != null) {
				if (_endpoint != null)
					((javax.xml.rpc.Stub) extincionReconocimientoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
				else
					_endpoint = (String) ((javax.xml.rpc.Stub) extincionReconocimientoPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
			}

		} catch (javax.xml.rpc.ServiceException serviceException) {
		}
	}

	public String getEndpoint() {
		return _endpoint;
	}

	public void setEndpoint(String endpoint) {
		_endpoint = endpoint;
		if (extincionReconocimientoPortType != null)
			((javax.xml.rpc.Stub) extincionReconocimientoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

	}

	public cl.paperless.siagf.ws.ExtincionReconocimientoPortType getExtincionReconocimientoPortType() {
		if (extincionReconocimientoPortType == null)
			_initExtincionReconocimientoPortTypeProxy();
		return extincionReconocimientoPortType;
	}

	public java.lang.String extincionReconocimiento(java.lang.String token, java.lang.String xmlDetalle) throws java.rmi.RemoteException {
		if (extincionReconocimientoPortType == null)
			_initExtincionReconocimientoPortTypeProxy();
		return extincionReconocimientoPortType.extincionReconocimiento(token, xmlDetalle);
	}

}