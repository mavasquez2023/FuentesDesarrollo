package ztest;

import cl.lib.export.txt.impl.GenerarTXT;

public class TestReporte {

	public static void main(String[] args) {
		
		new GenerarTXT().generar("envio_nrp_sap", "", "txt", ";", null);
		
	}
}
