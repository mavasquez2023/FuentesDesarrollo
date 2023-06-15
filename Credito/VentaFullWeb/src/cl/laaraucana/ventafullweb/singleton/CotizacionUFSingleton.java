package cl.laaraucana.ventafullweb.singleton;

import cl.laaraucana.ventafullweb.ws.ClienteCotizacion;
import cl.laaraucana.ventafullweb.ws.ClienteCotizacionImpl;

public class CotizacionUFSingleton {
	
	private static CotizacionUFSingleton instance = new CotizacionUFSingleton();
	
	private int cotizacionUf;
	
	public static CotizacionUFSingleton getInstance() {
		return instance;
	}
	
	private CotizacionUFSingleton () {
		try {
			ClienteCotizacion clienteCotizacion = new ClienteCotizacionImpl(); 
			cotizacionUf = clienteCotizacion.getRespuestaCotizacion();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getCotizacionUf() {
		return cotizacionUf;
	}
	
	public void setCotizacionUf(int cotizacionUf) {
		this.cotizacionUf = cotizacionUf;
	}
	

}
