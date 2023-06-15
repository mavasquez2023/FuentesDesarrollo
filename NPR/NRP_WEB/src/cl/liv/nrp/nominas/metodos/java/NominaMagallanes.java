package cl.liv.nrp.nominas.metodos.java;

import java.util.HashMap;
import java.util.ResourceBundle;

import cl.liv.comun.utiles.UtilesComunes;

public class NominaMagallanes {


	static String TABLA_PRINCIPAL = ResourceBundle.getBundle("etc/metodos_java").getString("configs.metodos.java.tabla.principal");
	
	public static String[] obtenerInfoNomina(HashMap pars){
		String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
		
		String[] querys = new String[3];
		querys[0]="	select "+ 
					" YEAR(CURRENT DATE) ANYO,"+
					" RIGHT(CONCAT('00', MONTH(CURRENT DATE)), 2) MES,"+
					" '121' CODSERV,"+
					" '61607900' RUTTERC,"+
					" 'K' RUTDVTERC,"+
					" '1' NUMENVIO,"+
					" RIGHT(CONCAT('0000000000', COUNT(1)), 10) CANTREGS,"+
					" CONCAT(RIGHT(CONCAT('000000000000', SUM(IMPORTE_CUOTA_MONEDA_LOCAL+IMPORTE_GRAVE_MONEDA_LOCAL)),12), '00') MONTODESC "+
					" from "+TABLA_PRINCIPAL+" N15  where   RUT_EMPRESA = 61607900 AND N15.PERIODO = '"+periodo+"' ";
		querys[1]="		select "+
				" N15.PERIODO, "+
				" '121' FIJO_1,  "+
				" N15.RUT_EMPRESA,  "+
				" N15.DV_EMPRESA,  "+
				"  (select max(N70.FICHA) from NRPDTA.NRP70F1 N70 where N15.RUT_PAGADOR = N70.RUTDEU ) FICHA, "+
				" N15.RUT_PAGADOR, "+
				" N15.DV_PAGADOR, "+
				" '0130100' FIJO_2, "+
				" INTEGER( NUMERO_CONTRATO ) FOLIO_CREDITO, "+
				" NRO_CUOTA, "+
				" 'P' FIJO_3, "+
				" IMPORTE_CUOTA_MONEDA_LOCAL, IMPORTE_CUOTA_MONEDA_LOCAL MONTO_CUOTA, "+
				" '00' FIJO_4, "+
				" SUBSTR(FECHA_COLOCACION,7,2) || SUBSTR(FECHA_COLOCACION,5,2) || SUBSTR(FECHA_COLOCACION,0,5)   FECHA_OTORGAMIENTO ,TIPO_NOMINA"+ 
				" from  "+TABLA_PRINCIPAL+"  N15 where   RUT_EMPRESA = 61607900 AND N15.PERIODO = '"+periodo+"' " ;
		querys[2]="";
		
		
		
		return querys;
	}
	
	
	
