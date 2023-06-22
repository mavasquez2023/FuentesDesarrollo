

package cl.araucana.wssiagf.clients;


import java.io.IOException;

import cl.araucana.wssiagf.WebServiceResponse;


public abstract class WebServicesInterfaceClient {

	protected String sysID;
	private int nextReqID = 1;

	public WebServicesInterfaceClient(String sysID) {
		this.sysID = sysID;
	}

	public String getSysID() {
		return sysID;
	}

	protected int newReqID() {
		return nextReqID++;
 	}

	public abstract WebServiceResponse getVersion() throws IOException;

	public abstract WebServiceResponse ingresoReconocimiento(String parameter)
			throws IOException;

	public abstract WebServiceResponse consultaCausante(String rutCausante)
			throws IOException;

	public abstract WebServiceResponse extincionReconocimiento(
			String parameter) throws IOException;

	public abstract WebServiceResponse actualizarCausante(String parameter)
			throws IOException;
}
