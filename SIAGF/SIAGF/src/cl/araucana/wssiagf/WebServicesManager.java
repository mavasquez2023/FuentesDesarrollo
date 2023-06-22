

package cl.araucana.wssiagf;


import java.util.Date;
import java.util.HashMap;

import cl.araucana.core.util.AbsoluteDateTime;
import cl.araucana.core.util.CallProfiler;


public class WebServicesManager {

	private static WebServicesManager instance;

	private WebServicesAdapter wsAdapter;

	public static synchronized void setInstance(Credential credential, HashMap serviceURLs,
			long timeout, int nRetries) throws WSSIAGFException {

		if (instance != null) {
			return;
		}

		instance = new WebServicesManager(
				credential, serviceURLs, timeout, nRetries);
	}

	public static WebServicesManager getInstance() {
		return instance;
	}

	private WebServicesManager(Credential credential,
			HashMap serviceURLs, long timeout, int nRetries)
			throws WSSIAGFException {

		wsAdapter =
				new WebServicesAdapter(
						credential, serviceURLs, timeout, nRetries);
	}

	public CallProfiler getVersion() throws WSSIAGFException {

		CallProfiler profiler = new CallProfiler("getVersion");

		profiler.start();

		WebServiceResponse response = wsAdapter.getVersion();

		profiler.stop();
		profiler.setReturnedObject(response);

		return profiler;
	}

	public CallProfiler ingresoReconocimiento(String xmlDetalle)
			throws WSSIAGFException {

		CallProfiler profiler = new CallProfiler("ingresoReconocimiento");

		profiler.start();

		WebServiceResponse response =
				wsAdapter.ingresoReconocimiento(xmlDetalle);

		profiler.stop();
		profiler.setReturnedObject(response);

		return profiler;
	}

	public CallProfiler extincionReconocimiento(String xmlDetalle)
			throws WSSIAGFException {

		CallProfiler profiler = new CallProfiler("extincionReconocimiento");

		profiler.start();

		WebServiceResponse response =
				wsAdapter.extincionReconocimiento(xmlDetalle);

		profiler.stop();
		profiler.setReturnedObject(response);

		return profiler;
	}

	public CallProfiler consultaCausante(String rutCausante)
			throws WSSIAGFException {

		CallProfiler profiler = new CallProfiler("consultaCausante");

		profiler.start();

		WebServiceResponse response =
				wsAdapter.consultaCausante(rutCausante);

		profiler.stop();
		profiler.setReturnedObject(response);

		return profiler;
	}

	public CallProfiler actualizarCausante(String xmlDetalle)
			throws WSSIAGFException {

		CallProfiler profiler = new CallProfiler("actualizarCausante");

		profiler.start();

		WebServiceResponse response =
				wsAdapter.actualizarCausante(xmlDetalle);

		profiler.stop();
		profiler.setReturnedObject(response);

		return profiler;
	}

	private static void log(String message) {
		System.out.println(
				  "WebServicesManager: "
				+ new AbsoluteDateTime() + " "
				+ message);
	}
}
