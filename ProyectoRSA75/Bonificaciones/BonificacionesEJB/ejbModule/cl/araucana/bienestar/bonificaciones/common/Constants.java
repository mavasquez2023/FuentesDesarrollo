
package cl.araucana.bienestar.bonificaciones.common;

/**
 * @author Alejandro Sepúlveda
 */
public class Constants {
	
	//Estado de Rangos
	public static final int RANGO_VIGENTE=1;
	public static final int RANGO_HISTORICO=2;
	public static final int RANGO_FUTURO=3;
	
	//Pagar a
	public static final String PAGAR_A_CONVENIO="CONVENIO";
	public static final String PAGAR_A_PROFESIONALES="PROFESIONALES";
	public static final String PAGAR_A_SOCIO="SOCIO";
	
	// Forma de Pago
	public static final String PAGO_EFECTIVO="EFECTIVO";
	public static final String PAGO_CHEQUE="CHEQUE";	
	
	// Tipo Movimiento
	public static final String MOVI_INGRESO_ISAPRE="INGRESO_I";
	public static final String MOVI_INGRESO_OTROS="INGRESO_O";
	public static final String MOVI_EGRESO="EGRESO";	
	
	public static final long TIPO_USUARIO_SOCIO = 5;
	//OBSERVACION: como admin es != 5, no se definio una variable para este tipo de usuario

	//Parámetros configurados en BD BF35F1
	public static final String RUT_ARAUCANA= "RUT_ARAUCANA";
	public static final String DV_RUT_ARAUCANA= "DV_RUT_ARAUCANA";
	public static final String TRANS_REE_PATH_FILE= "TRANS_REE_PATH_FILE";
	public static final String TRANS_REE_NAME_FILE= "TRANS_REE_NAME_FILE";
	public static final String TRANS_REE_EXT_FILE= "TRANS_REE_EXT_FILE";
	public static final String TRANS_REE_TIPO_SERVICIO= "TRANS_REE_TIPO_SERVICIO";
	public static final String TRANS_REE_MEDIO_RESPALDO= "TRANS_REE_MEDIO_RESPALDO";
	public static final String TRANS_REE_MEDIO_RESPALDO_DET= "TRANS_REE_MEDIO_RESPALDO_DET";
	public static final String TRANS_REE_NUM_MEDIO_RESPALDO= "TRANS_REE_NUM_MEDIO_RESPALDO";
	public static final String TRANS_REE_GLOSA= "TRANS_REE_GLOSA";
	public static final String TRANS_REE_METODO_PAGO= "TRANS_REE_METODO_PAGO";
	public static final String TRANS_REE_COD_BANCO= "TRANS_REE_COD_BANCO";
	public static final String TRANS_REE_COD_SUCURSAL= "TRANS_REE_COD_SUCURSAL";
	public static final String TRANS_REE_REF_CLIENTE= "TRANS_REE_REF_CLIENTE";
	public static final String TRANS_REE_GLOSA_PAGO= "TRANS_REE_GLOSA_PAGO";	
	public static final String TRANS_REE_TIPO_CTA_= "TRANS_REE_TIPO_CTA_";
	public static final String TRANS_REE_MAIL_MODO_TEST= "TRANS_REE_MAIL_MODO_TEST";
	public static final String TRANS_REE_MAIL_TEST= "TRANS_REE_MAIL_TEST";
	
	public static final String TRANS_REE_SUMA_DIAS= "TRANS_REE_SUMA_DIAS";
	public static final String TRANS_REE_MAILHOSTLOCAL= "TRANS_REE_MAILHOSTLOCAL";
	public static final String TRANS_REE_MAILFROM= "TRANS_REE_MAILFROM";
	public static final String TRANS_REE_USERMAIL= "TRANS_REE_USERMAIL";
	public static final String TRANS_REE_PASSMAIL= "TRANS_REE_PASSMAIL";
	public static final String TRANS_REE_MAILHOSTTO= "TRANS_REE_MAILHOSTTO"; 
	public static final String TRANS_REE_MAILPORT= "TRANS_REE_MAILPORT";
	
	public static final String MAIL_MODO_TEST_NO= "NO";
	public static final String MAIL_MODO_TEST_1_MAIL= "1";
	public static final String MAIL_MODO_TEST_CADA_MAIL= "2";
	

}
