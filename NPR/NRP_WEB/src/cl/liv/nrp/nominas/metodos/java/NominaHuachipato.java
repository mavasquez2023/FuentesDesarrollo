package cl.liv.nrp.nominas.metodos.java;

import java.util.HashMap;
import java.util.ResourceBundle;

import cl.liv.comun.utiles.UtilesComunes;

public class NominaHuachipato {

	static String TABLA_PRINCIPAL = ResourceBundle.getBundle("etc/metodos_java").getString("configs.metodos.java.tabla.principal");
	
	public static String[] obtenerInfoNominarRolA(HashMap pars){
		String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
		
		String[] querys = new String[3];
		querys[0]="";
		querys[1]="		select "+
			" A.FICHA, "+
			" 'X' PERIODO, "+
			" '0015' INFOTIPO, "+
			" '7648' CODDES, "+
			" '7648' CCNOMINA, "+
			" IMPORTE_CUOTA_MONEDA_LOCAL, IMPORTE_CUOTA_MONEDA_LOCAL MONTO, "+
			" 'CLP' MONEDA, "+
			" '0' CANTIDAD, "+
			" FECHA_VCTO_NOMINA FECPRO, "+
			" SUBSTR(PERIODO,5,2) MES, "+
			" SUBSTR(PERIODO,0,5) ANODES, TIPO_NOMINA "+
			" from CRDTA.EMPFICHAA A,  "+TABLA_PRINCIPAL+"  B where A.EMPRUT = B.RUT_EMPRESA AND A.EMPRUT = 94637000 AND A.ROL = 'A' AND PERIODO = '"+periodo+"' AND A.AFIRUT = B.RUT_PAGADOR  order by ficha asc" ;
		querys[2]="";
		
		return querys;
	}
	
	public static String[] obtenerInfoNominarRolC(HashMap pars){
		String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
		
		String[] querys = new String[3];
		querys[0]="";
		querys[1]="		select "+
			" A.FICHA, "+
			" 'X' PERIODO, "+
			" '0015' INFOTIPO, "+
			" '7648' CODDES, "+
			" '7648' CCNOMINA, "+
			" IMPORTE_CUOTA_MONEDA_LOCAL, IMPORTE_CUOTA_MONEDA_LOCAL MONTO, "+
			" 'CLP' MONEDA, "+
			" '0' CANTIDAD, "+
			" FECHA_VCTO_NOMINA FECPRO, "+
			" SUBSTR(PERIODO,5,2) MES, "+
			" SUBSTR(PERIODO,0,5) ANODES ,TIPO_NOMINA"+
			" from CRDTA.EMPFICHAA A,  "+TABLA_PRINCIPAL+"  B where A.EMPRUT = B.RUT_EMPRESA AND A.EMPRUT = 94637000 AND A.ROL = 'C' AND PERIODO = '"+periodo+"' AND A.AFIRUT = B.RUT_PAGADOR order by ficha asc" ;
		querys[2]="";
		
		return querys;
	}
}
