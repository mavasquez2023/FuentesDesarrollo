package cl.laaraucana.utils;

public final class ContantesCredito {
	
	// uso interno para mapeo del recordset del credito
	public static int OFIPRO = 1;
	public static int CREFOL = 2;
	public static int AFIRUT = 3;
	public static int AFIRUTDV = 4;
	public static int EMPRUT = 5;
	public static int EMPRUTDV = 6;
	public static int AFINOM = 7;
	public static int CMPA = 8;
	public static int CREOTOFEC = 9;
	public static int CRELINGLO = 10;	
	public static int CRENOMMON = 11;
	public static int CRECUOTOT = 12;
	public static int CRECUOMON = 13;
	public static int CREIMPMON = 14;
	public static int CREREAMON = 15;
	public static int CRENOMACU = 16;
	public static int CRETAS = 17;
	public static int CREDEUSIT = 18;
	public static int CREESTPTM = 19;
	public static int CRETOP = 20;
	public static int COBEST = 21;
	public static int RUTAVAL1 = 22;
	public static int RUTAVAL1DV = 23;
	public static int RUTAVAL2 = 24;
	public static int RUTAVAL2DV = 25;
	
	public static int CANTCUOTPAG = 26;
	public static int ESTULTCUOTA = 27;
	public static int PROXIMCUOTA = 28;
	public static int FECPROXVENC = 29;
	
	public static int SEGUROCESAN = 30;
	public static int SEGURODESAH = 31;
	

	// uso interno para mapeo del recordset de cuotas
	public static int CUOTA_CUONUM = 1;
	public static int CUOTA_CUOVENFEC = 2;
	public static int CUOTA_CUOMONCAP = 3;
	public static int CUOTA_CUOMONINT = 4;
	public static int CUOTA_CUOMONABO = 5;
	public static int CUOTA_CUOEST = 6;
	public static int CUOTA_OFICINA = 7;
	public static int CUOTA_FOLIO = 8;
	
	// uso interno para mapeo del recordset de pagos
	public static int PAGO_CUONUM = 1;
	public static int PAGO_CUOFECPAG = 2;
	public static int PAGO_CMCA = 3;
	public static int PAGO_CUODOCPAG = 4;
	public static int PAGO_CUODOCTIP = 5;
	public static int PAGO_CUOCAPPAG = 6;
	public static int PAGO_CUOINTPAG = 7;
	public static int PAGO_CUOESTPAG = 8;
	public static int PAGO_OFICINA = 9;
	public static int PAGO_FOLIO = 10;


	// uso interno para mapeo del recordset de pagos
	public static final String FECHA_PATTERN = "dd/MM/yyyy";
	public static final String FECHA_PATTERN2 = "yyMMdd";
	public static final String FECHA_PATTERN3 = "yyyyMMdd";
	public static final String FECHA_PATTERN4 = "yyyy-MM-dd";

//	 uso interno tipo de filtro de busqueda para creditos
	public final static int BUSCAR_TITULAR= 1;
	public final static int BUSCAR_FOLIO = 2;
	public final static int BUSCAR_EMPRESA = 3;
	public final static int BUSCAR_EMPRESA_TITULAR = 4;
	public final static int BUSCAR_AVAL = 5;
	public final static int BUSCAR_AFILIADO = 6;

//	 uso interno tipo de filtro de busqueda para creditos
	public final static int SEGURO_AFIRUT = 1;
	public final static int SEGURO_OFIPRO = 2;
	public final static int SEGURO_CREFOL = 3;
	public final static int SEGURO_PORPOLIZA = 4;
	public final static int SEGURO_MONTOSEG = 5;
	public final static int SEGURO_FECINICOB = 6;
	public final static int SEGURO_ESTADO = 7;
	public final static int SEGURO_SEGCARGO = 8;
	public final static int SEGURO_SEGFECEST = 9;
	public final static int SEGURO_SEGPOLIZA = 10;
	public final static int SEGURO_TIPOSEG = 11;


}
