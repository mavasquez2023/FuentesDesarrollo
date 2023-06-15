package cl.paperless.siagf.ws;

public class ActualizarCausantePortTypeProxy implements cl.paperless.siagf.ws.ActualizarCausantePortType {
	private String _endpoint = null;
	private cl.paperless.siagf.ws.ActualizarCausantePortType actualizarCausantePortType = null;

	public ActualizarCausantePortTypeProxy() {
		_initActualizarCausantePortTypeProxy();
	}

	public ActualizarCausantePortTypeProxy(String endpoint) {
		_endpoint = endpoint;
		_initActualizarCausantePortTypeProxy();
	}

	private void _initActualizarCausantePortTypeProxy() {
		try {
			actualizarCausantePortType = (new cl.paperless.siagf.ws.ActualizarCausanteLocator()).getActualizarCausanteSOAP11port_http();
			if (actualizarCausantePortType != null) {
				if (_endpoint != null)
					((javax.xml.rpc.Stub) actualizarCausantePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
				else
					_endpoint = (String) ((javax.xml.rpc.Stub) actualizarCausantePortType)._getProperty("javax.xml.rpc.service.endpoint.address");
			}

		} catch (javax.xml.rpc.ServiceException serviceException) {
		}
	}

	public String getEndpoint() {
		return _endpoint;
	}

	public void setEndpoint(String endpoint) {
		_endpoint = endpoint;
		if (actualizarCausantePortType != null)
			((javax.xml.rpc.Stub) actualizarCausantePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

	}

	public cl.paperless.siagf.ws.ActualizarCausantePortType getActualizarCausantePortType() {
		if (actualizarCausantePortType == null)
			_initActualizarCausantePortTypeProxy();
		return actualizarCausantePortType;
	}

	public java.lang.String actualizarCausante(java.lang.String token, java.lang.String xmlDetalle) throws java.rmi.RemoteException {
		if (actualizarCausantePortType == null)
			_initActualizarCausantePortTypeProxy();
		return actualizarCausantePortType.actualizarCausante(token, xmlDetalle);
	}

}