package cl.paperless.siagf.ws;

public class IngresoReconocimientoPortTypeProxy implements cl.paperless.siagf.ws.IngresoReconocimientoPortType {
	private String _endpoint = null;
	private cl.paperless.siagf.ws.IngresoReconocimientoPortType ingresoReconocimientoPortType = null;

	public IngresoReconocimientoPortTypeProxy() {
		_initIngresoReconocimientoPortTypeProxy();
	}

	private void _initIngresoReconocimientoPortTypeProxy() {
		try {
			ingresoReconocimientoPortType = (new cl.paperless.siagf.ws.IngresoReconocimientoLocator()).getIngresoReconocimientoSOAP11port_http();
			if (ingresoReconocimientoPortType != null) {
				if (_endpoint != null)
					((javax.xml.rpc.Stub) ingresoReconocimientoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
				else
					_endpoint = (String) ((javax.xml.rpc.Stub) ingresoReconocimientoPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
			}

		} catch (javax.xml.rpc.ServiceException serviceException) {
		}
	}

	public String getEndpoint() {
		return _endpoint;
	}

	public void setEndpoint(String endpoint) {
		_endpoint = endpoint;
		if (ingresoReconocimientoPortType != null)
			((javax.xml.rpc.Stub) ingresoReconocimientoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

	}

	public cl.paperless.siagf.ws.IngresoReconocimientoPortType getIngresoReconocimientoPortType() {
		if (ingresoReconocimientoPortType == null)
			_initIngresoReconocimientoPortTypeProxy();
		return ingresoReconocimientoPortType;
	}

	public java.lang.String ingresoReconocimiento(java.lang.String token, java.lang.String xmlDetalle) throws java.rmi.RemoteException {
		if (ingresoReconocimientoPortType == null)
			_initIngresoReconocimientoPortTypeProxy();
		return ingresoReconocimientoPortType.ingresoReconocimiento(token, xmlDetalle);
	}

}