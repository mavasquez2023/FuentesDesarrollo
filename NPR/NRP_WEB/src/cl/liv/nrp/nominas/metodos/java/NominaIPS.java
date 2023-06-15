package cl.liv.nrp.nominas.metodos.java;

import cl.jfactory.planillas.service.util.UtilLogWorkflow;
import cl.liv.comun.utiles.UtilesComunes;

import java.util.HashMap;

public class NominaIPS{
	public static String TABLA_PRINCIPAL = " nrpdta.nrp15f1 ";
	public static String FETCH_FIRST = " ";

    public NominaIPS()
    {
    }

    public static String getPeriodoActual()
    {
        String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
        return periodo;
    }

    public static String[] obtenerQuerysNomina81(HashMap pars)
    {
        String tablaPrincipal = TABLA_PRINCIPAL;
        if(pars != null && pars.get("TABLA_PRINCIPAL") != null)
            tablaPrincipal = pars.get("TABLA_PRINCIPAL").toString();
        UtilLogWorkflow.debug("ejecutando nomina para 81");
        String querys[] = new String[3];
        querys[0] = "";
        querys[1] = (new StringBuilder(" select  RUT_PAGADOR RUTBEN,  DV_PAGADOR DVRBEN,  CAJA_PREVISION CODINS,  2 TIPREG,  0 ATRIB,  225 CODDES,  2 UMDESC,  nro_inscripcion,  case LOCATE('-',nro_inscripcion) when 0 then decimal(SUBSTR(nro_inscripcion,0) )else decimal(SUBSTR(nro_inscripcion,1,LOCATE('-',nro_inscripcion)-1) ) end NUMINS,  substr(nro_inscripcion,20,20) DVNINS,  GRUPO_PAGO GRUPA,  NUMERO_BENEFICIARIO NUMBE,   0 NUMRET,  1 TIPMOV,  COALESCE ( (select MAX(NOMBRE) FROM nrpdta.nrp8085v1 A \n WHERE A.RUT = RUT_PAGADOR \n and RIGHT('00000000000000000000'||substr(nro_inscripcion,1,18), 20 ) = RIGHT('00000000000000000000'|| A.nro_ins, 20 )\n \n ),(  TRIM(APELLIDO_PATERNO) ||' '|| TRIM(APELLIDO_MATERNO) ||' '|| TRIM(NOMBRE_DEUDOR) ))  NOMBEN,   IMPORTE_CUOTA_MONEDA_LOCAL, IMPORTE_CUOTA_MONEDA_LOCAL MONDE,   00 FIJO,  SUBSTR(FECHA_VCTO_NOMINA,7,2) || SUBSTR(FECHA_VCTO_NOMINA,5,2) || SUBSTR(FECHA_VCTO_NOMINA,1,5) FECVEN ,   '01' || SUBSTR(FECHA_VCTO_NOMINA,5,2) || SUBSTR(FECHA_VCTO_NOMINA,1,4) FECINC,  CANTIDAD_CUOTAS CANCUO,  concat(SUBSTR(PERIODO,5,2),SUBSTR(PERIODO,1,4)) FECMOV ,  971 AGENCIA  , TIPO_NOMINA, CANTIDAD_CUOTAS CANCUO_AUX from ")).append(tablaPrincipal).append(" where rut_empresa = 61533000 and periodo = '").append(getPeriodoActual()).append("' and caja_prevision in (01,06,07,09,10,11,12,14,16,44,45,57)").append(FETCH_FIRST).toString();
        querys[2] = "";
        return querys;
    }

