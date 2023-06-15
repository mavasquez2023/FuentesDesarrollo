package cl.paperless.siagf.ws;

public class AutenticacionPortTypeProxy implements cl.paperless.siagf.ws.AutenticacionPortType {
	private String _endpoint = null;
	private cl.paperless.siagf.ws.AutenticacionPortType autenticacionPortType = null;

	public AutenticacionPortTypeProxy() {
		_initAutenticacionPortTypeProxy();
	}

	private void _initAutenticacionPortTypeProxy() {
		try {
			autenticacionPortType = (new cl.paperless.siagf.ws.AutenticacionLocator()).getAutenticacionSOAP11port_http();
			if (autenticacionPortType != null) {
				if (_endpoint != null)
					((javax.xml.rpc.Stub) autenticacionPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
				else
					_endpoint = (String) ((javax.xml.rpc.Stub) autenticacionPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
			}

		} catch (javax.xml.rpc.ServiceException serviceException) {
		}
	}

	public String getEndpoint() {
		return _endpoint;
	}

	public void setEndpoint(String endpoint) {
		_endpoint = endpoint;
		if (autenticacionPortType != null)
			((javax.xml.rpc.Stub) autenticacionPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

	}

	public cl.paperless.siagf.ws.AutenticacionPortType getAutenticacionPortType() {
		if (autenticacionPortType == null)
			_initAutenticacionPortTypeProxy();
		return autenticacionPortType;
	}

	public java.lang.String login(java.lang.Integer codigoEntidad, java.lang.String loginUsuario, java.lang.String claveUsuario) throws java.rmi.RemoteException {
		if (autenticacionPortType == null)
			_initAutenticacionPortTypeProxy();
		return autenticacionPortType.login(codigoEntidad, loginUsuario, claveUsuario);
	}

}