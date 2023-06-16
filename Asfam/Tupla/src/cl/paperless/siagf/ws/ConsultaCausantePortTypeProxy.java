package cl.paperless.siagf.ws;

public class ConsultaCausantePortTypeProxy implements cl.paperless.siagf.ws.ConsultaCausantePortType {
	private String _endpoint = null;
	private cl.paperless.siagf.ws.ConsultaCausantePortType consultaCausantePortType = null;

	public ConsultaCausantePortTypeProxy() {
		_initConsultaCausantePortTypeProxy();
	}

	private void _initConsultaCausantePortTypeProxy() {
		try {
			consultaCausantePortType = (new cl.paperless.siagf.ws.ConsultaCausanteLocator()).getConsultaCausanteSOAP11port_http();
			if (consultaCausantePortType != null) {
				if (_endpoint != null)
					((javax.xml.rpc.Stub) consultaCausantePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
				else
					_endpoint = (String) ((javax.xml.rpc.Stub) consultaCausantePortType)._getProperty("javax.xml.rpc.service.endpoint.address");
			}

		} catch (javax.xml.rpc.ServiceException serviceException) {
		}
	}

	public String getEndpoint() {
		return _endpoint;
	}

	public void setEndpoint(String endpoint) {
		_endpoint = endpoint;
		if (consultaCausantePortType != null)
			((javax.xml.rpc.Stub) consultaCausantePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

	}

	public cl.paperless.siagf.ws.ConsultaCausantePortType getConsultaCausantePortType() {
		if (consultaCausantePortType == null)
			_initConsultaCausantePortTypeProxy();
		return consultaCausantePortType;
	}

	public java.lang.String consultaCausante(java.lang.String token, java.lang.String rutCausante) throws java.rmi.RemoteException {
		if (consultaCausantePortType == null)
			_initConsultaCausantePortTypeProxy();
		return consultaCausantePortType.consultaCausante(token, rutCausante);
	}

}