    public static String[] obtenerQuerysNomina82(HashMap pars)
    {
        String tablaPrincipal = TABLA_PRINCIPAL;
        if(pars != null && pars.get("TABLA_PRINCIPAL") != null)
            tablaPrincipal = pars.get("TABLA_PRINCIPAL").toString();
        UtilLogWorkflow.debug("ejecutando nomina para 82");
        String querys[] = new String[3];
        querys[0] = "";
        querys[1] = (new StringBuilder(" select  RUT_PAGADOR RUTBEN,  DV_PAGADOR DVRBEN,  CAJA_PREVISION CODINS,  2 TIPREG,  0 ATRIB,  225 CODDES,  2 UMDESC,  nro_inscripcion,  case LOCATE('-',nro_inscripcion) when 0 then decimal(SUBSTR(nro_inscripcion,0) )else decimal(SUBSTR(nro_inscripcion,1,LOCATE('-',nro_inscripcion)-1) ) end NUMINS,  substr(nro_inscripcion,20,20) DVNINS,  GRUPO_PAGO GRUPA,  NUMERO_BENEFICIARIO NUMBE,   0 NUMRET,  1 TIPMOV,  COALESCE ( (select MAX(NOMBRE) FROM nrpdta.nrp8085v1 A \n WHERE A.RUT = RUT_PAGADOR \n and RIGHT('00000000000000000000'||substr(nro_inscripcion,1,18), 20 ) = RIGHT('00000000000000000000'|| A.nro_ins, 20 )\n \n ),(  TRIM(APELLIDO_PATERNO) ||' '|| TRIM(APELLIDO_MATERNO) ||' '|| TRIM(NOMBRE_DEUDOR) ))  NOMBEN,   IMPORTE_CUOTA_MONEDA_LOCAL, IMPORTE_CUOTA_MONEDA_LOCAL MONDE,   00 FIJO,  SUBSTR(FECHA_VCTO_NOMINA,7,2) || SUBSTR(FECHA_VCTO_NOMINA,5,2) || SUBSTR(FECHA_VCTO_NOMINA,1,5) FECVEN ,   '01' || SUBSTR(FECHA_VCTO_NOMINA,5,2) || SUBSTR(FECHA_VCTO_NOMINA,1,4) FECINC,  CANTIDAD_CUOTAS CANCUO,  concat(SUBSTR(PERIODO,5,2),SUBSTR(PERIODO,1,4)) FECMOV ,  971 AGENCIA  ,TIPO_NOMINA, CANTIDAD_CUOTAS CANCUO_AUX  from  ")).append(tablaPrincipal).append(" where rut_empresa = 61533000  and periodo = '").append(getPeriodoActual()).append("' and caja_prevision in (01, 06, 07, 10, 11, 12, 14, 16, 45) ").append(FETCH_FIRST).toString();
        querys[2] = "";
        return querys;
    }

    public static String[] obtenerQuerysNomina83(HashMap pars)
    {
        String tablaPrincipal = TABLA_PRINCIPAL;
        if(pars != null && pars.get("TABLA_PRINCIPAL") != null)
            tablaPrincipal = pars.get("TABLA_PRINCIPAL").toString();
        UtilLogWorkflow.debug("ejecutando nomina para 83");
        String querys[] = new String[3];
        querys[0] = "";
        querys[1] = (new StringBuilder(" select  RUT_PAGADOR RUTBEN,  DV_PAGADOR DVRBEN,  CAJA_PREVISION CODINS,  2 TIPREG,  0 ATRIB,  225 CODDES,  2 UMDESC,  nro_inscripcion,  case LOCATE('-',nro_inscripcion) when 0 then decimal(SUBSTR(nro_inscripcion,0) )else decimal(SUBSTR(nro_inscripcion,1,LOCATE('-',nro_inscripcion)-1) ) end NUMINS,  substr(nro_inscripcion,20,20) DVNINS,  GRUPO_PAGO GRUPA,  NUMERO_BENEFICIARIO NUMBE,   0 NUMRET,  1 TIPMOV,  COALESCE ( (select MAX(NOMBRE) FROM nrpdta.nrp8085v1 A \n WHERE A.RUT = RUT_PAGADOR \n and RIGHT('00000000000000000000'||substr(nro_inscripcion,1,18), 20 ) = RIGHT('00000000000000000000'|| A.nro_ins, 20 )\n \n ),(  TRIM(APELLIDO_PATERNO) ||' '|| TRIM(APELLIDO_MATERNO) ||' '|| TRIM(NOMBRE_DEUDOR) ))  NOMBEN,   IMPORTE_CUOTA_MONEDA_LOCAL, IMPORTE_CUOTA_MONEDA_LOCAL MONDE,   00 FIJO,  SUBSTR(FECHA_VCTO_NOMINA,7,2) || SUBSTR(FECHA_VCTO_NOMINA,5,2) || SUBSTR(FECHA_VCTO_NOMINA,1,5) FECVEN ,   '01' || SUBSTR(FECHA_VCTO_NOMINA,5,2) || SUBSTR(FECHA_VCTO_NOMINA,1,4) FECINC,  CANTIDAD_CUOTAS CANCUO,  concat(SUBSTR(PERIODO,5,2),SUBSTR(PERIODO,1,4))  FECMOV ,  971 AGENCIA  ,TIPO_NOMINA, CANTIDAD_CUOTAS CANCUO_AUX  from  ")).append(tablaPrincipal).append("  where rut_empresa = 61533000  and periodo = '").append(getPeriodoActual()).append("'  and caja_prevision in (02, 05, 08, 13, 15, 17, 18, 20, 21, 22, 23, 24, 39, 51, 58, 94)").append(FETCH_FIRST).toString();
        querys[2] = "";
        return querys;
    }

