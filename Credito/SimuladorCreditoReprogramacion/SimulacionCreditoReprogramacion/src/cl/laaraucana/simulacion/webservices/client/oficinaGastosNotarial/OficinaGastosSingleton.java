package cl.laaraucana.simulacion.webservices.client.oficinaGastosNotarial;

import cl.laaraucana.simulacion.utils.SimuladorServicesUtil;
import cl.laaraucana.simulacion.webservices.client.oficinaGastosNotarial.VO.OficinaGastosNotarialSalidaLista;

public class OficinaGastosSingleton {
	
	private static OficinaGastosSingleton instance = null;
	OficinaGastosNotarialSalidaLista listaOficinasGastos;
	
	
	protected OficinaGastosSingleton() {
		
	      // Llamada al servicio
		try {
			this.listaOficinasGastos = SimuladorServicesUtil.getOficinasGastos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static OficinaGastosSingleton getInstance() {
	      if(instance == null) {
	         instance = new OficinaGastosSingleton();
	      }
	      return instance;
	   }


	public OficinaGastosNotarialSalidaLista getListaOficinasGastos() {
		try {
			if(listaOficinasGastos==null || listaOficinasGastos.getOficinasGastosList().size()==0){
				this.listaOficinasGastos = SimuladorServicesUtil.getOficinasGastos();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.listaOficinasGastos;
	}
	
	

}