	public static String[] obtenerInfo0130(HashMap pars){
		String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
		
		String[] querys = new String[3];
		querys[0]="	select "+ 
					" YEAR(CURRENT DATE) ANYO,"+
					" RIGHT(CONCAT('00', MONTH(CURRENT DATE)), 2) MES,"+
					" '121' CODSERV,"+
					" '61607900' RUTTERC,"+
					" 'K' RUTDVTERC,"+
					" '1' NUMENVIO,"+
					" RIGHT(CONCAT('0000000000', COUNT(1)), 10) CANTREGS,"+
					" CONCAT(RIGHT(CONCAT('000000000000', SUM(IMPORTE_CUOTA_MONEDA_LOCAL+IMPORTE_GRAVE_MONEDA_LOCAL)),12), '00') MONTODESC "+
					" from "+TABLA_PRINCIPAL+" N15  where   RUT_EMPRESA = 61607900 AND N15.PERIODO = '"+periodo+"' AND FECHA_COLOCACION < '20180509'";
		querys[1]="		select "+
				" N15.PERIODO, "+
				" '121' FIJO_1,  "+
				" N15.RUT_EMPRESA,  "+
				" N15.DV_EMPRESA,  "+
				"  (select max(N70.FICHA) from NRPDTA.NRP70F1 N70 where N15.RUT_PAGADOR = N70.RUTDEU ) FICHA, "+
				" N15.RUT_PAGADOR, "+
				" N15.DV_PAGADOR, "+
				" '0130100' FIJO_2, "+
				" INTEGER( NUMERO_CONTRATO ) FOLIO_CREDITO, "+
				" NRO_CUOTA, "+
				" 'P' FIJO_3, "+
				" IMPORTE_CUOTA_MONEDA_LOCAL, IMPORTE_CUOTA_MONEDA_LOCAL MONTO_CUOTA, "+
				" '00' FIJO_4, "+
				" SUBSTR(FECHA_COLOCACION,7,2) || SUBSTR(FECHA_COLOCACION,5,2) || SUBSTR(FECHA_COLOCACION,0,5)   FECHA_OTORGAMIENTO ,TIPO_NOMINA"+ 
				" from  "+TABLA_PRINCIPAL+"  N15 where   RUT_EMPRESA = 61607900 AND N15.PERIODO = '"+periodo+"' AND FECHA_COLOCACION < '20180509'" ;
		querys[2]="";
		
		
		
		return querys;
	}
	
	
	public static String[] obtenerInfo0330(HashMap pars){
		String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
		
		String[] querys = new String[3];
		querys[0]="	select "+ 
					" YEAR(CURRENT DATE) ANYO,"+
					" RIGHT(CONCAT('00', MONTH(CURRENT DATE)), 2) MES,"+
					" '121' CODSERV,"+
					" '61607900' RUTTERC,"+
					" 'K' RUTDVTERC,"+
					" '1' NUMENVIO,"+
					" RIGHT(CONCAT('0000000000', COUNT(1)), 10) CANTREGS,"+
					" CONCAT(RIGHT(CONCAT('000000000000', SUM(IMPORTE_CUOTA_MONEDA_LOCAL+IMPORTE_GRAVE_MONEDA_LOCAL)),12), '00') MONTODESC "+
					" from "+TABLA_PRINCIPAL+" N15  where   RUT_EMPRESA = 61607900 AND N15.PERIODO = '"+periodo+"'  AND FECHA_COLOCACION > '20180508'";
		querys[1]="		select "+
				" N15.PERIODO, "+
				" '121' FIJO_1,  "+
				" N15.RUT_EMPRESA,  "+
				" N15.DV_EMPRESA,  "+
				"  (select max(N70.FICHA) from NRPDTA.NRP70F1 N70 where N15.RUT_PAGADOR = N70.RUTDEU ) FICHA, "+
				" N15.RUT_PAGADOR, "+
				" N15.DV_PAGADOR, "+
				" '0330100' FIJO_2, "+
				" INTEGER( NUMERO_CONTRATO ) FOLIO_CREDITO, "+
				" NRO_CUOTA, "+
				" 'P' FIJO_3, "+
				" IMPORTE_CUOTA_MONEDA_LOCAL, IMPORTE_CUOTA_MONEDA_LOCAL MONTO_CUOTA, "+
				" '00' FIJO_4, "+
				" SUBSTR(FECHA_COLOCACION,7,2) || SUBSTR(FECHA_COLOCACION,5,2) || SUBSTR(FECHA_COLOCACION,0,5)   FECHA_OTORGAMIENTO ,TIPO_NOMINA"+ 
				" from  "+TABLA_PRINCIPAL+"  N15 where   RUT_EMPRESA = 61607900 AND N15.PERIODO = '"+periodo+"' AND FECHA_COLOCACION > '20180508'" ;
		querys[2]="";
		
		
		
		return querys;
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(obtenerInfo0130(new HashMap())[1]);
		
	}
}