    public static String[] obtenerQuerysNomina41(HashMap pars)
    {
        String tablaPrincipal = TABLA_PRINCIPAL;
        if(pars != null && pars.get("TABLA_PRINCIPAL") != null)
            tablaPrincipal = pars.get("TABLA_PRINCIPAL").toString();
        UtilLogWorkflow.debug("ejecutando nomina para 41");
        String querys[] = new String[3];
        querys[0] = "";
        querys[1] = (new StringBuilder(" select  RUT_PAGADOR RUTBEN,  DV_PAGADOR DVRBEN,  CAJA_PREVISION CODINS,  2 TIPREG,  0 ATRIB,  225 CODDES,  2 UMDESC,  nro_inscripcion,  case LOCATE('-',nro_inscripcion) when 0 then decimal(SUBSTR(nro_inscripcion,0) )else decimal(SUBSTR(nro_inscripcion,1,LOCATE('-',nro_inscripcion)-1) ) end NUMINS,  substr(nro_inscripcion,20,20) DVNINS,  GRUPO_PAGO GRUPA,  NUMERO_BENEFICIARIO NUMBE,   0 NUMRET,  1 TIPMOV,  COALESCE ( (select MAX(NOMBRE) FROM nrpdta.nrp8085v1 A \n WHERE A.RUT = RUT_PAGADOR \n and RIGHT('00000000000000000000'||substr(nro_inscripcion,1,18), 20 ) = RIGHT('00000000000000000000'|| A.nro_ins, 20 )\n \n ),(  TRIM(APELLIDO_PATERNO) ||' '|| TRIM(APELLIDO_MATERNO) ||' '|| TRIM(NOMBRE_DEUDOR) ))  NOMBEN,   IMPORTE_CUOTA_MONEDA_LOCAL, IMPORTE_CUOTA_MONEDA_LOCAL MONDE,   00 FIJO,  SUBSTR(FECHA_VCTO_NOMINA,7,2) || SUBSTR(FECHA_VCTO_NOMINA,5,2) || SUBSTR(FECHA_VCTO_NOMINA,1,5) FECVEN ,   '01' || SUBSTR(FECHA_VCTO_NOMINA,5,2) || SUBSTR(FECHA_VCTO_NOMINA,1,4) FECINC,  CANTIDAD_CUOTAS CANCUO,  concat(SUBSTR(PERIODO,5,2),SUBSTR(PERIODO,1,4)) FECMOV ,  971 AGENCIA  ,TIPO_NOMINA, CANTIDAD_CUOTAS CANCUO_AUX  from ")).append(tablaPrincipal).append(" where rut_empresa = 61533000 and  periodo = '").append(getPeriodoActual()).append("' and caja_prevision in (41, 61) ").append(FETCH_FIRST).toString();
        querys[2] = "";
        return querys;
    }

    public static void main(String args[])
    {
        String querys[] = obtenerQuerysNomina41(null);
        System.out.println(querys[1]);
    }

  

}