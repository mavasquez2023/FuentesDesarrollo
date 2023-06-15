package ztest;

import cl.lib.export.txt.impl.GenerarTXT;
import cl.liv.comun.utiles.UtilesComunes;

public class TestGeneracionSAP {

	public static void main(String[] args) {

		UtilesComunes.variablesEstaticas.put("sys.YearMonth", "201811");
		String condicion = " where 1=1 fetch first 10 rows only ";
		
		String ruta = new GenerarTXT().generar("envio_nrp_sap", "condicion:"+ condicion,"csv",";", null);
		
		System.out.println("ruta: "+ ruta);
	}
}
