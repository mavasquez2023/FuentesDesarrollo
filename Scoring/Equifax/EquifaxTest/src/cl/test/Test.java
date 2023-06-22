package cl.test;

import java.io.IOException;
import cl.dicom.dwww.gru_wse01_solinforut.services.PlatinumService.PlatinumImplProxy;
import cl.equifax.wse.gru01.platinum.PLATINUMOutput;

public class Test {

	public static void main(String[] args) throws IOException {
		
		PlatinumImplProxy myPlatinumImplProxy = new PlatinumImplProxy();
		myPlatinumImplProxy.setEndpoint("https://www.dicom.cl/gru.wse01.solinforut/services/PlatinumService");
		
		PLATINUMOutput salida = myPlatinumImplProxy.getInforme("0104239609", "", "N", "A", "0", "0", "LA ARAUCANA-MATRIZ", "ARAUCA");
		
		System.out.println(salida.getIdentificacionPersona().getNombre());
		System.out.println(salida.getIdentificacionPersona().getNacionalidad());
		
	}
}
