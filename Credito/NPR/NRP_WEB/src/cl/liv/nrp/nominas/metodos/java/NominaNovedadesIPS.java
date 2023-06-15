package cl.liv.nrp.nominas.metodos.java;

import java.util.HashMap;

import cl.jfactory.planillas.service.util.UtilLogWorkflow;
import cl.liv.comun.utiles.UtilesComunes;

public class NominaNovedadesIPS {

	public static String getPeriodoActual(){
		String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
		return periodo;
	}
	
	public static String[] obtenerQuerysNominaNovedades(HashMap pars){
		UtilLogWorkflow.debug("ejecutando nomina para 81");
		String[] querys = new String[3];
		querys[0]="";
		querys[1]=" select " 
				+ " RUT_PAGADOR RUTBEN, " 
				+ " DV_PAGADOR DVRBEN, "
				+ " CAJA_PREVISION CODINS, " 
				+ " EMPLANILLADO_SN TIPREG, "
				+ " 0 ATRIB, "
				+ " 225 CODDES, "
				+ " 2 UMDESC, "
				+ " nro_inscripcion, "
				+ " case LOCATE('-',nro_inscripcion) when 0 then decimal(SUBSTR(nro_inscripcion,0) )else decimal(SUBSTR(nro_inscripcion,1,LOCATE('-',nro_inscripcion)-1) ) end NUMINS, "
				+ " substr(nro_inscripcion,20,20) DVNINS, "
				+ " GRUPO_PAGO GRUPA, "
				+ " NUMERO_BENEFICIARIO NUMBE,  "
				+ " 0 NUMRET, "
				+ " 1 TIPMOV, "
				+ " COALESCE ( (select MAX(NOMBRE) FROM nrpdta.nrp8085v1 A \n" + 
				" WHERE A.RUT = RUT_PAGADOR \n" + 
				" and RIGHT('00000000000000000000'||substr(nro_inscripcion,1,18), 20 ) = RIGHT('00000000000000000000'|| A.nro_ins, 20 )\n" + 
				" \n" + 
				" ),(  TRIM(APELLIDO_PATERNO) ||' '|| TRIM(APELLIDO_MATERNO) ||' '|| TRIM(NOMBRE_DEUDOR) ))  NOMBEN,  "
				+ " IMPORTE_CUOTA_MONEDA_LOCAL, IMPORTE_CUOTA_MONEDA_LOCAL MONDE,  "
				+ " 00 FIJO, "
				+ " SUBSTR(FECHA_VCTO_NOMINA,7,2) || SUBSTR(FECHA_VCTO_NOMINA,5,2) || SUBSTR(FECHA_VCTO_NOMINA,1,5) FECVEN ,  "
				+ " ( "
				+ " RIGHT('00'|| DAY((date(concat(SUBSTR(FECHA_VCTO_NOMINA,1,4),concat('-', concat (SUBSTR(FECHA_VCTO_NOMINA,5,2) ,concat('-',SUBSTR(FECHA_VCTO_NOMINA,7,2)))))) + 1 DAY)),2 ) || "
				+ " RIGHT('00'|| MONTH((date(concat(SUBSTR(FECHA_VCTO_NOMINA,1,4),concat('-', concat (SUBSTR(FECHA_VCTO_NOMINA,5,2) ,concat('-',SUBSTR(FECHA_VCTO_NOMINA,7,2)))))) )),2 ) || "
				+ " YEAR((date(concat(SUBSTR(FECHA_VCTO_NOMINA,1,4),concat('-', concat (SUBSTR(FECHA_VCTO_NOMINA,5,2) ,concat('-',SUBSTR(FECHA_VCTO_NOMINA,7,2)))))) + 1 DAY)) "
				+ " ) FECINC, "
				+ " CANTIDAD_CUOTAS CANCUO, " 
				+ " concat(SUBSTR(PERIODO,5,2),SUBSTR(PERIODO,1,4)) FECMOV , "
				+ " 971 AGENCIA  , TIPO_NOMINA"
				+ " from nrpdta.nrp15ips where  EMPLANILLADO_SN = '3' or EMPLANILLADO_SN = '1' or EMPLANILLADO_SN = '2'  " ;
			
		
		querys[2]="";
		
		return querys;
	}
	

	
}