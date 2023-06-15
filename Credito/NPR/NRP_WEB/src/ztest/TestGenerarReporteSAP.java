package ztest;

import cl.lib.export.txt.impl.GenerarTXT;

public class TestGenerarReporteSAP {
	public static void main(String[] args) {
		
		String ruta = new GenerarTXT().generar("envio_nrp_sap", "condicion:","csv",";", null);
		
		System.out.println(ruta);
	}
}
